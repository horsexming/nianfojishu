package com.task.entity;

import java.util.Date;
import java.util.Set;

/**
 * 产品制造检验表
 * @author 马凯
 *
 */
public class Manufacturing  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;// id
	private String productType;// 产品型别
	private String partNumber;// 零件号
	private String processNumber;// 工序号
	private String gwNumber;// 工位号
	private String jygcNumber;// 检验规程编号
	private String czg;// 操作工
	private String code;// 工号
	private Date checkDate;// 检查时间
	private String jcpic; // 检查批次
	private Integer quantity;// 本批数量
	private String jcnr;// 规范要求检查内容
	private String jcpinc;// 规范要求检查频次
	private Set<ManufacturingProp> children;
	private Integer serverId;

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

	public String getCzg() {
		return czg;
	}

	public void setCzg(String czg) {
		this.czg = czg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getJcpic() {
		return jcpic;
	}

	public void setJcpic(String jcpic) {
		this.jcpic = jcpic;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getJcnr() {
		return jcnr;
	}

	public void setJcnr(String jcnr) {
		this.jcnr = jcnr;
	}

	public String getJcpinc() {
		return jcpinc;
	}

	public void setJcpinc(String jcpinc) {
		this.jcpinc = jcpinc;
	}
	
	@Override
	public String toString() {
		return gwNumber;
	}

	public Set<ManufacturingProp> getChildren() {
		return children;
	}

	public void setChildren(Set<ManufacturingProp> children) {
		this.children = children;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

}
