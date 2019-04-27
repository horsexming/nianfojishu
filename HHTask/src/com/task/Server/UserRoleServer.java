package com.task.Server;

import java.util.List;

import com.task.entity.ModuleFunction;
import com.task.entity.UserRole;
import com.task.entity.Users;

public interface UserRoleServer {

	String addrole(UserRole role);

	String deleterole(Integer id);

	List<UserRole> findAllrole();

	UserRole findbyIdrole(Integer id);

	String updaterole(UserRole role);

	List<Users> findRoleuser(Integer id);

	Object[] findAllUsers();

	List<ModuleFunction> findRolemoduleFunction(Integer id);

	Object[] findUsersByDept(String deptName);

	/**
	 * 修改角色名称  简介
	 * @param userRole
	 * @return
	 */
	String updateUserRole(UserRole userRole);

	
}
