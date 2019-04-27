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
					项目评分
				</h2>
				<br />
				<div>
					<form action="projectPoolAction_submitProjectGrade.action" method="post" id="SubmitStoreFrm">
						<input type="hidden" name="projectManageyf.id" value="${projectManageyf.id}"/>
						<h1 style="color: red">${message}</h1>
						<table class="table">
							<tr>
								<th width="50%" align="right">
									项目编号:
								</th>
								<td width="50%" align="left">
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
									创建时间:
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
									项目评分:
								</th>
								<td align="left">
									<input type="text" name="projectManageyf.gradeStore" id="store"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${projectManageyf.gradeStore}"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								</td>
							</tr>
							<tr>
								<th align="right">
									评分备注:
								</th>
								<td align="left">
									<textarea rows="3" cols="30" name="projectManageyf.gradeRemark" >${projectManageyf.gradeRemark}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<s:if test="null==projectManageyf || null==projectManageyf.gradeStore || 0 == projectManageyf.gradeStore">
										<input type="button" value="提交分数" id="submitBtn"
											style="width: 70px; height: 40px" />
									</s:if>
									<s:elseif test="null!=projectManageyf.gradeStatus&&'打回'==projectManageyf.gradeStatus">
										<input type="button" value="提交分数" id="submitBtn"
											style="width: 70px; height: 40px" />
										
									</s:elseif>
									<s:else>
										<span style="color:red">您已经提交了分数</span>
									</s:else>
								</td>
							</tr>
						</table>
						<br />
						<br />
						<table class="table">
							<tr>
								<th colspan="10">
									其他已评分项目
								</th>
							</tr>
							<tr>
								<td align="center">
									序号
								</td>
								<td align="center">
									项目编号
								</td>
								<td align="center">
									项目名称
								</td>
								<td align="center">
									指派时间
								</td>
								<td align="center">
									负责人
								</td>
								<td align="center">
									预完成时间
								</td>
								<td align="center">
									备注
								</td>
								<td align="center">
									状态
								</td>
								<td align="center">
									评分分数
								</td>
								<td align="center">
									评分状态
								</td>
							</tr>
							<s:if
								test="projectManageyfList!=null&&projectManageyfList.size()>0">
								<s:iterator value="projectManageyfList" id="project"
									status="yfStatus">
									<s:if test="#yfStatus.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#yfStatus.index+1" />
									</td>
									<td>
										<s:property value="#project.proNum" />
									</td>
									<td>
										<s:property value="#project.proName" />
									</td>
									<td>
										<s:property value="#project.zpTime" />
									</td>
									<td>
										<s:property value="#project.principals" />
									</td>
									<td>
										<s:property value="#project.reTime" />
									</td>
									<td>
										<s:property value="#project.remark" />
									</td>
									<td>
										<s:property value="#project.status" />
									</td>
									<td>
										<s:property value="#project.gradeStore" />
									</td>
									<td>
										<s:property value="#project.gradeStatus" />
									</td>
									

									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="10" style="font-size: 15px; color: red;">
										对不起，还没有别人为自己的项目评分
									</td>
								</tr>
							</s:else>
						</table>
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</div>
		<script type="text/javascript">
			$(function(){
				$("#submitBtn").click(function(){
					var store = $("#store").val();
					if(null==store ||  ""==store){
						alert("请输入分数");
						 $("#store").focus();
						return ;
					}
					if(confirm("确定提交分数吗？")){
						$("#SubmitStoreFrm").submit();
					}
				});
			})
		</script>
	</body>
</html>
