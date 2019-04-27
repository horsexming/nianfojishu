package com.task.action;

import com.sun.faces.util.Util;
import com.task.Server.JavaMailService;
import com.task.util.MKUtil;

public class JavaMailAction {

	private JavaMailService javaMailService;
	private String mailname;
	private String password;
	/**
	 * 返回 data[0] 全部邮件
	 * data[1] 未读邮件
	 * data[2] 邮箱登录地址
	 * @return
	 */
	public String getMailInfo(){
		String[] data = javaMailService.getJavaMailInfo();
		MKUtil.writeJSON(data);
		
		return null;
	}
	
	public void checkEmailInfo(){
		String info = javaMailService.checkEmailInfo(mailname, password);
		MKUtil.writeJSON(info);
	}
	
	
	public JavaMailService getJavaMailService() {
		return javaMailService;
	}

	public void setJavaMailService(JavaMailService javaMailService) {
		this.javaMailService = javaMailService;
	}

	public String getMailname() {
		return mailname;
	}

	public void setMailname(String mailname) {
		this.mailname = mailname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
