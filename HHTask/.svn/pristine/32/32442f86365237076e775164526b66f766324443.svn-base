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
			<div>
				<div
					style="float: left; width: 48%; color: red; font-weight: bolder;"
					align="left">
					<br />
					待检验项:
					<s:property value="list.size*total" />
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
					<a href="logOff.jsp"> [退出] </a>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div align="center">
				<form id="findSjForm" action="LogoStickerAction!findSjList.action"
					method="post">
					<input id="operators" name="sticker.operator" type="hidden">
					<table class="table">
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input id="markIds" type="text" name="sticker.markId"
									style="width: 110px;" value="${sticker.markId}" />
								<br />
								<input type="submit" style="width: 110px; height: 30px;"
									value="查询" />
							</td>
							<th>
								工序名称:
							</th>
							<td>
								<input id="processName" type="text" name="sticker.processName"
									style="width: 110px;" value="${sticker.processName}" />
								<br />
								<input type="submit" style="width: 110px; height: 30px;"
									value="查询" />
							</td>
							<td align="center">
								<input type="button" style="width: 70px; height: 60px;"
									onclick="autocheckList()"
									value="扫码" />
							</td>
							<td align="center">
								<input type="button" style="width: 70px; height: 60px;"
									onclick="javascript:window.location.href = 'LogoStickerAction!findSjList.action?sticker.markId=';"
									value="刷新" />
							</td>
							<td>
							</td>
						</tr>
					</table>
				</form>
				<%--<table style="width: 100%;border-collapse: collapse;border: ">
					--%>
				<table class="table">
					<tr>
						<th colspan="4" style="color: red;">
							待首检项
						</th>
					</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="pageProcard">
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
										onclick="getcheckList2(${pageProcard.id},'${pageProcard.number}','${pageProcard.gongwei}' )"
										style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
										<br />
										${pageProcard.gongwei}
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
										onclick="toFindForm('markIds','${pageProcard.markId}')">${pageProcard.markId}</a>
								</th>
								<td>
									${pageProcard.partsName}
								</td>
								<td>
									${pageProcard.lotId}
								</td>
							</tr>
							<tr>
								<td>
									${pageProcard.processNO}
								</td>
								<th>
									<a href="javascript:;"
										onclick="toFindForm('processName','${pageProcard.processName}')">${pageProcard.processName}</a>
								</th>
								<td>
									<a href="javascript:;"
										onclick="toFindForm('operators','${pageProcard.operator}')">${pageProcard.operator}</a>
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
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var idforAction = "";
var daijianma = "";
var gongwei = "";
function getcheckList2(id, djm, gw) {
	if (typeof (myObj) != "undefined") {
		idforAction = id;
		daijianma = djm;
		gongwei = gw;
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		window.location.href = "LogoStickerAction!getcheckList2.action?id="
				+ id + "&cpage=${cpage}";
	}
}
function autocheckList() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("未检测到摄像头!无法扫码!");
	}
}
function funFromjs(tm) {
	if (tm == daijianma) {
		window.location.href = "LogoStickerAction!getcheckList2.action?id="
				+ idforAction + "&cpage=${cpage}";
	} else if ("" == daijianma && tm != "") {
		window.location.href = "LogoStickerAction!getcheckList2.action?content="
				+ tm + "&cpage=${cpage}";
	} else {
		alert("请您先前往" + gongwei + "工位再进行首检操作!谢谢您的配合!");
	}
}

function toFindForm(ids, values) {
	$("#" + ids).val(values);
	$("#findSjForm").submit();
}
</script>
	</body>
</html>
