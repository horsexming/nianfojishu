package com.task.entity.payment;


/**
 * 资金使用明细表:(ta_FundApplyDetailed)
 * @author wxf
 *
 */
public class FundApplyDetailed implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private FundApply fundApply;//对应资金使用申请单信息
	private String zjStyle;//资金使用科目
	private Integer fk_SubBudgetRateId;//科目id
	private String zjStyleMx;//资金使用科目明细
	private String markId;//件号
	private String proname;//零件名称
	private String saveTime;//保存时间
	private String more;//备注
	private String budgetDept;//申请部门(承担部门)
	private String status;//状态（confirm/print） 判断是否跨部门审核
	private Integer deptMonthBudgetID;//申报项目对应id
	private String deptMonthBudgetName;//申报项目对应内容
	private String planMonth;//计划月份(与报销单信息里面的计划月份对应)
	
	private String goodsStoreMarkId;//件号
	private String goodsStoreLot;//批次
	private Float goodsStoreCount;//数量
	private String processesNo;//工序号
	private String processesName;//工序名
	private String invoiceNo;//发票号
	private Integer goodsStoreId;//入库id标识
	private String pay_use;//摘要
	private Double voucherMoney;//总金额
//	private Double backMoney;//还款金额
	private String  partName;//零件名称
	
	/*跨部门确认字段*/
	private String confirmCode;//确认人工号
	private String confirmTime;//确认时间、
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FundApply getFundApply() {
		return fundApply;
	}
	public void setFundApply(FundApply fundApply) {
		this.fundApply = fundApply;
	}
	
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getBudgetDept() {
		return budgetDept;
	}
	public void setBudgetDept(String budgetDept) {
		this.budgetDept = budgetDept;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDeptMonthBudgetID() {
		return deptMonthBudgetID;
	}
	public void setDeptMonthBudgetID(Integer deptMonthBudgetID) {
		this.deptMonthBudgetID = deptMonthBudgetID;
	}
	public String getPlanMonth() {
		return planMonth;
	}
	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}
	public String getGoodsStoreMarkId() {
		return goodsStoreMarkId;
	}
	public void setGoodsStoreMarkId(String goodsStoreMarkId) {
		this.goodsStoreMarkId = goodsStoreMarkId;
	}
	public String getGoodsStoreLot() {
		return goodsStoreLot;
	}
	public void setGoodsStoreLot(String goodsStoreLot) {
		this.goodsStoreLot = goodsStoreLot;
	}
	public Float getGoodsStoreCount() {
		return goodsStoreCount;
	}
	public void setGoodsStoreCount(Float goodsStoreCount) {
		this.goodsStoreCount = goodsStoreCount;
	}
	public String getProcessesNo() {
		return processesNo;
	}
	public void setProcessesNo(String processesNo) {
		this.processesNo = processesNo;
	}
	public String getProcessesName() {
		return processesName;
	}
	public void setProcessesName(String processesName) {
		this.processesName = processesName;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Integer getGoodsStoreId() {
		return goodsStoreId;
	}
	public void setGoodsStoreId(Integer goodsStoreId) {
		this.goodsStoreId = goodsStoreId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getZjStyle() {
		return zjStyle;
	}
	public void setZjStyle(String ziStyle) {
		this.zjStyle = ziStyle;
	}
	public String getZjStyleMx() {
		return zjStyleMx;
	}
	public void setZjStyleMx(String zjStyleMx) {
		this.zjStyleMx = zjStyleMx;
	}
	public String getPay_use() {
		return pay_use;
	}
	public void setPay_use(String payUse) {
		pay_use = payUse;
	}
	public Double getVoucherMoney() {
		return voucherMoney;
	}
	public void setVoucherMoney(Double voucherMoney) {
		this.voucherMoney = voucherMoney;
	}
	public String getDeptMonthBudgetName() {
		return deptMonthBudgetName;
	}
	public void setDeptMonthBudgetName(String deptMonthBudgetName) {
		this.deptMonthBudgetName = deptMonthBudgetName;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public Integer getFk_SubBudgetRateId() {
		return fk_SubBudgetRateId;
	}
	public void setFk_SubBudgetRateId(Integer fkSubBudgetRateId) {
		fk_SubBudgetRateId = fkSubBudgetRateId;
	}
	
//	public Double getBackMoney() {
//		return backMoney;
//	}
//	public void setBackMoney(Double backMoney) {
//		this.backMoney = backMoney;
//	}
//	
	
	
	
}