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
					style="background-color: #ffffff; width: 1000px;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 1000px; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div>
			<div align="left">
				<!-- 显示树形流水卡片模板 -->
				<div style="float: left;width: 35%" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div style="float: left;width: 63%"  id="showProDetail" >
					<div id="showCardTemplate"
						style="display: none; height: 1000px; background-color: #ffffff">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
//========================================zTree显示
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
	var firstId="${param.id}";
	$
			.ajax( {
				url : 'procardTemplateGyAction_toUpLvTree.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : firstId
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
								//var b = true;
									if ($(this).attr('procardStyle') == "自制"
											|| $(this).attr('procardStyle') == "外购") {
										//b = false;
									} else if ($(this).attr('procardStyle') == "总成") {
										totalMaxCount = $(this)
												.attr('maxCount');
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
												pId : $(this).attr('fatherId'),
												proStruts : $(this).attr(
														'procardStyle'),
												rootId : $(this).attr('rootId'),
												markId : $(this).attr('markId'),
												danjiaojian : danjiaojian,
												productStyle : $(this).attr(
														'productStyle'),
												belongLayer : $(this).attr(
														'belongLayer'),
												name : "<span style='font-weight: bolder;font-size: 12px;'>"
														+ $(this).attr(
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
														+ " "
														+ bzStatus,
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
												drop : false,
												open : false
											});

								});
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				},
				error : function() {
					alert("服务器异常!");
				}
			});

}
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
var moveId = 0;
function onDrag(event, treeId, treeNodes) {
	moveId = treeNodes[0].id;
}
function beforeDrag(treeId, treeNodes) {
			return false;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	return false;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	//显示流水卡片明细
	$("#showProDetail").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"procardTemplateGyAction_toupptlv.action?id="
							+ treeNode.id);
}

//显示卡片已有工序
function showProcess() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!findProcessByFkId.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='8'>已有工序(<a onclick='toAddProcess()'>添加工序</a>)</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th><th>特殊工序</th>"
											+ "<th>是否并行</th><th>是否首检</th><th>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					var i=0;
					$(msg)
							.each(
									function() {
										var isSpecial = $(this).attr('isSpecial');
										var applySpecial="";
										if(isSpecial!=null&&isSpecial=="审批中"){
											applySpecial='<a href="CircuitRunAction_findAduitPage.action?id='+ $(this).attr('epId')+'">审批动态</a>/';
										}else if(isSpecial==null||isSpecial.length==0||isSpecial=="普通"){
											isSpecial="普通";
											applySpecial='<a href="javascript:;" onclick=\'applySpecial('
															+ $(this).attr('id')
															+ ','+i+');\'>申请特殊工序</a>/';
										}
										isSpecial = "<label id='isSpecial"+i+"'>"+isSpecial+"</label>"
										$("#ProcessTab")
												.append(
														'<tr align="center"  onmouseover=chageBgcolor(this)  onmouseout=outBgcolor(this,"#FFFFFF") ><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+ parseFloat($(
																		this)
																		.attr(
																				'opshebeijiepai')
																		+ $(
																				this)
																				.attr(
																						'opcaozuojiepai')
																		+ $(
																				this)
																				.attr(
																						'gzzhunbeijiepai')
																		* $(
																				this)
																				.attr(
																						'gzzhunbeicishu'))
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'productStyle')
																+ '</td><td>'
																+ isSpecial
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processStatus')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'zjStatus')
																+ '</td><td>' 
																+applySpecial
																+'<a href="javascript:;" onclick="showProcessTz('
																+ $(this).attr(
																		'id')
																+ ');">图纸</a>/<a href="javascript:;" onclick="showProcessForSb('
																+ $(this).attr(
																		'id')//  onmouseout="outBgcolor(this,'#e6f3fb')" onmouseover="alert(000000000)"
																+ ');">修改</a>/<a href="javascript:;" onclick="showProcessZJ('
																	+$(this).attr('id')+','+$(this).attr('processNO')+');">质验</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a></td></tr>');
																																
																
										
										var processStatus = $(this).attr(
												'processStatus');
										if (processStatus == "no") {
											$("#parallelId").val("");
										} else {
											$("#parallelId").val(
													$(this).attr('id'));
										}
										maxProcessNO = parseFloat($(this).attr(
												'processNO')) + 5;
										i++;
									});
					$("#processNO").val(maxProcessNO);//计算下一个工序号是多少，方便填写
					$("#processName").select();
				}
			});
}


//显示工序详细
function showProcessForSb(id,ProcessNO ) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcess.action?id=" + id);
	chageDiv('block');

}

</script>

		<script language="javascript">
	</body>
</html>
