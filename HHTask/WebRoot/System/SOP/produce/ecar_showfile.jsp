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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>${ecar.ecarNumber}文件</h3>
				<s:if test="#session.Users.id==ecar.addUserId">
				<div align="center">
					<form action="procardTemplateGyAction_updateecarFile.action"  enctype="multipart/form-data" method="post">
						<input type="hidden" value="${ecar.id}" name="id">
						<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">
										<div id="fileDiv_1" style="display: none;">

										</div>
						<input type="submit" value="上传" >
					</form>
				</div>
				</s:if>
				<table class="table">
					<tr>
						<td align="center">文件</td>
						<td align="center">文件名称</td>
						<td align="center">上传日期</td>
						<td align="center">上传人</td>
						<td align="center">操作</td>
					</tr>
					<tr>
						<td align="center" colspan="5" bgcolor="red">已上传文件</td>
					</tr>
					<s:iterator value="ecarfileList" id="pageecarFile">
					<tr>
						<td align="center"><a
									href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/ecar/<s:property value="#pageecarFile.filename"/>">
									<img
										src="<%=path%>/upload/file/ecar/<s:property value="#pageecarFile.filename"/>"
										style="width: 80px; height: 80px;" />
								</a></td>
						<td align="center"><s:property value="#pageecarFile.oldFileName"/></td>
						<td align="center"><s:property value="#pageecarFile.addTime"/></td>
						<td align="center"><s:property value="#pageecarFile.addUserName"/></td>
						<td align="center">
						<a href="procardTemplateGyAction_downloadEcarFile.action?id=<s:property value="#pageecarFile.id"/>">下载</a>
						<s:if test="#session.Users.id==#pageecarFile.addUserId">
						<a href="javascript:void(0);" onclick="deleteecarFile(<s:property value="#pageecarFile.id"/>)">删除</a>
						</s:if>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function deleteecarFile(id){
	if(confirm("是否确定删除?")){
		window.location.href="procardTemplateGyAction_deleteecarFile.action?id="+id;
	}
}
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}
</SCRIPT>
	</body>
</html>
