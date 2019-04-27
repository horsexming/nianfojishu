package com.task.entity;

import java.io.Serializable;

/**
 * 报账明细表
 * @author 贾辉辉
 *数据库表  oa_business
 */
public class Oabusiness  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String oaproductnumber;//物品编号（OFT+年月+序号）与OaappDetail 对应
	private String oaproductName;//物品名称
	private String oaspecification;//规格
	private String oaunit;//单位
	private Float oaquantity;//数量
	private Float oaunitprice;//单价
	private Float oatotalMon;//总金额=单价×数量
	private String oastatus;//状态（审核中，可打印，可付款，打回，已付款）
	private String oausername;//用户名
	private String oafastatus;//
	private String oauniNum; //统一编号 外键（部门编码——年月）
	private String oadate;//日期
	private String oafactory;//厂家
	private String oainvoicenumber;//发票号
	private String oacontractNumber;//档案号
	private String oaremarks;
	private String oatemporarystatus;
	private String oafkpznumber;//付款凭证编号 外键
	private String oahetongnumber;//合同编号
	
	private Oareimbursement oarbs;//报账表 多对一的管理
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOaproductnumber() {
		return oaproductnumber;
	}
	public void setOaproductnumber(String oaproductnumber) {
		this.oaproductnumber = oaproductnumber;
	}
	public Oareimbursement getOarbs() {
		return oarbs;
	}
	public void setOarbs(Oareimbursement oarbs) {
		this.oarbs = oarbs;
	}
	public String getOaproductName() {
		return oaproductName;
	}
	public void setOaproductName(String oaproductName) {
		this.oaproductName = oaproductName;
	}
	public String getOaspecification() {
		return oaspecification;
	}
	public void setOaspecification(String oaspecification) {
		this.oaspecification = oaspecification;
	}
	public String getOaunit() {
		return oaunit;
	}
	public void setOaunit(String oaunit) {
		this.oaunit = oaunit;
	}
	
	public Float getOaquantity() {
		return oaquantity;
	}
	public void setOaquantity(Float oaquantity) {
		this.oaquantity = oaquantity;
	}
	public Float getOaunitprice() {
		return oaunitprice;
	}
	public void setOaunitprice(Float oaunitprice) {
		this.oaunitprice = oaunitprice;
	}
	public Float getOatotalMon() {
		return oatotalMon;
	}
	public void setOatotalMon(Float oatotalMon) {
		this.oatotalMon = oatotalMon;
	}
	public String getOastatus() {
		return oastatus;
	}
	public void setOastatus(String oastatus) {
		this.oastatus = oastatus;
	}
	public String getOausername() {
		return oausername;
	}
	public void setOausername(String oausername) {
		this.oausername = oausername;
	}
	public String getOafastatus() {
		return oafastatus;
	}
	public void setOafastatus(String oafastatus) {
		this.oafastatus = oafastatus;
	}
	public String getOauniNum() {
		return oauniNum;
	}
	public void setOauniNum(String oauniNum) {
		this.oauniNum = oauniNum;
	}
	public String getOadate() {
		return oadate;
	}
	public void setOadate(String oadate) {
		this.oadate = oadate;
	}
	public String getOaremarks() {
		return oaremarks;
	}
	public void setOaremarks(String oaremarks) {
		this.oaremarks = oaremarks;
	}
	public String getOatemporarystatus() {
		return oatemporarystatus;
	}
	public void setOatemporarystatus(String oatemporarystatus) {
		this.oatemporarystatus = oatemporarystatus;
	}
	public String getOafactory() {
		return oafactory;
	}
	public void setOafactory(String oafactory) {
		this.oafactory = oafactory;
	}
	public String getOainvoicenumber() {
		return oainvoicenumber;
	}
	public void setOainvoicenumber(String oainvoicenumber) {
		this.oainvoicenumber = oainvoicenumber;
	}
	public String getOacontractNumber() {
		return oacontractNumber;
	}
	public void setOacontractNumber(String oacontractNumber) {
		this.oacontractNumber = oacontractNumber;
	}
	public String getOafkpznumber() {
		return oafkpznumber;
	}
	public void setOafkpznumber(String oafkpznumber) {
		this.oafkpznumber = oafkpznumber;
	}
	public String getOahetongnumber() {
		return oahetongnumber;
	}
	public void setOahetongnumber(String oahetongnumber) {
		this.oahetongnumber = oahetongnumber;
	}
	
	
}
