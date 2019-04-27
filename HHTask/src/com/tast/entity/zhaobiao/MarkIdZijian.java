package com.tast.entity.zhaobiao;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;

/*
 * 标示贴 报废原因表
 *   ta_sop_MarkIdZijian
 */
public class MarkIdZijian implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private Integer procardTemplateId;// BOM 模板表 ProcardTemplate  
	private Integer provisionId;// 自检项  Provision的   provisionStatus=zj
	private String markId;// 件号
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcardTemplateId() {
		return procardTemplateId;
	}
	public void setProcardTemplateId(Integer procardTemplateId) {
		this.procardTemplateId = procardTemplateId;
	}
	public Integer getProvisionId() {
		return provisionId;
	}
	public void setProvisionId(Integer provisionId) {
		this.provisionId = provisionId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	
	
	
	//---------------------------------------
	
	

}
