package com.task.entity.gongyi.gongxu;

import java.io.Serializable;
import java.util.List;

public class OperationOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**操作序号*/
	private String numb;
	/**操作顺序*/
	private String content;
	/**工序数据ID*/
	private Integer processDataId;
	private List<OperationOrderItem> operationOrderItemList;
	
	/**获取前端参数*/
	private String params;

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

	public Integer getProcessDataId() {
		return processDataId;
	}

	public void setProcessDataId(Integer processDataId) {
		this.processDataId = processDataId;
	}

	public List<OperationOrderItem> getOperationOrderItemList() {
		return operationOrderItemList;
	}

	public void setOperationOrderItemList(List<OperationOrderItem> operationOrderItemList) {
		this.operationOrderItemList = operationOrderItemList;
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
}
