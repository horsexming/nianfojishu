package com.task.entity.weixin;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.task.entity.Users;

/**
 * SendMass entity. @author MyEclipse Persistence Tools
 */

public class SendMass implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private String userName;
	private String userCode;
	private String userDept;
	private String creattime;
	private String mediaid;
	private String msgid;
	private Integer status;
	private Set articles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SendMass() {
	}

	/** minimal constructor */
	public SendMass(Users user, String creattime) {
		userId = user.getId();
		userName=user.getName();
		userCode=user.getCode();
		userDept=user.getDept();
	}

	/** full constructor */
	public SendMass(Users user, String creattime, String mediaid,
			String msgid, Integer status, Set articles) {
		userId = user.getId();
		userName=user.getName();
		userCode=user.getCode();
		userDept=user.getDept();
		this.creattime = creattime;
		this.mediaid = mediaid;
		this.msgid = msgid;
		this.status = status;
		this.articles = articles;
	}

	// Property accessors

	


	public String getMediaid() {
		return this.mediaid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

}