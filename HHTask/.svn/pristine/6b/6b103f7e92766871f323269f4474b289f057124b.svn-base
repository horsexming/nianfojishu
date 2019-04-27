package com.task.ServerImpl.bbs;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.bbs.ScrnServer;
import com.task.entity.bbs.Scrn;




public class ScrnServerImpl implements ScrnServer{
	private TotalDao totalDao;
	
	//添加屏幕记录
	public Scrn addScrn(Scrn scrn) {
		boolean result= totalDao.save(scrn);
		if(result){
			return scrn;
		}else {
			return null;
		}
	}
	
	//删除屏幕记录
	public String deleteScrn(Scrn scrn) {
		boolean result= totalDao.delete(scrn);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新屏幕记录
	public String updateScrn(Scrn scrn) {
		boolean result= totalDao.update(scrn);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得屏幕记录
	public Scrn getScrnById(Integer id) {
		if(id != null){
			return (Scrn)totalDao.getObjectById(Scrn.class, id);
		}
		return null;
	}
	
	//获得屏幕记录集合
	public Object[] findAllScrn(Map map, int pageNo, int pageSize) {
		String hql = "from Scrn s where 1 = 1";
		if(map!=null){
		}
		hql+=" order by s.sort asc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public List findAllScrn() {
		String hql = "from Scrn s where 1 = 1";
		hql+=" order by s.sort asc";
		List list = totalDao.find(hql);
		return list;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
