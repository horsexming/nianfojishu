package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ManufacturingService;
import com.task.Server.UserServer;
import com.task.entity.Manufacturing;
import com.task.util.MKUtil;

public class ManufacturingAction extends ActionSupport {
	private ManufacturingService manufacturingService;
	private List<Manufacturing> manufacturings;
	private Manufacturing m;
	private Integer id;
	private String jsonText;
	private String usercode;
	
	private UserServer userService;

	private String password;

	private String errorMessage;
	private String successMessage;// 成功信息
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String addInput(){
		return "addInput";
	}
	
	
	@SuppressWarnings("unchecked")
	public String list(){
		Object[] object = manufacturingService.find(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			manufacturings = (List<Manufacturing>) object[0];
			if (manufacturings != null && manufacturings.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("Manufacturing_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";
	}

	public String add(){
		manufacturingService.add(m);
		return "relist";
	}
	
	public String getData(){
		if(!userService.login(usercode, password)){
			MKUtil.writeJSON("登录失败");
			return null;
		}
		List<Manufacturing> lists = manufacturingService.getData(id);
		if(lists != null && lists.size()>0){
			for (Manufacturing manufacturinga : lists) {
				manufacturinga.setChildren(null);
			}
		}
		MKUtil.writeJSON(lists);
		
		return null;
	}

	public ManufacturingService getManufacturingService() {
		return manufacturingService;
	}


	public void setManufacturingService(ManufacturingService manufacturingService) {
		this.manufacturingService = manufacturingService;
	}


	public List<Manufacturing> getManufacturings() {
		return manufacturings;
	}


	public void setManufacturings(List<Manufacturing> manufacturings) {
		this.manufacturings = manufacturings;
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

	public Manufacturing getM() {
		return m;
	}

	public void setM(Manufacturing m) {
		this.m = m;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getJsonText() {
		return jsonText;
	}


	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}
	

	public UserServer getUserService() {
		return userService;
	}


	public void setUserService(UserServer userService) {
		this.userService = userService;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsercode() {
		return usercode;
	}


	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}
