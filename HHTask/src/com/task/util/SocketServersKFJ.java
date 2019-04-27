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

import com.task.Dao.TotalDao;
import com.task.ServerImpl.IntegralServerDaoImpl;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessTime;
import com.task.entity.menjin.DrinksType;

public class SocketServersKFJ extends Thread {

	public static final int PORT = 8876;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersKFJ(TotalDao toalDao) {
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
				System.out.println("KFJ ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadKF(server.accept(), clientcount, toalDao)
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
class ServerThreadKF extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadKF(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的咖啡机数: " + number);
	}

	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		BufferedInputStream bis3 = null;
		InputStream in = null;
		StringBuffer builder = new StringBuffer();
		String accessIP = "";
		String cardIds = "";// 卡号
		String yanzheng = "";// 来访人员验证码
		String adminCordId = "";// 管理员卡号
		String username = "";// 员工姓名
		String accessname = "";// 设备名称
		String inOutType = "";// 进出类型
		Integer accessId = 0;// 设备id
		AccessEquipment accessEquipment = null;

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
			System.out.println(accessIP + "标识: " + mess1);
			builder.append(mess1);
			/**
			 * 第一步： 根据接收到IP或SIM标识去查找门禁设备
			 */
			String accessType = "";// 门禁类型
			List acElist = totalDao
					.query(
							"from AccessEquipment where equipmentIP=? order by id desc",
							accessIP);
			if (acElist != null && acElist.size() > 0) {
				accessEquipment = (AccessEquipment) acElist.get(0);
				accessType = accessEquipment.getEquipmentDaoType();
				adminCordId = accessEquipment.getAdminCardId();
				accessname = accessEquipment.getEquipmentName();
				accessId = accessEquipment.getId();
			}
			if ("00".equals(mess1) && "咖啡机".equals(accessType)) {
				byte[] yg_cardId = new byte[3];// 开始接受3位16进制的卡号
				bis.read(yg_cardId);// 读取数据

				byte[] zong_code = new byte[biao_data.length + yg_cardId.length];
				for (int i = 0; i < zong_code.length; i++) {
					if (i < biao_data.length) {
						zong_code[i] = biao_data[i];
					} else {
						zong_code[i] = yg_cardId[i - biao_data.length];
					}
				}
				String card = Util.byteArrayToHexStringK(zong_code).replaceAll(
						" ", "");// 十六进制转换为String类型并去掉空格
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

				// 判断是否为管理员卡，
				// 对比卡号是否为管理员卡号
				boolean bbs = true;
				if (adminCordId!=null&&!"".equals(adminCordId) && adminCordId.equals(cardId)) {
					getsocket_1(socket, 5);
					bis2 = new BufferedInputStream(in);
					byte[] admin_data = new byte[1];// 再接收第EE字符
					bis2.read(admin_data);// 读取数据
					String messs = Util.byteArrayToHexString(admin_data);
					System.out.println("管理员返回: " + messs);
					builder.append(", 管理员返回:" + messs);
					if ("BB".equals(messs)) {// 设置模式=>结束
						builder.append(cardId + "进入设置模式");bbs = false;
					} else if ("AA".equals(messs)) {
					} else {}
				}
				
				//正常卡模式
				if(bbs) {
					// 查询Users表是否存在。
					// 1、判断是否为内部（users表）
					List<Users> userList = totalDao.query("from Users where cardId = ? and onWork <>'离职' ",cardId);
					if (userList != null && userList.size() > 0) {// 2是内部员工卡判断有无积分
						Users users = userList.get(0);
						List<Integral> listJF = totalDao.query("from Integral where userId = (select id from Users where cardId=?)",cardId);
						if (listJF != null && listJF.size() > 0) {
							Integral integral = listJF.get(0);
							if (integral.getTotalIntegral() != null && integral.getTotalIntegral() >= 0) {
								getsocket_1(socket, 4);// 等待接受CC&7F

								int i = 0,jf = 0,djf = 0;
								while (i == 0) {
									//处理员工信息（工号，姓名）
									String usName = Util.buqiString(users.getName(), 8);//姓名
									char[] strCharCodenum = Util.huoquChar(users.getCode(), 4);//工号
									Thread.sleep(100);
									//发送员工信息。
									getsocketbyte(socket, strCharCodenum, usName, 1);
									Thread.sleep(300);
									//发送剩余可用积分	
									getsocketbyte(socket, Util.jifenByte(integral.getTotalIntegral()+"", 4), 1);
									//获取杯子积分
									List<DrinksType> drinksTypelist = totalDao.query("from DrinksType where drinkType = '2'");
									if (drinksTypelist!=null&&drinksTypelist.size()>0) {
										djf = drinksTypelist.get(0).getDrinkJiFen();//杯子积分
									}
									//等待接收是否要落杯指令
									int f = 0;
									boolean b = true;//是否执行之后代码
									while (f == 0) {
										bis3 = new BufferedInputStream(in);
										byte[] isbei = new byte[1];
										bis3.read(isbei);
										String f7 = Util.byteArrayToHexString(isbei).replaceAll(" ", "");
										System.out.println(",落杯: " + f7);
										builder.append(",落杯:" + f7);
										if("F7".equals(f7)){//自带杯
											f++;
										}else if("CC".equals(f7)){//落杯
											jf = djf;
											f++;
										}else {//其它
											i++;b=false;
											getsocket(socket, 6);
											builder.append(",落杯异常信号:" + f7);
											break;
										}
									}//while落杯结束
									if (b) {
										//处理物品消费积分
										//查询饮品类型表
										int kafe_1 = 60,naica_1 = 50,baishui_1 = 0;
										List<DrinksType> drinksTypes = totalDao.query("from DrinksType where drinkType = '1'");
										if (drinksTypes!=null&&drinksTypes.size()>0) {
											byte[] drinkT = new byte[drinksTypes.size()*2];
											for (int j = 0; j < drinksTypes.size(); j++) {//循环得到各个饮品的积分
												DrinksType drinksType = drinksTypes.get(j);
												if (j==0)kafe_1=drinksType.getDrinkJiFen();//咖啡赋值
												if (j==1)naica_1=drinksType.getDrinkJiFen();//奶茶赋值
												if (j==2)baishui_1=drinksType.getDrinkJiFen();//白水赋值
												byte [] type = Util.jifenByte(drinksType.getDrinkJiFen()+jf+"",2);
												drinkT[j*2] = type[0];
												drinkT[j*2+1] = type[1];
											}
											//发送物品消费积分
											Thread.sleep(200);
											getsocketbyte(socket, drinkT, 2);
										}
										
										
										bis3 = new BufferedInputStream(in);
										byte[] yingpin = new byte[1];
										bis3.read(yingpin);
										String fn = Util.byteArrayToHexString(yingpin).replaceAll(" ", "");
										System.out.println(",返回: " + fn);
										builder.append(",返回:" + fn);
										if ("F1".equals(fn) || "F4".equals(fn)) {// 喝咖啡 60
											if (integral.getTotalIntegral() < kafe_1+jf) {
												getsocket_1(socket, 3);
											} else {
												getsocket_1(socket, 2);// 足够 发2
												XiaoFei xf = new XiaoFei();
												xf.setNeirong(accessType);
												xf.setXiaofeijifen(kafe_1 + jf);
												List<XiaoFei> xflList = new ArrayList<XiaoFei>();
												xflList.add(xf);
												integral.setXfList(xflList);
												IntegralServerDaoImpl.updateIntegral1(integral);
												int kf = integral.getTotalIntegral() - kafe_1 - jf;
												i++;
												integral.setTotalIntegral(kf);
												getsocket_1(socket, 6);// 发4跳到选杯界面
											}
										} else if ("F2".equals(fn) || "F5".equals(fn)) {// 喝奶茶 50
											if (integral.getTotalIntegral() < naica_1+jf) {
												getsocket_1(socket, 3);
											} else {
												getsocket_1(socket, 2);// 足够 发2
												XiaoFei xf = new XiaoFei();
												xf.setNeirong(accessType);
												xf.setXiaofeijifen(naica_1);
												List<XiaoFei> xflList = new ArrayList<XiaoFei>();
												xflList.add(xf);
												integral.setXfList(xflList);
												IntegralServerDaoImpl.updateIntegral1(integral);
												int kf = integral.getTotalIntegral() - naica_1 - jf;
												i++;
												integral.setTotalIntegral(kf);
												getsocket_1(socket, 6);// 发4跳到选杯界面
											}
										} else if ("F3".equals(fn) || "F6".equals(fn)) {// 不扣分
											if (integral.getTotalIntegral() < baishui_1+jf) {
												getsocket_1(socket, 3);
											} else {
												getsocket_1(socket, 2);// 足够 发2
												XiaoFei xf = new XiaoFei();
												xf.setNeirong(accessType);
												xf.setXiaofeijifen(baishui_1+jf);
												List<XiaoFei> xflList = new ArrayList<XiaoFei>();
												xflList.add(xf);
												integral.setXfList(xflList);
												IntegralServerDaoImpl.updateIntegral1(integral);
												int kf = integral.getTotalIntegral() - baishui_1+jf;
												i++;
												integral.setTotalIntegral(kf);
												getsocket_1(socket, 6);// 发4跳到选杯界面
											}
										} else if ("DF".equals(fn)) {
											// 返回
											i++;
										} else {
											builder.append(",选择饮品异常:" + fn);
											getsocket(socket, 6);
											break;
										}
										jf = 0;//落杯积分清空
									}
								}
							} else {
								getsocket(socket, 0);// 积分不足
								builder.append(",积分不足0发0:" + mess1);
							}
						} else {
							getsocket(socket, 0);// 没有积分
							builder.append(",没有积分信息发0:" + mess1);
						}
					} else {
						// 非内部卡发01
						getsocket(socket, 1);
						builder.append(",非内部卡发01:" + mess1);
					}
				}
			} else {
				// 无操作
				System.out.println(",收到无效指令:" + mess1);
				builder.append(",收到无效指令:" + mess1);
				getsocket(socket, 0);
			}
			System.out.println("服务端关闭 soko");
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
					SocketServersKFJ.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersKFJ.clientcount);
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
