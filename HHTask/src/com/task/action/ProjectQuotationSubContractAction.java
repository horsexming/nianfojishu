package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationService;
import com.task.Server.ProjectQuotationSubContractService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationSubContract;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectQuotationSubContractAction extends ActionSupport {
	
	private ProjectQuotationSubContractService projectQuotationSubContractService;
	private List<ProjectQuotationSubContract> subContracts;
	private ProjectQuotation quotation;
	private ProjectQuotationSubContract subContract;
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
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_wgwx_add")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		try {
			projectQuotationSubContractService.add(subContract, quotation.getId());
			MKUtil.writeJSON(true, "添加" + subContract.getName() + "成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加" + subContract.getName() + "失败", null);
		}
		return null;
	}
	
	public String list(){
		
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_wgwx_add")){
			return "noAuth";
		}
		
		Object[] object = projectQuotationSubContractService.getByQuotation(quotation.getId(), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			subContracts = (List<ProjectQuotationSubContract>) object[0];
			if (subContracts != null && subContracts.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectQuotationSubContract_list.action?quotation.id=" + quotation.getId());
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return SUCCESS;
	}
	
	public String update(){
		try {
			projectQuotationSubContractService.update(subContract);
			MKUtil.writeJSON(true, "修改" + subContract.getName() + "成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "修改" + subContract.getName() + "失败", null);
		}
		return null;
	}
	
	public String updateInput(){
		subContract = projectQuotationSubContractService.get(subContract);
		return INPUT;
	}
	
	public String delete(){
		projectQuotationSubContractService.delete(subContract);
		return null;
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
	public ProjectQuotationSubContractService getProjectQuotationSubContractService() {
		return projectQuotationSubContractService;
	}
	public void setProjectQuotationSubContractService(ProjectQuotationSubContractService projectQuotationSubContractService) {
		this.projectQuotationSubContractService = projectQuotationSubContractService;
	}
	public List<ProjectQuotationSubContract> getSubContracts() {
		return subContracts;
	}
	public void setSubContracts(List<ProjectQuotationSubContract> subContracts) {
		this.subContracts = subContracts;
	}
	public ProjectQuotation getQuotation() {
		return quotation;
	}
	public void setQuotation(ProjectQuotation quotation) {
		this.quotation = quotation;
	}
	public ProjectQuotationSubContract getSubContract() {
		return subContract;
	}
	public void setSubContract(ProjectQuotationSubContract subContract) {
		this.subContract = subContract;
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
