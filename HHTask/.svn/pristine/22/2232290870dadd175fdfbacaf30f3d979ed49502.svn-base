package com.task.entity.fin;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.payment.PaymentVoucher;
import com.task.entity.sop.WaigouWaiweiPlan;

/**
 * 财务非生产性报销单（ta_fin_baoXiaoDan）
 * @author jhh
 *与报销明细一对多
 */
public class BaoxiaoDan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Set<BaoxiaoDetail> baoxiaoDetail;//报销明细
	private PaymentVoucher paymentVouchers;// 关联报销单
	private String shoukuanRen;//收款单位或个人
	private String dept;//报销人所属部门
	private String code;//报销人工号
	private String payStyle;//报销方式(现金、银行对公转账、归还借款、其他)
	private String baoxiaoClass;//报销科目（制造费用、管理费用、销售费用、财务费用）//不用
	private String explain;//说明
	private Double totalMoney;//合计金额
	private Float attachmentsCount;//附件张数
	private String attachmentsFile;//附件文件
	private String baoxiaoDate;//报销日期
	private String saveTime;//保存时间
	private String baoxiaoBarcode;//报销条码
	private String more;//备注
	private String baoxiaoren;//报销人
	private String deptManager;//部门主管
	private String finExam;//财务审核
	private String status;//报销单状态
	private String Deputymanager;//主管副总
	private String Genalmangager;//总经理
	private String currency;//币种
	private String planMonth;//计划月份（新添）
	private String isSelfDept;//是否跨部门报销（新添）（本部门/多部门）
	private String isPrint;//是否打印
	private String dealstatus;//(已处理、未处理)
	private String producestatus;//(生产、非生产、其他)
	//新添
	private String isTax;//是否增税发票
	private String invoiceNum;//发票号
	private String contract_Number;//合同号
	private String order_number;//订单号
	private Set<WaigouWaiweiPlan> waigouWaiweiPlans; 
	
	
	
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<BaoxiaoDetail> getBaoxiaoDetail() {
		return baoxiaoDetail;
	}
	public void setBaoxiaoDetail(Set<BaoxiaoDetail> baoxiaoDetail) {
		this.baoxiaoDetail = baoxiaoDetail;
	}
	public String getShoukuanRen() {
		return shoukuanRen;
	}
	public void setShoukuanRen(String shoukuanRen) {
		this.shoukuanRen = shoukuanRen;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Float getAttachmentsCount() {
		return attachmentsCount;
	}
	public void setAttachmentsCount(Float attachmentsCount) {
		this.attachmentsCount = attachmentsCount;
	}
	public String getAttachmentsFile() {
		return attachmentsFile;
	}
	public void setAttachmentsFile(String attachmentsFile) {
		this.attachmentsFile = attachmentsFile;
	}
	public String getBaoxiaoDate() {
		return baoxiaoDate;
	}
	public void setBaoxiaoDate(String baoxiaoDate) {
		this.baoxiaoDate = baoxiaoDate;
	}
	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	public String getBaoxiaoBarcode() {
		return baoxiaoBarcode;
	}
	public void setBaoxiaoBarcode(String baoxiaoBarcode) {
		this.baoxiaoBarcode = baoxiaoBarcode;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getBaoxiaoren() {
		return baoxiaoren;
	}
	public void setBaoxiaoren(String baoxiaoren) {
		this.baoxiaoren = baoxiaoren;
	}
	public String getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	public String getFinExam() {
		return finExam;
	}
	public void setFinExam(String finExam) {
		this.finExam = finExam;
	}
	public String getDeputymanager() {
		return Deputymanager;
	}
	public void setDeputymanager(String deputymanager) {
		Deputymanager = deputymanager;
	}
	public String getGenalmangager() {
		return Genalmangager;
	}
	public void setGenalmangager(String genalmangager) {
		Genalmangager = genalmangager;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBaoxiaoClass() {
		return baoxiaoClass;
	}
	public void setBaoxiaoClass(String baoxiaoClass) {
		this.baoxiaoClass = baoxiaoClass;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPlanMonth() {
		return planMonth;
	}
	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}
	public String getIsSelfDept() {
		return isSelfDept;
	}
	public void setIsSelfDept(String isSelfDept) {
		this.isSelfDept = isSelfDept;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getIsTax() {
		return isTax;
	}
	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public PaymentVoucher getPaymentVouchers() {
		return paymentVouchers;
	}
	public void setPaymentVouchers(PaymentVoucher paymentVouchers) {
		this.paymentVouchers = paymentVouchers;
	}
	public String getDealstatus() {
		return dealstatus;
	}
	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}
	public String getContract_Number() {
		return contract_Number;
	}
	public void setContract_Number(String contractNumber) {
		contract_Number = contractNumber;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String orderNumber) {
		order_number = orderNumber;
	}
	public String getProducestatus() {
		return producestatus;
	}
	public void setProducestatus(String producestatus) {
		this.producestatus = producestatus;
	}
	public Double getTotalMoney() {
		if(null!=totalMoney){
			return Double.parseDouble(String.format("%.2f", totalMoney));
		}
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Set<WaigouWaiweiPlan> getWaigouWaiweiPlans() {
		return waigouWaiweiPlans;
	}
	public void setWaigouWaiweiPlans(Set<WaigouWaiweiPlan> waigouWaiweiPlans) {
		this.waigouWaiweiPlans = waigouWaiweiPlans;
	}
	 
	
	
}
