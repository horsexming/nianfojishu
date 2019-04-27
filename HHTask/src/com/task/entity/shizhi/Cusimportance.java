package com.task.entity.shizhi;

import java.io.Serializable;

/**
 * 客户重要系数
 * 
 * @表名：ta_sk_Cusimportance
 * @author 唐晓斌
 */
public class Cusimportance implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cuName;//客户名称
	private Float improtance;// 客户重要系数
	private Float cuMonthSale;// 当月销售比
	private Float cuYearSale;// 当年销售比
	private Float threeYearsExSale;// 三年销售预期比

	public Cusimportance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getImprotance() {
		return improtance;
	}

	public void setImprotance(Float improtance) {
		this.improtance = improtance;
	}

	public Float getCuMonthSale() {
		return cuMonthSale;
	}

	public void setCuMonthSale(Float cuMonthSale) {
		this.cuMonthSale = cuMonthSale;
	}

	public Float getCuYearSale() {
		return cuYearSale;
	}

	public void setCuYearSale(Float cuYearSale) {
		this.cuYearSale = cuYearSale;
	}

	public Float getThreeYearsExSale() {
		return threeYearsExSale;
	}

	public void setThreeYearsExSale(Float threeYearsExSale) {
		this.threeYearsExSale = threeYearsExSale;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}




}
