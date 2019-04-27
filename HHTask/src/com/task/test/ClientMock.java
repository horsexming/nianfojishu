package com.task.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ClientMock implements Runnable {
	private static final int CLIENT_COUNT = 100000; // 用户数
	private final int SLEEP_TIME = 5; // 某个用户每次访问的间隔时间
	private int request_count = 10000; // 每个用户准备发出的请求个数
	private int requested_count = 0; // 已发出请求个数
	private Document doc; // 网页文档对象
	List<String> dstUrls = new ArrayList<String>(); // 请求地址库
	private int errorCount = 0; // 失败的请求个数

	public static void main(String[] args) throws Exception {
		// 原始的请求地址集
		List<String> srcUrls = new ArrayList<String>() {
			{
				add("http://bushere.com/");
			}
		};

		// 从原始地址形成地址库
		ClientMock mocker = new ClientMock();
		List<String> dstUrls = new ArrayList<String>();
		for (String srcUrl : srcUrls) {
			dstUrls.addAll(mocker.getUrls(srcUrl));
		}
		// 模拟多用户请求
		for (int i = 0; i < CLIENT_COUNT; i++) {
			ClientMock client = new ClientMock();
			client.setDstUrls(dstUrls);
			new Thread(client, "thread_" + i).start();
		}

	}

	public void run() {
		try {
			int remainCount = dstUrls.size(); // 剩余个数
			// while (request_count > 0) {
			while (true) {
				int randomNum = new Random().nextInt(dstUrls.size());
				if (requested_count % 5 == 0) {
					System.out.println("\r\n"
							+ Thread.currentThread().getName()
							+ "--------------" + randomNum + "/"
							+ requested_count + "/" + dstUrls.size()
							+ "----------------");
				}
				doRequest(dstUrls.get(randomNum));
				remainCount--;
				request_count--;
				requested_count++;
			}
			/*
			 * if (request_count == 0) {
			 * System.err.println("::Report-------------errorCount of" +
			 * Thread.currentThread().getName() + " :" + errorCount + "/" +
			 * requested_count); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 088 发送请求 089
	 * 
	 * 090
	 * 
	 * @param dstUrl
	 *            091 要被访问的地址 092
	 * @throws Exception
	 *             093
	 */
	private void doRequest(String dstUrl) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(dstUrl.replaceAll(" ", ""));
		try {
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			/*
			 * if (statusCode != 200) { errorCount++;
			 * System.err.println(Thread.currentThread().getName() +
			 * "--------------errorCount: " + errorCount); }
			 */
			// System.out.println(dstUrl);
			Thread.sleep(SLEEP_TIME);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从原始地址形成地址库 119
	 * 
	 * 120
	 * 
	 * @param baseUrl
	 *            121 原始地址 122
	 * @return 123
	 * @throws IOException
	 *             124
	 */
	private List<String> getUrls(String baseUrl) throws IOException {
		List<String> urls = new ArrayList<String>();
		doc = Jsoup.connect(baseUrl.trim()).get();
		String sufferURI = doc.baseUri();

		for (Element element : doc.getElementsByTag("a")) {
			String href = element.attr("href");
			if (href.startsWith("http://")) {
				urls.add(href);
			} else {
				urls.add(sufferURI + href);
			}
		}
		System.out.println("------------------------- 获得" + urls.size()
				+ "条url地址 ----------------------------");
		return urls;
	}

	/**
	 * 144 设置要被访问的地址库 145
	 * 
	 * 146
	 * 
	 * @param dstUrls
	 *            147 地址库集合 148
	 */
	private void setDstUrls(List<String> dstUrls) {
		this.dstUrls = dstUrls;
	}

	/**
	 * 154 获得失败请求的个数 155
	 * 
	 * 156
	 * 
	 * @return 157
	 */
	private int getErrorCount() {
		return this.errorCount;
	}

}
