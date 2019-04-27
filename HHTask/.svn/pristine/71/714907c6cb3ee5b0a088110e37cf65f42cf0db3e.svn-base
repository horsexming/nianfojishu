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
		<SCRIPT type="text/javascript">
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
								"<option value='" + this.id+"_"+this.dept+"_"+this.deptNumber + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						$(
								"<option value='" + this.id+"_"+this.dept+"_"+this.deptNumber + "'>" + this.dept
										+ "</option>").appendTo("#deptname1");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
			$("#deptname1").tinyselect();
			
		}
	});
});
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var obj = $("#deptname").val();
		obj	=	obj.split("_");
		var deptid = 0;
		var deptname = "";
		if(obj!=null && obj.length == 3){
			deptid = obj[0];
			$("#beforeDeptId").val(obj[0]);
			$("#beforeDept").val(obj[1]);
			$("#befroedeptNo").val(obj[2]);
			
		}
		
	if (deptid == "0") {
		$("#username").empty();
		if("${user.name}!=null && ${user.name}!=''"){
			$("<option value='${user.name}'>${user.name}</option>").appendTo("#username");
		}else{
			$("<option value='0'>请先选择部门</option>").appendTo("#username");
		}
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
											"<option value='" +this.id+"_"+this.code+"_"+ this.name + "'>"
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
		
function changvalue(obj){
	if(obj!=null && obj.value!="" && obj.value!="0"){
		var v = obj.value;
		var array = v.split("_");
		if(array.length == 3){
			$("#userId").val(array[0]);
			$("#code").val(array[1]);
			$("#name").val(array[2]);
		}
	}
}
function changdept(obj){
	if(obj!=null && obj.value!="" && obj.value!="0"){
		var v = obj.value;
		var array = v.split("_");
		if(array.length == 3){
			$("#deptId").val(array[0]);
			$("#dept").val(array[1]);
			$("#deptNo").val(array[2]);
		}
	}
}
function check(){
	var deptname = document.getElementById("deptname");
	var deptname1 = document.getElementById("username");
	var deptname1 = document.getElementById("deptname1");
	if(deptname!=null && deptname.value=="0"){
		deptname.focus();
		$("#deptnamefont").html("请选择原来的部门");
		return false;
	}else if(username!=null && username.value=="0"){
		username.focus();
		$("#usernamefont").html("请选择被调动人姓名");
		return false;
	}else if(deptname1!=null && deptname1.value=="0"){
		deptname1.focus();
		$("#deptname1font").html("请选择所调往的部门");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}

</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					人员调动申请
					<a href="TransferAction_showtransferList.action">人员调动信息</a>
				</h2>
				<font color="red" size="5">${errorMessage}</font>
				<form action="TransferAction_addtransfer.action" method="post" onsubmit="return check()">
					<table class="table" style="width: 50%;">
						<tr>
							<th align="right">
								原来部门:
							</th>
							<td >
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
									<s:if test="user!=null && user.dept!=null">
										<option value="${user.dept}">
											${user.dept}
										</option>
									</s:if>
									<s:else>
										<option value="0">
											请选择部门
										</option>
									</s:else>
								</select>
								<input type="hidden"  name="transfer.beforeDeptId" id="beforeDeptId" value="${user.deptId}" />
								<input type="hidden"  name="transfer.beforeDept" id="beforeDept"  value="${user.dept}"/>
								<input type="hidden"  name="transfer.befroedeptNo" id="befroedeptNo" value="${user.password.deptNumber}"/>
								<font id="deptnamefont" color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								调动人名字:
							</th>
							<td  id="userstd">
								<select id="username"  style="width: 100px;" onchange="changvalue(this)">
									<s:if test="user!=null && user.name!=null">
										<option value="${user.name}">
											${user.name}
										</option>
									</s:if>
									<s:else>
										<option value="0">
											请先选择部门
										</option>
									</s:else>
								</select>
							<input type="hidden"  name="transfer.userId" id="userId" value="${user.id}"/>
							<input type="hidden"  name="transfer.code" id="code" value="${user.code}"/>
							<input type="hidden"  name="transfer.name" id="name" value="${user.name}"/>
							<font id="usernamefont" color="red"></font>
						</td>
					</tr>
					<tr>
						<th align="right">
								所调部门:
							</th>
							<td >
								<select id="deptname1"  style="width: 100px;"onchange="changdept(this)">
										<option value="0">
											请选择部门
										</option>
								</select>
								<input type="hidden" value="" name="transfer.deptId" id="deptId"/>
								<input type="hidden" value="" name="transfer.dept" id="dept"/>
								<input type="hidden" value="" name="transfer.deptNo" id="deptNo"/>
								<font id="deptname1font" color="red"></font>
							</td>
					</tr>
					<tr>
						<th align="right">
							备注:
						</th>
						<td>
							<textarea rows="8" cols="60" name="transfer.more"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="提交" id="sub" style="width: 75px;height: 35px;">
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
