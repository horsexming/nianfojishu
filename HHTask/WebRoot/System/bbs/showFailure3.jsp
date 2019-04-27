<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>

		<title>${companyInfo.shortName}电子看板</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>

		<script type="text/javascript">

$(function() {

	//自动切换下一页 (20秒/次)
	setInterval(nextPage, 1000 * 20);

	//下一个页面（饼图）
	function nextPage() {
		if ("${param.pageStatus}" != "oneView") {
			getOnline();//在线检测
			window.location.href = "${pageContext.request.contextPath}/screen_printScreen2.action?screen.id=${param.id}";
		} else {
			//window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure3.jsp";
		}
	}
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
		<!-- core -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/jquery.jqplot.js">
</script>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/jquery.jqplot.css"
			type="text/css"></link>
		<!--[if IE]><script language="javascript" type="text/javascript" src="../jqplot/excanvas.js"></script><![endif]-->

		<!-- plugin -->
		<!-- 横坐标类别 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/plugins/jqplot.categoryAxisRenderer.js">
</script>
		<!-- 高亮显示 -->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/tubiao/plugins/jqplot.highlighter.js">
</script>
		<script class="code" type="text/javascript">
$(function() {

	$
			.ajax( {
				type : "POST",
				url : "FailureStAction!findAllFsMarkId.action",
				dataType : "json",
				success : function(object) {
					var data = object[0];//数据
				var ticksa = object[1];//周数

				//生成横向周列
				var week = "<tr><th>件号 \\ 周</th>";
				for ( var j = 0; j < ticksa.length; j++) {
					week += "<th>" + ticksa[j] + "</th>";
				}
				week += "</tr>";

				/*多个客户显示*/
				for ( var i = 0; i < data.length; i++) {
					client = data[i].client;//客户名称
				allLine = data[i].frequency;//PPM
				mf = data[i].mf;//件号&PPM

				$("<div id='client" + i + "'></div>").appendTo("#showData");
				//生成数据展示table
				$(
						"<table id='showDataTab"
								+ i
								+ "' class='table' style='width:100%;'></table>")
						.appendTo("#client" + i);
				//标题
				$(
						"<tr><th align='center' colspan='53' height='50px;'>产品一次提交不合格品率趋势图 (PPM) ("
								+ client + ")</th></tr>").appendTo(
						"#showDataTab" + i);
				//表头
				$(week).appendTo("#showDataTab" + i);
				//填充数据
				for ( var j = 0; j < mf.length; j++) {
					var clientMf = mf[j];
					var tr = "<tr align='center'>";
					for ( var k = 0; k < clientMf.length; k++) {
						tr += "<td>" + clientMf[k] + "</td>";
					}
					tr += "</tr>";
					$(tr).appendTo("#showDataTab" + i);
				}

				//生成折线图
				$(
						"<div id='chart"
								+ i
								+ "' style='height: 300px; width: 98%;'></div><br/><br/>")
						.appendTo("#client" + i);

				plot = $.jqplot('chart' + i, allLine, {
					axes : {
						xaxis : {
							ticks : ticksa,//设置横（纵）坐标的刻度上的值，可为该ticks数组中的值
							renderer : $.jqplot.CategoryAxisRenderer
						}
					},
					grid : {
						shadow : true,
						background : '#000000'
					}
				});
			}
		}

			});
});
</script>
	</head>

	<body>
		<div id="showData">
		</div>
	</body>
</html>