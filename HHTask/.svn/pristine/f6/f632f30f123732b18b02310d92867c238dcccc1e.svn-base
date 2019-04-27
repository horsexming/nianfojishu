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
		<script type="text/javascript" src="javascript/query-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
		<SCRIPT type="text/javascript">
			$(function(){
				$('#closedProject').bind('click', function(){
					$.ajax({
						type: 'POST',
						url: 'Project_closed.action',
						dataType: "json",
						data: "project.id=" + ${project.id},
						success: function(msg){
							alert(msg.message);
							if(msg.success){
								window.location.reload();
							}
						}
					});
				});
			});
		</SCRIPT>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" style="width: 50%">
					<tr>
						<th>项目流程</th>
						<th>项目操作</th>
						<th>项目状态</th>
					</tr>
					<tr>
						<td>项目人员</td>
						<td>
							<a target="_blank" href="ProjectUser_addInput.action?user.root.id=${project.id}">添加</a>
							<a target="_blank" href="ProjectUser_list.action?user.root.id=${project.id}">查看</a>
						</td>
						<td>
							无状态
						</td>
					</tr>
					<tr>
						<td>项目建议书</td>
						<td>
							<a target="_blank" href="ProjectProposal_addInput.action?proposal.root.id=${project.id}">添加</a>
							<a target="_blank" href="ProjectProposal_updateInput.action?proposal.root.id=${project.id}">修改</a>
							<a target="_blank" href="ProjectProposal_list.action?proposal.root.id=${project.id}">查看</a>
						</td>
						<td>
							<s:if test="projectProposal != null">
								<s:if test="projectProposal.closed == false">
									<font color="red">进行中</font>
								</s:if><s:else>
									关闭
								</s:else>
							</s:if>
						</td>
						
					</tr>
					<tr>
						<td>项目报价</td>
						<td>
							<a target="_blank" href="ProjectQuotationList_addInput.action?quotationList.root.id=${project.id}">开启</a>
							<a target="_blank" href="ProjectQuotationList_list.action?quotationList.root.id=${project.id}">查看</a>
						</td>
						<td>
							<s:if test="projectQuotation != null">
								<s:if test="projectQuotation.closed == false">
									<font color="red">进行中</font>
								</s:if><s:else>
									关闭
								</s:else>
							</s:if>
						</td>
					</tr>
					<tr>
						<td>项目启动</td>
						<td>
							<a target="_blank" href="ProjectStart_add.action?projectStart.root.id=${project.id}">开启</a>
							<a target="_blank" href="ProjectStart_list.action?projectStart.root.id=${project.id}">查看</a>
						</td>
						<td>
							<s:if test="projectStart != null">
								<s:if test="projectStart.closed == false">
									<font color="red">进行中</font>
								</s:if><s:else>
									关闭
								</s:else>
							</s:if>
						</td>
					</tr>
					<tr>
						<td>项目根踪</td>
						<td>
							<a target="_blank" href="ProjectTrack_add.action?projectTrack.root.id=${project.id}">开启</a>
							<a target="_blank" href="ProjectTrack_list.action?projectTrack.root.id=${project.id}">查看</a>
						</td>
						<td>
							<s:if test="projectTrack != null">
								<s:if test="projectTrack.closed == false">
									<font color="red">进行中</font>
								</s:if><s:else>
									关闭
								</s:else>
							</s:if>
						</td>
					</tr>
					<s:if test="projectTrack.closed == false">
						<tr>
							<td colspan="3" align="center">
								<input id="closedProject" type="button" value="关闭项目">
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
