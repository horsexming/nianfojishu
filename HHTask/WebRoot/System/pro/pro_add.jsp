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
				<form action="proAction!addPro.action" method="post"
					style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								项目名称:
							</td>
							<td>
								<input type="text" name="pro.name" />
							</td>
							<td align="right">
								项目编号:
							</td>
							<td>
								<input type="text" name="pro.code" />
							</td>
							<td align="right">
								客户:
							</td>
							<td>
								<input type="text" name="pro.clientName" />
							</td>
						</tr>
						<tr>
							<td align="right">
								项目预算:
							</td>
							<td>
								<input type="text" name="pro.budget" />
							</td>
							<td align="right">
								结束时间:
							</td>
							<td>
								<input id="finishDate" class="Wdate" type="text" name="pro.finishDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
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
