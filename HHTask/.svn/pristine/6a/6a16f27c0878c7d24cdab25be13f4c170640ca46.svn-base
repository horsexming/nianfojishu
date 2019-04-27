package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.FlowService;
import com.task.entity.ZhFlow;

public class FlowServiceImpl implements FlowService {
	private TotalDao totalDao;

	@Override
	public Object[] getList(int pageNo, int pageSize) {
		String hql = "from ZhFlow order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	@Override
	public ZhFlow getFid(String fid) {
		String hql = "from ZhFlow where fid = ? order by id desc";
		return (ZhFlow) totalDao.getObjectByCondition(hql, fid);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
