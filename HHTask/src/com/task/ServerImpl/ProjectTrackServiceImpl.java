package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectTrackService;
import com.task.entity.Project;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectTrack;

public class ProjectTrackServiceImpl implements ProjectTrackService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectTrack p) {
		String hql = "from ProjectStart where root.id = ?";
		ProjectStart p2 = (ProjectStart) totalDao.getObjectByCondition(hql, p.getRoot().getId());
		Project pro = p2.getRoot();
		pro.setStaring("项目跟踪进行中");
		p2.setClosed(true);

		p.setClosed(false);
		totalDao.save(p);
	}

	@Override
	public void updateClose(ProjectTrack p) {
		ProjectTrack p2 = (ProjectTrack) totalDao.getObjectById(p.getClass(), p.getId());
		p2.setClosed(true);
	}

	@Override
	public ProjectTrack get(Project root) {
		String hql = "from ProjectTrack where root.id = ?";
		return (ProjectTrack) totalDao.getObjectByCondition(hql, root.getId());
	}
	
	@Override
	public ProjectTrack get(ProjectTrack root){
		return (ProjectTrack) totalDao.getObjectById(root.getClass(), root.getId());
	} 


	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
