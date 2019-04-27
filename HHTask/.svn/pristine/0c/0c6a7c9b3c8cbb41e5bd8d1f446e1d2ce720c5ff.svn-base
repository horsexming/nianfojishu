package com.task.entity.sop;

/**
 * 外委(外采）产品申报单 （ta_sop_outSourcingApp）
 * 
 * @author 贾辉辉
 * 
 */
public class OutSourcingApp implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;// 主键
	/** 外委申报单信息 **/
	private String osaNO;// 外委申请编号
	private String code;// 外委人工号
	private String username;// 外委人
	private String dept;// 外委班组
	private String number;// 外委工作单编号（条码）
	private String osaSubmitTime;// 提交时间
	private String customer;// 客户名称
	private String status;// 状态
	private String executeStatus;// 执行状态(周期录入、原因录入、成本核算录入、自制新增成本录入、评审完成)
	private String explain;// 外委说明
	/** 产品基本信息 **/
	private String partName;// 零件名称
	private String markID;// 零件号
	private String processNO;// 工序号
	private String processName;// 工序名
	private Float deliveryCount;// 交付数量
	private String deliveryDate;// 交付时间
	private String isJiaji;// 是否加急
	private String lotId;// 批次号
	private Float outSourceCount;// 外委数量
	private Float receiveCount;// 接收数量
	private Float breakCount;// 损坏数量
	private String inforFile;// 基本信息文件
	// 新添
	private Float procardCycle;// 加工周期
	private Float productCysle;// 产品生命周期
	private Float yuceCount;// 预测产量
	private Float selefMaxBalanceCount;// 自制外委最大数量<预测产量
	private Float addChengMinBalanceCount;// 新增成本自制平衡数量 （最少不得低于数） <预测产量
	private String orderId;// 订单号
	/** 申报原因 **/
	private String timeLimit;// 时限（长期/短期）
	private String alertNum;// 报警单号
	private String machineFail;// 设备故障（多选[人为破坏,延迟修理,保养不力,违章操作,修理再故障,设备老化,其他]）
	private Float repairCycle;// 维修周期
	private Float repairBudget;// 维修预算
	private String machineFile;// 设备故障说明文件维修报价文件上传
	private String abilityLack;// 产能不足（多选[人员不足,没有设备,工艺限制,其他]）
	private String abilityLackFile;// 产能不足文件
	private String othersLack;// 其他原因（多选[自然灾害,事故]）
	private String othersLackFile;// 其他原因文件
	private String otherMore;// 原因描述
	/** 成本核算 **/
	private Float osOneRengong;// 外委单件人工
	private Float osOneMater;// 外委单件材料成本
	private Float osOneOthsers;// 外委单件其他
	private Float osOneHeji;// 外委单件合计===================================
	private String ospriceFile;// 外委报价上传文件--------------------------------------新增
	private String selfCode;// 自制人员工号
	private Float selfRenshu;// 自制所需人数
	private Float selfOneRengong;// 自制所需人工--------------------------------------自制所需人工
	//
	private String   paihao;//原材料牌号
	private String   guige;//原材料规格
	private String   danwei;//原材料单位
	private Float    jiage;//原材料价格
	private String   shuliang;//原材料数量
	
	// 元/月
	private Float selfOneMater;// 自制单件材料成本
	private Float selfOneOthsers;// 自制单件其他
	private Float selfOneHeji;// 自制单件合计================================
	private Float lotChae;// 批次差额 =================================
	private Float addWorker;// 添加人员
	private Float oneWorkerMoney;// 合计人员费用
	private Float addMachineCost;// 增加设备成本
	private Float zhejiuCost;// 设备折旧成本
	private String addNewMachineFile;// 新增设备报价文件------------------------------新增
	private Float recoryPeriod;// 回收期
	private Float addAssistWorker;// 辅助人数
	private Float oneAssistWorkerMoney;// 合计辅助人工成本
	private String othersCosts;// 其他成本描述
	private Float othersCost;// 其他成本数
	private String more;// 备注
	private Float machineFailAnalysis;// 设备故障盈亏分析
	private Float abilityLackAnalysis;// 产能不足盈亏分析
	
	private Integer rootId;//bom的rootId
	private Integer qpId;//bom的零件id
	private Integer qpinfoId;//bom的工序id

	private String per_status;//采购执行单状态标识
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOsaNO() {
		return osaNO;
	}

	public void setOsaNO(String osaNO) {
		this.osaNO = osaNO;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOsaSubmitTime() {
		return osaSubmitTime;
	}

	public void setOsaSubmitTime(String osaSubmitTime) {
		this.osaSubmitTime = osaSubmitTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getMarkID() {
		return markID;
	}

	public void setMarkID(String markID) {
		this.markID = markID;
	}

	public String getProcessNO() {
		return processNO;
	}

	public void setProcessNO(String processNO) {
		this.processNO = processNO;
	}

	public Float getDeliveryCount() {
		return deliveryCount;
	}

	public void setDeliveryCount(Float deliveryCount) {
		this.deliveryCount = deliveryCount;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getIsJiaji() {
		return isJiaji;
	}

	public void setIsJiaji(String isJiaji) {
		this.isJiaji = isJiaji;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public Float getOutSourceCount() {
		return outSourceCount;
	}

	public void setOutSourceCount(Float outSourceCount) {
		this.outSourceCount = outSourceCount;
	}

	public Float getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Float receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Float getBreakCount() {
		return breakCount;
	}

	public void setBreakCount(Float breakCount) {
		this.breakCount = breakCount;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getMachineFail() {
		return machineFail;
	}

	public void setMachineFail(String machineFail) {
		this.machineFail = machineFail;
	}

	public Float getRepairCycle() {
		return repairCycle;
	}

	public void setRepairCycle(Float repairCycle) {
		this.repairCycle = repairCycle;
	}

	public Float getRepairBudget() {
		return repairBudget;
	}

	public void setRepairBudget(Float repairBudget) {
		this.repairBudget = repairBudget;
	}

	public String getMachineFile() {
		return machineFile;
	}

	public void setMachineFile(String machineFile) {
		this.machineFile = machineFile;
	}

	public String getAbilityLack() {
		return abilityLack;
	}

	public void setAbilityLack(String abilityLack) {
		this.abilityLack = abilityLack;
	}

	public String getAbilityLackFile() {
		return abilityLackFile;
	}

	public void setAbilityLackFile(String abilityLackFile) {
		this.abilityLackFile = abilityLackFile;
	}

	public String getOthersLack() {
		return othersLack;
	}

	public void setOthersLack(String othersLack) {
		this.othersLack = othersLack;
	}

	public String getOthersLackFile() {
		return othersLackFile;
	}

	public void setOthersLackFile(String othersLackFile) {
		this.othersLackFile = othersLackFile;
	}

	public Float getOsOneRengong() {
		return osOneRengong;
	}

	public void setOsOneRengong(Float osOneRengong) {
		this.osOneRengong = osOneRengong;
	}

	public Float getOsOneMater() {
		return osOneMater;
	}

	public void setOsOneMater(Float osOneMater) {
		this.osOneMater = osOneMater;
	}

	public Float getOsOneOthsers() {
		return osOneOthsers;
	}

	public void setOsOneOthsers(Float osOneOthsers) {
		this.osOneOthsers = osOneOthsers;
	}

	public Float getOsOneHeji() {
		if (null != osOneHeji) {
			return Float.parseFloat(String.format("%.2f", osOneHeji));
		}
		return osOneHeji;
	}

	public void setOsOneHeji(Float osOneHeji) {
		this.osOneHeji = osOneHeji;
	}

	public Float getSelfOneRengong() {
		return selfOneRengong;
	}

	public void setSelfOneRengong(Float selfOneRengong) {
		this.selfOneRengong = selfOneRengong;
	}

	public Float getSelfOneMater() {
		return selfOneMater;
	}

	public void setSelfOneMater(Float selfOneMater) {
		this.selfOneMater = selfOneMater;
	}

	public Float getSelfOneOthsers() {
		return selfOneOthsers;
	}

	public void setSelfOneOthsers(Float selfOneOthsers) {
		this.selfOneOthsers = selfOneOthsers;
	}

	public Float getSelfOneHeji() {
		if (null != selfOneHeji) {
			return Float.parseFloat(String.format("%.2f", selfOneHeji));
		}
		return selfOneHeji;
	}

	public void setSelfOneHeji(Float selfOneHeji) {
		this.selfOneHeji = selfOneHeji;
	}

	public Float getLotChae() {
		return lotChae;
	}

	public void setLotChae(Float lotChae) {
		this.lotChae = lotChae;
	}

	public Float getAddWorker() {
		return addWorker;
	}

	public void setAddWorker(Float addWorker) {
		this.addWorker = addWorker;
	}

	public Float getOneWorkerMoney() {
		return oneWorkerMoney;
	}

	public void setOneWorkerMoney(Float oneWorkerMoney) {
		this.oneWorkerMoney = oneWorkerMoney;
	}

	public Float getAddMachineCost() {
		return addMachineCost;
	}

	public void setAddMachineCost(Float addMachineCost) {
		this.addMachineCost = addMachineCost;
	}

	public Float getZhejiuCost() {
		if (null != zhejiuCost) {
			return Float.parseFloat(String.format("%.2f", zhejiuCost));
		}
		return zhejiuCost;
	}

	public void setZhejiuCost(Float zhejiuCost) {

		this.zhejiuCost = zhejiuCost;
	}

	public Float getRecoryPeriod() {
		return recoryPeriod;
	}

	public void setRecoryPeriod(Float recoryPeriod) {
		this.recoryPeriod = recoryPeriod;
	}

	public Float getAddAssistWorker() {
		return addAssistWorker;
	}

	public void setAddAssistWorker(Float addAssistWorker) {
		this.addAssistWorker = addAssistWorker;
	}

	public Float getOneAssistWorkerMoney() {
		if (null != oneAssistWorkerMoney) {
			return Float
					.parseFloat(String.format("%.2f", oneAssistWorkerMoney));
		}
		return oneAssistWorkerMoney;
	}

	public void setOneAssistWorkerMoney(Float oneAssistWorkerMoney) {
		this.oneAssistWorkerMoney = oneAssistWorkerMoney;
	}

	public String getOthersCosts() {
		return othersCosts;
	}

	public void setOthersCosts(String othersCosts) {
		this.othersCosts = othersCosts;
	}

	public Float getOthersCost() {
		return othersCost;
	}

	public void setOthersCost(Float othersCost) {
		this.othersCost = othersCost;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Float getMachineFailAnalysis() {
		if (null != machineFailAnalysis) {
			return Float.parseFloat(String.format("%.2f", machineFailAnalysis));
		}
		return machineFailAnalysis;
	}

	public void setMachineFailAnalysis(Float machineFailAnalysis) {
		this.machineFailAnalysis = machineFailAnalysis;
	}

	public Float getAbilityLackAnalysis() {
		if (null != abilityLackAnalysis) {
			return Float.parseFloat(String.format("%.2f", abilityLackAnalysis));
		}
		return abilityLackAnalysis;
	}

	public void setAbilityLackAnalysis(Float abilityLackAnalysis) {
		this.abilityLackAnalysis = abilityLackAnalysis;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getInforFile() {
		return inforFile;
	}

	public void setInforFile(String inforFile) {
		this.inforFile = inforFile;
	}

	public String getAlertNum() {
		return alertNum;
	}

	public void setAlertNum(String alertNum) {
		this.alertNum = alertNum;
	}

	public String getOtherMore() {
		return otherMore;
	}

	public void setOtherMore(String otherMore) {
		this.otherMore = otherMore;
	}

	public Float getProcardCycle() {
		return procardCycle;
	}

	public void setProcardCycle(Float procardCycle) {
		this.procardCycle = procardCycle;
	}

	public Float getProductCysle() {
		return productCysle;
	}

	public void setProductCysle(Float productCysle) {
		this.productCysle = productCysle;
	}

	public Float getYuceCount() {
		return yuceCount;
	}

	public void setYuceCount(Float yuceCount) {
		this.yuceCount = yuceCount;
	}

	public Float getSelefMaxBalanceCount() {
		if (null != selefMaxBalanceCount) {
			return Float
					.parseFloat(String.format("%.2f", selefMaxBalanceCount));
		}
		return selefMaxBalanceCount;
	}

	public void setSelefMaxBalanceCount(Float selefMaxBalanceCount) {
		this.selefMaxBalanceCount = selefMaxBalanceCount;
	}

	public Float getAddChengMinBalanceCount() {
		if (null != addChengMinBalanceCount) {
			return Float.parseFloat(String.format("%.0f",
					addChengMinBalanceCount));
		}
		return addChengMinBalanceCount;
	}

	public void setAddChengMinBalanceCount(Float addChengMinBalanceCount) {
		this.addChengMinBalanceCount = addChengMinBalanceCount;
	}

	public String getOspriceFile() {
		return ospriceFile;
	}

	public void setOspriceFile(String ospriceFile) {
		this.ospriceFile = ospriceFile;
	}

	public String getAddNewMachineFile() {
		return addNewMachineFile;
	}

	public void setAddNewMachineFile(String addNewMachineFile) {
		this.addNewMachineFile = addNewMachineFile;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Float getSelfRenshu() {
		return selfRenshu;
	}

	public void setSelfRenshu(Float selfRenshu) {
		this.selfRenshu = selfRenshu;
	}

	public String getSelfCode() {
		return selfCode;
	}

	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}

	public String getPaihao() {
		return paihao;
	}

	public void setPaihao(String paihao) {
		this.paihao = paihao;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public Float getJiage() {
		return jiage;
	}

	public void setJiage(Float jiage) {
		this.jiage = jiage;
	}

	public String getShuliang() {
		return shuliang;
	}

	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public Integer getQpId() {
		return qpId;
	}

	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}

	public Integer getQpinfoId() {
		return qpinfoId;
	}

	public void setQpinfoId(Integer qpinfoId) {
		this.qpinfoId = qpinfoId;
	}

	public String getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getPer_status() {
		return per_status;
	}

	public void setPer_status(String perStatus) {
		per_status = perStatus;
	}

	
	
}
