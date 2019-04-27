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
		<script type="text/javascript">
$(function() {
	//加载所有分组
	$
			.ajax( {
				type : "post",
				url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=sz",
				dataType : "json",
				success : function(object) {
					//填充分组信息
					$("<option value='0'>所有审核人员</option>").appendTo(
							"#userGroup1");
					$.each(object, function(i, group) {
						$(
								"<option value='" + group.id + "'>"
										+ group.groupName + "</option>")
								.appendTo("#userGroup1");
					});

					//绑定选择分组
					$("#userGroup1")
							.bind(
									"click",
									function() {
										$
												.ajax( {
													type : "post",
													url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=sz",
													data : {
														id : $("#userGroup1")
																.val()
													},
													dataType : "json",
													success : function(persons) {
														$("#person1").empty();
														$(
																"<option>请选择人员</option>")
																.appendTo(
																		"#person1");
														$
																.each(
																		persons,
																		function(
																				i,
																				person) {
																			$(
																					"<option value='"
																							+ person.userName
																							+ "'>"
																							+ person.userName
																							+ "</option>")
																					.appendTo(
																							"#person1");
																		});
													}
												});
									});
				}
			});
	//加载所有分组
	$
			.ajax( {
				type : "post",
				url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=sz",
				dataType : "json",
				success : function(object) {
					//填充分组信息
					$("<option value='0'>所有审核人员</option>").appendTo(
							"#userGroup2");
					$.each(object, function(i, group) {
						$(
								"<option value='" + group.id + "'>"
										+ group.groupName + "</option>")
								.appendTo("#userGroup2");
					});

					//绑定选择分组
					$("#userGroup2")
							.bind(
									"click",
									function() {
										$
												.ajax( {
													type : "post",
													url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=sz",
													data : {
														id : $("#userGroup2")
																.val()
													},
													dataType : "json",
													success : function(persons) {
														$("#person2").empty();
														$(
																"<option>请选择人员</option>")
																.appendTo(
																		"#person2");
														$
																.each(
																		persons,
																		function(
																				i,
																				person) {
																			$(
																					"<option value='"
																							+ person.userName
																							+ "'>"
																							+ person.userName
																							+ "</option>")
																					.appendTo(
																							"#person2");
																		});
													}
												});
									});
				}
			});

});
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<center>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="projectOrderAction_add.action" style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加项目需求单
					<br />
					（add project order）
				</h3>
				<form action="projectOrderAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								项目
								<br />
								(project name)
							</th>
							<td>
								  <SELECT name="proTryMakeScore.id" id="projectid">
							       <option value="0">
							       ----未选择----
							       </option>
							      <s:iterator value="ptmsList" id="ptms">
							       <option value="<s:property value="#ptms.id"/>">
							       <s:property value="#ptms.proName"/>::<s:property value="#ptms.month"/>
							       </option> 
							      </s:iterator>
							   </SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								产品工程师
								<br />
								（product engineer）：
							</th>
							<td>
								<select id="userGroup1" name="userGroup1" style="width: 100px;" >
									<option>
										请选择分组
									</option>
								</select>
								<select id="person1" name="projectOrder.productEngineer" style="width: 100px;">
									<option value="请先选择分组">
										请先选择分组
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								技术工程师
								<br />
								（technical engineer）：
							</th>
							<td>
							<select id="userGroup2" name="userGroup2" style="width: 100px;" >
									<option>
										请选择分组
									</option>
								</select>
								<select id="person2" name="projectOrder.technicalEngineer" style="width: 100px;">
									<option value="请先选择分组">
										请先选择分组
									</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th align="right">
								项目依据
								<br />
								（project Based）：
							</th>
							<td>
								<input type="text" name="projectOrder.projectBy" id="projectBy" />
							</td>
						</tr>
						<tr>
							<th align="right">
								项目目的
								<br />
								（project intent）：
							</th>
							<td>
								<input type="text" name="projectOrder.projectTo"
									id="difficultScore" />
							</td>
						</tr>
						<tr>
							<th align="right">
								交付信息
								<br />
								（delivery infomation）：
							</th>
							<td>
								<input type="text" name="projectOrder.deliveryInfo"
									id="deliveryInfo" />（交付时间、地点及接受人）
							</td>
						</tr>
						<tr>
							<th align="right">
								项目处理
								<br />
								（deal with）：
							</th>
							<td>
								<SELECT name="projectOrder.deal">
									<option value="1">
										冲抵生产计划,入库销售
									</option>
									<option value="2">
										试验用,入库
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
								<br />
								（remark）：
							</th>
							<td>
								<input type="text" name="projectOrder.remark" id="remark" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
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
		<script type="text/javascript">
function validate() {
	var projectid = document.getElementById("projectid").value;
	if (projectid == 0) {
		alert("请选择项目");
		return false;
	}
	

}
</script>
	</body>
</html>
