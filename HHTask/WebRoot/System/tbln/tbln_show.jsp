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
			<div align="center">
				<form action="TbBarcodeLockNoticeAction_updatetbln.action" method="post" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<th align="right">条码</th>
							<td>
								<input type="text" value="${tbln.barcode}" name="tbln.barcode" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">料号</th>
							<td>
								<input type="text" value="${tbln.matCode}" name="tbln.matCode" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">品名</th>
							<td>
								<input type="text" value="${tbln.matName}" name="tbln.matName" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">锁定状态</th>
							<td>
								<input type="text" value="${tbln.locked}" name="tbln.locked" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">锁定原因</th>
							<td>
								<input type="text" value="${tbln.lockReason}" name="tbln.lockReason" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">解锁原因</th>
							<td>
								<input type="text" value="${tbln.unlockReason}" name="tbln.unlockReason" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">锁定人</th>
							<td>
								<input type="text" value="${tbln.lockUser}" name="tbln.lockUser" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">锁定日期</th>
							<td>
								<input type="text" value="${tbln.lockDate}" name="tbln.lockDate" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">隔离单号</th>
							<td>
								<input type="text" value="${tbln.lockNo}" name="tbln.lockNo" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">上传文件</th>
							<td>
								<input type="file" name="attachment"/>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${tbln.id}" name="tbln.id" />
					<input type="submit" value="提交" class="input"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
