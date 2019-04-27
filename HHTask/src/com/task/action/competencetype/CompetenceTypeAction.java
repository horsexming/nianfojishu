package com.task.action.competencetype;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.competencetype.CompetenceTypeServer;
import com.task.entity.CompetenceType;

public class CompetenceTypeAction extends ActionSupport{
	private String successMessage;
	private String errorMessage;
	private CompetenceType competenceType;
	private List<CompetenceType> competenceTypeList;
	private CompetenceTypeServer competenceTypeServer;
	//分页
	private String cpage = "1";
	private int pageSize = 15;
	private String total;
	private String url;
	public String code;
	public String toAdd(){
		if(competenceType.getId()!=null){
			competenceType = competenceTypeServer.findById(competenceType.getId());
			ActionContext.getContext().put("competenceType",competenceType);
		}
			return "competenceType_add";
	}
	public String add(){
		boolean b = competenceTypeServer.save(competenceType);
		if(b){
			successMessage="添加成功";
		}else{
			errorMessage="添加失败";
			return "error";
		}
			return "competenceType_toList";
	}
	public String update(){
		boolean b = competenceTypeServer.update(competenceType);
		if(b){
			successMessage="修改成功";
		}else{
			errorMessage="修改失败";
			return "error";
		}
			return "competenceType_toList";
	}
	public String delete(){
		competenceType = competenceTypeServer.findById(competenceType.getId());
		competenceTypeServer.delete(competenceType);
		return "competenceType_toList";
	}
	public String findAll(){
		Map<Integer, Object> map = competenceTypeServer
		.findAll(competenceType, Integer
				.parseInt(cpage), pageSize);
		competenceTypeList = (List<CompetenceType>) map.get(1);
		if (competenceTypeList != null & competenceTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CompetenceTypeAction_findAll.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
			return "competenceType_List";
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
	public CompetenceType getCompetenceType() {
		return competenceType;
	}
	public void setCompetenceType(CompetenceType competenceType) {
		this.competenceType = competenceType;
	}
	public List<CompetenceType> getCompetenceTypeList() {
		return competenceTypeList;
	}
	public void setCompetenceTypeList(List<CompetenceType> competenceTypeList) {
		this.competenceTypeList = competenceTypeList;
	}
	public CompetenceTypeServer getCompetenceTypeServer() {
		return competenceTypeServer;
	}
	public void setCompetenceTypeServer(CompetenceTypeServer competenceTypeServer) {
		this.competenceTypeServer = competenceTypeServer;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
