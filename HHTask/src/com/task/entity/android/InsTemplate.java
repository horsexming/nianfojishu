package com.task.entity.android;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 模版(表名:ta_m_InsTemplate)
 * 
 * @author 马凯
 * 
 */
public class InsTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String productType;// 产品型别
	private String partNumber;// 零件号
	private String processNumber;// 工序号
	private String workStation;// 工位号
	private String type;// 检查类型
	private Date createDate;// 创建时间
	private String checkpc;// 检查频次
	private Set<InsScope> scope;// 检查条目
	private InsRecord record;// 检查记录
	private String username;// 检查人
	private List<InsScope> scopes;

	private String dept;// 所属部门

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}

	public String getWorkStation() {
		return workStation;
	}

	public void setWorkStation(String workStation) {
		this.workStation = workStation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCheckpc() {
		return checkpc;
	}

	public void setCheckpc(String checkpc) {
		this.checkpc = checkpc;
	}

	public InsRecord getRecord() {
		return record;
	}

	public void setRecord(InsRecord record) {
		this.record = record;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<InsScope> getScope() {
		return scope;
	}

	public void setScope(Set<InsScope> scope) {
		this.scope = scope;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<InsScope> getScopes() {
		return scopes;
	}

	public void setScopes(List<InsScope> scopes) {
		this.scopes = scopes;
	}

}
