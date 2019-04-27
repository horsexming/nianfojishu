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
				<form action="CircuitRunAction_findCircuitRun.action" method="post"
					method="post">
					<input name="circuitRun.addUserId" value="${circuitRun.addUserId}"
						type="hidden" />
					<table class="table">
						<tr>
							<th align="right">
								流程名称:
							</th>
							<td>
								<input name="circuitRun.name" value="${circuitRun.name}" />
							</td>

							<th align="right">
								添加人:
							</th>
							<td>
								<input name="circuitRun.addUserName"
									value="${circuitRun.addUserName}" />
							</td>
							<th align="right">
								审批状态:
							</th>
							<td>
								<SELECT name="circuitRun.allStatus">
									<option>
										${circuitRun.allStatus}
									</option>
									<option></option>
									<option>
										未审批
									</option>
									<option>
										审批中
									</option>
									<option>
										同意
									</option>
									<option>
										打回
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input" />

							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th colspan="15">
							<s:if test="circuitRun.addUserId==0">
								<font style="font-size: 16px; font-weight: bolder;">我的待审批记录</font>
								<a
									href="CircuitRunAction_findCircuitRun.action?circuitRun.addUserId=1">
									查看我的历史审批</a>
							</s:if>
							<s:elseif test="circuitRun.addUserId==1">
								<font style="font-size: 16px; font-weight: bolder;">我的历史审批记录</font>
								<a
									href="CircuitRunAction_findCircuitRun.action?circuitRun.addUserId=0">
									查看我的待审批</a>
							</s:elseif>
							<s:else>
								审批记录查询
							</s:else>
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<s:if test="circuitRun.addUserId!=0&&circuitRun.addUserId!=1">
							<th align="center">
								数据id
							</th>
							<th align="center">
								审批类型
							</th>
							<th align="center">
								对应实体类名称
							</th>
							<th align="center">
								对应实体类状态
							</th>
							<th align="center">
								对应实体类
							</th>
							<th align="center">
								是否验证
							</th>
						</s:if>
						<th align="center">
							流程名称
						</th>
						<th align="center">
							审核动态
						</th>
						<th align="center">
							总状态
						</th>
						<th align="center">
							审核等级
						</th>
						<th align="center">
							添加人
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							备注
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="circuitRunList" id="pageCC" status="pageindex">
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
						<s:if test="circuitRun.addUserId!=0&&circuitRun.addUserId!=1">

							<td>
								${pageCC.id}
							</td>
							<td>
								${pageCC.auditType}
							</td>
							<td>
								${pageCC.entityName}
							</td>
							<td>
								${pageCC.entityStatusName}
							</td>
							<td>
								${pageCC.entityIdName}
							</td>
							<td>
								${pageCC.isVerification}
							</td>
						</s:if>
						<td align="left">
							${pageCC.name}
						</td>
						<td>
							${pageCC.auditStatus}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageCC.id}">
								${pageCC.allStatus}</a>
						</td>
						<td>
							${pageCC.auditLevel}
						</td>
						<td>
							${pageCC.addUserName}
						</td>
						<td>
							${pageCC.addDateTime}
						</td>
						<td>
							${pageCC.more}
						</td>
						<%--						<td>--%>
						<%--				<a href="circuitRun_updateCircuitRun.action?id=${pageCC.id}">修改</a>--%>
						<%--						</td>--%>

						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageCC.id}">
								<s:if test="circuitRun.addUserId==0">前往审批</s:if>
								<s:else>审批动态</s:else> </a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
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
