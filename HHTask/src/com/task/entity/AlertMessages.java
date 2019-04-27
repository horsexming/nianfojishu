package com.task.entity;

import java.io.Serializable;

/***
 * 系统提醒消息(表名:ta_sys_AlertMessages)
 * 
 * @author 刘培
 * 
 */
public class AlertMessages  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer receiveuserId;// 接收用户id
	private Integer sendUserId;// 发送用户id
	private String sendUserName;// 发送用户名称
	private String sendUserImg;// 发送用户头像
	private String messageType;// 消息类型(消息类型("1"="您有新的审核提醒消息")
	private String title;// 标题
	private String content;// 内容
	private int functionId;// 功能id
	private String functionName;// 功能名称
	private String functionUrl;// 功能地址
	private String addTime;// 添加时间
	private String readStatus;// 阅读状态(yes/no)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReceiveuserId() {
		return receiveuserId;
	}

	public void setReceiveuserId(Integer receiveuserId) {
		this.receiveuserId = receiveuserId;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getSendUserImg() {
		return sendUserImg;
	}

	public void setSendUserImg(String sendUserImg) {
		this.sendUserImg = sendUserImg;
	}

}
