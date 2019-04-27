<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
			request.getSession().invalidate();
		%>
		<script type="text/javascript">
var loginType = "";// 登录方式
var strCookie = document.cookie;//获得所有cookie
var arrCookie = strCookie.split(";");
for ( var i = 0; i < arrCookie.length; i++) {
	var arr = arrCookie[i].split("=");
	var arr0 = arr[0].replace(/\ /g, "");
	if ("loginType" == arr0) {
		loginType = arr[1];
		break;
	}
}
window.location = loginType;
</script>
	</body>
</html>

