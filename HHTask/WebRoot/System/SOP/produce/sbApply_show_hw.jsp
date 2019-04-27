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
		</div>

		<div align="center">
			<h3>
				GDP待下发列表
			</h3>
			<form action="procardTemplateGyAction_findHWEcList.action"
				method="post">
				<table class="table">
					<tr>
						<td>
							ECN单号/名称/类型/编码/部件名称:
							<input name="ecaFeedBackBean.ecnNumber"
								value="${ecaFeedBackBean.ecnNumber}" />
							开始时间:
							<input class="Wdate" type="text"
								name="ecaFeedBackBean.workItemCreateTime"
								value="${ecaFeedBackBean.workItemCreateTime}" size="15"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							<input type="submit" value="查询" class="input">
						</td>
					</tr>
				</table>
			</form>
			<table class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
						序号
					</td>
					<td align="center">
						ECN单号
					</td>
					<td align="center">
						ECN名称
					</td>
					<td align="center">
						ECN类型
					</td>
					<td align="center">
						部件/文档编码
					</td>
					<td align="center">
						部件/文档名称
					</td>
					<td align="center" colspan="2">
						操作
						<br />
						(Operation)
					</td>
				</tr>
				<s:iterator value="efbbList" id="sbApply" status="pageStatus">
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
						${sbApply.ecnNumber}
					</td>
					<td align="left">
						${sbApply.ecnName}
					</td>
					<td align="left">
						${sbApply.ecType}
					</td>
					<td align="left">
						${sbApply.partDocNumber}
					</td>
					<td align="left">
						${sbApply.partDocName}
					</td>
					<td align="left">
						${sbApply.workItemCreateTime}
					</td>
					<td colspan="2">
						<a href="procardTemplateGyAction_toAddSb.action?ecaFeedBackBean.ecnName=${sbApply.ecnNumber}">发起设变</a>
					</td>

				</s:iterator>
				</tr>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="13" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
					</s:if>
					<s:else>
						<td colspan="13" align="center" style="color: red">
							${errorMessage}
					</s:else>
					</td>
				</tr>
				<s:if test="successMessage!=null">
					<tr>
						<td colspan="13" align="center" style="color: red">
							${successMessage}

						</td>
					</tr>
				</s:if>
			</table>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function view(id, obj) {
	todisabled(obj);
	window.location.href = "procardTemplateGyAction_showSbApplyJd.action?bbAply.id="
			+ id;
}
function toadd() {
	window.location.href = "procardTemplateGyAction_toAddSb.action";
}
function todelete(id) {
	if (confirm("是否确定删除!")) {
		window.location.href = "procardTemplateGyAction_deleteSb.action?id="
				+ id + "&cpage=${cpage}";
	}
}
function toquxiao(id) {
	if (confirm("是否确定删除!")) {
		window.location.href = "procardTemplateGyAction_quxiaoSb.action?id="
				+ id + "&cpage=${cpage}";
	}
}
function toclose(id) {
	if (confirm("是否确定关闭!")) {
		window.location.href = "procardTemplateGyAction_closeSb.action?id="
				+ id + "&cpage=${cpage}";
	}
}
function exprotBbAply(obj) {
	$(obj).attr("action", "procardTemplateGyAction_exprotBbAply.action");
	$(obj).submit();
	$(obj).attr("action", "procardTemplateGyAction_findSbApplyList.action")
}
function exprotBbAply_new(obj) {
	$(obj).attr("action", "procardTemplateGyAction_exprotBbAply_new.action");
	$(obj).submit();
	$(obj).attr("action", "procardTemplateGyAction_findSbApplyList.action")
}
</script>
	</body>
</html>
