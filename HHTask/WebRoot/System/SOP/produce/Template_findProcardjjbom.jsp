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
					<div align="center" style="display: none; width: 100%" id="showdateil2">
						<table style="width: 100%">
							<tr>
								<th style="font-size: 10px;">
									产品明细与维护
								</th>
								<td align="right">
									<img alt="" src="<%=basePath%>/images/closeImage.png"
										width="30" height="25"
										onclick="javascript:$('#showProDetail').hide();chageDiv('none')">
								</td>
							</tr>
						</table>
					</div>
					<div id="selectDiv" style="display: none; " align="center">
						<input id="module4" type="button" value="工序计件单价"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 120px; height: 40px;" />
					</div>
					<div id="module1_4"
						style="display: none; background-color: #ffffff" align="center">
						<font id="msg_font" color="red" size="5mm"></font>
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
		<div style="display: none;"></div>

		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
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
<%--	edit : {--%>
<%--		enable : true,--%>
<%--		showRemoveBtn : false,--%>
<%--		showRenameBtn : false,--%>
<%--		showTitle : true--%>
<%--	},--%>
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
<%--		beforeDrag : beforeDrag,--%>
<%--		beforeDrop : beforeDrop,--%>
<%--		onDrag : onDrag--%>
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
										glsx = "<span style='color:green;margin-right:0px;'>"
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
												sbStatus : $(this).attr(
														'sbStatus'),
												bzStatus : $(this).attr(
														'bzStatus'),
												bomApplyStatus : $(this).attr(
														'bomApplyStatus'),
												epId2 : $(this).attr('epId2'),
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
														+ bzStatus + " " + glsx,
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
}function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	if (!window.confirm("是否移动bom结构")) {
		return false;
	}
	if (targetNode.id == null) {
		return false;
	}
	var moveok = targetNode ? targetNode.drop !== false : true;
	if (!moveok) {
		return moveok;
	}
	if (moveok) {
		//alert(moveId);
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!moveProcardTemplate.action",
			dataType : "json",
			data : {
				moveId : moveId,
				targetId : targetNode.id
			},
			success : function(msg) {
				alert(msg.message);
				loadTree();
				return true;
			}
		});
	}
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
	var sbStatus = treeNode.sbStatus;//设变申请状态
	var bzStatus = treeNode.bzStatus;//编制状态
	var bomApplyStatus = treeNode.bomApplyStatus;//编制状态
	if (proStruts == "总成"
			&& (bomApplyStatus == null || (!bomApplyStatus == "未审批"
					&& !bomApplyStatus == "审批中" && !bomApplyStatus == "同意"))) {
		$("#module15").attr("disabled", false);
	} else {
		$("#module15").attr("disabled", true);
	}
	if (bomApplyStatus == "未审批" || bomApplyStatus == "审批中"
			|| bomApplyStatus == "同意") {
		$("#module16").show();
	} else {
		$("#module16").hide();
	}
	$("#module1_12").hide();

<%--	if (bzStatus == "已批准") {--%>
<%--		if (sbStatus == "未审批" || sbStatus == "审批中") {--%>
<%--			$("#module14").attr("disabled", true);--%>
<%--		} else {--%>
<%--			$("#module14").attr("disabled", false);--%>
<%--		}--%>
<%--	} else {--%>
<%--		$("#module14").attr("disabled", true);--%>
<%--	}--%>

	//工序赋值
	$("#epId2").val(treeNode.epId2);
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
	var  width =  document.body.clientWidth;//网页可见区域宽
	var mouseLeft = e.clientX + 30;
	var mouseTop = e.clientY - 40 + scrollTop;
	width=width- mouseLeft;
	if (mouseTop < 0) {
		mouseTop = 0;
	}
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft,
		"width":width
	});

	if (proStruts == "总成" && productStyle == "试制") {
		$("#module9").show();
	} else {
		$("#module9").hide();
	}
	if (proStruts == "总成") {
		$("#module17").show();
		$("#module12").show();
	} else {
		$("#module17").hide();
		$("#module12").hide();
	}
