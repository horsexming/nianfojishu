package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.NoticeServer;
import com.task.entity.Notice;
import com.task.entity.OrderManagement;
import com.task.util.Util;

public class NoticeServerImpl implements NoticeServer {
	private TotalDao totalDao;
	private Notice notice;
	

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	@Override
	public void update(Notice notice) {
		// TODO Auto-generated method stub
		if(notice!=null){
			 this.totalDao.update(notice);
		}
	}

	@Override
	public void delete(Notice notice) {
		// TODO Auto-generated method stub
		this.totalDao.delete(notice);
	}

	@Override
	public List find(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from Notice order by id desc ";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Notice look(int id) {
		// TODO Auto-generated method stub
		return (Notice)this.totalDao.getObjectById(Notice.class, id);
	}

	@Override
	public void save(Notice notice) {
		// TODO Auto-generated method stub
		if(notice!=null){
			notice.setPersonname(Util.getLoginUser().getName());
			notice.setTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			notice.setPid(Util.getLoginUser().getCode());
			notice.setStatus("隐藏");
		    this.totalDao.save(notice);
		}
	}

	@Override
	public int getcount() {
		// TODO Auto-generated method stub
		String hql="from Notice";
		return this.totalDao.getCount(hql);
	}

	@Override
	public List seek() {
		String hql="from Notice where status = '显示' order by id desc ";      
		return this.totalDao.query(hql);
	}
}
