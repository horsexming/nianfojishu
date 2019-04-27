package com.task.entity;

import java.util.Set;

public class Qualityto implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer Id;
	private Integer ybshu;      //样本数量
	private String productname; //产品名称
	private String kehu;		//产品客户
	private String bianhao;		//产品图号
	private String beizhu;		//备注
	private String addtime;		//添加时间
	private String leibie;     	//产品类别
	private String jcbh;        //检测编号
	private String statu;		//审批状态
	private Integer qualitychecktoid; //质检模具id
	private String pici;  //抽样批次
	private String yybz;   //引用标准
	private String kbz;  
	private String jlun;	//结论
	private String faan;    //处置方案
	private String shuoming1; //说明
	private String shuoming2; //说明
	private String shy;   //审核人员
	private String shr;		//审批人员
	
	private Set<Qualityta> qualityta;
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
	public String getBianhao() {
		return bianhao;
	}
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
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
	public Set<Qualityta> getQualityta() {
		return qualityta;
	}
	public void setQualityta(Set<Qualityta> qualityta) {
		this.qualityta = qualityta;
	}
	public String getLeibie() {
		return leibie;
	}
	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}
	public String getJcbh() {
		return jcbh;
	}
	public void setJcbh(String jcbh) {
		this.jcbh = jcbh;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public void setYbshu(Integer ybshu) {
		this.ybshu = ybshu;
	}
	public Integer getYbshu() {
		return ybshu;
	}
	public void setQualitychecktoid(Integer qualitychecktoid) {
		this.qualitychecktoid = qualitychecktoid;
	}
	public Integer getQualitychecktoid() {
		return qualitychecktoid;
	}
	public String getPici() {
		return pici;
	}
	public void setPici(String pici) {
		this.pici = pici;
	}
	public String getYybz() {
		return yybz;
	}
	public void setYybz(String yybz) {
		this.yybz = yybz;
	}
	public String getJlun() {
		return jlun;
	}
	public void setJlun(String jlun) {
		this.jlun = jlun;
	}
	public String getShuoming1() {
		return shuoming1;
	}
	public void setShuoming1(String shuoming1) {
		this.shuoming1 = shuoming1;
	}
	public String getShuoming2() {
		return shuoming2;
	}
	public void setShuoming2(String shuoming2) {
		this.shuoming2 = shuoming2;
	}
	public String getShy() {
		return shy;
	}
	public void setShy(String shy) {
		this.shy = shy;
	}
	public void setFaan(String faan) {
		this.faan = faan;
	}
	public String getFaan() {
		return faan;
	}
	public void setKbz(String kbz) {
		this.kbz = kbz;
	}
	public String getKbz() {
		return kbz;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShr() {
		return shr;
	} 
	
}
