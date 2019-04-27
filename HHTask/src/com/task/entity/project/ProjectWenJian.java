package com.task.entity.project;

import com.task.entity.sop.muju.MouldApplyOrder;

/**
 * 项目文件 :(ta_ProjectWenJian)
 * @author 王晓飞
 *
 */
public class ProjectWenJian implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	
	private Integer id;
	private String fileName;//真实文件名(系统生成的)
	private String otherName;//别名 (方便记忆的)
	private ProjectManage projectManage;
	private MouldApplyOrder mao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public ProjectManage getProjectManage() {
		return projectManage;
	}
	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}
	public MouldApplyOrder getMao() {
		return mao;
	}
	public void setMao(MouldApplyOrder mao) {
		this.mao = mao;
	}
	

}