package com.task.entity;

import java.util.List;
import java.util.Set;

import com.task.entity.sop.WaigouDeliveryDetail;
import com.task.util.Util;

/**
 * 库存表(表名:goods)
 * 
 *@author 贾辉辉
 */

public class Goods implements java.io.Serializable {

	// 商品信息

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer goodsId; // ID
	/******************* 库存合并条件 **********/
	private String goodsClass;// 库别
	private String goodHouseName;// 仓区
	private String goodsPosition;// 库位
	private String goodsMarkId;// 件号
	private String banBenNumber;// 版本号
	private String kgliao;// 供货属性
	private String goodsCustomer;// 客户
	private String goodsSupplier;// 供应商
	private String customerId;// 客户ID
	private String supplierId;// 供应商ID
	private String goodsLotId;// 批次
	/******************* 可合并库存条件 结束 **********/


	private Float goodsPrice;// 单价(平均不含税)
	private Float goodshsPrice;// 单价(平均含税)
	private String goodsFullName;// 品名
	private String goodsFormat;// 规格
	private String wgType;// 物料类别
	private Float goodsCurQuantity;// 库存量
	private String goodsCurQuantitySting;//库存量字符
	private String goodsUnit;// 单位
	private Float goodsZhishu;// 转换数量
	private String goodsStoreZHUnit;// 转换单位;
	private Float goodsAvgzhishu;// 单支重量

	private Float bzApplyCount;// 包装申请数量
	private Float bzyiCount;// 包装已领数量
	private Float bztjCount;// 包装提交数量

	private Float shApplyCount;// 送货申请数量
	private Float shqrCount;// 送货确认数量
	private Float shthCount;// 送货退货数量

	private Float goodsBeginQuantity;// 期初量

	private String goodsShortName;// 简称
	private String goodsRemember;// 油封期
	private String goodsSet;// 是否可组装拆卸
	private String goodsCode; // 炉批号
	private String goodsMarkFormat;
	private Float goodsCost;// 库存成本
	private Float goodsMax;
	private Float goodsMin;
	private Integer kuweiId;// 库位id(页面传值使用)
	private String goodsBarcode;// 条码

	private Float goodsBuyPrice;// 采购批次单价(含税)
	private Float goodsBuyBhsPrice;// 采购批次单价(不含税)
	private Float taxprice;// 采购批次单价(税率)
	
	private Float goodsSellPrice;// 销售价
	private Float goodsMinSell;
	private Float goodsMaxBuy;
	private Float goodsVipPrice;
	private Float goodsWholesale1;
	private Float goodsWholesale2;
	private Float goodsWholesale3;
	private String goodsChangeTime;// 入库时间
	private String goodsDetailsTime;//入库详细时间
	private Float goodsRealPrice;
	private String goodsArtsCard;// 工艺卡片号
	private String goodsProMarkId;// 总成件号
	private String goodsMore;// 改为是‘否积’压字段
	private String goodsMore2;// 用来标识卡片中的出库
	private String goodsStyle;// 入库类型（手动入库/批量入库/返修入库/退货入库/刷卡入库（提奖依据））
	private Boolean bout;// 出库权限
	private Boolean bedit;// 编辑权限
	private Boolean isdel;;//删除权限;
	private String goodstype;// 类型（管料，板料）临时使用不存数据库
	private float ckCount;// 仓库剩余数量（临时使用数据不放入数据库）
	private Integer processNo;// 工序号(外委接受回来的标识为半成品转库的库存记录外委最后一道工序)
	private String processName;//

	private String fcStatus;// 封存状态（可用,,封存默认可用）
	private String fcApplyStatus;// 封存审批状态
	private Integer epId;// 审批流程Id
	private String epStatus;//审批状态;
	private String tuhao;// 图号(别称)

	private String neiorderId;// 内部订单号;
	private String waiorderId;// 外部订单号;
	private Integer order_Id;// 订单Id;

	private String goodslasttime;// 上次质检时间
	private String goodsnexttime;// 下次质检时间;
	private Float goodsround;// 质检周期;
	
