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
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript">
$(function() {
	var url = "${moduleFunction.functionLink}";
	if (url.indexOf(".action") == -1) {
		url = "${pageContext.request.contextPath}" + url;
	}
	var target = "${moduleFunction.targetNewPage}";
	var pageStatus = "${pageStatus}";
	if (pageStatus != "" && pageStatus == "last") {
		$("#sysModule").hide();
		$('#workMainIframe').attr("src", url);
		$("#showAll").show();
	}
})
</script>
	</head>
	<body>
		<%@include file="/util/newSonMain.jsp"%>
		<div id="sysModule">
			<s:iterator id="mf" value="moduleFunctionList" status="pageId">
				<s:if test="#pageId.index % 4 == 0">
					<ul id="sop">
				</s:if>
				<li
					style="background:${mf.bgColor};position:absolute;z-index:1;margin-left:${pageId.index % 4 *252}px;display:inline;cursor: pointer;"
					onclick="goPageByUrl('${mf.id}','${mf.targetNewPage}')">
					<a href="javascript:;">
						<p class="zyxt" style="color: #ffffff">
							${mf.functionName}
						</p>
						<p class="english" style="color: #ffffff">
							${mf.englishName}
						</p> </a>
				</li>
				<s:if test="(#pageId.index+1) % 4 == 0">
					</ul>
				</s:if>
			</s:iterator>
			<s:if test="moduleFunctionList ==null || moduleFunctionList.size()<=0">
				<div align="center"
					style="color: red; font-weight: bolder; font-size: 16px;">
					抱歉！该功能正在完善中或者您没有权限访问该功能！
				</div>
			</s:if>
		</div>
		<div id="showAll" style="display: none;">
			<!--主体 iframe -->
			<iframe name="workMain" target="workMain" id="workMainIframe" src=""
				marginwidth="0" marginheight="0" hspace="0" vspace="0"
				frameborder="0" scrolling="auto"
				style="width: 100%; height: 500px; margin: 0px; padding: 0px;"></iframe>
		</div>
		</div>
		<%@include file="/util/newFooter.jsp"%>
	</body>
</html>

