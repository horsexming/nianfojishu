package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.TemplateTypemService;
import com.task.entity.android.TemplateRecordm;
import com.task.entity.android.TemplateTypem;

public class TemplateTypemServiceImpl implements TemplateTypemService {
	private TotalDao totalDao;

	@Override
	public void add(TemplateTypem templateTypem) {
		totalDao.save(templateTypem);
	}

	@Override
	public void add(TemplateRecordm t) {
		totalDao.save(t);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] find(int parseInt, int pageSize) {
		String hql = "from TemplateTypem order by createDate desc";
		List<TemplateTypem> list = (List)totalDao.findAllByPage(hql, parseInt, pageSize);
		for (TemplateTypem object : list) {
			object.getRoot().getGwNumber();
			object.getChildren().size();
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List<TemplateRecordm> getChildren(TemplateRecordm r) {
		String hql = "from TemplateRecordm where root.id = ? order by nowDate desc";
		List<TemplateRecordm> ls = totalDao.find(hql, new Object[]{r.getRoot().getId()});
		for (TemplateRecordm templateRecordm : ls) {
			templateRecordm.getRoot().getRoot().getJcnr();
		}
		return ls;
	}

}
