package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.OrderprocesstablerServer;
import com.task.entity.Orderprocesstabler;

public class OrderprocesstablerServerImpl implements OrderprocesstablerServer {

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	//添加订单流程
	public boolean saveOrderprocesstabler(Orderprocesstabler orderprocesstabler) {
		if(orderprocesstabler!=null){
			return this.totalDao.save(orderprocesstabler);
		}
		return false;
	}

}
