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
				<h2>调查问卷模板查看</h2>
				<font color="red" size="5">${errorMessage}</font>
				<form action="QuestionTemplateAction_findqtList.action" method="post">
					<STRONG>调查问卷名称:</STRONG><input type="text" name="qt.name"/>
					<STRONG>添加人姓名:</STRONG><input type="text" name="qt.addusername"/>
					<input type="submit" value="查询" style="width: 75px;height: 35px;"/>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>序号</th>
						<th>调查问卷名称</th>
						<th>添加人姓名</th>
						<th>添加人部门</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
					<s:iterator value="qtList" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>${pageList.name}</td>
						<td>${pageList.addusername}</td>
						<td>${pageList.adddept}</td>
						<td>${pageList.addtime}</td>
						<td>
							<a href="QuestionnairePersonAction_initadd.action?id=${pageList.id}">调查</a>/
							<a href="QuestionTemplateAction_findqeListbyid.action?id=${pageList.id}&status=mingxi">明细</a>/
							<a href="QuestionTemplateAction_findqeListbyid.action?id=${pageList.id}&status=update">修改</a>/
							<a href="QuestionTemplateAction_delqt.action?cpage=${cpage}&qt.id=${pageList.id}">删除</a>
						</td>
					</s:iterator>
					<tr>

							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>