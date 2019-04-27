package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;

public interface MachineDayYZSJServer {

	public boolean add(MachineDayYZSJ mdy);
	public boolean del(MachineDayYZSJ mdy);
	public boolean update(MachineDayYZSJ mdy,Float sc,String status);
	public Map<Integer, Object> findDJNRByCondition(MachineDayYZSJ mdy,
			int pageNo, int pageSize); 
	public List<MachineDayYZSJ> findmdy(Integer id,String month);
	public List<String> getdjnrofmonth(Integer id,String month);
	public List<MachineDayYZSJ> findmdyofmonth(Integer id,String month);
	/**
	 * 根据设备编号和日期查出设备日运行时长;
	 */
	public MachineDayYZSJ findmdybyno(String No,String date);
	/**
	 * 根据设备Id和日期查出设备日运行时长
	 */
	public MachineDayYZSJ getmdybymachineId(Integer id,String date);
	/**
	 * 根据设备id 查询出设备信息
	 */
	public  Machine getMachine(Integer id);
	/**
	 * 根据工序id 查询出设备信息
	 */
	public Machine getMachinebyproessId(Integer id);
	
}
