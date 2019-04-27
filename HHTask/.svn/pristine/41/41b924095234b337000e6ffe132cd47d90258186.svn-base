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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>添加评审组别</h2>
				<form action="orderManager_updateEvaluators.action" method="POST" >
					<table class="table" style="width: 65%;">
						<tr>
							<th align="right">
								评审组别:
							</th>
							<td>
								<input type="text" value="${evaluators.groups}" name="evaluators.groups"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								评审部门:
							</th>
							<td>
								<select id="deptname" style="width: 100px;"
								onchange="userlist(0)" name="evaluators.dept">
									<option value="0">
										${evaluators.dept}
									</option>
								</select>
							</td>
						</tr>
						<tr >
							<th align="right">
								评审人员:
							</th>
							<td id="userstd">
								<select id="username" name="evaluators.userId" style="width: 100px;">
									<option value="${evaluators.userId}">
										${evaluators.userName}
									</option>
								</select>
							</td>
						</tr>
					</table>
					<input type="submit" value="提交" class="input"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var deptid = $("#deptname").val();
	if (deptid == "0") {
		$("#username").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#username");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");

						}
						$(data).each(
								function() {
									$(
											"<option value='" + this.id + "'>"
													+ this.code + "__"
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();

					},
					error:function(data){
						console.log('json:',data.responseText);
						
					}
				});
	}

}
$(document).ready(function() {
	userlist(1);
});

</SCRIPT>
	</body>
</html>
