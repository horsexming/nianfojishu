package com.task.action.checktype;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.checktype.CheckTypeServer;
import com.task.entity.checktype.CheckType;
import com.task.util.MKUtil;

public class CheckTypeAction extends ActionSupport{
	private CheckTypeServer checkTypeServer;
	private CheckType checkType;
	private String successMessage;
	private String errorMessage;
	private String cpage = "1"; 
	private int pageSize = 15;
	private String total;
	private String url;
	private String jType;
	private List<CheckType> checkTypeList;
	public String toAdd(){
		if(checkType.getId()!=null){
			checkType = checkTypeServer.findById(checkType.getId());
		}
		return "checkType_add";
	}
	public String add(){
		if(checkTypeServer.save(checkType)){
			successMessage="添加成功";
			return "checkType_toList";
		}else{
			errorMessage="添加失败";
			return "error";
		}
	}
	public String update(){
		if(checkTypeServer.update(checkType)){
			successMessage="修改成功";
			return "checkType_toList";
		}else{
			errorMessage="修改失败";
			return "error";
		}
	}
	public String findAll(){
		Map<Integer, Object> map = checkTypeServer
		.findAll(checkType, Integer
				.parseInt(cpage), pageSize);
		checkTypeList = (List<CheckType>) map.get(1);
		if (checkTypeList != null & checkTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("checkTypeAction_findAll.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		
			return "checkType_List";
		
	}

	public void findJson() {
		pageSize = 1000;
		String s = null;
		try {
			s = URLDecoder.decode(jType, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<Integer, Object> map = checkTypeServer.findAll1(checkType, Integer
				.parseInt(cpage), pageSize, s);
		checkTypeList = (List<CheckType>) map.get(1);
		MKUtil.writeJSON(true, "获取成功", checkTypeList);
	}
	public String delete(){
		checkType = checkTypeServer.findById(checkType.getId());
		checkTypeServer.delete(checkType);
		return "checkType_toList";
	}
	public CheckTypeServer getCheckTypeServer() {
		return checkTypeServer;
	}
	public void setCheckTypeServer(CheckTypeServer checkTypeServer) {
		this.checkTypeServer = checkTypeServer;
	}
	public CheckType getCheckType() {
		return checkType;
	}
	public void setCheckType(CheckType checkType) {
		this.checkType = checkType;
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
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	public List<CheckType> getCheckTypeList() {
		return checkTypeList;
	}
	public void setCheckTypeList(List<CheckType> checkTypeList) {
		this.checkTypeList = checkTypeList;
	}
	public String getjType() {
		return jType;
	}
	public void setjType(String jType) {
		this.jType = jType;
	}
	
	
	
	

}
