package com.task.util;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.task.util.serialPort.ByteUtils;

/***
 * 与硬件通讯的方法(socket)
 * 
 * @author 李聪
 * 
 */
public class UtilTong {

	public static int ji(int num) {
		return num * 10;
	}

	public static int deng(int num, int o) {
		return num * 10 + o;
	}

	/**
	 * 打开/关闭库位方法
	 * 
	 * @param ip
	 * @param b
	 *            打开：true/关闭：false
	 * @param port
	 *            端口号
	 * @param num
	 *            第几套 (1~6)
	 * @param openOrClose
	 *            指令(1~6)
	 * @return String
	 */
	public static String OCKuWei(String ip, Integer port, boolean b, int num, int openOrClose) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			Thread.sleep(1000);
			s = new Socket(ip, port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(0);
			p.write(ji(num));
			p.write(0x3B);
			p.write(10);
			p.write(11);
			p.write(openOrClose);
			p.write(00);
			p.write(00);
			p.write(00);
			p.write(00);
			p.write(00);
			if (b)
				p.write(0xFE);// 打开门
			else
				p.write(0xFF);// 关闭门
			p.flush();
			p.close();

			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 关闭库位后刷新屏幕的内容（库位号）
	 * 
	 * @param ip
	 * @param port
	 *            端口号
	 * @param num
	 *            第几套(1~6)
	 * @param pers
	 *            (1~6) 柜子编号
	 * @param str
	 *            屏信息
	 * @return
	 */
	public static String pingKuWei(String ip, Integer port, int num, int pers, String str) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			int guizi = 0;
			int guizi2 = 0;
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(0);
			p.write(ji(num));
			p.write(0x3B);
			p.write(0x1C);
			p.write(0x1D);
			switch (pers) {
			case 5:
				break;
			case 6:
				guizi2 = 0x40;
				break;
			case 3:
				guizi2 = 0x80;
				break;
			case 4:
				guizi2 = 0xC0;
				break;
			case 1:
				guizi = 0x01;
				break;
			case 2:
				guizi = 0x01;
				guizi2 = 0x40;
				break;
			default:
				break;
			}
			p.write(guizi);
			p.write(guizi2);
			// 发送屏幕信息
			PrintWriter p1 = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			p1.write(str);// 要发送的内容
			p1.flush();
			p1.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 发送二维码的方法
	 * 
	 * @param ip
	 * @param num
	 *            第几套(1~6)
	 * @param port
	 *            端口号
	 * @param str
	 *            内容
	 * @return
	 */
	public static String sendTowMa_1(String ip, int num, Integer port, String str) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			// 发送屏幕信息
			PrintWriter p1 = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			p1.write(0);
			p1.write(ji(num));
			p1.write(0x3B);
			p1.write(0x2C);
			p1.write(0x2D);
			p1.write(Util.code_1(str, 36));// 要发送的内容
			p1.flush();
			p1.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	public static void cenl(String ip, Integer port, String str) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			// 发送屏幕信息
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(ByteUtils.hexStr2Byte(str));// 要发送的内容
			p.flush();
			p.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 确认后刷新屏幕的内容(1、第一页 2、第二页 3、第三页)
	 * 
	 * @param ip
	 * @param port
	 *            端口号
	 * @param num
	 *            第几套(1~6)
	 * @param pers(1~6)
	 *            柜子编号
	 * @param str
	 *            屏信息
	 * @param yeshu
	 *            页数
	 * @return
	 */
	public static String querenpingKuWei1(String ip, Integer port, int num, int pers, String str, Integer yeshu) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			// 发送屏幕信息
			PrintWriter p1 = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			p1.write(0);
			p1.write(ji(num));
			p1.write(0x3B);
			if (yeshu == 1) {
				p1.write(0x3c);
				p1.write(0x3d);
			} else if (yeshu == 2) {
				p1.write(0x4c);
				p1.write(0x4d);
			} else if (yeshu == 3) {
				p1.write(0x5c);
				p1.write(0x5d);
			} else {
				p1.write(0x3c);
				p1.write(0x3d);
			}
			p1.write(guiding(pers));
			p1.write(str);// 要发送的内容
			p1.flush();
			p1.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 确认后刷新屏幕的内容(4C4D第二页)
	 * 
	 * @param ip
	 * @param port
	 *            端口号
	 * @param num
	 *            第几套(1~6)
	 * @param pers(1~6)
	 *            柜子编号
	 * @param str
	 *            屏信息第3页
	 * @return
	 */
	// public static String querenpingKuWei(String ip, Integer port,int num, int
	// pers, String str) {
	// // TODO Auto-generated method stub
	// Socket s;
	// try {
	// s = new Socket(ip, port);
	// //发送屏幕信息
	// PrintWriter p1 = new PrintWriter(new
	// OutputStreamWriter(s.getOutputStream()));
	// p1.write(0);
	// p1.write(ji(num));
	// p1.write(0x3B);
	// p1.write(0x4c);
	// p1.write(0x4d);
	// p1.write(guiding(pers));
	// p1.write(str);//要发送的内容
	// p1.flush(); p1.close();
	// s.close();
	// return "true";
	// } catch (Exception e) {
	// e.printStackTrace();
	// return "操作失败!";
	// }
	// }

	/**
	 * 返回主页面的方法
	 * 
	 * @param ip
	 * @param port
	 *            端口号
	 * @param num
	 *            第几套(1~6)
	 * @return
	 */
	public static String backTowMa(String ip, Integer port, int num) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			// 发送屏幕信息
			PrintStream p1 = new PrintStream(s.getOutputStream());
			p1.write(0);
			p1.write(ji(num));
			p1.write(0x3B);
			p1.write(0x2A);
			p1.write(0x2A);
			p1.write(0xA2);
			p1.write(0xA2);
			p1.flush();
			p1.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 发送库位码
	 * 
	 * @param ip
	 * @param port
	 * @param num
	 *            第几套(1~6)
	 * @param pers
	 * @param str
	 * @return
	 */
	public static String sendKuWeiCode(String ip, Integer port, int num, int pers, String str) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			// 发送屏幕信息
			PrintWriter p1 = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			p1.write(0);
			p1.write(ji(num));
			p1.write(0x3B);
			p1.write(0x6c);
			p1.write(guidingCode(pers));
			p1.write(str);// 要发送的内容
			p1.flush();
			p1.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 发送开灯或关灯指令(1b=>FE开/FF关)
	 * 
	 * @param ip
	 * @param port
	 *            端口
	 * @param b
	 *            开灯&开始闪烁:true/灭灯&结束闪烁:false
	 * @param b1
	 *            开灯:true/灯闪烁:false
	 * @param num
	 *            第几套(1~6)
	 * @param guiNum
	 *            库位号
	 * @param typeNum
	 *            灯类型
	 * @return
	 */
	public static String openColorLight(String ip, Integer port, boolean b, boolean b1, int num, int guiNum,
			int typeNum) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(0);
			p.write(deng(num, guiNum));
			if (b1)
				p.write(0x1B);// 开关
			else
				p.write(0x2B);// 闪
			p.write(typeNum);// 灯指令
			if (b)
				p.write(0xFE);// 开灯
			else
				p.write(0xFF);// 灭灯
			p.flush();
			p.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 发送关灯指令(0C=>FF关)
	 * 
	 * @param ip
	 * @param port
	 *            端口
	 * @param num
	 *            第几套(1~6)
	 * @param guiNum
	 *            库位号
	 * @return
	 */
	public static String shanColorLight(String ip, Integer port, boolean b, int num, int guiNum, int typeNum) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(0);
			p.write(deng(num, guiNum));
			p.write(0x0C);// 关全部灯指令
			p.write(0xFF);//
			p.flush();
			p.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 发送开灯或关灯指令(1b=>FE开/FF关)
	 * 
	 * @param ip
	 * @param port
	 *            端口
	 * @param b
	 *            开灯:true/灭灯:false
	 * @param b1
	 *            开关灯:true/闪灯&不闪灯:false
	 * @param num
	 *            第几套(1~6)
	 * @param guiNum
	 *            库位号
	 * @param typeNum
	 *            灯类型
	 * @return
	 */
	public static String shanColorLight_close(String ip, Integer port, boolean b, boolean b1, int num, int guiNum,
			int typeNum) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			PrintStream p = new PrintStream(s.getOutputStream());
			// closeLight(s, guiNum);//关灯指令
			p.write(0);
			p.write(deng(num, guiNum));
			if (b1)
				p.write(0x1B);// 开关
			else
				p.write(0x2B);// 闪
			p.write(typeNum);// 灯指令
			if (b)
				p.write(0xFE);// 开灯
			else
				p.write(0xFF);// 灭灯
			p.flush();
			p.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 熄灭所有灯
	 * 
	 * @param s
	 * @param num
	 *            第几套(1~6)
	 * @param guiNum
	 */
	public static String closeLight(String ip, Integer port, int num, int guiNum) {
		Socket s;
		try {
			s = new Socket(ip, port);
			PrintStream p = new PrintStream(s.getOutputStream());
			p.write(0);
			p.write(deng(num, guiNum));
			p.write(0X1B);
			p.write(0x0C);// 关全部灯指令
			p.write(0xFF);//
			p.flush();
			p.close();
			s.close();
			Thread.sleep(800);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/**
	 * 库位码指令
	 * 
	 * @param i
	 * @return
	 */
	public static int guidingCode(int i) {
		switch (i) {
		case 1:
			i = 0x5d;
			break;
		case 2:
			i = 0x6d;
			break;
		case 3:
			i = 0x3d;
			break;
		case 4:
			i = 0x4d;
			break;
		case 5:
			i = 0x1d;
			break;
		case 6:
			i = 0x2d;
			break;
		default:
			break;
		}
		return i;
	}

	/**
	 * 屏幕指令
	 * 
	 * @param i
	 * @return
	 */
	public static int guiding(int i) {
		switch (i) {
		case 1:
			i = 5;
			break;
		case 2:
			i = 6;
			break;
		case 3:
			i = 3;
			break;
		case 4:
			i = 4;
			break;
		case 5:
			i = 1;
			break;
		case 6:
			i = 2;
			break;
		default:
			break;
		}
		return i;
	}

	public static int guiDing_1(int i) {
		switch (i) {
		case 1:
			i = 2;
			break;
		case 2:
			i = 1;
			break;
		case 3:
			i = 4;
			break;
		case 4:
			i = 3;
			break;
		case 5:
			i = 6;
			break;
		case 6:
			i = 5;
			break;
		default:
			break;
		}
		return i;
	}

	/**
	 * 根据类型1~4
	 * 
	 * @author LiCong
	 * @param b
	 *            类型 true闪灯/false亮灯
	 * @param typeNum
	 *            类型 1:红/2:绿/3:蓝/4:黄/0：所有灯都熄灭掉
	 * @return 返回对应颜色灯的指令
	 */

}
