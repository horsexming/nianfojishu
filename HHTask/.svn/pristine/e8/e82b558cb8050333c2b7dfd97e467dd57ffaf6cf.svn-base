package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectQuotationListService;
import com.task.Server.ProjectQuotationService;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationList;
import com.task.entity.Users;
import com.task.util.MKUtil;

/**
 * 项目报价
 * @author 马凯
 *
 */
public class ProjectQuotationListAction extends ActionSupport {
	private ProjectQuotationListService projectQuotationListService;
	private ProjectAuthService projectAuthService;
	private ProjectQuotationService projectQuotationService;
	private ProjectQuotationList quotationList;
	private List<ProjectQuotationList> quotationLists;
	private List<ProjectQuotation> quotations;
	
	
	public String addInput() {
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		/*陈曦20140415
		if(!projectAuthService.isLook(projectQuotationListService.getProject(quotationList).getId(), user.getId(), "xmbjd_list_add")){
			return "noAuth";
		}
		*/
		//陈曦20140415
		if(!projectAuthService.isLook(quotationList.getRoot().getId(), user.getId(), "xmbjd_list_add")){
			return "noAuth";
		}
		
		ProjectQuotationList list = projectQuotationListService.get(quotationList.getRoot());

		if(list != null){
			return "has";
		}
		if(list == null && projectQuotationListService.isClosedByProposal(quotationList.getRoot())){
			return "noClosed";
		}
		
		return INPUT;
	}
	
	public String add() {
		projectQuotationListService.add(quotationList);
		return SUCCESS;
	}
	
	public String list(){
		if(projectQuotationListService.get(quotationList.getRoot()) == null){
			return "nohas";
		}
		quotationList = projectQuotationListService.get(quotationList.getRoot());
		quotationLists = projectQuotationListService.getChildren(quotationList);
		return SUCCESS;
	}
	
	public String showAll(){
//		quotationList = projectQuotationListService.updateAttr(quotationList);
		
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(projectQuotationListService.getProject(quotationList).getId(), user.getId(), "xmbjd_jsbj")){
			return "noAuth";
		}
		quotationList = projectQuotationListService.showAll(quotationList);
		return SUCCESS;
	}
	
	public String updateWlInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(projectQuotationListService.getProject(quotationList).getId(), user.getId(), "xmbjd_wlfy_add")){
			return "noAuth";
		}
		quotationList = projectQuotationListService.get(quotationList);
		return INPUT;
	}
	
	public String updateOiInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(projectQuotationListService.getProject(quotationList).getId(), user.getId(), "xmbjd_lr_add")){
			return "noAuth";
		}
		quotationList = projectQuotationListService.get(quotationList);
		return INPUT;
	}
	
	public String updateWl(){
		try{
			projectQuotationListService.updateWl(quotationList);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String updateOi(){
		try{
			projectQuotationListService.updateOi(quotationList);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String addDirectoryInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(projectQuotationListService.getProject(quotationList).getId(), user.getId(), "xmbjd_list_add")){
			return "noAuth";
		}
		return SUCCESS;
	}
	
//	public String addChild(){
//		try {
//			projectQuotationListService.add(quotationList);
//			MKUtil.writeJSON(true, "添加" + quotationList + "成功!", null);
//		} catch (Exception e) {
//		}
//	}
//	
	public String addDirectory(){
		try {
			projectQuotationListService.addDirectory(quotationList);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(true, "添加成功", null);
		}
		return null;
	}

	public String listChildren(){
		quotations = projectQuotationService.get(quotationList);
		quotationLists = projectQuotationListService.getChildren(quotationList);
		return SUCCESS;
	}
	
	public ProjectQuotationListService getProjectQuotationListService() {
		return projectQuotationListService;
	}

	public void setProjectQuotationListService(ProjectQuotationListService projectQuotationListService) {
		this.projectQuotationListService = projectQuotationListService;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}

	public ProjectQuotationList getQuotationList() {
		return quotationList;
	}

	public void setQuotationList(ProjectQuotationList quotationList) {
		this.quotationList = quotationList;
	}

	public List<ProjectQuotationList> getQuotationLists() {
		return quotationLists;
	}

	public void setQuotationLists(List<ProjectQuotationList> quotationLists) {
		this.quotationLists = quotationLists;
	}

	public ProjectQuotationService getProjectQuotationService() {
		return projectQuotationService;
	}

	public void setProjectQuotationService(ProjectQuotationService projectQuotationService) {
		this.projectQuotationService = projectQuotationService;
	}

	public List<ProjectQuotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<ProjectQuotation> quotations) {
		this.quotations = quotations;
	}
	

}
