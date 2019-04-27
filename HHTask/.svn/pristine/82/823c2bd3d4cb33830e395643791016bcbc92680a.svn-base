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
				<div style="float: left;width: 30%">
				<h3>需要关系升级的BOM的总成</h3>
				<form id="myForm">
					<input value="${procardTemplate.id}" name="id">
					<s:iterator value="procardTemplateList" id="pagePt">
						<input name="checkboxs" type="checkbox" value="${pagePt.id}"/>${pagePt.markId}<font>(${pagePt.ywMarkId})</font>
					</s:iterator>
					<input type="button" onclick="submitform" value="提交">
				</form>
				</div>
				<div style="float: left;width: 69%">
					<table id="showTable" style="display: none;">
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function submitform(){
			$.ajax( {
		type : "post",
		url : "procardTemplateGyAction_findSonToUP.action",
		dataType : "json",
		data : $('#myForm').serialize(),
		success : function(data) {
				int i= 0;
				var html="";
				$(data.data2).each(
					function() {
						if(i==0){
							html + ="<tr><td align='center'></td></tr>";
						}
						i++;
					});
		}
	});
		}
		</SCRIPT>
	</body>
</html>
