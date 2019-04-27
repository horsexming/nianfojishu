package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 页面传值使用
 */
public class KuweiSongHuoDan implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer kuweiId;// 库位Id
	private String kuweiNo;// 库位号
	private Double number;// 库位里存放的某个零件的数量
	private String towCode;// 二维码

	public Integer getKuweiId() {
		return kuweiId;
	}

	public void setKuweiId(Integer kuweiId) {
		this.kuweiId = kuweiId;
	}

	public String getKuweiNo() {
		return kuweiNo;
	}

	public void setKuweiNo(String kuweiNo) {
		this.kuweiNo = kuweiNo;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getTowCode() {
		return towCode;
	}

	public void setTowCode(String towCode) {
		this.towCode = towCode;
	}

}
