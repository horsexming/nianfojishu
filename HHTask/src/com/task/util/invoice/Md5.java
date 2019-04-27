package com.task.util.invoice;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	/**
     * 获得随机数字
	 * @param num  位数
	 * @return
	 */
	public static String getRandom(int num){
		Double key = Math.random()*100000;
		String result = new Integer(key.intValue()).toString();
		if(result.length()>num){
			result = result.substring(result.length()-num);
		}else{
			for(int i=num-result.length();i>0;i--){
				result = "0" + result; 
			}
		}
		return result;
	}
	
	/**
	 * 获取Md5码
	 * @param key
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
    public static String getEncode(String key) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        try {
			messageDigest.update(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			messageDigest.update(key.getBytes());
		}
        byte digest[] = messageDigest.digest();
        int j = digest.length;
        char str[] = new char[j * 2];
        int k = 0;
        for(int i = 0; i < j; i++)
        {
            byte byte0 = digest[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str);
    }
    
    public static String getEncode(byte[] key) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(key);
        byte digest[] = messageDigest.digest();
        int j = digest.length;
        char str[] = new char[j * 2];
        int k = 0;
        for(int i = 0; i < j; i++)
        {
            byte byte0 = digest[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str);
    }

    static final char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };

}