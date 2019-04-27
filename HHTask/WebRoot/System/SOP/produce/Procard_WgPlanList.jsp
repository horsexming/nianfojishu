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
	<style type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: 120px;
	background: #CCC;
	color: green;
}

.ztree li a {
	color: #fff;
}
</style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看明细详情</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							订单编号
							<br />
							(内部)
						</th>
						<th align="center">
							件号
							<br />
							Part No.
						</th>
						<th align="center">
							业务件号
							<br />
							Part No.
						</th>
						<th align="center">
							名称
							<br />
							Name
						</th>
						<th align="center">
							卡片类型
							<br />
							Card Type
						</th>
						<th align="center">
							产品类型
							<br />
							Product Type
						</th>
						<th align="center">
							批次
							<br />
							Batch
						</th>
						<th align="center">
							数量
							<br />
							Quantity
						</th>
						<th align="center">
							交付日期
							<br />
							Card time
						</th>
						<th align="center">
							状态
							<br />
							State
						</th>
						<th align="center">
							批次进度
							<br />
							State
						</th>
					</tr>
					<tr>
						<td align="left">
							${list[2].orderNumber}
						</td>
						<td align="left">
							${list[2].markId}
						</td>
						<td align="left">
							${list[2].ywMarkId}
						</td>
						<td align="left">
							${list[2].proName}
						</td>
						<td>
							${list[2].procardStyle}
						</td>
						<td>
							${list[2].productStyle}
						</td>
						<td>
							${list[2].selfCard}
						</td>
						<td>
							${list[2].filnalCount}
						</td>
						<td>
							${list[2].jioafuDate}
						</td>
						<td>
							${list[2].wlstatus}
						</td>
						<td>
							<a target="_showprocard"
								href="ProcardAction!findProcardView.action?id=${list[2].id}&pageStatus=history&viewStatus=">生产进度</a>
						</td>
					</tr>
				</table>

				<form action="WaigouwaiweiPlanAction!findDqrWlDetailList.action"
					method="post" id="form">
					<input type="hidden" name="id" value="${id}" />
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="6">
								采购计划管理
							</th>
						</tr>
						<tr>
							<th align="right">
								件号:
							</th>
							<td>
								<input name="procard.markId" value="${procard.markId}" />
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input name="procard.proName" value="${procard.proName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								类别:
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list" id="qsw_1" style="">
										<li class="title">
											<input id="wgType" type="text" name="procard.wgType" value=""
												style="width: 120px;" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
										</li>
									</ul>
								</div>
								<div id="menuContent" class="menuContent"
									style="display: none; position: absolute;">
									<ul id="treeDemo" class="ztree"
										style="margin-top: 0; width: 160px;"></ul>
								</div>
							</td>
							<th align="right">
								供料属性:
							</th>
							<td>
								<select name="procard.kgliao" style="width: 155px;" id="kgliao">
									<option>
									</option>
									<option>
										${procard.kgliao}
									</option>
									<option value="TK">
										自购(TK)
									</option>
									<option value="TK AVL">
										指定供应商(TK AVL)
									</option>
									<option value="CS">
										客供(CS)
									</option>
									<option value="TK Price">
										完全指定(TK Price)
									</option>
								</select>
							</td>
						</tr>

						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="button" value="导出物料清单" class="input"
									onclick="clicks();todisabledone(this)" data="downData" />
								<%--<input type="button" value="新增" class="input">
							--%>
							</th>
						</tr>
					</table>
				</form>
				<div align="right">
				</div>
				<form action="WaigouwaiweiPlanAction!updateForWgPlan.action"
					method="post"
					onsubmit="return confirm('确定提交${list[2].markId}(${list[2].ywMarkId})的物料需求清单吗?');">
					<input type="hidden" name="id" value="${id}">
					<table class="table sticky-enabled">
						<thead>
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center" class="lie1" ondblclick="hidelie(2)">
									序号
								</th>
								<th align="center" class="lie2" ondblclick="hidelie(22)">
									备注
								</th>
								<th align="center" class="lie2" ondblclick="hidelie(2)">
									物料类别
								</th>
								<th align="center" class="lie7" ondblclick="hidelie(7)">
									件号
								</th>
								<th align="center" class="lie9" ondblclick="hidelie(9)">
									版本
								</th>
								<th align="center" class="lie12" ondblclick="hidelie(12)">
									供料属性
								</th>
								<th align="center" style="max-width: 40px;" class="lie11"
									ondblclick="hidelie(11)">
									零件名称
								</th>
								<th align="center" style="max-width: 100px;" class="lie8"
									ondblclick="hidelie(8)">
									规格
								</th>
								<th align="center" class="lie21" ondblclick="hidelie(21)">
									单位
								</th>
								<th align="right" class="lie19" ondblclick="hidelie(19)">
									需求数量
									<br />
									合计:${list[0]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									采购数量
									<br />
									合计:${list[1]}
								</th>
								<th align="center" class="lie22" ondblclick="hidelie(22)">
									到货日期
								</th>
								<th align="center" class="lie24" ondblclick="hidelie(24)">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="procardList" id="pageWgww" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<th class="lie1">
									<s:property value="#pageStatus.index+1" />
								</th>
								<td class="lie2" align="right">
									<b><span style="color: red">${pageWgww.remark}</span>
									</b>
								</td>
								<th class="lie6" align="left">
									${pageWgww.wgType}
								</th>
								<th class="lie7" align="left" style="color: red">
									${pageWgww.markId}
								</th>
								<th class="lie9" style="color: blue">
									${pageWgww.banBenNumber}
								</th>
								<th class="lie9" style="color: blue">
									${pageWgww.kgliao}
								</th>
								<td align="left" class="lie11"
									style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageWgww.proName}</font>
									<ul class="qs_ul">
										<li>
											${pageWgww.proName}
										</li>
									</ul>
								</td>
								<td class="lie8" align="left"
									style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageWgww.specification}</font>
									<ul class="qs_ul">
										<li>
											${pageWgww.specification}
										</li>
									</ul>
								</td>
								<td class="lie21" align="right">
									${pageWgww.unit}
								</td>
								<td class="lie19" align="right">
									${pageWgww.filnalCount}
								</td>
								<td class="lie20" align="right"
									id="cgnTd<s:property value="#pageStatus.index" />">
									<input style="width: 80px;"
										id="cgNumber<s:property value="#pageStatus.index" />"
										value="${pageWgww.cgNumber}"
										onkeyup="mustBeNumber('cgNumber<s:property value="#pageStatus.index" />')"
										onblur="update('${pageWgww.markId}','${pageWgww.kgliao}','${pageWgww.banBenNumber}',<s:property value="#pageStatus.index" />,'${list[2].productStyle}','${pageWgww.cgNumber}')">
									<input type="hidden"
										id="hcgNumber<s:property value="#pageStatus.index" />"
										value="${pageWgww.cgNumber}">
								</td>
								<td class="lie22" align="right">
									<s:if
										test="#pageWgww.needFinalDate==null||#pageWgww.needFinalDate==''">
										空
									</s:if>
									<s:else>
										${pageWgww.needFinalDate}
									</s:else>
								</td>
								<td class="lie24" style="width: 90px;" align="left">
									<input type="button" value="调整"
										onclick="update('${pageWgww.markId}','${pageWgww.kgliao}','${pageWgww.banBenNumber}',<s:property value="#pageStatus.index" />,'${list[2].productStyle}','${pageWgww.cgNumber}')">
									<input type="button" value="替换"
										onclick="changeWgj('${pageWgww.markId}','${pageWgww.kgliao}')" />
									<br />
									<input type="button"
										onclick="showmingxi('${pageWgww.markId}','${pageWgww.kgliao}','${pageWgww.banBenNumber}','${pageWgww.specification}')"
										value="明细" />
									<s:if
										test='pageWgww.isuse== "待"&&(#pageWgww.gyscodeAndNum!=null && #pageWgww.gyscodeAndNum!="")'>
										<input type="button" value="起订量确认"
											onclick="showmoqkz('${id}','${pageWgww.markId}','${pageWgww.kgliao}','${pageWgww.banBenNumber}','${pageWgww.specification}','${pagemop.gyscodeAndNum}')"
											style="width: 80px" />
									</s:if>
								</td>
							</s:iterator>
							<s:if test="procardList.size()>0">
								<tr>
									<td colspan="25" align="center">
										到货日期：
										<input class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d'})"/>
									</td>
								</tr>
							</s:if>
							<tr>
								<td colspan="25" align="center">
									<s:if test='list[2].wlstatus=="待确认"'>
										<input type="submit" style="height: 50px;" value="物料需求清单确认"
											onclick="todisabled(this)" />
									</s:if>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(document).ready(function() {
	getCaizhi("wgType");
})
function selectWgType() {
	$("#xftitle").html("选择类别!");
	$("#showIframe")
			.attr("src", "WaigouwaiweiPlanAction!toselectWgType.action");
	chageDiv('block')
}
function update(markId, kgliao, banBenNumber, index, productStyle, inputVal) {
	if (parseFloat(inputVal) != parseFloat($("#cgNumber" + index).val())) {
		if (confirm("确定要将" + markId + "的采购数量由" + parseFloat(inputVal) + "调整到"
				+ parseFloat($("#cgNumber" + index).val()) + "吗?")) {
			$.ajax( {
				type : "post",
				url : "WaigouwaiweiPlanAction!adjust.action",
				dataType : "json",
				data : {
					'procard.rootId' : "${id}",
					'procard.markId' : markId,
					'procard.kgliao' : kgliao,
					'procard.banBenNumber' : banBenNumber,
					'procard.cgNumber' : $("#cgNumber" + index).val(),
					'procard.productStyle' : productStyle
				},
				success : function(data) {
					if (data == "true") {
						alert("调整成功!");
						$("#cgnTd" + index).attr("bgColor", "green");
					} else {
						alert("调整失败!\n" + data);
						$("#cgNumber" + index).val(
								$("#hcgNumber" + index).val());
					}
				}
			});
		}
	}
}
function clicks() {
	var id = '${id}';
	document.getElementById('form').action = "WaigouwaiweiPlanAction!export.action";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findDqrWlDetailList.action";
}

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
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : '物料类别'
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
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

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);

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
function changeWgj(markId, kgliao, count, unit) {
	window.location.href = "WaigouwaiweiPlanAction!toChangeWgj.action?id=${id}&markId="
			+ markId + "&kgliao=" + kgliao;
}

function showmingxi(markId, kgliao, banBenNumber, specification) {

	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!showWgProcardList.action?id=${id}&markId="
			+ markId + "&kgliao=" + kgliao + "&banBenNumber=" + banBenNumber;
	chageDiv('block')
}

function showmoqkz(rootId, markId, kgliao, banBenNumber, specification,
		gyscodeAndNum) {
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!toisuseMQQ0.action?id=${id}&markId="
			+ markId
			+ "&kgliao="
			+ kgliao
			+ "&banBenNumber="
			+ banBenNumber
			+ "&mxId=" + mxId;
	chageDiv('block')
}
</script>
		<%--		<script src="../javascript/lockTr/jquery.min(1).js">--%>
		<%--</script>--%>
		<%--		<script src="../javascript/lockTr/jquery.ba-throttle-debounce.min.js">--%>
		<%--</script>--%>
		<%--		<script src="../javascript/lockTr/jquery.stickyheader.js">--%>
		<%--</script>--%>
	</body>
</html>
