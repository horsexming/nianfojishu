<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@include file="/util/sonHead.jsp"%>

  </head>
  
  <body>
  	
  	<center>
  	<%@include file="/util/sonTop.jsp"%>
   		<form action="jimileixing_updateJiMi.action" method="POST">
   			<input type="hidden" name="jimileixing.id" value="${jimileixing.id}"/>
   			<input type="text" name="jimileixing.type" value="${jimileixing.type}"/>
   			<input type="submit" value="修改"/>
   			<input type="reset" value="重置">
   		</form>
   		<input type="hidden" id="rebeack" value='${successMessage}'/>
   	<%@include file="/util/foot.jsp"%>
   	</center>	
  </body>
  <script type="text/javascript">
	$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</script>
</html>
