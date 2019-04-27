package com.task.ServerImpl.gzbj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.gzbj.GzstoreUseLogServer;
import com.task.entity.gzbj.GzstoreUseLog;

public class GzstoreUseLogServerImpl implements GzstoreUseLogServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public Map<Integer, Object> findGzstoreUseLogsByCondition(
			GzstoreUseLog gzstoreUseLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (gzstoreUseLog == null) {
			gzstoreUseLog = new GzstoreUseLog();
		}
		String hql = totalDao.criteriaQueries(gzstoreUseLog, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public GzstoreUseLog getById(Integer id) {
		// TODO Auto-generated method stub
		return (GzstoreUseLog) totalDao.getObjectById(GzstoreUseLog.class, id);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		GzstoreUseLog g=getById(id);
		if(g!=null){
			return totalDao.delete(g);
		}
		return false;
	}
}
