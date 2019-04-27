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
			印章使用申请
			</div>
			<table class="table" style="width: 100%;">
						<tr>
			 <td></td>
			 <td colspan="2">申请单编号</td>
			 <td><s:property value="sealLog.number"/></td>
			</tr>
			
			<tr>
			 <td>使用印章名称
			 </td>
			 <td colspan="3"><s:property value="sealLog.name"/>
			 </td>
			</tr>
			<tr> 
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			</tr>
			<tr> 
			<td rowspan="2">使用人员</td>
			<td>工号</td>
			<td>名称</td>
			<td></td>
			</tr>
			<tr> 
			<td><s:property value="sealLog.userCode"/> </td>
			<td><s:property value="sealLog.userName"/> </td>
			<td></td>
			</tr>
			<tr>
			 <td rowspan="2">使用用途简述</td>
			 <td rowspan="2" colspan="2"><s:property value="sealLog.useFor"/> </td>
			 <td>附件
			 </td>
			</tr>
			<tr>
			 <td><a href="<%=path%>/<s:property value='sealLog.fujian'/>">查看附件</a> 
			 </td>
			</tr>
			<tr>
			 <td> 是否需要存档
			 </td>
			 <td colspan="2"><s:property value="sealLog.isSave"/> </td>
			 <td colspan="1">加章后附件：
			 <s:if test="sealLog.fujian2!=null">
			 	<a href="<%=path%><s:property value='sealLog.fujian2'/>">查看附件</a> 
			 </s:if>
			 </td>
			 
			</tr>
			<s:if test="sealLog.isSave!=null&&sealLog.isSave!='yes'">
			<tr>
			 <td>系统自动生成存档编号
			 </td>
			 <td colspan="3"><s:property value="sealLog.isSave"/> </td>
			</tr>
			</s:if>
			
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
