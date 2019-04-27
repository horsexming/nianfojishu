<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<!-- include file="/util/inc.jsp" -->
		<base href="<%=basePath%>">
		<title>公司信息管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.tag_1 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_1.jpg');
}

.tag_2 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_2_2.jpg');
}
</style>
		<script type="text/javascript">
function backsubmit() {
	window.location.href = "companyInfoAction_showCompanyInfos.action";
}
</SCRIPT>
		<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>

	<body bgcolor="#ffffff" onload="createDept('dept')">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">公司图标展示</font>
				</div>
			</div>
			<div>
				<div align="center">
					<font color="red"><s:property
							value="#session.successMessage" /> </font>
				</div>

				<div align="center"padding-top: 20px;">
					<div align="center" id="addModule">
						<form id="updateform" action="" method="post"
							enctype="multipart/form-data">
							<table class="table">
								<input type="hidden" name="cpage"
									value="<s:property value="#request.cpage"/>" />
								<input type="hidden" name="total"
									value="<s:property value="#request.total"/>" />
								<tr>
									<td align="right">
										登陆图标:
									</td>
									<td>
										<img
											src="${pageContext.request.contextPath}<s:property value="#request.companyInfo.tasklogo"/>"
											width="200">

									</td>
								</tr>
								<tr>
									<td align="right">
										底部图标:
									</td>
									<td>
										<img
											src="${pageContext.request.contextPath}<s:property value="#request.companyInfo.bottomlogo"/>"
											width="200">

									</td>
								</tr>
								<tr>
									<td align="right">
										打印图标:
									</td>
									<td>
										<img
											src="${pageContext.request.contextPath}<s:property value="#request.companyInfo.logoOKjpg"/>"
											width="200">

									</td>
								</tr>
								<tr>
									<td align="right">
										图表图标:
									</td>
									<td>
										<img
											src="${pageContext.request.contextPath}<s:property value="#request.companyInfo.shhhjpg"/>"
											width="200">

									</td>
								</tr>
								<tr>
									<td align="right">
										水印图标:
									</td>
									<td>
										<img
											src="${pageContext.request.contextPath}<s:property value="#request.companyInfo.shuiyinpng"/>"
											width="200">

									</td>
								</tr>
								<tr>
									<td align="right">
										ico图标:
									</td>
									<td>
										<%--											<img  src="${pageContext.request.contextPath}/<s:property value="#request.companyInfo.favicon"/>" width="200">--%>
										<%--												<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/<s:property value="#request.companyInfo.favicon"/>"  />--%>
										<%--									          ${pageContext.request.contextPath}/<s:property value="#request.companyInfo.favicon"/>--%>
									</td>
								</tr>



								<tr>
									<td colspan="2" align="center">
										<br>
										<input type="button" style="width: 100px; height: 50"
											onclick="backsubmit()" value="返回列表">
									</td>
								</tr>
							</table>
						</form>
					</div>

				</div>

				<div align="center">
					<%
							request.getSession().removeAttribute("successMessage");
							request.getSession().removeAttribute("errorMessage");
						%>
					<br />
					<br />
					<br />
				</div>
			</div>
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>
