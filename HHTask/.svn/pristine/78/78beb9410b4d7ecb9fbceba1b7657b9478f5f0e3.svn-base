<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>

	<body>
		<form action="performsingleAction_jumpaddBargain.action?tag=self"
			method="post" onsubmit="return check()">
			<table align="center" class="table">
				<tr>
					<td align="center" colspan="2">
						<h1>
							采购执行单缘由
						</h1>
					</td>
				</tr>
				<tr>
					<th>
						采购来源
					</th>
					<th align="left">
						<select id="source" name="bargain_source">
							<option value="">
								--请选择议价来源--
							</option>
							<option value="OA">
								项目编号
							</option>
							<option value="SB">
								设备维修
							</option>
							<option value="设备">
								设备
							</option>
							<option value="KVP">
								KVP
							</option>
							<%--    				<option value="XBYJ">询比议价</option>--%>
							<%--    				<option value="QT">其他</option>--%>
							<%--					<option value="紧急采购">紧急采购</option>--%>
							<option value="零部件及工序外委采购">
								零部件及工序外委采购
							</option>
							<option value="原材料采购">
								原材料采购
							</option>
							<option value="包装物">
								包装物
							</option>
						</select>
					</th>
				</tr>
				<tr>
					<th>
						单号
					</th>
					<td align="left" id="checkBoxs">
					</td>
				</tr>
				<tr>
					<th>
						名称
					</th>
					<th align="left">
						<input id="purchase_name" name="purchase_name" value="">
					</th>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="下一步">
					</th>
				</tr>
			</table>
		</form>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$("#source").change(function() {
	getClass();
});
function getClass() {
	var source = $("#source").val();
	$.ajax( {
		url : "bargainAction_findbargainSource2.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"source" : source
		},
		success : function(data) {
			$("#checkBoxs").empty();//清空
			$.each(data, function(i, n) {
				if (source == 'OA') {
					if (i == 0) {
						$("#checkBoxs").append("<font>请选择OA单号:</font><br/>");
					}
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_num
										+ "<br/>");
					} else {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_num
										+ "");
					}
				}
				if (source == 'SB') {
					if(i==0){	$("#checkBoxs").append("<select id='number1' name='bargain_num'></select>");
								$("#number1").append("<option value=''>--请选择设备单号--</option>");
							}
							$("#number1").append("<option value='" + n.bargain_num+ "'>"+ n.bargain_num +"</option>");
				}
				if (source == 'KVP') {
					if (i == 0) {
						$("#checkBoxs").append("<font>请选择KVP单号:</font><br/>");
					}
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n + "'>" + n + "<br/>");
					} else {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n + "'>" + n + "");
					}
				}
				if (source == 'XBYJ') {
					if (i == 0) {
						$("#checkBoxs").append("<font>请选择询比议价单号:</font><br/>");
					}
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_num
										+ "<br/>");
					} else {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_num
										+ "");
					}
				}
				if (source == '紧急采购') {
					if (i == 0) {
						$("#checkBoxs").append("<font>请选择紧急采购物:</font><br/>");
					}
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n + "'>" + n + "<br/>");
					} else {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n + "'>" + n + "");
					}
				}
				if (source == '原材料采购') {
					if (i == 0) {
						$("#checkBoxs").append("<select id='number1' name='bargain_num'></select>");
						$("#number1").append("<option value=''>--请选择原材料采购物--</option>");
					}
						$("#number1").append("<option value='"+n+"'>"+n+"</option>");
				}
				if (source == '包装物') {
					if (i == 0) {
						$("#checkBoxs").append("<select id='number1' name='bargain_num'></select>");
						$("#number1").append("<option value=''>--请选择请选择包装物--</option>");
					}
					$("#number1").append("<option value='"+n+"'>"+n+"</option>");
				}
				if (source == '设备') {
					if (i == 0) {
						$("#checkBoxs").append("<font>请选择设备单号:</font><br/>");
					}
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_number+"、"+n.bargain_num + "<br/>");
					} else {
						$("#checkBoxs").append(
								"<input type='checkbox' id='number' name='bargain_num' value='"
										+ n.bargain_num + "'>" + n.bargain_number+"、"+n.bargain_num + "");
					}
				}
				if (source == '零部件及工序外委采购') {
						$("#checkBoxs").append(
								"<input type='checkbox' id='bgoodsIds' name='ids' value='"
										+ n.id + "'>" + n.goods_format +","+n.goods_name + "&nbsp");
					if ((i + 1) % 5 == 0) {
						$("#checkBoxs").append("<br />");
					}
				}
			})
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function check() {
	var source = document.getElementById("source");
	var number = document.getElementById("number");
	var purchase_name = document.getElementById("purchase_name");
	if (source.value == "") {
		alert("请选择议价来源!");
		source.focus();
		return false;
	}
	if (number.value == "") {
		alert("请选择单号!");
		number.focus();
		return false;
	}
	if (purchase_name.value == "") {
		alert("请填写名称!");
		purchase_name.focus();
		return false;
	}

}
</script>

</html>
