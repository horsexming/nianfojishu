package com.task.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetPageByIpAndUrl {

	public static void main(String[] args) {
		try {
			new GetPageByIpAndUrl();
			String temp=GetPageByIpAndUrl.getContent("http://www.cjsdn.net/Doc/JDK60/", "180.168.41.175", "utf-8");
			System.out.println(temp);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static String getContent(String url, String hostIp, String encode)
			throws ClientProtocolException, IOException {
		String host = url.split("/")[2];
		if (hostIp != null) {
			url = url.replaceFirst(host, hostIp);
		}
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Host", host);
		httpGet.addHeader("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		httpGet.addHeader("Referer", host);
		httpGet.addHeader("Pragma", "No-cache");
		HttpResponse response = httpclient.execute(httpGet);
		String result = ResponseHander.getInstance().toString(response, encode);
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	public static void getFile(String target, String hostIp, File fileOut)
			throws ClientProtocolException, IOException {
		String host = target.split("/")[2];
		if (hostIp != null) {
			target = target.replaceFirst(host, hostIp);
		}
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(target);
		httpGet.addHeader("Host", host);
		httpGet.addHeader("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		httpGet.addHeader("Referer", host);
		httpGet.addHeader("Pragma", "No-cache");
		HttpResponse response = httpclient.execute(httpGet);
		InputStream input = ResponseHander.getInstance().toStream(response);
		FileOutputStream out = new FileOutputStream(fileOut);
		byte[] bytes = new byte[1024];
		int c = 0;
		while ((c = input.read(bytes)) != -1) {
			out.write(bytes, 0, c);
		}
		input.close();
		out.flush();
		out.close();
		httpclient.getConnectionManager().shutdown();
	}
}
