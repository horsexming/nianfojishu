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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="IntegralGiftAction_addindian.action" method="post" onsubmit="check()">
					<table>
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<select name="indianaGift.dept" id="dept"
									style="width: 155px;" onchange="showzpname()">
									<option value=""></option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								姓名:
							</th>
							<td>
								<select name="" id="users" style="width: 155px;" onclick="sel()">
									<option value=""></option>
								</select>
								<input type="hidden" name="indianaGift.name" id="userName"
									value="" />
									<input type="hidden" id="userId" value="" name="indianaGift.userId">
								<input type="hidden" id="code" name="indianaGift.code" />	
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="报名夺宝" id="sub" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var select;
function showzpname() {
	deptname = document.getElementById("dept").value;
	select = document.getElementById("users");
	if (deptname != "") {
		$.ajax( {
			type : "POST",
			url : "UsersAction!findUsersByDept.action",
			cache : false,//防止数据缓存
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				deptName : deptname
			},
			dataType : "json",
			success : function(useradsfa) {
				$("#users").empty();//清空
			$("<option></option>").appendTo("#users");
			$(useradsfa).each(
					function() {
						$(
								"<option value='" + this.code + "|" + this.name
										+ "|" + this.id + "|" + this.cardId
										+ "'>" + this.name + "</option>")
								.appendTo("#users")
					});
			$("#users").bind("change", function() {
				var user = $("#users").val();
				var userCodeName = user.split("|");
				if (userCodeName != "") {
					$("#code").val(userCodeName[0]);
					$("#userName").val(userCodeName[1]);
					$("#userId").val(userCodeName[2]);
					$("#cardId").val(userCodeName[3]);
				} else {
					$("#code").val("");
					$("#userName").val("");
				}
			})

		},
		error : function() {
			alert("服务器异常!");
		}
		});
	} else {
		$("#users").empty();//清空
	}
}
function sel(){
	var dept=document.getElementById("dept").value;
	if(dept==""){
		alert("请先选择部门")
	}
}
var totalIntegral ='${param.totalIntegral}' ;
function bijiao(){
	var addintegral = $("#addintegral").val();
	if(addintegral!=null && addintegral!=''){
		addintegral = parseInt(addintegral);
		totalIntegral =  parseInt(totalIntegral);
		if(addintegral>totalIntegral){
			alert("您所剩余的积分不足"+addintegral+",请从新输入");
			$("#addintegral").val('');
		}
	}
}
function check(){
	var dept = $("#dept").val();
	var userName = $("#userName").val();
	var addintegral = $("#addintegral").val();
	if(dept==''){
		alert("请选择部门");
		$("#dept").focus();
		return false;
	}else if(userName == ''){
		alert("请选择人员");
		$("#userName").focus();
		return false;
	}else if(addintegral == ''){
		alert("请填写赠送积分");
		$("#addintegral").focus();
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="赠送成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</SCRIPT>
	</body>
</html>
