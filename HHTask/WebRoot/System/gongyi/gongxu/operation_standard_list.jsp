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
					<a target="_blank" href="processDataAction!getOperationStandardAddPage.action?operationStandard.processDataId=${processData.id}" style="color: #ffffff">添加</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" style="text-align: center;">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td align="center">序号</td>
						<td align="center">序号</td>
						<td align="center">规范</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator id="o" value="operationStandardList" status="st">
						<s:if test="#st.index%2==1" >
						<tr  align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">	
						</s:if>
						<s:else>
						<tr align="center" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">
						</s:else>
						
							<td>${st.index + 1}</td>
							<td>${o.numb}</td>
							<td>${o.content}</td>
							<td>
								<a target="_blank" href="processDataAction!getOperationStandardUpdatePage.action?operationStandard.id=${o.id}">修改</a>
								<a target="_blank" href="processDataAction!deleteOperationStandard.action?operationStandard.id=${o.id}&operationStandard.processDataId=${o.processDataId}">删除</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br/>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
