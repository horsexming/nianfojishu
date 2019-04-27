package com.task.action;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectProposalFlowService;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;
import com.task.entity.Users;

public class ProjectProposalFlowAction extends ActionSupport {
	private ProjectProposalFlowService projectProposalFlowService;
	private ProjectProposal proposal;
	private Set<ProjectProposalFlow> flows;
	private ProjectProposalFlow flow;
	
	public String update(){
		projectProposalFlowService.update(flow);
		return SUCCESS;
	}
	
	public String check(){
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		flows = projectProposalFlowService.getFlow(proposal);

		for (ProjectProposalFlow f : flows) {
			if(Integer.parseInt(f.getName()) == user.getId()){
				flow = f;
			}
		}
		if(flow == null){
			return "noAuth";
		}
		
		if(flow.isChecks()){
			return "hasCheck";
		}
		for (ProjectProposalFlow f : flows) {
			if(f.getLevel() < flow.getLevel() && !f.isChecks()){
				return "noCheck";
			}
		}
		return SUCCESS;
		
	}
	
	public String list(){
		flows = projectProposalFlowService.getFlow(proposal);
		flows = projectProposalFlowService.getUser(flows);
		return SUCCESS;
	}


	public ProjectProposalFlowService getProjectProposalFlowService() {
		return projectProposalFlowService;
	}

	public void setProjectProposalFlowService(ProjectProposalFlowService projectProposalFlowService) {
		this.projectProposalFlowService = projectProposalFlowService;
	}

	public ProjectProposal getProposal() {
		return proposal;
	}

	public void setProposal(ProjectProposal proposal) {
		this.proposal = proposal;
	}

	public Set<ProjectProposalFlow> getFlows() {
		return flows;
	}

	public void setFlows(Set<ProjectProposalFlow> flows) {
		this.flows = flows;
	}

	public ProjectProposalFlow getFlow() {
		return flow;
	}

	public void setFlow(ProjectProposalFlow flow) {
		this.flow = flow;
	}

}
