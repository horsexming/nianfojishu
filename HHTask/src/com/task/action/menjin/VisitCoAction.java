package com.task.action.menjin;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.menjin.VisitCoServer;
import com.task.entity.menjin.VisitCo;
import com.task.entity.rfid.Rfid;

@SuppressWarnings("serial")
public class VisitCoAction extends ActionSupport {

	private Integer id;
	private VisitCo visitCo;
	private VisitCoServer visitCoServer;
	private List<VisitCo> visitCoList;
	private String errorMessage;
	private String successMessage;
	private String name;
	private String contactPerson;

	// 显示查询内容
	@SuppressWarnings("unchecked")
	public String showList() {
		Object[] listObject=visitCoServer.findVisitco();
		visitCoList=(List<VisitCo>) listObject[0];
		return "VisitCo_show";
	}

	// 添加方法
	public String add() {
		if (visitCo != null) {
			visitCoServer.addVisitCo(visitCo);
			errorMessage = "添加成功！";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 修改方法
	public String update() {
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			visitCoServer.deleteVisitCo(id);
			errorMessage = "删除成功！";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	public VisitCo getVisitCo() {
		return visitCo;
	}

	public void setVisitCo(VisitCo visitCo) {
		this.visitCo = visitCo;
	}

	public VisitCoServer getVisitCoServer() {
		return visitCoServer;
	}

	public void setVisitCoServer(VisitCoServer visitCoServer) {
		this.visitCoServer = visitCoServer;
	}

	public List<VisitCo> getVisitCoList() {
		return visitCoList;
	}

	public void setVisitCoList(List<VisitCo> visitCoList) {
		this.visitCoList = visitCoList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

}
