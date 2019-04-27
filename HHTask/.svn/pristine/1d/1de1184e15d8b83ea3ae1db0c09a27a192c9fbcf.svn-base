package com.task.Server;

import java.util.List;

import com.task.entity.AttendanceCount;

/**
 * @author 刘晓霆
 * @Date 2014-04-26
 */
public interface AtCountServer {

	/**
	 * 按条件查询考勤分页信息
	 * 
	 * @param attendanceCount
	 *            实体对象
	 * @param pageNo
	 *            每页显示条数
	 * @param pageSize
	 *            总记录数
	 * @param date1
	 *            由用户动态选择开始时间
	 * @param date2
	 *            由用户动态选择结束时间
	 */
	public Object[] findAllAttC(
			AttendanceCount attendanceCount, int pageNo, int pageSize,
			String date1, String date2,String banciName);
	/**
	 * 根据ID获取出勤路对象
	 * @param id
	 * @return
	 */
	AttendanceCount getAttendanceCount(Integer id);
	/**
	 * 查询时间段内的出勤率记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List findListAcc(String startDate, String endDate);



}
