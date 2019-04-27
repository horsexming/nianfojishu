package com.task.ServerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.biff.XFRecord;

import com.task.Dao.TotalDao;
import com.task.Server.XiaoFeiServerDao;
import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.XiaoFei;

public class XiaoFeiServerDaoImpl implements XiaoFeiServerDao{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	

	@Override
	public boolean delXiaoFei(XiaoFei xf) {
		if(xf!=null){
			return totalDao.delete(xf);
		}
		return false;
	}

	@Override
	public List<XiaoFei> findAllByPage(int pageNo, int pageSize) {
		String hql="from XiaoFei";
		return (List<XiaoFei>)totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Map<Integer, Object> findIntegralByCondition(XiaoFei  xf, int pageNo,
			int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(xf, null);
		int count=totalDao.getCount(hql);
		List<Integralsource> IntegralList=(List<Integralsource>)totalDao.findAllByPage(hql+" order by xiaofeitime desc", pageNo, pageSize);
		map.put(1, IntegralList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean updataXiaoFei(XiaoFei xf) {
		if(xf!=null){
			return 	totalDao.update(xf);
		}
		return false;
	}

	@Override
	public int getcont() {
		String hql="from XiaoFei";
		return totalDao.getCount(hql);
	}

}
