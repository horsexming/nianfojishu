package com.task.entity.bargain;

import java.io.Serializable;
import java.util.Set;
/***
 * 合同明细表
 * 表名：ta_BarContractDetails
 * @author 毛小龙
 *
 */

public class BarContractDetails implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Float zongMoney;//总金额
	private BarContract barContract;
	/*****OA*********/
	private String detailAppName;// 物品名称 ------
	private String detailChildClass;// 类型（办公用品，金属五金，杂品）
	private String detailFormat;// 规格---
	private Float detailCount;// 数量（申报）---
	private String detailUnit;// 单位---
	private Float detailBudgetMoney;// 预算单价（报修和采购需填）---
	private String detailItemId;//项目编号
	/*****询比议价********/
	private String goods_name;//品名
	private String goods_format;//规格
	private String goods_amount;//采购数量
	private String money;//单价
	private String goods_unit;//单位
	private String purchase_delivery;//采购交期
	private String quality_requirements;//质量要求
	private String remark;//备注
	/*******设备维修***********/
//	private String type;//设备类型
//	private String name;//设备名称
//	private String barcode;// 报修单号
//	private String macrepair_unit;// 设备单位
//	private String macrepair_money;// 设备单价
//	private String macrepair_amount;// 设备数量
//	private String macrepair_classGroup;//所在班组
//	private String macrepair_alermMan;//报修人
	
	private String macrepair_name;//设备名称
	private String macrepair_format;//规格
	private String macrepair_amount;//数量
	private String macrepair_money;//单价
	private String macrepair_unit;//单位
	private String macrepair_remark;//备注
	
	/**************设备****************/
	private String machine_no;// 设备编号
	private String machine_name;// 设备名称
	private String machine_type;// 设备类型
	private String machine_classGroup; // 部门
	private String machine_buyamount;// 购买金额
	
	/*******其他************/
	/***临时采购***/
	private String qtName;// 品名 ------
	private String qtUnit;// 单位
	private String qtNum;// 数量
	private Float qtMoney;// 单价
	
	/**** 零部件及工序外委采购*****/
	private String gx_name;//名称
	private String gx_number;//件号
	private String gx_price;//单价
	private String gx_type;//型别
	private String gx_producetype;//生产类型
	private String gx_goodstype;//产品类型
	private String gx_status;//外委工序状态
	private String gx_projectnum;//项目编号
	private String gx_quotedNumber;//询价单号
	
	
	/***原材料采购****/
	private String materials_name;//牌号
	private String materials_format;//规格
	private String materials_unit;//单位
	private String materials_price;//单价
	
	/******工装采购**********/
	private String frock_applynum;//申请单号
	private String frock_biddingnum;//招标单号
	private String frock_name;//名称
	private String frock_partnum;//零件号
	private String frock_num;//工装号
	private String frock_amount;//数量
	private String frock_money;//金额
	
	/**********KVP***************/
	private String executeNumber;//项目执行编号
	private String improve_username;//改进员工
	private String res_username;//责任员工
	private Float costsavings;//成本结余
	
	private String wgType;//物料类别(页面传值)
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetailAppName() {
		return detailAppName;
	}
	public void setDetailAppName(String detailAppName) {
		this.detailAppName = detailAppName;
	}
	public String getDetailChildClass() {
		return detailChildClass;
	}
	public void setDetailChildClass(String detailChildClass) {
		this.detailChildClass = detailChildClass;
	}
	public String getDetailFormat() {
		return detailFormat;
	}
	public void setDetailFormat(String detailFormat) {
		this.detailFormat = detailFormat;
	}
	public Float getDetailCount() {
		return detailCount;
	}
	public void setDetailCount(Float detailCount) {
		this.detailCount = detailCount;
	}
	public String getDetailUnit() {
		return detailUnit;
	}
	public void setDetailUnit(String detailUnit) {
		this.detailUnit = detailUnit;
	}
	public Float getDetailBudgetMoney() {
		return detailBudgetMoney;
	}
	public void setDetailBudgetMoney(Float detailBudgetMoney) {
		this.detailBudgetMoney = detailBudgetMoney;
	}
	 
	public BarContract getBarContract() {
		return barContract;
	}
	public void setBarContract(BarContract barContract) {
		this.barContract = barContract;
	}
	public Float getZongMoney() {
		return zongMoney;
	}
	public void setZongMoney(Float zongMoney) {
		this.zongMoney = zongMoney;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goodsName) {
		goods_name = goodsName;
	}
	public String getGoods_format() {
		return goods_format;
	}
	public void setGoods_format(String goodsFormat) {
		goods_format = goodsFormat;
	}
	public String getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(String goodsAmount) {
		goods_amount = goodsAmount;
	}
	public String getGoods_unit() {
		return goods_unit;
	}
	public void setGoods_unit(String goodsUnit) {
		goods_unit = goodsUnit;
	}
	public String getPurchase_delivery() {
		return purchase_delivery;
	}
	public void setPurchase_delivery(String purchaseDelivery) {
		purchase_delivery = purchaseDelivery;
	}
	public String getQuality_requirements() {
		return quality_requirements;
	}
	public void setQuality_requirements(String qualityRequirements) {
		quality_requirements = qualityRequirements;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	 
	public String getMacrepair_unit() {
		return macrepair_unit;
	}
	public void setMacrepair_unit(String macrepairUnit) {
		macrepair_unit = macrepairUnit;
	}
	public String getMacrepair_money() {
		return macrepair_money;
	}
	public void setMacrepair_money(String macrepairMoney) {
		macrepair_money = macrepairMoney;
	}
	public String getMacrepair_amount() {
		return macrepair_amount;
	}
	public void setMacrepair_amount(String macrepairAmount) {
		macrepair_amount = macrepairAmount;
	}
	public String getQtName() {
		return qtName;
	}
	public void setQtName(String qtName) {
		this.qtName = qtName;
	}
 
	public String getQtNum() {
		return qtNum;
	}
	public void setQtNum(String qtNum) {
		this.qtNum = qtNum;
	}
	public Float getQtMoney() {
		return qtMoney;
	}
	public void setQtMoney(Float qtMoney) {
		this.qtMoney = qtMoney;
	}
	public String getQtUnit() {
		return qtUnit;
	}
	public void setQtUnit(String qtUnit) {
		this.qtUnit = qtUnit;
	}
	public String getGx_name() {
		return gx_name;
	}
	public void setGx_name(String gxName) {
		gx_name = gxName;
	}
	public String getGx_number() {
		return gx_number;
	}
	public void setGx_number(String gxNumber) {
		gx_number = gxNumber;
	}
	public String getGx_price() {
		return gx_price;
	}
	public void setGx_price(String gxPrice) {
		gx_price = gxPrice;
	}
	public String getGx_type() {
		return gx_type;
	}
	public void setGx_type(String gxType) {
		gx_type = gxType;
	}
	public String getGx_producetype() {
		return gx_producetype;
	}
	public void setGx_producetype(String gxProducetype) {
		gx_producetype = gxProducetype;
	}
	public String getGx_goodstype() {
		return gx_goodstype;
	}
	public void setGx_goodstype(String gxGoodstype) {
		gx_goodstype = gxGoodstype;
	}
	public String getMaterials_name() {
		return materials_name;
	}
	public void setMaterials_name(String materialsName) {
		materials_name = materialsName;
	}
	public String getMaterials_format() {
		return materials_format;
	}
	public void setMaterials_format(String materialsFormat) {
		materials_format = materialsFormat;
	}
	public String getMaterials_unit() {
		return materials_unit;
	}
	public void setMaterials_unit(String materialsUnit) {
		materials_unit = materialsUnit;
	}
	public String getMaterials_price() {
		return materials_price;
	}
	public void setMaterials_price(String materialsPrice) {
		materials_price = materialsPrice;
	}
	public String getFrock_applynum() {
		return frock_applynum;
	}
	public void setFrock_applynum(String frockApplynum) {
		frock_applynum = frockApplynum;
	}
	public String getFrock_biddingnum() {
		return frock_biddingnum;
	}
	public void setFrock_biddingnum(String frockBiddingnum) {
		frock_biddingnum = frockBiddingnum;
	}
	public String getFrock_name() {
		return frock_name;
	}
	public void setFrock_name(String frockName) {
		frock_name = frockName;
	}
	public String getFrock_partnum() {
		return frock_partnum;
	}
	public void setFrock_partnum(String frockPartnum) {
		frock_partnum = frockPartnum;
	}
	public String getFrock_num() {
		return frock_num;
	}
	public void setFrock_num(String frockNum) {
		frock_num = frockNum;
	}
	public String getFrock_amount() {
		return frock_amount;
	}
	public void setFrock_amount(String frockAmount) {
		frock_amount = frockAmount;
	}
	public String getFrock_money() {
		return frock_money;
	}
	public void setFrock_money(String frockMoney) {
		frock_money = frockMoney;
	}
	public String getGx_status() {
		return gx_status;
	}
	public void setGx_status(String gxStatus) {
		gx_status = gxStatus;
	}
	public String getGx_projectnum() {
		return gx_projectnum;
	}
	public void setGx_projectnum(String gxProjectnum) {
		gx_projectnum = gxProjectnum;
	}
	public String getGx_quotedNumber() {
		return gx_quotedNumber;
	}
	public void setGx_quotedNumber(String gxQuotedNumber) {
		gx_quotedNumber = gxQuotedNumber;
	}
	public String getDetailItemId() {
		return detailItemId;
	}
	public void setDetailItemId(String detailItemId) {
		this.detailItemId = detailItemId;
	}
	public String getMachine_no() {
		return machine_no;
	}
	public void setMachine_no(String machineNo) {
		machine_no = machineNo;
	}
	public String getMachine_name() {
		return machine_name;
	}
	public void setMachine_name(String machineName) {
		machine_name = machineName;
	}
	public String getMachine_type() {
		return machine_type;
	}
	public void setMachine_type(String machineType) {
		machine_type = machineType;
	}
	public String getMachine_classGroup() {
		return machine_classGroup;
	}
	public void setMachine_classGroup(String machineClassGroup) {
		machine_classGroup = machineClassGroup;
	}
	public String getMachine_buyamount() {
		return machine_buyamount;
	}
	public void setMachine_buyamount(String machineBuyamount) {
		machine_buyamount = machineBuyamount;
	}
	public String getMacrepair_name() {
		return macrepair_name;
	}
	public void setMacrepair_name(String macrepairName) {
		macrepair_name = macrepairName;
	}
	public String getMacrepair_format() {
		return macrepair_format;
	}
	public void setMacrepair_format(String macrepairFormat) {
		macrepair_format = macrepairFormat;
	}
	public String getMacrepair_remark() {
		return macrepair_remark;
	}
	public void setMacrepair_remark(String macrepairRemark) {
		macrepair_remark = macrepairRemark;
	}
	public String getExecuteNumber() {
		return executeNumber;
	}
	public void setExecuteNumber(String executeNumber) {
		this.executeNumber = executeNumber;
	}
	public String getImprove_username() {
		return improve_username;
	}
	public void setImprove_username(String improveUsername) {
		improve_username = improveUsername;
	}
	public String getRes_username() {
		return res_username;
	}
	public void setRes_username(String resUsername) {
		res_username = resUsername;
	}
	public Float getCostsavings() {
		return costsavings;
	}
	public void setCostsavings(Float costsavings) {
		this.costsavings = costsavings;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

}
