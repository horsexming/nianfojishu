package com.task.entity.weixin;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * WeChatMsg entity. @author MyEclipse Persistence Tools
 */

public class WeChatMsg implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private WeChatUser weChatUser;
	private String creattime;
	private String type;
	private String text;

	// Constructors

	/** default constructor */
	public WeChatMsg() {
	}

	/** minimal constructor */
	public WeChatMsg(WeChatUser weChatUser, String creattime, String type) {
		this.weChatUser = weChatUser;
		this.creattime = creattime;
		this.type = type;
	}

	/** full constructor */
	public WeChatMsg(WeChatUser weChatUser, String creattime, String type,
			String text) {
		this.weChatUser = weChatUser;
		this.creattime = creattime;
		this.type = type;
		this.text = text;
	}

	// Property accessors

	

	public WeChatUser getWeChatUser() {
		return this.weChatUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setWeChatUser(WeChatUser weChatUser) {
		this.weChatUser = weChatUser;
	}

	public String getCreattime() {
		return this.creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}