package com.task.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.OrderManagementServer;
import com.task.entity.ClientManagement;
import com.task.entity.OrderManagement;
import com.task.entity.Users;

public class OrderManagementAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderManagementServer orderManagementServer;
	public OrderManagementServer getOrderManagementServer() {
		return orderManagementServer;
	}

	public void setOrderManagementServer(OrderManagementServer orderManagementServer) {
		this.orderManagementServer = orderManagementServer;
	}
	// 分页
 	private String cpage = "1";
 	private String total;
 	private String url;
 	private int pageSize = 15;
	private OrderManagement orderManagement;
	private String pageStatus;
	private String str;
	private String number;//订单编号
	//添加订单 类型（实制）
	@Override
	public String execute() throws Exception {
		orderManagement.setOrdertype("实制");
		orderManagement.setOrderstatus("开发中");
		orderManagementServer.saveOrderManagement(orderManagement);
		str="123";
		number=orderManagement.getOrdernumber();
		return super.execute();
	}
	private String dingdannumber;//订单编号
	private String username;//创建人用户名
	private List<Users> gongyilist=new ArrayList<Users>();
	//添加订单前 生成订单编号和创建人和创建时间  订单类型(实制)
	public String saveqian(){
		HttpServletRequest request=ServletActionContext.getRequest();
		dingdannumber= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Users user =(Users)request.getSession().getAttribute(TotalDao.users);
		username=user.getName();
		gongyilist=orderManagementServer.findgongyiName();//第一阶段负责人查询部门是工艺的所有人的姓名
		return "saveqian";
	}
	//根据联系人查询出客户名称和联系电话
	public String findlianxiren() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		String lianxiren=java.net.URLDecoder.decode(request.getParameter("lianxiren"),"utf-8");
		String lianxiren2=java.net.URLDecoder.decode(lianxiren,"utf-8");
		List orderlist=orderManagementServer.findlianxiren(lianxiren2);
		 if(orderlist!=null&&orderlist.size()>0){
			 ClientManagement clientManagement=(ClientManagement)orderlist.get(0); 
			 if (clientManagement != null) {
				 String message =clientManagement.getClientmobilenumber()+ "|" + clientManagement.getClientcompanyname() ;
				 response.getWriter().write(message);
			 } else {
				 response.getWriter().write("");
			 }
			 response.getWriter().close();
		 }
		return null;
	}
	private List<OrderManagement> list=new ArrayList<OrderManagement>();
	private String name;
	//查询订单
	public String findAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		if(pageStatus.equals("all")){
			list =orderManagementServer.findAll(Integer.parseInt(cpage), pageSize);
			if(list.size()<=0){
				errorMessage="没有找到相关信息";
				return "error";
			}
			this.setUrl("OrderManagementAction!findAll.action?pageStatus="+pageStatus);
			this.cpage = request.getParameter("cpage");
			if ("".equals(cpage) || null == cpage) {
				cpage = 1 + "";
			}
			int count = orderManagementServer.getcount();
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			name=user.getName();
			return "findAll";
		}
		else if(pageStatus.equals("ptyh")){
			list=orderManagementServer.findName(user.getName(), Integer.parseInt(cpage), pageSize);
			if(list.size()<=0){
				errorMessage="没有找到相关信息";
				return "error";
			}
			this.setUrl("OrderManagementAction!findAll.action?pageStatus="+pageStatus);
			this.cpage = request.getParameter("cpage");
			if ("".equals(cpage) || null == cpage) {
				cpage = 1 + "";
			}
			int count = orderManagementServer.getcountName(user.getName());
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			name=user.getName();
			return "findAll";
		}
		return null;
	}
	private int id;
	//修改订单 前
	public String updatefind(){
		orderManagement=orderManagementServer.findByID(id);
		return "updatefind";
	}
	//修改订单
	public String update(){
		orderManagementServer.updateOrderManagement(orderManagement);
		return "update";
	}
	
	//删除订单
	public String delete(){
		orderManagement=orderManagementServer.findByID(id);
		orderManagementServer.deleteOrderManagement(orderManagement);
		return "delete";
	}
	private String errorMessage;
	//条件查询
	public String conditionsfind(){
		HttpServletRequest request=ServletActionContext.getRequest();
		if(orderManagement!=null){
			ActionContext.getContext().getSession().put("orderManagement", orderManagement);
		}
		else{
			orderManagement=(OrderManagement)ActionContext.getContext().getSession().get("orderManagement");
		}
		list =orderManagementServer.findconditions(orderManagement,Integer.parseInt(cpage), pageSize);// 条件查询所有用户
		if(list.size()<=0){
			errorMessage="没有找到你的相关信息";
			return "error";
		}
		this.setUrl("OrderManagementAction!conditionsfind.action?pageStatus="+pageStatus);
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = orderManagementServer.orderManagementcount(orderManagement);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findAll";
	}
	
	//查看订单详细
	public String findDetailed(){
		orderManagement=orderManagementServer.findByID(id);
		return "findDetailed";
	}
	
	
	
	
	//构造方法
	public OrderManagement getOrderManagement() {
		return orderManagement;
	}
	
	public void setOrderManagement(OrderManagement orderManagement) {
		this.orderManagement = orderManagement;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<OrderManagement> getList() {
		return list;
	}

	public void setList(List<OrderManagement> list) {
		this.list = list;
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

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getDingdannumber() {
		return dingdannumber;
	}

	public void setDingdannumber(String dingdannumber) {
		this.dingdannumber = dingdannumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Users> getGongyilist() {
		return gongyilist;
	}

	public void setGongyilist(List<Users> gongyilist) {
		this.gongyilist = gongyilist;
	}
	
}
