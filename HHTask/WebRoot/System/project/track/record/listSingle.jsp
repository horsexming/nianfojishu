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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<input type="hidden" name="p.id" value="${p.id}" />
				<table class="table" style="width: 53%">
					<tr>
						<th colspan="4" >项目跟踪记录</th>
					</tr>
					<tr>
						<th align="right">申请人:</th>
						<td> ${p.username}</td>
						<th align="right">事件:</th>
						<td> ${p.thing}</td>
					</tr>
					<tr>
						<th align="right">项目类型:</th>
						<td>
							${p.recordType}
						</td>
						<th align="right">
							预算花费:
						</th>
						<td>
							${p.budgetMoney}
						</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>
							${p.startTime}
						</td>
						<th align="right">结束时间:</th>
						<td>
							${p.endTime}
						</td>
					</tr>
					<tr>
						<th align="right">事件描述:</th>
						<td colspan="3">
							${p.description}
						</td>
					</tr>
					<s:if test="p.agree != null">
						<tr>
							<th colspan="4">审核部分</th>
						</tr>
						<tr>
							<th align="right">是否同意:</th>
							<td>
								<s:if test="p.agree">
									同意
								</s:if><s:else>
									驳回
								</s:else>
							</td>
							<th align="right">批准钱数:</th>
							<td>
								${p.approveMoney }
							</td>
						</tr>
						<tr>
							<th align="right">
								审核说明:
							</th>
							<td colspan="3">
								${p.about}
							</td>
						</tr>
					</s:if>
					
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
