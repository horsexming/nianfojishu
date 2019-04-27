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
	<title>快递员信息录入界面</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<style type="text/css">
	</style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div width=100%; id="gongneng" style="">
			<div>
			    <table>
			        <tr>
			            <td><input type="text" value="" id="kdyname" /></td>
			        </tr>
			        <tr>
			            <td><input type="text" value="" id="phName" /></td>
			        </tr>
			        <tr>
			            <td>
			                <select id="kdCompany">
			                    <option>顺丰快递</option>
			                </select>
			            </td>
			        </tr>
			    </table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)--> 
		<script type="text/javascript">  
		
	    </script>
	</body>
</html>
