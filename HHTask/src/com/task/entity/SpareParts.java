package com.task.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 产品零组件信息表(表名:ta_sop_tj_SpareParts)
 * 
 * @author 贾辉辉
 * 
 * 
 */
public class SpareParts implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id; // 序号
	private ProductPrice product;// 总成件号
	private Set<ProductProcess> productProcess; // 零件对应工序
	private String spmarkId;// 件号
	private String spgoodsName;// 品名
	private Float allWorkHours;// 总工时
	private Double allLaborcost;// 零件总人工费用
	private String username;// 填写人
	private String entryDate;// 录入时间
	private String more;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductPrice getProduct() {
		return product;
	}

	public void setProduct(ProductPrice product) {
		this.product = product;
	}

	public Set<ProductProcess> getProductProcess() {
		return productProcess;
	}

	public void setProductProcess(Set<ProductProcess> productProcess) {
		this.productProcess = productProcess;
	}

	public String getSpmarkId() {
		return spmarkId;
	}

	public void setSpmarkId(String spmarkId) {
		this.spmarkId = spmarkId;
	}

	public String getSpgoodsName() {
		return spgoodsName;
	}

	public void setSpgoodsName(String spgoodsName) {
		this.spgoodsName = spgoodsName;
	}

	public Float getAllWorkHours() {
		return allWorkHours;
	}

	public void setAllWorkHours(Float allWorkHours) {
		this.allWorkHours = allWorkHours;
	}

	public Double getAllLaborcost() {
		return allLaborcost;
	}

	public void setAllLaborcost(Double allLaborcost) {
		this.allLaborcost = allLaborcost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

}
