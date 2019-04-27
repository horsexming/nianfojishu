package com.task.entity.project;
/**
 * ta_pro_QuotedPricejyDetailFile
 * @author txb
 *
 */
public class QuotedPricejyDetailFile implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private Integer jydId;
	private Integer userId;
	private String userName;//
	private String userCode;//
	private String userDept;//
	private String addTime;
	private String type;//类型（描述文件,执行文件）
	private String fileName;//文件名称/upload/file/jy
	private String oldFileName;//原文件名称
	private String baomi;//是否保密文件（保密，null）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJydId() {
		return jydId;
	}
	public void setJydId(Integer jydId) {
		this.jydId = jydId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBaomi() {
		return baomi;
	}
	public void setBaomi(String baomi) {
		this.baomi = baomi;
	}
	
	
}
