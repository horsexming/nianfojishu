package com.task.ServerImpl.iao;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.iao.InformServer;
import com.task.entity.iao.LeaveInform;
import com.task.util.Util;

public class InformServerImpl implements InformServer{
	private TotalDao totalDao;
	public boolean save(LeaveInform leaveInform){
		if(leaveInform.getFuck1().equals("离场通知")||
				leaveInform.getFuck1().equals("离职通知")||
				leaveInform.getFuck1().equals("入职通知")||
				leaveInform.getFuck1().equals("财务离职通知")){
			LeaveInform a = this.findByCode(leaveInform.getCode(),leaveInform.getFuck1());
			if(a==null){
				leaveInform.setKkk(Util.getLoginUser().getName());
				totalDao.save(leaveInform);
				return true;
			}else{
				return false;
			}
		}else{
			leaveInform.setKkk(Util.getLoginUser().getName());
			totalDao.save(leaveInform);
			return true;
		}
	}
	//个人人事查询验证
	public LeaveInform findByCode(String code , String fuck1){
		if (code != null && code.length() > 0) {
			String hql = "from LeaveInform where code=? and fuck1 = ? ";
			return (LeaveInform) totalDao.getObjectByCondition(hql, code,fuck1);
		}
		return null;
	}
	public LeaveInform findById(int code , String fuck1){
		if (code > 0) {
			String hql = "from LeaveInform where id=? and fuck1 = ? ";
			return (LeaveInform) totalDao.getObjectByCondition(hql, code,fuck1);
		}
		return null;
	}
	public LeaveInform findById(int code ){
		if (code > 0) {
			String hql = "from LeaveInform where id=?";
			return (LeaveInform) totalDao.getObjectByCondition(hql, code);
		}
		return null;
	}
	//全部人事通知列表查询
	public List getAllList(int pageNo, int pageSize, String key) {
		String hql = "from LeaveInform where fuck1 = ? order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, key);
		return list;
	}
	public Integer getcount(String key) {
		String hql="from LeaveInform where fuck1 = ?";
		return this.totalDao.getCount(hql,key);
	}
	public int getshu(String key) {
		String hql="from LeaveInform where fuck1 = ?";
		return this.totalDao.getCount(hql,key);
	}
	//删除
	public void delete(LeaveInform leaveInform){
		totalDao.delete(leaveInform);
	}
	//根据状态查询
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
