<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<script src="javascript/radialIndicator.js">
</script>
		<style type="text/css">
.showdiv {
	border: solid 1px #000000;
	width: 100px;
	height: 100px;
	float: left;
	margin-left: 30px;
	margin-top: 50px;
	text-align: center;
	border-radius: 50%;
	cursor: pointer;
	margin-top: 30px;
}
</style>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">成本分析</span>(点击可查看明细)
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
					<iframe id="showProcard" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 4000px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div id="container"
				style="min-width: 310px; height: 400px; margin: 0 auto"></div>
			<div align="center">
				<br />
				<div style="font-weight: bolder; font-size: 20;">
					订单${om.orderNum}的成本构成明细
				</div>
				<div id="indicatorContainer" class="showdiv"
					style="background-color:green; color: #ffffff; font-weight: bolder;"
					data="rg">
					<br />
					<s:if test="om.rengongfei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.rengongfei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					利润
				</div>
				<div id="indicatorContainer" class="showdiv"
					style="background-color: #800080; color: #ffffff; font-weight: bolder;"
					data="rg">
					<br />
					<s:if test="om.rengongfei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.rengongfei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					人工成本
				</div>
				<div class="showdiv"
					style="background-color: #800080; color: #ffffff; font-weight: bolder;"
					data="sb">
					<br />
					<s:if test="om.shebeiZjFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.shebeiZjFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					设备折旧费
				</div>
				<div class="showdiv"
					style="background-color: #800080; color: #ffffff; font-weight: bolder;"
					data="ny">
					<br />
					<s:if test="om.nyxhFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.nyxhFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					能源消耗费
				</div>
				<%--<div style="clear: both;"></div>
				--%>
				<div class="showdiv"
					style="background-color: #00a3a2; color: #ffffff; font-weight: bolder;"
					data="cl">
					<br />
					<s:if test="om.clFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.clFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					材料费
				</div>
				<div class="showdiv"
					style="background-color: #00a3a2; color: #ffffff; font-weight: bolder;"
					data="wg">
					<br />
					<s:if test="om.wgFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.wgFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					外购费
				</div>
				<div class="showdiv"
					style="background-color: #00a3a2; color: #ffffff; font-weight: bolder;"
					data="fl">
					<br />
					<s:if test="om.flFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.flFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					辅料费
				</div>
				<%--<div style="clear: both;"></div>
				--%>
				<div class="showdiv"
					style="background-color: #fe6a00; color: #ffffff; font-weight: bolder;"
					data="gl">
					<br />
					<s:if test="om.glFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.glFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					管理费
				</div>
				<div class="showdiv"
					style="background-color: #fe6a00; color: #ffffff; font-weight: bolder;"
					data="clv">
					<br />
					<s:if test="om.clvFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.clvFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					差旅费
				</div>
				<div class="showdiv"
					style="background-color: #fe6a00; color: #ffffff; font-weight: bolder;">
					<br />
					<s:if test="om.canFei==null">0</s:if>
					<fmt:formatNumber type="number" value="${om.canFei}"
									pattern="0.00" maxFractionDigits="2" />
					<hr color="#ffffff" />
					餐费
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script src="https://code.highcharts.com/highcharts.js">
</script>
		<script src="https://code.highcharts.com/modules/exporting.js">
</script>
		<script type="text/javascript">
$(".showdiv").each(
		function() {
			$(this).click(
					function() {
						chageDiv("block");
						$("#showProcard").attr(
								"src",
								"orderManager_orderChengbenFenxi.action?id=${om.id}&flag="
										+ $(this).attr('data'));
					})
		});
$(function() {
	var x = ${obj[0]};
	var add = ${obj[1]};
	var all =${obj[2]};

	Highcharts.chart('container', {
		chart : {
			type : 'line'
		},
		title : {
			text : '订单${om.orderNum}的成本动态趋势图<br/>(<font style="color:#000000;font-size:12px;font-weight:bold;cursor:pointer;fill:#000000;">2016-12-20 14:00:00-2016-12-24 14:56</font>)'
		},
		subtitle : {
			text : ''
		},
		xAxis : {
			categories : x
		},
		yAxis : {
			title : {
				text : '金额(元)'
			}
		},
		plotOptions : {
			line : {
				dataLabels : {
					enabled : true
				},
				enableMouseTracking : false
			}
		},
		series : [ {
			name : '瞬态发生成本',
			data : add
		}, {
			name : '瞬态累计成本',
			data : all
		} ]
	});
});
</script>
	</body>
</html>
