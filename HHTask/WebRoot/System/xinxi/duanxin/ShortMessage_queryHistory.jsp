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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="ShortMessage_queryHistory.action" method="post">
					<table>
						<tr>
							<td>
								部门：
								<input type="text" name="shortMessage.dept" />
							</td>
							<td>
								工号：<input type="text" name="shortMessage.code" />
							</td>
							<td>
								姓名：<input type="text" name="shortMessage.name" /><br />
							</td>
						</tr>
						<tr>
							<td>
								手机：<input type="text" name="shortMessage.phone" />
							</td>
							<td>
								日期：<input id="month" style="width: 155px" class="Wdate" type="text" name="shortMessage.sendDate" 	onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="center">
								<input type="submit" style="width: 70px" value="提交" >
							</td>
						</tr>
					</table>
				</form> 
			</div>
			
				<table class="table">
					<tr>
						<td>序号</td>
						<td>名称</td>
						<td>手机号</td>
						<td>工号</td>
						<td>部门</td>
						<td>发送时间</td>
					</tr>
					<s:iterator value="sendHistory" id="uh1" status="st">
						<tr>
							<td>${st.index+1}</td>
							<td>${uh1.name}</td>
							<td>${uh1.phone}</td>
							<td>${uh1.code}</td>
							<td>${uh1.dept}</td>
							<td>${uh1.sendDate}</td>
						</tr>
					</s:iterator>
				</table>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
