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
var winWidth = 0;
var winHeight = 0;
var zanting = false;

$(function() {
var s1 = new Array();
var s2 = new Array();
	var id = "${cpage}";
	if(id!=''){
		$.ajax( {
		type : "POST",
		url : "FailureStAction!findFsdByweekId.action",
		data : {
				cpage:id
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				var bhgtype_div_html = "";
				var bhgnum_div_html = "";
				var bhgsumNum_div_html = "";
				var bhgpl_div_html = "";
				$(data).each(function(index,obj){
					var s3 = new Array();
					s3[0] =obj[0];
					s3[1] =obj[1];
					s2[index]=s3;
					bhgtype_div_html+= '<div class="detail">'+obj[0]+'</div>';
					bhgnum_div_html+= '<div class="detail">'+obj[1]+'</div>';
				});
				
				bhgtype_div_html+= '<div class="detail">...</div>';
				bhgnum_div_html+= '<div class="detail">...</div>';
				
				bhgsumNum_div_html = '<div class="detail" id="bhgsumNum_div" style="height: 135px; color: red; padding-top: 120px"> ${pageSize}</div>';
				bhgpl_div_html = '<div class="detail" id="bhgpl_div" style="height: 135px; color: red; padding-top: 120px">${url}%</div>';
				
				$("#bhgtype_div").html(bhgtype_div_html);
				$("#bhgnum_div").html(bhgnum_div_html);
				$("#bhgsumNum_div").html(bhgsumNum_div_html);
				$("#bhgpl_div").html(bhgpl_div_html);
					s1[0]=s2;
					plot2 = $.jqplot('chart2', s1, {
						seriesColors:['#1b1430', '#216b63', '#5b2c5f', '#b45a4e', '#dddddd', '#3f5da9', '#983f9c'],
						seriesDefaults : {
							shadow : true,
							renderer : $.jqplot.PieRenderer,
							rendererOptions : {
							 	sliceMargin: 2,
								padding: 10,
								showDataLabels : true,
								dataLabelThreshold : 1,
								//dataLabelFormatString : '%.1f%%',
							}
						},
						legend : {
							show : true,
							background : '#32228c',
							border :'hidden',
							fontSize :'20px',
						},
						gridPadding: {top:0, bottom:0, left:0, right:50},
						grid : {
							background : '#32228c',
							borderColor : '#999999',
							drawBorder: false, 
				            drawGridlines: false,
				            shadow:false,
						}
					});
			}
		}
	})
	}


});

$(function() {
	if ("${param.pageStatus}" == "oneView") {
		$("#oneView").show();
	}
})

function findDimensions() //函数：获取尺寸 
{
	//获取窗口宽度 
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;

	//获取窗口高度 
	if (window.innerHeight) {
		winHeight = window.innerHeight;
	} else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;

	//通过深入Document内部对body进行检测，获取窗口大小 
	//	if (document.documentElement && document.documentElement.clientHeight
	//			&& document.documentElement.clientWidth) {
	//		winHeight = document.documentElement.clientHeight;
	//		winWidth = document.documentElement.clientWidth;
	//	}
}
<%----%>
<%--var pagePage = "${param.pagePage}";--%>
<%--$(function() {--%>
<%--	if (pagePage == "") {--%>
<%--		pagePage = 1;--%>
<%--	} else {--%>
<%--		pagePage = parseInt(pagePage);--%>
<%--	}--%>
<%----%>
<%--	//加载页面--%>
<%--	function load() {--%>
<%--		getOnline();//在线检测--%>
<%--		findDimensions();--%>
<%----%>
<%--		//计算每页显示数量--%>
<%--		var pageRows = parseInt((winHeight - 100) / 85);--%>
<%--		var linRows = (winHeight - 100) / 85;--%>
<%--		if (linRows - pageRows >= 0.7) {--%>
<%--			pageRows++;--%>
<%--		}--%>
<%--		$.ajax( {--%>
<%--					type : "POST",--%>
<%--					url : "${pageContext.request.contextPath}/FailureStAction!showScreen.action",--%>
<%--					data : "id=${id}" + "&page=" + pagePage + "&rows ="--%>
<%--							+ pageRows,--%>
<%--					dataType : "json",--%>
<%--					success : function(object) {--%>
<%--						var pageMarkIdSum = object[0];//数据列表--%>
<%--						var count = object[0].length;	--%>
<%--					--%>
<%--					$('#order').empty();//清空表格--%>
<%--					--%>
<%--					var tr =--%>
<%--						"<div class='detail' style='width: 24.5%;'>"--%>
<%--						+	pageMarkIdSum.markId--%>
<%--						+"</div>"--%>
<%--						+"<div class='detail' style='width: 24.5%;'>"--%>
<%--						+	pageMarkIdSum.oneWeekFc--%>
<%--						+"</div>"--%>
<%--						+"<div class='detail' style='width: 24.5%;'>"--%>
<%--						+	pageMarkIdSum.oneWeekSc--%>
<%--						+"</div>"--%>
<%--						+"<div class='detail' style='width: auto;'>"--%>
<%--						+		pageMarkIdSum.frequency--%>
<%--						+"</div>"--%>
<%--					//$('#order').html(tr);				--%>
<%--					var allPage = (Math.ceil(count / pageRows)) == 0 ? 1--%>
<%--							: (Math.ceil(count / pageRows));--%>
<%--					var tr3 = "<tr><td colspan='8' align='center'>页码:"--%>
<%--							+ pagePage + "/" + allPage + "</td></tr>";--%>
<%--					$('#order').append(tr3);--%>
<%--					//计算下一页 --%>
<%--					var nexPage = (pagePage + 1) * pageRows - count;--%>
<%--					var allPage = count / pageRows;--%>
<%--					if (nexPage >= pageRows) {--%>
<%--						setInterval(nextPage, 1000 * 10);--%>
<%--						--%>
<%--					} else {--%>
<%--						pagePage++;--%>
<%----%>
<%--					}--%>
<%--				}--%>
<%--				});--%>
<%----%>
<%--	}--%>
<%--	--%>
<%--	function nextPage() {--%>
<%--		window.parent.next();--%>
<%--	}--%>
<%--	--%>
<%--	load();--%>
<%--	//自动切换下一页 (20秒/次)--%>
<%--	setInterval(nextPage2, 1000 * 30);--%>
<%----%>
<%--	//  pagePage/allPage跳转--%>
<%--	function nextPage2() {--%>
<%--		window.parent.clearsetInterval();	--%>
<%--		window.parent.next();  --%>
<%--		window.location.href = "${pageContext.request.contextPath}/ProcardAction!viewProcardList.action?id=${id}&pagePage="--%>
<%--				+ pagePage;--%>
<%--	}--%>
<%----%>
<%--});--%>
</script>
		<style type="text/css">
