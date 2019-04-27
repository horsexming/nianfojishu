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
<body onload="createDept('dept');createDept('dept1');" >
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
		<h4>添加追踪记录</h4>
			<form action="SetupCheckAction_addTrackRecord.action"  method="post"  enctype="multipart/form-data" onsubmit="return validate()">
				<table class="table">
					<tr>
					  <th>描述
					  </th>
					  <td colspan="3">
					  <input type="hidden" name="trackRecord.setupCheckId"
								value="${setupCheck.id}" id="id"/>
					   <textarea rows="5" cols="80" name="trackRecord.description">${trackRecord.description}</textarea>	
					  </td>
					  </tr>
					  <tr>
					  <th>上传文件</th>
						<td colspan="3">
						<br/>
							<input type="file" name="shortFile" id="shortFile"/>
						</td>
					</tr>
					 <td colspan="4" align="center">
					   <input type="submit" value="提交">
					 </td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">

	</script>
</body>
</html>

