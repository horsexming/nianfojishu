package com.task.entity.project;

/**
 * 项目人员中间表
 * 
 * @author txb ta_pro_yfuser
 */
public class YfUser implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer projectManagerYfId;// ProjectManageyf 的Id
	private Integer yfUserId;// ProjectManageyfUser 的Id
	private Boolean approve;//是否是已认证的人员
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYfUserId() {
		return yfUserId;
	}

	public void setYfUserId(Integer yfUserId) {
		this.yfUserId = yfUserId;
	}

	public Integer getProjectManagerYfId() {
		return projectManagerYfId;
	}

	public void setProjectManagerYfId(Integer projectManagerYfId) {
		this.projectManagerYfId = projectManagerYfId;
	}

	public Boolean getApprove() {
		return approve;
	}

	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

}
