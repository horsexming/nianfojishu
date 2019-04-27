<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			
				<form action="SystemDemandAction_addSystemDemand.action" method="post" enctype="multipart/form-data" >
					<h2>
						系统需求查看
					</h2>
					<table class="table" width="85%">
						<tr>
							<th>添加人:</th>
							<th><input type="text" value="${systemDemand.userName}" readonly="readonly" class="horizontalLine"/></th>
							<th>部门:</th>
							<th><input type="text" value="${systemDemand.userDept}" readonly="readonly" class="horizontalLine"/></th>
						</tr>
						<tr>
							<td align="right">编号:</td>
							<th align="left"><input type="text" value="${systemDemand.sdNum}" readonly="readonly" class="horizontalLine"/></th>
						</tr>
						<tr>
							<th align="right">
								需求简称:
							</th>
							<td>
								<input type="text" name="systemDemand.sdShortName" id="sdShortName" vlaue="${systemDemand.sdShortName}" class="horizontalLine">
							</td>
							<th align="right">
								需求类型:
							</th>
							<td>
								<input type="text" value="${systemDemand.sdType}" class="horizontalLine" />
							</td>
						</tr>
						<tr>
							<th align="right">
								功能名称:
							</th>
							<td>
								<input type="text" name="systemDemand.functionName" id="functionName" value="${systemDemand.functionName}" class="horizontalLine">
							</td>
							<th align="right">
								需求级别:
							</th>
							<td>
								<input type="text" value="${systemDemand.sdLeave}" class="horizontalLine"/>
							</td>
						</tr>
						<tr>
							<th align="right">需求描述:</th>
							<td colspan="3">
								<textarea rows="5" cols="50"name="systemDemand.sdDesc" id="sdDesc">${systemDemand.sdDesc}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								需求文件
							</th>
							<td colspan="3">
								<s:if test="null!=systemDemand.demandFile&&systemDemand.demandFile!='' ">
										<c:forEach var="tdv" items="${fn:split(fn:replace(fn:replace(systemDemand.demandFile,']',''),'[',''),',')}">
<%--						                    <td>${tdv}</td>--%>
										&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/upload/file/${tdv}">查看附件(${tdv})</a>&nbsp;&nbsp;
						                </c:forEach>
										<br/>
									</s:if>
									<s:else>
										无
									</s:else>
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" value="提交" class="input" id="sub"
									onclick="todisabled(this)">
							</th>
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