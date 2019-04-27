package com.task.entity.sop;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 王晓飞 预测周分配量（ta_yc_YcWeekFePei）
 *
 */
public class YcWeekFePei {
	private Integer id;
	private Integer fpNum;//每周分配数量
	private Integer whateWeek;//第几周;
	private YcProduct ycProduct;// 预测订单产品表 （多对一）
	private Set<YcWaiGouProcrd> setycwgProcard;// 预测外购件预分配表 （多对多）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFpNum() {
		return fpNum;
	}
	public void setFpNum(Integer fpNum) {
		this.fpNum = fpNum;
	}
	public Integer getWhateWeek() {
		return whateWeek;
	}
	public void setWhateWeek(Integer whateWeek) {
		this.whateWeek = whateWeek;
	}
	@JSONField(serialize = false)
	public YcProduct getYcProduct() {
		return ycProduct;
	}
	public void setYcProduct(YcProduct ycProduct) {
		this.ycProduct = ycProduct;
	}
	@JSONField(serialize = false)
	public Set<YcWaiGouProcrd> getSetycwgProcard() {
		return setycwgProcard;
	}
	public void setSetycwgProcard(Set<YcWaiGouProcrd> setycwgProcard) {
		this.setycwgProcard = setycwgProcard;
	}
	
	
	
}
