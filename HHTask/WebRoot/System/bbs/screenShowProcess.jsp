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
		<script type="text/javascript">
			$(function(){
				 $.ajax({
					type: "POST",
					url: "screenFiles_autoFile.action?screen.id=" + ${screen.id},
					dataType : "json",
					success: function(json){
						for(var i=0; i<json.length; i++){
							var td = "<tr><th>" + (i+1) + "</th><td>" + json[i].proname + "</td><td>" + json[i].markid + "</td><td>" + json[i].processName + "</td><td> <a href='screenFiles_uploadPage.action?id="+json[i].id+"'>上传文件</a>&nbsp;&nbsp;&nbsp;<a href='screenFiles_showFilePage?id="+json[i].id+"'>查看文件</a></td></tr>"
							$('#fileUploadTable tr:last').after(td);
						}
					}
				});
			});
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;" align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table id="fileUploadTable" class="table">
					<tr>
						<th colspan="8">
							查看屏幕下所有的工序
						</th>
					</tr>
					<tr>
						<th>序号</th>
						<th>总成名称</th>
						<th>件号</th>
						<th>工序名</th>
						<th>相关文件</th>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
