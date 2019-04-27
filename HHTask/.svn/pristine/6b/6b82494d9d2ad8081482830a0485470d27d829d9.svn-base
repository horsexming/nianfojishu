package com.task.Server;

import java.util.List;
import com.task.entity.OrderManagement;

public interface OrderManagementServer {

	//添加订单信息
	public boolean saveOrderManagement(OrderManagement orderManagement);
	//删除订单信息
	public boolean deleteOrderManagement(OrderManagement orderManagement);
	//根据ID查找订单信息
	public OrderManagement findByID(int id);
	//修改订单信息
	public boolean updateOrderManagement(OrderManagement orderManagement);
	//查询订单所有信息
	public List findAll(int pageNo, int pageSize);
	//查询出订单所有信息总数
	public Integer getcount();
	//根据登入的用户名查询出所对应的订单信息
	public List findName(String name,int pageNo, int pageSize);
	//根据登入的用户名查询出所对应的订单信息总数
	public Integer getcountName(String name);
	//条件查询
	public List findconditions(OrderManagement orderManagement, int pageNo,int pageSize);
	//条件查询总数
	public Integer orderManagementcount(OrderManagement orderManagement);
	//根据联系人查询出客户名称和联系电话
	public List findlianxiren(String lianxiren);
	//查询部门是工艺的所有人的姓名
	public List findgongyiName();
}
