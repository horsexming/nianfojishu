package com.task.action;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ManufacturingPropService;
import com.task.Server.UserServer;
import com.task.entity.Manufacturing;
import com.task.entity.ManufacturingProp;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ManufacturingPropAction extends ActionSupport {
	
	private List<ManufacturingProp> props;
	private ManufacturingProp prop;
	private String jsonText;
	private String jsonText1;
	private String usercode;
	private String password;
	private UserServer userService;
	private ManufacturingPropService manufacturingPropService;
	
	public String list(){
		props = manufacturingPropService.find(prop);
		return "list";
	}
	
	public String updata(){
		
		
		if(!userService.login(usercode, password)){
			MKUtil.writeJSON("登录失败");
			return null;
		}
		if(jsonText == null || jsonText.equals("") || jsonText.equals("[]")){
			MKUtil.writeJSON("success");
		}
		
		try {
			props = JSON.parseArray(jsonText, ManufacturingProp.class);
			List<Manufacturing> list = JSON.parseArray(jsonText1, Manufacturing.class);
			Users u = userService.findUserByCode(usercode);
			manufacturingPropService.add(props, list, u);
			MKUtil.writeJSON("success");
		} catch (Exception e) {
			MKUtil.writeJSON(null);
		}
		return null;
	}

	public List<ManufacturingProp> getProps() {
		return props;
	}

	public void setProps(List<ManufacturingProp> props) {
		this.props = props;
	}

	public ManufacturingProp getProp() {
		return prop;
	}

	public void setProp(ManufacturingProp prop) {
		this.prop = prop;
	}

	public ManufacturingPropService getManufacturingPropService() {
		return manufacturingPropService;
	}

	public void setManufacturingPropService(ManufacturingPropService manufacturingPropService) {
		this.manufacturingPropService = manufacturingPropService;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public String getJsonText1() {
		return jsonText1;
	}

	public void setJsonText1(String jsonText1) {
		this.jsonText1 = jsonText1;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
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
	

}
