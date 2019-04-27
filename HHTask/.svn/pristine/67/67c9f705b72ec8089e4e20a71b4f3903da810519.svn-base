package com.task.util;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Properties;

public class AESEnctypeUtil {
	private String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	//private String path = "src";
	/**支持8-15位
	 * 加密
	 * @param context  加密内容
	 * @param password 加密密码
	 * @return
	 */
	public String enctype(String context){
		
		try {
			Properties prop = new Properties();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path+"/javamail.properties"));
			prop.load(inputStream);
			String password = (String) prop.get("mail.enctype.pwd");
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128,new SecureRandom(password.getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encoded = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(encoded, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] bytesContext = context.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
			byte[] result = cipher.doFinal(bytesContext);
			String str = parsebyte2HexStr(result);
			return str;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解码
	 * @param context
	 * @return
	 */
	public String dencype(String context){
		try {
			Properties prop = new Properties();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(path+"/javamail.properties"));
			prop.load(inputStream);
			String password = (String) prop.get("mail.enctype.pwd");
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(password.getBytes()));
			SecretKey generateKey = keyGenerator.generateKey();
			byte[] encoded = generateKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] bytes = parseHexStr2Byte(context);
			byte[] result = cipher.doFinal(bytes);
			String str = new String(result);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		
		AESEnctypeUtil util = new AESEnctypeUtil();
		try {
			String context = "iccfamkcnshmbadjjahfawfewnaflhwaf"; //要加密的内容
			System.out.println("加密前 : "+context);
			String str = util.enctype(context);
			System.out.println("加密后 ： "+str);
			int num = 0;
			for(int i=0;i<str.length();i++){
				num++;
			}
			System.out.println("位数 ："+num);
			String dencype = util.dencype(str);
			System.out.println("解密后 ： "+dencype);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String parsebyte2HexStr(byte[] buff){
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i< buff.length;i++){
			String hex = Integer.toHexString(buff[i] & 0xFF);
			if(hex.length()==1){
				hex='0'+hex;
			}
			stringBuffer.append(hex.toUpperCase());
		}
		
		return stringBuffer.toString();
	}
	
	public byte[] parseHexStr2Byte(String hexStr){
		if(hexStr.length()<1){
			return null;
		}
		byte[] result =  new byte[hexStr.length()/2];
		for(int i=0;i<hexStr.length()/2;i++){
			int high = Integer.parseInt(hexStr.substring(i*2,i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1,i*2+2), 16);
			result[i]=(byte) (high*16+low);
		}
		return result;
	}
	
	
}
