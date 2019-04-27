<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<s:iterator id="pageggAff" value="gongyiGuichengAffixList">
			<img src="<%=basePath%>${pageggAff.url}" />
			<%--<APPLET NAME="JVue"  
			 CODE="com.cimmetry.jvue.JVue.class" 
			 CODEBASE="http://www.autovuetext.club/av"  (AUTOVUE Server 网站) 
			 ARCHIVE="jvue.jar,jogl.jar,gluegen-rt.jar" 
			 HSPACE="0" VSPACE="0" WIDTH="100%" HEIGHT="100%" VIEWASTEXT> 
			  
			 <PARAM NAME="jvueserver" VALUE="http://www.autovuetext.club:5098/servlet/VueServlet;">    (AUTOVUE Server 网站端口) 
			 <PARAM NAME="embedded" VALUE="true"> 
			 
			 
			 <PARAM NAME="filename" VALUE="<%=basePath%>${pageggAff.url}">(你们的文件路径) 
			 <PARAM NAME="cabinets" VALUE="jvue.cab"> 
			 <PARAM NAME="verbose" VALUE="false"> 
			 <PARAM NAME="format" VALUE="AUTO"> 
			
			</APPLET>  
		--%>
		</s:iterator>
	</body>
</html>
