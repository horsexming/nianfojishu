package com.task.entity.renshi;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 离职交接对象
 * @author Li_Cong
 * 表名 ta_hr_lz_dormitoryLog_Handover(交接)
 */
public class Dimission_Handover implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer codeId; // 申请人ID
	@FieldMeta(name="申请人姓名")
	private String name;
	@FieldMeta(name="申请人部门")
	private String dept;
	@FieldMeta(name="离职原因")
	private String dimission_Reason;//离职原因
	private String dimission_number;//离职交接单编号
	private String ta_dimissionLog_id;//对应的离职申请单编号
	@FieldMeta(name="申请时间")
	private String addTime;//添加时间
	private Integer epId;//审批流程
	private String lzjj_status;//审核状态(未审批、审批中、同意、打回)
	
	private String updateTime;//修改时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTa_dimissionLog_id() {
		return ta_dimissionLog_id;
	}
	public void setTa_dimissionLog_id(String taDimissionLogId) {
		ta_dimissionLog_id = taDimissionLogId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getLzjj_status() {
		return lzjj_status;
	}
	public void setLzjj_status(String lzjjStatus) {
		lzjj_status = lzjjStatus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDimission_Reason() {
		return dimission_Reason;
	}
	public void setDimission_Reason(String dimissionReason) {
		dimission_Reason = dimissionReason;
	}
	public String getDimission_number() {
		return dimission_number;
	}
	public void setDimission_number(String dimissionNumber) {
		dimission_number = dimissionNumber;
	}
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	
}