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
	<center>
			   <form action="zhaobiaoAction!updatezhaobiao.action" method="post"  theme="simple">
			   <input type="hidden" name="zhaobiao.numbers" value="${zhaobiao.numbers}">
			   <table class="table" style="width: 60%"><tr><th colspan="2" align="center">修改招标</th></tr>
				    <tr><th align="right">公司简称</th><td>
				    			<s:hidden id="zhaobiao.id" name="zhaobiao.id" />
								<s:hidden id="zhaobiao.faburen" name="zhaobiao.faburen" />	
				    			<s:hidden id="zhaobiao.fabushijian" name="zhaobiao.fabushijian" />	
				    			<s:hidden id="zhaobiao.status" name="zhaobiao.status" />	
				    			
				                <s:textfield id="zhaobiao.title" name="zhaobiao.title" /></td> </tr>   
				    <tr><th align="right">开始时间：</th><td><s:textfield id="zhaobiao.moban" name="zhaobiao.moban"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td> </tr>   
				    
				    <tr><th align="right">结束时间：</th><td><s:textfield id="zhaobiao.kongxian" name="zhaobiao.kongxian" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  /></td></tr>
				    <tr><th align="right">招标负责人：</th><td><s:textfield id="zhaobiao.fuze" name="zhaobiao.fuze" /></td></tr>
				    <tr><th align="right">负责人电话：</th><td><s:textfield id="zhaobiao.phone" name="zhaobiao.phone" /></td></tr>
				    <tr><th align="right">招商简介：</th><td ><s:textfield id="zhaobiao.loc" name="zhaobiao.loc" /></td></tr>  
				    
					 <tr><td align="right"><s:submit value="保存" cssClass="input"/></td>
					      <td><input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
			   </table>
		</form>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	 $(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
	</SCRIPT>

</html>
