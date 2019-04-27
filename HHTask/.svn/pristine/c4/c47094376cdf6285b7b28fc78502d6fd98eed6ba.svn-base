package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectQuotationListService;
import com.task.entity.Project;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationList;
import com.task.util.MKUtil;

public class ProjectQuotationListServiceImpl implements ProjectQuotationListService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationList list) {
		String hql = "from ProjectProposal p where p.root.id = ?";
		ProjectProposal pp =(ProjectProposal) totalDao.getObjectByCondition(hql, list.getRoot().getId());
		Project p = (Project) totalDao.getObjectById(Project.class, list.getRoot().getId());
		list.setRoot(p);
		pp.setClosed(true);
		list.setClosed(false);
		list.setVendor("上海红湖排气系统有限公司");
		totalDao.save(list);
		Project pro = list.getRoot();
		pro.setStaring("项目报价进行中");
		totalDao.update(pro);
	}

	@Override
	public void delete(ProjectQuotationList list) {
		totalDao.delete(list);
	}

	@Override
	public ProjectQuotationList get(ProjectQuotationList list) {
		return (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, list.getId());
	}

	@Override
	public void update(ProjectQuotationList list) {
		ProjectQuotationList l = (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, list.getId());
//		list.setPurchasingCost(list.getRawMaterial() + list.getSubContract());
//		list.setMaintenanceCost(list.getToolingCost() + list.getEquipmentDepreciation() * 0.2 );
//		list.setSubTotal(list.getDirectLaborCost() + list.getIndirectLaborCost() + list.getEnergyCost() + list.getEquipmentDepreciation() + list.getMaintenanceCost());
//		list.setFinicialInterest(MKUtil.round(2, list.getPurchasingCost() + list.getToolingCost()) * 0.072 );
//		list.setLogistics(list.getTransportation() + list.getPack());
//		list.setTotalPrice( list.getPurchasingCost() + list.getToolingCost() + list.getSubTotal() + list.getFreePrice()  + list.getLogistics() + list.getOi());
		BeanUtils.copyProperties(list, l, new String[]{"id", "root", "quotations"});
	}
	
	@Override
	public ProjectQuotationList get(Project project) {
		String hql = "from ProjectQuotationList p where p.root.id = ?";
		return (ProjectQuotationList) totalDao.getObjectByCondition(hql, project.getId());
	}

	@Override
	public boolean isClosedByProposal(Project project) {
		String hql = "from ProjectProposal p where p.root.id = ?";
		return ((ProjectProposal)totalDao.getObjectByCondition(hql, project.getId())).isClosed();
	}
	

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectQuotationList updateAttr(ProjectQuotationList quotationList) {
		ProjectQuotationList list = (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, quotationList.getId());
		list.setRawMaterial(0.0);
		list.setSubContract(0.0);
		list.setPurchasingCost(0.0);
		list.setToolingCost(0.0);
		list.setDirectLaborCost(0.0);
		list.setIndirectLaborCost(0.0);
		list.setEnergyCost(0.0);
		list.setEquipmentDepreciation(0.0);
		list.setMaintenanceCost(0.0);
		list.setSubTotal(0.0);
		if(list.getTransportation() == null){
			list.setTransportation(0.0);
		}
		
		if(list.getPack() == null){
			list.setPack(0.0);
		}

		list.setFinicialInterest(0.0);
		list.setFreePrice(0.0);
		if(list.getOi() == null){
			list.setOi(0.0);
		}
		list.setTotalPrice(0.0);
		for (ProjectQuotation pq : list.getQuotations()) {
			list.setRawMaterial( list.getRawMaterial() + 0.0 + pq.getRawMaterial());
			list.setSubContract( list.getSubContract() + 0.0 + pq.getSubContract());
			list.setPurchasingCost( list.getPurchasingCost() + 0.0 + pq.getPurchasingCost());
			list.setToolingCost( list.getToolingCost() + 0.0 + pq.getToolingCost());
			list.setDirectLaborCost( list.getDirectLaborCost() + 0.0 + pq.getDirectLaborCost());
			list.setIndirectLaborCost( list.getIndirectLaborCost() + 0.0 + pq.getIndirectLaborCost());
			list.setEnergyCost( list.getEnergyCost() + 0.0 + pq.getEnergyCost());
			list.setEquipmentDepreciation( list.getEquipmentDepreciation() + 0.0 + pq.getEquipmentDepreciation());
			list.setMaintenanceCost( list.getMaintenanceCost() + 0.0 + pq.getMaintenanceCost());
			list.setSubTotal( list.getSubTotal() + 0.0 + pq.getSubTotal());
			list.setFinicialInterest( list.getFinicialInterest() + 0.0 + pq.getFinicialInterest());
			list.setFreePrice( list.getFreePrice() + 0.0 + pq.getFreePrice());
			list.setTotalPrice( list.getTotalPrice() + 0.0 + pq.getTotalPrice());
		}
		list.setLogistics(list.getTransportation() + list.getPack());
		list.setTotalPrice( list.getTotalPrice() + list.getLogistics() + list.getOi());
		totalDao.update(list);
		return list;
	}

	@Override
	public void updateOi(ProjectQuotationList quotationList) {
		ProjectQuotationList l = (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, quotationList.getId());
		l.setOi(quotationList.getOi());
		totalDao.update(l);
	}

	@Override
	public void updateWl(ProjectQuotationList quotationList) {
		ProjectQuotationList l = (ProjectQuotationList) totalDao.getObjectById(ProjectQuotationList.class, quotationList.getId());
		l.setTransportation(quotationList.getTransportation());
		l.setPack(quotationList.getPack());
		totalDao.update(l);
	}

	@Override
	public void addDirectory(ProjectQuotationList quotationList) {
		ProjectQuotationList l = (ProjectQuotationList) totalDao.get(ProjectQuotationList.class, quotationList.getMyroot().getId());
		quotationList.setMyroot(l);
		totalDao.save(quotationList);
	}

	@Override
	public List<ProjectQuotationList> getChildren(ProjectQuotationList quotationList) {
		String hql = "from ProjectQuotationList l where l.myroot.id = ?";
		return totalDao.find(hql, new Object[]{quotationList.getId()});
	}

	@Override
	public ProjectQuotationList showAll(ProjectQuotationList quotationList) {
		ProjectQuotationList p = new ProjectQuotationList();
		List<ProjectQuotation> list = new ArrayList<ProjectQuotation>();
		init(p, quotationList);
		tree(quotationList, list, p);
		for (ProjectQuotation projectQuotation : list) {
			sumOtherField(projectQuotation, p);
		}
		
		p.setTotalPrice(p.getTotalPrice() + p.getLogistics() + p.getOi());
		p.setSellingPrice(p.getTotalPrice() * 1.17);
		ProjectQuotationList temp = (ProjectQuotationList) totalDao.get(quotationList.getClass(), quotationList.getId());
		p.setDescription(temp.getDescription());
		p.setPartNum(temp.getPartNum());
		flor(p);
		return p;
	}

	private void flor(ProjectQuotationList p1) {
		p1.setRawMaterial(MKUtil.round(2, p1.getRawMaterial()));
		p1.setSubContract(MKUtil.round(2, p1.getSubContract()));
		p1.setPurchasingCost(MKUtil.round(2, p1.getPurchasingCost()));
		p1.setToolingCost(MKUtil.round(2, p1.getToolingCost()));
		p1.setDirectLaborCost(MKUtil.round(2, p1.getDirectLaborCost()));
		p1.setIndirectLaborCost(MKUtil.round(2, p1.getIndirectLaborCost()));
		p1.setEnergyCost(MKUtil.round(2, p1.getEnergyCost()));
		p1.setEquipmentDepreciation(MKUtil.round(2, p1.getEquipmentDepreciation() ));
		p1.setMaintenanceCost( MKUtil.round(2, p1.getMaintenanceCost()));
		p1.setSubTotal(MKUtil.round(2, p1.getSubTotal()));
		p1.setLogistics(MKUtil.round(2, p1.getLogistics()));
		p1.setFinicialInterest(MKUtil.round(2, p1.getFinicialInterest()));
		p1.setFreePrice(MKUtil.round(2, p1.getFreePrice()));	
		p1.setTotalPrice(MKUtil.round(2, p1.getTotalPrice()));
	}

	private void init(ProjectQuotationList p, ProjectQuotationList quotationList) {
		ProjectQuotationList p1 = (ProjectQuotationList) totalDao.getObjectById(p.getClass(), quotationList.getId());
		p.setRawMaterial(0.0);
		p.setSubContract(0.0);
		p.setPurchasingCost(0.0);
		p.setToolingCost(0.0);
		p.setDirectLaborCost(0.0);
		p.setIndirectLaborCost(0.0);
		p.setEnergyCost(0.0);
		p.setEquipmentDepreciation(0.0);
		p.setMaintenanceCost(0.0);
		p.setSubTotal(0.0);
		p.setTransportation((p1.getTransportation() == null) ? 0.0 :p1.getTransportation());
		p.setPack((p1.getPack() == null) ?0.0 :p1.getPack());
		p.setLogistics(p.getPack() + p.getTransportation());
		p.setFreePrice(0.0);
		p.setFinicialInterest(0.0);
		p.setOi((p1.getOi()) == null ? 0.0 :p1.getOi());
		p.setTotalPrice(0.0);
	}

	private void tree(ProjectQuotationList quotationList, List<ProjectQuotation> list, ProjectQuotationList p) {
		String hql = "from ProjectQuotationList l where l.myroot.id = ?";
		List<ProjectQuotationList> ds = totalDao.find(hql, new Object[]{quotationList.getId()});
		for (ProjectQuotationList projectQuotationList : ds) {
			p.setTransportation(p.getTransportation() + ((projectQuotationList.getTransportation() == null) ? 0.0:projectQuotationList.getTransportation()));
			p.setPack(p.getPack() + ((projectQuotationList.getPack() == null)? 0.0 :projectQuotationList.getPack()));
			p.setLogistics(p.getTransportation() + p.getPack());
			p.setOi(p.getOi() + ((projectQuotationList.getOi() == null) ?0.0: projectQuotationList.getOi()));
			tree(projectQuotationList,list,p);
		}
		
		String hql1 = "from ProjectQuotation q where q.root.id = ?";
		List<ProjectQuotation> qs = totalDao.find(hql1, new Object[]{quotationList.getId()});
		list.addAll(qs);
		
	}
	
	
	/**
	 * 计算出别的属性.
	 * @param p
	 */
	private void sumOtherField(ProjectQuotation p, ProjectQuotationList p1){
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

		
//		p1.setRawMaterial(MKUtil.round(2, rawMaterial));
//		p1.setSubContract(MKUtil.round(2, subContract));
//		p1.setPurchasingCost(MKUtil.round(2, rawMaterial + subContract));
//		p1.setToolingCost(MKUtil.round(2, toolingCost));
//		p1.setDirectLaborCost(MKUtil.round(2, laborCost));
//		p1.setIndirectLaborCost(MKUtil.round(2, laborCost * 0.25));
//		p1.setEnergyCost(MKUtil.round(2, energyCost));
//		p1.setEquipmentDepreciation(MKUtil.round(2, equipmentDepreciation ));
//		p1.setMaintenanceCost( MKUtil.round(2, (toolingCost + equipmentDepreciation) * 0.2 ));
//		p1.setSubTotal(MKUtil.round(2, laborCost + p1.getIndirectLaborCost() + p1.getEnergyCost() + p1.getEquipmentDepreciation() + p1.getMaintenanceCost()));
//		p1.setTransportation(MKUtil.round(2, transportation));
//		p1.setPack(MKUtil.round(2, packagePrice));
//		p1.setLogistics(MKUtil.round(2, p1.getTransportation() + p1.getPack()));
//		p1.setFinicialInterest(MKUtil.round(2, p1.getPurchasingCost() + p1.getToolingCost()) * 0.072 );
//		p1.setFreePrice(MKUtil.round(2, (p1.getToolingCost() + p1.getSubTotal()) * 0.15));	
//		p1.setTotalPrice(MKUtil.round(2, p1.getPurchasingCost() + p1.getToolingCost() + p1.getSubTotal() + p1.getFreePrice() ));
		
		p1.setRawMaterial(p1.getRawMaterial() + rawMaterial);
		p1.setSubContract(p1.getSubContract() + subContract);
		p1.setPurchasingCost(p1.getPurchasingCost() + rawMaterial + subContract);
		p1.setToolingCost(p1.getToolingCost() + toolingCost);
		p1.setDirectLaborCost(p1.getDirectLaborCost() + laborCost);
		p1.setIndirectLaborCost(p1.getIndirectLaborCost() +  laborCost * 0.25);
		p1.setEnergyCost(p1.getEnergyCost() + energyCost);
		p1.setEquipmentDepreciation(p1.getEquipmentDepreciation() + equipmentDepreciation );
		p1.setMaintenanceCost( p1.getMaintenanceCost() +  (toolingCost + equipmentDepreciation) * 0.2 );
		p1.setSubTotal(p1.getDirectLaborCost() + p1.getIndirectLaborCost() + p1.getEnergyCost() + p1.getEquipmentDepreciation() + p1.getMaintenanceCost());
		p1.setLogistics(p1.getTransportation() + p1.getPack());
		p1.setFinicialInterest((p1.getRawMaterial() + p1.getToolingCost()) * 0.072 );
		p1.setFreePrice((p1.getToolingCost() + p1.getSubTotal()) * 0.15);
		p1.setTotalPrice(p1.getPurchasingCost() + p1.getToolingCost() + p1.getSubTotal() + p1.getFreePrice() + p1.getFinicialInterest());
		
	}

	@Override
	public Project getProject(ProjectQuotationList list) {
		ProjectQuotationList pql = (ProjectQuotationList) totalDao.get(list.getClass(), list.getId());
		ProjectQuotationList temp = pql;
		while(temp.getMyroot() != null){
			temp = temp.getMyroot();
		}
		return temp.getRoot();
	}

}
