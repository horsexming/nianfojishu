package com.task.entity;

import java.util.Set;

public class Qualitycheckto implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer Id;
	private String productname; //产品名称
	private String kehu;		//产品客户
	private String leibie;		//产品图号
	private String beizhu;		//备注
	private String addtime;		//添加时间
	private String yybz;    //引用标准
	private String shenpi;	//审批
	private String renyuan; //审查人员
	private String renyuangh;	//审查人员工号
	private String pici;	//审查批次
	private Set<Qualitycheckta> qualitycheckta;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getKehu() {
		return kehu;
	}
	public void setKehu(String kehu) {
		this.kehu = kehu;
	}
	public String getLeibie() {
		return leibie;
	}
	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public void setQualitycheckta(Set<Qualitycheckta> qualitycheckta) {
		this.qualitycheckta = qualitycheckta;
	}
	public Set<Qualitycheckta> getQualitycheckta() {
		return qualitycheckta;
	}
	public void setYybz(String yybz) {
		this.yybz = yybz;
	}
	public String getYybz() {
		return yybz;
	}
	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}
	public String getShenpi() {
		return shenpi;
	}
	
	public void setPici(String pici) {
		this.pici = pici;
	}
	public String getPici() {
		return pici;
	}
	public void setRenyuangh(String renyuangh) {
		this.renyuangh = renyuangh;
	}
	public String getRenyuangh() {
		return renyuangh;
	}
	public void setRenyuan(String renyuan) {
		this.renyuan = renyuan;
	}
	public String getRenyuan() {
		return renyuan;
	}
	
}
