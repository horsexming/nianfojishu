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
		<form action="GoodsStoreAction!addgoodHouse.action" method="post"
			onsubmit="return checkOK()">
			<table align="center" class="table">
				<tr>
					<th colspan="4">
						<font size="5">添加仓区</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						区名:
					</th>
					
					<td>
						<input id="GoodHouseName" name="goodHouse.GoodHouseName" value="" />
					</td>
					
							<th align="right">
								编号:
								</th>
						<td>
						<input id="GoodHouseNum" name="goodHouse.GoodHouseNum" value="" />
					</td>
					
							</tr>
							
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
function checkOK() {
	var GoodHouseName = document.getElementById("GoodHouseName");
	if (GoodHouseName.value == "") {
		alert("请填写区名!");
		GoodHouseName.focus();
		return false;

	}

}
</script>
</html>
