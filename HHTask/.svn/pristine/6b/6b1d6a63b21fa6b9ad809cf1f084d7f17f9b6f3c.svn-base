package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.GroupShiZhiServer;
import com.task.entity.shizhi.GroupShiZhi;

public class GroupShiZhiServerImpl implements GroupShiZhiServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(GroupShiZhi groupShiZhi) {
		// TODO Auto-generated method stub
		return totalDao.save(groupShiZhi);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		GroupShiZhi sscore = getById(id);
		if (sscore != null) {
			return totalDao.delete(sscore);
		}
		return false;
	}

	@Override
	public GroupShiZhi getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(GroupShiZhi.class, id);
			if (o != null) {
				return (GroupShiZhi) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(GroupShiZhi GroupShiZhi) {
		// TODO Auto-generated method stub
			return totalDao.update(GroupShiZhi);
	}

	@Override
	public Map<Integer, Object> findGroupShiZhisByCondition(
			GroupShiZhi groupShiZhi, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (groupShiZhi == null) {
			groupShiZhi = new GroupShiZhi();
		}
		String hql = totalDao.criteriaQueries(groupShiZhi, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<GroupShiZhi> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from GroupShiZhi");
		if (all.size() > 0) {
			List<GroupShiZhi> groupShiZhis = new ArrayList<GroupShiZhi>();
			for (Object o : all) {
				groupShiZhis.add((GroupShiZhi) o);
			}
			return groupShiZhis;
		}

		return null;
	}

}
