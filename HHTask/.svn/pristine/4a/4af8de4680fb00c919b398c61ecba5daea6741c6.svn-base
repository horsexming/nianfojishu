package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationLogisticsService;
import com.task.Server.ProjectQuotationService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationLogistics;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectQuotationLogisticsAction extends ActionSupport {
	
	
	private ProjectQuotationLogisticsService projectQuotationLogisticsService;
	private List<ProjectQuotationLogistics> logisticses;
	private ProjectQuotation quotation;
	private ProjectQuotationLogistics logistics;
	
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
		if(!projectAuthService.isLook(quotation.getRoot().getId(), userkk.getId(), "xmbjd_zjrg_add")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		try {
			projectQuotationLogisticsService.add(logistics, quotation.getId());
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(true, "添加失败", null);
		}
		return null;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(quotation.getRoot().getId(), userkk.getId(), "xmbjd_zjrg_add")){
			return "noAuth";
		}
		Object[] object = projectQuotationLogisticsService.getByQuotation(quotation.getId(), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			logisticses = (List<ProjectQuotationLogistics>) object[0];
			if (logisticses != null && logisticses.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectQuotationLogistics_list.action?quotation.id=" + quotation.getId());
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
			projectQuotationLogisticsService.update(logistics);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(true, "修改失败", null);
		}
		return null;
	}
	
	public String updateInput(){
		logistics = projectQuotationLogisticsService.get(logistics);
		
		return INPUT;
	}
	
	public String delete(){
		projectQuotationLogisticsService.delete(logistics);
		return null;
	}

	public ProjectQuotationLogisticsService getProjectQuotationLogisticsService() {
		return projectQuotationLogisticsService;
	}

	public void setProjectQuotationLogisticsService(ProjectQuotationLogisticsService projectQuotationLogisticsService) {
		this.projectQuotationLogisticsService = projectQuotationLogisticsService;
	}

	public List<ProjectQuotationLogistics> getLogisticses() {
		return logisticses;
	}

	public void setLogisticses(List<ProjectQuotationLogistics> logisticses) {
		this.logisticses = logisticses;
	}

	public ProjectQuotation getQuotation() {
		return quotation;
	}

	public void setQuotation(ProjectQuotation quotation) {
		this.quotation = quotation;
	}

	public ProjectQuotationLogistics getLogistics() {
		return logistics;
	}

	public void setLogistics(ProjectQuotationLogistics logistics) {
		this.logistics = logistics;
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
