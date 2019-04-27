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
			<s:if test="flag == true">
				<h3 style="color: red;">
					${voal.msg}
				</h3>
			</s:if>
			<s:else>
				<caption align="top" style="color: red;">
					归还成功!
				</caption>
				<table align="center" border="1" style="border-collapse: collapse;">
					<tr>
						<td>
							卡号：
						</td>
						<td>
							${al.cardNum }
						</td>
						<td>
							姓名：
						</td>
						<td>
							${al.peopleName }
						</td>
						<td>
							部门：
						</td>
						<td>
							${al.dept}
						</td>
					</tr>
					<tr>
						<td>
							编号：
						</td>
						<td>
							${al.number }
						</td>
						<td>
							品名：
						</td>
						<td>
							${al.name }
						</td>
						<td>
							单位：
						</td>
						<td>
							${al.unit }
						</td>
					</tr>
					<tr>
						<td>
							数量：
						</td>
						<td>
							${al.num}
						</td>
						<td>
							归还时间：
						</td>
						<td>
							${al.date }
						</td>
						<td>
							规格：
						</td>
						<td>
							${al.format}
						</td>
					</tr>
					<tr>
						<td>
							加工数量：
						</td>
						<td>
							${al.processQuantity }
						</td>
						<td>
							借物时间：
						</td>
						<td>
							${al.alsoDate}
						</td>
						<td>
							加工件号：
						</td>
						<td>
							${al.processPieceNum }
						</td>
					</tr>
				</table>
			</s:else>
		</center>
	</body>
</html>
