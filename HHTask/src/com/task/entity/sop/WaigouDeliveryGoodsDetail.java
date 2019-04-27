package com.task.entity.sop;

/****
 * 成品送货单明细
 * 
 * @author 刘培
 * @表名 ta_sop_w_WaigouDeliveryGoodsDetail
 * 
 */
public class WaigouDeliveryGoodsDetail implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String cgOrderNum;// 采购订单编号

	private String markId;// 件号
	private String selfCard;// 批次
	private String ywmarkId;// 业务件号
	private String banben;// 版本
	private Integer banci;// 版次
	private String tuhao;// 图号
	private String proName;// 零件名称
	private String processNo;// 工序号
	private String processName;// 工序名称
	private String specification;// 规格
	private String unit;// 单位
	private Float hsPrice;// 含税单价
	private Float price;// 不含税单价
	private Float taxprice; // 税率
	private Integer priceId;// 价格id

	private Integer khId;// 客户id
	private String khName;// 客户名称名称

	private Float shNumber;// 送货数量
	private Float qrNumber;// 确认数量
	private String qrWeizhi;// 确认位置
	private Float hgNumber;// 合格数量
	private Float ycNumber;// 已存入数量
	private Float bhgNumber;// 不合格数量
	private Float lingliaoNum;// 领料数量
	private Float yrukuNum;// 已入库数量;
	private Float ctn;// 箱单数
	private Float oneCtnNum;// 单箱数量

	/******** 供应商相关 *******/
	private String addTime;// 添加时间
	private String printTime;// 打印时间（仓库打印）
	private String jinmenTime;// 出门时间（物流扫描）
	private String querenTime;// 客户确认时间
	private String jianyanTime;// 检验时间
	private String rukuTime;// 入库时间

	private String remarks;// 备注

	private String status;// 状态(待打印、送货中、待存柜、待检验、检验中、待入库、入库、已领)
	private String isprint;// 是否打印;(YES/NO)

	private Integer goodsId;// 对应库存信息

	private WaigouDeliveryGoods waigouDeliveryGoods;// 送货单（多对一）
	private String classNames;// 前端显示
	private String jyuserName;// 检验人姓名;
	private String thStatus;// 退货状态（待领,已领） (禁用)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCgOrderNum() {
		return cgOrderNum;
	}

	public void setCgOrderNum(String cgOrderNum) {
		this.cgOrderNum = cgOrderNum;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getYwmarkId() {
		return ywmarkId;
	}

	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}

	public String getBanben() {
		return banben;
	}

	public void setBanben(String banben) {
		this.banben = banben;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getHsPrice() {
		return hsPrice;
	}

	public void setHsPrice(Float hsPrice) {
		this.hsPrice = hsPrice;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Float taxprice) {
		this.taxprice = taxprice;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getKhId() {
		return khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public String getKhName() {
		return khName;
	}

	public void setKhName(String khName) {
		this.khName = khName;
	}

	public Float getShNumber() {
		return shNumber;
	}

	public void setShNumber(Float shNumber) {
		this.shNumber = shNumber;
	}

	public Float getQrNumber() {
		return qrNumber;
	}

	public void setQrNumber(Float qrNumber) {
		this.qrNumber = qrNumber;
	}

	public String getQrWeizhi() {
		return qrWeizhi;
	}

	public void setQrWeizhi(String qrWeizhi) {
		this.qrWeizhi = qrWeizhi;
	}

	public Float getHgNumber() {
		return hgNumber;
	}

	public void setHgNumber(Float hgNumber) {
		this.hgNumber = hgNumber;
	}

	public Float getYcNumber() {
		return ycNumber;
	}

	public void setYcNumber(Float ycNumber) {
		this.ycNumber = ycNumber;
	}

	public Float getBhgNumber() {
		return bhgNumber;
	}

	public void setBhgNumber(Float bhgNumber) {
		this.bhgNumber = bhgNumber;
	}

	public Float getLingliaoNum() {
		return lingliaoNum;
	}

	public void setLingliaoNum(Float lingliaoNum) {
		this.lingliaoNum = lingliaoNum;
	}

	public Float getYrukuNum() {
		return yrukuNum;
	}

	public void setYrukuNum(Float yrukuNum) {
		this.yrukuNum = yrukuNum;
	}

	public Float getCtn() {
		return ctn;
	}

	public void setCtn(Float ctn) {
		this.ctn = ctn;
	}

	public Float getOneCtnNum() {
		return oneCtnNum;
	}

	public void setOneCtnNum(Float oneCtnNum) {
		this.oneCtnNum = oneCtnNum;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public String getJinmenTime() {
		return jinmenTime;
	}

	public void setJinmenTime(String jinmenTime) {
		this.jinmenTime = jinmenTime;
	}

	public String getQuerenTime() {
		return querenTime;
	}

	public void setQuerenTime(String querenTime) {
		this.querenTime = querenTime;
	}

	public String getJianyanTime() {
		return jianyanTime;
	}

	public void setJianyanTime(String jianyanTime) {
		this.jianyanTime = jianyanTime;
	}

	public String getRukuTime() {
		return rukuTime;
	}

	public void setRukuTime(String rukuTime) {
		this.rukuTime = rukuTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsprint() {
		return isprint;
	}

	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public String getJyuserName() {
		return jyuserName;
	}

	public void setJyuserName(String jyuserName) {
		this.jyuserName = jyuserName;
	}

	public String getThStatus() {
		return thStatus;
	}

	public void setThStatus(String thStatus) {
		this.thStatus = thStatus;
	}

	public WaigouDeliveryGoods getWaigouDeliveryGoods() {
		return waigouDeliveryGoods;
	}

	public void setWaigouDeliveryGoods(WaigouDeliveryGoods waigouDeliveryGoods) {
		this.waigouDeliveryGoods = waigouDeliveryGoods;
	}

	public String getSelfCard() {
		return selfCard;
	}

	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

}
