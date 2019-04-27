package com.task.entity.sop;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Users;
import com.task.util.FieldMeta;
import com.tast.entity.zhaobiao.ZhUser;

/****
 * 外购采购订单表
 * 
 * @author 刘培
 * @表名 ta_sop_w_WaigouOrder
 * 
 */
public class WaigouOrder  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer gysId;// 供应商id
	private Integer userId;// 用户id

	/****** 订单表头 **********/
	private String userCode;// 用户工号(供应商编号)
	@FieldMeta(name = "供应商名称")
	private String gysName;// 供应商名称
	@FieldMeta(name = "供应商地址")
	private String ghAddress;// 供货地址（供应商地址）
	private String lxPeople;// 供应商联系人
	private String gysPhone;// 供应商电话/手机
	@FieldMeta(name = "订单编号")
	private String planNumber;// 订单编号(OS201702220001)
	private String addTime;// 添加时间
	private String addUserCode;// 添加人工号）
	@FieldMeta(name = "采购员")
	private String addUserName;// 添加人姓名（采购员）(联系人)
	@FieldMeta(name = "付款方式")
	private String payType;// 付款方式(票到结60天等)
	private String piaojuType;// 票据类型(增值税发票 17%)
	private String shAddress;// 送货地址(公司地址)
	/****** 订单表头 结束 **********/
	
	private String status;// 状态(待核对,待审批,待通知、待确认、待审核（外委订单需要审核）、生产中、送货中、完成)
	private String oldstatus;
	private String caigouMonth;// 采购月份
	private String addUserPhone;// 添加人电话
	private String addUserYx;// 添加人联系人邮箱（可能是件号）
	private String tongzhiTime;// 通知时间
	private String tzUserCode;// 通知员工号
	private String tzUserName;// 通知员姓名
	private String tzUserPhone;// 通知员电话
	private String querenTime;// 采购确认时间
	@FieldMeta(name = "采购类型")
	private String wwType;// 外委类型（工序外委,包工包料,外购）
	private String type;// 计划类型（外购、外委、模具、辅料）
	private String isprint;//打印状态(是,否);
	private String printTime;//打印时间
	/**
	 * 外委使用
	 */
	private String wwSource;// 外委来源（BOM外委,手动外委）
	private Integer rootId;// 总成Id
	private String fax;// 传真
	private Integer processApplyId;// 工序外委表Id
	private String applystatus;// 状态(//待核对，待确认，待审批，审批中，同意，打回)

	private String rootMarkId;// 总成件号
	@FieldMeta(name = "业务件号")
	private String ywMarkId;// 业务件号
	private String rootSlfCard;//总成批次（页面传值使用不存入数据库）
	private String neiorderNum;//内部订单号（页面传值使用不存入数据库）
	private String waiorderNum;//外部订单号（页面传值使用不存入数据库）
	private Float filnalCount;//需求数量（页面传值使用不存入数据库）
	
	private Integer epId;//
	private ZhUser zhUser;// 页面传值
	private Users user;// 页面传值
	private String wlStatus;// 物料状态(待出库,已出库)
	private Double allMoney;//订单总价
	private Set<WaigouPlan> wwpSet;
	private List<WaigouPlan> wwpList;
	private Float sumMoney;
	private String allMoneys;//合计
	
	public List<WaigouPlan> getWwpList() {
		return wwpList;
	}

	public void setWwpList(List<WaigouPlan> wwpList) {
		this.wwpList = wwpList;
	}

	// private ProcardWGCenter pwc;//采购计划中间表
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
	@JSONField(serialize=false)
	public Set<WaigouPlan> getWwpSet() {
		return wwpSet;
	}

	public void setWwpSet(Set<WaigouPlan> wwpSet) {
		this.wwpSet = wwpSet;
	}

	public String getTongzhiTime() {
		return tongzhiTime;
	}

	public void setTongzhiTime(String tongzhiTime) {
		this.tongzhiTime = tongzhiTime;
	}

	public String getQuerenTime() {
		return querenTime;
	}

	public void setQuerenTime(String querenTime) {
		this.querenTime = querenTime;
	}

	public String getAddUserCode() {
		return addUserCode;
	}

	public void setAddUserCode(String addUserCode) {
		this.addUserCode = addUserCode;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public String getAddUserPhone() {
		return addUserPhone;
	}

	public void setAddUserPhone(String addUserPhone) {
		this.addUserPhone = addUserPhone;
	}

	public String getAddUserYx() {
		return addUserYx;
	}

	public void setAddUserYx(String addUserYx) {
		this.addUserYx = addUserYx;
	}

	public String getLxPeople() {
		return lxPeople;
	}

	public void setLxPeople(String lxPeople) {
		this.lxPeople = lxPeople;
	}

	public String getGhAddress() {
		return ghAddress;
	}

	public void setGhAddress(String ghAddress) {
		this.ghAddress = ghAddress;
	}

	public String getShAddress() {
		return shAddress;
	}

	public void setShAddress(String shAddress) {
		this.shAddress = shAddress;
	}

	public String getWwType() {
		return wwType;
	}

	public void setWwType(String wwType) {
		this.wwType = wwType;
	}

	public Integer getProcessApplyId() {
		return processApplyId;
	}

	public void setProcessApplyId(Integer processApplyId) {
		this.processApplyId = processApplyId;
	}

	public String getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTzUserCode() {
		return tzUserCode;
	}

	public void setTzUserCode(String tzUserCode) {
		this.tzUserCode = tzUserCode;
	}

	public String getTzUserName() {
		return tzUserName;
	}

	public void setTzUserName(String tzUserName) {
		this.tzUserName = tzUserName;
	}

	public String getTzUserPhone() {
		return tzUserPhone;
	}

	public void setTzUserPhone(String tzUserPhone) {
		this.tzUserPhone = tzUserPhone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ZhUser getZhUser() {
		return zhUser;
	}

	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getWwSource() {
		return wwSource;
	}

	public void setWwSource(String wwSource) {
		this.wwSource = wwSource;
	}

	public String getWlStatus() {
		return wlStatus;
	}

	public void setWlStatus(String wlStatus) {
		this.wlStatus = wlStatus;
	}

	public String getPiaojuType() {
		return piaojuType;
	}

	public void setPiaojuType(String piaojuType) {
		this.piaojuType = piaojuType;
	}

	public String getRootSlfCard() {
		return rootSlfCard;
	}

	public void setRootSlfCard(String rootSlfCard) {
		this.rootSlfCard = rootSlfCard;
	}

	public String getNeiorderNum() {
		return neiorderNum;
	}

	public void setNeiorderNum(String neiorderNum) {
		this.neiorderNum = neiorderNum;
	}

	public Float getFilnalCount() {
		return filnalCount;
	}

	public void setFilnalCount(Float filnalCount) {
		this.filnalCount = filnalCount;
	}

	public String getWaiorderNum() {
		return waiorderNum;
	}

	public void setWaiorderNum(String waiorderNum) {
		this.waiorderNum = waiorderNum;
	}


	public Float getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Float sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getIsprint() {
		return isprint;
	}

	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}

	/**
	 * @return the allMoneys
	 */
	public String getAllMoneys() {
		return allMoneys;
	}
	/**
	 * @param allMoneys the allMoneys to set
	 */
	public void setAllMoneys(String allMoneys) {
		this.allMoneys = allMoneys;
	}

	public Double getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public String getOldstatus() {
		return oldstatus;
	}

	public void setOldstatus(String oldstatus) {
		this.oldstatus = oldstatus;
	}

}
