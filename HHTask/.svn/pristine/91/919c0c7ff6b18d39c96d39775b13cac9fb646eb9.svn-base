package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * 图号申请表:(ta_ChartNOSQ)
 * 
 * @author 王晓飞
 * 
 */
public class ChartNOSQ implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name = "图号类别")
	private String type;// 图号类别
	private Integer categoryId;// 对应类别Id
	@FieldMeta(name = "申请数量")
	private Integer sqNum;// 申请数量;
	@FieldMeta(name = "回收数量")
	private Integer hsNum;// 回收数量;
	@FieldMeta(name = "实际申请数量")
	private Integer sjsqNum;// 实际申请数量;
	@FieldMeta(name = "申请图号从")
	private String firstNo;// 申请图号从
	@FieldMeta(name = "到")
	private String endNo;// 到
	@FieldMeta(name = "申请时间")
	private String addTime;// 申请时间
	@FieldMeta(name = "申请人")
	private String addUser;// 申请人
	private Integer addUserId;// 申请人Id
	private String epstatus;// 审批状态
	private Integer epId;
	private String gztype;// 规则类型;
	private String cpcode;// 所属产品编码
	private String sqNo;//申请编号 工号+yyyyMM+0000
	private String remak;//备注
	private Set<ChartNOSC> chartNOSCSet;// 对应 图号表(一对多)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getFirstNo() {
		return firstNo;
	}

	public void setFirstNo(String firstNo) {
		this.firstNo = firstNo;
	}

	public String getEndNo() {
		return endNo;
	}

	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public String getEpstatus() {
		return epstatus;
	}

	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	@JSONField(serialize = false)
	public Set<ChartNOSC> getChartNOSCSet() {
		return chartNOSCSet;
	}

	public void setChartNOSCSet(Set<ChartNOSC> chartNOSCSet) {
		this.chartNOSCSet = chartNOSCSet;
	}

	public Integer getSqNum() {
		return sqNum;
	}

	public void setSqNum(Integer sqNum) {
		this.sqNum = sqNum;
	}

	public String getGztype() {
		return gztype;
	}

	public void setGztype(String gztype) {
		this.gztype = gztype;
	}

	public String getCpcode() {
		return cpcode;
	}

	public void setCpcode(String cpcode) {
		this.cpcode = cpcode;
	}

	public Integer getHsNum() {
		return hsNum;
	}

	public void setHsNum(Integer hsNum) {
		this.hsNum = hsNum;
	}

	public Integer getSjsqNum() {
		return sjsqNum;
	}

	public void setSjsqNum(Integer sjsqNum) {
		this.sjsqNum = sjsqNum;
	}

	public String getSqNo() {
		return sqNo;
	}

	public void setSqNo(String sqNo) {
		this.sqNo = sqNo;
	}

	public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}
 
}
