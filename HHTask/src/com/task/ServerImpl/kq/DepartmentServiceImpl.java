package com.task.ServerImpl.kq;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.kq.IDepartmentService;
import com.task.entity.Department;

/**
 * @author 曾建森
 * @FileNam DepartmentServiceImpl.java
 * @Date 2012-10-9
 */
public class DepartmentServiceImpl implements IDepartmentService {
	
	private TotalDao totalDao;
	
	public void add(Department dt) {
		totalDao.save(dt);
	}
	public void delById(int id) {
		Department dt = (Department) totalDao.getObjectById(Department.class, id);
		totalDao.delete(dt);
	}
	public void del(Department dt) {
		totalDao.delete(dt);
	}
	public void update(Department dt) {
		totalDao.update(dt);
	}
	@SuppressWarnings("unchecked")
	public List<Department> queryAllDepartment() {
		return totalDao.query("from Department", null);
	}
	public Department getDepartmentById(int id) {
		return (Department) totalDao.query("from Department d where d.id = ?", id).get(0);
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
