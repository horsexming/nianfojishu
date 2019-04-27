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
		<script type="text/javascript" src="../../javascript/jquery-1.8.3.js">
</script>

	</head>
	<body>
		<form id="xform" action="GzstoreAction_toUpdateGzbj.action"
			method="post" onsubmit="return validate()">
			<table class="table">
				<tr>
					<th colspan="2" align="center">
						<h3>
							修改工装信息
						</h3>
					</th>
				</tr>
				<tr>
					<th align="right">
						工装名称:
					</th>
					<td>
						<input type="text" id="matetag" name="gzstore.matetag"
							value="${gzstore.matetag}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th align="right">
						工装数量:
					</th>
					<td>
						<input type="text" id="number " name="gzstore.number"
							value="${gzstore.number}"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						仓库:
					</th>
					<td>
						<input type="text" name="gzstore.storehouse"
							value="${gzstore.storehouse}"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						型别:
					</th>
					<td>
						<input type="text" name="gzstore.xingbie"
							value="${gzstore.xingbie}" />
					</td>
				</tr>
				<tr>
					<th align="right"></th>
					<td>
						<input type="hidden" name="gzstore.id"  value="${gzstore.id}" />
						<input type="submit" value="修改 " class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
		$(function(){
			var errorMessage='${errorMessage}';
			if(errorMessage!=""){
				alert(errorMessage);
				parent.location.reload(true);//刷新父页面
//				parent.newUrl();//调用其父页面的方法
			}
		});
		
	function validate() {
		var matetag = document.getElementById("matetag").value;
		//	var storehouse = document.getElementById("storehouse").value;
		//	var parClass = document.getElementById("parClass").value;
	if (matetag == "") {
		alert("请输入名称!");
		return false;
	}
//	if (storehouse == "") {
//		alert("请输入仓库名称！");
//		return false;
//	}
//	if (parClass == "") {
//		alert("请输入分类名称！");
//		return false;
//	}
}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
