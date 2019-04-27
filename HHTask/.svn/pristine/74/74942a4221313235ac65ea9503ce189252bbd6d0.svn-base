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
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
			style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
			align="left">
			<font color="#ffffff">收藏夹功能管理</font>
		</div>
		<div align="left">
			<div align="center">
				<font color="red">${errorMessage}</font>
			</div>
			<s:iterator id="favorites" value="listFavorites" status="pageIndex">
				<div
					style="width:48%;float: left;border-bottom: solid 1px #0170b8; margin: 10px 10px 0px 10px; padding-top: 5px; background: url('<%=basePath%>title.jpg');">
					<div
						style="font-weight: bold; padding-left: 10px; width: 100%; float: left;">
						${pageIndex.index+1}、
						<input type="button"
							style="color:#fff;width:200px;height:50px;background-color:${favorites.bgcolor} "
							value="${favorites.name}"
							onclick="changeiframe('${favorites.mfid}')">
						<a href="FavoriteAction_delete.action?id=${favorites.id}"
							style="float: right"> 删除</a>
					</div>
				</div>
			</s:iterator>
		</div>
		<br>
	</div>
	<script type="text/javascript">
function changeiframe(id) {
	window.parent.location.href = "ModuleFunctionAction!findMfByIdForJump.action?id="
			+ id;
}
</script>
	<%@include file="/util/foot.jsp"%></center>
</body>
</html>
