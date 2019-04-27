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
				<form action="yuanclAndWaigjAction_findAllListfmtr.action"
					method="POSt">
					<table class="table">
						<tr>
							<td align="right">
								件号:
							</td>
							<td>
								<input type="text" value="" name="fmtzr.markId" />
							</td>
							<td align="right">
								名称:
							</td>
							<td>
								<input type="text" value="" name="fmtzr.name" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input">
				</form>
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
							每公斤喷粉面积(调整前)
						</th>
						<th>
							每公斤喷粉面积(调整后)
						</th>
						<th>
							用量标准(调整前)
						</th>
						<th>
							用量标准(调整后)
						</th>
						<th>
							调整原因
						</th>
						<th>
							申请人
						</th>
						<th>
							申请时间
						</th>
						<th>
							批准人
						</th>
						<th>
							批准时间
						</th>
						<th>
							审批状态
						</th>
					</tr>
					<s:iterator id="pageList" value="fmtzrList" status="statussdf">
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
							${pageList.markId}
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.areakg0}
						</td>
						<td>
							${pageList.areakg1}
						</td>
						<td>
							${pageList.ylbz0}
						</td>
						<td>
							${pageList.ylbz1}
						</td>
						<td>
							${pageList.tzReason}
						</td>
						<td>
							${pageList.sqUserName}
						</td>
						<td>
							${pageList.sqTime}
						</td>
						<td>
							${pageList.pzUsreName}
						</td>
						<td>
							${pageList.pzTime}
						</td>
						<td>
							<s:if test="pageList.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">${pageList.epId}</a>	
							</s:if>
							<s:else>
								<a href="CircuitRunAction_findAduitPage.action?id=${pageList.dtd.epId}">${pageList.dtd.epStatus}</a>	
							</s:else>
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
