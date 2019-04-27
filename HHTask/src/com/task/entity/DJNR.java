package com.task.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author Administrator
 *设备点检内容表;(表名:ta_DJNR1)
 */
public class DJNR  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nr;//点检内容;
	private Set<Machine> machine;//对应设备表 多对多关系;
	private List<Machine> machineList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	@JSONField(serialize = false)
	public Set<Machine> getMachine() {
		return machine;
	}
	public void setMachine(Set<Machine> machine) {
		this.machine = machine;
	}
	public List<Machine> getMachineList() {
		return machineList;
	}
	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}
	
}
