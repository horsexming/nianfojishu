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
	<!--  onload="createDept('repairdepartment');" -->
	<body>
	<%@include file="/util/sonTop.jsp"%>
		<s:if test="text!=0">
			<form action="RepairAction!updateRepairpop.action?t=1" method="post" onsubmit="return checkAssessPersonnel()">
		</s:if>
		<s:else>
			<form action="RepairAction!updateRepairpop.action" method="post" onsubmit="return checkAssessPersonnel()">
		</s:else>
		
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">修改修理人信息</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						工号:
					</th>
					<td>
<!--						<input id="employeenumber" name="responsibilities.employeenumber"-->
<!--							value="${responsibilities.employeenumber}" />-->
							<input id="code" onblur="send(this)" name="responsibilities.employeenumber" value="${responsibilities.employeenumber}" />
					</td>
					<th align="right">
						姓名:
					</th>
					<td>
<!--						<input id="repairname" name="responsibilities.repairname"  value="${responsibilities.repairname}"/>-->
							<input id="userName" name="responsibilities.repairname" title="只读" readonly="readonly"  value="${responsibilities.repairname}"/>
					</td>
				</tr>
				<tr>
<!--					<th align="right">-->
<!--						部门:-->
<!--					</th>-->
<!--					<td>-->
<!--						<select id="repairdepartment"-->
<!--							name="responsibilities.repairdepartment" >-->
<!--							<option selected="selected" value="${responsibilities.repairdepartment}">-->
<!--							${responsibilities.repairdepartment}-->
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
					<input  id="repairdepartment"  name="responsibilities.repairdepartment" type="hidden"  value=" ${responsibilities.repairdepartment}"/>
					<select id="repaircategory" name="responsibilities.repaircategory">
					<option value=""></option>
<!--					<option selected="selected" value="${responsibilities.repaircategory}">${responsibilities.repaircategory}</option>-->
					</select>
<!--						<input id="repaircategory" name="responsibilities.repaircategory"  value="${responsibilities.repaircategory}"/>-->
					</td>
				</tr>
				<tr>
					<th align="right">
						手机号:
					</th>
					<td>
						<input id="phone" onblur="send(this)"
							name="responsibilities.phone"  value="${responsibilities.phone}"/>
					</td>
					<th align="right">
						邮箱:
					</th>
					<td>
						<input id="mailbox" name="responsibilities.mailbox"  value="${responsibilities.mailbox}"/>
					</td>
				</tr>
				<tr>
					<th>
						职责
					</th>
					<td colspan="5">
						<textarea id="repairresponsibilitiesl" rows=""
							style="width: 350px; height: 80px;" cols=""
							name="responsibilities.repairresponsibilitiesl">${responsibilities.repairresponsibilitiesl}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
					<input  name="responsibilities.id"  value="${responsibilities.id}" type="hidden"/>
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="reset" value="取  消" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<%@include file="/util/foot.jsp"%>
<script type="text/javascript"  src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		function send(obj) {
	sendRequest("UsersAction!findCardIdBCodeForzgkh.action?user.code="
			+ obj.value, messageResponse);
}

// 人员查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var code = document.getElementById("code");//工号
			var userName = document.getElementById("userName");//姓名
			var value = message.split("|");
			if (value[1] == null) {
				userName.value = "";
				code.focus();
				code.select();
				code.title = message;
				code.style.border = " solid 1px red";
				return;
			} else {
				code.title = "该工号存在!";
				userName.value = value[1];
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
	
	
		$(function(){
			var repairdepartment = $("#repairdepartment").val();
			$.ajax( {
				url : "RepairAction!isChange.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"repairdepartment" : repairdepartment
				},
				success : function(data) {
//				$("#repaircategory").empty();//清空
				$.each(data.data,function(i){
					$("#repaircategory").append("<option value='" + data.data[i].category+ "'>"+ data.data[i].category +"</option>");
				});
				var values = "${responsibilities.repaircategory}";
				$("#repaircategory").find("option[value='"+values+"']").attr("selected",true);
				},
				error : function() {
					alert("服务器异常!");
				}
			});
			
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
		
		})
//$(function() {
//	var errorMessage = '${errorMessage}';
//	if (errorMessage != "") {
//		alert(errorMessage);
//		parent.location.reload(true);//刷新父页面
//	}
//
//	$("#isok").click(function() {
//		var employeenumber = $("#employeenumber").val();
//		var repairname = $("#repairname").val();
//		var repairdepartment = $("#repairdepartment").val();
//		var repaircategory = $("#repaircategory").val();
//		var repairresponsibilitiesl = $("#repairresponsibilitiesl").val();
//		var phone = $("#phone").val();
//		var mailbox = $("#mailbox").val();
//		if ($("#employeenumber").val() == "" || $("#repairname").val() == "") {
//			alert("工号和姓名不能为空!");
//			return false;
//		} else {
//			var retNum = "";
//			$.ajax( {
//				url : "RepairAction!isOK.action",
//				data : {
//					"employeenumber" : employeenumber,
//					"repairname" : repairname
//				},
//				type : 'POST',
//				dataType : "json", // 可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
//				async : false,
//				success : function(data) {
//					if (data.success) {
//						retNum = data.message;
//					}
//				}
//			})
//			if (retNum == "1") {
//				alert("工号和姓名有误!");
//				return false;
//			}
//		}
//	});
//
//});
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
