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
		<style type="text/css">
.ztree li a {
	color: #fff;
}
</style>
	</head>
	<body>
	<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div align="center">
		</div>

		<div align="center">
			<h3>

				<br />
			</h3>
			<form action="ZhuserOfferAction_showYuancail.action"
				method="post" id="form">
				<input name="status" value="${status}" type="hidden">
				<table class="table" align="center">
					<tr>
						<td align="center" colspan="4">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title">
										物料类别:
										<input id="wgType" type="text" readonly="readonly" value=""
											style="width: 120px;" name="yuanclAndWaigj.wgType" />
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
					</tr>
					<tr>
						<td align="center">
							<input type="hidden" name="pageStatus"
								value="<s:property value='pageStatus'/>" />
							件号：
							<input type="text" name="yuanclAndWaigj.markId"
								value="<s:property value="yuanclAndWaigj.markId"/>" />
						</td>
						<td align="center">
							名称：
							<input type="text" name="yuanclAndWaigj.name"
								value="<s:property value="yuanclAndWaigj.name"/>" />
						</td>
						<td align="center">
							规格：
							<input type="text" name="yuanclAndWaigj.specification"
								value="<s:property value="yuanclAndWaigj.specification"/>" />
						</td>
						<td align="center">
							材质：
							<input type="text" name="yuanclAndWaigj.caizhi"
								value="<s:property value="yuanclAndWaigj.caizhi"/>" />
						</td>
					</tr>
					<tr>
						<td align="center">
							版本号：
							<input type="text" name="yuanclAndWaigj.banbenhao"
								value="<s:property value="yuanclAndWaigj.banbenhao"/>" />
						</td>
						<td align="center">
							状态：
							<select id="status" name="yuanclAndWaigj.pricestatus"
								style="width: 155px;">
								<option></option>
								<option value="新增">
									新增
								</option>
								<option value="打样中">
									打样中
								</option>
								<option value="同意">
									同意
								</option>
								<option value="有效">
									有效
								</option>
							</select>
						</td>
						<td align="center">
							图号：
							<input id="caizhi" type="text" name="yuanclAndWaigj.tuhao"
								value="<s:property value="yuanclAndWaigj.tuhao"/>" />
						</td>
						<td align="center">
							总成号：
							<input id="zcMarkId" type="text" name="yuanclAndWaigj.zcMarkId"
								value="<s:property value="yuanclAndWaigj.zcMarkId"/>" />
						</td>
					</tr>
					<tr>
						<td align="center">
							BOM单位：
							<select id="unit" name="yuanclAndWaigj.unit"
								style="width: 155px;">
								<option></option>
							</select>
						</td>
						<td align="center">
							仓库单位：
							<select id="ckUnit" name="yuanclAndWaigj.ckUnit"
								style="width: 155px;">
								<option></option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询(select)" />
							<s:if test="status=='newly'">
								<input type="button" value="导出(export)" onclick="clickWei();"
									style="width: 100px; height: 40px;" />
							</s:if>
						</td>
					</tr>
				</table>
			</form>
			<table class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
						序号
					</td>
					<td align="center">
						物料类别
					</td>
					<td align="center">
						件号
					</td>
					<td align="center">
						名称
					</td>
					<td align="center">
						规格
					</td>
					<td align="center">
						版本号
					</td>
					<td align="center">
						供料属性
					</td>
					<td align="center">
						单位
					</td>
					<td align="center">
						材质
					</td>
					<td align="center">
						图号
					</td>
					<td align="center">
						单张重量
					</td>
					<td align="center">
						仓库单位
					</td>
					<td align="center">
						总成号
					</td>
					<%--
					<td align="center">
						材料类型
					</td>
					--%>
					<td align="center">
						质检周期
					</td>
					<td align="center">
						可用状态
					</td>
					<td align="center">
						物料状态
					</td>
					<td align="center" colspan="2">
						操作
					</td>
				</tr>
				<s:iterator value="YuanclAndWaigjList" id="yuanclAndWaigjPage"
					status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pageStatus.index+1" />
					</td>
					<td>
						${yuanclAndWaigjPage.wgType}
					</td>
					<td align="left">
						${yuanclAndWaigjPage.markId}
					</td>
					<td align="left">
						${yuanclAndWaigjPage.name}
					</td>
					<td>
						${yuanclAndWaigjPage.specification}
					</td>
					<td>
						${yuanclAndWaigjPage.banbenhao}
					</td>
					<td>
						${yuanclAndWaigjPage.kgliao}
					</td>
					<td>
						${yuanclAndWaigjPage.unit}
					</td>
					<td>
						${yuanclAndWaigjPage.caizhi}
					</td>
					<td>
						${yuanclAndWaigjPage.tuhao}
					</td>
					<td>
						${yuanclAndWaigjPage.bili}
					</td>
					<td>
						${yuanclAndWaigjPage.ckUnit}
					</td>
					<td>
						${yuanclAndWaigjPage.zcMarkId}
					</td>
					<td>
						${yuanclAndWaigjPage.round}
					</td>
					<td>
						<s:if test="#yuanclAndWaigjPage.status=='禁用'">禁用</s:if>
						<s:elseif test="#yuanclAndWaigjPage.status=='已确认'">已确认</s:elseif>
						<s:else>使用</s:else>
					</td>
					<td>
						${yuanclAndWaigjPage.pricestatus}
					</td>
					<td colspan="2">
					<s:if test="#yuanclAndWaigjPage.pricestatus=='新增'">
					未发起报价
					</s:if>
					<s:else>
						<input type="button" value="所有报价" style="width: 75px; height: 30px;"
							onclick="findAllZhOffer(${yuanclAndWaigjPage.id})" />
					</s:else>
					<a onclick="downloadFile(${yuanclAndWaigjPage.id},'${cpage}')">(下载图纸)</a>
					</td>
				</s:iterator>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="19" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
					</s:if>
					<s:else>
						<td colspan="19" align="center" style="color: red">
							${errorMessage}
							</td>
					</s:else>
					
				</tr>

				<s:if test="successMessage!=null">
					<tr>
						<td colspan="18" align="center" style="color: red">
							${successMessage}

						</td>
					</tr>
				</s:if>
			</table>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

$(function() {
	getUnit("ckUnit")
	getUnit("unit")
	getCaizhi("caizhi");
})
function baojia(id) {
	window.location.href = "yuanclAndWaigjAction_findZhUserByType.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}";
}
function downloadFile(id, cpage) {
	window.location.href = "ZhuserOfferAction_getwgOrderTz1.action?yuanclAndWaigj.id="
			+ id + "&cpage=" + cpage;
}
function findAllZhOffer(id) {
	window.location.href = "yuanclAndWaigjAction_findAllZhOffer.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}";
}
function showProcessForSb(id) {
	$("#showProcess").attr("src", "yuanclAndWaigjAction_findOne.action?yuanclAndWaigj.id=" + id+ "&cpage=${cpage}");
	chageDiv('block');

}

function clickWei() {
	document.getElementById('form').action = "yuanclAndWaigjAction_explorDaiLuru.action";
	document.getElementById('form').submit();
	document.getElementById('form').action = "yuanclAndWaigjAction_showWuXiaoList.action";
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
</script>
	</body>
</html>

