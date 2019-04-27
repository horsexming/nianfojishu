package com.task.util;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.task.entity.led.LED;

public class LedSendUtil {
	static final int COLOR_RED = 0x0000FF;// 红色
	static final int COLOR_GREEN = 0x00FF00;// 绿色
	static final int COLOR_YELLOW = 0x00FFFF;// 黄色

	public interface DllLibrary extends StdCallLibrary {

		// 添加节目
		int User_AddProgram(int CardNum, boolean bWaitToEnd, int iPlayTime);

		// 添加文本区
		int User_AddText(int CardNum, User_Text pText, int iProgramIndex);

		// 删除所有节目
		boolean User_DelAllProgram(int CardNum);

		// 发送数据
		boolean User_SendToScreen(int CardNum);

		// // 添加图文区
		// int User_AddBmpZone(int iCardNum, User_Bmp pBmp, int iProgramIndex);

		// boolean User_AddBmp(int iCardNum, int iBmpPartNum, HBITMAP hBitmap,
		// User_MoveSet pMoveSet, int iProgramIndex);
		//
		// boolean User_AddBmpFile(int CardNum, int iBmpPartNum,
		// String strFileName, User_MoveSet pMoveSet, int iProgramIndex);

		// // 添加RTF区
		// int User_AddRTF(int CardNum, User_RTF pRTFt, int iProgramIndex);
		//
		// // 添加单行文本区
		// int User_AddSingleText(int CardNum, User_SingleText pSingleText,
		// int iProgramIndex);
		//
		// // 添加时间区
		// int User_AddTime(int CardNum, User_DateTime pDateTime, int
		// iProgramIndex);
		//
		// 添加计时区
		int User_AddTimeCount(int CardNum, User_Timer pTimeCount,
				int iProgramIndex);

		//
		// // 添加温度区
		// int User_AddTemperature(int CardNum, User_Temperature pTemperature,
		// int iProgramIndex);
		//
		// // 删除节目
		// boolean User_DelProgram(int CardNum, int iProgramIndex);

		// // 发送节目文件和索引文件
		// boolean User_SendFileToScreen(int CardNum, String pSendPath,
		// String pIndexPath);

		// // 关闭屏幕
		// boolean User_CloseScreen(int iCardNum);
		//
		// // 打开屏幕
		// boolean User_OpenScreen(int iCardNum);

		// 校正板卡的时间
		boolean User_AdjustTime(int CardNum);

		// // 实时发送数据
		// boolean User_RealtimeConnect(int CardNum); // 建立连接
		//
		// boolean User_RealtimeSendData(int CardNum, int x, int y, int iWidth,
		// int iHeight, WinDef.HBITMAP hBitmap); // 发送数据
		//
		// boolean User_RealtimeDisConnect(int CardNum); // 断开连接
		//
		// boolean User_RealtimeSendBmpData(int CardNum, int x, int y, int
		// iWidth,
		// int iHeight, String strFileName); // 发送数据
		//
		// boolean User_ImportIniFile(String pPath, int iLength);// 导入ini配置文件
		//
		// // 亮度调节
		// boolean User_SetScreenLight(int CardNum, int iLightDegreen);

		/**
		 * 节目区域参数
		 */
		public class User_PartInfo extends Structure {

			public int iX; // 窗口的起点X
			public int iY; // 窗口的起点Y
			public int iWidth; // 窗体的宽度
			public int iHeight; // 窗体的高度
			public int iFrameMode; // 边框的样式
			public int FrameColor; // 边框颜色

		}

		/**
		 * 字体参数
		 */
		public class User_FontSet extends Structure {

			public String strFontName; // 字体的名称
			public int iFontSize; // 字体的大小
			public boolean bFontBold; // 字体是否加粗
			public boolean bFontItaic; // 字体是否是斜体
			public boolean bFontUnderline; // 字体是否带下划线
			public int colorFont; // 字体的颜色
			public int iAlignStyle; // 对齐方式
			// 0－ 左对齐
			// 1－居中
			// 2－右对齐
			public int iVAlignerStyle; // 上下对齐方式
			// 0-顶对齐
			// 1-上下居中
			// 2-底对齐
			public int iRowSpace; // 行间距

		}

		/**
		 * 计时窗口
		 */
		public class User_Timer extends Structure {

			public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
			public int BkColor; // 背景颜色
			public User_FontSet FontInfo = new User_FontSet(); // 字体设置
			public int ReachTimeYear; // 到达年
			public int ReachTimeMonth; // 到达月
			public int ReachTimeDay; // 到达日
			public int ReachTimeHour; // 到达时
			public int ReachTimeMinute; // 到达分
			public int ReachTimeSecond; // 到达秒
			public boolean bDay; // 是否显示天 0－不显示 1－显示
			public boolean bHour; // 是否显示小时
			public boolean bMin; // 是否显示分钟
			public boolean bSec; // 是否显示秒
			public boolean bMulOrSingleLine; // 单行还是多行
			public String chTitle; // 添加显示文字

		}

