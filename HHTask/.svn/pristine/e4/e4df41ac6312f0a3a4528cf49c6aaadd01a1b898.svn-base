package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.InsScopeService;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;

public class InsScopeServiceImpl implements InsScopeService {
	private TotalDao totalDao;

	@Override
	public List<InsScope> get(InsTemplate t) {
		InsTemplate t1 = (InsTemplate) totalDao.get(InsTemplate.class, t
				.getId());
		for (InsScope i : t1.getScope()) {
			i.getId();
		}
		return new ArrayList<InsScope>(t1.getScope());
	}

	@Override
	public InsScope findInsScope(Integer id) {
		return (InsScope) totalDao.getObjectById(InsScope.class, id);
	}

	@Override
	public boolean updateInsScope(InsScope insScope) {
		return totalDao.update(insScope);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
