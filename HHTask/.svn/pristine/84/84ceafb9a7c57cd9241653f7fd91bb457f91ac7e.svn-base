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
				var procardStyle="${procard.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"||procardStyle=="自制"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative;  background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
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
			<div align="center">
				<s:if test="pageStatus!='history'">
					<h1>
						领取工序
					</h1>
					<div>
						<form action="ProcardAction!findProcardByRunCard.action"
							method="post">
							请刷流水卡片(扫描报废单条码):
							<input type="text" id="runCard" name="cardNumber"
								value="${cardNumber}" />
						</form>
					</div>
				</s:if>
				<div id="showZong" style="border: solid #000 1px; display: none;">
					<div align="center" style="border-bottom: solid #000 1px;">
						工 艺 流 水 卡 片
					</div>
					<div style="font-weight: bolder;">
						名称:${procard.proName} &nbsp;&nbsp;件号:${procard.markId}
						&nbsp;&nbsp; 批次:${procard.selfCard} &nbsp;&nbsp;
						卡片类型:${procard.procardStyle} &nbsp;&nbsp;
						产品类型:${procard.productStyle} &nbsp;&nbsp;
						车型:${procard.carStyle}&nbsp;&nbsp; 状态:
						<font color="red">${procard.status}</font>&nbsp;&nbsp;
					</div>
					<table class="table" style="width: 100%;">
						<tr>
							<th>
								最大数量
							</th>
							<th>
								${procard.maxCount} ${procard.unit}
							</th>
							<th>
								实际数量
							</th>
							<th>
								${procard.filnalCount}
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
						<s:iterator value="procardList" id="pageProcardTem">
							<tr align="center">
								<th>
									${pageProcardTem.markId}
								</th>
								<th>
									${pageProcardTem.proName}
								</th>
								<th>
									${pageProcardTem.corrCount}
								</th>
								<th>
									${pageProcardTem.procardStyle}
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
					<table class="table">
						<tr>
							<th colspan="13" height="40px;">
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
								<th>
									${pageProcess.submitDate}
								</th>
								<th>
									${pageProcess.processStatus}
								</th>
								<th>
									${pageProcess.productStyle}
								</th>
								<th>
									${pageProcess.status}  
								</th>
								<th>
									${pageProcess.usernames}
								</th>
								<th>
									<s:if test="pageStatus!='history'">
										<s:if
											test="procard.status=='领工序'||procard.status=='已发料'&&#pageProcess.productStyle=='自制'">
											<s:if test='#pageProcess.status =="初始"'>
												<a href="javascript:;"
													onclick="getProcess('${pageProcess.id}')">领取 </a>
											</s:if>
											<s:elseif test='#pageProcess.status=="已领"'>
												<s:if
													test="#pageProcess.totalCount!=#pageProcess.applyCount">
													<a href="javascript:;"
														onclick="getProcess('${pageProcess.id}')">领取 </a>/</s:if>
												<a href="javascript:;"
													onclick="submitProcess('${pageProcess.id}','${pageProcess.applyCount-pageProcess.submmitCount-pageProcess.breakCount}')">提交</a>
											</s:elseif>
										</s:if>
									</s:if>
								</th>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
//初始选中
$(function() {
	//$("#runCard").focus();
})

//显示卡片已有工序
function getProcess(pageId) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!findProcess.action",
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
						$(msg.data)
								.each(
										function() {
											tableHtml += '<tr align="center"><td>'
													+ '<input type="hidden" name="processIds" value="'
													+ $(this).attr('id')
													+ '"/>'
													+ $(this).attr('processNO')
													+ '</td><td>'
													+ $(this).attr(
															'processName')
													+ '</td><td>'
													+ ($(this).attr(
															'totalCount') - $(
															this).attr(
															'applyCount'))
													+ '</td><td>'
													+ $(this).attr(
															'processStatus')
													+ '</td><td>'
													+ $(this).attr('gongwei')
													+ '</td><td>'
													+ $(this).attr('shebeiNo')
													+ '</td><td>'
													+ '<input type="text" id="processNumbers" name="processNumbers" value="'
													+ ($(this).attr(
															'totalCount') - $(
															this).attr(
															'applyCount'))
													+ '"/></td><td>';

											var status = $(this).attr('status');
											var userCardId = $(this).attr(
													'userCardId');
											//判断是否领过
											if (status == '初始'
													|| userCardId == '') {
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
												var userCardId = $(this).attr(
														'userCardId');
												var cards = userCardId
														.split(",");
												for ( var i = 0; i < cards.length; i++) {
													tableHtml += '<input type="text" id="processCards'
															+ forCount
															+ '" name="list['
															+ forCount
															+ ']" readonly="readonly" value="'
															+ cards[i]
															+ '"/><br/>';
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
						chageDiv("block");
						$("#collorProcessDiv").show();
						$("#submitProcessDiv").hide();
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
	} else {
		$
				.ajax( {
					type : "POST",
					url : "ProcardAction!collorProcess.action",
					dataType : "json",
					data : $("#collorProcess").serialize(),
					success : function(msg) {
						alert(msg);
						if (msg == "领取成功") {
							window.location = "ProcardAction!findProcardByRunCard.action?cardNumber="
									+ $("#runCard").val();
						}
					}
				});
	}
}

//提交工序赋值 
function submitProcess(processId, number) {
	$("#submitProId").val(processId);
	$("#subNumber").val(number);
	chageDiv("block");
	$("#submitProcessDiv").show();
	$("#collorProcessDiv").hide();
}

//提交工序
function submitForm() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!submitProcess.action",
				dataType : "json",
				data : $("#submitProcess").serialize(),
				success : function(msg) {
					alert(msg);
					if (msg == "提交工序成功") {
						window.location = "ProcardAction!findProcardByRunCard.action?cardNumber="
								+ $("#runCard").val();
						//chageDiv("none");
					}
				}
			});
}
</script>

			<link rel="stylesheet"
				href="<%=basePath%>javascript/keyboard/keyboard.css" />
			<script
				src="<%=basePath%>javascript/keyboard/js/jquery.keyboard/jquery.keyboard.js">
</script>
			<script
				src="<%=basePath%>javascript/keyboard/js/jquery.keyboard/keyboards/qwerty.js">
</script>
			<script
				src="<%=basePath%>javascript/keyboard/js/jquery.keyboard/plugins/form.js">
</script>
			<script src="<%=basePath%>javascript/keyboard/js/jquery.selection.js">
</script>
			<script>
(function($) {
	$(document).ready(function() {
		$('body').keyboard( {
			keyboard : 'qwerty',
			plugin : 'form'
		});
		$('#keyboard').bind('change', function() {
			$('body').keyboard('keyboard', $(this).val());
		});
	})
})(jQuery);</script>
	</body>
</html>
