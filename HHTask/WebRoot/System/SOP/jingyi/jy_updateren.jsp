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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
					<h3>
						人
					</h3>
				</div>
				<s:if
					test="processTemplateJY.progressStatus=='未分析'||processTemplateJY.progressStatus=='分析中'">
					<form action="procardTemplateJYAction_updatejgljwr.action"
						method="post" onsubmit="return validateFrom();">
						<table>
							<tr>
								<th>
									改进方案：
								</th>
								<td>
									<textarea id="updateContext" name="updateContext" rows="5"
										cols="40">
										<s:property value="processTemplateJY.contextRen" />
									</textarea>
								</td>
							</tr>
							<tr>
								<th>
									人工节拍（准备）
								</th>
								<td>
									<input type="hidden" value="ren" name="pageStatus">
									<input type="hidden"
										value="<s:property value="processTemplateJY.id"/>"
										name="processTemplateJY.id">
									<input id="gzzhunbeijiepai"
										name="processTemplateJY.gzzhunbeijiepai"
										value="<s:property value="processTemplateJY.gzzhunbeijiepai"/>"
										onkeyup="checkNum1()" />
								</td>
							</tr>
							<tr>
								<th>
									人工节拍（操作）
									<td>
										<input id="opcaozuojiepai"
											name="processTemplateJY.opcaozuojiepai"
											value="<s:property value="processTemplateJY.opcaozuojiepai"/>"
											onkeyup="checkNum2()" />
									</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="修改" class="input">
								</td>
							</tr>
						</table>
					</form>
				</s:if>
				<s:else>
					<br />
				改进方案：<textarea id="updateContext" name="updateContext" rows="5"
						cols="40">
						<s:property value="processTemplateJY.contextLiang" />
					</textarea>
					<div id="showAll"
						style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1; width: 200px"></div>
					<br />操作人：
							<td align="center">
						<select id="deptname" style="width: 100px;" onchange="userlist()">
							<s:if test="processTemplateJY.operatorDept==null">
								<option value="0">
									请选择部门
								</option>
							</s:if>
							<s:else>
								<option value="-1">
									<s:property value="processTemplateJY.operatorDept" />
								</option>
							</s:else>
						</select>

					</td>
					<td align="center">
						<select id="username" name="users.id" style="width: 100px;">
							<s:if test="processTemplateJY.operatorUserId==null">
								<option value="0">
									请先选择部门
								</option>
							</s:if>
							<s:else>
								<option
									value="<s:property value="processTemplateJY.operatorUserId"/>">
									<s:property value="processTemplateJY.operatorName" />
								</option>
							</s:else>
						</select>
					</td>
					<input type="button" value="确定" onclick="updateren()" />
				</s:else>
				<input type="hidden" value="<s:property value='successMessage'/>"
					id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function checkNum1() {
	var gzzhunbeijiepai = $("#gzzhunbeijiepai").val();
	if (isNaN(gzzhunbeijiepai)) {
		alert("请输入数字！");
		$("#gzzhunbeijiepai").val(0);
	}
}
function checkNum2() {
	var opcaozuojiepai = $("#opcaozuojiepai").val();
	if (isNaN(opcaozuojiepai)) {
		alert("请输入数字！");
		$("#opcaozuojiepai").val(0);
	}
}
function validateFrom() {
	var updateContext = $("#updateContext").val();
	if (updateContext == null || updateContext == "") {
		alert("请填写改进方案！");
		return false;
	}
}
function updateren() {
	var username = $("#username").val();
	if (username == "" || username == 0) {
		alert("请选择操作人员!");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "procardTemplateJYAction_updateren.action",
		dataType : "json",
		data : {
			id : username,
			'processTemplateJY.id' : ${processTemplateJY.id}
		},
		success : function(data) {
			if (data.success) {
				alert("修改成功");
			} else {
				alert(data.message);
			}
		}
	});
}
$(document).ready(function(){
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                          }
		  });
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "procardTemplateJYAction_getdept.action",
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
		}
	});
});
function userlist() {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var deptid = $("#deptname").val();
	if (deptid == 0) {
		$("#username").empty();
		$("<option value='0'>请选择先选择部门</option>").appendTo("#username");
	} else {
		$
				.ajax( {
					type : "post",
					url : "procardTemplateJYAction_getusers.action",
					data : {
						id : deptid,
						'processTemplateJY.id' : ${processTemplateJY.id}
					},
					dataType : "json",
					success : function(data) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");
						$(data).each(
								function() {
									$(
											"<option value='" + this.id + "'>"
													+ this.name + "</option>")
											.appendTo("#username");
								});
					}
				});
	}

}
		</script>
	</body>
</html>
