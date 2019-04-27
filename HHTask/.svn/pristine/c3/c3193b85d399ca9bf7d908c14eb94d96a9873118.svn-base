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
		<form action="UnitManagerAction_addUnitManager.action" method="post" >
		  <s:if test="pageStatus==null">
			<input type="hidden" name="manager.type" value="unit"/>
		  </s:if>
		  <s:else>
			<input type="hidden" name="manager.type" value="${param.pageStatus}"/>
		  </s:else>
			<table align="center" class="table">
				<tr>
					<th colspan="6">
					    <s:if test="%{pageStatus=='material'}">
						<font size="5">添加材质</font>
					    </s:if>
					    <s:else>
						<font size="5">添加单位</font>
					    </s:else>
					</th>
				</tr>
				<tr>
					<th align="right">
					<s:if test="pageStatus=='material'">
						材质名称:
					    </s:if>
					    <s:else>
						单位名称:
					    </s:else>
						
					</th>
					<td>
						<input   name="manager.unitname" value="" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="button" value="关 闭" onclick="isdown()" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
				var successMessage = '${successMessage}';
				if (successMessage != "") {
					alert(successMessage);
					parent.location.reload(true);//刷新父页面
				}
 
				//刷新父页面关闭当前窗体
				function isdown(){
					parent.location.reload(true);//刷新父页面
				}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
