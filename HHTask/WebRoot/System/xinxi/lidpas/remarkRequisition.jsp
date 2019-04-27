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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="RequisitionAction!findAll.action?requisition.name="
						style="color: #ffffff">返回</a>
				</div>
			</div>
			
			<div align="center">

				<form action="RequisitionAction!upremarkRequisition.action"
					method="post">
					<input type="hidden" value="${id}" name="id" />
					<input type="hidden" value="${pageStatus}" name="pageStatus" />

					<table border="1" width="100%" class="table">
						<tr>
							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								申请单执行回复操作
							</td>
						</tr>
						<tr>
							<td align="right">
								申请人:
							</td>
							<td align="left">

								<input type="text" disabled="disabled" type="hidden"
									name="requisition.name" value="${requisition.name}" />

							</td>


							</td>
							<td align="right">
								部门:
							</td>
							<td align="left">

								<input type="text" disabled="disabled" type="hidden"
									name="requisition.department" value="${requisition.department}" />

							</td>
						</tr>
						<tr>
							<td align="right">
								查询信息时段从:
							</td>
							<td>
								<input type="text" disabled="disabled"
									type="hidden" name="requisition.startdate"
									value="${requisition.startdate}" />
								到
								<input  type="text" disabled="disabled"
									type="hidden" name="requisition.enddate"
									value="${requisition.enddate}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								查询内容:
							</td>
							<td>
								<input type="text" disabled="disabled" type="hidden"
									style="width: 250px; height: 80px;"
									value="${requisition.content}" name="requisition.content" />
							</td>
							</td>
							<td align="right">
								事由:
							</td>
							<td>
								<input type="text" disabled="disabled" type="hidden"
									style="width: 250px; height: 80px;"
									value="${requisition.subjectmatter}"
									name="requisition.subjectmatter" />
							</td>


							<tr>
								<td align="right">
									回复查询结果:
								</td>
								<td>
									<input type="text" style="width: 250px; height: 80px;"
										value="${requisition.replyremarks}"
										name="requisition.replyremarks" />
								</td>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="修改"
										style="width: 100px; height: 50px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 100px; height: 50px;">
								</td>
							</tr>
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
