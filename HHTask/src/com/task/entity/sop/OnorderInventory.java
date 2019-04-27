package com.task.entity.sop;
/*
 * 在途
 */
public class OnorderInventory implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id; 
	private String orderNumber;//订单号
	private String markId;//件号
	private String ywMarkId;//业务件号
	private String name;//名称
	private String productStyle;//生产类型
	private String procardTime;// 制卡时间
	private String status;// 状态
	private Float filnalCount;// 数量(实际需要生产数量)
	private Float rukuCount;// 入库数量(申请入库数量和已入库数量的结合)
	private Float tlPrice;//投料金额(数量*不含税价)
	private Float ylPrice;//已领金额(已领数量*不含税价)
	private Float contractCompletion;//订单约单完工量(已领金额/投料金额)
	private Float singleProductionOntheway;//在途约单产量(订单约单完工量*数量-入库数量)
	private Float singleMaterialCost;//	单台材料成本(投料金额/数量)
	private Float materialsInTransit;//在途材料(单台材料成本*在途约单产量)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductStyle() {
		return productStyle;
	}
	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}
	public String getProcardTime() {
		return procardTime;
	}
	public void setProcardTime(String procardTime) {
		this.procardTime = procardTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getFilnalCount() {
		return filnalCount;
	}
	public void setFilnalCount(Float filnalCount) {
		this.filnalCount = filnalCount;
	}
	public Float getRukuCount() {
		return rukuCount;
	}
	public void setRukuCount(Float rukuCount) {
		this.rukuCount = rukuCount;
	}
	public Float getTlPrice() {
		return tlPrice;
	}
	public void setTlPrice(Float tlPrice) {
		this.tlPrice = tlPrice;
	}
	public Float getYlPrice() {
		return ylPrice;
	}
	public void setYlPrice(Float ylPrice) {
		this.ylPrice = ylPrice;
	}
	public Float getContractCompletion() {
		return contractCompletion;
	}
	public void setContractCompletion(Float contractCompletion) {
		this.contractCompletion = contractCompletion;
	}
	public Float getSingleProductionOntheway() {
		return singleProductionOntheway;
	}
	public void setSingleProductionOntheway(Float singleProductionOntheway) {
		this.singleProductionOntheway = singleProductionOntheway;
	}
	public Float getSingleMaterialCost() {
		return singleMaterialCost;
	}
	public void setSingleMaterialCost(Float singleMaterialCost) {
		this.singleMaterialCost = singleMaterialCost;
	}
	public Float getMaterialsInTransit() {
		return materialsInTransit;
	}
	public void setMaterialsInTransit(Float materialsInTransit) {
		this.materialsInTransit = materialsInTransit;
	}
	
	
}
