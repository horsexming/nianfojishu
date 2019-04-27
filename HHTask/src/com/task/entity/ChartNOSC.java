package com.task.entity;

import java.io.Serializable;

/**
 * 图号表:(ta_ChartNOSC)
 * @author 王晓飞
 *
 */
public class ChartNOSC implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;//图号类别
	private String chartNO;//图号
	private String chartcode;//不带点的
	private String sjsqUsers;//实际申请人
	private String cpcode;//所属产品编码(产品名称)/所属辅料类别
	private String isuse;//是否使用(YES/NO);
	private String remak;//备注
	private ChartNOSQ chartnosq ;//对应 图号申请表(多对一)
	private String sqNo;//申请编号 工号+yyyyMM+0000
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
	public String getChartNO() {
		return chartNO;
	}
	public void setChartNO(String chartNO) {
		this.chartNO = chartNO;
	}
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public String getChartcode() {
		return chartcode;
	}
	public void setChartcode(String chartcode) {
		this.chartcode = chartcode;
	}
	public ChartNOSQ getChartnosq() {
		return chartnosq;
	}
	public void setChartnosq(ChartNOSQ chartnosq) {
		this.chartnosq = chartnosq;
	}
	public String getSjsqUsers() {
		return sjsqUsers;
	}
	public void setSjsqUsers(String sjsqUsers) {
		this.sjsqUsers = sjsqUsers;
	}
	public String getCpcode() {
		return cpcode;
	}
	public void setCpcode(String cpcode) {
		this.cpcode = cpcode;
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
