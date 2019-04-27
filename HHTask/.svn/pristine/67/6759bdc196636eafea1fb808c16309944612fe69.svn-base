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
			
				<form action="SystemDemandAction_addSystemDemand.action" method="post" enctype="multipart/form-data" >
					<h2>
						修改系统需求
					</h2>
					<table class="table" width="85%">
						<tr>
							<th align="right">
								需求简称:
							</th>
							<td>
								<input type="text" name="systemDemand.sdShortName" id="sdShortName" vlaue="${systemDemand.sdShortName}">
							</td>
							<th align="right">
								需求类型:
							</th>
							<td>
								<select name="systemDemand.sdType" id="sdType">
									<option value="">--请选择--</option>
									<s:iterator value="{'系统新需求','系统问题'}" id="item">
										<s:if test="systemDemand.sdType==#item">
											<option value="${item}" selected="selected">${item}</option>
										</s:if>
										<s:else>
											<option value="${item}">${item}</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								功能名称:
							</th>
							<td>
								<input type="text" value="" name="systemDemand.functionName" id="functionName" value="${systemDemand.functionName}">
							</td>
							<th align="right">
								需求级别:
							</th>
							<td>
								<select name="systemDemand.sdLeave" >
									<option value="${systemDemand.sdLeave}">${systemDemand.sdLeave}</option>
									<option value="可以有">可以有</option>
									<option value="需要有">需要有</option>
									<option value="必须有">必须有</option>
								</select>
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
<%--								<input type="text" value="" style="width: 500px; height: 45px;"--%>
<%--									name="rtxConnect.driverName" id="driverName">--%>
								<input type="file" name="systemDemand.demandFile" id="demandFile" />
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