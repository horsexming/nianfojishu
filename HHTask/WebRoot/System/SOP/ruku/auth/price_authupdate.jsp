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
	$('#submitBtn').bind('click', function() {
		$.ajax( {
			type : "POST",
			url : "WareHouseAuth!edit.action",
			data : $('#myform').serialize(),
			dataType : "json",
			success : function(msg) {
				alert(msg.message);
				$('#msgDiv').html(msg.message);
			}
		});
	});

	$
			.ajax( {
				type : "POST",
				url : "WareHouseAuth!findAllCompetenceType.action",
				data : {},
				dataType : "json",
				success : function(data) {
					if (data != null) {
						$(data)
								.each(
										function() {
											var str = '<tr><th>'
													+ this.name
													+ '</th><td><input type="checkbox" id="'
													+ this.code
													+ '_show"  value="'
													+ this.code
													+ '_show" name="auth.auth" />查看</td>'
													+ '<td><input type="checkbox" value="'
													+ this.code
													+ '_price" id="'
													+ this.code
													+ '_price"  name="auth.auth" />价格</td>'
													+ '<td><input type="checkbox" value="'
													+ this.code
													+ '_phone" id="'
													+ this.code
													+ '_phone"  name="auth.auth" />手机</td>'
													+ '<td><input type="checkbox" value="'
													+ this.code
													+ '_update" id="'
													+ this.code
													+ '_update"  name="auth.auth" />编辑</td></tr>';
											if (this.code == 'dd') {
												str = '<tr><th>'
														+ this.name
														+ '</th><td><input type="checkbox" id="'
														+ this.code
														+ '_ddshow"  value="'
														+ this.code
														+ '_ddshow" name="auth.auth" />查看</td>'
														+ '<td><input type="checkbox" value="'
														+ this.code
														+ '_ddprice" id="'
														+ this.code
														+ '_ddprice" name="auth.auth" />价格</td>'
														+ '<td><input type="checkbox" value="'
														+ this.code
														+ '_ddphone" id="'
														+ this.code
														+ '_ddphone"  name="auth.auth" />手机</td>'
														+ '<td><input type="checkbox" value="'
														+ this.code
														+ '_ddupdate"id="'
														+ this.code
														+ '_ddupdate"  name="auth.auth" />编辑</td></tr>';
											}
											$("#lasttr").before(str);
											$
													.ajax( {
														type : "POST",
														url : "WareHouseAuth!get.action",
														data : "auth.id="
																+ $('#idInput')
																		.val(),
														dataType : "json",
														success : function(msg) {
															$('#usercode')
																	.val(
																			msg.usercode);
															$('#username')
																	.val(
																			msg.username);
															$('#authSelect')
																	.val(
																			msg.group);
															var k = msg.auth
																	.split(",");
															for ( var str in k) {
																//console.log($('input[value='+ k[str] +']'));
																$('input[value=' + k[str] + ']')[0].checked = true;
																//$("#"+k[str])[0].checked = true;
															}

														}
													});
											//changeSelect();
										})
					}
				}
			});
	ischecked("show");
	ischecked("price");
	ischecked("phone");
	ischecked("update");
	ischecked("ddshow");
	ischecked("ddprice");
	ischecked("ddphone");
	ischecked("ddupdate");

});

function changeSelect() {
	var cbox = $('#myform [type=checkbox]');
	if ($('#authSelect').val() == "查看") {
		for ( var i = 0; i < cbox.length; i++) {
			if (i % 4 == 0) {
				cbox[i].checked = true;
			} else {
				cbox[i].checked = false;
			}
		}
	} else if ($('#authSelect').val() == "价格") {
		for ( var i = 0; i < cbox.length; i++) {
			if (i % 4 == 1) {
				cbox[i].checked = true;
			} else {
				cbox[i].checked = false;
			}
		}
	} else if ($('#authSelect').val() == "手机") {
		for ( var i = 0; i < cbox.length; i++) {
			if (i % 4 == 2) {
				cbox[i].checked = true;
			} else {
				cbox[i].checked = false;
			}
		}
	} else if ($('#authSelect').val() == "管理员") {
		for ( var i = 0; i < cbox.length; i++) {
			cbox[i].checked = true;
		}
	} else if ($('#authSelect').val() == "") {
		for ( var i = 0; i < cbox.length; i++) {
			cbox[i].checked = false;
		}
	}
}
function ischecked(quanxian) {
	$.ajax( {
		type : "POST",
		url : "WareHouseAuth_ischecked.action",
		data : {
			quanxian : quanxian
		},
		dataType : "json",
		success : function(data) {
			if (data != null && data.length > 0) {
				alert(data.length)
				for ( var i = 0; i < data.length; i++) {
					var str = data[i];
					var obj = document.getElementById(str + "_" + quanxian);
					if (obj != null) {
						obj.checked = "checked";
					}
				}
			}
		}
	})

}
</script>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<form id="myform" action="">
					<input id="idInput" type="hidden" name="auth.id" value="${auth.id}" />
					<table id="mytable" class="table" style="width: 70%">
						<tr>
							<th colspan="5">
								权限管理
							</th>
						</tr>
						<tr>
							<th>
								权限组
							</th>
							<td>
								<select name="auth.group" id="authSelect"
									onchange="changeSelect()">
									<option value=""></option>
									<option value="查看">
										查看
									</option>
									<option value="价格">
										价格
									</option>
									<option value="手机">
										手机
									</option>
									<option value="管理员">
										管理员
									</option>
								</select>
							</td>
							<th>
								工号
							</th>
							<td colspan="2">
								<input id="username" type="hidden" name="auth.username" />
								<input id="usercode" name="auth.usercode" />
								<font id="nameFont" color="red"></font>
							</td>
						</tr>
						<tr id="lasttr">
							<th colspan="5">
								<input type="hidden" value="价格" name="auth.type" />
								<input id="submitBtn" type="button" value="提交" />
							</th>
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
