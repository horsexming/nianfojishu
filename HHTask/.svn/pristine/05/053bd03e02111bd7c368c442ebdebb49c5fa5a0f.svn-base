package com.task.entity.gongyi.gongxu;

import java.io.Serializable;
public class ProcessPart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	/**序号*/
	private String numb;
	/**件号*/
	private String jianNumb;
	/**件名*/
	private String jianName;
	/**单台数量*/
	private String danNumb;
	/**材料*/
	private String cailiao;
	/**备注*/
	private String remark;
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
	
	public String getJianNumb() {
		return jianNumb;
	}
	public void setJianNumb(String jianNumb) {
		this.jianNumb = jianNumb;
	}
	public String getJianName() {
		return jianName;
	}
	public void setJianName(String jianName) {
		this.jianName = jianName;
	}
	public String getDanNumb() {
		return danNumb;
	}
	public void setDanNumb(String danNumb) {
		this.danNumb = danNumb;
	}
	public String getCailiao() {
		return cailiao;
	}
	public void setCailiao(String cailiao) {
		this.cailiao = cailiao;
	}
	
	public Integer getProcessDataId() {
		return processDataId;
	}
	public void setProcessDataId(Integer processDataId) {
		this.processDataId = processDataId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
