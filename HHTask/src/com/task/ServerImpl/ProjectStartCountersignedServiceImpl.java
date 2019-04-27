package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartCountersignedService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartCountersigned;
import com.task.util.MKUtil;

public class ProjectStartCountersignedServiceImpl implements ProjectStartCountersignedService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartCountersigned p) {
		totalDao.save(p);
	}

	@Override
	public ProjectStartCountersigned get(ProjectStart p) {
		String hql = "from ProjectStartCountersigned where root.id = ?";
		return (ProjectStartCountersigned) totalDao.getObjectByCondition(hql, p.getId());
	}

	@Override
	public ProjectStartCountersigned get(ProjectStartCountersigned p) {
		return (ProjectStartCountersigned) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public void update(ProjectStartCountersigned p) {
		ProjectStartCountersigned pso = (ProjectStartCountersigned) totalDao.getObjectById(p.getClass(), p.getId());
		MKUtil.deleteFile("projectStart", pso.getMinutes());
		BeanUtils.copyProperties(p, pso, new String[]{"id", "root"});
		totalDao.update(pso);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
