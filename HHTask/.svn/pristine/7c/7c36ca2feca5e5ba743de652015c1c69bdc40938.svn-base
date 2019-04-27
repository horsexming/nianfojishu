package com.task.action.android;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.android.CustomerServer;
import com.task.entity.android.pscs.Customer;
import com.task.util.MKUtil;

import com.task.entity.android.pscs.CustomerInformation;
import com.task.entity.quality.Quality;
@SuppressWarnings("unchecked")
public class CustomerAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerServer customerServer;
	private Customer customer;
	private CustomerInformation customerInformation;
	private String errorMessage;
	private String successMessage;
	private List<Map> maps;
	private Object[] objects;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private String number;//手机号 
	private Integer customer_id;
	private Integer id;//区分每次提交转速角度的id

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	// 查询所有客户信息
	public String findCustomerAll() {
		if (customer != null) {
			ActionContext.getContext().getSession().put("customer", customer);
		} else {
			customer = (Customer) ActionContext.getContext().getSession().get(
					"customer");
		}
		Object[] object = this.customerServer.findCustomerAll(customer, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("CustomerAction_findCustomerAll.action?customer.customer_phone=");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findCustomerAll";
	}
	//查询到的角度和转速
	public void selectAngularSpeed() {
		if(id != null && id>0){
			List list = customerServer.selectAngularSpeed(id);
			if(list != null && list.size()>0) MKUtil.writeJSON(true, "", list);
			else MKUtil.writeJSON(false, "没有提交的数据!", list);
		}MKUtil.writeJSON(false, "记录不存在！","");
	}
	//查询用户提交的信息
	public void selectCustomerInformation() {
		if(customer_id != null && customer_id>0){
			List list = customerServer.selectCustomerInformation(customer_id);
			if(list != null && list.size()>0) MKUtil.writeJSON(true, "", list);
			else MKUtil.writeJSON(false, "没有数据!", list);     
		}MKUtil.writeJSON(false, "用户不存在！","");
	}

	//添加客户信息 
	public String addCustomer(){
		this.customerServer.saveCustomer(this.customer);
		this.successMessage="添加成功!";
		return "addCustomer";
	}
	
	//根据编号查询客户信息
	public String findCustomerById(){
		this.customer = this.customerServer.findCustomerById(this.customer_id);
		return "findCustomerById";
	}
	
	//修改客户信息
	public String updateCustomer(){
		this.customerServer.updateCustomer(this.customer);
		this.successMessage = "修改成功!";
		return "updateCustomer";
	}
	
	//修改客户信息 
	public void updateCustomer1(){
		errorMessage = this.customerServer.updateCustomer1(this.customer);
		if ("true".equals(errorMessage))
			MKUtil.writeJSON(true,"更新成功!",null);
		else
			MKUtil.writeJSON(false,errorMessage,null);
	}
	//删除客户信息
	public String delCustomerById(){
		this.customerServer.delCustomerById(this.customer_id);
		return "delCustomerById";
	}
	
	//根据手机号查询接口 登陆接口，
	public void findCustomerBynum(){
		objects = this.customerServer.findCustomerById(customer);
		if(objects!=null)
			MKUtil.writeJSON((Boolean)objects[2],objects[0].toString(),(Customer)objects[1]);
		else//登陆失败
			MKUtil.writeJSON(false,"005",null);
	}

	//安卓端添加接口
	public void addCustomer1(){
		 try {
			 
			if (customer==null) MKUtil.writeJSON(false,"002",null);
			String message= this.customerServer.saveCustomer1(this.customer);
			if("001".equals(message))
				MKUtil.writeJSON(true,message,null);
			else
				MKUtil.writeJSON(false,message,null);
		} catch (Exception e) {
			//对象为空，注册失败!
			MKUtil.writeJSON(false,"002",null);
		}
	}
	
	//注册生成验证码
	public String Security_addyan(){
		errorMessage = customerServer.addSecurity(customer);
		if ("true".equals(errorMessage)) {
			return MKUtil.writeJSON(true, customer.getRandomNum(), null);
		}else {
			return MKUtil.writeJSON(false, errorMessage, null);
		} 
	}
	
	//找回密码生成验证码
	public void LookForPassWord(){
		errorMessage = customerServer.backSecurity(customer);
		if ("true".equals(errorMessage))
			MKUtil.writeJSON(true, customer.getRandomNum(), null);
		else 
			MKUtil.writeJSON(false, errorMessage, null);
	}
	
	//重设密码
	public void ResetPassWord(){
		errorMessage = this.customerServer.updateCustomer2(this.customer);
		if ("true".equals(errorMessage))
			MKUtil.writeJSON(true,"密码修改成功!",null);
		else
			MKUtil.writeJSON(false,errorMessage,null);
	}
	
	public void addCInfor(){
		errorMessage = customerServer.addCustomerInformation(customerInformation,customer_id);
		if ("true".equals(errorMessage))
			MKUtil.writeJSON(true,"保存成功!",null);
		else
			MKUtil.writeJSON(false,errorMessage,null);
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customerId) {
		customer_id = customerId;
	}

	public CustomerServer getCustomerServer() {
		return customerServer;
	}

	public void setCustomerServer(CustomerServer customerServer) {
		this.customerServer = customerServer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
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

	public CustomerInformation getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerInformation customerInformation) {
		this.customerInformation = customerInformation;
	}

	

}
