package com.task.action.pro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import com.task.Server.UserServer;

import com.task.Server.pro.ProToolingServer;
import com.task.entity.pro.ProTooling;



public class ProToolingAction extends ActionSupport {
	private ProTooling proTooling;
	private List<ProTooling> proToolingList;
	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private ProToolingServer proToolingServer;
	private UserServer userServer;
	
	//获得工装添加
	public String getProToolingAddPage(){
		return "getProToolingAddPage_success";
	}
	//添加工装记录
	public String addProTooling(){
		try {
			String result=proToolingServer.addProTooling(this.proTooling);
			if("true".equals(result)){
				//MKUtil.writeJSON(true, "操作成功", null);
				return "addProTooling_success";
				//return null;
			}else{
				//MKUtil.writeJSON(false, "操作失败", null);
				return "addProTooling_failure";
			}
		} catch (Exception e) {
			//MKUtil.writeJSON(false, "操作失败", null);
			return "addProTooling_failure";
		}
	};
	
	//删除工装记录
	public String deleteProTooling(){
		try {
			this.proTooling=proToolingServer.getProToolingById(this.proTooling.getId());
			String result=proToolingServer.deleteProTooling(this.proTooling);
			if("true".equals(result)){
				return "deleteProTooling_success";
			}else{
				return "deleteProTooling_failure";
			}
		} catch (Exception e) {
			return "deleteProTooling_failure";
		}
	};
	
	//获得更新页面
	public String getProToolingUpdatePage(){
		this.proTooling=proToolingServer.getProToolingById(this.proTooling.getId());
		return "getProToolingUpdatePage_success";
	}
	
	//更新工装记录
	public String updateProTooling(){
		try {
			ProTooling proTooling=proToolingServer.getProToolingById(this.proTooling.getId());
			String name=this.proTooling.getName();
			String numb=this.proTooling.getNumb();
			Double amount=this.proTooling.getAmount();
			String specification=this.proTooling.getSpecification();
			String explain=this.proTooling.getExplain();
			String status=this.proTooling.getStatus();
			if(name!=null && !"".equals(name)){
				proTooling.setName(name);
			}
			if(numb!=null && !"".equals(numb)){
				proTooling.setNumb(numb);
			}
			if(amount!=null){
				proTooling.setAmount(amount);
			}
			if(specification!=null && !"".equals(specification)){
				proTooling.setSpecification(specification);
			}
			if(explain!=null && !"".equals(explain)){
				proTooling.setExplain(explain);
			}
			if(status!=null && !"".equals(status)){
				proTooling.setStatus(status);
			}
			String result=proToolingServer.updateProTooling(proTooling);
			if("true".equals(result)){
				return "updateProTooling_success";
			}else{
				return "updateProTooling_failure";
			}
		} catch (Exception e) {
			return "updateProTooling_failure";
		}
	};
	
	//获得工装记录
	public String getProToolingById(){
		this.proTooling=proToolingServer.getProToolingById(this.proTooling.getId());
		return null;
	};
	
	//获得工装记录集合
	public String findAllProToolingByproId(){
		Map map=new HashMap();
		Integer proId=this.proTooling.getProId();
		map.put("proId", proId);
		Object[] object = proToolingServer.findAllProToolingByproId(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			proToolingList = (List<ProTooling>) object[0];
			if (proToolingList != null && proToolingList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("proToolingAction!findAllProToolingByproId.action?proTooling.proId="+proId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllProToolingByproId_success";
	}
	public ProTooling getProTooling() {
		return proTooling;
	}
	public void setProTooling(ProTooling proTooling) {
		this.proTooling = proTooling;
	}
	public List<ProTooling> getProToolingList() {
		return proToolingList;
	}
	public void setProToolingList(List<ProTooling> proToolingList) {
		this.proToolingList = proToolingList;
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
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public ProToolingServer getProToolingServer() {
		return proToolingServer;
	}
	public void setProToolingServer(ProToolingServer proToolingServer) {
		this.proToolingServer = proToolingServer;
	}
}
