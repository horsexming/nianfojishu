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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							供料属性
						</th>
						<th>
							规格
						</th>
						<th>
							版本
						</th>
						<th>
							需求量
						</th>
						<th>
							损耗值
						</th>
						<th>
							采购量
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pageWgProcard" value="procardList"
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
							${pageWgProcard.markId}
						</td>
						<td>
							${pageWgProcard.proName}
						</td>
						<td>
							${pageWgProcard.kgliao}
						</td>
						<td>
							${pageWgProcard.specification}
						</td>
						<td>
							${pageWgProcard.banBenNumber}
						</td>
						<td>
							${pageWgProcard.filnalCount}
						</td>
						<td>
							${pageWgProcard.sunhao}
						</td>
						<td>
							${pageWgProcard.cgNumber}
						</td>
						<td>
							<a
								href="ProcardAction!findProcardByRunCard2.action?id=${pageWgProcard.id}&pageStatus=history&viewStatus=">查看明细</a>
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
