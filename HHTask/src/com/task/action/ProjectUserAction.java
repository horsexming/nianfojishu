package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.ProjectUserService;
import com.task.entity.ProjectUser;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectUserAction extends ActionSupport {
	
	private ProjectUserService projectUserService;
	private ProjectUser user;
	private List<ProjectUser> users;
	private ProjectAuthService projectAuthService;
	
	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(projectAuthService.isLook(user.getRoot().getId(), userkk.getId(), "cygl")){
			return INPUT;
		}

		return "noAuth";
	}
	
	public String add(){
		try {
			projectUserService.add(user);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String selectFromProject(){
		try {
			List<Users> users = projectUserService.toSelector(user.getRoot().getId());
			MKUtil.writeJSON(true, "添加成功", users);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String updateInput(){
		user = projectUserService.get(user);
		return INPUT;
	}
	
	public String update(){
		try {
			projectUserService.update(user);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String delete(){
		try {
			projectUserService.delete(user);
			MKUtil.writeJSON(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String list(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(projectAuthService.isLook(user.getRoot().getId(), userkk.getId(), "cygl")){
			users = projectUserService.list(user.getRoot());
			return SUCCESS;
		}
		
		return "noAuth";
		
	}
	
	public ProjectUserService getProjectUserService() {
		return projectUserService;
	}
	public void setProjectUserService(ProjectUserService projectUserService) {
		this.projectUserService = projectUserService;
	}
	public ProjectUser getUser() {
		return user;
	}
	public void setUser(ProjectUser user) {
		this.user = user;
	}
	public List<ProjectUser> getUsers() {
		return users;
	}
	public void setUsers(List<ProjectUser> users) {
		this.users = users;
	}

	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}

	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}

	
	
}
