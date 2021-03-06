package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Calendar;
import com.task.entity.Users;

/***
 * 日历Server层
 * 
 * @author 刘培
 * 
 */
public interface CalendarServer {

	/**
	 * 查询所有的日历信息
	 * 
	 * @return
	 */
	List<Calendar> findCalendar();

	/**
	 * 根据日期查询日历信息
	 * 
	 * @return
	 */
	List<Calendar> findCalendar(String dateTime);


	Object[]  findCalendarByUsers(Calendar calendar, int pageNo, int pageSize);

	/**
	 * 根据开始和结束日期查询所对应的日历信息
	 * 
	 * @return
	 */
	List<Calendar> findCalendar(String start, String end);
	
	List<Calendar> findCalendargo(String start, String end);

	List<Calendar> taskCalendar(String start);
	//自己未完成任务
	
	List<Calendar> Calendarajax(String userName);
	//个人工作况
	Users tiemeing(Integer id);
	
    String addCalendars(Calendar calendar, String ids);

    /***
	 * 添加日历事件
	 * 
	 * @param calendar
	 *            日历对象
	 * @return true/false
	 */
	String addCalendar(Calendar calendar);

	/***
	 * 删除日历事件
	 * 
	 * @param calendar
	 *            日历对象
	 * @return true/false
	 */
	boolean delCalendar(Calendar calendar);

	/***
	 * 删除日历事件(重复系列事件)
	 * 
	 * @param calendar
	 *            日历对象
	 * @return true/false
	 */
	boolean delCalendar(Calendar calendar, String repeat);

	/***
	 * 根据id查询日历事件
	 * 
	 * @param id
	 *            日历id
	 * @return true/false
	 */
	Calendar findCalendarById(int id);

	void CalendarRemind();

    String submitCalendarState(Integer id);

	String updateMsgAssign(Integer id);
	
	/**
	 * 获得人员任务周报
	 */
	Map<String, Object> getTaskWeekly(Calendar calendar,Integer pageNo,Integer pageSize,String pageStatus);
	
	/**
	 * 任务时长分析
	 */
	Map<String, Object> taskTimeAnalyze(Calendar calendar);
}
