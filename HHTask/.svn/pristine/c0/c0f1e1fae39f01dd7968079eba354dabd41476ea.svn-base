package com.task.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ClientManagementServer;
import com.task.entity.ClientManagement;

public class ClientManagementAction extends ActionSupport {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientManagementServer clientManagementServer;
	public ClientManagementServer getClientManagementServer() {
		return clientManagementServer;
	}
	public void setClientManagementServer(
			ClientManagementServer clientManagementServer) {
		this.clientManagementServer = clientManagementServer;
	}
     private ClientManagement clientManagement;//客户信息表
  // 分页
 	private String cpage = "1";
 	private String total;
 	private String url;
 	private int pageSize = 15;

		//添加客户信息
 	 private String str;
 	 private String kehuname;
 	 private String errorMessage;
	@Override
	public String execute() throws Exception {
		if(clientManagement.getClientsex()==null&&clientManagement.getClientsex().isEmpty()){
			errorMessage="性别不能为空";
			return "error";
		}
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		clientManagement.setClientdatatime(date);
		clientManagementServer.saveClientManagement(clientManagement);
		kehuname=clientManagement.getClientname();
		str="123";
		return super.execute();
	}
	//查询客户信息
	private List<ClientManagement> list=new ArrayList<ClientManagement>();
	public String findAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		list =clientManagementServer.findAllClientManagement(Integer
				.parseInt(cpage), pageSize);
		this.setUrl("ClientManagementAction!findAll.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = clientManagementServer.clientManagementcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findAll";
	}
	
	//删除客户信息
	private int id;
	public String delete(){
		clientManagement=clientManagementServer.findByID(id);
		clientManagementServer.deleteClientManagement(clientManagement);
		return "delete";
	}
	
	//修改客户信息 前
	public String updatefind(){
		clientManagement=clientManagementServer.findByID(id);
		return "updatefind";
	}
	
	//修改客户信息
	public String update(){
		clientManagementServer.updateClientManagement(clientManagement);
		return "update";
	}
	
	//查看客户详细信息
	public String findByclientManagement(){
		clientManagement=clientManagementServer.findByID(id);
		return"findByclientManagement";
	}
	
	//条件查询
	public String conditionsinquires(){
		HttpServletRequest request=ServletActionContext.getRequest();
		if(clientManagement!=null){
			ActionContext.getContext().getSession().put("clientManagement", clientManagement);
		}
		else{
			clientManagement=(ClientManagement)ActionContext.getContext().getSession().get("clientManagement");
		}
		list =clientManagementServer.findconditions(clientManagement,Integer.parseInt(cpage), pageSize);// 条件查询所有用户
		if(list.size()<=0){
			errorMessage="没有找到你的相关信息";
			return "error";
		}
		this.setUrl("ClientManagementAction!conditionsinquires.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = clientManagementServer.getcount(clientManagement);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findAll";
	}
	
	
	
	
	
	
	
	
	//构造方法
	public ClientManagement getClientManagement() {
		return clientManagement;
	}
	public void setClientManagement(ClientManagement clientManagement) {
		this.clientManagement = clientManagement;
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
	
	public List<ClientManagement> getList() {
		return list;
	}
	public void setList(List<ClientManagement> list) {
		this.list = list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getKehuname() {
		return kehuname;
	}
	public void setKehuname(String kehuname) {
		this.kehuname = kehuname;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
