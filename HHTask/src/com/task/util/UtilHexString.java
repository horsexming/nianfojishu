package com.task.util;


/***
 * 10进制字符串与16进制字符串相互转换
 * 
 * @author 李聪
 * 
 */
public class UtilHexString {

	/**
	 * 1、字节转10进制
	 */
	public static int byte2Int(byte b) {
		int r = (int) b;
		return r;
	}

	/**
	 * 2、10进制转字节
	 */
	public static byte int2Byte(int i) {
		byte r = (byte) i;
		return r;
	}

	/**
	 * 3、字节数组转16进制字符串
	 */
	public static String bytes2HexString(byte[] b) {
		String r = "";

		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			r += hex.toUpperCase();
		}

		return r;
	}

	/**
	 * 字符转换为字节
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 4、16进制字符串转字节数组
	 */
	public static byte[] hexString2Bytes(String hex) {

		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}

	}

	/**
	 * 5、字节数组转字符串
	 */
	public static String bytes2String(byte[] b) throws Exception {
		String r = new String(b, "UTF-8");
		return r;
	}

	/**
	 * 6、字符串转字节数组
	 */
	public static byte[] string2Bytes(String s) {
		byte[] r = s.getBytes();
		return r;
	}

	/**
	 * 7、16进制字符串转字符串
	 */
	public static String hex2String(String hex) throws Exception {
		String r = bytes2String(hexString2Bytes(hex));
		return r;
	}

	/**
	 * 8、字符串转16进制字符串
	 */
	public static String string2HexString(String s) throws Exception {
		String r = bytes2HexString(string2Bytes(s));
		return r;
	}

	public static void main(String[] args) throws Exception {
		// byte b1 = (byte) 45;
		// System.out.println("1.字节转10进制:" + byte2Int(b1));
		//
		// int i = 89;
		// System.out.println("2.10进制转字节:" + int2Byte(i));
		//
		// byte[] b2 = new byte[] { (byte) 0xFF, (byte) 0x5F, (byte) 0x6,
		// (byte) 0x5A };
		// System.out.println("3.字节数组转16进制字符串:" + bytes2HexString(b2));
		//
		// String s1 = new String("1DA47C");
		// System.out.println("4.16进制字符串转字节数组:"
		// + Arrays.toString(hexString2Bytes(s1)));
		//
		// System.out.println("5.字节数组转字符串:" + bytes2String(b2));
		//
		// System.out.println("6.字符串转字节数组:" +
		// Arrays.toString(string2Bytes(s1)));
		//
		// System.out.println("7.16进制字符串转字符串:" + hex2String(s1));
		//
		// String s2 = new String("Hello!");
		// System.out.println("8.字符串转16进制字符串:" + string2HexString(s2));
		 System.out.println("8.字符串转16进制字符串:" + string2HexString("36095"));
		 System.out.println("8.字符串转16进制字符串:" + hex2String("4E4F2E31343338303436353934393537"));

		System.out.println(charToByteAscii('1'));
		System.out.println(charToByteAscii2('1'));

		System.out.println(byteAsciiToChar(38));
		System.out.println(byteAsciiToChar(31));

		System.out.println(SumStrAscii("t"));

		System.out.println(SumStrAscii("="));
		

        asciiToString(50);//ASCII转换为字符串
        stringToAscii("$");//字符串转换为ASCII码
        stringToAscii("字符串");//字符串转换为ASCII码
        String x = String.valueOf((char)8730);
        String y ="√";
        System.out.println(x+y+x.equals(y));
    
	}

	  /*0-9对应Ascii 48-57

	    *A-Z 65-90

	    *a-z 97-122

	    *第33～126号(共94个)是字符，其中第48～57号为0～9十个阿拉伯数字

	    */
	
	/**
	 * 
	 * 方法一：将char 强制转换为byte
	 * 
	 * @param ch
	 * 
	 * @return
	 */

	public static byte charToByteAscii(char ch) {
		byte byteAscii = (byte) ch;
		return byteAscii;
	}

	/**
	 * 
	 * 方法二：将char直接转化为int，其值就是字符的ascii
	 * 
	 * @param ch
	 * 
	 * @return
	 */

	public static byte charToByteAscii2(char ch) {
		byte byteAscii = (byte) ch;
		return byteAscii;
	}

	/**
	 * 
	 * 同理，ascii转换为char 直接int强制转换为char
	 * 
	 * @param ascii
	 * 
	 * @return
	 */

	public static char byteAsciiToChar(int ascii) {
		char ch = (char) ascii;
		return ch;
	}

	/**
	 * 
	 * 求出字符串的ASCII值和
	 * 
	 * 注意，如果有中文的话，会把一个汉字用两个byte来表示，其值是负数
	 */

	public static int SumStrAscii(String str)
	{
		byte[] bytestr = str.getBytes();
		int sum = 0;
		for (int i = 0; i < bytestr.length; i++)
		{
			sum += bytestr[i];
		}
		return sum;
	}

	/**
	 * ascii转String
	 * @param num
	 */
    public static void asciiToString(int num){
        System.out.println(num +" -> "+(char)num);
    }
    
    /**
     * String转ascii
     * @param str
     */
    public static void stringToAscii(String str){
        char[]chars=str.toCharArray();
        for(int i=0;i<chars.length;i++){
            System.out.println(chars[i]+" -> "+(int)chars[i]);
        }
    }
	
}
