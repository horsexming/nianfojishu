package com.task.test.pm;

import com.sun.jna.Native;

public class Pmtest {
	// 函数声明
	public static native int User_AddProgram(int CardNum, boolean bWaitToEnd,
			int iPlayTime);// 添加节目

	// int User_AddText(int CardNum,User_Text *pText,int iProgramIndex);
	// 函数功能：添加文本区
	// 参数说明：CardNum － 控制卡地址，基数为1，即第一块控制卡地址为1
	// pText － 文本参数表指针，参考【参数表】中9
	// iProgramIndex － 节目索引号
	// 返回值： -1－添加文本区失败，非-1－分区编号
	public static native int User_AddText(int CardNum, User_Text pText,
			int iProgramIndex);

	public static native boolean User_SendToScreen(int CardNum);

	public static native Boolean User_OpenScreen(int CardNum);

	public static native Boolean User_CloseScreen(int CardNum);

	public static void main(String[] args) {
		try {
			Native.register("D:/dllku/EQ2008_Dll.dll");
			User_CloseScreen(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// int num = User_AddProgram(1, true, 1);// 添加节目 得到节目编号
		// // 创建窗口信息
		// User_PartInfo partInfo = new User_PartInfo(0, 0, 64, 32, 0);
		// // 文本信息
		// String chContent = "test123";
		// // 创建文本信息
		// User_Text pText = new User_Text(chContent, partInfo);
		// // 添加文本区
		// int addtext = User_AddText(1, pText, num);
		// // 向控制器发送数据
		// boolean bool = User_SendToScreen(1);
		// System.out.println("添加情况:" + addtext);
		// System.out.println("发送情况:" + bool);
	}
}
