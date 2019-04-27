<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<form action="procardTemplateGyAction_zpBOmTree.action" method="post">
					<input type="hidden" name="procardTemplate.id" value="${procardTemplate.id}"/>
				<table class="table">
					<tr>
							<tr>
								<th align="right">
									BOM结构校对人 ：
								</th>
								<td>
									<select name="procardTemplate.jiaoduiId2" id='jiaodui2'>
										<option value="${procardTemplate.jiaoduiId2}">
											${procardTemplate.jiaoduiName2}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.jiaoduiDate" id="jiaoduiDate"
										readonly="readonly" value="${procardTemplate.jiaoduiDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
								<th align="right">
									BOM结构审核人 ：
								</th>
								<td>
									<select name="procardTemplate.shenheId2" id='shenhe2'>
										<option value="${procardTemplate.shenheId2}">
											${procardTemplate.shenheName2}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.shenheDate" id="shenheDate"
										readonly="readonly" value="${procardTemplate.shenheDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
								<th align="right">
									BOM结构批准人 ：
								</th>
								<td>
									<select name="procardTemplate.pizhunId2" id='pizhun2'>
										<option value="${procardTemplate.pizhunId2}">
											${procardTemplate.pizhunName2}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.pizhunDate" id="pizhunDate"
										readonly="readonly" value="${procardTemplate.pizhunDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
								<td><input value="提交" type="submit"> </td>
							</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function getGyPeople(type) {
	$.ajax( {
		type : "POST",
		url : "procardTemplateGyAction_getGyPeople.action?tag=" + type,
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						if (type == "bz") {
							$("#bianzhi2").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "jd") {
							$("#jiaodui2").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "sh") {
							$("#shenhe2").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "pz") {
							$("#pizhun2").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");

						}
					});
		}
	});
}
$(document).ready(function() {
	getGyPeople("jd")
	getGyPeople("sh")
	getGyPeople("pz")
})
		</script>
	</body>
</html>
