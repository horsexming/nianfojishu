package com.task.entity;

import java.io.Serializable;

/*
 * 项目管理 报价资料（公司核价）
 */
public class Companyprice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id; //	序号	int
	private String companypricegxuname;//	工序名称（直接人工和能耗、设备折旧）	Varchar
	private String companypricesbname;	//设备名称	Varchar
	private String companypricegl;	//功率	Varchar
	private Float companypricebcde;	//班产定额	Float
	private Integer companypriceczy;	//操作员	Varchar
	private Float companypricegsde;	//工时定额	Float
	private Float companypricedprice;	//电价	Float
	private Float companypricedlf;	//动力费	Float
	private Float companypricegrdj;	//人工单价	Float
	private Float companypricegrcb;	//人工成本	Float
	private Float companypricesbjz;	//设备价值	Float
	private Float companypricesbcz;	//设备残值	Float
	private Float companypricezjnx;	//折旧年限	Varchar
	private Float companypricebzgs;	//标准工时	Varchar
	private Float companypricegzr;	//工作日/年	Varchar
	private String companypricelyl;	//利用率	Varchar
	private Float companypricezjfy;	//折旧费用	Float
	private String companypricemoju;	//模具/检具名称（模具费用）	Varchar
	private Integer companypricecount;	//数量	int
	private Float companypriceprice;	//单价	Float
	private Float companypricemoney;	//金额	Float
	private Integer companypriceprojectcount;	//项目产量	int
	private Float companypricedjje;	//单件金额	Float
	private String companypricezizhi;	//自制或外购	Varchar
	private String companypricewaigou;	//外购/外协名称（外购/外协）	Varchar
	private String companypricelingjnumber;	//零件代号	Varchar
	private String companypricecijigys;	//次级供应商	Varchar
	private String companypricekehuzhiding;	//客户指定	Varchar
	private Float companypricedinge;	//定额	Float
	private Float companypricelingbjcb;	//零部件成本	Varchar
	
	private String companypriceyclph;//原材料牌号
	private String companypricehdjgc;//厚度及公差
	private String companypricelp;//料片长X宽
	private String companypricebchs;//板材损耗
	private String companypricenbsh;//内部损耗
	private String companypriceljjz;//零件净重
	private String companypricemidu;//密度
	private Float companypriceycldj;//原材料单价
	private Float companypricecldj;//废料单价
	
	private String companypriceclass;	//类别（直接人工河能耗、设备折旧、模具费用、外购外协）	Varchar 
	private String companypricestatus; //状态
	private String companypricejysnumber;	//项目建议书编号	Varchar
	private String companypriceprojectname;//项目名称
	private String companypricebjname;	//报价人员姓名	Varchar
	private String companypricebjdept;	//报价人员部门	Varchar
	private String companypricetime;	//时间	Varchar
	private String companypriceremarks;	//备注	Varchar
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanypricegxuname() {
		return companypricegxuname;
	}
	public void setCompanypricegxuname(String companypricegxuname) {
		this.companypricegxuname = companypricegxuname;
	}
	public String getCompanypricesbname() {
		return companypricesbname;
	}
	public void setCompanypricesbname(String companypricesbname) {
		this.companypricesbname = companypricesbname;
	}
	public String getCompanypricegl() {
		return companypricegl;
	}
	public void setCompanypricegl(String companypricegl) {
		this.companypricegl = companypricegl;
	}
	public Float getCompanypricebcde() {
		return companypricebcde;
	}
	public void setCompanypricebcde(Float companypricebcde) {
		this.companypricebcde = companypricebcde;
	}
	
	public Integer getCompanypriceczy() {
		return companypriceczy;
	}
	public void setCompanypriceczy(Integer companypriceczy) {
		this.companypriceczy = companypriceczy;
	}
	public Float getCompanypricegsde() {
		return companypricegsde;
	}
	public void setCompanypricegsde(Float companypricegsde) {
		this.companypricegsde = companypricegsde;
	}
	public Float getCompanypricedprice() {
		return companypricedprice;
	}
	public void setCompanypricedprice(Float companypricedprice) {
		this.companypricedprice = companypricedprice;
	}
	public Float getCompanypricedlf() {
		return companypricedlf;
	}
	public void setCompanypricedlf(Float companypricedlf) {
		this.companypricedlf = companypricedlf;
	}
	public Float getCompanypricegrdj() {
		return companypricegrdj;
	}
	public void setCompanypricegrdj(Float companypricegrdj) {
		this.companypricegrdj = companypricegrdj;
	}
	public Float getCompanypricegrcb() {
		return companypricegrcb;
	}
	public void setCompanypricegrcb(Float companypricegrcb) {
		this.companypricegrcb = companypricegrcb;
	}
	public Float getCompanypricesbjz() {
		return companypricesbjz;
	}
	public void setCompanypricesbjz(Float companypricesbjz) {
		this.companypricesbjz = companypricesbjz;
	}
	public Float getCompanypricesbcz() {
		return companypricesbcz;
	}
	public void setCompanypricesbcz(Float companypricesbcz) {
		this.companypricesbcz = companypricesbcz;
	}

	public Float getCompanypricezjnx() {
		return companypricezjnx;
	}
	public void setCompanypricezjnx(Float companypricezjnx) {
		this.companypricezjnx = companypricezjnx;
	}
	public Float getCompanypricebzgs() {
		return companypricebzgs;
	}
	public void setCompanypricebzgs(Float companypricebzgs) {
		this.companypricebzgs = companypricebzgs;
	}
	public Float getCompanypricegzr() {
		return companypricegzr;
	}
	public void setCompanypricegzr(Float companypricegzr) {
		this.companypricegzr = companypricegzr;
	}
	public String getCompanypricelyl() {
		return companypricelyl;
	}
	public void setCompanypricelyl(String companypricelyl) {
		this.companypricelyl = companypricelyl;
	}
	public Float getCompanypricezjfy() {
		return companypricezjfy;
	}
	public void setCompanypricezjfy(Float companypricezjfy) {
		this.companypricezjfy = companypricezjfy;
	}
	public String getCompanypricemoju() {
		return companypricemoju;
	}
	public void setCompanypricemoju(String companypricemoju) {
		this.companypricemoju = companypricemoju;
	}
	public Integer getCompanypricecount() {
		return companypricecount;
	}
	public void setCompanypricecount(Integer companypricecount) {
		this.companypricecount = companypricecount;
	}
	public Float getCompanypriceprice() {
		return companypriceprice;
	}
	public void setCompanypriceprice(Float companypriceprice) {
		this.companypriceprice = companypriceprice;
	}
	public Float getCompanypricemoney() {
		return companypricemoney;
	}
	public void setCompanypricemoney(Float companypricemoney) {
		this.companypricemoney = companypricemoney;
	}
	public Integer getCompanypriceprojectcount() {
		return companypriceprojectcount;
	}
	public void setCompanypriceprojectcount(Integer companypriceprojectcount) {
		this.companypriceprojectcount = companypriceprojectcount;
	}
	public Float getCompanypricedjje() {
		return companypricedjje;
	}
	public void setCompanypricedjje(Float companypricedjje) {
		this.companypricedjje = companypricedjje;
	}
	public String getCompanypricezizhi() {
		return companypricezizhi;
	}
	public void setCompanypricezizhi(String companypricezizhi) {
		this.companypricezizhi = companypricezizhi;
	}
	public String getCompanypricewaigou() {
		return companypricewaigou;
	}
	public void setCompanypricewaigou(String companypricewaigou) {
		this.companypricewaigou = companypricewaigou;
	}
	public String getCompanypricelingjnumber() {
		return companypricelingjnumber;
	}
	public void setCompanypricelingjnumber(String companypricelingjnumber) {
		this.companypricelingjnumber = companypricelingjnumber;
	}
	public String getCompanypricecijigys() {
		return companypricecijigys;
	}
	public void setCompanypricecijigys(String companypricecijigys) {
		this.companypricecijigys = companypricecijigys;
	}
	public String getCompanypricekehuzhiding() {
		return companypricekehuzhiding;
	}
	public void setCompanypricekehuzhiding(String companypricekehuzhiding) {
		this.companypricekehuzhiding = companypricekehuzhiding;
	}
	
	public Float getCompanypricedinge() {
		return companypricedinge;
	}
	public void setCompanypricedinge(Float companypricedinge) {
		this.companypricedinge = companypricedinge;
	}
	
	public Float getCompanypricelingbjcb() {
		return companypricelingbjcb;
	}
	public void setCompanypricelingbjcb(Float companypricelingbjcb) {
		this.companypricelingbjcb = companypricelingbjcb;
	}
	public String getCompanypriceclass() {
		return companypriceclass;
	}
	public void setCompanypriceclass(String companypriceclass) {
		this.companypriceclass = companypriceclass;
	}
	public String getCompanypricejysnumber() {
		return companypricejysnumber;
	}
	public void setCompanypricejysnumber(String companypricejysnumber) {
		this.companypricejysnumber = companypricejysnumber;
	}
	public String getCompanypricebjname() {
		return companypricebjname;
	}
	public void setCompanypricebjname(String companypricebjname) {
		this.companypricebjname = companypricebjname;
	}
	public String getCompanypricebjdept() {
		return companypricebjdept;
	}
	public void setCompanypricebjdept(String companypricebjdept) {
		this.companypricebjdept = companypricebjdept;
	}
	public String getCompanypricetime() {
		return companypricetime;
	}
	public void setCompanypricetime(String companypricetime) {
		this.companypricetime = companypricetime;
	}
	public String getCompanypriceremarks() {
		return companypriceremarks;
	}
	public void setCompanypriceremarks(String companypriceremarks) {
		this.companypriceremarks = companypriceremarks;
	}
	public String getCompanypricestatus() {
		return companypricestatus;
	}
	public void setCompanypricestatus(String companypricestatus) {
		this.companypricestatus = companypricestatus;
	}
	public String getCompanypriceprojectname() {
		return companypriceprojectname;
	}
	public void setCompanypriceprojectname(String companypriceprojectname) {
		this.companypriceprojectname = companypriceprojectname;
	}
	public String getCompanypriceyclph() {
		return companypriceyclph;
	}
	public void setCompanypriceyclph(String companypriceyclph) {
		this.companypriceyclph = companypriceyclph;
	}
	public String getCompanypricehdjgc() {
		return companypricehdjgc;
	}
	public void setCompanypricehdjgc(String companypricehdjgc) {
		this.companypricehdjgc = companypricehdjgc;
	}
	public String getCompanypricelp() {
		return companypricelp;
	}
	public void setCompanypricelp(String companypricelp) {
		this.companypricelp = companypricelp;
	}
	public String getCompanypricebchs() {
		return companypricebchs;
	}
	public void setCompanypricebchs(String companypricebchs) {
		this.companypricebchs = companypricebchs;
	}
	public String getCompanypricenbsh() {
		return companypricenbsh;
	}
	public void setCompanypricenbsh(String companypricenbsh) {
		this.companypricenbsh = companypricenbsh;
	}
	public String getCompanypriceljjz() {
		return companypriceljjz;
	}
	public void setCompanypriceljjz(String companypriceljjz) {
		this.companypriceljjz = companypriceljjz;
	}
	public String getCompanypricemidu() {
		return companypricemidu;
	}
	public void setCompanypricemidu(String companypricemidu) {
		this.companypricemidu = companypricemidu;
	}
	public Float getCompanypriceycldj() {
		return companypriceycldj;
	}
	public void setCompanypriceycldj(Float companypriceycldj) {
		this.companypriceycldj = companypriceycldj;
	}
	public Float getCompanypricecldj() {
		return companypricecldj;
	}
	public void setCompanypricecldj(Float companypricecldj) {
		this.companypricecldj = companypricecldj;
	}
	
}
