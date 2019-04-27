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
				<form action="huikuanAction!addSendNum.action" method="post"
					target="main" enctype="multipart/form-data">
					<table width="900px">
						
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
							<td>
								备注
							</td>
						</tr>
							<s:iterator value="listSelect" status="hk" id="huikuan">
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
								<input name="hkset[<s:property value='#hk.index' />].hkSellCumpanyName" 
									value="${huikuan[2]}" />
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellMarkId" readonly="readonly"
									value="${huikuan[1]}" />
							</td>
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellCount" readonly="readonly"
									value="${huikuan[0]}" />
							</td>							
							<td>
								<input name="hkset[<s:property value='#hk.index' />].hkSellMore" />
							</td>
							</tr>
						</s:iterator>	
						<tr><td colspan="6">送货单号：<input type="text" name="commStr" /></td></tr>						
						<tr>						
							<td colspan="6" align="center">
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>

</html>
