package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectStartService;
import com.task.Server.ProjectStartUserService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartUser;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectStartUserAction extends ActionSupport {
	private ProjectStartUser p;
	private ProjectStartUserService projectStartUserService;
	private List<ProjectStartUser> pUsers;
	
	private ProjectAuthService projectAuthService;
	private ProjectStartService projectStartService;

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
		ProjectStart start = projectStartService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmcy")) {
			return "noAuth";
		}
		return INPUT;
	}

	public String add(){
		try {
			projectStartUserService.add(p);
			MKUtil.writeJSON(true, "添加成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectStart start = projectStartService.get(p.getRoot());
		if(!projectAuthService.isLook(start.getRoot().getId(), userkk.getId(), "xmqds_xmcy")){
			return "noAuth";
		}
		Object[] object = projectStartUserService.getByStart(p, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			pUsers = (List<ProjectStartUser>) object[0];
			if (pUsers != null && pUsers.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectStartUser_list.action?p.root.id=" + p.getRoot().getId());
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return SUCCESS;
	}
	
	public String updateInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectStartUser start = projectStartUserService.get(p);
		if(!projectAuthService.isLook(start.getRoot().getRoot().getId(), userkk.getId(), "xmqds_xmcy")){
			return "noAuth";
		}
		p = projectStartUserService.get(p);
		return INPUT;
	}
	
	public String update(){
		try {
			projectStartUserService.update(p);
			MKUtil.writeJSON(true, "修改成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String delete(){
		try {
			projectStartUserService.delete(p);
			MKUtil.writeJSON(true, "删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败:" + e.getMessage(), null);
		}
		return null;
	}
	

	public ProjectStartUser getP() {
		return p;
	}

	public void setP(ProjectStartUser p) {
		this.p = p;
	}

	public ProjectStartUserService getProjectStartUserService() {
		return projectStartUserService;
	}

	public void setProjectStartUserService(ProjectStartUserService projectStartUserService) {
		this.projectStartUserService = projectStartUserService;
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

	public List<ProjectStartUser> getpUsers() {
		return pUsers;
	}

	public void setpUsers(List<ProjectStartUser> pUsers) {
		this.pUsers = pUsers;
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
