<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>PEBS,生产力生态平衡系统</title>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
<%--window.onload = function() {--%>
<%--	function showTime() {--%>
<%--		var datetime = new Date();--%>
<%--		var h = datetime.getHours();--%>
<%--		var m = datetime.getMinutes();--%>
<%--		var s = datetime.getSeconds();--%>
<%----%>
<%--		if (h < 10) {--%>
<%--			h = "0" + h;--%>
<%--		}--%>
<%--		if (m < 10) {--%>
<%--			m = "0" + m;--%>
<%--		}--%>
<%--		if (s < 10) {--%>
<%--			s = "0" + s;--%>
<%--		}--%>
<%----%>
<%--		var hour = document.getElementById("hour");--%>
<%--		var minute = document.getElementById("minute");--%>
<%--		var seconds = document.getElementById("second");--%>
<%----%>
<%--		hour.innerHTML = h;--%>
<%--		minute.innerHTML = m;--%>
<%--		seconds.innerHTML = s;--%>
<%--		setTimeout(showTime, 1000);--%>
<%--	}--%>
<%--	showTime();--%>
<%----%>
<%--}--%>

$(function() {
	var url = "${param.url}";
	var pageStatus = "${pageStatus}";
	if (url != "") {
		$("#sysModule").hide();
		$('#workMainIframe').attr("src",
				"${pageContext.request.contextPath}/" + url);
		$("#showAll").show();
	} else if (pageStatus == "last") {
		$("#sysModule").hide();
		$("#showAll").show();
	}
})
</script>
	</head>
	<body>
		<%@include file="/util/newHead.jsp"%>
		<div class="content">
<%--			<div class="c_logo">--%>
<%--				<div class="fl">--%>
<%--					<img src="images/ph_03.jpg" />--%>
<%--				</div>--%>
<%--				<div class="fr time">--%>
<%--					北京时间：--%>
<%--					<span id="hour"></span>时--%>
<%--					<span id="minute"></span>分--%>
<%--					<span id="second"></span>秒--%>
<%--					<a href="DingdanAction!listLoginUsers.action"> 当前 <span--%>
<%--						class="zxrs">${count}</span>人在线</a>--%>
<%--				</div>--%>
<%--			</div>--%>
			<div id="sysModule" class="c_relation">
				<s:iterator id="mf" value="allModuleList" status="pageId">
					<s:if test="#pageId.index % 4 == 0">
						<ul id="rela">
					</s:if>
					<li
						style="background:${mf.bgColor};position:absolute;z-index:1;margin-left:${pageId.index % 4 *252}px;display:inline;cursor: pointer;"
						onclick="goPageByUrl('${mf.id}','${mf.targetNewPage}')">
						<a
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${mf.id}"><img
								src="<%=basePath%>upload/file/sysImages/${mf.qximageName}" /> </a>
					</li>
					<s:if test="(#pageId.index+1) % 4 == 0">
						</ul>
					</s:if>
				</s:iterator>
			</div>
			<div id="showAll" style="display: none;">
				<!--主体 iframe -->
				<iframe name="workMain" target="workMain" id="workMainIframe" src=""
					marginwidth="0" marginheight="0" hspace="0" vspace="0"
					frameborder="0" scrolling="no"
					style="width: 100%; height: auto; margin: 0px; padding: 0px;"></iframe>
			</div>
		</div>
		<%@include file="/util/newFooter.jsp"%>
	</body>
</html>

