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
			<form action="WaigouwaiweiPlanAction!adjust.action"
				method="post">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">调整采购数量</font>
						</th>

					</tr>
					<tr>
						<th colspan="4">
							<center>

							</center>
						</th>
					</tr>
					<tr>
						<th align="right">
							原有数量：
						</th>

						<td>
							<input
								value="${procard.cgNumber}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							修改数量：
						</th>

						<td>
							<input type="hidden" name="procard.id"
								value="${procard.id}" />
							<input id="cgNumber" name="procard.cgNumber"/>
							<label id="message"></label>
						</td>
					</tr>
					

					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="调整" class="input" />
							<input type="reset" value=" 重置" class="input" />
						</td>
					</tr>
				</table>
			</form>
		</div>

<SCRIPT type="text/javascript">
</SCRIPT>
</body>
</html>