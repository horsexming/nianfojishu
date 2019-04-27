package com.task.Server;

import com.task.entity.AttendancePersonInformation;
import com.task.entity.Department;
import com.task.entity.Person;


/**
 * @author 刘晓霆
 * @Date 2014-04-26
 */
public interface AtPersonInforServer {
	/***
	 * 按条件查询分页
	 * 
	 * @param askForLeave
	 *            实体类对象
	 * @param pageNo
	 *            每页显示条数
	 * @param pageSize
	 *            总记录数
	 */
	public Object[] selectAllByAtPersonInforPage(AttendancePersonInformation attendancePersonInformation,Person person,Department department, int pageNo,
			int pageSize);
	
	/***
	 * 根据Id删除考勤人员信息
	 * 
	 * @param Id
	 *            :根据Id删除
	 */
	public String deleteAtPersonInfor(Integer id);
	/***
	 * 根据ID获得考勤人员对象，人员对象，部门对象的数组
	 * @param id 人员ID
	 * @return
	 */
	Object[] getKQObj(Integer id);
	/**
	 * 考勤人员信息维护
	 * @param id（人员ID）
	 * @param attendancePersonInformation（考勤表对象）
	 * @param person（人员姓名，卡号信息）
	 * @param department
	 * @return
	 */
	boolean updateKQPerson(Integer id,
			AttendancePersonInformation attendancePersonInformation,
			Person person, Department department);
	/**
	 * 下拉部门
	 * 
	 * @param tag
	 * @return
	 */
	String findSTPDept(String tag);
}
