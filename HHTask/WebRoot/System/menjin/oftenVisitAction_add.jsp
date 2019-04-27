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
	<body onload="createDept('ndept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
			</div>

			<div align="center">
				<h3>
					添加常访车辆信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				
				<form action="InEmployeeCarInforAction_add.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right">
								被访人部门
								<input type="hidden" name="tag" value="cf" />
								<input type="hidden" name="all" value="dept" />
							</th>
							<td align="left">
								<select id="ndept" name="inEmployeeCarInfor.ndept"
									style="width: 155px;">
									<option value="">
										--请选择部门--
									</option>
								</select>
							</td>
							<th align="right">
								被访人姓名
							</th>
							<td align="left">
								<select id="name" style="width: 155px;" onclick="deptNotNull()">
									<option></option>
								</select>
								<input id="bfName" name="inEmployeeCarInfor.name" type="hidden">
							</td>
						</tr>
						<tr>
							<th align="right">
								被访人工号
							</th>
							<td align="left" colspan="3">
								<input type="text" name="inEmployeeCarInfor.ncode" id="ncode" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								常访人姓名
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.oftenname"
									id="oftenname" />
							</td>
							<th align="right">
								常访人性别
							</th>
							<td align="left">
								<SELECT name="inEmployeeCarInfor.oftenSex" id="oftenSex"
									style="width: 156px;">
									<option value="">
										请选择性别
									</option>
									<option value="男">
										男
									</option>
									<option value="女">
										女
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								常访人车牌号
								<input type="hidden" name="inEmployeeCarInfor.carInCangType"
									value="常访" />
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.nplates"
									id="nplates" />
							</td>
							<th align="right">
								常访人手机号
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.oftenTel"
									id="oftenTel" maxlength="11" />
							</td>
						</tr>
						<tr>
							<th align="right">
								汽车行驶证附件
							</th>
							<td align="left" colspan="1">
								<input type="file" name="fujian" id="carFiles" />
							</td>
							<th align="right">
								车型品牌
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.carModels"
									id="carModels" />
							</td>
						</tr>
						<tr>
							<th align="right">
								常访原因
							</th>
							<td align="left" colspan="3">
								<input type="text" name="inEmployeeCarInfor.oftenInfor"
									id="oftenInfor" style="width: 60%;" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("ndept", "被访人部门")) {
		return false;
	}
	if (!validateText("name", "被访人名称")) {
		return false;
	}
	if (!validateText("ncode", "被访人工号")) {
		return false;
	}
	if (!validateText("oftenname", "常访人姓名")) {
		return false;
	}
	if (!validateText("oftenSex", "常访人性别")) {
		return false;
	}
	if (!validateText("nplates", "常访人车牌号")) {
		return false;
	}
	if (!validateText("oftenTel", "常访人手机号")) {
		return false;
	}
	if (!validateText("carFiles", "上传车辆行驶证附件")) {
		return false;
	}
	if (!validateText("oftenInfor", "常访原因")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

//根据部门显示人员
$(function(){
	//显示部门对应的员工信息
	$("#ndept").bind(
			"change",
			function() {
				if ($("#ndept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#ndept").val()
						},
						success : function(useradsfa) {
							$("#name").empty();//清空
							$("#ncode").val("");
							$("<option></option>").appendTo("#name");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
												.appendTo("#name")
									});
								$("#name").bind("change", function() {
								var name = $("#name").val();
								var usersData = name.split("|");
								var ncode = usersData[0];
								var bfName = usersData[1];
								$("#ncode").val(ncode);
								$("#bfName").val(bfName);
							});
							
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name").empty();//清空
					$("#ncode").val("");
				}
		});
});

$("#oftenTel").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
function deptNotNull() {
	if ($("#ndept").val() == "" || $("#ndept").val() == "") {
		alert("被访人部门不能为空！");
		return false;
	}
}
</script>
	</body>
</html>
