<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h2>文件编码申请</h2>
				<table>
					<tr>
						<th>来源：</th>
						<td>
							<select>
								<option value="来源于项目">来源于项目</option>
								<option value="">
							</select>
						</td>
					</tr>
					
					
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
