package com.task.ServerImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ManufacturingService;
import com.task.entity.Manufacturing;

public class ManufacturingServiceImpl implements ManufacturingService {
	private TotalDao totalDao;

	@Override
	public Serializable add(Manufacturing m) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(d);
		
		String hql = "select count(*) from Manufacturing where jygcNumber like ?";
		long k = totalDao.count(hql,new Object[]{"xj" + dateStr + "%%"});
		String xjNumber = "xj" + dateStr + String.format("%03d" ,k+1);
		m.setJygcNumber(xjNumber);
		totalDao.save(m);
		return m.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] find(int rows, int size) {
		String hql = "from Manufacturing order by id desc";
		List list = totalDao.findAllByPage(hql, rows, size);
		
		List<Manufacturing> a = list;
		for (Manufacturing manufacturing : a) {
			manufacturing.getChildren().size();
		}
		int count = totalDao.getCount(hql);
		Object[] o = { a, count };
		return o;
	}

	@Override
	public void update(Manufacturing m) {
		// TODO Auto-generated method stub

	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<Manufacturing> getData(Integer id) {
		String countHql = "select count(*) from Manufacturing where id > ?";
		long l = totalDao.count(countHql, new Object[]{id});
		if(l <= 0){
			return null;
		}
		String hql = " from Manufacturing where id > ?";
		return totalDao.find(hql, new Object[]{id});
	}

}
