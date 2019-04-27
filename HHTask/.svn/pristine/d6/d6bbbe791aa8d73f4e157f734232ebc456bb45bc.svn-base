package com.task.entity.gongyi.gongxu;

import java.io.Serializable;

public class HanjieZuoyeGuifan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**序号*/
	private String numb;
	/**内容*/
	private  String content;
	/**工序数据ID*/
	private Integer processDataId;
	
	/**获取客户端参数*/
	private String params;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getProcessDataId() {
		return processDataId;
	}
	public void setProcessDataId(Integer processDataId) {
		this.processDataId = processDataId;
	}
	public String getParams() {
		if(params!=null){
			return params.replace("\\t", "").replace("\\r","").replace("\\n","").replace("\\f","").replace("\\","");
		}
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
}
