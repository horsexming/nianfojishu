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
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<!-- 显示树形流水卡片模板 -->
				<div style="" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<style>
/*按钮*/
.icon_div {
	height: 25px;
	width: 35px;
	color: #000;
}

.icon_div a {
	display: inline-block;
	width: 27px;
	height: 20px;
	cursor: pointer;
}

/*end--按钮*/ /*ztree表格*/
.ztree {
	padding: 0;
	border: 2px solid #CDD6D5;
}

.ztree li a {
	vertical-align: middle;
	height: 30px;
}

.ztree li a.curSelectedNode {
	height: 30px;
}

.ztree li>a {
	width: 100%;
}

.ztree li>a,.ztree li a.curSelectedNode {
	padding-top: 0px;
	background: none;
	border: none;
	cursor: default;
	opacity: 1;
}

.ztree li ul {
	padding-left: 0px
}

.ztree div.diy span {
	line-height: 30px;
	vertical-align: middle;
}

.ztree div.diy {
	height: 100%;
	width: 18%;
	line-height: 30px;
	border-top: 1px dotted #ccc;
	border-left: 1px solid #000000;
	text-align: center;
	display: inline-block;
	box-sizing: border-box;
	color: #000000;
	font-family: "SimSun";
	font-size: 14px;
	overflow: hidden;
	font-weight: bolder;
}

.ztree div.diy:first-child {
	text-align: left;
	text-indent: 10px;
	border-left: thin;
}

.ztree .head {
	background: #5787EB;
}

.ztree .head div.diy {
	border-top: none;
	border-right: 1px solid #CDD2D4;
	color: #fff;
	font-family: "Microsoft YaHei";
	font-size: 14px;
}
/*end--ztree表格*/
</style>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
//========================================zTree显示
var module4 = $("#module4");
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
	callback : {},
	view : {
		fontCss : getFont,
		nameIsHTML : true,
		showTitle : true,
		showLine : true,
		showIcon : true,
		addDiyDom : addDiyDom
	}
};
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
	var spaceWidth = 15;
	var liObj = $("#" + treeNode.tId);
	var aObj = $("#" + treeNode.tId + "_a");
	var switchObj = $("#" + treeNode.tId + "_switch");
	var icoObj = $("#" + treeNode.tId + "_ico");
	var spanObj = $("#" + treeNode.tId + "_span");
	aObj.attr('title', '');
	aObj.append('<div class="diy swich"></div>');
	var div = $(liObj).find('div').eq(0);
	switchObj.remove();
	spanObj.remove();
	icoObj.remove();
	div.append(switchObj);
	div.append(spanObj);
	var spaceStr = "<span style='height:1px;display: inline-block;width:"
			+ (spaceWidth * treeNode.level) + "px'></span>";
	switchObj.before(spaceStr);
	var editStr = '';
	var markidAndTuhao = treeNode.markId;
	if (treeNode.tuhao != null && treeNode.tuhao != ""
			&& treeNode.tuhao != treeNode.markId) {
		markidAndTuhao += "(" + treeNode.tuhao + ")"
	}
	editStr += '<div class="diy" style="text-align: left;">' + (markidAndTuhao == null ? '&nbsp;'
			: markidAndTuhao) + '</div>';
	var corpCat = '<div title="' + treeNode.proName + '">' + treeNode.proName
			+ '</div>';
	editStr += '<div class="diy" style="text-align: left;">' + (treeNode.proName == null ? '&nbsp;'
			: corpCat) + '</div>';
	editStr += '<div class="diy" style="text-align: left;width:50px;">' + treeNode.status + '</div>';
	editStr += '<div class="diy" style="text-align: left;width:140px;">' + (treeNode.jihuoDate == null ? '&nbsp;'
			: treeNode.jihuoDate) + '</div>';
	editStr += '<div class="diy" style="width:140px;">' + (treeNode.needFinalDate == null ? '&nbsp;'
			: treeNode.needFinalDate) + '</div>';
	editStr += '<div class="diy" style="width:140px;">' + (treeNode.nowFinalDate == null ? '&nbsp;'
			: treeNode.nowFinalDate) + '</div>';
	editStr += '<div class="diy" style="text-align: left;width:90px;" >'
			+ '<a  title="查看"  href="javascript:updateTime(\'' + treeNode.id
			+ '\')">调时</a>/'
			+ '<a  title="查看"  href="javascript:bangdingPeople(\''
			+ treeNode.id + '\')">绑人</a></div>';
	aObj.append(editStr);
}

