<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<base href="<%=basePath%>">
		<%@include file="/util/sonHead.jsp"%>

		<title>My JSP 'role_listshow.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


		<script language="javascript"
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-1.12.4.js">
</script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<form action="UserRoleAction_add.action" method="post" style="">
				<br>
				<table border="0" width="100%" class="table">
					<tr>
						<td align="right">
							角色名称:
						</td>
						<td>
							<input type="text" id="name" name="userRole.name" />
						</td>
						<td align="right">
							角色描述:
						</td>
						<td>
							<input type="text" id="Url" name="userRole.description" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" value="添加submit" onclick="reload()"
								style="width: 100px; height: 50px;" />
							<input type="reset" value="重置reset"
								style="width: 100px; height: 50px;" />
						</td>
					</tr>
				</table>
			</form>

			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							角色
						</th>
						<th>
							角色描述
						</th>
						<%--						<th>--%>
						<%--							已绑人员--%>
						<%--						</th>--%>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="userroles" status="st" id="ms">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1 }
						</th>
						<td>
							${ms.name}
						</td>
						<td>
							${ms.description}
						</td>
						<%--						<td>--%>
						<%--							<s:iterator value="roles[0].users" status="st2" id="us">--%>
						<%--								${us.name}--%>
						<%--							</s:iterator>--%>
						<%--						</td>--%>
						<td>
							<a href="UserRoleAction_edituser.action?userRole.id=${ms.id}">绑定人员</a>/
							<a
								href="UserRoleAction_editModuleFunction.action?userRole.id=${ms.id}">绑定模块</a>
							<a href="UserRoleAction_editRole.action?id=${ms.id}">   修改角色 </a>
							<a href="UserRoleAction_delete.action?userRole.id=${ms.id}">&nbsp&nbsp&nbsp&nbsp&nbsp删除角色</a>
						</td>
						</tr>
					</s:iterator>

				</table>


			</div>
		</div>

		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
</script>
	</body>
</html>
