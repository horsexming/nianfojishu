<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
	</head>
	<body>
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
				<title>移动Tr</title>
				<style>
td {
	font-size: 12px;
}
</style>
				<script>
var currobj, currindex = -1;
function showindex() {
	alert(currobj);
	if (currobj != null && currobj.tagName == "TR")
		currobj.bgColor = "#eeeeee";
	currobj = event.srcElement.parentElement;
	if (currobj.tagName == "TR") {
		currobj.bgColor == "#eeeeee" ? currobj.bgColor = "#fefefe"
				: currobj.bgColor = "#eeeeee";
		currindex = currobj.rowIndex;
	}
}

function movetr() {
	if (currindex == -1) {
		alert("请先选择");
		return;
	}
	if (event.srcElement.name == "b1" && currindex > 0) {
		mytable.moveRow(currindex, currindex - 1);
		currindex -= 1;
	}
	if (event.srcElement.name == "b2" && currindex < 4) {
		mytable.moveRow(currindex, currindex + 1);
		currindex += 1;
	}
}
</script>
			</head>
			<body style="overflow: auto">
				<table width="80%" height="100%" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
							<table width="90%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#333333" onclick="showindex()" id="mytable">
								<tr bgcolor="#eeeeee">
									<td>
										1
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr bgcolor="#eeeeee">
									<td>
										2
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr bgcolor="#eeeeee">
									<td>
										3
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr bgcolor="#eeeeee">
									<td>
										4
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr bgcolor="#eeeeee">
									<td>
										5
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="1">
							<input type="button" name="b1" value="↑" onClick="movetr()">
							<br>
							<input type="button" name="b2" value="↓" onClick="movetr()">
						</td>
					</tr>
				</table>
			</body>
		</html>