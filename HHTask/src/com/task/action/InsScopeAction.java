package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.InsScopeService;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;

public class InsScopeAction extends ActionSupport {
	private InsScopeService insScopeService;
	private List<InsScope> l;
	private InsScope s;
	private InsTemplate t;
	private Integer id;
	private InsScope insScope;
	private String errorMessage;
	private String url;

	public String get() {
		l = insScopeService.get(t);
		return "get";
	}

	public String find() {
		insScope = insScopeService.findInsScope(id);
		l = insScopeService.get(t);
		return "get";
	}

	public String update() {
		boolean bool = insScopeService.updateInsScope(insScope);
		errorMessage = "修改成功";
		url = "InsScope_find.action?id=" + insScope.getId() + "&t.id="
				+ t.getId();
		return ERROR;
	}

	public InsScopeService getInsScopeService() {
		return insScopeService;
	}

	public void setInsScopeService(InsScopeService insScopeService) {
		this.insScopeService = insScopeService;
	}

	public List<InsScope> getL() {
		return l;
	}

	public void setL(List<InsScope> l) {
		this.l = l;
	}

	public InsScope getS() {
		return s;
	}

	public void setS(InsScope s) {
		this.s = s;
	}

	public InsTemplate getT() {
		return t;
	}

	public void setT(InsTemplate t) {
		this.t = t;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InsScope getInsScope() {
		return insScope;
	}

	public void setInsScope(InsScope insScope) {
		this.insScope = insScope;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
