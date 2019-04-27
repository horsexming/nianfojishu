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
	<body onload="changeType('2');">
<center>
				<form action="EquipmentAction!updateresponsibilities.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">修改设备人员维护</th></tr>
						<tr>
							<th align="right">
								部门：	<input type="hidden" value="${responsibilities.id}" id="responsibilities.id" name="responsibilities.id" />
							
							</th>
							<td>
							<select id="dept" style="width: 155px;"
									name="responsibilities.repairdepartment">
									<option value="${responsibilities.repairdepartment}">${responsibilities.repairdepartment}</option>
									</select>
							</td>
						</tr>

						<tr>
							<th align="right">
								人员：
							</th>
							<td  >
						
							<input id="userName" type="hidden" name="responsibilities.repairname" value="${responsibilities.repairname}"
									readonly="readonly" ">

								<select id="users" style=" width: 155px;">
									<option value="[${responsibilities.repairname},${responsibilities.employeenumber}]">${responsibilities.repairname}</option>
								</select>
						
						
							</td>
						</tr>
						<tr>
							<th align="right">
								工号：
							</th>
							<td>
							<input type="text" id="leavePersonCode" name="responsibilities.employeenumber"  
							value="${responsibilities.employeenumber}">
							</td>
						</tr>
						<tr>
							<th align="right">
								类别：
							</th>
							<td>
								<input type="text" id="responsibilities.repairresponsibilitiesl" name="responsibilities.repairresponsibilitiesl" value="${responsibilities.repairresponsibilitiesl}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								手机号：
							</th>
							<td>
								<input type="text" id="responsibilities.phone" 
								name="responsibilities.phone"  value="${responsibilities.phone}" />
							</td>
						</tr>
								<tr>
							<th align="right">
								邮箱：
							</th>
							<td>
								<input type="text" id="responsibilities.mailbox" name="responsibilities.mailbox" value="${responsibilities.mailbox}"  />
							</td>
						</tr>
					
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="保存" class="input" />
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>


				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			
			
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
		function changeType(type) {
		if (type == "1") {
			$("#userName").val("${Users.name}");
			$("#userName").css("display", "block");
			$("#users").css("display", "none");
			$("#userName").attr("readonly", "readonly");
			$("#dept").empty();
			$("<option value='${Users.dept}'>${Users.dept}</option>").appendTo(
					"#dept");
		} else if (type == "2") {
			$("#userName").css("display", "none");
			$("#users").css("display", "block");
			//显示所有部门信息
			$.ajax({
				url : 'DeptNumberAction!findAllDept.action',
				dataType : 'json',
				cache : false,//防止数据缓存
				success : function(allDdept) {
					$("#dept").empty();
					$("<option value=''>--请选择部门--</option>").appendTo("#dept");
					$(allDdept).each(
							function() {
								$(
										"<option value='" + this.dept + "'>"
												+ this.dept + "</option>")
										.appendTo("#dept");
							});
				}

			});
			//显示部门对应的员工信息
			$("#dept")
					.bind(
							"change",
							function() {
								if ($("#dept").val() != "") {
									$
											.ajax({
												url : "UsersAction!findUsersByDept.action",
												type : 'post',
												dataType : 'json',
												cache : false,//防止数据缓存
												data : {
													deptName : $("#dept").val()
												},
												success : function(useradsfa) {
													$("#users").empty();//清空
													$("<option></option>")
															.appendTo("#users");
													$(useradsfa)
															.each(
																	function() {
																		$(
																				"<option value='"
														                    + this.code+"|"
																			+ this.name
																			+ "'>"
																						+ this.name
																						+ "</option>")
																				.appendTo(
																						"#users")
																	});
													$("#users")
															.bind(
																	"change",
																	function() {
																		$(
																				"#userName")
																				.val(
																						$(
																								"#users")
																								.val());
																	});
												},
												error : function() {
													alert("服务器异常!");
												}
											});
								}

							});

		}
		$("#users").bind("change", function() {
			var users = $("#users").val();
			var usersData = users.split("|");
			var code = usersData[0];
			$("#leavePersonCode").val(code);
		});
	}
	</script>


</html>
