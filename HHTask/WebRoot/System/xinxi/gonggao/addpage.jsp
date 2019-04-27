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
		<div id="gongneng">
			<div align="center">
				<form action="NoticeAction!save.action" method="post" name="myForm">

					<table class="table">
						<tr>
							<td align="right">
								公告标题
							</td>
							<td>
								<input type="text" name="notice.title" value="${notice.title}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								公告内容:
							</td>
							<td>
								<textarea name="notice.content" rows="3" style="width: 500px">${notice.content}</textarea>
								<input type="hidden" name="notice.id" value="${notice.id}" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">

								<s:if test='notice.status != null'>
									<input type="button" value="修改" class="input" onclick="subB()" />
								</s:if>
								<s:else>
									<input type="button" value="提交" class="input" onclick="subA()" />
								</s:else>
								<input type="reset" value="重置" class="input" />
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
function subA() {
	document.forms.myForm.action = "NoticeAction!save.action";
	document.forms.myForm.submit();
}
function subB() {
	document.forms.myForm.action = "NoticeAction!update.action";
	document.forms.myForm.submit();
}
</script>
	</body>
</html>
