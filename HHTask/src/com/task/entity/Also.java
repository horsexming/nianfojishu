package com.task.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Also(表名:ta_Also)
 * @Description: 还
 * @author Damon
 * @date 2013-4-23 下午03:03:19
 * 
 * add relaction Fields
 * private Store store   关系:Also(many-to-one) : Store(one-to-many)
 * private Borrow borrow 关系:Also(many-to-one) : Borrow(one-to-many)
 */
public class Also  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; //主键
	private String peopleName;//借主
	private String name; //产品名称 
	private Date date;//借出日期
	private String format; //规格 
	private Integer num;//数量 （出借）
	private String storehouse; //仓库?
	private String remark;//备注
	private String cardNum;//卡号
	private String dept;//部门
	private String number;//编号
	private String unit;//单位
	private String mix;
	private Integer processQuantity;//归还数
	private String processPieceNum;
	private String carType;
	private Date alsoDate;//归还日期
	private String cqStatus; // 存取状态(待存，存中，已存)16-12-09 LC
	private Integer ware_id; // 归还后的库位ID 16-12-09 LC
	private String wareHouse; // 库位名称
	
	private Store store;
	private Borrow borrow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getCqStatus() {
		return cqStatus;
	}

	public void setCqStatus(String cqStatus) {
		this.cqStatus = cqStatus;
	}

	public Integer getWare_id() {
		return ware_id;
	}

	public void setWare_id(Integer wareId) {
		ware_id = wareId;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMix() {
		return mix;
	}

	public void setMix(String mix) {
		this.mix = mix;
	}

	public Integer getProcessQuantity() {
		return processQuantity;
	}

	public void setProcessQuantity(Integer processQuantity) {
		this.processQuantity = processQuantity;
	}

	public String getProcessPieceNum() {
		return processPieceNum;
	}

	public void setProcessPieceNum(String processPieceNum) {
		this.processPieceNum = processPieceNum;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	public Date getAlsoDate() {
		return alsoDate;
	}

	public void setAlsoDate(Date alsoDate) {
		this.alsoDate = alsoDate;
	}
	
	
}