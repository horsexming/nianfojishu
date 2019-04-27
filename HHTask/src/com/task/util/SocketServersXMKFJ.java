package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.task.Dao.TotalDao;
import com.task.ServerImpl.IntegralServerDaoImpl;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessTime;
import com.task.entity.menjin.DrinksType;

public class SocketServersXMKFJ extends Thread {

	public static final int PORT = 9988;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersXMKFJ(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8876监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("XMKFJ ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadXMKF(server.accept(), clientcount, toalDao)
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
class ServerThreadXMKF extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadXMKF(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的现磨咖啡机数: " + number);
	}

	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		BufferedInputStream bis3 = null;
		InputStream in = null;
		StringBuffer builder = null;
		String accessIP = "";
		String cardIds = "";// 卡号
		String yanzheng = "";// 来访人员验证码
		String username = "";// 员工姓名
		String accessname = "";// 设备名称
		String inOutType = "";// 进出类型
		Integer accessId = 0;// 设备id
		AccessEquipment accessEquipment = null;

		try {
			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			while(true){
				builder = new StringBuffer();
				in = socket.getInputStream();
				bis = new BufferedInputStream(in);
				// 先接收接收第一个字符 用做标识
				System.out.println("服务端开始接受标识！");
				byte[] biao_data = new byte[4];// 先接收第4个字符
				bis.read(biao_data);// 读取数据
				String mess1 = Util.byteArrayToHexString(biao_data).toString();
				System.out.println(accessIP + "标识: " + mess1);
				builder.append(mess1);
				accessIP = Util.toStringHex_1(mess1);
				System.out.println(accessIP + ": " + mess1);
				/**
				 * 第一步： 根据接收到IP或SIM标识去查找门禁设备
				 */
				String accessType = "";// 门禁类型
				List acElist = totalDao.query("from AccessEquipment where equipmentIP=? order by id desc",accessIP);
				if (acElist != null && acElist.size() > 0) {
					accessEquipment = (AccessEquipment) acElist.get(0);
					accessType = accessEquipment.getEquipmentDaoType();
					accessname = accessEquipment.getEquipmentName();
					accessId = accessEquipment.getId();
				}
				if ("NO.1".equals(accessIP) && "咖啡机".equals(accessType)) {
					byte[] yg_biao = new byte[1];// 开始接受标识
					bis.read(yg_biao);
					String biao_1 = Util.byteArrayToHexStringK(yg_biao).replaceAll(
							" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("心跳包：" + biao_1);
					if ("00".equals(biao_1)) {
						byte[] yg_cardId = new byte[3];// 开始接受3位16进制的卡号
						bis.read(yg_cardId);// 读取数据
						byte[] zong_code = new byte[yg_biao.length + yg_cardId.length];
						for (int i = 0; i < zong_code.length; i++) {
							if (i < yg_biao.length) {
								zong_code[i] = yg_biao[i];
							} else {
								zong_code[i] = yg_cardId[i - yg_biao.length];
							}
						}
						String card = Util.byteArrayToHexStringK(zong_code).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
						System.out.println("sdasdasd：" + card);
						String cardId = "";
						try {
							Integer cardNumber = Integer.parseInt(card, 16);
							System.out.println("处理之后的cardNumber1为：" + cardNumber);
							cardId = cardNumber.toString();
						} catch (Exception e) {
							cardId = Util.yanNumber(10);
						}
						cardId = Util.cardId(cardId);
						System.out.println("处理之后的卡号为：" + cardId);
						builder.append(",处理之后的卡号为：" + cardId);
						
						// 查询Users表是否存在。
						// 1、判断是否为内部（users表）
						List<Users> userList = totalDao.query("from Users where cardId = ? and onWork <>'离职' ",cardId);
						if (userList != null && userList.size() > 0) {// 2是内部员工卡判断有无积分
							username = userList.get(0).getName();
							cardIds = cardId;
							List<Integral> listJF = totalDao.query("from Integral where userId = (select id from Users where cardId=?)",cardId);
							if (listJF != null && listJF.size() > 0) {
								Integral integral = listJF.get(0);
								if (integral.getTotalIntegral() != null && integral.getTotalIntegral() >= 0) {
									getsocket_1(socket, 2);// 
									bis2 = new BufferedInputStream(in);
									byte[] isbei = new byte[4];
									bis2.read(isbei);
									byte[] isbei1 = new byte[1];
									bis2.read(isbei1);
									String oa = Util.byteArrayToHexString(isbei1).replaceAll(" ", "");
									System.out.println(",扣积分结果: " + oa);
									int xmkf_1 = 100;//现磨咖啡
									List<DrinksType> drinksTypes = totalDao.query("from DrinksType where drinkType = '3'");
									if (drinksTypes!=null&&drinksTypes.size()>0) xmkf_1 = drinksTypes.get(0).getDrinkJiFen();//给现磨咖啡赋值消费积分
									if ("0A".equals(oa)) builder.append("扣除"+xmkf_1+"积分："+IntegralServerDaoImpl.kouJiFen(accessType, xmkf_1, integral));//扣除积分
									else {
										getsocket_1(socket, 1);//非0A情况
										builder.append(",非0A情况:" + oa);
									}
								} else {
									getsocket_1(socket, 0);// 积分不足
									builder.append(",积分不足0发0:" + accessIP);
								}
							} else {
								getsocket_1(socket, 0);// 没有积分
								builder.append(",没有积分信息发0:" + accessIP);
							}
						} else {
							// 非内部卡发01
							getsocket_1(socket, 0);
							builder.append(",非内部卡发01:" + accessIP);
						}
					}else {
						//BB心跳包数据
						System.out.println(",收到无效指令:" + mess1);
					}
				} else {
					// 无操作
					System.out.println(",收到无效指令:" + mess1);
					builder.append(",收到无效指令:" + mess1);
					getsocket(socket, 0);
				}
				System.out.println("一杯咖啡流程结束");
				SocketServersXMKFJ.clientcount--;
				DoorBangDingServerImpl.caeLogInfor(builder, cardIds,
						yanzheng, username, inOutType, accessname,
						accessId, accessIP);
			}
		} catch (Exception e) {
			e.printStackTrace();
			builder.append(e);
		} finally {
			try {
				if (bis != null) {bis.close();}
				if (bis1 != null) {bis1.close();}
				if (bis2 != null) {bis2.close();}
				if (bis3 != null) {bis3.close();}
				if (in != null) {in.close();}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersXMKFJ.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersXMKFJ.clientcount);
					DoorBangDingServerImpl.caeLogInfor(builder, cardIds,
							yanzheng, username, inOutType, accessname,
							accessId, accessIP);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}
	}

	// 管理员卡无积分
	@SuppressWarnings("unused")
	private void adminWujifen(BufferedInputStream bis, Socket sockets,
			StringBuffer builder, InputStream in1) {
		// 等待接受F1~F6
		try {
			int i = 0;
			while (i < 1) {
				bis = new BufferedInputStream(in1);
				byte[] yingpin = new byte[1];
				bis.read(yingpin);// 饮品类型
				String fn = Util.byteArrayToHexString(yingpin).replaceAll(" ",
						"");
				System.out.println("管理员喝饮料返回: " + fn);
				builder.append(", 管理员喝饮料返回:" + fn);
				if ("F3".equals(fn) || "F6".equals(fn)) {
					// 不扣分
					getsocket_1(socket, 2);// 足够 发2
				} else if ("DF".equals(fn)) {
					// 返回
					i++;
				} else {
					getsocket_1(socket, 0);// 管理员卡，如果没有积分的话，只能和白开水
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			builder.append(", 管理员喝饮料异常:" + e);
		}
	}

	public static boolean InTime(List<AccessTime> time) {
		String nowTime = Util.getDateTime("HH:mm:ss");
		if (time != null && time.size() > 0) {
			boolean fan = false;
			for (AccessTime time2 : time) {
				boolean a0 = Util.compareTime(nowTime, "HH:mm:ss", time2
						.getStartTime(), "HH:mm:ss");
				boolean a1 = Util.compareTime(time2.getEndTime(), "HH:mm:ss",
						nowTime, "HH:mm:ss");
				fan = a0 && a1;
				if (fan) {
					return fan;
				}
			}
		}
		return false;
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
		bw.write(i);// 发送不开门信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}

	private static void getsocket_1(Socket sockets, Integer i)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets.getOutputStream()));
		bw.write(i);// 发送不开门信号
		bw.flush();
	}

	public static void getsocketbyte(Socket sockets, char[] by, String s, int i)
	throws IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(sockets.getOutputStream()));
		out.write(0x09);
		out.write(by);
		out.write(s);
		out.write(0x0A);
		out.flush();
	}
	
	/**
	 * 
	 * @param sockets
	 * @param by 工号
	 * @param s 姓名
	 * @param i 类型(1:剩余可用积分\2:品种消费积分)
	 * @throws IOException
	 */
	public static void getsocketbyte(Socket sockets, byte[] by, int i)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		switch (i) {
		case 1://剩余可用积分
			out.write(0x07);
			out.write(by);
			out.write(0x0A);
			out.flush();
			break;
		case 2://品种消费积分
			out.write(0x08);
			out.write(by);
			out.write(0x0A);
			out.flush();
			break;
		default:
			break;
		}
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
