/**
 * 
 */
package com.task.entity.expresscabinet;

import java.io.Serializable;

/**
 * @author Administrator 快递取件费用类型生成表
 */
public class Charging implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 费用类型ID
	private String type; // 柜子类型
	private Integer overTime; // 超时小时(按小时定义)
	private float cost; // 费用

	public Charging() {
		super();
	}

	public Charging(Integer id, String type, Integer overTime, float cost) {
		super();
		this.id = id;
		this.type = type;
		this.overTime = overTime;
		this.cost = cost;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOverTime() {
		return overTime;
	}

	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
