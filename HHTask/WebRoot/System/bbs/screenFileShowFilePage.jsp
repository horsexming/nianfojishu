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
		<SCRIPT type="text/javascript">
			function deleteFile(mid){
				if(!confirm('确定删除？')){
					return;
				}
				$.ajax({
					type: "POST",
					url: "screenFiles_delete.action",
					data : {
						id : mid
					},
					dataType : "json",
					success: function(json){
						alert(json.message);
						if(json.success){
							window.location.reload();
						}
					}
				});
			}
		</SCRIPT>
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
				<table style="width: 70%" id="fileUploadTable" class="table">
					<tr>
						<th colspan="8">
							查看屏幕下所有的工序
						</th>
					</tr>
					<tr>
						<th>序号</th>
						<th>类型</th>
						<th>文件</th>
						<th>操作</th>
					</tr>
					<s:iterator value="files" id="ff" status="st">
						<tr>
							<th>${st.index+1}</th>
							<th>${ff.type}</th>
<%--							<th><a target="_blank" href="DownAction.action?fileName=${filepath}&directory=/upload/file/screenfile/">${ff.filename}</a></th>--%>
							<th><a target="_blank" href="FileViewAction.action?FilePath=/upload/file/screenfile/${filepath}">${ff.filename}</a></th>
							<th><input type="button" onclick="deleteFile(${ff.id});" value="删除"/></th>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
