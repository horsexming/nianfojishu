<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'log_caozuo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
			 <form action="WorkLogAction!updateWorkLog.action?pageStatus=${pageStatus}"
					method="post">
						<input id="workLogId" name="id" type="hidden">
						<table class="table">
							<tr>
								<th align="right">
									状态:
								</th>
								<td align="left">
									<input type="radio" name="workLog.logStatus" value="办理中" checked="checked"/>
									办理中
									<input type="radio" name="workLog.logStatus" value="已完成"
										 onclick="change('radio1','option','1')" id="radio1"/>
									已完成
								</td>
							</tr>
							<tr>
								<th align="right">
									进度:
								</th>
								<td>
									<select id="jindu" name="workLog.jindu" onmouseout="change('option1','radio','1')">
										<option value=0>0%</option>
										<option value=1>10%</option>
										<option value=2>20%</option>
										<option value=3>30%</option>
										<option value=4>40%</option>
										<option value=5>50%</option>
										<option value=6>60%</option>
										<option value=7>70%</option>
										<option value=8>80%</option>
										<option value=9>90%</option>
										<option value=10 id="option1">100%</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									备注:
								</th>
								<td align="left">
									<textarea rows="6" cols="80" name="workLog.remarks"></textarea>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" value="提交" class="input">
									<input type="reset" value="重置" class="input">
								</td>
							</tr>
						</table>
					</form>
  </body>
</html>
