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
			<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
			
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>添加物料类别</h2>
			<font id="zi_font" color="red">${errorMessage}</font>
				<form action="CategoryAction_addcategory.action" method="post" onsubmit="return check();">
					<table id="mytable">
						<tr>
							<th>
								类别名称:
							</th>
							<td>
								<input type="text" name="cylist[0].name" id="name0"/>
								<input type="button" value="追加" onclick="addline()" />
								<input type="button" value="删除" onclick="deline()">
							</td>
						</tr>
						<tr id="lastTr">
							<td colspan="2" align="center">
								<input type="hidden" value="物料" name="type">
								<input type="submit" value="提交" class="input"/>
							</td>
						</tr>
					</table>
				</form>
				<div style="float: left;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
	var index = 1;
function addline(){
	var newline = '<tr><th>类别名称:</th><td><input type="text" name=cylist['+index+'].name  id="name'+index+'" /></td></tr>';
	$("#lastTr").before(newline);
	index++;
}
function deline(){
	var n = $('#mytable tr').length;
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[n - 2]).remove();
		index--;
}	
function check(){
	var bool = false;
	var i;
	for(i = 0;i<=index; i++){
		var name = $("#name"+i).val();
		if(name == ""){
			bool = true;
			break;
		}
	}
	if(bool){
		$("#zi_font").html("请填写类别名称");
		$("#name"+i).focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled"
	return true;
}



</SCRIPT>
	</body>
</html>
