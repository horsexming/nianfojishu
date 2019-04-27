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
				<h2>件号:<font color="red">${procard.markId}</font> 名称:<font color="red">${procard.proName}</font>所关联的包工包料外购件信息</h2>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							 序号
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							规格
						</th>
					</tr>
					<s:iterator value="list" id="pageList" status="statussdf">
						<s:if test="#statussdf.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff">已关联的外购件<br /> </font>
									</th>
								</tr>
							</s:if>
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
								<td>${pageList.markId}</td>
								<td>${pageList.proName}</td>
								<td>${pageList.specification}</td>
					</s:iterator>
					<s:iterator value="procardList" id="pageList0" status="statussdf0">
						<s:if test="#statussdf0.first">
								<tr bgcolor="green">
									<th colspan="16" align="center">
										<font color="#ffffff">未关联的外购件<br /> </font>
									</th>
								</tr>
							</s:if>
						<s:if test="#statussdf0.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf0.index+1" />
								</td>
								<td>${pageList0.markId}</td>
								<td>${pageList0.proName}</td>
								<td>${pageList0.specification}</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