if (proStruts == "总成" || proStruts == "自制") {
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
			$("#module1").attr("disabled", true);
			$("#module2").attr("disabled", true);
			$("#module2_1").attr("disabled", true);
			$("#module3").attr("disabled", true);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
			$("#module6").attr("disabled", false);
		} else {
			//全部可用
			$("#module1").attr("disabled", false);
			$("#module2").attr("disabled", false);
			$("#module2_1").attr("disabled", false);
			$("#module3").attr("disabled", false);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
			$("#module6").attr("disabled", false);
			if (proStruts == "总成") {
				$("#module6").attr("disabled", false);
			}
		}
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
		$("#module5").attr("disabled", true);
		$("#module5").attr("disabled", true);
		$("#module6").attr("disabled", false);
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module2_1").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
		$("#module5").attr("disabled", false);
		$("#module6").attr("disabled", false);
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#bodyDiv").show();
	$("#showProDetail").show();
	$("#showdateil2").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ treeNode.id+"&tag==jjbom");
	$("#bodyDiv").bind("click", function() {
		$("#bodyDiv").hide();
		$("#showProDetail").hide();
		$("#showCardTemplate").hide();
	});
}
//添加组件/原材料流水卡片
function submitForm(obj,formId) {
	if(formId=="lingForm"){//组合
		var makrId=$("#zuhemarkId").val();
		var proName=$("#zuheproName").val();
		var corrCount=$("#zuhecorrCount").val();
		var lingliaostatus=$("#zuhelingliaostatus").val();
		var trademark=$("#zuhetrademark").val();
		if(makrId==null||makrId==""){
			alert("请填写件号!");
			return false;
		}
		if(proName==null||proName==""){
			alert("请填写名称!");
			return false;
		}
		if(corrCount==null||corrCount==""){
			alert("请填写组合与上层的比例!");
			return false;
		}
		//if((lingliaostatus==null||lingliaostatus==""||lingliaostatus=="是")&&trademark.length>0){
		//	var specification=$("#zuhespecification").val();
		//	if(specification==null||specification==""){
		//		alert("该组合需领料且有原材料，请填写原材料规格!");
		//		return false;
		//	}
		//	var quanzi1=$("#zuhequanzi1").val();
		//	var quanzi2=$("#zuhequanzi2").val();
		//	if(quanzi1==null||quanzi1==""||quanzi2==null||quanzi2==""){
		//		alert("该组合需领料且有原材料，请填写原材料的权值比例!");
		//		return false;
		//	}
		//}
	}
	$(obj).attr("disabled","disabled");
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!addProcardTemplate.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(data) {
			if (data.success) {
				alert("添加成功!");
				$(obj).removeAttr("disabled");
				loadTree();
			} else {
				$(obj).removeAttr("disabled");
				alert(data.message);
			}
		}
	});
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
							.append( "<tr><th>工序号</th><th>名称</th><th style='width:50px;'>总节拍(s)</th><th style='width:80px;'>生产类型</th><th style='width:80px;'>特殊工序</th>"+ "<th style='width:50px;'>工序点数</th>" +
							"<th style='width:50px;'>计件单价(<font id='sum1' color='red'></font>)</th>" +
							"<th>实际单价(<font id='sum2' color='red'></font>)</th>" +
							"<th>盈亏单价(<font id='sum3' color='red'></font>)</th><th style='width:50px;'>系数</th><th style='width:300px;'>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					var i=0;
					var sum1 = 0;
					var sum2 = 0;
					var sum3 = 0;
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
															+ ','+i+');\'>申请多工序并行</a>/';
										}
										isSpecial = "<label id='isSpecial"+i+"'>"+isSpecial+"</label>"
										var procesdianshu =  $(this).attr('procesdianshu');
										var processjjMoney =  $(this).attr('processjjMoney');
										var id = $(this).attr('id');
										var jjratio =  $(this).attr('jjratio');
										var sjprocessMomey = $(this).attr('sjprocessMomey');
										var ykprocessMomey = $(this).attr('ykprocessMomey');
										if(procesdianshu == 'null' || procesdianshu == null){
											procesdianshu = '';
										}
										if(processjjMoney == 'null' || processjjMoney == null){
											processjjMoney = '';
										}else{
											sum1+=processjjMoney;
										}
										if(jjratio == 'null' || jjratio == null){
											jjratio = '';
										}
										if(sjprocessMomey == 'null' || sjprocessMomey == null){
											sjprocessMomey = '';
										}else{
											sum2+=sjprocessMomey;
										}
										if(ykprocessMomey == 'null' || ykprocessMomey == null){
											ykprocessMomey = '';
										}else{
											sum3+=ykprocessMomey;
										}
										var html='<tr align="center"  onmouseover=chageBgcolor(this)  onmouseout=outBgcolor(this,"#FFFFFF") ><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td  align="left">'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td style="width:50px;">'
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
																						'gzzhunbeicishu')).toFixed(4)
																+ '</td><td style="width:80px;">'
																+ $(this)
																		.attr(
																				'productStyle')
																+ '</td><td style="width:80px;">'
																+ isSpecial
																+ '</td><td style="width:50px;">'
																+ '<input type="text" style="width:50px;" disabled="disabled" onblur="numyanzheng(this)" value="'+procesdianshu+'" readonly="readonly" id="procesdianshu_'+id+'" name="processTemplate.procesdianshu" class ="process_price_'+id+'" />' 
																+ '</td><td style="width:50px;">'
																+ '<input type="text" style="width:50px;" disabled="disabled" onchange="numyanzheng(this)"   value="'+processjjMoney+'" readonly="readonly" id="processjjMoney_'+id+'" name="processTemplate.processjjMoney"  class ="process_price_'+id+'" />'
																+ '</td>' 
																+'<td><input type="text" style="width:50px;" disabled="disabled" onchange="numyanzheng(this);jisuanjjration('+id+')"  value="'+sjprocessMomey+'" readonly="readonly" id="sjprocessMomey_'+id+'" name="processTemplate.sjprocessMomey"  class ="process_price_'+id+'"  /></td>'
																+'<td><input type="text" style="width:50px;" disabled="disabled" onchange="numyanzheng(this);jisuanjjration('+id+')"  value="'+ykprocessMomey+'" readonly="readonly"  id="ykprocessMomey_'+id+'" name="processTemplate.ykprocessMomey"  class ="process_price_'+id+'" /></td>' 
																+'<td style="width:50px;"><input type="text" style="width:50px;" disabled="disabled" onchange="numyanzheng(this)"  value="'+jjratio+'" id="jjratio_'+id+'" readonly="readonly"  name="processTemplate.jjratio"  class ="process_price_'+id+'" /></td>' 
																+'<td id="xiugai_td_'+id+'"  style="width:300px; "><a href="javascript:;" onclick="toupadteProcessPrice('+id+')" id="xiugai_a_'+id+'"><span id="xiugai_span_'+id+'">修改BOM单价</span></a>'
																+'/<a href="javascript:;" onclick="tanchu('+id+',&apos;xgpc&apos;)" id="xiugai_a1_'+id+'"><span id="xiugai_span1_'+id+'">修改批次单价</span></a>/<a href="javascript:;" onclick="tanchu('+id+')">修改记录</a></td></tr>';
										$("#ProcessTab").append(html);
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
					$("#sum1").html(sum1.toFixed(4));
					$("#sum2").html(sum2.toFixed(4));
					$("#sum3").html(sum3.toFixed(4));
					$("#processNO").val(maxProcessNO);//计算下一个工序号是多少，方便填写
					$("#processName").select();
				}
			});
}

