package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationRawMaterialService;
import com.task.Server.ProjectQuotationService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationRawMaterial;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectQuotationRawMaterialAction extends ActionSupport {
	private ProjectQuotationRawMaterialService projectQuotationRawMaterialService;
	private ProjectQuotationService projectQuotationService;
	private List<ProjectQuotationRawMaterial> rawMaterials;
	private ProjectQuotation quotation;
	private ProjectQuotationRawMaterial rawMaterial;
	private ProjectAuthService projectAuthService;
	

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
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_ycl_add")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		try {
			projectQuotationRawMaterialService.add(rawMaterial, rawMaterial.getRoot().getId());
			MKUtil.writeJSON(true, "添加 " + rawMaterial.getMaterialGrade() + " 成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(true, "添加失败", null);
		}
		return null;
	}
	
	public String updateInput(){
		rawMaterial = projectQuotationRawMaterialService.get(rawMaterial);
		return INPUT;
	}
	
	public String update(){
		try {
			projectQuotationRawMaterialService.update(rawMaterial);
			MKUtil.writeJSON(true, "修改 " + rawMaterial.getMaterialGrade() + " 成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(true, "修改失败" + e.getMessage(), null);
		}
		return null;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_ycl_add")){
			return "noAuth";
		}
		Object[] object = projectQuotationRawMaterialService.getByQuotation(quotation.getId(), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			rawMaterials = (List<ProjectQuotationRawMaterial>) object[0];
			if (rawMaterials != null && rawMaterials.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectQuotationRawMaterial_list.action?quotation.id=" + quotation.getId());
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return SUCCESS;
	}
	
	public String delete(){
		projectQuotationRawMaterialService.delete(rawMaterial);
		return null;
	}

	public ProjectQuotationRawMaterialService getProjectQuotationRawMaterialService() {
		return projectQuotationRawMaterialService;
	}

	public void setProjectQuotationRawMaterialService(ProjectQuotationRawMaterialService projectQuotationRawMaterialService) {
		this.projectQuotationRawMaterialService = projectQuotationRawMaterialService;
	}

	public List<ProjectQuotationRawMaterial> getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(List<ProjectQuotationRawMaterial> rawMaterials) {
		this.rawMaterials = rawMaterials;
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

	public ProjectQuotation getQuotation() {
		return quotation;
	}

	public void setQuotation(ProjectQuotation quotation) {
		this.quotation = quotation;
	}

	public ProjectQuotationRawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(ProjectQuotationRawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
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
