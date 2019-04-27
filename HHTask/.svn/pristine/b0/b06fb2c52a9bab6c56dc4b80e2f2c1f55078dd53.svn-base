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
		<script type="text/javascript"
			src="<%=basePath%>javascript/radialIndicator.js">
</script>
		<STYLE type="text/css">
/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 1024px; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<s:if test="viewStatus=='zjl'">
								<a href="javascript:history.go(-1);">返回</a>
							</s:if>
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center" id="divWatermark"
					style="font-weight: bolder; font-size: 16px; top: 5px; width: 100%">
					<span id="zc_name"></span> [
					<a href="javascript:;"
						onclick="javascript:location.href='<%=basePath%>/ProcardAction!findProcardView.action?id=${param.id}&pageStatus=${param.pageStatus}&viewStatus=${param.viewStatus}'">进入树形模式</a>]
					[
					<a href="javascript:;"
						onclick="javascript:location.href='<%=basePath%>/System/SOP/produce/Procard_wllbck.jsp?id=${param.id}&pageStatus=${param.pageStatus}&viewStatus=${param.viewStatus}'">物料列表模式</a>]
					[
					<a href="javascript:;" onclick="javascript:location.reload(true)">刷新</a>]
					<br />
					<span id="zc_xinxi"></span>
				</div>
				<table>
					<tr align="left">
						<th align="right">
							工序类型:
						</th>
						<td>
							<select id="type" style="width: 80px;">
								<option></option>
								<option value="zz">
									自制
								</option>
								<option value="ww">
									委外
								</option>
							</select>
						</td>
						<th align="right">
							工序名:
						</th>
						<td>
							<select id="processName" name="processName">
								<option></option>
							</select>
						</td>
					</tr>
				</table>
				<input type="button" value="查询" onclick="selectProess()"
					style="width: 80px; height: 40px;" />
				<br />
				<table class="table" id="mytable">
				</table>
				<div id="show" style="color: red; font-size: x-large;">
					<br />
					正在加载,请稍候
					<span id="showMess1_font"></span>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	var type = "";
	getProcard(type);
})

function getProcard(type, workShop) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!getprocardById.action",
				data : {
					id : '${param.id}'
				},
				dataType : "json",
				success : function(data) {
					if (data != null) {
						$("#zc_name").html(data.proName);
						$("#zc_xinxi").html(
								"(件号:" + data.markId
										+ "&nbsp;(<font color='green'>"
										+ data.ywMarkId + "</font>)" + "批次:"
										+ data.selfCard + "&nbsp;数量:"
										+ data.filnalCount + ")");
						var csth = '';
						var maxBelongLayer = data.maxBelongLayer;
						for ( var i = 1; i < maxBelongLayer; i++) {
							csth += '<th style="width: 10px;">层数</th>'
						}
						var tr = '<tr><th style="width: 10px;">序号</th>' + csth + '<th>件号</th><th>状态</th><th>批次</th><th>名称</th>' + '<th>数量</th><th>加工进度</th></tr>';
						$("#mytable").append(tr);
					}
				}
			})
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!findProcardByRootId.action",
				data : {
					pageStatus : 'gxjd',
					id : '${param.id}'
				},
				dataType : "json",
				success : function(data) {
					if (data != null && data.length > 0) {
						$("#show").hide();
						clearInterval(time);
						for ( var i = 0; i < data.length; i++) {
							var procard = data[i];
							var jihuoStatus = "wei";
							if (procard.jihuoStatua == "激活") {
								jihuoStatus = "all";
							}
							var bgcorlor="";
							if (procard.status != "完成"
									&& procard.status != "入库"
									&& procard.status != "待入库"
									&& procard.status != "取消") {
								bgcorlor="background-color: red;color: #ffffff;";
							}
							$("#mytable")
									.append(
											'<tr id=tr_'
													+ procard.id
													+ ' align="right"><td><font color="red">'
													+ (i + 1)
													+ '</font></td>'
													+ procard.lingliaoDetail
													+ '<td style="width: 120px;"><a style="cursor:pointer;" href="javascript:;" onclick="showLingGx('
													+ procard.id
													+ ',this,&apos;'
													+ procard.status
													+ '&apos;)">'
													+ procard.markId
													+ '</a></td>'
													+ '<td style="width:50px;">'
													+ procard.status
													+ '</td><td style="width: 100px;">'
													+ procard.selfCard
													+ '</td>'
													+ '<td style="width: 100px;" align="left">'
													+ ''
													+ procard.proName
													+ '</td>'
													+ '<td style="width: 50px;'+bgcorlor+'">'
													+ procard.filnalCount
													+ '</td></tr>');
							if(procard.procardStyle!='外购'||procard.needProcess=='yes'){
							getProcessInfor(procard.id, procard.id,
									procard.filnalCount, procard.jihuoStatua,procard.wwblCount,
									type, workShop);
							}
						}
					}
				}
			})
}
function getProcessInfor(procardId, num, num1, jihuoStatua,wwblCount, type, workShop) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!getProcessInforByprocardId.action",
				data : {
					id : procardId,
					pageStatus : type,
					tc : workShop
				},
				dataType : "json",
				success : function(data) {
					if (data != null && data.length > 0) {

						var table = "<td align='left' style='padding:0px;'><table style='border-collapse: collapse;'>";
						var tr1 = "<tr>";
						var tr2 = "<tr>";
						var tr3 = "<tr>";
						for ( var i = 0; i < data.length; i++) {
							var process = data[i];
							var color = "";
							num1 = parseInt(num1);
							var num2 = parseInt(process[2]);
							var agreeWwCount = parseInt(process[3]);
							var pagestatus = '自制';
							var processName = process[1];
							if (processName.indexOf("外委") >= 0) {
								agreeWwCount = num1;
							}
							if(wwblCount!=null&&wwblCount>0){
								agreeWwCount = wwblCount;
							}
							if (agreeWwCount > 0) {
								pagestatus = "<font color='#08088A'>外委</font>(<font color='red'>"
										+ agreeWwCount + "</font>)"
							}
							var num3 = (num2 / num1).toFixed(2) * 50;
							var jiduti = '<div style="width: 50px;height: 10px;border: 1px solid #33CC33;">'
									+ '<div style="width: '
									+ num3
									+ 'px;height: 10px;background-color: #33CC33; float: left;">'
									+ '<font size="1px;" color="#000">'
									+ num2
									+ '/' + num1 + '</font>'
							'</div>' + '</div>';
							tr1 += '<td style="font-size:10px;">' + process[0]
									+ '-' + process[1]
									+ '<hr><div align="right">' + jiduti
									+ '</div></td>';
							tr2 += "<td ><a style='cursor:pointer;' href='javascript:;' onclick='showprocesslog("
									+ process[4]
									+ ")'><div id='jdt"
									+ num
									+ "_"
									+ i
									+ "' class= 'jindu' data="
									+ (num2 / num1) * 100 + " ></div></a></td>";
							tr3 += '<td style="font-size:10px;"><span>' + pagestatus + '<span></td>';

						}
						table += tr1 + "</tr>" + tr2 + "</tr>" + tr3 + "</tr>";
						$("#tr_" + num).append(table + "</tr></table></td>");
						for ( var i = 0; i < data.length; i++) {
							var color_0 = '#7B7B7B';
							var color_66 = '#7B7B7B';
							var color_100 = '#7B7B7B';
							if (jihuoStatua == '激活') {
								var color_0 = '#FF0000';
								var color_66 = '#FFFF00';
								var color_100 = '#33CC33';
							}

							$("#jdt" + num + "_" + i).each(function(i) {
								var hk_val = $(this).attr('data');
								$(this).radialIndicator( {
									barColor : ( {
										0 : color_0,
										66 : color_66,
										100 : color_100
									}),
									barWidth : 3,
									radius : 18,
									initValue : hk_val,
									roundCorner : true,
									percentage : true
								});
							});

						}

					}
				}
			})
}
//显示工序详细
function showProcessForSb(id, jihuoStatus, proStatus) {
	//历史查看,不显示工序领取
	if ('${param.pageStatus}' == 'history') {
		$("#showProcess").attr(
				"src",
				"ProcardAction!findProcardByRunCard2.action?id=" + id
						+ "&pageStatus=history&viewStatus=");
		chageDiv('block');
	} else {
		if (proStatus != "初始" && proStatus != "已发卡") {
			$("#showProcess")
					.attr(
							"src",
							"ProcardAction!findProcardByRunCard2.action?id="
									+ id
									+ "&pageStatus=history&viewStatus=${param.viewStatus}");
			chageDiv('block');
		} else {
			alert("该生产周转卡尚未领料!请先领料!");
		}
	}
}

