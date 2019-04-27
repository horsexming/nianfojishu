package com.task.entity.sop;
/**
 * 模板Bom版本关系（表：ta_ProcardTBanbenRelation）
 * @author txb
 *
 */
public class ProcardTBanbenRelation implements java.io.Serializable{
	private static final long serialVersionUID =1L;
  private Integer id;
  private String fmarkId;//父件号
  private String fbanben;//父版本号
  private Integer fbanci;//父版次
  private String smarkId;//子件号
  private String sbanben;//子版本号
  private Integer sbanci;//子版次
  private String status;//关系状态（使用,停用）
  private Integer epId;
  private String applyStatus;//申请状态
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFmarkId() {
	return fmarkId;
}
public void setFmarkId(String fmarkId) {
	this.fmarkId = fmarkId;
}
public String getFbanben() {
	return fbanben;
}
public void setFbanben(String fbanben) {
	this.fbanben = fbanben;
}
public Integer getFbanci() {
	return fbanci;
}
public void setFbanci(Integer fbanci) {
	this.fbanci = fbanci;
}
public String getSmarkId() {
	return smarkId;
}
public void setSmarkId(String smarkId) {
	this.smarkId = smarkId;
}
public String getSbanben() {
	return sbanben;
}
public void setSbanben(String sbanben) {
	this.sbanben = sbanben;
}
public Integer getSbanci() {
	return sbanci;
}
public void setSbanci(Integer sbanci) {
	this.sbanci = sbanci;
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
public String getApplyStatus() {
	return applyStatus;
}
public void setApplyStatus(String applyStatus) {
	this.applyStatus = applyStatus;
}
  
  
}
