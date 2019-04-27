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
		<TITLE>物料类别</TITLE>
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
var status = "物料";
var tag = '${param.tag}';
if('${param.pageStatus}' == 'code'){
		status = "编码";
}
</script>
	</HEAD>

	<frameset cols="350, *" rows="800,*">
		<frame id="menu" name="menu"
			src="<%=path%>/System/xinxi/category/category_tree.jsp?id=${param.id}"
			frameBorder="0" noresize="noresize" scrolling=yes>
		<frame id="main" name="main"
			src="<%=path%>/System/xinxi/category/category_update.jsp"
			frameBorder="0" noresize scrolling=yes>
	</frameset>

	<noframes>
	</noframes>
</HTML>
