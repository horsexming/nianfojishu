<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="printImg">
				
				<table style="width:98%;margin: 5 px">
				<tr>
				<td><img src="${companyInfo.logoOKjpg}" height="80px" width="120px"></td>
				<td colspan="5" style="width:100%;text-align:center;font-size:25;font-weight:bold;">	开票通知单</td>
				<tr>
						<td colspan="6" align="right">
							编号：
							<s:property value="taHk.hkNum" /><span style="width:30px"></span>

						</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>
							客户
						</td>
						<td>
							零件号
						</td>
						<td>
							开票数量
						</td>
						<td>
							送货单号
						</td>
						<td>
							订单号
						</td>
						<td>
							备注
						</td>
					</tr>
					<s:iterator value="listHkSellSta" status="hk" id="huikuan">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="hkSellCumpanyName" />
						<td>
							<s:property value="hkSellMarkId" />
						</td>
						<td>
							<s:property value="hkSellCount" />
						</td>
						<td>
							<s:property value="hkSellSendId" />
						</td>
						<td>
							<s:property value="hkSellOrderId" />
						</td>
						<td>
							<s:property value="hkSellMore" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="6">
							&nbsp;
						</td>
					</tr>
					<tr align="center">
						<td>
							客户公司名称：
						</td>
						<td>
							<s:property value="taHk.hkClientComp" />
						</td>
						<td>
							客户负责人：
						</td>
						<td>
							<s:property value="taHk.hkClientName" />
						</td>
						<td>
							申请开票时间：
						</td>
						<td>
							<s:property value="taHk.hkAppPayDate" />
						</td>
					</tr>



					<tr>
						<td colspan="6" align="right">
							
							<span style="width: 200px;">财务</span> 
							<span style="width: 200px;">物流</span>
							<span style="width: 100px;"></span>
						</td>
					</tr>
					<tr><td colspan="6">&nbsp;</td></tr>
				</table>
			</div>
			<input type="button" onclick="pagePrint('printImg')" value="打印" />
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>
















</html>
