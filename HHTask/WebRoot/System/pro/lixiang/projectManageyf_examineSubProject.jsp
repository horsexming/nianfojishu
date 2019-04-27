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
					审批子项目完成情况
				</h2>
				<br />
				<div>
					<form action="projectPoolAction_examineSubProject.action" id="frm" method="post">
						<input type="hidden" name="projectManageyf.id" value="${projectManageyf.id}"/>
						<input type="hidden" value="${message}" id="message"/>
						
						<!-- <input type="hidden" name="projectManageyf." -->
						<table class="table">
							<tr>
								<th width="25%" align="right">
									项目编号:
								</th>
								<td width="25%" align="left">
									<span>${projectManageyf.proNum}</span>
								</td>
								<th rowspan="3">文件：</th>
								<td rowspan="3">
									<div id="showdownloadFile">
										<input type="hidden" value="${projectManageyf.yfProjectFile}" id="fileNames">
										<input type="hidden" value="${projectManageyf.aliasFile}" id="aliasFiles">
									</div>
									<input type="file" name="attachment" multiple="multiple"/> 
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
								<th rowspan="6">
									内容:
								</th>
								<td rowspan="6">
									<textarea rows="6" cols="50" readonly>${projectManageyf.schedule}</textarea>
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
									项目实际分数
								</th>
								<td align="left" colspan="3">
									<s:if test="projectManageyf.actualStore!=null">
										<input type="text" value="${projectManageyf.actualStore}" id="actualStore"
									 style="color:#FF0000;" />
									</s:if>
									<s:else>
										<input type="text" value="${projectManageyf.gradeStore}" id="actualStore"
										 style="color:#FF0000;" name="projectManageyf.actualStore" />
										
									</s:else>
								</td>
							</tr>
							<s:if test="'确认关闭'!=projectManageyf.status &&'完成'!=projectManageyf.status">
								<tr>
									<td colspan="4" align="center">
										<input type="hidden" id="resultStatus" name="resultStatus"/>
										<input type="button" value="同意" style="width: 100px;height: 40px"
											onclick="submitResult('同意')"/>
										<input type="button" value="打回" style="width: 100px;height: 40px"
											onclick="submitResult('打回')"/>
									</td>
								</tr>
							</s:if>
						</table>
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</div>
		<script type="text/javascript">
		$(function(){
			
			var fileNames = $("#fileNames").val();
			var files = fileNames.split(",");
			var aliasFiles = $("#aliasFiles").val();
			var aliasFiles = aliasFiles.split(",");
			if(files.length>0){
				for(var i=0;i<files.length;i++){
					if(aliasFiles==null || aliasFiles[i]==null || aliasFiles[i]==""){
						aliasFiles[i] = files[i];
					}
					if(aliasFiles[i]==null || aliasFiles[i]==""){
						$("#showdownloadFile").append("&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/upload/file/project/"+files[i]+"'>&nbsp;&nbsp;" +
						"查看附件("+aliasFiles[i]+")</a><br>");
						
					}
					
				}
			}
			var message = $("#message").val();
			if(""!=message){
				alert(message);
				$("#message").val("");
			}
		});
		function submitResult(result){
			var selfStore = $("#selfStore").val();
			var actualStore = $("#actualStore").val();
			
			if(parseInt(actualStore)>parseInt(selfStore)){
				alert("实际分数不能大于项目预期分数");
				return ;
			}
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
