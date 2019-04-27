package com.task.entity.sop;
/**
 * 设变外购增减记录
 * @author txb
 *
 */
public class ProcardSbWgLog implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer psbwgId;//设变外购增减表Id ProcardSbWg
	private String forObj;//增减对象(物料需求,外购订单)
	private Integer forObjId;//增减对象id ManualOrderPlanDetail,WaigouPlan
	private Float count;//数量
	private String clType;//处理方案
	private String addTime;//添加时间
	private String userName;//添加人
	private Integer userId;//添加人id
	private String status;//待处理,完成,打回(已通知供应商的订单需要供应商同意)
	private Integer clApplyId;//处理申请Id(WaigouPlanclApply)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPsbwgId() {
		return psbwgId;
	}
	public void setPsbwgId(Integer psbwgId) {
		this.psbwgId = psbwgId;
	}
	public String getForObj() {
		return forObj;
	}
	public void setForObj(String forObj) {
		this.forObj = forObj;
	}
	public Float getCount() {
		return count;
	}
	public void setCount(Float count) {
		this.count = count;
	}
	public String getClType() {
		return clType;
	}
	public void setClType(String clType) {
		this.clType = clType;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getForObjId() {
		return forObjId;
	}
	public void setForObjId(Integer forObjId) {
		this.forObjId = forObjId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getClApplyId() {
		return clApplyId;
	}
	public void setClApplyId(Integer clApplyId) {
		this.clApplyId = clApplyId;
	}
	
}
