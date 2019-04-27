package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.TrainServer;
import com.task.entity.Train;

/**
 * 员工培训Server层实现类
 * 
 * @author 刘培
 * 
 */
public class TrainServerImpl implements TrainServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加培训信息
	public boolean addTrain(Train train) {
		if (train != null) {
			return totalDao.save(train);
		}
		return false;
	}

}
