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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>去除人员功能</h2>
				<form action="ModuleFunctionAction!delModulByUsers.action" method="POST">
					<table class="table">
						<tr>
							<th	align="right">
								工号:
							</th>
							<td>
								<input type="text" value="" name="user.code" id="code" onkeyup="getUsers(this)" onblur="getUsers(this)">
							</td>
						</tr>
						<tr>
							<th	align="right">
								姓名:
							</th>
							<td>
								<input type="text" value="" name="user.name" id="name" readonly="readonly">
								<input type="hidden" value="" id="id" name="user.id"/>
							</td>
						</tr>
					</table>
						<input type="submit" value="去除功能" class="input"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
<SCRIPT type="text/javascript">

function getUsers(obj){
	if(obj!=null && obj.value!=""){
		$.ajax( {
		type : "POST",
		url : "UsersAction!findUsersBCode.action",
		data : {
				'user.code':obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#name").val(data.name);
				$("#id").val(data.id);
			}
		}
	})
	}
}

</SCRIPT>
	</body>
</html>
