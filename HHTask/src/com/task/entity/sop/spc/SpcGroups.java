package com.task.entity.sop.spc;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞 spc群组数表 (ta_sop_spc_SpcGroups);
 *
 */
public class SpcGroups  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer gropsSize;//群组数大小
	private Float a2;//计算上下限均值时使用
	private Float d4;//计算极差最大/最小值时使用
	private Float d3;
	private String	addTime;//添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGropsSize() {
		return gropsSize;
	}
	public void setGropsSize(Integer gropsSize) {
		this.gropsSize = gropsSize;
	}
	public Float getA2() {
		return a2;
	}
	public void setA2(Float a2) {
		this.a2 = a2;
	}
	public Float getD4() {
		return d4;
	}
	public void setD4(Float d4) {
		this.d4 = d4;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Float getD3() {
		return d3;
	}
	public void setD3(Float d3) {
		this.d3 = d3;
	}

	
}
