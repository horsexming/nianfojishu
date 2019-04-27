package com.task.ServerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationDirectLaborCost;

public class ProjectQuotationDirectLaborCostServiceImpl implements com.task.Server.ProjectQuotationDirectLaborCostService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationDirectLaborCost viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		autoOtherAttr(viewObj);
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
	}

	@Override
	public void delete(ProjectQuotationDirectLaborCost viewObj) {
		ProjectQuotationDirectLaborCost sc = (ProjectQuotationDirectLaborCost) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		sc.setRoot(null);
		totalDao.update(sc);
		totalDao.delete(sc);
	}

	@Override
	public ProjectQuotationDirectLaborCost get(ProjectQuotationDirectLaborCost viewObj) {
		return (ProjectQuotationDirectLaborCost) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationDirectLaborCost p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectQuotationDirectLaborCost viewObj) {
		ProjectQuotationDirectLaborCost p = (ProjectQuotationDirectLaborCost) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		autoOtherAttr(viewObj);
		BeanUtils.copyProperties(viewObj, p, new String[]{"id", "root"});
		totalDao.update(p);
	}
	
	/**
	 * 算出别的属性
	 * @param po
	 */
	private void autoOtherAttr(ProjectQuotationDirectLaborCost po) {
		po.setPowerPrice(1.1);
		po.setCycleTime(8 / po.getCapacity());
		po.setEnergy(po.getPower() * po.getCycleTime() * po.getPowerPrice() * 0.9);
		po.setLaborCost(po.getOperator() * po.getCycleTime() * po.getLaborPrice());
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<ProjectQuotationDirectLaborCost> toSelector(Integer id) {
		String hql = "from ProjectQuotationDirectLaborCost p where p.root.id = :rootId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rootId", id);
		return totalDao.find(hql, params);
	}

}
