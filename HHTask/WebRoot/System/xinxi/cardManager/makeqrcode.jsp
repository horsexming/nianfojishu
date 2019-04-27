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
		<div id="gongneng" style="width: 100%;" align="center">
			<div align="center" id="showdiv">
				<form id="form" onsubmit="return validate();">
					<input id="codetext" name="codetext">
				</form>
				<table id="showtable" style="display: none;">
					<tr>
						<td>
							<img id="showcode" alt="" src=""
								style="width: 20mm; height: 20mm;">
						</td>
						<td>
							<label id="showfont"></label>
						</td>
					</tr>
				</table>
			</div>
			<button id="printimg" onclick="pagePrint('showdiv')"
				style="display: none;">
				打印
			</button>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
 onload = function() {
	document.getElementById("codetext").onchange();
}

function validate() {
	$.ajax( {
		type : "POST",
		url : "airtightLogAction_createMatrixQr.action",
		dataType : "json",
		data : {
			content : $("#codetext").val()
		},
		success : function(data) {
			if(!data.success){
				alert("该内容无效！");
				return;
			}
			$("#showcode").attr("src", data.message);
			$("#showtable").show();
			$("#printimg").show();
			$("#form").hide();
			var font = data.data.customerNumber+"<br/>"+data.data.type+"<br/>"+data.data.markId+"<br/>"
					+ $("#codetext").val();
			$("#showfont").html(font);
		}
	});
	return false;
}
</script>
</html>