//修改工序计件单价
function toupadteProcessPrice(id){
	if(id!=null){
	var bool = true;
			$.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!isNeedJjPrice.action",
				dataType : "json",
				async:false,
				data : {
					id:id
				},
				success : function(data) {
					bool = data;
				}
			});
		if(bool){
			var status = '${param.pageStatus}';
			if(status == 'caiwu'){
				$("#sjprocessMomey_"+id).removeAttr("readonly");
			}else{
				$("#procesdianshu_"+id).removeAttr("readonly");
				$("#processjjMoney_"+id).removeAttr("readonly");
			}
			$(".process_price_"+id).removeAttr("disabled");
			$("#xiugai_a_"+id).attr("onclick",'upadteProcessPrice('+id+')');
			$("#xiugai_span_"+id).html('修改');
		}else{
			$("#msg_font").html('该工序已有按工时计价奖金的单价，不可添加计件奖金单价!');
		}
			
	}
}
//数字取值范围num1~num2 (前后都包含)
function fanweiNUM(obj,num1,num2){
	if(obj!=null){
		var num = obj.value;
		if(num<num1){
			$("#msg_font").html('请输入'+num1+"~"+num2+"范围的数字");
			obj.value='';
			return ;
		}
		if(num>num2){
			$("#msg_font").html('请输入'+num1+"~"+num2+"范围的数字");
			obj.value='';
			return ;
		}
	}
}
function upadteProcessPrice(id){
	var procesdianshu = $("#procesdianshu_"+id).val();
	var sjprocessMomey = $("#sjprocessMomey_"+id).val();
	var ykprocessMomey = $("#ykprocessMomey_"+id).val();
	var processjjMoney = $("#processjjMoney_"+id).val();
	var jjratio = $("#jjratio_"+id).val();
	var bool = true;
<%--	if(procesdianshu == ''){--%>
<%--		$("#msg_font").html('请输入工序点数!~');--%>
<%--		$("#procesdianshu_"+id).focus();--%>
<%--		bool= false;--%>
<%--	}else if(processjjMoney == ''){--%>
<%--		$("#msg_font").html('请输入工序单件计件工资!~');--%>
<%--		$("#processjjMoney_"+id).focus();--%>
<%--		bool= false;--%>
<%--	}else if(sjprocessMomey == ''){--%>
<%--		$("#msg_font").html('请输入实际单价!~');--%>
<%--		$("#sjprocessMomey_"+id).focus();--%>
<%--		bool= false;--%>
<%--	}--%>
	if(bool){
		$("#msg_font").html('');
		$.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!updatProcesPrice.action",
				dataType : "json",
				data : {
					'processTemplate.id':id,
					'processTemplate.procesdianshu':procesdianshu,
					'processTemplate.processjjMoney':processjjMoney,
					'processTemplate.jjratio':jjratio,
					'processTemplate.sjprocessMomey':sjprocessMomey,
					'processTemplate.ykprocessMomey':ykprocessMomey
				},
				success : function(data) {
					if(data=="true"){
						$(".process_price_"+id).attr("disabled","disabled");
						$("#xiugai_a_"+id).attr("onclick",'toupadteProcessPrice('+id+')');
						$("#xiugai_span_"+id).html('修改功序单价');
						$("#msg_font").html('修改成功!~');
					}else{
						$("#msg_font").html(data);
					}
				}
			});
	}
	
	
}

