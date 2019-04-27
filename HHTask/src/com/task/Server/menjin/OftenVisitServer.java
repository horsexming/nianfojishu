package com.task.Server.menjin;

import java.util.Map;

import com.task.entity.menjin.OftenVisit;

public interface OftenVisitServer {
	/**
	 * 添加常访车辆
	 * 
	 * @param OftenVisit
	 * @return
	 */
	public String addOftenVisit(OftenVisit OftenVisit);

	/**
	 * 根据id查询常访车辆
	 * 
	 * @param visit
	 * @return
	 */
	public OftenVisit OftenVisitByid(Integer integer);
	/**
	 * 修改常访车辆记录
	 * @return
	 */
	public String updateOftenVisit(OftenVisit OftenVisit);
	/**
	 * 删除常访车辆记录
	 * @return
	 */
	public boolean deleteOftenVisit(OftenVisit OftenVisit);
	/**
	 * 查询
	 * 
	 * @param OftenVisit
	 * 
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            每页个数
	 * @return
	 */
	public Map<Integer, Object> findOftenVisitByCondition(OftenVisit OftenVisit, int pageNo, int pageSize);
}
