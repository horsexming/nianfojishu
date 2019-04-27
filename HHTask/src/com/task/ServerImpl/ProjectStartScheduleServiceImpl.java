package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartScheduleService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartSchedule;

public class ProjectStartScheduleServiceImpl implements ProjectStartScheduleService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartSchedule p) {
		totalDao.save(p);
	}

	@Override
	public ProjectStartSchedule get(ProjectStartSchedule p) {
		return (ProjectStartSchedule) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public Object[] getByStart(ProjectStartSchedule p, int pageNo, int pageSize) {
		String hql = "from ProjectStartSchedule p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, p.getRoot().getId());
		int count = totalDao.getCount(hql,p.getRoot().getId());
		Object[] o = { list, count };
		return o;
	}

	@Override
	public int getCount(ProjectStart projectStart) {
		String hql = "from ProjectStartSchedule p where p.root.id = ? ";
		return totalDao.getCount(hql, projectStart.getId());
	}

	@Override
	public void update(ProjectStartSchedule p) {
		totalDao.update(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
