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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">

				</div>
			</div>

			<div align="center">
				<form action="rewardPunishAction!addRewardPunish.action"
					method="post" style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								部门:
							</td>
							<td>
								<input id="userId" type="hidden" name="rewardPunish.userId"
									value="${user.id}" />
								<input id="code" type="hidden" name="rewardPunish.code" value="${user.code}" />
								<input id="name" type="hidden" name="rewardPunish.name" value="${user.name}" />
								<select id="dept" name="rewardPunish.dept">
									<s:if test="user!=null && user.dept!=null">
										<option value="${user.dept}">
											${user.dept}
										</option>
									</s:if>
									<s:else>
										<option value="">
											请选择
										</option>
									</s:else>
								</select>
							</td>
							<td align="right">
								姓名:
							</td>
							<td>
								<select id="user" name="rewardPunish.user">
									<s:if test="user!=null && user.name!=null">
										<option value="${user.name}">
											${user.name}
										</option>
									</s:if>
									<s:else>
										<option value="部门">
											请选择
										</option>
									</s:else>
								</select>
								(不选员工时默认为部门)
							</td>

						</tr>
						<tr>
							<td align="right">
								时间:
							</td>
							<td>
								<input id="date" class="Wdate" type="text"
									name="rewardPunish.date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">
								项目:
							</td>
							<td>
								<input type="project" name="rewardPunish.project" value="" />
							</td>


						</tr>
						<tr>
							<td align="right">
								类型:
							</td>
							<td>
								<select name="rewardPunish.type" id="type">
									<option value="">
										请选择
									</option>
									<option value="加班">
										加班
									</option>
									<option value="请假">
										请假
									</option>
									<option value="违纪">
										违纪
									</option>
								</select>
							</td>
							<td align="right">
								金额:
							</td>
							<td>
								<input type="money" name="rewardPunish.money" value="" />
							</td>

						</tr>
						<tr>
							<td align="right">
								备注:
							</td>
							<td colspan="3">
								<textarea type="explain" name="rewardPunish.explain"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
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
<script type="text/javascript">
$(function() {
	/*
	$('#code').blur(function(){
		var code=$('#code').val();
		if(''!=code){
			$.ajax({
				type: "get",
				dataType: "json",
				//url: "UsersAction!findByCode?user.code="+applyCode,
	            url: "overtimeAction!findUserByCode?code="+code,
				async: false,
				success: function(data){
					var data=data.data;
					$('#code').val(data.code);
					$('#name').val(data.name);
					$('#dept').val(data.dept);
				}
			});
		}
	});
	 */
	$.ajax( {
		type : "get",
		dataType : "text",
		url : "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async : false,
		success : function(data) {
			var dept = data.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				var deptItem = new Option(dept[i], dept[i]);
				$("#dept").append(deptItem);
			}
		}
	});

	//级联查询出部门所对应的所有人员
	$("#dept").bind(
			"change",
			function() {
				if ($("#dept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#dept").val()
						},
						success : function(useradsfa) {
							$("#user").empty();//清空
							$("<option value=''>请选择</option>")
									.appendTo("#user");
							$(useradsfa).each(
									function() {
										$(
												"<option value='" + this.code
														+ "|" + this.name + "|"
														+ this.id + "|"
														+ this.cardId + "'>"
														+ this.name + this.code
														+ "</option>")
												.appendTo("#user")
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				}
			});
	$("#user").bind("change", function() {
		var overtimeUser = $("#user").val();
		var overtimeValues = overtimeUser.split("|");
		var code = overtimeValues[0];
		var name = overtimeValues[1];
		var id = overtimeValues[2];
		var cardId = overtimeValues[3];
		$('#userId').val(id);
		$('#code').val(code);
		$('#name').val(name);
	});

});
</script>
