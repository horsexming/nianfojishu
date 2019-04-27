<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
	<HEAD>
		<TITLE>组织架构</TITLE>
<%--		<LINK REL="SHORTCUT ICON" HREF="<%=PATH%>/FAVICON.ICO" />--%>
<%--		<META HTTP-EQUIV=CONTENT-TYPE CONTENT="TEXT/HTML; CHARSET=GB2312">--%>
<%--		<META HTTP-EQUIV=PRAGMA CONTENT=NO-CACHE>--%>
<%--		<META HTTP-EQUIV=CACHE-CONTROL CONTENT=NO-CACHE>--%>
<%--		<META HTTP-EQUIV=EXPIRES CONTENT=-1000>--%>
<%--		<LINK HREF="<%=PATH%>/ADMIN/CSS/ADMIN.CSS" TYPE="TEXT/CSS"--%>
<%--			REL="STYLESHEET">--%>
		<script type="text/javascript">
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;

</script>
	</HEAD>
	
<%--		<FRAME name=header src="<%=path%>/admin/header.jsp" frameBorder=0--%>
<%--			noResize scrolling=no>--%>
		<frameset cols="550, *" rows="800,*">
			<frame id="menu" name="menu" src="<%=path%>/System/renshi/hr_deptTree.jsp"
				frameBorder="0" noresize="noresize">
			<frame id="main" name="main" src="<%=path%>/System/renshi/hr_deptManage.jsp"
				frameBorder="0"  noResize scrolling="auto">
		</frameset>
	
	<noframes>
	</noframes>
</HTML>
