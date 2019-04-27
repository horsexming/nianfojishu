
package com.task.Server;

import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartUser;

public interface ProjectStartUserService {
	
	/**
	 * 添加
	 * @param u
	 */
	public void add(ProjectStartUser u);

	/**
	 * 前台列表显示
	 * @param p
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] getByStart(ProjectStartUser p, int parseInt, int pageSize);

	/**
	 * 取一个对象
	 * @param p
	 * @return
	 */
	public ProjectStartUser get(ProjectStartUser p);

	/**
	 * 修改
	 * @param p
	 */
	public void update(ProjectStartUser p);

	/**
	 * 查询总数
	 * @param projectStart
	 * @return
	 */
	public int getCount(ProjectStart projectStart);
	
	/**
	 * 获取项目负责人
	 * @param p
	 * @return
	 */
	public ProjectStartUser getBoss(ProjectStart p);

	public void delete(ProjectStartUser p);
	
}
