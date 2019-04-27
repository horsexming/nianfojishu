package com.task.entity.wlWeizhiDt;

import java.io.Serializable;

import com.task.util.Util;

/**
 * ta_wlWeizhiDt
 * @author licong
 *
 */
public class WlWeizhiDt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer procardId;//procard表id
	private Integer waigouPlanId;//waigouPlan表Id
	private Integer waigouDeliveryDetailId;//waigouDeliveryDetail表Id
	private String detail;//详情
	private String addDate;//添加时间
	
	public WlWeizhiDt(){}
	
	public WlWeizhiDt(Integer procardId,Integer waigouPlanId,Integer waigouDeliveryDetailId,String detail){
		this.procardId =procardId;
		this.waigouPlanId=waigouPlanId;
		this.waigouDeliveryDetailId=waigouDeliveryDetailId;
		this.detail=detail;
		this.addDate=Util.getDateTime();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public Integer getWaigouPlanId() {
		return waigouPlanId;
	}
	public void setWaigouPlanId(Integer waigouPlanId) {
		this.waigouPlanId = waigouPlanId;
	}
	public Integer getWaigouDeliveryDetailId() {
		return waigouDeliveryDetailId;
	}
	public void setWaigouDeliveryDetailId(Integer waigouDeliveryDetailId) {
		this.waigouDeliveryDetailId = waigouDeliveryDetailId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	
	
}
