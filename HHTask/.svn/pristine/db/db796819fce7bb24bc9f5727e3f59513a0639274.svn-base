<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML >
<html>
	<head>

		<title>${companyInfo.shortName}电子看板</title>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/DatePicker/WdatePicker.js">
</script>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/jquery.jqplot.css" />

		<!-- BEGIN: load jquery -->
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<!-- END: load jquery -->

		<!-- BEGIN: load jqplot -->
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/jquery.jqplot.js">
</script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/jqplot.funnelRenderer.js">
</script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/jqplot.pieRenderer.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>
		<!-- END: load jqplot -->

		<script type="text/javascript">

$(function() {
	var s1 = [[ [ '及时到货',${ms.completionRate*100}],[ '未到货',${(1-ms.completionRate)*100}] ]];
	plot2 = $.jqplot('chart', s1, {
		title : {
			text:'到货及时率',
			color:'#ffffff'
		},
		seriesDefaults : {
			shadow : true,
			renderer : $.jqplot.PieRenderer,
			rendererOptions : {
				showDataLabels : true,
				dataLabelThreshold : 1,
				dataLabelFormatString : '%.1f%%'
			}
		},
		legend : {
			show : true,
			placement : 'outside'
		},
		grid : {
			background : '#000000',
			borderColor : '#999999'
		}
	});
	var s2 = [[ [ '及时完成',67.4546],[ '未完成',32.5454] ]];
	plot2 = $.jqplot('chart2', s2, {
		title : {
			text:'订单完成率',
			color:'#ffffff'
		},
		seriesDefaults : {
			shadow : true,
			renderer : $.jqplot.PieRenderer,
			rendererOptions : {
				showDataLabels : true,
				dataLabelThreshold : 1,
				dataLabelFormatString : '%.1f%%'
			}
		},
		legend : {
			show : true,
			placement : 'outside'
		},
		grid : {
			background : '#000000',
			borderColor : '#999999'
		}
	});
   var s3 = [[ [ '及时开票',${ms3.completionRate*100}],[ '未开票',${(1-ms3.completionRate)*100}] ]];
	plot3 = $.jqplot('chart3', s3, {
		title : {
			text:'开票及时率',
			color:'#ffffff'
		},
		seriesDefaults : {
			shadow : true,
			renderer : $.jqplot.PieRenderer,
			rendererOptions : {
				showDataLabels : true,
				dataLabelThreshold : 1,
				dataLabelFormatString : '%.1f%%'
			}
		},
		legend : {
			show : true,
			placement : 'outside'
		},
		grid : {
			background : '#000000',
			borderColor : '#999999'
		}
	});
	var s4 = [[ [ '及时回款',${ms4.completionRate*100}],[ '未及时回款',${(1-ms4.completionRate)*100}] ]];
	plot4 = $.jqplot('chart4', s4, {
		title : {
			text:'回款完成及时率',
			color:'#ffffff'
		},
		seriesDefaults : {
			shadow : true,
			renderer : $.jqplot.PieRenderer,
			rendererOptions : {
				showDataLabels : true,
				dataLabelThreshold : 1,
				dataLabelFormatString : '%.1f%%'
			}
		},
		legend : {
			show : true,
			placement : 'outside'
		},
		grid : {
			background : '#000000',
			borderColor : '#999999'
		}
	});
});
	
</script>
		<style type="text/css">
table {
	border-collapse: collapse;
}

.table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #999 0px;
	border-width: 0px;
}

.table th,.table td {
	border: solid #999;
	border-width: 1px;
	padding: 0px;
}

.bbtt th,.bbtt td {
	border: solid #999;
	border-width: 0px;
	padding: 0px;
}

body {
	background-color: black;
}

body {
	color: white
}
</style>
	</head>
	<body style="overflow: hidden; background-color: #000000;">
		<div id="oneView" align="center">
			<form action="ProcardAction!showDaohuoLv.action" method="post"
				style="margin: 0px; padding: 0x;">
				<input type="hidden" name="pageStatus" value="${pageStatus}" />
				<input class="Wdate" type="text" name="weekds"
					onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
				<input type="submit" value="查询" class="input" />
			</form>
		</div>
		<div align="center">
			${weekds}
			<font color="yellow">${errorMessage}</font>
		</div>
		<hr color="#000000" />
		<div>
			<div
				style="float: left; width: 49%; padding-right: 10px; border: solid #000 1px;"
				align="left">
				<s:if test="ms!=null">
					${ms.month}的到货及时率为:<a target="showLv"
						href="ProcardAction!findWgWwPlanList.action?pageStatus=wgPlan&ms.id=${ms.id}">${ms.completionRate*100}%</a>
				</s:if>
				<s:else>
					<font color="red">不存在${weekds}的到货及时率信息!</font>
				</s:else>
				<div id="chart" style="width: 80%;"></div>
			</div>
		</div>
		<div>
			<div
				style="float: left; width: 49%; padding-right: 10px; border: solid #000 1px;"
				align="left">
				<s:if test="ms!=null">
					${ms.month}的订单完成率为:<a target="showLv"
						href="orderManager_queryOrderManagerByCondition.action">67.4546%</a>
				</s:if>
				<s:else>
					<font color="red">不存在${weekds}的到货及时率信息!</font>
				</s:else>
				<div id="chart2" style="width: 80%;"></div>
			</div>
		</div>
		<div>
			<div
				style="float: left; width: 49%; padding-right: 10px; border: solid #000 1px;"
				align="left">
				<s:if test="ms3!=null">
					${ms3.month}的开票及时率为:<a target="showLv"
						href="ProcardAction!findWgWwPlanList.action?pageStatus=wgPlan&ms3.id=${ms3.id}">${ms3.completionRate*100}%</a>
				</s:if>
				<s:else>
					<font color="red">不存在${weekds}的开票及时率信息!</font>
				</s:else>
				<div id="chart3" style="width: 80%;"></div>
			</div>
		</div>
		<div>
			<div
				style="float: left; width: 49%; padding-right: 10px; border: solid #000 1px;"
				align="left">
				<s:if test="ms4!=null">
					${ms4.month}的回款完成及时率为:<a target="showLv"
						href="ProcardAction!findWgWwPlanList.action?pageStatus=wgPlan&ms.id=${ms4.id}">${ms4.completionRate*100}%</a>
				</s:if>
				<s:else>
					<font color="red">不存在${weekds}的回款完成及时率信息!</font>
				</s:else>
				<div id="chart4" style="width: 80%;"></div>
			</div>
		</div>
	</body>
</html>
