<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<script type="text/javascript">

</script>
	</head>
	<!--	//onload="createDept('repairdepartment');"-->
	<body>
		<form action="RepairAction!addRepairpop.action" method="post"
			onsubmit="return checkAssessPersonnel()">
			<input type="hidden" name="test" value="${param.test}">
			<input type="hidden" name="deptname" value="${param.dept_name}">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加修理人</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						工号:
					</th>
					<td>
						<!--						<input id="employeenumber" name="responsibilities.employeenumber"  value="" />-->
						<input id="code" onblur="send(this)"
							name="responsibilities.employeenumber" value="${user.code}" />
					</td>
					<th align="right">
						姓名:  
					</th>
					<td>
						<!--						<input id="repairname" name="responsibilities.repairname" />-->
						<input id="userName" name="responsibilities.repairname" title="只读"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<!--					<th align="right">-->
					<!--						部门:-->
					<!--					</th>-->
					<!--					<td>-->
				
		 			<set var="n" value="#parameters.test" />
					<s:if test="#n==a">
						<th align="right">
							维修部门:
						</th>
						<td>
							<input id="dept" name="responsibilities.repairdepartment" readonly="readonly" title="只读" />
						</td>
					</s:if>
					<s:else>
						<input id="dept"  name="responsibilities.repairdepartment" type="text" value="" />
					</s:else>

					<!--						<select id="repairdepartment"-->
					<!--							name="responsibilities.repairdepartment" >-->
					<!--							<option selected="selected" value="0">-->
					<!--								选择部门-->
					<!--							</option>-->
					<!--							<s:iterator id="cu" value="list">-->
					<!--								<option value="${cu.ta_dept}">-->
					<!--									${cu.ta_dept}-->
					<!--								</option>-->
					<!--							</s:iterator>-->
					<!--						</select>-->
					<!--					</td>-->
					<th align="right">
						类别:
					</th>
					<td>
						<select id="repaircategory" name="responsibilities.repaircategory">
							<option value="">
								选择类别
							</option>
						</select>
						<!--						<input id="repaircategory" name="responsibilities.repaircategory" />-->
					</td>
				</tr>
				<tr>
					<th align="right">
						手机号:
					</th>
					<td>
						<input id="phone"   name="responsibilities.phone"  title="只读"  readonly="readonly"/>
					</td>
					<th align="right">
						邮箱:
					</th>
					<td>
						<input id="mailbox" name="responsibilities.mailbox"  title="只读"
							readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th  align="right">
						职责
					</th>
					<td colspan="5">
						<textarea id="repairresponsibilitiesl" rows=""
							style="width: 350px; height: 80px;" cols=""
							name="responsibilities.repairresponsibilitiesl"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="button" value="关 闭" onclick="isdown()" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function send(obj) {
	sendRequest("UsersAction!findCardIdBCodeForzgkh.action?test=1&user.code="+ obj.value, messageResponse);
}

// 人员查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var code = document.getElementById("code");//工号
			var userName = document.getElementById("userName");//姓名
			var dept = document.getElementById("dept");//部门
			var repaircategory = document.getElementById("repaircategory");//部门
			var phone = document.getElementById("phone");//部门
			var mailbox = document.getElementById("mailbox");//邮箱
			var value = message.split("|");
			if (value[1] == null) {
				userName.value = "";
				dept.value = "";
				repaircategory.value="";
				phone.value="";
				mailbox.value="";
				code.focus();
				code.select();
				code.title = message;
				code.style.border = " solid 1px red";
				return;
			} else {
				code.title = "该工号存在!";
				userName.value = value[1];
				var deptName1 = decodeURI("${param.test}");
				if(deptName1=='a'){
					dept.value = value[2];
					getClass();	
				}
				phone.value=value[4];
				mailbox.value=value[5];
				
				code.style.border = " solid 1px";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

//表单检查
function checkAssessPersonnel() {
	var userName = document.getElementById("userName");//姓名
	if (userName.value == "") {
		alert("请正确填写工号和姓名！");
		userName.focus();
		return false;
	}
	return true;
}


function getClass(){
	var repairdepartment = $("#dept").val();
	$.ajax( {
		url : "RepairAction!isChange.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"repairdepartment" : repairdepartment
		},
		success : function(data) {
			$("#repaircategory").empty();//清空
			$.each(data.data, function(i) {
					$("#repaircategory").append( "<option value='" + data.data[i].category + "' >"+ data.data[i].category + "</option>");
				})
		}
	});
}

$(function() {
	var deptName = decodeURI("${param.dept_name}");
	var deptName1 = decodeURI("${param.test}");
	if(deptName1!='a'){
		document.getElementById("dept").value = deptName;
		getClass();
	}
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
	//	parent.location.reload(true);//刷新父页面
	}
})

	//刷新父页面关闭当前窗体
	function isdown(){
		parent.location.reload(true);//刷新父页面
	}

 
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