function showLingGx(id, obj, proStatus) {
	if (proStatus != "初始" && proStatus != "已发卡" && proStatus != "设变锁定"
			&& proStatus != "取消") {
		$("#showProcess").css("height", "400px");
		$("#showProcess")
				.attr(
						"src",
						"ProcardAction!findProcardByRunCard2.action?id="
								+ id
								+ "&pageStatus=noCardLingGx&viewStatus=${param.viewStatus}");
		$("#lingLiaoDiv").hide();
		chageDiv('block');
		$("#contentDiv").removeAttr("css");
		$("#contentDiv").css("left", "0");
		$("#contentDiv").addClass("contentDiv100");//关闭统一样式
		var gettop = $(obj).offset().top - 250;
		if (gettop < 0) {
			gettop = 0;
		}
		//单独设置弹出层的高度
		var thisTopHeight = gettop;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});
	} else {
		if (proStatus == "设变锁定") {
			alert("设变锁定中!");
		} else if (proStatus == "取消") {
			alert("该订单已取消!");
		} else {
			alert("该生产周转卡尚未领料!请先领料!");
		}
	}
}

function showprocesslog(id) {
	$("#showProcess").css("height", "600px");
	$("#showProcess").attr("src",
			"ProdEquipmentAction!findMachineByNum.action?id=" + id);
	$("#lingLiaoDiv").hide();
	chageDiv('block');
	$("#contentDiv").removeAttr("css");
	$("#contentDiv").css("left", "0");
	$("#contentDiv").addClass("contentDiv100");//关闭统一样式
	var gettop = $(obj).offset().top - 250;
	if (gettop < 0) {
		gettop = 0;
	}
	//单独设置弹出层的高度
	var thisTopHeight = gettop;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}

$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	var contentDiv = $(window.parent.document).find("#contentDiv");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
		contentDiv.height(thisheight2 + 200);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})

function selectProess() {
	var type = $("#type").val();
	var workShop = $("#textselectprocessName").val();
	$("#mytable").empty();
	$("#show").show();
	time = setInterval("test()", 100);
	getProcard(type, workShop);
}
var str = '';
var time;
function test() {
	if (str.length >= 8) {
		str = '';
	} else {
		str += '。';
	}
	$("#showMess1_font").html(str);
}

time = setInterval("test()", 400);
$(function() {
	$.ajax( {
		type : "post",
		url : "ProcessCollectAction_findAllProcessGzstore.action",
		dataType : "json",
		async : false,
		success : function(data) {
			$("#processName").empty();
			$("#processName").append("<option value=''></option>");
			$(data).each(
					function() {
						$("<option value='" + this + "'>" + this + "</option>")
								.appendTo("#processName");
					});
			duoxuaSelect('processName', '${tc}');
		}
	});
})
</script>
	</body>
</html>
