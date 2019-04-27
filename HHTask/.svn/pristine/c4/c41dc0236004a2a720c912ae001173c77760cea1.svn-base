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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table">
					<tr>
						<th colspan="7">
							项目核算时间进度
						</th>
					</tr>
					<tr>
						<th rowspan="2">
							类别名称
						</th>
						<th colspan="4">
							指派信息
						</th>
						<th rowspan="2">
							完成时间
						</th>
						<th rowspan="2">
							超出时间
						</th>
					</tr>
					<tr>
						<th>
							部门
						</th>
						<th>
							人员
						</th>
						<th>
							短信
						</th>
						<th>
							截止时间
						</th>
					</tr>
					<s:iterator value="list" id="pageProTime" status="pageSt">
						<tr style="height: 25px;">
							<th>
								<input name="projectTime.id" value="${pageProTime.id}"
									type="hidden" />
								${pageProTime.className}:
							</th>
							<td align="center">
								${pageProTime.dept}
							</td>
							<td align="center">
								${pageProTime.userName}
							</td>
							<td align="center">
								${pageProTime.isSendSms}
							</td>
							<td align="center">
								${pageProTime.provisionTime}
							</td>
							<td align="center">
								${pageProTime.realTime}
							</td>
							<td align="center">
								${pageProTime.exceedTime}
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