function tanchu(id,status){
	if(status=='xgpc'){
		$("#showProcess").attr("src","ProcardTemplateAction!showpcProcess.action?id=" + id);
	}else{
		$("#showProcess").attr("src","ProcardTemplateAction!showProUpdatePriceLog.action?ppul.processId=" + id);
	}
	chageDiv('block');
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
function delProCard(obj) {
	if (window.confirm('确定要删除本卡片吗?此操作将会删除该流水卡片下属的所有信息!')) {
		$(obj).attr("disabled","disabled");
		$(obj).val("正在删除中,请耐心等待....");
		
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!delProcard.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg) {
					alert("删除成功!");
					$(obj).val("删除本卡片");
					$(obj).removeAttr("disabled");
					showDiv();//页面内容清空
			$("#selectDiv").hide();
			loadTree();//重新加载树形

		} else {
			alert("删除失败!");
		}
	}
		});
	}
}

//精益计算
function jingyiJisuan() {
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!jingyiJisuan.action",
		dataType : "json",
		data : {
			id : $("#cardId").val()
		},
		success : function(msg) {
			alert(msg);
		}
	});
}

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
//显示工序工序关联外购件
function showWgProcard(id){
	$("#showProcess").attr("src",
			"ProcardTemplateAction!findwgProcard.action?id=" + id);
	chageDiv('block');
}
//显示工序图纸
function showProcessTz(id) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcessTz.action?id=" + id);
	chageDiv('block');

}
//申请特殊工序
function applySpecial(id,index) {
if (window.confirm('确定要将此工序申请为特殊工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!applySpecial.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg=="true") {
					$("#isSpecial"+index).empty();
					$("#isSpecial"+index).html("申请中");
				} else {
					alert(msg);
				}
			}
		});
	}
}
//显示工序质检；
function showProcessZJ(id,ProcessNO ){
	$("#showProcess").attr("src",
		"OsTemplate_addInput.action?id="+id+"&gongxuNum="+ProcessNO);


	chageDiv('block');
}
function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!deleteProcess.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();//显示该流水卡片已有工序
			$("#processNO").val(parseFloat($("#processNO").val()) + 5);
			$("#processName").val("");
			$("#processName").select();
		} else {
			alert(msg.message);
		}
	}
		});
	}
}

