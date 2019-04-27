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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="test" style="position: absolute;right: 5px;top: 10px;">
					<canvas id="myCanvas" width="200" height="100"></canvas>
				</div>
				<form action="ProcardTemplateAction!updateProcessTz.action?tag=${tag}" enctype="multipart/form-data" method="post">  
				<table class="table">
				<tr>
				 <td align="left"><h3>缺陷图纸文件</h3>
				 </td>
				 <td align="left">
				 <input type="button" id="fileButton_1" onclick="uploadFile(this,1)" value="上传附件">
					<div id="fileDiv_1" style="display: none;">

					</div>
				 </td>
				 <td align="left">
				 <input type="hidden" name="id" value="${id}">
				 <input type="hidden" name="processTemplateFile.type" value="缺陷图纸">
				 <input type="submit" value="上传" style="width: 50px;height: 20px;">
				 </td>
				</tr>
				<tr id="qxTr">
				 <td colspan="3" align="left">
				 <h3>缺陷图纸</h3>
				 </td>
				</tr>
				<s:iterator value="list" id="qxFile">
				 	<s:if test="#qxFile.type=='缺陷图纸'">
						<tr>
							<td align="left">
								<a
<%--									href="<%=path%>/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>">--%>
									href="FileViewAction.action?FilePath=/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>">
									<img
										src="<%=path%>/upload/file/processTz/${qxFile.month}/<s:property value="#qxFile.fileName"/>"
										style="width: 80px; height: 80px;" />
								</a>
							</td>
							<td align="left">
								${oldfileName}
							</td>
							<td>
								<a
<%--									href="DownAction.action?fileName=${qxFile.fileName}&directory=/upload/file/processTz/${qxFile.month}/">下载</a>&nbsp;&nbsp;--%>
									href="FileViewAction.action?FilePath=/upload/file/processTz/${qxFile.month}/${qxFile.fileName}">下载</a>&nbsp;&nbsp;
								<a 
								onclick="return window.confirm('确认要删除该条记录，是否继续?')"
									href="ProcardTemplateAction!deleteProcessTz.action?processTemplateFile.id=${qxFile.id}&&id=${param.id}&&pageStatus=${pageStatus}&tag=${tag}">删除</a>
							</td>
						</tr>
					</s:if>
				</s:iterator>
				</table>
				 </form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
} 
$(function(){
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
 	ctx.strokeStyle = 'red';
	ctx.font="30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}",10,50);
})
		var fileDivHTML = "";
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
		</script>
	</body>
</html>
