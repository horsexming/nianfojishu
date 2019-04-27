package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.SmsService;
import com.task.util.MKUtil;

public class SmsAction extends ActionSupport{
	private String id;
	private String msg;
	private SmsService smsService;
	
	public String send(){
		if(id == null || id.length() ==0){
			MKUtil.writeJSON(false, "发送失败,人员不能为空", null);
			return null;
		} 

		try {
			smsService.sendSms(id,msg);
			MKUtil.writeJSON(true,"短信发送成功",null);
		} catch (Exception e) {
			MKUtil.writeJSON(false,"短信发送失败:" + e.getMessage(), null);
			e.printStackTrace();
		}
		return null;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SmsService getSmsService() {
		return smsService;
	}
	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
