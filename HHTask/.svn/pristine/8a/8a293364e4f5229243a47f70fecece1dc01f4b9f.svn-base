package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;


import com.task.Dao.TotalDao;
import com.task.ServerImpl.IntegralServerDaoImpl;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.entity.menjin.DoorBangDing;
import com.task.entity.menjin.DoorUseRecording;
import com.task.util.Util;

public class SocketServersTeaRoom extends Thread {

	public static final int PORT = 8872;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersTeaRoom(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8868监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("tk ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadTR(server.accept(), clientcount, toalDao)
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

class ServerThreadTR extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadTR(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的TR数: " + number);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		BufferedInputStream bis = null;
		InputStream in = null;
		String accessIP = "";// 门禁IP
		String cardIds = "";// 卡号
		String yanzheng = "";//来访人员验证码
		String username = "";//员工姓名
		String accessname = "";// 设备名称
		String inOutType = "";// 进出类型
		Integer accessId = 0;// 设备id
		StringBuffer builder = new StringBuffer();

		try {
			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			// 先接收接收第一个字符 用做标识
			System.out.println("服务端开始接受标识！");
			byte[] biao_data = new byte[1];// 先接收第1个字符
			String mess1 = Util.byteArrayToHexString(biao_data);
			bis.read(biao_data);// 读取数据
			if ("AA".equals(mess1)) {
				System.out.println("验证码刷卡类型标识: " + mess1);
				/************************ 验证码流程 **********************/
			} else if ("00".equals(mess1)) {
				/************************* 刷卡流程 **********************/
				byte[] yg_cardId = new byte[4];// 开始接受4位16进制的卡号
				bis.read(yg_cardId);// 读取数据

				String card = Util.byteArrayToHexStringK(yg_cardId).replaceAll(
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
				int num = cardId.length();
				for (int i = num; i < 10; i++) {
					cardId = "0" + cardId;
				}
				cardIds = cardId;
				System.out.println("处理之后的卡号为：" + cardId);
				builder.append(mess1 + ",处理之后的卡号为：" + cardId);
				// 查询Users表是否存在。
				// 1、判断是否为内部（users表）
				List<Users> userList = totalDao.query(
						"from Users where cardId = ? and onWork <>'离职' ",
						cardId);
				if (userList != null && userList.size() > 0) {
					Users users = userList.get(0);
					List<Integral> listJF = totalDao.query(
							"from Integral where userId = ?", users.getId());
					if (listJF != null && listJF.size() > 0) {
						username = users.getName();
						// 2是内部员工卡
						byte[] men_data = new byte[1];// 再接收第6个字符
						bis.read(men_data);// 读取数据
						String mess = Util.byteArrayToHexString(men_data);
						System.out.println("刷卡类型标识: " + mess);
						// 根据标识判断处理类型。
						if ("DD".equals(mess) || "BB".equals(mess)) {
							// 2、判断此门是否有权限开
							// 根据ip 查设备编号，卡号判断是否绑定过。
							List listdbd = totalDao
									.query(
											"from DoorBangDing where fk_user_id = ? and fk_acEq_id = (select id from AccessEquipment where equipmentIP = ?) order by id desc",
											users.getId(), accessIP);
							if (listdbd != null && listdbd.size() > 0) {

								Integral integral = listJF.get(0);
								if (listJF.get(0).getTotalIntegral() > 0) {
 									DoorBangDing bangDing = (DoorBangDing) listdbd
											.get(0);
									if ("DD".equals(mess)) {
										inOutType = "进门";
										// 进门=>直接开门
										/*************************/
										// 返回员工信息
										String us = users.getCode();
										String usCodenum = "";
										if (users.getCodeIdNum() != null
												&& users.getCodeIdNum() > 0) {
											usCodenum = users.getCodeIdNum()
													.toString();
										} else {
											if (users != null
													&& "女".equals(users
															.getSex())) {
												usCodenum = "2";
											} else {
												usCodenum = "1";
											}
										}

										int codeNumImage = usCodenum.length();
										for (int i = codeNumImage; i < 4; i++) {
											usCodenum = "0" + usCodenum;
										}

										int num1 = us.length();
										for (int i = num1; i < 4; i++) {
											us = "0" + us;
										}
										String[] strCodenum = usCodenum
												.split("");
										char[] strCharCodenum = new char[strCodenum.length - 1];
										for (int i = 1; i < strCodenum.length; i++) {
											int ic = Integer
													.parseInt(strCodenum[i]);
											char ch = (char) (ic);
											strCharCodenum[i - 1] = ch;
										}
										String[] strs = us.split("");
										char[] strChar = new char[4];
										for (int i = 1; i < strs.length; i++) {
											int ic = Integer.parseInt(strs[i]);
											char ch = (char) (ic);
											strChar[i - 1] = ch;
										}
										String nameN = users.getName();
										String deptD = users.getDept();
										try {
											int si = 8 - nameN
													.getBytes("gb2312").length;
											for (int i = 0; i < si; i++) {
												nameN = nameN + " ";
											}
											int sl = 8 - deptD
													.getBytes("gb2312").length;
											for (int i = 0; i < sl; i++) {
												deptD = deptD + " ";
											}
											System.out.println(nameN);
											System.out.println(deptD);
										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										PrintWriter bw = new PrintWriter(
												new OutputStreamWriter(socket
														.getOutputStream()));
										bw.write(0x01);//
										bw.write(strChar);//strCharCodenum
										bw.write(nameN);//
										bw.write(deptD);//
										bw.write(strChar);//
										bw.write(0x0A);//
										bw.flush();

										/*************************/
										sleep(1000);
										int SumIntegarl = 0;
										if(integral.getSumxf()!=null){
											SumIntegarl = integral.getSumxf();
										}
										
											
										Set<XiaoFei> xfset = integral.getXf();
										String NowMonth = Util.getDateTime(
												"yyyy-MM-dd").substring(5, 7);
										// for (XiaoFei xf : xfset) {
										// if
										// (NowMonth.equals(xf.getXiaofeitime()
										// .substring(5, 7))) {
										// SumIntegarl += xf.getXiaofeijifen();
										// }
										// }
										String sumString = SumIntegarl + "";
										String jifenString = integral
												.getTotalIntegral()
												+ "";
										int jflength = jifenString.length();
										for (int i = jflength; i < 5; i++) {
											jifenString = "0" + jifenString;
										}
										int sumlength = sumString.length();
										for (int i = sumlength; i < 5; i++) {
											sumString = "0" + sumString;
										}
										String[] strSum = sumString.split("");
										char[] charSum = new char[strSum.length - 1];
										for (int i = 1; i < strSum.length; i++) {
											int ic = Integer
 													.parseInt(strSum[i]);
											char ch = (char) (ic);
											charSum[i - 1] = ch;
										}
										String[] strjf = jifenString.split("");
										char[] charjf = new char[strjf.length - 1];
										for (int i = 1; i < strjf.length; i++) {
											int ic = Integer.parseInt(strjf[i]);
											char ch = (char) (ic);
											charjf[i - 1] = ch;
										}
										bw.write(0x04);//
										bw.write(charSum);// 累计消费
										bw.write(charjf);// 剩余
										bw.write(0x0A);//
										bw.flush();

										BufferedInputStream bis1 = new BufferedInputStream(
												in);
										byte[] biao_data1 = new byte[1];// 先接收第1个字符
										bis1.read(biao_data1);// 读取数据
										String mess2 = Util
												.byteArrayToHexString(biao_data1);
										System.out.println("反回标识: " + mess2);
										if ("EE".equals(mess2)) {
											getsocket(socket, 5);
											if (DoorBangDingServerImpl.addInDoorUseRecording(users,
													bangDing)) {
												// getsocket(socket, 2);
											} else {
												// getsocket(socket, 0);
											}
											;
										} else if ("DF".equals(mess2)) {

										}
										// 设置状态为：内 并添加记录
									} else {
										inOutType = "出门";
										//查询记录时长的表
										List<DoorUseRecording> doorUseRecordingList = totalDao.query("from DoorUseRecording where id = ?", bangDing.getFk_security_id());
										if (doorUseRecordingList!=null&&doorUseRecordingList.size()>0) {
											DoorUseRecording doorUseRecording2 = doorUseRecordingList.get(0);
											// 出门=>状态为内，开门，状态为外，不开门
											if ("外".equals(bangDing.getStatus())) {
												getsocket_2(socket, 0);
											} else if ("内".equals(bangDing
													.getStatus())
													&& bangDing.getFk_security_id() != null) {
												getsocket_1(socket, 2);
												if (DoorBangDingServerImpl.addOutDoorUseRecording(users,
														bangDing)) {
													// 计算时长，并根据时长消费积分;
//													int time = (int) (Long
//															.parseLong(doorUseRecording2
//																	.getUseDateNum()) / 1000 / 60);// 所用时长
													// 分钟表示
													// 每十分钟消耗一积分，不足十分钟，按十分钟计算;
													//计算出待在休息室的时间(分为上班时间和下班时间), 并根据不同的时间段，采用不同的消耗积分算法;
													Integer xfjf=IntegralServerDaoImpl.xhjf(doorUseRecording2.getStartTime(), users.getId());
													/*************************/
													PrintWriter bw = new PrintWriter(
															new OutputStreamWriter(
																	socket
																	.getOutputStream()));
													String strxf = xfjf
													+ "";
													String strjf = integral
													.getTotalIntegral()
													- xfjf+ "";
													int xflength = strxf.length();
													for (int i = xflength; i < 5; i++) {
														strxf = "0" + strxf;
													}
													int jflegth = strjf.length();
													for (int i = jflegth; i < 5; i++) {
														strjf = "0" + strjf;
													}
													String[] strxfs = strxf
													.split("");
													char[] charxf = new char[strxfs.length - 1];
													for (int i = 1; i < strxfs.length; i++) {
														int ic = Integer
														.parseInt(strxfs[i]);
														char ch = (char) (ic);
														charxf[i - 1] = ch;
													}
													String[] strjfs = strjf
													.split("");
													char[] charjf = new char[strjfs.length - 1];
													for (int i = 1; i < strjfs.length; i++) {
														int ic = Integer
														.parseInt(strjfs[i]);
														char ch = (char) (ic);
														charjf[i - 1] = ch;
													}
													bw.write(0x06);//
													bw.write(charxf);// 消费
													bw.write(charjf);// 剩余
													bw.write(0x0A);//
													bw.flush();
													
													/******************************************/
												}
											} else {
												// 不为外 内或null
												getsocket_2(socket, 0);
											}
											// 设置状态为：外 并添加记录
										}
									}

									// 走开门流程
									// 开门后记录你是进门还是出门，进门记录开始时间，出门记录结束时间。
									// 保存进门记录根据BB或CC分别保留
								} else {
									// 积分小于等于0不开门,并发送积分不足的提示;
									getsocket_2(socket, 0);
								}
							} else {
								// 不开门
								getsocket_2(socket, 0);
							}
						} else {
							// nn??
							getsocket_2(socket, 0);
						}
						// 打卡
					} else {
						// 2不是内部员工
						getsocket_2(socket, 0);
					}
				}
			} else {
				// 无操作
				System.out.println("收到无效指令:"+mess1);
			}
			// String sk_date = Util.getDateTime("yyyy-MM-dd");// 获取当前日期
			// String sk_date_one = Util.getSpecifiedDayAfter(sk_date, 1);//
			// 获取当前日期
			// String sk_Time_hour = Util.getDateTime("HH:mm:ss");// 获取当前时间
			// 确认发送信息

			System.out.println("服务端关闭 soko");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersTeaRoom.clientcount--;

					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersTeaRoom.clientcount);
					DoorBangDingServerImpl.caeLogInfor(builder, cardIds, yanzheng, username, inOutType, accessname, accessId, accessIP);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
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
		bw.write(i);// 发送不开门信号
		bw.write(0x0a);// 发送不开门信号
		bw.flush();
		bw.close();
	}
	
	private static void getsocket_1(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(i);// 发送不开门信号
		bw.write(0x0a);// 发送不开门信号
		bw.flush();
	}
	//fa00
	private static void getsocket_2(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(i);// 发送不开门信号
		bw.flush();
		bw.close();
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
