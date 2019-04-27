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
		<script src="<%=basePath%>/javascript/jquery.corner.js"
			type="text/javascript">
</script>
		<style type="text/css">
body {
	background: #ffffff;
}

#processbar {
	height: 13px;
	width: 30%;
	margin-top: 200px;
	margin-left: 35%;
	background-color: #fff;
	border: 1px solid #999;
}

#processbar .finish {
	height: 13px;
	width: 40%;
	background-color: #999;
}

.topDiv {
	filter: Alpha(Opacity =                                 95);
	background: #000;
	opacity: 1;
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	opacity: 0.5;
	background: #000;
}
</style>
		<script type="text/javascript">
$(document).ready(function() {
	$("#processbar").corner();
	$("#processbar .finish").corner();
})
</script>
		<script type="text/javascript">
var maxBelongLayer = parseInt('${maxBelongLayer}');//总层数
var allProcard;
$(function() {
	if (maxBelongLayer < 5) {
		maxBelongLayer = 5;
	}
	var avgWeight = 98 / maxBelongLayer;//平均每层宽度
	var id = "${id}";//rootId
	for ( var i = 1; i <= maxBelongLayer; i++) {
		$(
				"<div id='pro" + i
						+ "' style=' border-left: solid 0px #000000;width: "
						+ avgWeight + "%;float: left;'></div>").appendTo(
				"#viewProcard");
	}
	$.ajax( {
		async : false,
		type : "post",
		url : "ProcardAction!findProByBel.action",
		data : {
			id : id
		},
		dataType : "json",
		success : function(obj) {
			allProcard = obj;
		}
	})
	//递归生成进度图
	findPro(id, 1);
	$("#processbar").hide();
	$("#viewProcard").show();
});

