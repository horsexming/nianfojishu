package com.task.entity.payment;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.fin.BaoxiaoDan;
import com.task.util.FieldMeta;

/***
 * 付款凭证总表
 * 
 * @author Administrator
 * @表名：ta_PaymentVoucher
 */
public class PaymentVoucher implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	@FieldMeta(name = "编号")
	private String number;// 编号
	@FieldMeta(name = "借款单位名称")
	private String unitname;// 借款单位名称
	private String relationclient;// 收款单位
	@FieldMeta(name = "借款人")
	private String approvalApplier;// 借款人
	private String approvaldept;// 借款人所在部门
	private String accreditationnum;// 评审编号
	private String contractnum;// 合同号
	@FieldMeta(name = "合同创建日期")
	private String contractdate;// 创建合同日期

	private String approvalcontent;// 审批内容
	private String approvalexplain;// 审批说明
	private String reviewer;// 审批人
	private String approvalStatus;// 批准（审批状态 通过、打回）
	private String paymentStatus;// 付款状态（(已付款、未付清、未付款)）
	@FieldMeta(name = "借款金额")
	private Float voucherMoney;// 借款金额
	private Float prepaidMoney;// 已付金额
	private String voucherNature;// 借款性质
	private String voucherbasis;// 借款依据(合同、发票、协议、通知、其他依据)
	private Integer heTongId;// 对应合同id
	private String voucherway;// 借款方式
	private String vouchersituation;// 借款情况
	private String vouchercondition;// 借款条件
	private String category;// 类别
	private String voucherdate;// 付款日期
	private Integer epId; // 审批ID
	private String printStatus;// 打印状态
	private Set paymentDetail;
	private Set<BaoxiaoDan> baoxiaoDanSet;// 报销明细

	public Float getPrepaidMoney() {
		return prepaidMoney;
	}

	public void setPrepaidMoney(Float prepaidMoney) {
		this.prepaidMoney = prepaidMoney;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getApprovalApplier() {
		return approvalApplier;
	}

	public void setApprovalApplier(String approvalApplier) {
		this.approvalApplier = approvalApplier;
	}

	public String getVoucherbasis() {
		return voucherbasis;
	}

	public void setVoucherbasis(String voucherbasis) {
		this.voucherbasis = voucherbasis;
	}

	@JSONField(serialize = false)
	public Set getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(Set paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	public Float getVoucherMoney() {
		return voucherMoney;
	}

	public void setVoucherMoney(Float voucherMoney) {
		this.voucherMoney = voucherMoney;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getRelationclient() {
		return relationclient;
	}

	public void setRelationclient(String relationclient) {
		this.relationclient = relationclient;
	}

	public String getAccreditationnum() {
		return accreditationnum;
	}

	public void setAccreditationnum(String accreditationnum) {
		this.accreditationnum = accreditationnum;
	}

	public String getContractnum() {
		return contractnum;
	}

	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}

	public String getContractdate() {
		return contractdate;
	}

	public void setContractdate(String contractdate) {
		this.contractdate = contractdate;
	}

	public String getApprovalcontent() {
		return approvalcontent;
	}

	public void setApprovalcontent(String approvalcontent) {
		this.approvalcontent = approvalcontent;
	}

	public String getApprovalexplain() {
		return approvalexplain;
	}

	public void setApprovalexplain(String approvalexplain) {
		this.approvalexplain = approvalexplain;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getVoucherNature() {
		return voucherNature;
	}

	public void setVoucherNature(String voucherNature) {
		this.voucherNature = voucherNature;
	}

	public String getVoucherway() {
		return voucherway;
	}

	public void setVoucherway(String voucherway) {
		this.voucherway = voucherway;
	}

	public String getVouchersituation() {
		return vouchersituation;
	}

	public void setVouchersituation(String vouchersituation) {
		this.vouchersituation = vouchersituation;
	}

	public String getVouchercondition() {
		return vouchercondition;
	}

	public void setVouchercondition(String vouchercondition) {
		this.vouchercondition = vouchercondition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVoucherdate() {
		return voucherdate;
	}

	public void setVoucherdate(String voucherdate) {
		this.voucherdate = voucherdate;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getApprovaldept() {
		return approvaldept;
	}

	public void setApprovaldept(String approvaldept) {
		this.approvaldept = approvaldept;
	}

	@JSONField(serialize = false)
	public Set<BaoxiaoDan> getBaoxiaoDanSet() {
		return baoxiaoDanSet;
	}

	public void setBaoxiaoDanSet(Set<BaoxiaoDan> baoxiaoDanSet) {
		this.baoxiaoDanSet = baoxiaoDanSet;
	}

	public Integer getHeTongId() {
		return heTongId;
	}

	public void setHeTongId(Integer heTongId) {
		this.heTongId = heTongId;
	}

}