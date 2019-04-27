package com.task.entity;

import java.io.Serializable;

/**
 * 票据打印单明细(ta_PrintedOut)
 * 
 * @author 王晓飞
 *
 */
public class PrintedOut implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String planNum;// 编号(自动生产)
	private String type;// 类型(销售出库单、产品入库单);
	private String className;// 对应实体类名称
	private Integer entiyId;// 对应实体类Id
	private String entiyIds;//
	private String ywmarkId;// 业务件号
	private String format;// 规格型号
	private String selfCard;// 生产批次
	private String markId;// 件号
	private String banbenNum;// 版本号
	private String tuhao;// 图号
	private String proNmae;// 产品名称
	private String unit;// 单位
	private Float num;// 数量
	private String waiOrderNum;// 外部订单号
	private String neiOrderNum;// 内部订单号
	private String suodingdanhao;// 锁定单号(领料单使用)
	private String kubie;// 库别
	private String cangqu;// 仓区
	private String kehuNmae;// 客户名称
	private String shaddress;// 送货地址
	private String shPlanNum;// 送货单号
	private String sellTime;// 出库日期
	private String isPrint;// 是否打印过(YES/NO)
	private String rmeak;// 备注
	private String cgoderNumber;// 采购订单号
	private String cgUserName;// 采购员
	private String addtime;// 打印时间
	private String processName;// 工序名称
	private PrintedOutOrder printedOutOrder;
	private Float hsPrice;// 单价(含税)
	private String bhsPrice;// 单价(不含税)
	private String shuie;// 税额
	private Double taxprice; // 税率
	private Double hsTotalPrice;// 含税总价格
	private Integer printcount;// 打印次数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlanNum() {
		return planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getYwmarkId() {
		return ywmarkId;
	}

	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getProNmae() {
		return proNmae;
	}

	public void setProNmae(String proNmae) {
		this.proNmae = proNmae;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWaiOrderNum() {
		return waiOrderNum;
	}

	public void setWaiOrderNum(String waiOrderNum) {
		this.waiOrderNum = waiOrderNum;
	}

	public String getNeiOrderNum() {
		return neiOrderNum;
	}

	public void setNeiOrderNum(String neiOrderNum) {
		this.neiOrderNum = neiOrderNum;
	}

	public String getKubie() {
		return kubie;
	}

	public void setKubie(String kubie) {
		this.kubie = kubie;
	}

	public String getCangqu() {
		return cangqu;
	}

	public void setCangqu(String cangqu) {
		this.cangqu = cangqu;
	}

	public String getKehuNmae() {
		return kehuNmae;
	}

	public void setKehuNmae(String kehuNmae) {
		this.kehuNmae = kehuNmae;
	}

	public String getShaddress() {
		return shaddress;
	}

	public void setShaddress(String shaddress) {
		this.shaddress = shaddress;
	}

	public String getShPlanNum() {
		return shPlanNum;
	}

	public void setShPlanNum(String shPlanNum) {
		this.shPlanNum = shPlanNum;
	}

	public String getSellTime() {
		return sellTime;
	}

	public void setSellTime(String sellTime) {
		this.sellTime = sellTime;
	}

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public PrintedOutOrder getPrintedOutOrder() {
		return printedOutOrder;
	}

	public void setPrintedOutOrder(PrintedOutOrder printedOutOrder) {
		this.printedOutOrder = printedOutOrder;
	}

	public Integer getEntiyId() {
		return entiyId;
	}

	public void setEntiyId(Integer entiyId) {
		this.entiyId = entiyId;
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

	public String getRmeak() {
		return rmeak;
	}

	public void setRmeak(String rmeak) {
		this.rmeak = rmeak;
	}

	public String getBanbenNum() {
		return banbenNum;
	}

	public void setBanbenNum(String banbenNum) {
		this.banbenNum = banbenNum;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getSelfCard() {
		return selfCard;
	}

	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

	public String getCgoderNumber() {
		return cgoderNumber;
	}

	public void setCgoderNumber(String cgoderNumber) {
		this.cgoderNumber = cgoderNumber;
	}

	public String getCgUserName() {
		return cgUserName;
	}

	public void setCgUserName(String cgUserName) {
		this.cgUserName = cgUserName;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Float getHsPrice() {
		return hsPrice;
	}

	public void setHsPrice(Float hsPrice) {
		this.hsPrice = hsPrice;
	}

	public Double getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Double getHsTotalPrice() {
		return hsTotalPrice;
	}

	public void setHsTotalPrice(Double hsTotalPrice) {
		this.hsTotalPrice = hsTotalPrice;
	}

	public String getBhsPrice() {
		return bhsPrice;
	}

	public void setBhsPrice(String bhsPrice) {
		this.bhsPrice = bhsPrice;
	}

	public String getShuie() {
		return shuie;
	}

	public void setShuie(String shuie) {
		this.shuie = shuie;
	}

	public Integer getPrintcount() {
		return printcount;
	}

	public void setPrintcount(Integer printcount) {
		this.printcount = printcount;
	}

	public String getSuodingdanhao() {
		return suodingdanhao;
	}

	public void setSuodingdanhao(String suodingdanhao) {
		this.suodingdanhao = suodingdanhao;
	}

	public String getEntiyIds() {
		return entiyIds;
	}

	public void setEntiyIds(String entiyIds) {
		this.entiyIds = entiyIds;
	}

}