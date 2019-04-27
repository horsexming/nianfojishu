package com.task.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ShortMessageService;
import com.task.entity.ShortMessage;
import com.task.entity.Users;

public class ShortMessageAction extends ActionSupport {
	private ShortMessageService shortMessageService;

	private ArrayList<Users> usersList;//sendMessageInput方法
	private ArrayList<Users> usersSend;//发送消息时需要收信人的code，用此保存
	private String msg;//短信息内容
	private ArrayList<ShortMessage> sendHistory;//queryHistory用到的变量
	private Users usersInput;//queryHistory用到的变量
	private ShortMessage shortMessage;
	
	private String successMessage;// 正确信息
	private String errorMessage;// 错误信息

	//发送消息的输入页面
	public String sendMessageInput() {
		usersList = shortMessageService.queryUsers(usersInput);
		return INPUT;
	}
	
	//发送消息的处理页面
	public String sendMessage(){
		if(usersSend == null || usersSend.size() == 0){
			errorMessage = "没有要接收短信的人";
			return SUCCESS;
		}
		usersSend = shortMessageService.get(usersSend);
		Long l = Long.parseLong(shortMessageService.send(usersSend, msg));
		if(l >= 0){
			successMessage = "短信发送成功!";
		} else if (l == -1) {
			errorMessage = "短信帐号余额不足，请通知管理员！错误代码:-1";
		} else if (l==-2) {
			errorMessage = "短信帐号ID错误，请通知管理员！错误代码:-2";
		} else if (l==-3) {
			errorMessage = "短信帐号密码错误，请通知管理员！错误代码:-3";
		}else if (l==-4) {
			errorMessage = "参数不够或参数内容的类型错误，请通知管理员！错误代码:-4";
		}else if (l==-12) {
			errorMessage = "发送短信失败，请重新尝试，如果多次失败，请联系管理员！错误代码:-12";
		}else if (l==-13) {
			errorMessage = "网络连接失败！";
		}
		return "chainToSend";
	}

	//条件查询列出历史
	public String queryHistory(){
		if(shortMessage != null){
			sendHistory = shortMessageService.query(shortMessage);
		}
		return SUCCESS;
	}

	public ArrayList<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(ArrayList<Users> usersList) {
		this.usersList = usersList;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ShortMessageService getShortMessageService() {
		return shortMessageService;
	}

	public void setShortMessageService(ShortMessageService shortMessageService) {
		this.shortMessageService = shortMessageService;
	}

	public ArrayList<Users> getUsersSend() {
		return usersSend;
	}

	public void setUsersSend(ArrayList<Users> usersSend) {
		this.usersSend = usersSend;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ShortMessage getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

	public ArrayList<ShortMessage> getSendHistory() {
		return sendHistory;
	}

	public void setSendHistory(ArrayList<ShortMessage> sendHistory) {
		this.sendHistory = sendHistory;
	}

	public Users getUsersInput() {
		return usersInput;
	}

	public void setUsersInput(Users usersInput) {
		this.usersInput = usersInput;
	}


}