		/**
		 * 温度窗口
		 */
		// public class User_Temperature extends Structure {
		//
		// public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
		// public int BkColor; // 背景颜色
		// public User_FontSet FontInfo = new User_FontSet(); // 字体设置
		// public String chTitle; // 标题
		// public int DisplayType; // 显示格式：0－度 1－C
		//
		// }

		/**
		 * 日期时间窗口
		 */
		// public class User_DateTime extends Structure {
		//
		// public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
		// public int BkColor; // 背景颜色
		// public User_FontSet FontInfo = new User_FontSet(); // 字体设置
		// public int iDisplayType; // 显示风格
		// public String chTitle; // 添加显示文字
		// public boolean bYearDisType; // 年份位数0 －4；1－2位
		// public boolean bMulOrSingleLine; // 单行还是多行
		// public boolean bYear; // 是否显示年
		// public boolean bMouth; // 是否显示月
		// public boolean bDay; // 是否显示日
		// public boolean bWeek; // 是否显示星期
		// public boolean bHour; // 是否显示小时
		// public boolean bMin; // 是否显示分钟
		// public boolean bSec; // 是否显示秒钟
		//
		// }

		/**
		 * 图文框
		 */
		// public class User_Bmp extends Structure {
		//
		// public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
		//
		// }

		/**
		 * 单行文本框
		 */
		// public class User_SingleText extends Structure {
		//
		// public String chContent; // 显示内容
		// public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
		// public int BkColor; // 背景颜色
		// public User_FontSet FontInfo = new User_FontSet(); // 字体设置
		// public User_MoveSet MoveSet = new User_MoveSet(); // 动作方式设置
		//
		// }

		/**
		 * 文本框
		 */
		public class User_Text extends Structure {

			public String chContent; // 显示内容
			public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
			public int BkColor; // 背景颜色
			public User_FontSet FontInfo = new User_FontSet(); // 字体设置
			public User_MoveSet MoveSet = new User_MoveSet(); // 动作方式设置

		}

		/**
		 * RTF文件
		 */
		// public class User_RTF extends Structure {
		//
		// public String strFileName; // RTF文件名
		// public User_PartInfo PartInfo = new User_PartInfo(); // 分区信息
		// public User_MoveSet MoveSet = new User_MoveSet(); // 动作方式设置
		//
		// }

		/**
		 * 动画方式设置
		 */
		public class User_MoveSet extends Structure {

			public int iActionType; // 节目变换方式
			public int iActionSpeed; // 节目的播放速度
			public boolean bClear; // 是否需要清除背景
			public int iHoldTime; // 在屏幕上停留的时间
			public int iClearSpeed; // 清除显示屏的速度
			public int iClearActionType; // 节目清除的变换方式
			public int iFrameTime; // 每帧时间

		}

		/**
		 * 重新加载配置文件
		 */
		void User_ReloadIniFile(String strEQ2008_Dll_Set_Path);
	}

	static int m_iCardNum = 1;
	static int m_iProgramIndex = -1;
	static int m_iProgramCount = 0;
	static int iActionType = 2;// 移动方式
	static int iAlignStyle = 0;// 对齐方式(0－左对齐 1－居中 2－右对齐)
	static int iHoldTime = 0;// 暂停时间
	static int iHeight = 16;// 高度
	static int iWidth = 64;// 宽度
	static int colorFont = 0x0000FF;// 颜色
	static int iFontSize = 10;// 字体
	static int iX = 0;// 
	static int iY = 0;// 

	static DllLibrary m_DllLibrary = null;
	// static String m_strUserPath = System.getProperty("user.dir");// 获得项目路径
	static String m_strUserPath = "";// 获得项目路径

	/***
	 * 创建文本区域
	 * 
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @return
	 * @throws Exception
	 */
	public static void OnCreatText(String chContent) throws Exception {
		User_AdjustTime(m_iCardNum);// 校时

		DllLibrary.User_Text Text = new DllLibrary.User_Text();

		Text.BkColor = 0;
		Text.chContent = chContent;
		// 分区设置
		Text.PartInfo.FrameColor = 0;
		Text.PartInfo.iFrameMode = 0;
		Text.PartInfo.iHeight = iHeight;
		Text.PartInfo.iWidth = iWidth;
		Text.PartInfo.iX = iX;
		Text.PartInfo.iY = iY;
		// 设置
		Text.FontInfo.bFontBold = false;
		Text.FontInfo.bFontItaic = false;
		Text.FontInfo.bFontUnderline = false;
		Text.FontInfo.colorFont = colorFont;
		Text.FontInfo.iFontSize = iFontSize;
		Text.FontInfo.strFontName = "宋体";
		Text.FontInfo.iAlignStyle = iAlignStyle;// 对齐方式(0－左对齐 1－居中 2－右对齐)
		Text.FontInfo.iVAlignerStyle = 1;// 上下对齐方式(0-顶对齐 1-上下居中 2-底对齐)
		Text.FontInfo.iRowSpace = 1;
		// 移动设置
		Text.MoveSet.bClear = false;
		Text.MoveSet.iActionSpeed = 12;
		Text.MoveSet.iActionType = iActionType;// 移动方式
		Text.MoveSet.iHoldTime = iHoldTime;// 暂停时间
		Text.MoveSet.iClearActionType = 0;
		Text.MoveSet.iClearSpeed = 10;
		Text.MoveSet.iFrameTime = 20;

		if (-1 == m_DllLibrary.User_AddText(m_iCardNum, Text, m_iProgramIndex)) {
			throw new Exception("添加文本失败!");
		}
	}

