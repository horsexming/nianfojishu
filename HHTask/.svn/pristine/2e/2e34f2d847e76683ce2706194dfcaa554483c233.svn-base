<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<%@include file="/util/sonHead.jsp"%>
		<title>打印预览</title>
	</head>
	<body style="background: #ffffff url('') repeat-x;">
		<center>
			<div id="printDiv">
				<div id="shuiyin"
					style="left: 0px; top: 20px; POSITION: absolute; width: 100%"
					align="center">
					<span class="waterMark"><img hspace="0"
							src="<%=basePath%>${companyInfo.shuiyinpng}"> </span><span
						class="waterMark"><img hspace="0"
							src="<%=basePath%>${companyInfo.shuiyinpng}"> </span><span
						class="waterMark"><img hspace="0"
							src="<%=basePath%>${companyInfo.shuiyinpng}"> </span><span
						class="waterMark"><img hspace="0"
							src="<%=basePath%>${companyInfo.shuiyinpng}"> </span>
				</div>
				<div id="contDiv">

				</div>
			</div>
			<br />
			<br />
			<input type="button" value="打印" onclick="pagePrint('printDiv')"
				style="width: 80px; height: 50px;">
		</center>
		<script>
$(function() {
	var printBodyHTML = window.dialogArguments;
	document.getElementById("contDiv").innerHTML = printBodyHTML.name;
	var conHeight = parseFloat($("#contDiv").css("height"));//contentDiv div的高度
	var syHeight = parseFloat($("#shuiyin").css("height"));//shuiyin div的高度
	var px = (conHeight - syHeight) / 2;
	//调整水印的上边距，使其居中
	$("#shuiyin").css("top", px + "px");
})
</script>
	</body>
</html>
