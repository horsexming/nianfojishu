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
			<form action="also_alsoStore.action" method="post"
				onsubmit="return validate()">
				<table align="center" border="1" style="border-collapse: collapse;">
					<tr>
						<td>
							卡号：
							<input type="text" name="al.cardNum" value="${bo.cardNum }"
								readonly="readonly" />
						</td>
						<td>
							姓名：
							<input type="text" name="al.peopleName" value="${bo.peopleName }"
								readonly="readonly" />
						</td>
						<td>
							部门：
							<input type="text" name="al.dept" value="${bo.dept }"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							编号：
							<input type="text" name="al.number" value="${bo.number }"
								readonly="readonly" />
						</td>
						<td>
							品名：
							<input type="text" name="al.name" value="${bo.matetag }"
								readonly="readonly" />
						</td>
						<td>
							单位：
							<input type="text" name="al.unit" value="${bo.unit }"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							数量：
							<input type="text" name="al.processQuantity" id="num" />
						</td>
						<td>
							归还日期：
							<input type="text" name="al.alsoDate" class="Wdate" id="time"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
						<td>
							规格：
							<input type="text" name="al.format" value="${bo.format}" />
						</td>
					</tr>
					<tr>
						<td>
							借物日期：
							<input type="text" name="al.date" value="${bo.date}"
								readonly="readonly" />
						</td>
						<td>
							加工数量：
							<input type="text" name="al.num" />
						</td>
						<td>
							加工件号：
							<input type="text" name="al.processPieceNum"
								value="${bo.processPieceNum}" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="hidden" name="al.id" value="${bo.id}" />
							<input type="submit" value="归还" />
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script type="text/javascript">
var exp = /^\d+$/;
function validate() {
	var num = document.getElementById("num").value;
	var time = document.getElementById("time").value;
	if (num == "") {
		alert("请输入数量!");
		return false;
	}
	if (!exp.test(num)) {
		alert("请输入整数!");
		return false;
	}
	if (time == "") {
		alert("请选择日期！谢谢");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
