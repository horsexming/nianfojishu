package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.pmi.PmiManagement;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.util.Util;

public class SocketServers extends Thread {

	public static final String SERVER_IP = "192.168.0.114";
	public static final int PORT = 9999;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServers(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口2121监听客户请求
				server = new ServerSocket(PORT);

				System.out.println("PMI ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;

				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThread(server.accept(), clientcount, toalDao).start();
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

class ServerThread extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThread(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的PMI数: " + number);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		try {
			String pmiIP = socket.getInetAddress().getHostAddress();
			System.out
					.println("********************************新的PMI进入分割线***************************");
			System.out.println(pmiIP + "进入服务端了");
			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			InputStream in = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedInputStream bis1 = null;
			byte[] nenghao_data = new byte[1];// 先接收第一个字符
			bis.read(nenghao_data);// 读取数据
			String mes = Util.byteArrayToHexString(nenghao_data);
			System.out.println("[Client " + number + "]: " + mes);
		 	// 确认包
			if ("FF".equals(mes)) {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream()));
				bw.write(new char[] { 03 });
				bw.flush();
				bw.close();
				System.out.println("确认包已经发送！！！！");
				bis.close(); // 关闭Socket输入流
				socket.close(); // 关闭Socket
				SocketServers.clientcount--;
				System.out.println("服务端关闭");
				System.out
						.println("==============================================");
			} else if ("EF".equals(mes)) {
				bis.close(); // 关闭Socket输入流
				socket.close(); // 关闭Socket
				SocketServers.clientcount--;
				System.out.println("服务端关闭");
				System.out
						.println("==============================================");
			} else if ("AA".equals(mes) || "CC".equals(mes)) {
				System.out.println("服务端接收数据");
				if (pmiIP != null && pmiIP.length() > 0) {
					// 根据ip查询PMI
					String hql = "select id,pmi_type from PmiManagement where pmi_ip=?";
					List list = totalDao.query(hql, pmiIP);
					if (list != null && list.size() > 0) {
						Object[] obj_pmi = (Object[]) list.get(0);
						PmiManagement pmi = new PmiManagement();
						pmi.setId(Integer.parseInt(obj_pmi[0].toString()));
						pmi.setPmi_type(obj_pmi[1].toString());
						if (pmi != null) {
							// 读取最大可能数据 (因为pmi只能存储200件产品数据)
							int maxnum = (int) (200 * 12 + 8);
							byte[] nenghao_datanext = new byte[maxnum];// 先接收第一个字符
							bis.read(nenghao_datanext);// 读取数据
							String mes_next = Util
									.byteArrayToHexStringK(nenghao_datanext);
							int ccIndex = mes_next.indexOf("CC");
							if ("弱控".equals(pmi.getPmi_type())
									&& (ccIndex >= 0 || "CC".equals(mes))) {
								System.out.println("弱控");
								return;
							}

							// 查询pmi对应"已领"取的工序
							String hql_process = "from ProcessInfor where pmiId = ? and  status='已领'";
							List processList = (List) totalDao.query(
									hql_process, pmi.getId());
							if (processList != null && processList.size() > 0) {
								ProcessInfor processInfor = (ProcessInfor) processList
										.get(0);
								if (processInfor != null) {
									// 查询该工序的领取记录
									String hql_proLog = "from ProcessInforReceiveLog where status='领取' and fk_processInforId=?";
									List proLogList = totalDao.query(
											hql_proLog, processInfor.getId());
									if (proLogList != null
											&& proLogList.size() > 0) {
										ProcessInforReceiveLog proLog = (ProcessInforReceiveLog) proLogList
												.get(0);
										if (proLog != null) {
											Float allnowJiepai = 0F;// 本次的总节拍
											Float allnowNenghao = 0F;// 本次的总能耗
											Float num = 0F;
											// 用于累计提交数量
											if (proLog.getSubmitNumber() != null) {
												num = proLog.getSubmitNumber();
											}
											// 用于累计总节拍
											if (proLog.getAllJiepai() != null) {
												allnowJiepai = proLog
														.getAllJiepai();
											}
											// 用于累计总能耗
											if (proLog.getAllNenghao() != null) {
												allnowNenghao = proLog
														.getAllNenghao();
											}

											// 剩余未提交数量
											float lastNum = proLog
													.getReceiveNumber()
													- num;
											// 等于0，则刚好50件都被提交了，就剩下CC....DD
											if (lastNum >= 0) {
												// 当前时间
												String sumDate = Util
														.getDateTime();

												// 读取最大会出现的N组AA 00 00 00 07 00
												// 00 00 04 02 01 BB(N*12=600)
												// 以及
												// CC 00 00 00 00 00 00 DD(8)
												// int maxnum = (int) (lastNum *
												// 12 + 8);
												// byte[] nenghao_datanext = new
												// byte[maxnum];// 先接收第一个字符
												// bis.read(nenghao_datanext);//
												// 读取数据
												// String mes_next = Util
												// .byteArrayToHexStringK(nenghao_datanext);
												// int ccIndex = mes_next
												// .indexOf("CC");
												// 存在工作记录
												if (!"CC".equals(mes)) {
													mes_next = "AA " + mes_next;
												} else {
													mes_next = "CC " + mes_next;
												}
												// 处理每条工作数据
												String[] allMes = mes_next
														.split("BB");
												// 分批提交处理
												List<ProcessInforReceiveLog> pirlogList = new ArrayList<ProcessInforReceiveLog>();
												int forcount = 0;
												for (int i = 0; i < allMes.length; i++) {
													int aaIndex = allMes[i]
															.indexOf("AA");
													// 存在AA
													if (aaIndex >= 0) {
														String oneMes = allMes[i]
																.replace(
																		" AA ",
																		"")
																.replace("AA ",
																		"");
														String[] jpmes = oneMes
																.split(" ");

														// 截取生产节拍
														// "00 00 00 07"==生产节拍
														Integer nowJiepai = 0;
														String nowJiepai_str = "";
														for (int j = 0; j < 4; j++) {
															String jiepai_data2 = jpmes[j];
															nowJiepai_str += Integer
																	.parseInt(jiepai_data2);// 累计节拍字符
														}
														nowJiepai = Integer
																.parseInt(nowJiepai_str);// 生产节拍
														allnowJiepai += nowJiepai;

														// 截取生产能耗
														// "00 00. 00 04 02 01"==能耗
														Float nowNenghao = 0F;
														String nowNenghao_str = "";
														// 先截取前两位
														for (int j = 4; j < 6; j++) {
															String nenghao_data2 = jpmes[j];
															nowNenghao_str += Integer
																	.parseInt(nenghao_data2);// 累计节拍字符
														}
														nowNenghao_str += ".";// 添加小数点
														// 再截取后四位
														for (int j = 6; j < 10; j++) {
															String nenghao_data2 = jpmes[j];
															nowNenghao_str += Integer
																	.parseInt(nenghao_data2);// 累计节拍字符
														}
														nowNenghao = Float
																.parseFloat(nowNenghao_str);
														allnowNenghao += nowNenghao;

														/*********** 添加领取记录信息 ***********/
														ProcessInforReceiveLog pirlog_new = new ProcessInforReceiveLog();
														pirlog_new
																.setUsercodes(proLog
																		.getUsercodes());
														pirlog_new
																.setUserCardId(proLog
																		.getUserCardId());
														pirlog_new
																.setUsernames(proLog
																		.getUsernames());
														pirlog_new
																.setUserId(proLog
																		.getUserId());
														pirlog_new
																.setFirstApplyDate(proLog
																		.getFirstApplyDate());
														pirlog_new
																.setReceiveNumber(1F);// 本次领取数量
														pirlog_new
																.setFk_pirLId(proLog
																		.getId());
														pirlog_new
																.setGongwei(proLog
																		.getGongwei());
														pirlog_new
																.setSubmitNumber(1F);
														pirlog_new
																.setBreakCount(0F);
														pirlog_new
																.setSumitApplyDate(sumDate);
														// 计算本次节拍
														pirlog_new
																.setAllJiepai(nowJiepai * 1F);
														// 本次能耗
														pirlog_new
																.setAllNenghao(nowNenghao);
														pirlog_new
																.setStatus("提交");
														// pirlog_new
														// .setProcessInfor(processInfor);
														pirlogList
																.add(pirlog_new);
														num++;
														forcount++;
														if (forcount >= lastNum) {
															break;
														}
													} else {
														break;
													}
												}

												/*** 处理领取记录信息 ****/

												// 如果是弱控，则不再完成数量
												if ("弱控".equals(pmi
														.getPmi_type())) {
													num = 0F;
													forcount = 0;
												}

												ccIndex = mes_next
														.indexOf("CC");
												// 如果存在CC或刚好全部提交了,则用户提交工序，更新记录状态为提交
												if (ccIndex >= 0
														|| (lastNum == 0 && ccIndex == 0)) {
													proLog.setStatus("待提交");
												}

												proLog.setSubmitNumber(num);
												proLog
														.setSumitApplyDate(sumDate);
												proLog
														.setAllJiepai(allnowJiepai + 0F);
												proLog
														.setAllNenghao(allnowNenghao + 0F);
												totalDao.update2(proLog);

												for (ProcessInforReceiveLog processInforReceiveLog : pirlogList) {
													processInforReceiveLog
															.setFk_processInforId(processInfor
																	.getId());
													totalDao
															.save2(processInforReceiveLog);
												}

												// 如果是弱控，则不再完成数量
												if (!"弱控".equals(pmi
														.getPmi_type())) {
													processInfor
															.setSubmmitCount(processInfor
																	.getSubmmitCount()
																	+ forcount);// 提交完成数量
													totalDao
															.update2(processInfor);
												}
												System.out.println("服务端逻辑结束");
											}
										}
									}
								}
							}
						} else {

						}
					}
				}
				in.close();
				bis.close(); // 关闭Socket输入流
				socket.close(); // 关闭Socket
				SocketServers.clientcount--;
				System.out.println("服务端关闭");
				System.out.println("==============================================");
			}else if("10".equals(mes)){
				byte[] min_num = new byte[1];// 第二位标识（用于确定哪台PMI）
				bis.read(min_num);// 读取数据
				String min_nummen = Util.byteArrayToHexString(min_num);
				System.out.println("[min_nummen " + min_nummen + "]: " + min_nummen);
				// 根据ip查询PMI
				String hql = "from PmiManagement where pmi_ip=? and min_num = ?";
				int ii = Integer.parseInt(min_nummen);
				List list = totalDao.query(hql, pmiIP, ii);
				if(list!=null&&list.size()>0){
					PmiManagement pm = (PmiManagement) list.get(0);
					if(pm!=null){
//						getsocket(socket, new byte []{0x0A,(byte) Integer.parseInt(min_nummen),(byte) 0xBC});
						//bis1 = new BufferedInputStream(in);
						byte[] three = new byte[1];// 第三位标识
						bis.read(three);// 读取数据
						String three_num = Util.byteArrayToHexString(three);
						System.out.println("[three " + three_num + "]: " + three_num);
						// 确认包
						if ("FF".equals(three_num)) {
							getsocketClose(socket, 16,ii,3);//发送确认包
							System.out.println("确认包已经发送！！！！");
							bis.close(); // 关闭Socket输入流
							socket.close(); // 关闭Socket
							System.out.println("服务端关闭");
							System.out.println("==============================================");
						} else if ("EF".equals(three_num)) {
							bis.close(); // 关闭Socket输入流
							socket.close(); // 关闭Socket
							System.out.println("服务端关闭");
							System.out.println("==============================================");
						} else if ("AA".equals(three_num) || "CC".equals(three_num)) {
							System.out.println("服务端接收数据");
							// 读取最大可能数据 (因为pmi只能存储200件产品数据)
							int maxnum = (int) (200 * 12 + 8);
							byte[] nenghao_datanext = new byte[maxnum];// 先接收第一个字符
							bis.read(nenghao_datanext);// 读取数据
							String mes_next = Util.byteArrayToHexStringK(nenghao_datanext);
							int ccIndex = mes_next.indexOf("CC");
							if ("弱控".equals(pm.getPmi_type())
									&& (ccIndex >= 0 || "CC".equals(mes))) {
								System.out.println("弱控");
								return;
							}
							// 查询pmi对应"已领"取的工序
							String hql_process = "from ProcessInfor where pmiId = ? and  status='已领'";
							List processList = (List) totalDao.query(hql_process, pm.getId());
							if (processList != null && processList.size() > 0) {
								ProcessInfor processInfor = (ProcessInfor) processList
										.get(0);
								if (processInfor != null) {
									// 查询该工序的领取记录
									String hql_proLog = "from ProcessInforReceiveLog where status='领取' and fk_processInforId=?";
									List proLogList = totalDao.query(
											hql_proLog, processInfor.getId());
									if (proLogList != null
											&& proLogList.size() > 0) {
										ProcessInforReceiveLog proLog = (ProcessInforReceiveLog) proLogList
												.get(0);
										if (proLog != null) {Float allnowJiepai = 0F;// 本次的总节拍
										Float allnowNenghao = 0F;// 本次的总能耗
										Float num = 0F;
										// 用于累计提交数量
										if (proLog.getSubmitNumber() != null) {
											num = proLog.getSubmitNumber();
										}
										// 用于累计总节拍
										if (proLog.getAllJiepai() != null) {
											allnowJiepai = proLog.getAllJiepai();
										}
										// 用于累计总能耗
										if (proLog.getAllNenghao() != null) {
											allnowNenghao = proLog.getAllNenghao();
										}

										// 剩余未提交数量
										float lastNum = proLog.getReceiveNumber() - num;
										// 等于0，则刚好50件都被提交了，就剩下CC....DD
										if (lastNum >= 0) {
											// 当前时间
											String sumDate = Util.getDateTime();

											// 读取最大会出现的N组AA 00 00 00 07 00
											// 00 00 04 02 01 BB(N*12=600)
											// 以及
											// CC 00 00 00 00 00 00 DD(8)
											// int maxnum = (int) (lastNum *
											// 12 + 8);
											// byte[] nenghao_datanext = new
											// byte[maxnum];// 先接收第一个字符
											// bis.read(nenghao_datanext);//
											// 读取数据
											// String mes_next = Util
											// .byteArrayToHexStringK(nenghao_datanext);
											// int ccIndex = mes_next
											// .indexOf("CC");
											// 存在工作记录
											if (!"CC".equals(mes)) {
												mes_next = "AA " + mes_next;
											} else {
												mes_next = "CC " + mes_next;
											}
											// 处理每条工作数据
											String[] allMes = mes_next.split("BB");
											// 分批提交处理
											List<ProcessInforReceiveLog> pirlogList = new ArrayList<ProcessInforReceiveLog>();
											int forcount = 0;
											for (int i = 0; i < allMes.length; i++) {
												int aaIndex = allMes[i].indexOf("AA");
												// 存在AA
												if (aaIndex >= 0) {
													String oneMes = allMes[i].replace(" AA ", "").replace(
															"AA ", "");
													String[] jpmes = oneMes.split(" ");

													// 截取生产节拍
													// "00 00 00 07"==生产节拍
													Integer nowJiepai = 0;
													String nowJiepai_str = "";
													for (int j = 0; j < 4; j++) {
														String jiepai_data2 = jpmes[j];
														nowJiepai_str += Integer.parseInt(jiepai_data2);// 累计节拍字符
													}
													nowJiepai = Integer.parseInt(nowJiepai_str);// 生产节拍
													allnowJiepai += nowJiepai;

													// 截取生产能耗
													// "00 00. 00 04 02 01"==能耗
													Float nowNenghao = 0F;
													String nowNenghao_str = "";
													// 先截取前两位
													for (int j = 4; j < 6; j++) {
														String nenghao_data2 = jpmes[j];
														nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
													}
													nowNenghao_str += ".";// 添加小数点
													// 再截取后四位
													for (int j = 6; j < 10; j++) {
														String nenghao_data2 = jpmes[j];
														nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
													}
													nowNenghao = Float.parseFloat(nowNenghao_str);
													allnowNenghao += nowNenghao;

													/*********** 添加领取记录信息 ***********/
													ProcessInforReceiveLog pirlog_new = new ProcessInforReceiveLog();
													pirlog_new.setUsercodes(proLog.getUsercodes());
													pirlog_new.setUserCardId(proLog.getUserCardId());
													pirlog_new.setUsernames(proLog.getUsernames());
													pirlog_new.setUserId(proLog.getUserId());
													pirlog_new
															.setFirstApplyDate(proLog.getFirstApplyDate());
													pirlog_new.setReceiveNumber(1F);// 本次领取数量
													pirlog_new.setFk_pirLId(proLog.getId());
													pirlog_new.setGongwei(proLog.getGongwei());
													pirlog_new.setSubmitNumber(1F);
													pirlog_new.setBreakCount(0F);
													pirlog_new.setSumitApplyDate(sumDate);
													// 计算本次节拍
													pirlog_new.setAllJiepai(nowJiepai * 1F);
													// 本次能耗
													pirlog_new.setAllNenghao(nowNenghao);
													pirlog_new.setStatus("提交");
													// pirlog_new
													// .setProcessInfor(processInfor);
													pirlogList.add(pirlog_new);
													num++;
													forcount++;
													if (forcount >= lastNum) {
														break;
													}
												} else {
													break;
												}
											}

											/*** 处理领取记录信息 ****/

											// 如果是弱控，则不再完成数量
											if ("弱控".equals(pm.getPmi_type())) {
												num = 0F;
												forcount = 0;
											}

											ccIndex = mes_next.indexOf("CC");
											// 如果存在CC或刚好全部提交了,则用户提交工序，更新记录状态为提交
											if (ccIndex >= 0 || (lastNum == 0 && ccIndex == 0)) {
												proLog.setStatus("待提交");
											}

											proLog.setSubmitNumber(num);
											proLog.setSumitApplyDate(sumDate);
											proLog.setAllJiepai(allnowJiepai + 0F);
											proLog.setAllNenghao(allnowNenghao + 0F);
											totalDao.update2(proLog);

											for (ProcessInforReceiveLog processInforReceiveLog : pirlogList) {
												processInforReceiveLog.setFk_processInforId(processInfor
														.getId());
												totalDao.save2(processInforReceiveLog);
											}

											// 如果是弱控，则不再完成数量
											if (!"弱控".equals(pm.getPmi_type())) {
												processInfor.setSubmmitCount(processInfor.getSubmmitCount()
														+ forcount);// 提交完成数量
												totalDao.update2(processInfor);
											}
											System.out.println("服务端逻辑结束");
										}
										}
									}
								}
							}
							in.close();
							bis.close(); // 关闭Socket输入流
							socket.close(); // 关闭Socket
							//SocketServers.clientcount--;
							System.out.println("服务端关闭");
							System.out.println("==============================================");
						}
					}
				}
			}
			System.out
					.println("===================通讯结束分割线===========================");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error. " + e);
		} finally {
			try {
//				if (bis != null) {bis.close();}
//				if (in != null) {in.close();}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServers.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServers.clientcount);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}

	}

	/**
	 * 发送
	 * @param sockets
	 * @throws IOException
	 */
	public static void getsocket(Socket sockets,int... b) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		for (int i : b) {
			out.write(i);
		}
		out.flush();
	}
	public static void getsocketClose(Socket sockets, int... b) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		for (int i : b) {
			out.write(i);
		}
		out.flush();
		out.close();
	}
	
	/***
	 * 读取数据
	 * 
	 * @param bis
	 * @return
	 * @throws Exception
	 */
	private static String readBis(InputStream is) throws Exception {
		// 再截取后四位
		byte[] nenghao_data = new byte[1];// 先接收第一个字符
		is.read(nenghao_data);// 读取数据
		return Util.byteArrayToHexString(nenghao_data);
	}
}
