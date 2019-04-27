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
	</head>
	<body >
		<form action="signatureAction_addSignature.action?test=<s:property value="#parameters.test"/>"  enctype="multipart/form-data"  method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加电子签</font> 
					</th>
				</tr>
				<%
					if(request.getParameter("test")!=null&&request.getParameter("test").equals("0")){
						%>
						<tr>
						<th align="right">
						工号:
					</th>
					<td>
						<input id="code" onblur="send(this)"
							name="signature.code" value="" />
					</td>
					<th align="right">
						姓名:  
					</th>
					<td>
						<input id="userName" name="signature.name" title="只读"
							readonly="readonly" />
					</td>
				</tr>
					<%	
					}
				%>					
				<tr>
				<th align="right">
						电子签名:
					</th>
					<td colspan="3">
						<input id="signature_address"  type="file"  name="signature_address" />
<%--						<input  type="hidden" name="signature.code"  value="${sessionScope.Users.code}"/>--%>
<%--						<input  type="hidden" name="signature.name"  value="${sessionScope.Users.name}"/>--%>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
	
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})

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
				//var deptName1 = decodeURI("${param.test}");
				code.style.border = " solid 1px";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}

</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</html>
