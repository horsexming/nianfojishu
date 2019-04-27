package com.task.entity.sop;
/**
 * 手动外委时关联的件号ta_sop_w_ProcessInforWWProcard
 * @author txb
 *
 */
public class ProcessInforWWProcard  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer procardId;//零件id
	private String markId;//件号
	private String procName;//名称
	private String banben;//版本号
	private Integer banci;//版次
	private Float applyCount;//数量
	private Float hascount;//剩余未领数量（工序外委使用）
	private String status;//状态
	private Integer applyDtailId;//外委申请明细id对应表ProcessInforWWApplyDetail(手动外委)
	private Integer wwxlId;//外委序列ID(WaigouWaiweiPlan的id)(BOM外委)
	private String procardStyle;//生产或者外购(生产包括自制和外购半成品)
	private String addTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getBanben() {
		return banben;
	}
	public void setBanben(String banben) {
		this.banben = banben;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public Float getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getApplyDtailId() {
		return applyDtailId;
	}
	public void setApplyDtailId(Integer applyDtailId) {
		this.applyDtailId = applyDtailId;
	}
	public Float getHascount() {
		return hascount;
	}
	public void setHascount(Float hascount) {
		this.hascount = hascount;
	}
	public Integer getWwxlId() {
		return wwxlId;
	}
	public void setWwxlId(Integer wwxlId) {
		this.wwxlId = wwxlId;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	
	
	
	
}
