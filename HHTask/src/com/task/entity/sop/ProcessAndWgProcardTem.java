package com.task.entity.sop;
/**
 * 
 * @author 王晓飞 工序对应外购件多对多（ta_sop_w_ProcessAndWgProcardTem1）
 *
 */
public class ProcessAndWgProcardTem  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String  procardMarkId;//自制件件号
	private String wgprocardMardkId;//外购件件号;
	private Integer processNo;//工序号
	private String processName;//工序名
	private String addTime;//添加时间
	private String source;//来源
	private String ywMarkId;//（页面传值）
	private String wgType;//页面传值
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getProcardMarkId() {
		return procardMarkId;
	}
	public void setProcardMarkId(String procardMarkId) {
		this.procardMarkId = procardMarkId;
	}
	public String getWgprocardMardkId() {
		return wgprocardMardkId;
	}
	public void setWgprocardMardkId(String wgprocardMardkId) {
		this.wgprocardMardkId = wgprocardMardkId;
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
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
