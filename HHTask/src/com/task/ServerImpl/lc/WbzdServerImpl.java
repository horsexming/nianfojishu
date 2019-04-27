package com.task.ServerImpl.lc;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Dao.TotalDao_2;
import com.task.Server.lc.WbzdServer;

public class WbzdServerImpl implements WbzdServer {

	private TotalDao totalDao;
	private TotalDao_2 totalDao_2;

	@Override
	public Object[] findWBZD(Integer cpage, Integer pageSize) {
		// TODO Auto-generated method stub
		Object[] obj = new Object[2];
		String hql = "from LSwbzd";
		int count = totalDao_2.getCount(hql);
		List list = totalDao_2.findAllByPage(hql, cpage, pageSize);
		obj[0] = count;
		obj[1] = list;
		return obj;
	}

	public TotalDao_2 getTotalDao_2() {
		return totalDao_2;
	}

	public void setTotalDao_2(TotalDao_2 totalDao_2) {
		this.totalDao_2 = totalDao_2;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
