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
			<form action="procardTemplateGyAction_zpbz.action" method="post">
					<input type="hidden" name="procardTemplate.id" value="${procardTemplate.id}"/>
				<table class="table">
					<tr>
							<tr>
								<th align="right">
									编制人 ：
								</th>
								<td>
									<select name="procardTemplate.bianzhiId" id='bianzhi'>
										<option value="${procardTemplate.bianzhiId}">
											${procardTemplate.bianzhiName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.bianzhiDate" id="bianzhiDate"
										readonly="readonly" value="${procardTemplate.bianzhiDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
								<th align="right">
									校对人 ：
								</th>
								<td>
									<select name="procardTemplate.jiaoduiId" id='jiaodui'>
										<option value="${procardTemplate.jiaoduiId}">
											${procardTemplate.jiaoduiName}
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
									审核人 ：
								</th>
								<td>
									<select name="procardTemplate.shenheId" id='shenhe'>
										<option value="${procardTemplate.shenheId}">
											${procardTemplate.shenheName}
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
									批准人 ：
								</th>
								<td>
									<select name="procardTemplate.pizhunId" id='pizhun'>
										<option value="${procardTemplate.pizhunId}">
											${procardTemplate.pizhunName}
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
							$("#bianzhi").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "jd") {
							$("#jiaodui").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "sh") {
							$("#shenhe").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "pz") {
							$("#pizhun").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");

						}
					});
		}
	});
}
$(document).ready(function() {
	getGyPeople("bz")
	getGyPeople("jd")
	getGyPeople("sh")
	getGyPeople("pz")
})
		</script>
	</body>
</html>
