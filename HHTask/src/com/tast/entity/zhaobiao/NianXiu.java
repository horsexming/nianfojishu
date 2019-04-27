package com.tast.entity.zhaobiao;

import java.io.Serializable;

public class NianXiu implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String gonghao;			//工号	
	private String name;			//姓名	
	private String namedept;        //员工部门
	private Float  yingde;      //应享受年休假；
	private Float  yuliu;      //上年余留
	private Float   kouchu; 		//统一扣除年休
	
	
	
	private Float    yiyue;
	private Float    eryue;
	private Float sanyue;
	private Float siyue;
	private Float wuyue;
	private Float  liuyue;
	private Float  qiyue;
	private Float  bayue;
	private Float  jiuyue;
	private Float shiyue;
	private Float shiyiyue;
	private Float shieryue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getYingde() {
		return yingde;
	}
	public void setYingde(Float yingde) {
		this.yingde = yingde;
	}
	public Float getYuliu() {
		return yuliu;
	}
	public void setYuliu(Float yuliu) {
		this.yuliu = yuliu;
	}
	public Float getKouchu() {
		return kouchu;
	}
	public void setKouchu(Float kouchu) {
		this.kouchu = kouchu;
	}
	public Float getYiyue() {
		return yiyue;
	}
	public void setYiyue(Float yiyue) {
		this.yiyue = yiyue;
	}
	public Float getEryue() {
		return eryue;
	}
	public void setEryue(Float eryue) {
		this.eryue = eryue;
	}
	public Float getSanyue() {
		return sanyue;
	}
	public void setSanyue(Float sanyue) {
		this.sanyue = sanyue;
	}
	public Float getSiyue() {
		return siyue;
	}
	public void setSiyue(Float siyue) {
		this.siyue = siyue;
	}
	public Float getWuyue() {
		return wuyue;
	}
	public void setWuyue(Float wuyue) {
		this.wuyue = wuyue;
	}
	public Float getLiuyue() {
		return liuyue;
	}
	public void setLiuyue(Float liuyue) {
		this.liuyue = liuyue;
	}
	public Float getQiyue() {
		return qiyue;
	}
	public void setQiyue(Float qiyue) {
		this.qiyue = qiyue;
	}
	public Float getBayue() {
		return bayue;
	}
	public void setBayue(Float bayue) {
		this.bayue = bayue;
	}
	public Float getJiuyue() {
		return jiuyue;
	}
	public void setJiuyue(Float jiuyue) {
		this.jiuyue = jiuyue;
	}
	public Float getShiyue() {
		return shiyue;
	}
	public void setShiyue(Float shiyue) {
		this.shiyue = shiyue;
	}
	public Float getShiyiyue() {
		return shiyiyue;
	}
	public void setShiyiyue(Float shiyiyue) {
		this.shiyiyue = shiyiyue;
	}
	public Float getShieryue() {
		return shieryue;
	}
	public void setShieryue(Float shieryue) {
		this.shieryue = shieryue;
	}
	public String getNamedept() {
		return namedept;
	}
	public void setNamedept(String namedept) {
		this.namedept = namedept;
	}
	
	
	
	

}
