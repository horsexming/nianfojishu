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
		$("<option value='${transfer.name}'>${transfer.name}</option>").appendTo("#username");
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
				<h2>人员调动修改</h2>
				<s:if test="status=='xg'">
				<form action="TransferAction_addtransfer.action" method="post" onsubmit="return check()">
					<table class="table" style="width: 50%;">
						<tr>
							<th align="right">
								原来部门:
							</th>
							<td >
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
									<s:if test="transfer.beforeDept!=null">
										<option value="${transfer.beforeDept}">
											${transfer.beforeDept}
										</option>
									</s:if>
									<s:else>
										<option value="0">
											请选择部门
										</option>
									</s:else>
								</select>
								<input type="hidden" value="${transfer.beforeDeptId}"  name="transfer.beforeDeptId" id="beforeDeptId" />
								<input type="hidden" value="${transfer.beforeDept}"  name="transfer.beforeDept" id="beforeDept" />
								<input type="hidden"  value="${transfer.befroedeptNo}" name="transfer.befroedeptNo" id="befroedeptNo" />
								<font id="deptnamefont" color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								调动人名字:${transfer.name}
							</th>
							<td  id="userstd">
								<select id="username"  style="width: 100px;" onchange="changvalue(this)">
								<s:if test="transfer.name!=null">
									<option value="${transfer.name}">
										${transfer.name}
									</option>
								</s:if>
								<s:else>
								<option value="0">
										请先选择部门
									</option>
								</s:else>
									
								</select>
							<input type="hidden" value="${transfer.userId}" name="transfer.userId" id="userId"/>
							<input type="hidden" value="${transfer.code}" name="transfer.code" id="code"/>
							<input type="hidden"  value="${transfer.name}"	name="transfer.name" id="name"/>
							<font id="usernamefont" color="red"></font>
						</td>
					</tr>
					<tr>
						<th align="right">
								所调部门:
							</th>
							<td >
								<select id="deptname1"  style="width: 100px;"onchange="changdept(this)">
								<s:if test="transfer.dept!=null">
									<option value="${transfer.dept}">
											${transfer.dept}
									</option>
								</s:if>
								<s:else>
									<option value="0">
											请选择部门
										</option>
								</s:else>
										
								</select>
								<input type="hidden" value="${transfer.deptId}" name="transfer.deptId" id="deptId"/>
								<input type="hidden" value="${transfer.dept}" name="transfer.dept" id="dept"/>
								<input type="hidden" value="${transfer.deptNo}" name="transfer.deptNo" id="deptNo"/>
								<font id="deptname1font" color="red"></font>
							</td>
					</tr>
					<tr>
						<th align="right">
							备注:
						</th>
						<td>
							<textarea rows="8" cols="60" name="transfer.more">
								${transfer.more}
							</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="修改" id="sub" style="width: 75px;height: 35px;">
							<input type="reset" value="重置"  style="width: 75px;height: 35px;">
						</td>
					</tr>
					</table>
				</form>
			</s:if>
			<s:else>
			<h2>
				<a href="TransferAction_showtransfer.action?id=${transfer.userId}&status=person">历史记录 </a>
			</h2>
				<table class="table" style="width: 50%;">
					<tr>
						<th align="right">调动人姓名:</th>
						<td>${transfer.name}</td>
						<th align="right">调动前部门:</th>
						<td>${transfer.beforeDept}</td>
					</tr>	
					<tr>
						<th align="right">所调部门:</th>
						<td>${transfer.dept}</td>
						<th align="right"> 申请人:</th>
						<td>${transfer.sqName}</td>
					</tr>	
					<tr>
						<th align="right">申请时间:</th>
						<td>${transfer.sqTime}</td>
						<th align="right">调动时间:</th>
						<td>${transfer.dgTime}</td>
					</tr>	
					<tr>
						<th align="right">备注:</th>
						<td colspan="3">
							<textarea rows="8" cols="60">${transfer.more}</textarea>
						</td>
					</tr>	
				</table>
			</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
