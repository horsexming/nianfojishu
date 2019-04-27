package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ManufacturingPropService;
import com.task.entity.Manufacturing;
import com.task.entity.ManufacturingProp;
import com.task.entity.Users;

public class ManufacturingPropServiceImpl implements ManufacturingPropService {
	private TotalDao totalDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<ManufacturingProp> find(ManufacturingProp m) {
		String hql = "from ManufacturingProp where parent.id = ?";
		List list = totalDao.find(hql, new Object[]{m.getParent().getId()});
		return list;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void add(List<ManufacturingProp> props, List<Manufacturing> list, Users u) {
		for (ManufacturingProp manufacturingProp : props) {
			int i = manufacturingProp.getF_manu_id();
			Manufacturing m = new Manufacturing();
			m.setId(i);
			manufacturingProp.setParent(m);
			manufacturingProp.setUsername(u.getName());
			manufacturingProp.setUsercode(u.getCode());
			totalDao.save(manufacturingProp);
		}
		for (int i = 0; i < list.size(); i++) {
			Manufacturing m = (Manufacturing) totalDao.get(Manufacturing.class, list.get(i).getServerId());
			m.setCzg(list.get(i).getCzg());
			m.setJcpic(list.get(i).getJcpic());
			m.setQuantity(list.get(i).getQuantity());
			totalDao.update(m);
		}
	}

}
