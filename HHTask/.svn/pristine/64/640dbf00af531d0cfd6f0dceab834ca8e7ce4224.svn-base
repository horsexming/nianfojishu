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
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							资金使用科目
						</th>
						<th>
							资金使用科目明细
						</th>
						<th>
							金额
						</th>
						<th>
							申请部门
						</th>
						<th>
							计划月份
						</th>
						<th>
							件号
						</th>
						<th>
							零件名称
						</th>
						<th>
							工序号
						</th>
						<th>
							工序名
						</th>
					</tr>
					<s:iterator id="pagelist" value="fundDetailedList"
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
						<td>${pagelist.zjStyle}</td>
						<td>${pagelist.zjStyleMx}</td>
						<td>${pagelist.voucherMoney}</td>
						<td>${pagelist.budgetDept}</td>
						<td>${pagelist.planMonth}</td>
						<td>${pagelist.goodsStoreMarkId}</td>
						<td>${pagelist.partName}</td>
						<td>${pagelist.processesNo}</td>
						<td>${pagelist.processesName}</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
