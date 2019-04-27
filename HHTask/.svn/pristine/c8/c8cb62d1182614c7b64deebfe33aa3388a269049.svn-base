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
		
		</script>
	</head>
	<body>
<center>
				<form id="myForm" action="OsTemplate_addOsScope.action" method="post" enctype="multipart/form-data" onsubmit="return check();">
					<table class="table" style="width: 60%">
					<tr><th colspan="2" align="center">添加检查项 </th></tr>
						<tr>
					        <th align="right">检查内容</th>
							<td><input type="hidden" name="tt.id" value="${tt.id}"/><input name="tt.content" value="${tt.content}"/></td>
						</tr>
						<tr>
					        <th align="right">检查类型</th>
							<td><select name="tt.type" style="width:150px " >
									<option>${tt.type}</option>
									<option>手动填写</option>
									<option>OKorNo</option>
								</select></td>
						</tr>
						<tr>
					        <th align="right">质量特征</th>
							<td><input name="tt.zltz" value="${tt.zltz}"/></td>
						</tr>
						<tr>
					        <th align="right">检查方法</th>
							<td><input name="tt.jcff" value="${tt.jcff}"/>
							<input name="t.id" value="${param.tid}" type="hidden"/>
							
							</td>
						</tr>
						<tr>
						
						<th align="center" colspan="2">	<input type="submit" value="提交" class="input"></th></tr>
					</table>
					
				</form>
			</div>
			<br>
		</div>
		
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
					$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	})	
		
		</SCRIPT>
	</body>
</html>
