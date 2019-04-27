package com.task.entity.project;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;
/**
 * 研发项目材料清单
 * @表名 ta_pro_ProjectMaterialOrder
 * @author txb
 *
 */
public class ProjectMaterialOrder implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	 @FieldMeta(name = "订单编号", order = 3)
	private String orderNo;//订单编号
	@FieldMeta(name = "项目名称", order = 1)
	private String proName;//项目名称
	private Integer userId;//申请人id
	private String userName;//申请人编号
	private String usercode;//申请人工号
	@FieldMeta(name = "项目时间", order = 2)
	private String addTime;//添加时间
	private Integer qpId;//总成Id
	private Integer proId;//项目Id
	private String remark;//备注
	private String receiveStatus;//领取状态（未领、已领）
	private String isbaomi;//是否保密
	private Float ptCount;//配套数量
	
	private String aduitStatus;// 审核状态(未审批、审批中、同意、打回)
	private String receiver ;//领料人
	private Integer epId;//流程id
	private Set<ProjectMaterial> projectMaterial;//材料
	 
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAduitStatus() {
		return aduitStatus;
	}
	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public Set<ProjectMaterial> getProjectMaterial() {
		return projectMaterial;
	}
	public void setProjectMaterial(Set<ProjectMaterial> projectMaterial) {
		this.projectMaterial = projectMaterial;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getIsbaomi() {
		return isbaomi;
	}
	public void setIsbaomi(String isbaomi) {
		this.isbaomi = isbaomi;
	}
	public Float getPtCount() {
		return ptCount;
	}
	public void setPtCount(Float ptCount) {
		this.ptCount = ptCount;
	}
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	
	 

}
