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
				action="screen_${printer.id==null?'add':'update'}Printer.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${printer.id==null?'添加':'修改'}打印机信息</font>
						</th>

					</tr>
					<tr>
						<th colspan="4">
							<center>

							</center>
						</th>
					</tr>
					<tr>
						<th align="right">
							名称
						</th>
						<td>
							<input type="hidden" name="printer.id"
								value="${printer.id}" />
							<input id="keyCode" name="printer.name"
								value="${printer.name}" />
							<label id="message"></label>
						</td>

						<th align="right">
							IP：
						</th>
						<td>
							<input type="text" name="printer.ip"
								value="${printer.ip}" />
						</td>
						<tr>
							<th align="right">
								工位:
							</th>
							<td>
								<input id="valueCode" name="printer.workPosition"
									value="${printer.workPosition}" />
							</td>
							<th align="right">
								责任人
							</th>
							<td>
								<input id="valueName" name="printer.person"
									value="${printer.person}" />
							</td>
						</tr>
					</tr>

					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="${printer.id==null?'添加':'修改'}" class="input" />
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