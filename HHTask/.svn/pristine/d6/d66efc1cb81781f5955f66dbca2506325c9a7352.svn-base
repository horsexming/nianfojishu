package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartClaimService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartClaim;
import com.task.util.MKUtil;

public class ProjectStartClaimServiceImpl implements ProjectStartClaimService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartClaim p) {
		totalDao.save(p);
	}

	@Override
	public ProjectStartClaim get(ProjectStart p) {
		String hql = "from ProjectStartClaim p where p.root.id = ?";
		return (ProjectStartClaim) totalDao.getObjectByCondition(hql, p.getId());
	}

	@Override
	public ProjectStartClaim get(ProjectStartClaim p) {
		return (ProjectStartClaim) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public void update(ProjectStartClaim p) {
		ProjectStartClaim pso = (ProjectStartClaim) totalDao.getObjectById(p.getClass(), p.getId());
		MKUtil.deleteFile("projectStart", pso.getClaimName());
		BeanUtils.copyProperties(p, pso, new String[] { "id", "root" });
		totalDao.update(pso);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
