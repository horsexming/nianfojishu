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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font color="red" size="5">${errorMessage}</font>
				<form action="IntegralGiftAction_jszjnum.action" method="post" onsubmit="check()">
					<table>
						<tr>
							<th align="right">
								活动期号:
							</th>
							<td>
								<input type="text" name="qihao">
						</td>
						</tr>
						<tr>
							<th align="right">
								时时彩数字:
							</th>
							<td>
								<input type="text" name="str">
						</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="开奖" id="sub" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

</SCRIPT>
	</body>
</html>