	private Float singleCount;//单张产量
	private Float qlCount;// 缺领数量（外购件分开领取时页面传值使用）-->外委数量
	private Float tqlCount;// 本次缺领数量（外购件分开领取时页面传值使用）-->缺少数量
	private Float hqlCount;// 之前缺领数量（外购件分开领取时页面传值使用）-->已领数量
	private Float qlCountZh;// 缺领对应组合或者总成的数量（外购件分开领取时页面传值使用）
	private Float kfCount;// 可发数量（页面传值使用）
	private Float kzCount;// 页面传值使用
	private Boolean isEnough;// 库存足够页面传值
	private Boolean isChangeSf;// 是否可以改变实发页面传值
	private String showType;// 显示类型页面传值
	private Integer flag;// 领料页面同件号统一确认标记页面传值
	private Float quanzi1;// 权值1(页面传值)
	private Float quanzi2;// 权值2(页面传值)
	private Float xqCount;// 需求数量(页面传值)

	// 余料模块
	private String ylMarkId;// 领此件号产生的余料
	private String ylSelfCard;// 领此批次是产生的余料
	private String yllock;// 是否被锁定（yes,no默认不被锁定）当被锁定是只能被ylMarkId的同件号零件使用
	private float ylshifa;// 余料抵扣实发数量（临时使用数据不放入数据库）
	private Float ylApplyCount;// 申请报废或者转件号数量
	private String qlUnit;// 请领单位
	private String inputSource;// 入库来源（手动入库(来源为手动入库时出库关联的订单id为order_Id)，生产入库(默认为生产入库)）
	private Integer llGysId;// 领料产生余料的供应商（为空时表示在己放，否则就是拥有者的Id）
	private Float zyqjCount;//页面显示;
	
	private Float flushCount;//缓存数量页面传值使用
	private Float flushCount2;//缓存数量页面传值使用
	private Float flushCount3;//缓存数量页面传值使用

	private String dtcFlag;// 待调仓标记（外协调委外）
	private String islock;// 是否锁仓;(YES/NO)
	
	// Constructors
	/**
	 * 页面传值
	 */
	private String lingliaocardId;// 领料人Id
	private String lingliaoName;// 领料人
	private Integer lingliaoUserId;// 领料人Id
	private String wxselfCard;//
	private String ywmarkId;//业务件号
	private Integer procardId;//零件Id(页面传值)
	private String gongxuNum;//工序号(页面传值)
	private String gongxuName;//工序名(页面传值)
	
	private Integer wddId;//页面显示送货单Id
	private WaigouDeliveryDetail wdd;//页面显示送货单数据
	private String shplanNumber;//送货单号
	
	private String ids;  //id列表，页面传值
	private Integer listIndex;//列表下标值  （页面传值）
	
	//借领属性 
	private String lendNeckStatus;//借领属性:借/领
	private Float lendNeckCount;//可借数量(领的时候此值不存在)
	private String nectCanChangeStatus;//领的时候是否可以以旧换新状态：是/否
	private String suodingdanhao;//锁定单号
	
	private String proNumber;//项目编号(研发项目编号)
	
	private String nextProcessname;//下工序名称(页面显示)
	private Float dwyl;//单位用量(页面显示)
	private Double turnoverRate;//周转率(页面显示)
	private String demanddept;//需求部门
	
	public String getGoodsMore() {
		return goodsMore;
	}

