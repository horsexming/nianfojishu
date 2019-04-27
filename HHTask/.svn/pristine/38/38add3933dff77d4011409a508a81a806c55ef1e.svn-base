package com.task.Server;

import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartBudget;

/**
 * 项目预算
 * @author 马凯
 *
 */
public interface ProjectStartBudgetService {
	/**
	 * 添加
	 * @param p
	 */
	public void add(ProjectStartBudget p);
	
	/**
	 * 修改
	 * @param p
	 */
	public void update(ProjectStartBudget p);

	/**
	 * 分页查询
	 * @param p
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] getByStart(ProjectStartBudget p, int parseInt, int pageSize);

	/**
	 * 获得某个对象
	 * @param p
	 */
	public ProjectStartBudget get(ProjectStartBudget p);

	/**
	 * 通过项目启动书,拿到预算的数量
	 * @param projectStart
	 * @return
	 */
	public int getCount(ProjectStart projectStart);
}
