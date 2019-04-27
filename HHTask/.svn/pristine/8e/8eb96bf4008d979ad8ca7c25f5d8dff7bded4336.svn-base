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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function check() {
	var quxuanName = document.getElementsByName("quxuanName");
	var i = 0
	for (i = 0; i < quxuanName.length; i++) {
		document.getElementById("quxian" + i).checked = true;
	}
}
</script>
		<script type="text/javascript">
function check2() {
	var quxuanName = document.getElementsByName("quxuanName");
	var i = 0
	for (i = 0; i < quxuanName.length; i++) {
		document.getElementById("quxian" + i).checked = false;
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="FunctionAction!findFunctionById.action?id=${function.id}"
						style="color: #ffffff">添加功能</a>
				</div>
			</div>
			
			<div align="center">
				<form action="BonusAction!saveBonusPeople.action" method="post">
					<table>
						<tr>
							<th>
								选择时间
							</th>
							<td colspan="4">
								<input class="Wdate" type="text" name="todayDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="5">
								<font size="5">奖金分配前</font>
							</td>
						</tr>
						<tr>
							<th>
								选择
							</th>
							<th>
								成员工号
							</th>
							<th>
								成员卡号
							</th>
							<th>
								成员姓名
							</th>
							<th>
								班组
							</th>
						</tr>
						<s:iterator id="bo" value="list" status="stauts">
							<s:if test="#stauts.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								<input type="checkbox" name="quxuanName" value="${bo.id}"
									id="quxian<s:property value="#stauts.index" />"
									checked="checked" disabled="disabled">

								<input type="hidden" name="quxuanName" value="${bo.id}"
									id="quxian<s:property value="#stauts.index" />"
									checked="checked">

							</td>
							<td align="center">
								${bo.teammembersmembernumber}
							</td>
							<td align="center">
								${bo.teammemberscardnumber}
							</td>
							<td align="center">
								${bo.teammembersteamname}
							</td>
							<td align="center">
								${bo.teammembersteam}
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5" align="center">
								<input type="submit" value="下 一 步"
									style="height: 50px; width: 80px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
