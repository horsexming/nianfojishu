<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>player.html</title>

	</head>

	<body>
		<SCRIPT LANGUAGE="Javascript"> 
var playerInstalled = 0; 
MSDetect = "false"; 
if (navigator.mimeTypes && navigator.mimeTypes.length)

{ 
x = navigator.mimeTypes['application/x-3dxmlplugin']; 
if (x && x.enabledPlugin) 
playerInstalled = 1; 
} 
else 
{ 
MSDetect = "true"; 
} 
</SCRIPT>

		<SCRIPT LANGUAGE="Javascript"> 
if (playerInstalled == 0) 
{ 
<!-- 3D XML Player is not installed - > 
} 
else 
{ 
<!-- 3D XML Player is installed - >
} 
</SCRIPT>
		<div style="border: solid #000000 1px;">

			<object type='application/x-3dxmlplugin' id='3DXMLPluginId'
				height="100%" width="100%">
				<param name='DocumentFile' value='${param.filePath}'>
			</object>
		</div>
	</body>
</html>
