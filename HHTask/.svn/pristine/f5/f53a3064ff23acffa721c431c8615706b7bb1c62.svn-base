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
				<form action="ProjectTrackRecord_add.action" method="post">
					<input type="hidden" name="p.root.id" value="${p.root.id}" />
					<table class="table" style="width: 53%">
						<tr>
							<th colspan="4" >添加项目跟踪记录</th>
						</tr>
						<tr>
							<td align="right">事件:</td>
							<td> <input name="p.thing" /> </td>
							<td align="right">项目类型:</td>
							<td>
								<input type="radio" name="p.recordType" value="进行中" checked="checked"/>进行中
								<input type="radio" name="p.recordType" value="已完成"/>已完成
								<input type="radio" name="p.recordType" value="待审批"/>待审批
							</td>
						</tr>
						<tr>
							<td align="right">
								预算花费
							</td>
							<td colspan="3">
								<input name="p.budgetMoney" value="0" />
							</td>
						</tr>
						<tr>
							<td align="right">开始时间:</td>
							<td>
								<input name="p.startTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">结束时间:</td>
							<td>
								<input name="p.endTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>事件描述</th>
							<td colspan="3">
								<textarea name="p.description" rows="5" cols="40"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" />
								<input type="reset" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
