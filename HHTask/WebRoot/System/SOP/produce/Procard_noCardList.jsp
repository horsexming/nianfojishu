<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none');">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 100%; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<s:if test="viewStatus=='zjl'">
								<a href="javascript:history.go(-1);">返回</a>
							</s:if>
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
			</div>
			<div id="operatingDiv"
				style="background-color: #ffffff; width: 100%;">
				<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
			</div>
			<div id="lingLiaoDiv" style="background-color: #ffffff; width: 100%;">
				<iframe id="showLingLiaoIf" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<s:if test="tc=='no'">
						<div>
							<input class="input" onclick="window.history.back();"
								type="button" value="返回"
								style="height: 38px; width: 50%; float: left;">
							<input class="input" onclick="zhuye()" type="button" value="主页"
								style="height: 38px; width: 50%; float: right;">
						</div>
					</s:if>
<%--					<s:if test="pageStatus=='lingqu'||requestScope.pageStatus=='lingqu'">--%>
						<form action="OneMachineAction_findProcardByCardGxNum.action"
							method="post">
							<input name="pageStatus" value="${pageStatus}" type="hidden" />
							<input name="gongwei" value="${gongwei}" type="hidden" />
							<input name="tc" value="${tc}" type="hidden" />
							<table class="table">
								<tr>
									<td align="center">
										件号：
									</td>
									<td align="center">
										<input type="text" name="procard.markId"
											value="${procard.markId}" />
									</td>
									<td align="center">
										业务件号：
									</td>
									<td align="center">
										<input type="text" name="procard.ywMarkId"
											value="${procard.ywMarkId}" />
									</td>
								</tr>
								<tr>
									<td align="center">
										批次：
									</td>
									<td align="center">
										<input type="text" name="procard.selfCard"
											value="${procard.selfCard}" />
									</td>
									<td align="center">
										内部订单号：
									</td>
									<td align="center">
										<input type="text" name="procard.orderNumber" value="${procard.orderNumber}" />
									</td>
								</tr>
								<tr>
									<td align="center">
										零件名称：
									</td>
									<td align="center">
										<input type="text" name="procard.proName"
											value="${procard.proName}" />
									</td>
									<td align="center">
										工序名称：
									</td>
									<td align="center">
										<input type="text" name="procard.unit" value="${procard.unit}" />
									</td>
								</tr>
								<tr>
									<td align="center" colspan="4">
										<input type="submit" style="height: 38px; width: 100px;"
											value="查询生产任务" onclick="todisabled(this)" />
										<%--										<input class="input" onclick="window.history.back();" type="button" value="返回" style="height: 38px;width: 56px;">--%>
									</td>
								</tr>
							</table>
						</form>
