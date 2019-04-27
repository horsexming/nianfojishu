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
	<center>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="craftComplexityAction_update.action?id=craftComplexity.id"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					修改工艺复杂系数<br/>
					（update skillscore）
				</h3>
				<form action="craftComplexityAction_update.action" method="post"
					onsubmit="return validate()">
					<table class="table">
					<tr>
							
						<tr>
							<th align="right">
								分类<br/>（skill lv）
							</th>
							<td>
							<input type="hidden" name="craftComplexity.id"  value="<s:property value="craftComplexity.id"/>" />
								<input type="text" name="craftComplexity.name" id="ccname" value="<s:property value="craftComplexity.name"/>" />
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
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
function validate() {
	var ccname = document.getElementById("ccname").value;
	if (ccname == "") {
		alert("请输入分类名称!");
		return false;
	}
}
</script>
	</body>
</html>
