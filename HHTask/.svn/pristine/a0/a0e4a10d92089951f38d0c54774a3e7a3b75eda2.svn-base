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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="projectOrderAction_update.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改项目需求单<br/>
					（update project order）
				</h3>
				<h2>
				 <br/><font color="red">${errorMessage}</font>
				</h2>
				<form action="projectOrderAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
					<tr>
							<th align="right">
								组别
							<br />
							（group）：
							</th>
							<td>
							<input type="text" value="<s:property value="projectOrder.groupName"/>" readonly="readonly">
							
							</td>
						</tr>
						<tr>
							<th align="right">
								项目名称
							<br />
							(project name)
							</th>
							<td><input type="hidden" name="projectOrder.id" value="<s:property value='projectOrder.id'/>">
								<input type="text" value="<s:property value="projectOrder.proName"/>" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="right">
								月份
							<br />
							（group）：
							</th>
							<td>
							<input type="text" value="<s:property value="projectOrder.month"/>" readonly="readonly">
							
							</td>
						</tr>
						<tr>
							<th align="right">
							试制单编号
							<br />
							（order number）
							</th>
							<td>
								<input type="text" name="projectOrder.orderNO" id="orderNO" 
								  value="<s:property value='projectOrder.orderNO'/>" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								产品工程师
								<br />
								（product engineer）：
							</th>
							<td>
								<select id="userGroup1" name="" style="width: 100px;">
									<option>
										请选择分组
									</option>
								</select>
								<select id="person1" name="projectOrder.productEngineer" style="width: 100px;">
									<s:if test="projectOrder.productEngineer==null">
							<option value="请先选择分组">
							  请先选择分组
							    </option>
							</s:if>
							 <s:else>
							 <option value="projectOrder.productEngineer">
							 ${projectOrder.productEngineer}
							 	</option>
							 </s:else>
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
							<select id="userGroup2" name="" style="width: 100px;">
									<option>
										请选择分组
									</option>
								</select>
								<select id="person2" name="projectOrder.technicalEngineer" style="width: 100px;">
									<s:if test="projectOrder.technicalEngineer==null">
							<option>
										请先选择分组
									</option>
							</s:if>
							 <s:else>
							 <option value="projectOrder.technicalEngineer">
							 ${projectOrder.technicalEngineer}
							 	</option>
							 </s:else>
								</select>
							</td>
						</tr>
						<tr>
														<th align="right">
								客户名称
							<br />
							（customer name）：
							</th>

							<td>
							<input type="text" value="<s:property value="projectOrder.cusName"/>" readonly="readonly">
							
							</td>
						</tr>
						
						<tr>
							<th align="right">
								项目依据
							<br />
							（project Based）：
							</th>
							<td>
								<input type="text" name="projectOrder.projectBy" id="projectBy" 
								value="<s:property value='projectOrder.projectBy'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
							项目目的
							<br />
							（project intent）：
							</th>
							<td>
								<input type="text" name="projectOrder.projectTo" id="difficultScore"
								value="<s:property value='projectOrder.projectTo'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
							交付信息
							<br />
							（delivery infomation）：
							</th>
							<td>
								<input type="text" name="projectOrder.deliveryInfo" id="deliveryInfo" 
								value="<s:property value='projectOrder.deliveryInfo'/>" />
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
								<s:if test="projectOrder.deal==1">
								  <option value="1">
								  冲抵生产计划,入库销售
								  </option>
								  <option value="2">
								  试验用,不予入库
								  </option>
								  </s:if>
								  <s:else>
								   <option value="2">
								  试验用,不予入库
								  </option>
								  <option value="1">
								  冲抵生产计划,入库销售
								  </option>
								 
								  </s:else>
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
								<input type="text" name="projectOrder.remark" id="remark" 
								value="<s:property value='projectOrder.remark'/>"/>
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
}
</script>
	</body>
</html>
