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
				<h1>
					月份：${month}
				</h1>
				<br>
				<table class="table">
					<tr>
						<td align="center">
							序号
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							试制奖金
						</td>
					</tr>
					<s:iterator value="list" id="pageObj" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							${pageObj[1].code}
						</td>
						<td align="center">
							${pageObj[1].dept}
						</td>
						<td align="center">
							${pageObj[1].name}
						</td>
						<td align="center">
							${pageObj[2]}
						</td>
						</tr>
					</s:iterator>
					<s:if test="list.size()>0">
				<tr><td colspan="5" align="center"><input type="button" value="生成奖金分配" onclick="addShiZhiBomus()">
					</td>
				</tr>
					</s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function addShiZhiBomus(){
			window.location.href="proTryMakeScoreAction_addShiZhiBomus.action?month=${month}";
		}
		</SCRIPT>
	</body>
</html>
