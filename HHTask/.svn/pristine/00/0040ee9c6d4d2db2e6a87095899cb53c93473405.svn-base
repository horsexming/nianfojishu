package com.task.entity.weixin;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * WeChatUser entity. @author MyEclipse Persistence Tools
 */

public class WeChatUser implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer subscribe;
	private String openid;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String subtime;
	private String unsubtime;
	private Set sendMsgs = new HashSet(0);
	private Set qrCodes = new HashSet(0);
	private Set scanLogs = new HashSet(0);
	private Set weChatMsgs = new HashSet(0);

	// Constructors

	/** default constructor */
	public WeChatUser() {
	}

	/** minimal constructor */
	public WeChatUser(Integer subscribe, String openid, String nickname,
			Integer sex, String language, String city, String province,
			String country, String subtime, String unsubtime) {
		this.subscribe = subscribe;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.subtime = subtime;
		this.unsubtime = unsubtime;
	}

	/** full constructor */
	public WeChatUser(Integer subscribe, String openid, String nickname,
			Integer sex, String language, String city, String province,
			String country, String subtime, String unsubtime,
			Set sendMsgs, Set qrCodes, Set scanLogs, Set weChatMsgs) {
		this.subscribe = subscribe;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.subtime = subtime;
		this.unsubtime = unsubtime;
		this.sendMsgs = sendMsgs;
		this.qrCodes = qrCodes;
		this.scanLogs = scanLogs;
		this.weChatMsgs = weChatMsgs;
	}

	// Property accessors

	

	public Integer getSubscribe() {
		return this.subscribe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSubtime() {
		return this.subtime;
	}

	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}

	public String getUnsubtime() {
		return this.unsubtime;
	}

	public void setUnsubtime(String unsubtime) {
		this.unsubtime = unsubtime;
	}

	public Set getSendMsgs() {
		return this.sendMsgs;
	}

	public void setSendMsgs(Set sendMsgs) {
		this.sendMsgs = sendMsgs;
	}

	public Set getQrCodes() {
		return this.qrCodes;
	}

	public void setQrCodes(Set qrCodes) {
		this.qrCodes = qrCodes;
	}

	public Set getScanLogs() {
		return this.scanLogs;
	}

	public void setScanLogs(Set scanLogs) {
		this.scanLogs = scanLogs;
	}

	public Set getWeChatMsgs() {
		return this.weChatMsgs;
	}

	public void setWeChatMsgs(Set weChatMsgs) {
		this.weChatMsgs = weChatMsgs;
	}

}