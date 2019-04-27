package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationDirectLaborCostService;
import com.task.Server.ProjectQuotationEquipmentDepreciationService;
import com.task.Server.ProjectQuotationService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationDirectLaborCost;
import com.task.entity.ProjectQuotationEquipmentDepreciation;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectQuotationEquipmentDepreciationAction extends ActionSupport {
	private ProjectQuotationEquipmentDepreciationService projectQuotationEquipmentDepreciationService;
	private ProjectQuotationDirectLaborCostService projectQuotationDirectLaborCostService;
	private List< ProjectQuotationEquipmentDepreciation> equipmentDepreciations;
	private List<ProjectQuotationDirectLaborCost> directLaborCosts;
	
	private ProjectQuotation quotation;
	private ProjectQuotationEquipmentDepreciation equipmentDepreciation;
	
	private ProjectAuthService projectAuthService;
	private ProjectQuotationService projectQuotationService;
	
	
	private String errorMessage;
	private String successMessage;// 成功信息
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_sbzj_add")){
			return "noAuth";
		}
		directLaborCosts = projectQuotationDirectLaborCostService.toSelector(quotation.getId());
		return INPUT;
	}
	
	public String add(){
		try {
			projectQuotationEquipmentDepreciationService.add(equipmentDepreciation, quotation.getId());
			MKUtil.writeJSON(true, "添加" + equipmentDepreciation.getProcess() + "成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加" + equipmentDepreciation.getProcess() + "失败", null);
		}
		return null;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_sbzj_add")){
			return "noAuth";
		}
		Object[] object = projectQuotationEquipmentDepreciationService.getByQuotation(quotation.getId(), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			equipmentDepreciations = (List<ProjectQuotationEquipmentDepreciation>) object[0];
			if (equipmentDepreciations != null && equipmentDepreciations.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectQuotationEquipmentDepreciation_list.action?quotation.id=" + quotation.getId());
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return SUCCESS;
	}
	
	public String updateInput(){
		equipmentDepreciation = projectQuotationEquipmentDepreciationService.get(equipmentDepreciation);
		directLaborCosts = projectQuotationDirectLaborCostService.toSelector(equipmentDepreciation.getRoot().getId());
		return INPUT;
	}
	
	public String update(){
		try {
			projectQuotationEquipmentDepreciationService.update(equipmentDepreciation);
			MKUtil.writeJSON(true, "修改" + equipmentDepreciation.getProcess() + "成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "修改" + equipmentDepreciation.getProcess() + "失败", null);
		}
		return null;
	}
	
	public String delete(){
		projectQuotationEquipmentDepreciationService.delete(equipmentDepreciation);
		return null;
	}
	
	public ProjectQuotationEquipmentDepreciationService getProjectQuotationEquipmentDepreciationService() {
		return projectQuotationEquipmentDepreciationService;
	}
	public void setProjectQuotationEquipmentDepreciationService(ProjectQuotationEquipmentDepreciationService projectQuotationEquipmentDepreciationService) {
		this.projectQuotationEquipmentDepreciationService = projectQuotationEquipmentDepreciationService;
	}
	public List<ProjectQuotationEquipmentDepreciation> getEquipmentDepreciations() {
		return equipmentDepreciations;
	}
	public void setEquipmentDepreciations(List<ProjectQuotationEquipmentDepreciation> equipmentDepreciations) {
		this.equipmentDepreciations = equipmentDepreciations;
	}
	public ProjectQuotation getQuotation() {
		return quotation;
	}
	public void setQuotation(ProjectQuotation quotation) {
		this.quotation = quotation;
	}
	public ProjectQuotationEquipmentDepreciation getEquipmentDepreciation() {
		return equipmentDepreciation;
	}
	public void setEquipmentDepreciation(ProjectQuotationEquipmentDepreciation equipmentDepreciation) {
		this.equipmentDepreciation = equipmentDepreciation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ProjectQuotationDirectLaborCostService getProjectQuotationDirectLaborCostService() {
		return projectQuotationDirectLaborCostService;
	}

	public void setProjectQuotationDirectLaborCostService(ProjectQuotationDirectLaborCostService projectQuotationDirectLaborCostService) {
		this.projectQuotationDirectLaborCostService = projectQuotationDirectLaborCostService;
	}

	public List<ProjectQuotationDirectLaborCost> getDirectLaborCosts() {
		return directLaborCosts;
	}

	public void setDirectLaborCosts(List<ProjectQuotationDirectLaborCost> directLaborCosts) {
		this.directLaborCosts = directLaborCosts;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}

	public ProjectQuotationService getProjectQuotationService() {
		return projectQuotationService;
	}

	public void setProjectQuotationService(ProjectQuotationService projectQuotationService) {
		this.projectQuotationService = projectQuotationService;
	}
	

}
