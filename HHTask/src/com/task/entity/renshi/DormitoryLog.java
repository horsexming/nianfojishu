package com.task.entity.renshi;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 宿舍申请单
 * @author Li_Cong
 * 表名 ta_hr_lz_dormitoryLog
 */
public class DormitoryLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer codeId;//申请人ID
	private String shenqing_number;//申请单编号
	@FieldMeta(name="申请人名称")
	private String name;//申请人名称
	private String age;//年龄
	@FieldMeta(name="申请人性别")
	private String sex;//性别
	private String phone;//照片
	private String identity_id;//身份证号
	@FieldMeta(name="申请居住起始时间")
	private String startTime;//申请开始时间
	@FieldMeta(name="申请居住截止时间")
	private String endTime;//结束时间
	@FieldMeta(name="申请人部门")
	private String dept;//申请人部门
	@FieldMeta(name="申请人工号")
	private String code;//申请人工号
	private String contract_number;//合同编号
	@FieldMeta(name="添加时间")
	private String addTime;//添加时间
	private String isAgree;//是否同意协议
	
	private Integer epId;//审批流程
	@FieldMeta(name="审批状态")
	private String status;//审核状态(未审批、审批中、同意、打回)
	
	private String updateTime;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}
	public String getShenqing_number() {
		return shenqing_number;
	}
	public void setShenqing_number(String shenqingNumber) {
		shenqing_number = shenqingNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentity_id() {
		return identity_id;
	}
	public void setIdentity_id(String identityId) {
		identity_id = identityId;
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
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contractNumber) {
		contract_number = contractNumber;
	}
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	
	
	
}
