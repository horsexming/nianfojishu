package com.task.entity;

import java.io.Serializable;

/***
 * 短信(表名:ta_ShortMessage)
 * 
 * @author 马凯
 * 
 */
public class ShortMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String phone;// 短信发到和手机
	private String name;// 收信人
	private String code;// 工号
	private String sendDate;// 发送名字
	private String content;// 短信内容
	private String dept;// 所在部门

	public Integer getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getSendDate() {
		return sendDate;
	}

	public String getContent() {
		return content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
