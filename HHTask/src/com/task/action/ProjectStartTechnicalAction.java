package com.task.action;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartService;
import com.task.Server.ProjectStartTechnicalService;
import com.task.entity.ProjectStartTechnical;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectStartTechnicalAction extends ActionSupport {
	
	private ProjectStartTechnicalService projectStartTechnicalService;
	private ProjectStartTechnical p;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	private ProjectAuthService projectAuthService;
	private ProjectStartService projectStartService;

	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p.setRoot(projectStartService.get(p.getRoot()));
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_jsfa")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		String fileName = MKUtil.saveFile(attachment, attachmentFileName);
		p.setTechnicalProgram(fileName);
		projectStartTechnicalService.add(p);
		return SUCCESS;
	}
	
	public String updateInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartTechnicalService.get(p);
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_jsfa")){
			return "noAuth";
		}
		p = projectStartTechnicalService.get(p);
		return INPUT;
	}
	
	public String update(){
		if(attachment != null && attachment.length > 0){
			String fileName = MKUtil.saveFile(attachment, attachmentFileName);
			p.setTechnicalProgram(fileName);
			projectStartTechnicalService.update(p);
		}
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartTechnicalService.get(p);
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_jsfa")){
			return "noAuth";
		}
		p = projectStartTechnicalService.get(p);
		return SUCCESS;
	}

	public ProjectStartTechnicalService getProjectStartTechnicalService() {
		return projectStartTechnicalService;
	}

	public void setProjectStartTechnicalService(ProjectStartTechnicalService projectStartTechnicalService) {
		this.projectStartTechnicalService = projectStartTechnicalService;
	}

	public ProjectStartTechnical getP() {
		return p;
	}

	public void setP(ProjectStartTechnical p) {
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
