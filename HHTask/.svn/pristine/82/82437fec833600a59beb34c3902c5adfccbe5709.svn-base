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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${procardGys.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"){
					$("#showZong").show();
					$("#process").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
					$("#process").show();
				}else if(procardStyle=="外购"){
					$("#showWai").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative;background: url(<%=basePath%>/images/bq_bg2.gif); width:100%;">
				<div id="zjProcessDiv" style="display: none;">
					<iframe id="showZjProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 700px; margin: 0px; padding: 0px;"></iframe>
				</div>
				<div id="collorProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在领取工序:
								<input type="text" />
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form id="collorProcess" style="margin: 0px; padding: 0px;">
							<table id="ProcessTab" class="table">
							</table>
						</form>
					</div>
				</div>
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在提交工序:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form id="submitProcess">
							<input id="submitProId" type="hidden" name="process.id" />
							<input type="hidden" name="id" value="${procardGys.id}" />
							<table class="table" style="width: 40%">
								<tr>
									<th colspan="2">
										提交工序
									</th>
								</tr>
								<tr>
									<th>
										提交数量:
									</th>
									<th align="left">
										<input id="subNumber" name="process.submmitCount" />
										最大可提交数量
									</th>
								</tr>
								<tr>
									<th>
										不合格数量:
									</th>
									<th align="left">
										<input name="process.breakCount" value="0" />
									</th>
								</tr>
								<tr>
									<th colspan="2">
										<input type="button" value="提交" onclick='submitForm()'
											style="width: 80px; height: 50px;" />
									</th>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div id="aginPrintDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在补打工序提交单:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form id="aginPrintForm">
							<input id="printProId" type="hidden" name="process.id" />
							<input type="hidden" name="id" value="${procardGys.id}" />
							<table class="table" style="width: 40%">
								<tr>
									<th colspan="2">
										打印工序提交单
									</th>
								</tr>
								<tr>
									<th>
										打印数量:
									</th>
									<th align="left">
										<input id="printNumber" name="process.submmitCount" />
									</th>
								</tr>
								<tr>
									<th colspan="2">
										<input type="button" value="提交" onclick='aginPrintForm()'
											style="width: 80px; height: 50px;" />
									</th>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="printDiv" style="display: none; width: 100%">
			<div id="printgxDiv" style="width: 100%; padding: 0px; margin: 0px;">
				<table class="table"
					style="width: 260px; padding: 0px; margin: 0px; font-weight: bolder;">
					<tr>
						<th colspan="2">
							提交工序
						</th>
					</tr>
					<tr>
						<th align="right">
							件号:
						</th>
						<td>
							<div id="printMarkId"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							批次:
						</th>
						<td>
							<div id="printSelfCard"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							工序号:
						</th>
						<td>
							<div id="printProcessNO"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							工序名称:
						</th>
						<td>
							<div id="printProcessName"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交数量:
						</th>
						<td>
							<div id="printSubmmitCount"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交人:
						</th>
						<td>
							<div id="printUsernames"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交时间:
						</th>
						<td>
							<div id="printSubmitDate"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="showZong" style="border: solid #000 1px; display: none;">
			<div align="center"
				style="border-bottom: solid #000 1px; font-weight: bolder;">
				工 艺 流 水 卡 片
			</div>
			<div style="font-weight: bolder;" align="center">
				名称:${procardGys.proName} &nbsp;&nbsp;件号:
				<font color="red"> ${procardGys.markId}</font>&nbsp;&nbsp;
				批次:${procardGys.selfCard} &nbsp;&nbsp; 卡片类型:
				<font color="red">${procardGys.procardStyle}</font>&nbsp;&nbsp; 产品类型:
				${procardGys.productStyle} 车型:${procardGys.carStyle}&nbsp;&nbsp; 状态:
				<font color="red">${procardGys.status}</font>&nbsp;&nbsp;
			</div>
			<table class="table" style="width: 100%;">
				<tr>
					<th>
						最大数量 : ${procardGys.maxCount} ${procardGys.unit}
					</th>
					<th>

					</th>
					<th>
						实际数量 : ${procardGys.filnalCount}
					</th>
					<th>

					</th>
				</tr>
				<tr align="center">
					<td>
						零组件
					</td>
					<td>
						名称
					</td>
					<td>
						数量
					</td>
					<td>
						卡片类型
					</td>
				</tr>
				<s:iterator value="procardGysList" id="pageprocardGysTem">
					<tr align="center">
						<th>

							${pageprocardGysTem.markId}
						</th>
						<th>
							${pageprocardGysTem.proName}
						</th>
						<th>
							${pageprocardGysTem.corrCount}
						</th>
						<th>
							${pageprocardGysTem.procardStyle}
						</th>
					</tr>
				</s:iterator>
				<tr>
					<th>
						&nbsp;
					</th>
					<th>
						&nbsp;
					</th>
					<th>
						&nbsp;
					</th>
					<th>
						&nbsp;
					</th>
				</tr>
			</table>
		</div>
		<div id="showZi" style="border: solid #000 1px; display: none;">
			<div align="center"
				style="border-bottom: solid #000 1px; font-weight: bolder;">
				工 艺 流 水 卡 片
			</div>
			<div style="font-weight: bolder;" align="center">
				名称:${procardGys.proName} &nbsp;&nbsp;件号:
				<font color="red">${procardGys.markId}</font>&nbsp;&nbsp;
				批次:${procardGys.selfCard} &nbsp;&nbsp; 卡片类型:
				<font color="red">${procardGys.procardStyle}</font>&nbsp;&nbsp; 产品类型:
				${procardGys.productStyle} 车型:${procardGys.carStyle}&nbsp;&nbsp; 状态:
				<font color="red">${procardGys.status}</font>&nbsp;&nbsp;
			</div>
			<table class="table" style="width: 100%;">
				<tr>
					<th colspan="3" width="40%">
						数量: ${procardGys.maxCount}${procardGys.unit}
					</th>
					<th colspan="2" width="50%">
						数量(权值) 1 : ${procardGys.corrCount==null?0:procardGys.corrCount}
					</th>
				</tr>
				<tr>
					<td rowspan="5" style="width: 5px;" align="center">
						原
						<br />
						<br />
						材
						<br />
						<br />
						料
					</td>
					<td width="15%">
						牌号
					</td>
					<td width="15%">
						${procardGys.trademark}
					</td>
					<td rowspan="5" align="center" width="10%">
						备
						<br />
						<br />
						<br />
						注
					</td>
					<td rowspan="5">
						${procardGys.remark}
					</td>
				</tr>
				<tr>
					<td>
						规格
					</td>
					<td>
						${procardGys.specification}
					</td>
				</tr>
				<tr>
					<td>
						数量(权值)
					</td>
					<td>
						${procardGys.quanzi1} : ${procardGys.quanzi2}
					</td>
				</tr>
				<tr>
					<td>
						炉号
					</td>
					<td>
						${procardGys.luhao}
					</td>
				</tr>
				<tr>
					<td>
						编号
					</td>
					<td>
						${procardGys.number}
					</td>
				</tr>
			</table>
		</div>
		<div id="process" style="display: none;">
			<table class="table" style="width: 100%;">
				<tr>
					<th colspan="14" height="40px;">
						领工序
					</th>
				</tr>
				<tr align="center">
					<th>
						工序号
					</th>
					<th>
						名称
					</th>
					<th>
						可领数量
					</th>
					<th>
						已领数量
					</th>
					<th>
						提交数量
					</th>
					<th>
						不合格量
					</th>
					<th>
						初领时间
					</th>
					<th>
						提交时间
					</th>
					<th>
						并行
					</th>
					<th>
						生产类型
					</th>
					<th>
						状态
					</th>
					<th>
						领取人
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="list" id="pageProcess">
					<tr align="center">
						<th height="30px">
							${pageProcess.processNO}
						</th>
						<th>
							${pageProcess.processName}
						</th>
						<th>
							${pageProcess.totalCount}
						</th>
						<th>
							${pageProcess.applyCount}
						</th>
						<th>
							${pageProcess.submmitCount}
						</th>
						<th>
							${pageProcess.breakCount}
						</th>
						<th>
							${pageProcess.firstApplyDate}
						</th>
						<th style="width: 174px;">
							${pageProcess.submitDate}
						</th>
						<th>
							${pageProcess.processStatus}
						</th>
						<th>
							<s:if test='#pageProcess.productStyle=="外委"'>
								<a
									href="OSWorkAction!findAllOSW.action?osWork.markID=${procardGys.markId}&osWork.lotId=${procardGys.selfCard}">${pageProcess.productStyle}</a>
							</s:if>
							<s:else>
							${pageProcess.productStyle}
							</s:else>
						</th>
						<th>
							${pageProcess.status}
						</th>
						<th>
							<s:if test="viewStatus=='zjl'">
								<a
									href="UsersAction!findUserByIdForDetails.action?id=${pageProcess.userId}">
									${pageProcess.usernames}</a>
							</s:if>
							<s:else>${pageProcess.usernames}</s:else>
						</th>
						<th>
							<s:if test="pageStatus!='history'">
								<s:if test='procardGys.status=="领工序"||procardGys.status=="已发料"'>
									<s:if test='#pageProcess.productStyle!="外委"'>
										<s:if test='#pageProcess.status =="初始"'>
											<a href="javascript:;"
												onclick="showZjProcess('${pageProcess.id}','${procardGys.markId}')">自检</a>
										</s:if>
										<s:elseif test='#pageProcess.status =="自检"'>
											<a href="javascript:;"
												onclick="getProcess('${pageProcess.id}')">领取</a>
										</s:elseif>
										<s:elseif test='#pageProcess.status=="已领"'>
											<s:if test="#pageProcess.totalCount!=#pageProcess.applyCount">
												<a href="javascript:;"
													onclick="getProcess('${pageProcess.id}')">领取</a>/</s:if>
											<!-- 如果提交量为0 则开始首检提交 -->
											<s:if
												test="#pageProcess.submmitCount <=0&&#pageProcess.zjStatus=='yes'">
												<a href="javascript:;"
													onclick="submitProcess('${pageProcess.id}','1','sj')">提交</a>
											</s:if>
											<s:else>
												<a href="javascript:;"
													onclick="submitProcess('${pageProcess.id}','${pageProcess.applyCount-pageProcess.submmitCount-pageProcess.breakCount}')">提交</a>
											</s:else>
										</s:elseif>
									</s:if>
								</s:if>
							</s:if>
							<s:if test="viewStatus=='zjl'">
								<div style="width: 100px;">
									<s:if
										test="#pageProcess.shebeiNo!=''&&#pageProcess.shebeiNo!=null">
										<a
											href="ProdEquipmentAction!findMachineByNum.action?machine.workPosition=${pageProcess.gongwei}&machine.no=${pageProcess.shebeiNo}">机</a>
									</s:if>
									<s:else>
										<font color="gray">机</font>
									</s:else>
									<s:if
										test="#pageProcess.gzstoreId!=''&&#pageProcess.gzstoreId!=null">
										<a
											href="GzstoreAction_findAll.action?gzstore.number=${pageProcess.matetag}">工</a>
									</s:if>
									<s:else>
										<font color="gray">工</font>
									</s:else>
									<s:if
										test="#pageProcess.measuringId!=''&&#pageProcess.measuringId!=null">
										<a
											href="MeasuringAction_saveMeasuring.action?measuring.measuring_no=${pageProcess.measuring_no}">量</a>
									</s:if>
									<s:else>
										<font color="gray">量</font>
									</s:else>
									<s:if
										test='#pageProcess.zjStatus=="yes"&&#pageProcess.status!="初始"'>
										<a
											href="LogoStickerAction!findLogoSticker2.action?tag=manger&sticker.lotId=${procardGys.selfCard}&sticker.markId=${procardGys.markId}&sticker.processNO=${pageProcess.processNO}">检</a>
									</s:if>
									<s:else>
										<font color="gray">检</font>
									</s:else>
									<a
										href="gyslgxAction_findGongyiGuifan.action?markid=${procardGys.markId}&processNO=${pageProcess.processNO}">文</a>
								</div>
							</s:if>
							<s:else>
								<s:if
									test='#pageProcess.status =="完成"||#pageProcess.status =="已领"'>
									<a href="javascript:;" onclick="aginPrint('${pageProcess.id}')">补打</a>
								</s:if>
							</s:else>
						</th>
					</tr>
					<tr>
						<th align="left" colspan="20"
							style="background-color: red; border: 0px; margin: 0px; padding: 0px;">
							<div align="left"
								style="background-color: #00ff01; width:${pageProcess.submmitCount/pageProcess.totalCount*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
								&nbsp;&nbsp;&nbsp;&nbsp;${pageProcess.processNO}工序完成进度:
								${pageProcess.submmitCount/pageProcess.totalCount*100}%
							</div>
						</th>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="showWai" style="border: solid #000 1px; display: none;">
			<div align="center" style="border-bottom: solid #000 1px;">
				工 艺 流 水 卡 片
			</div>
			<table class="table" style="width: 100%;">
				<tr>
					<th align="right" style="width: 25%;">
						件号:
					</th>
					<td>
						<font color="red">${procardGys.markId}</font>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<th align="right">
						名称:
					</th>
					<td>
						${procardGys.proName}
					</td>
				</tr>
				<tr>
					<th align="right">
						批次:
					</th>
					<td>
						${procardGys.selfCard}
					</td>
				</tr>
				<tr>
					<th align="right">
						车型:
					</th>
					<td>
						${procardGys.carStyle}
					</td>
				</tr>
				<tr>
					<th align="right">
						卡片类型:
					</th>
					<td>
						<font color="red">${procardGys.procardStyle}</font>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<th align="right">
						单位:
					</th>
					<td>
						${procardGys.yuanUnit}
					</td>
				</tr>
				<tr>
					<th align="right">
						权值:
					</th>
					<td>
						${procardGys.quanzi1} : ${procardGys.quanzi2}
					</td>
				</tr>
				<tr>
					<th align="right">
						产品类型:
					</th>
					<td>
						${procardGys.productStyle}
					</td>
				</tr>
			</table>
		</div>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//现场自检表
function showZjProcess(processId, markId) {
	$("#showZjProcess").attr(
			"src",
			"gyslgxAction_showZj.action?procardGys.markId=" + markId
					+ "&id=" + processId);
	$("#zjProcessDiv").show();
	$("#submitProcessDiv").hide();
	$("#collorProcessDiv").hide();
	chageDiv("block");
}

//显示卡片已有工序
function getProcess(pageId) {
	$
			.ajax( {
				type : "POST",
				url : "gyslgxAction_findProcess.action",
				dataType : "json",
				data : {
					id : pageId
				},
				success : function(msg) {
					if (msg.success) {
						$("#ProcessTab").empty();
						$("#ProcessTab").append(
								"<tr>" + "<th colspan='8'>领取工序</th>" + "</tr>"
										+ "<tr>" + "<th>工序号</th>"
										+ "<th>名称</th>" + "<th>可领取数量</th>"
										+ "<th>是否并行</th>" + "<th>工位</th>"
										+ "<th>设备编号</th>" + "<th>领取数量</th>"
										+ "<th>卡号(请刷员工卡)</th>" + "</tr>");
						var tableHtml = "";
						var forCount = 0;
						$
								.each(msg.data, function(i, process) {
									var processNumbers = process.totalCount
											- process.applyCount;
									var readonly = "";
									//如果未领取过，开始首检
										//if (process.applyCount == 0) {
										//	readonly = "readonly = 'readonly'";
										//	processNumbers = 1;
										//} else if (process.submmitCount == 0) {
										//	return true;
										//} else {
										//	processNumbers = process.totalCount
										//	-process.applyCount;
										//}
										tableHtml += '<tr align="center"><td>'
												+ '<input type="hidden" name="processIds" value="'
												+ process.id
												+ '"/>'
												+ process.processNO
												+ '</td><td>'
												+ process.processName
												+ '</td><td>'
												+ (process.totalCount - process.applyCount)
												+ '<input type="hidden" id="totalCount" value="'
												+ (process.totalCount - process.applyCount)
												+ '"/></td><td>'
												+ process.processStatus
												+ '</td><td>'
												+ process.gongwei
												+ '</td><td>'
												+ process.shebeiNo
												+ '</td><td>'
												+ '<input type="text" id="processNumbers" name="processNumbers" value="'
												+ processNumbers
												+ '"/></td><td>';
										var status = process.status;
										var userCardId = process.userCardId;
										//判断是否领过
										if (status == '自检' || userCardId == '') {
											tableHtml += '<input type="text" id="processCards'
													+ forCount
													+ '" name="list['
													+ forCount
													+ ']" />'
													+ '<input onclick="uploadFile(this,'
													+ forCount
													+ ')" type="button" value="添加人员" /> <div id="fileDiv_'
													+ forCount
													+ '" style="display: none;"></div></td></tr>';
										} else {
											var userCardId = process.userCardId;
											var cards = userCardId.split(",");
											for ( var i = 0; i < cards.length; i++) {
												tableHtml += '<input type="text" id="processCards'
														+ forCount
														+ '" name="list['
														+ forCount
														+ ']" readonly="readonly" value="'
														+ cards[i] + '"/><br/>';
											}
											tableHtml += '</td></tr>';
										}
										forCount++;
									});

						tableHtml += "<tr>"
								+ "<th colspan='8'>"
								+ "<input type='button' value='领取' "
								+ "style='height:50px;width:80px;' onclick='collorForm()' /></th>"
								+ "</tr>"
						$("#ProcessTab").append(tableHtml);
						$("#zjProcessDiv").hide();
						$("#submitProcessDiv").hide();
						$("#collorProcessDiv").show();
						chageDiv("block");
					} else {
						alert(msg.message);
					}
				}
			});
}

var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	fileDiv.style.display = "block";
	fileDivHTML = "<div id='file" + count
			+ "' style='padding-left:74px;' align='left'><input name='list["
			+ few + "]' /><a href='javascript:delFile(" + count + "," + few
			+ ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
}