$(function() {
	getUnit("danwei2");
	getUnit("danwei3");
	getUnit("danwei4");
})
</script>

		<script language="javascript">
function initfz(obj1, obj2, num1, num2) {
	count_seach++;
	var shortname = document.getElementById(obj1);
	var showAll = document.getElementById(obj2);
	showAll.style.top = shortname.offsetTop + num1;
	showAll.style.left = shortname.offsetLeft + num2;
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllNames(obj1, obj2, obj3, obj4) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!getAllNames.action",
				dataType : "json",
				data : {
					markId : $("#" + obj1).val(),
					procardStyle : obj4
				},
				success : function(data) {
					$("#" + obj2).empty();
					$("#" + obj3).val(0);
					$(data)
							.each(
									function() {
										var markid = $(this).attr('markId')
												.replace(
														$("#" + obj1).val(),
														"<font color='red'>"
																+ $("#" + obj1)
																		.val()
																+ "</font>");
										$("#" + obj2)
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick=selectdiv(this,'"
																+ obj1
																+ "','"
																+ obj2
																+ "','"
																+ obj3
																+ "') align='left' data='"
																+ $(this).attr(
																		'id')
																+ "'>"
																+ markid
																+ ","
																+ $(this)
																		.attr(
																				'procardStyle')
																+ "<span style='display: none;'>"
																+ $(this)
																		.attr(
																				'markId')
																+ "</span></div>");
									});
				}
			});
}

function copyProcard(id, obj1, obj2) {
	if ($("#" + obj1).val() == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!copyProcard.action",
		dataType : "json",
		data : {
			id : $("#mfatherId").val(),
			id2 : $("#" + obj2).val()
		},
		success : function(data) {
			if (data.success) {
				loadTree();
			}
			if (data.message != null) {
				alert(data.message);

			}

		}
	});
}
function updateProcard(obj) {
	if (!window.confirm("您将更新所有同件号的模板和状态为初始或者已发卡的流水卡,是否继续更新！")) {
		return false;
	}
	var id = $("#uId").val();
	if (id == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$(obj).attr("disabled", "disabled");
	$(obj).val("更新中....");
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateProcard.action",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if (data.message != null) {
				alert(data.message);
				$(obj).removeAttr("disabled");
				$(obj).val("更新流水卡");
			}
		}
	});
}
/**
 * 显示添加工序弹框
 */
function toAddProcess() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!toAddProcess.action?id=" + id);
	chageDiv('block');
}
function deleteAllProcess() {
	var id = $("#cardId").val();
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!deleteAllProcess.action",
				data : {
					id : id
				},
				dataType : "json",
				success : function(msg) {
					if (msg.success) {
						alert("已清空!");
						$("#ProcessTab").empty();
						$("#ProcessTab")
								.append(
										"<tr><th colspan='8'>已有工序(<a onclick='toAddProcess()'>添加工序</a>)(<a onclick='deleteAllProcess()'>清空工序</a>)</th></tr>"
												+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th><th>特殊工序</th>"
												+ "<th>是否并行</th><th>是否首检</th><th>操作</th></tr>");
					} else {
						alert(msg.message);
					}
				}
			});
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
function updateMarkId() {
	var oldMarkId = $("#oldMarkId").html();
	var newMarkId = $("#newMarkId").val();
	if (newMarkId == null || newMarkId == "") {
		alert("请输入新件号");
		return false;
	}
	if (oldMarkId == newMarkId) {
		alert("原新件号请不要一致！");
		return false;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateMarkId.action",
		dataType : "json",
		data : {
			id : $("#cardId").val(),
			markId : newMarkId
		},
		success : function(data) {
			alert(data.message);
			if (data.success) {
				loadTree();
				$("#oldMarkId").empty();
				$("#oldMarkId").append(newMarkId);
				$("#showCardTemplate").show();
				$("#showCardIframe").attr(
						"src",
						"ProcardTemplateAction!findCardTemForShow.action?id="
								+ $("#cardId").val());
			}
		}
	})
}
function changeTolp() {
	var lpMarkId = $("#lpMarkId").val();
	$("#dd9").show();
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!changeTolp.action",
		dataType : "json",
		data : {
			id : $("#cardId").val(),
			markId : lpMarkId
		},
		success : function(data) {
			alert(data.message);
			$("#dd9").hide();
			loadTree();
			$("#oldMarkId").empty();
			$("#oldMarkId").append(newMarkId);
			$("#showCardTemplate").show();
			$("#showCardIframe").attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ $("#cardId").val());
		}
	})
}
function showSonCard() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showSonCard.action?id=" + id);
	chageDiv('block');

}
</script>
		<!-- 外购件原材料模糊搜索js -->
		<SCRIPT type="text/javascript">
		function hidediv(allname) {
	count_seach--;
	if (count_seach == 0) {
		var showAll = document.getElementById(allname);
		showAll.style.visibility = "hidden";
	}

}

