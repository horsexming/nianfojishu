package com.task.entity;

import java.io.Serializable;

/*
 *项目管理  报价资料总额表
 */
public class Companytotal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;	//序号	int
	private String companytotalprojectname;	//项目名称	varchar
	private String companytotalprojectjys;	//项目建议书编号	varchar
	private Float companytotalrgnhmoney;	//直接人工和能耗总和	Float
	private Float companytotalsbzjmoney;	//设备折旧总和	Float
	private Float companytotalmjfymoney;	//模具费用总和	Float
	private Float companytotalwgwxmoney;	//外购外协总和	Float
	private Float companytotalclcbmoney;	//材料成本总和	Float
	private Float companytotalcgmoney;	//采购成本	Float
	private Float companytotaljjrgmoney;	//间接人工	Float
	private Float companytotalnhmoney;	//能耗	Float
	private Float companytotalwhfymoney;	//维护费用	Float
	private Float companytotalxjmoney;	//小计	Float
	private Float companytotalysmoney;	//运输	Float
	private Float companytotalbzmoney;	//包装	Float
	private Float companytotalwlmoney;	//物流	Float
	private Float companytotalglfymoney;	//管理费用	Float
	private Float companytotalcwmoney;	//财务费用	Float
	private Float companytotallrmoney;	//利润	Float
 	private String companytotalghunit;	//供货单位	varchar
 	private String companytotalusername;//添加人用户名
	private String companytotalpartname;	//零件名称	Varchar
	private String companytotalstatus;	//状态	Varchar
	private String companytotaltime;	//创建时间	varchar
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanytotalprojectname() {
		return companytotalprojectname;
	}
	public void setCompanytotalprojectname(String companytotalprojectname) {
		this.companytotalprojectname = companytotalprojectname;
	}
	public String getCompanytotalprojectjys() {
		return companytotalprojectjys;
	}
	public void setCompanytotalprojectjys(String companytotalprojectjys) {
		this.companytotalprojectjys = companytotalprojectjys;
	}
	public Float getCompanytotalrgnhmoney() {
		return companytotalrgnhmoney;
	}
	public void setCompanytotalrgnhmoney(Float companytotalrgnhmoney) {
		this.companytotalrgnhmoney = companytotalrgnhmoney;
	}
	public Float getCompanytotalsbzjmoney() {
		return companytotalsbzjmoney;
	}
	public void setCompanytotalsbzjmoney(Float companytotalsbzjmoney) {
		this.companytotalsbzjmoney = companytotalsbzjmoney;
	}
	public Float getCompanytotalmjfymoney() {
		return companytotalmjfymoney;
	}
	public void setCompanytotalmjfymoney(Float companytotalmjfymoney) {
		this.companytotalmjfymoney = companytotalmjfymoney;
	}
	public Float getCompanytotalwgwxmoney() {
		return companytotalwgwxmoney;
	}
	public void setCompanytotalwgwxmoney(Float companytotalwgwxmoney) {
		this.companytotalwgwxmoney = companytotalwgwxmoney;
	}
	public Float getCompanytotalclcbmoney() {
		return companytotalclcbmoney;
	}
	public void setCompanytotalclcbmoney(Float companytotalclcbmoney) {
		this.companytotalclcbmoney = companytotalclcbmoney;
	}
	public Float getCompanytotalcgmoney() {
		return companytotalcgmoney;
	}
	public void setCompanytotalcgmoney(Float companytotalcgmoney) {
		this.companytotalcgmoney = companytotalcgmoney;
	}
	public Float getCompanytotaljjrgmoney() {
		return companytotaljjrgmoney;
	}
	public void setCompanytotaljjrgmoney(Float companytotaljjrgmoney) {
		this.companytotaljjrgmoney = companytotaljjrgmoney;
	}
	public Float getCompanytotalnhmoney() {
		return companytotalnhmoney;
	}
	public void setCompanytotalnhmoney(Float companytotalnhmoney) {
		this.companytotalnhmoney = companytotalnhmoney;
	}
	public Float getCompanytotalwhfymoney() {
		return companytotalwhfymoney;
	}
	public void setCompanytotalwhfymoney(Float companytotalwhfymoney) {
		this.companytotalwhfymoney = companytotalwhfymoney;
	}
	public Float getCompanytotalxjmoney() {
		return companytotalxjmoney;
	}
	public void setCompanytotalxjmoney(Float companytotalxjmoney) {
		this.companytotalxjmoney = companytotalxjmoney;
	}
	public Float getCompanytotalysmoney() {
		return companytotalysmoney;
	}
	public void setCompanytotalysmoney(Float companytotalysmoney) {
		this.companytotalysmoney = companytotalysmoney;
	}
	public Float getCompanytotalbzmoney() {
		return companytotalbzmoney;
	}
	public void setCompanytotalbzmoney(Float companytotalbzmoney) {
		this.companytotalbzmoney = companytotalbzmoney;
	}
	public Float getCompanytotalwlmoney() {
		return companytotalwlmoney;
	}
	public void setCompanytotalwlmoney(Float companytotalwlmoney) {
		this.companytotalwlmoney = companytotalwlmoney;
	}
	public Float getCompanytotalglfymoney() {
		return companytotalglfymoney;
	}
	public void setCompanytotalglfymoney(Float companytotalglfymoney) {
		this.companytotalglfymoney = companytotalglfymoney;
	}
	public Float getCompanytotalcwmoney() {
		return companytotalcwmoney;
	}
	public void setCompanytotalcwmoney(Float companytotalcwmoney) {
		this.companytotalcwmoney = companytotalcwmoney;
	}
	public Float getCompanytotallrmoney() {
		return companytotallrmoney;
	}
	public void setCompanytotallrmoney(Float companytotallrmoney) {
		this.companytotallrmoney = companytotallrmoney;
	}
	public String getCompanytotalghunit() {
		return companytotalghunit;
	}
	public void setCompanytotalghunit(String companytotalghunit) {
		this.companytotalghunit = companytotalghunit;
	}
	public String getCompanytotalpartname() {
		return companytotalpartname;
	}
	public void setCompanytotalpartname(String companytotalpartname) {
		this.companytotalpartname = companytotalpartname;
	}
	public String getCompanytotalstatus() {
		return companytotalstatus;
	}
	public void setCompanytotalstatus(String companytotalstatus) {
		this.companytotalstatus = companytotalstatus;
	}
	public String getCompanytotaltime() {
		return companytotaltime;
	}
	public void setCompanytotaltime(String companytotaltime) {
		this.companytotaltime = companytotaltime;
	}
	public String getCompanytotalusername() {
		return companytotalusername;
	}
	public void setCompanytotalusername(String companytotalusername) {
		this.companytotalusername = companytotalusername;
	}
	
	
	
	
	
	
}
