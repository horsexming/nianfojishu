package com.task.entity.quality;

import java.io.Serializable;

/*
 * 毛小龙
 * 品质部门过程审核管理
 */
public class Quality implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String quality_name;//部门
	private String quality_type;//类别
	private String quality_title;//标题
	private String quality_context;//描述
	private String quality_file;//附件
	private String quality_pop;//人员
	private String quality_time;//时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuality_name() {
		return quality_name;
	}
	public void setQuality_name(String qualityName) {
		quality_name = qualityName;
	}
	public String getQuality_title() {
		return quality_title;
	}
	public void setQuality_title(String qualityTitle) {
		quality_title = qualityTitle;
	}
	public String getQuality_file() {
		return quality_file;
	}
	public void setQuality_file(String qualityFile) {
		quality_file = qualityFile;
	}
	public String getQuality_pop() {
		return quality_pop;
	}
	public void setQuality_pop(String qualityPop) {
		quality_pop = qualityPop;
	}
	public String getQuality_time() {
		return quality_time;
	}
	public void setQuality_time(String qualityTime) {
		quality_time = qualityTime;
	}
	public String getQuality_context() {
		return quality_context;
	}
	public void setQuality_context(String qualityContext) {
		quality_context = qualityContext;
	}
	public String getQuality_type() {
		return quality_type;
	}
	public void setQuality_type(String qualityType) {
		quality_type = qualityType;
	}
	
	
	

}
