package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartBudgetService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartBudget;

public class ProjectStartBudgetServiceImpl implements ProjectStartBudgetService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartBudget p) {
		totalDao.save(p);
	}

	@Override
	public void update(ProjectStartBudget p) {
		totalDao.update(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] getByStart(ProjectStartBudget p, int pageNo, int pageSize) {
		String hql = "from ProjectStartBudget p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, p.getRoot().getId());
		int count = totalDao.getCount(hql,p.getRoot().getId());
		Object[] o = { list, count };
		return o;
	}
	

	@Override
	public ProjectStartBudget get(ProjectStartBudget p) {
		return (ProjectStartBudget) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public int getCount(ProjectStart projectStart) {
		String hql = "from ProjectStartBudget p where p.root.id = ? ";
		return totalDao.getCount(hql, projectStart.getId());
	}

}
