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
							<span id="title">您正在查看工序信息</span>
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
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加工艺BOM -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
					<div id="selectDiv" style="display: none;" align="center">
						<input id="module1" type="button" value="添加组合件"
							onclick="chageModule(this,'1')"
							style="width: 80px; height: 50px;" />
						<input id="module2" type="button" value="添加外购件"
							onclick="chageModule(this,'2')"
							style="width: 80px; height: 50px;" />
						<input id="module3" type="button" value="添加自制件"
							onclick="chageModule(this,'3')"
							style="width: 80px; height: 50px;" />
						<input id="module4" type="button" value="添加工序"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 80px; height: 50px;" />
						<input type="button" value="删除" onclick="delProCard()"
							style="width: 80px; height: 50px;" />
						<br />
					</div>
					<div id="module1_1" style="display: none;">
						<form id="lingForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="wrootId" name="quotedPrice.rootId" />
							<input type="hidden" id="wfatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="wbelongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加组合件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="zxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.corrCount" value="1">
										( 对应上层所需数量,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="组合"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" style="width: 155px;">
											<option value="件">
												件
											</option>
											<option value="支">
												支
											</option>
											<option value="吨">
												吨
											</option>
											<option value="公斤">
												公斤
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
											<option value="批产">
												批产
											</option>
											<option value="试制">
												试制
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="quotedPrice.remark">
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('lingForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_2" style="display: none;">
						<form id="waiForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="rootId" name="quotedPrice.rootId" />
							<input type="hidden" id="fatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="belongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加外购件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="wxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="外购"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" style="width: 155px;">
											<option value="件">
												件
											</option>
											<option value="支">
												支
											</option>
											<option value="吨">
												吨
											</option>
											<option value="公斤">
												公斤
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.quanzi1" style="width: 71px;"
											value="1" />
										:
										<input name="quotedPrice.quanzi2" style="width: 71px;"
											value="1" />
										(自制件:原材料,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
											<option value="批产">
												批产
											</option>
											<option value="试制">
												试制
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="quotedPrice.remark">
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('waiForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_3" style="display: none;">
						<form id="yuanForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="yrootId" name="quotedPrice.rootId" />
							<input type="hidden" id="yfatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="ybelongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加自制件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="yxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="quotedPrice.corrCount" value="1">
										(权值,对应上层所需数量,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="quotedPrice.remark">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" style="width: 155px;">
											<option value="件">
												件
											</option>
											<option value="支">
												支
											</option>
											<option value="吨">
												吨
											</option>
											<option value="公斤">
												公斤
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="自制"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
											<option value="批产">
												批产
											</option>
											<option value="试制">
												试制
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th colspan="2">
										原材料信息
									</th>
								</tr>
								<tr>
									<th align="right">
										牌号:
									</th>
									<td>
										<input name="quotedPrice.trademark">
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input name="quotedPrice.specification">
									</td>
								</tr>
								<tr>
									<th align="right">
										材料消耗:
									</th>
									<td>
										<input name="quotedPrice.materialXh">
										(指每件零件的单个消耗量)
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.yuanUnit" style="width: 155px;">
											<option value="件">
												件
											</option>
											<option value="支">
												支
											</option>
											<option value="吨">
												吨
											</option>
											<option value="公斤">
												公斤
											</option>
											<option value="块">
												块
											</option>
											<option value="个">
												个
											</option>
											<option value="根">
												根
											</option>
											<option value="kg">
												kg
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.quanzi1" style="width: 71px;" />
										:
										<input name="quotedPrice.quanzi2" style="width: 71px;" />
										(自制件:原材料,格式如1:1)
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('yuanForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_4" style="display: none;" align="center">
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
							<input id="gxingbie" type="hidden" />
							<table class="table" style="width: 100%">
								<tr>
									<th colspan="8" align="center">
										添加工序
									</th>
								</tr>
								<tr>
									<td align="right">
										工序号:
									</td>
									<td>
										<input id="processNO" name="qpInfor.processNO" value="5" />
									</td>
									<td align="right">
										名 称:
									</td>
									<td>
										<input id="processName" name="qpInfor.processName"
											onblur="getMachine(this.value)" />
									</td>
								</tr>
								<tr>
									<td align="right">
										设备:
									</td>
									<td>
										<select id="shebeiNo" name="qpInfor.shebeiId"
											style="width: 150px;">
											<option></option>
										</select>
									</td>
									<td align="right">
										设备时长:
									</td>
									<td>
										<input id="shebeiDateTime" name="qpInfor.shebeiDateTime" />
										(秒)
									</td>
								</tr>
								<tr>
									<td align="right">
										工装:
									</td>
									<td>
										<select id="gongzhuang" name="qpInfor.gongzhuangId"
											style="width: 150px;">
											<option></option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<input type="submit" value="添加"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="showCardTemplate" style="display: none; height: 100%;">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},

	callback : {
		onClick : onClick
	}
};
//加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$.ajax( {
		url : 'QuotedPrice_findAllForTree.action',
		type : 'post',
		dataType : 'json',
		data : {
			id : '${param.id}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStyle'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							xingbie : $(this).attr('xingbie'),
							name : $(this).attr('proName') + ' '
									+ $(this).attr('markId') + ' '
									+ $(this).attr('procardStyle'),
							click : false,
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

var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2) {
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}
	$("#module1_" + obj2).show('slow');
	oldObj = obj;
	oldObj2 = obj2;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	//工序赋值
	$("#cardId").val(treeNode.id);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	//工序赋值
	$("#gxingbie").val(treeNode.xingbie);
	if (proStruts == "总成" || proStruts == "组合") {
		//零组件赋值
		$("#rootId").val(treeNode.rootId);
		$("#fatherId").val(treeNode.id);
		$("#belongLayer").val(treeNode.belongLayer + 1);
		$("#zxingbie").val(treeNode.xingbie);
		//外购件赋值
		$("#wrootId").val(treeNode.rootId);
		$("#wfatherId").val(treeNode.id);
		$("#wbelongLayer").val(treeNode.belongLayer + 1);
		$("#wxingbie").val(treeNode.xingbie);
		//原材料赋值
		$("#yrootId").val(treeNode.rootId);
		$("#yfatherId").val(treeNode.id);
		$("#ybelongLayer").val(treeNode.belongLayer + 1);
		$("#yxingbie").val(treeNode.xingbie);
		//全部可用
		$("#module1").attr("disabled", false);
		$("#module2").attr("disabled", false);
		$("#module3").attr("disabled", false);
		$("#module4").attr("disabled", false);
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr(
			"src",
			"QuotedPrice_findQpDetailForShow.action?pageStatus=${pageStatus}&id="
					+ treeNode.id);
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	$.ajax( {
		type : "POST",
		url : "QuotedPrice_addQuotedPriceTree.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(msg) {
			if (msg) {
				alert("添加成功!");
				loadTree();//重新加载树形
	} else {
		alert("添加失败!");
	}
}
	});
}

//显示卡片已有工序
function showProcess() {
	$
			.ajax( {
				type : "POST",
				url : "QuotedPrice_findQpInforByFkid.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='7'>已有工序</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>设备</th><th>工装</th><th>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					$(msg)
							.each(
									function() {
										$("#ProcessTab")
												.append(
														'<tr align="center"><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td><a href="javascript:;" onclick="deleteProcess('
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
									});
					$("#processNO").val(maxProcessNO);//计算下一个工序号是多少，方便填写
					$("#processName").select();
					$("#gongzhuang").empty();//清空设备列表
					getGz();//加载工装
				}
			});
}
//添加工序
function submitForm2(formId) {
	if ($("#processName").val() == "") {
		alert("请填写工序名称!");
		$("#processName").select();
	} else if ($("#shebeiNo").val() != "" && $("#shebeiDateTime").val() == "") {
		alert("请填写设备时长!");
		$("#shebeiDateTime").select();
	} else {
		if ($("#processStatus").val() == "no") {
			$("#parallelId").val("");
		}
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_addQpInfor.action",
			dataType : "json",
			data : $("#" + formId).serialize(),
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();
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

//删除工艺规范
function delProCard() {
	if (window.confirm('确定要删除件号吗?此操作将会删除该件号下的所有信息!')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQuotedPriceTree.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showDiv();
					$("#selectDiv").hide();
					loadTree();
				} else {
					alert(msg.message);
				}
			}
		});
	}
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
function showProcessForSb(id) {
	$("#showProcess").attr("src", "QuotedPrice_showProcess.action?id=" + id);
	chageDiv('block');

}

function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQpInfor.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();
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
//通过工序名称查询设备
function getMachine(processName) {
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getMachineListByGongxuName.action",
		dataType : "json",
		data : {
			processName : processName
		},
		success : function(msg) {
			if (msg.success) {
				$("#shebeiNo").empty();
				$("#shebeiNo").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#shebeiNo").append(
							"<option value='" + n.id + "'>" + n.name + "("
									+ n.no + ")</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}
//通过型别查询工装信息
function getGz() {
	var gxingbie = $("#gxingbie").val();
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getGzstoreListByXingbie.action?gzstore.xingbie="
				+ gxingbie,
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				$("#gongzhuang").empty();
				$("#gongzhuang").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#gongzhuang").append(
							"<option value='" + n.id + "'>" + n.name + "("
									+ n.no + ")</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}
</script>
	</body>
</html>
