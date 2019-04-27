package com.task.entity.sop;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.fin.BaoxiaoDan;

/****
 * 外委采购计划表
 * 
 * @author 刘培
 * @表名 ta_sop_w_WaigouWaiweiPlan
 * 
 */
public class WaigouWaiweiPlan implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String orderNum;//订单编号
	private String markId;// 件号
	private String rootMarkId;// 总成件号
	private String ywMarkId;// 总成件号
	private String proName;// 零件名称
	private String specification;// 规格
	private String processName;// 工序名称
	private String processNo;// 工序号
	private String type;// 计划类型(外购/外委)
	private Float beginCount;//初始数量
	private Float number;// 待激活数量
	private String kgliao;// //供料属性（外购件使用：是,否，null代表否）

	private String addTime;// 添加时间
	private String jihuoTime;// 激活时间
	private String shArrivalTime;// 应到货时间
	private String acArrivalTime;// 实际到货时间
	private String jianyanTime;// 检验时间
	private String rukuTime;// 入库时间

	private Integer priceId;//合同Id
	private Integer gysId;// 供应商id
	private Integer userId;// 供应商用户id
	private String userCode;// 供应商用户工号(编号)
	private String gysName;// 供应商名称
	private String gysPhone;// 供应商电话
	private String status;// 状态(待入库、待激活、待采购、待确认、生产中、送货中、检验中、入库)
	private String oldStatus;//原状态（设变关联后记录之前的status,处理完成后恢复）
	private String caigouMonth;// 采购月份
	private String planNumber;// 采购计划单号(wg201412001)/(ww201412001)
	private String selfCard;// 批次号
	private String rootSelfCard;//总成批次

	private Float singleDuration;// 单班时长(工作时长)
	private Float allJiepai;// 单件总节拍
	private Float deliveryDuration;// 延误时长

	private Integer procardId;// 对应流水单Id
	private Procard procard;// 对应流总成水单
	
	private Float zzRukCount;//在制品入库数量
	private String applyDate;// 领取时间
	private Float applyCount;// 已临数量
	private Integer rukuCount;// 入库次数
	private Integer comeCount;// 来货次数
	private Float keruku;// 可入库数量
	private Float hasruku;// 已入库数量
	private Integer oswId; // OutSourcingWorkList表的Id
	private Set<BaoxiaoDan> baoxiaodans;
	private Integer banci;// 版次
	private String banben;//版本
	private String hadChange;//与同供应商上次的订单相比是否有过设变
	private String unit;//单位
	private String bgMarkIds;//变更零件件号（页面显示）
//	private ProcardWGCenter pwc;//采购计划中间表
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getRootMarkId() {
		return rootMarkId;
	}

	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getJihuoTime() {
		return jihuoTime;
	}

	public void setJihuoTime(String jihuoTime) {
		this.jihuoTime = jihuoTime;
	}

	public String getShArrivalTime() {
		return shArrivalTime;
	}

	public void setShArrivalTime(String shArrivalTime) {
		this.shArrivalTime = shArrivalTime;
	}

	public String getAcArrivalTime() {
		return acArrivalTime;
	}

	public void setAcArrivalTime(String acArrivalTime) {
		this.acArrivalTime = acArrivalTime;
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

	public Integer getGysId() {
		return gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getGysName() {
		return gysName;
	}

	public void setGysName(String gysName) {
		this.gysName = gysName;
	}

	public String getGysPhone() {
		return gysPhone;
	}

	public void setGysPhone(String gysPhone) {
		this.gysPhone = gysPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaigouMonth() {
		return caigouMonth;
	}

	public void setCaigouMonth(String caigouMonth) {
		this.caigouMonth = caigouMonth;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getSelfCard() {
		return selfCard;
	}

	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

	public Float getSingleDuration() {
		return singleDuration;
	}

	public void setSingleDuration(Float singleDuration) {
		this.singleDuration = singleDuration;
	}

	public Float getAllJiepai() {
		return allJiepai;
	}

	public void setAllJiepai(Float allJiepai) {
		this.allJiepai = allJiepai;
	}

	public Float getDeliveryDuration() {
		return deliveryDuration;
	}

	public void setDeliveryDuration(Float deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}

	public Integer getProcardId() {
		return procardId;
	}

	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Float getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getRukuCount() {
		return rukuCount;
	}

	public void setRukuCount(Integer rukuCount) {
		this.rukuCount = rukuCount;
	}

	public Integer getComeCount() {
		return comeCount;
	}

	public void setComeCount(Integer comeCount) {
		this.comeCount = comeCount;
	}

	public Float getKeruku() {
		return keruku;
	}

	public void setKeruku(Float keruku) {
		this.keruku = keruku;
	}

	public Float getHasruku() {
		return hasruku;
	}

	public void setHasruku(Float hasruku) {
		this.hasruku = hasruku;
	}

	public Integer getOswId() {
		return oswId;
	}

	public void setOswId(Integer oswId) {
		this.oswId = oswId;
	}

	public Set<BaoxiaoDan> getBaoxiaodans() {
		return baoxiaodans;
	}

	public void setBaoxiaodans(Set<BaoxiaoDan> baoxiaodans) {
		this.baoxiaodans = baoxiaodans;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public Float getBeginCount() {
		return beginCount;
	}

	public void setBeginCount(Float beginCount) {
		this.beginCount = beginCount;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getRootSelfCard() {
		return rootSelfCard;
	}

	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Float getZzRukCount() {
		return zzRukCount;
	}

	public String getBanben() {
		return banben;
	}

	public void setBanben(String banben) {
		this.banben = banben;
	}

	public void setZzRukCount(Float zzRukCount) {
		this.zzRukCount = zzRukCount;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getHadChange() {
		return hadChange;
	}

	public void setHadChange(String hadChange) {
		this.hadChange = hadChange;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBgMarkIds() {
		return bgMarkIds;
	}

	public void setBgMarkIds(String bgMarkIds) {
		this.bgMarkIds = bgMarkIds;
	}
	
	

}
