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
			<input type="hidden" value="${pageStatus}" id="genggai"/>
				<form action="WorkLogAction!updateWorkLog.action"
					method="post" onsubmit="return checkForm()">
					<table class="table">
						<tr>
							<th align="right">
								标题:
							</th>
							<td>
								<input id="logTitle" type="text" name="workLog.title" size="40"  value="${workLog.title}"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								类别:
							</th>
							<td align="left">
								<select id="className" title="新类别可直接输入"
									onmouseover="createDept('className','WorkLogClassAction!findPersonWorkLogClass.action')"
									name="workLogClass.name" style="width: 275px"
									onkeypress="writeSelect(this)"
									onkeydown="window.history.forward(1);if(event.keyCode == 8){this.options[0].text ='';}">
									<option value="${workLog.workLogClass.name}">${workLog.workLogClass.name}</option>
								</select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								状态:
							</th>
							<td>
								<SPAN>${workLog.logStatus}</SPAN>
							</td>
						</tr>
						<tr id="zhipai" >
							<th align="right">
								指派状态:
							</th>
							<td>
								<input type="radio" name="workLog.zpStatus" value="待指派"
									checked="checked" id="zpStatus1" onclick="showrenwu('1')"/>
								待指派
								<input type="radio" name="workLog.zpStatus" value="指派" id="zpStatus2" onclick="showrenwu('2')"/>
								指派
							</td>
						</tr>
						<tr style="display: none;" id="rewu">
							<th align="right">
								任务人:
							</th>
							<td>
								部门：
								<select name="" id="dept" style="width: 155px;"
									onchange="showzpname()">
									<option> </option>
								</select>
								姓名：
								<select name="" id="users" style="width: 70px;">
									
								</select>
								<input type="hidden" name="workLog.dept" id="dept1" value="${workLog.dept}">
								<input type="hidden" name="workLog.code" id="code" value="${workLog.code}" />
								<input type="hidden" name="workLog.cardId" id="cardId" value="${workLog.cardId}" />
								<input type="hidden" name="workLog.userId" id="userId" value="${workLog.userId}" />
								<input type="hidden" name="workLog.userName" id="userName"
									value="${workLog.userName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								任务交期:
							</th>
							<td>
								<input type="text" id="zptime" class="Wdate"  name="workLog.zptime" value="${workLog.zptime}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"  onchange="jyzptime()">
							</td>
						</tr>
						<tr>
							<th align="right">
								内容:
							</th>
							<td align="left">
								<textarea rows="6" cols="80" name="workLog.content">${workLog.content}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="hidden" name="workLog.id" value="${workLog.id}" id="worklogid">
								<input type="hidden" name="pageStatus" value="zhipai"/>
								<input type="submit" value="提交"
									style="width: 80px; height: 50px" id="submit">
								<input type="reset" value="重置" style="width: 80px; height: 50px">
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${successMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
//级联查询出部门所对应的所有人员
var select;
function showzpname() {
	deptname = document.getElementById("dept").value;
	select = document.getElementById("users");
	if (deptname != "") {
		document.getElementById("dept1").value=deptname;
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
		},
		error : function() {
			alert("服务器异常!");
		}
		});
	} else {
		$("#users").empty();//清空
	}
}
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
function checkForm() {
	var title = document.getElementById("logTitle");
	var className = document.getElementById("className");
	var dept=document.getElementById("dept");
	var name=document.getElementById("users");
	var zptime=document.getElementById("zptime");
	if (title.value == "") {
		alert("标题不能为空!");
		title.focus();
		return false;
	} else if (className.value == "") {
		alert("类别不能为空!");
		className.focus();
		return false;
	} else if(dept.value == ""){
		alert("任务人部门不能为空");
		dept.focus();
		return false;
	}else if(name.value == ""){
		alert("任务人姓名不能为空")
		name.focus();
		return false;
	}else if(zptime.value == ""){
		alert("任务交期不能为空");
		zptime.focus();
		return false;
	}
	document.getElementById("submit").disabled="disabled";
	return true;
	}
function showrenwu(num){
	var zpStatus = document.getElementById("zpStatus"+num);
	var rewu = document.getElementById("rewu");
	
	if("1"==num){
		if(zpStatus.checked==true){
			rewu.setAttribute("style","display: none;");
		}else{
			rewu.removeAttribute("style")
		}
	}else{
		if(zpStatus.checked==true){
			rewu.removeAttribute("style")
		}else{
			rewu.setAttribute("style","display: none;");
		}
	}
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
	
function jyzptime(){
	$.ajax({
		type:"POST",
		url:"WorkLogAction!jyzptime.action",
		data:{'workLog.id':$("#worklogid").val(), 'workLog.zptime':$("#wxstarttime").val()},
		datatype:'json',
		success:function(data){
			if(data!=""&&data!=null){
				alert(data);
			$("#zptime").val("");
			$("#zptime").focus();
			}
			
		}
	})
}
$(function(){
	 var genggai=$("#genggai").val();
	if(genggai == "genggai"){
		document.getElementById("zhipai").style.display="none";
		document.getElementById("rewu").removeAttribute("style");
	}	 
});
</SCRIPT>
		</body>
</html>
<%
	request.getSession().removeAttribute("mfNames");
	request.getSession().removeAttribute("moduleFunction");
%>