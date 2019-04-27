package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.entity.Users;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.ReceiveCabinet;
import com.task.entity.menjin.ToolCabine;
import com.task.util.Util;

public class SocketServersGJG extends Thread {

	public static final int PORT = 8878;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersGJG(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	public void run() {
		try {
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8878监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("GJG ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadGJG(server.accept(), clientcount, toalDao)
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

class ServerThreadGJG extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadGJG(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的档案柜数: " + number);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		BufferedInputStream bis3 = null;
		BufferedInputStream bis4 = null;
		InputStream in = null;
		StringBuffer builder = new StringBuffer();
		String accessIP = "";// 门禁IP
		String yanzheng = "";// 验证码
		String cardId = "";// 卡号
		String userName = "";// 刷卡人
		String accessType = "";// 门禁类型
		String accessname = "";// 设备名称
		Integer accessId = 0;// 设备id
//		String adminCordId = "";// 管理员卡号
//		String adminStatus = "";// 卡绑定状态
		AccessEquipment accessEquipment = null;
		String nowTime = Util.getDateTime("yyyy-MM-dd");// 系统当前日期
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
			System.out.println(accessIP + "验证码刷卡类型标识: " + mess1);
			/**
			 * 第一步： 根据接收到IP或SIM标识去查找门禁设备
			 */
			accessType = "";
			List acElist = totalDao.query("from AccessEquipment where equipmentIP=? order by id desc",accessIP);
			if (acElist != null && acElist.size() > 0) {
				accessEquipment = (AccessEquipment) acElist.get(0);
				accessType = accessEquipment.getEquipmentDaoType();
//				adminCordId = accessEquipment.getAdminCardId();
//				adminStatus = accessEquipment.getAdminStatus();
				accessname = accessEquipment.getEquipmentName();
				accessId = accessEquipment.getId();
			}
			builder.append(mess1 + ",+" + accessType);
			if (("AE".equals(mess1) || "AF".equals(mess1)) && "工具柜".equals(accessType)) {// 存档&取档
				String cunqu = "无";
				boolean cqty = true;
				if ("AE".equals(mess1))
					cunqu = "存物品";
				else {
					cunqu = "取物品";
					cqty = false;
				}
				bis1 = new BufferedInputStream(in);// 接收卡号
				byte[] yg_df = new byte[1];// 开始接受第一位标识，如果是DF结束
				bis1.read(yg_df);// 读取数据
				String df = Util.byteArrayToHexStringK(yg_df).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
				System.out.println("档案柜接收标识+" + df);
				if ("AD".equals(df)) {// AD不做操作 直接返回主界面
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
					String card = Util.byteArrayToHexStringK(zong_code).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("存取档卡号为：" + card);
					try {
						Integer cardNumber = Integer.parseInt(card, 16);
						System.out.println("处理之后的cardNumber为：" + cardNumber);
						cardId = cardNumber.toString();
					} catch (Exception e) {
						builder.append("JS卡号异常：" + e.toString());
						cardId = Util.yanNumber(10);
					}
					cardId = Util.cardId(cardId);
					builder.append(",处理之后的卡号(" + cunqu + ")为：" + cardId);
					String daiCunWuP = "from DepositCabinet where id = ? and printStatus = '1' and depositStatus <> '已入柜' and artQuantity > actualDepositQuantity ";//根据二维码查询待存物品
					String isCunWuP = "from DepositCabinet where printCardId = (select cardId from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是') and depositStatus <> '已入柜' and actualDepositQuantity < artQuantity";//根据卡号=》是否有存物信息
					String isQuWuP = "from ReceiveCabinet where receiveCardId = ? and receiveStatus = '待领取' and verificationCodeStatus = '未使用' and failTime > ? and receiveCardId = (select cardId from Users where cardId = ? and onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休') and internal = '是')";//根据卡号=》是否有存物信息
					String selectGuiWM = "from ToolCabine where cabAceIp = ? and cabStatus = '未满' and nowArticleName = ? and nowArticleFormat = ?";//相同柜子未满
					String selectTSGui = "from ToolCabine where articleName = ? and articleFormat = ?";//是否存在特殊规定柜子
					String selectTSGuiWM = "from ToolCabine where cabAceIp = ? and nowNumber = 0 and cabStatus = '未满' and articleName = ? and articleFormat = ?";//找出空非特殊物品柜子未满
					String selectGuiKong = "from ToolCabine where cabAceIp = ? and nowNumber = 0 and cabStatus = '未满' and (articleName = '' or articleName is null) and (articleFormat = '' or articleFormat is null) and (nowArticleName = '' or nowArticleName is null) and (nowArticleFormat = '' or nowArticleFormat is null)";//找出空非特殊物品的柜子
					
					if (cqty) {
						// 1、判断是否为内部（users表）
						// 存物品表中是否存在
						int cabinets = totalDao.getCount(isCunWuP, cardId);
						if (cabinets > 0) {// 证明有待入柜信息
							getsocket_1(socket, 4);// 扫描二维码界面
							bis2 = new BufferedInputStream(in);// 接收二维码中的id（4位16进制）
							byte[] qrCode = new byte[10];
							bis2.read(qrCode);
							//Util.byteArrayToHexStringK(qrCode).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
							StringBuffer buffer1 = new StringBuffer();
							for (int i = 0; i < qrCode.length; i++) {buffer1.append(qrCode[i]);}
							String qrCodes = buffer1.toString();
							System.out.println("qr接收：" + qrCodes);
							builder.append("qr接收：" + qrCodes);
							int qr1 = 0;
							try {
								qr1 = Integer.parseInt(qrCodes);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							List<DepositCabinet> cabinets2 = totalDao.query(daiCunWuP, qr1);
							if (cabinets2 != null && cabinets2.size() > 0) {
								DepositCabinet cabinet = cabinets2.get(0);// 此条待存物信息
								if (cabinet != null && cabinet.getDepArticleName() != null && cabinet.getDepArticleFormat() != null && cabinet.getArtQuantity() != null) {
									// 1首先查找是否有对应的柜子=》
									int num = 0;
									int shijcunNum = cabinet.getArtQuantity() - cabinet.getActualDepositQuantity();
									// 当存入数量等于总数量时，跳出循环
									while (shijcunNum > num) {
										List<ToolCabine> cabinelist = totalDao.query(selectGuiWM, accessIP, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
										if (cabinelist != null && cabinelist.size() > 0) {// 有相同物品且未满柜子的情况下
											ToolCabine cabine = cabinelist.get(0);
											int i = Util.swiInstruction(cabine.getCabOpenOrder());
											if (i > 0) { // 柜子编号要为数字，范围是1~26
												getsocketInt(socket, i);// 发送柜子编号对应的的开门指令
												/****************************************************/
												// 等待关门指令
												bis3 = new BufferedInputStream(in);
												byte[] close = new byte[1];
												bis3.read(close);
												String closes = Util.byteArrayToHexStringK(close).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
												System.out.println("关门指令接收：" + closes);
												builder.append("关门指令接收：" + closes);
												if ("FF".equals(closes)) {
													int[] is = Util.yanzhengintSz2(shijcunNum + "", 2);
													getsocketIntCUN(socket, is[0], is[1]);
													bis4 = new BufferedInputStream(in);
													byte[] shijNum = new byte[2];
													bis4.read(shijNum);
													String shijNums = Util.byteArrayToHexStringK(shijNum).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
													try {
														Integer shijNumint = Integer.parseInt(shijNums, 16);
														shijNums = shijNumint + "";
														System.out.println("处理之后的cardNumber为：" + shijNumint);
													} catch (Exception e) {
														builder.append("JS卡号异常：" + e.toString());
													}
													System.out.println("实际存入数量：" + shijNums);
													builder.append("实际存入数量：" + shijNums);
													num = Integer.parseInt(shijNums);
													// 更改柜子的数量个状态
													if (num == shijcunNum) {// 存完了
														cabine.setNowNumber(num + cabine.getNowNumber());// 实际数量应该是number加之前的数量
														cabine.setNowArticleName(cabinet.getDepArticleName());
														cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
														cabinet.setActualDepositQuantity(num + cabinet.getActualDepositQuantity());//
													} else if (num > 0 && num < shijcunNum) {// 没存完
														cabine.setNowNumber(num + cabine.getNowNumber());
														cabine.setCabStatus("已满");
														cabine.setNowArticleName(cabinet.getDepArticleName());
														cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
														cabinet.setActualDepositQuantity(num + cabinet.getActualDepositQuantity());
													} else if (num == 0) {// 没放东西
														// 如果当前数量不为0，将柜子设置为已满
														if (cabine.getNowNumber() > 0) {
															cabine.setCabStatus("已满");
															cabine.setNowArticleName(cabinet.getDepArticleName());
															cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
														}
													} else {// 如果大于，实际数量应该是剩余应存数量加已存数量，且柜子未满
														cabine.setNowNumber(shijcunNum + cabine.getNowNumber());
														cabine.setNowArticleName(cabinet.getDepArticleName());
														cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
														cabinet.setActualDepositQuantity(shijcunNum+ cabinet.getActualDepositQuantity());//
													}
													cabine.setUpdateTime(Util.getDateTime());
													totalDao.update2(cabine);// 更新柜子实际数量和状态
													shijcunNum = cabinet.getArtQuantity()- cabinet.getActualDepositQuantity();
													if (cabinet.getActualDepositQuantity() >= cabinet.getArtQuantity()) {
														cabinet.setDepositStatus("已入柜");
														num = shijcunNum;
													} else {
														cabinet.setDepositStatus("入柜中");
														num = 0;
													}
													cabinet.setDepositTime(Util.getDateTime());
													totalDao.update2(cabinet);// 更新待入柜表的实际数量和状态
												} else {
													getsocket(socket, 9);// 不是开门信号返回主页面之前的操作作废
													builder.append("不是关门信号(FF),指令异常！");
													num = shijcunNum;// 跳出循环
												}
											} else {
												getsocket(socket, 0);// 柜子编号异常情况发0结束程序
												num = shijcunNum;// 跳出循环
												builder.append(cabine.getId() + "柜子编号异常！");
											}
										} else {// 没有相同物品的情况下
											// 2.判断是否为特殊规定的物品 
											int special = totalDao.getCount(selectTSGui, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
											List<ToolCabine> toolCabines = null;
											if (special > 0) {// 为特殊规定物品
												toolCabines = totalDao.query(selectTSGuiWM, accessIP, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
											} else {// 不为特殊规定物品
												toolCabines = totalDao.query(selectGuiKong, accessIP);
											}
											if (toolCabines != null && toolCabines.size() > 0) {
												ToolCabine cabine = toolCabines.get(0);
												int i = Util.swiInstruction(cabine.getCabOpenOrder());
												if (i > 0) { // 柜子编号要为数字，范围是1~26
													getsocketInt(socket, i);// 发送柜子编号对应的的开门指令
													/****************************************************/
													// 等待关门指令
													bis3 = new BufferedInputStream(in);
													byte[] close = new byte[1];
													bis3.read(close);
													String closes = Util.byteArrayToHexStringK(close).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
													System.out.println("关门指令接收：" + closes);
													builder.append("关门指令接收：" + closes);
													if ("FF".equals(closes)) {
														int[] is = Util.yanzhengintSz2(shijcunNum + "", 2);
														getsocketIntCUN(socket, is[0], is[1]);
														// 等待实际数量
														bis4 = new BufferedInputStream(in);
														byte[] shijNum = new byte[2];
														bis4.read(shijNum);
														String shijNums = Util.byteArrayToHexStringK(shijNum).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
														try {
															Integer shijNumint = Integer.parseInt(shijNums, 16);
															shijNums = shijNumint + "";
															System.out.println("处理之后的cardNumber为：" + shijNumint);
														} catch (Exception e) {
															builder.append("JS卡号异常：" + e.toString());
														}
														System.out.println("实际存入数量：" + shijNums);
														builder.append("实际存入数量：" + shijNums);
														num = Integer.parseInt(shijNums);
														// 更改柜子的数量个状态 
														if (num == shijcunNum) {// 存完了
															cabine.setNowNumber(num + cabine.getNowNumber());// 实际数量应该是number加之前的数量
															cabine.setNowArticleName(cabinet.getDepArticleName());
															cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
															cabinet.setActualDepositQuantity(num + cabinet.getActualDepositQuantity());//
														} else if (num > 0 && num < shijcunNum) {// 没存完
															cabine.setNowNumber(num + cabine.getNowNumber());
															cabine.setCabStatus("已满");
															cabine.setNowArticleName(cabinet.getDepArticleName());
															cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
															cabinet.setActualDepositQuantity(num + cabinet.getActualDepositQuantity());
														} else if (num == 0) {// 没放东西
															// 如果当前数量不为0，将柜子设置为已满
															if (cabine.getNowNumber() > 0) {
																cabine.setCabStatus("已满");
																cabine.setNowArticleName(cabinet.getDepArticleName());
																cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
															}
														} else {// 如果大于，实际数量应该是剩余应存数量加已存数量，且柜子未满
															cabine.setNowNumber(shijcunNum + cabine.getNowNumber());
															cabine.setNowArticleName(cabinet.getDepArticleName());
															cabine.setNowArticleFormat(cabinet.getDepArticleFormat());
															cabinet.setActualDepositQuantity(shijcunNum + cabinet.getActualDepositQuantity());//
														}
														cabine.setUpdateTime(Util.getDateTime());
														totalDao.update2(cabine);// 更新柜子实际数量和状态
														shijcunNum = cabinet.getArtQuantity() - cabinet.getActualDepositQuantity();
														if (cabinet.getActualDepositQuantity() >= cabinet.getArtQuantity()) {
															num = shijcunNum;
															cabinet.setDepositStatus("已入柜");
														} else {
															num = 0;
															cabinet.setDepositStatus("入柜中");
														}
														cabinet.setDepositTime(Util.getDateTime());
														totalDao.update2(cabinet);// 更新待入柜表的实际数量和状态
													} else {
														num = shijcunNum;// 跳出循环
														builder.append("不是关门信号(FF),指令异常！");
														getsocket(socket, 9);// 不是关门信号返回主页面之前的操作作废
													}
												} else {
													num = shijcunNum;// 跳出循环
													builder.append(cabine.getId() + "柜子编号异常！");
													getsocket(socket, 0);// 柜子编号异常情况发0结束程序
												}
											} else {
												if (special > 0)
													builder.append("特殊规定物品" + cabinet.getDepArticleName() + cabinet.getDepArticleFormat() + "柜子已满！");
												else
													builder.append("非特殊物品柜子已满！");
												num = shijcunNum;// 跳出循环
												getsocket(socket, 0);// 柜子已满发0结束程序
											}
										}
									}// while结束
								} else {
									builder.append(", "+qrCodes + "此物品名称或规格为空");
									getsocket(socket, 6);
								}
							} else {
								builder.append(", "+qrCodes + "此物品不存在，或已入柜");
								getsocket(socket, 6);
							}
						} else {
							builder.append(", "+cardId + "没有待存物品");
							getsocket(socket, 1);
						}
					} else {
						// 取物品流程
						// 1、判断是否为内部（users表）
						// 取物品表中是否存在
						int receive = totalDao.getCount(isQuWuP ,cardId, nowTime, cardId);
						if (receive > 0) {// 证明有待领取信息
							getsocket_1(socket, 3);// 输入验证码界面
							bis2 = new BufferedInputStream(in);// 接收取物验证码（3位16进制）
							byte[] verificationCode = new byte[3];
							bis2.read(verificationCode);
							String verificationCodes = Util.byteArrayToHexStringK(verificationCode).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
							try {
								Integer shijNumint = Integer.parseInt(verificationCodes, 16);
								verificationCodes = shijNumint + "";
								System.out.println("处理之后的cardNumber为："+ verificationCodes);
							} catch (Exception e) {
								builder.append("JS卡号异常：" + e.toString());
							}
							System.out.println("验证码接收：" + verificationCodes);
							builder.append("验证码接收：" + verificationCodes);
							List<ReceiveCabinet> receive2 = totalDao.query("from ReceiveCabinet where receiveVerificationCode = ? and receiveStatus <> '已领取' and verificationCodeStatus = '未使用' and failTime > ?",verificationCodes, nowTime);
							if (receive2 != null && receive2.size() > 0) {
								ReceiveCabinet receive1 = receive2.get(0);// 此条待取物信息
								List<DepositCabinet> cabinets = totalDao.query("from DepositCabinet where id = ?",receive1.getPosId());
								if (cabinets != null && cabinets.size() > 0) {
									DepositCabinet depositCabinet = cabinets.get(0);
									int num = 0;int qub = 0;
									int yingQu = receive1.getReceiveQuantity() - receive1.getRealReceiveQuantity();// 应取数量
									while (yingQu > num) {
										List<ToolCabine> cabinelist = totalDao.query("from ToolCabine where nowArticleName = ? and nowArticleFormat = ? and cabAceIp = ?",depositCabinet.getDepArticleName(),depositCabinet.getDepArticleFormat(),accessIP);
										if (cabinelist != null && cabinelist.size() > 0) {// 查询存放物品的柜子
											ToolCabine cabine = cabinelist.get(0);
											int i = Util.swiInstruction(cabine.getCabOpenOrder());
											if (i > 0) { // 柜子编号要为数字，范围是1~26
												getsocketInt(socket, i);// 发送柜子编号对应的的开门指令
												qub++;
												// 等待开门指令
												bis3 = new BufferedInputStream(in);
												byte[] open = new byte[1];
												bis3.read(open);
												String opens = Util.byteArrayToHexStringK(open).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
												System.out.println("取物开门指令接收："+ opens);
												builder.append("取物开门指令接收："+ opens);
												if ("EE".equals(opens)) {
													num = cabine.getNowNumber();
													boolean b = true;// 验证柜子实际数量是否与应取数量相等
													boolean b1 = true;// 验证柜子实际数量时候大于应取数量
													int[] is = Util.yanzhengintSz2(yingQu + "", 2);
													getsocketIntQU(socket, is[0], is[1]);
													if (cabine.getNowNumber() > yingQu) {
													} else {
														b1 = false;
														if (cabine.getNowNumber() == yingQu)
															b = true;
														else
															b = false;
													}
													// 等待关门信号
													int iss = 0;
													while (iss == 0) {
														// 等待接受关门信号
														bis4 = new BufferedInputStream(in);
														byte[] close = new byte[1];
														bis4.read(close);
														String closes = Util.byteArrayToHexStringK(close).replaceAll(" ",
																"");// 十六进制转换为String类型并去掉空格
														System.out.println("取物关门：" + closes);
														builder.append("取物关门：" + closes);
														if ("FF".equals(closes)) {
															iss = 1;// 跳出循环
															// 返回数量
															int[] is1 = null;
															int shiji = 0;
															if (b1) { // 柜子实际数量大于应取数量，发应取数量，小于发柜子实际数量
																is1 = Util.yanzhengintSz2(yingQu + "", 2);
																shiji = yingQu;
															} else {
																is1 = Util.yanzhengintSz2(cabine.getNowNumber() + "", 2);
																shiji = cabine.getNowNumber();
															}
															getsocketIntQU(socket, is1[0], is1[1]);
															int iss1 = 0;
															while (iss1 == 0) {
																bis4 = new BufferedInputStream(in);// 接受确认还是取消
																byte[] queren = new byte[1];
																bis4.read(queren);
																String querens = Util.byteArrayToHexStringK(queren).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
																System.out.println("取物确认：" + querens);
																builder.append("取物确认：" + querens);
																if ("AA".equals(querens)) {
																	iss1 = 1;
																	String rt = "";
																	if (receive1.getReturnZhi() == null) {
																		rt = "确认" + shiji + " ";
																	} else {
																		rt = receive1.getReturnZhi() + "确认" + shiji + " ";
																	}
																	receive1.setReturnZhi(rt);
																	// 确认了数量清空，
																	if (b1) {
																		cabine.setNowNumber(cabine.getNowNumber() - yingQu);
																		receive1.setReceiveStatus("已领取");
																		receive1.setVerificationCodeStatus("已使用");
																		depositCabinet.setAlreadyReceivedQuantity(depositCabinet.getAlreadyReceivedQuantity()+ yingQu);
																	} else {
																		iss1 = 1;
																		if (b1){
																			receive1.setReceiveStatus("已领取");
																			receive1.setVerificationCodeStatus("已使用");
																		}
																		else
																			receive1.setReceiveStatus("领取中");
																		cabine.setNowNumber(0);
																		cabine.setNowArticleName(null);
																		cabine.setNowArticleFormat(null);
																		depositCabinet.setAlreadyReceivedQuantity(depositCabinet.getAlreadyReceivedQuantity()+ shiji);
																	}
																	cabine.setCabStatus("未满");
																	receive1.setRealReceiveQuantity(receive1.getRealReceiveQuantity()+ shiji);
																	yingQu = receive1.getReceiveQuantity() - receive1.getRealReceiveQuantity();// 当前剩余可领数量
																	if (b) {
																		num = yingQu;
																		getsocket(socket, 9);// 领完了结束掉
																	} else
																		num = 0;
																	totalDao.update2(cabine);
																	totalDao.update2(receive1);
																	totalDao.update2(depositCabinet);
																} else if ("AD".equals(querens)) {
																	String rt = "";
																	if (receive1.getReturnZhi() == null)
																		rt = "取消" + shiji + " ";
																	else 
																		rt = receive1.getReturnZhi() + "取消" + shiji + " ";
																	receive1.setReturnZhi(rt);
																	receive1.setReceiveStatus("已领取");
																	receive1.setVerificationCodeStatus("已使用");
																	// 按取消=>将验证码失效
																	getsocket(socket, 9);// 按取消=》结束掉
																	num = yingQu;
																	totalDao.update2(cabine);
																	totalDao.update2(receive1);
																	totalDao.update2(depositCabinet);
																}
															}// while2结束
														}
													}// while1结束
												} else {
													num = yingQu;// 跳出循环
													builder.append("不是开门信号柜子指令异常！");
													getsocket(socket, 9);// 不是关门信号返回主页面之前的操作作废
												}
											} else {
												num = yingQu;// 跳出循环
												builder.append(cabine.getId()+ "柜子指令异常！");
												getsocket(socket, 0);// 柜子编号异常情况发0结束程序
											}
										} else {// 没有存放对应物品的柜子
											num = yingQu;
											builder.append(verificationCodes+ "此物品不存在，或已取");
											if (qub == 0)//验证码没有对应物品=》提示验证码错误
												getsocket(socket, 5);
											else//当前设备对应柜子中已没有对应物品=》回到主页面
												getsocket(socket, 9);												
										}
									}// while结束
								}
							} else {
								builder.append(verificationCodes+ "此物品不存在，或已取");
								getsocket(socket, 5);
							}
						} else {
							builder.append(cardId + "没有待取物品");
							getsocket(socket, 2);
						}
					}
				}
			} else if(("00".equals(mess1)) && "用户柜".equals(accessType)){//分配给个人使用的柜子
				// 物品柜绑定给个人存放物品
				/************************* 刷卡流程 **********************/
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
				cardId = "";
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
				//查询用户
				List<Users> userList = totalDao.query("from Users where cardId = ? and onWork <>'离职' ",cardId);
				if (userList != null && userList.size() > 0) {
					Users users = userList.get(0);
					userName = users.getName();
					List<ToolCabine> toolCabineList = totalDao.query("from ToolCabine where cabAceIp = ? and id in (select t.id from Users u join u.toolCabines t where u.id = ? ) order by id desc", accessIP,users.getId());
					if (toolCabineList!=null&&toolCabineList.size()>0) {
						int i = Util.swiInstruction(toolCabineList.get(0).getCabOpenOrder());
						if (i > 0) { // 柜子编号要为数字，范围是1~26
							getsocketInt(socket, i);// 发送柜子编号对应的的开门指令
							builder.append(toolCabineList.get(0).getId()+ "开门成功！");
						}else {
							builder.append(toolCabineList.get(0).getId()+ "柜子指令异常！");
							getsocket(socket, 0);// 柜子编号异常情况发0结束程序
						}
					}else {
						builder.append("没有权限开门失败！");
						getsocket(socket, 0);//
					}
				}else {
					builder.append("不是内部卡开门失败！");
					getsocket(socket, 0);//
				}
			} else if(("AB".equals(mess1)||"AC".equals(mess1)) && "用户工具柜".equals(accessType)){//分配给个人使用的工具柜
				String cunqu = "无";
				boolean cqty = true;
				if ("AB".equals(mess1))
					cunqu = "存物品";
				else {
					cunqu = "取物品";
					cqty = false;
				}
				bis1 = new BufferedInputStream(in);// 接收卡号
				byte[] yg_df = new byte[1];// 开始接受第一位标识，如果是DF结束
				bis1.read(yg_df);// 读取数据
				String df = Util.byteArrayToHexStringK(yg_df).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
				System.out.println("用户工具柜接收标识+" + df);
				if ("AD".equals(df)) {// AD不做操作 直接返回主界面
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
					String card = Util.byteArrayToHexStringK(zong_code).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
					System.out.println("存取档卡号为：" + card);
					try {
						Integer cardNumber = Integer.parseInt(card, 16);
						System.out.println("处理之后的cardNumber为：" + cardNumber);
						cardId = cardNumber.toString();
					} catch (Exception e) {
						builder.append("JS卡号异常：" + e.toString());
						cardId = Util.yanNumber(10);
					}
					cardId = Util.cardId(cardId);
					builder.append(",处理之后的卡号(" + cunqu + ")为：" + cardId);
					
					//查询用户
					List<Users> userList = totalDao.query("from Users where cardId = ? and onWork <>'离职' ",cardId);
					if (userList != null && userList.size() > 0) {
						Users users = userList.get(0);
						userName = users.getName();
						List<ToolCabine> toolCabineList = totalDao.query("from ToolCabine where cabAceIp = ? and id in (select t.id from Users u join u.toolCabines t where u.id = ? ) order by id desc", accessIP,users.getId());
						if (toolCabineList!=null&&toolCabineList.size()>0) {
							if(toolCabineList.size()>1){//多个柜子
								getsocketInt(socket, 6);//返回选柜界面
								int i = 0;
								while (i < toolCabineList.size()) {
									bis2 = new BufferedInputStream(in);// 接收柜号
									byte[] accessGui = new byte[1];// 柜号b1~c9
									bis2.read(accessGui);
									String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
											"");// 十六进制转换为String类型并去掉空格
									if ("DF".equals(guiHao)) {
										i = toolCabineList.size();
									} else {
										boolean b = false;
										for (ToolCabine toolCabine : toolCabineList) {
											if (guiHao
													.equals(Util
															.swiInstructionDuiBi(toolCabine
															.getCabOpenOrder()))) {
												b = true;
											}
										}
										if (b) {
											getsocketInt(socket, 2);
											bis4 = new BufferedInputStream(in);// 等待接收返回值
											byte[] bankyan = new byte[1];
											bis4.read(bankyan);
											String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
													" ", "");// 十六进制转换为String类型并去掉空格
											System.out.println("返回结果：" + bank_yan);
											if ("11".equals(bank_yan)) {
												builder.append(toolCabineList.get(0).getId()+ "开门成功！");
											}else {
												builder.append(toolCabineList.get(0).getId()+ "开门无返回值！");
											}
											//getsocket(socket, 6);//返回选柜页面
											/** for **/
										} else {// 您没有该箱子的开门权限
											getsocketInt(socket, 4);
										}
									}
								}
							}else {
								int i = Util.swiInstruction(toolCabineList.get(0).getCabOpenOrder());
								if (i > 0) { // 柜子编号要为数字，范围是1~26
									getsocketInt(socket, i);// 发送柜子编号对应的的开门指令(柜号)
									bis4 = new BufferedInputStream(in);// 等待接收返回值
									byte[] bankyan = new byte[1];
									bis4.read(bankyan);
									String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
											" ", "");// 十六进制转换为String类型并去掉空格
									System.out.println("返回结果：" + bank_yan);
									if ("11".equals(bank_yan)) {
										builder.append(toolCabineList.get(0).getId()+ "开门成功！");
									}else {
										builder.append(toolCabineList.get(0).getId()+ "开门成功！但无返回值！");
									}
									getsocket(socket, 5);//返回主页面
								}else {
									builder.append(toolCabineList.get(0).getId()+ "柜子指令异常！");
									getsocket(socket, 0);// 柜子编号异常情况发0结束程序
								}
							}
						}else {
							if (cqty)
								getsocket(socket, 1);
							else
								getsocket(socket, 0);
							builder.append(cunqu + "没有权限开门失败！");
						}
					}else {
						builder.append("不是内部卡开门失败！");
						getsocket(socket, 0);//
					}
				}
			} else if("00".equals(mess1) && "衣柜".equals(accessType)){
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
				String card = Util.byteArrayToHexStringK(zong_code).replaceAll(" ", "");// 十六进制转换为String类型并去掉空格
				System.out.println("存取档卡号为：" + card);
				try {
					Integer cardNumber = Integer.parseInt(card, 16);
					System.out.println("处理之后的cardNumber为：" + cardNumber);
					cardId = cardNumber.toString();
				} catch (Exception e) {
					builder.append("JS卡号异常：" + e.toString());
					cardId = Util.yanNumber(10);
				}
				cardId = Util.cardId(cardId);
				builder.append(",处理之后的卡号为：" + cardId);
				
				//查询用户
				List<Users> userList = totalDao.query("from Users where cardId = ? and onWork <>'离职' ",cardId);
				if (userList != null && userList.size() > 0) {
					Users users = userList.get(0);
					userName = users.getName();//操作人
					List<ToolCabine> toolCabineList = totalDao.query("from ToolCabine where cabAceIp = ? and id in (select t.id from Users u join u.toolCabines t where u.id = ? ) order by id desc", accessIP,users.getId());
					if (toolCabineList!=null&&toolCabineList.size()>0) {
						// 返回员工信息
						String us = users.getCode();
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
						String nameN = users.getName();
						try {
							int si = 8 - nameN.getBytes("gb2312").length;
							for (int i = 0; i < si; i++) {
								nameN = nameN + " ";
							}
							System.out.println(us+"_"+nameN);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String guihao = "";
						if(toolCabineList.size()>1){
							for (ToolCabine c : toolCabineList) {
								guihao += c.getCabNumber()+" ";
							}
						}else {
							guihao = toolCabineList.get(0).getCabNumber();
						}
						// 考勤机显示个人信息
						PrintWriter bw = new PrintWriter(
								new OutputStreamWriter(socket
										.getOutputStream()));
						bw.write(0x01);//
						bw.write(strChar);//工号
						bw.write(nameN);//姓名
						bw.write(Util.neirong(guihao, 30));//衣柜号
						bw.write(0x0A);//
						bw.flush();
						
						bis1 = new BufferedInputStream(in);// 接收开柜指令FF
						byte[] kaigui = new byte[1];// 柜号b1~c9
						bis1.read(kaigui);
						String kaiguiZhi = Util.byteArrayToHexStringK(kaigui).replaceAll(" ",
								"");// 十六进制转换为String类型并去掉空格
						boolean bo1 = true;//是否进入等待关门循环
						if ("FF".equals(kaiguiZhi)) {
							if(toolCabineList.size()>1){
								//多个柜子
								getsocketInt(socket, 6);//返回选柜界面
								int i = 0;
								while (i < toolCabineList.size()) {
									bis2 = new BufferedInputStream(in);// 接收柜号
									byte[] accessGui = new byte[1];// 柜号b1~c9
									bis2.read(accessGui);
									String guiHao = Util.byteArrayToHexStringK(accessGui).replaceAll(" ",
											"");// 十六进制转换为String类型并去掉空格
									if ("FD".equals(guiHao)) {
										i = toolCabineList.size();
										bo1=false;
									} else {
										boolean b = false;
										String zhi = "0";
										for (ToolCabine toolCabine : toolCabineList) {
											if (guiHao.equals(Util.swiInstructionDuiBi(toolCabine.getCabOpenOrder()))) {
												b = true;
												zhi=toolCabine.getCabOpenOrder();
												yanzheng = toolCabine.getPassWord();
											}
										}
										if (b) {
											if(yanzheng==null||"".equals(yanzheng)){
												getsocketInt(socket, 0);
											}
											int[] yz = Util.yanzhengintSz2(yanzheng,2);
											getsocketPassWard(socket, yz[0], yz[1]);
											// 等待接收返回值
											bis3 = new BufferedInputStream(in);
											byte[] bankyan = new byte[1];
											bis3.read(bankyan);
											String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
													" ", "");// 十六进制转换为String类型并去掉空格
											System.out.println("返回结果：" + bank_yan);
											if ("DD".equals(bank_yan)) {
												builder.append(toolCabineList.get(0).getId()+ "开门成功！");
												getsocketInt(socket, Util.swiInstruction(zhi));
												i = toolCabineList.size();
											}else if("CF".equals(bank_yan)){
												builder.append(toolCabineList.get(0).getId()+ "开门失败！");
												bo1=false;
											}else if("FD".equals(bank_yan)){
												builder.append(toolCabineList.get(0).getId()+ "结束开门！");
												i = toolCabineList.size();
												bo1=false;
											}
										} else {// 您没有该箱子的开门权限
											getsocketInt(socket, 4);
										}
									}
								}
							}else {
								boolean bo = true;
								while (bo) {
									//单个柜子
									yanzheng = toolCabineList.get(0).getPassWord();
									if(yanzheng==null||"".equals(yanzheng)){
										getsocketInt(socket, 0);
									}
									int[] yz = Util.yanzhengintSz2(yanzheng,2);
									getsocketPassWard(socket, yz[0], yz[1]);
									// 等待接收返回值
									bis3 = new BufferedInputStream(in);
									byte[] bankyan = new byte[1];
									bis3.read(bankyan);
									String bank_yan = Util.byteArrayToHexStringK(bankyan).replaceAll(
											" ", "");// 十六进制转换为String类型并去掉空格
									System.out.println("返回结果：" + bank_yan);
									if ("DD".equals(bank_yan)) {
										builder.append(toolCabineList.get(0).getId()+ "开门成功！");
										getsocketInt(socket, Util.swiInstruction(toolCabineList.get(0).getCabOpenOrder()));
										bo=false;
									}else if("CF".equals(bank_yan)){
										builder.append(toolCabineList.get(0).getId()+ "开门失败！");
										bo1=false;
									}else if("FD".equals(bank_yan)){
										builder.append(toolCabineList.get(0).getId()+ "结束开门！");
										bo=false;
										bo1=false;
									}
								}
							}
							//等待接受OF关门信号。
							int i1 = 0;
							while (bo1) {
								// 等待接收返回值
								bis4 = new BufferedInputStream(in);
								byte[] ee = new byte[1];
								bis4.read(ee);
								String ee_yan = Util.byteArrayToHexStringK(ee).replaceAll(
										" ", "");// 十六进制转换为String类型并去掉空格
								System.out.println("返回结果：" + ee_yan);
								if ("EE".equals(ee_yan)) {
									bo1 = false;
								}
								if(i1>2){
									builder.append("衣柜出现锁无法关闭情况，请前检查");
									bo1 = false;
									List<Users> userss = totalDao.query("from Users where dept = '信息' and ((name = '周亮泽' and code = '479') or (name = '李聪' and code = '472'))");
									if(userss!=null&&userss.size()>0){
										List<String> codess = new ArrayList<String>();
										for (Users users2 : userss) {
											codess.add(users2.getCode());
										}
										if (codess != null && codess.size() > 0) {
											RtxUtil.sendNotify(codess, "衣柜出现锁无法关闭(无EE)情况，请前检查",
													"系统消息", "0", "0");
										}
									}
								}
								i1++;
							}
						}
					}else {
						getsocket(socket, 0);
						builder.append("没有权限开门失败！");
					}
				}else {
					System.out.println("不是内部卡。。。");
					getsocket(socket, 0);
				}
			} else if("EE".equals(mess1) && "衣柜".equals(accessType)){
				builder.append("衣柜关锁返回指令EE");
			}
			else {
				// 无操作
				builder.append("不是内部卡。。。");
				getsocket(socket, 0);
			}
			System.out.println("服务端关闭 soko");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) bis.close();
				if (bis1 != null) bis1.close();
				if (bis2 != null) bis2.close();
				if (bis3 != null) bis3.close();
				if (bis4 != null) bis4.close();
				if (in != null) in.close();
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersGJG.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:" + SocketServersGJG.clientcount);
					if (!"".equals(builder.toString()))
						DoorBangDingServerImpl.caeLogInfor(builder, cardId,yanzheng, userName, accessType, accessname,accessId, accessIP);
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
		bw.write(i);// 发送信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}

	/**
	 * @author Li_Cong
	 * @param sockets
	 *            连接 不断开连接，继续等待
	 * @param i
	 *            发送指令
	 * @throws IOException
	 *             异常抛出
	 */
	private static void getsocket_1(Socket sockets, Integer i)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(i);// 发送信号
		bw.flush();
	}

	/**
	 * 将验证码存入一个char数组 发送
	 * 
	 * @param sockets
	 * @param yz
	 * @throws IOException
	 */
	public static void getsocketChar(Socket sockets, char[] yz)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(0x03);// 发送信号
		bw.write(yz);// 发送信号
		bw.write(0x0A);// 发送信号
		bw.flush();
	}
	
	/**
	 * 发送三个两位十六进制的验证码
	 * 
	 * @param sockets
	 * @param i
	 * @param i1
	 * @param i2
	 * @throws IOException
	 */
	public static void getsocketPassWard(Socket sockets, int ...i)
	throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		for (int j : i) {
			out.write(j);
		}
		out.write(0x0A);
		out.flush();
	}

	/**
	 * 发送三个两位十六进制的验证码
	 * 
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
	 * 发送四个两位十六进制的验证码 adminCardId
	 * 
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
	 * 
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

	/**
	 * 发送大于7F 127的字符
	 * 
	 * @param sockets
	 * @param i
	 * @throws IOException
	 */
	public static void getsocketInt(Socket sockets, int i) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(i);
		out.flush();
	}

	public static void getsocketIntCUN(Socket sockets, int i, int i1)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x07);
		out.write(i);
		out.write(i1);
		out.write(0x0A);
		out.flush();
	}

	public static void getsocketIntQU(Socket sockets, int i, int i1)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x08);
		out.write(i);
		out.write(i1);
		out.write(0x0A);
		out.flush();
	}

}
