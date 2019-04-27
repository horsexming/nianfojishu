<%@ page language="java" import="java.util.*,com.task.entity.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Admin admin = (Admin) request.getSession().getAttribute("admin");
	if (admin == null) {
		response.sendRedirect("login.jsp");
	}
%>
<%@taglib prefix="s" uri="/struts-tags"%>