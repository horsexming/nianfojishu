<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div align="center">
			<form
				action="ProcardTemplateAction!${procardTemplatePrivilege.id==null?'add':'update'}PTPrivilege.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${procardTemplatePrivilege.id==null?'添加':'修改'}</font>
						</th>

					</tr>
			
					<tr>
						<th align="right">
							件号：
						</th>

						<td>
							<input type="hidden" name="procardTemplatePrivilege.id"
								value="${procardTemplatePrivilege.id}" />
							<input id="markId" name="procardTemplatePrivilege.markId"
								value="${procardTemplatePrivilege.markId}"/>
							
						</td>

						</tr>
						
					<tr>
						<td align="center" colspan="2">
							<input type="submit"
								value="${procardTemplatePrivilege.id==null?'添加':'修改'}" class="input" />
							<input type="reset" value=" 重置" class="input" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<SCRIPT type="text/javascript">  
		</SCRIPT>
	</body>
</html>