//领取流水卡片
function collorForm() {
	if ($("#processNumbers").val() == "") {
		alert("请填写领取数量!");
		$("#processNumbers").select();
	} else if ($("#processCards").val() == "") {
		alert("请刷员工卡!");
		$("#processCards").select();
	} else if (parseFloat($("#totalCount").val()) < parseFloat($(
			"#processNumbers").val())) {
		alert("领取数量不能大于" + $("#totalCount").val()) + "件";
	} else {
		$
				.ajax( {
					type : "POST",
					url : "gyslgxAction_collorProcess.action",
					dataType : "json",
					data : $("#collorProcess").serialize(),
					success : function(msg) {
						alert(msg);
						if (msg == "领取成功") {
							window.location = "gyslgxAction_findProcardGysByRunCard2.action?id=${procardGys.id}&pageStatus=${pageStatus}";
						}
					}
				});
	}
}

//提交工序赋值 
function submitProcess(processId, number, sjstatus) {
	$("#submitProId").val(processId);
	$("#subNumber").val(number);
	if (sjstatus == 'sj') {
		$("#subNumber").attr("readonly", "readonly");
	} else {
		$("#subNumber").removeAttr("readonly");
	}
	$("#zjProcessDiv").hide();
	$("#submitProcessDiv").show();
	$("#collorProcessDiv").hide();
	chageDiv("block");
}
//提交工序
function submitForm() {
	$
			.ajax( {
				type : "POST",
				url : "gyslgxAction_submitProcess.action",
				dataType : "json",
				data : $("#submitProcess").serialize(),
				success : function(datas) {
				if (datas.message == "提交工序成功") {
						var data = datas.data;
						var process = data.process;
						var procard = data.procard;
						$("#printMarkId").html(procard.markId);
						$("#printSelfCard").html(procard.selfCard);
						$("#printProcessNO").html(process.processNO);
						$("#printProcessName").html(process.processName);
						$("#printSubmmitCount").html(process.submmitCount);
						$("#printUsernames").html(process.usernames);
						$("#printSubmitDate").html(process.submitDate);
						pagePrint('printgxDiv', 'view');
						window.location = "gyslgxAction_findProcardGysByRunCard2.action?id=${procardGys.id}&pageStatus=${pageStatus}";
					} else {
						alert(datas.message);
					}
					
				}
			});
}
//补打工序
function aginPrintForm() {
	$
			.ajax( {
				type : "POST",
				url : "gyslgxAction_printProcess.action",
				dataType : "json",
				data : $("#aginPrintForm").serialize(),
				success : function(datas) {
					if (datas.success) {
						var data = datas.data;
						var process = data.process;
						var procardGys = data.procard;
						$("#printMarkId").html(procardGys.markId);
						$("#printSelfCard").html(procardGys.selfCard);
						$("#printProcessNO").html(process.processNO);
						$("#printProcessName").html(process.processName);
						$("#printSubmmitCount").html(process.submmitCount);
						$("#printUsernames").html(process.usernames);
						$("#printSubmitDate").html(process.submitDate);
						pagePrint('printgxDiv', 'view');
						window.location = "gyslgxAction_findProcardGysByRunCard2.action?id=${procardGys.id}&pageStatus=${pageStatus}";
					} else {
						alert(datas.message);
					}
				}
			});
}

function aginPrint(printProcessId) {
	$("#printProId").val(printProcessId);
	$("#zjProcessDiv").hide();
	$("#submitProcessDiv").hide();
	$("#collorProcessDiv").hide();
	$("#aginPrintDiv").show();
	chageDiv("block");
}
</script>
	</body>
</html>
