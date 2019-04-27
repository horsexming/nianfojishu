<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body onload="createDept('dept')" >
<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
		<h4>体系文件详情</h4>
				<table class="table">
					<tr>
					  <th>文件类型
					  </th>
					  <td>
					  
					  	${systemFile.fileType}
    					
					  </td>
					  <th>文件等级
					  </th>
					  <td>
					  ${systemFile.fileLevel}
    					
					  </td>
					</tr>
					<tr>
					  <th>文件编号
					  </th>
					  <td>
					  	${systemFile.fileNo}
					  </td>
					  <th>文件名称
					  </th>
					  <td>
					  	${systemFile.fileName}
					  </td>
					</tr>
					<tr>
					  <th>所属部门
					  </th>
					  <td>
					  	${systemFile.department}
					  </td>
					</tr>
					<tr>
					    <th>文件描述
					    </th>
					    <td colspan="3">
					   ${systemFile.description}
					    </td>
					</tr>
					<tr>
						<th>体系文件
					 	</th>
<%--					 		<td><a href="<%=path%>/upload/file/sysFile/${systemFile.fileUrl}"><FONT color="red">${systemFile.fileUrl}</FONT></a></td>--%>
					 		<td><a href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}"><FONT color="red">${systemFile.fileUrl}</FONT></a></td>
						</tr>
					<tr>
					 <td colspan="4" align="center">
					 <input type="hidden" name="tag" value="${tag}" id="tag"/>
					   <input type="button" value="返回" onclick="findAll('${tag}','${level}')" style="width: 60px; height: 30px;" >
					 </td>
					</tr>
				</table>
			
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
	function findAll(tag,level){
	if(tag=="nolook"){
		window.location.href = "systemFileAction_findAllBylevel.action?tag="+"nolook"+"&level="+level;
	}else{
		alert(tag)
		window.location.href = "systemFileAction_findAll.action";
	}
}
	</script>
</body>
</html>
