<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%@include file="checkAdmin.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
	</HEAD>
	<BODY>
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background="images/header_bg.jpg" border=0>
			<TR height=56>
				<TD width=260>
					<IMG height=56 src="images/header_left.jpg" width=260>
				</TD>
				<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
					align=middle>
					当前用户：
					<font color="red"> ${admin.adminName} </font> &nbsp;&nbsp;
					<A style="COLOR: #fff" href="index.jsp" target="_top">后台首页</A>
					&nbsp;&nbsp;
					<A style="COLOR: #fff" href="<%=basePath%>index.jsp" target="_top">系统首页</A>
					&nbsp;&nbsp;
					<s:if test="#session.adminusers==null">
						<A style="COLOR: #fff" href="updatePassword.jsp" target=main>修改密码</A>
					</s:if>
					&nbsp;&nbsp;
					<A style="COLOR: #fff"
						onclick="if (confirm('确定要退出吗？')) return true; else return false;"
						href="logOff.jsp" target=_top>退出系统</A>
				</TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TR bgColor=#1c5db6 height=4>
				<TD></TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>