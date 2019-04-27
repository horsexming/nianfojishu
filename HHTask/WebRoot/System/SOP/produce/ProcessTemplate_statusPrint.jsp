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
table {
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #030303; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 2px 2px 2px 2px; /* 设置边框状粗细：上 右 下 左 = 对应：1px 0 0 1px */
	//
	font-size: 13px;
}

table th,table td {
	border: solid #030303;
	border-width: 1px 1px 1px 1px;
	padding: 2px;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h2>
					${companyInfo.name}工序状态打印
				</h2>
				<s:if test="{list.size()>0}">
				<input style="width: 80px; font-size: 18px;" onclick="pagePrint('printDiv')"
					type="button" value="打印">
				<br />
				<br />
				
				<div id="printDiv" >
					<s:iterator value="list" status="se" id="process">
					<div style="width: 365px;height: 272;margin-left: 10px;text-align:center;">
					<table border="1"
						style="font-size: 13px; font-weight: bold; width: 360px; height: 270;margin-left: 5px;">

						<tr>
							<td style="padding-top: 0px; margin-left: 2px; height: 60px;" align="center">
								<img alt="honghu" widtn="64px;" height="55px;"
									src="<%=basePath%>${companyInfo.logoOKjpg}" />
							</td>
							<td colspan="3"
								style="font-size: 18px; font-weight: bold; center; vertical-align: middle;">
								${companyInfo.shortName} 工序状态卡
							</td>
						</tr>

						<tr>
							<td colspan="2" style="width: 120px;" align="center">
								零件号
							</td>
							<td colspan="2" style="width: 240px;">
								${process.procardTemplate.markId }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="width: 120px;" align="center">
								工序号
							</td>
							<td colspan="2" style="width: 240px;">
								${process.processNO }
							</td>
						</tr>
						<tr>
							<td colspan="2" style="width: 120px;" align="center">
								工序名
							</td>
							<td colspan="2" style="width: 240px;">
								${process.processName }
							</td>
						</tr>

					</table>
					
				</div>
				</s:iterator>
				</div>
				</s:if>
				<s:else>没有选择打印任务</s:else>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
	</body>
</html>
