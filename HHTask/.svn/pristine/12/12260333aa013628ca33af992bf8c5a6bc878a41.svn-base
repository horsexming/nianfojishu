package com.task.Server;

import com.task.entity.ProjectStartUser;
import com.task.entity.ProjectTrack;
import com.task.entity.ProjectTrackRecord;

public interface ProjectTrackRecordService {

	/**
	 * 分页
	 * @param p
	 */
	void add(ProjectTrackRecord p);

	/**
	 * 前台分页
	 * @param p
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] getByTrack(ProjectTrackRecord p, int parseInt, int pageSize);

	/**
	 * 取对象
	 * @param p
	 * @return
	 */
	ProjectTrackRecord get(ProjectTrackRecord p);

	/**
	 * 项目负责人审核
	 * @param p
	 */
	void updateCheck(ProjectTrackRecord p);

	/**
	 * 获取到项目负责人
	 * @param p
	 * @return
	 */
	ProjectStartUser getBoss(ProjectTrackRecord p);

	public ProjectTrackRecord get(ProjectTrack p) ;

	void updateComplete(ProjectTrackRecord p);
}
