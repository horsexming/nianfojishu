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
			style="border: solid 2px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3 align="center">
					请填写测试题
					<br />
					Please fill out the test questions
					<br/>
					<font color="#ff0000">${errorMessage}</font>
				</h3>
				<form action="interviewQuizzesAction_add.action" method="post"
					onsubmit="return validate();">
					<table width="70%" border="0" style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center" width="85%">
								内容
								<br />
								Content
								<input type="hidden" name="interviewLog.id"
									value="${interviewLog.id}" />
								<input type="hidden"
									value="<s:property value="provisionList.size()"/>"
									id="listSize" />
							</th>
						</tr>

						<s:iterator value="provisionList" id="pageProvision"
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

							<td align="left">
								<input type="text"
									name="interviewQuizzeslist[${pageStatus.index}].question"
									id="question${pageStatus.index}" style="width: 100%;"
									value="${pageProvision.content}">
								
								<textarea rows="3" cols="100%"
									name="interviewQuizzeslist[${pageStatus.index}].answer"
									id="answer${pageStatus.index}"></textarea><br /><br />
							</td>
						</s:iterator>
						<tr>
							<td align="center">
								<input type="submit" value="添加"
									style="width: 80px; height: 35px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 35px;">
							</td>
						</tr>
						<%--<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
					</tr>
				--%>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var size = $("#listSize").val();
	for ( var i = 0; i < size; i++) {
		n = i + 1;
		if (!validateText("answer" + i, "第" + n + "条内容")) {
			return false;
		}
	}
}

function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
