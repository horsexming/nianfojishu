package com.task.Server;

import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartClaim;

public interface ProjectStartClaimService {

	/**
	 * 添加
	 * @param p
	 */
	public void add(ProjectStartClaim p);

	/**
	 * 通过项目启动书的ID,拿到技术方案
	 * @param p
	 * @return
	 */
	public ProjectStartClaim get(ProjectStart p);
	
	/**
	 * 修改
	 * @param p
	 */
	public void update(ProjectStartClaim p);

	/**
	 * 通过ID拿到对象
	 * @param p
	 * @return
	 */
	public ProjectStartClaim get(ProjectStartClaim p);

}
