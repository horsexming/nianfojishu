package com.task.ServerImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationToolingCostService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationToolingCost;
import com.task.util.MKUtil;

public class ProjectQuotationToolingCostServiceImpl implements ProjectQuotationToolingCostService {
	private TotalDao totalDao;
	@Override
	public void add(ProjectQuotationToolingCost viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		autoAotherAttr(viewObj);
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
	}

	@Override
	public void delete(ProjectQuotationToolingCost viewObj) {
		ProjectQuotationToolingCost sc = (ProjectQuotationToolingCost) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		sc.setRoot(null);
		totalDao.update(sc);
		totalDao.delete(sc);
	}

	@Override
	public ProjectQuotationToolingCost get(ProjectQuotationToolingCost viewObj) {
		return (ProjectQuotationToolingCost) totalDao.getObjectById(ProjectQuotationToolingCost.class, viewObj.getId());
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationToolingCost p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectQuotationToolingCost viewObj) {
		ProjectQuotationToolingCost p = (ProjectQuotationToolingCost) totalDao.getObjectById(ProjectQuotationToolingCost.class, viewObj.getId());
		autoAotherAttr(viewObj);
		BeanUtils.copyProperties(viewObj, p, new String[]{"id", "root"});
		totalDao.update(p);
	}
	
	/**
	 * 计算其他的属性...
	 * @param po
	 */
	private void autoAotherAttr(ProjectQuotationToolingCost po) {
		po.setSubTotal(MKUtil.round(2, po.getPrice() * po.getQuantity()));//计算金额
		po.setUnitCost(MKUtil.round(2, po.getSubTotal() / po.getLifeTimeVolume()));//计算单价金额
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
