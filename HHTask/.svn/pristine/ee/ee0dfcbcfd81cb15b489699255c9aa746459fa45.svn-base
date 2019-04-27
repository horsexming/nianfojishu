package com.task.entity.caiwu.core;

import java.io.Serializable;

/**
 * 收付款管理汇总表[总账](ta_fin_caiwu_SupplierCorePayable)
 * 
 * @author 刘培
 * 
 */
public class SupplierCorePayable implements Serializable{
	private Integer id;
	private String supplierName;// 收/付款单位（供应商名称/客户/其他）【需对应Payee的name】
	private Integer payeeId;// 收/付款单位id（供应商名称/客户/其他）【需对应Payee的id】
	private String coreType;// 付款类型(主营/非主营)
	private String payableType;// 收付款类型(收款/付款)
	private Integer supplierId;// 供应商id (数据id)
	private Integer customerId;// 客户id (数据id)

	// 货物总计
	private Float cargoTotal;// 货物总量
	private Float shouNumber;// 收货量
	private Float goodsNumber;// 存货量
	private Float useNumber;// 使用量
	private Float sellNumber;// 发货量

	// 开票总计
	private Float billingTotal;// 开票总量
	private Float kaipiaoYgz;// 开票已挂账
	private Float kaipiaoWgz;// 开票未挂账
	private Float weiKaipiao;// 未开票
	private Float jieqing;// 结清

	// 付款总计
	private Integer paymentTotal;// 货款总计
	private Integer fkCount;// 付款次数
	private Float yingfukuanJine;// 应付款金额 / 应收
	private Float overTimeYfmoney;// 到期 应付款金额/应收款金额
	private Float realfukuanJine;// 已付款金额 / 已收
	private Float weifukuanJine;// 未付款金额 / 未收
	private Float fukuanzhongJine;// 付款中金额 / 收款中
	private String more;// 备注
	private String addTime;// 添加时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getFkCount() {
		return fkCount;
	}

	public void setFkCount(Integer fkCount) {
		this.fkCount = fkCount;
	}

	public Float getYingfukuanJine() {
		return yingfukuanJine;
	}

	public void setYingfukuanJine(Float yingfukuanJine) {
		this.yingfukuanJine = yingfukuanJine;
	}

	public Float getRealfukuanJine() {
		return realfukuanJine;
	}

	public void setRealfukuanJine(Float realfukuanJine) {
		this.realfukuanJine = realfukuanJine;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Float getWeifukuanJine() {
		return weifukuanJine;
	}

	public void setWeifukuanJine(Float weifukuanJine) {
		this.weifukuanJine = weifukuanJine;
	}

	public Float getOverTimeYfmoney() {
		return overTimeYfmoney;
	}

	public void setOverTimeYfmoney(Float overTimeYfmoney) {
		this.overTimeYfmoney = overTimeYfmoney;
	}

	public Float getFukuanzhongJine() {
		return fukuanzhongJine;
	}

	public void setFukuanzhongJine(Float fukuanzhongJine) {
		this.fukuanzhongJine = fukuanzhongJine;
	}

	public Float getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Float goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Float getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(Float useNumber) {
		this.useNumber = useNumber;
	}

	public Float getSellNumber() {
		return sellNumber;
	}

	public void setSellNumber(Float sellNumber) {
		this.sellNumber = sellNumber;
	}

	public Float getKaipiaoYgz() {
		return kaipiaoYgz;
	}

	public void setKaipiaoYgz(Float kaipiaoYgz) {
		this.kaipiaoYgz = kaipiaoYgz;
	}

	public Float getKaipiaoWgz() {
		return kaipiaoWgz;
	}

	public void setKaipiaoWgz(Float kaipiaoWgz) {
		this.kaipiaoWgz = kaipiaoWgz;
	}

	public Float getWeiKaipiao() {
		return weiKaipiao;
	}

	public void setWeiKaipiao(Float weiKaipiao) {
		this.weiKaipiao = weiKaipiao;
	}

	public Float getCargoTotal() {
		return cargoTotal;
	}

	public void setCargoTotal(Float cargoTotal) {
		this.cargoTotal = cargoTotal;
	}

	public Float getBillingTotal() {
		return billingTotal;
	}

	public void setBillingTotal(Float billingTotal) {
		this.billingTotal = billingTotal;
	}

	public Integer getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(Integer paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public Float getShouNumber() {
		return shouNumber;
	}

	public void setShouNumber(Float shouNumber) {
		this.shouNumber = shouNumber;
	}

	public Float getJieqing() {
		return jieqing;
	}

	public void setJieqing(Float jieqing) {
		this.jieqing = jieqing;
	}

	public String getCoreType() {
		return coreType;
	}

	public void setCoreType(String coreType) {
		this.coreType = coreType;
	}

	public String getPayableType() {
		return payableType;
	}

	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 *            the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}

	public Integer getPayeeId() {
		return payeeId;
	}
}
