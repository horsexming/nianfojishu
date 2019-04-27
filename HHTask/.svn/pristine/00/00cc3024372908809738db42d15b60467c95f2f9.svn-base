package com.task.ServerImpl.pro;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.pro.ProToolingServer;
import com.task.entity.pro.ProTooling;




public class ProToolingServerImpl implements ProToolingServer{
	private TotalDao totalDao;
	
	//添加项目工装记录
	public String addProTooling(ProTooling proTooling) {
		boolean result= totalDao.save(proTooling);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//删除项目工装记录
	public String deleteProTooling(ProTooling proTooling) {
		boolean result= totalDao.delete(proTooling);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新项目工装记录
	public String updateProTooling(ProTooling proTooling) {
		boolean result= totalDao.update(proTooling);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得项目工装记录
	public ProTooling getProToolingById(Integer id) {
		if(id != null){
			return (ProTooling)totalDao.getObjectById(ProTooling.class, id);
		}
		return null;
	}
	
	//更新项目工装记录集合
	public Object[] findAllProToolingByproId(Map map, int pageNo, int pageSize) {
		String hql = "from ProTooling p where 1 = 1";
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
