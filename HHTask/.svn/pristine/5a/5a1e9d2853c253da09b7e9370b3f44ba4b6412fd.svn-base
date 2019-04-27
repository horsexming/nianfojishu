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
			
				<form method="post" enctype="multipart/form-data" id="addForm">
					<input type="hidden" name="flag" value="ajax" /> 
					<h2>
						添加系统需求
					</h2>
					<table class="table" width="85%">
						<tr>
							<th align="right">需求添加人:</th>
							<td align="left">
								<input type="text" value="${Users.name}" readonly="readonly"/>
							</td>
							<th align="right">部门:</h>
							<th align="left">
								<input type="text" value="${Users.dept}" readonly="readonly"/>
							</th>
						</tr>
						<tr>
<%--							<th align="right">需求编号:</th>--%>
<%--							<td >--%>
<%--								<input type="text" value="${systemDemand.sdNum}" >--%>
<%--							</td>--%>
							<th align="right">
								需求简称:
							</th>
							<td>
								<input type="text" value="" name="systemDemand.sdShortName" id="sdShortName">
							</td>
							<th align="right">
								需求类型:
							</th>
							<td>
								<select name="systemDemand.sdType" id="sdType" class="select">
									<option value="">--请选择--</option>
									<option value="系统新需求">系统新需求</option>
									<option value="系统问题">系统问题</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								功能名称:
							</th>
							<td>
								<input type="text" value="" name="systemDemand.functionName" id="functionName">
							</td>
							<th align="right">
								需求级别:
							</th>
							<td>
								<input type="text" value="" name="systemDemand.sdLeave" id="sdLeave">
							</td>
						</tr>
						<tr>
							<th align="right">需求描述:</th>
							<td colspan="3">
								<textarea rows="5" cols="50"name="systemDemand.sdDesc" id="sdDesc"></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								需求文件
							</th>
							<td colspan="3">
<%--								<input type="text" value="" style="width: 500px; height: 45px;"--%>
<%--									name="rtxConnect.driverName" id="driverName">--%>
								<input type="file" name="demandFile" id="demandFile" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="button" value="提交" class="input" id="sub" onclick="addSystemDemand()">
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
});

/**
 * ajax 添加系统需求
 */
function addSystemDemand(){
	todisabled($("#sub"));
	var form = new FormData(document.getElementById("addForm"));
   	$.ajax({
         url:"${pageContext.request.contextPath}/SystemDemandAction_addSystemDemand.action",
         type:"post",
         data:form,
         processData:false,
         contentType:false,
         async : false,
         success:function(data){
             alert(data);
			 window.parent.chageDiv('none');
			 window.parent.reload();
         },error:function(){
        	 alert("添加失败");
         }
     });
	
}
</SCRIPT>
</body>
</html>