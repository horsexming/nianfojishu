package com.task.entity.rtx;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class RtxDept implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer fatherId;
	private String deptName;
	private Integer sortId;
	private Integer version;
	private Integer deptType;
	private String deptDescription;
	private String deptUrl;
	private Set<RtxUser> rtxUser;

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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	public String getDeptUrl() {
		return deptUrl;
	}

	public void setDeptUrl(String deptUrl) {
		this.deptUrl = deptUrl;
	}

	@JSONField(serialize = false)
	public Set<RtxUser> getRtxUser() {
		return rtxUser;
	}

	public void setRtxUser(Set<RtxUser> rtxUser) {
		this.rtxUser = rtxUser;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}
