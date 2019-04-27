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
	<script type="text/javascript"
	src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
</script>
<link rel="shortcut icon" href="/upload/file/sysImages/favicon.ico" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css" />
<%@include file="/util/inc.jsp"%>
		<title>嘉定共修班2106届</title>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="attendClassAction_updateLesson.action"  method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;期:</th>
							<td>
								<input type="text" name="lesson.ldate" class="Wdate" id="ldate" value="${lesson.ldate}"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课程开始时间:</th>
							<td>
								<input type="text" name="lesson.ltimestart"class="Wdate" id="ltimestart" value="${lesson.ltimestart}"
								onClick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课程结束时间:</th>
							<td>
								<input type="text" name="lesson.ltimeend"class="Wdate" id="ltimeend" value="${lesson.ltimeend}"
								onClick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;序&nbsp;&nbsp;&nbsp;号:</th>
							<td>
								<input type="text" name="lesson.lnumber" onblur="numyanzheng(this)"  value="${lesson.lnumber}"
								onkeyup="numyanzheng(this)" id="lnumber" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;容:</th>
							<td>
								<input type="text" name="lesson.content"   value="${lesson.content}"
								 id="content" >
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="${lesson.id}" name="lesson.id">
								<input type="submit" value="修改" class="input" id="sub"/>
								<input type="reset" value="重置" class="input">
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
<script type="text/javascript">	
function numyanzheng(obj){
	var ty1 = /^\d*$/;
	var bChk=ty1.test(obj.value);
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}
function check(){
	var ldate = $("#ldate").val();
	var ltimestart = $("#ltimestart").val();
	var ltimeend = $("#ltimeend").val();
	var lnumber = $("#lnumber").val();
	var content = $("#content").val();
	if(ldate ==""){
		$("#ziti_font").html("请填写课程日期");
		return false;
	}else if(ltimestart == ""){
		$("#ziti_font").html("请填写课程日开始时间");
		return false;
	}else if(ltimeend == ""){
		$("#ziti_font").html("请填写课程日结束时间");
		return false;
	}else if(lnumber == ""){
		$("#ziti_font").html("请填写课程序号");
		return false;
	}else if(content == ""){
		$("#ziti_font").html("请填写课程内容");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
}

$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</script>

	</body>
</html>
