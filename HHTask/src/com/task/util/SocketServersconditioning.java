package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.Users;
import com.task.entity.dmltry.Conditioning;
import com.task.entity.menjin.AccessEquipment;
import com.task.util.serialPort.ByteUtils;

public class SocketServersconditioning extends Thread {

	// 端口
	public static int PORT = 8888;
	public static int clientcount = 0;
	public TotalDao toalDao;

	Socket socket = null;

	public SocketServersconditioning(TotalDao toalDao) {
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

			System.out.println("Conditioning ServerSocket starts...");

			try {
				server = new ServerSocket(PORT);

			} catch (IOException e) {
				System.out.println("Can not listen to. " + e);
			}
			while (listening) {
				clientcount++;
				new ServersAir(server.accept(), clientcount, toalDao).start();
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
class ServersAir extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	TotalDao totalDao;

	public ServersAir(Socket socket, int clientnum, TotalDao toalDao) {
		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的机数: " + number);

	}

	public static int getIndex(String str, String s, int count) {
		if (count == 0)
			return -1;
		int len = s.length();
		int index = -1 - len;
		while (count > 0 && (index = str.indexOf(s, index + len)) != -1) {
			count--;
		}
		if (index == -1) {
			index = str.length();
		}

		return index;
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

			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			System.out.println("服务端开始接受指纹标识！");

			List<Conditioning> conlist = totalDao.query("from Conditioning");
			// 循环人员
			for (Conditioning conditioning : conlist) {
				String useridstrString = conditioning.getUserid();
				int zu = useridstrString.length() - useridstrString.replaceAll(",", "").length();
				int count = 0;
				for (int i = 0; i < zu + 1; i++) {
					String userid = useridstrString.substring(getIndex(useridstrString, ",", i) + 1,
							getIndex(useridstrString, ",", i + 1));
					int id = Integer.parseInt(userid);

					// 根据id查询这个人
					List<Users> users = totalDao.query("from Users WHERE id=?", id);
					for (Users users2 : users) {
						if (users2.getUserStatus() == null) {

						} else {

							if (users2.getUserStatus().equals("离开")) {
								count++;
							}
							if (count == zu + 1) {
								for (int j = 0; j < 3; j++) {
									try {
										get_closeCon(socket, conditioning.getConditioningip());
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}

						}

					}

				}

			}
			System.out.println("服务端关闭 soko");
		} catch (IOException e) {
			e.printStackTrace();
			builder.append(e);
			try {
				System.out.println("iii:" + iii);
				getsocket(socket, iii);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bis1 != null) {
					bis1.close();
				}
				if (bis2 != null) {
					bis2.close();
				}
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersconditioning.clientcount--;
					System.out.println("服务端关闭,当前连接设备数量为:" + SocketServersconditioning.clientcount);
					System.out.println("断开时间：" + Util.getDateTime());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}

	}

	private static void getsocket(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets.getOutputStream()));
		bw.write(i);// 发送不开门信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
	}

	// 关空调
	public static void get_closeCon(Socket sockets, String userNum) throws IOException, InterruptedException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		if (userNum.equals("A")) {
			userNum = "A3";
		}
		if (userNum.equals("B")) {
			userNum = "B3";
		}
		if (userNum.equals("C")) {
			userNum = "C3";
		}
		if (userNum.equals("D")) {
			userNum = "D3";
		}

		out.write(ByteUtils.hexStr2Byte(userNum));
		out.flush();
		out.close();
	}

}
