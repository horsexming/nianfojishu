package com.task.ServerImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationEquipmentDepreciationService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationDirectLaborCost;
import com.task.entity.ProjectQuotationEquipmentDepreciation;

public class ProjectQuotationEquipmentDepreciationServiceImpl implements ProjectQuotationEquipmentDepreciationService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationEquipmentDepreciation viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		autoAotherAttr(viewObj, viewObj.getDirectLaborCost().getId());
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
		
	}

	@Override
	public void delete(ProjectQuotationEquipmentDepreciation viewObj) {
		ProjectQuotationEquipmentDepreciation sc = (ProjectQuotationEquipmentDepreciation) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		sc.setRoot(null);
		sc.setDirectLaborCost(null);
		totalDao.update(sc);
		totalDao.delete(sc);
	}

	@Override
	public ProjectQuotationEquipmentDepreciation get(ProjectQuotationEquipmentDepreciation viewObj) {
		ProjectQuotationEquipmentDepreciation ed = (ProjectQuotationEquipmentDepreciation) totalDao.getObjectById(ProjectQuotationEquipmentDepreciation.class, viewObj.getId());
		String name = ed.getDirectLaborCost().getProcess();
		return ed;
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationEquipmentDepreciation p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectQuotationEquipmentDepreciation viewObj) {
		ProjectQuotationEquipmentDepreciation p = (ProjectQuotationEquipmentDepreciation) totalDao.getObjectById(ProjectQuotationEquipmentDepreciation.class, viewObj.getId());
		autoAotherAttr(viewObj, viewObj.getDirectLaborCost().getId());
		BeanUtils.copyProperties(viewObj, p, new String[]{"id", "root"});
		totalDao.update(p);
	}
	
	/**
	 * 自动计算属性.
	 * @param po
	 * @param dlcId
	 */
	private void autoAotherAttr(ProjectQuotationEquipmentDepreciation po, Integer dlcId) {
		po.setEndLifeValue(po.getEquipmentValue() * 0.05);
		ProjectQuotationDirectLaborCost p = (ProjectQuotationDirectLaborCost) totalDao.getObjectById(ProjectQuotationDirectLaborCost.class, dlcId);
		po.setEquipmentDepreciation( (po.getEquipmentValue() - po.getEndLifeValue()) / po.getLifeTime() / po.getWorkDay() / po.getWorkTime() * p.getCycleTime());
		po.setDirectLaborCost(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
