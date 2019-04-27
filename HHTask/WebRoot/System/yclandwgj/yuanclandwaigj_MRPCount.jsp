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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<style type="text/css">
			.ztree li a {
				color: #fff;
			}
		</style>
	</head>
	<body>
		<div align="center">
			<br />
			<form action="yuanclAndWaigjAction_findMarkIdByMRPCount.action" method="post">
				<table class="table" align="center">
					<tr>
						<td align="center">
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
						</td>
						<td align="center">
							<input type="hidden" name="pageStatus"
								value="${pageStatus}" />
							件号：
							<input type="text" name="yuanclAndWaigj.markId"
								value="<s:property value="yuanclAndWaigj.markId"/>" />
						</td>
						<td align="center">
							名称：
							<input type="text" name="yuanclAndWaigj.name"
								value="<s:property value="yuanclAndWaigj.name"/>" />
						</td>
					</tr>
					<tr>
						<td align="center">
							规格：
							<input type="text" name="yuanclAndWaigj.specification"
								value="<s:property value="yuanclAndWaigj.specification"/>" />
						</td>
						<td align="center">
							版本号：
							<input type="text" name="yuanclAndWaigj.banbenhao"
								value="<s:property value="yuanclAndWaigj.banbenhao"/>" />
						</td>
						<td align="center">
							供料属性：
							<input type="text" name="yuanclAndWaigj.kgliao"
								value="<s:property value="yuanclAndWaigj.kgliao"/>" />
						</td>
					</tr>
					<tr>
<!-- 						<td align="center"> -->
<!-- 							产品类型： -->
<%-- 							<select id="ckUnit" name="yuanclAndWaigj.productStyle" --%>
<%-- 								style="width: 155px;"> --%>
<!-- 								<option value="">--产品类型--</option> -->
<!-- 								<option value="试制">试制</option> -->
<!-- 								<option value="批产">批产</option> -->
<%-- 							</select> --%>
<!-- 						</td> -->
<!-- 						<td align="center"> -->
<!-- 							添加时间： -->
<%-- 							<input name="yuanclAndWaigj.addTime" class="Wdate" value="${yuanclAndWaigj.addTime}" --%>
<!-- 									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /> -->
<!-- 						</td> -->
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" class="input" value="查询(select)" />
							<input type="button" value="导出(export)"class="input" 
								onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
						</td>
					</tr>
				</table>
			</form>
			<form action="" method="post" id="myform">
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
							版本
						</td>
						<td align="center">
							供料属性
						</td>
						<td align="center">
							规格
						</td>
						<td align="center">
							库存量
						</td>
						<td align="center">
							在途量
						</td>
						<td align="center">
							占用量
						</td>
						<td align="center">
							呆滞数量
						</td>
					</tr>
					<s:iterator value="YuanclAndWaigjList" id="yuanclAndWaigjPage"
						status="pageStatus0">
						<s:if test="#pageStatus0.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus0.index+1" />
						</td>
						<td align="left">
							${yuanclAndWaigjPage.wgType}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.markId}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.name}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.banbenhao}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.kgliao}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.specification}
						</td>
						<td align="right">
							${yuanclAndWaigjPage.kcCount}
						</td>
						<td align="right">
							${yuanclAndWaigjPage.ztCount}
						</td>
						<td align="right">
							${yuanclAndWaigjPage.zyCount}
						</td>
						<td align="right">
							${yuanclAndWaigjPage.dzCount}
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="28" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="28" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="28" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
					<s:if test='pageStatus == "audit"'>
						<tr>
							<td colspan="28">
								<input type="button" onclick="tosubmit(1)" value="审批通过" class="input">
								<input type="button" onclick="tosubmit(2)" value="审批驳回" class="input">
							</td>
						</tr>
					</s:if>
				</table>

			</form>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function view(id) {
	window.location.href = "yuanclAndWaigjAction_yuanclAndWaigjLogView.action?yuanclAndWaigj.id="
			+ id;
}
function tanchu(id, status) {
	document.getElementById("xiugaiIframe").src = "yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}&status=" + status+"&tag=${tag}";
	chageDiv('block');
}
function update(id) {
	window.location.href = "yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}";
}
function updateStatus(id) {
	window.location.href = "yuanclAndWaigjAction_updateStatus.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}";
}
function todelete(id) {
	if (window.confirm("您将删除此数据,是否继续?")) {
		window.location.href = "yuanclAndWaigjAction_delete.action?yuanclAndWaigj.id="
				+ id + "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}";
	}
}
function add() {
	window.location.href = "<%=path%>/System/yclandwgj/yuanclAndWaigj_add.jsp";
}
function gethad() {
	window.location.href = "yuanclAndWaigjAction_gethad.action";
}
$(function() {
	getUnit("ckUnit")
	getUnit("unit")
	getCaizhi("caizhi");
})

function exportExcel(objForm) {
// 	location.href="yuanclAndWaigjAction_explorExcelMRPCount.action?&pageStatus=${pageStatus}";
	objForm.action = "yuanclAndWaigjAction_explorExcelMRPCount.action?&pageStatus=${pageStatus}";
	objForm.submit();
	objForm.action = "yuanclAndWaigjAction_findMarkIdByMRPCount.action?pageStatus=${pageStatus}";
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
			status : '物料'
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
function tosubmit(num) {
	$("#myform").attr('action',
			'yuanclAndWaigjAction_auditYclWgl.action?id=' + num+"&tag=${tag}")
	$("#myform").submit();
}
</script>
	</body>
</html>

