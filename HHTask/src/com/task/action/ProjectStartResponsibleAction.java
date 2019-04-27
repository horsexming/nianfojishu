package com.task.action;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartResponsibleService;
import com.task.Server.ProjectStartService;
import com.task.entity.ProjectStartResponsible;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectStartResponsibleAction extends ActionSupport {
	
	private ProjectStartResponsibleService projectStartResponsibleService;
	private ProjectStartResponsible p;

	private ProjectAuthService projectAuthService;
	private ProjectStartService projectStartService;

	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p.setRoot(projectStartService.get(p.getRoot()));
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmzrs")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		String fileName = MKUtil.saveFile(attachment, attachmentFileName);
		p.setResponsibility(fileName);
		projectStartResponsibleService.add(p);
		return SUCCESS;
	}
	
	public String updateInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartResponsibleService.get(p);
		p.setRoot(projectStartService.get(p.getRoot()));
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmzrs")){
			return "noAuth";
		}
		p = projectStartResponsibleService.get(p);
		return INPUT;
	}
	
	public String update(){
		if(attachment != null && attachment.length > 0){
			String fileName = MKUtil.saveFile(attachment, attachmentFileName);
			p.setResponsibility(fileName);
			projectStartResponsibleService.update(p);
		}
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartResponsibleService.get(p);
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmzrs")){
			return "noAuth";
		}
		p = projectStartResponsibleService.get(p);
		return SUCCESS;
	}

	public ProjectStartResponsibleService getProjectStartResponsibleService() {
		return projectStartResponsibleService;
	}

	public void setProjectStartResponsibleService(ProjectStartResponsibleService projectStartResponsibleService) {
		this.projectStartResponsibleService = projectStartResponsibleService;
	}

	public ProjectStartResponsible getP() {
		return p;
	}

	public void setP(ProjectStartResponsible p) {
		this.p = p;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
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
