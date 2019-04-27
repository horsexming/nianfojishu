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
				<table class="table">
				<tr>
				 <th>用车人</th>
				 <td align="center">${singleCar.car_usename}</td>
				 <th>部门</th>
				 <td align="center">${singleCar.var_dept}</td>
				 <th>车牌</th>
				 <td align="center">${singleCar.car_number}</td>
				 <th>目的地</th>
				 <td align="center">${singleCar.car_place}</td>
				 <th>备注</th>
				 <td align="center">${singleCar.car_content}</td>
				</tr>
				<s:if test="askForLeave!=null&&askForLeave.singleCarId!=null">
				<th>外出类型</th>
				 <td align="center">${askForLeave.leaveObjectType}</td>
				 <th>相关生产</th>
				 <td align="center" colspan="3">${askForLeave.leaveObjectNeirong}</td>
				 <th></th>
				 <td align="center"></td>
				 <th></th>
				 <td align="center"></td>
				</s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">]
</script>
	</body>
</html>
