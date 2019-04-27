package com.task.entity.weixin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Qr entity. @author MyEclipse Persistence Tools
 */

public class Qr implements Serializable{

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sceneId;
	private String ticket;
	private Integer count;
	private Set qrCodes = new HashSet(0);
	private Set scanLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Qr() {
	}

	/** minimal constructor */
	public Qr(Integer sceneId, String ticket) {
		this.sceneId = sceneId;
		this.ticket = ticket;
	}

	/** full constructor */
	public Qr(Integer sceneId, String ticket, Integer count, Set qrCodes,
			Set scanLogs) {
		this.sceneId = sceneId;
		this.ticket = ticket;
		this.count = count;
		this.qrCodes = qrCodes;
		this.scanLogs = scanLogs;
	}

	// Property accessors



	public Integer getSceneId() {
		return this.sceneId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

}