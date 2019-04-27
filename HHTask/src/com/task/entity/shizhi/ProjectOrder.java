package com.task.entity.shizhi;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;
/**
 * 项目需求
 * 
 * @表名：ta_sk_ProjectOrder
 * @author 唐晓斌
 */
public class ProjectOrder implements Serializable {
private static final long serialVersionUID = 1L;
 private Integer id;
 @FieldMeta(name = "项目名称", order = 1)
 private String proName;//项目名称
 private String groupName;//分组名称
 @FieldMeta(name = "项目时间", order = 2)
 private String month;//项目时间
 @FieldMeta(name = "试制订单编号", order = 3)
 private String orderNO;//试制订单编号
 private String userName;//创建人名字
 private String userCode;//创建人工号
 private Integer userId;//创建人Id
 private String productEngineer;//产品工程师
 private String technicalEngineer;//技术工程师
 private String projectBy;//项目依据
 private String projectTo;//项目目的
 private String deliveryInfo;//交付信息
 private Integer deal;//项目处理1冲抵生产计划,入库销售,2.试验用,不予入库
 private String remark;//备注
 private String cusName;//客户名称
 private String status;//项目状态
 private Integer epId;//流程id
 private Set<ProjectOrderPart> projectOrderPart;//项目需求单零件
 private ProTryMakeScore proTryMakeScore;//项目试制评分
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getProName() {
	return proName;
}

public void setProName(String proName) {
	this.proName = proName;
}

public String getOrderNO() {
	return orderNO;
}

public void setOrderNO(String orderNO) {
	this.orderNO = orderNO;
}

public String getProductEngineer() {
	return productEngineer;
}

public void setProductEngineer(String productEngineer) {
	this.productEngineer = productEngineer;
}

public String getTechnicalEngineer() {
	return technicalEngineer;
}

public void setTechnicalEngineer(String technicalEngineer) {
	this.technicalEngineer = technicalEngineer;
}

public String getProjectBy() {
	return projectBy;
}

public void setProjectBy(String projectBy) {
	this.projectBy = projectBy;
}

public String getProjectTo() {
	return projectTo;
}

public void setProjectTo(String projectTo) {
	this.projectTo = projectTo;
}

public String getDeliveryInfo() {
	return deliveryInfo;
}

public void setDeliveryInfo(String deliveryInfo) {
	this.deliveryInfo = deliveryInfo;
}

public Integer getDeal() {
	return deal;
}

public void setDeal(Integer deal) {
	this.deal = deal;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public String getCusName() {
	return cusName;
}

public void setCusName(String cusName) {
	this.cusName = cusName;
}

public Set<ProjectOrderPart> getProjectOrderPart() {
	return projectOrderPart;
}

public void setProjectOrderPart(Set<ProjectOrderPart> projectOrderPart) {
	this.projectOrderPart = projectOrderPart;
}

public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}


public String getMonth() {
	return month;
}

public void setMonth(String month) {
	this.month = month;
}

public ProTryMakeScore getProTryMakeScore() {
	return proTryMakeScore;
}

public void setProTryMakeScore(ProTryMakeScore proTryMakeScore) {
	this.proTryMakeScore = proTryMakeScore;
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	if(obj!=null&&obj instanceof ProTryMakeScore){
		ProTryMakeScore p=(ProTryMakeScore)obj;
		return this.id==p.getId();
	}
	return false;
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

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}



 
 
 
}
