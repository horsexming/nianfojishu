package com.task.ServerImpl;

import java.text.DecimalFormat;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.MetricService;
import com.task.entity.ZhFlow;
import com.task.entity.ZhMetric;

public class MetricServiceImpl implements MetricService {
	private TotalDao totalDao;

	@Override
	public void add(ZhMetric metric) {
		totalDao.save(metric);
	}

	@Override
	public String generatedXh(ZhFlow flow) {
		String hql = "from ZhMetric where fid = ?";
		Integer i = totalDao.getCount(hql, flow.getFid());
		DecimalFormat df = new DecimalFormat("0000");
 		return df.format(i+1);
	}

	@Override
	public List<ZhMetric> queryByFid(String fid) {
		String hql = "from ZhMetric where fid=?";
		return totalDao.query(hql, fid);
	}
	
	@Override
	public void save(ZhMetric metric) {
		totalDao.save(metric);
	}
	
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

 
}
