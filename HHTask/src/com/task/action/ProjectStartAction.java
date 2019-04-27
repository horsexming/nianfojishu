package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ProjectStartBudgetService;
import com.task.Server.ProjectStartClaimService;
import com.task.Server.ProjectStartCountersignedService;
import com.task.Server.ProjectStartOutlineService;
import com.task.Server.ProjectStartResponsibleService;
import com.task.Server.ProjectStartScheduleService;
import com.task.Server.ProjectStartService;
import com.task.Server.ProjectStartTechnicalService;
import com.task.Server.ProjectStartUserService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartClaim;
import com.task.entity.ProjectStartCountersigned;
import com.task.entity.ProjectStartOutline;
import com.task.entity.ProjectStartResponsible;
import com.task.entity.ProjectStartTechnical;

public class ProjectStartAction extends ActionSupport {
	
	private ProjectStart projectStart;
	private ProjectStartOutline projectStartOutline;
	private ProjectStartTechnical projectStartTechnical;
	private int projectStartBudget;
	private ProjectStartCountersigned projectStartCountersigned;
	private ProjectStartResponsible projectStartResponsible;
	private int projectStartSchedule;
	private int projectStartUser;
	private ProjectStartClaim projectStartClaim;
	
	private ProjectStartService projectStartService;
	private ProjectStartOutlineService projectStartOutlineService;
	private ProjectStartTechnicalService projectStartTechnicalService;
	private ProjectStartBudgetService projectStartBudgetService;
	private ProjectStartCountersignedService projectStartCountersignedService;
	private ProjectStartResponsibleService projectStartResponsibleService;
	private ProjectStartScheduleService projectStartScheduleService;
	private ProjectStartUserService projectStartUserService;
	private ProjectStartClaimService projectStartClaimService;
	
	public String add(){
		if(projectStartService.get(projectStart.getRoot()) != null){
			return "has";
		}
		projectStartService.add(projectStart);
		return SUCCESS;
	}

	public String addInput(){
		return INPUT;
	}

	public String list(){
		
		projectStart = projectStartService.get(projectStart.getRoot());
		if(projectStart == null){
			return "nohas"; 
		}
		projectStartOutline = projectStartOutlineService.get(projectStart);
		projectStartTechnical = projectStartTechnicalService.get(projectStart);
		projectStartBudget = projectStartBudgetService.getCount(projectStart);
		projectStartCountersigned = projectStartCountersignedService.get(projectStart);
		projectStartResponsible = projectStartResponsibleService.get(projectStart);
		projectStartSchedule = projectStartScheduleService.getCount(projectStart);
		projectStartUser = projectStartUserService.getCount(projectStart);
		projectStartClaim = projectStartClaimService.get(projectStart);
		return SUCCESS;
	}
	
	public String closed(){
		projectStartService.updateClose(projectStart);
		return null;
	}

	public ProjectStartService getProjectStartService() {
		return projectStartService;
	}

	public void setProjectStartService(ProjectStartService projectStartService) {
		this.projectStartService = projectStartService;
	}

	public ProjectStart getProjectStart() {
		return projectStart;
	}

	public void setProjectStart(ProjectStart projectStart) {
		this.projectStart = projectStart;
	}

	public ProjectStartOutline getProjectStartOutline() {
		return projectStartOutline;
	}

	public void setProjectStartOutline(ProjectStartOutline projectStartOutline) {
		this.projectStartOutline = projectStartOutline;
	}

	public ProjectStartOutlineService getProjectStartOutlineService() {
		return projectStartOutlineService;
	}

	public void setProjectStartOutlineService(ProjectStartOutlineService projectStartOutlineService) {
		this.projectStartOutlineService = projectStartOutlineService;
	}

	public ProjectStartTechnical getProjectStartTechnical() {
		return projectStartTechnical;
	}

	public void setProjectStartTechnical(ProjectStartTechnical projectStartTechnical) {
		this.projectStartTechnical = projectStartTechnical;
	}

	public ProjectStartTechnicalService getProjectStartTechnicalService() {
		return projectStartTechnicalService;
	}

	public void setProjectStartTechnicalService(ProjectStartTechnicalService projectStartTechnicalService) {
		this.projectStartTechnicalService = projectStartTechnicalService;
	}

	public int getProjectStartBudget() {
		return projectStartBudget;
	}

	public void setProjectStartBudget(int projectStartBudget) {
		this.projectStartBudget = projectStartBudget;
	}

	public ProjectStartBudgetService getProjectStartBudgetService() {
		return projectStartBudgetService;
	}

	public void setProjectStartBudgetService(ProjectStartBudgetService projectStartBudgetService) {
		this.projectStartBudgetService = projectStartBudgetService;
	}

	public ProjectStartCountersigned getProjectStartCountersigned() {
		return projectStartCountersigned;
	}

	public void setProjectStartCountersigned(ProjectStartCountersigned projectStartCountersigned) {
		this.projectStartCountersigned = projectStartCountersigned;
	}

	public ProjectStartCountersignedService getProjectStartCountersignedService() {
		return projectStartCountersignedService;
	}

	public void setProjectStartCountersignedService(ProjectStartCountersignedService projectStartCountersignedService) {
		this.projectStartCountersignedService = projectStartCountersignedService;
	}

	public ProjectStartResponsible getProjectStartResponsible() {
		return projectStartResponsible;
	}

	public void setProjectStartResponsible(ProjectStartResponsible projectStartResponsible) {
		this.projectStartResponsible = projectStartResponsible;
	}

	public ProjectStartResponsibleService getProjectStartResponsibleService() {
		return projectStartResponsibleService;
	}

	public void setProjectStartResponsibleService(ProjectStartResponsibleService projectStartResponsibleService) {
		this.projectStartResponsibleService = projectStartResponsibleService;
	}

	public int getProjectStartSchedule() {
		return projectStartSchedule;
	}

	public void setProjectStartSchedule(int projectStartSchedule) {
		this.projectStartSchedule = projectStartSchedule;
	}

	public ProjectStartScheduleService getProjectStartScheduleService() {
		return projectStartScheduleService;
	}

	public void setProjectStartScheduleService(ProjectStartScheduleService projectStartScheduleService) {
		this.projectStartScheduleService = projectStartScheduleService;
	}

	public int getProjectStartUser() {
		return projectStartUser;
	}

	public void setProjectStartUser(int projectStartUser) {
		this.projectStartUser = projectStartUser;
	}

	public ProjectStartUserService getProjectStartUserService() {
		return projectStartUserService;
	}

	public void setProjectStartUserService(ProjectStartUserService projectStartUserService) {
		this.projectStartUserService = projectStartUserService;
	}

	public ProjectStartClaim getProjectStartClaim() {
		return projectStartClaim;
	}

	public void setProjectStartClaim(ProjectStartClaim projectStartClaim) {
		this.projectStartClaim = projectStartClaim;
	}

	public ProjectStartClaimService getProjectStartClaimService() {
		return projectStartClaimService;
	}

	public void setProjectStartClaimService(ProjectStartClaimService projectStartClaimService) {
		this.projectStartClaimService = projectStartClaimService;
	}

}
