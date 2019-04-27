package com.task.entity.sop;

import java.util.Set;

import com.task.entity.android.OsRecord;
import com.task.util.FieldMeta;

/**
 * 
 * @author 王晓飞 不合格品提交记录表(ta_sop_w_BreakSubmit)
 *
 */
public class BreakSubmit implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer procardId;//自制件Id;
	private Integer processId;//工序Id;
	@FieldMeta(name="类型")
	private String type;//类型(零件损坏/外购件不合格)
	@FieldMeta(name="组别")
	private String breakgroup;//上工序不合格;本工序不合格
	@FieldMeta(name="工序号")
	private Integer processNo;//工序号
	@FieldMeta(name="工序名")
	private String processName;//工序名
	@FieldMeta(name="自制件件号")
	private String  markId;//自制件件号
	@FieldMeta(name="自制件名称")
	private String proName;//零件名称
	@FieldMeta(name="工位")
	private String gongwei;//工位
	private String ywmarkId;//业务件号
	private String selfcard;//生产批次
	@FieldMeta(name="所关联的外购件")
	private String wgmarkId;//所关联的外购件（用于外购件不合格类型）
	@FieldMeta(name="外购件名称")
	private String wgproName;//所关联的外购件名称
	private String wgselfcard;//所关联的外购件批次
	@FieldMeta(name="提交不合格数量")
	private Float tjbreakcount;//提交不合格数量
	@FieldMeta(name="确认不合格数量")
	private Float qrbreakcount;//确认不合格数量
	private Integer tjUsersId;//提交人Id
	@FieldMeta(name="提交人姓名")
	private String tjUsersName;//提交人姓名
	private Integer qrUsersId;//确认人Id
	@FieldMeta(name="确认人姓名")
	private String qrUsersName;//确认人姓名
	private Integer osRecordId;//检验记录Id(首检/巡检使用)
	@FieldMeta(name="提交时间")
	private String tjTime;//提交时间
	@FieldMeta(name="确认时间")
	private String qrTime;//确认时间
	@FieldMeta(name="提交方式")
	private String tjtype;//提交方式（领取工序中/领取工序前/提交工序时/首检/巡检）
	private Integer epId;
	private String epstatus;//审批状态
	private String clResult;//处理结果(返修/让步/报废/正常);
	private String prologId;//所对应日志Id;(多个以、分开)
	private String personLiable;//责任人(多个以、分开)
	private String codeLiable;//责任人工号(多个以、分开)
	private String cardIdLiable;//责任人卡号(多个以、分开)
	private String fanxiuUsers;//返修人(多个以、分开)
	private String fanxiuCode;//返修人工号(多个以、分开)
	private String fanxiuCardId;//返修人卡号（多个以、分开）
	private String hxStatus;//后续状态(已返修、已补料)
	private Set<OsRecord> osrSet;//检验记录 （一对多）
	private String rootmarkId;//总成件号
	private String rootselfCard;//总成批次
	
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
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getProcessNo() {
		return processNo;
	}
	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getWgmarkId() {
		return wgmarkId;
	}
	public void setWgmarkId(String wgmarkId) {
		this.wgmarkId = wgmarkId;
	}
	
	public Float getTjbreakcount() {
		return tjbreakcount;
	}
	public void setTjbreakcount(Float tjbreakcount) {
		this.tjbreakcount = tjbreakcount;
	}
	
	public String getTjUsersName() {
		return tjUsersName;
	}
	public void setTjUsersName(String tjUsersName) {
		this.tjUsersName = tjUsersName;
	}
	
	public Integer getTjUsersId() {
		return tjUsersId;
	}
	public void setTjUsersId(Integer tjUsersId) {
		this.tjUsersId = tjUsersId;
	}
	public Integer getQrUsersId() {
		return qrUsersId;
	}
	public void setQrUsersId(Integer qrUsersId) {
		this.qrUsersId = qrUsersId;
	}
	public String getQrUsersName() {
		return qrUsersName;
	}
	public void setQrUsersName(String qrUsersName) {
		this.qrUsersName = qrUsersName;
	}
	public String getTjTime() {
		return tjTime;
	}
	public void setTjTime(String tjTime) {
		this.tjTime = tjTime;
	}
	public String getQrTime() {
		return qrTime;
	}
	public void setQrTime(String qrTime) {
		this.qrTime = qrTime;
	}
	
	public String getBreakgroup() {
		return breakgroup;
	}
	public void setBreakgroup(String breakgroup) {
		this.breakgroup = breakgroup;
	}
	public Float getQrbreakcount() {
		return qrbreakcount;
	}
	public void setQrbreakcount(Float qrbreakcount) {
		this.qrbreakcount = qrbreakcount;
	}
	public Integer getOsRecordId() {
		return osRecordId;
	}
	public void setOsRecordId(Integer osRecordId) {
		this.osRecordId = osRecordId;
	}
	public String getTjtype() {
		return tjtype;
	}
	public void setTjtype(String tjtype) {
		this.tjtype = tjtype;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEpstatus() {
		return epstatus;
	}
	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}
	public String getClResult() {
		return clResult;
	}
	public void setClResult(String clResult) {
		this.clResult = clResult;
	}
	public String getPrologId() {
		return prologId;
	}
	public void setPrologId(String prologId) {
		this.prologId = prologId;
	}
	public String getPersonLiable() {
		return personLiable;
	}
	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	public String getSelfcard() {
		return selfcard;
	}
	public void setSelfcard(String selfcard) {
		this.selfcard = selfcard;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCodeLiable() {
		return codeLiable;
	}
	public void setCodeLiable(String codeLiable) {
		this.codeLiable = codeLiable;
	}
	public String getCardIdLiable() {
		return cardIdLiable;
	}
	public void setCardIdLiable(String cardIdLiable) {
		this.cardIdLiable = cardIdLiable;
	}
	public String getFanxiuUsers() {
		return fanxiuUsers;
	}
	public void setFanxiuUsers(String fanxiuUsers) {
		this.fanxiuUsers = fanxiuUsers;
	}
	public String getFanxiuCode() {
		return fanxiuCode;
	}
	public void setFanxiuCode(String fanxiuCode) {
		this.fanxiuCode = fanxiuCode;
	}
	public String getFanxiuCardId() {
		return fanxiuCardId;
	}
	public void setFanxiuCardId(String fanxiuCardId) {
		this.fanxiuCardId = fanxiuCardId;
	}
	public String getHxStatus() {
		return hxStatus;
	}
	public void setHxStatus(String hxStatus) {
		this.hxStatus = hxStatus;
	}
	public String getGongwei() {
		return gongwei;
	}
	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}
	public Set<OsRecord> getOsrSet() {
		return osrSet;
	}
	public void setOsrSet(Set<OsRecord> osrSet) {
		this.osrSet = osrSet;
	}
	public String getWgproName() {
		return wgproName;
	}
	public void setWgproName(String wgproName) {
		this.wgproName = wgproName;
	}
	public String getWgselfcard() {
		return wgselfcard;
	}
	public void setWgselfcard(String wgselfcard) {
		this.wgselfcard = wgselfcard;
	}
	public String getRootmarkId() {
		return rootmarkId;
	}
	public void setRootmarkId(String rootmarkId) {
		this.rootmarkId = rootmarkId;
	}
	public String getRootselfCard() {
		return rootselfCard;
	}
	public void setRootselfCard(String rootselfCard) {
		this.rootselfCard = rootselfCard;
	}
	
	
	
}
