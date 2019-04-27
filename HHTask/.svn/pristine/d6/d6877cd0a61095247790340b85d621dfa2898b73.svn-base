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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<%--<a
						href="InEmployeeCarInforAction_update.action?accessEquipment.id=${accessEquipment.id}"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				--%>
				</div>
			</div>

			<div align="center">
				<h3>
					修改内部车辆状态
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessRecordsAction_carType_update.action"
					method="post" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" colspan="2">
								<input type="hidden" name="carInOutType.id"
									value="${carInOutType.id}" />
								<input type="hidden" name="carInOutType.carPai"
									value="${carInOutType.carPai}" />
								车牌号:${carInOutType.carPai}
							</th>
							<th align="center">
								进出状态
							</th>
							<td align="center">
								<input type="text" name="carInOutType.carInOut" id="carInOut"
									value="${carInOutType.carInOut}" / />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update)"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
}
</script>
	</body>
</html>
