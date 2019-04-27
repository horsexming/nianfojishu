<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

		<title>My JSP 'VisitCo_show.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<h1 align="center">
			来访公司列表添加
		</h1>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<form action="VisitCoAction_add.action" method="post" style="">
				<br>
				<table border="0" width="100%" class="table">
					<tr>
						<td align="right">
							公司名称:
						</td>
						<td>
							<input type="text" name="visitCo.name" />
						</td>
						<td align="right">
							联系人:
						</td>
						<td>
							<input type="text" name="visitCo.contactPerson" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td>
							<input type="text" name="visitCo.contactNumber" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<input type="submit" value="添加submit" onclick="reload()"
								style="width: 100px; height: 50px;" />
							<input type="reset" value="重置reset"
								style="width: 100px; height: 50px;" />
						</td>
					</tr>
				</table>
			</form>

		</div>

		<div align="center">
			<table width="100%" border="0" style="border-collapse: collapse;"
				class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<th>
						序号
					</th>
					<th>
						公司名称
					</th>
					<th>
						联系人
					</th>
					<th>
						联系电话
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="visitCoList" status="st" id="ms">
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
						${ms.contactNumber}
					</td>
					<td>
						${ms.contactPerson}
					</td>
					<td>
						<a href="VisitCoAction_delete.action?id=${ms.id}">删除</a>
					</td>
					</tr>
				</s:iterator>

			</table>


		</div>

		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
</script>
	</body>
</html>
