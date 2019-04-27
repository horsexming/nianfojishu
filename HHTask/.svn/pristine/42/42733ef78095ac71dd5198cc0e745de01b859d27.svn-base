<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人脸列表</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="${pageContext.request.contextPath}/faceAction!gotoPictureSubmit.action" method="post">
					<table class="table">
						<tr>
							<th>工号：</th>
							<td><input type="text" name="faceUsers.code" value="${faceUsers.code }"></td>
						</tr>
						<tr>
							<th>姓名：</th>
							<td><input type="text" name="faceUsers.userName" value="${faceUsers.userName }"></td>
						</tr>
					</table>
					<input type="hidden" name="faceUsers.picturePath" value="${faceUsers.picturePath }">
					工号：<br>
					用户ID：<input type="text" name="faceUsers.userId" value="${faceUsers.userId }">
<%-- 					<input type="text" name="faceUsers.groupName" value="${faceUsers.groupName }" > --%>
					<input type="submit" value="提交" >
				</form>
				<img src="${pageContext.request.contextPath}/upload/file/face/${faceUsers.picturePath}" width="60%" >
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		</script>
	</body>
</html>
