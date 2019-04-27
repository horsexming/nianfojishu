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
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js">
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">领料进度</span>
						</td>
						<td align="right">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<div style="line-height: 15px;">
						总成件号:${procard.markId}&nbsp;&nbsp;&nbsp;业务件号:${procard.ywMarkId}&nbsp;&nbsp;&nbsp;总成批次:${procard.selfCard}&nbsp;&nbsp;&nbsp;订单号:${procard.orderNumber}
						&nbsp;&nbsp;&nbsp;批次数量:${procard.filnalCount}/${procard.unit}
					</div>
					<div
						style="font-size: 18px; font-weight: bolder; border-bottom: 1px solid #000000; margin-bottom: 2px; padding-left: 10px;"
						align="left">
						您当前的领料进度:
						<span id="count" style="font-size: 30px; color: red;"></span>/
						<span id="allcount" style="font-size: 30px;"></span>
					</div>
					<div id="showlllod"
						style="line-height: 10px; padding: 0 0 20 20px;" align="left"></div>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					材料清单
				</h3>
			</div>
			<div align="center">
				总成件号:${procard.markId}&nbsp;&nbsp;&nbsp;业务件号:${procard.ywMarkId}总成批次:${procard.selfCard}&nbsp;&nbsp;&nbsp;订单号:${procard.orderNumber}
				&nbsp;&nbsp;&nbsp;批次数量:${procard.filnalCount}/${procard.unit}
				<a target="showprocardjd"
					href="ProcardAction!findProcardView.action?id=${procard.rootId}&pageStatus=history&viewStatus=">生产进度</a>
			</div>
			<input type="button" value="全部确认" style="height: 50px; width: 100px;"
				onclick="sureAll()">
			<input type="button" value="导出" id="exportbtn"
				style="height: 50px; width: 100px; float: right; display: none"
				onclick="exportAll();todisabledone(this)" data="downData">
			<table class="table">
				<tr bgcolor="#e6f3fb">
					<th align="center">
						序号
					</th>
					<th align="center">
						件号
					</th>
					<th align="center" style="max-width: 80px;">
						名称
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center" style="max-width: 80px;">
						版本
					</th>
					<th align="center" style="max-width: 80px;">
						批次
					</th>
					<th align="center">
						规格
					</th>
					<th align="center" style="max-width: 80px;">
						图号
					</th>
					<th align="center">
						仓库
					</th>
					<th align="center">
						仓区
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
						单位用量
					</th>
					<th align="center">
						工序:数量
					</th>
					<th align="center">
						外委数量
					</th>
					<th align="center">
						已领数量
					</th>
					<th align="center">
						可发数量
					</th>
					<th align="center">
						库存量
					</th>
					<th align="center">
						请领数量
					</th>
					<th align="center">
						本次实发
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						操作
					</th>
				</tr>
				<s:iterator value="goodsList" id="pagegoods" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<form id="form${pageStatus.index}" action="" method="post"
						onsubmit="valeData();">
						<input type="hidden" name="id" value="${procard.id}">
						<input type="hidden" name="rootId" value="${rootId}">
						<input type="hidden" name="cardId" value="${cardId}"
							style="width: 150px;">
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>

						<td class="excludeThisClass" style="display: none">
							<s:if test="#pagegoods.goodsId!=null">
								<input type="hidden" value="${pagegoods.goodsId}"
									name="goodsList[0].goodsId">
							</s:if>
							<input type="hidden" value="${pagegoods.order_Id}"
								name="goodsList[0].order_Id">
						</td>
						<td align="left">
							${pagegoods.goodsMarkId}
							<input type="hidden" id="gsMarkId${pageStatus.index}"
								value="${pagegoods.goodsMarkId}">
							<input type="hidden" name="goodsList[0].goodsMarkId"
								value="${pagegoods.goodsMarkId}">
						</td>
						<td align="left" style="max-width: 100px;">
							${pagegoods.goodsFullName}
						</td>
						<td align="left" style="color: blue; font-weight: bolder;">
							${pagegoods.kgliao}
						</td>
						<td align="center" style="color: blue; font-weight: bolder;">
							${pagegoods.banBenNumber}
						</td>
						<td align="left">
							${pagegoods.goodsLotId}
						</td>
						<td align="left" style="max-width: 80px;">
							${pagegoods.goodsFormat}
						</td>
						<td align="left" style="max-width: 80px;">
							${pagegoods.tuhao}
						</td>
						<td align="left">
							${pagegoods.goodsClass}
						</td>
						<td align="left">
							${pagegoods.goodHouseName}
						</td>
						<td align="left">
							${pagegoods.goodsPosition}
							<s:property value="#pagegoods.zyqjCount" />
						</td>
						<td align="right">
							${pagegoods.dwyl}
						</td>
						<td align="left" style="max-width: 80px;">
							${pagegoods.gongxuName}
						</td>
						<s:if
							test="!#pagegoods.isEnough&&#pagegoods.zyqjCount!=null&&#pagegoods.zyqjCount>0">
							<td colspan="6" align="center">
								<font color="red"">占用缺件:${pagegoods.zyqjCount}</font>
							</td>
						</s:if>
						<s:else>
							<td align="right">
								${pagegoods.qlCount}
							</td>
							<td align="right">
								${pagegoods.hqlCount}
							</td>
							<td align="right">
								${pagegoods.kfCount}/${pagegoods.goodsUnit}
							</td>
							<td align="right">
								${pagegoods.goodsCurQuantity}
							</td>
							<td align="right">
								${pagegoods.goodsBeginQuantity}/${pagegoods.goodsUnit}
							</td>
							<%--							<s:if test="#pagegoods.isChangeSf">--%>
							<%--								<td align="right">--%>
							<%--									<input style="width: 90px;"--%>
							<%--										value="${pagegoods.goodsCurQuantity}"--%>
							<%--										name="goodsList[0].goodsCurQuantity">--%>
							<%--							</s:if>--%>
							<%--							<s:else>--%>
							<%--								<td align="right">--%>
							<%--									<input id="sfcount${pageStatus.index}" type="hidden"--%>
							<%--										value="${pagegoods.goodsCurQuantity}"--%>
							<%--										name="goodsList[0].goodsCurQuantity">--%>
							<%--									${pagegoods.goodsCurQuantity}--%>
							<%--							</s:else>--%>
							<td align="right">
								<input id="sfcount${pageStatus.index}" style="width: 60px;"
									value="${pagegoods.goodsCurQuantity}"
									name="goodsList[0].goodsCurQuantity"
									onkeyup="chagenumup(this,'${pagegoods.goodsCurQuantity}')">
								<input type="hidden" value="${pagegoods.flushCount}"
									name="goodsList[0].flushCount">
								<s:if
									test="#pagegoods.goodsZhishu!=null&&#pagegoods.goodsZhishu>0">

									<font color="red">(<s:property
											value="#pagegoods.goodsZhishu" />/<s:property
											value="#pagegoods.qlUnit" />)</font>
							</td>
							<input type="hidden" value="${pagegoods.goodsZhishu}"
								name="goodsList[0].goodsZhishu">
							</s:if>
							<s:else>
								</td>
							</s:else>
						</s:else>
						<td align="left">
							${pagegoods.goodsUnit}
						</td>

						<s:if test="#pagegoods.goodsId==null">
							<input type="hidden" disabled="disabled"
								class="gid<s:property value="#pagegoods.flag"/>"
								id="goodsId<s:property value='#pageStatus.index' />"
								name="selected" value="0">
							<%--更改td为了不导出input--%>
							<td>
						</s:if>
						<s:else>
							<input type="hidden" disabled="disabled"
								class="samegid gid<s:property value="#pagegoods.flag"/>"
								id="goodsId<s:property value='#pageStatus.index' />"
								name="selected" value="${pagegoods.goodsId}">
							<%--更改td为了不导出input--%>
							<td>
						</s:else>
						<s:if test="#pagegoods.isEnough">
							<input type="button" data="${pageStatus.index}" value="确认"
								style="background-color: yellow"
								class="samebtn btn<s:property value="#pagegoods.flag"/>"
								onClick="confirmGoods(<s:property value='#pageStatus.index' />,'<s:property value='#pagegoods.goodsMarkId' />','<s:property value="#pagegoods.flag"/>')">
							<input type="button" data="${pageStatus.index}" value="取消"
								style="background-color: green; display: none;"
								class="samefbtn fbtn<s:property value="#pagegoods.flag"/>"
								onClick="confirmGoods2(<s:property value='#pageStatus.index' />,'<s:property value='#pagegoods.goodsMarkId' />','<s:property value="#pagegoods.flag"/>')">
						</s:if>
						<s:else>
							<div>
								<s:if test="#pagegoods.tqlCount!=null&&#pagegoods.tqlCount>0">
									<font color="red">缺料${pagegoods.tqlCount}</font>
									<a
										href="procardBlAction_findLackGoodsDetail.action?goods.goodsMarkId=${pagegoods.goodsMarkId}"
										target="view_window">查看</a>
								</s:if>
								<s:else>
									<font color="red">库存不够</font>
								</s:else>
							</div>
						</s:else>
						<s:iterator id="pageinputSource"
							value="#pagegoods.inputSource.split(';')">
							<input type="hidden" style="width: 60px;" name="checkboxs2"
								value="<s:property value='#pageinputSource.split(",")[0]' />" />
							<input type="hidden" style="width: 40px;" name="peiqiCount"
								value="<s:property value='#pageinputSource.split(",")[1]' />" />
						</s:iterator>
						</td>
						<%--					<td id="htd1" colspan="20" align="center">--%>
						<%--						请输入刷卡人密码:--%>
						<%--						<input id="skinput" type="hidden" name="cardId" value="${cardId}"--%>
						<%--							style="width: 150px;">--%>
						<%--						<input id="skpassword" type="password" name="password"--%>
						<%--							style="width: 150px;">--%>
						<%--						<input id="sbtn" type="submit" value="领取">--%>
						<%--					</td>--%>
					</form>
					</tr>
				</s:iterator>
				<tr id="htr1" style="display: none;">
					<td id="htd1" colspan="21" align="center">
						请输入刷卡人密码:
						<input id="skpassword" type="password" name="password"
							style="width: 150px;">
						<input id="sbtn" type="button" value="领取" class="input"
							onclick="singlesubmit(this)">
					</td>
				</tr>
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
var formIndex = new Array();
var count = 0;
function confirmGoods(obj, goodsMarkId, btnclass) {
	$(".gid" + btnclass).removeAttr("disabled");
	$(".btn" + btnclass).hide();
	$(".fbtn" + btnclass).show();
	$("#htr1").show();
	$(".btn" + btnclass).each(function(index, obj) {
		formIndex[$(obj).attr("data")] = $(obj).attr("data");
		count++;
	});
}
function confirmGoods2(obj, goodsMarkId, btnclass) {
	$(".gid" + btnclass).attr("disabled", "disabled");
	$(".btn" + btnclass).show();
	$(".fbtn" + btnclass).hide();
	$(".btn" + btnclass).each(function(index, obj2) {
		formIndex[$(obj2).attr("data")] = null;
		count--;
	});
	if (count <= 0) {
		$("#htr1").hide();
	}
}
//全部确认
function sureAll() {
	$(".samegid").removeAttr("disabled");
	$(".samebtn").hide();
	$(".samebtn").each(function(index, obj) {
		if (formIndex[$(obj).attr("data")] == null) {
			count++;
		}
		formIndex[$(obj).attr("data")] = $(obj).attr("data");
	});
	$(".samefbtn").show();
	$("#htr1").show();
}

