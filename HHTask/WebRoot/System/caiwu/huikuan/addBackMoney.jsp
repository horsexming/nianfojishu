<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">
					<font color="#ffffff">编号：<s:property value="taHk.hkNum" />
					</font>
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<div id="hk">
					<table class="table" id="showPrice" border="0" id="showPrice"
						style="padding: 0px; margin: 0px; display: block;">
						<tr>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">客户: </span> ${taHk.hkClientComp}
									</li>
									<li>
										<span class="span1">回款金额 : </span>${taHk.hkTrackerTotMoney}
										${taHk.hkMoneyUnit}
										<li>
											<span class="span1">开票金额 : </span>${taHk.hkBillMoney}
											${taHk.hkMoneyUnit}

										</li>
										<li>
											<span class="span1">申请开票时间 : </span> ${taHk.hkAppPayDate}
										</li>
										<li>
											<span class="span1">目前状态 : </span> ${taHk.hkStatus}
										</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">
									<li>
										<span class="span1">开票总数 : </span> ${taHk.hkApplyCount}
									</li>
									<li>
										<span class="span1">&nbsp;回款周期 :</span>${taHk.hkPayCycle}天
									</li>
									<li>
										<span class="span1">回款率 : </span>
										<s:if test="%{null!=taHk.hkTrackRate}">${taHk.hkTrackRate}%</s:if>
									</li>
									<li>
										<span class="span1">追款人 : </span> ${taHk.hkZhuikuanren}
									</li>
									<li>
										<span class="span1">申 请 &nbsp;人 : </span> ${taHk.hkApplier}
									</li>
								</ul>
							</td>
							<td>
								<ul class="userCenter">

									<li>
										<span class="span1">开票审批通过时间 : </span> ${taHk.hkNotOverTime}
									</li>
									<li>
										<span class="span1">开发票时间 : </span> ${taHk.hkBillTime}
									</li>
									<li>
										<span class="span1">客户负责人 : </span> ${taHk.hkClientName}
									</li>
									<li>
										<span class="span1">开票负责人 : </span> ${taHk.hkBiller}
									</li>
									<li>
										<span class="span1">开票审核通过时间: </span> ${taHk.hkInvoOverTime}
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="padding-left: 38px;">
								<span class="span1">备注 : </span>${taHk.hkMore}
							</td>
						</tr>
					</table>
				</div>
				<br />
				<div id="newBack">
					<form action="huikuanAction!saveBackMoney.action" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="taHk.id" value="${taHk.id}" />
						<input type="hidden" id="staSize"
							value="<s:property value="listHkSellSta.size()"/>" />
						<table class="table">
							<tr>
								<td align="right">
											客户
								</td>
								<td colspan="3">
									<input name="backMoney.hkbmClientCom"
										value="${taHk.hkClientComp }" />
								</td>
								<td align="right">
										回款金额
								</td>
								<td colspan="3">
									<font id="taotalMoney">0</font>
									<input type="hidden" name="backMoney.hkbmMoney" id="hkbmMoney"
										value="0" />
									(${taHk.hkMoneyUnit})
								</td>
							</tr>
							<tr>
								<td align="right">
										关联客户
								</td>
								<td colspan="3">
									<input name="backMoney.hkbmClientUser" />
								</td>
								<td align="right">
										回款时间
								</td>
								<td colspan="3">
									<input class="Wdate" type="text" name="backMoney.hkbmDate"
										size="15"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>
							<s:if test="listHkSellSta!=null&&listHkSellSta.size()>0">
								<s:iterator value="listHkSellSta" id="sellSta"
									status="pageStatus">
									<tr>
										<td align="right">
											件号
										</td>
										<td colspan="">
											<s:property value="#sellSta.hkSellMarkId" />
											<input type="hidden"
												value="<s:property value="#sellSta.hkSellMarkId"/>"
												name="backMoney.taHkPartBackMoneys[${pageStatus.index}].hkmarkId">
										</td>
										<td colspan="2" align="center">
											订单号
											<s:property value="#sellSta.hkSellOrderId" />
											<input type="hidden"
												value="<s:property value="#sellSta.hkSellOrderId"/>"
												name="backMoney.taHkPartBackMoneys[${pageStatus.index}].hkSellOrderId">
										</td>
										<td align="right">
											金额
										</td>
										<td colspan="2">
											<input type="text" value="0"
												name="backMoney.taHkPartBackMoneys[${pageStatus.index}].hkbmMoney"
												id="partHkbmMoney${pageStatus.index}" onblur="sumHKMoney()">
											<input type="hidden"
												value="<s:property value="#sellSta.hkSellPrice"/>"
												name="backMoney.taHkPartBackMoneys[${pageStatus.index}].hkSellPrice">
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<tr>	
								<td align="right">
											回款依据
								</td>
								<td colspan="3">
									<input name="backMoney.hkbmResult" />
								</td>
								<td align="right">
									备注
								</td>
								<td colspan="3">
									<input name="backMoney.hkbmMore" />
								</td>
							</tr>
							<tr>
								<td align="right">
									上传附件
								</td>
								<td colspan="3">
									<input type="button" id="fileButton_1"
										onclick="uploadFile(this,1)" value="上传附件">

									<div id="fileDiv_1" style="display: none;">

									</div>
								</td>
								<td align="right">
									银行账户
								</td>
								<td colspan="5">
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="wgType" type="text" readonly="readonly"
													value="" style="width: 120px;"
													name="backMoney.bankAccount" />
													<input type="hidden" value="" id="subId" name="backMoney.subId"/>
													<input type="hidden" value="" id="fatherSubName" name="backMoney.fatherSubName"/>
												<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>(按住Ctrl建不松点击,可清空)
											</li>
										</ul>
									</div>
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 160px;"></ul>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
						</table>
					</form>
				</div>
				<br />
				<div id="othBack">
					<s:if test="%{null!=listbackMon}">
						<table class="table" style="width: 98%; margin: 5 px">
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<td>
									序号
								</td>
								<td>
									客户
								</td>
								<td>
									关联人
								</td>
								<td>
									回款金额
								</td>
								<td>
									回款依据
								</td>
								<td>
									负责人
								</td>
								<td>
									回款时间时间
								</td>
							</tr>
							<s:iterator value="listbackMon" status="hk" id="back">
								<s:if test="#hk.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#hk.index+1" />
								</td>
								<td>
									${taHk.hkClientComp}
									<td>
										<s:property value="hkbmClientUser" />
									</td>
									<td>
										<s:property value="hkbmMore" />
									</td>
									<td>
										<s:property value="hkInvoTaxMoney" />
									</td>
									<td>
										<s:property value="hkbmPerson" />
									</td>
									<td>
										<s:property value="hkbmDate" />
									</td>
									</tr>
							</s:iterator>
							<tr></tr>
						</table>
					</s:if>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

	</body>
	<script type="text/javascript">
var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='infooo"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("infooo" + obj).parentNode.removeChild(document
			.getElementById("infooo" + obj));
	//alert("test");
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}

function sumHKMoney() {
	var staSize = $("#staSize").val();
	var sum = 0;
	for ( var i = 0; i < staSize; i++) {
		mustBeNumber("partHkbmMoney" + i);
		sum = sum + ($("#partHkbmMoney" + i).val() - 0);
	}
	$("#taotalMoney").html(sum);
	$("#hkbmMoney").val(sum);

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
		url : 'huikuanAction!findBankSubByUsers.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					fatherName:$(this).attr('fatherName'),
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
if (!check) alert("只能选择最底层"); 
return check; 
} 

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	var id=0,fatherName='';
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
</script>

</html>
