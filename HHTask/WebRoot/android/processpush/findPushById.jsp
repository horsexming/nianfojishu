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
			<form action="PushAction_addTaSopGongwei.action" method="post" >
				<table class="table">
					<tr>
						<th colspan="4" align="center"><h3>添加工位</h3></th>
					</tr>
					<tr>
						<th align="right">工位:</th>
						<td>
<%--						<input type="text" name="push.station" value="${push.station}" />--%>
				<select name="taSopGongwei.id">
						<s:iterator value="list2"  id="taSopGongwei" status="pageIndex">
						 <option value="${taSopGongwei.id}">${taSopGongwei.gongweihao}</option>
					</s:iterator>
				 </select>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
						<input name="push.id" value="${push_id}"  type="hidden">
						<input type="submit" value="添加" class="input" />
						</td>
					</tr>
				</table>
			</form>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
 		$(function(){
 			var successMessage = '${successMessage}';
 			if(successMessage!=""){
 				alert(successMessage);
 				parent.location.reload(true);//刷新父页面
 			}
 		})
	</script>
	
	</body>
</html>
