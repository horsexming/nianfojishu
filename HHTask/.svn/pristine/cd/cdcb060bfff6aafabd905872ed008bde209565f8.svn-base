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
		<%@include file="/util/inc.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/index4.css" />
	</head>
	<body>
		<div id="sysModule">
			<s:iterator id="mf" value="moduleFunctionList" status="pageId">
				<s:if test="#pageId.index % 4 == 0">
					<ul id="sop">
				</s:if>
				<s:if test="#mf.functionLink!=''">
					<li
						style="background:${mf.bgColor};position:absolute;z-index:1;margin-left:${pageId.index % 4 *252}px;display:inline;cursor: pointer;"
						onclick="showWork('${mf.id}');goPageByUrl('${mf.id}','${mf.targetNewPage}','${pageStatus}','<%=basePath%>${mf.functionLink}')">
				</s:if>
				<s:else>
					<li
						style="background:${mf.bgColor};position:absolute;z-index:1;margin-left:${pageId.index % 4 *252}px;display:inline;cursor: pointer;"
						onclick="showWork('${mf.id}');goPageByUrl('${mf.id}','${mf.targetNewPage}','${pageStatus}','${mf.functionLink}')">
				</s:else>
				<a href="javascript:;">
					<p class="zyxt"  style="color: #ffffff">
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
			<s:if test="moduleFunctionList.size()<=0">
				<div align="center"
					style="color: red; font-weight: bolder; font-size: 16px;">
					抱歉！该功能正在完善中或者您没有权限访问该功能！
				</div>
			</s:if>
		</div>
		<script>
function showWork(moduleid) {
	if (moduleid != null && moduleid > 0) {
		$
				.ajax( {
					url : 'ModuleFunctionAction!findMfByIdForJson.action',
					type : 'post',
					dataType : 'json',
					data : {
						id : moduleid,
						pageStatus : '3'
					},
					cache : true,
					success : function(mf) {
						$(".cenjinav p", window.parent.document).html(mf[0]);
						$(".headercon strong", window.parent.document).html(
								mf[1].functionName);
						var href = "";
						if (mf[1].functionLink == null
								|| mf[1].functionLink == "") {
							href = "ModuleFunctionAction!findMfByIdForJump.action?pageStatus=3&id="
									+ moduleid;
						} else {
							href = mf[1].functionLink;
						}
						$("#showAllPm", window.parent.document).attr("href",
								href);
						$("#shuaxin", window.parent.document)
								.attr("href", href);
					},
					error : function() {
						location.href = 'ModuleFunctionAction!findMfByUser.action';
					}
				});
	}
}</script>
	</body>
</html>

