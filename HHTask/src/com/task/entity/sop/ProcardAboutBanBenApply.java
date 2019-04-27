package com.task.entity.sop;

import java.util.List;
import java.util.Set;

/**
 * 生产关联设变ta_ProcardAboutBanBenApply
 * @author txb
 *
 */
public class ProcardAboutBanBenApply implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer procardId;//零件id
	private String totaljd;//整体进度(初始,已运算MRP,已排产,已生产)
	private Integer fprocardId;//父零件Id
	private String orderNumber;//订单编号
	private String rootMarkId;//总成件号
	private String rootSelfCard;//总成批次
	private String ywMarkId;//业务件号
	private String markId;//件号
	private String selfCard;//批次
	private String proName;//零件名称
	private String procardStyle;//类型
	private String banebenNumber;//版本号
	private Integer banci;//版次
	private String clType;//处理方案(顺延,返修,重产)
	private String status;//状态（发起中,待处理，完成）
	private String rootStatus;//总成状态
	private String procardStatus;//零件状态
	private Float scCount;//生产数量
	private Integer bbapplyId;//ProcardTemplateBanBenApply
	private Integer bbId;//ProcardTemplateBanBen
	private List<ProcardBanBenJudge> pbbjList;
	private Set<ProcessAboutBanBenApply> processabbSet;//
	private List<ProcessAboutBanBenApply> processabbList;//
	private List<WaigouPlanLock> wplockList;//
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public String getRootSelfCard() {
		return rootSelfCard;
	}
	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getClType() {
		return clType;
	}
	public void setClType(String clType) {
		this.clType = clType;
	}
	public String getBanebenNumber() {
		return banebenNumber;
	}
	public void setBanebenNumber(String banebenNumber) {
		this.banebenNumber = banebenNumber;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBbapplyId() {
		return bbapplyId;
	}
	public void setBbapplyId(Integer bbapplyId) {
		this.bbapplyId = bbapplyId;
	}
	public Integer getBbId() {
		return bbId;
	}
	public void setBbId(Integer bbId) {
		this.bbId = bbId;
	}
	public String getRootStatus() {
		return rootStatus;
	}
	public void setRootStatus(String rootStatus) {
		this.rootStatus = rootStatus;
	}
	public String getProcardStatus() {
		return procardStatus;
	}
	public void setProcardStatus(String procardStatus) {
		this.procardStatus = procardStatus;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<ProcardBanBenJudge> getPbbjList() {
		return pbbjList;
	}
	public void setPbbjList(List<ProcardBanBenJudge> pbbjList) {
		this.pbbjList = pbbjList;
	}
	public Integer getFprocardId() {
		return fprocardId;
	}
	public void setFprocardId(Integer fprocardId) {
		this.fprocardId = fprocardId;
	}
	public Set<ProcessAboutBanBenApply> getProcessabbSet() {
		return processabbSet;
	}
	public void setProcessabbSet(Set<ProcessAboutBanBenApply> processabbSet) {
		this.processabbSet = processabbSet;
	}
	public List<ProcessAboutBanBenApply> getProcessabbList() {
		return processabbList;
	}
	public void setProcessabbList(List<ProcessAboutBanBenApply> processabbList) {
		this.processabbList = processabbList;
	}
	public Float getScCount() {
		return scCount;
	}
	public void setScCount(Float scCount) {
		this.scCount = scCount;
	}
	public String getTotaljd() {
		return totaljd;
	}
	public void setTotaljd(String totaljd) {
		this.totaljd = totaljd;
	}
	public List<WaigouPlanLock> getWplockList() {
		return wplockList;
	}
	public void setWplockList(List<WaigouPlanLock> wplockList) {
		this.wplockList = wplockList;
	}
	
	
	
}
