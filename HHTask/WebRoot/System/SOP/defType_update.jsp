<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加缺陷类型</title>
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
		<br />
		<div align="center">
			<form action="BuHeGePinAction_updateDefType.action" method="post"
				onsubmit="return yanzhen()">
				<table width="80%" id="mytable">
					<tr>
						<td colspan="4" align="center">
							<h2>
								修改不合格缺陷大类
							</h2>
						</td>
					</tr>
					<tr>
						<td align="right">
							缺陷代码
						</td>
						<td align="left">
							<input type="text" name="defType.defCode" id="code" value="${defType.defCode}"/>
						</td>
						<td align="right">
							缺陷描述
						</td>
						<td align="left">
							<input type="text" name="defType.defName" id="type" value="${defType.defCode}"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<h2>
								修改不合格缺陷小类
							</h2>
						</td>
					</tr>
					<s:iterator id="pagebhg" value="buhegepinlist"
								status="statussdf">
						<tr>
						<td align="right">
							缺陷代码
						</td>
						<td align="left">
							<input type="text" name="defType.bhgList[${statussdf.index}].code"  class="codes" value="${pagebhg.code}" />
						</td>
						<td align="right">
							缺陷描述
						</td>
						<td align="left">
							<input type="text" name="defType.bhgList[${statussdf.index}].type" class="types" value="${pagebhg.type}" />
							<input type="hidden" name="defType.bhgList[${statussdf.index}].id" value="${pagebhg.id}" class="bhgIds"/>
							<s:if test="#statussdf.first">
								<input type="button" value="增添" onclick="addLine()"/>
								<input type="button" value="删除" onclick="delLine()"/>
							</s:if>
							
						</td>
					</tr>
					</s:iterator>
					<tr>
					</tr>
				</table>
				<input type="hidden" value="${defType.id}" name="defType.id"/>
				<input type="submit" value="修改" id="add"
								style="width: 100px; height: 40px" />


			</form>
			<input type="hidden" id="rebeack" value='${errorMessage}' />


		</div>

		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(document).ready(function() {
	var rebeack = $("#rebeack").val();
	if (rebeack == "修改成功") {
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function yanzhen() {
	var code = document.getElementById("code");
	var type = document.getElementById("type");
	var codes = document.getElementsByClassName("codes");
	var types = document.getElementsByClassName("types");
	var bool = false;
	if(codes!=null && codes.length>0){
		for(var i=0;i<codes.length;i++){
			if(codes[i].value == ''){
				alert("缺陷小类缺陷代码不能为空!");
				codes[i].focus();
				bool = true;
				return false;
			}
		}
	}
	if(types!=null && types.length>0){
		for(var j=0;j<types.length;j++){
			if(types[j].value == ''){
				alert("缺陷小类缺陷类型不能为空!");
				types[j].focus();
				bool = true;
				return false;
			}
		}
	}
	if (code != null && code.value == "") {
		alert("缺陷代码不能为空!");
		code.focus();
		return false;
	} else if (type != null && type.value == "") {
		alert("缺陷类型不能为空!");
		type.focus();
		return false;
	}else if(!bool){
		//document.getElementById("add").disabled="disabled";
		$("#add").attr("disabled", "disabled");
		return true;
	}
}
var size = document.getElementsByClassName("bhgIds").length;
var index = size;
function addLine(){
	var newLine = '<tr><td align="right">缺陷代码</td><td align="left"><input type="text" name="defType.bhgList['+index+'].type" id="code" class="codes" /></td>' +
	'<td align="right">缺陷描述</td><td align="left"><input type="text" name="defType.bhgList['+index+'].code" id="type" class="types" /></td></tr> ';
	$("#mytable").append(newLine);
	index++;
}
function delLine(){
	if(index==size){
		alert("原有缺陷小类不允许删除");
		return false;
	}
	var n = $('#mytable tr').length;
	$($('#mytable tr')[n-1]).remove();
	index--; 
}
</script>


	
</script>
</html>
