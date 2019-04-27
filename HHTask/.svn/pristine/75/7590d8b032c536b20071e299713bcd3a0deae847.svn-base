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
								"<option value='" + this.id+"_"+this.dept + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
						//userlist($("#deptname").val());
					});
			$("#deptname").tinyselect();
			
		}
	});
});
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
			$("<option value='${user.name}'>${user.name}</option>").appendTo("#username");
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
											"<option value='" +this.id+"_"+this.code+"_"+ this.name+"_"+this.post+"_"+this.duty + "'>"
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
		if(array.length == 5){
			$("#userId").val(array[0]);
			$("#code").val(array[1]);
			$("#name").val(array[2]);
			if(array[3] != null && array[3]!="" && array[3]!="null"){
				$("#beforeRank").val(array[3]);
			}
			if(array[4] != null && array[4]!="" && array[4]!="null"){
				$("#beforeDuty").val(array[4]);
				$("#duty").val(array[4]);
			}
		}
	}
}

function check(){
	var deptname = document.getElementById("deptname");
	var username = document.getElementById("username");
	var rank = document.getElementById("rank");
	if(deptname!=null && deptname.value=="0"){
		deptname.focus();
		$("#deptnamefont").html("请晋升人的部门");
		return false;
	}else if(username!=null && username.value=="0"){
		username.focus();
		$("#usernamefont").html("请选择晋升人的姓名");
		return false;
	}else if(rank!=null && rank.value==""){
		rank.focus();
		$("#rankfont").html("请选择晋升职级");
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
					<a href="PromotionAction_showAllpnlist.action">人员晋升信息</a>
				</h2>
				<font color="red" size="5">${errorMessage}</font>
				<form action="PromotionAction_addpn.action" method="post" onsubmit="return check()" enctype="multipart/form-data">
					<table class="table" style="width: 70%;">
						<tr>
							<th align="right">
								晋升人部门
							</th>
							<td colspan="3">
								<select id="deptname" style="width: 100px;"
									onchange="userlist(0)">
									<s:if test="user!=null">
										<option value="user.dept">
											${user.dept}
										</option>
									</s:if>
									<s:else>
										<option value="0">
											请选择部门
										</option>
									</s:else>
								</select>
								<input type="hidden" value="${user.dept}"  name="pn.dept" id="dept" />
								<font id="deptnamefont" color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								调动人名字
							</th>
							<td  id="userstd" colspan="3">
								<select id="username"  style="width: 100px;" onchange="changvalue(this)">
									<s:if test="user!=null">
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
							<input type="hidden" value="${user.id}"  name="pn.userId" id="userId"/>
							<input type="hidden"  value="${user.code}" name="pn.code" id="code"/>
							<input type="hidden" value="${user.name}" name="pn.name" id="name"/>
							<font id="usernamefont"  color="red"></font>
						</td>
					</tr>
					<tr>
						<th align="right">
							原&nbsp;来&nbsp;&nbsp;职&nbsp;级
						</th>
						<td>
							<input type="text" id="beforeRank" name="pn.beforeRank" value="${user.post }"
							 style="width: 200px;height:35px;" readonly="readonly">
<%-- 							<select id="beforeRank" name="pn.beforeRank" style="width: 200px;height:35px;" > --%>
<%-- 								<s:if test="user!=null && user.post!=null"> --%>
<%-- 									<option value="${user.post}" selected="selected">${user.post}</option> --%>
<!-- 									<option value="GP">GP</option> -->
<!-- 									<option value="DM">DM</option> -->
<!-- 									<option value="DGM">DGM</option> -->
<!-- 									<option value="SW">SW</option> -->
<!-- 									<option value="GM">GM</option> -->
<%-- 								</s:if> --%>
<%-- 								<s:else> --%>
<!-- 									<option value="">请选择</option> -->
<!-- 									<option value="GP">GP</option> -->
<!-- 									<option value="DM">DM</option> -->
<!-- 									<option value="DGM">DGM</option> -->
<!-- 									<option value="SW">SW</option> -->
<!-- 									<option value="GM">GM</option> -->
<%-- 								</s:else> --%>
<%-- 							</select> --%>
							
							
						</td>
						<td>
							原&nbsp;来&nbsp;&nbsp;职&nbsp;务
						</td>
						<td>
							<input type="text" id="beforeDuty" name="pn.beforeDuty" value="${user.duty }"
								style="width: 200px;height:35px;" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th align="right">
							晋&nbsp;升&nbsp;&nbsp;职&nbsp;级
						</th>
						<td>
							<select id="rank" name="pn.rank" style="width: 200px;height:35px;">
							</select>
							<font id="rankfont" color="red"></font>
						</td>
						<td>
							晋&nbsp;升&nbsp;&nbsp;职&nbsp;务
						</td>
						<td>
							<input type="text" id="duty" name="pn.duty" value="${user.duty }" style="width: 200px;height:35px;"> 
						</td>
					</tr>
					<tr align="right">
						<th>上&nbsp;传&nbsp;&nbsp;附&nbsp;件</th>
						<td align="left" colspan="3">
							<input type="file"  name="attachment" />
						</td>
					</tr>
					<tr>
						<th align="right">
							事&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由
						</th>
						<td colspan="3">
							<textarea rows="8" cols="60" name="pn.cause"></textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
						</th>
						<td colspan="3">
							<textarea rows="8" cols="60" name="pn.more"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" value="提交" id="sub" style="width: 75px;height: 35px;">
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			$(function(){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/UsersAction!findRank.action?pageStatus=ajax",
					dataType:"json",
					success:function(data){
						if(data!=null){
							$("#rank").append("<option value=''>请选择</option>");
							$(data).each(function(){
								$("#rank").append("<option value='"+this.code+"'>"+this.name+"</option>");
							});
						}
					},error:function(){
						alert("系统异常");
					}
				});
			});
		
		
		</script>
	</body>
</html>
