package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.OrderManagementServer;
import com.task.entity.OrderManagement;

public class OrderManagementServerImpl implements OrderManagementServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	//添加订单信息
	public boolean saveOrderManagement(OrderManagement orderManagement) {
		if(orderManagement!=null){
			return this.totalDao.save(orderManagement);
		}
		return false;
	}

	//删除订单信息
	public boolean deleteOrderManagement(OrderManagement orderManagement) {
	if(orderManagement!=null){
		return this.totalDao.delete(orderManagement);
		}
		return false;
	}

	//根据ID查找订单信息
	public OrderManagement findByID(int id) {
		if(id>0){
			return (OrderManagement)this.totalDao.getObjectById(OrderManagement.class, id);
		}
		return null;
	}

	//修改订单信息
	public boolean updateOrderManagement(OrderManagement orderManagement) {
	 if(orderManagement!=null){
		 return this.totalDao.update(orderManagement);
	 }
		return false;
	}

	//查询订单所有信息
	public List findAll(int pageNo, int pageSize) {
		String hql="from OrderManagement order by ordercreatedatatime desc ";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	//查询出订单所有信息总数
	public Integer getcount() {
		String hql="from OrderManagement";
		return this.totalDao.getCount(hql);
	}

	//条件查询
	public List findconditions(OrderManagement orderManagement, int pageNo,
			int pageSize) {
		if(orderManagement!=null){
			String hql=this.totalDao.criteriaQueries(orderManagement, null, null);
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	//条件查询总数
	public Integer orderManagementcount(OrderManagement orderManagement) {
		if(orderManagement!=null){
			String hql=this.totalDao.criteriaQueries(orderManagement, null, null);
			return this.totalDao.getCount(hql);
		}
		return null;
	}

	//根据联系人查询出客户名称和联系电话
	public List findlianxiren(String lianxiren) {
		if(lianxiren!=null){
			String hql="from ClientManagement where clientname='"+lianxiren+"' ";
			return  this.totalDao.query(hql);
		}
		return null;
	}

	//根据登入的用户名查询出所对应的订单信息
	public List findName(String name, int pageNo, int pageSize) {
		if(name!=null){
			String hql="from OrderManagement where ordercreatePeople='"+name+"' order by ordercreatedatatime desc";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	//根据登入的用户名查询出所对应的订单信息总数
	public Integer getcountName(String name) {
		if(name!=null){
			String hql="from OrderManagement where ordercreatePeople='"+name+"'";
			return this.totalDao.getCount(hql);
		}
		return null;
	}

	//查询部门是工艺的所有人的姓名
	public List findgongyiName() {
		String hql="select name from Users where dept='工艺'";
		return this.totalDao.query(hql);
	}
	
}
