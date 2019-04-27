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
		<script type="text/javascript">
$(function() {
	var pageStatus = "${pageStatus}";
	if (pageStatus == "zjl" || pageStatus == "sc") {

		var size = parseFloat("${size}");
		var manage = $("#manage");
		var laborcostLabel = $("#laborcostLabel");
		var fenpeiRateLabel = $("#fenpeiRateLabel");
		if (pageStatus != "" && size > 0) {
			for ( var i = 0; i < size; i++) {
				$("#manage" + i).show();
				//市场,隐藏可调系数
				if (pageStatus == "sc") {
					$("#fenpeiRateLabel" + i).hide();
				}
			}
		}
	}
});

function update(obj, index, id, name, oldValue) {
	var laborcost = $("#laborcost" + index).val();
	var fenpeiRate = $("#fenpeiRate" + index).val();
	if (laborcost == "") {
		laborcost = 0;
	}
	if (fenpeiRate == "") {
		fenpeiRate = 0;
	}
	//判断值是否相同
	if (parseFloat(obj.value) == parseFloat(oldValue)) {
		$("#showMessage").html("");
		return false;
	}

	if (fenpeiRate > 1) {
		$("#showMessage").html("可调系数不能大于1,请重新填写!");
		$("#fenpeiRate" + index).select();
		return false;
	}

	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updatelf.action",
		data : {
			id : id,
			laborcost : laborcost,
			fenpeiRate : fenpeiRate
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				$("#showMessage").html(name + "更改成功");
			} else {
				$("#showMessage").html(name + "更改失败,请重新更改!");

			}
		}
	});
}
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
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
			</div>
		</div>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
				<div id="module1_4"
					style="overflow-y: visible; background-color: #ffffff; width: 1200px"
					align="center">
					<div>
					<form action="ProcardTemplateAction!addProcessTemplate.action"method="post" onsubmit="return checkForm();">
					
						<input type="hidden" name="id2" value="${id}"/>
						<input id="tpid" type="hidden" name="id" value=""/>
						<table id="ProcessTab" class="table" style="width: 100%;">
						</table>
						</form>
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
						<input type="hidden" name="processTemplate.processMomey" value="0" />
						<input type="hidden" name="processTemplate.opjiaofu" value="0" />
					</form>
				</div>

				<div id="operatingDiv"
					style="background-color: #ffffff; width: 1200px;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder; width: 100%;">
					</div>
					<table class="table" id="divs"
						style="table-layout: fixed; width: 100%;word-break:break-all;">
						<tr bgcolor="#c0dcf2" height="50px" style="width: 100%">
							<th align="center" width="5%">
								序号
							</th>
							<th align="center" width="5%">
								层次
							</th>
							<th align="center" width="8%">
								名称
							</th>
							<th align="center" width="10%">
								规格
							</th>
							<th align="center" width="5%">
								卡片类型
							</th>
							<th align="center" width="10%">
								件号
							</th>
							<th align="center" width="10%">
								图号
							</th>
							<th align="center" width="5%">
								单位
							</th>
							<th align="center" width="5%">
								单位用量
							</th>
							<th align="center" style="width: 30%">
								工序
							</th>
							<th align="center" width="5%">
								生产类型
							</th>
							<th align="center" width="8%">
								工序管理
							</th>
						</tr>
						<s:set name="myindex" value="0"></s:set>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr id='trdiv${pageindex.index+1}' align="center"
									bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr id='trdiv${pageindex.index+1}' align="center"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1+#myindex" />
							</td>
							<td align="left">
								<s:iterator begin="1" end="#pageProcardTem.belongLayer" step="1">
									<font size="1"><b>.</b> </font>
								</s:iterator>
								<s:property value="#pageProcardTem.belongLayer" />
							</td>
							<td align="center">
								${pageProcardTem.proName}
							</td>
							<td align="center">
								${pageProcardTem.specification}
							</td>
							<td align="center">
								${pageProcardTem.procardStyle}
							</td>
							<td align="center">
								<a href="javascript:;"
									onclick="toshowPro('${pageProcardTem.id}')">
									${pageProcardTem.markId} </a>
							</td>
							<td align="center">
								${pageProcardTem.tuhao}
							</td>
							<td align="center">
								${pageProcardTem.unit}
							</td>
							<td align="center">
								<s:if test="pageProcardTem.procardStyle=='外购'">
									${pageProcardTem.quanzi2/pageProcardTem.quanzi1}
								</s:if>
								<s:else>
									${pageProcardTem.corrCount}
								</s:else>
							</td>
							<td align="center">
								<s:iterator value="processStringList[#pageindex.index]"
									id="processlist" status="processindex">
									<s:property />
									<%--<s:property value="#pageindex.index+1+#myindex" />--%>
								</s:iterator>
							</td>
							<td align="center">
								${pageProcardTem.productStyle}
							</td>
							<td>
								<input id="module4" type="button" value="工序管理"
									onclick="chageModule(this,'4');showProcess('${pageProcardTem.id}');changeprocess('${pageProcardTem.id}','${pageindex.index}');" />
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="14" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="14" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>

				</div>
			</div>
			<br>
		</div>


		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toshowPro(id) {
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!findCardTemForShow.action?id=" + id);
<%--	chageDiv('block');--%>
}
function showDaoRuDiv() {
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_toDaoRuBom.action");
<%--	chageDiv('block');--%>
}
function exportExcel(id) {
	window.location.href = "procardTemplateGyAction_daoChuBom.action?id=" + id;
}
function showgongxu(parth, id) {
	window.open(parth + "System/SOP/produce/procard_showgongxu.jsp?id=" + id);
}

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

