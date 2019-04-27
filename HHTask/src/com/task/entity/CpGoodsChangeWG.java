package com.task.entity;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 成品库--》外购件（调仓）记录表
 * ta_CpGoodsChangeWG
 * @author Administrator
 *
 */
public class CpGoodsChangeWG implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer goodsId;//库存Id
	@FieldMeta(name="件号")
	private String goodsMarkId;// 件号
	@FieldMeta(name="批次")
	private String goodsLotId;//批次
	@FieldMeta(name="品名")
	private String goodsFullName;//品名
	@FieldMeta(name="规格")
	private String goodsFormat;//规格
	@FieldMeta(name="单位")
	private String goodsUnit;//单位
	
	@FieldMeta(name="申请调仓数量")
	private Float changeCount; //申请调仓数量
	
	private Float actualChangeCount;//实际调仓数量
	
	
	private Integer changePeopleId;//调仓人Id
	@FieldMeta(name="调仓人姓名")
	private String changePeoleName;//调仓人姓名
	@FieldMeta(name="申请时间")
	private String changeDate;//申请调仓时间
	private String changeTime;
	
	
	private Integer sellId;//出库Id
	private Integer GoodsStoreId;//入库Id
	
	
	//********************************************************审批
	private Integer ep_Id;//审批Id
	private String ep_status;//审批状态
	
	
	//**************************************************成品库出库(sell)
	private Integer cpSellId;  //出库Id
	private String cpGoodsClass;// 库别
	private String cpGoodHouseName;// 仓区
	private String cpGoodsPosition;// 库位
	
	
	//**************************************************外购件库入库(GoodsStore)
	private Integer wgGoodStoreId;  //入库Id
	private String wgGoodsClass;// 库别
	private String wgGoodHouseName;// 仓区
	private String wgGoodsPosition;// 库位
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsLotId() {
		return goodsLotId;
	}
	public void setGoodsLotId(String goodsLotId) {
		this.goodsLotId = goodsLotId;
	}
	public String getGoodsFullName() {
		return goodsFullName;
	}
	public void setGoodsFullName(String goodsFullName) {
		this.goodsFullName = goodsFullName;
	}
	public String getGoodsFormat() {
		return goodsFormat;
	}
	public void setGoodsFormat(String goodsFormat) {
		this.goodsFormat = goodsFormat;
	}
	
	public Integer getChangePeopleId() {
		return changePeopleId;
	}
	public void setChangePeopleId(Integer changePeopleId) {
		this.changePeopleId = changePeopleId;
	}
	public String getChangePeoleName() {
		return changePeoleName;
	}
	public void setChangePeoleName(String changePeoleName) {
		this.changePeoleName = changePeoleName;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public Integer getGoodsStoreId() {
		return GoodsStoreId;
	}
	public void setGoodsStoreId(Integer goodsStoreId) {
		GoodsStoreId = goodsStoreId;
	}
	public Integer getEp_Id() {
		return ep_Id;
	}
	public void setEp_Id(Integer epId) {
		ep_Id = epId;
	}
	public String getEp_status() {
		return ep_status;
	}
	public void setEp_status(String epStatus) {
		ep_status = epStatus;
	}
	
	public String getCpGoodsClass() {
		return cpGoodsClass;
	}
	public void setCpGoodsClass(String cpGoodsClass) {
		this.cpGoodsClass = cpGoodsClass;
	}
	public String getCpGoodHouseName() {
		return cpGoodHouseName;
	}
	public void setCpGoodHouseName(String cpGoodHouseName) {
		this.cpGoodHouseName = cpGoodHouseName;
	}
	public String getCpGoodsPosition() {
		return cpGoodsPosition;
	}
	public void setCpGoodsPosition(String cpGoodsPosition) {
		this.cpGoodsPosition = cpGoodsPosition;
	}
	
	public String getWgGoodsClass() {
		return wgGoodsClass;
	}
	public void setWgGoodsClass(String wgGoodsClass) {
		this.wgGoodsClass = wgGoodsClass;
	}
	public String getWgGoodHouseName() {
		return wgGoodHouseName;
	}
	public void setWgGoodHouseName(String wgGoodHouseName) {
		this.wgGoodHouseName = wgGoodHouseName;
	}
	public String getWgGoodsPosition() {
		return wgGoodsPosition;
	}
	public void setWgGoodsPosition(String wgGoodsPosition) {
		this.wgGoodsPosition = wgGoodsPosition;
	}
	
	public void setChangeCount(Float changeCount) {
		this.changeCount = changeCount;
	}
	public Float getChangeCount() {
		return changeCount;
	}
	public void setGoodsMarkId(String goodsMarkId) {
		this.goodsMarkId = goodsMarkId;
	}
	public String getGoodsMarkId() {
		return goodsMarkId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setCpSellId(Integer cpSellId) {
		this.cpSellId = cpSellId;
	}
	public Integer getCpSellId() {
		return cpSellId;
	}
	public void setWgGoodStoreId(Integer wgGoodStoreId) {
		this.wgGoodStoreId = wgGoodStoreId;
	}
	public Integer getWgGoodStoreId() {
		return wgGoodStoreId;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setActualChangeCount(Float actualChangeCount) {
		this.actualChangeCount = actualChangeCount;
	}
	public Float getActualChangeCount() {
		return actualChangeCount;
	}
	
	
	
	
	
	
}
