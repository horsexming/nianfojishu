package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartScheduleService;
import com.task.Server.ProjectStartService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartSchedule;
import com.task.entity.Users;

public class ProjectStartScheduleAction extends ActionSupport {
	private ProjectStartSchedule p;
	private ProjectStartScheduleService projectStartScheduleService;
	private List<ProjectStartSchedule> schedules;

	private String errorMessage;
	private String successMessage;// 成功信息
	
	private ProjectAuthService projectAuthService;
	private ProjectStartService projectStartService;
	
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
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmjd")){
			return "noAuth";
		}
		return INPUT;
	}

	public String add(){
		projectStartScheduleService.add(p);
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectStart start = projectStartService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmjd")){
			return "noAuth";
		}
		Object[] object = projectStartScheduleService.getByStart(p, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			schedules = (List<ProjectStartSchedule>) object[0];
			if (schedules != null && schedules.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectStartSchedule_list.action?p.root.id=" + p.getRoot().getId());
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
		ProjectStartSchedule start = projectStartScheduleService.get(p);
		if(!projectAuthService.isLook(start.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmjd")){
			return "noAuth";
		}
		p = projectStartScheduleService.get(p);
		return INPUT;
	}
	
	public String update(){
		projectStartScheduleService.update(p);
		return SUCCESS;
	}

	public ProjectStartSchedule getP() {
		return p;
	}

	public void setP(ProjectStartSchedule p) {
		this.p = p;
	}

	public ProjectStartScheduleService getProjectStartScheduleService() {
		return projectStartScheduleService;
	}

	public void setProjectStartScheduleService(ProjectStartScheduleService projectStartScheduleService) {
		this.projectStartScheduleService = projectStartScheduleService;
	}

	public List<ProjectStartSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<ProjectStartSchedule> schedules) {
		this.schedules = schedules;
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
