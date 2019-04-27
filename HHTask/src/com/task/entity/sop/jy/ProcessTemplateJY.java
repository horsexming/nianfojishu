package com.task.entity.sop.jy;


import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 工序模板(表名:ta_sop_w_ProcessTemplateJY)
 * 
 * @author jhh
 * 
 */
public class ProcessTemplateJY  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键
	private Integer processNO;// 工序号
	private String processName;// 工序名
	private String processStatus;// 状态(并行/单独)
	private Integer parallelId;// 并行工序开始id

	/***************** 设备及其他相关 *********/
	private String productStyle;// 生产类型（自制，外委）
	private String isPrice;// 是否参与奖金分配(true/false)
	private String zjStatus;// 是否首检

	private String gongwei; // 工位*(多个工位","分割)
	private Integer shebeiId;// 设备Id
	private String shebeiNo; // 设备编号*
	private String shebeiName; // 设备名称

	private String operatorDept;// 操作人部门***
	private String operatorCode;// 操作人工号***
	private String operatorCardId;// 操作人卡号***
	private String operatorName;// 操作人姓名***
	private Integer operatorUserId;// 操作人id***

	/*
	 * 操作指数
	 */
	private Float optechnologyRate;// 技能指数
	private Float opCouldReplaceRate;// 可替换人数
	private Float opfuheRate;// 负荷指数
	private Float opcaozuojiepai;// 操作人工节拍
	private Float opshebeijiepai;// 操作设备节拍
	private Float opnoReplaceRate;// 操作不可替换系数
	private Float opzonghezhishu;// 操作综合指数
	private Float opzongheqiangdu;// 操作综合强度
	/*
	 * 工装调试指数 准备过程
	 */
	private Float gztechnologyRate;// 技术指数
	private Float gzCouldReplaceRate;// 可替换人数
	private Float gzfuheRate;// 负荷指数
	private Float gzzhunbeijiepai;// 准备过程人工节拍
	private Float gzzhunbeicishu;// 准备次数
	private Float gznoReplaceRate;// 准备过程不可替换系数
	private Float gzzonghezhishu;// 准备过程综合指数
	private Float gzzongheqiangdu;// 综合强度

	// 奖金计算相关
	private Double processMomey;// 工序单价
	private Float opjiaofu;// 交付数量
	/*** 量具 ***/
	private Integer measuringId;// 主键
	private String measuringNumber;// 编号
	private String measuringMatetag;// 名称
	private String measuring_no; // 本厂编号

	/*** 工装 ***/
	private Integer gzstoreId;// 工装Id
	private String number;// 工装编号
	private String matetag;// 工装名称
	/*** 精益生产相关 */
	private Float allJiepai;// 总节拍
	private Float capacity;// 产能(单班时长)
	private Float capacitySurplus;// 产能盈余
	private Float capacityRatio;// 产能比
	private Float alldeliveryDuration;// 总延误时长
	private Float deliveryDuration;// 配送时长
	private Float deliveryRatio;// 配送比
	private Integer deliveryPeriod;// 配送周期(X天/次)
	private Float deliveryAmount;// 送货量
	private Float proSingleDuration;// 总成单班生产时长

	private Integer zhuserId;// 供应商的Id
	private String gys;// 供应商

	/*** 设备验证、工装验证、量具验证、规范验证、考勤验证 ***/
	private String shebeistatus;// 设备验证
	private String gongzhuangstatus;// 工装验证
	private String liangjustatus;// 量具验证
	private String guifanstatus;// 规范验证
	private String kaoqingstatus;// 考勤验证

	private String contextJi;//机 改进方案
	private String contextGong;//工改进方案
	private String contextLiang;//量 改进方案
	private String contextJian;//检 改进方案
	private String contextWen;//文 改进方案
	private String contextRen;//人 改进方案
	private String fileName;//工艺规范图纸地址
	private String progressStatus;//临时使用不进入数据库
	private String markId;//件号临时使用不进入数据库
	/********** 对应关系 ********************/
	private ProcardTemplateJY procardTemplateJY;// 对应流水卡片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessNO() {
		return processNO;
	}

	public void setProcessNO(Integer processNO) {
		this.processNO = processNO;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public Integer getParallelId() {
		return parallelId;
	}

	public void setParallelId(Integer parallelId) {
		this.parallelId = parallelId;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getIsPrice() {
		return isPrice;
	}

	public void setIsPrice(String isPrice) {
		this.isPrice = isPrice;
	}

	public String getGongwei() {
		return gongwei;
	}

	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
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

	public String getOperatorDept() {
		return operatorDept;
	}

	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorCardId() {
		return operatorCardId;
	}

	public void setOperatorCardId(String operatorCardId) {
		this.operatorCardId = operatorCardId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public Float getOptechnologyRate() {
		return optechnologyRate;
	}

	public void setOptechnologyRate(Float optechnologyRate) {
		this.optechnologyRate = optechnologyRate;
	}

	public Float getOpCouldReplaceRate() {
		return opCouldReplaceRate;
	}

	public void setOpCouldReplaceRate(Float opCouldReplaceRate) {
		this.opCouldReplaceRate = opCouldReplaceRate;
	}

	public Float getOpfuheRate() {
		return opfuheRate;
	}

	public void setOpfuheRate(Float opfuheRate) {
		this.opfuheRate = opfuheRate;
	}

	public Float getOpcaozuojiepai() {
		return opcaozuojiepai;
	}

	public void setOpcaozuojiepai(Float opcaozuojiepai) {
		this.opcaozuojiepai = opcaozuojiepai;
	}

	public Float getOpshebeijiepai() {
		return opshebeijiepai;
	}

	public void setOpshebeijiepai(Float opshebeijiepai) {
		this.opshebeijiepai = opshebeijiepai;
	}

	public Float getOpnoReplaceRate() {
		return opnoReplaceRate;
	}

	public void setOpnoReplaceRate(Float opnoReplaceRate) {
		this.opnoReplaceRate = opnoReplaceRate;
	}

	public Float getOpzonghezhishu() {
		return opzonghezhishu;
	}

	public void setOpzonghezhishu(Float opzonghezhishu) {
		this.opzonghezhishu = opzonghezhishu;
	}

	public Float getOpzongheqiangdu() {
		return opzongheqiangdu;
	}

	public void setOpzongheqiangdu(Float opzongheqiangdu) {
		this.opzongheqiangdu = opzongheqiangdu;
	}

	public Float getGztechnologyRate() {
		return gztechnologyRate;
	}

	public void setGztechnologyRate(Float gztechnologyRate) {
		this.gztechnologyRate = gztechnologyRate;
	}

	public Float getGzCouldReplaceRate() {
		return gzCouldReplaceRate;
	}

	public void setGzCouldReplaceRate(Float gzCouldReplaceRate) {
		this.gzCouldReplaceRate = gzCouldReplaceRate;
	}

	public Float getGzfuheRate() {
		return gzfuheRate;
	}

	public void setGzfuheRate(Float gzfuheRate) {
		this.gzfuheRate = gzfuheRate;
	}

	public Float getGzzhunbeijiepai() {
		return gzzhunbeijiepai;
	}

	public void setGzzhunbeijiepai(Float gzzhunbeijiepai) {
		this.gzzhunbeijiepai = gzzhunbeijiepai;
	}

	public Float getGzzhunbeicishu() {
		return gzzhunbeicishu;
	}

	public void setGzzhunbeicishu(Float gzzhunbeicishu) {
		this.gzzhunbeicishu = gzzhunbeicishu;
	}

	public Float getGznoReplaceRate() {
		return gznoReplaceRate;
	}

	public void setGznoReplaceRate(Float gznoReplaceRate) {
		this.gznoReplaceRate = gznoReplaceRate;
	}

	public Float getGzzonghezhishu() {
		return gzzonghezhishu;
	}

	public void setGzzonghezhishu(Float gzzonghezhishu) {
		this.gzzonghezhishu = gzzonghezhishu;
	}

	public Float getGzzongheqiangdu() {
		return gzzongheqiangdu;
	}

	public void setGzzongheqiangdu(Float gzzongheqiangdu) {
		this.gzzongheqiangdu = gzzongheqiangdu;
	}

	public Double getProcessMomey() {
		if (processMomey != null) {
			return Double.parseDouble(String.format("%.4f", processMomey));
		}
		return processMomey;
	}

	public void setProcessMomey(Double processMomey) {
		this.processMomey = processMomey;
	}

    


	public Float getOpjiaofu() {
		return opjiaofu;
	}

	public void setOpjiaofu(Float opjiaofu) {
		this.opjiaofu = opjiaofu;
	}

	public String getZjStatus() {
		return zjStatus;
	}

	public void setZjStatus(String zjStatus) {
		this.zjStatus = zjStatus;
	}

	public Integer getGzstoreId() {
		return gzstoreId;
	}

	public void setGzstoreId(Integer gzstoreId) {
		this.gzstoreId = gzstoreId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMatetag() {
		return matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}

	public Integer getMeasuringId() {
		return measuringId;
	}

	public void setMeasuringId(Integer measuringId) {
		this.measuringId = measuringId;
	}

	public String getMeasuringNumber() {
		return measuringNumber;
	}

	public void setMeasuringNumber(String measuringNumber) {
		this.measuringNumber = measuringNumber;
	}

	public String getMeasuringMatetag() {
		return measuringMatetag;
	}

	public void setMeasuringMatetag(String measuringMatetag) {
		this.measuringMatetag = measuringMatetag;
	}

	public String getMeasuring_no() {
		return measuring_no;
	}

	public void setMeasuring_no(String measuringNo) {
		measuring_no = measuringNo;
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

	public String getShebeistatus() {
		return shebeistatus;
	}

	public void setShebeistatus(String shebeistatus) {
		this.shebeistatus = shebeistatus;
	}

	public String getGongzhuangstatus() {
		return gongzhuangstatus;
	}

	public void setGongzhuangstatus(String gongzhuangstatus) {
		this.gongzhuangstatus = gongzhuangstatus;
	}

	public String getLiangjustatus() {
		return liangjustatus;
	}

	public void setLiangjustatus(String liangjustatus) {
		this.liangjustatus = liangjustatus;
	}

	public String getGuifanstatus() {
		return guifanstatus;
	}

	public void setGuifanstatus(String guifanstatus) {
		this.guifanstatus = guifanstatus;
	}

	public String getKaoqingstatus() {
		return kaoqingstatus;
	}

	public void setKaoqingstatus(String kaoqingstatus) {
		this.kaoqingstatus = kaoqingstatus;
	}

	public Float getAllJiepai() {
		return allJiepai;
	}

	public void setAllJiepai(Float allJiepai) {
		this.allJiepai = allJiepai;
	}

	public Float getAlldeliveryDuration() {
		return alldeliveryDuration;
	}

	public void setAlldeliveryDuration(Float alldeliveryDuration) {
		this.alldeliveryDuration = alldeliveryDuration;
	}

	public Integer getShebeiId() {
		return shebeiId;
	}

	public void setShebeiId(Integer shebeiId) {
		this.shebeiId = shebeiId;
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

	@JSONField(serialize = false)
	public ProcardTemplateJY getProcardTemplateJY() {
		return procardTemplateJY;
	}

	public void setProcardTemplateJY(ProcardTemplateJY procardTemplateJY) {
		this.procardTemplateJY = procardTemplateJY;
	}

	public String getContextJi() {
		return contextJi;
	}

	public void setContextJi(String contextJi) {
		this.contextJi = contextJi;
	}

	public String getContextGong() {
		return contextGong;
	}

	public void setContextGong(String contextGong) {
		this.contextGong = contextGong;
	}

	public String getContextLiang() {
		return contextLiang;
	}

	public void setContextLiang(String contextLiang) {
		this.contextLiang = contextLiang;
	}

	public String getContextJian() {
		return contextJian;
	}

	public void setContextJian(String contextJian) {
		this.contextJian = contextJian;
	}

	public String getContextWen() {
		return contextWen;
	}

	public void setContextWen(String contextWen) {
		this.contextWen = contextWen;
	}

	public String getContextRen() {
		return contextRen;
	}

	public void setContextRen(String contextRen) {
		this.contextRen = contextRen;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
