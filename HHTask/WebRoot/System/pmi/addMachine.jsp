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
		<form action="pmiManagementAction_addMachine.action" method="post"
			onsubmit="return checkOK()">
			<table class="table">
				<tr>
					<th colspan="4" align="center">
						<h3>
							添加设备
						</h3>
					</th>
				</tr>
				<tr>
					<th align="right">
						工位:
					</th>
					<td>
						<select id="workPosition" name="machine.workPosition"
							onchange="changeNo()">
							<option value="">
								--请选择工位--
							</option>
							<s:iterator value="list" id="machines" status="pageIndex">
								<option value="${workPosition}">
									${workPosition}
								</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						设备:
					</th>
					<td>
						<select id="no" name="machine.no">
							<option></option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="4" align="center">
						<input name="pmi_id" value="${pmi_id}" type="hidden">
						<input type="submit" value="添加" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function changeNo() {
	var workPosition = $("#workPosition").val();
	$.ajax( {
		url : "pmiManagementAction_findMachineByworkPosition.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"workPosition" : workPosition
		},
		success : function(data) {
			$("#no").empty();//清空
			$.each(data, function(i) {
				if (i == 0) {
					$("#no").append("<option value=''>--请选择设备--</option>");
				}
				$("#no").append(
						"<option value='" + data[i].no + "' >" + data[i].name
								+ "(" + data[i].no + ")</option>");
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});

}

//校验 
function checkOK() {
	var workPosition = document.getElementById("workPosition");
	var no = document.getElementById("no");
	if (workPosition.value == "") {
		alert("请选择工位!");
		workPosition.focus();
		return false;
	}
	if (no.value == "") {
		alert("请选择设备!");
		no.focus();
		return false;
	}
}
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>

	</body>
</html>
