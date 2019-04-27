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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href=""
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加快递单号
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessEquipmentAction_addResAccess.action"
					method="post" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="center">
								快递单号：
							</th>
							<td align="center">
								<input type="text" name="resAccess.cunCodes"
									id="danhao" />
								<input type="hidden" name="resAccess.type" value="存取"/>
								<input type="hidden" name="tag" value="aD"/>
							</td>
							<td align="center" colspan="2">
								<input type="submit" value="添加(Add)"
									style="width: 70px; height: 26px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("danhao", "快递单号")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

function add() {
	var url = "<%=request.getContextPath()%>/AccessEquipmentAction_toAddResAccessJi.action";
	$("#showProcess").attr("src", url);
}
<%--$("#cabOpenOrder").keyup(function() {--%>
<%--	var tmptxt = $(this).val();--%>
<%--	$(this).val(tmptxt.replace(/\D|^0/g, ''));--%>
<%--})--%>
</script>
	</body>
</html>
