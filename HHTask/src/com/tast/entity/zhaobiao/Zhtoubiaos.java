package com.tast.entity.zhaobiao;

import java.io.Serializable;

public class Zhtoubiaos  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer tid[];
	private String tname[];//投标公司
	private String tmoney[];//投标金额
	private String tshuliang[];//投标数量
	private String ttime[];//投标世间安
	private Float tkong1[] ;//90天回款报价/含税（元/人民币）
	private Float  tkong2[];//60天回款报价/含税（元/人民币）
	private Float  tkong3[];//30天回款报价/含税（元/人民币）
	private String tkong4[];//负责人
	private String tkong5[];//联系方式
	private String tkong6[];
	private String tkong7[];
	private String tkong8[];
	private String tkong9[];
	private Integer tkong10[];
	private ZhUser zhUser;
	public Zhtoubiaos() {
		// TODO Auto-generated constructor stub
	}
	public Integer[] getTid() {
		return tid;
	}
	public void setTid(Integer[] tid) {
		this.tid = tid;
	}
	public String[] getTname() {
		return tname;
	}
	public void setTname(String[] tname) {
		this.tname = tname;
	}
	public String[] getTmoney() {
		return tmoney;
	}
	public void setTmoney(String[] tmoney) {
		this.tmoney = tmoney;
	}
	public String[] getTshuliang() {
		return tshuliang;
	}
	public void setTshuliang(String[] tshuliang) {
		this.tshuliang = tshuliang;
	}
	public String[] getTtime() {
		return ttime;
	}
	public void setTtime(String[] ttime) {
		this.ttime = ttime;
	}
	public Float[] getTkong1() {
		return tkong1;
	}
	public void setTkong1(Float[] tkong1) {
		this.tkong1 = tkong1;
	}
	public Float[] getTkong2() {
		return tkong2;
	}
	public void setTkong2(Float[] tkong2) {
		this.tkong2 = tkong2;
	}
	public Float[] getTkong3() {
		return tkong3;
	}
	public void setTkong3(Float[] tkong3) {
		this.tkong3 = tkong3;
	}
	public String[] getTkong4() {
		return tkong4;
	}
	public void setTkong4(String[] tkong4) {
		this.tkong4 = tkong4;
	}
	public String[] getTkong5() {
		return tkong5;
	}
	public void setTkong5(String[] tkong5) {
		this.tkong5 = tkong5;
	}
	public String[] getTkong6() {
		return tkong6;
	}
	public void setTkong6(String[] tkong6) {
		this.tkong6 = tkong6;
	}
	public String[] getTkong7() {
		return tkong7;
	}
	public void setTkong7(String[] tkong7) {
		this.tkong7 = tkong7;
	}
	public String[] getTkong8() {
		return tkong8;
	}
	public void setTkong8(String[] tkong8) {
		this.tkong8 = tkong8;
	}
	public String[] getTkong9() {
		return tkong9;
	}
	public void setTkong9(String[] tkong9) {
		this.tkong9 = tkong9;
	}
	public Integer[] getTkong10() {
		return tkong10;
	}
	public void setTkong10(Integer[] tkong10) {
		this.tkong10 = tkong10;
	}
	public ZhUser getZhUser() {
		return zhUser;
	}
	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}
	
}
