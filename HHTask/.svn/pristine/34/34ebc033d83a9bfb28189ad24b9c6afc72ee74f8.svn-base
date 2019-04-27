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
				<form action="" method="POST" >
					<table class="table">
						<tr>
							<th>
								总成件号
							</th>
							<td>
								<input type="text" value="${csblorder.rootMarkId}" name="csblorder.rootMarkId"/>
							</td>
							<th>
								业务件号
							</th>
							<td>
								<input type="text" value="${csblorder.ywMarkId}" name="csblorder.ywMarkId"/>
							</td>
						</tr>
						<tr>
							<th>
								内部订单号
							</th>
							<td>
								<input type="text" value="${csblorder.orderNumber}" name="csblorder.orderNumber"/>
							</td>
							<th>
								申请人
							</th>
							<td>
								<input type="text" value="${csblorder.sqUsersName}" name="csblorder.sqUsersName"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							内部订单号
						</th>
						<th>
							业务件号
						</th>
						<th>
						 	总成件号
						</th>
						<th>
							总成批次
						</th>
						<th>
							订单数量
						</th>
						<th>
							补料总额
						</th>
						<th>
							申请部门
						</th>
						<th>
							申请人
						</th>
						<th>
							申请日期
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pagecsblOrder" value="csblorderList"
								status="statussdf">
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
								<td>${pagecsblOrder.orderNumber}</td>
								<td>${pagecsblOrder.ywMarkId}</td>
								<td>${pagecsblOrder.rootMarkId}</td>
								<td>${pagecsblOrder.rootSelfCard}</td>
								<td>${pagecsblOrder.orderCount}</td>
								<td>${pagecsblOrder.sumPrice}</td>
								<td>${pagecsblOrder.sqdept}</td>
								<td>${pagecsblOrder.sqUsersName}</td>
								<td>${pagecsblOrder.sqdate}</td>
								<td>
									<a href="CircuitRunAction_findAduitPage.action?id=${pagecsblOrder.epId}">${pagecsblOrder.epStatus}</a>
								<td>
									<a href="ProcardAction!findCsblListById.action?id=${pagecsblOrder.id}">明细</a>/
									<a href="ProcardAction!findCsblListById.action?id=${pagecsblOrder.id}&pageStatus=print">打印</a>
									<s:if test="#pagecsblOrder.epStatus == '未审批' || #pagecsblOrder.epStatus == '打回'
									 || #pagecsblOrder.epId == null ">
										/<a href="ProcardAction!delCsblOrder.action?csblorder.id=${pagecsblOrder.id}" onclick="return confirm('确定要删除吗？')" >删除</a>
									</s:if>
									
									
								</td>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
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
<SCRIPT type="text/javascript">


</SCRIPT>
	</body>
</html>
