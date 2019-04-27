package com.task.entity.sop;

import java.util.List;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 工序外委申请表 ta_sop_w_ProcessInforWWApply
 * @author Administrator
 *
 */
public class ProcessInforWWApply  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer userId;
	@FieldMeta(name="工序外委申请编号")
	private String wwApplyNumber;//工序外委申请编号
	@FieldMeta(name="申请人")
	private String userName;//申请人名字
	@FieldMeta(name="申请人工号")
	private String userCode;//申请人工号
	@FieldMeta(name="申请时间")
	private String addTime;//申请时间
	private Integer procardId;//总成Id
	@FieldMeta(name="总成件号")
	private String markId;//总成件号
	@FieldMeta(name="业务件号")
	private String ywMarkId;//业务件号
	@FieldMeta(name="总成名称")
	private String proName;//总成名称
	@FieldMeta(name="总成批次")
	private String selfCard;//总成批次
	@FieldMeta(name="内部订单号")
	private String orderNumber;// 内部订单编号
	@FieldMeta(name="批次数量")
	private Float finalCount;//批次数量
	@FieldMeta(name="备注")
	private String remarks;
	private String processStatus;//流转状态(预选未审批,合同待确认,订单外委采购，生产中)
	private String status;//审批状态
	private Float number;//页面传值 总成生产数量(某工非得让加个数量)
	private Integer epId;
	private String backReMark;//打回原由
	private String shenpiTime;//审批同意时间
	private String procardTime;//批次转换时间
	private Integer agreeCount;//审批同意次数（在审批同意之后，校对合同时打回在此同意时不再扣外购件采购数量）
	private String isagree;//当前登录人是否同意
	private Set<ProcessInforWWApplyDetail> processInforWWApplyDetails;//申请明细
	private List<ProcessInforWWApplyDetail> detailList;//页面传值使用
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public Set<ProcessInforWWApplyDetail> getProcessInforWWApplyDetails() {
		return processInforWWApplyDetails;
	}
	public void setProcessInforWWApplyDetails(
			Set<ProcessInforWWApplyDetail> processInforWWApplyDetails) {
		this.processInforWWApplyDetails = processInforWWApplyDetails;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public List<ProcessInforWWApplyDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ProcessInforWWApplyDetail> detailList) {
		this.detailList = detailList;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public String getBackReMark() {
		return backReMark;
	}
	public void setBackReMark(String backReMark) {
		this.backReMark = backReMark;
	}
	public Integer getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(Integer agreeCount) {
		this.agreeCount = agreeCount;
	}
	public Float getNumber() {
		return number;
	}
	public void setNumber(Float number) {
		this.number = number;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Float getFinalCount() {
		return finalCount;
	}
	public void setFinalCount(Float finalCount) {
		this.finalCount = finalCount;
	}

	public String getShenpiTime() {
		return shenpiTime;
	}
	public void setShenpiTime(String shenpiTime) {
		this.shenpiTime = shenpiTime;
	}
	public String getProcardTime() {
		return procardTime;
	}
	public void setProcardTime(String procardTime) {
		this.procardTime = procardTime;
	}
	/**
	 * @return the wwApplyNumber
	 */
	public String getWwApplyNumber() {
		return wwApplyNumber;
	}
	/**
	 * @param wwApplyNumber the wwApplyNumber to set
	 */
	public void setWwApplyNumber(String wwApplyNumber) {
		this.wwApplyNumber = wwApplyNumber;
	}
	public String getIsagree() {
		return isagree;
	}
	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
