package com.task.entity.sop;

import java.util.List;
import java.util.Set;

public class GysWWApply implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer gysId;//
	private String gysName;//供应商名称（简称）
	private String compayName;//供应商名称（公司名称）
	private String lxPeople;//联系人
	private String tel;//电话
	private String fax;//传真
	private String wwType;//外委类型（工序外委,包工包料）
	private String type;//票据类型
	private String condition;//付款条件
	private String wwNumber;//外委订单号码
	private String cgPeople;//采购员
	private String addDate;//添加日期
	private String addTime;//添加时间
	private Integer rootId;//生产总成id
	private String ghAddress;//供货地址（供应商地址）
	private String shAddress;//送货地址(公司地址)
	private Integer processApplyId;//工序外委表Id
	private String status;//状态(//待核对，待确认，待审批，审批中，同意，打回)
	private Integer epId;//
	private Set<GysWWApplyDetail> gysWWApplyDetails;//外委明细
	private List<GysWWApplyRemark> remarks;//注意条款（配关系太麻烦了不配了）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGysId() {
		return gysId;
	}
	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	public String getLxPeople() {
		return lxPeople;
	}
	public void setLxPeople(String lxPeople) {
		this.lxPeople = lxPeople;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getWwNumber() {
		return wwNumber;
	}
	public void setWwNumber(String wwNumber) {
		this.wwNumber = wwNumber;
	}
	public String getCgPeople() {
		return cgPeople;
	}
	public void setCgPeople(String cgPeople) {
		this.cgPeople = cgPeople;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public Set<GysWWApplyDetail> getGysWWApplyDetails() {
		return gysWWApplyDetails;
	}
	public void setGysWWApplyDetails(Set<GysWWApplyDetail> gysWWApplyDetails) {
		this.gysWWApplyDetails = gysWWApplyDetails;
	}
	public Integer getProcessApplyId() {
		return processApplyId;
	}
	public void setProcessApplyId(Integer processApplyId) {
		this.processApplyId = processApplyId;
	}
	public List<GysWWApplyRemark> getRemarks() {
		return remarks;
	}
	public void setRemarks(List<GysWWApplyRemark> remarks) {
		this.remarks = remarks;
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
	public String getCompayName() {
		return compayName;
	}
	public void setCompayName(String compayName) {
		this.compayName = compayName;
	}
	
	
	
	

}
