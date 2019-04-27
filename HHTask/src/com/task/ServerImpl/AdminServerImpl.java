package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.AdminServer;
import com.task.entity.Admin;
import com.task.entity.Users;
import com.task.util.MD5;

/**
 * 管理员server层
 * 
 * @author 刘培
 * 
 */
public class AdminServerImpl implements AdminServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 后台登录
	public Admin login(Admin admin) {
		if (admin != null) {
			String hql = "from Admin where adminName=? and adminPassword=?";
			MD5 md5 = new MD5();
			String mdsPassword = md5
					.getMD5(admin.getAdminPassword().getBytes());// 密码MD5转换
			admin = (Admin) totalDao.getObjectByCondition(hql, admin
					.getAdminName(), mdsPassword);
			if (admin != null) {
				admin.setCount(admin.getCount() + 1);
				admin.setLastLogin(admin.getNowLogin());
				admin.setNowLogin(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));
				totalDao.update(admin);
				return admin;
			}
		}
		return null;
	}
	// 后台登录
	public Users login2(Admin admin) {
		String hql = "from Users where backStage is not null and backStage>0 and code=?";
		Users users = (Users) totalDao.getObjectByCondition(hql, admin.getAdminName());
		if(users!=null&&users.getPassword()!=null){
			String pwd=users.getPassword().getPassword();
			MD5 md5=new MD5();
			if(pwd!=null&&md5.isEquals(admin.getAdminPassword(), pwd)){
				return users;
			}
		}
		return null;
	}
	// 修改密码
	public boolean update(Admin admin) {
		if (admin != null) {
			return totalDao.update(admin);

		}
		return false;
	}

	// 根据Id查询用户信息
	public Admin findAdminById(Integer id) {
		if (id != null && id > 0) {
			return (Admin) totalDao.getObjectById(Admin.class, id);
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findAdminsByCondition(Admin superAdmin,
			int cpage, int pageSize, String sql) {
		// TODO Auto-generated method stub
			if (superAdmin == null) {
				superAdmin = new Admin();
			}
			String hql = totalDao.criteriaQueries(superAdmin, sql, null);
			int count = totalDao.getCount(hql);
			List objs = totalDao.findAllByPage(hql, cpage, pageSize);
				
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, objs);
			map.put(2, count);
			return map;
	}
	@Override
	public Admin getById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(Admin.class, id);
		if (o != null) {
			Admin superAdmin = (Admin) o;
			return superAdmin;
		}
		return null;
	}


	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		Object o = getById(id);
		if (o != null) {
			Admin admin = (Admin) o;
			return totalDao.delete(admin);
		}
		return false;
	}
	@Override
	public boolean add(Admin superAdmin) {
		// TODO Auto-generated method stub
		return totalDao.save(superAdmin);
	}

	@Override
	public List<String> getAllName() {
		// TODO Auto-generated method stub
		List<Admin> all=totalDao.query("from Admin");
		if(all.size()>0){
			List<String> names=new ArrayList<String>();
			for(Admin admin:all){
				names.add(admin.getAdminName());
			}
			return names;
		}
		return null;
	}
}