	public String getGoodHouseName() {
		return goodHouseName;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public void setGoodHouseName(String goodHouseName) {
		this.goodHouseName = goodHouseName;
	}

	public void setGoodsMore(String goodsMore) {
		this.goodsMore = goodsMore;
	}

	public String getGoodsMore2() {
		return goodsMore2;
	}

	public void setGoodsMore2(String goodsMore2) {
		this.goodsMore2 = goodsMore2;
	}

	public String getGoodsProMarkId() {
		return goodsProMarkId;
	}

	public void setGoodsProMarkId(String goodsProMarkId) {
		this.goodsProMarkId = goodsProMarkId;
	}

	public String getGoodsArtsCard() {
		return goodsArtsCard;
	}

	public void setGoodsArtsCard(String goodsArtsCard) {
		this.goodsArtsCard = goodsArtsCard;
	}

	public String getGoodsCustomer() {
		return goodsCustomer;
	}

	public void setGoodsCustomer(String goodsCustomer) {
		this.goodsCustomer = goodsCustomer;
	}

	public Float getGoodsRealPrice() {
		return goodsRealPrice;
	}

	public void setGoodsRealPrice(Float goodsRealPrice) {
		this.goodsRealPrice = goodsRealPrice;
	}

	/** default constructor */
	public Goods() {
	}

	public String getGoodsCurQuantitySting() {
		return goodsCurQuantitySting;
	}

	public void setGoodsCurQuantitySting(String goodsCurQuantitySting) {
		this.goodsCurQuantitySting = goodsCurQuantitySting;
	}

	/** minimal constructor */
	public Goods(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsMarkId() {
		return goodsMarkId;
	}

	public void setGoodsMarkId(String goodsMarkId) {
		this.goodsMarkId = goodsMarkId;
	}

	public String getGoodsChangeTime() {
		return goodsChangeTime;
	}

	public void setGoodsChangeTime(String goodsChangeTime) {
		this.goodsChangeTime = goodsChangeTime;
		if(goodsDetailsTime==null){
			this.goodsDetailsTime = Util.getDateTime("yyyy-MM-dd HH:mm:ss");
		}
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsFullName() {
		return this.goodsFullName;
	}

	public void setGoodsFullName(String goodsFullName) {
		this.goodsFullName = goodsFullName;
	}

	public String getGoodsShortName() {
		return this.goodsShortName;
	}

	public void setGoodsShortName(String goodsShortName) {
		this.goodsShortName = goodsShortName;
	}

	public String getGoodsRemember() {
		return this.goodsRemember;
	}

	public void setGoodsRemember(String goodsRemember) {
		this.goodsRemember = goodsRemember;
	}

	public String getGoodsSet() {
		return this.goodsSet;
	}

	public void setGoodsSet(String goodsSet) {
		this.goodsSet = goodsSet;
	}

	public String getGoodsFormat() {
		return this.goodsFormat;
	}

	public void setGoodsFormat(String goodsFormat) {
		this.goodsFormat = goodsFormat;
	}

	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Float getGoodsCost() {
		return this.goodsCost;
	}

	public void setGoodsCost(Float goodsCost) {
		this.goodsCost = goodsCost;
	}

	public Float getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Float getGoodsMax() {
		return this.goodsMax;
	}

	public void setGoodsMax(Float goodsMax) {
		this.goodsMax = goodsMax;
	}

	public Float getGoodsMin() {
		return this.goodsMin;
	}

	public void setGoodsMin(Float goodsMin) {
		this.goodsMin = goodsMin;
	}

	public String getGoodsClass() {
		return this.goodsClass;
	}

	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	public String getGoodsUnit() {
		return this.goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsBarcode() {
		return this.goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public Float getGoodsBuyPrice() {
		return this.goodsBuyPrice;
	}

	public void setGoodsBuyPrice(Float goodsBuyPrice) {
		this.goodsBuyPrice = goodsBuyPrice;
	}

	public Float getGoodsSellPrice() {
		return this.goodsSellPrice;
	}

	public void setGoodsSellPrice(Float goodsSellPrice) {
		this.goodsSellPrice = goodsSellPrice;
	}

	public Float getGoodsMinSell() {
		return this.goodsMinSell;
	}

	public void setGoodsMinSell(Float goodsMinSell) {
		this.goodsMinSell = goodsMinSell;
	}

	public Float getGoodsMaxBuy() {
		return this.goodsMaxBuy;
	}

	public void setGoodsMaxBuy(Float goodsMaxBuy) {
		this.goodsMaxBuy = goodsMaxBuy;
	}

	public Float getGoodsVipPrice() {
		return this.goodsVipPrice;
	}

	public void setGoodsVipPrice(Float goodsVipPrice) {
		this.goodsVipPrice = goodsVipPrice;
	}

	public Float getGoodsWholesale1() {
		return this.goodsWholesale1;
	}

	public void setGoodsWholesale1(Float goodsWholesale1) {
		this.goodsWholesale1 = goodsWholesale1;
	}

	public Float getGoodsWholesale2() {
		return this.goodsWholesale2;
	}

	public void setGoodsWholesale2(Float goodsWholesale2) {
		this.goodsWholesale2 = goodsWholesale2;
	}

	public Float getGoodsWholesale3() {
		return this.goodsWholesale3;
	}

	public void setGoodsWholesale3(Float goodsWholesale3) {
		this.goodsWholesale3 = goodsWholesale3;
	}

	public String getGoodsPosition() {
		return goodsPosition;
	}

	public void setGoodsPosition(String goodsPosition) {
		this.goodsPosition = goodsPosition;
	}

	public String getGoodsLotId() {
		return goodsLotId;
	}

	public void setGoodsLotId(String goodsLotId) {
		this.goodsLotId = goodsLotId;
	}

	public String getGoodsSupplier() {
		return goodsSupplier;
	}

	public void setGoodsSupplier(String goodsSupplier) {
		this.goodsSupplier = goodsSupplier;
	}

	public String getGoodsMarkFormat() {
		return goodsMarkFormat;
	}

	public void setGoodsMarkFormat(String goodsMarkFormat) {
		this.goodsMarkFormat = goodsMarkFormat;
	}

	public Float getGoodsBeginQuantity() {
		return goodsBeginQuantity;
	}

	public void setGoodsBeginQuantity(Float goodsBeginQuantity) {
		this.goodsBeginQuantity = goodsBeginQuantity;
	}

	public Float getGoodsCurQuantity() {
		return goodsCurQuantity;
	}

	public void setGoodsCurQuantity(Float goodsCurQuantity) {
		this.goodsCurQuantity = goodsCurQuantity;
	}

	public Float getGoodsZhishu() {
		return goodsZhishu;
	}

	public void setGoodsZhishu(Float goodsZhishu) {
		this.goodsZhishu = goodsZhishu;
	}

	public Float getGoodsAvgzhishu() {
		return goodsAvgzhishu;
	}

	public void setGoodsAvgzhishu(Float goodsAvgzhishu) {
		this.goodsAvgzhishu = goodsAvgzhishu;
	}

	public String getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(String goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public Boolean getBout() {
		return bout;
	}

	public void setBout(Boolean bout) {
		this.bout = bout;
	}

	public Boolean getBedit() {
		return bedit;
	}

	public void setBedit(Boolean bedit) {
		this.bedit = bedit;
	}

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}

	public float getCkCount() {
		return ckCount;
	}

	public void setCkCount(float ckCount) {
		this.ckCount = ckCount;
	}

	public Float getQlCountZh() {
		return qlCountZh;
	}

	public void setQlCountZh(Float qlCountZh) {
		this.qlCountZh = qlCountZh;
	}

	public String getYlMarkId() {
		return ylMarkId;
	}

	public void setYlMarkId(String ylMarkId) {
		this.ylMarkId = ylMarkId;
	}

	public String getYlSelfCard() {
		return ylSelfCard;
	}

	public void setYlSelfCard(String ylSelfCard) {
		this.ylSelfCard = ylSelfCard;
	}

	public float getYlshifa() {
		return ylshifa;
	}

	public void setYlshifa(float ylshifa) {
		this.ylshifa = ylshifa;
	}

	public Float getYlApplyCount() {
		return ylApplyCount;
	}

	public void setYlApplyCount(Float ylApplyCount) {
		this.ylApplyCount = ylApplyCount;
	}

	public String getBanBenNumber() {
		return banBenNumber;
	}

	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}

	public String getFcStatus() {
		return fcStatus;
	}

	public void setFcStatus(String fcStatus) {
		this.fcStatus = fcStatus;
	}

	public String getFcApplyStatus() {
		return fcApplyStatus;
	}

	public void setFcApplyStatus(String fcApplyStatus) {
		this.fcApplyStatus = fcApplyStatus;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getQlUnit() {
		return qlUnit;
	}

	public void setQlUnit(String qlUnit) {
		this.qlUnit = qlUnit;
	}

	public String getGoodsStoreZHUnit() {
		return goodsStoreZHUnit;
	}

	public void setGoodsStoreZHUnit(String goodsStoreZHUnit) {
		this.goodsStoreZHUnit = goodsStoreZHUnit;
	}

	public String getGoodsnexttime() {
		return goodsnexttime;
	}

	public void setGoodsnexttime(String goodsnexttime) {
		this.goodsnexttime = goodsnexttime;
	}

	public Float getGoodsround() {
		return goodsround;
	}

	public void setGoodsround(Float goodsround) {
		this.goodsround = goodsround;
	}

	public String getGoodslasttime() {
		return goodslasttime;
	}

	public void setGoodslasttime(String goodslasttime) {
		this.goodslasttime = goodslasttime;
	}

	public String getNeiorderId() {
		return neiorderId;
	}

	public void setNeiorderId(String neiorderId) {
		this.neiorderId = neiorderId;
	}

	public String getWaiorderId() {
		return waiorderId;
	}

	public void setWaiorderId(String waiorderId) {
		this.waiorderId = waiorderId;
	}

	public Integer getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(Integer orderId) {
		order_Id = orderId;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getInputSource() {
		return inputSource;
	}

	public void setInputSource(String inputSource) {
		this.inputSource = inputSource;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public Float getKzCount() {
		return kzCount;
	}

	public void setKzCount(Float kzCount) {
		this.kzCount = kzCount;
	}

	public Boolean getIsEnough() {
		return isEnough;
	}

	public void setIsEnough(Boolean isEnough) {
		this.isEnough = isEnough;
	}

	public Float getTqlCount() {
		return tqlCount;
	}

	public void setTqlCount(Float tqlCount) {
		this.tqlCount = tqlCount;
	}

	public Float getHqlCount() {
		return hqlCount;
	}

	public void setHqlCount(Float hqlCount) {
		this.hqlCount = hqlCount;
	}

	public Float getQlCount() {
		return qlCount;
	}

	public void setQlCount(Float qlCount) {
		this.qlCount = qlCount;
	}

	public Boolean getIsChangeSf() {
		return isChangeSf;
	}

	public void setIsChangeSf(Boolean isChangeSf) {
		this.isChangeSf = isChangeSf;
	}

	public Integer getKuweiId() {
		return kuweiId;
	}

	public void setKuweiId(Integer kuweiId) {
		this.kuweiId = kuweiId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getYllock() {
		return yllock;
	}

	public void setYllock(String yllock) {
		this.yllock = yllock;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getLlGysId() {
		return llGysId;
	}

	public void setLlGysId(Integer llGysId) {
		this.llGysId = llGysId;
	}

	public Integer getProcessNo() {
		return processNo;
	}

	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}

	public String getDtcFlag() {
		return dtcFlag;
	}

	public void setDtcFlag(String dtcFlag) {
		this.dtcFlag = dtcFlag;
	}

	public Float getQuanzi1() {
		return quanzi1;
	}

	public void setQuanzi1(Float quanzi1) {
		this.quanzi1 = quanzi1;
	}

	public Float getQuanzi2() {
		return quanzi2;
	}

	public void setQuanzi2(Float quanzi2) {
		this.quanzi2 = quanzi2;
	}

	public Float getXqCount() {
		return xqCount;
	}

	public void setXqCount(Float xqCount) {
		this.xqCount = xqCount;
	}

	public String getLingliaocardId() {
		return lingliaocardId;
	}

	public void setLingliaocardId(String lingliaocardId) {
		this.lingliaocardId = lingliaocardId;
	}

	public String getLingliaoName() {
		return lingliaoName;
	}

	public void setLingliaoName(String lingliaoName) {
		this.lingliaoName = lingliaoName;
	}

	public Integer getLingliaoUserId() {
		return lingliaoUserId;
	}

	public void setLingliaoUserId(Integer lingliaoUserId) {
		this.lingliaoUserId = lingliaoUserId;
	}

	public String getWxselfCard() {
		return wxselfCard;
	}

	public void setWxselfCard(String wxselfCard) {
		this.wxselfCard = wxselfCard;
	}

	public Float getKfCount() {
		return kfCount;
	}

	public void setKfCount(Float kfCount) {
		this.kfCount = kfCount;
	}

	public String getYwmarkId() {
		return ywmarkId;
	}

	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}

	public Float getBzApplyCount() {
		return bzApplyCount;
	}

	public void setBzApplyCount(Float bzApplyCount) {
		this.bzApplyCount = bzApplyCount;
	}

	public Float getBzyiCount() {
		return bzyiCount;
	}

	public void setBzyiCount(Float bzyiCount) {
		this.bzyiCount = bzyiCount;
	}

	public Float getBztjCount() {
		return bztjCount;
	}

	public void setBztjCount(Float bztjCount) {
		this.bztjCount = bztjCount;
	}


	public void setShApplyCount(Float shApplyCount) {
		this.shApplyCount = shApplyCount;
	}

	public Float getShqrCount() {
		return shqrCount;
	}

	public void setShqrCount(Float shqrCount) {
		this.shqrCount = shqrCount;
	}

	public Float getShthCount() {
		return shthCount;
	}

	public void setShthCount(Float shthCount) {
		this.shthCount = shthCount;
	}

	public Float getSingleCount() {
		return singleCount;
	}

	public void setSingleCount(Float singleCount) {
		this.singleCount = singleCount;
	}

	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

	public String getEpStatus() {
		return epStatus;
	}

	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}

	public String getGongxuNum() {
		return gongxuNum;
	}

	public void setGongxuNum(String gongxuNum) {
		this.gongxuNum = gongxuNum;
	}

	public String getGongxuName() {
		return gongxuName;
	}

	public void setGongxuName(String gongxuName) {
		this.gongxuName = gongxuName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getProcardId() {
		return procardId;
	}

	public Float getFlushCount() {
		return flushCount;
	}

	public void setFlushCount(Float flushCount) {
		this.flushCount = flushCount;
	}

	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}

	public Float getShApplyCount() {
		return shApplyCount;
	}


	public void setLendNeckStatus(String lendNeckStatus) {
		this.lendNeckStatus = lendNeckStatus;
	}

	public String getLendNeckStatus() {
		return lendNeckStatus;
	}

	public void setLendNeckCount(Float lendNeckCount) {
		this.lendNeckCount = lendNeckCount;
	}

	public Float getLendNeckCount() {
		return lendNeckCount;
	}



	public Float getFlushCount2() {
		return flushCount2;
	}

//	public void setFlushCount2(Float flushCount2) {
//		this.flushCount2 = flushCount2;
//	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public void setNectCanChangeStatus(String nectCanChangeStatus) {
		this.nectCanChangeStatus = nectCanChangeStatus;
	}

	public String getNectCanChangeStatus() {
		return nectCanChangeStatus;
	}

	

	public WaigouDeliveryDetail getWdd() {
		return wdd;
	}

	public void setWdd(WaigouDeliveryDetail wdd) {
		this.wdd = wdd;
	}

	public Integer getWddId() {
		return wddId;
	}

	public void setWddId(Integer wddId) {
		this.wddId = wddId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSuodingdanhao() {
		return suodingdanhao;
	}

	public void setSuodingdanhao(String suodingdanhao) {
		this.suodingdanhao = suodingdanhao;
	}

	public Float getZyqjCount() {
		return zyqjCount;
	}

	public void setZyqjCount(Float zyqjCount) {
		this.zyqjCount = zyqjCount;
	}

	public Float getFlushCount3() {
		return flushCount3;
	}

	public void setFlushCount3(Float flushCount3) {
		this.flushCount3 = flushCount3;
	}

	public void setFlushCount2(Float flushCount2) {
		this.flushCount2 = flushCount2;
	}

	public Float getGoodshsPrice() {
		return goodshsPrice;
	}

	public void setGoodshsPrice(Float goodshsPrice) {
		this.goodshsPrice = goodshsPrice;
	}

	public Float getGoodsBuyBhsPrice() {
		return goodsBuyBhsPrice;
	}

	public void setGoodsBuyBhsPrice(Float goodsBuyBhsPrice) {
		this.goodsBuyBhsPrice = goodsBuyBhsPrice;
	}

	public Float getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Float taxprice) {
		this.taxprice = taxprice;
	}

	public String getShplanNumber() {
		return shplanNumber;
	}

	public void setShplanNumber(String shplanNumber) {
		this.shplanNumber = shplanNumber;
	}

	public Integer getListIndex() {
		return listIndex;
	}

	public void setListIndex(Integer listIndex) {
		this.listIndex = listIndex;
	}

	public Boolean getIsdel() {
		return isdel;
	}

	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
	}

	public String getNextProcessname() {
		return nextProcessname;
	}

	public void setNextProcessname(String nextProcessname) {
		this.nextProcessname = nextProcessname;
	}

	public Float getDwyl() {
		return dwyl;
	}

	public void setDwyl(Float dwyl) {
		this.dwyl = dwyl;
	}

	public Double getTurnoverRate() {
		return turnoverRate;
	}

	public void setTurnoverRate(Double turnoverRate) {
		this.turnoverRate = turnoverRate;
	}

	public String getGoodsDetailsTime() {
		return goodsDetailsTime;
	}

	public void setGoodsDetailsTime(String goodsDetailsTime) {
		this.goodsDetailsTime = goodsDetailsTime;
	}

	public String getDemanddept() {
		return demanddept;
	}

	public void setDemanddept(String demanddept) {
		this.demanddept = demanddept;
	}

	
	
}