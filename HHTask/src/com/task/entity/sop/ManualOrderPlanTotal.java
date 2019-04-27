package com.task.entity.sop;

import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 物料需求单表(ta_sop_w_ManualOrderPlanTotal)
 * @author 王传运
 *
 */
public class ManualOrderPlanTotal implements java.io.Serializable{
	private static final long serialVersionUID =1L;
 
	private Integer id;
	private Integer userId;
	@FieldMeta(name="用户编号")
	private String userCode;
	@FieldMeta(name="用户名")
	private String userName;
	@FieldMeta(name="部门")
	private String userDept;
	@FieldMeta(name="申请时间")
	private String addTime;//添加时间
	private String updateTime;//修改时间
	@FieldMeta(name="申请类别")
	private String category;//申请类别、、辅料，外购件
	@FieldMeta(name="预估金额")
	private Float estimatePrice;//预估价格
	@FieldMeta(name="订单流水号")
	private String totalNum;//订单流水号
	@FieldMeta(name="项目编号")
	private String proCode;//项目编号
	@FieldMeta(name="项目名称")
	private String proName;//项目名称
	@FieldMeta(name="物料用途")
	private String application;//用途
	@FieldMeta(name="备注(标准、质量、独家及其它)")
	private String remark;//备注
	private String months;//计划月份
	private Float totalMoney;//金额
	private Integer planType;//计划类型 (0;表示本月，1表示下月)
	private String fltype;
	
	private Integer epId;
	private String epStatus;
	private String pickingStatus;//领料状态     已领完、未领料、未领完
	private Set<ManualOrderPlanDetail> details;//物料需求明细
	
	private String markId;//件号   //------
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<ManualOrderPlanDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<ManualOrderPlanDetail> details) {
		this.details = details;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getEpStatus() {
		return epStatus;
	}

	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(Float estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPickingStatus() {
		return pickingStatus;
	}

	public void setPickingStatus(String pickingStatus) {
		this.pickingStatus = pickingStatus;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getFltype() {
		return fltype;
	}

	public void setFltype(String fltype) {
		this.fltype = fltype;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}
	
}
