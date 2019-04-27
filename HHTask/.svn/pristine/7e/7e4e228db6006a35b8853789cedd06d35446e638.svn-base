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
			
			<div align="center">
				<h3>显示财务开票信息</h3>				
				<table style="width:98%;margin: 5 px">		
				<tr><td colspan="9" align="right">编号：<s:property value="taHk.hkNum" /><span style="width:50px;"></span></td></tr>		
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>序号</td>
						<td>
							客户
						</td>
						<td>
							零件号
						</td>
						<td>
							开票数量
						</td>						
						<td>单价</td>
						<td>金额（含税）</td>
						<td>单位</td>
						<td>
							发票号
						</td>
						
					</tr>
					<s:iterator value="hkset" status="hk" id="huikuan">
						<s:if test="#hk.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#hk.index+1" /></td>
						<td>
						<s:property	value="taHk.hkClientComp" />							
						<td>
						<s:property value="hkSellMarkId" />							
						</td>
						<td>
						<s:property value="hkSellCount" />	
						</td>
						
						<td>
						<s:property value="hkSellPrice" />	
						</td>
						<td>
						<s:property value="hkSellMoney" />	
						</td>
						<td>
						<s:property value="hkSellMoneyUnit" />	
						</td>
						<td>
							<s:property value="TaHkHkInvoice.hkInvoInvoNum" />	
						</td>						
						</tr>
					</s:iterator>					
					</table>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	
	</body>
















</html>
