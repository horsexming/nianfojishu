package com.task.test.pm;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class HelloJna {
	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(
				"D:/dllku/EQ2008_Dll.dll", CLibrary.class);

		// printf函数声明
		void printf(String format, Object... args);

		Boolean User_CloseScreen(int CardNum);

		Boolean User_DelAllProgram(int CardNum);
	}

	public static void main(String[] args) {
		// 调用printf打印信息
		System.out.println(CLibrary.Instance.User_CloseScreen(1));
	}
}
