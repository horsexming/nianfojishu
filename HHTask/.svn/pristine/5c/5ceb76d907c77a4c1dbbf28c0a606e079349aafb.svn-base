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
	filter: Alpha(Opacity =                  95);
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
	$
			.ajax( {
				async : false,
				type : "post",
				url : "procardTemplateGyAction_findProByBel.action",
				data : {
					id : fatherId,
					maxBelongLayer : belongLayer
				},
				dataType : "json",
				success : function(obj) {
					if (obj.length > 0) {
						//生成子层
						$
								.each(obj, function(i, procardTemplate) {
									eachHeight += 54;
									//判断激活状态
										var bgcolor = "";
										var color = "#BABABA";
										var cursor = "pointer";
										var jihuoStatus = "wei";
										var borderCorlor = "#BABABA";
										var backGround = "#BABABA";
											borderCorlor = " #008B00";
											backGround = "#008B00";
											color = "#000000";
											cursor = "pointer";
											jihuoStatus = "all";

										//计算提交百分比
										var width = 0;
										var nextWidth = 100;
										var tjNumber = procardTemplate.tjNumber;
										if (tjNumber != null) {
											width = (procardTemplate.tjNumber
													/ procardTemplate.maxCount * 100)
													.toFixed(0);
											nextWidth = 100 - width;
										} else {
											tjNumber = 0;
										}
										var flstatus = "<br/>";
										var bili ="";
										if ( procardTemplate.procardStyle == '总成') {
											flstatus = "(<b><font color='red'>"
													+ procardTemplate.status
													+ "</font></b>)<br/>";
													bili = "1/1";
										}
										if(procardTemplate.procardStyle == '自制'||procardTemplate.danjiaojian == '单交件'){
											bili = "1/"+procardTemplate.corrCount;
											if(procardTemplate.lingliaostatus=='否'){
												flstatus = "(<b><font color='red'>"
													+ procardTemplate.status
													+ "</font></b>(<b><font color='red'>不领料</font></b>)<br/>";
<%--											}else if(procardTemplate.quanzi1==null||procardTemplate.quanzi1==""){--%>
<%--												flstatus = "(<b><font color='red'>"--%>
<%--													+ procardTemplate.status--%>
<%--													+ "</font></b>)(<b><font color='red'>请填写原材料权值</font></b>)<br/>";--%>
<%--											}else{--%>
<%--												flstatus = "(<b><font color='red'>"--%>
<%--													+ procardTemplate.status--%>
<%--													+ "</font></b>)(<b><font color='red'>"+procardTemplate.quanzi1+"："+procardTemplate.quanzi2+"</font></b>)<br/>";--%>
											}
										}
										var otherStatus = "";
										if (procardTemplate.procardStyle == '外购'){
											bili = procardTemplate.quanzi1+"/"+procardTemplate.quanzi2;
											if( procardTemplate.needProcess == 'yes') {
											flstatus = "(<b><font color='red'>"
													+ procardTemplate.status
													+ "</font></b>)<br/>";
											otherStatus = "-半成品";
											}
										}
											
										
										if ( procardTemplate.danjiaojian == '是'|| procardTemplate.danjiaojian == '单交件') {
											otherStatus = "-单交件";
										}
										var nextWidthCon = procardTemplate.proName
												+ "(<b>" + procardTemplate.procardStyle
												+ otherStatus + "</b>)"
												+ flstatus + procardTemplate.markId
												+ "(<b>完成:" +bili+ "</b>)";
										var widthCon = "";
										if (width > 60) {
											color = "#ffffff";
											widthCon = nextWidthCon;
											nextWidthCon = "";
										}

										//生成件号Div
										$(
												"<div align='right' style='cursor: "
														+ cursor
														+ "; border: solid 2px "
														+ borderCorlor
														+ ";background-color:"
														+ bgcolor
														+ ";color:"
														+ color
														+ "' onclick=showProcessForSb("
														+ procardTemplate.id
														+ ",'"
														+ jihuoStatus
														+ "','"
														+ procardTemplate.status
														+ "') ><div align='center' style='float: left;background-color: #ffffff;width:"
														+ nextWidth
														+ "%;text-overflow:ellipsis;white-space: nowrap;height:40px;'>"
														+ nextWidthCon
														+ "</div><div align='center' class='topDiv' style='float: left;background-color: #008B00;width:"
														+ width
														+ "%;text-overflow:ellipsis;white-space: nowrap;height:40px;'>"
														+ widthCon
														+ "</div><div style='clear: both;'></dvi></div>")
												.appendTo("#pro" + belongLayer);

										var border = 0;
										if (obj.length - 1 != i) {
											border = 1;
										}

										//每个件号间隔
										$(
												"<div style='border-left: solid "
														+ border
														+ "px "
														+ borderCorlor
														+ ";height:10px;'></div>")
												.appendTo(
														"#pro" + (belongLayer));

										//递归生成
										var sonHeight = findPro(procardTemplate.id,
												belongLayer + 1, i);
										if (sonHeight > 54) {
											eachHeight = eachHeight - 54
													+ sonHeight;
										}

									});
						//本层最后 一个div的边框清空
						$("#pro" + belongLayer + " div:last").css("border",
								"solid 0px #000000");

						//计算高度，填补上层的高度
						var fatherHeight = (eachHeight - 54) / 2;
						var border = 0;
						if (index > 0) {
							border = 1;
						}
						//上部
						$("#pro" + (belongLayer - 1) + " div").eq(-5).before(
								"<div style='height:" + fatherHeight
										+ ";border-left: solid " + border
										+ "px #BABABA;'></div>");
						//下部
						$(
								"<div style='border-left: solid 1px #BABABA;height:"
										+ fatherHeight + "px;'></div>")
								.appendTo("#pro" + (belongLayer - 1));

					} else {
						if (belongLayer <= maxBelongLayer) {
							for ( var i = belongLayer; i <= maxBelongLayer; i++) {
								$(jiange2).appendTo("#pro" + (i));
							}
						}
					}
				}
			})
	return eachHeight;
}

//显示工序详细
function showProcessForSb(id, jihuoStatus, proStatus) {
	//历史查看,不显示工序领取
		$("#showProcess").attr(
				"src",
				"procardTemplateGyAction_findProcardByRunCard2.action?id=" + id);
		chageDiv('block');
}
</script>
	</head>
	<body style="background-color: #ffffff;">
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
		<!-- 加载进度 -->
		<div id="processbar" align="left">
			<div class="finish"></div>
		</div>
		<center>
			<div align="center"
				style="font-weight: bolder; font-size: 20px; margin: 35 0 10 0px;">
				${procardTemplate.proName}(件号:${procardTemplate.markId} 的生产预览图
				(
				<a href="javascript:;" onclick="javascript:location.reload(true)">刷新</a>))
			</div>
			<!-- 生产进度 -->
			<div id="viewProcard"
				style="width: 99%; border: solid 0px #000000; display: none;"
				align="center">

			</div>
		</center>
	</body>
</html>
