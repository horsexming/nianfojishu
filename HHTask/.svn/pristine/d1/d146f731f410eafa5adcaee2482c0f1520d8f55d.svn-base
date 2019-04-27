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
		<script type="text/javascript"
			src="../../../javascript/jquery-easyui-1.3.1/jquery-1.4.3.js"></script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
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
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h1>
					<font color="#46d0f5">修改考勤人员信息</font>
				</h1>
				<form
					action="AttendancePersonInformationAction!updateKQPerson.action"
					method="post">
					<input type="hidden" name="id" value="${id}">
					<table class="table">
					    <tr>
					        <td colspan="3" align="center">考勤人员信息维护</td>
					    </tr>
						<tr>
							<td>
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
								<input type="text" 	name="person.name" value="${person.name}" />
							</td>
							<td>
								卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<input type="text" name="person.cardNo" value="${person.cardNo}" />
							</td>
							</tr>
						    <tr>
							<td>
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
								<select name="department.name" id="department" onMouseOver="createDept('department','AttendancePersonInformationAction!findSTPDept.action?tag=department')">
								<option value="${department.name}">${department.name}</option>
								</select>
							</td>
							<td>
								班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次:
								<SELECT name="attendancePersonInformation.classNo" style="width:150px;">
								<s:if test="%{'A00001'==attendancePersonInformation.classNo}">
								<option value="A00001" checked="checked">白班</option>
								<option value="A00002">夜班</option>
								</s:if>
								<s:else>
								<option value="A00001" >白班</option>
								<option value="A00002" checked="checked">夜班</option>
								</s:else>
								</SELECT>
							</td>
						</tr>
					
						<tr>
							<td align="center" colspan="2">
								<input type="submit" class="input" value="确认修改" />
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
	//单选按钮的鼠标点击触发事件
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
	function checkType() {
		var val = document.getElementById('name').value;//姓名不能为空
		var val2 = document.getElementById('card').value;//卡号不能为空
		var val3 = document.getElementById('no').value;//编号不能为空
		var val4 = document.getElementById('type').value;//类型不能为空
		if (!val || val == "") {
			alert("姓名不能为空！！！");
			return false;
		} else if (!va2 || va2 == "") {
			alert("卡号不能为空！！！");
			return false;
		} else if (!va3 || va3 == "") {
			alert("编号不能为空！！！");
			return false;
		} else if (!va4 || va4 == "") {
			alert("类型不能为空！！！");
			return false;
		}
		return false;
	}
</script>
	</body>
</html>
