<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="gongneng">
			<table class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
						序号
					</td>
					<td align="center">
						一体机编号
					</td>
					<td align="center">
						IP
					</td>
					<td align="center">
						位置
					</td>
					<td align="center">
						添加时间
					</td>
					<td align="center" colspan="2">
						操作类型
					</td>
				</tr>
				<s:iterator value="machineList" id="samples" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							style="height: 25px;" onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td align="center">
						${samples.omNum}
					</td>
					<td align="center">
						${samples.ipAddress}
					</td>
					<td align="center">
						${samples.omAddress}
					</td>
					<td align="center">
						${samples.addTime}
					</td>
					<td align="center" colspan="2">
						<a
							href="OneMachineAction_toupdate.action?machine.id=${samples.id}">修改</a>
						<a onclick="return window.confirm('您将删除数据，是否继续?')"
							href="OneMachineAction_delete.action?id=${samples.id}">/删除</a>
							<a
							href="OneMachineAction_findMachineById.action?id=${samples.id}">/绑定工位</a>
							<a
							href="OneMachineAction_findOneLightById.action?id=${samples.id}">/绑定灯号</a>
					</td>
				</s:iterator>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="6" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
					</s:if>
					<s:else>
						<td colspan="6" align="center" style="color: red">
							${errorMessage}
						</td>
					</s:else>
				</tr>
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
