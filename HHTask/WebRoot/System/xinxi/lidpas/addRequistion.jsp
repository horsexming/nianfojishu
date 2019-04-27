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
					
				</div>
			</div>
			
			<div align="center">
				<font color="red"> ${successMessage}</font>
				<form action="RequisitionAction!addSubmit.action" method="post"
					onsubmit="return check()">
					<table border="1" width="100%" class="table">
						<tr>
							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
								信息资料查询申请单
							</td>
						</tr>
						<tr>
							<td align="right">
								申请人:
							</td>
							<td align="left">
								<select name="requisition.name" style="width: 155px">
									<option>
										${Users.name}
									</option>
								</select>
							</td>
							<td align="right">
								部门:
							</td>
							<td align="left">
								<select name="requisition.department" style="width: 155px">
									<option>
										${Users.dept}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								查询信息时段从:
							</td>
							<td>

								<input class="Wdate" type="text" name="requisition.startdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

								到
								<input class="Wdate" type="text" name="requisition.enddate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="right">
								查询内容:
							</td>
							<td>

								<input type="text" style="width: 250px; height: 80px;"
									name="requisition.content" />
							</td>
							</td>
							<td align="right">
								事由:
							</td>
							<td>

								<input type="text" style="width: 250px; height: 80px;"
									name="requisition.subjectmatter" />

							</td>
						</tr>

						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;">
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
		<script type="text/javascript">

function check() {

	var content = document.getElementById("content");
	var subjectmatter = document.getElementById("subjectmatter");

	if (content.value == "") {
		alert("查询内容不能为空!");
		content.focus();
		return false;
	} else if (subjectmatter.value == "") {
		alert("事由不能为空!");
		subjectmatter.focus();
		return false;

	} else {
		return true;
	}
}
</script>
	</body>
</html>
