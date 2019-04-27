package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.*;
import com.task.entity.*;

/**
 * 项目登录记录Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class ProjectRecordAction extends ActionSupport {

	private ProjectRecordServer projectRecordServer;// Server层
	private ProjectRecord projectRecord;// 对象
	private ProjectLogin projectLogin;// 网站登录信息
	private List<ProjectRecord> projectRecordList;// 集合
	private List<ProjectLogin> projectLoginList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加项目登录记录
	public String addProRecord() {
		if (id != 0) {// 修改
			ProjectRecord oldProjectRecord = projectRecordServer
					.findProRecordById(id);
			if (oldProjectRecord != null) {
				boolean bool = projectRecordServer
						.updateProRecord(projectRecord);
				if (bool) {
					successMessage = "修改 " + projectRecord.getWebsiteName()
							+ "成功!";
					return "findAllProRecord";
				}
				errorMessage = "删除失败,请检查后重试!";
			} else {
				errorMessage = "不存在该登录网站信息,请检查后重试!";
			}
		} else {// 添加
			ProjectRecord oldProjectRecord = projectRecordServer
					.findPrByProjectName(projectRecord.getProjectName());
			if (oldProjectRecord == null) {
				boolean bool = projectRecordServer.addProRecord(projectRecord);
				if (bool) {
					return "addProRecordSuccess";
				}
				errorMessage = "添加失败!请检查后重试!";
			} else {
				errorMessage = "该项目名称" + oldProjectRecord.getProjectName()
						+ "已经存在!请勿重复添加!";
			}
		}
		return "error";
	}

	// 查询所有项目登记记录(分页、条件查询)
	@SuppressWarnings("unchecked")
	public String findAllProRecord() {
		if (projectRecord != null) {
			ActionContext.getContext().getSession().put("projectRecord",
					projectRecord);
		} else {
			projectRecord = (ProjectRecord) ActionContext.getContext()
					.getSession().get("projectRecord");
		}

		Object[] object = projectRecordServer.findAllProRecord(projectRecord,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			projectRecordList = (List<ProjectRecord>) object[0];
			if (projectRecordList != null && projectRecordList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectRecordAction.findAllProRecord.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "findAllProRecord";
		}
		return ERROR;
	}

	// 删除登录网站
	public String delProRecord() {
		ProjectRecord oldProjectRecord = projectRecordServer
				.findProRecordById(id);
		if (oldProjectRecord != null) {
			boolean bool = projectRecordServer.delProRecord(oldProjectRecord);
			if (bool) {
				return "addProRecordSuccess";
			}
			errorMessage = "删除失败,请检查后重试!";
		} else {
			errorMessage = "不存在该登录网站信息,请检查后重试!";
		}
		return ERROR;

	}

	// 查询登录网站通过ID
	public String findProRecordById() {
		projectRecord = projectRecordServer.findProRecordById(id);
		if (projectRecord != null) {
			return "findAllProRecord";
		} else {
			errorMessage = "不存在该登录网站信息,请检查后重试!";
		}
		return ERROR;

	}

	// 添加网站登录信息
	public String addProjectLogin() {
		projectRecord = projectRecordServer.findProRecordById(projectLogin
				.getProjectRecord().getId());// 查询登录网站信息
		if (projectRecord != null) {
			projectLogin.setProjectRecord(projectRecord);
			boolean bool = projectRecordServer.addProjectLogin(projectLogin);
			if (bool) {
				return "addProLoginSuccess";
			}
		}
		return ERROR;
	}

	// 修改网站登录信息
	public String updateProjectLogin() {
		ProjectLogin oldProjectLogin = projectRecordServer
				.findProjectLoginById(projectLogin.getId());
		oldProjectLogin.setLoginFieldValue(projectLogin.getLoginFieldValue());
		boolean bool = projectRecordServer.updateProjectLogin(oldProjectLogin);
		if (bool) {
			return "delProjectLoginSu";
		}
		return ERROR;
	}

	// 查询用户已绑定网站登录信息
	public String findProLoginByUser() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		projectLoginList = projectRecordServer.findProjectLoginByUser(user);
		return "findUserProLogin";
	}

	// 删除用户已绑定网站登录信息
	public String delProjectLogin() {
		projectLogin = projectRecordServer.findProjectLoginById(id);
		if (projectRecordServer.delProjectLogin(projectLogin)) {
			return "delProjectLoginSu";
		}
		errorMessage = "删除信息失败";
		return ERROR;
	}

	// 构造方法
	public ProjectRecordServer getProjectRecordServer() {
		return projectRecordServer;
	}

	public void setProjectRecordServer(ProjectRecordServer projectRecordServer) {
		this.projectRecordServer = projectRecordServer;
	}

	public ProjectRecord getProjectRecord() {
		return projectRecord;
	}

	public void setProjectRecord(ProjectRecord projectRecord) {
		this.projectRecord = projectRecord;
	}

	public List<ProjectRecord> getProjectRecordList() {
		return projectRecordList;
	}

	public void setProjectRecordList(List<ProjectRecord> projectRecordList) {
		this.projectRecordList = projectRecordList;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ProjectLogin getProjectLogin() {
		return projectLogin;
	}

	public void setProjectLogin(ProjectLogin projectLogin) {
		this.projectLogin = projectLogin;
	}

	public List<ProjectLogin> getProjectLoginList() {
		return projectLoginList;
	}

	public void setProjectLoginList(List<ProjectLogin> projectLoginList) {
		this.projectLoginList = projectLoginList;
	}

}
