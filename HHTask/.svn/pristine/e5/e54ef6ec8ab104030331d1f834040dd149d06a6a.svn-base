package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartTechnicalService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartTechnical;
import com.task.util.MKUtil;

public class ProjectStartTechnicalServiceImpl implements ProjectStartTechnicalService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartTechnical p) {
		totalDao.save(p);
	}

	@Override
	public ProjectStartTechnical get(ProjectStart p) {
		String hql = "from ProjectStartTechnical where root.id = ?";
		return (ProjectStartTechnical) totalDao.getObjectByCondition(hql, p.getId());
	}

	@Override
	public ProjectStartTechnical get(ProjectStartTechnical p) {
		return (ProjectStartTechnical) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public void update(ProjectStartTechnical p) {
		ProjectStartTechnical pso = (ProjectStartTechnical) totalDao.getObjectById(p.getClass(), p.getId());
		MKUtil.deleteFile("projectStart", pso.getTechnicalProgram());
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
