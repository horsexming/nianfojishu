package com.task.Server;

import java.util.List;

import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineMonthDj;
import com.task.entity.sop.ProcessInfor;

public interface MachineMonthDjServer {

	public boolean  add(MachineMonthDj mdj);
	public boolean 	del(MachineMonthDj mdj);
	public boolean update(MachineMonthDj mdj);
	/**
	 * 根据设备id 和月份查询出对应的设备每月点检汇总;
	 * @param id
	 * @param month
	 * @return
	 */
	public MachineMonthDj findallbyid(Integer id,String month);
	/**
	 * 根据设备Id和月份查询出对应的工位，操作者，设备编号，设备名称，班。
	 * @param id
	 * @param month
	 * @return
	 */
	public List<ProcessInfor> getprocess(Integer id,String month);
	/**
	 *得到一月内某个设备的所有的点检内容
	 *
	 */
	public List<MachineDayDj> getdjnrofmonth(Integer id,String month);
	
	public List<MachineDayDj> getmddofmonth(Integer id,String month);
	/**
	 * 得到设备设备信息;
	 */
	public Machine getmachine(Integer id);
}