function updateTime(id) {
	$("#showProcess").attr("src",
			"ProcardAction!findProcard.action?id=" + id);
	chageDiv('block');
}
function bangdingPeople(id) {
	$("#showProcess").attr("src", "ProcardAction!findPeople.action?id=" + id);
	$("#showProcess").css("height", "2000px");
	chageDiv('block');
}

//加载树形数据
$(document).ready(loadTree());
var totalMaxCount = 0;
//生成
function loadTree() {
	$
			.ajax( {
				url : 'ProcardAction!findProcardByRootId.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${param.id}',
					pageStatus : 'plan'
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
								//var b = true;
									if ($(this).attr('procardStyle') == "总成") {
										totalMaxCount = $(this)
												.attr('maxCount');
									}
									//供料属性
									var glsx = '';
									if ($(this).attr('procardStyle') == "外购"
											&& $(this).attr('kgliao') != null
											&& $(this).attr('kgliao') != ""
											&& $(this).attr('kgliao') != "TK") {
										glsx = " <span style='color:green;margin-right:0px;'>"
												+ $(this).attr('kgliao')
												+ "</span>";
									}
									var procardStyle = $(this).attr(
											'procardStyle');
									if (procardStyle == "待定") {
										procardStyle = "<span style='color:red;margin-right:0px;'>待定</span>";
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
										needProcess = " (半成品)";
									} else {
										needProcess = "";
									}
									var bzStatus = $(this).attr('bzStatus');
									if (bzStatus == null || bzStatus == "") {
										bzStatus = "初始";
									}
									if (bzStatus != "已批准") {
										bzStatus = "<span style='color:red;margin-right:0px;'>"
												+ bzStatus + "</span>";
									} else {
										bzStatus = "";
									}

									var hgstyle = "<span style='font-weight: bolder;font-size: 18px;'>--</span>";
									zNodes
											.push( {
												id : $(this).attr('id'),
												sbStatus : $(this).attr(
														'sbStatus'),
												bzStatus : $(this).attr(
														'bzStatus'),
												banBenNumber : $(this).attr(
														'banBenNumber'),
												banci : $(this).attr('banci'),
												bomApplyStatus : $(this).attr(
														'bomApplyStatus'),
												epId2 : $(this).attr('epId2'),
												pId : $(this).attr('fatherId'),
												proStruts : $(this).attr(
														'procardStyle'),
												rootId : $(this).attr('rootId'),
												markId : $(this).attr('markId'),
												tuhao : $(this).attr('tuhao'),
												danjiaojian : danjiaojian,
												productStyle : $(this).attr(
														'productStyle'),
												belongLayer : $(this).attr(
														'belongLayer'),
												name : procardStyle
														+ needProcess + glsx,
												proName : $(this).attr(
														'proName'),
												quanzi1 : $(this).attr(
														'quanzi1'),
												quanzi2 : $(this).attr(
														'quanzi2'),
												corrCount : $(this).attr(
														'corrCount'),
												bianzhiName : $(this).attr(
														'bianzhiName'),
												jiaoduiName : $(this).attr(
														'jiaoduiName'),
												shenheName : $(this).attr(
														'shenheName'),
												pizhunName : $(this).attr(
														'pizhunName'),
												jihuoDate : $(this).attr(
														'jihuoDate'),
												needFinalDate : $(this).attr(
														'needFinalDate'),
												nowFinalDate : $(this).attr(
														'nowFinalDate'),
												status : $(this).attr('status'),
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
					//添加表头
					var li_head = ' <li class="head"><a><div class="diy" >产品类型</div><div class="diy" align="center">件号/图号</div><div class="diy" >产品名称</div><div class="diy" style="width:50px;">状态</div><div class="diy" style="width:140px;">计划开始日期</div><div class="diy"  style="width:140px;">计划完成日期</div><div class="diy"  style="width:140px;">实际完成日期</div><div class="diy" style="width:90px;">调整操作</div></a></li>';
					var rows = $("#treeDemo").find('li');
					if (rows.length > 0) {
						rows.eq(0).before(li_head)
					} else {
						$("#treeDemo").append(li_head);
						$("#treeDemo")
								.append(
										'<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});

}
</script>
	</body>
</html>
