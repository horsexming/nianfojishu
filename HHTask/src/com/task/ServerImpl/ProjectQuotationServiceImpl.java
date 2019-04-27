package com.task.ServerImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationService;
import com.task.entity.Project;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationList;
import com.task.util.MKUtil;

public class ProjectQuotationServiceImpl implements ProjectQuotationService {
	private TotalDao totalDao; 

	@Override
	public void add(ProjectQuotation p) {
		String hql = "from ProjectQuotationList where id = ?";
		ProjectQuotationList pp =(ProjectQuotationList) totalDao.getObjectByCondition(hql, p.getRoot().getId());
		String hql1 = "from ProjectProposal where root.id = ?";
		ProjectProposal proposal = (ProjectProposal) totalDao.getObjectByCondition(hql1, pp.getRoot().getId());
		if(!proposal.isClosed()){
			throw new RuntimeException("上面的权限还没有关闭");
		}
		p.setClosed(false);
		totalDao.save(p);
	}
	@Override
	public void addChild(ProjectQuotation quotation){
		quotation.setRoot((ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, quotation.getRoot().getId()));
		totalDao.save(quotation);
	}
	
	@Override
	public boolean isClosed(ProjectQuotation p){
		ProjectQuotationList list = (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, p.getRoot().getId());
		ProjectProposal pp = (ProjectProposal) totalDao.getObjectByCondition("from ProjectProposal where root.id = ?", list.getRoot().getId());
		Set<ProjectProposalFlow> flows = pp.getCheck();
		for (ProjectProposalFlow projectProposalFlow : flows) {
			if(projectProposalFlow.isChecks() == false){
				return false;
			}
		}

		return true;
	}

	@Override
	public ProjectQuotation getRoot(ProjectQuotation p) {
		return (ProjectQuotation) totalDao.getObjectByCondition("from ProjectQuotation where root.id = ?", p.getRoot().getId());
	}
	
	@Override
	public ProjectQuotation get(ProjectQuotation p) {
		return (ProjectQuotation) totalDao.getObjectById(p.getClass(), p.getId());
	}
	

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectQuotation update(ProjectQuotation quotation) {
		updateOtherField(quotation);
		return (ProjectQuotation) totalDao.getObjectById(quotation.getClass(), quotation.getId());
	}
	/**
	 * 计算出别的属性.
	 * @param p
	 */
	private void updateOtherField(ProjectQuotation p){
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(p.getClass(), p.getId());
		Double rawMaterial = (Double) totalDao.getObjectByCondition("select sum(totalCost) from ProjectQuotationRawMaterial where root.id = ?",p.getId());
		if (rawMaterial == null){
			rawMaterial = 0.0;
		}
		Double subContract = (Double) totalDao.getObjectByCondition("select sum(cost) from ProjectQuotationSubContract where root.id = ?",p.getId());
		if (subContract == null){
			subContract = 0.0;
		}
		Double toolingCost = (Double) totalDao.getObjectByCondition("select sum(unitCost) from ProjectQuotationToolingCost where root.id = ?",p.getId());
		if (toolingCost == null){
			toolingCost = 0.0;
		}
		Double laborCost = (Double) totalDao.getObjectByCondition("select sum(laborCost) from ProjectQuotationDirectLaborCost where root.id = ?",p.getId());
		if (laborCost == null){
			laborCost = 0.0;
		}
		Double energyCost = (Double) totalDao.getObjectByCondition("select sum(energy) from ProjectQuotationDirectLaborCost where root.id = ?",p.getId());
		if (energyCost == null){
			energyCost = 0.0;
		}
		Double equipmentDepreciation = (Double) totalDao.getObjectByCondition("select sum(equipmentDepreciation) from ProjectQuotationEquipmentDepreciation where root.id = ?",p.getId());
		if (equipmentDepreciation == null){
			equipmentDepreciation = 0.0;
		}
//		Double transportation = (Double) totalDao.getObjectByCondition("select sum(transportationPrice) from ProjectQuotationLogistics where root.id = ?",p.getId());
//		Double packagePrice = (Double) totalDao.getObjectByCondition("select sum(packagePrice) from ProjectQuotationLogistics where root.id = ?",p.getId());

		pq.setRawMaterial(MKUtil.round(4, rawMaterial));
		pq.setSubContract(MKUtil.round(4, subContract));
		pq.setPurchasingCost(MKUtil.round(4, rawMaterial + subContract));
		pq.setToolingCost(MKUtil.round(4, toolingCost));
		pq.setDirectLaborCost(MKUtil.round(4, laborCost));
		pq.setIndirectLaborCost(MKUtil.round(4, laborCost * 0.25));
		pq.setEnergyCost(MKUtil.round(4, energyCost));
		pq.setEquipmentDepreciation(MKUtil.round(4, equipmentDepreciation ));
		pq.setMaintenanceCost( MKUtil.round(4, (toolingCost + equipmentDepreciation) * 0.2 ));
		pq.setSubTotal(MKUtil.round(4, laborCost + pq.getIndirectLaborCost() + pq.getEnergyCost() + pq.getEquipmentDepreciation() + pq.getMaintenanceCost()));
//		pq.setTransportation(MKUtil.round(4, transportation));
//		pq.setPack(MKUtil.round(4, packagePrice));
//		pq.setLogistics(MKUtil.round(4, pq.getTransportation() + pq.getPack()));
		pq.setFinicialInterest(MKUtil.round(4, (pq.getPurchasingCost() + pq.getToolingCost()) * 0.072 ));
		pq.setFreePrice(MKUtil.round(4, (pq.getToolingCost() + pq.getSubTotal()) * 0.15));	
		pq.setTotalPrice(MKUtil.round(4, pq.getPurchasingCost() + pq.getToolingCost() + pq.getSubTotal() + pq.getFreePrice() + pq.getFinicialInterest()));
		totalDao.update(pq);
	}