//修改外购件是否需要工序
function changeneedProcess(obj) {
	if (obj == "yes") {
		$("#safetr").show();
		$("#lasttr").show();
	} else {
		$("#safetr").hide();
		$("#lasttr").hide();
	}

}

//显示卡片已有工序
function showProcess(tid) {
$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!findProcessByFkId.action",
				dataType : "json",
				data : {
					id : tid
<%--						$("#cardId").val()--%>
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='10'>已有工序(<a onclick='toAddProcess()'>添加/插入工序</a>)(<a onclick='deleteAllProcess()'>清空工序</a>)</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th><th>特殊工序</th>"
											+ "<th>是否并行</th><th>是否首检</th><th>添加人</th><th>添加时间</th><th>操作</th></tr>");
					
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
															+ ','+i+');\'>申请多工序并行</a>/';
										}
										isSpecial = "<label id='isSpecial"+i+"'>"+isSpecial+"</label>"
										var zjp = parseFloat($(
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
																						'gzzhunbeicishu'));
										zjp =	Math.round(zjp*10000)/10000;
										var html='<tr align="center"  onmouseover=chageBgcolor(this)  onmouseout=outBgcolor(this,"#FFFFFF") ><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+zjp
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
																+ $(this)
																		.attr(
																				'addUser')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'addTime')
																+ '</td><td>' 
																+applySpecial 
																+'<a href="javascript:;" onclick="showProcessTz('
																+ $(this).attr(
																		'id')
																+ ');">图纸</a>/<a href="javascript:;" onclick="showProcessForSb('
																+ $(this).attr(
																		'id')//  onmouseout="outBgcolor(this,'#e6f3fb')" onmouseover="alert(000000000)"
																+ ');">修改</a>/<a href="javascript:;" onclick= "showWgProcard('+$(this).attr('id')+');">关联外购件</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a></td></tr>';
										$("#ProcessTab")
												.append(html);
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
					var addhtml="<tr id='tradd'><td colspan=7 ></td><td style='text-align: center;'><a onclick='tabletoAddProcess()'>添加工序</a></td><tr>"
					$("#ProcessTab").append(addhtml);
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
//显示工序详细
function showProcessForSb(id,ProcessNO ) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcess.action?id=" + id);
	window.document.getElementById("operatingDiv").style.display='';
	changeprocess2(id);
<%--	chageDiv('block');--%>
}
//显示工序工序关联外购件
function showWgProcard(id){
	$("#showProcess").attr("src",
			"ProcardTemplateAction!findwgProcard.action?id=" + id);
	window.document.getElementById("operatingDiv").style.display='';
	changeprocess2(id);
<%--	chageDiv('block');--%>
}
//显示工序图纸
function showProcessTz(id) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcessTz.action?id=" + id);
	window.document.getElementById("operatingDiv").style.display='';
	changeprocess2(id);
<%--	chageDiv('block');--%>

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

/**
 * 显示添加工序弹框
 */
function toAddProcess() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
<%--			"ProcardTemplateAction!toAddProcess.action?id=" + id);--%>
			"ProcardTemplateAction!toAddProcessDefaultjiepai.action?id=" + id);
<%--	chageDiv('block');--%>
	changeprocess2();
	$("#operatingDiv").show();
}

