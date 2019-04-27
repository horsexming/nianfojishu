<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}
</style>
	</head>

	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="row clearfix">
					<h1 align="center">
						问题列表
					</h1>
					<div style="float: right;">
						<s:if test="status=='dcl'">
							<label style="background-color: gray;" class="dhlabel">
								待处理&nbsp;&nbsp;
								<b style="color: red">${dclcount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('dcl');" class="dhlabel">
								<font color="white">待处理&nbsp;&nbsp;<b style="color: red">${dclcount}条</b>
								</font>
							</label>
						</s:else>
						<s:if test="status=='clz'">
							<label style="background-color: gray;" class="dhlabel">
								处理中&nbsp;&nbsp;
								<b style="color: red">${clzcount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('clz');" class="dhlabel">
								<font color="white">处理中&nbsp;&nbsp;<b style="color: red">${clzcount}条</b>
								</font>
							</label>
						</s:else>
						<s:if test="status=='dqr'">
							<label style="background-color: gray;" class="dhlabel">
								待确认&nbsp;&nbsp;
								<b style="color: red">${dqrcount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('dqr');" class="dhlabel">
								<font color="white">待确认&nbsp;&nbsp;<b style="color: red">${dqrcount}条</b>
								</font>
							</label>
						</s:else>
						<s:if test="status=='ecfk'">
							<label style="background-color: gray;" class="dhlabel">
								二次反馈&nbsp;&nbsp;
								<b style="color: red">${fkcount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('ecfk');" class="dhlabel">
								<font color="white">二次反馈 &nbsp;&nbsp;<b
									style="color: red">${fkcount}条</b> </font>
							</label>
						</s:else>
						<s:if test="status=='wc'">
							<label style="background-color: gray;" class="dhlabel">
								完成&nbsp;&nbsp;
								<b style="color: red">${wccount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('wc');" class="dhlabel">
								<font color="white">完成&nbsp;&nbsp;<b style="color: red">${wccount}条</b>
								</font>
							</label>
						</s:else>
						<s:if test="status=='all'">
							<label style="background-color: gray;" class="dhlabel">
								所有&nbsp;&nbsp;
								<b style="color: red">${totalcount}条</b>
							</label>
						</s:if>
						<s:else>
							<label style="background-color: #5cb85c;"
								onclick="toShowWW('all');" class="dhlabel">
								<font color="white">所有&nbsp;&nbsp;<b style="color: red">${totalcount}条</b>
								</font>
							</label>
						</s:else>
					</div>
					<form action="TaskmanagerAction_list.action" method="post"
						id="myform">
						<input type="hidden" name="level" value="${level}">
						<input type="hidden" name="status" value="${status}">
						<table class="table">
							<tr>
								<td>
									<label for="">
										问题类型：
									</label>
									<%--									<input type="text" name="taskmanager.taskType">--%>
									<select class="form-control" id="taskTypes" readonly="true"
										name="taskmanager.taskType">
										<option value="${taskmanager.taskType}">
											${taskmanager.taskType}
										</option>
										<option value="">
										</option>
										<option value="问题点">
											问题点
										</option>
										<option value="新需求">
											新需求
										</option>
									</select>
								</td>
								<%--								<td>--%>
								<%--									<label for="">--%>
								<%--										状态：--%>
								<%--									</label>--%>
								<%--									<input type="text" name="taskmanager.taskState">--%>
								<%----%>
								<%--								</td>--%>
								<td>
									<label for="">
										申请人：
									</label>
									<input type="text" name="taskmanager.applyUsersName"
										value="${taskmanager.applyUsersName}">
								</td>

								<td>
									<label for="">
										紧急度：
									</label>
									<%--									<input type="text" name="taskmanager.urgency">--%>
									<%--									（0,1,2）--%>
									<select class="form-control" id="taskTypes" readonly="true"
										name="taskmanager.urgency">
										<option value="${taskmanager.urgency}">
											${taskmanager.urgency}
										</option>
										<option value="">

										</option>
										<option value="0">
											一般
										</option>
										<option value="1">
											紧急
										</option>
										<option value="2">
											非常紧急
										</option>
									</select>

								</td>
								<td>
									<label for="">
										处理人：
									</label>
									<input type="text" name="taskmanager.receiver"
										value="${taskmanager.receiver}">
								</td>

							</tr>
							<tr>
								<td>
									开始日期：
									<input style="width: 155px" class="Wdate" type="text"
										name="vobo.startTime" value="${taskmanager.startTime}"
										id="startTime"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td>
									完成日期：
									<input style="width: 155px" class="Wdate" id="endTime"
										type="text" name="taskmanager.finishTime"
										value="${taskmanager.finishTime}"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td>
									<label for="">
										部门：
									</label>
									<%--									<input id=taskdept"  type="hidden"--%>
									<%--										name="taskmanager.applyUsersDept">--%>
									<select id="deptname" name="taskmanager.applyUsersDept"
										style="width: 100px;" onchange="changeDept();">
										<option value="${taskmanager.applyUsersDept}">
											${taskmanager.applyUsersDept}
										</option>
										<option value="">
										</option>
									</select>

								</td>
								<td>
									内容：
									<input type="text" name="taskmanager.description"
										value="${taskmanager.description}">
								</td>
							</tr>
							<tr>
								<td>
									编号：
									<input type="text" name="taskmanager.id"
										value="${taskmanager.id}">
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="hidden" name="errorMessage" value="all" />
									<input type="submit" value="查询"
										style="width: 80px; height: 50px;" />

								</td>
							</tr>
						</table>
					</form>
					<div class="col-md-12 column">
						<table class="table">
							<thead>
								<tr>
									<th>
										序号
									</th>
									<th>
										编号
									</th>
									<th>
										问题类型
									</th>
									<th>
										问题分类
									</th>
									<th>
										问题描述
									</th>
									<th>
										关联订单号或零件号
									</th>
									<th>
										所属流程
									</th>
									<th>
										异常类别
									</th>
									<th>
										功能所属类
									</th>
									<th>
										重复次数
									</th>
									<th>
										开始时间
									</th>
									<th>
										完成时间
									</th>
									<th>
										困难度
									</th>
									<th>
										处理意见/分析
									</th>
									<th>
										反馈意见
									</th>
									<th>
										申请人
									</th>
									<th>
										部门
									</th>
									<th>
										领取人
									</th>
									<th>
										确认人
									</th>
									<th>
										状态
									</th>
									<th>
										紧急程度
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="status == 'dcl'">
									<tr>
										<th bgcolor="#FF6699" colspan="22">
											待处理:
											<b>${sum}条</b>
										</th>
									</tr>
								</s:if>
								<s:elseif test="status == 'clz'">
									<tr>

										<th bgcolor="#BFEFFF" colspan="22">
											处理中:
											<b>${sum}条</b>
										</th>
									</tr>
								</s:elseif>
								<s:elseif test="status == 'dqr'">
									<tr>
										<th bgcolor="yellow" colspan="22">
											待确认:
											<b>${sum}条</b>
										</th>
									</tr>
								</s:elseif>
								<s:elseif test="status == 'ecfk'">
									<tr>
										<th bgcolor="#CD9B9B" colspan="22">
											二次反馈:
											<b>${sum}条</b>
										</th>
									</tr>
								</s:elseif>
								<s:elseif test="status == 'wc'">
									<tr>
										<th bgcolor="#339966" colspan="22">
											完成:
											<b>${sum}条</b>
										</th>
									</tr>
								</s:elseif>
								<s:iterator value="taskmanagers" id="task" status="Status0">
									<s:if test="#task.taskState=='完成'">
										<tr class="success">
									</s:if>
									<s:elseif test="#task.taskState=='待处理'">
										<tr class="warning">
									</s:elseif>
									<s:elseif test="#task.taskState=='反馈'">
										<tr class="danger">
									</s:elseif>
									<s:elseif test="#task.taskState=='处理中'">
										<tr class="info">
									</s:elseif>
									<s:else>
										<tr>
									</s:else>
									<td>
										${Status0.index+1}
									</td>
									<td>
										${task.id}
									</td>
									<td>
										${task.taskType}
									</td>
									<td>
										${task.taskCategory}
									</td>
									<td>
										${task.description}
									</td>
									<td>
										${task.note}
									</td>
									<td>
										${task.process}
									</td>
									<td>
										${task.exceptionType}
									</td>
									<td>
										${task.functionType}
									</td>
									<td>
										${task.repeatTime}
									</td>
									<td>
										${task.startTime}
									</td>
									<td>
										${task.finishTime}
									</td>
									<td>
										${task.difficulty}
									</td>
									<td>
										${task.suggestion}
									</td>
									<td>
										${task.feedback}
									</td>
									<td>
										${task.applyUsersName}
									</td>
									<td>
										${task.applyUsersDept}
									</td>
									<td>
										${task.receiver}
									</td>
									<td>
										${task.verifier}
									</td>
									<td>
										${task.taskState}
									</td>
									<td>
										<s:if test="#task.urgency==0">
											一般
										</s:if>
										<s:elseif test="#task.urgency==1">
											紧急
										</s:elseif>
										<s:elseif test="#task.urgency==2">
											非常紧急
										</s:elseif>
									</td>
									<td>
										<s:if test="level=='view'"></s:if>
										<s:else>
											<s:if test="level=='self'||level=='dept'">
												<a
													href="TaskmanagerAction_confirmpage.action?id=${task.id}&level=${level}&status=${status}">问题确认</a>
												<a
													href="TaskmanagerAction_updatepage.action?id=${task.id}&level=${level}&status=${status}">修改</a>
												<a
													href="TaskmanagerAction_delete.action?id=${task.id}&level=${level}&status=${status}">删除</a>
												<a
													href="TaskmanagerAction_attachment.action?id=${task.id}&level=${level}&status=${status}">查看附件</a>
											</s:if>
											<s:elseif test="level=='all'">
												<a
													href="TaskmanagerAction_suggestion.action?id=${task.id}&level=${level}&status=${status}">处理</a>
												<a
													href="TaskmanagerAction_delete.action?id=${task.id}&level=${level}&status=${status}">删除</a>
												<a
													href="TaskmanagerAction_attachment.action?id=${task.id}&level=${level}&status=${status}">查看附件</a>

											</s:elseif>
										</s:else>
									</td>
								</s:iterator>
								<tr>
									<s:if test="errorMessage==null">
										<td colspan="22" align="right">
											第
											<font color="red"><s:property value="cpage" /> </font> /
											<s:property value="total" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
										</td>
									</s:if>

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toShowWW(status) {
	window.location.href = "TaskmanagerAction_list.action?level=${level}&status="
			+ status;
}

$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "TaskmanagerAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#deptname");
					});
			var tinyselect = $(".tinyselect");
			$("#deptname").tinyselect();
		}
	});
});<%--taskdept--%>
</script>
	</body>
</html>
