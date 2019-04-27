package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.ModuleFunctionServer;
import com.task.Server.UserRoleServer;
import com.task.entity.ModuleFunction;
import com.task.entity.UserRole;
import com.task.entity.Users;

public class UserRoleServerImpl implements UserRoleServer {
	ModuleFunctionServer moduleFunctionServer;
	TotalDao totalDao;

	@Override
	public String addrole(UserRole role) {
		totalDao.save(role);
		return null;
	}

	@Override
	public String deleterole(Integer id) {
		UserRole role = (UserRole) totalDao.getObjectById(UserRole.class, id);
		totalDao.delete(role);
		return null;
	}

	@Override
	public List<UserRole> findAllrole() {
		return (List<UserRole>) totalDao.query("from UserRole", null);
	}

	@Override
	public UserRole findbyIdrole(Integer id) {
		return (UserRole) totalDao.get(UserRole.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String updaterole(UserRole role) {
		boolean bool = true;
		if (role != null) {
			Set<ModuleFunction> SetMFNew = new HashSet<ModuleFunction>(); // 修改的模块功能
			Set<Users> SetUsersNew = new HashSet<Users>(); // 修改的用户

			if (role.getModuleFunction() != null) {

				// update role
				Set<ModuleFunction> SetPage = role.getModuleFunction();// 页面提交的模块id
				for (ModuleFunction moduleFunction : SetPage) {
					ModuleFunction mf = (ModuleFunction) totalDao
							.getObjectById(ModuleFunction.class, moduleFunction
									.getId());
					SetMFNew.add(mf);
				}
				UserRole role2 = (UserRole) totalDao.get(UserRole.class, role
						.getId());
				Set<Users> role_usersSet = role2.getUsers();
				//角色旧模块
				Set<ModuleFunction> role_ModuleFunctionsSet = role2
						.getModuleFunction();
				role2.setModuleFunction(SetMFNew);
				totalDao.update(role2);




				//减少的模块
				Set<ModuleFunction> lessSet = new HashSet<ModuleFunction>();
				//
				Set<ModuleFunction> moreSet = new HashSet<ModuleFunction>();

				for (ModuleFunction moduleFunction : role_ModuleFunctionsSet) {
					if (!SetMFNew.contains(moduleFunction)) {
						lessSet.add(moduleFunction);
					}
				}
				for (ModuleFunction moduleFunction : SetMFNew) {
					//旧Set中是否包含新Set中功能
					if (!role_usersSet.contains(moduleFunction)) {
						moreSet.add(moduleFunction);
					}
				}
				if(lessSet.size()>0){
					//绑定人员=之前自生绑定人员-这次减少的
					Set<Users> usersLessSet=new HashSet<Users>();
					for (ModuleFunction m:lessSet) {
						usersLessSet=m.getUsers();
						usersLessSet.removeAll(role_usersSet);
						m.setUsers(usersLessSet);
						totalDao.update(m);
					}

				}
				if(moreSet.size()>0){
					//
					Set<Users> usersMoreSet=new HashSet<Users>();
					for (ModuleFunction m:moreSet) {
						usersMoreSet=m.getUsers();
						usersMoreSet.addAll(role_usersSet);
						m.setUsers(usersMoreSet);
						totalDao.update(m);
					}

				}

			} else if (role.getUsers() != null) {

				Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前减少的绑定用户
				Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
				Set<Users> SetUsersPage = role.getUsers();// 页面传ID
				for (Users users : SetUsersPage) {
					Users users2 = (Users) totalDao.getObjectById(Users.class,
							users.getId());
					SetUsersNew.add(users2);
				}
				// update role
				UserRole role2 = (UserRole) totalDao.get(UserRole.class, role
						.getId());
				Set<Users> role_usersSet = role2.getUsers();
				Set<ModuleFunction> role_ModuleFunctionsSet = role2
						.getModuleFunction();
				role2.setUsers(SetUsersNew);
				totalDao.update(role2);

				//已有的用户和页面Id比
				for (Users users : role_usersSet) {
					if (!SetUsersNew.contains(users)) {
						lessSet.add(users);//减少的绑定用户
					}
				}
				for (Users users : SetUsersNew) {
					if (!role_usersSet.contains(users)) {
						moreSet.add(users);//本次新增用户
					}
				}
				Set<ModuleFunction> ModuleFunctionList = new HashSet<ModuleFunction>();
				if (null != role_ModuleFunctionsSet
						&& !role_ModuleFunctionsSet.isEmpty()) {
					for (ModuleFunction mf : role_ModuleFunctionsSet) {
						ModuleFunctionList.add(mf);
					}
				}
				//增加的人员 添加绑定 //要加上以前绑定的功能
				if(moreSet.size()>0){
					Set<ModuleFunction> ModuleFunctionMoreList = new HashSet<ModuleFunction>();
					for (Users u:moreSet) {
						ModuleFunctionMoreList=u.getModuleFunction();
						ModuleFunctionMoreList.addAll(ModuleFunctionList);
						u.setModuleFunction(ModuleFunctionMoreList);
						totalDao.update(u);
					}
				}
				//减少的人员 减少功能
				if(lessSet.size()>0){
					Set<ModuleFunction> ModuleFunctionLessList = new HashSet<ModuleFunction>();
					for (Users u:lessSet) {
						ModuleFunctionLessList=u.getModuleFunction();
						ModuleFunctionLessList.removeAll(ModuleFunctionList);
						u.setModuleFunction(ModuleFunctionLessList);
						totalDao.update(u);
					}
				}

			} else {
				return null;
			}

		}
		return null;
	}

	// 通过Id查询模块功能
	public ModuleFunction findMfById(Integer id) {
		if (id != null && id > 0) {
			return (ModuleFunction) totalDao.getObjectById(
					ModuleFunction.class, id);
		}
		return null;
	}

	@Override
	public List<Users> findRoleuser(Integer id) {
		String hql = "select u from Users u join u.userRole r where r.id=?";
		return (List<Users>) totalDao.query(hql, id);
	}

	@Override
	public List<ModuleFunction> findRolemoduleFunction(Integer id) {
		String hql = "select mf from ModuleFunction mf join mf.userRole r where r.id=?";
		return (List<ModuleFunction>) totalDao.query(hql, id);

	}

	@Override
	public Object[] findUsersByDept(String deptName) {
		String hql = null;
		if (deptName.indexOf(",") < 0) {
			hql = "select deptId,id,name,code from Users where dept=? and onWork not in ('离职','内退','离职中','退休') and dept not in('内退','病休')";
			List<Object> ls = totalDao.query(hql, deptName);
			Object[] o = { ls };
			return o;
		} else {
			String[] deptNames = deptName.split(",");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < deptNames.length; i++) {
				if (i == 0) {
					sb.append("'" + deptNames[i] + "'");
				} else {
					sb.append(",'" + deptNames[i] + "'");
				}
			}
			hql = " select deptId,id,name from Users where dept in ("
					+ sb.toString() + ")  and onWork not in ('离职','内退','离职中','退休') and dept not in('内退','病休')";
			List<Object> ls = totalDao.query(hql);
			Object[] o = { ls };
			return o;
		}

	}

	@Override
	public Object[] findAllUsers() {
		String hql = "select deptId,id,name from Users where deptId is not null and deptId <>''";
		List<Object> ls = totalDao.query(hql, null);
		Object[] o = { ls };
		return o;

	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String updateUserRole(UserRole userRole) {
		UserRole r=findbyIdrole(userRole.getId());
		r.setName(userRole.getName());
		r.setDescription(userRole.getDescription());
		Boolean b=totalDao.update(r);
		return b.toString();
	}

}
