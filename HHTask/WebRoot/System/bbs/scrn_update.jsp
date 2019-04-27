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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			
			<div align="center">
				<form action="scrnAction!updateScrn.action" method="post"
					style="">
					<input id="id" name="scrn.id" type="hidden" value="${scrn.id}"/>
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								屏幕名称:<br/>(Screen Name)
							</td>
							<td>
								<input id="name" type="text" name="scrn.name" value="${scrn.name}"/>
							</td>
							<td align="right">
								排序号:<br/>(Sort numbers)
							</td>
							<td>
								<input id="sort" type="text" name="scrn.sort" value="${scrn.sort}"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="修改update"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>