package com.task.entity.sop;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 预测订单产品表 （ta_yc_YcProduct）
 *
 */
public class YcProduct implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private Integer productId;//产品Id;
	private String orderNo;//订单编号（内部）
	private String outOrderNo;//订单号（外部）
	private String markId;//件号
	private String ywmarkId;//业务件号;
	private Float num;//下单数量
	private Float yfpNum;//预测分配数量
	private Float ycgNum;//已采购数量
	private Float ysqNum;//已申请数量
	private String status;//状态  (未分配、待申请、采购中、完成)
	private Float cxNum;//差异数量(正式订单与预测订单之差)
	private String proName;
	private String addTime;
	private String addUserName;
	private Set<YcWeekFePei> setycWeekFeiPei;// 一对多
	private List<YcWeekFePei> ycWeekList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
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
	public Float getNum() {
		return num;
	}
	public void setNum(Float num) {
		this.num = num;
	}
	public Float getYfpNum() {
		return yfpNum;
	}
	public void setYfpNum(Float yfpNum) {
		this.yfpNum = yfpNum;
	}
	public Float getYcgNum() {
		return ycgNum;
	}
	public void setYcgNum(Float ycgNum) {
		this.ycgNum = ycgNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JSONField(serialize = false)
	public Set<YcWeekFePei> getSetycWeekFeiPei() {
		return setycWeekFeiPei;
	}
	public void setSetycWeekFeiPei(Set<YcWeekFePei> setycWeekFeiPei) {
		this.setycWeekFeiPei = setycWeekFeiPei;
	}
	public List<YcWeekFePei> getYcWeekList() {
		return ycWeekList;
	}
	public void setYcWeekList(List<YcWeekFePei> ycWeekList) {
		this.ycWeekList = ycWeekList;
	}
	public Float getYsqNum() {
		return ysqNum;
	}
	public void setYsqNum(Float ysqNum) {
		this.ysqNum = ysqNum;
	}
	public Float getCxNum() {
		return cxNum;
	}
	public void setCxNum(Float cxNum) {
		this.cxNum = cxNum;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	
	
	
	
	
}
