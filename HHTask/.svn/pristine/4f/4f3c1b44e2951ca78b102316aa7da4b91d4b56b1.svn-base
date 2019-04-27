<%@ page language="java"
	import="java.util.*,java.text.*,com.task.entity.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<script type="text/javascript">
function back() {
	history.go(-1);
}
var timerID = null;
var timerRunning = false;
function stopclock() {
	if (timerRunning)
		clearTimeout(timerID);
	timerRunning = false;
}
function startclock() {
	stopclock();
	showtime();
}
function showtime() {
	var now = new Date();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds()
	var timeValue = now.getFullYear() + "年" + (now.getMonth() + 1) + "月"
			+ now.getDate() + "日" + ((hours >= 12) ? " 下午 " : " 上午 ")
	timeValue += ((hours > 12) ? hours - 12 : hours)
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds
	document.getElementById("thetime").innerHTML = timeValue;
	timerID = setTimeout("showtime()", 1000);
	timerRunning = true;
}
</script>
	</HEAD>
	<BODY onload=startclock()>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR height=28>
				<TD background=images/title_bg1.jpg>
					当前位置:
				</TD>
			</TR>
			<TR>
				<TD bgColor=#b1ceef height=1></TD>
			</TR>
			<TR height=20>
				<TD background=images/shadow_bg.jpg></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
			<TR height=100>
				<TD align=middle width=100>
					<IMG height=100 src="images/admin_p.gif" width=90>
				</TD>
				<TD width=60>
					&nbsp;
				</TD>
				<TD>
					<TABLE height=100 cellSpacing=0 cellPadding=0 width="100%" border=0>

						<TR>
							<TD>
								当前时间：
								<span id="thetime"></span>
							</TD>
						</TR>

						<TR>
							<TD style="FONT-WEIGHT: bold; FONT-SIZE: 16px">
							<s:if test="#session.admin!=null">
					${admin.adminName}
					</s:if>
					<s:else>
					${users.name}
					</s:else>
							</TD>
						</TR>
						<TR>
							<TD>
								<%
									request.setCharacterEncoding("utf-8");
									String successMessage = (String) request.getSession().getAttribute(
											"successMessage");
									String errorMessage = (String) request.getSession().getAttribute(
											"errorMessage");
									if (successMessage != null) {
										out.print("<font color='red'>" + successMessage + "</font>");
									} else if (errorMessage != null) {
										out.print("<font color='red'>" + errorMessage + "</font>");
									} else {
										out.print("欢迎进入网站管理中心！");

									}
									request.getSession().removeAttribute("successMessage");
									request.getSession().removeAttribute("errorMessage");
								%>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD colSpan=3 height=10></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
			<TR height=20>
				<TD></TD>
			</TR>
			<TR height=22>
				<TD style="PADDING-LEFT: 20px; FONT-WEIGHT: bold; COLOR: #ffffff"
					align=middle background=images/title_bg2.jpg>
					您的相关信息
				</TD>
			</TR>
			<TR bgColor=#ecf4fc height=12>
				<TD></TD>
			</TR>
			<TR height=20>
				<TD></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=2 width="95%" align=center border=0>
		
			<TR>
				<TD align=right width=100>
					登陆帐号：
				</TD>
				<TD style="COLOR: #880000">
					${admin.adminName}
				</TD>
			</TR>
			<s:if test="#session.adminusers==null">
			<TR>
				<TD align=right>
					登陆次数：
				</TD>
				<TD style="COLOR: #880000">
					${admin.count}
				</TD>
			</TR>
			<TR>
				<TD align=right>
					上次登录时间：
				</TD>
				<TD style="COLOR: #880000">
					${admin.lastLogin}
				</TD>
			</TR>
			</s:if>
			<TR>
				<TD align=right>
					IP地址：
				</TD>
				<TD style="COLOR: #880000">
					<%=request.getRemoteAddr()%>
				</TD>
			</TR>
			<TR>
				<TD align=right>
					身份过期：
				</TD>
				<TD style="COLOR: #880000">
					30 分钟
				</TD>
			</TR>
			
			
		</TABLE>
	</BODY>
</HTML>