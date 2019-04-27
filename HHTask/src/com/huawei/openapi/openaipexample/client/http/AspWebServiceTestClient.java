package com.huawei.openapi.openaipexample.client.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AspWebServiceTestClient {
	public static void main(String[] args) {
//		Service service = new Service();
//		Call call2;
		// try {
		// call2 = (Call) service.createCall();
		//
		// call2.setTargetEndpointAddress(new java.net.URL(
		// "http://localhost:5329/Service1.asmx"));
		// call2.setUseSOAPAction(true);
		// call2.setReturnType(new QName("http://www.w3.org/2001/XMLSchema",
		// "string"));
		// // 第二种设置返回值类型为String的方法
		// call2.setOperationName(new QName("http://erplab.sjtu.edu/", ""));
		// call2
		// .setSOAPActionURI("http://erplab.sjtu.edu/sayHelloToPersonNew");
		// call2.addParameter(new QName("http://erplab.sjtu.edu/", "name"),//
		// 这里的name对应参数名称
		// XMLType.XSD_STRING, ParameterMode.IN);
		// String retVal2 = (String) call2
		// .invoke(new Object[] { "asp webservice" });
		// System.out.println(retVal2);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// call2 = (Call) service.createCall();
		//
		// call2
		// .setTargetEndpointAddress(new java.net.URL(
		// "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx"));
		//
		// call2.setUseSOAPAction(true);
		// call2.setReturnType(new QName("http://www.w3.org/2001/XMLSchema",
		// "string"));
		//
		// // 第二种设置返回值类型为String的方法
		// call2
		// .setOperationName(new QName(
		// "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx",
		// "getSupportProvince"));
		//			
		// call2.setSOAPActionURI("http://WebXml.com.cn/getSupportProvince");
		//
		// // call2.addParameter(new QName("http://erplab.sjtu.edu/",
		// // "name"),// 这里的name对应参数名称
		// // XMLType.XSD_STRING, ParameterMode.IN);
		//
		// String retVal2 = (String) call2.invoke(new Object[] {});
		// System.out.println(retVal2);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.println(Integer.toString("fasdfasdf".length()));
		UrlForebService();
	}

	public static void UrlForebService() {

		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<GetUserInfo xmlns=\"http://tempuri.org/\">"
					+ " <uid>dgyt</uid>" + " <pwd>x343247</pwd>"
					+ "</GetUserInfo>" + "</soap:Body>" + "</soap:Envelope>";
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
						"\"http://tempuri.org/GetMessageRecord\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
				osw.close();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				String result = "-12";
				is = conn.getInputStream();

				Document doc;
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = dbf.newDocumentBuilder();
				if (is != null) {
					doc = db.parse(is);
					NodeList nl = doc
							.getElementsByTagName("GetUserInfoResult");
					Node n = nl.item(0);
					result = n.getFirstChild().getNodeValue();
					is.close();
				}
				System.out.println(result);
			} catch (Exception ex1) {
				ex1.printStackTrace();
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
		}

	}
}
