package com.task.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 正式和备货订单关联表（ta_ProductZsAboutBh）
 * 
 * @author Administrator
 *
 */
public class ProductZsAboutBh implements Serializable {
	private static final long serialVersionUID = 1L;
	public Integer id;
	public Integer zsProductId;// 正式产品Id
	public Integer bhProductId;// 备货产品Id
	public String markId;// 零件
	public String ywMarkId;// 业务件号
	public Float aboutCount;// 相关数量
	public String status;// 状态（未审批,同意，打回）
	// 对应的内部计划（页面传值）
	public List<InternalZsAboutBh> izbLIst;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZsProductId() {
		return zsProductId;
	}

	public void setZsProductId(Integer zsProductId) {
		this.zsProductId = zsProductId;
	}

	public Integer getBhProductId() {
		return bhProductId;
	}

	public void setBhProductId(Integer bhProductId) {
		this.bhProductId = bhProductId;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public Float getAboutCount() {
		return aboutCount;
	}

	public void setAboutCount(Float aboutCount) {
		this.aboutCount = aboutCount;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public List<InternalZsAboutBh> getIzbLIst() {
		return izbLIst;
	}

	public void setIzbLIst(List<InternalZsAboutBh> izbLIst) {
		this.izbLIst = izbLIst;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
