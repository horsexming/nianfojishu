<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
					<form
						action="zhaobiaoAction!suppliersAuditsApplyrun.action?id=${zhUser.id}"
						method="post"">
						<table class="table" style="width: 70%;">
							<tr>
								<th>
									附件1
								</th>
								<td>
									<s:iterator value="Filenames[0]" id="processlist"
										status="processindex">
										<a target="_showPri"
											href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/Taskmanager/<s:property value="processlist"/>">
											<img
												src="<%=path%>/upload/file/Taskmanager/<s:property value="processlist"/>"
												style="width: 80px; height: 80px;" /> </a>
										<s:property />
									</s:iterator>
								</td>
							</tr>
							<tr>
								<th>
									附件2
								</th>
								<td>
									<s:iterator value="Filenames[1]" id="processlist"
										status="processindex">
										<a target="_showPri"
											href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/Taskmanager/<s:property value="processlist"/>">
											<img
												src="<%=path%>/upload/file/Taskmanager/<s:property value="processlist"/>"
												style="width: 80px; height: 80px;" /> </a>
										<s:property />
									</s:iterator>
								</td>
							</tr>
							<tr>
								<th>
									附件3
								</th>
								<td>
									<s:iterator value="Filenames[2]" id="processlist"
										status="processindex">
										<a target="_showPri"
											href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/Taskmanager/<s:property value="processlist"/>">
											<img
												src="<%=path%>/upload/file/Taskmanager/<s:property value="processlist"/>"
												style="width: 80px; height: 80px;" /> </a>
										<s:property />
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="button" name="Submit2" value="返回" class="input"
										onclick="window.history.go(-1);" />
								</td>
							</tr>
						</table>
				</div>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
