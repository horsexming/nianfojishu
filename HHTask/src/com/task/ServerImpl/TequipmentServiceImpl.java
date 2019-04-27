package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TequipmentService;
import com.task.entity.Tequipment;

public class TequipmentServiceImpl implements TequipmentService {
	
	private TotalDao totalDao;

	@Override
	public void addAll(List<Tequipment> equipments) {
		for (int i = 0; i < equipments.size(); i++) {
			totalDao.save(equipments.get(i));
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List selector(Tequipment equipment) {
		String sql = "select id,name from ta_Tequipment t where t.f_project_id = ?";
		List query = totalDao.createQuerySelect(null, sql, new Object[]{equipment.getProject().getId()});
		return query;
	}

}
