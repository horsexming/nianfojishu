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
		<div id="bodyDiv" align="center" class="transDiv"
				onclick="chageDiv('none')">
			</div>
			<div id="contentDiv"
				style="position: absolute; z-index: 255; width: 900px; display: none;"
				align="center">
				<div id="closeDiv"
					style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
					<table style="width: 100%">
						<tr>
							<td>
								<span id="title">您正在修改货币类别</span>
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									id="closeTcDiv" height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
							hspace="0" vspace="0" frameborder="0" scrolling="yes"
							style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

					</div>
				</div>
			</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<form action="MoneyTypeAction_findMoneyType.action" method="post" >
				<b>货币名称:</b><input type="text" value="${mt.name}" name="mt.name"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<b>使用/禁用:</b><input type="text" value="${mt.status}" name="mt.status"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="查询" class="input"/>
			</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>货币名称</th>
						<th>货币符号</th>
						<th>使用/禁用</th>
						<th>操作</th>
					</tr>
					<s:iterator id="pageList" value="mtList"
								status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${pageList.name}
								</td>
								<td>
									${pageList.sign}
								</td>
								<td>
									${pageList.status}
								</td>
								<td>
									<a href="MoneyTypeAction_jinyong.action?id=${pageList.id}">禁用</a>/
									<a href="MoneyTypeAction_fanjinyong.action?id=${pageList.id}">恢复使用</a>/
									<a href="javascript:;" onclick="tanchu('${pageList.id}')">修改</a>/
									<a href="MoneyTypeAction_delMoneyType.action?mt.id=${pageList.id}">删除</a>
								</td>
								</tr>
							</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function tanchu(id){
		document.getElementById("xiugaiIframe").src = "MoneyTypeAction_findMoneyTypeById.action?id="+id;
		chageDiv('block');
		
}

</SCRIPT>
	</body>
</html>
