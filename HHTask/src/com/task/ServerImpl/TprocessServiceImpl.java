package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TprocessService;
import com.task.entity.Project;
import com.task.entity.Tdetail;
import com.task.entity.Tprocess;

public class TprocessServiceImpl implements TprocessService {

	private TotalDao totalDao;
	
	@Override
	public List<Tdetail> listDetail(Project p) {
		String hql = "from Tdetail where project.id = ?";
		List<Tdetail> list = totalDao.find(hql, new Object[]{p.getId()});
		return list;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Tdetail getDetail(Tdetail detail) {
		return (Tdetail) totalDao.get(Tdetail.class, detail.getId());
	}

	@Override
	public void add(List<Tprocess> processes) {
		for (int i = 0; i < processes.size(); i++) {
			if(processes.get(i).getEquipment().getId() == null){
				processes.get(i).setEquipment(null);
			}
			
			if(processes.get(i).getTooling().getId() == null){
				processes.get(i).setTooling(null);
			}
			
			totalDao.save(processes.get(i));
		}
	}

}
