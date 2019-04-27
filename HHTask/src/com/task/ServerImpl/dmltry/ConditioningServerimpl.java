package com.task.ServerImpl.dmltry;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.dmltry.ConditioningServer;
import com.task.entity.Users;
import com.task.entity.dmltry.Conditioning;

public class ConditioningServerimpl implements ConditioningServer {
	private TotalDao totalDao;

	@Override
	public String addConditioning(Conditioning conditioning) {
		totalDao.save(conditioning);
		return "true";
	}

	/**
	 * 修改
	 */
	@Override
	public String uadateConditioning(Conditioning conditioning) {
		totalDao.update(conditioning);
		return "true";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 查詢所有
	 */
	@Override
	public List<Conditioning> list() {
		List<Conditioning> list = totalDao.query("from Conditioning");
		return list;
	}

	/**
	 * 明细查询
	 */
	@Override
	public Conditioning ConditioningDetail(Integer id) {
		Conditioning conditioning = (Conditioning) totalDao.getObjectByCondition("from Conditioning WHERE id=?", id);
		return conditioning;
	}

	/**
	 * 查询人员信息
	 */
	@Override
	public List<Users> username() {
		List<Users> userlist = totalDao.query("from Users");
		return userlist;
	}

	@Override
	public Users users(Integer id) {
		Users users = (Users) totalDao.getObjectByCondition("from Users WHERE id=?", id);
		return users;
	}

}