function init(shortName,allname) {
	count_seach++;
	var shortnamewgj = document.getElementById(shortName);
	var showAllwgj = document.getElementById(allname);
	showAllwgj.style.top = getTop(shortnamewgj) + 20;
	showAllwgj.style.left = getLeft(shortnamewgj);
	showAllwgj.style.visibility = "visible";
}
function selectdiv(obj,obj1,obj2,obj3){
	var html=$(obj).find("span").html();
	$("#"+obj1).val(html);
	var data = $(obj).attr('data');
	$("#"+obj3).val(data);
	var showAll=document.getElementById(obj2); 
	   showAll.style.visibility = "hidden";
	   if(obj3!="shortnameId"){
		   showProcardTemplate(data,obj1)
	   }
	   if(obj1 == "zuhemarkId"){
		   $("#zuhemarkIddiv").html('<input type="button" value="复制" onclick=copyProcard("${param.id}","'+obj1+'","'+obj3+'")>');
	   }else if(obj1 == "zizmarkId"){
		   $("#zizmarkIddiv").html('<input type="button" value="复制" onclick=copyProcard("${param.id}","'+obj1+'","'+obj3+'")>');
	   }
}
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
				$("#"+obj+"wgType").val(pt.wgType);
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
function getAllNameswgj() {
	if($("#shortnamewgj").val()==null||$("#shortnamewgj").val()==""){
		$("#showAllwgj").empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.markId' : $("#shortnamewgj").val(),
					'yuanclAndWaigj.clClass' : '外购件'
				},
				success : function(data) {
					$("#showAllwgj").empty();
					$(data).each(
									function() {
										var markId = $(this)
												.attr('markId')
												.replace(
														$("#shortnamewgj").val(),
														"<font color='red'>"
																+ $("#shortnamewgj").val()
																+ "</font>");
										$("#showAllwgj")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdivwgj(this)' align='left'>"
																+ markId
																+ ":"
																+ $(this).attr('name')
																+":"
																+$(this).attr('unit')
																+"<span style='visibility: hidden;'>"
																+ $(this).attr('markId')
																+ ":"
																+ $(this).attr('name')
																+":"
																+$(this).attr('unit')
																+"</span>"
																+ "</div>");
									});
				}
			});
}
function selectdivwgj(obj){
	var html=$(obj).find("span").html();
	var showAllwgj=document.getElementById("showAllwgj"); 
	showAllwgj.style.visibility = "hidden";
	var htmls=html.split(":");
	$("#shortnamewgj").val(html);
	$("#wajmarkId").val(htmls[0]);
	$("#wajproName").val(htmls[1]);
	$("#danwei2").val(htmls[2]);
}
		
//ajax获取所有的类似的全称
function getAllNamesycl() {
	if($("#shortnameycl").val()==null||$("#shortnameycl").val()==""){
		$("#showAllycl").empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#shortnameycl").val(),
					'yuanclAndWaigj.clClass' : '原材料'
				},
				success : function(data) {
					$("#showAllycl").empty();
					$(data).each(
									function() {
										var trademark = $(this)
												.attr('trademark')
												.replace(
														$("#shortnameycl").val(),
														"<font color='red'>"
																+ $("#shortnameycl").val()
																+ "</font>");
										$("#showAllycl")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdivycl(this)' align='left'>"
																+ trademark
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"<span style='visibility: hidden;'>"
																+ $(this).attr('trademark')
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"</span>"
																+ "</div>");
									});
				}
			});
}

