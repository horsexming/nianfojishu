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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<form action="zhaobiaoAction!Updatehuikuang.action" method="post">
					<table class="table">
						<tr>
							<th colspan="4" align="center">
								<font size="5px;">修改原材料</font>
							</th>
						</tr>

						<tr>
							<th align="right">
								回款方式
							</th>


							<td>
								<input type="text" name="huikuang.h2"
									value="${huikuang.h2}" />
							</td>

						</tr>

						<tr>
							<th align="right">
								说明:
							</th>
							<td>
								<input type="text" name="huikuang.h3"
									value="${huikuang.h3}" />
								<input type="hidden" name="huikuang.hid"
									value="${huikuang.hid}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="保存" class="input">
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	</SCRIPT>

	</body>

</html>
