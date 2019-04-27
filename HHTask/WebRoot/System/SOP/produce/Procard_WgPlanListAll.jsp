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
	width: auto;
	background: #CCC;
	color: green;
}

.Freezing {
	z-index: 20;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
	background: #fff;
}
.ztree li a {
	color: #fff;
}
/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
/********带复选框的下拉框*****************/

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
								<span id="title">物料追踪明细页面</span>
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
							操作
							<br />
							Operation
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
							<a
								href="ProcardAction!findProcardView.action?id=${list[2].id}&pageStatus=history&viewStatus="
								target="showJindu">生产进度</a>
						</td>
					</tr>
				</table>
				<form action="WaigouwaiweiPlanAction!findAllWlDetailList.action"
					method="post" id="form">
					<input type="hidden" name="id" value="${id}" />
					<input type="hidden" name="tag" value="${tag}" />
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
							<th align="right">
								状态:
							</th>
							<td>
								<SELECT name="procard.wlstatus" id="status">
									<option value="待确认">
										待确认
									</option>
									<option value="待打印">
										待打印
									</option>
									<option value="待通知">
										待通知
									</option>
									<option value="生产中">
										生产中
									</option>
									<option value="送货中">
										送货中
									</option>
									<option value="待存柜">
										待存柜
									</option>
									<option value="待检验">
										待检验
									</option>
									<option value="检验中">
										检验中
									</option>
									<option value="待入库">
										待入库
									</option>
									<option value="入库">
										入库
									</option>
									<option value="退货">
										退货
									</option>
								</SELECT>
							</td>
							<th align="right">
								是否下单:
							</th>
							<td>
								<SELECT name="procard.jihuoStatua" >
									<option value="${procard.jihuoStatua}">${procard.jihuoStatua}</option>
									<option></option>
									<option value="未下单">未下单</option>
									<option value="已下单">已下单</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="button" value="导出物料清单" class="input"
									onclick="clicks();todisabledone(this);todisabledone(this)" data="downData" />
								<input type="button" value="导出待采购计划" class="input"
									onclick="clicks1();todisabledone(this);todisabledone(this)" data="downData" />
								<%--<input type="button" value="新增" class="input">
							--%>
							</th>
						</tr>
					</table>
				</form>
				<div align="right">
				</div>
				<form action="WaigouwaiweiPlanAction!updateForWgPlan.action"
					method="post">
					<input type="hidden" name="id" value="${id}">
					<table class="table" id="tabletest">
						<thead class="Freezing">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center" class="lie1" ondblclick="hidelie(2)">
									序号
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
									合计:
									<br />
									${list[0]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									采购数量
									<br />
									合计:
									<br />
									${list[1]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									下单数量
									<br />
									合计:
									<br />
									${list[3]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									入库数量
									<br />
									合计:
									<br />
									${list[4]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									激活数量
									<br />
									合计:
									<br />
									${list[5]}
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									领料数量
									<br />
									合计:
									<br />
									<fmt:formatNumber type="number" value="${list[6]}"
										pattern="0.00" maxFractionDigits="2" />
								</th>
								<th align="right" class="lie20" ondblclick="hidelie(20)">
									欠料数量
									<br />
									合计:
									<br />
									<fmt:formatNumber type="number" value="${list[0]-list[5]}"
										pattern="0.00" maxFractionDigits="2" />
								</th>
								<th align="right" class="lie20" >
									实时库存
								</th>
								<th align="right" class="lie20" >
									申购日期
								</th>
								<th align="" class="lie20" >
									工序信息
								</th>
								<th align="" class="lie20" >
									备注
								</th>
								<th align="center" class="lie24" ondblclick="hidelie(24)">
									<table class="table">
										<tr bgcolor="#c0dcf2" height="50px">
											<th align="center">
												序号
											</th>
											<th align="center">
												数量
											</th>
<%--											<th align="center">--%>
<%--												到货数量--%>
<%--											</th>--%>
											<th align="center">
												入库数量
											</th>
											<th align="center">
												产品状态
											</th>
											<th align="center">
												供应商
											</th>
										</tr>
									</table>
								</th>
								<th>
									物料追踪
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
								<td align="left" class="lie11" style="max-width: 80px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<font size="1">${pageWgww.proName}</font>
									<ul class="qs_ul">
										<li>
											${pageWgww.proName}
										</li>
									</ul>
								</td>
								<td class="lie8" align="left"
									style="max-width: 80px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
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
								<td class="lie20" align="right">
									<fmt:formatNumber type="number" value="${pageWgww.cgNumber}"
										pattern="0.00" maxFractionDigits="3" />
								</td>
								<td class="lie20" align="right">
									<fmt:formatNumber type="number" value="${pageWgww.outcgNumber}"
										pattern="0.000" maxFractionDigits="3" />
								</td>
								<td class="lie20" align="right">
									<fmt:formatNumber type="number" value="${pageWgww.dhNumber}"
										pattern="0.000" maxFractionDigits="3" />
								</td>
								<td class="lie20" align="right">
									<fmt:formatNumber type="number" value="${pageWgww.tjNumber}"
										pattern="0.00" maxFractionDigits="2" />
								</td>
								<td class="lie20" align="right">
									<fmt:formatNumber type="number" value="${pageWgww.hascount}"
										pattern="0.000" maxFractionDigits="3" />
								</td>
								<td class="lie20" align="right">
									<fmt:formatNumber type="number"
										value="${pageWgww.filnalCount-pageWgww.tjNumber}" pattern="0.00"
										maxFractionDigits="2" />
								</td>
								<td>
									<a href="javascript:;"
									 onclick="window.open('goodsAction!findGoods.action?goods.goodsMarkId=${pageWgww.markId}&pagestatus=wg')">
									<fmt:formatNumber type="number"
										value="${pageWgww.nowgoodsCount}" pattern="0.00"
										maxFractionDigits="2" /></a>
								</td>
								<td>
									${pageWgww.wlqrtime}
								</td>
								<td>
									${pageWgww.processNames}
								</td>
<%--								'${pageWgww.markId}','${pageWgww.kgliao}' --%>
<%--									 '${pageWgww.banBenNumber}','${pageWgww.specification}',this--%>
								<td  ondblclick="Changewrite(this)" >
									<input type="text" 
										disabled="disabled"  readonly="readonly" value="${pageWgww.remark}" 
									 onchange="addremark('${pageWgww.markId}','${pageWgww.kgliao}','${pageWgww.banBenNumber}','${pageWgww.specification}',this)"   />
									
								</td>
								<td class="lie24" style="font-size: 1px;" width="100px;">
									<%--<input type="button" value="调整"
										onclick="update(${pageWgww.id},<s:property value="#pageStatus.index" />)">
									--%>
									<table class="table" style="font-size: 8px;">
										<s:iterator value="#pageWgww.waigouPlanList" id="pageWgww2"
											status="pageStatus2">
											<tr>
												<td>
													<s:property value="#pageStatus2.index+1" />
												</td>
												<td style="width: 40px;" align="right">
													<fmt:formatNumber value="${pageWgww2.number}" pattern="#.###"></fmt:formatNumber>
												</td>
<%--												<td style="width: 40px;" align="right">--%>
<%--												<fmt:formatNumber value="${pageWgww2.number-pageWgww2.syNumber}" pattern="#.###"></fmt:formatNumber>--%>
<%--												</td>--%>
												<td style="width: 40px;" align="right">
												<fmt:formatNumber value="${pageWgww2.hasruku}" pattern="#.###"></fmt:formatNumber>
												</td>
												<td>
													${pageWgww2.status}
												</td>
												<td style="width: 60px;">
													<a
														href="WaigouwaiweiPlanAction!findWaigouPlanDNDetail.action?id=${pageWgww2.id}">${pageWgww2.gysjc}</a>
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
								<td>
									<a href="javascript:;" onclick="tanchu('${pageWgww.modId}')">物料追踪</a>
									<s:if test="tag=='tihuan'">//
									<a href="javascript:;" onclick="changeWgj('${pageWgww.markId}','${pageWgww.kgliao}')">物料替换</a>
									</s:if>
								</td>
							</s:iterator>
							<tr>
								<td colspan="23" align="center">
									<s:if test='list[2].wlstatus=="待确认"'>
										<input type="submit" style="height: 50px;" value="物料需求清单确认" />
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
	duoxuaSelect("status");//下拉多选框
})
function selectWgType() {
	$("#xftitle").html("选择类别!");
	$("#showIframe")
			.attr("src", "WaigouwaiweiPlanAction!toselectWgType.action");
	chageDiv('block')
}
function update(id, index) {
	//window.location.href = "WaigouwaiweiPlanAction!toAdjust.action?procard.id="
	//		+ id + "&cpage=" + cpage;
	$.ajax( {
		type : "post",
		url : "WaigouwaiweiPlanAction!adjust.action",
		dataType : "json",
		data : {
			'procard.id' : id,
			'procard.cgNumber' : $("#cgNumber" + index).val()
		},
		success : function(data) {
			//填充部门信息
		if (data || data == "true") {
			alert("调整成功!");
			$("#cgnTd" + index).attr("bgColor", "green");
		} else {
			alert("调整失败!");
			$("#cgNumber" + index).val($("#hcgNumber" + index).val());
		}
	}
	});
}
function clicks() {
	var id = '${id}';
	document.getElementById('form').action = "WaigouwaiweiPlanAction!export.action";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findAllWlDetailList.action";
}
function clicks1() {
	var id = '${id}';
	document.getElementById('form').action = "WaigouwaiweiPlanAction!export.action?exportTag=exportTag";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findAllWlDetailList.action";
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

function tanchu(num) {
	if(num!=''){
		document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!findAllCgxinxi.action?id="
				+ num;
		chageDiv('block')
	}else{
		alert('该零件未有相关物料需求信息');
	}
		
}

function changeWgj(markId, kgliao, count, unit) {
	window.location.href = "WaigouwaiweiPlanAction!toChangeWgj2.action?id=${id}&markId="
			+ markId + "&kgliao=" + kgliao;
}
function Changewrite(obj){
	$(obj).find("input").removeAttr("readonly");
	$(obj).find("input").removeAttr("disabled");
	
}


function addremark(markId,kgliao,banben,specification,obj){
		$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!ajax_addremark.action",
		data : {
				id:'${id}',
				markId:markId,
				kgliao:kgliao,
				banbenNumber:banben,
				specification:specification,
				noPrice:obj.value
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				if(data=="true"){
					alert('添加备注成功!');
					$(obj).attr("readonly","readonly");
					$(obj).attr("disabled","disabled");
				}else{
					alert("添加备注失败!")
				}
			}
		}
	})
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
