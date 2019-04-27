package com.task.entity.sop;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Users;
import com.task.util.FieldMeta;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

/**
 * 工程设变时临时BOM (表：ta_sop_w_ProcardTemplatesb)
 * @author txb
 *
 */
public class ProcardTemplatesb  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	// 公共属性
	private Integer sbApplyId;//设变单id
	private String sbNumber;//设变单号
	private Integer oldPtId;//原生产bomId
	private String issb;//是否为本零件发起设变（yes,no）
	private String sbApplyStatus;//设变状态('正常','删除')
	
	@FieldMeta(name="件号")
	private String markId;// 件号
	@FieldMeta(name="业务件号")
	private String ywMarkId;//业务件号（总成对外销售使用）
	@FieldMeta(name=" 名称")
	private String proName;// 名称
	private String specification;// 规格
	private String caizhi;//材质（标记生产件是用什么材料做的）
	private String unit;// 单位(自制)
	private Float corrCount;// 对应数量(权值)
	private Float quanzi1;// 权值(自制件:原材料)/(组合:外购)
	private Float quanzi2;// 权值(自制件:原材料)
	private Float bili;//比重
	private Float maxCount;// 数量(流水卡片最大数量)
	private Integer sunhaoType;//损耗值类型(0为百分比类型  ；1为固定值类型)
	private Float sunhao;//零件损耗(采购数量*(1+sunhao))
	private String productStyle;// 产品类型(试制，批产)
	private String procardStyle;// 卡片类型(总成，组合，外购，自制)
	private String wgType;//物料类别
	private String kgliao;//供料来源（TKVAL 指定，cs 客供，TK 自购）
	private String zzjihuo;//自制件激活模式
	
	private Integer rootId;// 第一层父类Id
	private String rootMarkId;// 对应成品件号
	private String loadMarkId;// 初始成品件号
	private Integer fatherId;// 上层父类Id(删除时不处理这个标记)
	private Integer belongLayer;// 当前层
	private String jihuoType;// 激活类型(层次激活(cc)、自制件激活(zzj))
	
	private String danjiaojian;// 是、否单交件
	private String checkMsg;//自检信息（页面显示）
	private String clType;//材料类型（外购件是材料类型，自制件是材质。共用一个字段）
	private String biaochu;//表处
	private Float cardXiaoHao;//以1为总成数量对应件号所需数量(临时使用)
	private String tuhao;//图号
	private String sbStatus;//设变审批状态
	private String bomApplyStatus;//BOM结构审批申请
	
	
	private String hwdrMarkId;//华为BOM导入时的另一个备用件号(不存数据库)
	private String carStyle;// 车型(型号)
	private String oldProcardStyle;//修改之前类型（页面传值）
	private String remark;// 备注
	private Float xuhao;//显示时用来排序
	private String showSize;//尺寸
	
	private String lingliaostatus;// 是否领料	
	private String needProcess;// （外购件半成品）是否需要工序(yes,no)
	private String lingliaoType; // 领料方式（完全到齐 all，部分到齐 part,只在组合和总成上用默认为完全到齐）	
	private Float thisLength;//长
	private Float thisWidth;//宽
	private Float thisHight;//高
	private String calculateType;//计算方式
	
	// 奖金计算相关
	private Float laborcost;// 报价人工费 对外
	private Float fenpeiRate;// 可调系数
	private Float dailyoutput;// 日产量
	private Double onePrice;// 单件提奖额,对内提奖价

	private String proTemStatus;// 状态(单独/并行)
	private Integer parallelId;// 并行id

	private String addDateTime;// 添加时间
	private Integer avgProductionTakt;//生产节拍
	private Float avgDeliveryTime;//配送时长
	private Float cgperiod;//采购周期

	/*** 精益生产相关 */
	private Float needCapacity;// 客户需求产能
	private Float needNumber;// 客户需求数
	private Float needZhoueqi;// 客户需求周期
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

	private Integer zhuserId;// 供应商的Id
	private String gys;// 供应商（TKVAL 指定，cs 客供，TK 自购）

	private String jisuanStatus;// 计算状态(ok/no)
	private Integer noNum;// 不合格数量(工序不满足客户需求量的工序个数)
	private String lastUser;//最后更新人员
	// 工艺编制系统
	private String banbenStatus;//版本状态(默认,历史（空则表示默认）,试用)
	private String banBenNumber;//版本号
	private String newBanBenNumber;//新版本号，临时使用不放入数据库
	private String changeProcess;//是否修改工序(设变之后控制是否可以更改)
	private String changeTz;//是否修改图纸(设变之后控制是否可以更改)

	private String bianzhiName;// 编制人
	private Integer bianzhiId;// 编制ID
	private String bianzhiDate;// 编制时间

	private String jiaoduiName;// 校对人
	private Integer jiaoduiId;// 校对ID
	private String jiaoduiDate;// 校对时间
	private String jiaoduiName2;// 校对人
	private Integer jiaoduiId2;// 校对ID
	private String jiaoduiDate2;// 校对时间

	private String shenheName;// 审核人
	private Integer shenheId;// 审核ID
	private String shenheDate;// 审核时间
	private String shenheName2;// 审核人
	private Integer shenheId2;// 审核ID
	private String shenheDate2;// 审核时间

	private String pizhunName;// 批准人
	private Integer pizhunId;// 批准ID
	private String pizhunDate;// 批准时间
	private String pizhunName2;// 批准人
	private Integer pizhunId2;// 批准ID
	private String pizhunDate2;// 批准时间

	private String fachuDate;// 发出日期
	private Integer banci;// 版次
	private Integer createUserId;// 创建人ID
	private String createUserName;// 创建人姓名
	private String createDate;// 创建时间
	private String upRemark;//设变备注
	
	private Integer epId;// 设变流程ID
	private Integer epId2;// BOm结构审批流程ID
	private String bzStatus;// 工艺编制状态(初始,待编制,已编制,已校对,已审核,已批准)
	private Float jianyanjiepai;// 外购件检验节拍；
	private Integer historyId;//历史件号Id；导入模板时使用不放人数据库
	private Integer historyId2;//历史预编译BOM件号Id；导入模板时使用不放人数据库
	private String standardSize;//标准尺寸
	private String errorRange;//误差范围
	private Float safeCount;// 安全库存
	private Float lastCount;// 最低存量
	private String hasChange;//设变之后下层权值发生改变或者新增的外购件做标记（是,否）
	private String importance;//重要度(A,B,C)
	
	private String dataStatus;//数据状态（正常,删除）
	private String sbmsg;//页面显示
	private Integer sbId;//设变主表Id(ProcardTemplateBanBenApply)
	private String tq;//特权页面显示（yes,no）
	
	private String gxxg;//是否有工序修改（是,否）默认否
	
	
	private ProcardTemplatesb procardTemplatesb;
	private Set<ProcardTemplatesb> procardsbTSet;// 下层流水卡片信息(本表的一对多关系)
	private Set<ProcessTemplatesb> processTemplatesb;// 对应的工序信息(一对多关系)
	private ProcardTemplateBanBen ptbb;//页面显示
	
	private String  adduser;//添加人；
	private String addcode;//添加人工号
	private String addtime;//添加时间
	private Integer beforeTrid;//页面使用
	private Integer cansb;//页面使用0表示可以1表示不可以

	private String daoruDate;//导入bom时间
	private String jgyl;//是否加工余料(yes,no 默认为no;多领的料放入余料库，yes时锁定件号)--待禁用
	private String isycl;//是否为原材料(yes,no);				--待禁用
	private Integer score;												//		--待禁用
	private Integer totalScore;											//		--待禁用
	private String trademark;// 牌号(件号)										--待禁用
	private String ytuhao;//原材料图号											--待禁用
	private String yuanName;//原材料名称											--待禁用
	private String luhao;// 炉号													--待禁用
	private String number;// 编号												--待禁用
	private String actualFixed;// 实际定额										--待禁用
	private String yuanUnit;// 原材料/外购件单位									--待禁用
	private Float xiaohaoCount;//原材料消耗数量									--待禁用
	private String clClass;// 材料类型(管料、卷料、外购件)						--待禁用
	private String status;// 自制件下 原材料属性 ：是否外购 						--待禁用
	private String numb;// 工艺编程编号//										--待禁用
	/** 历史版本的模板记录 版次每变更一次添加一个版本历史记录 */
	private Integer parentId;//													--待禁用
	private Integer pageTotal;// 工几页											--待禁用
	// 会签
	private Integer jiagongId;// 加工ID											--待禁用
	private String jiagongName;// 加工
	private String jiagongDate;// 加工时间										--待禁用
	private Integer pinzhiId;// 品质ID											--待禁用
	private String pinzhiName;// 品质											--待禁用
	private String pinzhiDate;// 品质时间										--待禁用
	/** 签名 */
	private String qianming;//													--待禁用
	/** 日期 */
	private String qianmingDate;//												--待禁用
	
	//壮龙需求
	private String isPeiJian;//是否是配件（true:有）
	
	
	
	private Double procardArea;//单产品占地面积  
	private Integer procardCengNum;//可叠放层数
	private Double  actualUsedProcardArea;//实际占地面积
	
	//页面传值使用
	private String t1;
	private String t2;
	private String t3;
	private String fatherMarkId;//父零件件号
	private String fatherbanben;//父零件版本
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStandardSize() {
		return standardSize;
	}

	public void setStandardSize(String standardSize) {
		this.standardSize = standardSize;
	}

	public String getErrorRange() {
		return errorRange;
	}

	public void setErrorRange(String errorRange) {
		this.errorRange = errorRange;
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

	public void setProName(String proName) {
		this.proName = proName;
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

	public String getActualFixed() {
		return actualFixed;
	}

	public void setActualFixed(String actualFixed) {
		this.actualFixed = actualFixed;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public String getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getProcardStyle() {
		return procardStyle;
	}

	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}

	public Float getLaborcost() {
		return laborcost;
	}

	public void setLaborcost(Float laborcost) {
		this.laborcost = laborcost;
	}

	public Float getFenpeiRate() {
		return fenpeiRate;
	}

	public void setFenpeiRate(Float fenpeiRate) {
		this.fenpeiRate = fenpeiRate;
	}

	public Float getDailyoutput() {
		return dailyoutput;
	}

	public void setDailyoutput(Float dailyoutput) {
		this.dailyoutput = dailyoutput;
	}

	public Double getOnePrice() {
		if (onePrice != null) {
			return Double.parseDouble(String.format("%.4f", onePrice));
		}
		return onePrice;
	}

	public void setOnePrice(Double onePrice) {
		this.onePrice = onePrice;
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

	public String getClClass() {
		return clClass;
	}

	public void setClClass(String clClass) {
		this.clClass = clClass;
	}


	public String getStatus() {
		return status;
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

	public Float getDeliveryDuration() {
		return deliveryDuration;
	}

	public void setDeliveryDuration(Float deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
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

	public Float getNeedCapacity() {
		return needCapacity;
	}

	public void setNeedCapacity(Float needCapacity) {
		this.needCapacity = needCapacity;
	}

	public String getDanjiaojian() {
		return danjiaojian;
	}

	public void setDanjiaojian(String danjiaojian) {
		this.danjiaojian = danjiaojian;
	}

	public String getLingliaostatus() {
		return lingliaostatus;
	}

	public void setLingliaostatus(String lingliaostatus) {
		this.lingliaostatus = lingliaostatus;
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

	public Float getJianyanjiepai() {
		return jianyanjiepai;
	}

	public void setJianyanjiepai(Float jianyanjiepai) {
		this.jianyanjiepai = jianyanjiepai;
	}

	public String getJisuanStatus() {
		return jisuanStatus;
	}

	public void setJisuanStatus(String jisuanStatus) {
		this.jisuanStatus = jisuanStatus;
	}

	public Integer getNoNum() {
		return noNum;
	}

	public void setNoNum(Integer noNum) {
		this.noNum = noNum;
	}

	public String getNeedProcess() {
		return needProcess;
	}

	public void setNeedProcess(String needProcess) {
		this.needProcess = needProcess;
	}

	public Float getNeedNumber() {
		return needNumber;
	}

	public void setNeedNumber(Float needNumber) {
		this.needNumber = needNumber;
	}

	public Float getNeedZhoueqi() {
		return needZhoueqi;
	}

	public void setNeedZhoueqi(Float needZhoueqi) {
		this.needZhoueqi = needZhoueqi;
	}

	public String getLingliaoType() {
		return lingliaoType;
	}

	public void setLingliaoType(String lingliaoType) {
		this.lingliaoType = lingliaoType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getBianzhiName() {
		return bianzhiName;
	}

	public void setBianzhiName(String bianzhiName) {
		this.bianzhiName = bianzhiName;
	}

	public Integer getBianzhiId() {
		return bianzhiId;
	}

	public void setBianzhiId(Integer bianzhiId) {
		this.bianzhiId = bianzhiId;
	}

	public String getJiaoduiName() {
		return jiaoduiName;
	}

	public void setJiaoduiName(String jiaoduiName) {
		this.jiaoduiName = jiaoduiName;
	}

	public Integer getJiaoduiId() {
		return jiaoduiId;
	}

	public void setJiaoduiId(Integer jiaoduiId) {
		this.jiaoduiId = jiaoduiId;
	}

	public String getJiaoduiDate() {
		return jiaoduiDate;
	}

	public void setJiaoduiDate(String jiaoduiDate) {
		this.jiaoduiDate = jiaoduiDate;
	}

	public String getShenheName() {
		return shenheName;
	}

	public void setShenheName(String shenheName) {
		this.shenheName = shenheName;
	}

	public Integer getShenheId() {
		return shenheId;
	}

	public void setShenheId(Integer shenheId) {
		this.shenheId = shenheId;
	}

	public String getPizhunName() {
		return pizhunName;
	}

	public void setPizhunName(String pizhunName) {
		this.pizhunName = pizhunName;
	}

	public Integer getPizhunId() {
		return pizhunId;
	}

	public void setPizhunId(Integer pizhunId) {
		this.pizhunId = pizhunId;
	}

	public String getFachuDate() {
		return fachuDate;
	}

	public void setFachuDate(String fachuDate) {
		this.fachuDate = fachuDate;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}


	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getJiagongId() {
		return jiagongId;
	}

	public void setJiagongId(Integer jiagongId) {
		this.jiagongId = jiagongId;
	}

	public String getJiagongName() {
		return jiagongName;
	}

	public void setJiagongName(String jiagongName) {
		this.jiagongName = jiagongName;
	}

	public Integer getPinzhiId() {
		return pinzhiId;
	}

	public void setPinzhiId(Integer pinzhiId) {
		this.pinzhiId = pinzhiId;
	}

	public String getPinzhiName() {
		return pinzhiName;
	}

	public void setPinzhiName(String pinzhiName) {
		this.pinzhiName = pinzhiName;
	}


	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getBzStatus() {
		return bzStatus;
	}

	public void setBzStatus(String bzStatus) {
		this.bzStatus = bzStatus;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getBianzhiDate() {
		return bianzhiDate;
	}

	public void setBianzhiDate(String bianzhiDate) {
		this.bianzhiDate = bianzhiDate;
	}

	public String getShenheDate() {
		return shenheDate;
	}

	public void setShenheDate(String shenheDate) {
		this.shenheDate = shenheDate;
	}

	public String getPizhunDate() {
		return pizhunDate;
	}

	public void setPizhunDate(String pizhunDate) {
		this.pizhunDate = pizhunDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getJiagongDate() {
		return jiagongDate;
	}

	public void setJiagongDate(String jiagongDate) {
		this.jiagongDate = jiagongDate;
	}

	public String getPinzhiDate() {
		return pinzhiDate;
	}

	public void setPinzhiDate(String pinzhiDate) {
		this.pinzhiDate = pinzhiDate;
	}

	public String getQianmingDate() {
		return qianmingDate;
	}

	public void setQianmingDate(String qianmingDate) {
		this.qianmingDate = qianmingDate;
	}

	public String getNumb() {
		return numb;
	}

	public void setNumb(String numb) {
		this.numb = numb;
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

	public String getLoadMarkId() {
		return loadMarkId;
	}

	public void setLoadMarkId(String loadMarkId) {
		this.loadMarkId = loadMarkId;
	}

	public String getBanBenNumber() {
		return banBenNumber;
	}

	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}

	public String getCheckMsg() {
		return checkMsg;
	}

	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	public Float getXiaohaoCount() {
		return xiaohaoCount;
	}

	public void setXiaohaoCount(Float xiaohaoCount) {
		this.xiaohaoCount = xiaohaoCount;
	}
	
	public boolean isDanJiaojian(){
		if(this.procardStyle!=null&&this.procardStyle.equals("总成")&&this.danjiaojian!=null&&this.danjiaojian.equals("单交件")){
			return true;
		}
		return false;
	}
	/**
	 * 计算原材料消耗值
	 * @param type 0不领料时返回true否则返回不领料,不计算
	 * @return
	 */
	public String sumXiaoHaoCount(Integer type){
		if(this.procardStyle==null||(!isDanJiaojian()&&this.procardStyle.equals("总成"))||(!this.procardStyle.equals("自制")&&!this.procardStyle.equals("组合"))){
			if(type==0){
				return "true";
			}
			return "非组合或自制,不计算";
		}
		if(this.lingliaostatus!=null&&this.lingliaostatus.equals("否")){
			if(type==0){
				return "true";
			}
			return "不领料,不计算";
		}
//		if(trademark==null||trademark.length()==0){
//			quanzi1=null;
//			quanzi2=null;
//			xiaohaoCount=null;
//			return "true";
//		}
		if(maxCount!=null&&maxCount>0){
			if(quanzi1!=null&&quanzi1>0&&quanzi2!=null&&quanzi2>0){
				this.xiaohaoCount=maxCount*quanzi2/quanzi1;
				return "true";
			}else {
				xiaohaoCount=null;
				return "原材权值不正确";
			}
		}else{
			return "没有最大数量";
		}
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
    public String getBanbenStatus() {
		return banbenStatus;
	}

	public void setBanbenStatus(String banbenStatus) {
		this.banbenStatus = banbenStatus;
	}

	public String getCaizhi() {
		return caizhi;
	}

	public void setCaizhi(String caizhi) {
		this.caizhi = caizhi;
	}

	public String getJgyl() {
		return jgyl;
	}

	public void setJgyl(String jgyl) {
		this.jgyl = jgyl;
	}

	public String getNewBanBenNumber() {
		return newBanBenNumber;
	}

	public void setNewBanBenNumber(String newBanBenNumber) {
		this.newBanBenNumber = newBanBenNumber;
	}

	public Float getCardXiaoHao() {
		return cardXiaoHao;
	}

	public void setCardXiaoHao(Float cardXiaoHao) {
		this.cardXiaoHao = cardXiaoHao;
	}

	public String getYuanName() {
		return yuanName;
	}

	public void setYuanName(String yuanName) {
		this.yuanName = yuanName;
	}

	public String getClType() {
		return clType;
	}

	public void setClType(String clType) {
		this.clType = clType;
	}

	public String getBiaochu() {
		return biaochu;
	}

	public void setBiaochu(String biaochu) {
		this.biaochu = biaochu;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	
	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getYtuhao() {
		return ytuhao;
	}

	public void setYtuhao(String ytuhao) {
		this.ytuhao = ytuhao;
	}

	/**
	 * 判断是否为组合有原材料
	 * @return
	 */
	public boolean isZhHasYcl(){
		if(procardStyle!=null&&procardStyle.equals("组合")&&trademark!=null&&trademark.length()>0){
			return true;
		}else{
			return false;
		}
	}

	public Float getBili() {
		return bili;
	}

	public void setBili(Float bili) {
		this.bili = bili;
	}

	public String getOldProcardStyle() {
		return oldProcardStyle;
	}

	public void setOldProcardStyle(String oldProcardStyle) {
		this.oldProcardStyle = oldProcardStyle;
	}

	public String getSbStatus() {
		return sbStatus;
	}

	public void setSbStatus(String sbStatus) {
		this.sbStatus = sbStatus;
	}


	public String getUpRemark() {
		return upRemark;
	}

	public void setUpRemark(String upRemark) {
		this.upRemark = upRemark;
	}

	public String getHwdrMarkId() {
		return hwdrMarkId;
	}

	public void setHwdrMarkId(String hwdrMarkId) {
		this.hwdrMarkId = hwdrMarkId;
	}

	public String getBomApplyStatus() {
		return bomApplyStatus;
	}

	public void setBomApplyStatus(String bomApplyStatus) {
		this.bomApplyStatus = bomApplyStatus;
	}

	public Integer getEpId2() {
		return epId2;
	}

	public void setEpId2(Integer epId2) {
		this.epId2 = epId2;
	}

	public String getJiaoduiName2() {
		return jiaoduiName2;
	}

	public void setJiaoduiName2(String jiaoduiName2) {
		this.jiaoduiName2 = jiaoduiName2;
	}

	public Integer getJiaoduiId2() {
		return jiaoduiId2;
	}

	public void setJiaoduiId2(Integer jiaoduiId2) {
		this.jiaoduiId2 = jiaoduiId2;
	}

	public String getJiaoduiDate2() {
		return jiaoduiDate2;
	}

	public void setJiaoduiDate2(String jiaoduiDate2) {
		this.jiaoduiDate2 = jiaoduiDate2;
	}

	public String getShenheName2() {
		return shenheName2;
	}

	public void setShenheName2(String shenheName2) {
		this.shenheName2 = shenheName2;
	}

	public Integer getShenheId2() {
		return shenheId2;
	}

	public void setShenheId2(Integer shenheId2) {
		this.shenheId2 = shenheId2;
	}

	public String getShenheDate2() {
		return shenheDate2;
	}

	public void setShenheDate2(String shenheDate2) {
		this.shenheDate2 = shenheDate2;
	}

	public String getPizhunName2() {
		return pizhunName2;
	}

	public void setPizhunName2(String pizhunName2) {
		this.pizhunName2 = pizhunName2;
	}

	public Integer getPizhunId2() {
		return pizhunId2;
	}

	public void setPizhunId2(Integer pizhunId2) {
		this.pizhunId2 = pizhunId2;
	}

	public String getPizhunDate2() {
		return pizhunDate2;
	}

	public void setPizhunDate2(String pizhunDate2) {
		this.pizhunDate2 = pizhunDate2;
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

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public String getIsycl() {
		return isycl;
	}

	public void setIsycl(String isycl) {
		this.isycl = isycl;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public String getAddcode() {
		return addcode;
	}

	public void setAddcode(String addcode) {
		this.addcode = addcode;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Float getSunhao() {
		return sunhao;
	}

	public void setSunhao(Float sunhao) {
		this.sunhao = sunhao;
	}

	public String getZzjihuo() {
		return zzjihuo;
	}

	public void setZzjihuo(String zzjihuo) {
		this.zzjihuo = zzjihuo;
	}

	public Integer getBeforeTrid() {
		return beforeTrid;
	}
	public String getDaoruDate() {
		return daoruDate;
	}
	public void setBeforeTrid(Integer beforeTrid) {
		this.beforeTrid = beforeTrid;
	}

	public Integer getCansb() {
		return cansb;
	}

	public void setCansb(Integer cansb) {
		this.cansb = cansb;
	}

	public void setDaoruDate(String daoruDate) {
		this.daoruDate = daoruDate;
	}

	public String getChangeProcess() {
		return changeProcess;
	}

	public void setChangeProcess(String changeProcess) {
		this.changeProcess = changeProcess;
	}

	public String getChangeTz() {
		return changeTz;
	}

	public void setChangeTz(String changeTz) {
		this.changeTz = changeTz;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getHasChange() {
		return hasChange;
	}

	public void setHasChange(String hasChange) {
		this.hasChange = hasChange;
	}

	public String getSbmsg() {
		return sbmsg;
	}

	public void setSbmsg(String sbmsg) {
		this.sbmsg = sbmsg;
	}

	public ProcardTemplateBanBen getPtbb() {
		return ptbb;
	}

	public void setPtbb(ProcardTemplateBanBen ptbb) {
		this.ptbb = ptbb;
	}

	public void setProcardArea(Double procardArea) {
		this.procardArea = procardArea;
	}

	public Double getProcardArea() {
		return procardArea;
	}

	public void setProcardCengNum(Integer procardCengNum) {
		this.procardCengNum = procardCengNum;
	}

	public Integer getProcardCengNum() {
		return procardCengNum;
	}

	public void setActualUsedProcardArea(Double actualUsedProcardArea) {
		this.actualUsedProcardArea = actualUsedProcardArea;
	}

	public Double getActualUsedProcardArea() {
		return actualUsedProcardArea;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public Integer getAvgProductionTakt() {
		return avgProductionTakt;
	}

	public void setAvgProductionTakt(Integer avgProductionTakt) {
		this.avgProductionTakt = avgProductionTakt;
	}

	public Float getAvgDeliveryTime() {
		return avgDeliveryTime;
	}

	public void setAvgDeliveryTime(Float avgDeliveryTime) {
		this.avgDeliveryTime = avgDeliveryTime;
	}

	public Float getCgperiod() {
		return cgperiod;
	}

	public void setCgperiod(Float cgperiod) {
		this.cgperiod = cgperiod;
	}
	
	public static Set<ProcardTemplatesbChangeLogDetail> diffrentTwoPt(ProcardTemplatesb oldpt,ProcardTemplatesb newpt,ProcardTemplatesbChangeLog changeLog){
		if(oldpt==null){
			return null;
		}
		if(newpt==null){
			return null;
		}
		Set<ProcardTemplatesbChangeLogDetail> logDetailSet = new HashSet<ProcardTemplatesbChangeLogDetail>();
		Users user= Util.getLoginUser();
		String newtime = Util.getDateTime();
		 diffrentsx(logDetailSet,oldpt.getMarkId(), newpt.getMarkId(), "件号", newtime, user, changeLog,0);
		 diffrentsx(logDetailSet,oldpt.getYwMarkId(), newpt.getYwMarkId(), "业务件号", newtime, user, changeLog,1);
		 diffrentsx(logDetailSet,oldpt.getBanBenNumber(), newpt.getBanBenNumber(), "版本号", newtime, user, changeLog,2);
		 diffrentsx(logDetailSet,oldpt.getProName(), newpt.getProName(), "名称", newtime, user, changeLog,3);
		 diffrentsx(logDetailSet,oldpt.getSpecification(), newpt.getSpecification(), " 规格", newtime, user, changeLog,4);
		 diffrentsx(logDetailSet,oldpt.getCaizhi(), newpt.getCaizhi(), "材质", newtime, user, changeLog,5);
		 diffrentsx(logDetailSet,oldpt.getCorrCount(), newpt.getCorrCount(), "权值", newtime, user, changeLog,6);
		 diffrentsx(logDetailSet,oldpt.getQuanzi1(), newpt.getQuanzi1(), "权值1", newtime, user, changeLog,6);
		 diffrentsx(logDetailSet,oldpt.getQuanzi2(), newpt.getQuanzi2(), "权值2", newtime, user, changeLog,6);
		 diffrentsx(logDetailSet,oldpt.getUnit(), newpt.getUnit(), "单位", newtime, user, changeLog,7);
		 diffrentsx(logDetailSet,oldpt.getProcardStyle(), newpt.getProcardStyle(), "零件类型", newtime, user, changeLog,8);
		 diffrentsx(logDetailSet,oldpt.getWgType(), newpt.getWgType(), "物料类别", newtime, user, changeLog,9);
		 diffrentsx(logDetailSet,oldpt.getKgliao(), newpt.getKgliao(), "供料属性", newtime, user, changeLog,10);
		 diffrentsx(logDetailSet,oldpt.getZzjihuo(), newpt.getZzjihuo(), "自制件激活模式", newtime, user, changeLog,11);
		 diffrentsx(logDetailSet,oldpt.getJihuoType(), newpt.getJihuoType(), "激活类型", newtime, user, changeLog,12);
		 diffrentsx(logDetailSet,oldpt.getClType(), newpt.getClType(), "材料类型", newtime, user, changeLog,13);
		 diffrentsx(logDetailSet,oldpt.getBiaochu(), newpt.getBiaochu(), "表处", newtime, user, changeLog,14);
		 diffrentsx(logDetailSet,oldpt.getTuhao(), newpt.getTuhao(), "图号", newtime, user, changeLog,15);
		 diffrentsx(logDetailSet,oldpt.getShowSize(), newpt.getShowSize(), "尺寸", newtime, user, changeLog,16);
		 diffrentsx(logDetailSet,oldpt.getLingliaostatus(), newpt.getLingliaostatus(), "是否领料", newtime, user, changeLog,17);
		 diffrentsx(logDetailSet,oldpt.getNeedProcess(), newpt.getNeedProcess(), "外购件是否半成品", newtime, user, changeLog,18);
		 diffrentsx(logDetailSet,oldpt.getLingliaoType(), newpt.getLingliaoType(), "领料方式", newtime, user, changeLog,19);
		 diffrentsx(logDetailSet,oldpt.getThisLength(), newpt.getThisLength(), "长", newtime, user, changeLog,20);
		 diffrentsx(logDetailSet,oldpt.getThisWidth(), newpt.getThisWidth(), "宽", newtime, user, changeLog,21);
		 diffrentsx(logDetailSet,oldpt.getThisHight(), newpt.getThisHight(), "高", newtime, user, changeLog,22);
		 diffrentsx(logDetailSet,oldpt.getCalculateType(), newpt.getCalculateType(), "计算方式", newtime, user, changeLog,23);
		 diffrentsx(logDetailSet,oldpt.getImportance(), newpt.getImportance(), "重要度", newtime, user, changeLog,24);
		
		return logDetailSet;
	}
	
	public static ProcardTemplatesbChangeLogDetail diffrentsx(Set<ProcardTemplatesbChangeLogDetail> logDetailSet,String oldValue,String newValue,String sxName,String newtime,Users user,ProcardTemplatesbChangeLog changeLog,Integer xuhao){
		if(!Util.isEquals(oldValue,newValue)){
			ProcardTemplatesbChangeLogDetail detail = new ProcardTemplatesbChangeLogDetail();
			detail.setSxName(sxName);
			detail.setOldValue(oldValue);
			detail.setNewValue(newValue);
			detail.setXuhao(xuhao);
			if(changeLog!=null){
				detail.setAddTime(newtime);
				detail.setAddUserId(user.getId());
				detail.setAddUsercode(user.getCode());
				detail.setAddUsername(user.getName());
				detail.setChangeLog(changeLog);
			}
			logDetailSet.add(detail);
			return detail;
		}
		return null;
	}
	public static ProcardTemplatesbChangeLogDetail diffrentsx(Set<ProcardTemplatesbChangeLogDetail> logDetailSet,Integer oldValue,Integer newValue,String sxName,String newtime,Users user,ProcardTemplatesbChangeLog changeLog,Integer xuhao){
		if(!Util.isEquals(oldValue,newValue)){
			ProcardTemplatesbChangeLogDetail detail = new ProcardTemplatesbChangeLogDetail();
			detail.setSxName(sxName);
			detail.setOldValue(oldValue+"");
			detail.setNewValue(newValue+"");
			detail.setXuhao(xuhao);
			if(changeLog!=null){
				detail.setAddTime(newtime);
				detail.setAddUserId(user.getId());
				detail.setAddUsercode(user.getCode());
				detail.setAddUsername(user.getName());
				detail.setChangeLog(changeLog);
			}
			logDetailSet.add(detail);
			return detail;
		}
		return null;
	}
	public static ProcardTemplatesbChangeLogDetail diffrentsx(Set<ProcardTemplatesbChangeLogDetail> logDetailSet,Float oldValue,Float newValue,String sxName,String newtime,Users user,ProcardTemplatesbChangeLog changeLog,Integer xuhao){
		if(!Util.isEquals(oldValue,newValue)){
			ProcardTemplatesbChangeLogDetail detail = new ProcardTemplatesbChangeLogDetail();
			detail.setSxName(sxName);
			detail.setOldValue(oldValue+"");
			detail.setNewValue(newValue+"");
			detail.setXuhao(xuhao);
			if(changeLog!=null){
				detail.setAddTime(newtime);
				detail.setAddUserId(user.getId());
				detail.setAddUsercode(user.getCode());
				detail.setAddUsername(user.getName());
				detail.setChangeLog(changeLog);
			}
			logDetailSet.add(detail);
			return detail;
		}
		return null;
	}
	public static ProcardTemplatesbChangeLogDetail diffrentsx(Set<ProcardTemplatesbChangeLogDetail> logDetailSet,Double oldValue,Double newValue,String sxName,String newtime,Users user,ProcardTemplatesbChangeLog changeLog,Integer xuhao){
		if(!Util.isEquals(oldValue,newValue)){
			ProcardTemplatesbChangeLogDetail detail = new ProcardTemplatesbChangeLogDetail();
			detail.setSxName(sxName);
			detail.setOldValue(oldValue+"");
			detail.setNewValue(newValue+"");
			detail.setXuhao(xuhao);
			if(changeLog!=null){
				detail.setAddTime(newtime);
				detail.setAddUserId(user.getId());
				detail.setAddUsercode(user.getCode());
				detail.setAddUsername(user.getName());
				detail.setChangeLog(changeLog);
			}
			logDetailSet.add(detail);
			return detail;
		}
		return null;
	}

	public Integer getSunhaoType() {
		return sunhaoType;
	}

	public void setSunhaoType(Integer sunhaoType) {
		this.sunhaoType = sunhaoType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsPeiJian() {
		return isPeiJian;
	}

	public void setIsPeiJian(String isPeiJian) {
		this.isPeiJian = isPeiJian;
	}

	public Integer getSbId() {
		return sbId;
	}

	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}

	public String getTq() {
		return tq;
	}

	public void setTq(String tq) {
		this.tq = tq;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}
	@JSONField(serialize = false)
	public ProcardTemplatesb getProcardTemplatesb() {
		return procardTemplatesb;
	}

	public void setProcardTemplatesb(ProcardTemplatesb procardTemplatesb) {
		this.procardTemplatesb = procardTemplatesb;
	}
	@JSONField(serialize = false)
	public Set<ProcardTemplatesb> getProcardsbTSet() {
		return procardsbTSet;
	}

	public void setProcardsbTSet(Set<ProcardTemplatesb> procardsbTSet) {
		this.procardsbTSet = procardsbTSet;
	}
	@JSONField(serialize = false)
	public Set<ProcessTemplatesb> getProcessTemplatesb() {
		return processTemplatesb;
	}

	public void setProcessTemplatesb(Set<ProcessTemplatesb> processTemplatesb) {
		this.processTemplatesb = processTemplatesb;
	}

	public Integer getSbApplyId() {
		return sbApplyId;
	}

	public void setSbApplyId(Integer sbApplyId) {
		this.sbApplyId = sbApplyId;
	}


	public Integer getOldPtId() {
		return oldPtId;
	}

	public void setOldPtId(Integer oldPtId) {
		this.oldPtId = oldPtId;
	}

	public String getIssb() {
		return issb;
	}

	public void setIssb(String issb) {
		this.issb = issb;
	}

	public String getSbApplyStatus() {
		return sbApplyStatus;
	}

	public void setSbApplyStatus(String sbApplyStatus) {
		this.sbApplyStatus = sbApplyStatus;
	}

	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}

	public String getGxxg() {
		return gxxg;
	}

	public void setGxxg(String gxxg) {
		this.gxxg = gxxg;
	}

	public String getFatherMarkId() {
		return fatherMarkId;
	}

	public void setFatherMarkId(String fatherMarkId) {
		this.fatherMarkId = fatherMarkId;
	}

	public String getFatherbanben() {
		return fatherbanben;
	}

	public void setFatherbanben(String fatherbanben) {
		this.fatherbanben = fatherbanben;
	}

	public Integer getHistoryId2() {
		return historyId2;
	}

	public void setHistoryId2(Integer historyId2) {
		this.historyId2 = historyId2;
	}
	

	
}
