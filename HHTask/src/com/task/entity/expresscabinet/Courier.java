/**
 * 
 */
package com.task.entity.expresscabinet;

import java.io.Serializable;

/**
 * @author Administrator 快递员信息表
 */
public class Courier implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // id
	private String couName; // 快递员姓名
	private String phoneNumber; // 快递员手机号
	private String kdCompany; // 快递员所属快递公司
	private String code; // 验证码
	private String couReTime; // 注册时间
	private String idNumber; // 身份证号
	private String idFront; // 证件照(身份证正面照或者工作证正面照)base64格式图片名称
	// private String route; //base64格式图片名称
	private String primaryName; // 图片原名称
	// private String idSide; //身份证反面
	// private String staffCard; //快递员工牌
	// private String headPortrait; //头像
	private Integer epId; // 审批流程ID

	private String couState; // 状态

	public Courier() {
		super();
	}

	public Courier(Integer id, String couName, String phoneNumber, String kdCompany, String code, String couReTime,
			String idNumber, String idFront, String primaryName, String couState) {
		super();
		this.id = id;
		this.couName = couName;
		this.phoneNumber = phoneNumber;
		this.kdCompany = kdCompany;
		this.code = code;
		this.couReTime = couReTime;
		this.idNumber = idNumber;
		this.idFront = idFront;
		// this.route = route;
		this.primaryName = primaryName;
		this.couState = couState;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String getCouReTime() {
		return couReTime;
	}

	public void setCouReTime(String couReTime) {
		this.couReTime = couReTime;
	}

	public String getCouState() {
		return couState;
	}

	public void setCouState(String couState) {
		this.couState = couState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getKdCompany() {
		return kdCompany;
	}

	public void setKdCompany(String kdCompany) {
		this.kdCompany = kdCompany;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdFront() {
		return idFront;
	}

	public void setIdFront(String idFront) {
		this.idFront = idFront;
	}
	// public String getRoute() {
	// return route;
	// }
	//
	// public void setRoute(String route) {
	// this.route = route;
	// }

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

}
