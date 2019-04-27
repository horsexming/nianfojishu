package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartService;
import com.task.entity.Project;
import com.task.entity.ProjectQuotationList;
import com.task.entity.ProjectStart;
//import com.task.entity.ProjectTrack;
import com.task.entity.ProjectTrack;

public class ProjectStartServiceImpl implements ProjectStartService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStart p) {
		String hql = "from ProjectQuotationList where root.id = ?";
		ProjectQuotationList pq = (ProjectQuotationList) totalDao.getObjectByCondition(hql, p.getRoot().getId());
		Project pro = pq.getRoot();
		pro.setStaring("项目启动书进行中");
		pq.setClosed(true);
		p.setClosed(false);
		totalDao.save(p);
	}

	@Override
	public void updateClose(ProjectStart p) {
		ProjectStart p2 = (ProjectStart) totalDao.getObjectById(p.getClass(), p.getId());
		p2.setClosed(true);
		totalDao.update(p2);
		//下面流程继续开始
		ProjectTrack pt = new ProjectTrack();
		pt.setRoot(p2.getRoot());
		pt.setClosed(false);
		totalDao.save(pt);
	}

	@Override
	public ProjectStart get(Project root) {
		String hql = "from ProjectStart where root.id = ?";
		return (ProjectStart) totalDao.getObjectByCondition(hql, root.getId());
	}


	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectStart get(ProjectStart p) {
		return (ProjectStart) totalDao.getObjectById(p.getClass(), p.getId());
	}

}
