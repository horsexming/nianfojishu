package com.task.entity.caiwu.baobiao;

import java.io.Serializable;

/**
 * 算账日期表
 * 
 * @表名 ta_baobiao_AccountsDate
 * @author wxf
 */
public class AccountsDate implements Serializable{

	private Integer id;
	private Integer jihao;// 每月的几号
	private String goodsType;// 库存成分记账方式(先进先出(xjxc)、先进后出(xjhc)、移动加权平均(allAgv)、加权平均(monthAgv))

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJihao() {
		return jihao;
	}

	public void setJihao(Integer jihao) {
		this.jihao = jihao;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

}