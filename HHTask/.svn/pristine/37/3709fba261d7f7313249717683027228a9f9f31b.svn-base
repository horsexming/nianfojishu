package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TemplateTypemService;
import com.task.Server.UserServer;
import com.task.entity.Users;
import com.task.entity.android.TemplateRecordm;
import com.task.entity.android.TemplateTypem;
import com.task.util.MKUtil;

public class TemplateTypemAction extends ActionSupport {
	
	private List<TemplateTypem> types;
	private TemplateRecordm r;
	private List<TemplateRecordm> rs;
	
	
	private String jsonText;
	private String usercode;
	private String password;
	private String errorMessage;
	private String successMessage;// 成功信息
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private TemplateTypemService templateTypemService;
	private UserServer userService;
	
	public String updata(){
		System.out.println(jsonText);
		List<TemplateTypem> l = new ArrayList<TemplateTypem>();
		if(!userService.login(usercode, password)){
			MKUtil.writeJSON("登录失败");
			return null;
		}
		Users u = userService.findUserByCode(usercode);
		if(jsonText == null || jsonText.equals("") || jsonText.equals("[]")){
			MKUtil.writeJSON(l);
		}
		try {
			List<TemplateTypem> list = JSON.parseArray(jsonText, TemplateTypem.class);
			for (TemplateTypem templateTypem : list) {
				if(templateTypem.getServerId() == 0){
					TemplateTypem m = new TemplateTypem();
					m.setId(templateTypem.getId());
					templateTypem.setId(null);
					
					for (TemplateRecordm templateTypem2 : templateTypem.getChildren()) {
						templateTypem2.setUsername(u.getName());
						templateTypem2.setUsercode(usercode);
						
					}
					//保存
					templateTypemService.add(templateTypem);
					m.setServerId(templateTypem.getId());
					l.add(m);
				} else {
					//保存子
					TemplateTypem m = new TemplateTypem();
					m.setId(templateTypem.getServerId());
					for (TemplateRecordm t : templateTypem.getChildren()) {
						t.setRoot(m);
						t.setUsername(u.getName());
						t.setUsercode(usercode);
						templateTypemService.add(t);
					}
				}
			}
			MKUtil.writeJSON(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String list(){
		Object[] object = templateTypemService.find(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			types = (List<TemplateTypem>) object[0];
			if (types != null && types.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("TemplateTypem_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";
	}
	
	public String listSingle(){
		rs = templateTypemService.getChildren(r);
		return "listSingle";
	}
	
	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TemplateTypemService getTemplateTypemService() {
		return templateTypemService;
	}

	public void setTemplateTypemService(TemplateTypemService templateTypemService) {
		this.templateTypemService = templateTypemService;
	}

	public UserServer getUserService() {
		return userService;
	}

	public void setUserService(UserServer userService) {
		this.userService = userService;
	}

	public List<TemplateTypem> getTypes() {
		return types;
	}

	public void setTypes(List<TemplateTypem> types) {
		this.types = types;
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

	public TemplateRecordm getR() {
		return r;
	}

	public void setR(TemplateRecordm r) {
		this.r = r;
	}

	public List<TemplateRecordm> getRs() {
		return rs;
	}

	public void setRs(List<TemplateRecordm> rs) {
		this.rs = rs;
	}

}
