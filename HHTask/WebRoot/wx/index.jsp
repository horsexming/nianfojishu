<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>
	<body>
		<center>
			<div style="margin-top: 35px;"></div>
			<div style="width: 50%; background-color: #666666; height: 88%;">
				<object width="100%" height="100%"
					codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0"
					classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
					<param value="flash874.swf" name="movie">
					<param value="high" name="quality">
					<param value="transparent" name="wmode">
					<param value="exactfit" name="SCALE">
					<embed width="100%" height="100%" wmode="transparent"
						type="application/x-shockwave-flash"
						pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"
						quality="high" src="flash874.swf">
				</object>
			</div>
		</center>
	</body>
</html>
