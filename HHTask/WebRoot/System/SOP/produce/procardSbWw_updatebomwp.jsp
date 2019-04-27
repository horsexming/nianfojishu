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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
				<h3>重选外委</h3>
				<div align="left">原外委： 件号:${procard.markId},批次:{procard.selfCard},工序号:{waigouplan.processNOs},工序名称:{waigouplan.processNames},
				数量:${procard.wwCount}/${waigouplan.unit},来源:{waigouplan.wwSource},类型:{waigouplan.wwType}</div>
			</div>
			<div>
			<form action="procardTemplateGyAction_updateSbbomwp.action" method="post">
			<input type="hidden" name="id" value="${id}" >
			<input type="hidden" name="id2" value="${id2}" >
			<input type="hidden" name="procard.id" value="${procard.id}" >
			<table class="table">
			<tr>
				<td align="center">序号<input type="checkbox" id="cbAll"
						onclick="cbAll()"/>全选
				</td>
				<td align="center">工序号
				</td>
				<td align="center">工序名称
				</td>
				<td align="center">总数量
				</td>
				<td align="center">外委数量
				</td>
				<td align="center">工序类型
				</td>
			</tr>
			<s:iterator value="processInforList" id="pageProcess"  status="pstatus">
			<tr>
			<td align="left">
			<s:if test="#pageProcess.productStyle=='外委'">
				<input type="checkbox" name="checkboxs" onclick="cbsingle(<s:property value="#pstatus.index" />)"
						value="${pageProcess.id}"/>${pstatus.index+1}
			</s:if>
			</td>
			<td align="left">
			${pageProcess.processNO}
			</td>
			<td align="left">
			${pageProcess.processName}
			</td>
			<td align="right">
			${procard.filnalCount}
			</td>
			<td align="right">
			${procard.productStyle}
			</td>
			</tr>
			</s:iterator>
			<tr>
			<td align="center"><input type="submit" value="提交" style="width: 40px;height: 60px;" > </td>
			</tr>
			</table>
			</form>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
</SCRIPT>
	</body>
</html>
