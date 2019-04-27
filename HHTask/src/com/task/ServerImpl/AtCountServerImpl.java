package com.task.ServerImpl;

import java.util.List;
import com.task.Dao.TotalDao;
import com.task.Server.AtCountServer;
import com.task.entity.AttendanceCount;
import com.task.util.Util;

public class AtCountServerImpl implements AtCountServer {
	public TotalDao totalDao;



	// 分页显示：出勤率查询
	@Override
	public Object[] findAllAttC(
			AttendanceCount attendanceCount, int pageNo, int pageSize,
			String startDate, String endDate, String banciName) {
		String hql ="from AttendanceCount where 1=1";
		//条件查询
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and cardDateTime between '" + startDate + "' and '"
					+ endDate + "'";
		}
		if(banciName!=null&&!"".equals(banciName)){
			hql+=" and banci_name = '"+banciName+"' ";
		}
	    hql += " order by cardDateTime desc desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;

	}
	/**
	 * 根据ID获取出勤路对象
	 * @param id
	 * @return
	 */
	@Override
	public AttendanceCount getAttendanceCount(Integer id){
		if(null!=id){
			return (AttendanceCount)totalDao.getObjectById(AttendanceCount.class, id);
		}
		return null;
	}
	/**
	 * 查询时间段内的出勤率记录
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public List findListAcc(String startDate,String endDate){
		String monthDate=Util.getDateTime("yyyy-MM-");
		String hql ="from AttendanceCount where cardDateTime like '%"+monthDate+"%'";
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql = " from AttendanceCount where cardDateTime between '" + startDate + "' and '"
					+ endDate + "'";
		}
	    hql += " order by cardDateTime desc asc";
	    return totalDao.query(hql);
	}
	// getter和setter方法
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
