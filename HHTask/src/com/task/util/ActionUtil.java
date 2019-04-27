package com.task.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.entity.*;

/**
 * xxxxxxxxAction层
 * 
 * @author xxx
 * 
 */
public class ActionUtil extends ActionSupport {

	private InsuranceGoldServer insuranceGoldServer;// Server层
	private InsuranceGold insuranceGold;// 对象
	private List<InsuranceGold> insuranceGoldList;// 集合
	private List<Object> list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession();
	public String Test() {
		return ERROR;
	}
	
	
	
	// 构造方法

}
