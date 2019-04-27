package com.task.entity;
/**
 * 
 * @author Administrator
 *设备日点检表 表名:ta_MachineDayDj
 */
public class MachineDayDj  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String machineNo;//设备编号;
	private String machineDay;//设备点检日期;
	private String machineMonth;//设备点检月份;
	private Integer machine_id;//设备Id
	private String machine_djnr;//点检内容
	private String dj_status;//点检状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public String getMachineDay() {
		return machineDay;
	}
	public void setMachineDay(String machineDay) {
		this.machineDay = machineDay;
	}
	public String getMachineMonth() {
		return machineMonth;
	}
	public void setMachineMonth(String machineMonth) {
		this.machineMonth = machineMonth;
	}
	
	public Integer getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(Integer machineId) {
		machine_id = machineId;
	}
	public String getMachine_djnr() {
		return machine_djnr;
	}
	public void setMachine_djnr(String machineDjnr) {
		machine_djnr = machineDjnr;
	}
	public String getDj_status() {
		return dj_status;
	}
	public void setDj_status(String djStatus) {
		dj_status = djStatus;
	}
	
	
	
}
