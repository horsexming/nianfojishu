package com.task.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.BecomingServer;
import com.task.entity.Becoming;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

public class BecomingAction extends ActionSupport{

	private Becoming becoming;
	private BecomingServer becomingServer;
	private List<Becoming> becomingsList;
	private Users user;
	private Users loguser;
	private Integer id;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private int size;
	
	private List<TemplateDetails> tdlist;
	private Template template;
	private List<String> strList;
 	
	public String add(){
		if(becoming!=null){
			errorMessage = becomingServer.add(becoming);
			if("true".equals(errorMessage)){
				errorMessage = "";
				return "showbecomingList";
			}
			errorMessage="没有找到员工信息，请刷新后重试";
		}
		return ERROR;
	}
	//查询所有分页
	public String showbecomingList(){
		if("del_true".equals(errorMessage)){
			errorMessage = "删除成功!";
		}else if("del_error".equals(errorMessage)){
			errorMessage = "删除失败!";
		}
		int count=becomingServer.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		becomingsList=becomingServer.showAllBecomingList(Integer.parseInt(cpage), pageSize);
		if(becomingsList!=null){
			this.setUrl("BecomingAction_showbecomingList.action");
			
		}else{
			errorMessage="没有人员调动信息";
		}
		return "becoming_list";
	
	}
	//条件查询
	public String findBecoming(){
		if(becoming!=null){
			ActionContext.getContext().getSession().put("becoming", becoming);
		}else{
			becoming=(Becoming) ActionContext.getContext().getSession().get("becoming");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		
		map=becomingServer.findBecomingByCondition(becoming, Integer.parseInt(cpage), pageSize,status);
		becomingsList=(List<Becoming>) map.get(1);
		if(becomingsList!=null && becomingsList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			if(becoming!=null && becoming.getUserId()!=null){
				id = becoming.getUserId();
			}
			this.setUrl("BecomingAction_findBecoming.action?status="+status+"&id="+id);
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "becoming_list";
	}
	//删除
	public String del(){
		if(becoming!=null){
			if(becomingServer.del(becoming)){
				errorMessage = "del_true";
			}else{
				errorMessage = "del_error";
			}
		}
		return  "showbecomingList";	
	}
	
	public String getBecomingByid(){
		becoming =becomingServer.findBecomingbyId(id);
		if(becoming!=null){
			user = becomingServer.getUsersById(becoming.getUserId());
			return "becoming_show";
		}else{
			errorMessage = "对不起，没有找到员工信息，请刷新后重试";
		}
		return ERROR;
	}
	public String updatebecoming(){
		if(	becomingServer.update(becoming)){
			return "showbecomingList";
		}
		return ERROR;
	}
	
	public String initBecomingkhdf(){
		try {
			tdlist = becomingServer.gettdlist();
			template = becomingServer.gettemplateBy();
			loguser = Util.getLoginUser();
			if(id!=null && id>0){
				user = becomingServer.getUsersById(id);
			}
			if(tdlist!=null && tdlist.size()>0){
				size =	tdlist.size();
				return "becoming_kh";
			}else{
				errorMessage = "不存在员转正考核模板，请前往添加。";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	public void gettdlist(){
		
		try {
			strList = becomingServer.gettdlistbyId(id);
				MKUtil.writeJSON(strList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
		
	}
	
	public Becoming getBecoming() {
		return becoming;
	}
	public void setBecoming(Becoming becoming) {
		this.becoming = becoming;
	}
	public BecomingServer getBecomingServer() {
		return becomingServer;
	}
	public void setBecomingServer(BecomingServer becomingServer) {
		this.becomingServer = becomingServer;
	}
	public List<Becoming> getBecomingsList() {
		return becomingsList;
	}
	public void setBecomingsList(List<Becoming> becomingsList) {
		this.becomingsList = becomingsList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<TemplateDetails> getTdlist() {
		return tdlist;
	}
	public void setTdlist(List<TemplateDetails> tdlist) {
		this.tdlist = tdlist;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public Users getLoguser() {
		return loguser;
	}
	public void setLoguser(Users loguser) {
		this.loguser = loguser;
	}
	

	
}
