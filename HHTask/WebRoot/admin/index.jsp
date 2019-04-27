<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%@include file="checkAdmin.jsp"%>
<HTML>
	<HEAD>
		<TITLE>后台管理</TITLE>
		<link rel="shortcut icon" href="<%=path%>/favicon.ico" />
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<META http-equiv=Pragma content=no-cache>
		<META http-equiv=Cache-Control content=no-cache>
		<META http-equiv=Expires content=-1000>
		<LINK href="<%=path%>/admin/css/admin.css" type="text/css"
			rel="stylesheet">
		<script type="text/javascript">
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;

</script>
	</HEAD>
	<FRAMESET border=0 frameSpacing=0 rows="60, *" frameBorder=0>
		<FRAME name=header src="<%=path%>/admin/header.jsp" frameBorder=0
			noResize scrolling=no>
		<FRAMESET cols="250, *">
			<FRAME id="menu" name="menu" src="<%=path%>/admin/menu.jsp"
				frameBorder=0 noResize>
			<FRAME id="main" name="main" src="<%=path%>/admin/main.jsp"
				frameBorder=0 noResize scrolling=yes>
		</FRAMESET>
	</FRAMESET>
	<noframes>
	</noframes>
</HTML>