body {
	background-color: white;
	color: white;
}

.content {
	text-align: center;
	background: #1b1430;
	color: white;
	font-size: 60px;
	font-weight: bold;
	width: 100%;
	height: 90px;
}

.title {
	border: 1px solid black;
	width: 24.5%;
	float: left;
	background-color: #407eb8;
	height: 30px;
	font-weight: bolder;
	font-size: 26px;
	padding-top: 15px;
	color: #000000;
	text-align: center;
}

.detail {
	border: 1px solid black;
	width: 100%;
	float: left;
	background-color: #32228c;
	height: 30px;
	font-weight: bolder;
	font-size: 26px;
	padding-top: 5px;
	color: #ffffff;
	text-align: center;
}

.col {
	border: 1px solid black;
	width: 24.5%;
	float: left;
}
</style>
	</head>
	<body style="background-color: #ccc; font-family: 黑体;">
		<div id="oneView" align="center" style="display: none">
			<form action="FailureStAction!findAllFailureSSOnWeek.action"
				method="post" style="margin: 0px; padding: 0x;">
				<input type="hidden" name="pageStatus" value="${pageStatus}" />
				<input name="year" class="Wdate" type="text"
					onClick="WdatePicker({dateFmt:'yyyy年',skin:'whyGreen'})"
					value="${year}" />
				<select name="weekds">
					<option value="weekds.substring(5)">
						<s:property value="weekds.substring(5)" />
					</option>
					<s:iterator
						value="{'1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53'}"
						id="week">
						<option value="${week}周">
							${week}周
						</option>
					</s:iterator>
				</select>
				<input type="submit" value="查询" class="input" />
			</form>
		</div>

		<div style="">
			<div class="content" style="height: 63px">
				<font style="font-size: 60px; width: 30%">${weekds}</font>
					<s:if test="pageStatus=='wg'">
						外购不良汇总
					</s:if>
					<s:elseif test="pageStatus=='wg'">
						外委不良汇总
					</s:elseif>
					<s:else>
						车间不良汇总
					</s:else>
				<img src="images/pebs.png"
					style="position: absolute; right: 150px; top: 15px;">
			</div>
		</div>
		<center style="background: #000">
			<font color="yellow" style="font-size: 30px;">${errorMessage}</font>
		</center>

		<%--		<hr color="#000000" />--%>
		<div style="background: #1b1430">
			<div style="padding-right: 10px; border: solid #000 1px;"
				align="left">
				<div style="float: left; width: 60%;">
					<div class='title'>
						不合格状态
					</div>
					<div class='title'>
						不合格数量
					</div>
					<div class='title'>
						周提交总数量
					</div>
					<div class='title'>
						不良率
					</div>
					<div style="width: 100%;">
						<div class="col" id="bhgtype_div">
						</div>
						<div class="col" id="bhgnum_div">
						</div>
						<div class="col" style="height: 261px;" id="bhgsumNum_div">
						</div>
						<div class="col" id="bhgpl_div">
						</div>
					</div>

				</div>

				<!-- ----------------------------------------------------------------- -->
				<div id="chart2"
					style="color: #fff; float: left; width: 40%; background-color: #32228c;"></div>
			</div>

			<div style="clear: both;">
				<div class="content" style="height: 63px">
					各件号不合格品数量统计
				</div>
				<div class="title">
					件号
				</div>
				<div class="title">
					不合格品数量
				</div>
				<div class="title">
					周提交总数
				</div>
				<div class="title">
					发生频率(PPM)
				</div>
				<div id="order">
					<s:iterator id="pageMarkIdSum" value="list2">
						<div class="detail" style="width: 24.5%;">
							${pageMarkIdSum.markId}
						</div>
						<div class="detail" style="width: 24.5%;">
							<fmt:formatNumber type="number"
								value="${pageMarkIdSum.oneWeekFc} " maxFractionDigits="0" />
						</div>
						<div class="detail" style="width: 24.5%;">
							<fmt:formatNumber type="number"
								value="${pageMarkIdSum.oneWeekSc} " maxFractionDigits="0" />
						</div>
						<div class="detail" style="width: 24.5%;">
							<fmt:formatNumber type="number"
								value="${pageMarkIdSum.frequency} " maxFractionDigits="0" />
						</div>
					</s:iterator>

				</div>
			</div>
			<%--				<input value="下一页"--%>
			<%--					onclick="javascript:window.location.href = '${pageContext.request.contextPath}/screen_printScreen2.action?screen.id=${param.id}';"--%>
			<%--					style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;float:right;"--%>
			<%--					type="button">--%>

		</div>

	</body>
</html>
