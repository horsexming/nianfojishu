package com.task.entity.android;

import java.io.Serializable;
import java.util.Set;


/**
 * 巡检模版
 * 
 * @author 马凯
 * 
 */
public class Templatem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String productType;// 产品型别
	private String partNumber;// 件号
	private String processNumber;// 工序号
	private String gwNumber;// 工位号
	private String jygcNumber;// 检验规程编号
	private String jcnr;// 规范要求检查内容
	private Set<TemplateTypem> children;

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

	public String getGwNumber() {
		return gwNumber;
	}

	public void setGwNumber(String gwNumber) {
		this.gwNumber = gwNumber;
	}

	public String getJygcNumber() {
		return jygcNumber;
	}

	public void setJygcNumber(String jygcNumber) {
		this.jygcNumber = jygcNumber;
	}

	public String getJcnr() {
		return jcnr;
	}

	public void setJcnr(String jcnr) {
		this.jcnr = jcnr;
	}

	public Set<TemplateTypem> getChildren() {
		return children;
	}

	public void setChildren(Set<TemplateTypem> children) {
		this.children = children;
	}

}
