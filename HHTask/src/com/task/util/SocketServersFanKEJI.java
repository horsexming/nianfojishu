package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.dmltry.Zhongjian;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.FingerprintMg;
import com.task.entity.menjin.visitor.Visitor;
import com.task.util.serialPort.ByteUtils;

public class SocketServersFanKEJI extends Thread {

	// 端口
	public static int PORT = 8836;
	public static int clientcount = 0;
	public TotalDao toalDao;

	Socket socket = null;

	public SocketServersFanKEJI(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	/**
	 * 跑
	 */
	public void run() {
		boolean listening = true;
		String accessIP = "";
		ServerSocket server = null; // 服务器端Socket对象
		try {

			System.out.println("Fangkeji ServerSocket starts...");

			try {
				server = new ServerSocket(PORT);

			} catch (IOException e) {
				System.out.println("Can not listen to. " + e);
			}
			while (listening) {
				clientcount++;
				new ServersFanKEJI(server.accept(), clientcount, toalDao).start();
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
class ServersFanKEJI extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServersFanKEJI(Socket socket, int clientnum, TotalDao toalDao) {
		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的考勤机数: " + number);

	}

	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		InputStream in = null;
		StringBuffer builder = new StringBuffer();
		String accessIP = "";
		String cardIds = "";// 卡号
		String yanzheng = "";// 来访人员验证码
		String username = "";// 员工姓名
		String accessname = "";// 设备名称
		String inOutType = "";// 进出类型
		String istrueKao = "";// 是否允许考勤
		Integer accessId = 0;// 设备id
		AccessEquipment accessEquipment = null;
		Integer iii = 6;

		try {
			System.out.println("----------------------------------");
			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			System.out.println("服务端开始接受指纹标识！");
			String accessType = "";// 门禁类型
			String equipmentOutIn = "";// 门禁用途（）
			List acElist = totalDao.query("from AccessEquipment where equipmentIP=? order by id desc", accessIP);
			if (acElist != null && acElist.size() > 0) {
				accessEquipment = (AccessEquipment) acElist.get(0);
				accessType = accessEquipment.getEquipmentDaoType();
				accessname = accessEquipment.getEquipmentName();
				accessId = accessEquipment.getId();
				istrueKao = accessEquipment.getIsTrueKao();
				equipmentOutIn = accessEquipment.getEquipmentOutIn();
			}
			
			List<Zhongjian> zhongjians = totalDao.query("FROM Zhongjian WHERE status=?", "待删除");
			if (zhongjians != null) {
				for (Zhongjian zhongjian : zhongjians) {
					// 发送删除命令删除指定用户
					getsocketbyte_shanchu(socket, zhongjian.getCanshu(), deleteORhuo(zhongjian.getCanshu()));

					bis = new BufferedInputStream(in);
					byte[] identifi = new byte[9];
					bis.read(identifi);

					String one=Util.byteArrayToHexStringK(identifi).replaceAll(" ", "");
					 String jieguo= one.substring(one.length()-2, one.length());
					 System.out.println(one);
					if (jieguo.equals("00")) {
						zhongjian.setStatus("已删除");
						totalDao.update2(zhongjian);
					}
				}
			}
			
			List<FingerprintMg> list = totalDao.query(
					"from FingerprintMg where id in (SELECT fingerprintMgid from Zhongjian where status='未下发'  and accessEquipmentid =?) and type='0'",
					5);
			if (list != null) {
				for (FingerprintMg fingerprintMg : list) {

					// 特征值
					String tzhen = fingerprintMg.getIdentification();
					if (tzhen != null) {

						// 获取到编号
						List<Zhongjian> liszho = totalDao.query("from Zhongjian WHERE fingerprintMgid = ?",
								fingerprintMg.getId());
						Zhongjian zho = liszho.get(0);
						in = socket.getInputStream();

						getsocketbyte_xiafa(socket, zho.getCanshu(), tzhen);

						bis = new BufferedInputStream(in);
						byte[] identi = new byte[10];
						bis.read(identi);
						

						
						String one=Util.byteArrayToHexStringK(identi).replaceAll(" ", "");
						 String jieguo= one.substring(one.length()-2, one.length());
						
						System.out.println(Util.byteArrayToHexStringK(identi).replaceAll(" ", ""));
						if (jieguo.equals("00")) {
							zho.setStatus("已下发");
							System.out.println("下发成功");
							totalDao.update(zho);
						}

						if (Util.byteArrayToHexStringK(identi).replaceAll(" ", "").equals("06")) {
							System.out.println("用户重复录入");
						}

					} else {
						System.out.println("当前特征值为空......");
					}

				}
			}

			// 查询访客待下发列表
			List<Visitor> visitorList = totalDao
					.query("from Visitor where visitorStatus is not null and visitorStatus in ('待打印','待进门','待出门') ");
			if (visitorList != null && visitorList.size() > 0) {
				DecimalFormat df = new DecimalFormat("0000");
				for (Visitor visitor : visitorList) {
					if (visitor.getMjFingerId() == null || visitor.getMjFingerId().equals("")) { // 说明需要下发的
						for (int i = 1; i < 3000; i++) {
							String format = df.format(i);
							Integer zhongjianCount = totalDao
									.getCount("from Zhongjian where canshu is not null and canshu = ?", format);
							Integer doorBangDingCount = totalDao.getCount(
									"from DoorBangDing where fk_security_id is not null and fk_security_id =?", i);
							Integer visitorCount = totalDao
									.getCount("from Visitor where mjFingerId is not null and MjFingerId = ?", format);
							if ((zhongjianCount == null || zhongjianCount <= 0)
									&& (doorBangDingCount == null || doorBangDingCount <= 0)
									&& (visitorCount == null || visitorCount <= 0)) {
								if (visitor.getFingerprint() != null && visitor.getFingerprint().length() > 0) {

									// 下发命令
									in = socket.getInputStream();

									getsocketbyte_xiafa(socket, format, visitor.getFingerprint());

									bis = new BufferedInputStream(in);
									byte[] identi = new byte[10];
									bis.read(identi);

									String one=Util.byteArrayToHexStringK(identi).replaceAll(" ", "");
									 String jieguo= one.substring(one.length()-2, one.length());

									if (jieguo.equals("00")) {
										visitor.setMjFingerId(format);
										visitor.setMjFingerStatus("已下发");
										totalDao.update(visitor);
										System.out.println("访客指纹下发成功");
									}
								}
								break;
							}
						}
					}
				}
			}
			// 删除访客的指纹记录
						List<Visitor> visitorDelList = totalDao
								.query("from Visitor where mjFingerStatus is not null and mjFingerStatus = '待删除'");
						if (visitorDelList != null && visitorDelList.size() > 0) {
							for (Visitor visitor : visitorDelList) {
								in = socket.getInputStream();
								getsocketbyte_shanchu(socket, visitor.getMjFingerId(), deleteORhuo(visitor.getMjFingerId()));
								bis = new BufferedInputStream(in);
								byte[] identifi = new byte[9];
								bis.read(identifi);//读取数据
								String one=Util.byteArrayToHexStringK(identifi).replaceAll(" ", "");
								 String jieguo= one.substring(one.length()-2, one.length());
								 System.out.println(one);
								if (jieguo.equals("00")) {
									visitor.setMjFingerStatus("已删除");
									totalDao.update(visitor);
								}

							}
						}
						
						bis = new BufferedInputStream(in);
						byte[] idf1 = new byte[10];
						bis.read(idf1);
						
						byte[] idf2 = new byte[8];
						bis.read(idf2);


						 String duibifanghui= Util.byteArrayToHexString(idf2).replaceAll(" ", "");
						 
						String biduiStatus= duibifanghui.substring(8, 10);
						 
						if ("01".equals(biduiStatus)) {
							
							//userFingerId
							String userFingerId= duibifanghui.substring(4, 8);
							 
							System.out.println("指纹ID : ==" + userFingerId);
							List<Visitor> list2=  totalDao.query("from Visitor where fingerId=?",userFingerId);
							if (list2==null || list2.size()==0) {
								System.out.println("没有模块为："+userFingerId+"的用户编号");
							}else {
								
					
								Visitor visitor=  list2.get(0);
		
								String mjstats= visitor.getMjFingerStatus();
								
								if(mjstats.equals("待出门")){
									visitor.setMjFingerStatus("待删除");
									totalDao.update(visitor);
								}								
								if(mjstats.equals("待进门")){
									visitor.setMjFingerStatus("待出门");
									totalDao.update(visitor);
								}  
								}
							
							getsocketbyte_duibi(socket);
						}					
						System.out.println("服务端关闭 soko");

		} catch (Exception e) {
			e.printStackTrace();
			builder.append(e);
			try {
				System.out.println("iii:" + iii);
				getsocket(socket, iii);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
			  finally { 
				  try { 
					  if (bis != null){ 
						  bis.close(); 
						  } if (bis1 !=null) { 
							  bis1.close();
							  } if (bis2 != null) {
								  bis2.close();
								  } if(in != null) {
									  in.close();
									  } 
								  if (socket != null) {
					  socket.close(); // 关闭
					  SocketServersFanKEJI.clientcount--;
					  System.out.println("服务端关闭,当前连接设备数量为:" +
					 SocketServersFanKEJI.clientcount);
					  System.out.println("断开时间：" +Util.getDateTime()); 
			 } 
			  } catch (IOException e) {
			  e.printStackTrace();
			  } 
			  }
			  // 关闭Socket输入流 }
			 

	}
	private static void dainti(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets.getOutputStream()));
		bw.write(i);// 发送不开门信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}
	/**
	 * 生成与值 删除
	 * 
	 * @param s
	 *            编号
	 * @param se
	 *            指纹标识与值
	 * @param i
	 *            类型（1/2/3）
	 * @return
	 */
	public static int deleteORhuo(String s) {
		int a = 4;
		a ^= Integer.parseInt(s.substring(0, 2));
		a ^= Integer.parseInt(s.substring(2, 4));
		return a;
	}

	/**
	 * 
	 * @param sockets
	 * @param userNum
	 *            指纹模块用户编号
	 * @param by
	 *            指纹特征值
	 * @throws IOException
	 * @throws InterruptedException
	 *             a 校验位
	 */
	public static void getsocketbyte_xiafa(Socket sockets, String userNum, String by)
			throws IOException, InterruptedException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		String a = Integer.toHexString(xiafayuzh(userNum, by, 01));

		if (a.length() == 1) {
			a = "0" + a;
		}

		String baoString = "F5 41 00 C4 00 00 85 F5 F5 " + userNum + "01" + by + a + "F5";
		out.write(ByteUtils.hexStr2Byte(baoString.replaceAll(" ", "")));
		out.flush();
	}

	/**
	 * 
	 * @param s
	 *            参数
	 * @param se
	 *            特征值
	 * @param i
	 *            权限 1
	 * @return
	 */

	public static int xiafayuzh(String s, String se, int i) {
		int a = 0;
		a ^= Integer.parseInt(s.substring(0, 2), 16);
		a ^= Integer.parseInt(s.substring(2, 4), 16);
		a ^= i;

		String[] str = se.split(" ");
		for (String string : str) {
			a ^= Integer.parseInt(string, 16);
		}
		return a;
	}
	
	
	
	public static void getsocketbyte_shanchu(Socket sockets, String usersNum, Integer a) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		String delte = null;
		if (a < 10) {
			int b = 0;
			delte = "F5 04 " + usersNum + "00 00" + b + Integer.toHexString(a) + "F5";
		} else if (a > 10) {
			delte = "F5 04 " + usersNum + "00 00" + Integer.toHexString(a) + "F5";
		}

		out.write(ByteUtils.hexStr2Byte(delte.replaceAll(" ", "")));
		out.flush();
	}
	
	/**
	 * 1：N 手指：指纹机 对比
	 * 
	 * @param sockets
	 * @throws IOException
	 */
	public static void getsocketbyte_duibi(Socket sockets) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(new byte[] { (byte) 0xF5, 0x0C, 0x00, 0x00, 0x00, 0x00, 0x0C, (byte) 0xF5 });
		// out.write(0xF5);
		// out.write(0x0C);
		// out.write(0X00);
		// out.write(0x00);
		// out.write(0x00);
		// out.write(0x00);
		// out.write(0x0C);
		// out.write(0xF5);
		out.flush();
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets.getOutputStream()));
		bw.write(i);// 发送不开门信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
