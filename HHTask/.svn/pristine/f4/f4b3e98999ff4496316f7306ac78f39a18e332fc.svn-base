package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


import com.task.Dao.TotalDao;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.entity.menjin.AccessEquipment;

public class SocketServersBaoJin extends Thread {

	public static final int PORT = 8876;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersBaoJin(TotalDao toalDao) {
		this.toalDao = toalDao;
	}

	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8876监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("KaoQing ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadBJ(server.accept(), clientcount, toalDao)
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
class ServerThreadBJ extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadBJ(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的考勤机数: " + number);
	}

	public void run() {
		BufferedInputStream bis = null;
		InputStream in = null;
		StringBuffer builder = new StringBuffer();
		String accessIP = "";
		String cardIds = "";// 卡号
		String yanzheng = "";//来访人员验证码
		String username = "";//员工姓名
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
			System.out.println(accessIP+"标识: " + mess1);
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
				accessname = accessEquipment.getEquipmentName();
				accessId = accessEquipment.getId();
			}
			if ("FF".equals(mess1) && ("人行道".equals(accessType))) {
				/************************ 验证码流程 **********************/
				System.out.println(accessIP+"报警了");
				builder.append(","+accessIP+"报警了");
				yanzheng = mess1;
				try {
					sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getsocket(socket, 1);
			}else {
				// 无操作
				System.out.println(",收到无效指令:"+mess1);
				builder.append(",收到无效指令:"+mess1);
				getsocket(socket, 0);
			}
			System.out.println("服务端关闭 soko");
		} catch (Exception e) {
			e.printStackTrace();
			builder.append(e);
		} finally {
			try {
				if (bis != null) {bis.close();}
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersBaoJin.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersBaoJin.clientcount);
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
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}

}
