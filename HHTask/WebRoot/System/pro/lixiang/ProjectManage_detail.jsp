<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<body style="background: url('');">
		<div align="center" id="printDiv">
			<table class="table" style="width: 100%; line-height: 35px;">
				<tr>
					<th colspan="4">
						<h2 style="margin: 0px; padding: 0px;">
							项目立项表
						</h2>
						<div align="right"
							style="margin: 0px; padding: 0px; padding-right: 30px;">
							编号: ${projectManage.projectNum}
						</div>
					</th>
				</tr>
				<tr>
					<th align="right">项目类别</th>
					<td align="left">
						${projectManage.proType }
					</td>
				</tr>
				<tr>
					<th align="right">
						项目名称
					</th>
					<td align="left">
						${projectManage.projectName}
					</td>
					<th align="right">
						客户
					</th>
					<td align="left">
						${projectManage.client}
					</td>
				</tr>
				<tr>
					<th align="right">
						填报人
					</th>
					<td align="left">
						${projectManage.userName}
					</td>
					<th align="right">
						预估金额
					</th>
					<td align="left">
						<fmt:formatNumber pattern="#.##" minFractionDigits="2">${projectManage.yuMoney}</fmt:formatNumber>元
					</td>
				</tr>
				<tr>
					<th align="right">
						项目内容
					</th>
					<td align="left" colspan="3" style="width: 85%; padding: 10px;">
						${projectManage.content}
					</td>
				</tr>
				<tr>
					<th align="right">
						审核意见
					</th>
					<td align="left">
						${projectManage.aduitStatus}
					</td>
					<th align="right">
						填报时间
					</th>
					<td align="left">
						${projectManage.dateTime}
					</td>
				</tr>
				<tr>
					<td align="right" colspan="4">
						<b>查看附件 </b> <br/>
<%--						<a href="DownAction.action?fileName=${projectManage.fileName}&directory=/upload/file/project/">查看附件</a>--%>
					<s:iterator value="projectManage.projectWenJianSet" id="pagewenjian">
						<a href="FileViewAction.action?FilePath=/upload/file/project/${pagewenjian.fileName}">${pagewenjian.otherName }</a><br>
					</s:iterator>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<div align="center">
			<s:if test='projectManage.aduitStatus=="同意"'>
				<input type="button" value="打印" class="input"
					onclick="pagePrint('printDiv')" />
			</s:if>
		</div>
	</body>
</html>
