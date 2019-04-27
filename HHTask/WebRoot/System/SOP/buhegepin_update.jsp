<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>不合格品类型修改</title>
		<%@include file="/util/sonHead.jsp"%>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}

</style>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div align="center">
			<br />
			
			<form action="BuHeGePinAction_updataBuHeGePin.action" method="post">
				<table width="80%">
					<tr>
						<td align="center" colspan="2"><strong>不合格品类型修改</strong></td>
					</tr>
					<tr>
						<td align="right">
							<strong>缺陷类型</strong>
						</td>
						<td>
							<input type="text" value='${buhegepin.type}' name="buhegepin.type"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" value='${buhegepin.id}'
								name="buhegepin.id" />
							<input type="reset" value="重置"  style="width:100; height:40"/>
							&nbsp;	&nbsp;
							<input type="submit" value="修改"  style="width:100; height:40"/>
						</td>
					</tr>
				</table>
				<input type="hidden" id="rebeack" value='${successMessage}'/>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</script>
	</body>
</html>
