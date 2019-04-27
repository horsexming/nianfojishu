package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MsgGroupServiceI;
import com.task.entity.MsgGroup;

@SuppressWarnings("serial")
public class MsgGroupAction extends ActionSupport {
	private MsgGroupServiceI msgGroupService;
	private List<MsgGroup> msgGroups;

	private MsgGroup msgAdd;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 30;
	
	//消息
	private String errorMessage;
	private String successMessage;
	
	public MsgGroup searchMsg;
	
	//添加用户
	public String add(){
		try {
			msgGroupService.add(msgAdd);
			successMessage = "添加成功";
		} catch (Exception e) {
			errorMessage = "添加失败";
		}
		return "chainToAdd";
	}
	
	//添加前的输入
	public String addInput(){
		return INPUT;
	}
	
	//发送前的输入
	public String sendInput(){
		
		Object[] object = msgGroupService.getList(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			msgGroups = (List<MsgGroup>) object[0];
			if (msgGroups != null && msgGroups.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("MsgGroup_sendInput.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		
		return INPUT;
	}
	
	public String search(){
		return SUCCESS;
	}

	public void setMsgAdd(MsgGroup msgAdd) {
		this.msgAdd = msgAdd;
	}


	public MsgGroupServiceI getMsgGroupService() {
		return msgGroupService;
	}


	public void setMsgGroupService(MsgGroupServiceI msgGroupService) {
		this.msgGroupService = msgGroupService;
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

	public MsgGroup getMsgAdd() {
		return msgAdd;
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

	public List<MsgGroup> getMsgGroups() {
		return msgGroups;
	}

	public void setMsgGroups(List<MsgGroup> msgGroups) {
		this.msgGroups = msgGroups;
	}

	public MsgGroup getSearchMsg() {
		return searchMsg;
	}

	public void setSearchMsg(MsgGroup searchMsg) {
		this.searchMsg = searchMsg;
	}

	
}
