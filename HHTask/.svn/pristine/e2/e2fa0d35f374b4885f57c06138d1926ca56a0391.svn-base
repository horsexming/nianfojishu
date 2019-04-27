package com.task.action.bbs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import com.task.Server.UserServer;

import com.task.Server.bbs.ScrnServer;
import com.task.entity.bbs.Scrn;
import com.task.util.MKUtil;



public class ScrnAction extends ActionSupport {
	private Scrn scrn;
	private List<Scrn> scrnList;
	
	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
		
	private ScrnServer scrnServer;
	private UserServer userServer;
	
	//添加屏幕记录
	public String addScrn(){
		try {
			//Users user=(Users)ActionContext.getContext().getSession().get(TotalDao.users);
			this.scrn.setCreateDate(new Date());
			Scrn result=this.scrnServer.addScrn(this.scrn);
			if(result!=null){
				//MKUtil.writeJSON(true, "操作成功", result);
				return "addScrn_success";	
			}else{
				//MKUtil.writeJSON(false, "操作失败", null);
				return "addScrn_failure";
			}
		} catch (Exception e) {
			//MKUtil.writeJSON(false, "操作失败", null);
			return "addScrn_failure";
		}
	};
	
	//删除评论记录
	public String deleteScrn(){
		try {
			String result=this.scrnServer.deleteScrn(this.scrn);
			if("true".equals(result)){
				return "deleteScrn_success";
			}else{
				return "deleteScrn_failure";
			}
		} catch (Exception e) {
			return "deleteScrn_failure";
		}
	};
	
	//获得更新评论
	public String getScrnUpdatePage(){
		this.scrn=this.scrnServer.getScrnById(this.scrn.getId());
		return "getScrnUpdatePage_success";
	}
	
	//更新评论记录
	public String updateScrn(){
		try {
			Scrn scrn=this.scrnServer.getScrnById(this.scrn.getId());
			String name=this.scrn.getName();
			Integer sort=this.scrn.getSort();
			if(name!=null && !"".equals(name)){
				scrn.setName(name);
			}
			if(sort!=null && 0!=sort){
				scrn.setSort(sort);
			}
			String result=this.scrnServer.updateScrn(scrn);
			if("true".equals(result)){
				return "updateScrn_success";
			}else{
				return "updateScrn_failure";
			}
		} catch (Exception e) {
			return "updateScrn_failure";
		}
	};
	
	//获得评论记录
	public String getScrnById(){
		this.scrn=this.scrnServer.getScrnById(this.scrn.getId());
		return null;
	};
	
	//获得评论记录集合
	public String findAllScrn(){
		Map map=new HashMap();
		Object[] object = this.scrnServer.findAllScrn(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.scrnList = (List<Scrn>) object[0];
			if (this.scrnList != null && this.scrnList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("scrnAction!findAllScrn.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllScrn_success";
	}
	
	public String findAllScrnForSelect(){
		List<Scrn> scrnList=this.scrnServer.findAllScrn();
		MKUtil.writeJSON(true, "操作成功", scrnList);
		return null;
	}
	

	public Scrn getScrn() {
		return scrn;
	}

	public void setScrn(Scrn scrn) {
		this.scrn = scrn;
	}

	public List<Scrn> getScrnList() {
		return scrnList;
	}

	public void setScrnList(List<Scrn> scrnList) {
		this.scrnList = scrnList;
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

	public ScrnServer getScrnServer() {
		return scrnServer;
	}

	public void setScrnServer(ScrnServer scrnServer) {
		this.scrnServer = scrnServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
}
