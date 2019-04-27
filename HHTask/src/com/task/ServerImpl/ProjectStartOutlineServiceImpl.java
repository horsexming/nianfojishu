package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartOutlineService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartOutline;
import com.task.util.MKUtil;

public class ProjectStartOutlineServiceImpl implements ProjectStartOutlineService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartOutline p) {
		totalDao.save(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectStartOutline get(ProjectStart p) {
		String hql = "from ProjectStartOutline where root.id = ?";
		return (ProjectStartOutline) totalDao.getObjectByCondition(hql, p.getId());
	}

	@Override
	public void update(ProjectStartOutline p) {
		ProjectStartOutline pso = (ProjectStartOutline) totalDao.getObjectById(p.getClass(), p.getId());
		if(p.getProductGraphics() == null || p.getProductGraphics().length() <= 0){
			BeanUtils.copyProperties(p, pso, new String[]{"id","productGraphics", "root"});
		} else {
			MKUtil.deleteFile("projectStart", pso.getProductGraphics());
			BeanUtils.copyProperties(p, pso, new String[]{"id", "root"});
		}
		totalDao.update(pso);
	}

	@Override
	public ProjectStartOutline get(ProjectStartOutline p) {
		return (ProjectStartOutline) totalDao.getObjectById(p.getClass(), p.getId());
	}

}
