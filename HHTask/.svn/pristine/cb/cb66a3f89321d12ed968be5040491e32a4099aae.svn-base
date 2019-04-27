package com.task.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Borrow
 * @Description: 借(表名:ta_Borrow)
 * @author Damon
 * @date 2013-4-23 下午03:03:32
 * 
 *       add relaction Fields private Store store 关系:Borrow(many-to-one) :
 *       Store(one-to-many) private Set<Also> alsos 关系:Borrow(one-to-many) :
 *       Also(many-to-one)
 */
public class Borrow implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String peopleName; // 借主
	private String matetag;// 名称
	private Date date;// 日期
	private String format;// 规格
	private Float num;// 数量
	private String storehouse; // 仓库?
	private String wareHouse; // 库位名称
	private String remark; // 备注
	private String cardNum;// 卡号
	private String dept;// 部门
	private String number;// 编号
	private String unit;// 单位
	private String mix;// 合成
	private String processPieceNum;// 加工件号
	private String state; // 状态
	private Float giveBackNum;// 未归还数量
	private String carType; // 车型
	private String cqStatus; // 存取状态(待取，已取，取中)16-12-08 LC
	private Integer ware_id; // 操作的库位ID 16-12-08 LC
	private Integer oa_id; // 对应Oa采购信息ID 16-12-08 LC
	private Store store; // 库存
	private Set<Also> alsos = new HashSet<Also>();
	private Set<Scrap> scraps = new HashSet<Scrap>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOa_id() {
		return oa_id;
	}

	public void setOa_id(Integer oaId) {
		oa_id = oaId;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public Integer getWare_id() {
		return ware_id;
	}

	public void setWare_id(Integer wareId) {
		ware_id = wareId;
	}

	public String getCqStatus() {
		return cqStatus;
	}

	public void setCqStatus(String cqStatus) {
		this.cqStatus = cqStatus;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getMatetag() {
		return matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
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

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
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

	public String getProcessPieceNum() {
		return processPieceNum;
	}

	public void setProcessPieceNum(String processPieceNum) {
		this.processPieceNum = processPieceNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getGiveBackNum() {
		return giveBackNum;
	}

	public void setGiveBackNum(Float giveBackNum) {
		this.giveBackNum = giveBackNum;
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

	public Set<Also> getAlsos() {
		return alsos;
	}

	public void setAlsos(Set<Also> alsos) {
		this.alsos = alsos;
	}

	public Set<Scrap> getScraps() {
		return scraps;
	}

	public void setScraps(Set<Scrap> scraps) {
		this.scraps = scraps;
	}
}