function valeData() {
	//$("#skinput").attr("readonly","readonly");
	var password = $("#skpassword").val();
	if (password == "") {
		alert("请先输入密码后再点击领取!");
		return false;
	}
	$("#skpassword").attr("readonly", "readonly");
	$("#sbtn").attr("disabled", "disabled");
}
//单条提交数据
var i = 0;
var nowcount = 0;
var password = "";
var len = 0;
function singlesubmit(obj) {
	if (i == 0) {
		if (count <= 0) {
			alert("请至少确认一项物料后再点击");
			return false;
		}
		password = $("#skpassword").val();
		if (password == "") {
			alert("请先输入密码后再点击领取!");
			return false;
		}
		$("#skpassword").attr("readonly", "readonly");
		$("#sbtn").attr("disabled", "disabled");

		$("#allcount").html(count);
		len = formIndex.length;
		chageDiv('block');
		$("#bodyDiv").unbind("click");
		$("#contentDiv").css("top", "10px");
		$("body").scrollTop(0);
	}
	var singleIndex = formIndex[i];
	if ((i + 1) <= len) {
		i++;
	} else {
		$("#showlllod")
				.append(
						"<div align='center'>"
								+ "<input class='input' value='确认' type='button' onclick='tohref()'>"
								+ "</div>");
		return "";
	}
	if (singleIndex != null) {
		nowcount++;
		$("#count").html(nowcount);
		$("#showlllod")
				.append(
						"第<b>"
								+ (nowcount)
								+ "</b>条:<font style='font-weight: bolder;font-size:14px;'>件号:"
								+ $("#gsMarkId" + singleIndex).val() + ",数量:"
								+ $("#sfcount" + singleIndex).val()
								+ ",</font>领料状态:");
		$
				.ajax( {
					type : "POST",
					url : "procardBlAction_outDetail.action?cpage=" + i
							+ "&password=" + password,
					dataType : "json",
					data : $("#form" + singleIndex).serialize(),
					success : function(rs) {
						i = parseInt(rs[0]);
						$("#showlllod").append(
								"<font color='red'>" + rs[1] + "</font><hr/>");
						if (rs[1] == "卡号有误!" || rs[1] == "刷卡人密码有误!") {
							$("#showlllod")
									.append(
											"<div align='center'><font color='red'>无法继续领料,请确认后重试!</font><br/><br/>"
													+ "<input class='input' value='确认' type='button' onclick='tohref()'>"
													+ "</div>");
						} else {
							singlesubmit();//再次调用，解决Ajax同步锁导致页面假死已经js线程阻塞的问题
						}
					},
					error : function(msg) {
						$("#showlllod").append(
								"<font color='red'>领取失败!</font><hr/>");
					}
				});
	} else {
		singlesubmit();
	}
}

function exportAll() {
	$(".table").table2excel( {
		exclude : ".excludeThisClass",//BUG // hidden input移出TD // 格式css selector 
		name : "材料清单",
		filename : "${procard.orderNumber}_材料清单" //do not include extension
	});
}

$(document).ready(function() {
	$("#exportbtn").show();
});

function tohref() {
	window.location = "procardBlAction_findProcardllDetailbyRootId.action?id=${procard.id}&rootId=${rootId}";
}

</script>
	</body>
</html>