	@Override
	public void updateOther(ProjectQuotation quotation) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(quotation.getClass(), quotation.getId());
		BeanUtils.copyProperties(quotation, pq, new String[]{"id", "vendor", "partNum", "description", "project", "annualForecast", "buyer", "logisticsDistance", "location", "quotationDate", "closed", "root" });
		
		pq.setRawMaterial(MKUtil.round(2, pq.getRawMaterial()));
		pq.setSubContract(MKUtil.round(2, pq.getSubContract()));
		pq.setPurchasingCost(MKUtil.round(2, pq.getRawMaterial() + pq.getSubContract()));
		pq.setToolingCost(MKUtil.round(2, pq.getToolingCost()));
		pq.setDirectLaborCost(MKUtil.round(2, pq.getDirectLaborCost()));
		pq.setIndirectLaborCost(MKUtil.round(2, pq.getDirectLaborCost() * 0.25));
		pq.setEnergyCost(MKUtil.round(2, pq.getEnergyCost()));
		pq.setEquipmentDepreciation(MKUtil.round(2, pq.getEquipmentDepreciation()));
		pq.setMaintenanceCost( MKUtil.round(2, (pq.getToolingCost() + pq.getEquipmentDepreciation()) * 0.2 ));
		pq.setSubTotal(MKUtil.round(2, pq.getDirectLaborCost() + pq.getIndirectLaborCost() + pq.getEnergyCost() + pq.getEquipmentDepreciation() + pq.getMaintenanceCost()));
		pq.setFinicialInterest(MKUtil.round(2, pq.getPurchasingCost() + pq.getToolingCost()) * 0.072 );
		pq.setFreePrice(MKUtil.round(2, (pq.getToolingCost() + pq.getSubTotal()) * 0.15));	
		pq.setTotalPrice(MKUtil.round(2, pq.getPurchasingCost() + pq.getToolingCost() + pq.getSubTotal()  + pq.getFinicialInterest()+ pq.getFreePrice()));
	}

	@Override
	public List<ProjectQuotation> get(ProjectQuotationList quotationList) {
		String hql = "from ProjectQuotation where root.id = ?";
		return totalDao.find(hql, new Object[]{quotationList.getId()});
	}
	@Override
	public Project getProject(ProjectQuotationList root) {
		ProjectQuotationList pql = (ProjectQuotationList) totalDao.get(root.getClass(), root.getId());
		ProjectQuotationList temp = pql;
		while(temp.getMyroot() != null){
			temp = temp.getMyroot();
		}
		return temp.getRoot();
	}
	
	@Override
	public Project getProject(ProjectQuotation root) {
		ProjectQuotation pql = (ProjectQuotation) totalDao.get(root.getClass(), root.getId());
		return this.getProject(pql.getRoot());
	}
	

}
