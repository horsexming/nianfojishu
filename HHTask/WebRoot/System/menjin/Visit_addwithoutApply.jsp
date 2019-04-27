<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
function carTel(obj) {
	if (obj == '是') {
		$("#verifycar").attr("disabled", "disabled");
		$("#verifycar1").attr("checked", "checked");
		$("#chepai").show();
		$("#tel").hide();
		$("#visitsLic1").removeAttr("disabled");
		$("#visitsTel2").removeAttr("disabled");
		$("#visitsTel1").attr("disabled", "disabled");
	} else {
		$("#verifycar1").attr("checked", "checked");
		$("#visitsLic1").attr("disabled", "disabled");
		$("#visitsTel2").attr("disabled", "disabled");
		$("#visitsTel1").removeAttr("disabled");
		$("#verifycar").removeAttr("disabled");
		$("#tel").show();
		$("#chepai").hide();
	}
}
function fuZhi(obj) {
	$("#visitsLic1").val(obj);
}
function need(obj) {
	if (obj == '否') {
		$("#yzfs1").show();
		$("#yzfs").hide();
		$("#verifyManner2").attr("checked", "checked");
		$("#tel").show();
		$("#chepai").hide();
		$("#visitsTel1").removeAttr("disabled");
		$("#visitsLic1").attr("disabled", "disabled");
	} else {
		$("#yzfs1").hide();
		$("#yzfs").show();
		$("#visitsLic1").removeAttr("disabled");

	}
}
//根据部门显示人员
function changeDept() {
	//显示部门对应的员工信息
	$("#shouFangDept").bind(
			"change",
			function() {
				if ($("#shouFangDept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#shouFangDept").val()
						},
						success : function(useradsfa) {
							$("#shouFangName").empty();//清空
							$("<option></option>").appendTo("#shouFangName");
							$(useradsfa).each(
									function() {
										$(
												"<option value='" + this.name
														+ "'>" + this.name
														+ "</option>")
												.appendTo("#shouFangName")
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#shouFangName").empty();//清空
				}

			});
}

function checkForm(num) {
	var verifyManners = document.getElementsByName("visit.verifyManner");
	var verifyManner = "";
	for ( var i = 0; i < verifyManners.length; i++) {
		if (verifyManners[i].checked == true) {
			verifyManner = verifyManners[i].value;
		}
	}
	if (verifyManner == "车牌") {
		if (!validateText("visitsLic1", "来访人车牌号")) {
			return false;
		}
		//匹配车牌正则
		var car = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/
		if ($("#visitsLic1").val().search(car) == -1) {
			alert("请输入正确的车牌号");
			return false;
		}
	} else {

	}

	var applyName = document.getElementById("applyName" + num);
	var code = document.getElementById("code" + num);
	var visitsName = document.getElementById("visitsName" + num);
	var visitsTel = document.getElementById("visitsTel" + num);
	var visitstime = document.getElementById("visitstime" + num);
	var visitsPer = document.getElementById("visitsPer" + num);

	return true;

}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('shouFangDept')">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
					align="left">
				</div>

				<div align="center">
					<div align="center" style="width: 100%">
						<font color="red">${successMessage}</font>
					</div>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th colspan="12">
										今日来访车辆（未申请）:点击申请自动填充车牌号
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator id="visit" value="visitListTow" status="statussdf">
									<s:if test="#statussdf.index%6==0">
										<tr>
									</s:if>
									<td align="center">
										${visit}
									</td>
									<td align="center">
										<a onclick="fuZhi('${visit}')" style="text-decoration: none;">申请</a>
									</td>
								</s:iterator>
							</tbody>
							<tfoot>
							</tfoot>
						</table>
					</div>
					<div id="module1_1"
						style="font-weight: bold; margin-top: 10px; width: 100%;"
						align="center">
						<form action="VisitAction_addVisitWithoutApply.action"
							method="post" onsubmit="return checkForm('1')">
							<table class="table">
								<tr>
									<td colspan="4" align="center"
										style="font-family: 微软雅黑; font-weight: bold;">
										快速来访申请(免审批)
									</td>
								</tr>
								<tr>
									<td align="right">
										来访时间:
									</td>
									<td colspan="1">
										<input class="Wdate" type="text" name="visit.visitstime"
											id="visitstime1"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<tr id="chepai">
									<td align="right">
										来访人车牌号:
									</td>
									<td colspan="1">
										<input type="text" id="visitsLic1" name="visit.visitsLic"
											maxlength="7">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td align="right">
										来访人公司:
										<br />
									</td>
									<td>
										<%--										<input type="text" id="visitsCom1" name="visit.visitsCom">--%>
										<select name="visit.visitsCom">
											<s:iterator id="visitCo" value="visitCoList"
												status="statussdf">
												<option value="<s:property value="#visitCo.name" />">
													${visitCo.name}
												</option>
											</s:iterator>
										</select>

									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<input type="submit" value="提交"
											style="width: 100px; height: 50px;">
										<input type="button" value="重置"
											style="width: 100px; height: 50px;">
									</td>
								</tr>

							</table>
						</form>
					</div>
				</div>
			</div>
			<br>

			<%@include file="/util/foot.jsp"%>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript"
				src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
			<script type="text/javascript">
$(document).ready(
		function() {
			var nowDate = new Date();
			var year = nowDate.getFullYear();
			var month = nowDate.getMonth() + 1 < 10 ? "0"
					+ (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
			var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate()
					: nowDate.getDate();
			var dateStr = year + "-" + month + "-" + day;
			$(".Wdate").val(dateStr);
		});

$("#visitsTel1").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
		</center>
	</body>
</html>
