package com.task.ServerImpl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import com.task.Dao.TotalDao;
import com.task.Dao.TotalDao_2;
import com.task.Server.AtPersonServer;
import com.task.entity.AttendancePerson;
import com.task.entity.Department;
import com.task.entity.Person;

/**
 * @author 刘晓霆
 * @Date 2014-04-29
 * 
 */

public class AtPersonServerImpl implements AtPersonServer {
	public TotalDao totalDao;// 导入接口

	// 根据Id查询人员信息 返回数组
	@SuppressWarnings("unchecked")
	@Override
	public Object[] selectId(Integer id) {
		// TODO Auto-generated method stub
		// 根据Id查询出个人信息
		Person person = (Person) totalDao.getObjectById(Person.class, id);
		// 根据得到的部门Id，到部门表中查询出，个人所对应的部门名称，并保存到实体对象中
		Department department = (Department) totalDao.getObjectById(
				Department.class, person.getDeptId());
		String hql = " from Department";
		// 用集合接收查询到的所有部门
		List<Department> listDept = totalDao.query(hql);
		// 用Object数组接收，查询出的，个人信息实体对象、个人对应部门实体队形以及所有部门列表
		Object[] obj = { person, department, listDept };
		return obj;
	}

	// 按条件查询分页显示，人员信息
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] selectAllByAtPersonPage(Person person, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		if (person == null) {
			person = new Person();
		}
		// 根据对象封装的条件查询
		String hql = totalDao.criteriaQueries(person, null);
		// 用List集合接收查询结果
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		// 得到查询的记录条数
		int count = totalDao.getCount(hql);
		// 用数组接收查询结果和记录
		Object[] obj = { list, count };
		return obj;
	}

	// 根据person表和department表联合查询，同时分页显示
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] selectAllByAtPersonAndDeptPage(Person person,
			Department department, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (person == null && department == null) {
			person = new Person();
			department = new Department();
		}
		String hql = " from Person as p where 1=1";
		if (department.getName() != null && !department.getName().equals("")) {
			hql += " and p.deptId in(select dept.id from Department as dept where dept.name=?)";
		}
		if (person.getName() != null && !person.getName().equals("")) {
			hql += " and name=?";
		}
		if (person.getCardNo() != null && !person.getCardNo().equals("")) {
			hql += " and cardNo=?";
		}
		if (person.getPersonNo() != null && !person.getPersonNo().equals("")) {
			hql += " and personNo=?";
		}
		// 用List集合接收查询结果
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		// 得到查询的记录条数
		int count = totalDao.getCount(hql);
		// 用数组接收查询结果和记录
		Object[] obj = { list, count };
		return obj;
	}

	// 修改人员信息
	@Override
	public void updatePerson(Person person, Person oldPerson) {
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(person, oldPerson, new String[] {
				"atPersonId", "duty", "sex", "isMarry", "isDel" });
		totalDao.update(oldPerson);
	}

	// getter和setter方法
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * ********************************************************************************************************
	 */
	// 根据Id查询 ：个人信息，个人所在部门 (注：此方法实际未被使用)
	@Override
	public Object[] selectOneId(Integer id) {
		// TODO Auto-generated method stub
		// 根据Id查询出个人信息，病保存到实体对象中
		AttendancePerson attendancePerson = (AttendancePerson) this.totalDao
				.getObjectById(AttendancePerson.class, id);
		// 根据得到的部门Id，到部门表中查询出，个人所对应的部门名称，并保存到实体队形中
		Department department = (Department) totalDao.getObjectById(
				Department.class, attendancePerson.getDeptId());
		// 用数组接收，查询到的两个热实体对象
		Object[] obj = { attendancePerson, department };
		return obj;
	}

	@Override
	public String deletePerson(Integer atId) {
		// TODO Auto-generated method stub
		return null;
	}

}
