package com.task.entity.android;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 模版类型
 * 
 * @author 马凯
 * 
 */
public class TemplateTypem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;// 类型
	private String checkpc;// 检查频次
	private Date createDate;// 创建时间
	private Integer serverId;// 服务器端id
	private Integer templateId;// 外键
	private Templatem root;
	private Set<TemplateRecordm> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheckpc() {
		return checkpc;
	}

	public void setCheckpc(String checkpc) {
		this.checkpc = checkpc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Templatem getRoot() {
		return root;
	}

	public void setRoot(Templatem root) {
		this.root = root;
	}

	public Set<TemplateRecordm> getChildren() {
		return children;
	}

	public void setChildren(Set<TemplateRecordm> children) {
		this.children = children;
	}

}
