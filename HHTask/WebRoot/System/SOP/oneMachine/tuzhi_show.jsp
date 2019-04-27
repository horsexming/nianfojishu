<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<!--Meta setup - Required for apple device-->
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!--Required libraries-->
		<script src="<%=basePath%>js/pirture/min/jquery-v1.10.2.min.js"
			type="text/javascript">
</script>
		<script
			src="<%=basePath%>js/pirture/min/modernizr-custom-v2.7.1.min.js"
			type="text/javascript">
</script>
		<script src="<%=basePath%>js/pirture/min/hammer-v2.0.3.min.js"
			type="text/javascript">
</script>

		<!--Include flickerplate-->
		<link href="<%=basePath%>css/pirture/flickerplate.css" type="text/css"
			rel="stylesheet">
		<script src="<%=basePath%>js/pirture/min/flickerplate.min.js"
			type="text/javascript">
</script>

		<!--Execute flickerplate-->
		<script>
$(function() {
	$('.flicker-example').flickerplate( {
		auto_flick : true,
		auto_flick_delay : 8,
		flick_animation : 'transform-slide'
	});
});
</script>

		<!-- Demo styles -->
		<link href="<%=basePath%>css/pirture/demo.css" type="text/css"
			rel="stylesheet">

	</head>
	<body>
		<!--Basic example-->
		<div class="flicker-example">
			<s:if test="errorMessage!=null">
			<div align="center">
				<font color="red">${errorMessage}</font>
			</div>
			</s:if>
			<ul>
				<s:iterator id="tuzhi" value="list2" status="pageStatus">
					<li data-background="<%=basePath%>${tuzhi}">
						<div class="">
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</body>
</html>