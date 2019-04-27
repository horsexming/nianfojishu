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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
		<style type="text/css">
</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<div class="row clearfix">
<%--				<form action="TaskmanagerAction_daoru.action" method="post"--%>
<%--					enctype="multipart/form-data" onsubmit="return checktype()">--%>
<%--					<table class="table" width="75%">--%>
<%--						<tr>--%>
<%--							<th align="right">--%>
<%--								选择导入文件:--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input type="file" name="addfil" />--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input type="submit"  id="sub" value="批量导入" class="input">--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</form>--%>
				<h2 align="center">
					修复可领数量
				</h2>
				<div class="col-md-12 column">
					<form action="ProcardAction!xiufuTotalCount.action" method="post"
						enctype="multipart/form-data" id="myform">
						<div class="form-group">
							<label for="">
								件号
							</label>
							<input type="text" class="form-control"
								name="procard.markId" />
						</div>
						<div class="form-group">
							<label for="">
								批次
							</label>
							<font color="red"> *</font>
							<input type="text" class="form-control"
								name="procard.selfCard" />
						</div>
						</br>
						<button type="submit" class="btn btn-default" id="taskadd">
							修改
						</button>
					</form>
				</div>
			</div>
		</div>
		</div>
		</center>
	</body>
	<script type="text/javascript">
</script>

</html>
