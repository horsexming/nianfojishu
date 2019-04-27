package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 绑定关系表 2016-11-17
 * 
 * @author Li_Cong 表名 ta_mj_WareBangGoogs 管理仓区和库存的中间表，记录库存在此仓区的数量
 */
public class WareBangGoogs implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer fk_good_id;// 库存ID(库存表goods)
	private Integer fk_waigouDeliveryDetail_id;// 送货单明细ID
	private Integer fk_ware_id;// 库位ID
	private Integer fk_oadetail_id;// OA采购ID
	private Integer fk_store_id;// OA采购对应库位ID
	private Integer fk_goodsStore_id;// 入库记录Id(入库记录表 goodsStore)
	private Float number;// 数量
	private String status;// 状态（待存入/待检验/检验中/待选择/待确认/入库）
	private String towCode;// 二维码
	private Float lqNumber;// 减去的数量(页面传值，不存入数据库)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFk_store_id() {
		return fk_store_id;
	}

	public void setFk_store_id(Integer fkStoreId) {
		fk_store_id = fkStoreId;
	}

	public Integer getFk_oadetail_id() {
		return fk_oadetail_id;
	}

	public void setFk_oadetail_id(Integer fkOadetailId) {
		fk_oadetail_id = fkOadetailId;
	}

	public Integer getFk_waigouDeliveryDetail_id() {
		return fk_waigouDeliveryDetail_id;
	}

	public void setFk_waigouDeliveryDetail_id(Integer fkWaigouDeliveryDetailId) {
		fk_waigouDeliveryDetail_id = fkWaigouDeliveryDetailId;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFk_good_id() {
		return fk_good_id;
	}

	public void setFk_good_id(Integer fkGoodId) {
		fk_good_id = fkGoodId;
	}

	public Integer getFk_ware_id() {
		return fk_ware_id;
	}

	public void setFk_ware_id(Integer fkWareId) {
		fk_ware_id = fkWareId;
	}

	public String getTowCode() {
		return towCode;
	}

	public void setTowCode(String towCode) {
		this.towCode = towCode;
	}

	public Float getLqNumber() {
		return lqNumber;
	}

	public void setLqNumber(Float lqNumber) {
		this.lqNumber = lqNumber;
	}

	public Integer getFk_goodsStore_id() {
		return fk_goodsStore_id;
	}

	public void setFk_goodsStore_id(Integer fkGoodsStoreId) {
		fk_goodsStore_id = fkGoodsStoreId;
	}

}
