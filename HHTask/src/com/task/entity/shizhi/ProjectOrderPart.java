package com.task.entity.shizhi;

import java.io.Serializable;

/**
 * 项目订单零件
 * 
 * @表名：ta_sk_ProjectOrderPart
 * @author 唐晓斌
 */
public class ProjectOrderPart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proName;//项目名称
	private String month;//月份
	
	private Integer tmId;//项目试制零件id
	private Integer  qpId;//零件ID 
	private String markId;//件号
	// 树形结构附属属性
	private Integer rootId;// 第一层父类Id
	private Integer fatherId;// 上层父类Id	
	private String partName;//零件名称
	
	private Integer partNum;//零件数量
	private Float hasturn;//已转化数量
	private Float wantturn;//欲转化数量（页面传值使用，不入数据库）
	private String type;//类型（首件,小批量）
	private String remark;//备注
	private ProjectOrder projectOrder;//项目需求单
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
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	public Integer getPartNum() {
		return partNum;
	}
	public void setPartNum(Integer partNum) {
		this.partNum = partNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ProjectOrder getProjectOrder() {
		return projectOrder;
	}
	public void setProjectOrder(ProjectOrder projectOrder) {
		this.projectOrder = projectOrder;
	}
	public Integer getTmId() {
		return tmId;
	}
	public void setTmId(Integer tmId) {
		this.tmId = tmId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Float getHasturn() {
		return hasturn;
	}
	public void setHasturn(Float hasturn) {
		this.hasturn = hasturn;
	}
	public Float getWantturn() {
		return wantturn;
	}
	public void setWantturn(Float wantturn) {
		this.wantturn = wantturn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	

}
