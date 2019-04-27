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
		<script type="text/javascript">
function checkZjForm() {
	var size = "${pageSize}"
	for ( var i = 1; i <= size; i++) {
		var val = $('input:radio[name="isQualifiedList[' + i + ']"]:checked')
				.val();
		if (val == null) {
			var content = $("#content" + i).val();
			alert("第" + i + "行 《" + content + "》 尚未检查!请检查后提交!");
			return false;
		}
	}
	return true;
}

function checkRadio(id) {
	$("#" + id).attr("checked", "checked");
}

$(function() {
	var successMessage = "${successMessage}";
	if (successMessage == "自检完成") {
		alert("自检完成");
		parent.location.reload(true);
	}
})
</script>
	</head>
	<body style="background: #ffffff url('');">
		<form action="gyslgxAction_addZj.action"
			style="margin: 0px; padding: 0px;" method="post"
			onsubmit="return checkZjForm()">
			<input name="id" type="hidden" value="${id}" />
			<table class="table">
				<tr>
					<th colspan="3" align="center">
						<h1 style="margin: 0px; padding: 0px;">
							现场自检表 日期:
						</h1>
					</th>
				</tr>
				<!-- 
				<tr>
					<th colspan="3">
						工区号:
						<select style="width: 155px;">
						</select>
						工位号:
						<select style="width: 155px;">
						</select>
						工装号:
						<select style="width: 155px;">
						</select>
					</th>
				</tr> -->
				<tr>
					<th>
						序号
					</th>
					<th>
						自检项
					</th>
					<th>
						自检结果
					</th>
				</tr>
				<s:iterator value="list" id="pageProvision" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td align="left">
						${pageProvision.content}
						<input id="content${pageStatus.index+1}"
							name="contentList[${pageStatus.index+1}]"
							value="${pageProvision.content}" style="display: none;">
					</td>
					<td>
						<input size="200px" id="hege${pageStatus.index+1}"
							name="isQualifiedList[${pageStatus.index+1}]" type="radio"
							checked="checked" value="合格">
						<input type="button"
							onclick="checkRadio('hege${pageStatus.index+1}')" value="合格">
						<input id="buhege${pageStatus.index+1}"
							name="isQualifiedList[${pageStatus.index+1}]" type="radio"
							value="不合格">
						<input type="button"
							onclick="checkRadio('buhege${pageStatus.index+1}')" value="不合格">
					</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="3" align="center">
						<input class="input" type="submit" value="确定" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
