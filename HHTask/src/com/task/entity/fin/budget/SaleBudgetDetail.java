package com.task.entity.fin.budget;

import java.io.Serializable;

/**
 * 销售收入明细表（ta_fin_saleBudgetDetail）
 * 
 * @author jhh
 * 
 */
public class SaleBudgetDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String customer;// 客户
	private String carStyle;// 车型
	private String markID;// 件号
	private String goodsName;// 品名
	private Float onePrice;// 单价（不含税）
	private String isIncludBudget;//是否计入预算总额中（yes/no）
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	private Float forecastCount;// 预测产量
	private Float saleReven;// 销售收入（单个件号）
	private Float saleRate;// 占月度销售收入比例
	private String more;// 备注
	private SaleBudget saleBudget;// 外键 销售收入

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public String getMarkID() {
		return markID;
	}

	public void setMarkID(String markID) {
		this.markID = markID;
	}

	public Float getOnePrice() {
		if (null != onePrice) {
			return Float.parseFloat(String.format("%.2f", onePrice));
		}
		return onePrice;
	}

	public void setOnePrice(Float onePrice) {
		this.onePrice = onePrice;
	}

	public Float getForecastCount() {
		if (null != forecastCount) {
			return Float.parseFloat(String.format("%.2f", forecastCount));
		}
		return forecastCount;
	}

	public void setForecastCount(Float forecastCount) {
		this.forecastCount = forecastCount;
	}

	public Float getSaleReven() {
		if (null != saleReven) {
			return Float.parseFloat(String.format("%.2f", saleReven));
		}
		return saleReven;
	}

	public void setSaleReven(Float saleReven) {
		this.saleReven = saleReven;
	}

	public Float getSaleRate() {
		if (null != saleRate) {
			return Float.parseFloat(String.format("%.4f", saleRate));
		}
		return saleRate;
	}

	public void setSaleRate(Float saleRate) {
		this.saleRate = saleRate;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public SaleBudget getSaleBudget() {
		return saleBudget;
	}

	public void setSaleBudget(SaleBudget saleBudget) {
		this.saleBudget = saleBudget;
	}

	public String getIsIncludBudget() {
		return isIncludBudget;
	}

	public void setIsIncludBudget(String isIncludBudget) {
		this.isIncludBudget = isIncludBudget;
	}

}
