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
				<h2>
					审批申请延期项目
				</h2>
				<br />
				<div>
					<form action="projectPoolAction_examineForPostoneOut.action" id="frm" method="post">
						<input type="hidden" name="id" value="${projectManageyf.id}"/>
						<table class="table">
							<tr>
								<th width="25%" align="right">
									项目编号:
								</th>
								<td width="25%" align="left">
									<span>${projectManageyf.proNum}</span>
								</td>
							</tr>
							<tr>
								<th align="right">
									项目名称:
								</th>
								<td align="left">
									${projectManageyf.proName}
								</td>
								
							</tr>
							<tr>
								<th align="right">
									开始时间:
								</th>
								<td align="left">
									${projectManageyf.addTime}
								</td>
							</tr>
							<tr>
								<th align="right">
									负责人:
								</th>
								<td align="left">
									${projectManageyf.principals}
								</td>
							</tr>
							<tr>
								<th align="right">
									指派时间:
								</th>
								<td align="left">
									${projectManageyf.zpTime}
								</td>
							</tr>
							<tr>
								<th align="right">
									预完成时间:
								</th>
								<td align="left">
									${projectManageyf.reTime}
								</td>
							</tr>
							<tr>
								<th align="right">
									项目备注:
								</th>
								<td align="left">
									${projectManageyf.remark}
								</td>
							</tr>
							<tr>
								<th align="right">
									项目预期评分:
								</th>
								<td align="left">
									${projectManageyf.gradeStore}
									<input type="hidden" value="${projectManageyf.gradeStore}" id="selfStore"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									项目占比:
								</th>
								<td align="left">
									${projectManageyf.proportion}
								</td>
							</tr>
							<tr>
								<th align="right">
									申请延期时间
								</th>
								<td align="left">
									<span style="color:red">${projectManageyf.outTime}</span>
								</td>
							</tr>
							<tr>
								<th align="right">
									延期说明
								</th>
								<td align="left">
									<span style="color:red">${projectManageyf.outExplain}</span>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="hidden" id="resultStatus" name="status"/>
									<input type="button" value="同意" style="width: 100px;height: 40px"
										onclick="submitResult('同意')"/>
									<input type="button" value="打回" style="width: 100px;height: 40px"
										onclick="submitResult('打回')"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</div>
		<script type="text/javascript">
		function submitResult(result){
			
			if(confirm("确定提交为"+result+"吗?")){
				if("同意"==result){
					$("#resultStatus").val("同意");
				}else if("不同意"==result || "打回"==result){
					$("#resultStatus").val("打回");	
				}
				$("#frm").submit();
			}
		}
		</script>
	</body>
</html>
