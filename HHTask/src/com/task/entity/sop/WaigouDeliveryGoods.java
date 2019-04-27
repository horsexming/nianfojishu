package com.task.entity.sop;

import java.util.Set;

/****
 * 成品送货单
 * 
 * @author 刘培
 * @表名 ta_sop_w_WaigouDeliveryGoods
 * 
 */
public class WaigouDeliveryGoods implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String planNumber;// 送货单号
	private Integer userId;// 用户id
	private String userCode;// 用户工号(编号)

	private String gysName;// 供应商名称
	private String gysContacts;// 供应商联系人
	private String gysPhone;// 供应商电话

	private Integer khId;// 客户id
	private String customerCode;// 客户编号
	private String customer;// 客户名称
	private String contacts;// 联系人
	private String contactsPhone;// 联系人手机号

	private String shContacts;// 送货联系人
	private String shContactsPhone;// 送货联系人手机号

	private String qsContacts;// 客户签收联系人
	private String qsContactsPhone;// 客户签收联系人手机号

	private String ysContacts;// 客户验收联系人
	private String ysContactsPhone;// 客户验收联系人手机号

	private String chepai;// 车牌(申请时如果存在车牌直接添加到门禁系统(可进门)，并且在仓库确认后自动设置为可出门)
	private String status;// 状态(待确认、待打印、待送货、送货中、退货带核对、已签收、已验收)
	private String chufaDizhi;// 出发地址
	private String daodaDizhi;// 到达地址
	private String addTime;// 添加时间
	private String shsqDate;// 送货申请日期(提醒送货员)
	private String printTime;// 打印时间
	private String chufaTime;// 出发时间
	private String daodaTime;// 到达时间
	private Set<WaigouDeliveryGoodsDetail> wddSet;
	private String classNames;// 前端显示
	private String isprint;// 是否打印;(YES/NO)

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

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getChufaDizhi() {
		return chufaDizhi;
	}

	public void setChufaDizhi(String chufaDizhi) {
		this.chufaDizhi = chufaDizhi;
	}

	public String getDaodaDizhi() {
		return daodaDizhi;
	}

	public void setDaodaDizhi(String daodaDizhi) {
		this.daodaDizhi = daodaDizhi;
	}

	public String getChufaTime() {
		return chufaTime;
	}

	public void setChufaTime(String chufaTime) {
		this.chufaTime = chufaTime;
	}

	public String getDaodaTime() {
		return daodaTime;
	}

	public void setDaodaTime(String daodaTime) {
		this.daodaTime = daodaTime;
	}

	public String getChepai() {
		return chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGysContacts() {
		return gysContacts;
	}

	public void setGysContacts(String gysContacts) {
		this.gysContacts = gysContacts;
	}

	public String getShContacts() {
		return shContacts;
	}

	public void setShContacts(String shContacts) {
		this.shContacts = shContacts;
	}

	public String getShContactsPhone() {
		return shContactsPhone;
	}

	public void setShContactsPhone(String shContactsPhone) {
		this.shContactsPhone = shContactsPhone;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public String getIsprint() {
		return isprint;
	}

	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}

	public Set<WaigouDeliveryGoodsDetail> getWddSet() {
		return wddSet;
	}

	public void setWddSet(Set<WaigouDeliveryGoodsDetail> wddSet) {
		this.wddSet = wddSet;
	}

	public String getQsContacts() {
		return qsContacts;
	}

	public void setQsContacts(String qsContacts) {
		this.qsContacts = qsContacts;
	}

	public String getQsContactsPhone() {
		return qsContactsPhone;
	}

	public void setQsContactsPhone(String qsContactsPhone) {
		this.qsContactsPhone = qsContactsPhone;
	}

	public Integer getKhId() {
		return khId;
	}

	public void setKhId(Integer khId) {
		this.khId = khId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getShsqDate() {
		return shsqDate;
	}

	public void setShsqDate(String shsqDate) {
		this.shsqDate = shsqDate;
	}

	public String getYsContacts() {
		return ysContacts;
	}

	public void setYsContacts(String ysContacts) {
		this.ysContacts = ysContacts;
	}

	public String getYsContactsPhone() {
		return ysContactsPhone;
	}

	public void setYsContactsPhone(String ysContactsPhone) {
		this.ysContactsPhone = ysContactsPhone;
	}

}
