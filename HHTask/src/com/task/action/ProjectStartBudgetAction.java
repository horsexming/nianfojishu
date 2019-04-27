package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartBudgetService;
import com.task.Server.ProjectStartService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartBudget;
import com.task.entity.Users;

public class ProjectStartBudgetAction extends ActionSupport {
	private ProjectStartBudget p;
	private ProjectStartBudgetService projectStartBudgetService;
	private List<ProjectStartBudget> budgets;
	private ProjectAuthService projectAuthService;
	private ProjectStartService projectStartService;

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
		ProjectStart start = projectStartService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmys")){
			return "noAuth";
		}
		return INPUT;
	}

	public String add(){
		projectStartBudgetService.add(p);
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectStart start = projectStartService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmys")){
			return "noAuth";
		}
		Object[] object = projectStartBudgetService.getByStart(p, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			budgets = (List<ProjectStartBudget>) object[0];
			if (budgets != null && budgets.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectStartBudget_list.action?p.root.id=" + p.getRoot().getId());
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
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectStartBudget start = projectStartBudgetService.get(p);
		if(!projectAuthService.isLook(start.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmys")){
			return "noAuth";
		}
		p = projectStartBudgetService.get(p);
		return INPUT;
	}
	
	public String update(){
		projectStartBudgetService.update(p);
		return SUCCESS;
	}

	public ProjectStartBudget getP() {
		return p;
	}

	public void setP(ProjectStartBudget p) {
		this.p = p;
	}

	public ProjectStartBudgetService getProjectStartBudgetService() {
		return projectStartBudgetService;
	}

	public void setProjectStartBudgetService(ProjectStartBudgetService projectStartBudgetService) {
		this.projectStartBudgetService = projectStartBudgetService;
	}

	public List<ProjectStartBudget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<ProjectStartBudget> budgets) {
		this.budgets = budgets;
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

	public ProjectStartService getProjectStartService() {
		return projectStartService;
	}

	public void setProjectStartService(ProjectStartService projectStartService) {
		this.projectStartService = projectStartService;
	}

}
