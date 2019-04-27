<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
	<link rel="shortcut icon" href="favicon.ico" />
	<base href="<%=basePath%>">
	<title>${moduleFunction.functionName},生产力生态平衡系统</title>
	<%@include file="/util/inc.jsp"%>
</head>
<body bgcolor="#ffffff">

	<%--	<%@include file="util/oldSonTop.jsp"%>--%>

	<%--	<div id="daohang" align="left"--%>
	<%--		style="width: 100%; border: solid 1px #0170b8; padding: 5 0 5; margin-top: 10px; font-size: 16px;">--%>
	<%--		<a href="index.jsp">首页</a> >>${moduleFunction.functionName}的搜索列表--%>
	<%--	</div>--%>
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
			style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
			align="left">
			<font color="#ffffff">搜索功能管理</font>
		</div>

		<div align="left">
			<div align="center">
				<font color="red">${errorMessage}</font>
			</div>
			<s:iterator id="searchMf" value="moduleFunctionList" status="pageIndex">
				<div
					style="border-bottom: solid 1px #0170b8; margin: 10px 10px 0px 10px; padding-top: 5px; background: url('<%=basePath%>title.jpg');">
					<div style="font-weight: bold; padding-left: 10px; ">
						${pageIndex.index+1}、<a
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${searchMf.id}"
							target="_blank">${searchMf.functionName}</a>
					</div>
					<%--					<hr>--%>
					<%--					<div>--%>
					<%--						&nbsp;&nbsp;&nbsp;&nbsp;${searchMf.functionIntro}--%>
					<%--					</div>--%>
					<%--					<div align="right"--%>
					<%--						style="padding-right: 10px; padding-bottom: 5px;">--%>
					<%--						<a--%>
					<%--							href="ModuleFunctionAction!findMfByIdForJump.action?id=${searchMf.id}"--%>
					<%--							target="_blank">进入</a>--%>
					<%--					</div>--%>
				</div>
			</s:iterator>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%></center>
</body>
</html>
