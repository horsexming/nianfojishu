package com.task.entity.sop;

/**
 * 设计问题反馈单文件  E-CAR文件 (ta_sop_w_DesignfeedbackNoticeFile )
 * @author txb
 *
 */
public class DesignfeedbackNoticeFile implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String addTime;//
	private Integer addUserId;
	private String addUserName;//
	private String addUserCode;//
	private Integer ecarId;//DesignfeedbackNotice
	private String filename;
	private String oldFileName;//uplode/flie/pcn
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
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getAddUserCode() {
		return addUserCode;
	}
	public void setAddUserCode(String addUserCode) {
		this.addUserCode = addUserCode;
	}
	public Integer getEcarId() {
		return ecarId;
	}
	public void setEcarId(Integer ecarId) {
		this.ecarId = ecarId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	
}
