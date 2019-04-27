<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center">
					员工离场交接单
				</div>
				<div>
					<form action="InformAction!leave.action" method="post">
						<input type="hidden" name="leaveInform.time"
							value="${leaveInform.time}" />
						<input type="hidden" name="leaveInform.dept"
							value="${leaveInform.dept}" />
						<input type="hidden" name="leaveInform.code"
							value="${leaveInform.code}" />
						<input type="hidden" name="leaveInform.username"
							value="${leaveInform.username}" />
						<table class="table">
							<tr>
								<td align="center">
									部门:
								</td>
								<td align="center">
									${users.dept}
								</td>
								<td align="center">
									姓名:
								</td>
								<td align="center">
									${users.name}
								</td>

								<td align="center">
									离职申请编号:
								</td>
								<td align="center">
									${leaveInform.time}
								</td>
							</tr>
							<tr>
								<td align="center">
									离职原因:
								</td>
								<td colspan="3" align="center">
									<input type="text" name="leaveInform.reason" value="克扣军饷"
										size="55">
								</td>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="确认"
										style="width: 80px; height: 50px;" />
								</td>
								
							</tr>
						</table>
					</form>
				</div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
