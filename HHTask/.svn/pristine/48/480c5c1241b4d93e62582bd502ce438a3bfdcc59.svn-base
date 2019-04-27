package com.task.ServerImpl.menjin;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.menjin.AccessServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.ServerImpl.SmsServiceImpl;
import com.task.ServerImpl.onemark.OneLightServerImpl;
import com.task.entity.menjin.Access;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.AccessWebcam;
import com.task.entity.menjin.CarInOutType;
import com.task.entity.menjin.InEmployeeCarInfor;
import com.task.entity.menjin.Visit;
import com.task.entity.onemark.OneLight;
import com.task.entity.system.CompanyInfo;
import com.task.util.LedCarPush;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class AccessServerImpl implements AccessServer {
	private TotalDao totalDao;

	public static final String triggerCmd = "{\"cmd\" :\"trigger\"}";// 触发车牌识别
	public static final String openCmd = "{\"cmd\" :\"ioctl\",\"io\" : 0,\"value\" :2,\"delay\" :500}";// 高状态方波（开门）
	// public static final String infoCmd =
	// "{\"Response_AlarmInfoPlate\":{\"info\":\"ok\",\"content\":\"...\",\"is_pay\":\"true\"}}";
	// public static final String openCmd =
	// "{\"cmd\" :\"ioctl\",\"io\" :\"0\",\"value\" :\"2\",\"delay\" :\"500\"}";//
	// 高状态方波（开门）
	public static final String getsnCmd = "{\"cmd\" :\"getsn\"}";// 序列号
	public static final String getivsresultCmd = "{\"cmd\" : \"getivsresult\",\"image\":true}";// 获取最近一次识别结果
	public static final int DISABLE_PUSH = 0;
	public static final int ENABLE_PUSH = 1;
	public static final int JSON_FMT = 1;
	public static final int BIN_FMT = 0;
	public static final int DISABLE_IMAGE = 0;
	public static final int ENABLE_IMAGE = 1;
	public static final int BLOCK_TYPE_BIN_RESULT = 1;
	public static final int BLOCK_TYPE_IMAGE_DATA = 2;
	public static String ivs = "";

	public static boolean openDoor(String ip) {
		// TODO Auto-generated method stub
		boolean b = false;
		Socket socket = null;
		try {
			// ///////////****************开门******************************/////////////////
			socket = new Socket(ip, 8131);
			b = sendCmd(socket, openCmd);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e);
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	/** 获取最近一次记录 **/
	public static String getivsresultCmd(String ip) {
		// TODO Auto-generated method stub
		boolean b = false;
		Socket socket = null;
		try {
			// ///////////****************获取最近一次记录******************************/////////////////
			socket = new Socket(ip, 8131);
			// 配置的方式：主动推送，JSON格式，带图片
			configFormat(socket, ENABLE_PUSH, JSON_FMT, ENABLE_IMAGE);
			b = sendCmd(socket, getivsresultCmd);

			int count = 0;

			boolean run = true;

			socket.setSoTimeout(2 * 1000);
			while (run) {
				if (count > 3) {
					sendKeepAlive(socket);
					count = 0;
				}

				count++;

				int packetLen = recvPacketSize(socket);
				if (packetLen > 0) {
					byte[] data = new byte[packetLen];
					int recvLen = recvBlock(socket, data, packetLen);
					if (recvLen > 0) {
						onRecv(data, recvLen);
						break;
					} else {
						System.out.println("socket error!");
						break;
					}
				} else if (packetLen == 0) { // 接收到心跳包
					System.out.println("recv a keep-alive packet!");
					break;
				} else {
					System.out.println("msg fmt wrong!");
					break;
				}
			}
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e);
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ivs;
	}

	/**
	 * 重新触发，获取车牌
	 */
	public static String RriggerCmd(String ip) {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			ivs = "";
			socket = new Socket(ip, 8131);
			sendCmd(socket, triggerCmd);// 触发车牌识别
			return "触发成功！";
		} catch (Exception e) {
			System.out.println("Error:" + e);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @param len
	 */
	public static void parseBinIVSResult(byte[] data, int len) {
		int pos = 0;

		if (data[2] == BLOCK_TYPE_BIN_RESULT) {
			int blockSize = convBytesToInt(data, 4);
			// data 8开始blockSize为结构体数据
			// 图片数据
			pos = 8 + blockSize;
			if (data[pos] == 'I' && data[pos + 1] == 'R') {
				if (data[pos + 2] == BLOCK_TYPE_IMAGE_DATA) {
					@SuppressWarnings("unused")
					int imageSize = convBytesToInt(data, pos + 4);
					// data pos+8开始imageSize具体的图片数据
					// saveImage(data,pos,len);
				}
			}
		}
	}

	/**
	 * 
	 * @param data
	 * @param len
	 */
	public static void onIVSResultRecv(byte[] data, int len) {
		// 接收到识别结果的处理
		if (data[0] == 'I' && data[1] == 'R') {
			// 二进制的结构体处理
			parseBinIVSResult(data, len);
		} else {
			// json处理
			int pos = 0;
			while (true) {
				if (data[pos] == 0) {
					break;
				}
				pos++;
			}
			ivs = "";
			ivs = new String(data, 0, pos);
			System.out.println("1:" + ivs);

			// 此处改为通过json来解析车牌识别结果,来获取车牌图片的大小
			int nImgSize = len - pos - 1;

			// 获取图片的大小
			saveImage(data, pos + 1, nImgSize, "D:\\test__1.jpg");
		}
	}

	/**
	 * 
	 * @param data
	 * @param len
	 * @throws UnsupportedEncodingException
	 */
	public static void onRecv(byte[] data, int len)
			throws UnsupportedEncodingException {
		// String ivs = new String(data,"UTF-8" );
		System.out.println("recved:" + len);
		if (len > 20 * 1024) {
			// 带图片数据
			onIVSResultRecv(data, len);
		} else {
			// 普通的指令响应
			onIVSResultRecv(data, len);
		}
	}

	/**
	 * 
	 * @param socket
	 * @param cmd
	 * @return
	 */
	public static boolean sendCmd(Socket socket, String cmd) {
		try {
			System.out.println(cmd);
			int len = cmd.length();
			byte[] header = { 'V', 'Z', 0, 0, 0, 0, 0, 0 };
			header[4] += (byte) ((len >> 24) & 0xFF);
			header[5] += (byte) ((len >> 16) & 0xFF);
			header[6] += (byte) ((len >> 8) & 0xFF);
			header[7] += (byte) (len & 0xFF);

			OutputStream out = socket.getOutputStream();
			out.write(header);
			out.write(cmd.getBytes());
			if (cmd.equals(openCmd)) {
				System.out.println("开门" + openCmd);
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Error:" + e);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param socket
	 * @return
	 */
	public static boolean sendKeepAlive(Socket socket) {
		try {
			byte[] buff = { 'V', 'Z', 1, 0, 0, 0, 0, 0 };

			OutputStream out = socket.getOutputStream();
			out.write(buff);
			out.close();
		} catch (Exception e) {
			System.out.println("Error:" + e);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param socket
	 * @param enable
	 * @param fmt
	 * @param image
	 * @return
	 */
	public static boolean configFormat(Socket socket, int enable, int fmt,
			int image) {
		String cmd;
		cmd = String.format("{" + "\"cmd\" : \"ivsresult\","
				+ "\"enable\" : %s," + "\"format\" : \"%s\","
				+ "\"image\" : %s" + "}", enable != 0 ? "true" : "false",
				fmt != 0 ? "json" : "bin", image != 0 ? "true" : "false");
		System.out.println(cmd);
		return sendCmd(socket, cmd);
	}

	/**
	 * 
	 * @param buff
	 * @param offset
	 * @return
	 */
	public static int convBytesToInt(byte[] buff, int offset) {
		// 4bytes 转为int，要考虑机器的大小端问题
		int len, byteValue;
		len = 0;
		byteValue = (0x000000FF & ((int) buff[offset]));
		len += byteValue << 24;
		byteValue = (0x000000FF & ((int) buff[offset + 1]));
		len += byteValue << 16;
		byteValue = (0x000000FF & ((int) buff[offset + 2]));
		len += byteValue << 8;
		byteValue = (0x000000FF & ((int) buff[offset + 3]));
		len += byteValue;
		return len;
	}

	/**
	 * 
	 * @param socket
	 * @return
	 */
	public static int recvPacketSize(Socket socket) {
		byte[] header = new byte[8];
		int recvLen = recvBlock(socket, header, 8);
		if (recvLen <= 0) {
			return -1;
		}

		if (header[0] != 'V' || header[1] != 'Z') {
			// 格式不对
			return -1;
		}

		if (header[2] == 1) {
			// 心跳包
			return 0;
		}

		return convBytesToInt(header, 4);
	}

	/**
	 * 
	 * @param socket
	 * @param buff
	 * @param len
	 * @return
	 */
	// 接收指定长度的数据，收完为止
	public static int recvBlock(Socket socket, byte[] buff, int len) {
		try {
			InputStream in = socket.getInputStream();
			int totleRecvLen = 0;
			int recvLen;
			System.out.println("len:" + len);
			while (totleRecvLen < len) {
				recvLen = in.read(buff, totleRecvLen, len - totleRecvLen);
				totleRecvLen += recvLen;
				if (totleRecvLen < 0) {
					break;
				}
			}
			return len;
		} catch (Exception e) {
			System.out.println("recvBlock timeout!");
			// System.out.println("Error:"+e);
			return -1;
		}
	}

	/**
	 * 将图片储存到目标文件夹
	 * 
	 * @param buff
	 * @param pos
	 * @param len
	 * @param imgPath
	 * @return
	 */
	public static int saveImage(byte[] buff, int pos, int len, String imgPath) {
		int ret = -1;
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					imgPath));
			out.write(buff, pos, len);
			out.close();
		} catch (IOException io) {
			System.out.println("save image failed " + imgPath);
		}

		return ret;
	}

	@Override
	public String addAccess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccess(Access access) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<Integer, Object> findAccessByCondition(Access access,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (access == null) {
			access = new Access();
		}
		String sql = "";
		String hql = totalDao.criteriaQueries(access, sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean updateAccess(Access access) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 生成门禁记录表信息的接口 **/
	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		AccessServerImpl acc = new AccessServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	/**
	 * 生成来访记录方法
	 * 
	 * @author Li_Cong
	 * 
	 * @param verifycar
	 *            是否开车
	 * @param verification
	 *            验证类型（手机/车牌）
	 * @param outInname
	 *            出入人名称
	 * @param time
	 *            申请来访时间
	 * @param carPar
	 *            来访车牌
	 * @param cordId
	 *            员工卡
	 * @param yanzhengNum
	 *            手机号
	 * @param classs
	 *            对应实体类
	 * @param entityId
	 *            对应实体类Id
	 * @throws Exception
	 * 
	 * @return 验证凭证
	 */
	public static Access createAccessRecord(String verifycar,
			String verification, String outInname, String time, String carPar,
			String cordId, String yanzhengNum, Class classs, Integer entityId)
			throws Exception {
		if (verification != null && verification.length() > 0) {
			if (outInname != null && outInname.length() > 0) {
				if (time != null && time.length() > 0) {
					if (yanzhengNum != null && yanzhengNum.length() > 0
							|| carPar != null || cordId != null) {
						if (classs != null) {
							if (entityId != null && entityId > 0) {
								TotalDao totalDao = createTotol();
								Access access = new Access();
								access.setOutInName(outInname);
								access.setYanzheng(verification);
								// 根据是否开车得到对应开哪一种门
								String dao = null;
								String comeIn = "进门";
								if ("是".equals(verifycar)) {
									dao = "车行道";
								} else if ("否".equals(verifycar)) {
									dao = "人行道";
								} else {
									dao = "";
								}
								access.setEquipmentDaoType(dao);
								access.setNextSend("已生成");
								access.setOutInDoor(comeIn);

								// 根据 门的种类 和 进出 判断IP
								AccessEquipment accessEquipment = (AccessEquipment) totalDao
										.getObjectByCondition(
												"from AccessEquipment where equipmentDaoType=? and equipmentOutIn=?",
												dao, comeIn);
								if (accessEquipment != null) {
									access.setEquipmentIP(accessEquipment
											.getEquipmentIP());// 给门禁对比表中加上ip
									/**************************** 位置是重要字段，后期多设备，依靠位置查询，页面获得************************************/
									access.setEquipmentLocation(accessEquipment
											.getEquipmentLocation());// 给门禁对比表中加上进门位置
									if ("车行道".equals(dao)) {
										AccessWebcam accessWebcam = (AccessWebcam) totalDao
												.getObjectByCondition(
														"from AccessWebcam where aeqt_ip=? and webcamOutIn='进门' and webcamLocation=?",
														accessEquipment
																.getEquipmentIP(),
														accessEquipment
																.getEquipmentLocation());
										if (accessWebcam != null) {
											access.setAccess_EquIp(accessWebcam
													.getWebcamIP());
										}
									}
								}
								access.setEntityId(entityId);
								access.setAddTime(Util.getDateTime());
								access.setEntityName(classs.getSimpleName());
								access.setUseSend("未使用");
								access.setVisitstime(time);
								access.setFailtime(getSpecifiedDayAfter(time));// 失效日期
								access.setDoorStatus("来访");
								String yanzhengnum = yanNumber();
								access.setYanzhengnum(yanzhengnum);
								// 生成验证码的同时将短信发送至手机号码
								SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
								smsServiceImpl.send(yanzhengNum, "尊敬的"
										+ outInname + "(先生/女士),您好! 您申请在 "
										+ time + " 日来访验证码为：" + yanzhengnum
										+ " 验证码当天有效。请从 " + dao + " 进门\n    在来访期间此验证码也可做其它门禁开门凭证使用。");
								System.out.println(yanzhengNum+ "+尊敬的"
										+ outInname + "(先生/女士),您好! 您申请在 "
										+ time + " 日来访验证码为：" + yanzhengnum
										+ " 验证码当天有效。请从 " + dao + " 进门\n    在来访期间此验证码也可做其它门禁开门凭证使用。");
								if ("车牌".equals(verification)) {
									access.setCarPai(carPar);
								}
								totalDao.save(access);// 添加数据
								return access;// 直接返回车牌号
							} else {
								throw new Exception("对应实体类Id必须填写!");
							}
						} else {
							throw new Exception("对应实体类必须填写!");
						}
					} else {
						throw new Exception("手机号/车牌/员工卡号必须填写一个!");
					}
				} else {
					throw new Exception("申请时间必须填写!");
				}
			} else {
				throw new Exception("出入人名称必须填写!");
			}
		} else {
			throw new Exception("验证类型（手机或车牌）必须填写!");
		}
	}

	/**
	 * 生成紧急出门凭证方法
	 * 
	 * @author Li_Cong
	 * 
	 * @param verifycar
	 *            是否开车
	 * @param verification
	 *            验证类型（手机/车牌）
	 * @param comeIn
	 *            进门出门
	 * @param outInname
	 *            出入人名称
	 * @param time
	 *            申请来访时间
	 * @param carPar
	 *            来访车牌
	 * @param cordId
	 *            员工卡
	 * @param yanzhengNum
	 *            手机号
	 * @param classs
	 *            对应实体类
	 * @param entityId
	 *            对应实体类Id
	 * @param waitCheck
	 *            紧急/待检
	 * @throws Exception
	 * 
	 * @return 验证凭证
	 */
	public static String createAccessJinJOut(String verifycar,
			String verification, String comeIn, String outInname, String time,
			String carPar, String cordId, String yanzhengNum, Class classs,
			Integer entityId) throws Exception {
		if (verification != null && verification.length() > 0) {
			if (outInname != null && outInname.length() > 0) {
				if (time != null && time.length() > 0) {
					if (yanzhengNum != null && yanzhengNum.length() > 0
							|| carPar != null || cordId != null) {
						if (classs != null) {
							if (entityId != null && entityId > 0) {
								TotalDao totalDao = createTotol();
								Access access = new Access();
								access.setOutInName(outInname);
								access.setYanzheng(verification);
								// 根据是否开车得到对应开哪一种门
								String dao = null;
								if ("是".equals(verifycar)) {
									dao = "车行道";
								} else if ("否".equals(verifycar)) {
									dao = "人行道";
								} else {
									dao = "";
								}
								access.setEquipmentDaoType(dao);
								access.setNextSend("已生成");
								access.setOutInDoor(comeIn);

								// 根据 门的种类 和 进出 判断IP
								AccessEquipment accessEquipment = (AccessEquipment) totalDao
										.getObjectByCondition(
												"from AccessEquipment where equipmentDaoType=? and equipmentOutIn=?",
												dao, comeIn);
								if (accessEquipment != null) {
									access.setEquipmentIP(accessEquipment
											.getEquipmentIP());// 给门禁对比表中加上ip
									/**************************** 位置是重要字段，后期多设备，依靠位置查询，页面获得************************************/
									access.setEquipmentLocation(accessEquipment
											.getEquipmentLocation());// 给门禁对比表中加上进门位置
									if ("车行道".equals(dao)) {
										AccessWebcam accessWebcam = (AccessWebcam) totalDao
												.getObjectByCondition(
														"from AccessWebcam where aeqt_ip=? and webcamOutIn='进门' and webcamLocation=?",
														accessEquipment
																.getEquipmentIP(),
														accessEquipment
																.getEquipmentLocation());
										if (accessWebcam != null) {
											access.setAccess_EquIp(accessWebcam
													.getWebcamIP());
										}
									}
								}
								access.setEntityId(entityId);
								access.setAddTime(Util.getDateTime());
								access.setEntityName(classs.getSimpleName());
								access.setUseSend("未使用");
								access.setVisitstime(time);
								access.setFailtime((Util.getSpecifiedDayAfter(
										time, 2)));// 失效日期
								if ("手机".equals(verification)) {
									access.setDoorStatus("来访");
									// 根据添加
									String yanzhengnum = yanNumber();
									access.setYanzhengnum(yanzhengnum);
									totalDao.save(access);// 添加数据
									return yanzhengnum;// 返回生成的验证码
								} else if ("车牌".equals(verification)) {
									access.setDoorStatus("来访");
									access.setCarPai(carPar);
									totalDao.save(access);// 添加数据
									return carPar;// 直接返回车牌号
								} else if ("员工卡".equals(verification)) {
									// 根据添加
									access.setCardId(cordId);// 卡号添加
									// 还得添加车牌

									totalDao.save(access);// 添加数据
									return yanzhengNum;// 返回员工卡号
								} else {
									throw new Exception("验证类型（手机，车牌，员工卡）不能为其他值");
								}
							} else {
								throw new Exception("对应实体类Id必须填写!");
							}
						} else {
							throw new Exception("对应实体类必须填写!");
						}
					} else {
						throw new Exception("手机号/车牌号/员工卡号必须填写一个!");
					}
				} else {
					throw new Exception("申请时间必须填写!");
				}
			} else {
				throw new Exception("出入人名称必须填写!");
			}
		} else {
			throw new Exception("验证类型（手机或车牌）必须填写!");
		}
	}

	/**
	 * 生成出门信息
	 * 
	 * @param verification
	 *            验证类型（员工卡）
	 * @param outInname
	 *            出入人名称
	 * @param time
	 *            申请来访时间
	 * @param yanzhengNum
	 *            出入验证对象（员工卡号）
	 * @param code
	 *            用户工号(用来获取车牌)
	 * @param classs
	 *            对应实体类
	 * @param entityId
	 *            对应实体类Id
	 * @param carPai
	 *            填写车牌
	 * @throws Exception
	 * 
	 */
	public static String createAccessRecordCode(String verification,
			String outInname, String time, String yanzhengNum, String code,
			Class classs, Integer entityId, String carPai) throws Exception {
		if (verification != null && verification.length() > 0) {
			if (outInname != null && outInname.length() > 0) {
				if (time != null && time.length() > 0) {
					if (yanzhengNum != null && yanzhengNum.length() > 0) {
						if (classs != null) {
							if (entityId != null && entityId > 0) {
								TotalDao totalDao = createTotol();
								Access access = new Access();
								access.setOutInName(outInname);
								access.setYanzheng(verification);
								access.setNextSend("已生成");
								access.setOutInDoor("进门");
								access.setEntityId(entityId);
								access.setAddTime(Util.getDateTime());
								access.setEntityName(classs.getSimpleName());
								access.setUseSend("未使用");
								access.setVisitstime(time);
								access.setFailtime(getSpecifiedDayAfter(time));// 失效日期
								if ("员工卡".equals(verification)) {
									// 根据添加
									access.setCardId(yanzhengNum);// 员工卡号
									if (!"".equals(carPai) && carPai != null) {
										access.setCarPai(carPai);
										// 判断是否为白名单车辆
										InEmployeeCarInfor inEmployeeCarInfor = (InEmployeeCarInfor) totalDao
												.getObjectByCondition(
														"from InEmployeeCarInfor where nplates =? and whiteCar='是'",
														carPai);
										if (inEmployeeCarInfor != null) {
											inEmployeeCarInfor
													.setBorrowStatus("出借");
											totalDao.update(inEmployeeCarInfor);
										}
									} else {
										// 添加车牌 根据工号
										List inEmployeeCarInforlist = totalDao
												.query(
														"from InEmployeeCarInfor where ncode =? and carType='个人' order by id desc",
														code);
										if (inEmployeeCarInforlist != null
												&& inEmployeeCarInforlist
														.size() > 0) {
											InEmployeeCarInfor inEmployeeCarInfor = (InEmployeeCarInfor) inEmployeeCarInforlist
													.get(0);
											access.setCarPai(inEmployeeCarInfor
													.getNplates());
										}
									}
									// 门禁凭证类型
									if ("AskForLeave".equals(classs
											.getSimpleName())) {
										access.setDoorStatus("请假");
									} else if ("Overtime".equals(classs
											.getSimpleName())) {
										access.setDoorStatus("加班");
									} else {
										access.setDoorStatus("其他");
									}
									totalDao.save(access);// 添加数据
									return yanzhengNum;// 返回员工卡号
								} else {
									throw new Exception("验证类型员工卡号，不能为其他值");
								}
							} else {
								throw new Exception("对应实体类Id必须填写!");
							}
						} else {
							throw new Exception("对应实体类必须填写!");
						}
					} else {
						throw new Exception("员工卡号必须填写!");
					}
				} else {
					throw new Exception("申请时间必须填写!");
				}
			} else {
				throw new Exception("出入人名称必须填写!");
			}
		} else {
			throw new Exception("验证类型必须填写!");
		}
	}

	/**
	 * 来访对象出
	 * 
	 * @param Class
	 *            来访对象（不能为空）
	 * @throws Exception
	 * 
	 */
	public static String updateAccessRecord(Class class1, Integer id, String tel)
			throws Exception {
		if (class1 != null) {
			TotalDao totalDao = createTotol();
			List list = totalDao.query(
					"from Access where entityName=? and entityId=?", class1
							.getSimpleName(), id);
			if (list != null && list.size() > 0) {
				Access oldAccess = (Access) list.get(0);
				Access access2 = new Access();
				if (oldAccess != null) {
					if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
						BeanUtils.copyProperties(oldAccess, access2,
								new String[] { "id", "addTime", "outInDoor",
										"failtime", "visitstime", "useSend",
										"nextSend", "access_EquIp" });
					} else if ("人行道".equals(oldAccess.getEquipmentDaoType())) {
						BeanUtils.copyProperties(oldAccess, access2,
								new String[] { "id", "addTime", "outInDoor",
										"failtime", "visitstime", "useSend",
										"nextSend", "equipmentIP" });
					} else {
					}
				} else {
					throw new Exception("不存在该条数据");
				}
				String addtime = Util.getDateTime();
				access2.setAddTime(addtime);
				access2.setVisitstime(addtime);
				access2.setUseSend("已生成");
				access2.setNextSend("已生成");
				String comeIn = "出门";
				access2.setOutInDoor(comeIn);
				// 根据 门的种类 和 进出 判断IP
				AccessEquipment accessEquipment = null;
				if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
					accessEquipment = (AccessEquipment) totalDao
							.getObjectByCondition(
									"from AccessEquipment where equipmentDaoType=?",
									access2.getEquipmentDaoType());
				} else if ("人行道".equals(oldAccess.getEquipmentDaoType())) {
					accessEquipment = (AccessEquipment) totalDao
							.getObjectByCondition(
									"from AccessEquipment where equipmentDaoType=? and equipmentOutIn=?",
									access2.getEquipmentDaoType(), comeIn);
				} else {
					throw new Exception("请选择车道（车行道|人行道）不能为其他值");
				}
				if (accessEquipment != null) {
					access2.setEquipmentIP(accessEquipment.getEquipmentIP());// 给门禁对比表中加上IP
					if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
						AccessWebcam accessWebcam = (AccessWebcam) totalDao
								.getObjectByCondition(
										"from AccessWebcam where aeqt_ip=? and webcamOutIn='出门' and webcamLocation=?",
										accessEquipment.getEquipmentIP(),
										accessEquipment.getEquipmentLocation());
						if (accessWebcam != null) {
							access2.setAccess_EquIp(accessWebcam.getWebcamIP());
						}
						access2.setWaitCheck(TO_BE_CHECKED);
					}
				}
				// visit.getClass().getSimpleName();//反射机制获取类名
				access2.setFailtime(getSpecifiedminuteAfter(addtime, 30));// 失效日期
				if ("手机".equals(access2.getYanzheng())) {
					// 根据添加
					String yanzhengnum = yanNumber();
					access2.setYanzhengnum(yanzhengnum);
					totalDao.save(access2);// 添加数据
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(tel, "尊敬的" + access2.getOutInName()
							+ "(先生/女士),您好! 您的来访已结束, "
							+ oldAccess.getEquipmentDaoType() + " 出门验证码为:"
							+ yanzhengnum + " 有效期为半小时。请在有效期内出门！谢谢合作");
					System.out.println(tel+ "+尊敬的" + access2.getOutInName()
							+ "(先生/女士),您好! 您的来访已结束, "
							+ oldAccess.getEquipmentDaoType() + " 出门验证码为:"
							+ yanzhengnum + " 有效期为半小时。请在有效期内出门！谢谢合作");
					return yanzhengnum;// 返回生成的验证码
				} else if ("车牌".equals(access2.getYanzheng())) {
					totalDao.save(access2);// 添加数据
					return access2.getCarPai();// 直接返回车牌号
				} else {
					throw new Exception("验证类型（手机或车牌）不能为其他值");
				}
			} else {
				throw new Exception("来访凭证不能为空");
			}
		} else {
			throw new Exception("来访对象不能为空");
		}
	}

	
	
	/**
	 * 来访对象出  免审批来访出门
	 * 
	 * @param Class
	 *            来访对象（不能为空）
	 * @throws Exception
	 * 
	 */
	public static String updateAccessRecordwithoutApply(Class class1, Integer id, String tel)
			throws Exception {
		if (class1 != null) {
			TotalDao totalDao = createTotol();
			List list = totalDao.query(
					"from Access where entityName=? and entityId=?", class1
							.getSimpleName(), id);
			if (list != null && list.size() > 0) {
				Access oldAccess = (Access) list.get(0);
				Access access2 = new Access();
				if (oldAccess != null) {
					if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
						BeanUtils.copyProperties(oldAccess, access2,
								new String[] { "id", "addTime", "outInDoor",
										"failtime", "visitstime", "useSend",
										"nextSend", "access_EquIp" });
					} else if ("人行道".equals(oldAccess.getEquipmentDaoType())) {
						BeanUtils.copyProperties(oldAccess, access2,
								new String[] { "id", "addTime", "outInDoor",
										"failtime", "visitstime", "useSend",
										"nextSend", "equipmentIP" });
					} else {
					}
				} else {
					throw new Exception("不存在该条数据");
				}
				String addtime = Util.getDateTime();
				access2.setAddTime(addtime);
				access2.setVisitstime(addtime);
				access2.setUseSend("已生成");
				access2.setNextSend("已生成");
				String comeIn = "出门";
				access2.setOutInDoor(comeIn);
				// 根据 门的种类 和 进出 判断IP
				AccessEquipment accessEquipment = null;
				if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
					accessEquipment = (AccessEquipment) totalDao
							.getObjectByCondition(
									"from AccessEquipment where equipmentDaoType=?",
									access2.getEquipmentDaoType());
				} else if ("人行道".equals(oldAccess.getEquipmentDaoType())) {
					accessEquipment = (AccessEquipment) totalDao
							.getObjectByCondition(
									"from AccessEquipment where equipmentDaoType=? and equipmentOutIn=?",
									access2.getEquipmentDaoType(), comeIn);
				} else {
					throw new Exception("请选择车道（车行道|人行道）不能为其他值");
				}
				if (accessEquipment != null) {
					access2.setEquipmentIP(accessEquipment.getEquipmentIP());// 给门禁对比表中加上IP
					if ("车行道".equals(oldAccess.getEquipmentDaoType())) {
						AccessWebcam accessWebcam = (AccessWebcam) totalDao
								.getObjectByCondition(
										"from AccessWebcam where aeqt_ip=? and webcamOutIn='出门' and webcamLocation=?",
										accessEquipment.getEquipmentIP(),
										accessEquipment.getEquipmentLocation());
						if (accessWebcam != null) {
							access2.setAccess_EquIp(accessWebcam.getWebcamIP());
						}
//						access2.setWaitCheck(TO_BE_CHECKED);
					}
				}
				// visit.getClass().getSimpleName();//反射机制获取类名
//				access2.setFailtime(getSpecifiedminuteAfter(addtime, 30));// 失效日期
//				String outTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
				Calendar c = Calendar.getInstance();
		        c.setTime(new Date());
		        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
		        Date tomorrow = c.getTime();
		        String outTime=new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
				access2.setFailtime(outTime);// 失效日期
				
				if ("手机".equals(access2.getYanzheng())) {
					// 根据添加
					String yanzhengnum = yanNumber();
					access2.setYanzhengnum(yanzhengnum);
					totalDao.save(access2);// 添加数据
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(tel, "尊敬的" + access2.getOutInName()
							+ "(先生/女士),您好! 您的来访已结束, "
							+ oldAccess.getEquipmentDaoType() + " 出门验证码为:"
							+ yanzhengnum + " 有效期为半小时。请在有效期内出门！谢谢合作");
					System.out.println(tel+ "+尊敬的" + access2.getOutInName()
							+ "(先生/女士),您好! 您的来访已结束, "
							+ oldAccess.getEquipmentDaoType() + " 出门验证码为:"
							+ yanzhengnum + " 有效期为半小时。请在有效期内出门！谢谢合作");
					return yanzhengnum;// 返回生成的验证码
				} else if ("车牌".equals(access2.getYanzheng())) {
					totalDao.save(access2);// 添加数据
					return access2.getCarPai();// 直接返回车牌号
				} else {
					throw new Exception("验证类型（手机或车牌）不能为其他值");
				}
			} else {
				throw new Exception("来访凭证不能为空");
			}
		} else {
			throw new Exception("来访对象不能为空");
		}
	}

	/**
	 * @author Li_Cong 加班请假申请
	 * 
	 * @param Class
	 *            加班/请假对象（不能为空）
	 * @param id
	 *            对象id
	 * @param time
	 *            开始时间
	 * @throws Exception
	 * 
	 */
	public static String updateAccessRecordCode(Class class1, Integer id,
			String time) throws Exception {
		if (class1 != null) {
			TotalDao totalDao = createTotol();
			Access oldAccess = (Access) totalDao.getObjectByCondition(
					"from Access where entityName=? and entityId=?", class1
							.getSimpleName(), id);
			Access access2 = new Access();
			if (oldAccess != null) {
				BeanUtils.copyProperties(oldAccess, access2,
						new String[] { "id", "addTime", "outInDoor",
								"failtime", "visitstime" });
			} else {
				throw new Exception("不存在该条数据");
			}
			String addtime = Util.getDateTime();
			access2.setAddTime(addtime);
			access2.setOutInDoor("出门");
			access2.setVisitstime(time);// 请假出门时间和加班结束时间
			// visit.getClass().getSimpleName();//反射机制获取类名
			access2.setFailtime(getSpecifiedDayAfter(time));// 失效日期
			// 根据添加
			access2.setYanzhengnum(oldAccess.getYanzhengnum());
			totalDao.save(access2);// 添加数据
			return oldAccess.getCardId();// 返回员工卡号
		} else {
			throw new Exception("来访对象不能为空");
		}
	}

	
	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获得指定日期的后number分钟
	 * 
	 * @param specifiedDay
	 *            起始时间
	 * @return
	 */
	public static String getSpecifiedminuteAfter(String specifiedDay, int num) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		// int minute = c.get(Calendar.MINUTE);
		c.add(Calendar.MINUTE, num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dayAfter;
	}

	// 获取6位随机验证码。
	public static String yanNumber() {
		Random ran = new Random();
		String number = "" + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10);
		return number;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	private static final String HEX_CODE = "0123456789ABCDEF";

	// 写一个方法验证时间
	public boolean time(String sTime) {
		@SuppressWarnings("unused")
		CompanyInfo companyInfo = (CompanyInfo) totalDao.getObjectByCondition(
				"from CompanyInfo where url=?", "上海红湖排气系统有限公司");
		return false;
	}

	/***
	 * 十六进制转换为String类型
	 * 
	 * @param bs
	 * @return
	 */
	public static String byteArrayToHexString(byte[] bs) {
		int _byteLen = bs.length;
		StringBuilder _result = new StringBuilder(_byteLen * 2);
		for (int i = 0; i < _byteLen; i++) {
			int n = bs[i] & 0xFF;
			_result.append(HEX_CODE.charAt(n >> 4));
			_result.append(HEX_CODE.charAt(n & 0x0F));
		}
		return String.valueOf(_result);
	}

	// 根据验证内容和当前时间 和进出去查询是否有对应记录
	@Override
	public Access findOneAccess(String carPai, String carTime, String outIn) {
		// TODO Auto-generated method stub
		String hql = "from Access where failtime > ? and carPai = ? and visitstime < ? and useSend <> '已失效' and outInDoor = ?";
		return (Access) totalDao.getObjectByCondition(hql, carTime, carPai,
				carTime, outIn);
	}

	private String car = "";// 获取到的车牌号
	private String newTime_hour = "";// 获取当前时间，判定时间段
	private String carTime = "";// 先获取当前日期时间用做对比
	private String ipaddr = "";// 获取到的IP
	/**
	 * 车牌类型  0：未知车牌 1：蓝牌小汽车 2：黑牌小汽车 3：单排黄牌 4：双排黄牌 5：警车车牌
	 * 6：武警车牌 7：个性化车牌 8：单排军用车牌 9：双排军用车牌 10使馆车牌 11：香港进出大陆车牌
	 * 12：农用车牌 13：教练车牌 14：澳门进出中国大陆车牌 15：双层武警车牌 16：武警总队车牌 17：双层武警总队车牌 
	 */
	private String carType = "";  
	private AccessWebcam accessWebcam = null;// 摄像头对象。根据IP获得
	private InEmployeeCarInfor inEmployeeCarInfor = null;// 内部、常访车辆表
	private CarInOutType carInOutType = null;// 车辆内外状态表
	private AccessRecords accessRecordZong = null;// 记录车牌信息表
	private Access accessCar = null;// 进出凭证表
	private static final String INTERNAL = "内部";// 内部标识
	private static final String OFTEN_VISIT = "常访";// 常访标识
	private static final String VISITS = "来访";// 来访标识
	private static final String TO_BE_CHECKED = "待检查";// 待检查标识
	private static final String YI_CHECKED = "已检查";// 待检查标识
	private static final String URGENT = "紧急";// 来访标识

	/**
	 * @param informartion
	 *            识别的车牌信息
	 * @author Li_Cong 摄像头获取车牌号，验证通过后，触发开门，并开门保存记录
	 */
	@Override
	public void carPaiVerifyImpl(String informartion) {
		StringBuffer buffer = new StringBuffer();
		try {
			// TODO Auto-generated method stub
			JieShouCar(informartion);// 接收车牌信息
			accessWebcam = null;
			inEmployeeCarInfor = null;
			carInOutType = null;
			if ("_无_".equals(car)) {// 1、判断车牌不为空
				System.out.println("没有识别到车牌，请重新识别!!!");
				// LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 5);//
				// 推送LED消息
			} else {// 1、车牌不为空
				if (carType!=null&&("5".equals(carType)||"6".equals(carType)
						||"8".equals(carType)||"8".equals(carType)||"15".equals(carType)
						||"16".equals(carType)||"17".equals(carType))) {
					buffer.append("特殊车辆直接开门："+AccessServerImpl.openDoor(ipaddr)+" ip:"+ipaddr+"类型"+carType+"车牌号:"+car);//开门一次
					DoorBangDingServerImpl.caeLogInfor(buffer, car, null, null, null, null, null, null);
					return;
				}
				/**
				 * 第一步： 根据IP查询摄像头信息（进门/出门）
				 */
				accessWebcam = (AccessWebcam) totalDao.getObjectByCondition(
						"from AccessWebcam where webcamIP = ?", ipaddr);

				/**
				 * 第二步：查询车牌是否为内部车辆或常访车辆
				 */
				inEmployeeCarInfor = (InEmployeeCarInfor) totalDao
						.getObjectByCondition(
								"from InEmployeeCarInfor where nplates = ?", car);
				/***
				 * 第三步：查询车牌是否有内外状态（CarInOutType）
				 */
				carInOutType = (CarInOutType) totalDao.getObjectByCondition(
						"from CarInOutType where carPai=? order by id desc", car);
				if (inEmployeeCarInfor != null) {// 2、内部&常访
					/***
					 * 第四步：获取车牌，记录车牌信息(AccessRecords)内部
					 */
					if (INTERNAL.equals(inEmployeeCarInfor.getCarInCangType())) {// 3、内部
						accessRecordZong(INTERNAL);// 保存识别记录
						if ("是".equals(inEmployeeCarInfor.getWhiteCar())) {// 4、白名单
							WhiteCar(carInOutType,buffer);// 内部白名单处理流程
						} else {// 4非白名单
							// 普通内部车辆 判断时间段
							if ("进门".equals(accessWebcam.getWebcamOutIn())) {// 7、进门时间段
								FeiWhiteCarIn(carInOutType ,buffer);// 内部非白名单进门处理流程
							} else if ("出门".equals(accessWebcam.getWebcamOutIn())) {// 7、出门时间段
								FeiWhiteCarOut(carInOutType,buffer);// 内部非白名单出门处理流程
							} else {
								System.out.println("摄像头IP添加有误！！！");
								buffer.append("摄像头IP添加有误！！！");
							}
						}
					} else if (OFTEN_VISIT.equals(inEmployeeCarInfor
							.getCarInCangType())
							&& "同意".equals(inEmployeeCarInfor.getOftenStatus())) {// 3、常访
						// accessRecordZong(OFTEN_VISIT);// 保存识别记录
						// 验证有效期
						if (duibi(inEmployeeCarInfor.getEffectiveDate(), carTime)) {// 有效日期大于当前时间
							accessRecordZong(OFTEN_VISIT);// 保存常访识别记录
							OftenVisitS(carInOutType,buffer);// 常访车辆进出流程
						} else {
							System.out.println(inEmployeeCarInfor.getOftenname()
									+ car + "您的常访权限已过期，请重新申请。");
							buffer.append(car + "常访权限已过期。");
							// 无法进门来访流程
							accessRecordZong(VISITS);// 保存来访识别记录
							// 对比Access表
							accessCar = null;
							accessCar = findOneAccess(car, carTime, accessWebcam
									.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
							if (accessCar != null) {
								WaiBuCarYou(buffer);// 外部车辆有申请流程
							} else {// 6、没有申请记录 走不开门流程 出门=》走紧急开门流程
								OftenCarWu("已过期",buffer);// 常访车辆无申请流程
							}
						}
					} else {
						// 第三类车 为未审批的常访车辆
						// System.out.println(car + "此车辆类型既不是内部常访也不是来访，请检查");
						accessRecordZong(VISITS);// 保存来访识别记录
						// 对比Access表
						accessCar = null;
						accessCar = findOneAccess(car, carTime, accessWebcam
								.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
						if (accessCar != null) {
							WaiBuCarYou(buffer);// 外部车辆有申请流程
						} else {// 6、没有申请记录 走不开门流程 出门=》走紧急开门流程
							OftenCarWu("未审批",buffer);// 常访车辆无申请流程
						}
					}
				} else {// 5、外部车牌
					// 如果没有，为外部车辆
					accessRecordZong(VISITS);// 保存识别记录
					// 对比Access表
					accessCar = null;
					accessCar = findOneAccess(car, carTime, accessWebcam
							.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
					if (accessCar != null) {
						WaiBuCarYou(buffer);// 外部车辆有申请流程
					} else {// 6、没有申请记录 走不开门流程 出门=》走紧急开门流程
						WaiBuCarWu(buffer);// 外部车辆无申请流程
					}
				}
			}
			//添加记录
			if (inEmployeeCarInfor!=null) {//内部或常访
				DoorBangDingServerImpl.caeLogInfor(buffer, 
						car, null, inEmployeeCarInfor.getName(), 
						accessWebcam.getWebcamOutIn(), accessWebcam.getWebcamName(),
						accessWebcam.getId(), accessWebcam.getWebcamIP());
				//FFxinhao();
			}else if (accessCar!=null) {//有申请
				DoorBangDingServerImpl.caeLogInfor(buffer, 
						car, null, accessCar.getOutInName(), 
						accessWebcam.getWebcamOutIn(), accessWebcam.getWebcamName(),
						accessWebcam.getId(), accessWebcam.getWebcamIP());
				//if ("进门".equals(accessWebcam.getWebcamOutIn())) FFxinhao();
			}else{
				DoorBangDingServerImpl.caeLogInfor(buffer, 
						car, null, null, 
						accessWebcam.getWebcamOutIn(), accessWebcam.getWebcamName(),
						accessWebcam.getId(), accessWebcam.getWebcamIP());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			buffer.append("异常：" + e.toString());
			DoorBangDingServerImpl.caeLogInfor(buffer, 
					car, null, null, 
					null, null,
					null, null);
		}
		buffer.setLength(0);
		
	}

	// 通过设备IP查找对应的记录表中的最晚一条开门数据，
	public List<AccessRecords> getAccessRecor(String ip, String str) {
		return totalDao
				.query(
						"from AccessRecords where asEqt_ip=? and recordStatus =? and addTime >? order by id desc",
						ip, str, Util.getDateTime(-3));
	}
	
	public void FFxinhao(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer builder = new StringBuffer();
		// 标识为地感线圈触发--车辆已进门
		// 通过设备IP去查找开门记录中最迟一条记录，将其设置为已进门
		// 通过开门记录中的（进门/出门）以及开门验证内容来查找出来访表中的来访记录，将其设置为已通过
		String nowTime = Util.getDateTime();
		String accessIP = "192.168.0.9";
		List accessRecordList = getAccessRecor(accessIP, "已开门");
		if (accessRecordList != null && accessRecordList.size() > 0) {
			AccessRecords accessRecords = (AccessRecords) accessRecordList.get(0);
			builder.append(accessRecords.getId() + "+"+ accessRecords.getRecordContents());
			// 根据车牌号查找CarInOutType表中对象，用于改变状态
			List carInOutTypelist = totalDao.query("from CarInOutType where carPai=? order by id desc",accessRecords.getRecordContents());
			if (carInOutTypelist!=null&&carInOutTypelist.size() > 0) {
				System.out.println(accessRecords.getAsWeam_ip());
				builder.append("重开门"+AccessServerImpl.openDoor(accessRecords.getAsWeam_ip())+"ip"+accessRecords.getAsWeam_ip());//重新开门一次
				if ("内部".equals(accessRecords.getRecordisIn())) {
					// 内部车辆
					accessRecords.setRecordStatus("已通过");
					accessRecords.setEnterTime(Util.getDateTime());
					totalDao.update2(accessRecords);
					
					// 改变车辆状态表的状态
					CarInOutType carInOutType = (CarInOutType) carInOutTypelist.get(0);
					carInOutType.setCarInOut(accessRecords.getOpenType());
					carInOutType.setUpdateTime(Util.getDateTime());
					totalDao.update2(carInOutType);

					//控制灯
					if ("是".equals(accessRecords.getIsKong())) {
						//根据卡号查找管理员办公室设备绑定的所有灯
						List<OneLight> lightOne = totalDao.query("from OneLight where ace_id in (select id from AccessEquipment where adminCardId = ? and equipmentDaoType = '办公室') ", accessRecords.getInmarkId());
						if (lightOne != null && lightOne.size() > 0)
							OneLightServerImpl.staticOCLight_1(lightOne,accessRecords.getInCode(),accessRecords.getInName(),accessRecords.getOpenType());
					}
					
					builder.append(","+ accessRecords.getRecordStatus() + " "+ carInOutType.getCarInOut());
					// 根据车牌查询是否有请假加班记录，如有，设置为已失效
					List accesslist = totalDao.query("from Access where carPai=? and outInDoor=? and failtime > ? and visitstime < ? and useSend <> '已失效' order by id desc",accessRecords.getRecordContents(),accessRecords.getOpenType(),nowTime, nowTime);
					if (accesslist != null && accesslist.size() > 0) {
						Access access = (Access) accesslist.get(0);
						System.out.println("内部Access+++++++++++++++++++++++++++++++" + access.getId());
						access.setUseSend("已失效");
						totalDao.update2(access);
						// 添加内部白名单出借状态为：正常车辆的考勤记录
//						String kao="";
//						try {
//							kao = DownloadServerImpl.AddDownLoads(access.getCardId().trim(),"正常",accessType,accessRecords.getOpenType());
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							builder.append(","+e+" 车有申请考勤异常");
//						}
						String kao1 = "无";
						try {
							kao1 = AttendanceTowServerImpl.addAttendanceTow(inEmployeeCarInfor.getNcardId(),accessRecords.getInCode(),accessRecords.getInName(), inEmployeeCarInfor.getNdept(), accessRecords.getInId(),"正常","车行道",accessRecords.getOpenType(),null);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							builder.append(","+e+" 车考勤异常1");
						}
						builder.append("," + access.getOutInName()+ access.getCarPai() + " 车辆已"+ access.getOutInDoor() + "(请假/加班)"+ access.getUseSend()+" "+"kao1:"+kao1);
					} else {
						builder.append(accessRecords.getInName()
								+ accessRecords.getRecordContents()
								+ " 车辆" + accessRecords.getOpenType()
								+ " 为正常进出");
					}

					// 添加考勤记录
					List inEmployeeCarInforlist = totalDao
							.query(
									"from InEmployeeCarInfor where nplates = ? and carInCangType ='内部' and carType='个人'",
									accessRecords.getRecordContents());
					if (inEmployeeCarInforlist != null
							&& inEmployeeCarInforlist.size() > 0) {
						InEmployeeCarInfor inEmployeeCarInfor = (InEmployeeCarInfor) inEmployeeCarInforlist
								.get(0);
						if ("是".equals(inEmployeeCarInfor
								.getRtxMessage())) {
							// 给需要发消息的车主发送RTX消息
							List<String> codes1 = new ArrayList<String>();
							codes1.add(accessRecords.getInCode());
							if ("苏E6M6L0".equals(accessRecords.getRecordContents())) {
								codes1.add("479");
								codes1.add("472");
							}
							boolean b = false;
							if (codes1 != null && codes1.size() > 0) {
								b = RtxUtil.sendNoLoginNotify(codes1, "您的车牌号为 "+ accessRecords.getRecordContents() + " 的车辆已 "+ accessRecords.getOpenType() + " !", "系统消息", "0", "0");
							}
							builder.append(",RTX" + b + "");
						}
						builder.append(",卡号" + inEmployeeCarInfor.getNcardId());
						if ("正常".equals(inEmployeeCarInfor.getBorrowStatus())) {
//							String kao = "";
//							try {
//								kao = DownloadServerImpl.AddDownLoads(inEmployeeCarInfor.getNcardId(),"正常",accessEquipment.getEquipmentName(),accessRecords.getOpenType());
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//								builder.append(","+e+" 车考勤异常");
//							}
							String kao1 = "无";
							try {
								kao1 = AttendanceTowServerImpl.addAttendanceTow(inEmployeeCarInfor.getNcardId(),accessRecords.getInCode(),accessRecords.getInName(), inEmployeeCarInfor.getNdept(), accessRecords.getInId(),"正常","车行道",accessRecords.getOpenType(),null);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								builder.append(","+e+" 车考勤异常1");
							}
							builder.append(accessRecords.getInName()+ accessRecords.getRecordContents()+ ",车辆考情："+"kao1:"+kao1);
						}
					}
				} else if ("常访".equals(accessRecords.getRecordisIn())) {
					// 常访车辆
					accessRecords.setRecordStatus("已通过");
					accessRecords.setEnterTime(Util.getDateTime());
					totalDao.update2(accessRecords);

					// 改变车辆状态表的状态
					CarInOutType carInOutType = (CarInOutType) carInOutTypelist.get(0);
					carInOutType.setCarInOut(accessRecords.getOpenType());
					carInOutType.setUpdateTime(Util.getDateTime());
					totalDao.update2(carInOutType);
					builder.append(accessRecords.getInName()+ accessRecords.getRecordContents()+ accessRecords.getRecordisIn() + "已通过");
				} else {
					// 外部的车辆
					accessRecords.setRecordStatus("已通过");
					accessRecords.setEnterTime(Util.getDateTime());
					totalDao.update2(accessRecords);
					// 根据车牌号查找CarInOutType表中对象，改变状态
					CarInOutType carInOutType = (CarInOutType) carInOutTypelist.get(0);
					carInOutType.setCarInOut(accessRecords.getOpenType());
					carInOutType.setUpdateTime(Util.getDateTime());
					totalDao.update2(carInOutType);

					List<Access> accesseList = totalDao.query("from Access where carPai=? and failtime > ? and visitstime < ? and useSend<>'已失效' and outInDoor=? order by id desc",accessRecords.getRecordContents(),nowTime, nowTime, accessRecords.getOpenType());
					if (accesseList != null && accesseList.size() > 0) {
						Access access = accesseList.get(0);
						System.out.println("Access来访+++++++++++++++++++++++++++++++"+ access.getId());
						builder.append(",Access来访+"+access.getId());
						access.setUseSend("已失效");
						totalDao.update2(access);
						List list = totalDao.query("from Visit where id=?", access.getEntityId());
						if (list != null && list.size() > 0) {
							Visit visit = (Visit) list.get(0);
							builder.append(",Visit来访+"+visit.getId());
							if ("进门".equals(access.getOutInDoor())) {
								visit.setVisit_laiStatus("已进门");// 进门后状态变为已进门，此时可以点击来访结束
							} else if ("出门".equals(access.getOutInDoor())) {
								visit.setVisit_laiStatus("已出门");// 出门后状态为已出门，来访结束
							} else {
								visit.setVisit_laiStatus("不出不进");
							}
							totalDao.update2(visit);
						}
						System.out.println(access.getCarPai() + " 车辆已" + access.getOutInDoor() + "(来访)");
						builder.append(","+access.getCarPai() + " 车辆已" + access.getOutInDoor() + "(来访)");
					}
				}
			} else {
				// 验证码
				// 外部的车辆
				accessRecords.setRecordStatus("已通过");
				accessRecords.setEnterTime(Util.getDateTime());
				totalDao.update2(accessRecords);

				List<Access> accesseList = totalDao
						.query(
								"from Access where yanzhengnum=? and failtime > ? and visitstime < ? and useSend<>'已失效' and outInDoor=? order by id desc",
								accessRecords.getRecordContents(),// ***************************??//
								nowTime, nowTime, accessRecords
										.getOpenType());
				if (accesseList != null && accesseList.size() > 0) {
					Access access = accesseList.get(0);
					System.out.println("Access+++++++++++++++++++++++++++++++" + access.getId());
					access.setUseSend("已失效");
					totalDao.update2(access);
					List list = totalDao.query("from Visit where id=?",
							access.getEntityId());
					if (list != null && list.size() > 0) {
						Visit visit = (Visit) list.get(0);
						if ("进门".equals(access.getOutInDoor())) {
							visit.setVisit_laiStatus("已进门");// 进门后状态变为已进门，此时可以点击来访结束
						} else if ("出门".equals(access.getOutInDoor())) {
							visit.setVisit_laiStatus("已出门");// 出门后状态为已出门，来访结束
						} else {
							visit.setVisit_laiStatus("不出不进");
						}
						totalDao.update2(visit);
					}
					System.out.println("验证码为："+ access.getYanzhengnum() + " 车辆已"+ access.getOutInDoor() + "(来访)");
					builder.append(",验证码为："+ access.getYanzhengnum() + " 车辆已"+ access.getOutInDoor() + "(来访)");
				}
			}
		}
		LedCarPush.ledShow("", "", 7);// 推送LED消息 出门结束
		/********************************* 1、地感线圈流程结束 *********************************/
	}
	
	public boolean duibi(String shixiao, String now) {
		return Util.compareTime(shixiao, "yyyy-MM-dd", now,
				"yyyy-MM-dd HH:mm:ss");
	}

	// 接收车牌信息
	private void JieShouCar(String informartion) {
		JSONObject jsonObject = JSONObject.fromObject(informartion);
		JSONObject jsonObject2 = jsonObject.getJSONObject("AlarmInfoPlate");
		JSONObject jsonObject3 = jsonObject2.getJSONObject("result");
		JSONObject jsonObject4 = jsonObject3.getJSONObject("PlateResult");
		ipaddr = "";
		ipaddr = jsonObject2.getString("ipaddr");
		car = jsonObject4.getString("license");
		carType = jsonObject4.getString("type");
		System.out.println("车牌类型：++++++++"+carType);
		System.out.println("车牌类型："+carType);
		System.out.println("车牌类型："+carType);
		carTime = Util.getDateTime();// 先获取当前时间用做对比
		newTime_hour = "";
		newTime_hour = Util.getDateTime("HH:mm:ss");// 获取当前时间，判定时间段
	}
	//text
	private void JieShouCar1(String informartion) {
//		JSONObject jsonObject = JSONObject.fromObject(informartion);
//		JSONObject jsonObject2 = jsonObject.getJSONObject("AlarmInfoPlate");
//		JSONObject jsonObject3 = jsonObject2.getJSONObject("result");
//		JSONObject jsonObject4 = jsonObject3.getJSONObject("PlateResult");
		ipaddr = "192.168.0.131";
//		ipaddr = jsonObject2.getString("ipaddr");
//		car = jsonObject4.getString("license");
		car = "沪C3L415";
		carType = "0";
//		carType = jsonObject4.getString("type");
		carTime = Util.getDateTime();// 先获取当前时间用做对比
		newTime_hour = "";
		newTime_hour = Util.getDateTime("HH:mm:ss");// 获取当前时间，判定时间段
	}

	// 常访车辆无申请流程
	private void OftenCarWu(String str,StringBuffer buffer) {
		if ("已过期".equals(str)) {
			LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 13);// 推送LED消息
			buffer.append(car + accessWebcam.getWebcamOutIn()+ " 您的常访申请已过期!");
		} else if ("未审批".equals(str)) {
			LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 14);// 推送LED消息
			buffer.append(car + accessWebcam.getWebcamOutIn()+ " 您的常访申请未审批!");
		}
		System.out.println("车牌为：" + car + "常访车辆，常访申请已过期，审批通过后方可来访。" + str);
	}

	// 外部车辆无申请流程
	private void WaiBuCarWu(StringBuffer buffer) {
		if ("进门".equals(accessWebcam.getWebcamOutIn())) {
			LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 2);// 推送LED消息
			buffer.append(car+ accessWebcam.getWebcamOutIn()+"无来访申请");
		} else if ("出门".equals(accessWebcam.getWebcamOutIn())) {
			//LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 8);// 推送LED消息
			Access accessCar1 = (Access) totalDao
					.getObjectByCondition(
							"from Access where carPai = ? and visitstime < ? and useSend = '已失效' and outInDoor = '进门' order by id desc",
							car, carTime);
			if (accessCar1 != null) {
				Visit visit = (Visit) totalDao.getObjectById(Visit.class,accessCar1.getEntityId());
				if (visit != null) {
					LedCarPush.ledShow(car, visit.getShouFangName(), 8);// 推送LED消息
					System.out.println(car + "请联系："+visit.getShouFangName()+"确认出门");
					buffer.append(car + "请联系："+visit.getShouFangName()+"确认出门");
				} else {
					LedCarPush.ledShow(car, "为非正常进入", 15);// 推送LED消息
					System.out.println("车牌为：" + car + "为非正常进入，无法开门。");
					buffer.append("车牌为：" + car + "为非正常进入，无法开门。");
				}
			} else {
				LedCarPush.ledShow(car, "为非正常进入", 15);// 推送LED消息
				System.out.println("车牌为：" + car + "为非正常进入，无法开门。");
				buffer.append("车牌为：" + car + "为非正常进入，无法开门。");
			}
		}
		System.out.println("车牌为：" + car + "为来访车辆，"+accessWebcam.getWebcamOutIn()+"无法开门。");
	}

	// 外部车辆有申请流程
	private void WaiBuCarYou(StringBuffer buffer) {
		/**** 添加车辆进出类型表 *****************************/
		AccessRecordsServerImpl.createCarInOutType(car);
		// 是否为待检车辆
		if (TO_BE_CHECKED.equals(accessCar.getWaitCheck())) {// 7、待检
			// 待检流程
			DaiJianChe(VISITS, "out", buffer);
		} else if (URGENT.equals(accessCar.getWaitCheck())) {
			if (openDoor(ipaddr)) {// 8、开门
				JinJi("",buffer);
			} else {// 8
				System.out.println(car + "紧急开门失败！请检查网络。");
				buffer.append(car + "紧急开门失败！请检查网络。");
			}
		} else {// 7、来访车辆进门
			if (openDoor(ipaddr)) {// 9、开门
				open_Chuli_Lai(VISITS, buffer);
			} else {// 9
				System.out.println(car + "开门失败！（申请记录）请检查网络。");
				buffer.append(car + "开门失败！（申请记录）请检查网络。");
			}
		}
	}

	// 常访车辆进出流程
	private void OftenVisitS(CarInOutType carInOutType,StringBuffer buffer) {
		if (carInOutType != null) {// 不是首次进入
//			if (accessWebcam.getWebcamOutIn()
//					.equals(carInOutType.getCarInOut())) {
//				// 相等
//				web_carTow_Cang();
//			} else {
				// 不相等 开门
				// 进门
				/**
				 * else if ("出门".equals(accessWebcam.getWebcamOutIn())) { //
				 * 出门需待检 // DaiJianChe(OFTEN_VISIT); // 出门不用待检 if
				 * (openDoor(ipaddr)) { open_Chuli_cang("(常访车辆)"); } else {
				 * System.out.println(car + "开门失败！（常访车辆）请检查网络。"); } }
				 */
				if (!"".equals(accessWebcam.getWebcamOutIn())
						&& accessWebcam.getWebcamOutIn() != null) {
					if (openDoor(ipaddr)) {
						open_Chuli_cang("(常访车辆)",buffer);
					} else {
						System.out.println(car + "开门失败！（常访车辆）请检查网络。");
					}
				} else {
					System.out.println("进出门摄像头设备添加有误！");
				}
//			}
		} else {// 是首次进入
			// if ("进门".equals(accessWebcam.getWebcamOutIn())) {
			// if (openDoor(ipaddr)) {
			// open_Chuli_cang("(常访车辆)");
			// /**** 添加车辆进出类型表 *****************************/
			// AccessRecordsServerImpl.createCarInOutType(car);
			// } else {
			// System.out.println(car + "开门失败！（常访车辆）请检查网络。");
			// }
			// } else if ("出门".equals(accessWebcam.getWebcamOutIn())) {
			// // 出门需待检
			// // DaiJianChe(OFTEN_VISIT);
			// // 出门不用待检
			// if (openDoor(ipaddr)) {
			// open_Chuli_cang("(常访车辆)");
			// /**** 添加车辆进出类型表 *****************************/
			// AccessRecordsServerImpl.createCarInOutType(car);
			// } else {
			// System.out.println(car + "开门失败！（常访车辆）请检查网络。");
			// }
			// } else {
			// System.out.println("进出门摄像头设备添加有误！");
			// }
			if (!"".equals(accessWebcam.getWebcamOutIn())
					&& accessWebcam.getWebcamOutIn() != null) {
				if (openDoor(ipaddr)) {
					open_Chuli_cang("(常访车辆)",buffer);
					/**** 添加车辆进出类型表 *****************************/
					AccessRecordsServerImpl.createCarInOutType(car);
				} else {
					System.out.println(car + "开门失败！（常访车辆）请检查网络。");
				}
			} else {
				System.out.println("进出门摄像头设备添加有误！");
			}
		}
	}

	// 出门需待检
	public void DaiJianChe(String type, String InOut ,StringBuffer buffer) {
		if ("out".equals(InOut)) {
			accessRecordZong.setInName(accessCar.getOutInName());// 添加来访人名称
		} else {
			accessRecordZong.setInName(inEmployeeCarInfor.getName());// 添加来访人名称
		}
		// 在添加待检车之前查找当天倒数第二条，记录，如果车牌号相等，为出门且状态为已检查，将其设置为已检查。
		List list2 = totalDao
				.query(
						"from AccessRecords where addtime>? and recordType='车牌' order by id desc",
						Util.getDateTime(-3));
		if (list2 != null && list2.size() > 0) {
			if (list2.size()>1) {
				AccessRecords accessRecords = (AccessRecords) list2.get(1);
				if (accessRecords != null) {
					if (car.equals(accessRecords.getRecordContents())
							&& "出门".equals(accessRecords.getOpenType())
							&& "已检查".equals(accessRecords.getWaitCheck())) {
						accessRecordZong.setWaitCheck(YI_CHECKED);
					} else {
						accessRecordZong.setWaitCheck(TO_BE_CHECKED);
						LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 9);// 推送LED消息
					}
				} else {
					accessRecordZong.setWaitCheck(TO_BE_CHECKED);
					LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 9);// 推送LED消息
				}
			}else{
				accessRecordZong.setWaitCheck(TO_BE_CHECKED);
				LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 9);// 推送LED消息
			}
		} else {
			accessRecordZong.setWaitCheck(TO_BE_CHECKED);
			LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 9);// 推送LED消息
		}
		totalDao.update2(accessRecordZong);
		// 检车
		System.out.println(car + "车辆为待检车辆，需门卫检查刷卡确认后开门+" + type);
		buffer.append(car + "车辆为待检车辆，需门卫检查刷卡确认后开门+" + type);
	}

	// 进门紧急开门情况
	private void JinJi(String type, StringBuffer buffer) {
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 10);// 推送LED消息
		// 开门成功 添加开门记录
		accessRecordZong.setInName(accessCar.getOutInName());// 将进出人姓名添加进去
		accessRecordZong.setUrgentCar(URGENT);// 紧急出门记录
		accessRecordZong.setRecordStatus("已开门");
		totalDao.update2(accessRecordZong);
		// 紧急
		System.out.println(car + " 紧急开门 " + type);
		buffer.append(car + " 紧急开门 " + type);
	}

	// 非白名单出门流程
	private void FeiWhiteCarOut(CarInOutType carInOutType,StringBuffer buffer) {
		if (carInOutType != null) {// 不是首次进入
//			if (accessWebcam.getWebcamOutIn()
//					.equals(carInOutType.getCarInOut())) {// 9、进门状态
//				// 相等
//				web_carTow();
//			} else {// 9、不相等 判断时间段
				if (Util.outMenTime(newTime_hour)) {// 8、在时间段内 开门
					if ("是".equals(inEmployeeCarInfor.getNbdaijian())) {
						// 待检流程
						DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
					} else {
						// 不为待检车辆，不是出门 开门
						if (openDoor(ipaddr)) {// 10、开门
							open_Chuli("（正常出门时间）",buffer);
						} else {
							System.out.println(inEmployeeCarInfor.getName()
									+ car + "开门失败！（正常出门时间）请检查网络。");
							buffer.append(inEmployeeCarInfor.getName()
									+ car + "开门失败！（正常出门时间）请检查网络。");
						}
					}
				} else {// 8、在时间段以外
					// 对比Access表
					Access accessCar = findOneAccess(car, carTime, accessWebcam
							.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
					if (accessCar != null) {// 9、有申请通过记录 开门
						if (openDoor(ipaddr)) {// 11、开门
							open_Chuli("（申请记录）",buffer);
						} else {
							System.out.println(car + "开门失败！（申请记录）请检查网络。");
							buffer.append(car + "开门失败！（申请记录）请检查网络。");
						}
					} else {// 9、没有申请记录 走不开门流程 出门=》走紧急开门流程
						LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(),
								3);// 推送LED消息
						System.out.println(inEmployeeCarInfor.getName()
								+ "您好，当前为工作时间，您的车牌为" + car
								+ "的车辆无法出门，如需出门，请先申请请假！");
						buffer.append(inEmployeeCarInfor.getName()
								+ "您好，当前为工作时间，您的车牌为" + car
								+ "的车辆无法出门，如需出门，请先申请请假！");
					}
				}
//			}
		} else {// 是首次进入
			if (Util.outMenTime(newTime_hour)) {// 8、在时间段内 开门
				if ("是".equals(inEmployeeCarInfor.getNbdaijian())) {
					// 待检流程
					DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
				} else {
					// 不为待检车辆， 开门
					if (openDoor(ipaddr)) {// 9、开门
						open_Chuli("（正常开门时间）",buffer);
						/**** 添加车辆进出类型表 *****************************/
						AccessRecordsServerImpl.createCarInOutType(car);
					} else {
						buffer.append(car + "首次开门失败！（正常开门时间）请检查网络。");
						System.out.println(car + "首次开门失败！（正常开门时间）请检查网络。");
					}
				}
			} else {// 8、在时间段以外
				// 对比Access表
				Access accessCar = findOneAccess(car, carTime, accessWebcam
						.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
				if (accessCar != null) {// 9、有申请通过记录 开门
					if (openDoor(ipaddr)) {// 10、开门
						open_Chuli("（申请记录）",buffer);
						totalDao.update2(accessRecordZong);
						/**** 添加车辆进出类型表 *****************************/
						AccessRecordsServerImpl.createCarInOutType(car);
					} else {
						System.out.println(car + "开门失败！（申请记录）请检查网络。");
						buffer.append(car + "开门失败！（申请记录）请检查网络。");
					}
				} else {// 9、没有申请记录 走不开门流程 出门=》走紧急开门流程
					LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 3);// 推送LED消息
					System.out.println(inEmployeeCarInfor.getName()+ "您好，当前为工作时间，您的车牌为" + car + "的车辆无法出门，如需出门，请先申请请假！");
					buffer.append(inEmployeeCarInfor.getName()+ "您好，当前为工作时间，您的车牌为" + car + "的车辆无法出门，如需出门，请先申请请假！");
				}
			}
		}
	}

	// 非白名单进门流程
	private void FeiWhiteCarIn(CarInOutType carInOutType,StringBuffer buffer) {
		if (carInOutType != null) {// 不是首次进门
//			if (accessWebcam.getWebcamOutIn()
//					.equals(carInOutType.getCarInOut())) {// 9、进门状态
//				// 相等
//				web_carTow();
//			} else {// 9、不相等 判断时间段
				if (Util.InMenTime(newTime_hour)) {// 8、在时间段内 对比状态
					if (openDoor(ipaddr)) {// 10、开门
						open_Chuli("（正常开门时间）",buffer);
					} else {
						System.out.println(car + "开门失败！（正常开门时间）请检查网络。");
						buffer.append(car + "进门开门失败！（正常开门时间）请检查网络。");
					}
				} else {// 8、不在时间段
					// 对比Access表
					/****************************************************/
					Access accessCar = findOneAccess(car, carTime, accessWebcam
							.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
					if (accessCar != null) {// 9、有申请通过记录 开门
						if (openDoor(ipaddr)) {// 11、开门
							open_Chuli("（申请记录）",buffer);
						} else {
							System.out.println(car + "开门失败！（申请记录）请检查网络。");
							buffer.append(car + "进门开门失败！（申请记录）请检查网络。");
						}
					} else {// 9、没有申请记录 走不开门流程 进门=》走紧急开门流程
						LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(),
								4);// 推送LED消息
						System.out.println(inEmployeeCarInfor.getName()+ "您好，当前为非工作时间，您的车牌为" + car + "的车辆无法进门，如需进门，请先申请加班！");
						buffer.append(inEmployeeCarInfor.getName()+ "您好，当前为非工作时间，您的车牌为" + car + "的车辆无法进门，如需进门，请先申请加班！");
					}
				}
//			}
		} else {// 是首次进入 判断时间
			if (Util.InMenTime(newTime_hour)) {// 8、在时间段内 开门
				if (openDoor(ipaddr)) {// 9、开门
					// if开门成功 添加开门记录
					open_Chuli("（正常开门时间+first）",buffer);
					/**** 添加车辆进出类型表 *****************************/
					AccessRecordsServerImpl.createCarInOutType(car);
				} else {
					System.out.println(car + "开门失败！（正常开门时间）请检查网络。+first");
					buffer.append(car + "进门开门失败！（正常开门时间）请检查网络。+first");
				}
			} else {// 8、不在时间段
				// 对比Access表
				Access accessCar = findOneAccess(car, carTime, accessWebcam
						.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
				if (accessCar != null) {// 9、有申请通过记录 开门
					if (openDoor(ipaddr)) {// 10、开门
						open_Chuli("（正常开门时间+first）",buffer);
						/**** 添加车辆进出类型表 *****************************/
						AccessRecordsServerImpl.createCarInOutType(car);
					} else {
						System.out.println(car + "开门失败！（申请记录）请检查网络。+first");
						buffer.append(car + "进门开门失败！（申请记录）请检查网络。+first");
					}
				} else {// 9、没有申请记录 走不开门流程 进门=》走紧急开门流程
					LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 4);// 推送LED消息
					System.out.println(inEmployeeCarInfor.getName()+ "您好，当前为非工作时间，您的车牌为" + car+ "的车辆无法进门，如需进门，请先申请加班！");
					buffer.append(inEmployeeCarInfor.getName()+ "您好，当前为非工作时间，您的车牌为" + car+ "的车辆无法进门，如需进门，请先申请加班！++first");
				}
			}
		}
	}

	// 内部白名单处理流程
	private void WhiteCar(CarInOutType carInOutType,StringBuffer buffer) {
		if (carInOutType != null) {
			// 对比车辆内外状态（非首次进入需要）
//			if (accessWebcam.getWebcamOutIn()
//					.equals(carInOutType.getCarInOut())) {
//				web_carTow();
//			} else {
//				if ("正常".equals(inEmployeeCarInfor.getBorrowStatus())) {
//					if ("出门".equals(accessWebcam.getWebcamOutIn())
//							&& "是".equals(inEmployeeCarInfor.getNbdaijian())) {
//						// 待检流程
//						DaiJianChe(INTERNAL, "",buffer);// 内部员工出门需待检流程
//					} else {
//						// 不为待检车辆，不是出门 开门
//						if (openDoor(ipaddr)) {
//							// if开门成功 添加开门记录
//							open_Chuli("（内部白名单）",buffer);
//						} else {
//							System.out.println(car + "开门失败！（内部白名单）请检查网络。");
//							buffer.append(car + "开门失败！（内部白名单）请检查网络。");
//						}
//					}
//				}
//				else if ("出借".equals(inEmployeeCarInfor.getBorrowStatus())) {
//					// 对比Access表
//					Access accessCar = findOneAccess(car, carTime, accessWebcam
//							.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
//					if (accessCar != null) {// 9、有申请通过记录 开门
//						if (openDoor(ipaddr)) {// 11、开门
//							open_Chuli("（申请记录）",buffer);
//						} else {
//							System.out.println(car + "开门失败！（申请记录）请检查网络。");
//							buffer.append(car + "开门失败！（申请记录）请检查网络。");
//						}
//					} else {// 9、没有申请记录 走不开门流程 进门=》走紧急开门流程
//						LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(),
//								4);// 推送LED消息
//						System.out.println(inEmployeeCarInfor.getName()
//								+ "您好，当前为非工作时间，您的车牌为" + car
//								+ "的车辆无法进门，如需进门，请先申请加班！");
//						buffer.append(inEmployeeCarInfor.getName()
//								+ "您好，当前为非工作时间，您的车牌为" + car
//								+ "的车辆无法进门，如需进门，请先申请加班！");
//					}
//				}
//				else {
//				}
				if ("出门".equals(accessWebcam.getWebcamOutIn())) {
					if("是".equals(inEmployeeCarInfor.getNbdaijian())){
						if("是".equals(inEmployeeCarInfor.getNbdaiSQ())){
							// 待申请流程
							DaiShenQing(INTERNAL, false, buffer);// 内部员工出门需申请
						}else {
							// 待检流程
							DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
						}
					}else {
						if("是".equals(inEmployeeCarInfor.getNbdaiSQ())){
							// 待申请流程
							DaiShenQing(INTERNAL, true, buffer);// 内部员工出门需申请
						}else {
							//不为待检车辆
							if (openDoor(ipaddr)) {
								// if开门成功 添加开门记录
								open_Chuli("（内部白名单）",buffer);
							} else {
								System.out.println(car + "开门失败！（内部白名单）请检查网络。");
								buffer.append(car+ accessWebcam.getWebcamOutIn() + "开门失败！（内部白名单）请检查网络。");
							}
						}
					}
				} else {
					// 不为待检车辆，不是出门 开门
					if (openDoor(ipaddr)) {
						// if开门成功 添加开门记录
						open_Chuli("（内部白名单）",buffer);
					} else {
						System.out.println(car + "开门失败！（内部白名单）请检查网络。");
						buffer.append(car+ accessWebcam.getWebcamOutIn() + "开门失败！（内部白名单）请检查网络。");
					}
				}
//			}
		} else {
			// 第一次出入 直接 开门
			if ("出门".equals(accessWebcam.getWebcamOutIn())
					&& "是".equals(inEmployeeCarInfor.getNbdaijian())) {
				// 待检流程
				DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
			} else {
				// 不为待检车辆，不是出门 开门
				if (openDoor(ipaddr)) {
					// if开门成功 添加开门记录
					open_Chuli("（内部白名单）",buffer);
					/**** 添加车辆进出类型表1 *****************************/
					AccessRecordsServerImpl.createCarInOutType(car);
				} else {
					System.out.println(car + "开门失败！（内部白名单）请检查网络。");
					buffer.append(car+ accessWebcam.getWebcamOutIn() + "首次开门失败！（内部白名单）请检查网络。");
				}
			}
//			if ("正常".equals(inEmployeeCarInfor.getBorrowStatus())) {
//			} else if ("出借".equals(inEmployeeCarInfor.getBorrowStatus())) {
//				// 对比Access表
//				Access accessCar = findOneAccess(car, carTime, accessWebcam
//						.getWebcamOutIn());// 查询验证表中是否有对应的开门信息
//				if (accessCar != null) {// 9、有申请通过记录 开门
//					if (openDoor(ipaddr)) {// 11、开门
//						open_Chuli("（申请记录）",buffer);
//						/**** 添加车辆进出类型表1 *****************************/
//						AccessRecordsServerImpl.createCarInOutType(car);
//					} else {
//						System.out.println(car + "开门失败！（申请记录）请检查网络。");
//					}
//				} else {// 9、没有申请记录 走不开门流程 进门=》走紧急开门流程
//					LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 4);// 推送LED消息
//					System.out.println(inEmployeeCarInfor.getName()
//							+ "您好，当前为非工作时间，您的车牌为" + car
//							+ "的车辆无法进门，如需进门，请先申请加班！");
//				}
//			} else {
//				if ("出门".equals(accessWebcam.getWebcamOutIn())
//						&& "是".equals(inEmployeeCarInfor.getNbdaijian())) {
//					// 待检流程
//					DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
//				} else {
//					// 不为待检车辆，不是出门 开门
//					if (openDoor(ipaddr)) {
//						// if开门成功 添加开门记录
//						open_Chuli("（内部白名单）",buffer);
//						/**** 添加车辆进出类型表1 *****************************/
//						AccessRecordsServerImpl.createCarInOutType(car);
//					} else {
//						System.out.println(car + "开门失败！（内部白名单）请检查网络。");
//					}
//				}
//			}
		}
	}

	//内部车辆出门需申请
	private void DaiShenQing(String string, boolean b,
			StringBuffer buffer) {
		// TODO Auto-generated method stub
		Access access = (Access) totalDao.getObjectByCondition("from Access where carPai = ? and failtime > ? and useSend = '未使用'", car, Util.getDateTime());
		if(access==null){
			LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 16);// 推送LED消息
		}else {
			if(b){
				if (openDoor(ipaddr)) {// 9、开门
					open_Chuli("（白名单(出门申请)）",buffer);
				} else {
					System.out.println(car + "开门失败！（正常开门时间）请检查网络。");
				}
			}else {
				DaiJianChe(INTERNAL, "",buffer);// 内部员工出门徐待检流程
			}
			access.setUseSend("已失效");
			totalDao.update2(access);
		}
	}

	// 开门后的处理 来访 进门
	private void open_Chuli_Lai(String type,StringBuffer buffer) {
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 1);// 推送LED消息
		accessRecordZong.setRecordStatus("已开门");
		accessRecordZong.setInName(accessCar.getOutInName());// 添加来访人名称
		totalDao.update2(accessRecordZong);
		System.out.println(accessCar.getOutInName() + " 车牌号为" + car + "的车辆已正常"
				+ accessWebcam.getWebcamOutIn() + type);
		buffer.append(accessCar.getOutInName() + " 车牌号为" + car + "的车辆已正常"
				+ accessWebcam.getWebcamOutIn() + type);
	}

	// 开门后的处理 内部
	private void open_Chuli(String type, StringBuffer buffer) {
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 1);// 推送LED消息
		accessRecordZong.setRecordStatus("已开门");
		accessRecordZong.setInDept(inEmployeeCarInfor.getNdept());
		totalDao.update2(accessRecordZong);
		System.out.println(inEmployeeCarInfor.getName() + " 车牌号为" + car
				+ "的车辆已正常" + accessWebcam.getWebcamOutIn() + type);
		buffer.append(inEmployeeCarInfor.getName() + " 车牌号为" + car
				+ "的车辆已正常" + accessWebcam.getWebcamOutIn() + type);
	}

	// 开门后的处理 常访
	private void open_Chuli_cang(String type, StringBuffer buffer) {
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 1);// 推送LED消息
		accessRecordZong.setRecordStatus("已开门");
		totalDao.update2(accessRecordZong);
		System.out.println(inEmployeeCarInfor.getOftenname() + " 车牌号为" + car
				+ "的车辆已正常" + accessWebcam.getWebcamOutIn() + type);
		buffer.append(inEmployeeCarInfor.getOftenname() + " 车牌号为" + car
				+ "的车辆已正常" + accessWebcam.getWebcamOutIn() + type);
	}	
	// 对比车辆内外状态（非首次进入需要）内部
	private void web_carTow() {
		// 相等
		accessRecordZong.setWaitCheck("待修改");
		totalDao.update2(accessRecordZong);
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 0);// 推送LED消息
		System.out.println(inEmployeeCarInfor.getName() + " 车牌号为" + car
				+ "的车辆已 " + accessWebcam.getWebcamOutIn() + "，请门卫刷卡开门");
	}

	// 对比车辆内外状态（非首次进入需要）常访
	private void web_carTow_Cang() {
		// 相等
		LedCarPush.ledShow(car, accessWebcam.getWebcamOutIn(), 0);// 推送LED消息
		System.out.println(inEmployeeCarInfor.getOftenname() + " 车牌号为" + car
				+ "的车辆已 " + accessWebcam.getWebcamOutIn() + "，无法开门");
	}

	// 添加识别车牌记录
	public void accessRecordZong(String typ) {
		accessRecordZong = null;
		if (INTERNAL.equals(typ) && !"".equals(car) && !"".equals("_无_")) {
			accessRecordZong = AccessRecordsServerImpl.createAccessRecordCarCardId(
					"车牌", car, typ, inEmployeeCarInfor.getNcode(),
					inEmployeeCarInfor.getCar_User_Id(), null,
					inEmployeeCarInfor.getName(),
					accessWebcam.getWebcamOutIn(), "车行道", accessWebcam
							.getWebcamLocation(), accessWebcam.getId(),
					accessWebcam.getWebcamIP(), accessWebcam.getAeqt_id(),
					accessWebcam.getAeqt_ip(), null, inEmployeeCarInfor.getNcardId(), inEmployeeCarInfor.getIsKong());
		} else if (OFTEN_VISIT.equals(typ) && !"".equals(car)
				&& !"".equals("_无_")) {
			accessRecordZong = AccessRecordsServerImpl.createAccessRecordCar(
					"车牌", car, typ, null, null, null, inEmployeeCarInfor
							.getOftenname(), accessWebcam.getWebcamOutIn(),
					"车行道", accessWebcam.getWebcamLocation(), accessWebcam
							.getId(), accessWebcam.getWebcamIP(), accessWebcam
							.getAeqt_id(), accessWebcam.getAeqt_ip(), null);
		} else if (VISITS.equals(typ) && !"".equals(car) && !"".equals("_无_")) {
			accessRecordZong = AccessRecordsServerImpl.createAccessRecordCar(
					"车牌", car, typ, null, null, null, null, accessWebcam
							.getWebcamOutIn(), "车行道", accessWebcam
							.getWebcamLocation(), accessWebcam.getId(),
					accessWebcam.getWebcamIP(), accessWebcam.getAeqt_id(),
					accessWebcam.getAeqt_ip(), null);
		} else {
			System.out.println("车辆类型不明。。。。。。");
		}
	}

	@Override
	public String oneOpen() {
		// TODO Auto-generated method stub where webcamLocation='大门'
		/****
		 * 一键开门流程 1、查询记录表中最近一次车辆扫描记录 得到车牌和摄像头ip 　如果不为空，才开门，状态改为已开门，
		 * 2、如果第一条为已识别，将记录改为已开门 --2、得到ip摄像头最近一次车牌号记录 --3、将得到的车牌号与记录表中的对比。
		 * --4、相等，激发摄像头做开门操作，同时查询记录表。如有车辆相关信息。设置为新的状态，如果没有，添加状态。
		 * --5、不相等，证明扫描的车牌信息不符。需要再次触发，相等，方可开门
		 */
		AccessWebcam accessWebcam = (AccessWebcam) totalDao
				.getObjectByCondition("from AccessWebcam where aeqt_id = 1");
		if (accessWebcam != null && accessWebcam.getWebcamIP() != null
				&& !"".equals(accessWebcam.getWebcamIP())) {
			DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("OneOpen:"), null, null, null, accessWebcam.getWebcamOutIn(), accessWebcam.getWebcamName(), accessWebcam.getId(), accessWebcam.getWebcamIP());
			if (openDoor(accessWebcam.getWebcamIP()))
				return "开门成功";
			else
				return "开门失败"+accessWebcam.getWebcamIP();
		}
		return "摄像头IP为空！";
	}

	public String oneOpenXin() {
		// TODO Auto-generated method stub where webcamLocation='大门'
		/****
		 * 新一键开门流程 1、查询记录表中最近一次车辆扫描记录 得到车牌和摄像头ip 　如果不为空，才开门，状态改为已开门，
		 * 2、如果第一条为已识别，将记录改为已开门 --2、得到ip摄像头最近一次车牌号记录 --3、将得到的车牌号与记录表中的对比。
		 * --4、相等，激发摄像头做开门操作，同时查询记录表。如有车辆相关信息。设置为新的状态，如果没有，添加状态。
		 * --5、不相等，证明扫描的车牌信息不符。需要再次触发，相等，方可开门
		 */
		AccessRecords accessRecords = (AccessRecords) totalDao
				.getObjectByCondition("from AccessRecords where recordType='车牌' order by id desc");
		if (!"_无_".equals(accessRecords.getRecordContents())
				&& "已识别".equals(accessRecords.getRecordStatus())) {
			StringBuffer buffer = new StringBuffer();
			if (openDoor(accessRecords.getAsWeam_ip())) {
				// if开门成功 添加开门记录
				accessRecords.setRecordStatus("已开门");
				totalDao.update2(accessRecords);
				/******************************* 封装6 *************************************/
				AccessRecordsServerImpl.createCarInOutType(accessRecords
						.getRecordContents());
				buffer.append("OneOpenXin:成功+"+accessRecords.getId());
				DoorBangDingServerImpl.caeLogInfor(buffer, 
						accessRecords.getRecordContents(), null, accessRecords.getInName(), 
						accessRecords.getOpenType(), accessRecords.getAsWeam_ip(), 
						accessRecords.getAsWeam_id(), accessRecords.getAsEqt_ip()+" "+accessRecords.getAsEqt_id());
				return "开门成功";
			} else {
				buffer.append("OneOpenXin:失败+"+accessRecords.getId());
				DoorBangDingServerImpl.caeLogInfor(buffer, 
						accessRecords.getRecordContents(), null, accessRecords.getInName(), 
						accessRecords.getOpenType(), accessRecords.getAsWeam_ip(), 
						accessRecords.getAsWeam_id(), accessRecords.getAsEqt_ip()+" "+accessRecords.getAsEqt_id());
				return "开门失败";
			}
		}
		DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("OneOpenXin:没有已识别车牌+"+accessRecords.getId()), 
				accessRecords.getRecordContents(), null, accessRecords.getInName(), 
				accessRecords.getOpenType(), accessRecords.getAsWeam_ip(), 
				accessRecords.getAsWeam_id(), accessRecords.getAsEqt_ip()+" "+accessRecords.getAsEqt_id());
		return "没有识别到车牌,请重新识别";
	}

}

