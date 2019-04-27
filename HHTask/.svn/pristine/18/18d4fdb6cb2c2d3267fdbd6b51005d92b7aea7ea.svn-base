package com.task.ServerImpl.checktype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.checktype.CheckTypeServer;
import com.task.entity.checktype.CheckType;
import com.task.entity.codetranslation.CodeTranslation;

public class CheckTypeServerImpl implements CheckTypeServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean delete(CheckType checkType) {
		return totalDao.delete(checkType);
	}

	@Override
	public Map<Integer, Object> findAll(CheckType checkType, int pageNo,
			int pageSize) {
		if (checkType == null) {
			checkType= new CheckType();
		}
		String hql = null;
		hql = totalDao.criteriaQueries(checkType,null);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	public Map<Integer, Object> findAll1(CheckType checkType, int pageNo, int pageSize,String type){
		if (checkType == null) {
			checkType= new CheckType();
		}
		String hql = null;
		String sql = "type='"+type+"'";
		hql = totalDao.criteriaQueries(checkType,sql,null);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	

	@Override
	public CheckType findById(Integer id) {
		return (CheckType)totalDao.get(CheckType.class,id);
	}

	@Override
	public boolean save(CheckType checkType) {
		return totalDao.save(checkType);
	}

	@Override
	public boolean update(CheckType checkType) {
		return totalDao.update(checkType);
	}
	
	

}