<%--					</s:if>--%>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								总成件号
							</th>
							<th align="center">
								总成批次
							</th>
							<th align="center">
								内部订单号
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								批次
							</th>
							<th align="center" style="max-width: 100px;">
								名称
							</th>
							<th align="center">
								卡片类型
							</th>
							<th align="center">
								产品类型
							</th>
							<th align="center">
								要求开始生产时间
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								未领数量
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="procardList" id="pageProcard"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')"
									onclick="clickBgcolor(this,'#FBEC88')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')"
									onclick="clickBgcolor(this,'#FBEC88')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageProcard.rootMarkId}

								<s:if
									test="#pageProcard.ywMarkId!=null&&#pageProcard.ywMarkId!=''">
									<br />
									(<font color="green" style="font-weight: bolder;">${pageProcard.ywMarkId}</font>)
									</s:if>
							</td>
							<td>
								${pageProcard.rootSelfCard}
							</td>
							<td>
								<font color="red">${pageProcard.orderNumber}</font>
							</td>
							<th align="left">
								<font color="red"> ${pageProcard.markId}</font>
							</th>
							<td align="left">
								<font color="red">${pageProcard.selfCard}</font>
							</td>
							<td align="left" style="max-width: 100px;">
								<font size="1">${pageProcard.proName}</font>
							</td>
							<td>
								${pageProcard.procardStyle}
							</td>
							<td>
								${pageProcard.productStyle}
							</td>
							<td>
								${pageProcard.jihuoDate}
							</td>
							<td align="right">
								<fmt:formatNumber value="${pageProcard.filnalCount}" pattern="#" />
							</td>
							<td style="color: red" align="right">
								<s:if test="pageStatus=='noCardLingliao'">
									<s:if test="#pageProcard.hascount==null">
										${pageProcard.filnalCount}
										</s:if>
									<s:else>
											${pageProcard.hascount}
										</s:else>
								</s:if>
								<s:else>
									<fmt:formatNumber
										value="${pageProcard.filnalCount-pageProcard.tjNumber}"
										pattern="#" />
								</s:else>
							</td>
							<td>
								${pageProcard.status}
							</td>
							<td>
								<s:if test="pageStatus=='noCardLingliao'">
									<input type="button" value="领料"
										onclick="showLingLiao(${pageProcard.id},this)"
										style="width: 70px; height: 40px;" />
									<br />
									<s:if test="tag!='code'">
										<a
											href="ProcardAction!findProcardView.action?id=${pageProcard.rootId}&pageStatus=history"
											target="showProView">领料进度</a>
									</s:if>
								</s:if>
								<s:else>
									<input type="button" value="领工序"
										onclick="showLingGx(${pageProcard.id},this)"
										style="width: 70px; height: 40px;" />
									<a
										href="ProcardAction!findProcardView.action?id=${pageProcard.rootId}&pageStatus=history"
										target="showProView">生产进度</a>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="17" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="17" align="center" style="color: red">
							</s:else>
						</tr>
						<s:if test="errorMessage!=null && errorMessage!= ''">
							<tr>
								<th colspan="15">
									<font color="red" size="5"> ${errorMessage}</font>
								</th>
							</tr>
						</s:if>
					</table>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<script>
function showLingLiao(id, obj) {
	if ('${tag}' == "phone" || '${tag}' == "code") {
		window.location.href = "sellAction!procardLingliaonew.action?id=" + id
				+ "&status=${tag}&cardNumber=${cardNumber}";
	} else {
		$("#showLingLiaoIf").css("height", "0px");
		$("#showLingLiaoIf").attr(
				"src",
				"sellAction!procardLingliaonew.action?id=" + id
						+ "&status=${tag}");
		$("#operatingDiv").hide();
		chageDiv('block');
		$("#contentDiv").removeAttr("css");
		$("#contentDiv").css("left", "0");
		$("#contentDiv").addClass("contentDiv100");//关闭统一样式
		var gettop = $(obj).offset().top - 200;
		if (gettop < 0) {
			gettop = 0;
		}
		//单独设置弹出层的高度
		var thisTopHeight = gettop;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});
	}
}

function showLingGx(id, tc) {
	window.location.href = "ProcardAction!findProcardByRunCard2.action?id="
			+ id + "&pageStatus=noCardLingGx&viewStatus=${viewStatus}&tc=${tc}";
}

function showLingGx1(id, obj) {
	$("#showProcess").css("height", "0px");
	$("#showProcess").attr(
			"src",
			"ProcardAction!findProcardByRunCard2.action?id=" + id
					+ "&pageStatus=noCardLingGx&viewStatus=${viewStatus}");
	$("#lingLiaoDiv").hide();
	chageDiv('block');
	$("#contentDiv").removeAttr("css");
	$("#contentDiv").css("left", "0");
	$("#contentDiv").addClass("contentDiv100");//关闭统一样式
	var gettop = $(obj).offset().top - 250;
	if (gettop < 0) {
		gettop = 0;
	}
	//单独设置弹出层的高度
	var thisTopHeight = gettop;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
function zhuye() {
	window.location.href = "ProdEquipmentAction!checkMachiner.action";
}
$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	var contentDiv = $(window.parent.document).find("#contentDiv");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
		contentDiv.height(thisheight2 + 200);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})
</script>
	</body>
</html>
