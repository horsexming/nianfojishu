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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ProcardAction!bangAndJihuo.action" method="post"
					target="showView"
					onsubmit="return  window.confirm('确认人员选择，并提交到激活序列?')">
					<input name="id" value="${id}" type="hidden">
					<table class="table">
						<tr>
							<th colspan="5">
								选择人员
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								选择
							</th>
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
						</tr>

						<s:iterator value="list" id="pageUsers" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox" name="selected" value="${pageUsers.id}">
							</td>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageUsers.code}
							</td>
							<td>
								${pageUsers.name}
							</td>
							<td>
								${pageUsers.dept}
							</td>
						</s:iterator>
						<tr>
							<td colspan="11" style="font-size: 10px; font-weight: bolder;">
								<input id="checkAll" type="checkbox" name="selectAll"
									value="checkbox" onClick="selectIt()">
								<label for="checkAll">
									全选
								</label>

								<input id="checkNotAll" type="checkbox" name="invest"
									value="checkbox" onClick="selectIt()">
								<label for="checkNotAll">
									反选
								</label>
								<br>
							</td>
						</tr>
						<tr>
							<th colspan="5">
								<input type="submit" value="确定" class="input">
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function selectIt() {
	form = document.lzyy
	action = event.srcElement.name
	for ( var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].name == "selected") {
			e = form.elements[i]
			e.checked = (action == "selectAll") ? (form.selectAll.checked)
					: (!e.checked)
		}
	}
}
</script>
	</body>
</html>
