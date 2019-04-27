package com.task.Server;

import com.task.entity.Department;
import com.task.entity.Person;

/**
 * @author 刘晓霆
 * @date 2014-04-23
 */
public interface AtPersonServer {
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
	public Object[] selectAllByAtPersonPage(Person person, int pageNo,
			int pageSize);

	/***
	 * 按条件查询分页
	 * 
	 * @param askForLeave
	 *            实体类对象
	 * @param department
	 *            部门实体对象
	 * @param pageNo
	 *            每页显示条数
	 * @param pageSize
	 *            总记录数
	 */
	public Object[] selectAllByAtPersonAndDeptPage(Person person,
			Department department, int pageNo, int pageSize);
	
	/***
	 * 修改请假信息
	 * 
	 * @param askForLeave
	 *            页面新的请假对象
	 * @param oldAskForLeave
	 *            数据库中老的请假对象
	 */
	public void updatePerson(Person person, Person oldPerson);
	
	
	
	
	
	
	
	
	/*
	 * ***********************************************************************************
	*/
	/***
	 * 根据Id查询 考勤人员 个人对应部门 返回数组类型（跟人信息和个人对应的部门）
	 * 
	 * @param Id(实际不曾调用此方法)
	 *            人员Id
	 */
	public Object[] selectOneId(Integer id);
	/***
	 * 删除考勤人员信息 根据Id删除
	 * 
	 * @param atId（实际未曾使用此方法）
	 *            考勤信息Id
	 */
	public String deletePerson(Integer atId);
}
