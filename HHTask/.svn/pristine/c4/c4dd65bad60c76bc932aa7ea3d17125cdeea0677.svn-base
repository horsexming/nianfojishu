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
		<div>
			<div align="left">
				<!-- 显示树形流水卡片模板 -->
				<div style="" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div
					style="border: 1px solid #000000; position: absolute; background-color: #ffffff"
					id="showProDetail">
					<div id="selectDiv" style="display: none;" align="center">
						<input id="module4" type="button" value="工序管理"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 80px; height: 40px;" />
						<input id="uId" type="hidden">
						<input id="module10" type="button" value="产品图纸"
							onclick="showCardTz()" style="width: 80px; height: 40px;" />
						<input id="module11" type="button" value="BOM预览"
							onclick="reviewBom();" style="width: 80px; height: 40px;" />
						<input id="module11_1" type="button" value="自检"
							onclick="checkSelf();" style="width: 80px; height: 40px;" />
						<br />
					</div>
					<div id="module1_4"
						style="display: none; background-color: #ffffff" align="center">
						<div>
							<table id="ProcessTab" class="table" style="width: 100%;">
							</table>
						</div>
						<br />
						<br />
						<form id="processForm"
							action="javascript:submitForm2('processForm');"
							style="margin: 0px; padding: 0px;" method="post">
							<input id="cardId" type="hidden" name="id" />
							<input id="parallelId" type="hidden"
								name="processTemplate.parallelId" />
							<input type="hidden" name="processTemplate.optechnologyRate"
								value="0" />
							<input type="hidden" name="processTemplate.opCouldReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.opfuheRate" value="0" />
							<input type="hidden" name="processTemplate.opcaozuojiepai"
								value="0" />
							<input type="hidden" name="processTemplate.opshebeijiepai"
								value="0" />
							<input type="hidden" name="processTemplate.opnoReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.opzonghezhishu"
								value="0" />
							<input type="hidden" name="processTemplate.opzongheqiangdu"
								value="0" />
							<input type="hidden" name="processTemplate.gztechnologyRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzCouldReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzfuheRate" value="0" />
							<input type="hidden" name="processTemplate.gzzhunbeijiepai"
								value="0" />
							<input type="hidden" name="processTemplate.gzzhunbeicishu"
								value="0" />
							<input type="hidden" name="processTemplate.gznoReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzzonghezhishu"
								value="0" />
							<input type="hidden" name="processTemplate.gzzongheqiangdu"
								value="0" />
							<input type="hidden" name="processTemplate.processMomey"
								value="0" />
							<input type="hidden" name="processTemplate.opjiaofu" value="0" />
						</form>
					</div>
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
				url : 'ProcardTemplateAction!findProcardTemByRootId.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${param.id}'
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
									var procardStyle =$(this).attr('procardStyle');
									if(procardStyle=="待定"){
										procardStyle="<span style='color:red;margin-right:0px;'>待定</span>";
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
												sbStatus : $(this).attr('sbStatus'),
												bzStatus : $(this).attr('bzStatus'),
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
														+ procardStyle
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
												open : true
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
	for ( var i = 0, l = treeNodes.length; i < l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		return false;
}
var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2, bcpStatus) {
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	if (bcpStatus) {
		$("#neddProcess").val("yes");
		changeneedProcess("yes");
		obj2 = "2";
	} else {
		$("#neddProcess").val("no");
		changeneedProcess("no");
	}
	$("#module1_" + obj2).show('slow');
	oldObj = obj;
	oldObj2 = obj2;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	var danjiaojian = treeNode.danjiaojian;// 卡片状态(总成，零组件，原材料)
	var productStyle = treeNode.productStyle;//生产类型
	var sbStatus =  treeNode.sbStatus;//设变申请状态
	var bzStatus =  treeNode.bzStatus;//编制状态
	if(bzStatus=="已批准"){
		if(sbStatus=="未审批"||sbStatus=="审批中"){
			$("#module14").attr("disabled", true);
		}else{
			$("#module14").attr("disabled", false);
		}
	}else{
		$("#module14").attr("disabled", true);
	}
	
	//工序赋值
	$("#cardId").val(treeNode.id);
	$("#mfatherId").val(treeNode.id);
	$("#uId").val(treeNode.id);
	$("#newMarkId").val(treeNode.markId);
	$("#oldMarkId").empty();
	$("#oldMarkId").append(treeNode.markId);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	var e = event || window.event;
	var scrollTop = getScrollTop();//获取滚动条离顶部距离
	var mouseLeft = e.clientX + 150;
	var mouseTop = e.clientY - 80 + scrollTop;
	if (mouseTop < 0) {
		mouseTop = 0;
	}
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft
	});

