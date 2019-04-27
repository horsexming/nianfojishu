package com.task.Server;

import java.util.List;

import com.task.entity.Project;
import com.task.entity.ProjectUser;
import com.task.entity.Users;

public interface ProjectUserService {
	
	/**
	 * 添加 
	 * @param user
	 */
	public void add(ProjectUser user);
	
	/**
	 * 更新
	 * @param user
	 */
	public void update(ProjectUser user);
	
	/**
	 * 列出某个项目下的所有人员
	 * @param project
	 * @return
	 */
	public List<ProjectUser> list(Project project);
	
	/**
	 * 删除
	 * @param user
	 */
	public void delete(ProjectUser user);

	/**
	 * 根据id取出对象
	 * @param user
	 * @return
	 */
	public ProjectUser get(ProjectUser user);

	/**
	 * 填充selector
	 * @param user
	 * @return
	 */
	public List<Users> toSelector(Integer projectId);

}
