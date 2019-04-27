<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
					<div align="left">
						<h3>共有<s:property value="list.size()"/>人充值成功!</h3>
						<s:if test="deptID>0">
							<h3>共有${deptID}人已经充值过,无须重复充值!</h3>
						</s:if>
						<s:if test="successMessage!=null">
							<h3>充值失败人员：</h3>
							${successMessage}
						</s:if>
					</div>
					<h3>充值结果</h3>
				<table>
					<tr>
						<td width="150">序号</td>
						<td width="150">姓名</td>
						<td width="150">部门</td>
						<td width="150">充值份数</td>
						<td width="200">充值时间</td>
					</tr>
					<c:forEach var="s" items="${list }" varStatus="i">
								<tr>
									<td width="150px;">${i.index+1}</td>
									<td width="150px;"><label>${s.name }</label></td>
									<td width="150px;"><label>${s.deptName }</label></td>
									<td width="150px;"><label>${s.copies }</label></td>
									<td width="200px;"><label>${s.supplyDate }</label></td>
								</tr>
					</c:forEach>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
