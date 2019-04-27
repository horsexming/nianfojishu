package com.task.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.task.util.Util;

public class SocketTest {
	public static final String SERVER_IP = "192.168.0.114";
	public static final int PORT = 8999;

	public static void main(String[] args) {
		try {

			int clientcount = 0; // 统计客户端总数

			boolean listening = true; // 是否对客户端进行监听

			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口2121监听客户请求
				server = new ServerSocket(PORT);

				System.out.println("Server starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;

				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThread(server.accept(), clientcount).start();
			}
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}
}

class ServerThread extends Thread {
	private static int number = 0; // 保存本进程的客户计数

	Socket socket = null; // 保存与本线程相关的Socket对象

	public ServerThread(Socket socket, int clientnum) {

		this.socket = socket;
		number = clientnum;
		System.out.println("当前在线的用户数: " + number);
	}

	public void run() {
		try {
			String pmiIP = socket.getInetAddress().getHostAddress();
			System.out.println(pmiIP);

			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			InputStream in = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			byte[] nenghao_data = new byte[1];// 先接收第一个字符
			bis.read(nenghao_data);// 读取数据
			String mes = Util.byteArrayToHexString(nenghao_data);
			System.out.println("[Client " + number + "]: " + mes);
			// 确认包
			if ("FF".equals(mes)) {
				OutputStream out = socket.getOutputStream();
				out.write(0x03);
				out.close();
			} else if ("AA".equals(mes)) {

			}
			bis.close(); // 关闭Socket输入流
			socket.close(); // 关闭Socket
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}
}
