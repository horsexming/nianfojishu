package com.task.entity.bargain;

import java.io.Serializable;

/***
 * 议价明细表
 * @author 毛小龙
 *ta_BargainingDetails
 */
	public class BargainingDetails implements Comparable,Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Float unitprice;//单价
	private Integer amount;//数量
	private Integer numbers;//次数
	private Bargain bargain;//对应主表议价
	private Company company;//对应公司信息
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Float unitprice) {
		this.unitprice = unitprice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Bargain getBargain() {
		return bargain;
	}
	public void setBargain(Bargain bargain) {
		this.bargain = bargain;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public int compareTo(Object o) {
		BargainingDetails ast=(BargainingDetails)o;
		 return ast.id-this.id;
	}
	

}
