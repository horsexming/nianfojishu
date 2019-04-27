package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Admin;
import com.task.entity.Users;

public interface AdminServer {

	public Admin login(Admin admin);// 登录
	public Users login2(Admin admin);//登陸

	public boolean update(Admin admin); // 修改密码

	public Admin findAdminById(Integer id); // 根据Id查询用户信息
	/**
	 * 根据超级管理员条件查询管理员列表
	 * @param superAdmin
	 * @param cpage
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Map<Integer, Object> findAdminsByCondition(Admin superAdmin,
			int cpage, int pageSize, String sql);
	/**
	 * 通过id获取管理员对象
	 * @param id
	 * @return
	 */
	public Admin getById(Integer id);
	/**
	 * 通过id删除管理员对象
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	/**
	 * 添加管理员对象
	 * @param superAdmin
	 * @return
	 */
	public boolean add(Admin superAdmin);
	/**
	 * 获取所有的管理员名称
	 * @return
	 */
	public List<String> getAllName();
}
