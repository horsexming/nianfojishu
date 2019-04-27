package com.task.entity.zhuseroffer;

public class NoPriceprocess implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ywmarkId;//业务件号
	private String rootMarkId;//总成件号
	private String markId;
	private String name;//零件名称
	private String banben;
	private String processNO;
	private String processName;
	private Integer processId;
	private Float waiweiShenqiCount;//外委申请数量
	private Float piciCount;//批次数量
	private String stutas;
	private String bjStartDate;//报价开始日期
	private String bjEndDate;//报价结束日期
	private String cycle;//周期
	private Integer sumProcessId;//总表Id、null
	private Integer procardId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getProcessNO() {
		return processNO;
	}
	public void setProcessNO(String processNO) {
		this.processNO = processNO;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public String getStutas() {
		return stutas;
	}
	public void setStutas(String stutas) {
		this.stutas = stutas;
	}
	public String getBjStartDate() {
		return bjStartDate;
	}
	public void setBjStartDate(String bjStartDate) {
		this.bjStartDate = bjStartDate;
	}
	public String getBjEndDate() {
		return bjEndDate;
	}
	public void setBjEndDate(String bjEndDate) {
		this.bjEndDate = bjEndDate;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getBanben() {
		return banben;
	}
	public void setBanben(String banben) {
		this.banben = banben;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getWaiweiShenqiCount() {
		return waiweiShenqiCount;
	}
	public void setWaiweiShenqiCount(Float waiweiShenqiCount) {
		this.waiweiShenqiCount = waiweiShenqiCount;
	}
	public Float getPiciCount() {
		return piciCount;
	}
	public void setPiciCount(Float piciCount) {
		this.piciCount = piciCount;
	}
	public Integer getSumProcessId() {
		return sumProcessId;
	}
	public void setSumProcessId(Integer sumProcessId) {
		this.sumProcessId = sumProcessId;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	
	
	
}
