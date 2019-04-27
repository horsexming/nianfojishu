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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
			</div>

			<div align="center">
				<table align="center">
					<tr>
						<th colspan="7">
							<font size="5">奖金分配明细</font> &nbsp;&nbsp;
							<a href="BonusmoneyAction.action?pageStatus=${pageStatus}">返回</a>
						</th>
					</tr>
					<tr>
						<th>
							班组
						</th>
						<th>
							成员姓名
						</th>
						<th>
							成员工号
						</th>
						<th>
							成员卡号
						</th>
						<th>
							成员奖金
						</th>
						<th>
							试制奖金
						</th>
						<th>
							成员加班费饭贴
						</th>
						<th>
							月份
						</th>
					</tr>
					<s:iterator id="bo" value="bonuslist" status="stauts">
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
							${bo.bonusteam}
						</td>
						<td align="center">
							${bo.bonusteamname}
						</td>
						<td align="center">
							${bo.bonusmembernumber}
						</td>
						<td align="center">
							${bo.bonuscardnumber}
						</td>
						<td align="center">
							${bo.bonusmembermoney}
						</td>
						<td align="center">
							${bo.shizhiMoney}
						</td>
						<td align="center">
							${bo.bonusovertimemealmoney}
						</td>
						<td align="center">
							${bo.bonusdata}
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
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
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
