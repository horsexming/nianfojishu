<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>注销页面</title>
		<meta http-equiv="refresh" content="2; url=login.jsp">
	</head>

	<body>
		<br>
		<br>
		<br>
		<br>
		<center>
			<h3>
				正在注销！
			</h3>
		</center>
		<TABLE cellSpacing=0 cellPadding=0 border=0 align="center">
			<TBODY>
				<TR>
					<TD width=1 rowSpan=2></TD>
					<TD width=1 height=1></TD>
					<TD bgColor=#cccccc height=1></TD>
					<TD width=1></TD>
					<TD width=1 rowSpan=2></TD>
				</TR>
				<TR>
					<TD width=1 bgColor=#cccccc height=1></TD>
					<TD height=1></TD>
					<TD width=1 bgColor=#cccccc></TD>
				</TR>
				<TR>
					<TD width=1 bgColor=#cccccc></TD>
					<TD width=1 height=1></TD>
					<TD width=1200?>
						<MARQUEE scrollAmount=10 direction=right>
							<TABLE style="FONT-SIZE: 1px; WIDTH: 50px; HEIGHT: 10px">
								<TBODY>
									<TR>
										<TD bgColor=#e5fee5></TD>
										<TD bgColor=#caf7ca></TD>
										<TD bgColor=#8feb90></TD>
									</TR>
								</TBODY>
							</TABLE>
						</MARQUEE>
					</TD>
					<TD width=1 height=1></TD>
					<TD width=1 bgColor=#cccccc></TD>
				</TR>
				<TR>
					<TD rowSpan=2></TD>
					<TD bgColor=#cccccc height=1></TD>
					<TD height=1></TD>
					<TD bgColor=#cccccc></TD>
					<TD rowSpan=2></TD>
				</TR>
				<TR>
					<TD height=1></TD>
					<TD bgColor=#cccccc height=1></TD>
					<TD></TD>
				</TR>
			</TBODY>
		</TABLE>
		<%
			request.getSession().removeAttribute("admin");
					request.getSession().removeAttribute("adminusers");
			request.getSession().removeAttribute("root");
			out.println("<script>navigate('login.jsp');</script>");
		%>
	</body>
</html>