function selectdivycl(obj){
	var html=$(obj).find("span").html();;
	var showAll=document.getElementById("showAllycl"); 
	showAll.style.visibility = "hidden";
	var htmls=html.split(":");
	$("#shortnameycl").val(html);
	$("#trademark").val(htmls[0]);
	$("#specification").val(htmls[1]);
	$("#unit4").val(htmls[2]);
}
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
function getGyPeople(type){
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_getGyPeople.action?tag="+type,
				dataType : "json",
				success : function(data) {
					$(data).each(
									function() {
										if(type=="bz"){
										    $(".bianzhi")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="jd"){
											$(".jiaodui")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="sh"){
											$(".shenhe")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="pz"){
											$(".pizhun")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
											
										}
									});
				}
			});
}
$(document).ready(function(){
	getUnit("zuheunit");
	$(".bianzhi").append("<option value='${Users.id}'>${Users.name}</option>");
	getGyPeople("bz");
	getGyPeople("jd");
	getGyPeople("sh");
	getGyPeople("pz");
})
function reviewBom(){
	window.open("procardTemplateGyAction_reviewBom.action?id=${param.id}");  
}
function showCardTz(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showCardTz.action?id=" + id);
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
function checkAndUpdateTz(){
		$("#module1_12").show();
}
function tzdr(){
	if(window.confirm("是否导入工艺图纸内容!")){
		$("#module1_12_son").show();
		$("#drh3").html("<font color='red'>正在导入图纸请耐心等待.............</font>");
		$("#drh3").show();
		$("#tzdrdbtn").attr("disabled","disabled");
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_checkAndUpdateTz.action",
				data : {
					id:${param.id}
				},
				dataType : "json",
				success : function(data) {
					$("#drh3").html("");
					$("#drh3").html(data);
					$("#jdh3").hide();
					$("#jdh4").hide();
					$("#tzdrdbtn").removeAttr("disabled");
				}
			});
	}
}
function tzjd(){
	if(window.confirm("是否工艺图纸名称加点!")){
		$("#module1_12_son").show();
		$("#jdh3").html("<font color='red'>正在一键加点,请耐心等待.............</font>");
		$("#jdh3").show();
		$("#tzjdbtn").attr("disabled","disabled");
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_updateUnuploadTzname.action",
				data : {
					id:${param.id}
				},
				dataType : "json",
				success : function(data) {
					$("#jdh3").html("");
					$("#jdh3").html(data);
					$("#drh3").hide();
					$("#jdh4").hide();
					$("#tzjdbtn").removeAttr("disabled");
				}
			});
	}
}
function tzgx(){
	if(window.confirm("是否一键导入工序?")){
		$("#module1_12_son").show();
		$("#jdh4").show();
		$("#jdh4").html("<font color='red'>正在一键导入工序,请耐心等待.............</font>");
		$("#gxjdbtn").attr("disabled","disabled");
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_daoRuProcessTempalte.action",
				data : {
					id:${param.id}
				},
				dataType : "json",
				success : function(data) {
					$("#jdh4").html("");
					$("#jdh4").html(data);
					$("#drh3").hide();
					$("#jdh3").hide();
					$("#gxjdbtn").removeAttr("disabled");
				}
			});
	}
}
function moveStatus(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_moveStatus.action?id="+id);
	chageDiv('block');
}
function BomTreeSpdt(){
	var id=$("#epId2").val();
	$("#showProcess").attr("src",
			"CircuitRunAction_findAduitPage.action?id="+id);
	chageDiv('block');
}
function BomTreeSpzp(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_tozhipaiBOmTree.action?id="+id);
	chageDiv('block');
}
function applyBomTree(){
	if(confirm("您将申请BOM结构审批是否继续?")){
	var id=$("#cardId").val();
	$("#module15").attr("disabled","disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_applyBomTree.action",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});
	}
}
function applySb(){
	if(confirm("您将申请设变是否继续?")){
	var id=$("#cardId").val();
	//$("#module14").attr("disabled","disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_applySb.action",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});
	}
}

function jisuanjjration(id){
	var sjprocessMomey =$("#sjprocessMomey_"+id).val();
	var processjjMoney =$("#processjjMoney_"+id).val();
	if(sjprocessMomey !='' && processjjMoney!='' && processjjMoney!=0){
		$('#jjratio_'+id).val((sjprocessMomey/processjjMoney).toFixed(4));
		$('#ykprocessMomey_'+id).val((processjjMoney-sjprocessMomey).toFixed(4));
	}
}

<%--function yinshen(obj){--%>
<%--	alert($("#"+obj).attr("display"));--%>
<%--}--%>
	
		</SCRIPT>
	</body>
</html>
