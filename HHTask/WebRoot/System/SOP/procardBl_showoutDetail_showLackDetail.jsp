<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</style>
	</head>
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
							<span id="title">您正在进行不合格品退货操作</span>
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
		<div align="center">
			<h2>
				${goods.goodsMarkId}&nbsp&nbsp&nbsp&nbsp  缺料
				<br/>
				 外购件库、待检库明细
			</h2>
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th align="center" style="width: 57px;">
						库别
					</th>
					<th align="center">
						仓区
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
						件号
					</th>
					<th align="center">
						内部单号
					</th>
					<th align="center">
						锁定单号
					</th>
					<th align="center">
						批次
					</th>
					<th align="center">
						版本
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center">
						物料类别
					</th>
					<th align="center">
						品名
					</th>
					<th align="center">
						规格
					</th>
					<th align="center">
						数量
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						转换数量
					</th>
					<th align="center">
						转换单位
					</th>
					<th align="center">
						客户
					</th>
					<th align="center">
						供应商
					</th>
					<th align="center">
						入库类型
					</th>
					<th align="center">
						入库日期
					</th>
					<th align="center">
						状态
					</th>
					<th align="center">
						属性
					</th>
<%--					<th align="center">--%>
<%--						封存审批--%>
<%--					</th>--%>
<%--					<th align="center">--%>
<%--						操作--%>
<%--					</th>--%>
<%--					<th align="center">--%>
<%--						在库不良检验申请--%>
<%--					</th>--%>
				</tr>
				<s:if test="{list.size()>0}">
					<s:iterator value="list" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#see.index+1" />
						</td>
						<td align="left" style="color: gray;">
							${gs.goodsClass}
						</td>
						<td align="left" style="color: gray;">
							${gs.goodHouseName}
						</td>
						<td align="left" style="color: gray;">
							${gs.goodsPosition}
						</td>
						<td align="left">
							${gs.goodsMarkId}
							<s:if test="#gs.processNo!=null">(<font color="red">${gs.processNo}</font> )</s:if>
							<s:if test='#gs.ywmarkId!=null'>
								(<font color="green">${gs.ywmarkId}</font> )
							</s:if>
						</td>
						<td align="left">
							${gs.neiorderId}
						</td>
						<td align="left">
							${gs.suodingdanhao}
						</td>
						<td align="right">
							${gs.goodsLotId}
						</td>
						<td>
							${gs.banBenNumber}
						</td>
						<td align="left">
							<s:if test="#gs.kgliao=='TK'">
										自购(TK)
										</s:if>
							<s:elseif test="#gs.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
							<s:elseif test="#gs.kgliao=='CS'">
										客供(CS)
										</s:elseif>
							<s:elseif test="#gs.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
						</td>
						<td align="left">
							${gs.wgType}
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs.goodsFullName}</font>
									<ul class="qs_ul">
										<li>
											${gs.goodsFullName}
										</li>
							</ul>
						</td>
						<td align="left" style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs.goodsFormat}</font>
									<ul class="qs_ul">
										<li>
											${gs.goodsFormat}
										</li>
							</ul>
						</td>
						<td align="right" >
							<fmt:formatNumber value="${gs.goodsCurQuantity}" pattern="#.0000"/>
						</td>
						<td>
							${gs.goodsUnit}
						</td>
						<td align="right">
							${gs.goodsZhishu}
						</td>
						<td>
							${gs.goodsStoreZHUnit}
						</td>
						<td>
							${gs.goodsCustomer}
						</td>
						<td>
							${gs.goodsArtsCard}
						</td>
						<td>
							${gs.goodsStyle}
						</td>
						<td>
							${gs.goodsChangeTime}
						</td>
						<td>
							<s:if test="#gs.fcStatus=='封存'">
								<font color="red">封存</font>
							</s:if>
							<s:else>可用</s:else>
						</td>
						<td>
							${gs.inputSource}
						</td>
