<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="ProcardAction!jisunLpPeoPleMoney.action" method="post">
					<table class="table">
						<tr>
							<th colspan="2">
								<font size="5">批产奖金自动计算系统</font>
							</th>
						</tr>
						<tr>
							<th>
								选择日期
							</th>
							<td>
								起
								<input class="Wdate" type="text" name="startDate"
									id="kaishidate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								&nbsp;&nbsp;&nbsp; 终

								<input class="Wdate" type="text" name="endDate" id="enddate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								&nbsp;&nbsp;&nbsp;
								<input type="submit" value="确   定" />
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
				<table align="center" width="100%" border="0"
					style="border-collapse: collapse;">
					<tr>
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							公司名称
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="s" value="zongsunlist" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#stauts.index+1" />
						</td>
						<td>
							${s[0]}
						</td>
						<td>
							${s[1]}
						</td>
						<td>
							<A
								href="${pageContext.request.contextPath}/System/renshi/addPrice.jsp?goodjh=${s[0]}&setDate=${setDate}&endDate=${endDate}">添加单件计价</A>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
