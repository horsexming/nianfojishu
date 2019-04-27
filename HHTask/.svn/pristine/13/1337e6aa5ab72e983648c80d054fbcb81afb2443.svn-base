<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<form id="findSjForm" action="ProcardAction!findbreaksubmitList.action"
					method="post">
					<input id="operators" name="breaksubmit.tjUsersName" type="hidden">
					<input  name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input id="markIds" type="text"
									name="breaksubmit.wgmarkId" style="width: 110px;"
									value="${breaksubmit.wgmarkId}" />
								<input type="submit" style="width: 110px; height: 30px;"
									value="查询" />
							</td>
							<td align="center">
								<input type="button" style="width: 70px; height: 60px;"
									onclick="javascript:window.location.href = 'UsersAction!sczzIndex.action';"
									value="首页" />
							</td>
							<td align="center">
								<input type="button" style="width: 70px; height: 60px;"
									onclick="javascript:window.location.href = 'InsRecord_getToXjList.action?processInfor.procard.markId=';"
									value="刷新" />
							</td>
							<td>
								<a href="logOff__sczd.jsp"> <input type="button"
										style="width: 70px; height: 60px;" value="[退出]" /> </a>
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th colspan="3" style="color: red;">
							待检验项
						</th>
					</tr>
					<s:if test="{bsList.size()>0}">
						<s:iterator value="bsList" status="se" id="pagebreak">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<tr>
								<th rowspan="3">
									<div
										onclick="getcheckList2('${pagebreak.id}' )"
										style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
										<br />
										${pagebreak.gongwei}
										<br />
										待检码
										<br />
										扫描
									</div>
								</th>
							</tr>
							<tr>
								<th>
									<a href="javascript:;"
										onclick="toFindForm('markIds','${pagebreak.markId}')">${pagebreak.wgmarkId} </a>
								</th>
								<td>
									${pagebreak.wgproName}
								</td>
								<td>
									${pagebreak.wgselfcard}
								</td>
							</tr>
							<tr>
								<th align="left">
									所属工序	[第${pagebreak.processNo}工序]${pagebreak.processName}
								</th>
								<th align="left">
									所属自制件 ${pagebreak.markId}
								</th>
								<td>
									<a href="javascript:;"
										onclick="toFindForm('operators','${pagebreak.tjUsersName}')">${pagebreak.tjUsersName}</a>
								</td>
							</tr>
							<tr>
								<td colspan="5"></td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="15">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

								</th>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td style="font-size: 15px; color: red;">
								对不起，没有查到相关的巡检信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getcheckList2(id,id2) {
	window.location.href = "InsRecord_towgsqjy.action?id=" + id
			+ "&cpage=${cpage}&tag=${pageStatus}";
}
function toFindForm(ids, values) {
	$("#" + ids).val(values);
	$("#findSjForm").submit();
}
</script>
	</body>
</html>
