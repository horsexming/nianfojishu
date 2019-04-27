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
				<table class="table">
					<tr>
						<th colspan="6">
							添加问题
						</th>
					</tr>
					<tr>
					<th>问题标题</th>
					<td> ${question.title}</td>
					<th>区域类型</th>
					<td>${question.type}</td>
					</tr>
					<tr>
					<th>问题详情
					 </th>
					 <td colspan="3">
					 	${question.detail}
					 </td>
					</tr>
					<tr>
					<th>回答
					 </th>
					 <td colspan="3">
					 <div style="position: relative;left: 0px;top: 0px;">
					<p>
						${answer.answer}	
					</p>
					<br/>
					<br/>
					
					<p style="color:gray;font-size:13px;" align="right">${answer.person}--${answer.time}</p>
					</div>
					 </td>
					</tr>
					</table>
					<br/>
				 <input type="button" onclick="findAll(${question.id});" value="返回" style="width: 60px; height: 30px;" />
					
				
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	function findAll(id) {
	window.location.href ="QuestionAction_findAnswer.action?questionId="+id;
}
	</SCRIPT>
</body>
</html>
