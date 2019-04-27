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
		<style type="text/css">
a:visited {
	color: #FF3030;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="CircuitCustomize_findCCByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								流程名称:
							</th>
							<td>
								<input name="circuitCustomize.name" />
							</td>
							<th align="right">
								审批类型:
							</th>
							<td>
								<select name="circuitCustomize.auditType" style="width: 155px;">
									<option></option>
									<option value="oneBack">
										oneBack
									</option>
									<option value="lastBack">
										lastBack
									</option>
									<option value="oneAudit">
										oneAudit
									</option>
								</select>
							</td>
							<th align="right">
								添加人:
							</th>
							<td>
								<input name="circuitCustomize.addUserName" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="border-collapse: collapse;">
					<tr>
						<th colspan="10">
							流程管理
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							数据id
						</th>
						<th align="center">
							流程名称
						</th>
						<th align="center">
							说明
						</th>
						<th align="center">
							添加人
						</th>
						<th align="center">
							审批类型
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="circuitCustomizeList" id="pageCC"
						status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							${pageindex.index+1}
						</td>
						<td>
							${pageCC.id}
						</td>
						<td>
							${pageCC.name}
						</td>
						<td width="300px">
							${pageCC.more}
						</td>
						<td>
							${pageCC.addUserName}
						</td>
						<td>
							${pageCC.auditType}
						</td>
						<td>
							${pageCC.addDateTime}
						</td>
						<td>
							<a href="CircuitCustomize_findAuditNodeByCcId.action?id=${pageCC.id}">流程节点</a>
							<br />
							<a href="CircuitCustomize_findCirCus.action?id=${pageCC.id}">修改</a>/
							<a onclick="return window.confirm('确定要删除本流程吗?')"
								href="CircuitCustomize_delCirCus.action?id=${pageCC.id}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
