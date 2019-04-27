package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.AtPersonInforServer;
import com.task.entity.AttendancePersonInformation;
import com.task.entity.Department;
import com.task.entity.Person;

public class AtPersonInforServerImpl implements AtPersonInforServer {
	public TotalDao totalDao;// 导入接口

	// 查询所有信息，同时显示分页
	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public Object[] selectAllByAtPersonInforPage(
			AttendancePersonInformation attendancePersonInformation,
			Person person, Department department, int pageNo, int pageSize) {
		// 姓名，部门，卡号 ，班次，添加时间
		String hql = "select st_p.name,dept.name,st_p.cardNo,kq_p.classNo,st_p.onWorkDate,kq_p.id from AttendancePersonInformation kq_p ,Person st_p,Department dept "
				+ "where kq_p.atPerInforId=st_p.id and st_p.deptId=dept.id ";
		// 姓名卡号
		if (null != person) {
			if (null != person.getName() && !"".equals(person.getName())) {
				hql += " and st_p.name like '%" + person.getName() + "%'";
			}
			if (null != person.getCardNo() && !"".equals(person.getCardNo())) {
				hql += " and st_p.cardNo like '%" + person.getCardNo() + "%'";
			}
		}
		// 部门不为空
		if (null != department) {
			if (null != department.getName()
					&& !"".equals(department.getName())) {
				hql += " and dept.name='" + department.getName() + "'";
			}
		}
		// 白班，夜班
		if (null != attendancePersonInformation) {
			if (null != attendancePersonInformation.getClassNo()
					&& !"".equals(attendancePersonInformation.getClassNo())) {
				hql += " and kq_p.classNo='"
						+ attendancePersonInformation.getClassNo() + "'";
			}
		}
		hql+=" order by dept.name desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] obj = { list, count };
		return obj;
	}

	/***
	 * 根据ID获得考勤人员对象，人员对象，部门对象的数组
	 * 
	 * @param id
	 *            人员ID
	 * @return
	 */
	@Override
	public Object[] getKQObj(Integer id) {
		AttendancePersonInformation kq_person = (AttendancePersonInformation) totalDao
				.getObjectById(AttendancePersonInformation.class, id);
		Person st_person = (Person) totalDao.getObjectById(Person.class, id);
		Department department = st_person.getDepartment();
		Object[] obj = { kq_person, st_person, department };
		return obj;

	}

	/**
	 * 考勤人员信息维护
	 * 
	 * @param id
	 *            （人员ID）
	 * @param attendancePersonInformation
	 *            （考勤表对象）
	 * @param person
	 *            （人员姓名，卡号信息）
	 * @param department
	 * @return
	 */
	@Override
	public boolean updateKQPerson(Integer id,
			AttendancePersonInformation attendancePersonInformation,
			Person person, Department department) {
		boolean boo = false;
		AttendancePersonInformation kq_person = (AttendancePersonInformation) totalDao
				.getObjectById(AttendancePersonInformation.class, id);
		Person st_person = (Person) totalDao.getObjectById(Person.class, id);
		// 赋值
		// 考勤人员白班，夜班
		kq_person.setClassNo(attendancePersonInformation.getClassNo());
		// 人员姓名，卡号
		st_person.setName(person.getName());
		st_person.setCardNo(person.getCardNo());
		// 部门名称
		String hql = " from Department where name=?";
		List list = totalDao.query(hql, department.getName());
		if (list.size() > 0 && null != list) {
			Department st_department = (Department) list.get(0);
			st_person.setDeptId(st_department.getId());
		}
		boo = totalDao.update(kq_person);
		boo = totalDao.update(st_person);
		return boo;
	}

	// 根据Id删除考勤人员信息
	@Override
	public String deleteAtPersonInfor(Integer id) {
		// TODO Auto-generated method stub
		AttendancePersonInformation attendancePersonInformation = (AttendancePersonInformation) totalDao
				.getObjectById(AttendancePersonInformation.class, id);
		if (attendancePersonInformation != null) {
			totalDao.delete(attendancePersonInformation);
		} else {
			return "你要删除的人员信息不存在!!!";
		}
		return null;
	}

	/**
	 * 下拉部门
	 * 
	 * @param tag
	 * @return
	 */
	@Override
	public String findSTPDept(String tag) {
		String message = "";
		if (null != tag && "department".equals(tag)) {
			String hql = "select distinct name from Department where isDel="+0;
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				if (null != d && d.length() > 0) {
					message += d.toString() + "|";
				}

			}
		}
		return message;
	}

	// getter和setter方法
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
