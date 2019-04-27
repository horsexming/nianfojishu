package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartUserService;
import com.task.Server.ProjectTrackRecordService;
import com.task.Server.ProjectTrackService;
import com.task.entity.ProjectStartUser;
import com.task.entity.ProjectTrack;
import com.task.entity.ProjectTrackRecord;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectTrackRecordAction extends ActionSupport {
	private ProjectTrackRecordService projectTrackRecordService;
	private ProjectStartUserService projectStartUserService;
	private ProjectTrackRecord p;
	private ProjectStartUser startUser;
	private List<ProjectTrackRecord> recoreds;
	
	private ProjectAuthService projectAuthService;
	private ProjectTrackService projectTrackService;

	private String errorMessage;
	private String successMessage;// 成功信息
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectTrack start = projectTrackService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmgz_xmgz")){
			return "noAuth";
		}
		return INPUT;
	}
	
	public String add(){
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		String name = user.getName();
		p.setUsername(name);
		projectTrackRecordService.add(p);
		return SUCCESS;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectTrack start = projectTrackService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmgz_xmgz")){
			return "noAuth";
		}
		Object[] object = projectTrackRecordService.getByTrack(p, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			recoreds = (List<ProjectTrackRecord>) object[0];
			if (recoreds != null && recoreds.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectTrackRecord_list.action?p.root.id=" + p.getRoot().getId());
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		
		// 让项目负责人审批
		startUser = projectTrackRecordService.getBoss(p);
		if(startUser != null){
			String name = userkk.getName();
			if(!name.equals(startUser.getInCharge())){
				startUser = null;
			}
			
		}
		
		return SUCCESS;
	}	
	
	public String checkInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectTrack start = projectTrackService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmgz_xmgz")){
			return "noAuth";
		}
		p = projectTrackRecordService.get(p);
		return INPUT;
	}
	
	public String complete(){
		try {
			projectTrackRecordService.updateComplete(p);
			MKUtil.writeJSON(true, "此记录已完成", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "完成失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String checked(){
		projectTrackRecordService.updateCheck(p);
		return SUCCESS;
	}
	
	public String listSingle(){
		p = projectTrackRecordService.get(p);
		return SUCCESS;
	}
	
	public ProjectTrackRecordService getProjectTrackRecordService() {
		return projectTrackRecordService;
	}

	public void setProjectTrackRecordService(ProjectTrackRecordService projectTrackRecordService) {
		this.projectTrackRecordService = projectTrackRecordService;
	}

	public ProjectTrackRecord getP() {
		return p;
	}

	public void setP(ProjectTrackRecord p) {
		this.p = p;
	}

	public List<ProjectTrackRecord> getRecoreds() {
		return recoreds;
	}

	public void setRecoreds(List<ProjectTrackRecord> recoreds) {
		this.recoreds = recoreds;
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

	public ProjectStartUserService getProjectStartUserService() {
		return projectStartUserService;
	}

	public void setProjectStartUserService(ProjectStartUserService projectStartUserService) {
		this.projectStartUserService = projectStartUserService;
	}

	public ProjectStartUser getStartUser() {
		return startUser;
	}

	public void setStartUser(ProjectStartUser startUser) {
		this.startUser = startUser;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}

	public ProjectTrackService getProjectTrackService() {
		return projectTrackService;
	}

	public void setProjectTrackService(ProjectTrackService projectTrackService) {
		this.projectTrackService = projectTrackService;
	}
	
	

}
