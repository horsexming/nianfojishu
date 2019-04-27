package com.task.action.pro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import com.task.Server.UserServer;

import com.task.Server.pro.ProUserServer;

import com.task.entity.Users;

import com.task.entity.pro.ProUser;


public class ProUserAction extends ActionSupport {
	private ProUser proUser;
	private List<ProUser> proUserList;
	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private ProUserServer proUserServer;
	private UserServer userServer;
	
	//获得项目人员添加
	public String getProUserAddPage(){
		return "getProUserAddPage_success";
	}
	//添加项目记录
	public String addProUser(){
		try {
			String result=proUserServer.addProUser(this.proUser);
			if("true".equals(result)){
				//MKUtil.writeJSON(true, "操作成功", null);
				return "addProUser_success";
				//return null;
			}else{
				//MKUtil.writeJSON(false, "操作失败", null);
				return "addProUser_failure";
			}
		} catch (Exception e) {
			//MKUtil.writeJSON(false, "操作失败", null);
			return "addProUser_failure";
		}
	};
	
	//删除项目记录
	public String deleteProUser(){
		try {
			this.proUser=proUserServer.getProUserById(this.proUser.getId());
			String result=proUserServer.deleteProUser(this.proUser);
			if("true".equals(result)){
				return "deleteProUser_success";
			}else{
				return "deleteProUser_failure";
			}
		} catch (Exception e) {
			return "deleteProUser_failure";
		}
	};
	
	//获得更新页面
	public String getProUserUpdatePage(){
		this.proUser=proUserServer.getProUserById(this.proUser.getId());
		Users user=userServer.findUserById(this.proUser.getUserId());
		proUser.setUser(user);
		return "getProUserUpdatePage_success";
	}
	
	//更新项目记录
	public String updateProUser(){
		try {
			ProUser proUser=proUserServer.getProUserById(this.proUser.getId());
			Integer userId=this.proUser.getUserId();
			String userGroup=this.proUser.getUserGroup();
			if(userId!=null){
				proUser.setUserId(userId);
			}
			if(userGroup!=null && !"".equals(userGroup)){
				proUser.setUserGroup(userGroup);
			}
			String result=proUserServer.updateProUser(proUser);
			if("true".equals(result)){
				return "updateProUser_success";
			}else{
				return "updateProUser_failure";
			}
		} catch (Exception e) {
			return "updateProUser_failure";
		}
	};
	
	//获得项目记录
	public String getProUserById(){
		this.proUser=proUserServer.getProUserById(this.proUser.getId());
		Users user=userServer.findUserById(this.proUser.getUserId());
		proUser.setUser(user);
		return null;
	};
	
	//获得项目记录集合
	public String findAllProUserByproId(){
		Map map=new HashMap();
		Integer proId=this.proUser.getProId();
		map.put("proId", proId);
		Object[] object = proUserServer.findAllProUserByproId(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			proUserList = (List<ProUser>) object[0];
			if (proUserList != null && proUserList.size() > 0) {
				int size=proUserList.size();
				for(int i=0;i<size;i++){
					ProUser proUser=proUserList.get(i);
					Users user=userServer.findUserById(proUser.getUserId());
					proUser.setUser(user);
				}
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("proUserAction!findAllProUserByproId.action?proUser.proId="+proId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllProUserByproId_success";
	}

	public ProUser getProUser() {
		return proUser;
	}

	public void setProUser(ProUser proUser) {
		this.proUser = proUser;
	}

	public List<ProUser> getProUserList() {
		return proUserList;
	}

	public void setProUserList(List<ProUser> proUserList) {
		this.proUserList = proUserList;
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

	public ProUserServer getProUserServer() {
		return proUserServer;
	}

	public void setProUserServer(ProUserServer proUserServer) {
		this.proUserServer = proUserServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	
	
}
