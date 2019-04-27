package com.task.ServerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.IntegralsourceServerDao;
import com.task.entity.Integral;
import com.task.entity.Integralsource;

public class IntegralsourceServerDaoImpl implements IntegralsourceServerDao{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	

	@Override
	public List<Integralsource> findAllByPage(int pageNo, int pageSize) {
		String hql="from Integralsource";
		return (List<Integralsource>)totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public boolean delIntegralsource(Integralsource is) {
		if(is!=null){
			return totalDao.delete(is);
		}
		return false;
	}

	@Override
	public boolean updataIs(Integralsource is) {
		if(is!=null){
			return totalDao.update(is);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findIntegralByCondition(Integralsource is ,
			int pageNo, int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(is, null);
		int count=totalDao.getCount(hql);
		List<Integralsource> IntegralList=(List<Integralsource>)totalDao.findAllByPage(hql+" order by addtime desc", pageNo, pageSize);
		map.put(1, IntegralList);
		map.put(2, count);
		return map;
		
	}

	@Override
	public int getcont() {
		String hql="from Integralsource";
		 return totalDao.getCount(hql);
	}

	@Override
	public boolean addIntegral(Integer integral) {
		
		return false;
	}

}
