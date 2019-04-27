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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="left">
				<div id="printDiv">
					<br />
					<div
						style="font-weight: bolder; font-size: 30px; margin-bottom: 10px;"
						align="center">
						请确认入库数量
					</div>
					<form action="WaigouwaiweiPlanAction!updateWNToRK.action"
						method="post" onsubmit="return validate()">
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									件号
									<input type="hidden" name="warehouseNumber.id"
										value="${warehouseNumber.id}" />
								</th>
								<th align="center">
									零件名称
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									物料位置
								</th>
							</tr>
							<tr>
								<td align="center">
									${waigoudd.markId}
									<input type="hidden"
										name="waigoudd.id" value="${waigoudd.id}">
								</td>
								<td align="center">
									${waigoudd.proName}
								</td>
								<td align="center">
									<input type="text" style="width: 60px;"
										name="waigoudd.hgNumber" value="${waigoudd.hgNumber}">
									${waigoudd.unit}
								</td>
								<td align="center">
									${warehouseNumber.number}
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="确认"
										style="width: 60px; height: 40px;" id = "queren"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	function validate(){
		$("#queren").attr("disabled", "disabled");
	}
</script>
	</body>
</html>