package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TtoolingService;
import com.task.entity.Ttooling;

public class TtoolingServiceImpl implements TtoolingService {
	private TotalDao totalDao;

	@Override
	public void addAll(List<Ttooling> toolings) {
		for (int i = 0; i < toolings.size(); i++) {
			totalDao.save(toolings.get(i));
		}
	} 

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List selector(Ttooling tooling) {
		String sql = "select id,name from ta_Ttooling t where t.f_project_id=?";
		return totalDao.createQuerySelect(null, sql, new Object[]{tooling.getProject().getId()});
	}

}
