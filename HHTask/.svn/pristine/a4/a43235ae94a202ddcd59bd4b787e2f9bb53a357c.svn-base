 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'DmlAppFileUrl_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="System/dmltry/jquery-1.11.3.min.js"></script>
<%@include file="/util/sonHead.jsp"%>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px;"
			align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left"></div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
			</div>
		</div>

		<div align="center">
			<h3>
				查看明细信息 <br />
				<s:if test="successMessage!=null">
					<font color="red">${successMessage}</font>
				</s:if>
			</h3>

			<form action="DmltryAppFilesAction!add.action" method="post"
				enctype="multipart/form-data">
				
				
			<table class="table">
		<tr bgcolor="#c0dcf2" height="50px">
				<th>序号</th>
				<th>版本号</th>
				<th>附件名称</th>
		</tr>
						<s:iterator id="liste" value="dmlAppFileUrllist" status="stauts">
			<s:if test="#pageStatus.index%2==1">
				<tr align="center" bgcolor="#e6f3fb"
					onmouseover="chageBgcolor(this)"
					onmouseout="outBgcolor(this,'#e6f3fb')">
			</s:if>
			<s:else>
				<tr align="center" onmouseover="chageBgcolor(this)"
					style="height: 25px;" onmouseout="outBgcolor(this,'')">
			</s:else>
				<td><s:property value="#stauts.index+1" /></td>
		<!-- <input type="text" value="${liste.coide}" name="dmltryAppFiles.appFilename" /> -->		<td>${liste.coide}</td>		
		<!-- <input type="text"value="${liste.appfileurlfj}" name="dmltryAppFiles.appFilesmshu" /> -->		<td><a href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/appFile/${liste.appfileurlfj}">${liste.appfileurlfj}</a></td>
				
				</s:iterator>
				</table>
			</form>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
</body>
</html>
