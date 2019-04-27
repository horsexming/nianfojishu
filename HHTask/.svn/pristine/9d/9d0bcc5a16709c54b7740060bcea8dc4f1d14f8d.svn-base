package com.task.entity;

import java.util.Date;

/**
 * @ClassName: Maintain
 * @Description: 报检
 * @author Damon
 * @date 2013-4-23 下午02:03:13
 * 
 * add relaction Fields
 * private Store store   关系:Maintain(many-to-one) : Store(one-to-many)
 */
public class Maintain  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id; //
	private String number;//编号
	private String matetag;//名称
	private Integer amount;//数量
	private String unit;//单位
	private Date fixDate;//时间
	private Date restorDate;//入库时间
	private String state;//状态
	private String mix;//合成
	private String more;//备注
	private String format;//规格
	
	private Store store;
	
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/** default constructor */
	public Maintain() {
	}

	/** minimal constructor */
	public Maintain(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMatetag() {
		return this.matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getFixDate() {
		return this.fixDate;
	}

	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}

	public Date getRestorDate() {
		return this.restorDate;
	}

	public void setRestorDate(Date restorDate) {
		this.restorDate = restorDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMix() {
		return this.mix;
	}

	public void setMix(String mix) {
		this.mix = mix;
	}

	public String getMore() {
		return this.more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
