package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.MsgGroupServiceI;
import com.task.entity.MsgGroup;

public class MsgGroupServiceImpl implements MsgGroupServiceI {
	private TotalDao totalDao;

	@Override
	public boolean add(MsgGroup msg) {
		return totalDao.save(msg);
	}

	@Override
	public boolean delete(MsgGroup msg) {
		return totalDao.delete(msg);
	}

	@Override
	public boolean modify(MsgGroup msg) {
		return totalDao.update(msg);
	}

	@Override
	public Object[] getList(int parseInt, int pageSize) {
		String hql = "from MsgGroup order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
