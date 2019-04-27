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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>操作工</th>
						<th>检查批次</th>
						<th>数量</th>
						<th>审核人</th>
						<th>是否合格</th>
						<th>巡检时间</th>
						<th>下次巡检</th>
						<th>检查内容</th>
					</tr>
					<s:if test="pageStatus=='xj'">
						<s:iterator value="ss" id="l" status="st">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<td>${st.index+1 }</td>
							<td>${l.czg }</td>
							<td>${l.jcpc }</td>
							<td>${l.quantity }</td>
							<td>${l.username }</td>
							<td>${l.verification}</td>
							<td>${l.nowDate }</td>
							<td>${l.nextDate }</td>
							<td>
								<s:iterator value="#l.recordScope" id="ll">
									<s:property value="#ll.title"/>  : <s:property value="#ll.content"/> <br/>
								</s:iterator>
							</td>
						</tr>
					</s:iterator>
					</s:if>
					<s:else>
					<s:iterator value="ss" id="l" status="st">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<td>${st.index+1 }</td>
							<td>${l.czg }</td>
							<td>${l.jcpc }</td>
							<td>${l.quantity }</td>
							<td>${l.username }</td>
							<td>${l.verification}</td>
							<td>${l.nowDate }</td>
							<td>${l.nextDate }</td>
							<td>
								<s:iterator value="#l.recordScope" id="ll">
									<s:property value="#ll.title"/>  : <s:property value="#ll.content"/> <br/>
								</s:iterator>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
