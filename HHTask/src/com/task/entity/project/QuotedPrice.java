package com.task.entity.project;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * 报价系统表
 * 
 *@表名 ta_pro_QuotedPrice
 * 
 * @author 刘培
 * 
 */
public class QuotedPrice implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;// 主键
	private String quotedNumber;// 询价单号
	private Integer procardLifeCycle;// 产品生命周期
	private Integer quantityLifeCycle;// 数量（不用。。。）
	@FieldMeta(name = "件号", order = 1)
	private String markId;// 件号
	private String ywmrkId;//业务件号
	@FieldMeta(name = "名称", order = 2)
	private String laiyuan;//来源；
	private String proName;// 名称
	private String tuhao;//图号
	private String banBenNumber;//版本号
	private String unit;// 单位
	private String carStyle;// 车型
	private String xingbie;// 型别
	private Float filnalCount;// 数量(实际数量)
	private Float corrCount;// 权值
	private String procardStyle;// 卡片类型(总成，组合，外购，自制)
	private String productStyle;// 产品类型(批产、试制)
	private String bzStatus;// 工艺编制状态(初始,待编制,已编制,已校对,已审核,已批准)
	@FieldMeta(name = "当前阶段", order = 3)
	private String status;// 状态(初始、BOM录入、外购外委评审中、评审完成、集合报价、项目启动中、项目跟踪、项目编制‘核算完成,
	//'首件阶段','试制阶段','批产阶段')(all、 bom、cl、sb、gz、fl、rg、wgww、cw)
	private String remark;// 备注
	private String needProcess;//半成品(yes,no,默认no)
	
	
	private Integer historyId;//历史件号Id；导入模板时使用不放人数据库

	// 树形结构附属属性
	private Integer rootId;// 第一层父类Id
	private Integer fatherId;// 上层父类Id
	private Integer belongLayer;// 当前层
	private Float cardXiaoHao;//以1为总成数量对应件号所需数量(临时使用)
	private String loadMarkId;// 初始成品件号
	private String rootMarkId;// 对应成品件号
	private Float xuhao;//显示时用来排序
	// 原材料属性
	private String trademark;// 牌号
	private String specification;// 规格
	private Float lastMonthPrice;// 上月招标价格
	private Float lastMonthPricesj;// 上月招标价格首件
	private Float lastMonthPricesz;// 上月招标价格试制
	private String caizhi;//材质（材料属性原材料和外购件使用）
	private String clType;//材料类型
	private String biaochu;//表处
	private Float thisLength;//长
	private Float thisWidth;//宽
	private Float thisHight;//高
	
	private String showSize;//尺寸
	
	private Float materialXh;// 材料消耗
	private Float materialXhsj;// 材料消耗首件
	private Float materialXhsz;// 材料消耗试制
	
	private Float materialPrice;// 材料费
	private Float materialPricesj;// 材料费首件
	private Float materialPricesz;// 材料费试制
	private String yucailiaostatus;// 原材料是否外购

	private String yuanUnit;// 原材料/外购件单位
	private Float quanzi1;// 权值(自制件:原材料)
	private Float quanzi2;// 权值(自制件:原材料)
	
	//导入新添字段
	private Float bili;//比重
	private String yuanName;//原材料名称
	private String ytuhao;//原材料图号
	private Float xiaohaoCount;//原材料消耗数量
	private Integer banci;// 版次
	private String kgliao;//供料来源（TKVAL 指定，cs 客供，TK 自购）
	private String clClass;// 材料类型(管料、卷料、外购件)
	private String wgType;//外购件类型
	private String banbenStatus;//版本状态(默认,历史（空则表示默认）,试用)

	// 外购价格
	private Float waigouPrice;// 外购价格
	private Float waigouPricesz;// 外购价格
	private Float waigouPricesj;// 外购价格

	// 财务录入
	private Float shuiFei;// 水费
	private Float dianFei;// 电费
	private Float fengFei;// 风费
	private Float qitiFei;// 气体费
	private Float shuiFeisz;// 水费
	private Float dianFeisz;// 电费
	private Float fengFeisz;// 风费
	private Float qitiFeisz;// 气体费
	private Float shuiFeisj;// 水费
	private Float dianFeisj;// 电费
	private Float fengFeisj;// 风费
	private Float qitiFeisj;// 气体费

	// 物流费用
	private Float baozhuangFei;// 包装费
	private Float yunshuFei;// 运输费
	private Float baozhuangFeisz;// 包装费
	private Float yunshuFeisz;// 运输费
	private Float baozhuangFeisj;// 包装费
	private Float yunshuFeisj;// 运输费

	// 工装费
	private Float gongzhuangFei;

	// 财务、管理费用
	private Float caiwuFei;// 财务费用
	private Float manageFei;// 管理费用
	//人工费。
	private Float rengongFei;//人工费
	
	private Float szCount;//试制预生产数量（不含首件）
	private Float sjrefy;//首件预算费用
	private Float szrefy;//试制预算费用（不含首件）
	private Double realAllfy;//实际花费费用对应所有费用（所有费用为预测）
	private Double allFeiyong;//所有费用（包括件号上自带的费用和额外申报的非生产费用（这部分没有记载件号上,在quotedpriceCost表上））
	private Double touziFeiyong;//投资成功的费用
	private Double cheziFeiyong;//撤资了的费用
	private Double yingkui;//盈亏
	private Float fhmoney;//已分红金额
	private Float jfcount;//交付数量
	private String hasTz;//个人是否投资（页面显示）
	//放标
	private String fbSatuts;//放标状态（未放标,放标,关闭）
	private Float fbBili;//放标比例
	private Float tfzonge;//投放总额（开放出去的最大可投资金额）
	private Float dfMoney;//单份金额
	private String gzcb;//工装是否参与成本计算（是,否）
	private String zuzhang;//组长
	
	// 其他
	private String procardTime;// 添加时间
	private String zhikaren;// 添加人
	private String barcode;// 条码

	// 项目关联
	private Integer proId;// 项目id

	private String fileName;// 附件
	
	private Integer epId;
	private String dateStatus;//数据类型(除历史，删除外默认为正常)
	
	private String isbaomi;

	// 树形结构对应关系
	private QuotedPrice quotedPrice;// 父流水卡片
	private Set<QuotedPrice> quotedPriceSet = new HashSet<QuotedPrice>();// 子流水卡片

	private Set<QuotedProcessInfor> qpinfor;// 对应工序信息(一对多)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuotedNumber() {
		return quotedNumber;
	}

	public void setQuotedNumber(String quotedNumber) {
		this.quotedNumber = quotedNumber;
	}

	public Integer getProcardLifeCycle() {
		return procardLifeCycle;
	}

	public void setProcardLifeCycle(Integer procardLifeCycle) {
		this.procardLifeCycle = procardLifeCycle;
	}

	public Integer getQuantityLifeCycle() {
		return quantityLifeCycle;
	}

	public void setQuantityLifeCycle(Integer quantityLifeCycle) {
		this.quantityLifeCycle = quantityLifeCycle;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Float getFilnalCount() {
		return filnalCount;
	}

	public void setFilnalCount(Float filnalCount) {
		this.filnalCount = filnalCount;
	}

	public String getProcardStyle() {
		return procardStyle;
	}

	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getBelongLayer() {
		return belongLayer;
	}

	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Float getLastMonthPrice() {
		return lastMonthPrice;
	}

	public void setLastMonthPrice(Float lastMonthPrice) {
		this.lastMonthPrice = lastMonthPrice;
	}

	public Float getMaterialXh() {
		return materialXh;
	}

	public void setMaterialXh(Float materialXh) {
		this.materialXh = materialXh;
	}

	public Float getMaterialPrice() {
		return materialPrice;
	}

	public void setMaterialPrice(Float materialPrice) {
		this.materialPrice = materialPrice;
	}

	public String getProcardTime() {
		return procardTime;
	}

	public void setProcardTime(String procardTime) {
		this.procardTime = procardTime;
	}

	public String getZhikaren() {
		return zhikaren;
	}

	public void setZhikaren(String zhikaren) {
		this.zhikaren = zhikaren;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	@JSONField(serialize = false)
	public Set<QuotedProcessInfor> getQpinfor() {
		return qpinfor;
	}

	public void setQpinfor(Set<QuotedProcessInfor> qpinfor) {
		this.qpinfor = qpinfor;
	}

	@JSONField(serialize = false)
	public QuotedPrice getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(QuotedPrice quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	@JSONField(serialize = false)
	public Set<QuotedPrice> getQuotedPriceSet() {
		return quotedPriceSet;
	}

	public void setQuotedPriceSet(Set<QuotedPrice> quotedPriceSet) {
		this.quotedPriceSet = quotedPriceSet;
	}

	public Float getCorrCount() {
		return corrCount;
	}

	public void setCorrCount(Float corrCount) {
		this.corrCount = corrCount;
	}

	public String getYuanUnit() {
		return yuanUnit;
	}

	public void setYuanUnit(String yuanUnit) {
		this.yuanUnit = yuanUnit;
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

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getXingbie() {
		return xingbie;
	}

	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}

	public Float getWaigouPrice() {
		return waigouPrice;
	}

	public void setWaigouPrice(Float waigouPrice) {
		this.waigouPrice = waigouPrice;
	}

	public Float getShuiFei() {
		return shuiFei;
	}

	public void setShuiFei(Float shuiFei) {
		this.shuiFei = shuiFei;
	}

	public Float getDianFei() {
		return dianFei;
	}

	public void setDianFei(Float dianFei) {
		this.dianFei = dianFei;
	}

	public Float getFengFei() {
		return fengFei;
	}

	public void setFengFei(Float fengFei) {
		this.fengFei = fengFei;
	}

	public Float getQitiFei() {
		return qitiFei;
	}

	public void setQitiFei(Float qitiFei) {
		this.qitiFei = qitiFei;
	}

	public Float getBaozhuangFei() {
		return baozhuangFei;
	}

	public void setBaozhuangFei(Float baozhuangFei) {
		this.baozhuangFei = baozhuangFei;
	}

	public Float getYunshuFei() {
		return yunshuFei;
	}

	public void setYunshuFei(Float yunshuFei) {
		this.yunshuFei = yunshuFei;
	}

	public Float getGongzhuangFei() {
		return gongzhuangFei;
	}

	public void setGongzhuangFei(Float gongzhuangFei) {
		this.gongzhuangFei = gongzhuangFei;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Float getCaiwuFei() {
		return caiwuFei;
	}

	public void setCaiwuFei(Float caiwuFei) {
		this.caiwuFei = caiwuFei;
	}

	public Float getManageFei() {
		return manageFei;
	}

	public void setManageFei(Float manageFei) {
		this.manageFei = manageFei;
	}

	public String getYucailiaostatus() {
		return yucailiaostatus;
	}

	public void setYucailiaostatus(String yucailiaostatus) {
		this.yucailiaostatus = yucailiaostatus;
	}

	public String getNeedProcess() {
		return needProcess;
	}

	public void setNeedProcess(String needProcess) {
		this.needProcess = needProcess;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Double getAllFeiyong() {
		return allFeiyong;
	}

	public void setAllFeiyong(Double allFeiyong) {
		this.allFeiyong = allFeiyong;
	}

	public Double getTouziFeiyong() {
		return touziFeiyong;
	}

	public void setTouziFeiyong(Double touziFeiyong) {
		this.touziFeiyong = touziFeiyong;
	}

	public Double getCheziFeiyong() {
		return cheziFeiyong;
	}

	public void setCheziFeiyong(Double cheziFeiyong) {
		this.cheziFeiyong = cheziFeiyong;
	}

	public Double getYingkui() {
		return yingkui;
	}

	public void setYingkui(Double yingkui) {
		this.yingkui = yingkui;
	}

	public Float getJfcount() {
		return jfcount;
	}

	public void setJfcount(Float jfcount) {
		this.jfcount = jfcount;
	}

	public Float getFhmoney() {
		return fhmoney;
	}

	public void setFhmoney(Float fhmoney) {
		this.fhmoney = fhmoney;
	}

	public String getFbSatuts() {
		return fbSatuts;
	}

	public void setFbSatuts(String fbSatuts) {
		this.fbSatuts = fbSatuts;
	}

	public Float getFbBili() {
		return fbBili;
	}

	public void setFbBili(Float fbBili) {
		this.fbBili = fbBili;
	}

	public Float getDfMoney() {
		return dfMoney;
	}

	public void setDfMoney(Float dfMoney) {
		this.dfMoney = dfMoney;
	}

	public Double getRealAllfy() {
		return realAllfy;
	}

	public void setRealAllfy(Double realAllfy) {
		this.realAllfy = realAllfy;
	}

	public Float getLastMonthPricesj() {
		return lastMonthPricesj;
	}

	public void setLastMonthPricesj(Float lastMonthPricesj) {
		this.lastMonthPricesj = lastMonthPricesj;
	}

	public Float getLastMonthPricesz() {
		return lastMonthPricesz;
	}

	public void setLastMonthPricesz(Float lastMonthPricesz) {
		this.lastMonthPricesz = lastMonthPricesz;
	}

	public Float getMaterialXhsj() {
		return materialXhsj;
	}

	public void setMaterialXhsj(Float materialXhsj) {
		this.materialXhsj = materialXhsj;
	}

	public Float getMaterialXhsz() {
		return materialXhsz;
	}

	public void setMaterialXhsz(Float materialXhsz) {
		this.materialXhsz = materialXhsz;
	}

	public Float getMaterialPricesj() {
		return materialPricesj;
	}

	public void setMaterialPricesj(Float materialPricesj) {
		this.materialPricesj = materialPricesj;
	}

	public Float getMaterialPricesz() {
		return materialPricesz;
	}

	public void setMaterialPricesz(Float materialPricesz) {
		this.materialPricesz = materialPricesz;
	}

	public Float getWaigouPricesz() {
		return waigouPricesz;
	}

	public void setWaigouPricesz(Float waigouPricesz) {
		this.waigouPricesz = waigouPricesz;
	}

	public Float getWaigouPricesj() {
		return waigouPricesj;
	}

	public void setWaigouPricesj(Float waigouPricesj) {
		this.waigouPricesj = waigouPricesj;
	}

	public Float getShuiFeisz() {
		return shuiFeisz;
	}

	public void setShuiFeisz(Float shuiFeisz) {
		this.shuiFeisz = shuiFeisz;
	}

	public Float getDianFeisz() {
		return dianFeisz;
	}

	public void setDianFeisz(Float dianFeisz) {
		this.dianFeisz = dianFeisz;
	}

	public Float getFengFeisz() {
		return fengFeisz;
	}

	public void setFengFeisz(Float fengFeisz) {
		this.fengFeisz = fengFeisz;
	}

	public Float getQitiFeisz() {
		return qitiFeisz;
	}

	public void setQitiFeisz(Float qitiFeisz) {
		this.qitiFeisz = qitiFeisz;
	}

	public Float getShuiFeisj() {
		return shuiFeisj;
	}

	public void setShuiFeisj(Float shuiFeisj) {
		this.shuiFeisj = shuiFeisj;
	}

	public Float getDianFeisj() {
		return dianFeisj;
	}

	public void setDianFeisj(Float dianFeisj) {
		this.dianFeisj = dianFeisj;
	}

	public Float getFengFeisj() {
		return fengFeisj;
	}

	public void setFengFeisj(Float fengFeisj) {
		this.fengFeisj = fengFeisj;
	}

	public Float getQitiFeisj() {
		return qitiFeisj;
	}

	public void setQitiFeisj(Float qitiFeisj) {
		this.qitiFeisj = qitiFeisj;
	}

	public Float getBaozhuangFeisz() {
		return baozhuangFeisz;
	}

	public void setBaozhuangFeisz(Float baozhuangFeisz) {
		this.baozhuangFeisz = baozhuangFeisz;
	}

	public Float getYunshuFeisz() {
		return yunshuFeisz;
	}

	public void setYunshuFeisz(Float yunshuFeisz) {
		this.yunshuFeisz = yunshuFeisz;
	}

	public Float getBaozhuangFeisj() {
		return baozhuangFeisj;
	}

	public void setBaozhuangFeisj(Float baozhuangFeisj) {
		this.baozhuangFeisj = baozhuangFeisj;
	}

	public Float getYunshuFeisj() {
		return yunshuFeisj;
	}

	public void setYunshuFeisj(Float yunshuFeisj) {
		this.yunshuFeisj = yunshuFeisj;
	}

	public String getGzcb() {
		return gzcb;
	}

	public void setGzcb(String gzcb) {
		this.gzcb = gzcb;
	}

	public Float getSzrefy() {
		return szrefy;
	}

	public void setSzrefy(Float szrefy) {
		this.szrefy = szrefy;
	}

	public Float getSzCount() {
		return szCount;
	}

	public void setSzCount(Float szCount) {
		this.szCount = szCount;
	}

	public Float getSjrefy() {
		return sjrefy;
	}

	public void setSjrefy(Float sjrefy) {
		this.sjrefy = sjrefy;
	}

	public Float getTfzonge() {
		return tfzonge;
	}

	public void setTfzonge(Float tfzonge) {
		this.tfzonge = tfzonge;
	}

	public String getZuzhang() {
		return zuzhang;
	}

	public void setZuzhang(String zuzhang) {
		this.zuzhang = zuzhang;
	} 

	public String getHasTz() {
		return hasTz;
	}

	public void setHasTz(String hasTz) {
		this.hasTz = hasTz;
	}

	public String getYwmrkId() {
		return ywmrkId;
	}

	public void setYwmrkId(String ywmrkId) {
		this.ywmrkId = ywmrkId;
	}

	public String getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}

	public String getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}

	public String getBiaochu() {
		return biaochu;
	}

	public void setBiaochu(String biaochu) {
		this.biaochu = biaochu;
	}

	public Float getThisLength() {
		return thisLength;
	}

	public void setThisLength(Float thisLength) {
		this.thisLength = thisLength;
	}

	public Float getThisWidth() {
		return thisWidth;
	}

	public void setThisWidth(Float thisWidth) {
		this.thisWidth = thisWidth;
	}

	public Float getThisHight() {
		return thisHight;
	}

	public void setThisHight(Float thisHight) {
		this.thisHight = thisHight;
	}

	public String getBanBenNumber() {
		return banBenNumber;
	}

	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
	}

	public String getClType() {
		return clType;
	}

	public void setClType(String clType) {
		this.clType = clType;
	}

	public String getBzStatus() {
		return bzStatus;
	}

	public void setBzStatus(String bzStatus) {
		this.bzStatus = bzStatus;
	}

	public Float getCardXiaoHao() {
		return cardXiaoHao;
	}

	public void setCardXiaoHao(Float cardXiaoHao) {
		this.cardXiaoHao = cardXiaoHao;
	}

	public String getLoadMarkId() {
		return loadMarkId;
	}

	public void setLoadMarkId(String loadMarkId) {
		this.loadMarkId = loadMarkId;
	}

	public Float getXuhao() {
		return xuhao;
	}

	public void setXuhao(Float xuhao) {
		this.xuhao = xuhao;
	}

	public String getRootMarkId() {
		return rootMarkId;
	}

	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public Float getBili() {
		return bili;
	}

	public void setBili(Float bili) {
		this.bili = bili;
	}

	public String getYuanName() {
		return yuanName;
	}

	public void setYuanName(String yuanName) {
		this.yuanName = yuanName;
	}

	public String getYtuhao() {
		return ytuhao;
	}

	public void setYtuhao(String ytuhao) {
		this.ytuhao = ytuhao;
	}

	public Float getXiaohaoCount() {
		return xiaohaoCount;
	}

	public void setXiaohaoCount(Float xiaohaoCount) {
		this.xiaohaoCount = xiaohaoCount;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getClClass() {
		return clClass;
	}

	public void setClClass(String clClass) {
		this.clClass = clClass;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public String getBanbenStatus() {
		return banbenStatus;
	}

	public void setBanbenStatus(String banbenStatus) {
		this.banbenStatus = banbenStatus;
	}

	public String getDateStatus() {
		return dateStatus;
	}

	public void setDateStatus(String dateStatus) {
		this.dateStatus = dateStatus;
	}

	public String getIsbaomi() {
		return isbaomi;
	}

	public void setIsbaomi(String isbaomi) {
		this.isbaomi = isbaomi;
	}

	public Float getRengongFei() {
		return rengongFei;
	}

	public void setRengongFei(Float rengongFei) {
		this.rengongFei = rengongFei;
	}

}
