package com.task.entity.sop;

import java.util.List;
import java.util.Set;

/**
 * 设变影响外委 ta_sop_w_ProcardSbWw
 * @author txb
 *
 */
public class ProcardSbWw  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer ptbbApplyId;//设变主表Id
	private String sbNumber;//设变单号
	private Integer procardId;//生产件Id
	private String markId;// 件号
	private String ywmarkId;//业务件号
	private String banben;// 版本
	private String proName;// 零件名称
	private Integer banci;// 版次
	private String tuhao;//图号
	private String unit;// 单位
	private String wwType;//外委类型（工序外委/包工包料、外购件/原材料）
	private String wwSource;//外委来源（BOM外委,手动外委）
	private Float number;// 数量
	private String processNOs;//工序号
	private String processNames;//工序名称
	private String status;//待处理,关闭
	private String wwOrderNumber;//外委单号
	
	private String waigouWaiweiPlanIds;//(BOM外委才有)BOM外委计划Id用,分隔
	private Integer wwDetailId;//(手动外委才有)外委申请明细Id(ProcessInforWWapplyDeatil)
	
	//进入外委单才有价格
	private Float hsPrice;// 含税单价
	private Float price;// 不含税单价
	private Double taxprice; // 税率
	private Float money;// 总额
	private Integer priceId;// 价格id
	private Integer userId;// 用户id
	private String userCode;// 用户工号(编号)
	private Integer gysId;// 供应商id
	private String gysName;// 供应商名称
	private Integer waigouPlanId;//委单Id
	
	private Float clCount;//处理数量
	private String clType;//处理方式
	private String clUser;//处理人
	private String clUserCode;//处理人工号
	private Integer clUserId;//处理人Id
	private String clTime;//第一次处理时间
	
	private String addTime;// 添加时间
	
	private Set<ProcardSbWwDetail> procardSbWwDetailSet;//
	
	private List<ProcardSbWwDetail> procardSbWwDetailList;//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPtbbApplyId() {
		return ptbbApplyId;
	}

	public void setPtbbApplyId(Integer ptbbApplyId) {
		this.ptbbApplyId = ptbbApplyId;
	}

	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getYwmarkId() {
		return ywmarkId;
	}

	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}

	public String getBanben() {
		return banben;
	}

	public void setBanben(String banben) {
		this.banben = banben;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWwType() {
		return wwType;
	}

	public void setWwType(String wwType) {
		this.wwType = wwType;
	}

	public String getWwSource() {
		return wwSource;
	}

	public void setWwSource(String wwSource) {
		this.wwSource = wwSource;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getProcessNOs() {
		return processNOs;
	}

	public void setProcessNOs(String processNOs) {
		this.processNOs = processNOs;
	}

	public String getProcessNames() {
		return processNames;
	}

	public void setProcessNames(String processNames) {
		this.processNames = processNames;
	}

	

	public String getWaigouWaiweiPlanIds() {
		return waigouWaiweiPlanIds;
	}

	public void setWaigouWaiweiPlanIds(String waigouWaiweiPlanIds) {
		this.waigouWaiweiPlanIds = waigouWaiweiPlanIds;
	}

	public Integer getWwDetailId() {
		return wwDetailId;
	}

	public void setWwDetailId(Integer wwDetailId) {
		this.wwDetailId = wwDetailId;
	}

	public Float getHsPrice() {
		return hsPrice;
	}

	public void setHsPrice(Float hsPrice) {
		this.hsPrice = hsPrice;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Double getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
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

	public Integer getWaigouPlanId() {
		return waigouPlanId;
	}

	public void setWaigouPlanId(Integer waigouPlanId) {
		this.waigouPlanId = waigouPlanId;
	}

	public Float getClCount() {
		return clCount;
	}

	public void setClCount(Float clCount) {
		this.clCount = clCount;
	}

	public String getClType() {
		return clType;
	}

	public void setClType(String clType) {
		this.clType = clType;
	}

	public String getClUser() {
		return clUser;
	}

	public void setClUser(String clUser) {
		this.clUser = clUser;
	}

	public String getClUserCode() {
		return clUserCode;
	}

	public void setClUserCode(String clUserCode) {
		this.clUserCode = clUserCode;
	}

	public Integer getClUserId() {
		return clUserId;
	}

	public void setClUserId(Integer clUserId) {
		this.clUserId = clUserId;
	}

	public String getClTime() {
		return clTime;
	}

	public void setClTime(String clTime) {
		this.clTime = clTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Set<ProcardSbWwDetail> getProcardSbWwDetailSet() {
		return procardSbWwDetailSet;
	}

	public void setProcardSbWwDetailSet(Set<ProcardSbWwDetail> procardSbWwDetailSet) {
		this.procardSbWwDetailSet = procardSbWwDetailSet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProcardId() {
		return procardId;
	}

	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}

	public List<ProcardSbWwDetail> getProcardSbWwDetailList() {
		return procardSbWwDetailList;
	}

	public void setProcardSbWwDetailList(
			List<ProcardSbWwDetail> procardSbWwDetailList) {
		this.procardSbWwDetailList = procardSbWwDetailList;
	}

	public String getWwOrderNumber() {
		return wwOrderNumber;
	}

	public void setWwOrderNumber(String wwOrderNumber) {
		this.wwOrderNumber = wwOrderNumber;
	}
	
	
	
}
