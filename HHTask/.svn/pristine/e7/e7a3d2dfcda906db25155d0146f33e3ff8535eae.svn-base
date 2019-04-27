package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class GenerateTokenByRestSample {

	/* Open API APP Consumer Key */
	private final static String key = "fvnIMtlJLPg7QEjfDPT3zUP4MGYa";
	/* Open API APP Consumer Secret */
	private final static String secury = "0ujInpvopXTx0dW9hBue_GyLxAka";
	/* SOAP Service URL */
	private final static String tokenRestURL = "https://api-beta.huawei.com:443/oauth2/token";

	// private final static String tokenRestURL =
	// "https://openapi.huawei.com:443/oauth2/token";

	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {

		String accessTokenOfRest = getAccessTokenOfRest(tokenRestURL, key,
				secury);
		System.out.println(accessTokenOfRest + "\n");
		Map<String, String> map = jsonToMap(accessTokenOfRest);
		for (Map.Entry<String, String> element : map.entrySet()) {
			System.out.println(element.getKey() + ":" + element.getValue());
		}
	}

	/**
	 * 基于rest协议获取访问token
	 * 
	 * @param tokenUrl
	 * @param key
	 * @param secret
	 * @return
	 */
	public static String getAccessTokenOfRest(String restTokenUrl, String key,
			String secret) {
		String result = "";
		URL postUrl = null;
		OutputStream output = null;
		BufferedReader reader = null;
		InputStreamReader isr = null;
		HttpsURLConnection connection = null;
		try {
			postUrl = new URL(restTokenUrl);
			connection = (HttpsURLConnection) postUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setConnectTimeout(20000);
			connection.setRequestProperty("Authorization", "Basic "
					+ getBaseEcode64(key, secret).trim());
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			output = connection.getOutputStream();
			output.write(("grant_type=client_credentials").getBytes());// 请求输入内容,grant_type为固定值
			output.flush();
			isr = new InputStreamReader(connection.getInputStream());
			reader = new BufferedReader(isr);
			String tempData;
			while ((tempData = reader.readLine()) != null) {
				result = tempData + result;
			}
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					output = null;
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					isr = null;
				}
			}
			if (null != isr) {
				try {
					isr.close();
				} catch (IOException e) {
					isr = null;
				}
			}
			if (null != connection) {
				connection.disconnect();
			}
		}
		return result;
	}

	/**
	 * 根据base64加密
	 * 
	 * @param key
	 * @param secury
	 * @return
	 */
	private static String getBaseEcode64(String key, String secury) {
		return new sun.misc.BASE64Encoder().encode(
				(key + ":" + secury).getBytes()).trim();
	}

	private static Map<String, String> jsonToMap(String strJson) {
		Map<String, String> mapJson = new HashMap<String, String>();
		if (strJson == null || "".equals(strJson)) {
			return mapJson;
		}
		strJson = strJson.replaceFirst("^\\{", "");
		strJson = strJson.replaceFirst("}$", "");
		String[] arrStr = strJson.split(",");
		String[] arrStrKV = null;
		String strK = null, strV = null;
		for (int i = 0; i < arrStr.length; i++) {
			arrStrKV = arrStr[i].split(":");
			strK = arrStrKV[0].replaceAll("\"", "").toLowerCase();
			strV = arrStrKV[1].replaceAll("\"", "");
			mapJson.put(strK, strV);
		}
		return mapJson;
	}
}
