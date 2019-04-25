package com.hhu.count.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.RandomStringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SendMassage {
	public String send(String mobiles, String msg) {
		// 暂时性封闭短信接口
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getSoapInputStream("shhh", "x346928", mobiles, msg,
					"");
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendMessagesResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			return "-12";
		}
		// 暂时性封闭短信接口
		// return "短信接口已暂停，请您通知管理员";
	}
	
	//获取用户信息
		private InputStream getSoapInputStream(String userid, String pass,
				String mobiles, String msg, String time) throws Exception {
			URLConnection conn = null;
			InputStream is = null;
			try {
				String soap = getSoapSmssend(userid, pass, mobiles, msg, time);
				if (soap == null) {
					return null;
				}
				try {

					URL url = new URL("http://service2.winic.org:8003/Service.asmx");

					conn = url.openConnection();
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestProperty("Content-Length", Integer.toString(soap
							.length()));
					conn.setRequestProperty("Content-Type",
							"text/xml; charset=utf-8");
					conn.setRequestProperty("HOST", "service2.winic.org");
					conn.setRequestProperty("SOAPAction",
							"\"http://tempuri.org/SendMessages\"");

					OutputStream os = conn.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
					osw.write(soap);
					osw.flush();
					osw.close();
				} catch (Exception ex) {
					System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
				}
				try {
					is = conn.getInputStream();
				} catch (Exception ex1) {
					System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
				}

				return is;
			} catch (Exception e) {
				System.out.print("SmsSoap.InputStream error:" + e.getMessage());
				return null;
			}
		}
		//获取用户信息
		private String getSoapSmssend(String userid, String pass, String mobiles,
				String msg, String time) {
			try {
				String soap = "";
				soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
						+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+ "<soap:Body>"
						+ "<SendMessages xmlns=\"http://tempuri.org/\">" + "<uid>"
						+ userid + "</uid>" + "<pwd>" + pass + "</pwd>" + "<tos>"
						+ mobiles + "</tos>" + "<msg>" + msg + "</msg>" + "<otime>"
						+ time + "</otime>" + "</SendMessages>" + "</soap:Body>"
						+ "</soap:Envelope>";
				return soap;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	public static void main(String[] args) {
		String randomCode = RandomStringUtils.randomNumeric(4);
		System.out.println(randomCode);
		// System.out.println(sendSmsByHTTP("xxx", randomCode));
		// System.out.println(sendSmsByHTTP("xxx", "尊敬的用户您好，本次获取的验证码为："
		// + randomCode + ",服务电话：4006184000"));
		/*PhoneUtil poneUtil = new PhoneUtil();
		System.out.println(poneUtil.sendSmsByWebService("17521619293", "尊敬的用户您好，本次获取的验证码为："
				+ randomCode + ",服务电话：4006184000"));*/
		
		//Phone phone = new Phone();
		// String userid="15039889155";   //你的用户名
		// String pass="5201314tt";	//你的密码
		 //String mobiles="17521619293"; //对方接收的手机号
		// String msg="JAVA测试短信通过2008-11-13";  //内容
		// String time="";
		// String result=phone.sendSms(userid,pass,mobiles,msg,time);
		SendMassage testPhone = new SendMassage();		
		 System.out.println(testPhone.send("17521619293", "尊敬的XX"+ randomCode + "X先生/女士："));

	}

}
