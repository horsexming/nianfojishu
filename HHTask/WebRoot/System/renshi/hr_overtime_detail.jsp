<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div class="container">
			<h2 align="center">加班明细查看</h2>
			<div class="row col-xs-12 table-responsive">
				<s:if test="tag!=null && tag=='backup' && fn:length(overtimeDetailList)>0">
					<table class="table">
						<caption>本次申请历史加班明细</caption>
						<tr>
							<th>序号</th>
							<th>加班类型</th>
							<s:if test="tag!=null && tag=='backup'">
								<th>旧的开始和结束时间</th>
							</s:if>
							<s:else>
							</s:else>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>休息时间</th>
							<th>加班时长</th>
						</tr>
						<s:iterator id="detail" value="overtimeDetailList2" status="ps">
							<tr>
								<td>${ps.index+1}</td>
								<td>${detail.type }</td>
								<s:if test="tag!=null && tag=='backup'">
									<td>
										${detail.oldStart}-${detail.oldEnd}
									</td>
								</s:if>
								<s:else>
								</s:else>
								<td>${detail.startTime }</td>
								<td>${detail.endTime }</td>
								<td>${detail.xiuxi }</td>
								<td>${detail.hour}小时${detail.minutes}分钟</td>
							</tr>
						</s:iterator>
					</s:if>
				<table class="table">
					<caption>有效的加班记录</caption>
					<tr>
						<th>序号</th>
							<th>加班类型</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>休息时间</th>
							<th>加班时长</th>
					</tr>
					<s:iterator id="detail" value="overtimeDetailList" status="ps">
						<s:if test="detail.status!=null && detail.status=='disable'">
							<tr style="color:rgb(144,144,144) ">
						</s:if>
						<s:else>
							<tr>
						</s:else>
							<td>${ps.index+1}</td>
							<td>${detail.type }</td>
							<td>${detail.startTime }</td>
							<td>${detail.endTime }</td>
							<td>${detail.xiuxi }</td>
							<td>${detail.hour}小时${detail.minutes}分钟</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
</html>
