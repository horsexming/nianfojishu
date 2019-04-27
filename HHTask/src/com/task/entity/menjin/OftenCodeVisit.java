package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 常访客表
 * 
 * @author Li_Cong 11-17 表名 ta_mj_OftenCodeVisit 常访客车辆信息表
 */
public class OftenCodeVisit implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String oftenName;// 常访人(内部员工)
	private String name;// 姓名
	private String nage;// 性别
	private String nameInfor;// 个人信息
	private String ncode;// 常访人卡号
	private String nuid;// 常访人身份证号码
	private String renFiles;// 常访人身份证附件(表中存文件地址)
	private String addTime;// 添加时间
	private String updateTime;// 修改时间

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameInfor() {
		return nameInfor;
	}

	public void setNameInfor(String nameInfor) {
		this.nameInfor = nameInfor;
	}

	public String getOftenName() {
		return oftenName;
	}

	public void setOftenName(String oftenName) {
		this.oftenName = oftenName;
	}

	public String getNuid() {
		return nuid;
	}

	public void setNuid(String nuid) {
		this.nuid = nuid;
	}

	public String getRenFiles() {
		return renFiles;
	}

	public void setRenFiles(String renFiles) {
		this.renFiles = renFiles;
	}

	public String getNage() {
		return nage;
	}

	public void setNage(String nage) {
		this.nage = nage;
	}

	public String getNcode() {
		return ncode;
	}

	public void setNcode(String ncode) {
		this.ncode = ncode;
	}

}
