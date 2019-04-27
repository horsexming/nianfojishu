package com.task.Server;

import java.util.List;

import com.task.entity.ProjectAuth;

public interface ProjectAuthService {
	
	/**
	 * 添加
	 */
	public void add(ProjectAuth auth);
	
	/**
	 * 添加全局管理员
	 * @param auth
	 */
	public void addGlobeAdmin(ProjectAuth auth);

	/**
	 * 列出全局管理员
	 * @return
	 */
	public List<ProjectAuth> listGlobeAdmin();

	/**
	 * 删除
	 * @param auth
	 */
	public void delete(ProjectAuth auth);

	/**
	 * 根据项目和ID,拿到此人的权限
	 * @param id
	 * @param id2
	 */
	public ProjectAuth get(Integer projectId, Integer userId);
	
	/**
	 * 是否有权限查看此处
	 * @param projectId 项目ID
	 * @param userId 人员ID
	 * @param authType 权限类型
	 * @return true为有权限查看
	 */
	public boolean isLook(Integer projectId, Integer userId, String authType);

}
