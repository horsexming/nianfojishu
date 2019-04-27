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
				<form action="ProcardAction!findUMMoneyForJJ.action" method="post">
					<table>
						<tr>
							<th>
								生产奖金申请
							</th>
						</tr>
						<tr>
							<th>
								选择月份
							</th>
							<td>
								<input class="Wdate" type="text" name="umm.month"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"
									value="${umm.month}">
							</td>
							<td>
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>

				<form action="BonusmoneyAction!lpToFp.action">
					<table class="table">
						<tr>
							<th colspan="10">
								月度生产奖金分配查询
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								选择
							</th>
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								奖金
							</th>
							<th align="center">
								在职状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageUmm" status="pageStatus">
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
								<input type="checkbox" name="ids" value="${pageUmm.id}" checked="checked">
							</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageUmm.code}
							</td>
							<td>
								${pageUmm.username}
							</td>
							<td>
								${pageUmm.dept}
							</td>
							<td>
								${pageUmm.month}
							</td>
							<td>
								${pageUmm.money}
							</td>
							<td>
								${pageUmm.sqstatus}
							</td>
							<td>
								<a
									href="ProcardAction!findUserMoneyDetailById.action?id=${pageUmm.id}">明细</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="10" align="center">
								<input type="submit" value="申请" class="input">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