if (proStruts == "总成" || proStruts == "组合") {
		//零组件赋值
		$("#rootId").val(treeNode.rootId);
		$("#fatherId").val(treeNode.id);
		$("#belongLayer").val(treeNode.belongLayer + 1);
		//外购件赋值
		$("#wrootId").val(treeNode.rootId);
		$("#wfatherId").val(treeNode.id);
		$("#wbelongLayer").val(treeNode.belongLayer + 1);
		//原材料赋值
		$("#yrootId").val(treeNode.rootId);
		$("#yfatherId").val(treeNode.id);
		$("#ybelongLayer").val(treeNode.belongLayer + 1);
		if (danjiaojian == "单交件") {
			//全部不可用
			$("#module4").attr("disabled", false);
		} else {
			//全部可用
			$("#module4").attr("disabled", false);
		}
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module4").attr("disabled", true);
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module4").attr("disabled", false);
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#bodyDiv").show();
	$("#showProDetail").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ treeNode.id+"&&pageStatus=view");
	$("#bodyDiv").bind("click", function() {
		$("#bodyDiv").hide();
		$("#showProDetail").hide();
		$("#showCardTemplate").hide();
	});
}
//添加组件/原材料流水卡片

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
									"<tr><th colspan='8'>已有工序</th></tr>"
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
											applySpecial='';
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
																+ ');">图纸</a></td></tr>');
																																
																
										
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
//添加工序
function submitForm2(formId) {
	if ($("#processName").val() == "") {
		alert("请填写工序名称!");
		$("#processName").select();
	} else {
		if ($("#processStatus").val() == "no") {
			$("#parallelId").val("");
		}
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!addProcessTemplate.action",
			dataType : "json",
			data : $("#" + formId).serialize(),
			success : function(msg) {
				if (msg) {
					alert("添加成功!");
					showProcess();
					$("#processNO").val(parseFloat($("#processNO").val()) + 5);
					$("#processName").val("");
					$("#processName").select();

				} else {
					alert("添加失败!");
				}
			}
		});
	}
}

//删除流水卡片

//精益计算

//页面内容清空
function showDiv() {
	//清空工序table
	$("#ProcessTab").empty();
	//隐藏各个添加的详细页面
	$("#showCardTemplate").hide();
	$("#processDiv").hide();
	$("#lingDiv").hide();
	$("#yuanDiv").hide();
}

//显示工序详细
function showProcessForSb(id,ProcessNO ) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcess.action?id=" + id);
	chageDiv('block');

}
//显示工序图纸
function showProcessTz(id) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcessTz.action?id=" + id+"&pageStatus=view");
	chageDiv('block');

}
//申请特殊工序
//显示工序质检；
function showProcessZJ(id,ProcessNO ){
	$("#showProcess").attr("src",
		"OsTemplate_addInput.action?id="+id+"&gongxuNum="+ProcessNO);


	chageDiv('block');
}

</script>

		<script language="javascript">
//ajax获取所有的类似的全称

/**
 * 显示添加工序弹框
 */
function toAddProcess() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!toAddProcess.action?id=" + id);
	chageDiv('block');
}
//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				//$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.id + "'>" + n.processName
									+ "</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}