var jdt = 0;
function findPro(fatherId, belongLayer, index) {
	jdt += 5;
	if (jdt <= 100) {
		$("#processbar .finish").css("width", jdt + "%");
	}

	var jiange2 = "<div style='border-left: solid 0px #BABABA;height:54px;'></div>";//间隔2div
	var eachHeight = 0;
	//挑选出所有的子层
	var obj = $.grep(allProcard, function(n, i) {
		if (belongLayer == 1) {
			return n.id == fatherId;
		} else {
			return n.fatherId == fatherId;
		}
	});
	if (obj.length > 0) {
		//生成子层
		$
				.each(obj, function(i, procard) {
					eachHeight += 54;
					//判断激活状态
						var bgcolor = "";
						var color = "#BABABA";
						var cursor = "pointer";
						var jihuoStatus = "wei";
						var borderCorlor = "#BABABA";
						var backGround = "#BABABA";
						if (procard.jihuoStatua == "激活") {
							borderCorlor = " #008B00";
							backGround = "#008B00";
							color = "#000000";
							cursor = "pointer";
							jihuoStatus = "all";
						} else {
							cursor = "pointer";
						}

						//计算提交百分比
						var width = 0;
						var nextWidth = 100;
						var tjNumber = procard.tjNumber;
						if (tjNumber != null) {
							width = (procard.tjNumber / procard.filnalCount * 100)
									.toFixed(0);
							nextWidth = 100 - width;
						} else {
							tjNumber = 0;
						}
						var flstatus = "<br/>";
						if (procard.procardStyle == '自制'
								|| procard.procardStyle == '组合'
								|| procard.procardStyle == '总成') {
							flstatus = "(<b><font color='red'>"
									+ procard.status + "</font></b>)<br/>";
						}
						if (procard.procardStyle == '外购'
								&& procard.needProcess == 'yes') {
							flstatus = "(<b><font color='red'>"
									+ procard.status + "</font></b>)<br/>";
						}
						var nextWidthCon = procard.proName + "(<b>"
								+ procard.procardStyle + "</b>)" + flstatus
								+ procard.markId + "(<b>完成:" + tjNumber + "/"
								+ procard.filnalCount + "</b>)";
						var widthCon = "";
						if (width > 60) {
							color = "#ffffff";
							widthCon = nextWidthCon;
							nextWidthCon = "";
						}

						//生成件号Div
						var html = "<div align='right' style='cursor: "
								+ cursor + "; border: solid 2px "
								+ borderCorlor + ";background-color:" + bgcolor
								+ ";color:" + color + "'";
						if ((procard.procardStyle != '外购')
								|| (procard.procardStyle == '外购' && procard.needProcess == 'yes')) {
							html = html + " onclick=showProcessForSb("
									+ procard.id + ",'" + jihuoStatus + "','"
									+ procard.status + "','" + procard.hascount
									+ "','" + procard.lingliaoType + "')";
						}
						html = html
								+ "><div align='center' style='float: left;background-color: #ffffff;width:"
								+ nextWidth
								+ "%;text-overflow:ellipsis;white-space: nowrap;height:40px;'>"
								+ nextWidthCon
								+ "</div><div align='center' class='topDiv' style='float: left;background-color: #008B00;width:"
								+ width
								+ "%;text-overflow:ellipsis;white-space: nowrap;height:40px;'>"
								+ widthCon
								+ "</div><div style='clear: both;'></dvi></div>";
						$(html).appendTo("#pro" + belongLayer);

						var border = 0;
						if (obj.length - 1 != i) {
							border = 1;
						}

						//每个件号间隔
						$(
								"<div style='border-left: solid " + border
										+ "px " + borderCorlor
										+ ";height:10px;'></div>").appendTo(
								"#pro" + (belongLayer));

						//递归生成
						var sonHeight = findPro(procard.id, belongLayer + 1, i);
						if (sonHeight > 54) {
							eachHeight = eachHeight - 54 + sonHeight;
						}

					});
		//本层最后 一个div的边框清空
		$("#pro" + belongLayer + " div:last")
				.css("border", "solid 0px #000000");

		//计算高度，填补上层的高度
		var fatherHeight = (eachHeight - 54) / 2;
		var border = 0;
		if (index > 0) {
			border = 1;
		}
		//上部
		$("#pro" + (belongLayer - 1) + " div").eq(-5).before(
				"<div style='height:" + fatherHeight + ";border-left: solid "
						+ border + "px #BABABA;'></div>");
		//下部
		$(
				"<div style='border-left: solid 1px #BABABA;height:"
						+ fatherHeight + "px;'></div>").appendTo(
				"#pro" + (belongLayer - 1));

	} else {
		if (belongLayer <= maxBelongLayer) {
			for ( var i = belongLayer; i <= maxBelongLayer; i++) {
				$(jiange2).appendTo("#pro" + (i));
			}
		}
	}
	return eachHeight;
}

//显示领料详细
function showProcessForSb(id, jihuoStatus, proStatus, hascount, lingliaoType) {
	if (jihuoStatus != "wei") {
		if (proStatus == "初始" || proStatus == "已发卡") {
			$("#showLingLiaoIf").attr("src",
					"sellAction!procardLingliaonew.action?id=" + id);
			chageDiv('block');
		} else {
//			if (hascount == 0) {
//				if (lingliaoType == null || lingliaoType != "part") {
//					alert("该生产周转卡已领料!请勿重复领料!");
//				} else {
//					$("#showLingLiaoIf").attr("src",
//							"sellAction!procardLingliao.action?id=" + id);
//					chageDiv('block');
//				}

//			} else {
				$("#showLingLiaoIf").attr("src",
						"sellAction!procardLingliaonew.action?id=" + id);
				chageDiv('block');
//			}

		}
	} else {
		alert("该流水卡片尚未激活!");
	}
}
</script>
	</head>
	<body style="background-color: #ffffff;">
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; width: 1080px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 140px; right: 50px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 1080px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showLingLiaoIf" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<!-- 加载进度 -->
		<div id="processbar" align="left">
			<div class="finish"></div>
		</div>
		<center>
			<div align="center"
				style="font-weight: bolder; font-size: 20px; margin: 35 0 10 0px;">
				${procard.proName}(件号:${procard.markId} 批次:${procard.selfCard})的领料进度
				(
				<a href="javascript:;" onclick="javascript:location.reload(true)">刷新</a>)
			</div>
			<!-- 生产进度 -->
			<div id="viewProcard"
				style="width: 99%; border: solid 0px #000000; display: none;"
				align="center">

			</div>
		</center>
	</body>
</html>
