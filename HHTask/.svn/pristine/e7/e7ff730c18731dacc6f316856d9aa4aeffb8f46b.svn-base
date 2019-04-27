package com.task.entity.dmltry;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * AppFiles主表(ta_DmltryAppFiles)
 * @author dmltry&&AppFiles
 *
 */
public class DmltryAppFiles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//id主键
	@FieldMeta(name="名称")
	private String 	appFilename;//名称
	@FieldMeta(name="描述")
	private String	appFilesmshu;//描述
	private Set<DmlAppFileUrl> dmlAppFileUrl;//外键
	
	
	
	
	/**
	 * 添加时间和修改时间
	 * @return
	 */
	private String addTime;	//提交时间
	private	String upDateTime;//修改时间
	
	

	
	
	
	/**
	 * 建立对象关系
	 * 2018-9-27
	 */
	//private Set<InterviewQuizzes> interviewQuizzes;
	
	
	public String getAppFilename() {
		return appFilename;
	}
	public void setAppFilename(String appFilename) {
		this.appFilename = appFilename;
	}
	public String getAppFilesmshu() {
		return appFilesmshu;
	}
	public void setAppFilesmshu(String appFilesmshu) {
		this.appFilesmshu = appFilesmshu;
	}
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
	public String getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
	}
	public Set<DmlAppFileUrl> getDmlAppFileUrl() {
		return dmlAppFileUrl;
	}
	public void setDmlAppFileUrl(Set<DmlAppFileUrl> dmlAppFileUrl) {
		this.dmlAppFileUrl = dmlAppFileUrl;
	}

	
}
