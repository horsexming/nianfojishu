package com.task.ServerImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.TemplatemService;
import com.task.entity.android.Templatem;

public class TemplatemServiceImpl implements TemplatemService {
	private TotalDao totalDao;

	@Override
	public Serializable add(Templatem m) {
		totalDao.save(m);
		return m.getId();
	}

	@Override
	public Object[] find(int rows, int size) {
		String hql = "from Templatem order by id desc";
		List list = totalDao.findAllByPage(hql, rows, size);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(Templatem m) {
		Templatem t = (Templatem) totalDao.get(Templatem.class, m.getId());
		BeanUtils.copyProperties(m, t, new String[]{"id", "children"});
	}

	@Override
	public Templatem get(Templatem m) {
		return (Templatem) totalDao.get(m.getClass(), m.getId());
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<Templatem> getData(Integer id) {
		String countHql = "select count(*) from Templatem where id > ?";
		long l = totalDao.count(countHql, new Object[]{id});
		if(l <= 0){
			return null;
		}
		String hql = " from Templatem where id > ?";
		return totalDao.find(hql, new Object[]{id});
	}

}
