package com.hhu.count.entity;

import java.util.Date;

/*保存发送记录*/

public class SendMsg {
	private String phone;
	private Date sendTime;
	private Integer sendnum;
	private String sendcontent;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getSendnum() {
		return sendnum;
	}

	public void setSendnum(Integer sendnum) {
		this.sendnum = sendnum;
	}

	public String getSendcontent() {
		return sendcontent;
	}

	public void setSendcontent(String sendcontent) {
		this.sendcontent = sendcontent;
	}
	
	

}
