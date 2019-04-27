package com.task.entity.weixin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Card entity. @author MyEclipse Persistence Tools
 */

public class WechatCard implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private QrCode qrCode;
	private String title;
	private String description;
	private String text;
	private Integer count;
	private Set wechatFiles = new HashSet(0);

	// Constructors

	/** default constructor */
	public WechatCard() {
	}

	/** minimal constructor */
	public WechatCard(QrCode qrCode) {
		this.qrCode = qrCode;
	}

	/** full constructor */
	public WechatCard(QrCode qrCode, String title, String description, String text,
			Integer count, Set wechatFiles) {
		this.qrCode = qrCode;
		this.title = title;
		this.description = description;
		this.text = text;
		this.count = count;
		this.wechatFiles = wechatFiles;
	}

	// Property accessors

	

	public QrCode getQrCode() {
		return this.qrCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQrCode(QrCode qrCode) {
		this.qrCode = qrCode;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set getWechatFiles() {
		return wechatFiles;
	}

	public void setWechatFiles(Set wechatFiles) {
		this.wechatFiles = wechatFiles;
	}


}