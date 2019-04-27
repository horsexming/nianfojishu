package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Server.WareHouseAuthService;
import com.task.entity.CompetenceType;
import com.task.entity.Users;
import com.task.entity.WareHouseAuth;
import com.task.entity.sop.BuHeGePin;
import com.task.util.MKUtil;
import com.task.util.Util;

public class WareHouseAuthAction extends ActionSupport {
	private WareHouseAuth auth;
	private List<WareHouseAuth> auths;
	private WareHouseAuthService wareHouseAuthService;
	
	
	private String errorMessage;
	private String successMessage;// 成功信息
	private String quanxian;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;

	/**
	 * 添加权限页面
	 * @return
	 */
	public String addInput(){
		return "addInput";
	}

	/**
	 * 添加权限
	 * @return
	 */
	public String add(){
		try {
			auth.setAuth(auth.getAuth().replaceAll("\\s", ""));
			wareHouseAuthService.add(auth,status);
			MKUtil.writeJSON(true, "权限添加成功!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "权限添加失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String list(){
		if (auth != null) {
			ActionContext.getContext().getSession().put("auth", auth);
		} else {
			auth = (WareHouseAuth) ActionContext.getContext().getSession()
					.get("auth");
		}
		Object[] object = wareHouseAuthService.getList(auth,Integer.parseInt(cpage), pageSize,status);
		if (object != null && object.length > 0) {
			auths = (List<WareHouseAuth>) object[0];
			if (auths != null && auths.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("WareHouseAuth_list.action?status="+status);
				errorMessage = null;
			} else {
				errorMessage = "抱歉!没有绑定的人员!";
			}
		} else {
			errorMessage = "抱歉!没有绑定的人员!";
		}
		return "list";//auth.jsp
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	public String editInput(){
		if("price".equals(status)){
			return "price_authupdate";
		}
		return "edit";
	}
	
	public String edit(){
		try {
			auth.setAuth(auth.getAuth().replaceAll("\\s", ""));
			wareHouseAuthService.update(auth);
			MKUtil.writeJSON(true, "权限修改成功!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "权限修改失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String delete(){
		try {
			wareHouseAuthService.delete(auth);
			MKUtil.writeJSON(true, "删除成功!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String get(){
		auth = wareHouseAuthService.get(auth);
		MKUtil.writeJSON(auth);
		return null;
	}
	
	public void getshow(){
		Users user = Util.getLoginUser();
		List<String> strlist = wareHouseAuthService.getShow1(user.getCode());
		try {
			if(strlist!=null && strlist.size()>0){
				MKUtil.writeJSON(strlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String test(){
		MKUtil.writeJSON(wareHouseAuthService.getOut("420"));
		return null;
	}
	public void findAllCompetenceType(){
		try {
			List<CompetenceType> ctList =	wareHouseAuthService.AllCompetenceType();
			if(ctList!=null && ctList.size()>0){
				MKUtil.writeJSON(ctList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void ischecked(){
		try {
			List<String> strlist =	wareHouseAuthService.ischecked(quanxian);
			if(strlist!=null && strlist.size()>0){
				MKUtil.writeJSON(strlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WareHouseAuth getAuth() {
		return auth;
	}

	public void setAuth(WareHouseAuth auth) {
		this.auth = auth;
	}

	public WareHouseAuthService getWareHouseAuthService() {
		return wareHouseAuthService;
	}

	public void setWareHouseAuthService(WareHouseAuthService wareHouseAuthService) {
		this.wareHouseAuthService = wareHouseAuthService;
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

	public List<WareHouseAuth> getAuths() {
		return auths;
	}

	public void setAuths(List<WareHouseAuth> auths) {
		this.auths = auths;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuanxian() {
		return quanxian;
	}

	public void setQuanxian(String quanxian) {
		this.quanxian = quanxian;
	}

}
