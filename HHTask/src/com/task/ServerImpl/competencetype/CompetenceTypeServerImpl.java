package com.task.ServerImpl.competencetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.competencetype.CompetenceTypeServer;
import com.task.entity.CompetenceType;

public class CompetenceTypeServerImpl implements CompetenceTypeServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean delete(CompetenceType competenceType) {
		return totalDao.delete(competenceType);
	}

	@Override
	public Map<Integer, Object> findAll(CompetenceType competenceType,
			int pageNo, int pageSize) {
		if (competenceType == null) {
			competenceType= new CompetenceType();
		}
	
		String hql = totalDao.criteriaQueries(competenceType,null);
		hql+=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public CompetenceType findById(Integer id) {
		// TODO Auto-generated method stub
		return (CompetenceType)totalDao.get(CompetenceType.class,id);
	}

	@Override
	public boolean save(CompetenceType competenceType) {
		// TODO Auto-generated method stub
		return  totalDao.save(competenceType);
	}

	@Override
	public boolean update(CompetenceType competenceType) {
		// TODO Auto-generated method stub
		return totalDao.update(competenceType);
	}

}
