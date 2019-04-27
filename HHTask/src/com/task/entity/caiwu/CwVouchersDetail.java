package com.task.entity.caiwu;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 财务凭证分录（ta_fin_CwVouchersDetail）
 * @author txb
 *
 */
public class CwVouchersDetail implements Serializable{
	private Integer id;
	private String vClass;//分录类别(收 付 转) 
	private String remark;//摘要
	private String sub;//一级科目
	private Integer subId;//科目Id
	
	private String detailSub;//明细科目
	private Integer detailSubId;//科目Id
	
	private Double jieMoney;//借
	private Double daiMoney;//贷
	
	private String createTime;//创建时间
	private String createName;//创建人
	private String createCode;//创建人工号
	private CwVouchers cwVouchers;//凭证
	
	private Set<CwUseDetail> cwUseDetails;//辅助明细
	private List<CwUseDetail> cwUseDetailList;//页面传值使用
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public Double getJieMoney() {
		return jieMoney;
	}
	public void setJieMoney(Double jieMoney) {
		this.jieMoney = jieMoney;
	}
	public Double getDaiMoney() {
		return daiMoney;
	}
	public void setDaiMoney(Double daiMoney) {
		this.daiMoney = daiMoney;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateCode() {
		return createCode;
	}
	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	public CwVouchers getCwVouchers() {
		return cwVouchers;
	}
	public void setCwVouchers(CwVouchers cwVouchers) {
		this.cwVouchers = cwVouchers;
	}
	public Set<CwUseDetail> getCwUseDetails() {
		return cwUseDetails;
	}
	public void setCwUseDetails(Set<CwUseDetail> cwUseDetails) {
		this.cwUseDetails = cwUseDetails;
	}
	public List<CwUseDetail> getCwUseDetailList() {
		return cwUseDetailList;
	}
	public void setCwUseDetailList(List<CwUseDetail> cwUseDetailList) {
		this.cwUseDetailList = cwUseDetailList;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public String getDetailSub() {
		return detailSub;
	}
	public void setDetailSub(String detailSub) {
		this.detailSub = detailSub;
	}
	public Integer getDetailSubId() {
		return detailSubId;
	}
	public void setDetailSubId(Integer detailSubId) {
		this.detailSubId = detailSubId;
	}
	public String getvClass() {
		return vClass;
	}
	public void setvClass(String vClass) {
		this.vClass = vClass;
	}
	
	
}
