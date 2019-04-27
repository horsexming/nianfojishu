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
			<font id="ziti_font" color="red" size="5">${errorMessage}</font>
				<form action="UsersAction!addUser.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>学员姓名:</th>
							<td>
								<input type="text"  name="user.name" id="name">
							</td>
						</tr>
						<tr>
							<th>学员姓别:</th>
							<td>
								<input type="radio" value="男" name="user.sex" checked="checked">男
								<input type="radio" value="女" name="user.sex" >女
							</td>
						</tr>
						<tr>
							<th>学员手机号:</th>
							<td>
								<input type="text"  name="user.password.phoneNumber" id="phoneNumber">
							</td>
						</tr>
						<tr>
							<th>学员身份证号:</th>
							<td>
								<input type="text"  name="user.uid" id="uid">
							</td>
						</tr>
						<tr>
							<th>学员编号:</th>
							<td>
								<input type="text"  name="user.code" id="code">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="fx" name="pageStatus">
								<input type="hidden" value="JC15"  name="user.password.deptNumber"/>
								<input type="hidden" value="JC15" name="user.dept"/>
								<input type="hidden" value="在职" name="user.onWork"/>
								<input type="hidden" value="学生" name="user.classrole"/>
								<input type="submit" value="添加" class="input" id="sub"/>
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="添加成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
function check(){
	var name =$("#name").val();
	var phoneNumber =$("#phoneNumber").val();
	var uid =$("#uid").val();
	var code =$("#code").val();
	if(name == ""){
		$("#ziti_font").html("请填写学员姓名");
		return false;
	}else if(phoneNumber == ""){
		$("#ziti_font").html("请填写学员手机号");
		return false;
	}else if(code == ""){
		$("#ziti_font").html("请填写学员编号");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}
</SCRIPT>
	</body>
</html>
