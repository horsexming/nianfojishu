package com.task.entity.gongyi.gongxu;

import java.io.Serializable;

public class MaoliaoParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**项目*/
	private String numb;
	/**内容*/
	private String content;
	/**单位*/
	private String danwei;
	/**数值*/
	private String shuzhi;
	/**备注*/
	private String remark;
	/**工序数据ID*/
	private Integer processDataId;
	/**获取参数*/
	private String params;
	
	
	
	public String getParams() {
		if(params!=null){
			return params.replace("\\t", "").replace("\\r","").replace("\\n","").replace("\\f","").replace("\\","");
		}
		return params;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNumb() {
		return numb;
	}



	public void setNumb(String numb) {
		this.numb = numb;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getDanwei() {
		return danwei;
	}



	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}



	public String getShuzhi() {
		return shuzhi;
	}



	public void setShuzhi(String shuzhi) {
		this.shuzhi = shuzhi;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Integer getProcessDataId() {
		return processDataId;
	}



	public void setProcessDataId(Integer processDataId) {
		this.processDataId = processDataId;
	}



	public void setParams(String params) {
		this.params = params;
	}
}
