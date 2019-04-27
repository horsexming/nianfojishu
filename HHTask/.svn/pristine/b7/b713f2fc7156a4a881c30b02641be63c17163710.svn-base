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
		<div align="center">
			<form action="BaoXiaoDanAction!addPayee.action"
				method="post">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">添加收款账户</font>
						</th>

					</tr>
					<tr>
						<th align="right">
							编号：
						</th>
						<td>
							${payee.num}
						</td>

						<th align="right">
							工号:
						</th>
						<td>
							${payee.code}
						</td>
						</tr>
						<tr>
							<th align="right">
								账号:
							</th>
							<td>
								${payee.aNumber}
							</td>
							<th align="right">
								名称:
							</th>
							<td>${payee.name}
							</td>
						</tr>
						<tr>
							<th align="right" >
								联系方式:
							</th>
							<td colspan="3">
								${payee.cmobile}
							</td>
						</tr>

					<tr>
					</tr>
				</table>
			</form>
		</div>

		<SCRIPT type="text/javascript">
	
		</SCRIPT>
	</body>
</html>