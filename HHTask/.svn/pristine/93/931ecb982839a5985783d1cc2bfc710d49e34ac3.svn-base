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
				<table style="width: 50%;">
					<tr>
						<th align="right">
							部门:
						</th>
						<td>
							<select id="deptname" style="width: 100px;"
								onchange="userlist(0)">
								<s:if test="user!=null">
									<option value="${deptId}_${user.dept}">
										${user.dept}
									</option>
								</s:if>
								<s:else>
									<option value="0">
										请选择部门
									</option>
								</s:else>
							</select>
						</td>
						<th align="right">
							名字:
						</th>
						<td id="userstd">
							<select id="username" style="width: 100px;"
								onchange="changvalue(this)">
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
							
						</td>
					</tr>
				</table>
				</div>
				<center><h3>请选择授权部门</h3></center>
				<div>
				<form action="CheckNoteAction_Usersbinding.action" method="post" >
				<input type="hidden" value="${user.id}" id="userId" name="id" />
					<table id="mytable" class="table">
					</table>
					<div id="se_div"></div>
					<center><input type="submit" value="绑定" class="input" id="sub"/></center>
					</form>
				</div>
				
			<%@include file="/util/foot.jsp"%>
		</div>
	
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	var n=1;
	$(function(){
	$.ajax( {
		type : "POST",
		url : "DeptNumberAction!findAlldept1.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data.length>0){
				var showTable="<tr>";
				$("#mytable").append();
				for(var i=0; i<data.length; i++){
					var pt = data[i];
					showTable+=
						'<td><input type="checkbox" id="'+ pt[0]
								+ '" value="' + pt[0]
								+ '" name="deptId"/>' +pt[1]
							+ "</td>";
					 if(n%4==0){
						showTable+="</tr><tr>";
					}
					n++;
				}
				$("#mytable").append(showTable+"</tr>");
				
			}
			
		}
	})
	
})
function findList(id){
	$.ajax( {
		type : "post",
		url : "CheckNoteAction_findDept.action",
		data: {userId:id},                                 
		dataType : "json",
		success : function(data) {
			if(data!=null && data.length>0){
				for(var i=0; i<data.length; i++){
					var pt = data[i];
					$("#"+pt).attr("checked","checked");
				}
			}
			
		}
	});
}
function uncheckAll()   
{   
	
var code_Values = document.all['deptId'];  
if(code_Values.length){   
for(var i=0;i<code_Values.length;i++)   
{   
code_Values[i].checked = false;   
}   
}else{   
code_Values.checked = false;   
}   
}   

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
											"<option value='" + this.id +"_"+this.name+ "'>"
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
	var id = array[0];
	if(array.length == 2){
		$("#userId").val(array[0]);
		}
	uncheckAll();
	findList(array[0]);
	}
}


</SCRIPT>
	</body>
</html>
