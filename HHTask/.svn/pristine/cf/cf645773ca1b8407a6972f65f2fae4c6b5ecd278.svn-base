package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.Server.UserServer;
import com.task.entity.ProjectAuth;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProjectAuthAction extends ActionSupport {

	private ProjectAuthService projectAuthService;
	private UserServer userServer;
	private List<ProjectAuth> auths;
	private ProjectAuth auth;
	
	public String addInput(){
		//下面是权限管理
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		if(!projectAuthService.isLook(auth.getRoot().getId(), userkk.getId(), "qxfp")){
			return "noAuth";
		}
		auth.setUser(userServer.findUserById(auth.getUser().getId()));
		return INPUT;
	}
	
	public String get(){
		Map session = ActionContext.getContext().getSession();
		Users userkk = (Users) session.get(TotalDao.users);
		ProjectAuth pa = projectAuthService.get(auth.getRoot().getId(), userkk.getId());
		pa.setUser(null);
		pa.setRoot(null);
		MKUtil.writeJSON(pa);
		return null;
	}
	
	public String delete(){
		try {
			projectAuthService.delete(auth);
			MKUtil.writeJSON(true, "添加管理员成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加管理员失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String add(){
		try {
			projectAuthService.add(auth);
			MKUtil.writeJSON(true, "权限管理成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "权限管理失败:" + e.getMessage(), null);
		}
		return null;
	}


	public ProjectAuthService getProjectAuthService() {
		return projectAuthService;
	}


	public void setProjectAuthService(ProjectAuthService projectAuthService) {
		this.projectAuthService = projectAuthService;
	}


	public List<ProjectAuth> getAuths() {
		return auths;
	}


	public void setAuths(List<ProjectAuth> auths) {
		this.auths = auths;
	}

	public ProjectAuth getAuth() {
		return auth;
	}

	public void setAuth(ProjectAuth auth) {
		this.auth = auth;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

}
