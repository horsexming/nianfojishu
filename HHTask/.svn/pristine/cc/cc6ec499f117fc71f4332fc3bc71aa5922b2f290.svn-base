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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="orderManager_findAllEvaluators.action">
					<table class="table">
						<tr>
							<th>
								评审组别
							</th>
							<td>
								<input type="text" value="${evaluators.groups}"
									name="evaluators.groups" />
							</td>
							<th>
								评审人
							</th>
							<td id="userstd">
								<input type="text" value="${evaluators.userName}" name="evaluators.userName"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="添加" class="input" onclick="window.open('./System/oderCancel/evaluatores_add.jsp')"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							组别
						</th>
						<th>
							部门
						</th>
						<th>
							评审人
						</th>
						<th>
							评审人工号
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="evalList" id="pageEval" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageEval.groups}
						</td>
						<td>
							${pageEval.dept}
						</td>
						<td>
							${pageEval.userName}
						</td>
						<td>
							${pageEval.usersCode}
						</td>
						<td>
							<a href="orderManager_findEvaluatorsById.action?id=${pageEval.id}">修改</a>/
							<a href="orderManager_delEvaluators.action?evaluators.id=${pageEval.id}"
							onclick="return confirm('确定要删除吗？')">删除</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
</SCRIPT>
	</body>
</html>
