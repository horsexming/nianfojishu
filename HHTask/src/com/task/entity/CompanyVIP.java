package com.task.entity;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞
 *	表（ta_CompanyVIP）
 */
public class CompanyVIP implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String vipNo;//会员编号
	private String name;//企业名称;
	private String foundingTime;//成立时间
	private String writeTime;// 填表日期
	private Float rcapital;//注册资金;
	private String number;//营业执照注册号
	private String typexz;//企业性质
	private String industry;//所属行业
	private String range;//范围
	private String address;//企业地址
	private String zipcode ;//企业邮编
	private String website;//企业网址
	private String email;//
	private String synopsis;//企业简介
	private String honor;//企业荣誉
	private Float lastsales;//上年度营业总额
	private Float sales;//资产总额
	private Float totaltax;//交税总额
	private Integer enumber;//员工人数
	private String attachmentName;//附件名称;
	private CompanyBoss companyboss;//公司负责人资料
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFoundingTime() {
		return foundingTime;
	}
	public void setFoundingTime(String foundingTime) {
		this.foundingTime = foundingTime;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public Float getRcapital() {
		return rcapital;
	}
	public void setRcapital(Float rcapital) {
		this.rcapital = rcapital;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTypexz() {
		return typexz;
	}
	public void setTypexz(String typexz) {
		this.typexz = typexz;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public Float getLastsales() {
		return lastsales;
	}
	public void setLastsales(Float lastsales) {
		this.lastsales = lastsales;
	}
	public Float getSales() {
		return sales;
	}
	public void setSales(Float sales) {
		this.sales = sales;
	}
	public Float getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(Float totaltax) {
		this.totaltax = totaltax;
	}
	
	public Integer getEnumber() {
		return enumber;
	}
	public void setEnumber(Integer enumber) {
		this.enumber = enumber;
	}
	public CompanyBoss getCompanyboss() {
		return companyboss;
	}
	public void setCompanyboss(CompanyBoss companyboss) {
		this.companyboss = companyboss;
	}
	public String getVipNo() {
		return vipNo;
	}
	public void setVipNo(String vipNo) {
		this.vipNo = vipNo;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	
	
}
