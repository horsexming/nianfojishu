package com.task.entity.sop;
/**
 * 零件对应工序
 * 表名：ta_sop_ProcardForProcess
 * @author Administrator
 *
 */
public class ProcardForProcess implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private Integer fatherId;//父零件ID
	private String fatherMarkId;//父零件号
	private Integer fbanci;//父版次
	private String markIds;//零件号
	private String processNos;//工序号
	private String processNames;//工序名称
	private Float processCount;//外委数量（对工序）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public String getFatherMarkId() {
		return fatherMarkId;
	}
	public void setFatherMarkId(String fatherMarkId) {
		this.fatherMarkId = fatherMarkId;
	}
	public String getMarkIds() {
		return markIds;
	}
	public void setMarkIds(String markIds) {
		this.markIds = markIds;
	}
	public String getProcessNos() {
		return processNos;
	}
	public void setProcessNos(String processNos) {
		this.processNos = processNos;
	}
	public String getProcessNames() {
		return processNames;
	}
	public void setProcessNames(String processNames) {
		this.processNames = processNames;
	}
	public Float getProcessCount() {
		return processCount;
	}
	public void setProcessCount(Float processCount) {
		this.processCount = processCount;
	}
	public Integer getFbanci() {
		return fbanci;
	}
	public void setFbanci(Integer fbanci) {
		this.fbanci = fbanci;
	}
	
	
}