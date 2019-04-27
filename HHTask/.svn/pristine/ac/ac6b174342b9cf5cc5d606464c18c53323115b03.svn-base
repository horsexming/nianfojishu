package com.task.Server;

import java.util.List;
import java.util.Map;


import com.task.entity.AttendanceTow;
import com.task.entity.AttendanceTowCollect;
import com.task.entity.Download;


/**
 * @author Li_Cong
 * @Date 2016-01-02
 */

public interface AttendanceTowServer{
	/**
	 * 分页查询打卡记录
	 * @return
	 */
	public Map<Integer, Object> findAttendanceTow(AttendanceTow attendanceTow, int pageNo, int pageSize, String tag);
	/**
	 * 分页查询打卡汇总记录
	 * @return
	 */
	public Map<Integer, Object> findAttendanceTowCollect(AttendanceTowCollect attendanceTowCollect, int pageNo, int pageSize, String tag);
	/**
	 * 分页查询打卡记录
	 * @return
	 */
	public Map<Integer, Object> findDownloads(Download Download, int pageNo, int pageSize, String tag);
	/**
	 *  查询指定日期 ,卡号打卡记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findDownload(String date,String cardId);
	/***
	 * 查询摄像头考勤进入播报语音用
	 * @param id
	 * @return
	 */
	List findSxtKq(Integer id);
	
	/***
	 * 查询摄像头考勤进入播报语音离开使用
	 * @param id
	 * @return
	 */
	List findSxtKqLeave(Integer id);
}
