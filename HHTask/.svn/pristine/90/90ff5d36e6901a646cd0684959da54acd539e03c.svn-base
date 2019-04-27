package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;
import com.task.entity.dangan.DangAnBank;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.seal.SealLog;

public class SocketServersChunDang extends Thread {

	public static final int PORT = 8870;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersChunDang(TotalDao toalDao) {
		this.toalDao = toalDao;
	}
 
	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8870监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("ChunDang ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadCD(server.accept(), clientcount, toalDao)
						.start();
			}
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}

	public TotalDao getToalDao() {
		return toalDao;
	}

	public void setToalDao(TotalDao toalDao) {
		this.toalDao = toalDao;
	}
}

@SuppressWarnings("unchecked")
class ServerThreadCD extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadCD(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的档案机数: " + number);
	}

	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		BufferedInputStream bis3 = null;
		BufferedInputStream bis4 = null;
		InputStream in = null;
		StringBuilder builder = new StringBuilder();
		String accessIP = "";// 门禁IP
		String yanzheng = "";// 验证码
		String cardId = "";// 卡号
		String adminCordId = "";// 管理员卡号
		String adminStatus = "";// 卡绑定状态
		AccessEquipment accessEquipment = null;
		String nowDate = Util.getDateTime("yyyy-MM-dd");// 系统当前日期
		String nowDateTime = Util.getDateTime();// 系统当前时间
		try {
			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			// 先接收接收第一个字符 用做标识
			System.out.println("服务端开始接受标识！");
			byte[] biao_data = new byte[1];// 先接收第1个字符
			bis.read(biao_data);// 读取数据
			String mess1 = Util.byteArrayToHexString(biao_data);
			System.out.println(accessIP+"验证码刷卡类型标识: " + mess1);
			/**
			 * 第一步： 根据接收到IP或SIM标识去查找门禁设备
			 */
			String accessType = "";// 门禁类型
			List acElist = totalDao.query("from AccessEquipment where equipmentIP=? order by id desc",accessIP);
			if (acElist != null && acElist.size() > 0) {
				accessEquipment = (AccessEquipment) acElist.get(0);
				accessType = accessEquipment.getEquipmentDaoType();
				adminCordId = accessEquipment.getAdminCardId();
				adminStatus = accessEquipment.getAdminStatus();
			}
			builder.append(mess1 + ",+" + accessType);
			if ("AA".equals(mess1)) {
				/************************ 验证码流程 **********************/
				byte[] yg_cardId = new byte[6];// 开始接受6验证码
				bis.read(yg_cardId);// 读取数据
				int _byteLen = yg_cardId.length;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < _byteLen; i++) {
					int n = yg_cardId[i];
					sb.append(n);
				}
				builder.append(",接收验证码为：" + sb.toString());
				List listda = totalDao.query("from DangAn where status = '同意' and yanzheng = ? and useStatus = '使用中' and cdAceNum = (select equipmentNum from AccessEquipment where equipmentIP = ?) and addTime > ? order by id desc",sb.toString(), accessIP, nowDateTime);// 加上id作对比
				if (listda != null && listda.size() > 0) {
					DangAn bangDing = (DangAn) listda.get(0);
					// 先保存
					bangDing.setInTime(Util.getDateTime());
					if (totalDao.update2(bangDing)) {
						getsocket(socket, 2);// 开门指令
					} else {
						getsocket(socket, 0);// 复位指令
					}
				} else {
					getsocket(socket, 0);
				}
			} else if ("00".equals(mess1) && "档案".equals(accessType)) {
				/************************* 刷卡流程 **********************/
				byte[] yg_cardId = new byte[3];// 开始接受4位16进制的卡号
				bis.read(yg_cardId);// 读取数据
				byte[] zong_code = new byte[biao_data.length + yg_cardId.length];
				for (int i = 0; i < zong_code.length; i++) {
					if (i < biao_data.length) {
						zong_code[i] = biao_data[i];
					} else {
						zong_code[i] = yg_cardId[i - biao_data.length];
					}
				}
				String card = Util.byteArrayToHexStringK(zong_code).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
				System.out.println("sdasdasd：" + card);
				try {
					Integer cardNumber = Integer.parseInt(card, 16);
					System.out.println("处理之后的cardNumber为：" + cardNumber);
					cardId = cardNumber.toString();
				} catch (Exception e) {
					cardId = Util.yanNumber(10);
				}
				int num = cardId.length();
				for (int i = num; i < 10; i++) {
					cardId = "0" + cardId;
				}
				builder.append(",处理之后的卡号为：" + cardId);
				// 查询Users表是否存在。
				// 1、判断是否为内部（users表）
				List<Users> userList = totalDao.query("from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') ",cardId);
				if (userList != null && userList.size() > 0) {
					// 2是内部员工卡
					Users users = userList.get(0);
					//在此处判断是否为管理员卡
					/******************************/
					if (adminCordId!=null&& !"".equals(adminCordId) && adminCordId.equals(cardId)) {
						getsocketbyte(socket, zong_code);
						bis2 = new BufferedInputStream(in);
						byte[] admin_data = new byte[1];// 再接收第EE字符
						bis2.read(admin_data);// 读取数据
						String mess = Util.byteArrayToHexString(admin_data);
						System.out.println("管理员卡号添加结果返回: " + mess);
						builder.append(", 管理员卡号添加结果返回:" + mess);
						if ("FF".equals(mess)) {
							//添加成功
							if (!"已设置".equals(adminStatus)) {
								accessEquipment.setAdminStatus("已设置");
								totalDao.update2(accessEquipment);
							}
							builder.append(", 管理员添加成功");
						}
					}else {
						/******************************/
						byte[] men_data = new byte[1];// 再接收第5个字符
						bis.read(men_data);// 读取数据
						String mess = Util.byteArrayToHexString(men_data);
						System.out.println("刷卡类型标识: " + mess);
						builder.append(", 刷卡类型标识" + mess);
						// 根据标识判断处理类型。
						if ("BB".equals(mess)) {// 进门
							// 2、判断此门是否有权限开
							// 根据卡号去查档案进出记录表 有未失效的验证码
							List listdbd = totalDao.query("from DangAn where status = '同意' and shixiaodate > ? and useStatus not in ('已失效','已进门') and sqCardId = ? and cdAceNum = (select equipmentNum from AccessEquipment where equipmentIP = ?) order by id desc",nowDateTime, users.getCardId(), accessIP);// 加上id作对比
							if (listdbd != null && listdbd.size() > 0) {
								DangAn bangDing = (DangAn) listdbd.get(0);
								if (bangDing != null && bangDing.getYanzheng() != null && !"".equals(bangDing.getYanzheng())) {
									bangDing.setUseStatus("使用中");
									totalDao.update2(bangDing);	
									// 处理验证码
									yanzheng = bangDing.getYanzheng();
									if (yanzheng!=null && !"".equals(yanzheng)) {
										//发送验证码
										int[] yz = Util.yanzhengintSz(yanzheng);
										getsocket256wei(socket, yz[0], yz[1], yz[2]);
										/****************************************/
										/***************** 处理个人信息 *******************/
										String us = users.getCode();//工号
										int num1 = us.length();
										for (int i = num1; i < 4; i++) {
											us = "0" + us;
										}
										String[] strs = us.split("");
										char[] strChar = new char[4];
										for (int i = 1; i < strs.length; i++) {
											int ic = Integer.parseInt(strs[i]);
											char ch = (char) (ic);
											strChar[i - 1] = ch;
										}
										String nameN = Util.getLimitLengthString(users.getName(), 8);
										String deptD = Util.getLimitLengthString(users.getDept(), 8);
										try {
											int si = 8 - nameN.getBytes("gb2312").length;
											for (int i = 0; i < si; i++) {
												nameN = nameN + " ";
											}
											int sl = 8 - deptD.getBytes("gb2312").length;
											for (int i = 0; i < sl; i++) {
												deptD = deptD + " ";
											}
											System.out.println(nameN);
											System.out.println(deptD);
										} catch (UnsupportedEncodingException e) {
											e.printStackTrace();
										}
										// 等待接收保存返回标识
										bis1 = new BufferedInputStream(in);
										byte[] dd_f = new byte[1];// 再接收第1个字符
										bis1.read(dd_f);// 读取数据
										String yanm = Util.byteArrayToHexString(dd_f).replaceAll(" ", "");
										System.out.println("验证码返回后标识: " + yanm);
										if ("DD".equals(yanm)) {
											// 发送个人信息
											BufferedWriter bw = new BufferedWriter(
													new OutputStreamWriter(socket
															.getOutputStream()));
											bw.write(0x01);//
											bw.write(strChar);//
											bw.write(nameN);//
											bw.write(deptD);//
											bw.write(0x0A);//
											bw.flush();
											bw.close();
											builder.append(" 收到验证码!"+yanzheng);
										} else {
											//不确认设备收到验证码
											getsocket(socket, 0);
											builder.append("不确认设备收到验证码!");
										}
									}else {
										//验证码为空
										getsocket(socket, 0);
										builder.append("验证码为空");
									}
									// 设置状态为：内 并添加记录
								} else {
									// 没有申请记录 不开门 返回0
									getsocket(socket, 0);
									builder.append("没有档案申请记录 或申请的验证码为空 不开门 返回0");
								}
							} else {
								// 没有申请记录 不开门 返回0
								getsocket(socket, 0);
								builder.append("没有申请记录 不开门 返回0");
							}
						} else if ("CC".equals(mess)) {// 出门
							// 保存打卡记录
							List listinfor = totalDao.query("from AccessLogInfor where aceIp = ? and cardId = ? and useStatus = '1' and yanzheng is not null and yanzheng <> '' and addTime > ? order by id desc",accessIP, users.getCardId(), nowDate);// 加上id作对比
							if (listinfor != null && listinfor.size() > 0) {
								AccessLogInfor accessLogInfor = (AccessLogInfor) listinfor.get(0);
								// 查询进门记录
								List listdbd = totalDao.query("from DangAn where status = '同意' and useStatus = '已进门' and sqCardId = ? and yanzheng = ? and cdAceNum = (select equipmentNum from AccessEquipment where equipmentIP = ?) order by id desc",users.getCardId(), accessLogInfor.getYanzheng(), accessIP);// 加上id作对比
								if (listdbd != null && listdbd.size() > 0) {
									String outTime = Util.getDateTime();
									DangAn an = (DangAn) listdbd.get(0);
									an.setUseStatus("已失效");
									an.setOutTime(outTime);
									if (an.getInTime() != null&& an.getInTime().length() > 0)
										an.setUseDate(Util.timeDifference(an.getInTime(), outTime));
									if (totalDao.update2(an)) {
										accessLogInfor.setUseStatus("2");
										totalDao.update2(accessLogInfor);
										// 输入正确 返回开门指令
										getsocket(socket, 2);
									}
								} else {// 没有正在使用中的申请记录
									getsocket(socket, 0);
								}
							}
						} else {
							// 不分进出门??
							getsocket(socket, 0);
						}
					}
				} else {
					// 2不是内部员工
					getsocket(socket, 0);
				}
			} else if ("EE".equals(mess1) && "档案".equals(accessType)) {
				// 查询最后一条验证码不为空的记录
				List listinfor = totalDao
						.query(
								"from AccessLogInfor where aceIp = ? and yanzheng is not null and yanzheng <> '' and addTime > ? order by id desc",
								accessIP, nowDate);// 加上id作对比
				if (listinfor != null && listinfor.size() > 0) {
					AccessLogInfor accessLogInfor = (AccessLogInfor) listinfor.get(0);
					List listda = totalDao
							.query(
									"from DangAn where status = '同意' and useStatus = '使用中' and sqCardId = ? and yanzheng = ? and cdAceNum = (select equipmentNum from AccessEquipment where equipmentIP = ?) order by id desc",
									accessLogInfor.getCardId(), accessLogInfor
											.getYanzheng(), accessIP);// 加上id作对比
					if (listda != null && listda.size() > 0) {
						DangAn an = (DangAn) listda.get(0);
						an.setUseStatus("已进门");
						an.setInTime(Util.getDateTime());
						if (totalDao.update2(an)) {
							accessLogInfor.setUseStatus("1");
							totalDao.update2(accessLogInfor);
						}
					}
				}
			} else if (("AB".equals(mess1) && "档案柜".equals(accessType))
					|| ("AC".equals(mess1) && "档案柜".equals(accessType))) {
				// 存档&取档
				String cunqu = "无";
				boolean cqty = true;
				if ("AB".equals(mess1)) {
					cunqu = "存档";
				} else {
					cunqu = "取档";
					cqty = false;
				}
				bis1 = new BufferedInputStream(in);// 接收卡号
				byte[] yg_df = new byte[1];// 开始接受第一位标识，如果是DF结束
				bis1.read(yg_df);// 读取数据
				String df = Util.byteArrayToHexStringK(yg_df).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
				System.out.println("档案柜接收标识+" + df);
				if ("DF".equals(df)) {
					//DF不做操作 直接返回主界面
				} else {
					byte[] userCode = new byte[3];
					bis1.read(userCode);
					byte[] zong_code = new byte[yg_df.length + userCode.length];
					for (int i = 0; i < zong_code.length; i++) {
						if (i < yg_df.length) {
							zong_code[i] = yg_df[i];
						} else {
							zong_code[i] = userCode[i - yg_df.length];
						}
					}
					String card = Util.byteArrayToHexStringK(zong_code)
							.replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("存取档卡号为：" + card);
					try {
						Integer cardNumber = Integer.parseInt(card, 16);
						System.out.println("处理之后的cardNumber为：" + cardNumber);
						cardId = cardNumber.toString();
					} catch (Exception e) {
						cardId = Util.yanNumber(10);
					}
					cardId = Util.cardId(cardId);
					builder.append(",处理之后的卡号("+cunqu+")为：" + cardId);
					// 查询Users表是否存在。
					// 1、判断是否为内部（users表）
					List<Users> userList = totalDao
							.query(
									"from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是'",
									cardId);
					// 判断有无存取档记录
					if (userList != null && userList.size() > 0) {
						// 查询ArchiveUnarchiverAplt表中有无申请记录
						List<ArchiveUnarchiverAplt> aplt = totalDao
								.query(
										"from ArchiveUnarchiverAplt where cqType = ? and cardId = ? and ace_Ip = ? and daGuiposition is not null and daGuiposition <> '' and useType = '未使用' and shixiaoTime > ? order by id desc",
										cunqu, cardId, accessIP, nowDateTime);// , nowTime
						if (aplt != null && aplt.size() > 0) {
							getsocketNoClose(socket, 6);//返回选柜界面
							int i = 0;
							while (i < aplt.size()) {
								bis2 = new BufferedInputStream(in);// 接收柜号
								byte[] accessGui = new byte[1];// 柜号b1~bf
								bis2.read(accessGui);
								String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
										"");// 十六进制转换为String类型并去掉空格
								if ("DF".equals(guiHao)) {
									i = aplt.size();
								} else {
									boolean b = false;
									for (ArchiveUnarchiverAplt aplt2 : aplt) {
										if (guiHao.equals(aplt2.getDaGuiposition())
												&& "未使用".equals(aplt2.getUseType())) {
											b = true;
										}
									}
									if (b) {
										for (ArchiveUnarchiverAplt aplt2 : aplt) {
											if (guiHao.equals(aplt2.getDaGuiposition())
													&& "未使用".equals(aplt2.getUseType())) {
												// 如果有相等的 将状态设置为已使用
												if (cqty) {
													getsocketNoClose(socket, 2);
												} else {
													// 处理验证码
													yanzheng = aplt2.getInCodes();
													if (yanzheng != null && !"".equals(yanzheng)) {
														getsocketNoClose(socket, 7);// 有申请，且验证码不为空，返回07选择打开方式
														bis4 = new BufferedInputStream(in);// 等待接收返回值
														byte[] bankyan = new byte[1];
														// AD:验证码&&AE:二维码
														bis4.read(bankyan);
														String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
																" ", "");// 十六进制转换为String类型并去掉空格
														System.out.println("返回结果：" + bank_yan);
														if ("AD".equals(bank_yan)) {
															int[] yz = Util.yanzhengintSz(yanzheng);
															getsocket256wei(socket, yz[0], yz[1], yz[2]);// 发送三个两位十六进制的验证码
														} else if ("AE".equals(bank_yan)) {//六位验证码给二维码对比
															char[] yz = Util.yanzhengchar(yanzheng);
															getsocketChar(socket, yz);// 发送char数组的验证码
														} else {
															// 返回的值不正确，返回选柜号页面 跳出for循环
															getsocketNoClose(socket, 6);
															break;
														}
													}else {
														//验证码为空，返回选柜号页面 跳出for循环
														getsocketNoClose(socket, 6);
														break;
													}
												}
												bis3 = new BufferedInputStream(in);// 等待接收返回值
												byte[] bankZhi = new byte[1];// EE:正确
												// CF:错误
												// DF:返回主界面
												bis3.read(bankZhi);
												String bank_zhi = Util.byteArrayToHexStringK(bankZhi).replaceAll(" ",
														"");// 十六进制转换为String类型并去掉空格
												System.out.println("返回结果：" + bank_zhi);
												if ("EE".equals(bank_zhi)) {
													// 已开柜，将状态改为已使用 在aplt中除去
													aplt2.setUseType("已使用");
													totalDao.update2(aplt2);
													/***********
													 * 存取档之后的操作 将price表中的状态改变， 将ArchiveUnarchiverAplt表中相同柜号的记录设置为已使用
													 ***********/
													updatePrice(nowDateTime, aplt2);
													/**状态回调**/
													i++;// i加一
													if (i < aplt.size() && !cqty) {// 取档成功，且还有未取的档案时发送06
														// 如果还有未使用的申请 继续发送06
														getsocketNoClose(socket, 6);// 返回选柜号页面
													} else if (i >= aplt.size()) {
														getsocketNoClose(socket, 5);// 返回主页面
													}
													break;
												} else if ("CF".equals(bank_zhi)) {// 返回上一页面
													break;
												} else if ("DF".equals(bank_zhi)) {// 返回主页面
													i = aplt.size();
													break;
												} else {
													i = aplt.size();
													break;
												}
											}
										}
										/** for **/
									} else {// 您没有该箱子的开门权限
										getsocketNoClose(socket, 4);
									}
								}
							}
							/** 当没有待存档或取档的任务的时候while结束，随后连接断开通讯结束 **/
							// 流程结束，页面返回主界面
						} else {
							// 没有申请记录 //存档返回1 取档返回0
							if (cqty)
								getsocket(socket, 1);
							else
								getsocket(socket, 0);
						}
					} else {
						if (cqty)
							getsocket(socket, 1);
						else
							getsocket(socket, 0);
					}
				}
			}
			else if (("AB".equals(mess1) && "档案柜A".equals(accessType))
					|| ("AC".equals(mess1) && "档案柜A".equals(accessType))) {
				// 存档&取档
				String cunqu = "无";
				boolean cqty = true;
				if ("AB".equals(mess1)) {
					cunqu = "存档";
				} else {
					cunqu = "取档";
					cqty = false;
				}
				bis1 = new BufferedInputStream(in);// 接收卡号
				byte[] yg_df = new byte[1];// 开始接受第一位标识，如果是DF结束
				bis1.read(yg_df);// 读取数据
				String df = Util.byteArrayToHexStringK(yg_df).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
				System.out.println("档案柜接收标识+" + df);
				if ("DF".equals(df)) {
					//DF不做操作 直接返回主界面
				} else {
					byte[] userCode = new byte[3];
					bis1.read(userCode);
					byte[] zong_code = new byte[yg_df.length + userCode.length];
					for (int i = 0; i < zong_code.length; i++) {
						if (i < yg_df.length) {
							zong_code[i] = yg_df[i];
						} else {
							zong_code[i] = userCode[i - yg_df.length];
						}
					}
					String card = Util.byteArrayToHexStringK(zong_code)
							.replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("存取档卡号为：" + card);
					try {
						Integer cardNumber = Integer.parseInt(card, 16);
						System.out.println("处理之后的cardNumber为：" + cardNumber);
						cardId = cardNumber.toString();
					} catch (Exception e) {
						cardId = Util.yanNumber(10);
					}
					cardId = Util.cardId(cardId);
					builder.append(",处理之后的卡号("+cunqu+")为：" + cardId);
					// 查询Users表是否存在。
					// 1、判断是否为内部（users表）
					List<Users> userList = totalDao
							.query(
									"from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是'",
									cardId);
					// 判断有无存取档记录
					if (userList != null && userList.size() > 0) {
						// 查询ArchiveUnarchiverAplt表中有无申请记录
						List<ArchiveUnarchiverAplt> aplt = totalDao
								.query(
										"from ArchiveUnarchiverAplt where cqType = ? and cardId = ? and ace_Ip = ? and daGuiposition is not null and daGuiposition <> '' and useType = '未使用' and shixiaoTime > ? order by id desc",
										cunqu, cardId, accessIP, nowDateTime);// , nowTime
						if (aplt != null && aplt.size() > 0) {
							getsocketNoClose(socket, 6);//返回选柜界面
							int i = 0;
							while (i < aplt.size()) {
								bis2 = new BufferedInputStream(in);// 接收柜号
								byte[] accessGui = new byte[1];// 柜号b1~bf
								bis2.read(accessGui);
								String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
										"");// 十六进制转换为String类型并去掉空格
								if ("DF".equals(guiHao)) {
									i = aplt.size();
								} else {
									boolean b = false;
									for (ArchiveUnarchiverAplt aplt2 : aplt) {
										if (guiHao.equals(aplt2.getDaGuiposition())
												&& "未使用".equals(aplt2.getUseType())) {
											b = true;
										}
									}
									if (b) {
										for (ArchiveUnarchiverAplt aplt2 : aplt) {
											if (guiHao.equals(aplt2.getDaGuiposition())
													&& "未使用".equals(aplt2.getUseType())) {
												// 如果有相等的 将状态设置为已使用
												if (cqty) {
													getsocketNoClose(socket, 2);
												} else {
													// 处理验证码
													yanzheng = aplt2.getInCodes();
//													if (yanzheng != null && !"".equals(yanzheng)) {
//														getsocketNoClose(socket, 7);// 有申请，且验证码不为空，返回07选择打开方式
//														bis4 = new BufferedInputStream(in);// 等待接收返回值
//														byte[] bankyan = new byte[1];
//														// AD:验证码&&AE:二维码
//														bis4.read(bankyan);
//														String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
//																" ", "");// 十六进制转换为String类型并去掉空格
//														System.out.println("返回结果：" + bank_yan);
//														if ("AD".equals(bank_yan)) {
															int[] yz = Util.yanzhengintSz(yanzheng);
															getsocket256wei(socket, yz[0], yz[1], yz[2]);// 发送三个两位十六进制的验证码
//														} else if ("AE".equals(bank_yan)) {//六位验证码给二维码对比
//															char[] yz = Util.yanzhengchar(yanzheng);
//															getsocketChar(socket, yz);// 发送char数组的验证码
//														} else {
//															// 返回的值不正确，返回选柜号页面 跳出for循环
//															getsocketNoClose(socket, 6);
//															break;
//														}
//													}else {
//														//验证码为空，返回选柜号页面 跳出for循环
//														getsocketNoClose(socket, 6);
//														break;
//													}
												}
												bis3 = new BufferedInputStream(in);// 等待接收返回值
												byte[] bankZhi = new byte[1];// EE:正确
												// CF:错误
												// DF:返回主界面
												bis3.read(bankZhi);
												String bank_zhi = Util.byteArrayToHexStringK(bankZhi).replaceAll(" ",
														"");// 十六进制转换为String类型并去掉空格
												System.out.println("返回结果：" + bank_zhi);
												if ("EE".equals(bank_zhi)) {
													// 已开柜，将状态改为已使用 在aplt中除去
													aplt2.setUseType("已使用");
													totalDao.update2(aplt2);
													addDangAnBank( accessIP, cardId, aplt2);
//													boolean fe = true;
//													while (fe) {
//														bis4 = new BufferedInputStream(in);// 等待接收返回值
//														byte[] bankZhi4 = new byte[1];// EE:正确
//														// CF:错误
//														// DF:返回主界面
//														bis3.read(bankZhi4);
//														String bank_zhi4 = Util.byteArrayToHexStringK(bankZhi4).replaceAll(" ",
//																"");// 十六进制转换为String类型并去掉空格
//														System.out.println("返回结果：" + bank_zhi4);
//														if("FF".equals(bank_zhi4)){
//															
//															if(fe){
//																fe = false;
//															}
//														}
//													}
													/***********
													 * 存取档之后的操作 将price表中的状态改变， 将ArchiveUnarchiverAplt表中相同柜号的记录设置为已使用
													 ***********/
													updatePrice(nowDateTime, aplt2);
													i++;// i加一
													if (i < aplt.size() && !cqty) {// 取档成功，且还有未取的档案时发送06
														// 如果还有未使用的申请 继续发送06
														getsocketNoClose(socket, 6);// 返回选柜号页面
													} else if (i >= aplt.size()) {
														getsocketNoClose(socket, 5);// 返回主页面
													}
													break;
												} else if ("CF".equals(bank_zhi)) {// 返回上一页面
													break;
												} else if ("DF".equals(bank_zhi)) {// 返回主页面
													i = aplt.size();
													break;
												} else {
													i = aplt.size();
													break;
												}
											}
										}
										/** for **/
									} else {// 您没有该箱子的开门权限
										getsocketNoClose(socket, 4);
									}
								}
							}
							/** 当没有待存档或取档的任务的时候while结束，随后连接断开通讯结束 **/
							// 流程结束，页面返回主界面
						} else {
							// 没有申请记录 //存档返回1 取档返回0
							if (cqty)
								getsocket(socket, 1);
							else
								getsocket(socket, 0);
						}
					} else {
						if (cqty)
							getsocket(socket, 1);
						else
							getsocket(socket, 0);
					}
				}
			} else if ("档案柜A".equals(accessType) && "00".equals(mess1)) {
				byte[] userCode = new byte[3];
				bis.read(userCode);
				byte[] zong_code = new byte[biao_data.length + userCode.length];
				for (int i = 0; i < zong_code.length; i++) {
					if (i < biao_data.length) {
						zong_code[i] = biao_data[i];
					} else {
						zong_code[i] = userCode[i - biao_data.length];
					}
				}
				String card = Util.byteArrayToHexStringK(zong_code)
						.replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
				System.out.println("存取档卡号为：" + card);
				try {
					Integer cardNumber = Integer.parseInt(card, 16);
					System.out.println("处理之后的cardNumber为：" + cardNumber);
					cardId = cardNumber.toString();
				} catch (Exception e) {
					cardId = Util.yanNumber(10);
				}
				cardId = Util.cardId(cardId);
				builder.append(",处理之后的卡号为：" + cardId);
				// 查询Users表是否存在。
				// 1、判断是否为内部（users表）
				List<Users> userList = totalDao
						.query(
								"from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是'",
								cardId);
				// 判断有无存取档记录
				if (userList != null && userList.size() > 0) {
					List<DangAnBank> anBanks = totalDao.query("from DangAnBank where daIp = ? and cardId = ? and useStatus = '未使用'", accessIP,cardId);
					if(anBanks!=null&&anBanks.size()>0){
						if(anBanks.size()>1){
							getsocketNoClose(socket, 6);//返回选柜界面
							int i = 0;
							while (i < anBanks.size()) {
								bis2 = new BufferedInputStream(in);// 接收柜号
								byte[] accessGui = new byte[1];// 柜号b1~bf
								bis2.read(accessGui);
								String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
										"");// 十六进制转换为String类型并去掉空格
								if ("DF".equals(guiHao)) {
									i = anBanks.size();
								} else {
									boolean b = false;
									for (DangAnBank aplt2 : anBanks) {
										if (guiHao.equals(aplt2.getDaNum())
												&& "未使用".equals(aplt2.getUseStatus())) {
											b = true;
										}
									}
									if (b) {
										for (DangAnBank aplt2 : anBanks) {
											if (guiHao.equals(aplt2.getDaNum())
													&& "未使用".equals(aplt2.getUseStatus())) {
												// 如果有相等的 将状态设置为已使用
													getsocketNoClose(socket, 2);
												bis3 = new BufferedInputStream(in);// 等待接收返回值
												byte[] bankZhi = new byte[1];// EE:正确
												// CF:错误
												// DF:返回主界面
												bis3.read(bankZhi);
												String bank_zhi = Util.byteArrayToHexStringK(bankZhi).replaceAll(" ",
														"");// 十六进制转换为String类型并去掉空格
												System.out.println("返回结果：" + bank_zhi);
												if ("EE".equals(bank_zhi)) {
													// 已开柜，将状态改为已使用 在aplt中除去
													aplt2.setUseStatus("已失效");
													totalDao.update2(aplt2);
													
													//必须接受到FF才可结束
													boolean fe = true;
													while (fe) {
														bis4 = new BufferedInputStream(in);// 等待接收返回值
														byte[] bankZhi4 = new byte[1];// FF:正确
														bis3.read(bankZhi4);
														String bank_zhi4 = Util.byteArrayToHexStringK(bankZhi4).replaceAll(" ",
														"");// 十六进制转换为String类型并去掉空格
														System.out.println("返回结果：" + bank_zhi4);
														if("FF".equals(bank_zhi4)){
															fe = false;
														}
													}
													
													/***********
													 * 存取档之后的操作 将price表中的状态改变， 将ArchiveUnarchiverAplt表中相同柜号的记录设置为已使用
													 ***********/
													i++;// i加一
													if (i < anBanks.size()) {// 取档成功，且还有未取的档案时发送06
														// 如果还有未使用的申请 继续发送06
														getsocketNoClose(socket, 6);// 返回选柜号页面
													} else if (i >= anBanks.size()) {
														getsocketNoClose(socket, 5);// 返回主页面
													}
													break;
												} else if ("CF".equals(bank_zhi)) {// 返回上一页面
													break;
												} else if ("DF".equals(bank_zhi)) {// 返回主页面
													i = anBanks.size();
													break;
												} else {
													i = anBanks.size();
													break;
												}
											}
										}
										/** for **/
									} else {// 您没有该箱子的开门权限
										getsocketNoClose(socket, 4);
									}
								}
							}
						}else {
							getsocketNoClose(socket, Integer.parseInt(anBanks.get(0).getDaNum(),16));
							bis1 = new BufferedInputStream(in);// 等待接收返回值
							byte[] bankZhi = new byte[1];// EE:正确
							// CF:错误
							// DF:返回主界面
							bis1.read(bankZhi);
							String bank_zhi = Util.byteArrayToHexStringK(bankZhi).replaceAll(" ",
									"");// 十六进制转换为String类型并去掉空格
							System.out.println("开门后返回结果：" + bank_zhi);
							anBanks.get(0).setUseStatus("已失效");
							totalDao.update2(anBanks.get(0));
						}
//						byte [] b = new byte[12];
//						for (Integer i = 0; i < b.length; i++) {
//							if(anBanks.contains(i))
//								b[i]=(byte) (i+1);
//							else
//								b[i]= 0;
//						}
//						getsocket12wei(socket, b);
//						//
					}else {
						System.out.println("请先选择存档取档。。。");
						getsocket(socket, 5);
					}
				}
			}else if (("AB".equals(mess1) && "档案柜B".equals(accessType))
					|| ("AC".equals(mess1) && "档案柜B".equals(accessType))) {
				// 存档&取档
				String cunqu = "无";
				boolean cqty = true;
				if ("AB".equals(mess1)) {
					cunqu = "存档";
				} else {
					cunqu = "取档";
					cqty = false;
				}
				bis1 = new BufferedInputStream(in);// 接收卡号
				byte[] yg_df = new byte[1];// 开始接受第一位标识，如果是DF结束
				bis1.read(yg_df);// 读取数据
				String df = Util.byteArrayToHexStringK(yg_df).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
				System.out.println("档案柜接收标识+" + df);
				if ("DF".equals(df)) {
					//DF不做操作 直接返回主界面
				} else {
					byte[] userCode = new byte[3];
					bis1.read(userCode);
					byte[] zong_code = new byte[yg_df.length + userCode.length];
					for (int i = 0; i < zong_code.length; i++) {
						if (i < yg_df.length) {
							zong_code[i] = yg_df[i];
						} else {
							zong_code[i] = userCode[i - yg_df.length];
						}
					}
					String card = Util.byteArrayToHexStringK(zong_code)
							.replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("存取档卡号为：" + card);
					try {
						Integer cardNumber = Integer.parseInt(card, 16);
						System.out.println("处理之后的cardNumber为：" + cardNumber);
						cardId = cardNumber.toString();
					} catch (Exception e) {
						cardId = Util.yanNumber(10);
					}
					cardId = Util.cardId(cardId);
					builder.append(",处理之后的卡号("+cunqu+")为：" + cardId);
					// 查询Users表是否存在。
					// 1、判断是否为内部（users表）
					List<Users> userList = totalDao
							.query(
									"from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是'",
									cardId);
					// 判断有无存取档记录
					if (userList != null && userList.size() > 0) {
						// 查询ArchiveUnarchiverAplt表中有无申请记录
						List<ArchiveUnarchiverAplt> aplt = totalDao
								.query(
										"from ArchiveUnarchiverAplt where cqType = ? and cardId = ? and ace_Ip = ? and daGuiposition is not null and daGuiposition <> '' and useType = '未使用' and shixiaoTime > ? order by id desc",
										cunqu, cardId, accessIP, nowDateTime);// , nowTime
						if (aplt != null && aplt.size() > 0) {
							getsocketNoClose(socket, 6);//返回选柜界面
							int i = 0;
							while (i < aplt.size()) {
								bis2 = new BufferedInputStream(in);// 接收柜号
								byte[] accessGui = new byte[1];// 柜号b1~bf
								bis2.read(accessGui);
								String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
										"");// 十六进制转换为String类型并去掉空格
								if ("DF".equals(guiHao)) {
									i = aplt.size();
								} else {
									boolean b = false;
									for (ArchiveUnarchiverAplt aplt2 : aplt) {
										if (guiHao.equals(aplt2.getDaGuiposition())
												&& "未使用".equals(aplt2.getUseType())) {
											b = true;
										}
									}
									if (b) {
										for (ArchiveUnarchiverAplt aplt2 : aplt) {
											if (guiHao.equals(aplt2.getDaGuiposition())
													&& "未使用".equals(aplt2.getUseType())) {
												// 如果有相等的 将状态设置为已使用
												if (cqty) {
													getsocketNoClose(socket, 2);
												} else {
													// 处理验证码
													yanzheng = aplt2.getInCodes();
//													if (yanzheng != null && !"".equals(yanzheng)) {
//														getsocketNoClose(socket, 7);// 有申请，且验证码不为空，返回07选择打开方式
//														bis4 = new BufferedInputStream(in);// 等待接收返回值
//														byte[] bankyan = new byte[1];
//														// AD:验证码&&AE:二维码
//														bis4.read(bankyan);
//														String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
//																" ", "");// 十六进制转换为String类型并去掉空格
//														System.out.println("返回结果：" + bank_yan);
//														if ("AD".equals(bank_yan)) {
															int[] yz = Util.yanzhengintSz(yanzheng);
															getsocket256wei(socket, yz[0], yz[1], yz[2]);// 发送三个两位十六进制的验证码
//														} else if ("AE".equals(bank_yan)) {//六位验证码给二维码对比
//															char[] yz = Util.yanzhengchar(yanzheng);
//															getsocketChar(socket, yz);// 发送char数组的验证码
//														} else {
//															// 返回的值不正确，返回选柜号页面 跳出for循环
//															getsocketNoClose(socket, 6);
//															break;
//														}
//													}else {
//														//验证码为空，返回选柜号页面 跳出for循环
//														getsocketNoClose(socket, 6);
//														break;
//													}
												}
												bis3 = new BufferedInputStream(in);// 等待接收返回值
												byte[] bankZhi = new byte[1];// EE:正确
												// CF:错误
												// DF:返回主界面
												bis3.read(bankZhi);
												String bank_zhi = Util.byteArrayToHexStringK(bankZhi).replaceAll(" ",
														"");// 十六进制转换为String类型并去掉空格
												System.out.println("返回结果：" + bank_zhi);
												if ("EE".equals(bank_zhi)) {
													// 已开柜，将状态改为已使用 在aplt中除去
													aplt2.setUseType("已使用");
													totalDao.update2(aplt2);
													/***********
													 * 存取档之后的操作 将price表中的状态改变， 将ArchiveUnarchiverAplt表中相同柜号的记录设置为已使用
													 ***********/
													updatePrice(nowDateTime, aplt2);
													i++;// i加一
													if (i < aplt.size() && !cqty) {// 取档成功，且还有未取的档案时发送06
														// 如果还有未使用的申请 继续发送06
														getsocketNoClose(socket, 6);// 返回选柜号页面
													} else if (i >= aplt.size()) {
														getsocketNoClose(socket, 5);// 返回主页面
													}
													break;
												} else if ("CF".equals(bank_zhi)) {// 返回上一页面
													break;
												} else if ("DF".equals(bank_zhi)) {// 返回主页面
													i = aplt.size();
													break;
												} else {
													i = aplt.size();
													break;
												}
											}
										}
										/** for **/
									} else {// 您没有该箱子的开门权限
										getsocketNoClose(socket, 4);
									}
								}
							}
							/** 当没有待存档或取档的任务的时候while结束，随后连接断开通讯结束 **/
							// 流程结束，页面返回主界面
						} else {
							// 没有申请记录 //存档返回1 取档返回0
							if (cqty)
								getsocket(socket, 1);
							else
								getsocket(socket, 0);
						}
					} else {
						if (cqty)
							getsocket(socket, 1);
						else
							getsocket(socket, 0);
					}
				}
			}else {
				// 无操作
				System.out.println("档案标识不正确。。。");
				getsocket(socket, 0);
			}
			System.out.println("服务端关闭 soko");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {bis.close();}
				if (bis1 != null) {bis1.close();}
				if (bis2 != null) {bis2.close();}
				if (bis3 != null) {bis3.close();}
				if (bis4 != null) {bis4.close();}
				if (in != null) {in.close();}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersChunDang.clientcount--;

					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersChunDang.clientcount);
					AccessLogInfor accessLogInfor = new AccessLogInfor();
					accessLogInfor.setYanzheng(yanzheng);
					accessLogInfor.setInfor(builder.toString());
					accessLogInfor.setAceIp(accessIP);// IP
					accessLogInfor.setCardId(cardId);//
					accessLogInfor.setAddTime(Util.getDateTime());
					totalDao.save2(accessLogInfor);
					System.out.println("++" + builder.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}
	}

	/**
	 * 添加再次开门记录
	 * @param accessIP
	 * @param cardId
	 * @param aplt2
	 */
	private void addDangAnBank(String accessIP, String cardId,
			ArchiveUnarchiverAplt aplt2) {
		DangAnBank anBank = new DangAnBank();
		anBank.setAddTime(Util.getDateTime());
		anBank.setCardId(cardId);
		anBank.setDaIp(accessIP);
		anBank.setDaNum(aplt2.getDaGuiposition());
		anBank.setUseStatus("未使用");
		totalDao.save2(anBank);
	}

	/**
	 * 存取档之后的操作 将price表中的状态改变
	 * @author licong
	 * @param nowDateTime
	 * @param aplt2
	 */
	private void updatePrice(String nowDateTime, ArchiveUnarchiverAplt aplt2) {
		if(aplt2.getDaId()!=null&&aplt2.getDaId()>0){
			List<Price> pricel = totalDao.query("from Price where id = ?", aplt2.getDaId()); 
			if(pricel!=null&&pricel.size()>0){
				Price price = pricel.get(0);
				price.setIsGuiDang("yes");
				price.setCunStatus("已存档");
				price.setDanganCunQuStatus("已存");
				price.setIsCunTime(nowDateTime);
				totalDao.update2(price);
				List<SealLog> seall = totalDao.query("from SealLog where documentId = ?", price.getId()); 
				if(seall!=null&&seall.size()>0){
					SealLog seal = seall.get(0);
					seal.setFujian2Status("已存档");
					seal.setIsCunTime(nowDateTime);
					totalDao.update2(seal);
				}
			}
		}
	}


	/**
	 * @author Li_Cong
	 * @param sockets
	 *            连接
	 * @param i
	 *            发送指令
	 * @throws IOException
	 *             异常抛出
	 */
	private static void getsocket(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(i);// 发送信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
		
	}

	/**
	 * @author Li_Cong
	 * @param sockets
	 *            连接
	 * @param i
	 *            发送指令
	 * @throws IOException
	 *             异常抛出
	 */
	private static void getsocketNoClose(Socket sockets, Integer i)
			throws IOException {
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
//				.getOutputStream()));
//		bw.write(i);// 发送信号
//		bw.flush();
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(i);
		out.flush();
		
	}

	/**
	 * 将验证码存入一个char数组 发送
	 * @param sockets
	 * @param yz
	 * @throws IOException
	 */
	public static void getsocketChar(Socket sockets, char[] yz)throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(0x03);// 发送信号
		bw.write(yz);// 发送信号
		bw.write(0x0A);// 发送信号
		bw.flush();
	}
	
	/**
	 * 发送三个两位十六进制的验证码
	 * @param sockets
	 * @param i
	 * @param i1
	 * @param i2
	 * @throws IOException
	 */
	public static void getsocket256wei(Socket sockets, int i, int i1, int i2)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(i);
		out.write(i1);
		out.write(i2);
		out.write(0x0A);
		out.flush();
	}
	
	/**
	 * 发送12位十六进制的开门编码
	 * @param sockets
	 * @throws IOException
	 */
	public static void getsocket12wei(Socket sockets, byte[] b)
	throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0xAF);
		out.write(b);
		out.flush();
	}
	
	/**
	 * 发送四个两位十六进制的验证码  adminCardId
	 * @param sockets
	 * @param i
	 * @param i1
	 * @param i2
	 * @param i3
	 * @throws IOException
	 */
	public static void getsocket256wei(Socket sockets, int i, int i1, int i2,
			int i3) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(i);
		out.write(i1);
		out.write(i2);
		out.write(i3);
		out.write(0x0A);
		out.flush();
	}
	
	/**
	 * 将验证码存入一个byte数组 发送
	 * @param sockets
	 * @param by
	 * @throws IOException
	 */
	public static void getsocketbyte(Socket sockets, byte[] by)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(by);
		out.write(0x0A);
		out.flush();
	}
	
	/***
	 * 读取数据
	 * 
	 * @param bis
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String readBis(InputStream is) throws Exception {
		// 再截取后四位
		byte[] nenghao_data = new byte[1];// 先接收第一个字符
		is.read(nenghao_data);// 读取数据
		return Util.byteArrayToHexString(nenghao_data);
	}

}
