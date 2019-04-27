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
			style="border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3 align="center">
					查看测试题
					<br />
					Check Test Questions
					<input type="hidden" name="interviewLog.id" value="${interviewLog.id}"/>
				</h3>
				<form action="" method="post">
					<table width="70%" border="0" style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center" width="85%">
								内容
								<br />
								Content
							</th>
						</tr>
						<s:iterator value="interviewQuizzeslist" id="interviewQuizzes"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>

							<td align="left"><br />
								问题：${interviewQuizzes.question}
								<br /><br />
								<font color="red">答案：${interviewQuizzes.answer}</font>
							</td>
						</s:iterator>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
