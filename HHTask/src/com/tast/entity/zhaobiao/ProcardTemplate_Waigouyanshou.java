package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * ProcardTemplate与Waigoujianpinci关系变
 *   ta_baofei
 */
public class ProcardTemplate_Waigouyanshou implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private Integer procardTemplateId;// 模板id
	private Integer waigoujianpinciId;// 外购件频次id
	
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
	public Integer getWaigoujianpinciId() {
		return waigoujianpinciId;
	}
	public void setWaigoujianpinciId(Integer waigoujianpinciId) {
		this.waigoujianpinciId = waigoujianpinciId;
	}

	
}
