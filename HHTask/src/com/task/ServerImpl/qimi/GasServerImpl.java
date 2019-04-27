package com.task.ServerImpl.qimi;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.qimi.GasServer;

import com.task.entity.Users;
import com.task.entity.qimi.Gas;

import com.task.util.Util;

public class GasServerImpl implements GasServer{
     private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
 //添加
	@SuppressWarnings("deprecation")
	@Override
	public String addGas(Gas gas) {
		if (gas!=null) {
			Gas oldGas = (Gas) totalDao.getObjectByQuery("from Gas where number=?", gas.getNumber());
			if (oldGas != null) {
				return "数据库存在此序列号，无法重复！";
		}
		gas.setAddTime(Util.getDateTime());
		Users user =Util.getLoginUser();
		gas.setUserss(user.getName());
		boolean bool = totalDao.save(gas);
		if (bool) {
			return "true";
			
		}
	}
	return "数据异常，添加失败。请检查后再试！";
}

  //查询
	@Override
	public Object[] findGas(Gas gas, int parseInt, int pageSize) {
		if(gas==null){
			gas = new Gas();
		}
		String hql = totalDao.criteriaQueries(gas, null);
			hql +=" order by addTime desc";
			List list=totalDao.findAllByPage(hql, parseInt, pageSize);
			int count=totalDao.getCount(hql);
		    Object[] o={list,count};
		return o;
	}
  
 
	
     
}
