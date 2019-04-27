package com.task.entity.rtx;

import java.io.Serializable;

/**
 * rtx消息记录 表名：ta_rtxmsg
 * @author txb
 *
 */
public class RtxMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//消息id
	private Integer userid;//消息发送人id
	private String userName;//消息发送人名字
	private String userCode;//消息发送人工号
	private String receivers;//消息接收人工号（用逗号隔开）
	private String receiverNames;//消息接收人名字（用逗号隔开）
	private String title;//消息标题
	private String msg;//消息内容
	private String sendTime;//发送时间
	private Integer receiverCount;//接收人个数
	private String msgType;//消息类型（平台消息，流程消息）
	private String senderIp;//发送人ip
	private String sendOk;//是否发送成功(OK,ON)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getReceiverNames() {
		return receiverNames;
	}
	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getReceiverCount() {
		return receiverCount;
	}
	public void setReceiverCount(Integer receiverCount) {
		this.receiverCount = receiverCount;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSenderIp() {
		return senderIp;
	}
	public void setSenderIp(String senderIp) {
		this.senderIp = senderIp;
	}
	public String getSendOk() {
		return sendOk;
	}
	public void setSendOk(String sendOk) {
		this.sendOk = sendOk;
	}
	
	

}