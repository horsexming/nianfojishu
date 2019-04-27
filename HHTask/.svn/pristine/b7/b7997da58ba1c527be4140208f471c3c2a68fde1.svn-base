package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.DJNR;
import com.task.entity.MachineDayDj;

public interface MachineDayDjServer {

	public boolean add(MachineDayDj mdd);
	public boolean del(MachineDayDj mdd);
	public boolean update(MachineDayDj mdd);
	/**
	 * 分页条件查询
	 * @param mdd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findMDDByCondition(MachineDayDj mdd,
			int pageNo, int pageSize);
	/**
	 * 根据月份查出某个设备月点检(不分页)
	 * @return
	 */
	public List<MachineDayDj> findmddbymonth(Integer id,String month);
	
	public List<MachineDayDj> findmonthstatus(String machine_djnr,String month ,Integer id);
	
	
	/**
	 * 由日运行时长计算月运行时长，计算月稼动率;
	 */
	public String jisuantest();
	
}
