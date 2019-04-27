package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartResponsibleService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartResponsible;
import com.task.util.MKUtil;

public class ProjectStartResponsibleServiceImpl implements ProjectStartResponsibleService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartResponsible p) {
		totalDao.save(p);
	}

	@Override
	public ProjectStartResponsible get(ProjectStart p) {
		String hql = "from ProjectStartResponsible where root.id = ?";
		return (ProjectStartResponsible) totalDao.getObjectByCondition(hql, p.getId());
	}

	@Override
	public ProjectStartResponsible get(ProjectStartResponsible p) {
		return (ProjectStartResponsible) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public void update(ProjectStartResponsible p) {
		ProjectStartResponsible pso = (ProjectStartResponsible) totalDao.getObjectById(p.getClass(), p.getId());
		MKUtil.deleteFile("projectStart", pso.getResponsibility());
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
