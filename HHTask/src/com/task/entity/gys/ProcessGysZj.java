package com.task.entity.gys;

import java.io.Serializable;

/***
 * 工序自检表(表名:ta_sop_ProcessGysZj)
 * 
 * @author txb
 * 
 */
public class ProcessGysZj implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String zjItem;// 自检项
	private String isQualified;// 是否合格(合格/不合格)
	private ProcessGysInfor processGysInfor;//供应商工序
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZjItem() {
		return zjItem;
	}
	public void setZjItem(String zjItem) {
		this.zjItem = zjItem;
	}
	public String getIsQualified() {
		return isQualified;
	}
	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}
	public ProcessGysInfor getProcessGysInfor() {
		return processGysInfor;
	}
	public void setProcessGysInfor(ProcessGysInfor processGysInfor) {
		this.processGysInfor = processGysInfor;
	}
	
}
