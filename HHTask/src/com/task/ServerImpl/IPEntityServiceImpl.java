package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.IIPEntityService;
import com.task.entity.IPEntity;

public class IPEntityServiceImpl implements IIPEntityService {

	private TotalDao totalDao;

	public void add(IPEntity ip) {
		totalDao.save(ip);
	}

	public void del(IPEntity ip) {
		totalDao.delete(ip);
	}

	public IPEntity getIPEntityById(int id) {
		return (IPEntity) totalDao.getObjectById(IPEntity.class, id);
	}

	public void update(IPEntity ip) {
		totalDao.update(ip);
	}

	@SuppressWarnings("unchecked")
	public Object[] queryIPEntityByCondition(Map map, int pageNo, int pageSize) {
		List list = new ArrayList();
		String hql = "from IPEntity i where 1 = 1";
		if (map != null) {
			if (map.get("deptStr") != null) {
				hql += " and i.dept = '" + map.get("deptStr") + "'";
			}
			if (map.get("ipStr") != null) {
				hql += " and i.ip like '%" + map.get("ipStr") + "%'";
			}
			if (map.get("stateStr") != null) {
				hql += " and i.status = '" + map.get("stateStr") + "'";
			}
			if (map.get("nameStr") != null) {
				hql += " and i.name like '%" + map.get("nameStr") + "%'";
				;
			}
		}
		list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public void delIPEntityById(Integer id) {
		IPEntity ip = getIPEntityById(id);
		totalDao.delete(ip);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
