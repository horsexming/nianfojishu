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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>BOM版本升级明细</h2>
			</div>
		</div>
		<div  align="center">
		<div align="center">
		<font  style="font-weight: bolder;" > 申请人:</font>${bbAply.applicantName}&nbsp;&nbsp;<font  style="font-weight: bolder;" >申请时间:</font>${bbAply.applyTime}&nbsp;&nbsp;<font  style="font-weight: bolder;" >状态:</font>${bbAply.status}&nbsp;&nbsp;
		</div>
		<div align="center">
		 ${bbAply.remark}
		 </div>
		</div>
		<hr/>
		<div>
		 <table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								件号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								原版本号
							</th>
							<th align="center">
								新版本号
							</th>
						</tr>
						<tr>
							<td align="center">
								${bbAply.markId}
							</td>
							<td align="center">
								${bbAply.proName}
							</td>
							<td align="center">
								${bbAply.banbenNumber}
							</td>
							<td align="center">
								${bbAply.newBanbenNumber}
							</td>
							</tr>
					</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
