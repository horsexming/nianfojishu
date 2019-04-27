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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none');">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 100%; display: none;"
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
								height="32" onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
			</div>
			<div id="operatingDiv"
				style="background-color: #ffffff; width: 100%;">
				<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
			</div>
			<div id="lingLiaoDiv" style="background-color: #ffffff; width: 100%;">
				<iframe id="showLingLiaoIf" src="" marginwidth="0" marginheight="0"
					hspace="0" vspace="0" frameborder="0" scrolling="yes"
					style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;" >
			<div align="center">
				<div align="center" id="divWatermark"
					style="font-weight: bolder; font-size: 16px; top: 5px; width: 100%">
					<span id="zc_name"></span> [
					<a href="javascript:;"
						onclick="javascript:location.href='<%=basePath%>/ProcardAction!findProcardView.action?id=${param.id}&pageStatus=${param.pageStatus}&viewStatus=${param.viewStatus}'">进入树形模式</a>]
					[<a href="javascript:;"
					onclick="javascript:location.href='<%=basePath%>/System/SOP/produce/Procard_gxjdck.jsp?id=${param.id}&pageStatus=${param.pageStatus}&viewStatus=${param.viewStatus}'">工序列表模式</a>]
					[
					<a href="javascript:;" onclick="javascript:location.reload(true)">刷新</a>]	
						<br/>
						<span id="zc_xinxi"></span>
				</div>
				<br />
				<table class="table" id="mytable" >
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "POST",
		url : "ProcardAction!getprocardById.action",
		data : {
			id:'${param.id}'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#zc_name").html(data.proName);
				$("#zc_xinxi").html("(件号:"+data.markId+"&nbsp;(<font color='green'>"+data.ywMarkId+"</font>)"+"批次:"+data.selfCard+"&nbsp;数量:"+data.filnalCount+")");
				var csth = '';
				var maxBelongLayer = data.maxBelongLayer;
				for(var i=1;i<maxBelongLayer;i++){
					csth+='<th style="width: 10px;">层数</th>'
				}
				 var tr = '<tr><th style="width: 10px;">序号</th>'
				 	+csth+'<th>件号</th><th>状态</th><th>批次</th><th>名称</th>' +
				 	'<th>数量</th><th>物料进度</th></tr>';	
				 $("#mytable").append(tr);
			}
		}
	})
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!findProcardByRootId.action",
				data : {
					pageStatus : 'wllb',
					id : '${param.id}'
				},
				dataType : "json",
				success : function(data) {
					if (data != null && data.length > 0) {
						for ( var i = 0; i < data.length; i++) {
							var procard = data[i];
							var jihuoStatus = "wei";
							var a_html ='<a href="javascript:;" onclick="alert(&apos;尚未激活!&apos;)">'
							if (procard.jihuoStatua == "激活") {
								a_html = '<a href="javascript:;" onclick="showLingLiao('+procard.id+',this)">'
								jihuoStatus = "all";
							}
							$("#mytable")
									.append(
											'<tr id=tr_'
													+ procard.id
													+ ' align="right"><td>'
													+ (i+1)
													+ '</td>'
													+procard.lingliaoDetail
													+ '<td>'
													+ procard.belongLayer
													+ '</td>'
													+ '<td >'
													+ procard.markId
													+ '</td>'
													+ '<td>'
													+ procard.selfCard
													+ '</td>'
													+ '<td style="width: 100px;" align="left">'
													+ '' + procard.proName
													+ '</td>' + '<td>'
													+ procard.filnalCount
													+ '</td></tr>');
							getProcessInfor(procard.id, procard.id,
									procard.filnalCount);
						}
					}
				}
			})
		
			
})

function getProcessInfor(procardId, num, num1) {
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!findProcardByfatherId.action",
				data : {
					id : procardId
				},
				dataType : "json",
				success : function(data) {
					if (data != null && data.length > 0) {

						var table = "<td align='left' style='padding:0px;'><table style='border-collapse: collapse;'>";
						var tr1 = "<tr align='center'>";
						var tr2 = "<tr align='center'>";
						var tr3 = "<tr align='center'>";
						var tr4 = "<tr align='center'>";
						var tr5 = "<tr align='center'>";
						for ( var i = 0; i < data.length; i++) {
							var procard = data[i];
							var color = "";
							num1 = parseInt(num1);
							tr1 += '<td style="font-size:10px; ">&nbsp;&nbsp; <b>' +procard.markId
									+ '</b></td>';
							tr2+='<td style="font-size:10px; ">&nbsp;&nbsp;<b>'+procard.proName+'</b>&nbsp;&nbsp;</td>'
							tr3+='<td style="font-size:10px; ">&nbsp;&nbsp;<b>'+procard.tjNumber + '/' + procard.filnalCount+'</b>&nbsp;&nbsp;</td>';
							tr4 += "<td ><div id='jdt" + num + "_" + i
									+ "' class= 'jindu' data=" + (procard.tjNumber / procard.filnalCount)
									* 100 + " ></div></td>";
							tr5+= '<td style="font-size:10px;">'+procard.cangqu+'('+procard.procardStyle+')</td>';
						}
						table += tr1 + "</tr>" + tr2 + "</tr>"+tr3+"</tr>"+tr4+"</tr4>"+tr5+"</tr>";
						$("#tr_" + num).append(table + "</tr></table></td>");
						for ( var i = 0; i < data.length; i++) {
							var procard = data[i];
							 var color_0 = '#7B7B7B';
							 var color_66 = '#7B7B7B';
							 var color_100 = '#7B7B7B';
							 if(procard.jihuoStatua == '激活' ){
								 if(procard.procardStyle == '外购'){
									var color_0 = '#FF0000';
							 		var color_66 = '#FFFF00';
							 		var color_100 = '#33CC33';
								 }else if(procard.procardStyle == '自制'){
									var color_0 = '#483D8B';
							 		var color_66 = '#8A2BE2';
							 		var color_100 = '#FF00FF';
								 }
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
//领料
function showLingLiao(id, obj) {
	if ('${tag}' == "phone" || '${tag}' == "code") {
		window.location.href = "sellAction!procardLingliaonew.action?id=" + id
				+ "&status=${tag}&cardNumber=${cardNumber}";
	} else {
		$("#showLingLiaoIf").css("height", "0px");
		$("#showLingLiaoIf").attr(
				"src",
				"sellAction!procardLingliaonew.action?id=" + id
						+ "&status=${tag}");
		$("#operatingDiv").hide();
		chageDiv('block');
		$("#contentDiv").removeAttr("css");
		$("#contentDiv").css("left", "0");
		$("#contentDiv").addClass("contentDiv100");//关闭统一样式
		var gettop = $(obj).offset().top - 200;
		if (gettop < 0) {
			gettop = 0;
		}
		//单独设置弹出层的高度
		var thisTopHeight = gettop;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});
	}
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
</script>
	</body>
</html>
