<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<center>
					<div id="printDiv" align="center" style="width: 100%;">
						<br />
						<br />
						<table>
							<tr>
								<th>
									<h2>
										车补油卡充值单
									</h2>
								</th>
							</tr>
						</table>

						
						<table class="table" style="width: 100%">
							
							<tr height="35px">
								
										<th align="center">
											工号
										</th>
										<th>
											姓名
										</th>
										<th>
											车牌号
										</th>
										<th>
											充值金额
										</th>
									
										<th>
											状态
										</th>
							<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="bxd">
								
										<tr align="center" height="35px">
											<td align="center">
												${bxd.code}
											</td>
											<td>
												${bxd.name}
											</td>
											<td>
												${bxd.platenumber}
											</td>
											<td>
												${bxd.chongzhiJine}
											</td>
											<td>
												${bxd.baoxiaoStatus}
											</td>											
										</tr>
									
							</s:iterator>
							</s:if>
							<tr>
							<td>汇总</td><td colspan="4"></td>
							</tr>
						</table>
					</div>
					<table class="table" style="width: 100%">
						<tr>
							<td align="center">
								<input style="width: 80px; font-size: 18px;"
									onclick="pagePrint('printDiv')" type="button" value="打印">
							</td>
						</tr>
					</table>
				</center>
			</div>

			<%@include file="/util/foot.jsp"%>
			
	</body>
</html>
