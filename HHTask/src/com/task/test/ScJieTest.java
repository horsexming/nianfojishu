package com.task.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class ScJieTest {

	public static void main(String[] args) {
		// try {
		// // ======================获得开关的信号
		// // 1.建立客户端socket连接，指定服务器位置及端口
		// Socket socket = new Socket("192.168.0.201", 8899);
		// BufferedInputStream bis = new BufferedInputStream(socket
		// .getInputStream());
		// // ======================获得开关的信号
		// while (true) {
		// // 每次接收的数据
		// byte[] data = new byte[1];
		// bis.read(data);// 读取数据
		// String data1 = byteArrayToHexString(data);
		// if ("02".equals(data1)) {// 合格
		// long ll;
		// long l = System.currentTimeMillis();
		// ll = l;
		// while (true) {
		// l = System.currentTimeMillis();
		// long d = l - ll;
		// if (d == 20000) {
		// Socket socket2 = new Socket("192.168.0.91", 8899);
		// BufferedInputStream bis2 = new BufferedInputStream(
		// socket2.getInputStream());
		// while (true) {
		// // 每次接收的数据
		// byte[] data2 = new byte[19];
		// bis2.read(data2);// 读取数据
		// String dataString = byteArrayToHexString(data2);
		// Integer xlsz = Integer.valueOf(dataString
		// .substring(22, 26), 16);
		// Integer ylsz = Integer.valueOf(dataString
		// .substring(30, 34), 16);
		// Float xl = xlsz / 8525F / 24;
		// Float yl = ylsz / 250F / 2.67F;
		// xl = Float
		// .parseFloat(String.format("%.2f", xl));
		// yl = Float
		// .parseFloat(String.format("%.1f", yl));
		// System.out.println("\n泄露量:" + xl + "压力值" + yl);
		// return;
		// }
		// }
		// //
		// }
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// ======================获得气密信（测试实时信息）
		// 1.建立客户端socket连接，指定服务器位置及端口
		// =====================获得气密信号
		try {
			Socket socket2 = new Socket("192.168.0.91", 8899);
			BufferedInputStream bis2 = new BufferedInputStream(socket2
					.getInputStream());
			while (true) {
				// 每次接收的数据
				byte[] data2 = new byte[19];
				bis2.read(data2);// 读取数据
				String dataString = byteArrayToHexString(data2);
				Integer xlsz = Integer
						.valueOf(dataString.substring(22, 26), 16);
				Integer ylsz = Integer
						.valueOf(dataString.substring(30, 34), 16);
				// Float xl = xlsz / 8525F / 28.3F;
				// Float yl = ylsz / 250F / 2.62F;
				Float xl = findXlValue(xlsz);
				Float yl = findYlValue(ylsz);
				xl = Float.parseFloat(String.format("%.2f", xl));
				if (xl <= 0.07F) {
					xl = 0F;
				}
				yl = Float.parseFloat(String.format("%.1f", yl));
				// System.out.println(data1 + "\n泄露数值:" + xlsz + "压力数值"
				// + ylsz
				// + "\n泄露值:" + xl + "压力值" + yl);
				System.out.println("\n泄露量:" + xl + "压力值" + yl);
				// long ll;
				// long l = System.currentTimeMillis();
				// ll = l;
				// while (true) {
				// l = System.currentTimeMillis();
				// long d = l - ll;
				// // System.out.println(d);
				// if (d == 500) {
				// break;
				// }
				// }
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * ========如果合格的时候获取数据 if ("0A".equals(data1) || "06".equals(data1)) {//
		 * 合格 while (true) { // 每次接收的数据 byte[] data2 = new byte[19];
		 * bis2.read(data2);// 读取数据 String dataString =
		 * byteArrayToHexString(data2); Integer xlsz =
		 * Integer.valueOf(dataString.substring(22, 26), 16); Integer ylsz =
		 * Integer.valueOf(dataString.substring(30, 34), 16); Float xl = xlsz /
		 * 8525F / 24; Float yl = ylsz / 250F / 2.67F; xl =
		 * Float.parseFloat(String.format("%.2f", xl)); yl =
		 * Float.parseFloat(String.format("%.1f", yl)); //
		 * System.out.println(data1 + "\n泄露数值:" + xlsz + "压力数值" // + ylsz // +
		 * "\n泄露值:" + xl + "压力值" + yl); System.out.println( "\n泄露量:" + xl +
		 * "压力值" + yl); return; }
		 * 
		 * }
		 */

		// 4.关闭资源
		// socket.close();

	}

	private static final String HEX_CODE = "0123456789ABCDEF";

	/***
	 * 十六进制转换为String类型
	 * 
	 * @param bs
	 * @return
	 */
	public static String byteArrayToHexString(byte[] bs) {
		int _byteLen = bs.length;
		StringBuilder _result = new StringBuilder(_byteLen * 2);
		for (int i = 0; i < _byteLen; i++) {
			int n = bs[i] & 0xFF;
			_result.append(HEX_CODE.charAt(n >> 4));
			_result.append(HEX_CODE.charAt(n & 0x0F));
		}
		return String.valueOf(_result);
	}

	/***
	 * 查表获取泄露量
	 * 
	 * @param xlZhi泄露数值
	 * @return
	 */
	public static Float findXlValue(Integer xlZhi) {
		Float xlNum = 0F;
		// System.out.println(xlZhi);
		if (xlZhi < 15500) {// (0L/min)
			xlNum = 0F;
		} else if (xlZhi >= 15500 && xlZhi < 27600) {// (0-0.16L/min)
			xlNum = (xlZhi - 15500F) / (27600F - 15500F) * 0.16F + 0F;
		} else if (xlZhi >= 27600 && xlZhi < 29650) {// (0.16-0.24L/min)
			xlNum = (xlZhi - 27600F) / (29650F - 27600F) * 0.08F + 0.16F;
		} else if (xlZhi >= 29650 && xlZhi < 31760) {// (0.24-0.0.32L/min)
			xlNum = (xlZhi - 29650F) / (31760F - 29650F) * 0.08F + 0.24F;
		} else if (xlZhi >= 31760 && xlZhi < 33970) {// (0.32-0.4L/min)
			xlNum = (xlZhi - 31760F) / (33970F - 31760F) * 0.08F + 0.32F;
		} else if (xlZhi >= 33970 && xlZhi < 36600) {// (0.4-0.48L/min)
			xlNum = (xlZhi - 33970F) / (36600F - 33970F) * 0.08F + 0.4F;
		} else if (xlZhi >= 36600 && xlZhi < 37947) {// (0.48-0.56L/min)
			xlNum = (xlZhi - 36600F) / (37947F - 36600F) * 0.08F + 0.48F;
		} else if (xlZhi >= 37947 && xlZhi < 39467) {// (0.56-0.64L/min)
			xlNum = (xlZhi - 37947F) / (39467F - 37947F) * 0.08F + 0.56F;
		} else if (xlZhi >= 39467 && xlZhi < 40800) {// (0.64-0.72L/min)
			xlNum = (xlZhi - 39467F) / (40800F - 39467F) * 0.08F + 0.64F;
		} else if (xlZhi >= 40800 && xlZhi < 42600) {// (0.72-0.8L/min)
			xlNum = (xlZhi - 40800F) / (42600F - 40800F) * 0.08F + 0.72F;
		} else if (xlZhi >= 42600 && xlZhi < 44200) {// (0.8-0.88L/min)
			xlNum = (xlZhi - 42600F) / (44200F - 42600F) * 0.08F + 0.8F;
		} else if (xlZhi >= 44200 && xlZhi < 45850) {// (0.88-0.96L/min)
			xlNum = (xlZhi - 44200F) / (45850F - 44200F) * 0.08F + 0.88F;
		} else if (xlZhi >= 45850 && xlZhi < 47102) {// (0.96-1.04L/min)
			xlNum = (xlZhi - 45850F) / (47102F - 45850F) * 0.08F + 0.96F;
		} else if (xlZhi >= 47102 && xlZhi < 48750) {// (1.04-1.12L/min)
			xlNum = (xlZhi - 47102F) / (48750F - 47102F) * 0.08F + 1.04F;
		} else if (xlZhi >= 48750 && xlZhi < 50437) {// (1.12-1.2L/min)
			xlNum = (xlZhi - 48750F) / (50437F - 48750F) * 0.08F + 1.12F;
		} else if (xlZhi >= 50437 && xlZhi < 52079) {// (1.2-1.28L/min)
			xlNum = (xlZhi - 50437F) / (52079F - 50437F) * 0.08F + 1.2F;
		} else if (xlZhi >= 52079 && xlZhi < 53352) {// (1.28-1.36L/min)
			xlNum = (xlZhi - 52079F) / (53352F - 52079F) * 0.08F + 1.28F;
		} else if (xlZhi >= 53352 && xlZhi < 54330) {// (1.36-1.44L/min)
			xlNum = (xlZhi - 53352F) / (54330F - 53352F) * 0.08F + 1.36F;
		} else if (xlZhi >= 54330 && xlZhi < 55646) {// (1.44-1.52L/min)
			xlNum = (xlZhi - 54330F) / (55646F - 54330F) * 0.08F + 1.44F;
		} else if (xlZhi >= 55646 && xlZhi < 57707) {// (1.52-1.6L/min)
			xlNum = (xlZhi - 55646F) / (57707F - 55646F) * 0.08F + 1.52F;
		} else if (xlZhi >= 57707 && xlZhi < 65535) {// (1.6-6L/min)
			xlNum = (xlZhi - 57707F) / (65535F - 57707F) * 3.4F + 1.6F;
		}
		return xlNum;
	}

	/***
	 * 查表获取压力值
	 * 
	 * @param ylZhi泄露数值
	 * @return
	 */
	public static Float findYlValue(Integer ylZhi) {
		Float ylNum = 0F;
		if (ylZhi < 100) {// 0Kpa
			ylNum = 0F;
		} else if (ylZhi >= 100 && ylZhi < 3335) {// 0-5Kpa
			ylNum = (ylZhi - 100F) / (3335F - 100F) * 5F + 0F;
		} else if (ylZhi >= 3335 && ylZhi < 16675) {// 5-25Kpa
			ylNum = (ylZhi - 3335F) / (16675F - 3335F) * 20F + 5F;
		} else if (ylZhi >= 16675 && ylZhi < 20010) {// 25-30Kpa
			ylNum = (ylZhi - 16675F) / (20010F - 16675F) * 5F + 25F;
		} else if (ylZhi >= 20010 && ylZhi < 20200) {// 30-31Kpa
			ylNum = (ylZhi - 20010F) / (20200F - 20010F) * 1F + 30F;
		} else if (ylZhi >= 20200 && ylZhi < 20870) {// 31-32Kpa
			ylNum = (ylZhi - 20200F) / (20870F - 20200F) * 1F + 31F;
		} else if (ylZhi >= 20870 && ylZhi <= 23335) {// 32-35Kpa
			ylNum = (ylZhi - 20870F) / (23335F - 20870F) * 3F + 32F;
		}
		return ylNum;
	}

}
