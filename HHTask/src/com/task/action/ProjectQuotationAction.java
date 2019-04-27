package com.task.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationService;
import com.task.entity.Project;
import com.task.entity.ProjectQuotation;
import com.task.entity.Users;
import com.task.util.MKUtil;

/**
 * 项目报价
 * @author 马凯
 *
 */
public class ProjectQuotationAction extends ActionSupport {
	private ProjectQuotationService projectQuotationService;
	private ProjectAuthService projectAuthService;
	private ProjectQuotation quotation;
	
	
	public String addInput() {
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		Project p =projectQuotationService.getProject(quotation.getRoot());
		if(!projectAuthService.isLook(p.getId(), userkk.getId(), "xmbjd_list_add")){
			return "noAuth";
		}
//		if(!projectQuotationService.isClosed(quotation)){
//			return "noClosed";
//		}

		return INPUT;
	}
	
	public String add() {
		projectQuotationService.add(quotation);
		return SUCCESS;
	}
	
	public String addChild(){
		try {
			projectQuotationService.addChild(quotation);
			MKUtil.writeJSON(true, "添加【" + quotation.getDescription() + "】成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加", null);
		}
		return null;
	}
	
	public String addChildInput(){
		return INPUT;
	}
	
	public String list(){
		quotation = projectQuotationService.get(quotation);
		return SUCCESS;
	}
	

	public String showAll(){
		
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(projectQuotationService.get(quotation).getId(), userkk.getId(), "xmbjd_jsbj")){
			return "noAuth";
		}
		quotation = projectQuotationService.update(quotation);
		return SUCCESS;
	}

	public String updateOther(){
		try {
			projectQuotationService.updateOther(quotation);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败", null);
		}
		return null;
	}
	
	public String updateOtherInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		quotation = projectQuotationService.get(quotation);
		if(!projectAuthService.isLook(quotation.getRoot().getId(), userkk.getId(), "xmbjd_zjrg_add")){
			return "noAuth";
		}
		quotation = projectQuotationService.get(quotation);
		return "updateOtherInput";
	}

	public ProjectQuotationService getProjectQuotationService() {
		return projectQuotationService;
	}

	public void setProjectQuotationService(ProjectQuotationService projectQuotationService) {
		this.projectQuotationService = projectQuotationService;
	}

	public ProjectQuotation getQuotation() {
		return quotation;
	}

	public void setQuotation(ProjectQuotation quotation) {
		this.quotation = quotation;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}
	
	
}
