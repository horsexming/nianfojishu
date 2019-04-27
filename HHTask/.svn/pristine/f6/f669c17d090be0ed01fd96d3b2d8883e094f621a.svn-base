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
		<style type="text/css">
		
			a{
				cursor: hand;
			}
		</style>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					体系审核追踪管理
				</h3>
				<table class="table">

					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							审核方
						</td>
						<td align="center">
							审核类型
						</td>
						<td align="center">
							审核日期
						</td>
						<td align="center">
							负责部门
						</td>
						<td align="center">
							总负责人
						</td>
						<td align="center">
							第二负责人
						</td>
						<td align="center">
							完成日期
						</td>
						<td align="center">
							问题描述
						</td>
						<td align="center">
							状态
						</td>
						
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="setupCheckList" id="list" status="pageStatus">
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
							${list.auditParty}
						</td>
						<td align="center">
							${list.checkType}
						</td>
						<td align="center">
							${list.checkDate}
						</td>
						<td align="center">
							${list.department}
						</td>
						<td align="center">
							${list.uploadPerson}
						</td>
						<td align="center">
							${list.uploadPerson1}
						</td>
						<td align="center">
							${list.allTime}
						</td>
						<td align="center">
							${list.description}
						</td>
						<td align="center">
							${list.stauts}
						</td>
						<td colspan="2">
							<a onclick="addtrackRecord(${list.id},'${cpage}')">添加追踪记录</a>/
							<a onclick="findtrackRecord(${list.id},'${cpage}')">查看追踪记录</a>/
						</td>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font>/
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	function addtrackRecord(id, cpage) {
		window.location.href = "SetupCheckAction_toAddTrackRecord.action?setupCheck.id=" + id
			+ "&cpage=" + cpage;
}
		function findtrackRecord(id, cpage) {
		window.location.href = "SetupCheckAction_findtrackRecordByid.action?setupCheck.id=" + id
			+ "&cpage=" + cpage;
}

</script>
	</body>
</html>
