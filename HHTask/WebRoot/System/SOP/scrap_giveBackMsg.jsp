<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'store_return.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"
		src="<%=basePath%>javascript/DatePicker/WdatePicker.js">
	</script>
  </head>
  
  <body>
  		 <hr/>
  		 <center>
  		 		<s:if test="flag == true">
  		 			<h3 style="color: red;">${vosc.msg}</h3>
  		 		</s:if>
  		 		<s:else>
  		 		 <caption align="top" style="color: red;">报废成功!</caption>
   				 <table align="center"  border="1" style="border-collapse: collapse;">
					<tr>
						<td>编&nbsp;&nbsp;号：</td><td>${scr.number }</td>
						<td>名&nbsp;&nbsp;称：</td><td>${scr.matetag }</td>
						<td>规&nbsp;&nbsp;格：：</td><td>${scr.format}</td>
					</tr>
					<tr>
						<td>责任人：</td><td>${scr.username }</td>
						<td>部&nbsp;&nbsp;门：</td><td>${scr.dept }</td>
						<td>日&nbsp;&nbsp;期：</td><td><s:date name="scr.badDate" format="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>数&nbsp;&nbsp;量：</td><td>${scr.amount}</td>
						<td>损失原因：</td><td>${scr.badView}</td>
						<td>损失意见：</td><td>${scr.more1}</td>
					</tr>
					<tr>
						<td colspan="5">备注：</td><td>${scr.more2 }</td>
					</tr>
					</table>
					</s:else>
			</center>
  </body>
</html>
