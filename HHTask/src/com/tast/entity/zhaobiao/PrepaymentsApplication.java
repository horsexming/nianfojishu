package com.tast.entity.zhaobiao;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 预付款申请单
 * @author LiCong
 * @表名ta_zh_PrepaymentsApplication
 */

public class PrepaymentsApplication implements Serializable {
	private static final long serialVersionUID = 1L;
     private Integer id;
     @FieldMeta(name = "编号")
     private String number;//编号(自动生成)
     @FieldMeta(name = "订单编号")
     private String poNumber;//订单编号
     @FieldMeta(name = "预付项目名称")
     private String yyName;//预付项目名称
     private Double allMoney;//采购总额
     @FieldMeta(name = "采购总额")
     private String allMoneys;//采购总额
     @FieldMeta(name = "预付比例")
     private Float yfbl;//预付比例
     private Double yfMoney;//预付金额
     @FieldMeta(name = "预付金额")
     private String yfMoneys;//预付金额
     @FieldMeta(name = "预付金额(大写)")
     private String yfMoneyDX;//预付金额(大写)
     @FieldMeta(name = "申请人")
     private String jbName;//申请人
     private String qsName;//签收人
     private String pjType;//票据类别(支票/电汇)
     @FieldMeta(name = "预计报销日期")
     private String expectedTime;//预计报销日期
     @FieldMeta(name = "添加时间")
     private String addDate;//添加日期
     private String addTime;//添加时间
     private String addName;//添加人
     @FieldMeta(name = "添加人部门")
     private String addDept;//添加人部门
     @FieldMeta(name = "申请状态")
     private String status;//申请状态(待完善/未审批/审批中/同意/打回)
     private Integer epId;//审批相关
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getYyName() {
		return yyName;
	}
	public void setYyName(String yyName) {
		this.yyName = yyName;
	}
	public Double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}
	public String getAllMoneys() {
		return allMoneys;
	}
	public void setAllMoneys(String allMoneys) {
		this.allMoneys = allMoneys;
	}
	public Float getYfbl() {
		return yfbl;
	}
	public void setYfbl(Float yfbl) {
		this.yfbl = yfbl;
	}
	public Double getYfMoney() {
		return yfMoney;
	}
	public void setYfMoney(Double yfMoney) {
		this.yfMoney = yfMoney;
	}
	public String getYfMoneys() {
		return yfMoneys;
	}
	public void setYfMoneys(String yfMoneys) {
		this.yfMoneys = yfMoneys;
	}
	public String getYfMoneyDX() {
		return yfMoneyDX;
	}
	public void setYfMoneyDX(String yfMoneyDX) {
		this.yfMoneyDX = yfMoneyDX;
	}
	public String getJbName() {
		return jbName;
	}
	public void setJbName(String jbName) {
		this.jbName = jbName;
	}
	public String getQsName() {
		return qsName;
	}
	public void setQsName(String qsName) {
		this.qsName = qsName;
	}
	public String getPjType() {
		return pjType;
	}
	public void setPjType(String pjType) {
		this.pjType = pjType;
	}
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public String getAddDept() {
		return addDept;
	}
	public void setAddDept(String addDept) {
		this.addDept = addDept;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
     
}