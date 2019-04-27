<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
			src="<%=basePath%>javascript/DatePicker/WdatePicker.js">
</script>
		<base href="<%=basePath%>">

		<title>My JSP 'store_return.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<hr />
		<center>
			<form action="scrap_also.action" method="post"
				onsubmit="return validate()">
				<caption align="top">
					添加报废记录
				</caption>
				<table align="center" border="1" style="border-collapse: collapse;">
					<tr>
						<td>
							编&nbsp;&nbsp;号：
							<input type="text" name="scr.number" value="${bo.number }"
								readonly="readonly" />
						</td>
						<td>
							名&nbsp;&nbsp;称：
							<input type="text" name="scr.matetag" value="${bo.matetag }"
								readonly="readonly" />
						</td>
						<td>
							规&nbsp;&nbsp;格：
							<input type="text" name="scr.format" value="${bo.format }"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							责任人：
							<input type="text" name="scr.username" value="${bo.peopleName }"
								readonly="readonly" />
						</td>
						<td>
							部&nbsp;&nbsp;门：
							<input type="text" name="scr.dept" value="${bo.dept }"
								readonly="readonly" />
						</td>
						<td>
							日&nbsp;&nbsp;期：
							<input type="text" name="scr.badDate" class="Wdate"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td>
							数&nbsp;&nbsp;量：
							<input type="text" name="scr.amount" id="num" />
						</td>
						<td>
							损失意见：
							<input type="text" name="scr.badView" />
						</td>
						<td>
							损失原因：
							<input type="text" name="scr.more1" />
						</td>
					</tr>
					<tr>
						<td colspan="3">
							备&nbsp;&nbsp;注：
							<input type="text" name="scr.more2" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="hidden" name="scr.id" value="${bo.id}" />
							<input type="hidden" name="scr.state" value="0" />
							<input type="submit" value="报废" />
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script type="text/javascript">
var exp = /^\d+$/;
function validate() {
	var num = document.getElementById("num").value;
	if (num == "") {
		alert("请输入数量!");
		return false;
	}
	if (!exp.test(num)) {
		alert("请输入整数!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
