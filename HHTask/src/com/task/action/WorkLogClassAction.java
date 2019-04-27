package com.task.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.WorkLogClassServer;
import com.task.entity.WorkLogClass;

/**
 * WorkLogClassAction层
 * 
 * @author 刘培
 * 
 */
public class WorkLogClassAction extends ActionSupport {

	private WorkLogClassServer workLogClassServer;// Server层（改）
	private WorkLogClass workLogClass;// 对象（改）
	private List<WorkLogClass> workLogClassList;// 集合（改）
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 查询个人的类别(添加日志时下拉框使用)
	public String findPersonWorkLogClass() {
		workLogClassList = workLogClassServer.findPersonWorkLogClass();
		String message = "";
		for (int i = 0; i < workLogClassList.size(); i++) {
			WorkLogClass workLogClass = workLogClassList.get(i);
			message += workLogClass.getName() + "|";
		}
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "personWorkLogClass";
	}

	// 构造方法

	public WorkLogClassServer getWorkLogClassServer() {
		return workLogClassServer;
	}

	public void setWorkLogClassServer(WorkLogClassServer workLogClassServer) {
		this.workLogClassServer = workLogClassServer;
	}

	public WorkLogClass getWorkLogClass() {
		return workLogClass;
	}

	public void setWorkLogClass(WorkLogClass workLogClass) {
		this.workLogClass = workLogClass;
	}

	public List<WorkLogClass> getWorkLogClassList() {
		return workLogClassList;
	}

	public void setWorkLogClassList(List<WorkLogClass> workLogClassList) {
		this.workLogClassList = workLogClassList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
