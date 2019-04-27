package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 快递柜存取验证码表 2018-08-15
 * 
 * @author Li_Cong 表名 ta_mj_ResAccess 取物品表()
 */
public class ResAccess implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;// (存取/寄取)
	private String quCodes;// 验证码(取)
	private String cunCodes;// 验证码/条码(存)
	private String copenTime;// 存入时间
	private String qopenTime;// 取出时间
	private Integer cuseType;// 存码使用状态(0未使用/1使用中/2已失效)
	private Integer quseType;// 取码使用状态(0未使用/1使用中/2已失效)

	private Integer daGuiId;// 快递柜ID
	private String daGuihao;// 快递柜编号
	private Integer daGuiposition;// 快递柜开柜子指令1~45（对比的开门指令）
	private String ace_Ip;// 设备Ip(nb编号)

	private String addTime;// 添加时间
	private String addName;// 添加ren
	private String addCode;// 添加人工号
	private Integer addUserId;// 添加人ID
	private String shixiaoTime;// 失效时间
	private Integer yxType;// 是否有效(0有效/1已使用/2过期/3取消)

	private String shouTel;// 取物人手机号(寄快递专用)

	private String picture;// 员工照片--页面传值

	public ResAccess() {

	}

	public ResAccess(Integer id, String type, Integer cuseType, Integer quseType, Integer daGuiId, String daGuihao,
			String addTime, String addName, String addCode, Integer addUserId, Integer yxType, String picture) {
		this.id = id;
		this.type = type;
		this.cuseType = cuseType;
		this.quseType = quseType;
		this.daGuiId = daGuiId;
		this.daGuihao = daGuihao;
		this.addTime = addTime;
		this.addName = addName;
		this.addCode = addCode;
		this.addUserId = addUserId;
		this.yxType = yxType;
		this.picture = picture;
	}

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShouTel() {
		return shouTel;
	}

	public void setShouTel(String shouTel) {
		this.shouTel = shouTel;
	}

	public String getAddCode() {
		return addCode;
	}

	public void setAddCode(String addCode) {
		this.addCode = addCode;
	}

	public Integer getYxType() {
		return yxType;
	}

	public void setYxType(Integer yxType) {
		this.yxType = yxType;
	}

	public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuCodes() {
		return quCodes;
	}

	public void setQuCodes(String quCodes) {
		this.quCodes = quCodes;
	}

	public String getCunCodes() {
		return cunCodes;
	}

	public void setCunCodes(String cunCodes) {
		this.cunCodes = cunCodes;
	}

	public String getCopenTime() {
		return copenTime;
	}

	public void setCopenTime(String copenTime) {
		this.copenTime = copenTime;
	}

	public String getQopenTime() {
		return qopenTime;
	}

	public void setQopenTime(String qopenTime) {
		this.qopenTime = qopenTime;
	}

	public Integer getCuseType() {
		return cuseType;
	}

	public void setCuseType(Integer cuseType) {
		this.cuseType = cuseType;
	}

	public Integer getQuseType() {
		return quseType;
	}

	public void setQuseType(Integer quseType) {
		this.quseType = quseType;
	}

	public Integer getDaGuiId() {
		return daGuiId;
	}

	public void setDaGuiId(Integer daGuiId) {
		this.daGuiId = daGuiId;
	}

	public String getDaGuihao() {
		return daGuihao;
	}

	public void setDaGuihao(String daGuihao) {
		this.daGuihao = daGuihao;
	}

	public Integer getDaGuiposition() {
		return daGuiposition;
	}

	public void setDaGuiposition(Integer daGuiposition) {
		this.daGuiposition = daGuiposition;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getShixiaoTime() {
		return shixiaoTime;
	}

	public void setShixiaoTime(String shixiaoTime) {
		this.shixiaoTime = shixiaoTime;
	}

	public String getAce_Ip() {
		return ace_Ip;
	}

	public void setAce_Ip(String aceIp) {
		ace_Ip = aceIp;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
