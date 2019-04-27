package com.task.ServerImpl.pro;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.pro.ProUserServer;
import com.task.entity.pro.ProUser;



public class ProUserServerImpl implements ProUserServer{
	private TotalDao totalDao;
	
	//添加项目记录
	public String addProUser(ProUser proUser) {
		boolean result= totalDao.save(proUser);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//删除项目记录
	public String deleteProUser(ProUser proUser) {
		boolean result= totalDao.delete(proUser);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新项目记录
	public String updateProUser(ProUser proUser) {
		boolean result= totalDao.update(proUser);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得项目记录
	public ProUser getProUserById(Integer id) {
		if(id != null){
			return (ProUser)totalDao.getObjectById(ProUser.class, id);
		}
		return null;
	}
	
	//更新项目记录
	public Object[] findAllProUserByproId(Map map, int pageNo, int pageSize) {
		String hql = "from ProUser p where 1 = 1";
		if(map!=null){
			if(map.get("proId")!=null){
				Integer proId=(Integer) map.get("proId");
				hql+=" and p.proId="+proId;
			}
		}
		hql+=" order by p.id asc";
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

}
