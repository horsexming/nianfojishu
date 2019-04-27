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
			<form action="ModuleFunctionAction!findMfByUserId.action" method="post" onsubmit="return chek()">
					<table style="width: 50%;" class="table">
					<tr>
						<th align="right">
							部门:
						</th>
						<td>
							<select id="deptname" style="width: 100px;"
								onchange="userlist(0)">
								<option value="0">
										请选择部门
									</option>
							</select>
						</td>
						<th align="right">
							名字:
						</th>
						<td id="userstd">
							<select id="username" style="width: 100px;"
								onchange="changvalue(this)" name="id">
									<option value="0">
										请先选择部门
									</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">
							功能名称:
						</th>
						<td>
							<input type="text" name="moduleFunction.functionName" style="width: 200px; height: 34px;" />
						</td>
						<th></th>
						<th></th>
					</tr>
				</table>
				<input type="submit" value="查询"  class="input"/>
			</form>
			<s:if test="user!=null">
				<h2>
					<font color="red">${user.name}</font>功能模块绑定情况
				</h2>
			</s:if>
		<s:if test="allModuleList!=null ||moduleFunctionList!=null">
		<form action="ModuleFunctionAction!binDingModuleFunction.action" method="post">
			<table class="table">
				<thead>
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							<input type="checkbox" value="" name="checkboxs" onclick="chageAllCheck(this)" />
						</th>	
						<th>
							序号
						</th>	
						<th>
							功能名称
						</th>	
						<th>
							操作
						</th>	
					</tr>
				</thead>
				<s:if test="moduleFunctionList!=null">
					<s:iterator value="moduleFunctionList" id="pageList1" status="pagestatus1">
						<s:if test="#pagestatus1.first">
							<tr bgcolor="green">
								<th colspan="5">
									${user.name}已绑定的功能模块
								</th>
							</tr>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<th>
								<input type="checkbox" value="${pageList1.id}" name="detailSelect"  checked="checked"/>
							</th>
							<th>
								${pagestatus1.index+1}
							</th>
							<th>
								${pageList1.functionName}
							</th>
							<th>
							
							</th>
						</tr>
					</s:iterator>
				</s:if>
				<s:if test="allModuleList!=null">
					<s:iterator value="allModuleList" id="pageList2" status="pagestatus2">
						<s:if test="#pagestatus2.first">
							<tr bgcolor="red">
								<th colspan="5">
									${user.name}未绑定的功能模块
								</th>
							</tr>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<th>
								<input type="checkbox" value="${pageList2.id}" name="detailSelect" />
							</th>
							<th>
								${pagestatus2.index+1}
							</th>
							<th>
								${pageList2.functionName}
							</th>
							<th>
							
							</th>
						</tr>
					</s:iterator>
				</s:if>
				<tr>
							<s:if test="errorMessage==null">
								<td colspan="16" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="9" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
			</table>
			<input type="hidden" value="${user.id}" name="id"/>
			<input type="submit" value="绑定" class="input" />
		</form>
		</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

//查询所有部门
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "DeptNumberAction!findAllDept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id+"_"+this.dept + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});
			$("#deptname").tinyselect();
		}
	});
});
//选择人员
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var obj = $("#deptname").val();
		obj	=	obj.split("_");
		var deptid = 0;
		var deptname = "";
		if(obj!=null && obj.length == 2){
			deptid = obj[0];
			$("#dept").val(obj[1]);
		}
	if (deptid == "0") {
		$("#username").empty();
			if("${user.name}!=null && ${user.name}!=''"){
			$("<option value='${user.id}'>${user.name}</option>").appendTo("#username");
		}else{
			$("<option value='0'>请先选择部门</option>").appendTo("#username");
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
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();
					}
				});
		
	}

}
$(document).ready(function() {
	userlist(1);
});

function chek(){
	var username = $("#username").val();
	if(username == 0){
		alert("请先选择人员!");
		return false;
	}
}

</SCRIPT>
	</body>
</html>
