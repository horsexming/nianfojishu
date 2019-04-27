<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.task.util.SessionListener"%>
<%@ page import="com.task.entity.Users"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Online session Query</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body bgcolor="#e5ecf9" topmargin="5px" leftmargin="5px"
		rightmargin="5px">
		<%
			List sessions = SessionListener.getSessions();
			String pageErrorInfo = null;
			try {
		%>
		<h5>
			Online Employee
		</h5>

		<table width="100%" align="center" cellspacing="1" cellpadding="4"
			border="0">
			<tr id="listTitle">
				<td>
					No.
				</td>
				<td>
					登录Id
				</td>
				<td>
					登录时间
				</td>
				<td>
					ip
				</td>
			</tr>
			<%
				StringBuffer datas = new StringBuffer();
					if (sessions != null) {
						Users loginUser = new Users();
						 
						int count = 0;
						while (it.hasNext()) {
							count++;
							out.print(count);
							loginUser = (Users) it.next();
							datas.append("<tr><td>");
							datas.append(count);
							datas.append("</td><td>");
							datas.append(loginUser.getName());
							datas.append("</td><td>");
							datas.append(loginUser.getDept());
							datas.append("</td><td>");
							datas.append(loginUser.getCode());
							datas.append("</td></tr>");
						}
					}
					out.print(datas.toString());
			%>
		</table>
		<%
			} catch (Exception e) {
				pageErrorInfo = e.toString();
			} finally {
				if (pageErrorInfo != null) {
		%>
		<font color="red">Error:<%=pageErrorInfo%></font>
		<%
			}
		%>
	</body>
</html>
<%
	}
%>