getProcess();
function showSonCard() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showSonCard.action?id=" + id);
	chageDiv('block');

}
</script>
		<!-- 外购件原材料模糊搜索js -->
		<SCRIPT type="text/javascript">

function showProcardTemplate(id,obj){
	if(obj == "zuhemarkId"){
		obj="zuhe";
	}else if(obj =="zizmarkId"){
		obj = "ziz";
	}
	$.ajax({
		type : "POST",
		url : "ProcardTemplateAction!findProcardTemById.action",
		dataType : "json",
		data : {id:id},
		success : function(pt) {
			if(pt!=null){
				$("#"+obj+"proName").val(pt.proName);
				$("#"+obj+"anBenNumber").val(pt.banBenNumber);
				$("#"+obj+"loadMarkId").val(pt.loadMarkId);
				$("#"+obj+"carStyle").val(pt.carStyle);
				$("#"+obj+"corrCount").val(pt.corrCount);
				$("#"+obj+"safeCount").val(pt.safeCount);
				$("#"+obj+"lastCount").val(pt.lastCount);
				$("#"+obj+"procardStyle").val(pt.procardStyle);
				$("#"+obj+"unit").val(pt.unit);
				$("#"+obj+"productStyle").val(pt.productStyle);
				$("#"+obj+"status").val(pt.status);
				$("#"+obj+"lingliaostatus").val(pt.lingliaostatus);
				$("#"+obj+"trademark").val(pt.trademark);
				$("#"+obj+"specification").val(pt.specification);
				$("#"+obj+"bili").val(pt.bili);
				$("#"+obj+"yuanUnit").val(pt.yuanUnit);
				$("#"+obj+"clType").val(pt.clType);
				$("#"+obj+"quanzi1").val(pt.quanzi1);
				$("#"+obj+"quanzi2").val(pt.quanzi2);
				$("#"+obj+"jgyl").val(pt.jgyl);
				$("#"+obj+"luhao").val(pt.luhao);
				$("#"+obj+"number").val(pt.number);
				$("#"+obj+"actualFixed").val(pt.actualFixed);
				$("#"+obj+"status").val(pt.status);
				$("#"+obj+"numb").val(pt.numb);
				$("#"+obj+"fachuDate").val(pt.fachuDate);
				$("#"+obj+"pageTotal").val(pt.pageTotal);
				$("#"+obj+"bianzhiId").val(pt.bianzhiId);
				$("#"+obj+"jiaoduiId").val(pt.jiaoduiId);
				$("#"+obj+"shenheId").val(pt.shenheId);
				$("#"+obj+"pizhunId").val(pt.pizhunId);
				$("#"+obj+"remark").val(pt.remark);
			}
		}
		
	});
}

//ajax获取所有的类似的全称
		
//ajax获取所有的类似的全称

//修改外购件是否需要工序
function changeneedProcess(obj){
	if(obj=="yes"){
		$("#safetr").show();
		$("#lasttr").show();
	}else{
		$("#safetr").hide();
		$("#lasttr").hide();
	}
	
}
function reviewBom(){
	window.open("procardTemplateGyAction_reviewBom.action?id=${param.id}");  
}
function showCardTz(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showCardTz.action?id=" + id+"&pageStatus=view");
	chageDiv('block');
}
function selectYclAndWgj(type){
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type="+type);
	chageDiv('block');
}
function noZhongwen(obj,obj1,obj2,obj3,obj4){
	getAllNames(obj1,obj2,obj3,obj4);
	if (escape(obj.value).indexOf( "%u" )>=0){
	  obj.value="";
	  alert( "不能包含中文!" );
	}
}
function checkSelf(){
	$("#showProcess").attr("src",
			"procardTemplateGyAction_checkSelf.action?id=${param.id}");
	chageDiv('block');
}
		</SCRIPT>
	</body>
</html>
