package com.task.action.shizhi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.GroupShiZhiServer;
import com.task.entity.shizhi.GroupShiZhi;

/**
 * 试制分组Action层
 * 
 * @author 唐晓斌
 * 
 */
public class GroupShiZhiAction {
	private GroupShiZhiServer groupShiZhiServer;//技能系数服务层
	private GroupShiZhi groupShiZhi;//技能系数对象
	private List<GroupShiZhi> groupList;//技能系数列表
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
 public String showList(){
		if (groupShiZhi != null) {
			ActionContext.getContext().getSession().put("groupShiZhi",
					groupShiZhi);
		    } else {//用来保持分页时带着查询条件
		    	groupShiZhi = (GroupShiZhi) ActionContext.getContext().getSession().get("groupShiZhi");
		      }
		Map<Integer, Object> map = groupShiZhiServer.findGroupShiZhisByCondition(
				groupShiZhi, Integer.parseInt(cpage), pageSize);
		groupList = (List<GroupShiZhi>) map.get(1);// 显示页的技能系数列表
			if (groupList != null & groupList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("groupShiZhiAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "group_show";
 }
 
 public String add(){
		List<GroupShiZhi> groupShiZhis=groupShiZhiServer.findAll();
		if(groupShiZhis!=null){
			for(GroupShiZhi g:groupShiZhis){
				if(g.getName()!=null&&groupShiZhi.getName()!=null&&g.getName().equals(groupShiZhi.getName())){
					errorMessage="添加失败,该组名已经存在";
					return "group_add";
				}
			}
		}
	 boolean b=groupShiZhiServer.add(groupShiZhi);
	 if(b){
		 successMessage="添加成功！";
		return showList();
	 }else{ 
		 errorMessage="添加失败！";
	  return "group_add";
	 }
 }
 
 public String toupdate(){
	 groupShiZhi=groupShiZhiServer.getById(groupShiZhi.getId());
	if(groupShiZhi!=null){
		 return "group_update";
			
	}else{
		errorMessage="修改失败,不存在该技能系数！";
	}
	return showList();
 }
 public String update(){
	 List<GroupShiZhi> groupShiZhis=groupShiZhiServer.findAll();
		if(groupShiZhis!=null){
			for(GroupShiZhi g:groupShiZhis){
				if(g.getName()!=null&&groupShiZhi.getName()!=null&&g.getName().equals(groupShiZhi.getName())
						&&g.getId()!=null&&groupShiZhi.getId()!=null&&!g.getId().equals(groupShiZhi.getId())){
					errorMessage="修改失败,该组名已经存在";
					return "group_add";
				}
			}
		}
	 boolean b=groupShiZhiServer.update(groupShiZhi);
	 if(b){
		 successMessage="修改成功！";
		 groupShiZhi=null;
		 return showList();
	 }else{
		 errorMessage="修改失败！";
		 return "group_update";
	 }
 }
 public String delete(){
	 boolean b=groupShiZhiServer.deleteById(groupShiZhi.getId());
	 if(b){
		 successMessage="删除成功！";
	 }else{
		 errorMessage="删除失败！";
	 }
	 groupShiZhi=null;
	 return showList();
 }
 //get set方法


public String getSuccessMessage() {
	return successMessage;
}
public GroupShiZhiServer getGroupShiZhiServer() {
	return groupShiZhiServer;
}

public void setGroupShiZhiServer(GroupShiZhiServer groupShiZhiServer) {
	this.groupShiZhiServer = groupShiZhiServer;
}

public GroupShiZhi getGroupShiZhi() {
	return groupShiZhi;
}

public void setGroupShiZhi(GroupShiZhi groupShiZhi) {
	this.groupShiZhi = groupShiZhi;
}

public List<GroupShiZhi> getGroupList() {
	return groupList;
}

public void setGroupList(List<GroupShiZhi> groupList) {
	this.groupList = groupList;
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
 
 
}
