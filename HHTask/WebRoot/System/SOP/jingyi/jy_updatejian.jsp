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
			<div align="center">
			    <h3>检</h3>
			   </div>
			   <s:if test="processTemplateJY.progressStatus=='未分析'||processTemplateJY.progressStatus=='分析中'">
			   <form action="procardTemplateJYAction_updatejgljwr.action" method="post" onsubmit="return validateFrom();">
				<table>
					<tr>
						<th>
							改进方案：
						</th>
						<td>
						<input type="hidden" value="jian" name="pageStatus">
						<input type="hidden" value="<s:property value="processTemplateJY.id"/>" name="processTemplateJY.id">
							<textarea id="updateContext" name="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextJian"/></textarea>
						</td>
					</tr>
					<tr>
					 <td colspan="2" align="center">
					  <input type="submit" value="修改" class="input">
					 </td>
					</tr>
				</table>
				</form>
				</s:if>
				<s:else>
				<br/>
				改进方案：<textarea id="updateContext" name="updateContext" rows="5" cols="40"><s:property value="processTemplateJY.contextJian"/></textarea>
				</s:else>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function validateFrom(){
			 var updateContext= $("#updateContext").val();
			 if(updateContext==null||updateContext==""){
				 alert("请填写改进方案！");
				 return false;
			 }
		 }
		$(document).ready(function(){
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                          }
		  });
		</script>
	</body>
</html>
