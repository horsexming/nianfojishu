<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<STYLE type="text/css">
.ztree li a {
	color: #fff;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					待审批付款记录
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>

				<form action="ReceiptAction!auditPay.action" method="post"
					onsubmit="return window.confirm('您确定要批准付款吗？')">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								<input type="hidden" name="pageStatus" value="${pageStatus}">
								<input type="checkbox" id="checkId"
									onclick="chageAllCheck(this,'showCheckDetail')">
							</td>
							<td align="center">
								序号
							</td>
							<td align="center">
								付款单号
							</td>
							<td align="center">
								类型
							</td>
							<td align="center">
								收款单位
							</td>
							<td align="center">
								摘要
							</td>
							<td align="center">
								应付金额
							</td>
							<td align="center">
								已付金额
							</td>
							<td align="center">
								未付金额
							</td>
							<td align="center">
								付款中金额
							</td>
							<td align="center">
								调整金额
							</td>
							<td align="center">
								关联单据
							</td>
							<td align="center">
								申请时间
							</td>
							<td align="center">
								付款周期(天)
							</td>
							<td align="center">
								应付款日期
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								操作
							</td>
						</tr>
						<s:iterator value="receiptLogList" id="pagecoreReceiptLog"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox"
									onclick="chageNum(this,'showCheckDetail')" name=""
									value="${pagecoreReceiptLog.id}"
									data="${pagecoreReceiptLog.receipt.payOn}"
									data2="${pageIndex.index}">
							</td>
							<td>
								<s:property value="#pageIndex.index+1" />
							</td>
							<td>
								${pagecoreReceiptLog.receipt.pkNumber}
							</td>
							<td>
								${pagecoreReceiptLog.receipt.payType}
							</td>
							<td align="left">
								<a
									href="CorePayableAction!findSupplierCorePayableList.action?supplierCorePayable.supplierId=${pagecoreReceiptLog.receipt.payeeId}">
									${pagecoreReceiptLog.receipt.payee} </a>
							</td>
							<td align="left">
								${pagecoreReceiptLog.receipt.summary}
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecoreReceiptLog.receipt.allMoney}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecoreReceiptLog.receipt.accountPaid}"
									pattern="0.00" maxFractionDigits="2" />
							</td>
							<td>
								<fmt:formatNumber type="number"
									value="${pagecoreReceiptLog.receipt.unPay}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number"
									value="${pagecoreReceiptLog.receipt.payIng}" pattern="0.00"
									maxFractionDigits="2" />
							</td>
							<td style="color: red;">
								<input name="" id="payIng_${pageIndex.index}"
									value="<fmt:formatNumber type="number"
								value="${pagecoreReceiptLog.allMoney}" pattern="0.00"
								maxFractionDigits="2" />" />
							</td>
							<td>
								<s:if test="#pagecoreReceiptLog.receipt.fk_fundApplyId>0">
									<a
										href="FundApplyAction_findfundDetailedList.action?id=${pagecoreReceiptLog.receipt.fk_fundApplyId}">${pagecoreReceiptLog.receipt.aboutNum}</a>
								</s:if>
								<s:else>
									<a
										href="CorePayableAction!findCorePaybleList.action?corePayable.fk_CPMId=${pagecoreReceiptLog.receipt.fk_monthlyBillsId}&pageStatus=all">${pagecoreReceiptLog.receipt.aboutNum}</a>
								</s:else>
							</td>
							<td>
								${pagecoreReceiptLog.addTime}
							</td>
							<td>
								${pagecoreReceiptLog.receipt.paymentCycle}
							</td>
							<td>
								${pagecoreReceiptLog.receipt.fukuanDate}
							</td>
							<td>
								${pagecoreReceiptLog.receipt.status}
							</td>
							<td>
								<a
									href="ReceiptAction!findReceiptLogList.action?receiptLog.receipt.id=${pagecoreReceiptLog.receipt.id}">付款记录</a>
							</td>
						</s:iterator>

						<tr>
							<td colspan="25" align="left"
								style="color: red; font-size: 20px;">
								<span id="showCheckDetail"></span>
								<div id="div_bank" style="display: none;">
									<span>银行账户:</span>
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="wgType" type="text" readonly="readonly" value=""
													style="width: 120px;" name="" />
												<input type="hidden" value="" id="subId" name="subId" />
												<input type="hidden" value="" id="fatherSubName" />
												<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>(按住Ctrl建不松点击,可清空)
											</li>
										</ul>
									</div>
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 160px;"></ul>
									</div>
								</div>
							</td>
						</tr>

<%--												<tr>--%>
<%--													<td></td>--%>
<%--													<td colspan="25" align="left"--%>
<%--														style="color: red; font-size: 20px;">--%>
<%--														<span id="showCheckDetail">已选中:0条 总计：0.00</span>--%>
<%--													</td>--%>
<%--												</tr>--%>
						<tr>
							<th colspan="25">
								<input type="submit" value="确认付款" class="input">
							</th>
						</tr>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="25" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="20" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
					</table>
				</form>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