//已有工序表格添加
function tabletoAddProcess(){
	 $("#tradd").remove();
		var addhtml="<tr style='text-align: center;' ><td><input type='text' name='processTemplate.processNO' value=''></td>" +
						  "<td><select id='processName' name='processTemplate.processName'style='width: 150px;' onchange='getMachineBygongxu();'></select></td>" +
						  "<td></td>" +
						  "<td><select name='processTemplate.productStyle' id='productStyle'><option >自制</option><option >外委</option></select></td>" +
						  "<td>普通</td>" +
						  "<td><select id='processStatus' name='processTemplate.processStatus'><option value='yes' >并行</option><option value='no' >不并行</option></select></td>" +
						  "<td><select id='zjStatus' name='processTemplate.zjStatus' ><option value='yes'>yes</option><option value='no'>no</option></select></td>" +
						  "<td ><input id='addBtn' type='submit' class='input' value='添加'>" +
						  "<select style='display:none' name='processTemplate.isJisuan' class='yesOrNo2' id='isJisuan'><option value='yes'>是</option><option value='no'>否</option></select>"+
						  "<select style='display:none' name='processTemplate.isPrice' id='isPrice'><option value='yes'>参与</option><option value='no'>不参与</option></select>"+
						  "<select style='display:none' name='processTemplate.kaoqingstatus' class='yesOrNo' id='kaoqingstatus'><option value='是'>是</option><option value='否'>否</option></select>"+
						  "<select style='display:none' name='processTemplate.guifanstatus' id='guifanstatus'><option value='是'>是</option><option value='否'>否</option></select>" +
						  "<select style='display:none' name='processTemplate.liangjustatus' class='yesOrNo' id='liangjustatus'><option value='是'>是</option><option value='否'>否</option></select>" +
						  "<select style='display:none' name='processTemplate.gongzhuangstatus' class='yesOrNo' id='gongzhuangstatus'><option value='是'>是</option><option value='否'>否</option></select>" +
						  "<select style='display:none' name='processTemplate.shebeistatus' class='yesOrNo' id='shebeistatus'><option value='是'>是</option><option value='否'>否</option></select>" +
						  "</td></tr>";
	$("#ProcessTab").append(addhtml);
	getProcess();
	getMachineBygongxu('yes');
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
							"<option value='" + n.processName + "'>"
									+ n.processName + "</option>");
				});
				$("#processName").tinyselect();
			} else {
				alert(msg.message);
			}
		}
	});
}

//通过工序查询设备信息
function getMachineBygongxu(isload) {
	var processName = $("#processName").val();
	if(processName!=null && processName!=""){
		findProcessByName(processName);
	}
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getMachineBygongxu.action",
		dataType : "json",
		data : {
			matetag : processName
		},
		success : function(object) {
			
			if (isload != 'yes') {
				$("#shebeiNo").empty();
			}
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj.no + "'>" + obj.no + "、"
						+ obj.workPosition + "、" + obj.name + "</option>";
			});
			$(bj).appendTo("#shebeiNo")
		}
	});

}
function findProcessByName(processName){
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_findProcessByName.action",
		dataType : "json",
		data : {
			processName : processName
		},
		success : function(pro) {
			if(pro.shebeistatus!=null){
				$("#shebeistatus").val(pro.shebeistatus);
			}else{
				$("#shebeistatus").val("是");
			}
			if(pro.gongzhuangstatus!=null){
				$("#gongzhuangstatus").val(pro.gongzhuangstatus);
			}else{
				$("#gongzhuangstatus").val("是");
			}
			if(pro.liangjustatus!=null){
				$("#liangjustatus").val(pro.liangjustatus);
			}else{
				$("#liangjustatus").val("是");
			}
			if(pro.guifanstatus!=null){
				$("#guifanstatus").val(pro.guifanstatus);
			}else{
				$("#guifanstatus").val("是");
			}
			if(pro.kaoqingstatus!=null){
				$("#kaoqingstatus").val(pro.kaoqingstatus);
			}else{
				$("#kaoqingstatus").val("是");
			}
			if(pro.isPrice!=null){
				$("#isPrice").val(pro.isPrice);
			}else{
				$("#isPrice").val("参与");
			}
			if(pro.zjStatus!=null){
				$("#zjStatus").val(pro.zjStatus);
			}else{
				$("#zjStatus").val(pro.zjStatus);
			}
			if(pro.isJisuan!=null){
				$("#isJisuan").val(pro.isJisuan);
			}else{
				$("#isJisuan").val("yes");
			}
			if(pro.productStyle!=null){
				$("#productStyle").val(pro.productStyle);
			}else{
				$("#productStyle").val("自制");
			}
			
			
			if(pro.processStatus!=null){
				$("#processStatus").val(pro.processStatus);
			}else{
				$("#processStatus").val("yes");
			}
			
		}
	});
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

$(window).load(function (){
	window.document.getElementById("operatingDiv").style.display='none';
	$("#module1_4").hide();
}); 

//module1_4添加显示至tr下
var tr="";
function changeprocess(id,index){
		var oDivs=document.getElementById('divs');
<%--		var oDiv=document.getElementById("operatingDiv");--%>
		var oDiv2=document.getElementById("module1_4");
        var nDiv=document.createElement('div');
        var num =parseInt(index) + 1;
        tr="trdiv"+num;
        var test=document.getElementById(tr);
		test.after(oDiv2);
		$("#cardId").val(id);
		
		$("#tpid").val(id);
		
		
	if(window.document.getElementById("operatingDiv").style.display!='none'){
		$("#operatingDiv").hide();
	}
}
 
//operatingDiv显示
function changeprocess2(id){
		var oDivs=document.getElementById('divs');
		var oDiv=document.getElementById("operatingDiv");
<%--        var test=document.getElementById("module1_4");--%>
        var test=document.getElementById(tr);
		test.after(oDiv);
		$("#cardId").val(id);
	
}

function checkForm(){
	$("#addBtn").attr("disabled","disabled");
}



</script>
	</body>
</html>
