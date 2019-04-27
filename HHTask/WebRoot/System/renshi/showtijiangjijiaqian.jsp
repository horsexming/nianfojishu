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
				<form action="TijingpriceAction!conditionAllBefore.action"
					method="post">
					<table border="1" style="width: 100%" align="center">
						<tr>
							<th colspan="2">
								<font size="5">提奖计价表前</font>
							</th>
						</tr>
						<tr>
							<th>
								选择日期
							</th>
							<td>
								起
								<input class="Wdate" type="text" name="setDate" id="kaishidate"
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
