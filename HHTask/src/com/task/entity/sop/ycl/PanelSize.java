package com.task.entity.sop.ycl;

import java.io.Serializable;

/**
 * 板材粉末表
 * @author (ta_PanelSize)
 *
 */
public class PanelSize implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String caizhi;//材质
	private String type;//类别；
	private Float fristThickness;//起始厚度
	private Float endThickness;//截止厚度
	private Float thislength;//长
	private Float thiswideth;//宽
	private String size;//尺寸
	private String density;//密度
	private String fenmo1;//粉末1
	private String fenmo2;//粉末2
	private Float areakg1;//每公斤喷粉面积
	private Integer miancount1;//喷粉面数
	private Float areakg2;//每公斤喷粉面积
	private Integer miancount2;//喷粉面数
	private String remarks;//备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaizhi() {
		return caizhi;
	}
	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Float getFristThickness() {
		return fristThickness;
	}
	public void setFristThickness(Float fristThickness) {
		this.fristThickness = fristThickness;
	}
	
	public Float getThiswideth() {
		return thiswideth;
	}
	public void setThiswideth(Float thiswideth) {
		this.thiswideth = thiswideth;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Float getEndThickness() {
		return endThickness;
	}
	public void setEndThickness(Float endThickness) {
		this.endThickness = endThickness;
	}
	public Float getThislength() {
		return thislength;
	}
	public void setThislength(Float thislength) {
		this.thislength = thislength;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFenmo1() {
		return fenmo1;
	}
	public void setFenmo1(String fenmo1) {
		this.fenmo1 = fenmo1;
	}
	public String getFenmo2() {
		return fenmo2;
	}
	public void setFenmo2(String fenmo2) {
		this.fenmo2 = fenmo2;
	}
	public Float getAreakg1() {
		return areakg1;
	}
	public void setAreakg1(Float areakg1) {
		this.areakg1 = areakg1;
	}
	public Integer getMiancount1() {
		return miancount1;
	}
	public void setMiancount1(Integer miancount1) {
		this.miancount1 = miancount1;
	}
	public Float getAreakg2() {
		return areakg2;
	}
	public void setAreakg2(Float areakg2) {
		this.areakg2 = areakg2;
	}
	public Integer getMiancount2() {
		return miancount2;
	}
	public void setMiancount2(Integer miancount2) {
		this.miancount2 = miancount2;
	}
	
	
	 
	
	

}