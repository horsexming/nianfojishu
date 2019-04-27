package com.task.ServerImpl.pro;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.pro.ProProductServer;
import com.task.entity.pro.ProProduct;




public class ProProductServerImpl implements ProProductServer{
	private TotalDao totalDao;
	
	//添加项目产品记录
	public String addProProduct(ProProduct proProduct) {
		boolean result= totalDao.save(proProduct);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//删除项目产品记录
	public String deleteProProduct(ProProduct proProduct) {
		boolean result= totalDao.delete(proProduct);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新项目产品记录
	public String updateProProduct(ProProduct proProduct) {
		boolean result= totalDao.update(proProduct);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得项目产品记录
	public ProProduct getProProductById(Integer id) {
		if(id != null){
			return (ProProduct)totalDao.getObjectById(ProProduct.class, id);
		}
		return null;
	}
	
	//更新项目产品记录集合
	public Object[] findAllProProductByproId(Map map, int pageNo, int pageSize) {
		String hql = "from ProProduct p where 1 = 1";
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
