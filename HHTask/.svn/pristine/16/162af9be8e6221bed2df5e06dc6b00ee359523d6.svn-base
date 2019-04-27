package com.task.entity;

import java.io.Serializable;

/**
 * 内部计划与流水卡对应关系ta_crm_OrderAndProcard
 * 流水卡初始对应的内部计划为非正式订单时就会产生新的对应关系（原关系依然保留）
 * @author txb
 *
 */
public class OrderAndProcard  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer InorderId;//内部计划Id
	private Integer procardId;//流水卡Id
	private Float count;//对应数量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInorderId() {
		return InorderId;
	}
	public void setInorderId(Integer inorderId) {
		InorderId = inorderId;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public Float getCount() {
		return count;
	}
	public void setCount(Float count) {
		this.count = count;
	}

	
}
