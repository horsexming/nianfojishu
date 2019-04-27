package com.task.Server.menjin;

import java.util.Map;

import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.CarInOutType;

public interface AccessRecordsServer {
	/**
	 * 分页查询门禁记录
	 * @return
	 */
	public Map<Integer, Object> findAccessRecordsByCondition(AccessRecords accessRecord, int pageNo, int pageSize, String tag);
	
	
	/**
	 * 分页查询车辆状态记录
	 * @return
	 */
	public Map<Integer, Object> findCarInOutTypeByCondition(CarInOutType carInOutType, int pageNo, int pageSize);
	
	/**
	 * 修改车辆状态记录
	 * @return
	 */
	public String updateCarInOutType(CarInOutType carInOutType);
	
	/**
	 * 根据id获得对象
	 * @return
	 */
	public CarInOutType getByIdCarInOutType(Integer integer);
	
	/**
	 * 分页查询日志记录
	 * @return
	 */
	public Map<Integer, Object> findAccessLogInforByCondition(AccessLogInfor accessLogInfor, int pageNo, int pageSize, String tag);
	
	/**
	 * 分页查询报警记录
	 * @return
	 */
	public Map<Integer, Object> findAccessLogInforByBaoJingCondition(AccessLogInfor accessLogInfor, int pageNo, int pageSize);
	
	 
}
