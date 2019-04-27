<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
				</div>
				
				<div align="center">
					<table align="center">
						<tr>
							<th colspan="6">
								<font size="5">查看客户详细信息</font> &nbsp;&nbsp;
								<a href="ClientManagementAction!findAll.action">返回</a>
							</th>
						</tr>
						<tr>
							<th>
								客户姓名
							</th>
							<td>
								${clientManagement.clientname}
							</td>
							<th>
								所在部门
							</th>
							<td>
								${clientManagement.clientdept}
							</td>
							<th>
								职位
							</th>
							<td>
								${clientManagement.clientposition}
							</td>
						</tr>
						<tr>
							<th>
								手机号码
							</th>
							<td>
								${clientManagement.clientmobilenumber}
							</td>
							<th>
								电话号码
							</th>
							<td>
								${clientManagement.clientphonenumber}
							</td>
							<th>
								公司名称
							</th>
							<td>
								${clientManagement.clientcompanyname}
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${clientManagement.clientsex}
							</td>
							<th>
								身份证号码
							</th>
							<td colspan="3">
								${clientManagement.clientcardnumber}
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="5" style="width: 250px; height: 80px;">
								${clientManagement.clientremarks}
							</td>
						</tr>
					</table>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
