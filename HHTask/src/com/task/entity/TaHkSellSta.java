package com.task.entity;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * ta_hk_sellSta
 */
public class TaHkSellSta implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private TaHkHuikuan taHkHuikuan;//与回款信息多对一
	private Integer oldHkId;//当确认开票数量为0时接触与TaHkHuikuan之前的关系，用来保存TaHkHuikuan的id
	private TaHkShoppingCard taShoppingCard;//与回款任务栏多对一
	private TaHkHkInvoice TaHkHkInvoice;//与发票信息对应
	private String hkSellNum;
	private String hkSellSendId;//送货单号
	private String hkSellMarkId;//件号
	private String hkSelfCard;//批次
	private String hkSellGoods;//品名
	private Float applyCount;//申请开票数量
	private Float hkSellCount;//实际开票数量
	private Float hkcount;//回款数量（页面显示）
	private Float hkmoney;//回款金额（页面显示）
	private Float hkSellPrice;//单价
	private Float hkSellMoney;//总额
	private String hkSellMore;//备注
	private String hkSellCumpanyName;//客户
	private String hkSellOrderId;//订单号（内部）
	private String hkSellOutOrderId;//订单号(外部)
	private String hkSellFile;//上传文件
	private String hkSellMoneyUnit;//币种
	private String hkSellIsTax;//单价是否含税
	private String hksellTime;//添加送货单时间
	private String hksellUsername;//添加人员姓名
	
	
	
	public String getHkSellIsTax() {
		return hkSellIsTax;
	}

	public void setHkSellIsTax(String hkSellIsTax) {
		this.hkSellIsTax = hkSellIsTax;
	}
	@JSONField(serialize = false)
	public TaHkHkInvoice getTaHkHkInvoice() {
		return TaHkHkInvoice;
	}

	public void setTaHkHkInvoice(TaHkHkInvoice taHkHkInvoice) {
		TaHkHkInvoice = taHkHkInvoice;
	}

	public String getHkSellFile() {
		return hkSellFile;
	}

	public String getHkSellMoneyUnit() {
		return hkSellMoneyUnit;
	}

	public void setHkSellMoneyUnit(String hkSellMoneyUnit) {
		this.hkSellMoneyUnit = hkSellMoneyUnit;
	}
	@JSONField(serialize = false)
	public TaHkShoppingCard getTaShoppingCard() {
		return taShoppingCard;
	}

	public void setTaShoppingCard(TaHkShoppingCard taShoppingCard) {
		this.taShoppingCard = taShoppingCard;
	}

	public void setHkSellFile(String hkSellFile) {
		this.hkSellFile = hkSellFile;
	}

	/** default constructor */
	public TaHkSellSta() {
	}

	/** minimal constructor */
	public TaHkSellSta(TaHkHuikuan taHkHuikuan, String hkSellSendId) {
		this.taHkHuikuan = taHkHuikuan;
		this.hkSellSendId = hkSellSendId;
	}

	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize = false)
	public TaHkHuikuan getTaHkHuikuan() {
		return this.taHkHuikuan;
	}

	public void setTaHkHuikuan(TaHkHuikuan taHkHuikuan) {
		this.taHkHuikuan = taHkHuikuan;
	}

	public String getHkSellNum() {
		return this.hkSellNum;
	}

	public void setHkSellNum(String hkSellNum) {
		this.hkSellNum = hkSellNum;
	}

	public String getHkSellSendId() {
		return this.hkSellSendId;
	}

	public void setHkSellSendId(String hkSellSendId) {
		this.hkSellSendId = hkSellSendId;
	}

	public String getHkSellMarkId() {
		return this.hkSellMarkId;
	}

	public void setHkSellMarkId(String hkSellMarkId) {
		this.hkSellMarkId = hkSellMarkId;
	}

	public String getHkSellGoods() {
		return this.hkSellGoods;
	}

	public void setHkSellGoods(String hkSellGoods) {
		this.hkSellGoods = hkSellGoods;
	}

	public Float getHkSellCount() {
		return hkSellCount;
	}

	public void setHkSellCount(Float hkSellCount) {
		this.hkSellCount = hkSellCount;
	}

	public Float getHkSellPrice() {
		return this.hkSellPrice;
	}

	public void setHkSellPrice(Float hkSellPrice) {
		this.hkSellPrice = hkSellPrice;
	}

	public Float getHkSellMoney() {
		return this.hkSellMoney;
	}

	public void setHkSellMoney(Float hkSellMoney) {
		this.hkSellMoney = hkSellMoney;
	}

	public String getHkSellMore() {
		return this.hkSellMore;
	}

	public void setHkSellMore(String hkSellMore) {
		this.hkSellMore = hkSellMore;
	}

	public String getHkSellCumpanyName() {
		return hkSellCumpanyName;
	}

	public void setHkSellCumpanyName(String hkSellCumpanyName) {
		this.hkSellCumpanyName = hkSellCumpanyName;
	}

	public String getHkSellOrderId() {
		return hkSellOrderId;
	}

	public void setHkSellOrderId(String hkSellOrderId) {
		this.hkSellOrderId = hkSellOrderId;
	}

	public String getHksellTime() {
		return hksellTime;
	}

	public void setHksellTime(String hksellTime) {
		this.hksellTime = hksellTime;
	}

	public String getHksellUsername() {
		return hksellUsername;
	}

	public void setHksellUsername(String hksellUsername) {
		this.hksellUsername = hksellUsername;
	}

	public Float getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getOldHkId() {
		return oldHkId;
	}

	public void setOldHkId(Integer oldHkId) {
		this.oldHkId = oldHkId;
	}

	public Float getHkcount() {
		return hkcount;
	}

	public void setHkcount(Float hkcount) {
		this.hkcount = hkcount;
	}

	public Float getHkmoney() {
		return hkmoney;
	}

	public void setHkmoney(Float hkmoney) {
		this.hkmoney = hkmoney;
	}

	public String getHkSelfCard() {
		return hkSelfCard;
	}

	public void setHkSelfCard(String hkSelfCard) {
		this.hkSelfCard = hkSelfCard;
	}

	public String getHkSellOutOrderId() {
		return hkSellOutOrderId;
	}

	public void setHkSellOutOrderId(String hkSellOutOrderId) {
		this.hkSellOutOrderId = hkSellOutOrderId;
	}
	
	
	
	/*//重写equals方法
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj!=null && obj.getClass() == TaHkSellSta.class){
			TaHkSellSta oaApp = (TaHkSellSta)obj;
			if(this.getHkSellCumpanyName().equals(oaApp.getHkSellCumpanyName())){
				return true;
			}			
		}
		return false;
	}
	//根据DetailSeqNum属性来计算hashCode
	public int hashCode(){
		return hkSellCumpanyName.hashCode();
	}*/
}
