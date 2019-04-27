package com.task.util;

import com.task.Dao.TotalDao;
import com.task.entity.menjin.AccessLogInfor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class SocketServersTest extends Thread {

	public static final int PORT = 0000;//此处改成你要测试的端口
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersTest(TotalDao toalDao) {
		this.toalDao = toalDao;
	}
 
	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口？监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("Test ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadTest(server.accept(), clientcount, toalDao)
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

class ServerThreadTest extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServerThreadTest(Socket socket, int clientnum, TotalDao toalDao) {
		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的Test数: " + number);
	}

	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		InputStream in = null;
		StringBuilder builder = new StringBuilder();
		String accessIP = "";// 门禁IP
		String yanzheng = "";// 验证码
		accessIP = socket.getInetAddress().getHostAddress();
		System.out.println(accessIP + " 进入服务端了");
		// 由Socket对象得到输入流,并构造相应的BufferedReader对象
		try{
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			// 先接收接收第一个字符 用做标识
			System.out.println("服务端开始接受标识！");
			byte[] first = new byte[1];
			bis.read(first);
			String ff = Util.byteArrayToHexStringK(first).replaceAll(" ","");// 十六进制转换为String类型并去掉空格
			System.out.println("返回结果：" + ff);
			System.out.println("服务端关闭 soko");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {bis.close();}
				if (bis1 != null) {bis1.close();}
				if (in != null) {in.close();}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersTest.clientcount--;

					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersTest.clientcount);
					AccessLogInfor accessLogInfor = new AccessLogInfor();
					accessLogInfor.setYanzheng(yanzheng);
					accessLogInfor.setInfor(builder.toString());
					accessLogInfor.setAceIp(accessIP);// IP
					accessLogInfor.setCardId("dangan");//
					accessLogInfor.setAddTime(Util.getDateTime());
					totalDao.save2(accessLogInfor);
					System.out.println("++" + builder.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}
	}
}
