package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectService;
import com.task.entity.Project;
import com.task.entity.ProjectTrack;

public class ProjectServiceImpl implements ProjectService {
	private TotalDao totalDao;

	@Override
	public void add(Project p) {
		p.setStaring("人员添加中..");
		totalDao.save(p);
	}

	@Override
	public void delete(Project p) {
		totalDao.delete(p);
	}

	@Override
	public Project get(Project project) {
		return (Project) totalDao.getObjectById(project.getClass(), project.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getList(int parseInt, int pageSize) {
		String hql = "from Project order by closed,id";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(Project p) {
		totalDao.update(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void closed(Project project) {
		String hql = "from ProjectTrack where root.id = ?";
		ProjectTrack track = (ProjectTrack) totalDao.getObjectByCondition(hql, project.getId());
		if(track == null || track.getClosed()){
			throw new RuntimeException("项目还没有进行完或者已经关闭");
		}
		track.setClosed(true);
		project = (Project) totalDao.getObjectById(project.getClass(), project.getId());
		project.setClosed(true);
		project.setStaring("");
		
	}

}
