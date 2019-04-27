<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script language="javascript" src="<%=basePath%>javascript/jquery-1.7.2.min.js">
</script>
		<script language="javascript" src="<%=basePath%>javascript/jquery.jqprint-0.3.js">
</script>
		<script language="javascript">
function a() {
	$("#ddd").jqprint();
}
</script>
	</head>
	<body>
		<div id="ddd">
			<table>
				<tr>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
				</tr>
				<tr>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
				</tr>
				<tr>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
				</tr>
				<tr>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
				</tr>
				<tr>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
					<td>
						test
					</td>
				</tr>

			</table>

		</div>

		<input type="button" onclick=" a()" value="print" />
	</body>
</html>

