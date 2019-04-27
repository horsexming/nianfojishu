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
				<form action="ProcardAction!findUMMAll.action" method="post">
					<input class="Wdate" type="text" name="weekds"
						onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr>
						<th colspan="15">
							${weekds}的生产奖金汇总对比
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							批次总节拍
							<br />
							(秒)
						</th>
						<th align="center">
							奖金总额
							<br />
							(元)
						</th>
						<th align="center">
							实际单件节拍
							<br />
							(秒)
						</th>
						<th align="center">
							单件奖金
							<br />
							(元)
						</th>
						<th align="center">
							批次标准累计节拍
							<br />
							(秒)
						</th>
						<th align="center">
							批次累计奖金
							<br />
							(元)
						</th>
						<th align="center">
							单件标准节拍
							<br />
							(秒)
						</th>
						<th align="center">
							单件奖金
							<br />
							(元)
						</th>
						<th align="center">
							标准-生产
						</th>
					</tr>
					<s:iterator value="list" id="pageUmD" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageUmD.markid}
						</td>
						<td>
							${pageUmD.selfCard}
						</td>
						<td>
							${pageUmD.scNumber}
						</td>
						<td>
							${pageUmD.scJie}
						</td>
						<td>
							${pageUmD.scje}
						</td>
						<td>
							${pageUmD.scdjJie}
						</td>
						<td>
							${pageUmD.scdjje}
						</td>
						<td>
							${pageUmD.bzsumJie}
						</td>
						<td>
							${pageUmD.bzsumje}
						</td>
						<td>
							${pageUmD.sumJie}
						</td>
						<td>
							${pageUmD.bzje}
						</td>
						<td>
							${pageUmD.cyNumber}
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
