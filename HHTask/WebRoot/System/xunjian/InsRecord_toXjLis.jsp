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
			<div>
				<div
					style="float: left; width: 48%; color: red; font-weight: bolder;"
					align="left">
					<br />
					待检验项:
					<s:property value="processList.size*total" />
					项,加油加油~
				</div>
				<div align="right" style="float: left; width: 49%">
					质检员:${Users.name}!
					<s:if test='#session.Users.sex =="男"'>
						<img alt="${Users.name}"
							src="upload/user/${Users.password.picture}" height="40"
							width="40" style="border: solid 1px #000000; border-radius: 50%;"
							onerror="this.src='images/man.jpg'">
					</s:if>
					<s:else>
						<img alt="${Users.name}"
							src="upload/user/${Users.password.picture}" height="88"
							width="89" style="border: solid 1px #000000; border-radius: 50%;"
							onerror="this.src='images/woman.jpg'">
					</s:else>

				</div>
			</div>
			<div style="clear: both;"></div>
			<div align="center">
				<form id="findSjForm" action="InsRecord_getToXjList.action"
					method="post">
					<input id="operators" name="processInfor.usernames" type="hidden">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input id="markIds" type="text"
									name="processInfor.procard.markId" style="width: 110px;"
									value="${processInfor.procard.markId}" />
								<input type="submit" style="width: 110px; height: 30px;"
									value="查询" />
							</td>
							<th>
								工序名称:
							</th>
							<td>
								<input id="processName" type="text"
									name="processInfor.processName" style="width: 110px;"
									value="${processInfor.processName}" />
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
						<th colspan="4" style="color: red;">
							待巡检项
						</th>
					</tr>
					<s:if test="processList.size()>0">
						<s:iterator value="processList" status="se" id="pageprocess">
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
										onclick="getcheckList2(${id},'','${pageprocess.gongwei}' )"
										style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
										<br />
										${pageprocess.gongwei}
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
										onclick="toFindForm('markIds','${pageprocess.procard.markId}')">${pageprocess.procard.markId}</a>
								</th>
								<td>
									${pageprocess.procard.proName}
								</td>
								<td>
									${pageprocess.procard.selfCard}
								</td>
							</tr>
							<tr>
								<th align="left">
									[第${pageprocess.processNO}工序]
									<a href="javascript:;"
										onclick="toFindForm('processName','${pageprocess.processName}')">${pageprocess.processName}</a>

								</th>
								<td>
									<fmt:formatNumber
										value="${pageprocess.applyCount-pageprocess.submmitCount}"
										pattern="#" />
									PSC
								</td>
								<td>
									<a href="javascript:;"
										onclick="toFindForm('operators','${pageprocess.usernames}')">${pageprocess.usernames}</a>
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
							<td style="font-size: 15px; color: red;" colspan="4">
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
function getcheckList2(id) {
	window.location.href = "InsRecord_getXjProcess.action?id=" + id
			+ "&cpage=${cpage}";
}
function toFindForm(ids, values) {
	$("#" + ids).val(values);
	$("#findSjForm").submit();
}
</script>
	</body>
</html>
