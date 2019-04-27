package com.task.ServerImpl.zhaobiao;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.zhaobiao.ClauseServer;
import com.tast.entity.zhaobiao.ClauseFather;
import com.tast.entity.zhaobiao.ClauseSon;
import com.tast.entity.zhaobiao.Nianlilv;
import com.tast.entity.zhaobiao.Zhtoubiao;

public class ClauseServerImpl  implements ClauseServer{
	private TotalDao totalDao;
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] listtianxiejiepai(ClauseFather clauseFather,
			Integer parseInt, Integer pageSize) {
		if (clauseFather == null) {
			clauseFather = new ClauseFather();
		}
		String hql = totalDao.criteriaQueries(clauseFather, null, null);
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public void addClause(ClauseFather c) {
		totalDao.save(c);
	}

	@Override
	public void deleteclauseFather(ClauseFather clauseFather) {
		// TODO Auto-generated method stub
		totalDao.delete(clauseFather);
	}
	public Object getByIdObject(Object o,Integer id) {
		return (Object) totalDao.getObjectById(Object.class, id);
	}
	public void updateClause(ClauseFather c) {
		totalDao.update(c);
	}
	public ClauseFather getByIdClauseFather(ClauseFather c) {
		return (ClauseFather) totalDao.getObjectById(ClauseFather.class,c.getId());
	}
	public  ClauseSon ByIdclauseSon(Integer id){
		return (ClauseSon) totalDao.getObjectById(ClauseSon.class,id);
	}

	@Override
	public Object[] listclauseFatherSon(Integer id, ClauseSon clauseSon,
			int parseInt, int pageSize) {
		if (clauseSon == null) {
			clauseSon = new ClauseSon();
		}
		String hql = totalDao.criteriaQueries(clauseSon, null, null)+" and  fatherId="+id;
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public void addClauseSon(ClauseSon s) {
		totalDao.save(s);
	}
	public void deleteclauseSon(ClauseSon s) {
		totalDao.delete(s);
	}

	public void updateClauseSon(ClauseSon clauseSon) {
		// TODO Auto-generated method stub
		totalDao.update(clauseSon);
	}
	
	
}
