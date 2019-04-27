package com.task.ServerImpl.zhaobiao;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.zhaobiao.QueXianServer;
import com.task.entity.Users;
import com.task.util.Util;
import com.tast.entity.zhaobiao.QueXian;

public class QueXianServerImpl  implements QueXianServer{
	private TotalDao totalDao;

	public Users ByIdUser(Integer id) {
		return (Users) totalDao.getObjectById(Users.class, id);
	}
	public Object[] listqueXianZong(QueXian queXian, Integer pageNo,
			Integer pageSize) {
		Users user = Util.getLoginUser();
		if (queXian == null) {
			queXian = new QueXian();
		}
		String hql = totalDao.criteriaQueries(queXian, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
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

	public Object[] listQueXian(QueXian queXian, Integer pageNo,
			Integer pageSize) {
		Users user = Util.getLoginUser();
		if (queXian == null) {
			queXian = new QueXian();
		}
		String hql = totalDao.criteriaQueries(queXian, null, null)+" and  tianbaodept like '"+user.getDept()+"'";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public void addqueXian(QueXian queXian) {
		totalDao.save(queXian);
	}
	public void deletequeXian(QueXian queXian) {
		totalDao.delete(queXian);
	}
	public QueXian ByIdquexian(Integer id) {
		return (QueXian) totalDao.getObjectById(QueXian.class, id);
	}
	public void UpdatequeXian(QueXian queXian) {
		totalDao.update(queXian);
	}

}