	static void createDll() {
		if (m_strUserPath != null && "".equals(m_strUserPath)) {
			try {
				m_strUserPath = ServletActionContext.getServletContext()
						.getRealPath("/WEB-INF");
			} catch (Exception e) {
				// D:\开发工具\apache-tomcat-6.0.41-windows-x86\apache-tomcat-6.0.41\bin
				// D:\开发工具\apache-tomcat-6.0.41-windows-x86\apache-tomcat-6.0.41\webapps\HHTask\WEB-INF
				// E:\work\PEBS\HHTask\WebRoot\WEB-INF\res
				m_strUserPath = System.getProperty("user.dir");// 本地测试
				m_strUserPath = m_strUserPath
						+ "\\..\\webapps\\HHTask\\WEB-INF";
			}
		}
		if (m_DllLibrary == null) {
			String strDllFileName = m_strUserPath + "\\res\\EQ2008_Dll";
			String strEQ2008_Dll_Set_Path = m_strUserPath
					+ "\\res\\EQ2008_Dll_Set.ini";
			m_DllLibrary = (DllLibrary) Native.loadLibrary(strDllFileName,
					DllLibrary.class);
			m_DllLibrary.User_ReloadIniFile(strEQ2008_Dll_Set_Path);
		}
	}

	/***
	 *函数：添加文本窗
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @return
	 */
	public static String OnAddtext(int CardNum, String chContent,
			int pActionType, int ledId) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				// 创建文本区
				iActionType = pActionType;
				OnCreatText(chContent);
				// //(3)、添加分区窗口到节目,每个节目可以添加多个分区，但分区位置不能重叠
				// OnAddText(); //文本窗操作演示
				// OnAddRTF(); //RTF窗操作演示
				// OnAddSingleText();//单行文本窗操作演示
				// OnAddbmp(); //图片窗操作演示
				// OnAddTime(); //时间窗操作演示
				// OnAjusttime();
				// OnAddTimeCount(); //计时窗操作演示
				// OnAddTemperature();//温度窗操作演示
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}

	/***
	 *函数：添加文本窗
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @return
	 */
	public static String OnAddtext(int CardNum, String chContent, int ledId) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				// 创建文本区
				OnCreatText(chContent);
				// //(3)、添加分区窗口到节目,每个节目可以添加多个分区，但分区位置不能重叠
				// OnAddText(); //文本窗操作演示
				// OnAddRTF(); //RTF窗操作演示
				// OnAddSingleText();//单行文本窗操作演示
				// OnAddbmp(); //图片窗操作演示
				// OnAddTime(); //时间窗操作演示
				// OnAjusttime();
				// OnAddTimeCount(); //计时窗操作演示
				// OnAddTemperature();//温度窗操作演示
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	/***
	 *函数：添加文本窗（支持宽度和高度）
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @return
	 */
	public synchronized static String OnAddtext(int CardNum, String chContent,
			int pActionType, int ledId, int w, int h) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				// 创建文本区
				iActionType = pActionType;
				iWidth = w;
				iHeight = h;
				OnCreatText(chContent);
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();

			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}

	}

	/***
	 *函数：添加文本窗（支持宽度和高度、字体颜色和字体大小）
	 * 
	 * @author Li_Cong
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @param color
	 *            字体颜色,
	 * @param size
	 *            字体大小(8~12)合适
	 * @return
	 */
	public static String OnAddtext(int CardNum, String chContent,
			int pActionType, int ledId, int w, int h, int color, int size) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				iY = 0;
				// 创建文本区
				iActionType = pActionType;
				iWidth = w;
				iHeight = h;
				colorFont = color;
				iFontSize = size;
				iAlignStyle = 1;
				OnCreatText(chContent);
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	/***
	 *函数：添加文本窗（支持宽度和高度、字体颜色和字体大小）
	 * 
	 * @author Li_Cong
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @param color
	 *            字体颜色,
	 * @param size
	 *            字体大小(8~12)合适
	 * @return
	 */
	public static String OnAddtextUp(int CardNum, String chContent,
			int pActionType, int ledId, int w, int h, int color, int size) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				iY = 0;
				// 创建文本区
				iActionType = pActionType;
				iHoldTime = 20;
				iWidth = w;
				iHeight = h;
				colorFont = color;
				iFontSize = size;
				OnCreatText(chContent);
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	public static String OnAddtextUp(LED led, String chContent) {
		try {
			if (led != null) {
				m_iCardNum = led.getNumber();
				createDll();

				// (1)、删除所有节目
				OnDelAllProgram();
				// (2)、添加节目(可以添加多个节目)
				for (int i = 0; i < 1; i++) {
					OnAddProgram();// 添加节目
					iY = 0;
					// 创建文本区
					iActionType = led.getIactionType();
					iAlignStyle = led.getIalignStyle();
					iHoldTime = led.getIholdTime();
					iWidth = led.getWidth().intValue();
					iHeight = led.getHigth().intValue();
					colorFont = led.getColor();
					iFontSize = led.getFontSize();
					OnCreatText(chContent);
				}

				// //(3)、发送节目到显示屏
				OnSendToScreen();
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "发送失败!";
	}

	/***
	 *函数：添加文本窗（倒计时&单条数据左移）
	 * 
	 * @author Li_Cong
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @param color
	 *            字体颜色,
	 * @param size
	 *            字体大小(8~12)合适
	 * @return
	 */
	public static String OnAddtextLeft(int CardNum, String[] chContents,
			int pActionType, int ledId, int w, int h, int color, int size) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目
			OnAddProgram();// 添加节目

			// 创建倒计时区域
			String[] oneStr = chContents[0].split(";");
			OnAddTimeCount(Util.StringToDate(oneStr[0], "yyyy-MM-dd HH:ss:mm"),
					oneStr[1]);

			// 创建文本区
			iActionType = pActionType;
			iWidth = w;
			iHeight = h;
			iFontSize = 10;

			String[] twoStr = chContents[1].split(";");
			if ("COLOR_GREEN".equals(twoStr[0])) {
				colorFont = COLOR_GREEN;
			} else if ("COLOR_RED".equals(twoStr[0])) {
				colorFont = COLOR_RED;
			} else if ("COLOR_YELLOW".equals(twoStr[0])) {
				colorFont = COLOR_YELLOW;
			}
			iY = 16;
			OnCreatText(twoStr[1]);// 产品信息

			iY = 32;
			String[] sStr = chContents[2].split(";");
			if ("COLOR_GREEN".equals(sStr[0])) {
				colorFont = COLOR_GREEN;
			} else if ("COLOR_RED".equals(sStr[0])) {
				colorFont = COLOR_RED;
			} else if ("COLOR_YELLOW".equals(sStr[0])) {
				colorFont = COLOR_YELLOW;
			}
			OnCreatText(sStr[1]);// 设备状态

			iY = 48;
			String[] s2Str = chContents[3].split(";");
			if ("COLOR_GREEN".equals(s2Str[0])) {
				colorFont = COLOR_GREEN;
			} else if ("COLOR_RED".equals(s2Str[0])) {
				colorFont = COLOR_RED;
			} else if ("COLOR_YELLOW".equals(s2Str[0])) {
				colorFont = COLOR_YELLOW;
			}
			OnCreatText(s2Str[1]);// 穿戴规范

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	/***
	 *函数：添加文本窗（倒计时&多条数据上移）
	 * 
	 * @author Li_Cong
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @param color
	 *            字体颜色,
	 * @param size
	 *            字体大小(8~12)合适
	 * @return
	 */
	public static String OnAddtextUp(int CardNum, String[] chContents,
			int pActionType, int ledId, int w, int h, int color, int size) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目
			OnAddProgram();// 添加节目

			// 创建倒计时区域
			colorFont = 0x0000FF;
			String[] oneStr = chContents[0].split(";");
			OnAddTimeCount(Util.StringToDate(oneStr[0], "yyyy-MM-dd HH:ss:mm"),
					oneStr[1]);

			// 创建文本区
			iActionType = pActionType;
			iHoldTime = 20;// 暂停时间
			iWidth = w;
			iHeight = h - 16;// (被倒计时占用了16)
			iFontSize = 12;
			colorFont = color;
			String twoStr = chContents[1];
			iY = 16;
			OnCreatText(twoStr);// 产品信息

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	public static String OnAddtextUp(LED led, String[] chContents) {
		try {
			if (led != null) {
				m_iCardNum = led.getNumber();
				createDll();

				// (1)、删除所有节目
				OnDelAllProgram();
				// (2)、添加节目
				OnAddProgram();// 添加节目

				User_AdjustTime(m_iCardNum);// 校时
				// 创建倒计时区域
				String[] oneStr = chContents[0].split(";");
				OnAddTimeCount(Util.StringToDate(oneStr[0],
						"yyyy-MM-dd HH:ss:mm"), oneStr[1]);

				// 创建文本区
				iActionType = led.getIactionType();
				iAlignStyle = led.getIalignStyle();
				iHoldTime = led.getIholdTime();
				iWidth = led.getWidth().intValue();
				iHeight = led.getHigth().intValue() - 16;
				colorFont = led.getColor();
				iFontSize = led.getFontSize();
				String twoStr = chContents[1];
				iY = 16;
				OnCreatText(twoStr);// 产品信息

				// //(3)、发送节目到显示屏
				OnSendToScreen();
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "发送失败!";
	}

	/***
	 *函数：添加文本窗（支持宽度、高度和字体颜色）
	 * 
	 * @author Li_Cong
	 * 
	 * @param CardNum
	 *            控制卡地址,对应ip
	 * @param chContent
	 *            内容
	 * @param iActionType
	 *            移动方式(1=固定；2=左移)
	 * @param ledId
	 *            屏幕id,用于保存log
	 * @param w
	 *            屏宽,
	 * @param h
	 *            屏高,
	 * @param color
	 *            字体颜色,
	 * @return
	 */
	public static String OnAddtext(int CardNum, String chContent,
			int pActionType, int ledId, int w, int h, int color) {
		try {
			m_iCardNum = CardNum;
			createDll();

			// (1)、删除所有节目
			OnDelAllProgram();
			// (2)、添加节目(可以添加多个节目)
			for (int i = 0; i < 1; i++) {
				OnAddProgram();// 添加节目
				// 创建文本区
				iActionType = pActionType;
				iWidth = w;
				iHeight = h;
				colorFont = color;
				OnCreatText(chContent);
			}

			// //(3)、发送节目到显示屏
			OnSendToScreen();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败!";
		}
	}

	// 函数：添加RTF文件
	// public static void OnAddrtf() {
	//
	// boolean result = m_DllLibrary.User_DelAllProgram(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_DelAllProgram error");
	// return;
	// }
	//
	// m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, true, 10);
	//
	// System.out.println(m_iProgramIndex);
	// if (m_iProgramIndex < 0) {
	//
	// System.out.println("proindex error m_iProgramIndex = "
	// + m_iProgramIndex);
	// return;
	// }
	//
	// String strFileName = System.getProperty("user.dir");
	//
	// strFileName += "\\EQ2008_RTF.rtf";
	//
	// DllLibrary.User_RTF RTF = new DllLibrary.User_RTF();
	//
	// RTF.strFileName = strFileName;
	//
	// RTF.PartInfo.FrameColor = 0;
	// RTF.PartInfo.iFrameMode = 0;
	// RTF.PartInfo.iHeight = 32;
	// RTF.PartInfo.iWidth = 64;
	// RTF.PartInfo.iX = 0;
	// RTF.PartInfo.iY = 0;
	//
	// RTF.MoveSet.bClear = false;
	// RTF.MoveSet.iActionSpeed = 0;
	// RTF.MoveSet.iActionType = 20;
	// RTF.MoveSet.iHoldTime = 10;
	// RTF.MoveSet.iClearActionType = 0;
	// RTF.MoveSet.iClearSpeed = 4;
	// RTF.MoveSet.iFrameTime = 10;
	//
	// if (-1 == m_DllLibrary.User_AddRTF(m_iCardNum, RTF, m_iProgramIndex)) {
	// System.out.println("添加RTF失败!");
	// return;
	//
	// } else {
	// System.out.println("添加RTF成功！");
	// }
	//
	// result = m_DllLibrary.User_SendToScreen(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_SendToScreen error");
	// return;
	// } else {
	// System.out.println("User_SendToScreen sucess");
	// return;
	// }
	//
	// }

	// 函数：添加单行文本
	// public static void OnAddsinglelinetext() {
	// boolean result = m_DllLibrary.User_DelAllProgram(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_DelAllProgram error");
	// return;
	// }
	//
	// m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, true, 10);
	//
	// System.out.println(m_iProgramIndex);
	// if (m_iProgramIndex < 0) {
	//
	// System.out.println("proindex error m_iProgramIndex = "
	// + m_iProgramIndex);
	// return;
	// }
	//
	// DllLibrary.User_SingleText SingleText = new DllLibrary.User_SingleText();
	//
	// SingleText.BkColor = 0;
	// SingleText.chContent = "欢迎使用EQ2008型控制卡动态库!";
	// SingleText.PartInfo.iFrameMode = 0;
	// SingleText.PartInfo.iHeight = 32;
	// SingleText.PartInfo.iWidth = 64;
	// SingleText.PartInfo.iX = 0;
	// SingleText.PartInfo.iY = 0;
	// SingleText.FontInfo.bFontBold = false;
	// SingleText.FontInfo.bFontItaic = false;
	// SingleText.FontInfo.bFontUnderline = false;
	// SingleText.FontInfo.colorFont = 0xFF;
	// SingleText.FontInfo.iFontSize = 12;
	// SingleText.PartInfo.FrameColor = 0xFF;
	// SingleText.FontInfo.strFontName = "宋体";
	// SingleText.MoveSet.bClear = false;
	// SingleText.MoveSet.iActionSpeed = 1;
	// SingleText.MoveSet.iActionType = 20;
	// SingleText.MoveSet.iHoldTime = 0;
	// SingleText.MoveSet.iClearActionType = 0;
	// SingleText.MoveSet.iClearSpeed = 1;
	// SingleText.MoveSet.iFrameTime = 20;
	//
	// if (-1 == m_DllLibrary.User_AddSingleText(m_iCardNum, SingleText,
	// m_iProgramIndex)) {
	// System.out.println("添加单行文本失败");
	// return;
	// } else {
	// System.out.println("添加单行文本成功!");
	// }
	//
	// result = m_DllLibrary.User_SendToScreen(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_SendToScreen error");
	// return;
	// } else {
	// System.out.println("User_SendToScreen sucess");
	// }
	//
	// }

	// 函数：添加图片
	// public static void OnAddbmp() {
	// boolean result = m_DllLibrary.User_DelAllProgram(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_DelAllProgram error");
	// return;
	// }
	//
	// m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, true, 10);
	//
	// System.out.println(m_iProgramIndex);
	// if (m_iProgramIndex < 0) {
	//
	// System.out.println("proindex error m_iProgramIndex = "
	// + m_iProgramIndex);
	// return;
	// }
	//
	// DllLibrary.User_MoveSet MoveSet = new DllLibrary.User_MoveSet();
	//
	// // CBitmap bitmap;
	//
	// DllLibrary.User_Bmp BmpZone = new DllLibrary.User_Bmp();
	//
	// int iBMPZoneNum = 0;
	//
	// BmpZone.PartInfo.iX = 0;
	// BmpZone.PartInfo.iY = 0;
	// BmpZone.PartInfo.iHeight = 32;
	// BmpZone.PartInfo.iWidth = 64;
	// BmpZone.PartInfo.FrameColor = 0xFF00;
	// BmpZone.PartInfo.iFrameMode = 75;
	//
	// MoveSet.bClear = true;
	// MoveSet.iActionSpeed = 4;
	// MoveSet.iActionType = 0;
	// MoveSet.iHoldTime = 50;
	// MoveSet.iClearActionType = 0;
	// MoveSet.iClearSpeed = 4;
	// MoveSet.iFrameTime = 10;
	//
	// iBMPZoneNum = m_DllLibrary.User_AddBmpZone(m_iCardNum, BmpZone,
	// m_iProgramIndex);
	//
	// // bitmap.LoadBitmap(IDB_BITMAP1);
	// //
	// if(!dlllibrary.User_AddBmp(m_iCardNum,iBMPZoneNum,(HBITMAP)bitmap,&MoveSet,m_iProgramIndex))
	// // {
	// // System.out.println("添加图片失败1!");
	// // }
	//
	// if (!m_DllLibrary.User_AddBmpFile(m_iCardNum, iBMPZoneNum, ".\\32.bmp",
	// MoveSet, m_iProgramIndex)) {
	// System.out.println("添加图片失败2!");
	// return;
	// } else {
	// System.out.println("添加图片成功！");
	// }
	//
	// result = m_DllLibrary.User_SendToScreen(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_SendToScreen error");
	// return;
	// } else {
	// System.out.println("User_SendToScreen sucess");
	// }
	// }

	// 函数：添加时间
	// public static void OnAddtime() {
	// boolean result = m_DllLibrary.User_DelAllProgram(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_DelAllProgram error");
	// return;
	// }
	//
	// m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, true, 10);
	//
	// System.out.println(m_iProgramIndex);
	// if (m_iProgramIndex < 0) {
	//
	// System.out.println("proindex error m_iProgramIndex = "
	// + m_iProgramIndex);
	// return;
	// }
	//
	// DllLibrary.User_DateTime DateTime = new DllLibrary.User_DateTime();
	//
	// DateTime.bDay = false;
	// DateTime.bHour = true;
	// DateTime.BkColor = 0;
	// DateTime.bMin = true;
	// DateTime.bMouth = false;
	// DateTime.bMulOrSingleLine = true;
	// DateTime.bSec = true;
	// DateTime.bWeek = false;
	// DateTime.bYear = false;
	// DateTime.bYearDisType = false;
	// DateTime.chTitle = "";
	//
	// DateTime.PartInfo.iFrameMode = 0;
	// DateTime.iDisplayType = 2;
	// DateTime.PartInfo.FrameColor = 0xFFFF;
	// DateTime.PartInfo.iHeight = 32;
	// DateTime.PartInfo.iWidth = 64;
	// DateTime.PartInfo.iX = 0;
	// DateTime.PartInfo.iY = 0;
	// DateTime.FontInfo.bFontBold = false;
	// DateTime.FontInfo.bFontItaic = false;
	// DateTime.FontInfo.bFontUnderline = false;
	// DateTime.FontInfo.colorFont = 0xFF;
	// DateTime.FontInfo.iAlignStyle = 1;
	// DateTime.FontInfo.iFontSize = 12;
	// DateTime.FontInfo.strFontName = "宋体";
	//
	// if (-1 == m_DllLibrary.User_AddTime(m_iCardNum, DateTime,
	// m_iProgramIndex)) {
	// System.out.println("添加时间失败！");
	// } else {
	// System.out.println("添加时间成功");
	// }
	//
	// result = m_DllLibrary.User_SendToScreen(m_iCardNum);
	// if (result == false) {
	//
	// System.out.println("User_SendToScreen error");
	// return;
	// } else {
	// System.out.println("User_SendToScreen sucess");
	// }
	//
	// }

	// 建立连接
	// public static void OnRealtimeSendbmpData() {
	// // 建立连接
	// boolean reVal = m_DllLibrary.User_RealtimeConnect(m_iCardNum);
	// if (reVal == false) {
	// System.out.println("建立连接失败！");
	// return;
	// } else {
	// System.out.println("建立连接成功！");
	// }
	// // 发送数据
	// reVal = m_DllLibrary.User_RealtimeSendBmpData(m_iCardNum, 0, 0, 64, 32,
	// ".//32.bmp");
	// if (reVal == false) {
	// System.out.println("实时发送调用失败！");
	// return;
	// } else {
	// System.out.println("实时发送调用成功！");
	// }
	// // 暂停5秒，继续发送
	// try {
	// Thread.sleep(5000);// 括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// reVal = m_DllLibrary.User_RealtimeSendBmpData(m_iCardNum, 0, 0, 64, 32,
	// ".//PM.bmp");
	// if (reVal == false) {
	// System.out.println("实时发送调用失败！");
	// return;
	// } else {
	// System.out.println("实时发送调用成功！");
	// }
	//
	// // 断开连接
	// reVal = m_DllLibrary.User_RealtimeDisConnect(m_iCardNum);
	// if (reVal == false) {
	// System.out.println("断开连接调用失败！");
	// return;
	// } else {
	// System.out.println("断开连接调用成功！");
	// }
	//
	// }

	// 函数：删除所有节目
	public static void OnDelAllProgram() throws Exception {
		if (!m_DllLibrary.User_DelAllProgram(m_iCardNum)) {
			throw new Exception("删除节目失败!");
		} else {
			// 提示信息
			m_iProgramCount = 0;
			// System.out.println("请首先添加节目！");
		}
	}

	// 函数：添加节目索引
	public static void OnAddProgram() {
		m_iProgramIndex = m_DllLibrary.User_AddProgram(m_iCardNum, false, 10);
		m_iProgramCount++;
		System.out.println("添加节目" + m_iProgramCount);
	}

	// 函数：添加计时
	public static void OnAddTimeCount(Date showDate, String title) {
		try {
			User_AdjustTime(m_iCardNum);

			DllLibrary.User_Timer TimeCount = new DllLibrary.User_Timer();
			TimeCount.bDay = true;
			TimeCount.bHour = true;
			TimeCount.BkColor = 0;
			TimeCount.bMin = true;
			TimeCount.bMulOrSingleLine = false;
			TimeCount.bSec = true;
			TimeCount.chTitle = title;
			TimeCount.FontInfo.bFontBold = false;
			TimeCount.FontInfo.bFontItaic = false;
			TimeCount.FontInfo.bFontUnderline = false;
			TimeCount.FontInfo.colorFont = 0xFFFFff;
			TimeCount.FontInfo.iAlignStyle = 1;
			TimeCount.FontInfo.iFontSize = 10;
			TimeCount.FontInfo.strFontName = "";
			TimeCount.PartInfo.FrameColor = 0xFF00;
			TimeCount.PartInfo.iFrameMode = 0;
			TimeCount.PartInfo.iHeight = 16;
			TimeCount.PartInfo.iWidth = 128;
			TimeCount.PartInfo.iX = 0;
			TimeCount.PartInfo.iY = 0;
			TimeCount.ReachTimeYear = Integer.parseInt(Util.DateToString(
					showDate, "yyyy"));
			TimeCount.ReachTimeMonth = Integer.parseInt(Util.DateToString(
					showDate, "MM"));
			TimeCount.ReachTimeDay = Integer.parseInt(Util.DateToString(
					showDate, "dd"));
			TimeCount.ReachTimeHour = Integer.parseInt(Util.DateToString(
					showDate, "HH"));
			TimeCount.ReachTimeMinute = Integer.parseInt(Util.DateToString(
					showDate, "ss"));
			TimeCount.ReachTimeSecond = Integer.parseInt(Util.DateToString(
					showDate, "mm"));

			if (-1 == m_DllLibrary.User_AddTimeCount(m_iCardNum, TimeCount,
					m_iProgramIndex)) {
				System.out.println("添加计时失败！");
			} else {
				System.out.println("添加计时成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void User_AdjustTime(int mICardNum) throws Exception {
		if (!m_DllLibrary.User_AdjustTime(m_iCardNum)) {
			throw new Exception("校时失败!");
		} else {
			System.out.println("校时成功!");
		}

	}

	// 函数：发送数据到显示屏
	public static void OnSendToScreen() throws Exception {
		if (!m_DllLibrary.User_SendToScreen(m_iCardNum)) {
			throw new Exception("数据发送失败!");
		} else {
			System.out.println("发送数据成功!");
		}

	}

	public static void main(String[] args) {
		// System.out.println(System.getProperty("java.library.path"));
		// dlllibrary = (DllLibrary) Native.loadLibrary("EQ2008_Dll",
		// DllLibrary.class);
		// OnAddtext();
		// OnAddrtf();
		// OnAddsinglelinetext();
		// OnAddbmp();
		// OnAddtime();
		// Thread thread = Thread.currentThread();
		// thread.sleep(1500);
		// OnRealtimeSendbmpData();

		m_strUserPath = "E:\\work\\PEBS\\HHTask\\WebRoot\\WEB-INF";
		m_strUserPath = "D:\\kaifaUTIL\\apache-tomcat-6.0.41-windows-x86\\apache-tomcat-6.0.41\\webapps\\HHTask\\WEB-INF";
		// System.out.println(OnAddtext(30, "ASM-30", 2, 1, 64, 18, 0xffff,
		// 12));
		/**** 倒计时&单条数据左移 **********/
		// String[] context = { "2016-03-01 00:00:00;ASM-01  ",
		// "COLOR_GREEN;M091187 20160100001 第55工序 气密测试 数量:50 沈建新",
		// "COLOR_YELLOW;设备信息:消声器气密试验台 3005012 状态:正常",
		// "COLOR_GREEN;穿戴规范: 护耳器,工作服,防护手套,护目镜" };
		// LedSendUtil.OnAddtextLeft(1, context, 3, 1, 192, 16, 0x00FF00, 10);

		/**** 倒计时&单条数据左移 **********/
		String[] context2 = {
				"2016-03-10 16:30:00;ASM-63",
				"ASM-63 M090999 20151200007 第45工序 定位焊、焊接（手工焊） 数量:45 冀建军 ASM-63 穿戴规范: 帽子,防护眼镜,护耳器,口罩,工作服,防护手套,工作鞋 ASM-63 设备状态: 6003058:正常" };

		LedSendUtil.OnAddtextUp(0, context2, 5, 1, 192, 64, 0x00FF00, 12);

		// try {
		// LedSendUtil.User_AdjustTime(2);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// LedSendUtil.OnAddtextUp(2, "", 5, 1,
		// 64, 32, 0xffff, 8);
		//
		// LedSendUtil
		// .OnAddtext(
		// 11,
		// "A-11, 52210907, DKBA8.043.5552, 20160200002,  第3工序,  折弯,  数量:128, "
		// +
		// "宋付国 ,   A-11,  设备编号:YT-SZ01, 状态:正常,  CPK=1.06, CP=1.44, Ca=26.27%, A-11,  穿戴规范: 护耳器,工作服,防护手套,",
		// 3, 1, 64, 25, 0xffff, 10);
		//
		// LedSendUtil
		// .OnAddtext(
		// 6,
		// "A-06,52210990,DKBA8.015.7582,20160200002,第1工序,NC冲,数量:80,"
		// +
		// "唐晓洪,A-06,设备编号: YT-SC06,状态:正常 ,CPK=2.88,CP=3.01,Ca=4.27%,A-06,穿戴规范: 护耳器,工作服,防护手套,",
		// 3, 1, 64, 25, 0xffff, 10);
		//
		// LedSendUtil
		// .OnAddtext(
		// 1,
		// "A-01, 52210990, DKBA8.042.6539, 20160200002 , 第1工序,  NC冲,  数量:40PCS ,"
		// +
		// "程三明   , A-01  ,设备编号:YT-SC05, 状态:正常,  CPK=1.06 ,CP=1.44 ,Ca=26.27%, A-01,  穿戴规范: 护耳器,工作服,防护手套, ",
		// 3, 1, 64, 25, 0xffff, 10);

		// LedSendUtil1
		// OnAddtext(43, "15195561654654561654564465", 1, 1);
		// OnAddtext(2, "ASM-04\n张山49\n穿戴衣帽,裤子、鞋子、袜子", 2);

		// System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
		// System.out.println(Class.class.getClass().getResource("/").getPath()
		// );

	}
}
