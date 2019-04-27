package com.task.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * 发送http或者https请求类
 * 
 * @author Administrator
 * 
 */
public class HttpRequest {

	public static final String SEND_POST = "POST";
	public static final String SEND_GET = "GET";

	public HttpRequest() {
	}

	/**
	 * httpGet请求
	 * 
	 * @param urlString
	 *            请求地址
	 * @param params
	 *            参数，会拼接到urlString上
	 * @return HttpRespone 封装的返回数据
	 * @throws IOException
	 */
	public HttpResponse sendHttpGet(String urlString, Map<String, String> params)
			throws IOException {
		// 拼接url参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		// 创建连接
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setRequestMethod(SEND_GET);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(3000);
		urlConnection.setReadTimeout(1000);

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());
		// 获取文件名。。。
		response.setFileName(urlConnection.getRequestProperty("filename"));
		return response;
	}

	/**
	 * httpPost请求
	 * 
	 * @param urlString
	 *            请求地址
	 * @param params
	 *            参数，会拼接到urlString上
	 * @param filePath
	 *            文件路径
	 * @param contentType
	 *            文件类型
	 * @return HttpRespone 封装的返回数据
	 * @throws IOException
	 */
	public HttpResponse sendHttpPost(String urlString,
			Map<String, String> params, String filePath, String contentType)
			throws IOException {
		// 拼接url参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		// 创建连接
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		// 设置连接参数
		urlConnection.setRequestMethod(SEND_POST);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(3000);
		urlConnection.setReadTimeout(1000);
		//
		File file = new File(filePath);

		urlConnection.setRequestProperty("Connection", "Keep-Alive");
		urlConnection.setRequestProperty("Charset", "gb2312");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		urlConnection.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("gb2312");

		// 获得输出流
		OutputStream out1 = new DataOutputStream(urlConnection
				.getOutputStream());
		// 输出表头
		out1.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in1 = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in1.read(bufferOut)) != -1) {
			out1.write(bufferOut, 0, bytes);
		}
		in1.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("gb2312");// 定义最后数据分隔线
		out1.write(foot);
		out1.flush();
		out1.close();

		FileInputStream in = new FileInputStream(file);

		OutputStream out = urlConnection.getOutputStream();
		byte[] buf = new byte[1024];
		int hasRead = 0;
		while ((hasRead = in.read(buf)) > 0) {
			out.write(buf, 0, hasRead);
		}
		in.close();
		out.flush();
		out.close();

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());

		return response;
	}

	/**
	 * httpPost请求
	 * 
	 * @param urlString
	 *            请求地址
	 * @param params
	 *            参数，会拼接到urlString上
	 * @param filePath
	 *            文件路径
	 * @param contentType
	 *            文件类型
	 * @return HttpRespone 封装的返回数据
	 * @throws IOException
	 */
	public HttpResponse sendHttpPost(String urlString,
			Map<String, String> params) throws IOException {
		// 拼接url参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		// 创建连接
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		// 设置连接参数
		urlConnection.setRequestMethod(SEND_POST);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(500);
		urlConnection.setReadTimeout(1000);
		//
		urlConnection.setRequestProperty("Connection", "Keep-Alive");
		urlConnection.setRequestProperty("Charset", "gb2312");
		// // 请求正文信息
		// OutputStream out = urlConnection.getOutputStream();
		// byte[] buf = new byte[1024];
		// int hasRead = 0;

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());

		return response;
	}

	public HttpResponse sendHttpPosts(String urlString,
			Map<String, Object> params) throws IOException {
		// 拼接url参数
		String param = "";
		if (params != null) {
			int i = 0;
			StringBuffer paramb = new StringBuffer();
			for (String key : params.keySet()) {
				if (i == 0) {
				}
				// paramb.append("?");
				else
					paramb.append("&");
				paramb.append(key).append("=").append(params.get(key));
				i++;
			}
			param = paramb.toString();
			// urlString += param;
		}
		// // 创建连接
		// URL url = new URL(urlString);
		// HttpURLConnection urlConnection = (HttpURLConnection) url
		// .openConnection();
		// // 设置连接参数
		// urlConnection.setRequestMethod(SEND_POST);
		// urlConnection.setDoOutput(true);
		// urlConnection.setDoInput(true);
		// urlConnection.setUseCaches(false);
		// //
		// urlConnection.setRequestProperty("Connection", "Keep-Alive");
		// urlConnection.setRequestProperty("Charset", "utf-8");
		// urlConnection.connect();
		//
		// PrintWriter pw = new PrintWriter(urlConnection.getOutputStream());
		// pw.write(urlString); // 向连接中输出数据（相当于发送数据给服务器）
		// pw.flush();
		// pw.close();
		//
		// // // 请求正文信息
		// // OutputStream out = urlConnection.getOutputStream();
		// // byte[] buf = new byte[1024];
		// // int hasRead = 0;
		// //
		// // HttpResponse response = new HttpResponse();
		// // response.setInputStream(urlConnection.getInputStream());
		//
		// return null;

		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn =null;
		String result = "";
		try {
			URL realUrl = new URL(urlString);
			// 打开和URL之间的连接
			conn= (HttpURLConnection) realUrl
					.openConnection();
			// 设置通用的请求属性
			// 　accept:浏览器通过这个头告诉服务器，它所支持的数据类型
			// 　　Accept-Charset: 浏览器通过这个头告诉服务器，它支持哪种字符集
			// 　　Accept-Encoding：浏览器通过这个头告诉服务器，支持的压缩格式
			// 　　Accept-Language：浏览器通过这个头告诉服务器，它的语言环境
			// 　　Host：浏览器通过这个头告诉服务器，想访问哪台主机
			// 　　If-Modified-Since: 浏览器通过这个头告诉服务器，缓存数据的时间
			// 　　Referer：浏览器通过这个头告诉服务器，客户机是哪个页面来的 防盗链
			// 　　Connection：浏览器通过这个头告诉服务器，请求完后是断开链接还是何持链接
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(SEND_POST);
			conn.setConnectTimeout(500);// 连接主机的时长
			conn.setReadTimeout(1);//0表示无限等待
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			conn.getInputStream();
			// 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(
			// new InputStreamReader(conn.getInputStream()));
			// String line;
			// while ((line = in.readLine()) != null) {
			// result += line;
			// }
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 发送Https请求（GET）
	 * 
	 * @param urlString
	 *            请求地址url
	 * @param params
	 *            键值对参数 ，会拼接到urlString上
	 * @return HttpRespone 封装的返回数据
	 * @throws IOException
	 */
	public HttpResponse sendHttpsGet(String urlString,
			Map<String, String> params) throws IOException {
		// 拼接url参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		// 创建连接
		URL url = new URL(urlString);
		HttpsURLConnection urlConnection = (HttpsURLConnection) url
				.openConnection();
		// 设置连接参数
		urlConnection.setRequestMethod(SEND_GET);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(3000);
		urlConnection.setReadTimeout(1000);
		// 将返回数据封装成HttpResponse
		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());

		return response;
	}

	/**
	 * 发送Https请求（POST）
	 * 
	 * @param urlString
	 *            请求地址url
	 * @param params
	 *            键值对参数 ，会拼接到urlString上
	 * @param sendData
	 *            post的数据
	 * @return HttpRespone 封装的返回数据
	 * @throws IOException
	 */
	public HttpResponse sendHttpsPost(String urlString,
			Map<String, String> params, String sendData) throws IOException {
		// 拼接url参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		// 创建连接
		URL url = new URL(urlString);
		HttpsURLConnection urlConnection = (HttpsURLConnection) url
				.openConnection();
		// 设置参数
		urlConnection.setRequestMethod(SEND_POST);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(3000);
		urlConnection.setReadTimeout(1000);
		// 发送post数据
		if (sendData != null) {
			urlConnection.getOutputStream().write(sendData.getBytes("utf-8"));
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());

		return response;
	}

}
