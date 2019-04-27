package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TconsumptionService;
import com.task.entity.Tconsumption;

public class TconsumptionServiceImpl implements TconsumptionService {
	
	private TotalDao totalDao; 

	@Override
	public void addAll(List<Tconsumption> consumptions) {
		for (int i = 0; i < consumptions.size(); i++) {
			totalDao.save(consumptions.get(i));
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
