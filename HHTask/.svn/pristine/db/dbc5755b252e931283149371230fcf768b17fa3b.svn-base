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
			<div align="center" id="test">
				<h3>
					补充对账单明细导入
				</h3>
				<form action="CorePayableAction!addBcCorePayable.action"
					method="post" enctype="multipart/form-data"
					onsubmit="return checktype()">
					<a href="./upload/file/download/rukuTemplate1.xls">导入模版下载</a>
					<table class="table" width="75%">
						<tbody>
							<tr>
								<th align="right">
									选择导入文件:
								</th>
								<td align="left">
									<input type="file" name="attachment">
								</td>
							</tr>
							<tr>
								<th>
								</th>
								<td align="left">
									<input type="submit" id="sub" value="批量导入" class="input">
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
