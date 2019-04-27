package com.task.util;

import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 获取http和https请求的返回数据
 * @author Administrator
 *
 */
public class HttpResponse {

	//输出流
	private InputStream inputStream;
	//
	private String fileName;
	//
	private String contentType;
	//
	private int contentLength;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
	/**
	 * 将得到的数据转换为utf-8字符串
	 * @return
	 */
	public String getDataString(){
		StringBuilder sb=new StringBuilder();
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(this.getInputStream(),"utf-8");
			char[] cbuf=new char[1024];
			int hasRead=0;
			while((hasRead=isr.read(cbuf))>0){
				sb.append(cbuf,0,hasRead);
			}
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 将得到的数据转换为gb2312字符串
	 * @return
	 */
	public String getRTXDataString(){
		StringBuilder sb=new StringBuilder();
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(this.getInputStream(),"gb2312");
			char[] cbuf=new char[1024];
			int hasRead=0;
			while((hasRead=isr.read(cbuf))>0){
				sb.append(cbuf,0,hasRead);
			}
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
