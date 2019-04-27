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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function check() {
	var shijimoney = document.getElementById("shijimoney");
	if (shijimoney.value == "") {
		alert("实际提奖额不能为空");
		shijimoney.focus();
		return false;
	} else {
		return true;
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>
			
			<div align="center">
				<form
					action="MentionrecordAction!financialupdate.action?id=${mentionrecord.id}"
					method="post" onsubmit="return check()">
					<table align="center">
						<tr>
							<th colspan="4">
								<font size="5">财务添加实际提奖额</font>
								<a href="MentionrecordAction.action">返回</a>
							</th>
						</tr>
						<tr>
							<th>
								月份
							</th>
							<td>
								${mentionrecord.mentionMonth}
							</td>
							<th>
								应提奖额
							</th>
							<td>
								${mentionrecord.mentionshallMoney}
							</td>
						</tr>

						<tr>
							<th>
								实际提奖额
							</th>
							<td>
								<input type="text" name="mentionrecord.mentionactualMoney"
									id="shijimoney" />
							</td>
							<th>
								提奖状态
							</th>
							<td>
								${mentionrecord.mentionstatus}
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取  消" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
