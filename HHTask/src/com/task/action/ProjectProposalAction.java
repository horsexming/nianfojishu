package com.task.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectProposalService;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectProposalAction extends ActionSupport {
	private ProjectAuthService projectAuthService;
	private ProjectProposal proposal;
	private ProjectProposalService projectProposalService;
	private String basicSelect;
	private String deputuSelect;
	private String bossSelect;

	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(proposal.getRoot().getId(), userkk.getId(), "xmjys_add")){
			return "noAuth";
		}
		if(projectProposalService.get(proposal) != null){
			return "hasPP";
		}
		return SUCCESS;
	}
	
	public String add(){
		Set<ProjectProposalFlow> set = new HashSet<ProjectProposalFlow>();
		try {
			for (String s : basicSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(1);
				flow.setChecks(false);
				set.add(flow);
			}
			for (String s : deputuSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(2);
				flow.setChecks(false);
				set.add(flow);
			}
			for (String s : bossSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(3);
				flow.setChecks(false);
				set.add(flow);
			}
			proposal.setCheck(set);
			projectProposalService.add(proposal);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String updateInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(proposal.getRoot().getId(), userkk.getId(), "xmjys_edit")){
			return "noAuth";
		}
		if(projectProposalService.isCheck(projectProposalService.get(proposal.getRoot()))){
			return "check";
		}
		proposal = projectProposalService.get(proposal.getRoot());
		return INPUT;
	}
	
	public String update(){
		Set<ProjectProposalFlow> set = new HashSet<ProjectProposalFlow>();
		try {
			for (String s : basicSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(1);
				flow.setChecks(false);
				set.add(flow);
			}
			for (String s : deputuSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(2);
				flow.setChecks(false);
				set.add(flow);
			}
			for (String s : bossSelect.split(",")) {
				ProjectProposalFlow flow = new ProjectProposalFlow();
				flow.setName(s);
				flow.setLevel(3);
				flow.setChecks(false);
				set.add(flow);
			}
			proposal.setCheck(set);
			projectProposalService.update(proposal);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	
	public String list(){
		proposal = projectProposalService.list(proposal);
		if(proposal == null){
			return "noAuth";
		}
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(proposal.getRoot().getId(), userkk.getId(), "xmjys_check")){
			return "noAuth";
		}
		projectProposalService.getUser(proposal);
		return INPUT;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}

	public ProjectProposal getProposal() {
		return proposal;
	}

	public void setProposal(ProjectProposal proposal) {
		this.proposal = proposal;
	}

	public String getBasicSelect() {
		return basicSelect;
	}

	public void setBasicSelect(String basicSelect) {
		this.basicSelect = basicSelect;
	}

	public String getDeputuSelect() {
		return deputuSelect;
	}

	public void setDeputuSelect(String deputuSelect) {
		this.deputuSelect = deputuSelect;
	}

	public String getBossSelect() {
		return bossSelect;
	}

	public void setBossSelect(String bossSelect) {
		this.bossSelect = bossSelect;
	}

	public ProjectProposalService getProjectProposalService() {
		return projectProposalService;
	}

	public void setProjectProposalService(ProjectProposalService projectProposalService) {
		this.projectProposalService = projectProposalService;
	}

}
