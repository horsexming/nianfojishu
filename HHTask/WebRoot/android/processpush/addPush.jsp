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
		<script type="text/javascript">

</script>
	</head>
	<!--	//onload="createDept('repairdepartment');"-->
	<body>
		<form action="PushAction_addProcessandmeid.action" method="post" >
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加序列</font>
					</th>
				</tr>
				<tr>
<%--					<th align="right">--%>
<%--						工位:--%>
<%--					</th>--%>
<%--					<td>--%>
<%--						<select name="taSopGongwei.id">--%>
<%--							<s:iterator value="list2"  id="taSopGongwei">--%>
<%--								<option value="${taSopGongwei.id}">--%>
<%--									${taSopGongwei.gongweihao}--%>
<%--								</option>--%>
<%--							</s:iterator>--%>
<%--						</select>--%>
<%--					</td>--%>
					<th align="right">
						设备序列号:
					</th>
					<td>
					<input   name="push.meid" />
					</td>
						<th align="right">ip地址:</th>
						<td>
						<input type="text" name="push.ipAddress" " />
						</td>
					</tr>
					<tr>
					  <th align="right">平板编号:</th>
						<td>
						<input type="text" name="push.flatNum" " />
						</td>
						<th align="right">平板工位:</th>
						<td>
						<input type="text" name="push.flatStation"  />
						</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="确  定" class="input" /> &nbsp;&nbsp;
						<input type="button" value="关 闭" onclick="isdown()" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
$(function() {
	var successMessage = '${successMessage}';
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})
	//刷新父页面关闭当前窗体
	function isdown(){
		parent.location.reload(true);//刷新父页面
	}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
