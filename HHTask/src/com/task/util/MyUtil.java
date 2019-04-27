package com.task.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
	public static boolean isValidEngDigitString(String str){
	    // 验证规则
	    String regEx = "[a-zA-Z0-9_-]{0,}";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // 字符串是否与正则表达式相匹配
	    return matcher.matches();
	}
	
	public static String byte2String(byte[] res, int offset, int len) throws UnsupportedEncodingException{
		String s = new String(res, offset, len,"UTF-8");
		return s;
	}
	
	public static byte[] int2byte(int res) {  
		byte[] targets = new byte[4];  

		targets[0] = (byte) (res & 0xff);// 最低位   
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位   
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。   
		return targets;   
	}
	public static byte[] short2byte(short res) {  
		byte[] targets = new byte[2];  
		
		targets[0] = (byte) (res & 0xff);// 最低位   
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		return targets;   
	}

	public static int byte2int(byte[] abytBSComm, int posBin) {
		byte[] res = Arrays.copyOfRange(abytBSComm, posBin, abytBSComm.length);
		return byte2int(res);
	}
	public static int byte2int(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		byte[] res0 = Arrays.copyOf(res, 4);
		int targets = (res0[0] & 0xff) | ((res0[1] << 8) & 0xff00) // | 表示安位或   
				| ((res0[2] << 24) >>> 8) | (res0[3] << 24);
		return targets;   
	}
	public static short byte2Short(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		byte[] res0 = Arrays.copyOf(res, 2);
		short targets = (short) ((res0[0] & 0xff) | ((res0[1] << 8) & 0xff00));
		return targets;   
	}

    public static long ConvertFKTimeToNormalTime(String astrFKTime14)
    {
    	if (astrFKTime14.length() != 14)
    		return 0;
    	int year = Integer.parseInt(astrFKTime14.substring(0, 4));
    	int month = Integer.parseInt(astrFKTime14.substring(4, 6));
    	int day = Integer.parseInt(astrFKTime14.substring(6, 8));
    	int hour = Integer.parseInt(astrFKTime14.substring(8, 10));
    	int minute = Integer.parseInt(astrFKTime14.substring(10, 12));
    	int second = Integer.parseInt(astrFKTime14.substring(12, 14));
    	Calendar c = Calendar.getInstance();
    	c.set(year, month-1, day, hour, minute, second);
    	return c.getTimeInMillis();
    }

	public static String GetFKTimeString14(Date date) {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            return sdf.format(date);
        }
        catch (Exception e)
        {
            return "19700101010000";
        }
	}
	
	public static void Mytest(byte[] res,Calendar c) {
		c.set(Calendar.YEAR, 1999);
		c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2001);
		res = new byte[]{4,5,6};
	}
/*	public static String byte2ShortString(byte[] res) {
		byte[] res0 = Arrays.copyOf(res, 2);
		short targets = (short) ((res0[0] & 0xff) | ((res0[1] << 8) & 0xff00));
		String s = Integer.toBinaryString(targets);
		return Add0(s, 16); 
	}
	
	public static String byte2intString(byte[] res) {
		byte[] res0 = Arrays.copyOf(res, 4);
		int targets = ((res0[0] & 0xff) | ((res0[1] << 8) & 0xff00)
				| ((res0[2] << 24) >>> 8) | (res0[3] << 24));
		String s = Integer.toBinaryString(targets);
		return Add0(s, 32); 
	}
	
	public static String Add0(String res,int len){
		StringBuilder sb = new StringBuilder(res);
		while (sb.length()<len) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}*/

	public static String ConvertFKTimeToNormalTimeString(String astrFKTime14) {
        String strRet = "";

        if (astrFKTime14.length() != 14)
            return strRet;

        strRet = astrFKTime14.substring(0, 4) + "-" +
                astrFKTime14.substring(4, 6) + "-" +
                astrFKTime14.substring(6, 8) + " " +
                astrFKTime14.substring(8, 10) + ":" +
                astrFKTime14.substring(10, 12) + ":" +
                astrFKTime14.substring(12, 14);

        return strRet;
	}

	public static boolean IsNullOrEmptyString(String str) {
		if(str == null) return true;
    	if(str.length() == 0)return true;
    	return false;
	}

	public static boolean IsValidTimeString(String astrVal) {
        if (IsNullOrEmptyString(astrVal))
            return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
        	sdf.parse(astrVal);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }finally {
        	sdf = null;
		}
	}

	public static String TimeToString(Date date) {
        String strRet;
        try
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strRet = sdf.format(date);
            return strRet;
        }
        catch (Exception e)
        {
            return "1970-1-1 1:0:0";
        }
	}
	
	/***
	 * 获得时间(返回日期格式为'yyyy-MM-dd HH:ss:mm'的时间)
	 */
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	/***
	 * 获得时间(返回日期格式为S的时间)
	 */
	public static String getDateTime(String s) {
		return new SimpleDateFormat(s).format(new Date());
	}
}
