package com.task.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectProposalService;
import com.task.Server.ProjectQuotationListService;
import com.task.Server.ProjectService;
import com.task.Server.ProjectStartService;
import com.task.Server.ProjectTrackService;
import com.task.entity.Project;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectQuotationList;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectTrack;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectAction extends ActionSupport {
	private ProjectService projectService;
	private Project project;
	private List<Project> projects;
	private String dept;
	private String proposalAdmin;
	private String quotationAdmin;

	private ProjectProposalService projectProposalService;
	private ProjectQuotationListService projectQuotationListService;
	private ProjectStartService projectStartService;
	private ProjectTrackService projectTrackService;

	private ProjectProposal projectProposal;
	private ProjectQuotationList projectQuotation;
	private ProjectStart projectStart;
	private ProjectTrack projectTrack;

	private String errorMessage;
	private String successMessage;// 成功信息

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String add() {
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		project.setCreatePerson(user.getName());
		project.setCreateDate(sdf.format(d));
		project.setClosed(false);
		projectService.add(project);
		return SUCCESS;
	}

	public String addInput() {
		return INPUT;
	}

	public String list() {
		Object[] object = projectService.getList(Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			projects = (List<Project>) object[0];
			if (projects != null && projects.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("Project_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return SUCCESS;
	}

	public String updateInput() {
		project = projectService.get(project);
		return INPUT;
	}

	public String update() {
		Project project = projectService.get(this.project);
		String name = this.project.getName();
		String numb = this.project.getNumb();
		if (name != null && !name.equals("")) {
			project.setName(name);
		}
		if (numb != null && !numb.equals("")) {
			project.setNumb(numb);
		}
		projectService.update(project);
		return SUCCESS;
	}

	public String showAll() {
		project = projectService.get(project);
		projectProposal = new ProjectProposal();
		projectQuotation = new ProjectQuotationList();
		projectProposal.setRoot(project);
		projectQuotation.setRoot(project);

		projectProposal = projectProposalService.get(projectProposal);
		projectQuotation = projectQuotationListService.get(project);
		projectStart = projectStartService.get(project);
		projectTrack = projectTrackService.get(project);
		return SUCCESS;
	}

	public String closed() {
		try {
			projectService.closed(project);
			MKUtil.writeJSON(true, "关闭成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "关闭失败:" + e.getMessage(), null);
		}
		return null;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getProposalAdmin() {
		return proposalAdmin;
	}

	public void setProposalAdmin(String proposalAdmin) {
		this.proposalAdmin = proposalAdmin;
	}

	public String getQuotationAdmin() {
		return quotationAdmin;
	}

	public void setQuotationAdmin(String quotationAdmin) {
		this.quotationAdmin = quotationAdmin;
	}

	public ProjectProposalService getProjectProposalService() {
		return projectProposalService;
	}

	public void setProjectProposalService(
			ProjectProposalService projectProposalService) {
		this.projectProposalService = projectProposalService;
	}

	public ProjectStartService getProjectStartService() {
		return projectStartService;
	}

	public void setProjectStartService(ProjectStartService projectStartService) {
		this.projectStartService = projectStartService;
	}

	public ProjectTrackService getProjectTrackService() {
		return projectTrackService;
	}

	public void setProjectTrackService(ProjectTrackService projectTrackService) {
		this.projectTrackService = projectTrackService;
	}

	public ProjectStart getProjectStart() {
		return projectStart;
	}

	public void setProjectStart(ProjectStart projectStart) {
		this.projectStart = projectStart;
	}

	public ProjectTrack getProjectTrack() {
		return projectTrack;
	}

	public void setProjectTrack(ProjectTrack projectTrack) {
		this.projectTrack = projectTrack;
	}

	public ProjectProposal getProjectProposal() {
		return projectProposal;
	}

	public void setProjectProposal(ProjectProposal projectProposal) {
		this.projectProposal = projectProposal;
	}

	public ProjectQuotationList getProjectQuotation() {
		return projectQuotation;
	}

	public void setProjectQuotation(ProjectQuotationList projectQuotation) {
		this.projectQuotation = projectQuotation;
	}

	public ProjectQuotationListService getProjectQuotationListService() {
		return projectQuotationListService;
	}

	public void setProjectQuotationListService(
			ProjectQuotationListService projectQuotationListService) {
		this.projectQuotationListService = projectQuotationListService;
	}

}
