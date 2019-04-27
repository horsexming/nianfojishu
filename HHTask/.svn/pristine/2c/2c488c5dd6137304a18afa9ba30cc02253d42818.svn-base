package com.task.action;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartClaimService;
import com.task.Server.ProjectStartService;
import com.task.entity.ProjectStartClaim;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectStartClaimAction extends ActionSupport {
	
	private ProjectStartClaimService projectStartClaimService;
	private ProjectStartClaim p;
	
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
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmyq")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		String fileName = MKUtil.saveFile(attachment, attachmentFileName);
		p.setClaimName(fileName);
		projectStartClaimService.add(p);
		return SUCCESS;
	}
	
	public String updateInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartClaimService.get(p);
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmyq")){
			return "noAuth";
		}
		p = projectStartClaimService.get(p);
		return INPUT;
	}
	
	public String update(){
		if(attachment != null && attachment.length > 0){
			String fileName = MKUtil.saveFile(attachment, attachmentFileName);
			p.setClaimName(fileName);
			projectStartClaimService.update(p);
		}
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		p = projectStartClaimService.get(p);
		if(!projectAuthService.isLook(p.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmyq")){
			return "noAuth";
		}
		p = projectStartClaimService.get(p);
		return SUCCESS;
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

	public ProjectStartClaimService getProjectStartClaimService() {
		return projectStartClaimService;
	}

	public void setProjectStartClaimService(ProjectStartClaimService projectStartClaimService) {
		this.projectStartClaimService = projectStartClaimService;
	}

	public ProjectStartClaim getP() {
		return p;
	}

	public void setP(ProjectStartClaim p) {
		this.p = p;
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
