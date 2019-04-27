package com.task.entity.project;

import com.task.util.FieldMeta;

/**
 * 费用消耗表:ta_pro_QuotedPriceCost
 * @author txb
 *
 */
public class QuotedPriceCost implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	@FieldMeta(name = "报销类别", order = 2)
	private String costType;//花费类型
	@FieldMeta(name = "报销金额", order = 3)
	private Double money;//花费数量
	private Double tzMoney;//投资数量（公司成员投资数额以同意的为标准申请时不扣）
	private Double bxMoney;//预算申报的中报销的费用
	@FieldMeta(name = "项目阶段", order = 1)
	private String proStatus;//项目阶段
	private String source;//来源（预算申报,OA申报,公出申报,加班申报,人工申报,设备折旧申报,能源申报,材料费申报,外购件申报）
	private Integer qpId;//零件Id
	private String markId;//件号
	private String selfCard;//批次号
	private Float counts;//数量
	private String sendNumber;//送货单号
	private String userName;//申请人名称
	private String userCode;//申请人工号
	private String dept;//申请人部门
	private String remark;//备注
	private String applyStatus;//审批状态
	private Integer epId;//
	private String addTime;//添加时间
	private Integer sinleCarId;//用车Id;
	private Integer overTimeId;//加班申请Id;
	private Integer storageId;//库存表id
	private Integer oadetailId;//OA申请ID
	private Float totalMoney;//页面显示
	//页面传值
	private String proName;//项目名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public Double getTzMoney() {
		return tzMoney;
	}
	public void setTzMoney(Double tzMoney) {
		this.tzMoney = tzMoney;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getSinleCarId() {
		return sinleCarId;
	}
	public void setSinleCarId(Integer sinleCarId) {
		this.sinleCarId = sinleCarId;
	}
	public Integer getOverTimeId() {
		return overTimeId;
	}
	public void setOverTimeId(Integer overTimeId) {
		this.overTimeId = overTimeId;
	}
	public Integer getStorageId() {
		return storageId;
	}
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public Integer getOadetailId() {
		return oadetailId;
	}
	public void setOadetailId(Integer oadetailId) {
		this.oadetailId = oadetailId;
	}
	public String getSendNumber() {
		return sendNumber;
	}
	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}
	public Float getCounts() {
		return counts;
	}
	public void setCounts(Float counts) {
		this.counts = counts;
	}
	public Float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Double getBxMoney() {
		return bxMoney;
	}
	public void setBxMoney(Double bxMoney) {
		this.bxMoney = bxMoney;
	}
	
	
	
	
	
}
