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
<body>
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
			<form action="customerClaimAction_analysis.action" enctype="multipart/form-data" method="post" onsubmit="return validate()">
				<table class="table">
					<tr>
					  <th>客户
					  </th>
					  <td>
					  <input type="hidden" name="tag" value="<s:property value="tag"/>">
					  <input type="hidden" name="customerClaim.id" value="<s:property value="customerClaim.id"/>">
					  <s:property value="customerClaim.otherCompany"/>
					  </td>
					  <th>索赔标题
					  </th>
					  <td><s:property value="customerClaim.title"/>
					  </td>
					</tr>
					<tr>
					 <th>索赔内容</th>
					 <td colspan="3">
					   <s:property value="customerClaim.context"/>
					 </td>
					</tr>
					<tr>
					    <th>分析方案
					    </th>
					    <td colspan="3">
					    <textarea rows="8" cols="80" name="customerClaim.analysisText"><s:property value="customerClaim.analysisText"/></textarea>
					    </td>
					</tr>
					<tr>
					 <th>上传文件
					 </th>
					 <td><input type="file" name="analysis" id="analysis"/><s:if test="customerClaim.analysisFile!=null"><FONT color="red">原有分析文件</FONT></s:if> </td>
				     
					</tr>
					<tr>
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
	function validate(){
		var file =document.getElementById("analysis");
    	if(file.value==""){
       	 	alert("上传文件不能为空！");
       	 	return false;
        }
		return true;
	}
	</script>
</body>
</html>
