package com.task.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.Regexp;

import com.sun.jna.platform.win32.WinDef.LONG;
import com.task.entity.IgiftSet;
import com.task.entity.sop.Procard;
import com.task.entity.sop.WaigouPlan;
import com.task.util.Util;

public class Test2 {
	public static void main(String[] args) {
	String str ="123;456\r\n789 901:586&658,895";
	String 	ragex = "[\\r\\n\\t\040:&$!~@^#,]+";
	str =str.replaceAll(ragex, ";");
	System.out.println(str);
		
	}
	public static String haoAddOne(String liuShuiHao){
	    Integer intHao = Integer.parseInt(liuShuiHao.substring(10));
	    intHao++;
	    String strHao = intHao.toString();
	    while (strHao.length() < 3)
	        strHao = "0" + strHao;
	    return strHao;
	}
	public void test() {
		String cod="034";
		System.out.println(cod.replace(";", "；"));
	}
}
