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
								<span id="title">您正在对不合格品缺陷类型进行操作</span>
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
			<form action="ChartNOSQAction_findAllgzType.action" method="post">
				<p>
					<b>
						规则类型:
					</b>
					<input type="text" value="${gzType.type}" name="gzType.type"/>
					&nbsp;&nbsp;
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="tanchu()"/>
				</p>
			</form>
				<table class="table" >
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>规则类型</th>
						<th>组别</th>
						<th>操作</th>
					</tr>
					<s:iterator value="cgtList" id="pageList" status="">
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
								<td>${pageList.type}</td>
								<td>${pageList.groups}</td>
								<td>
									<a href="ChartNOSQAction_delgzType.action?gzType.id=${pageList.id}&cpage=${cpage}" onclick="return confirm('确定要删除吗?')">删除</a>
								</td>
							</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
function tanchu(num) {
		document.getElementById("xiugaiIframe").src = "<%=basePath%>/System/ChartNo/gzType_add.jsp";
	chageDiv('block')
}
</script>
	</body>
</html>
