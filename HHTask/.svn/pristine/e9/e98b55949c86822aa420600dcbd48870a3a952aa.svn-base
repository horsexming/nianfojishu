package com.task.Server.kq;

import java.util.List;

import com.task.entity.AttendancePerson;
import com.task.entity.Person;


/**
 * @author 刘晓霆
 * @FileNam IPersonService.java
 * @Date 2014-04-29
 */
public interface IPersonService {
	
	/***
	 * 根据Id查询考勤人员信息返回数组类型（部门集合，个人信息集合）
	 * 
	 * @param Id
	 *            人员Id
	 */
	public Object[] selectId(Integer id);
	
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
	public Object[] selectAllByAtPersonPage(AttendancePerson attendancePerson, int pageNo,
			int pageSize);
	
	/***
	 * 修改请假信息
	 * 
	 * @param askForLeave
	 *            页面新的请假对象
	 * @param oldAskForLeave
	 *            数据库中老的请假对象
	 */
	public void updateAttendancePerson(AttendancePerson attendancePerson, AttendancePerson oldAttendancePerson);

	
	
	
	public void add(Person ps);
	public void delById(int id);
	public void del(Person ps);
	public void update(Person ps);
	public List<Person> queryAllPerson();
	public Person getPersonById(int id);
	public String getPersonIdBycardNo(String cardNo);
	public List<Person> queryPersonByDeptId(int id);
	public Object[] getPersonNameAndDept(int id);
}
