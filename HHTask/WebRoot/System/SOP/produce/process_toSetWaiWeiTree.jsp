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
	<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 254; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序外委申请</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div style="" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
			<div style="border: 1px solid #000000; position: absolute; background-color: #ffffff"
					id="showProDetail">
					<div id="showCardTemplate"
						style="display: none; height: 1000px;width:1000px; background-color: #ffffff">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>
					</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
<SCRIPT type="text/javascript">
//自动组装树形结构
var setting = {
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		showTitle : true
	},
	data : {
		simpleData : {
			enable : true
		},
		key : {
			title : "title"
		}
	},
	callback : {
		onClick : onClick,
		beforeDrag : beforeDrag,
		beforeDrop : beforeDrop,
		onDrag : onDrag
	},
	view : {
		fontCss : getFont,
		nameIsHTML : true,
		showTitle : true
	}
};
function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), isCopy = true, isMove = true, prev = true, inner = true, next = true;
	zTree.setting.edit.drag.isCopy = isCopy;
	zTree.setting.edit.drag.isMove = isMove;

	zTree.setting.edit.drag.prev = prev;
	zTree.setting.edit.drag.inner = inner;
	zTree.setting.edit.drag.next = next;
}

//加载树形数据
$(document).ready(loadTree());
var totalMaxCount = 0;
//生成
function loadTree() {
	$
			.ajax( {
				url : 'ProcardAction!findProcardTemByRootId.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${id}'
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
								//var b = true;
									//供料属性
									var glsx = '';
									if ($(this).attr('procardStyle') == "外购"
											&& $(this).attr('kgliao') != null
											&& $(this).attr('kgliao') != ""
											&& $(this).attr('kgliao') != "TK") {
										glsx = "<span style='color:green;margin-right:0px;'>"
												+ $(this).attr('kgliao')
												+ "</span>";
									}
									//单交件状态
									var danjiaojian = $(this).attr(
											'danjiaojian');
									if (danjiaojian == null) {
										danjiaojian = "";
									}
									//半成品状态
									var needProcess = $(this).attr(
											'needProcess');
									if (needProcess == "yes") {
										needProcess = "(半成品)";
									} else {
										needProcess = "";
									}

									var hgstyle = "<span style='font-weight: bolder;font-size: 18px;'>--</span>";
									zNodes
											.push( {
												id : $(this).attr('id'),
												proStruts : $(this).attr(
														'procardStyle'),
												rootId : $(this).attr('rootId'),
												markId : $(this).attr('markId'),
												pId : $(this).attr('fatherId'),
												proStruts : $(this).attr(
														'procardStyle'),
												danjiaojian : danjiaojian,
												productStyle : $(this).attr(
														'productStyle'),
												belongLayer : $(this).attr(
														'belongLayer'),
												name : "<span style='font-weight: bolder;font-size: 12px;'>"
														+$(this).attr(
														'procardStyle')
														+ needProcess
														+ "</span>"
														+ hgstyle
														+ $(this)
																.attr('markId')
														+ hgstyle
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ " "+ glsx,
												title : $(this).attr(
														'procardStyle')
														+ '--'
														+ $(this)
																.attr('markId')
														+ '--'
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ needProcess,
												click : false,
												drop : true,
												open : true
											});

								});
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				},
				error : function() {
					alert("服务器异常!!");
				}
			});

}
function getFont(treeId, node) {
	return node.font ? node.font : {};
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var e = event || window.event;
	var scrollTop = getScrollTop();//获取滚动条离顶部距离
	var mouseLeft = e.clientX + 30;
	var mouseTop = e.clientY - 40 + scrollTop;
	if (mouseTop < 0) {
		mouseTop = 0;
	}
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft
	});
	//显示流水卡片明细
	$("#bodyDiv").show();
	$("#showProDetail").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardAction!towwsqyx.action?id="
							+ treeNode.id);
	$("#bodyDiv").bind("click", function() {
		$("#bodyDiv").hide();
		$("#showProDetail").hide();
		$("#showCardTemplate").hide();
	});
}
var moveId = 0;
function onDrag(event, treeId, treeNodes) {
	moveId = treeNodes[0].id;
}
function beforeDrag(treeId, treeNodes) {
			return false;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return false
}
</SCRIPT>
	</body>
</html>
