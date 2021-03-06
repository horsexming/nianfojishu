package com.task.entity.sop;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Goods;
import com.task.entity.Sell;
import com.task.util.FieldMeta;

/**
 * 流水卡片表（ta_sop_w_procard）
 * 
 * @author 刘培
 * 
 */
/**
 * @author liupei
 * 
 */
public class Procard implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	@FieldMeta(name = "件号")
	private String markId;// 件号
	private String ywMarkId;// 业务件号（总成对外使用）
	private String proName;// 名称
	@FieldMeta(name = "生产数量")
	private Float filnalCount;// 数量(实际需要生产数量)
	private Float countDao;// 假的？
	private Integer sunhaoType;// 损耗值类型(0为百分比类型 ；1为固定值类型)
	private Float sunhao;// 零件损耗(采购数量*(1+sunhao))
	private Float maxCount;// 数量(流水卡片最大数量)
	private Float corrCount;// 对应数量(权值自制件对上层2:1)
	private Float corrCountDao;// 对应数量(权值自制件对上层2:1)(导出使用 不保存)
	private String unit;// 单位
	private String carStyle;// 型别
	private String procardStyle;// 卡片类型(总成，外购，自制)
	private String productStyle;// 产品类型(试制，批产)
	private String remark;// 备注
	private String jihuoType;// 激活类型(层次激活(cc)、自制件激活(zzj))
	private String danjiaojian;// 是、否单交件
	private String lingliaostatus;// 是否领料（是、否）
	private String cgStatus;// 是否采购（是、否）
	private String needProcess;// （外购件）是否需要工序(yes,no)
	private Float safeCount;// 安全库存
	private Float lastCount;// 最低存量
	private String lingliaoType; // 领料方式（完全到齐 all，部分到齐 part,只在组合和总成上用默认为完全到齐）
	private String banBenNumber;// 版本号
	private Integer banci;// 版次
	private String lingliaoDetail;// 领料详情（批次:数量 用,隔开）
	private String tuhao;// 图号
	private String zzjihuo;// 自制件激活模
	private String sbStatus;// 设变删除（null 或者删除）
	private String hasChange;// 是否通过设变改变（是否，默认否）
	private Integer thProcardId;// 记录替换之前的零件Id

	// 树形结构附属属性
	private Integer rootId;// 第一层父类Id
	private Integer fatherId;// 上层父类Id
	private Integer belongLayer;// 当前层
	// 关系维护
	private Integer planOrderId;// 内部计划单id
	private Integer planOrderDetailId;// 内部计划单明细id（一对一）
	private String planOrderNum;// 内部计划单号
	private String orderNumber;// 订单编号
	private String orderId;// 订单id
	private String jioafuDate;// 交付日期
	private Integer procardTemplateId;// BOM模板对应ID

	private String wgType;// 物料类别
	private String specification;// 规格
	private Float bili;// 比重
	private Float thisLength;// 长
	private Float thisWidth;// 宽
	private Float thisHight;// 高
	private String calculateType;// 计算方式
	private Float quanzi1;// 权值(自制件:原材料)
	private Float quanzi2;// 权值(自制件:原材料)
	private String procardTime;// 制卡时间
	@FieldMeta(name = "批次")
	private String selfCard;// 本卡片号(批次号)
	private Integer zhikarenId;// 制卡人Id
	private String zhikaren;// 制卡人
	private String status;// 状态(初始、已发卡、已发料、领工序、完成、待入库、入库、取消)
	private String oldStatus;// 暂停和取消前的状态
	private String sduser;//锁定人

	// 补料使用
	private Integer oldProcardId;

	/****
	 * 工序生产相关
	 */
	private String proTemStatus;// 状态(单独/并行)
	private Integer parallelId;// 并行id
	private String jihuoStatua;// 激活状态(激活、未激活、null)
	private Float klNumber;// 可领数量
	private Float ylNumber;// 已领数量
	private Float tjNumber;// 提交数量
	private Float hascount;// 剩余数量
	private Float minNumber;// 最小数量(根据比例换算，上层可领数量)
	private Float rukuCount;// 入库数量(申请入库数量和已入库数量的结合)
	private Integer hgNumber;//合格数量(总成终检合格数量)
	private Float hasRuku;// 已入库数量
	private Float hasPlan;// 已排产数量
	private Float xuhao;// 显示时用来排序
	private Float wwblCount;// 被包公包料关联的数量
	private Float wwblreceiveCount;// 外委包料接受数量
	private String caizhi;// 材质（标记生产件是用什么材料做的）
	/***
	 * 精益生产相关
	 */
	private Float singleDuration;// 单班时长(工作时长)
	private Float capacity;// 产能(单班时长)
	private Float capacitySurplus;// 产能盈余
	private Float capacityRatio;// 产能比
	private Float deliveryDuration;// 延误时长
	private Float deliveryRatio;// 配送比
	private Integer deliveryPeriod;// 配送周期(X天/次)
	private Float deliveryAmount;// 送货量
	private Float proSingleDuration;// 总成单班生产时长
	private Float allJiepai;// 总节拍

	private Float nowAllJiepai;// 实际生产节拍(S)
	private Float nowAllNenghao;// 实际生产能耗(kw/h)

	private String jihuoDate;// 指定激活时间
	private String nowJihuoDate;// 实际激活时间
	private String wgjihuoTime;// 外购件激活待采购时间
	private String needFinalDate;// 系统指定完成时间
	private String nowFinalDate;// 实际完成时间
	private String mrpjihuoDate;// MRP激活时间

	private String lingliaoren;// 领料人临时存储
	private String rootMarkId;// 总成件号
	private String rootSelfCard;// 总成批次

	/*** 奖金分配金额 ****/
	private Float sellCount;// 出库数量
	private Float onePeice;// 分配单价
	private Float allMonty;// 分配总额

	/****** 物料(外购件)跟踪相关 **************/

	private String wlqrtime;// 物料确认时间
	private String wlstatus;// 物料状态 [总成流程控制(待定、待确认、待采购、采购中)]
	// (待定、待采购、待确认、生产中、送货中、检验中、入库、出库)
	private String wlWeizhiDt;// (已屏蔽)
								// 物料位置动态(供应商地址；运输中(xxx-yyy)；进门；检验区(xxx)；库位(xxx);领取人)
	private String wlKw;// 物料库位(库位)
	private String cangqu;// 仓区(临时存储，不存入数据库)
	private String barcode;// (已屏蔽) 条码
	private Integer zhuserId;// 供应商的Id
	private String gys;// 供应商
	private String kgliao;// 供料属性（TK/TK AVL/TK Price/CS）

	private Float zzNumber;// 占用数量
	private Float ztNumber;// 在途数量
	private Float ztzyNumber;// 在途占用数量 用于在途订单多余的数量到货后激活填充用
	private Float kcNumber;// 库存数量
	private Float qjCount;// 缺件量--禁用
	private Float zyqjCount;// 因为tjnumber不够而不能领料的数量（页面传值使用）
	private Float planCount;// 排产拆分临时使用数量（页面传值使用）

	private Float cgNumber;// 采购数量
	private Float outcgNumber;// 已转采购订单数量
	private Float dhNumber;// 到货数量
	private Float kcCount;// 入库数量
	private Double nowgoodsCount;// 实时库存 (页面传值)

	private Float kcNumberAvl;// 库存数量TK AVL（页面传值使用）--禁用
	private Float kcNumberPrice;// 库存数量TK Price（页面传值使用）--禁用
	private Float kcNumberCs;// 库存数量CS（页面传值使用）--禁用

	private Float zaizhizkCount;// 半成品转库数量（现场在制品离开现场进入仓库用以临时存储,外委或者中转）
	private Float zaizhiApplyZk;// 半成品转库申请中数量
	private Float zaizhikzkCount;// 在制品可转库数量（临时存储）

	private Float qlCount;// 缺领数量（外购件分开领取时页面传值使用）
	private Float qlCountZh;// 缺领对应组合或者总成的数量（外购件分开领取时页面传值使用）
	private Float zyCount;// 占用量--禁用
	private Float hasAlertCount;// 已发提醒数量
	private Float thisAlertCount;// 本次提醒数量（页面传值使用）
	private Float wwCount;// 被外委出去的数量（页面传值使用）
	private Float wwhascount;// 被外委出去的数量中剩余未领数量（页面传值使用）
	private Integer historyId;// 设变之前的id
	private Integer sbId;// 设变单Id
	private String sbNumber;// 设变单号
	private Integer oldFatherId;// 被数据化删除时记录
	private Integer oldRootId;// 被数据化删除时记录

	/*
	 * 价格关系(领料时根据库存对应价格,保存)
	 */
	private Integer priceId;// 价格表Id
	private Float hsPrice;// 含税价
	private Float bhsPrice;// 不含税价
	private Float taxprice;// 税率；

	/**
	 * MOQ控制相关;
	 */
	private Float cgbl;
	private String isUse;// 是否启用MOQ;
	private Float moqNum;
	private String gyscodeAndNum;;

	/****** 物料(外购件)跟踪相关结束 **************/
	// 树形结构对应关系
	private String fatherMarkId;
	private Procard procard;// 父流水卡片
	private Set<Procard> procardSet = new HashSet<Procard>();// 子流水卡片
	private List<Procard> procardList;// 页面传值使用
	private Set<ProcessInfor> processInforSet = new HashSet<ProcessInfor>();// 对应工序信息(一对多)

	private Set<WaigouWaiweiPlan> wgwwPlanSet;// 外委采购计划(只用于外委工序计划)
	// private ProcardWGCenter pwc;//采购计划中间表（用于外购、原材料的关系维护 .勿删）

	private String outOrderNum;// 外部订单号(页面传值)
	private List<ProcardMaterial> procardMateriallist;// 需要材料（页面传值使用）
	private List<ProcessInfor> processList;// 工序列表（页面传值使用）
	private List<ProcessInforWWApplyDetail> wwApplyDetailList;// 外委申请明细（页面传值使用）
	private List<WaigouPlan> waigouPlanList;// 外购订单申请明细（页面传值使用）
	private Integer modId;// 物料需求表Id(页面传值使用)
	private String gongxuNum;// (页面传值使用)
	private String gongxuName;// (页面传值使用)

	/****** 待禁用字段列表 **************/
	private String isycl;// (已屏蔽)
							// 是否为原材料(yes,no);------------------------------------------------------------待禁用
	// /*** 产品所在工位信息 ***/
	private String gongwei; // 工位*(多个工位","分割)------------------------------------------------------------待禁用
	private String shebeiNo; // 设备编号*------------------------------------------------------------待禁用
	private String shebeiName; // 设备名称------------------------------------------------------------待禁用
	/**
	 * 后续使用属性
	 */
	private String provateSubTime;// (已屏蔽)
									// 卡片入库时间------------------------------------------------------------待禁用
	private Float applyCount;// (已屏蔽)
								// 公斤（常规单位）------------------------------------------------------------待禁用
	private Float realReceive;// (已屏蔽)
								// 实发（支数或块数）------------------------------------------------------------待禁用
	private Float money;// (已屏蔽)
						// 应发奖金------------------------------------------------------------待禁用

	/***** 成本分析 ********/
	private Float bzysfei;// 包装运输------------------------------------------------------------待禁用
	private Float jbfei;// 加班费------------------------------------------------------------待禁用
	private Float rengongfei;// 人工成本（没有算上加班费）------------------------------------------------------------待禁用
	private Float shebeiZjFei;// 设备折旧费------------------------------------------------------------待禁用
	private Float nyxhFei;// 能源消耗费------------------------------------------------------------待禁用
	private Float clFei;// 材料费------------------------------------------------------------待禁用
	private Float wgFei;// 外购费------------------------------------------------------------待禁用
	private Float flFei;// 辅料费------------------------------------------------------------待禁用
	private Float glFei;// 管理费------------------------------------------------------------待禁用
	private Float clvFei;// 差旅费------------------------------------------------------------待禁用
	private Float canFei;// 餐费------------------------------------------------------------待禁用
	private Float allMoney;// 总费用------------------------------------------------------------待禁用
	private Float ckCount;// 仓库可领数量(临时保存数据，不放入数据库)
	private Float getCount;// 本次领取数量（临时保存数据，不放入数据库）
	// 原材料属性
	private String trademark;// (已屏蔽)
								// 牌号------------------------------------------------------------待禁用
	private String yuanName;// (已屏蔽)
							// 原材料名称------------------------------------------------------------待禁用
	private Float needCount;// 数量(实际数量)------------------------------------------------------------待禁用
	private String luhao;// (已屏蔽)
							// 炉号------------------------------------------------------------待禁用
	private String number;// (已屏蔽)
							// 编号------------------------------------------------------------待禁用
	private String actualFixed;// (已屏蔽)
								// 实际定额------------------------------------------------------------待禁用
	private String yuanUnit;// (已屏蔽)
							// 原材料单位------------------------------------------------------------待禁用
	private Float yhascount;// (已屏蔽)
							// 组合有原材料时用的剩余数量------------------------------------------------------------待禁用
	private String jgyl;// 是否加工余料(yes,no 默认为no;多领的料放入余料库，yes时锁定件号)
	private Float xiaohaoCount;// (已屏蔽)
								// 原材料消耗数量------------------------------------------------------------待禁用
	private String ytuhao;// (已屏蔽)
							// 原材料图号------------------------------------------------------------待禁用
	private String clClass;// (已屏蔽)
							// 材料类型(管料、卷料、外购件)------------------------------------------------------------待禁用
	// 对应卡片信息
	private Integer cardId;// (已屏蔽)
							// 主键------------------------------------------------------------待禁用
	private String cardNum;// (已屏蔽)
							// 卡号------------------------------------------------------------待禁用
	private Set<ProcardParts> procardPartsSet = new HashSet<ProcardParts>();// 领料(一对多)------------------------------------------------------------待禁用
	private Set<ProcardPro> procardPro = new HashSet<ProcardPro>();// ------------------------------------------------------------待禁用
	// 对应实际生产工序信息
	private Set<OneProcardBonus> oneProcardBonus = new HashSet<OneProcardBonus>();// 工序奖金分配------------------------------------------------------------待禁用
	private Set<ProcessinforPeople> processPeopleSet;// 领取工序的成员
	private List<ProcessinforPeople> processPeopleList;// 领取工序的成员----临时存储

	private List<ProcessAndWgProcardTem> proAndWgList;// 工序和外购件手维护关系表(传值使用)
	private Integer manualPlanId;// 页面传值 手动下单Id
	private String defaultKuwei;// 默认库位
	private String defaultCangqu;// 默认仓区
	// 壮龙需求
	private String isPeiJian;// 是否是配件（true:有）

	/******************* 退料申请 *********************/
	private Integer epId;// 申请退料流程Id
	@FieldMeta(name = "本次申请退料数量")
	private Float stuiLiaoNumber;// 本次申请退料数量
	@FieldMeta(name = "已退料数量")
	private Float ytuiLiaoNumber;// 已退料数量
	@FieldMeta(name = "申请状态")
	private String tuiLiaoStatus;// 退料申请状态 ; 未审批,同意(打回),已退料
	@FieldMeta(name = "申请时间")
	private String appliTime;// 申请退料时间
	private Integer appliUserId;// 申请退料人Id
	@FieldMeta(name = "申请人")
	private String appliUserName;// 申请退料人名称
	private Float gxtjNum;// 工序提交数量(页面传值)
	private String sbmsg;// 设变提示页面传值
	private String processNames;// 工序名(页面传值);

	private String oldSelfCard;// 之前的批次页面传值);
	private Integer maxBelongLayer;// 最大层数 （页面传值）
	/**
	 * 订单取消
	 */
	private Float qxCount;// 取消数量
	private Float oldCount;// 之前的数量;
	
	private List<Goods> goodsList;//页面传值
	private List<Sell> sellList;//页面传值

	/******************* 退料申请 *********************/
	public void setDuixiangNull() {
		this.procard = null;
		this.procardSet = null;
		this.processInforSet = null;
		this.procardPartsSet = null;
		this.procardPro = null;
		this.oneProcardBonus = null;
		this.processPeopleSet = null;
		this.wgwwPlanSet = null;
		this.procardMateriallist = null;
		this.processList = null;
		this.wwApplyDetailList = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getStuiLiaoNumber() {
		return stuiLiaoNumber;
	}

	public void setStuiLiaoNumber(Float stuiLiaoNumber) {
		this.stuiLiaoNumber = stuiLiaoNumber;
	}

	public Float getYtuiLiaoNumber() {
		return ytuiLiaoNumber;
	}

	public void setYtuiLiaoNumber(Float ytuiLiaoNumber) {
		this.ytuiLiaoNumber = ytuiLiaoNumber;
	}

	public Integer getAppliUserId() {
		return appliUserId;
	}

	public void setAppliUserId(Integer appliUserId) {
		this.appliUserId = appliUserId;
	}

	public String getAppliTime() {
		return appliTime;
	}

	public void setAppliTime(String appliTime) {
		this.appliTime = appliTime;
	}

	public String getAppliUserName() {
		return appliUserName;
	}

	public void setAppliUserName(String appliUserName) {
		this.appliUserName = appliUserName;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getTuiLiaoStatus() {
		return tuiLiaoStatus;
	}

	public void setTuiLiaoStatus(String tuiLiaoStatus) {
		this.tuiLiaoStatus = tuiLiaoStatus;
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

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public String getPlanOrderNum() {
		return planOrderNum;
	}

	public void setPlanOrderNum(String planOrderNum) {
		this.planOrderNum = planOrderNum;
	}

	public Float getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Float maxCount) {
		this.maxCount = maxCount;
	}

	public Float getCorrCount() {
		return corrCount;
	}

	public void setCorrCount(Float corrCount) {
		this.corrCount = corrCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProcardStyle() {
		return procardStyle;
	}

	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
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

	public String getLuhao() {
		return luhao;
	}

	public void setLuhao(String luhao) {
		this.luhao = luhao;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getActualFixed() {
		return actualFixed;
	}

	public void setActualFixed(String actualFixed) {
		this.actualFixed = actualFixed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@JSONField(serialize = false)
	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	@JSONField(serialize = false)
	public Set<Procard> getProcardSet() {
		return procardSet;
	}

	public void setProcardSet(Set<Procard> procardSet) {
		this.procardSet = procardSet;
	}

	@JSONField(serialize = false)
	public Set<ProcessInfor> getProcessInforSet() {
		return processInforSet;
	}

	public void setProcessInforSet(Set<ProcessInfor> processInforSet) {
		this.processInforSet = processInforSet;
	}

	@JSONField(serialize = false)
	public Set<ProcardParts> getProcardPartsSet() {
		return procardPartsSet;
	}

	public void setProcardPartsSet(Set<ProcardParts> procardPartsSet) {
		this.procardPartsSet = procardPartsSet;
	}

	public String getProvateSubTime() {
		return provateSubTime;
	}

	public void setProvateSubTime(String provateSubTime) {
		this.provateSubTime = provateSubTime;
	}

	public Float getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}

	public Float getRealReceive() {
		return realReceive;
	}

	public void setRealReceive(Float realReceive) {
		this.realReceive = realReceive;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getZhikaren() {
		return zhikaren;
	}

	public void setZhikaren(String zhikaren) {
		this.zhikaren = zhikaren;
	}

	public String getSelfCard() {
		return selfCard;
	}

	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

	@JSONField(serialize = false)
	public Set<ProcardPro> getProcardPro() {
		return procardPro;
	}

	public void setProcardPro(Set<ProcardPro> procardPro) {
		this.procardPro = procardPro;
	}

	@JSONField(serialize = false)
	public Set<OneProcardBonus> getOneProcardBonus() {
		return oneProcardBonus;
	}

	public void setOneProcardBonus(Set<OneProcardBonus> oneProcardBonus) {
		this.oneProcardBonus = oneProcardBonus;
	}

	public Integer getPlanOrderId() {
		return planOrderId;
	}

	public void setPlanOrderId(Integer planOrderId) {
		this.planOrderId = planOrderId;
	}

	public Float getFilnalCount() {
		return filnalCount;
	}

	public void setFilnalCount(Float filnalCount) {
		this.filnalCount = filnalCount;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Float getNeedCount() {
		return needCount;
	}

	public void setNeedCount(Float needCount) {
		this.needCount = needCount;
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

	public Integer getOldProcardId() {
		return oldProcardId;
	}

	public void setOldProcardId(Integer oldProcardId) {
		this.oldProcardId = oldProcardId;
	}

	public String getLingliaoren() {
		return lingliaoren;
	}

	public void setLingliaoren(String lingliaoren) {
		this.lingliaoren = lingliaoren;
	}

	public String getShebeiNo() {
		return shebeiNo;
	}

	public void setShebeiNo(String shebeiNo) {
		this.shebeiNo = shebeiNo;
	}

	public String getShebeiName() {
		return shebeiName;
	}

	public void setShebeiName(String shebeiName) {
		this.shebeiName = shebeiName;
	}

	public String getGongwei() {
		return gongwei;
	}

	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}

	public String getProTemStatus() {
		return proTemStatus;
	}

	public void setProTemStatus(String proTemStatus) {
		this.proTemStatus = proTemStatus;
	}

	public Integer getParallelId() {
		return parallelId;
	}

	public void setParallelId(Integer parallelId) {
		this.parallelId = parallelId;
	}

	public String getJihuoStatua() {
		return jihuoStatua;
	}

	public void setJihuoStatua(String jihuoStatua) {
		this.jihuoStatua = jihuoStatua;
	}

	public Float getKlNumber() {
		return klNumber;
	}

	public void setKlNumber(Float klNumber) {
		this.klNumber = klNumber;
	}

	public Float getYlNumber() {
		return ylNumber;
	}

	public void setYlNumber(Float ylNumber) {
		this.ylNumber = ylNumber;
	}

	public Float getTjNumber() {
		return tjNumber;
	}

	public void setTjNumber(Float tjNumber) {
//		if (getFilnalCount() != null && tjNumber != null
//				&& getFilnalCount() < tjNumber) {
//			System.out.println(getFilnalCount() - tjNumber);
//			throw new RuntimeException(markId
//					+ "'s error:filnalCount<tjNumber;");
//		}
		this.tjNumber = tjNumber;
	}

	public Float getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(Float minNumber) {
		if (minNumber == null) {
			minNumber = 0f;
		}
		this.minNumber = minNumber;
	}

	public Float getRukuCount() {
		return rukuCount;
	}

	public void setRukuCount(Float rukuCount) {
		this.rukuCount = rukuCount;
	}

	public String getJihuoType() {
		return jihuoType;
	}

	public void setJihuoType(String jihuoType) {
		this.jihuoType = jihuoType;
	}

	public Float getSingleDuration() {
		return singleDuration;
	}

	public void setSingleDuration(Float singleDuration) {
		this.singleDuration = singleDuration;
	}

	public Float getCapacity() {
		return capacity;
	}

	public void setCapacity(Float capacity) {
		this.capacity = capacity;
	}

	public Float getCapacitySurplus() {
		return capacitySurplus;
	}

	public void setCapacitySurplus(Float capacitySurplus) {
		this.capacitySurplus = capacitySurplus;
	}

	public Float getCapacityRatio() {
		return capacityRatio;
	}

	public void setCapacityRatio(Float capacityRatio) {
		this.capacityRatio = capacityRatio;
	}

	public Float getDeliveryDuration() {
		return deliveryDuration;
	}

	public void setDeliveryDuration(Float deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}

	public Float getDeliveryRatio() {
		return deliveryRatio;
	}

	public void setDeliveryRatio(Float deliveryRatio) {
		this.deliveryRatio = deliveryRatio;
	}

	public Integer getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(Integer deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public Float getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(Float deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public Float getProSingleDuration() {
		return proSingleDuration;
	}

	public void setProSingleDuration(Float proSingleDuration) {
		this.proSingleDuration = proSingleDuration;
	}

	public Float getAllJiepai() {
		return allJiepai;
	}

	public void setAllJiepai(Float allJiepai) {
		this.allJiepai = allJiepai;
	}

	public String getNeedFinalDate() {
		return needFinalDate;
	}

	public void setNeedFinalDate(String needFinalDate) {
		this.needFinalDate = needFinalDate;
	}

	@JSONField(serialize = false)
	public Set<ProcessinforPeople> getProcessPeopleSet() {
		return processPeopleSet;
	}

	public void setProcessPeopleSet(Set<ProcessinforPeople> processPeopleSet) {
		this.processPeopleSet = processPeopleSet;
	}

	@JSONField(serialize = false)
	public Set<WaigouWaiweiPlan> getWgwwPlanSet() {
		return wgwwPlanSet;
	}

	public void setWgwwPlanSet(Set<WaigouWaiweiPlan> wgwwPlanSet) {
		this.wgwwPlanSet = wgwwPlanSet;
	}

	public String getJihuoDate() {
		return jihuoDate;
	}

	public void setJihuoDate(String jihuoDate) {
		this.jihuoDate = jihuoDate;
	}

	public String getDanjiaojian() {
		return danjiaojian;
	}

	public void setDanjiaojian(String danjiaojian) {
		this.danjiaojian = danjiaojian;
	}

	public Integer getZhuserId() {
		return zhuserId;
	}

	public void setZhuserId(Integer zhuserId) {
		this.zhuserId = zhuserId;
	}

	public String getGys() {
		return gys;
	}

	public void setGys(String gys) {
		this.gys = gys;
	}

	public Float getHascount() {
		return hascount;
	}

	public void setHascount(Float hascount) {
		if (hascount != null && hascount < 0) {
			throw new RuntimeException(markId + ":hascount<0("+this.id+")");
		}
		this.hascount = hascount;
	}

	public String getLingliaostatus() {
		return lingliaostatus;
	}

	public void setLingliaostatus(String lingliaostatus) {
		this.lingliaostatus = lingliaostatus;
	}

	public Float getCkCount() {
		return ckCount;
	}

	public Float getSafeCount() {
		return safeCount;
	}

	public void setSafeCount(Float safeCount) {
		this.safeCount = safeCount;
	}

	public Float getLastCount() {
		return lastCount;
	}

	public void setLastCount(Float lastCount) {
		this.lastCount = lastCount;
	}

	public void setCkCount(Float ckCount) {
		this.ckCount = ckCount;
	}

	public String getNeedProcess() {
		return needProcess;
	}

	public void setNeedProcess(String needProcess) {
		this.needProcess = needProcess;
	}

	public Float getNowAllJiepai() {
		return nowAllJiepai;
	}

	public void setNowAllJiepai(Float nowAllJiepai) {
		this.nowAllJiepai = nowAllJiepai;
	}

	public Float getNowAllNenghao() {
		return nowAllNenghao;
	}

	public void setNowAllNenghao(Float nowAllNenghao) {
		this.nowAllNenghao = nowAllNenghao;
	}

	public Float getOnePeice() {
		return onePeice;
	}

	public void setOnePeice(Float onePeice) {
		this.onePeice = onePeice;
	}

	public Float getAllMonty() {
		return allMonty;
	}

	public void setAllMonty(Float allMonty) {
		this.allMonty = allMonty;
	}

	public Float getSellCount() {
		return sellCount;
	}

	public void setSellCount(Float sellCount) {
		this.sellCount = sellCount;
	}

	public String getRootMarkId() {
		return rootMarkId;
	}

	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}

	public String getRootSelfCard() {
		return rootSelfCard;
	}

	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}

	public Float getRengongfei() {
		return rengongfei;
	}

	public void setRengongfei(Float rengongfei) {
		this.rengongfei = rengongfei;
	}

	public Float getShebeiZjFei() {
		return shebeiZjFei;
	}

	public void setShebeiZjFei(Float shebeiZjFei) {
		this.shebeiZjFei = shebeiZjFei;
	}

	public Float getNyxhFei() {
		return nyxhFei;
	}

	public void setNyxhFei(Float nyxhFei) {
		this.nyxhFei = nyxhFei;
	}

	public Float getClFei() {
		return clFei;
	}

	public void setClFei(Float clFei) {
		this.clFei = clFei;
	}

	public Float getWgFei() {
		return wgFei;
	}

	public void setWgFei(Float wgFei) {
		this.wgFei = wgFei;
	}

	public Float getFlFei() {
		return flFei;
	}

	public void setFlFei(Float flFei) {
		this.flFei = flFei;
	}

	public Float getGlFei() {
		return glFei;
	}

	public void setGlFei(Float glFei) {
		this.glFei = glFei;
	}

	public Float getClvFei() {
		return clvFei;
	}

	public void setClvFei(Float clvFei) {
		this.clvFei = clvFei;
	}

	public Float getCanFei() {
		return canFei;
	}

	public void setCanFei(Float canFei) {
		this.canFei = canFei;
	}

	public Float getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(Float allMoney) {
		this.allMoney = allMoney;
	}

	public Float getQlCount() {
		return qlCount;
	}

	public void setQlCount(Float qlCount) {
		this.qlCount = qlCount;
	}

	public Float getQlCountZh() {
		return qlCountZh;
	}

	public void setQlCountZh(Float qlCountZh) {
		this.qlCountZh = qlCountZh;
	}

	public String getLingliaoType() {
		return lingliaoType;
	}

	public void setLingliaoType(String lingliaoType) {
		this.lingliaoType = lingliaoType;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public Float getHasRuku() {
		return hasRuku;
	}

	public void setHasRuku(Float hasRuku) {
		this.hasRuku = hasRuku;
	}

	public String getJgyl() {
		return jgyl;
	}

	public void setJgyl(String jgyl) {
		this.jgyl = jgyl;
	}

	public String getBanBenNumber() {
		return banBenNumber;
	}

	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}

	public Float getYhascount() {
		return yhascount;
	}

	public void setYhascount(Float yhascount) {
		this.yhascount = yhascount;
	}

	/**
	 * 判断是否为组合有原材料
	 * 
	 * @return
	 */
	public boolean isZhHasYcl() {
		if (procardStyle != null && procardStyle.equals("组合")
				&& trademark != null && trademark.length() > 0) {
			if (yhascount == null) {
				yhascount = klNumber;
			}
			return true;
		} else {
			return false;
		}
	}

	public Float getGetCount() {
		return getCount;
	}

	public void setGetCount(Float getCount) {
		this.getCount = getCount;
	}

	public String getYuanName() {
		return yuanName;
	}

	public void setYuanName(String yuanName) {
		this.yuanName = yuanName;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		if ("设变锁定".equals(oldStatus) && "设变锁定".equals(status)) {
			throw new RuntimeException(markId
					+ ":  oldStatus and status repeat Error(shebiansuoding)");
		}
		this.oldStatus = oldStatus;
	}

	public Float getJbfei() {
		return jbfei;
	}

	public void setJbfei(Float jbfei) {
		this.jbfei = jbfei;
	}

	public Float getBzysfei() {
		return bzysfei;
	}

	public void setBzysfei(Float bzysfei) {
		this.bzysfei = bzysfei;
	}

	public String getLingliaoDetail() {
		return lingliaoDetail;
	}

	public void setLingliaoDetail(String lingliaoDetail) {
		this.lingliaoDetail = lingliaoDetail;
	}

	public Float getHasAlertCount() {
		return hasAlertCount;
	}

	public void setHasAlertCount(Float hasAlertCount) {
		this.hasAlertCount = hasAlertCount;
	}

	public List<ProcardMaterial> getProcardMateriallist() {
		return procardMateriallist;
	}

	public void setProcardMateriallist(List<ProcardMaterial> procardMateriallist) {
		this.procardMateriallist = procardMateriallist;
	}

	public Float getKcCount() {
		return kcCount;
	}

	public void setKcCount(Float kcCount) {
		this.kcCount = kcCount;
	}

	public Float getZyCount() {
		return zyCount;
	}

	public void setZyCount(Float zyCount) {
		this.zyCount = zyCount;
	}

	public Float getThisAlertCount() {
		return thisAlertCount;
	}

	public void setThisAlertCount(Float thisAlertCount) {
		this.thisAlertCount = thisAlertCount;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWlstatus() {
		return wlstatus;
	}

	public void setWlstatus(String wlstatus) {
		this.wlstatus = wlstatus;
	}

	public String getWlWeizhiDt() {
		return wlWeizhiDt;
	}

	public void setWlWeizhiDt(String wlWeizhiDt) {
		this.wlWeizhiDt = wlWeizhiDt;
	}

	public String getWlKw() {
		return wlKw;
	}

	public void setWlKw(String wlKw) {
		this.wlKw = wlKw;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Float getCgNumber() {
		return cgNumber;
	}

	public void setCgNumber(Float cgNumber) {
		this.cgNumber = cgNumber;
	}

	public Float getDhNumber() {
		return dhNumber;
	}

	public void setDhNumber(Float dhNumber) {
		this.dhNumber = dhNumber;
	}

	public Float getZzNumber() {
		return zzNumber;
	}

	public void setZzNumber(Float zzNumber) {
		this.zzNumber = zzNumber;
	}

	public Float getZtNumber() {
		return ztNumber;
	}

	public void setZtNumber(Float ztNumber) {
		this.ztNumber = ztNumber;
	}

	public Float getKcNumber() {
		return kcNumber;
	}

	public void setKcNumber(Float kcNumber) {
		this.kcNumber = kcNumber;
	}

	public Float getXuhao() {
		return xuhao;
	}

	public void setXuhao(Float xuhao) {
		this.xuhao = xuhao;
	}

	public List<ProcessInfor> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessInfor> processList) {
		this.processList = processList;
	}

	public List<ProcessInforWWApplyDetail> getWwApplyDetailList() {
		return wwApplyDetailList;
	}

	public void setWwApplyDetailList(
			List<ProcessInforWWApplyDetail> wwApplyDetailList) {
		this.wwApplyDetailList = wwApplyDetailList;
	}

	public Float getQjCount() {
		return qjCount;
	}

	public void setQjCount(Float qjCount) {
		this.qjCount = qjCount;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public Float getBili() {
		return bili;
	}

	public void setBili(Float bili) {
		this.bili = bili;
	}

	public Float getXiaohaoCount() {
		return xiaohaoCount;
	}

	public void setXiaohaoCount(Float xiaohaoCount) {
		this.xiaohaoCount = xiaohaoCount;
	}

	public String getYtuhao() {
		return ytuhao;
	}

	public void setYtuhao(String ytuhao) {
		this.ytuhao = ytuhao;
	}

	public String getClClass() {
		return clClass;
	}

	public void setClClass(String clClass) {
		this.clClass = clClass;
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

	public String getCalculateType() {
		return calculateType;
	}

	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}

	public Float getWwblCount() {
		return wwblCount;
	}

	public void setWwblCount(Float wwblCount) {
		this.wwblCount = wwblCount;
	}

	public Float getWwblreceiveCount() {
		return wwblreceiveCount;
	}

	public void setWwblreceiveCount(Float wwblreceiveCount) {
		this.wwblreceiveCount = wwblreceiveCount;
	}

	public Float getOutcgNumber() {
		return outcgNumber;
	}

	public void setOutcgNumber(Float outcgNumber) {
		this.outcgNumber = outcgNumber;
	}

	public Float getKcNumberAvl() {
		return kcNumberAvl;
	}

	public void setKcNumberAvl(Float kcNumberAvl) {
		this.kcNumberAvl = kcNumberAvl;
	}

	public Float getKcNumberPrice() {
		return kcNumberPrice;
	}

	public void setKcNumberPrice(Float kcNumberPrice) {
		this.kcNumberPrice = kcNumberPrice;
	}

	public Float getKcNumberCs() {
		return kcNumberCs;
	}

	public void setKcNumberCs(Float kcNumberCs) {
		this.kcNumberCs = kcNumberCs;
	}

	public String getIsycl() {
		return isycl;
	}

	public void setIsycl(String isycl) {
		this.isycl = isycl;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Float getHsPrice() {
		return hsPrice;
	}

	public void setHsPrice(Float hsPrice) {
		this.hsPrice = hsPrice;
	}

	public Float getBhsPrice() {
		return bhsPrice;
	}

	public void setBhsPrice(Float bhsPrice) {
		this.bhsPrice = bhsPrice;
	}

	public Float getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Float taxprice) {
		this.taxprice = taxprice;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getOutOrderNum() {
		return outOrderNum;
	}

	public void setOutOrderNum(String outOrderNum) {
		this.outOrderNum = outOrderNum;
	}

	public Float getZaizhizkCount() {
		return zaizhizkCount;
	}

	public void setZaizhizkCount(Float zaizhizkCount) {
		this.zaizhizkCount = zaizhizkCount;
	}

	public Float getZaizhikzkCount() {
		return zaizhikzkCount;
	}

	public void setZaizhikzkCount(Float zaizhikzkCount) {
		this.zaizhikzkCount = zaizhikzkCount;
	}

	public Float getZaizhiApplyZk() {
		return zaizhiApplyZk;
	}

	public void setZaizhiApplyZk(Float zaizhiApplyZk) {
		this.zaizhiApplyZk = zaizhiApplyZk;
	}

	public String getWgjihuoTime() {
		return wgjihuoTime;
	}

	public void setWgjihuoTime(String wgjihuoTime) {
		this.wgjihuoTime = wgjihuoTime;
	}

	public Integer getProcardTemplateId() {
		return procardTemplateId;
	}

	public void setProcardTemplateId(Integer procardTemplateId) {
		this.procardTemplateId = procardTemplateId;
	}

	public String getJioafuDate() {
		return jioafuDate;
	}

	public void setJioafuDate(String jioafuDate) {
		this.jioafuDate = jioafuDate;
	}

	public Float getSunhao() {
		return sunhao;
	}

	public void setSunhao(Float sunhao) {
		this.sunhao = sunhao;
	}

	public Float getWwCount() {
		return wwCount;
	}

	public void setWwCount(Float wwCount) {
		this.wwCount = wwCount;
	}

	public Float getWwhascount() {
		return wwhascount;
	}

	public void setWwhascount(Float wwhascount) {
		this.wwhascount = wwhascount;
	}

	public String getCangqu() {
		return cangqu;
	}

	public void setCangqu(String cangqu) {
		this.cangqu = cangqu;
	}

	public String getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}

	public List<WaigouPlan> getWaigouPlanList() {
		return waigouPlanList;
	}

	public void setWaigouPlanList(List<WaigouPlan> waigouPlanList) {
		this.waigouPlanList = waigouPlanList;
	}

	public String getZzjihuo() {
		return zzjihuo;
	}

	public void setZzjihuo(String zzjihuo) {
		this.zzjihuo = zzjihuo;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public String getSbStatus() {
		return sbStatus;
	}

	// public void setSbStatus(String sbStatus) {
	// this.sbStatus = sbStatus;
	// }

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
	}

	public List<ProcessAndWgProcardTem> getProAndWgList() {
		return proAndWgList;
	}

	public void setProAndWgList(List<ProcessAndWgProcardTem> proAndWgList) {
		this.proAndWgList = proAndWgList;
	}

	public Integer getManualPlanId() {
		return manualPlanId;
	}

	public void setManualPlanId(Integer manualPlanId) {
		this.manualPlanId = manualPlanId;
	}

	public List<ProcessinforPeople> getProcessPeopleList() {
		return processPeopleList;
	}

	public void setProcessPeopleList(List<ProcessinforPeople> processPeopleList) {
		this.processPeopleList = processPeopleList;
	}

	public String getHasChange() {
		return hasChange;
	}

	public void setHasChange(String hasChange) {
		this.hasChange = hasChange;
	}

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public String getNowFinalDate() {
		return nowFinalDate;
	}

	public void setNowFinalDate(String nowFinalDate) {
		this.nowFinalDate = nowFinalDate;
	}

	public String getWlqrtime() {
		return wlqrtime;
	}

	public void setWlqrtime(String wlqrtime) {
		this.wlqrtime = wlqrtime;
	}

	public Integer getSbId() {
		return sbId;
	}

	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}

	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
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

	public Integer getPlanOrderDetailId() {
		return planOrderDetailId;
	}

	public void setPlanOrderDetailId(Integer planOrderDetailId) {
		this.planOrderDetailId = planOrderDetailId;
	}

	public Integer getOldFatherId() {
		return oldFatherId;
	}

	public void setOldFatherId(Integer oldFatherId) {
		this.oldFatherId = oldFatherId;
	}

	public Integer getOldRootId() {
		return oldRootId;
	}

	public void setOldRootId(Integer oldRootId) {
		this.oldRootId = oldRootId;
	}

	public void setSbStatus(String sbStatus) {
		this.sbStatus = sbStatus;
	}

	public String getNowJihuoDate() {
		return nowJihuoDate;
	}

	public void setNowJihuoDate(String nowJihuoDate) {
		this.nowJihuoDate = nowJihuoDate;
	}

	public Float getZtzyNumber() {
		return ztzyNumber;
	}

	public void setZtzyNumber(Float ztzyNumber) {
		this.ztzyNumber = ztzyNumber;
	}

	public Float getCgbl() {
		return cgbl;
	}

	public void setCgbl(Float cgbl) {
		this.cgbl = cgbl;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public Float getMoqNum() {
		return moqNum;
	}

	public void setMoqNum(Float moqNum) {
		this.moqNum = moqNum;
	}

	public String getGyscodeAndNum() {
		return gyscodeAndNum;
	}

	public void setGyscodeAndNum(String gyscodeAndNum) {
		this.gyscodeAndNum = gyscodeAndNum;
	}

	public String getCgStatus() {
		return cgStatus;
	}

	public void setCgStatus(String cgStatus) {
		this.cgStatus = cgStatus;
	}

	public Integer getZhikarenId() {
		return zhikarenId;
	}

	public void setZhikarenId(Integer zhikarenId) {
		this.zhikarenId = zhikarenId;
	}

	public Float getZyqjCount() {
		return zyqjCount;
	}

	public void setZyqjCount(Float zyqjCount) {
		this.zyqjCount = zyqjCount;
	}

	public Integer getSunhaoType() {
		return sunhaoType;
	}

	public void setSunhaoType(Integer sunhaoType) {
		this.sunhaoType = sunhaoType;
	}

	public Integer getThProcardId() {
		return thProcardId;
	}

	public void setThProcardId(Integer thProcardId) {
		this.thProcardId = thProcardId;
	}

	public Float getCorrCountDao() {
		return corrCountDao;
	}

	public void setCorrCountDao(Float corrCountDao) {
		this.corrCountDao = corrCountDao;
	}

	public Float getCountDao() {
		return countDao;
	}

	public void setCountDao(Float countDao) {
		this.countDao = countDao;
	}

	public String getIsPeiJian() {
		return isPeiJian;
	}

	public void setIsPeiJian(String isPeiJian) {
		this.isPeiJian = isPeiJian;
	}

	public String getDefaultKuwei() {
		return defaultKuwei;
	}

	public void setDefaultKuwei(String defaultKuwei) {
		this.defaultKuwei = defaultKuwei;
	}

	public String getDefaultCangqu() {
		return defaultCangqu;
	}

	public void setDefaultCangqu(String defaultCangqu) {
		this.defaultCangqu = defaultCangqu;
	}

	public String getMrpjihuoDate() {
		return mrpjihuoDate;
	}

	public void setMrpjihuoDate(String mrpjihuoDate) {
		this.mrpjihuoDate = mrpjihuoDate;
	}

	public Double getNowgoodsCount() {
		return nowgoodsCount;
	}

	public void setNowgoodsCount(Double nowgoodsCount) {
		this.nowgoodsCount = nowgoodsCount;
	}

	public Float getHasPlan() {
		return hasPlan;
	}

	public void setHasPlan(Float hasPlan) {
		this.hasPlan = hasPlan;
	}

	public Float getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Float planCount) {
		this.planCount = planCount;
	}

	public Float getGxtjNum() {
		return gxtjNum;
	}

	public void setGxtjNum(Float gxtjNum) {
		this.gxtjNum = gxtjNum;
	}

	public String getSbmsg() {
		return sbmsg;
	}

	public void setSbmsg(String sbmsg) {
		this.sbmsg = sbmsg;
	}

	public String getProcessNames() {
		return processNames;
	}

	public void setProcessNames(String processNames) {
		this.processNames = processNames;
	}

	public String getOldSelfCard() {
		return oldSelfCard;
	}

	public void setOldSelfCard(String oldSelfCard) {
		this.oldSelfCard = oldSelfCard;
	}

	public Integer getMaxBelongLayer() {
		return maxBelongLayer;
	}

	public void setMaxBelongLayer(Integer maxBelongLayer) {
		this.maxBelongLayer = maxBelongLayer;
	}

	public Float getQxCount() {
		return qxCount;
	}

	public void setQxCount(Float qxCount) {
		this.qxCount = qxCount;
	}

	public Float getOldCount() {
		return oldCount;
	}

	public void setOldCount(Float oldCount) {
		this.oldCount = oldCount;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Sell> getSellList() {
		return sellList;
	}

	public void setSellList(List<Sell> sellList) {
		this.sellList = sellList;
	}

	public String getSduser() {
		return sduser;
	}

	public void setSduser(String sduser) {
		this.sduser = sduser;
	}

	public String getFatherMarkId() {
		return fatherMarkId;
	}

	public void setFatherMarkId(String fatherMarkId) {
		this.fatherMarkId = fatherMarkId;
	}

	public Integer getHgNumber() {
		return hgNumber;
	}

	public void setHgNumber(Integer hgNumber) {
		this.hgNumber = hgNumber;
	}

	

}
