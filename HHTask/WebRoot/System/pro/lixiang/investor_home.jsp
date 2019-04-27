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
	 <title>投资人个人中心</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="right" style="width: 48%;float: left;">
				<label><font>${investor.name},您好!</font> </label>
			</div>
			<div align="right" style="width: 48%;float: left;">
				<!-- <label><a href="">账户资料</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">投资记录</a>&nbsp;&nbsp;</label>
				 -->
			</div>
			<div align="right" style="width: 100%;clear: none;">
			</br>
			</br>
			</br>
			</div>
			<div id="showCardTemplate"
						style="display: none; height: 1000px; background-color: #ffffff">
						<iframe id="showhome" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>
					</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(document).ready(function(){
			$("#showCardTemplate").show();
			$("#showhome").attr("src","QuotedPrice_showInvestorsMsg.action");
		})
		</script>
	</body>
</html>
