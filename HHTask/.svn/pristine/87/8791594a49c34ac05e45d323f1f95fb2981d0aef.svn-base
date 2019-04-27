package com.task.entity.fin.budget;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 销售收入预算表（ta_fin_saleBudget）
 * 
 * @author jhh
 * 
 */
public class SaleBudget implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	@FieldMeta(name="预算月份")
	private String planMonth;// 预算月份
	@FieldMeta(name="销售金额")
	private Float saleMoney;// 销售金额
	@FieldMeta(name="输入时间")
	private String inputTime;// 输入时间
	@FieldMeta(name="录入人姓名")
	private String userName;// 输入人姓名
	@FieldMeta(name="状态")
	private String status;// 状态
	private String examPath;// 审批流程
	private String more;// 备注
	private String barcode;//条码
	private Integer epId;//流程id
	private Set<SaleBudgetDetail> saleBudgetDetail;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}

	public Float getSaleMoney() {
		if (null != saleMoney) {
			return Float.parseFloat(String.format("%.2f", saleMoney));
		}
		return saleMoney;
	}

	public void setSaleMoney(Float saleMoney) {
		this.saleMoney = saleMoney;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExamPath() {
		return examPath;
	}

	public void setExamPath(String examPath) {
		this.examPath = examPath;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Set<SaleBudgetDetail> getSaleBudgetDetail() {
		return saleBudgetDetail;
	}

	public void setSaleBudgetDetail(Set<SaleBudgetDetail> saleBudgetDetail) {
		this.saleBudgetDetail = saleBudgetDetail;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}


}
