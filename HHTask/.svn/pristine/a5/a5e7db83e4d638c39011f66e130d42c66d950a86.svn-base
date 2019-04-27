<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
				检查记录表
				</h3>
					<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
		
						<td align="center">
							${checkNote.firstPerson}
						</td>
						<td align="center">
							${checkNote.checkType.type}----${checkNote.checkType.name}
						</td>
						<td align="center">
							<img alt="${checkNote.checkType.type}----${checkNote.checkType.name}" src="<%=basePath%>upload/file/sixJian/${list.url}" style="width:121px; height:162px">
						</td>
						<td align="center">
							${checkNote.describe}
						</td>
						<td align="center">
							${checkNote.status}
						</td>
					
					
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	
	</body>
</html>


