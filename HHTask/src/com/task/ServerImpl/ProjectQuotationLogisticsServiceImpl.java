package com.task.ServerImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationLogisticsService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationLogistics;

public class ProjectQuotationLogisticsServiceImpl implements ProjectQuotationLogisticsService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationLogistics viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
	}

	@Override
	public void delete(ProjectQuotationLogistics viewObj) {
		ProjectQuotationLogistics sc = (ProjectQuotationLogistics) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		sc.setRoot(null);
		totalDao.update(sc);
		totalDao.delete(sc);
	}

	@Override
	public ProjectQuotationLogistics get(ProjectQuotationLogistics viewObj) {
		return (ProjectQuotationLogistics) totalDao.getObjectById(ProjectQuotationLogistics.class, viewObj.getId());
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationLogistics p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectQuotationLogistics viewObj) {
		ProjectQuotationLogistics p = (ProjectQuotationLogistics) totalDao.getObjectById(ProjectQuotationLogistics.class, viewObj.getId());
		BeanUtils.copyProperties(viewObj, p, new String[]{"id", "root"});
		totalDao.update(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
