package com.task.ServerImpl.yw;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Title: ConvertNumber.java
 * @Package com.task.ServerImpl.yw
 * @Description: 转换人民币大小写
 * @author 曾建森
 * @date 2012-11-7 上午09:06:16
 * @version V1.0
 */
public class ConvertNumber {

	// 每个数字对应的大写

	private static final String[] num = {

	"零", "壹", "贰", "叁", "肆", "伍",

	"陆", "柒", "捌", "玖",

	};

	// 从低到高排列的单位

	private static final String[] bit = {

	"圆", "拾", "佰", "仟", "万", "拾",

	"佰", "仟", "亿", "拾", "佰", "仟",

	"万", "拾", "佰", "仟", "亿"

	};

	// 金额里面的角和分

	private static final String[] jf = {

	"角", "分"

	};

	/**
	 * 
	 * 处理金额的整数部分,返回"...圆整"
	 * 
	 * @param integer
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */

	public static String praseUpcaseRMB(String integer) throws Exception {

		StringBuilder sbdr = new StringBuilder("");

		int j = integer.length();

		if (j > bit.length) {

			throw new Exception("\n只能处理亿万亿以内的数据(含亿万亿)!");

		}

		char[] rmb = integer.toCharArray();

		for (int i = 0; i < rmb.length; i++) {

			int numLocate = Integer.parseInt("" + rmb[i]); // 大写数字位置

			int bitLocate = j - 1 - i; // 数字单位的位置

			/*
			 * 
			 * 连续大写零只添加一个
			 */

			if (numLocate == 0) {

				if (!sbdr.toString().endsWith(num[0])) {

					sbdr.append(num[numLocate]);

				}

				continue;

			}

			/*
			 * 
			 * 下面的if语句保证
			 * 
			 * 10065004583.05-->壹佰亿陆仟伍佰万肆仟伍佰捌拾叁圆零伍分
			 */

			if (bit[bitLocate].equals("仟")) {

				String s = sbdr.toString();

				if (!s.endsWith(bit[bitLocate + 1]) && s.length() > 0) {

					if (s.endsWith(num[0])) {

						sbdr.deleteCharAt(sbdr.length() - 1);

					}

					sbdr.append(bit[bitLocate + 1]);

				}

			}

			sbdr.append(num[numLocate]);

			sbdr.append(bit[bitLocate]);

		}// end for

		/*
		 * 
		 * 去掉结尾"零"后,补全
		 */

		if (sbdr.toString().endsWith(num[0])) {

			sbdr.deleteCharAt(sbdr.length() - 1);

			sbdr.append("圆整");

		} else {

			sbdr.append("整");

		}

		return sbdr.toString();

	}

	/**
	 * 
	 * 处理带小数的金额,整数部分交由上一个方法处理,小数部分自己处理
	 * 
	 * @param integer
	 * 
	 * @param decimal
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */

	public static String praseUpcaseRMB(String integer, String decimal)
			throws Exception {

		String ret = ConvertNumber.praseUpcaseRMB(integer);

		ret = ret.split("整")[0]; // 处理整数部分

		StringBuilder sbdr = new StringBuilder("");

		sbdr.append(ret);

		char[] rmbjf = decimal.toCharArray();

		for (int i = 0; i < rmbjf.length; i++) {

			int locate = Integer.parseInt("" + rmbjf[i]);

			if (locate == 0) {

				if (!sbdr.toString().endsWith(num[0])) {

					sbdr.append(num[locate]);

				}

				continue;

			}

			sbdr.append(num[locate]);

			sbdr.append(jf[i]);

		}

		return sbdr.toString();

	}

	/**
	 * 
	 * 将double形式的字符串(有两位小数或无小数)转换成人民币的大写格式
	 * 
	 * @param doubleStr
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */

	public static String doChangeRMB(String doubleStr) throws Exception {

		String result = null;

		if (doubleStr.contains(".")) { // 金额带小数

			int dotloc = doubleStr.indexOf(".");

			int strlen = doubleStr.length();

			String integer = doubleStr.substring(0, dotloc);

			String decimal = doubleStr.substring(dotloc + 1, strlen);

			result = ConvertNumber.praseUpcaseRMB(integer, decimal);

		} else { // 金额是整数

			String integer = doubleStr;

			result = ConvertNumber.praseUpcaseRMB(integer);

		}

		return result;

	}

	/**
	 * 
	 * 将double数值(有两位小数或无小数)转换成人民币的大写格式
	 * 
	 * @param rmbDouble
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */

	public static String doChangeRMB(double rmbDouble) throws Exception {

		String result = null;

		double theInt = Math.rint(rmbDouble);

		if (theInt > rmbDouble) {

			theInt -= 1;

		}

		double theDecimal = rmbDouble - theInt;

		String integer = new Long((long) theInt).toString();

		String decimal = "" + Math.round(theDecimal * 100);

		if (decimal.equals("0")) {

			result = ConvertNumber.praseUpcaseRMB(integer);

		} else {

			result = ConvertNumber.praseUpcaseRMB(integer, decimal);

		}

		return result;

	}
	/**
	 * @Title: doubleSub
	 * @Description: 转换double类型，保留两位
	 * @param d
	 * @return Double  
	 * @throws
	 */
	public static Double doubleSub(double d){
		return new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * @Title: doubleSum
	 * @Description: 相加
	 * @param num1
	 * @param num2
	 * @return 
	 * @return Double  
	 * @throws
	 */
	public static Double doubleSum(Double num1,Double num2){
		BigDecimal bigA = new BigDecimal(num1);
		BigDecimal bigB = new BigDecimal(num2);
		return doubleSub(bigA.add(bigB).doubleValue());
	}
	/**
	 * @Title: doubleSubtract
	 * @Description: 相减
	 * @param num1
	 * @param num2
	 * @return Double  
	 * @throws
	 */
	public static Double doubleSubtract(Double num1,Double num2){
		 BigDecimal b1 = new BigDecimal(Double.toString(num1));
	      BigDecimal b2 = new BigDecimal(Double.toString(num2));
		return doubleSub(b1.subtract(b2).doubleValue());
	}
	/**
	 * @Title: multiply
	 * @Description: 乘法
	 * @param v1
	 * @param v2
	 * @return double  
	 * @throws
	 */
	public static double multiply(double v1, double v2){
	      BigDecimal b1 = new BigDecimal(Double.toString(v1));
	      BigDecimal b2 = new BigDecimal(Double.toString(v2));
	      return doubleSub(b1.multiply(b2).doubleValue());
	  }
	/**
	 * @Title: mod
	 * @Description: 除法
	 * @param v1
	 * @param v2
	 * @return double  
	 * @throws
	 */
	public static double mod(Double v1,Double v2){
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
	     BigDecimal b2 = new BigDecimal(Double.toString(v2));
	     return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * @Title: isNum
	 * @Description: 判断是否是NaN或者是否无穷大
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return double 结果  
	 * @throws
	 */
	public static Double isNum(Double divisor,Double dividend){
		Double remainder = divisor/dividend; 
		if(Double.isNaN(remainder))
			return 0.0;
		if(Double.POSITIVE_INFINITY == remainder)
			return 0.0;
		return remainder;
	}
	/**
	 * @Title: keepDecimal
	 * @Description: 保留小数同时四舍五入
	 * @param num 被操作的数字
	 * @param digit 位数
	 * @return Double  
	 * @throws
	 */
	public static Double keepDecimal(Double num,int digit){
		return new BigDecimal(num).setScale(digit,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static String conversionDateStr(){
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sf.format(date);
		return dateStr;
	}
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 

}
