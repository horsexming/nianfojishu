package com.task.ServerImpl.kq;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.kq.IPersonService;
import com.task.entity.AttendancePerson;
import com.task.entity.Department;
import com.task.entity.Person;


/**
 * @author 刘晓霆
 * @FileNam PersonServiceImpl.java
 * @Date 2014-04-29
 */
public class PersonServiceImpl implements IPersonService {
	private TotalDao totalDao;//导入接口
	// 根据Id查询人员信息 返回数组
	@SuppressWarnings("unchecked")
	@Override
	public Object[] selectId(Integer id) {
		// TODO Auto-generated method stub
		// 根据Id查询出个人信息
		AttendancePerson attendancePerson = (AttendancePerson) totalDao
				.getObjectById(AttendancePerson.class, id);
		// 根据得到的部门Id，到部门表中查询出，个人所对应的部门名称，并保存到实体对象中
		Department department = (Department) totalDao.getObjectById(
				Department.class, attendancePerson.getDeptId());
		String hql = " from Department";
		// 用集合接收查询到的所有部门
		List<Department> listDept = totalDao.query(hql);
		// 用Object数组接收，查询出的，个人信息实体对象、个人对应部门实体队形以及所有部门列表
		Object[] obj = { attendancePerson, department, listDept };
		return obj;
	}
	// 按条件查询分页显示，人员信息
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] selectAllByAtPersonPage(AttendancePerson attendancePerson,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (attendancePerson == null) {
			attendancePerson = new AttendancePerson();
		}
		
		//String hql = "select distinct dept.name from AttendancePerson as atp,Department as dept where atp.deptId = dept.id";
		String hql = totalDao.criteriaQueries(attendancePerson, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] obj = { list, count };
		return obj;
	}
	//修改人员信息
	@Override
	public void updateAttendancePerson(AttendancePerson attendancePerson,
			AttendancePerson oldAttendancePerson) {
		// TODO Auto-generated method stub
		BeanUtils.copyProperties(attendancePerson, oldAttendancePerson,
				new String[] { "atPersonId", "duty", "sex", "isMarry",
						"onWorkDate", "isDel" });
		totalDao.update(oldAttendancePerson);
	}
	
	//getter和setter方法
    public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	
	
	
	
	
	//保存个人信息
	public void add(Person ps) {
		totalDao.save(ps);
	}
	//根据Id删除个人信息
	public void delById(int id) {
		Person ps = (Person) totalDao.getObjectById(Person.class, id);
		totalDao.delete(ps);
	}
	//根据对实体对象删除
	public void del(Person ps) {
		totalDao.delete(ps);
	}
	//根据对象修改个人信息
	public void update(Person ps) {
		totalDao.update(ps);
	}
	//查询所有跟人信息列表用List集合接收
	@SuppressWarnings("unchecked")
	public List<Person> queryAllPerson() {
		return totalDao.query("from Person", null);
	}
	//根据Id查询个人信息
	public Person getPersonById(int id) {
		return (Person) totalDao.getObjectById(Person.class, id);
	}
	//根据卡号，查询对应的Id
	@SuppressWarnings("rawtypes")
	public String getPersonIdBycardNo(String cardNo) {
		List list = totalDao.query("select id from Person p where p.cardNo = ?", cardNo);
		if (list != null && list.size() > 0) {
			return list.get(0)+"";
		}
		return null;
	}
	//根据Id查询对应的部门
	@SuppressWarnings("unchecked")
	public List<Person> queryPersonByDeptId(int id) {
		List<Person> list = totalDao.query("from Person p where p.deptId = ?", id);
		return list;
	}
	//根据Id查询员工姓名以及对应的部门名称
	@SuppressWarnings("rawtypes")
	public Object[] getPersonNameAndDept(int id) {
		List list = totalDao.query("select p.name,d.name from Person p,Department d where p.deptId = d.id and p.id = ?", id);
		Object[] obj = (Object[]) list.get(0);
		return obj;
	}
	
}