<%--						<td>--%>
<%--							<s:if test="#gs.fcApplyStatus!=null&&true==#gs.bedit">--%>
<%--							${gs.fcApplyStatus}--%>
<%--						   </s:if>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:if test="pagestatus == 'bhg'">--%>
<%--								<a href="javascript:;" onclick="tanchu('${gs.goodsId}')">不合格品确认</a>--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<s:if test="true==#gs.bout">--%>
<%--									<a href="goodsAction!getOneGoods.action?id=${goodsId}&tag=out">出库</a>--%>
<%--								</s:if>--%>
<%--								<s:if test="true==#gs.bedit">--%>
<%--									<a--%>
<%--										href="goodsAction!getOneGoods.action?id=${goodsId}&tag=update">修改</a>--%>
<%--									<s:if test="#gs.goodsClass!='成品库'">--%>
<%--										<s:if test="#gs.fcStatus=='封存'">--%>
<%--											<s:if test="#gs.fcApplyStatus!='审批中'">--%>
<%--												<a--%>
<%--													href="javaScript:;if(window.confirm('您将申请解封此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">解封</a>--%>
<%--											</s:if>--%>
<%--										</s:if>--%>
<%--										<s:else>--%>
<%--											<s:if test="#gs.fcApplyStatus!='审批中'">--%>
<%--												<a--%>
<%--													href="javaScript:;if(window.confirm('您将申请封存此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">封存</a>--%>
<%--											</s:if>--%>
<%--										</s:else>--%>
<%--									</s:if>--%>
<%--									--%>
<%--									--%>
<%--									<s:if test="#gs.fcApplyStatus!=null&&#gs.fcApplyStatus!=''">--%>
<%--										<a href="CircuitRunAction_findAduitPage.action?id=${gs.epId}">审批动态</a>--%>
<%--									</s:if>--%>
<%--								</s:if>--%>
<%----%>
<%--							</s:else>--%>
<%--							<s:if test='#gs.goodsClass == "外购件库"&&true==#gs.bedit' >--%>
<%--								<s:if test='#gs.islock == "NO" || #gs.islock == null'>--%>
<%--									<a href="goodsAction!sqsuocang.action?id=${gs.goodsId}" onclick="return confirm('是否要申请锁仓?')">锁仓</a>--%>
<%--								</s:if>--%>
<%--								<s:elseif test='#gs.islock == "YES"'>--%>
<%--									<a href="goodsAction!sqsqjiesuo.action?id=${gs.goodsId}" onclick="return confirm('是否要申请解锁?')">解锁</a>--%>
<%--								</s:elseif>--%>
<%--							</s:if>--%>
<%--							--%>
<%--							--%>
<%--			成品库--》外购件（调仓）记录表			--%>
<%--							<s:if test='#gs.goodsClass== "成品库"&&true==#gs.bedit '>--%>
<%--								<a href="goodsAction!cpChangeWG.action?id=${gs.goodsId}"  onclick="return confirm('是否要调仓到外购件库')">调仓</a>--%>
<%--							</s:if>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:if test='#gs.goodsClass == "外购件库"&&true==#gs.bedit'>--%>
<%--								<a href="javaScript:;"--%>
<%--									onclick="sqbljy(${goodsId},'${gs.goodsMarkId}','${gs.goodsLotId}')">在库不良检验申请</a>--%>
<%--							</s:if>--%>
<%--						</td>--%>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="13" align="right">

						</th>
						<th>
							<fmt:formatNumber value="${sumcount}" pattern="#,#00.0#"></fmt:formatNumber>
						</th>
						<th colspan="12"></th>
					</tr>
					<tr>
						<td colspan="26" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="24" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
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
<%--function exportExcel(objForm) {--%>
<%--	objForm.action = "goodsAction!exportEXCEL.action?tag=goodsDetail";--%>
<%--	objForm.submit();--%>
<%--	objForm.action = "goodsAction!findGoods.action";--%>
<%--	window.confirm(arg0)--%>
<%--}--%>
<%----%>
<%--var mfzTree;--%>
<%--var addzTree;--%>
<%--var delzTree;--%>
<%--var updatezTree;--%>
<%----%>
<%--var id;--%>
<%--var pId;--%>
<%--var name;--%>
<%--var setting = {--%>
<%--	view : {--%>
<%--		dblClickExpand : false--%>
<%--	},--%>
<%--	data : {--%>
<%--		simpleData : {--%>
<%--			enable : true--%>
<%--		}--%>
<%--	},--%>
<%--	callback : {--%>
<%--		beforeClick : beforeClick,--%>
<%--		onClick : onClick--%>
<%--	}--%>
<%--};--%>
<%--//读取树形数据--%>
<%--$(document).ready(function() {--%>
<%--	parent.mfzTree();--%>
<%----%>
<%--});--%>
<%--var zNodes = [];--%>
<%--parent.mfzTree = function() {--%>
<%--	$.ajax( {--%>
<%--		url : 'CategoryAction_findcyListByrootId.action',--%>
<%--		type : 'post',--%>
<%--		data : {--%>
<%--			status : '物料类别'--%>
<%--		},--%>
<%--		dataType : 'json',--%>
<%--		cache : true,--%>
<%--		success : function(doc) {--%>
<%--			$(doc).each(function() {--%>
<%--				zNodes.push( {--%>
<%--					id : $(this).attr('id'),--%>
<%--					pId : $(this).attr('fatherId'),--%>
<%--					name : $(this).attr('name'),--%>
<%--					target : "main",--%>
<%--					click : false--%>
<%--				});--%>
<%----%>
<%--			});--%>
<%--			$.fn.zTree.init($("#treeDemo"), setting, zNodes);--%>
<%--			var zTree = $.fn.zTree.getZTreeObj("treeDemo");--%>
<%--			zTree.expandAll(true);--%>
<%--		},--%>
<%--		error : function() {--%>
<%--			alert("服务器异常!");--%>
<%--		}--%>
<%--	});--%>
<%--};--%>
<%--function beforeClick(treeId, treeNode) {--%>
<%--	var check = (treeNode && !treeNode.isParent);--%>
<%----%>
<%--	return true;--%>
<%--}--%>
<%----%>
<%--function onClick(e, treeId, treeNode) {--%>
<%--	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree--%>
<%--			.getSelectedNodes(), v = "";--%>
<%--	nodes.sort(function compare(a, b) {--%>
<%--		return a.id - b.id;--%>
<%--	});--%>
<%--	for ( var i = 0, l = nodes.length; i < l; i++) {--%>
<%--		v = nodes[i].name;--%>
<%--	}--%>
<%--	//if (v.length > 0 ) v = v.substring(0, v.length-1); --%>
<%--	cityObj = $("#wgType").val(v);--%>
<%----%>
<%--}--%>
<%----%>
<%--function showMenu() {--%>
<%--	var cityObj = $("#wgType");--%>
<%--	var cityOffset = $("#wgType").offset();--%>
<%--	$("#menuContent").css( {--%>
<%--		left : cityOffset.left + "px",--%>
<%--		top : cityOffset.top + cityObj.outerHeight() + "px"--%>
<%--	}).slideDown("fast");--%>
<%----%>
<%--	$("body").bind("mousedown", onBodyDown);--%>
<%--}--%>
<%--function hideMenu() {--%>
<%--	$("#menuContent").fadeOut("fast");--%>
<%--	$("body").unbind("mousedown", onBodyDown);--%>
<%--}--%>
<%--function onBodyDown(event) {--%>
<%--	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(--%>
<%--			event.target).parents("#menuContent").length > 0)) {--%>
<%--		hideMenu();--%>
<%--	}--%>
<%--}--%>
<%--function tanchu(num) {--%>
<%--	document.getElementById("xiugaiIframe").src = "goodsAction!getOneGoods.action?id="--%>
<%--			+ num + "&pagestatus=bhgth"--%>
<%--	chageDiv('block');--%>
<%--}--%>
<%--function showAll(objForm) {--%>
<%--	objForm.action = "goodsAction!findGoods.action?pagestatus=all";--%>
<%--	objForm.submit();--%>
<%--	objForm.action = "goodsAction!findGoods.action";--%>
<%--}--%>
<%--function sqbljy(id, markId, lotId) {--%>
<%--	if (confirm('你确定要申请检验该库存?此操作会将件号:' + markId + '批次:' + lotId--%>
<%--			+ '，下所有库存暂时转移到不合格品库。')) {--%>
<%--		window.location.href = "goodsAction!bljysq.action?id=" + id;--%>
<%--	}--%>
<%--}--%>
<%----%>
<%--function changvalue(obj){--%>
<%--	if(obj!=null && obj.value != ""){--%>
<%--		$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "WarehouseAreaAction_findwaListByNO.action",--%>
<%--		data : {--%>
<%--			wareHouseName:obj.value--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		success : function(data) {--%>
<%--			if (data != null) {--%>
<%--				$("#goodHouseName").empty();--%>
<%--				$(data).each(function(){--%>
<%--						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');--%>
<%--					});--%>
<%--			var duoxuanselect = $(".duoxuaselect_div");--%>
<%--			if (duoxuanselect[0] != null) {--%>
<%--					$(duoxuanselect[0]).remove();--%>
<%--			}--%>
<%--				duoxuaSelect("goodHouseName");--%>
<%--			}--%>
<%--		}--%>
<%--	});--%>
<%--	}--%>
<%--}--%>
</script>
	</body>
</html>
