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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	</head>
	<body>
		<table class="table">
			<tr align="center">
				<td colspan="8" style="font-size: 20px;">
					可靠性测试申请
				</td>
			</tr>
			
			<tr>
				<th align="right">申请单流水号:</th>
				<td>${sheet.number }</td>
				<th align="right">申请单位:</th>
				<td>${sheet.company }</td>
				<th align="right">申请日期:</th>
				<td>${sheet.addTime }</td>
				<th align="right">申请人:</th>
				<td>${sheet.addUserName }</td>
			</tr>
			<tr>
				<th align="right">物料类别:</th>
				<td>${sheet.materialCategory }</td>
				<th align="right">物料来源:</th>
				<td>${sheet.materialResource }</td>
				<th align="right">样品编号:</th>
				<td colspan="3">${sheet.specimenNum }</td>
			</tr>
			<tr>
				<th align="right">机柜名称:</th>
				<td>${sheet.jGname }</td>
				<th align="right">件号:</th>
				<td>${sheet.markId }</td>
				<th align="right">检验批次号:</th>
				<td colspan="3">${sheet.lotId}</td>
			</tr>
			<tr>
				<th align="right">测试项目:</th>
				<td colspan="7">
					<s:iterator value="proList" id="pro" >
						&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" name="recordList.proName">${pro.proName }
					</s:iterator>
				</td>
			</tr>
		</table>
		
		<table class="table">
			<tr>
				<th colspan="3" style="text-align: center;font-size: 20px;">测试记录</th>
			</tr>
			<tr>
				<th class="col-xs-2">测试项目</th>
				<th class="col-xs-7">测试记录</th>
				<th class="col-xs-3">附件</th>
			</tr>
			<s:iterator value="sheet.recordSet" id="record" >
				<tr>
					<td>${record.proName }</td>
					<td>${record.testRecord }</td>
					<td><a target="_showPri" href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/rts/${record.testFile }">${record.otherFileName }</td>
				</tr>
			</s:iterator>			
		</table>
		<tfoot>
					<tr>
						<td class="col-xs-12" colspan="3">
							<table class="table" >
								<tr>
									<th class="col-xs-2">记录人</th>
									<td class="col-xs-3">
										${sheet.jlPerson}
									</td>
									<th class="col-xs-3">记录填写日期</th>
									<td class="col-xs-4" >
										${sheet.jlAddTime}
									</td>
								</tr>
								<tr>
									<th class="col-xs-2">判定标准</th>
									<td class="col-xs-3" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideBasis" >${sheet.decideBasis}</textarea>
									</td>
									<th class="col-xs-3">判定结果</th>
									<td class="col-xs-4" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideResult">${sheet.decideResult}</textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
				</tfoot>
			</table>
			<br/>
<%--			<br/>--%>
<%--			<br/>--%>
<%--			<table class="table" align="center">--%>
<%--				<tr>--%>
<%--					<th colspan="4" style="font-size: 20px;text-align: center">结果判定</th>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th class="col-xs-2">判定依据</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						${sheet.jlPerson}--%>
<%--					</td>--%>
<%--					<th class="col-xs-2">判定结果</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						${sheet.jlPerson}--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th colspan="1" class="col-xs-2">记录/异常描述</th>--%>
<%--					<td colspan="3" class="col-xs-10" style="margin: 0;padding: 0">--%>
<%--						${sheet.jlPerson}--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
	</body>
</html>
