package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 标示贴 报废原因表
 *   ta_baofei
 */
public class Baofei implements  Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private String yuanyin;// 报废原因
	private String leixing;// 报废类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYuanyin() {
		return yuanyin;
	}

	public void setYuanyin(String yuanyin) {
		this.yuanyin = yuanyin;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

}
