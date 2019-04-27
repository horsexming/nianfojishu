package com.task.Server;

import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartTechnical;

public interface ProjectStartTechnicalService {

	/**
	 * 添加
	 * @param p
	 */
	public void add(ProjectStartTechnical p);

	/**
	 * 通过项目启动书的ID,拿到技术方案
	 * @param p
	 * @return
	 */
	public ProjectStartTechnical get(ProjectStart p);
	
	/**
	 * 修改
	 * @param p
	 */
	public void update(ProjectStartTechnical p);

	/**
	 * 通过ID拿到对象
	 * @param p
	 * @return
	 */
	public ProjectStartTechnical get(ProjectStartTechnical p);

}