<%--var num = 0;--%>
<%--var other = 0;--%>
<%--function chageNum(obj, numId) {--%>
<%--	var check = obj;--%>
<%--	var checkAll = document.getElementById("checkAll");--%>
<%--	var checkAll2 = document.getElementById("checkAll2");--%>
<%--	if (checkAll == null) {--%>
<%--		checkAll = $(obj).parents("table").find("input[type='checkbox']")[0];--%>
<%--	}--%>
<%--	if (checkAll2 == null) {--%>
<%--		checkAll2 = $(obj).parents("table").find("input[type='checkbox']")[0];--%>
<%--	}--%>
<%--	if (check.checked == true) {--%>
<%--		var inputs = document.getElementsByTagName("input");--%>
<%--		var status = true;--%>
<%--		for ( var i = 0; i < inputs.length; i++) {--%>
<%--			if (inputs[i].type == "checkbox") {--%>
<%--				var checkBox = inputs[i];--%>
<%--				if (checkBox != checkAll2 && checkBox != checkAll) {--%>
<%--					if (checkBox.checked == false) {--%>
<%--						status = false;--%>
<%--						break;--%>
<%--					}--%>
<%--				}--%>
<%--			}--%>
<%--		}--%>
<%--		if (status == true) {--%>
<%--			checkAll.checked = true;--%>
<%--			checkAll2.checked = true;--%>
<%--		}--%>
<%--		num++;--%>
<%--		if ($(obj).attr("data") != '') {--%>
<%--			other += parseFloat($(obj).attr("data"));--%>
<%--		}--%>
<%--	} else if (num == 0 && check.checked == false) {--%>
<%--		num = 0;--%>
<%--	} else {--%>
<%--		if (checkAll.checked == true || checkAll2.checked == true) {--%>
<%--			checkAll.checked = false;--%>
<%--			checkAll2.checked = false;--%>
<%--		}--%>
<%--		num--;--%>
<%--		if ($(obj).attr("data") != '') {--%>
<%--			other -= parseFloat($(obj).attr("data"));--%>
<%--		}--%>
<%--	}--%>
<%--	if (numId != "") {--%>
<%--		$("#" + numId).html("已选中:" + num + "条 总计：" + other.toFixed(2));--%>
<%--	}--%>
<%----%>
<%--	//处理name--%>
<%--	var index = $(obj).attr("data2");--%>
<%--	if (obj.checked) {--%>
<%--		obj.name = "receiptLogList[" + index + "].id";--%>
<%--		document.getElementById("payIng_" + index).name = "receiptLogList["--%>
<%--				+ index + "].allMoney";--%>
<%--	} else {--%>
<%--		obj.name = "";--%>
<%--		document.getElementById("payIng_" + index).name = "receiptList["--%>
<%--				+ index + "].payIng";--%>
<%--	}--%>
<%--}--%>

// 选择付款 开始
var pageStatus = "${pageStatus}";
function chageName(obj, index) {
	if (obj.checked) {
		obj.name = "receiptList[" + index + "].id";
		if (pageStatus == "dfk") {
			document.getElementById("payOn_" + index).name = "receiptList["
					+ index + "].payOn";
		} else {
			document.getElementById("payIng_" + index).name = "receiptList["
					+ index + "].payIng";
		}
	} else {
		obj.name = "";
		if (pageStatus == "dfk") {
			document.getElementById("payOn_" + index).name = "receiptList["
					+ index + "].payOn";
		} else {
			document.getElementById("payIng_" + index).name = "receiptList["
					+ index + "].payIng";
		}
	}
}

function chageAllCheck(obj, numId) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox, numId);
				}
			}
		}
	}
}
var num = 0;
var other = 0;
function chageNum(obj, numId) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (checkAll == null) {
		checkAll = $(obj).parents("table").find("input[type='checkbox']")[0];
	}
	if (checkAll2 == null) {
		checkAll2 = $(obj).parents("table").find("input[type='checkbox']")[0];
	}
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox != checkAll2 && checkBox != checkAll) {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
		if ($(obj).attr("data") != '') {
			other += parseFloat($(obj).attr("data"));
		}
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
		if ($(obj).attr("data") != '') {
			other -= parseFloat($(obj).attr("data"));
		}
	}
	if (numId != "") {
		$("#" + numId).html("已选中:" + num + "条 总计：" + other.toFixed(2));
	}



	//处理name
	var index = $(obj).attr("data2");
	if (obj.checked) {
		obj.name = "receiptLogList[" + index + "].id";
		document.getElementById("payIng_" + index).name = "receiptLogList["
				+ index + "].allMoney";
	} else {
		obj.name = "";
		document.getElementById("payIng_" + index).name = "receiptList["
				+ index + "].payIng";
	}
	
	
<%--	var index = $(obj).attr("data2");--%>
<%--	if (obj.checked) {--%>
<%--		obj.name = "receiptList[" + index + "].id";--%>
<%--		if (pageStatus == "dfk") {--%>
<%--			document.getElementById("payOn_" + index).name = "receiptList["--%>
<%--					+ index + "].payOn";--%>
<%--		} else {--%>
<%--			document.getElementById("payIng_" + index).name = "receiptList["--%>
<%--					+ index + "].payIng";--%>
<%--		}--%>
<%--	} else {--%>
<%--		obj.name = "";--%>
<%--		if (pageStatus == "dfk") {--%>
<%--			document.getElementById("payOn_" + index).name = "receiptList["--%>
<%--					+ index + "].payOn";--%>
<%--		} else {--%>
<%--			document.getElementById("payIng_" + index).name = "receiptList["--%>
<%--					+ index + "].payIng";--%>
<%--		}--%>
<%--	}--%>
	$("#div_bank").show();
}

//下拉树形

var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'huikuanAction!findBankSub.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							name : $(this).attr('name') + " 余额:"
									+ $(this).attr('borrowJieyuMoney'),
							fatherName : $(this).attr('fatherName'),
							target : "main",
							click : false
						});

					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择最底层");
	return check;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	var id = 0, fatherName = '';
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
		id = nodes[i].id;
		fatherName = nodes[i].fatherName;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);
	$("#subId").val(id);
	$("#fatherSubName").val(fatherName);
}

function showMenu() {
	var cityObj = $("#wgType");
	var cityOffset = $("#wgType").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
//下拉树形结束

function validate() {
	var subId = $("#subId").val();
	if (subId == '') {
		alert("请选择一个银行账户付款")
		return false;
	}
}
</script>
	</body>
</html>
