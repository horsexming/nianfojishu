package com.task.ServerImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationSubContractService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationSubContract;

public class ProjectQuotationSubContractServiceImpl implements ProjectQuotationSubContractService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationSubContract viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
	}

	@Override
	public void delete(ProjectQuotationSubContract viewObj) {
		ProjectQuotationSubContract sc = (ProjectQuotationSubContract) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		sc.setRoot(null);
		totalDao.update(sc);
		totalDao.delete(sc);
	}

	@Override
	public ProjectQuotationSubContract get(ProjectQuotationSubContract viewObj) {
		ProjectQuotationSubContract p = (ProjectQuotationSubContract) totalDao.getObjectById(ProjectQuotationSubContract.class, viewObj.getId());
		return p;
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationSubContract p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectQuotationSubContract viewObj) {
		ProjectQuotationSubContract p = (ProjectQuotationSubContract) totalDao.getObjectById(ProjectQuotationSubContract.class, viewObj.getId());
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
