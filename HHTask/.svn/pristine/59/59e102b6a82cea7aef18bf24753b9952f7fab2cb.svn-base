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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					应付明细信息
				</h3>
					<table class="table">
						<tr style="width: 100%">
							<th>
								应付类型：
							</th>
							<th align="left">
								${corePayable.subjectItem}
							</th>
							<th>
								收款单位：
							</th>
							<td align="left">
								${corePayable.shoukuandanwei}
							</td>
						</tr>
						<tr>
							<th>
								账期开始日期：
							</th>
							<td align="left">
								${corePayable.zhangqiStartDate}
							</td>
							<th>
								账期结束日期：
							</th>
							<td align="left">
								${corePayable.zhangqiEndDate}
							</td>
						</tr>
						<tr>
							<th>
								附件编号：
							</th>
							<td align="left">
								${corePayable.hetongbianhao}
							</td>
							<th>
								附件：
							</th>
							<td align="left">
<%--								<a href="<%=path%>/upload/file/feiZY/${corePayable.fujian}">查看附件</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/feiZY/${corePayable.fujian}">查看附件</a>
							</td>
						</tr>
						<tr>
							<th>
								应付付款金额：
							</th>
							<td align="left">
								${corePayable.yingfukuanJIne}
							</td>
							<th>
								实付款金额：
							</th>
							<td align="left">
								${corePayable.realfukuanJIne}
							</td>
						</tr>
						<tr>
							<th>
								负责人：
							</th>
							<td align="left">
								${corePayable.fuzeren}
							</td>
							<th>
								付款日期：
							</th>
							<td align="left">
								${corePayable.fukuaiDate}
							</td>
						</tr>
						<tr>
							<th>
								截止付款日期：
							</th>
							<td align="left">
								<font color="red">${corePayable.lateDate}</font>
							</td>
							<th colspan="2">
							</th>
						</tr>
						<tr>
							<th>
								摘要：
							</th>
							<td align="left" colspan="3">
								${corePayable.zhaiyao}
							</td>
						</tr>
					</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
