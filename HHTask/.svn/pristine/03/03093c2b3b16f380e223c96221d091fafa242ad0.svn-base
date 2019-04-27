package com.task.Server;

import java.util.List;

import com.task.entity.ProjectLogin;
import com.task.entity.ProjectRecord;
import com.task.entity.Users;

/***
 * 项目登录server层
 * 
 * @author 刘培
 * 
 */
public interface ProjectRecordServer {

	public boolean addProRecord(ProjectRecord proRecord);// 添加项目登录记录

	public Object[] findAllProRecord(ProjectRecord proRecord, int pageNo,
			int pageSize); // 查询所有登录网站记录信息(分页、条件查询)

	public ProjectRecord findPrByProjectName(String projectName); // 查询项目名称是否重复

	public boolean delProRecord(ProjectRecord proRecord); // 删除登录网站

	public ProjectRecord findProRecordById(Integer id); // 根据id查询登录网站

	public boolean updateProRecord(ProjectRecord proRecord); // 修改登录网站

	public List<ProjectRecord> findAllProRecord();// 查询所有的登录网站信息

	public ProjectLogin findProjectLogin(Integer userId, ProjectRecord proRecord); // 通过登录网站以及登录用户信息查询登录信息

	public boolean addProjectLogin(ProjectLogin projectLogin); // 添加网站登录信息

	public List<ProjectLogin> findProjectLoginByUser(Users user);// 查询用户已绑定网站登录信息

	public boolean delProjectLogin(ProjectLogin projectLogin); // 删除用户已绑定网站登录信息

	public ProjectLogin findProjectLoginById(Integer id); // 根据ID查询登录信息

	public boolean updateProjectLogin(ProjectLogin projectLogin); // 修改用户已绑定网站登录信息
}
