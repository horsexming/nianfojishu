<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<body  onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			
			<div align="center">
			<form action="QualityccAction!updateQualitychecktoRp.action?qualitycheckto.Id=${qualitycheckto.id}" 
						method="post" id="xform">
				<table class="table" style="width: 85%;">
					<tr>
						<td align="right">指定人员部门：</td>
						<td>
							<select id="dept" style="width: 155px;" name=""
										onchange="find();">
										<option></option>
									</select>
						</td>
						<td align="right">指定人员：</td>
						<td>
									<select id="users" style="width: 155px;">
									</select>
									<input name="qualitycheckto.renyuan" id="userName" type="hidden" />
						</td>
					</tr>
						<tr>
						<td align="right">
							指定人员工号：
						</td>
						<td>
						<input type="text" id="leavePersonCode" name="qualitycheckto.renyuangh"
										readonly="readonly" />
						</td>
						<td align="right">产品图号：</td>
						<td> ${qualitycheckto.leibie}</td>
					</tr>
					<tr>
						<td align="right">
							指定批次：
						</td>
						<td>
						<select id="pici" style="width: 155px;" name="qualitycheckto.pici"
										>
										<option></option>
									</select>
						</td>
						
						<td></td>
						<td></td>
					</tr>
					<tr>
							<td align="center" colspan="12">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
				</table>
			</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function findp(){
	$.ajax( {
			url : "ProcardAction!findPici.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				markid : '${qualitycheckto.leibie}'
			},
			success : function(newList) {
				$("#pici").empty();//清空
				$("<option></option>").appendTo("#pici");
				$(newList).each(
						function() {
							
							$(						
									"<option value='" + this.selfCard 
											+ "'>" + this.selfCard
											+ "</option>").appendTo("#pici")

						});
			},
			error : function() {
				alert("服务器异常!");
			}
			
		});
	
}
findp();
function find() {
	if ($("#dept").val() != "") {
		$.ajax( {
			url : "UsersAction!findUsersByDept.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				deptName : $("#dept").val()
			},
			success : function(userLsit) {
				$("#users").empty();//清空
				$("<option></option>").appendTo("#users");
				$(userLsit).each(
						function() {
							
							$(						
									"<option value='" + this.code + "|"
											+ this.name + "'>" + this.name
											+ "</option>").appendTo("#users")

						});
			},
			error : function() {
				alert("服务器异常!");
			}
		});
		$("#users").bind("change", function() {
			var users = $("#users").val();
			var usersData = users.split("|");
			var code = usersData[0];
			var userName = usersData[1];
			$("#leavePersonCode").val(code);
			$("#userName").val(userName);
		});
	}
}
		</script>
	</body>
</html>
