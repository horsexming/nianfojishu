package com.task.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AdminServer;
import com.task.entity.Admin;
import com.task.entity.Users;
import com.task.util.MD5;
import com.task.util.MKUtil;

/**
 * 管理员Action
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class AdminAction extends ActionSupport {

	private AdminServer adminServer;
	private Admin admin; 
	private int id;
	private String errorMessage;
	private String successMessage;
	private Admin superAdmin;//超级管理员对象
	private List<Admin> superAdminList;//超级管理员列表
	// 分页信息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String oldPassword;//修改密码时输入的原密码

	// 管理员登陆
	public String adminLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Admin sessionadmin=(Admin) request.getSession().getAttribute("admin");
		if(sessionadmin!=null){
			String message = "已有用户登录请先注销在登录！";
			request.getSession().setAttribute("message", message);
			return "loginError";
		}
		if (admin!=null&&admin.getAdminName().length() > 0
				&& admin.getAdminPassword().length() > 0) {
			Admin admin2 = adminServer.login(admin);
			if (admin2 != null) {
				request.getSession().setAttribute("admin", admin2);
				return "loginSuccess";
			}
			//当后台管理员登陆不上时判断是否是员工号用户登陆
			Users user = adminServer.login2(admin);
			if (user != null) {
				admin.setAdminName(user.getName());
				request.getSession().setAttribute("admin", admin);
				request.getSession().setAttribute("adminusers", user);
				return "loginSuccess";
			}
			String message = "用户名或密码错误!";
			request.getSession().setAttribute("message", message);

		} else {
			String message = "用户名或密码不能为空!";
			request.getSession().setAttribute("message", message);
		}

		return "loginError";
	}

	// 管理员登陆
	public String adminLoginForJson() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = "";
		if (admin.getAdminName().length() > 0
				&& admin.getAdminPassword().length() > 0) {
			String oldPassword = admin.getAdminPassword();// 输入的密码
			admin = adminServer.login(admin);
			if (admin != null) {
				int b = admin.getAdminPassword().compareTo(oldPassword);// 判断是否匹配
				if (b == 0) {
					request.getSession().setAttribute("admin", admin);
					// 首页列表
					MKUtil.writeJSON(true, "登陆成功", null);
					return null;
				}
			} else {
				message = "用户名或密码错误!";
			}
		} else {
			message = "用户名或密码不能为空!";
		}
		MKUtil.writeJSON(false, message, null);
		return null;
	}

	// 修改密码
	public String updatePassword() {
		Admin oldadmin = adminServer.findAdminById(admin.getId());// 根据id查询用户信息
		if (oldadmin != null) {
			MD5 md5 = new MD5();
			if(oldPassword==null){
				errorMessage="请输入原密码";
				return "updateSuccess";
			}else if(!md5.isEquals(oldPassword, oldadmin.getAdminPassword())){
				errorMessage="原密码不正确!";
				return "updateSuccess";
			}
			String mdsPassword = md5
					.getMD5(admin.getAdminPassword().getBytes());// 密码MD5转换
			oldadmin.setAdminPassword(mdsPassword);
			boolean bool = adminServer.update(oldadmin);
			if (bool) {
				successMessage = "密码修改成功!";
				return "updateSuccess";
			}
			successMessage = "密码修改失败,请检查后重试!";
			return "updateSuccess";
		}else{
			errorMessage = "不存在该用户,请检查后重试!";
			return ERROR;
		}
		
	}
	public String showsuperadmin(){
		if (superAdmin != null) {
			ActionContext.getContext().getSession().put("superAdmin",
					superAdmin);
		} else {
			superAdmin = (Admin) ActionContext.getContext().getSession()
					.get("superAdmin");
		}
		if(superAdmin!=null){
		superAdmin.setAdminPassword(null);
		superAdmin.setCount(null);
		superAdmin.setLastLogin(null);
		superAdmin.setNowLogin(null);
		superAdmin.setType("super");
		}
		Map<Integer, Object> map = adminServer.findAdminsByCondition(
				superAdmin, Integer.parseInt(cpage), pageSize,"type = 'super'");
		superAdminList = new ArrayList<Admin>();
		if (null != map) {
			superAdminList = (List<Admin>) map.get(1);// 显示页的公司信息列表
			if (superAdminList != null && superAdminList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("AdminAction_showsuperadmin.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "showsuperadmin";
	}
	public String addSuper(){
		if(superAdmin!=null){
			List<String> adminNames=adminServer.getAllName();
			if(adminNames!=null&&superAdmin.getAdminName()!=null&&adminNames.contains(superAdmin.getAdminName())){
				errorMessage="添加失败,改名字已经存在";
				return showsuperadmin();
			}
			MD5 md5=new MD5();
			superAdmin.setAdminPassword(md5.getMD5("admin".getBytes()));
			superAdmin.setCount(0);
			superAdmin.setType("super");
			boolean b=adminServer.add(superAdmin);
			if(b){
				successMessage="添加成功";
			}else{
				errorMessage="添加失败";
			}
		}
		return showsuperadmin();
	}
	public String delete(){
		if(superAdmin!=null&&superAdmin.getId()!=null){
			boolean b=adminServer.delete(superAdmin.getId());
			if(b){
				successMessage="删除成功";
			}else{
				errorMessage="删除失败";
			}
		}
		return showsuperadmin();
	}
	
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public AdminServer getAdminServer() {
		return adminServer;
	}

	public void setAdminServer(AdminServer adminServer) {
		this.adminServer = adminServer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Admin getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Admin superAdmin) {
		this.superAdmin = superAdmin;
	}

	public List<Admin> getSuperAdminList() {
		return superAdminList;
	}

	public void setSuperAdminList(List<Admin> superAdminList) {
		this.superAdminList = superAdminList;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
