<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="javascript/BarIndicator/bi-style.css" rel="stylesheet" />
		<%@include file="/util/sonHead.jsp"%>
		<script
			src="${pageContext.request.contextPath}/javascript/jquery.percentageloader-0.1.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/radialIndicator.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/BarIndicator/jquery.easing.1.3.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/BarIndicator/jquery-barIndicator.js">
</script>
		<style type="text/css">
.content {
	background-color: #000;
	text-align: center;
	color: white;
	width: 100%;
	font-size: 60px;
	font-weight: bold;
	padding-top: 30px;
}

.title {
	border: 1px solid black;
	width: 11%;
	float: left;
	background-color: #407eb8;
	height: 65px;
	font-weight: bolder;
	font-size: 40px;
	padding-top: 40px;
	color: #000000;
	text-align: center
}

.detail {
	border: 1px solid black;
	width: 11%;
	float: left;
	background-color: #32228c;
	height: 65px;
	font-weight: bolder;
	font-size: 45px;
	padding-top: 40px;
	color: #ffffff;
	text-align: center
}
</style>
	</head>
	<body style="color: white; background-color: #ccc;">
		<div class="content" style="">
			${successMessage} 生产任务列表
			<img src="images/pebs.png"
				style="position: absolute; right: 150px; top: 20px;">
			<br />
			<br />
		</div>

		<div id="order">
		</div>
		<div id="order">
		</div>
		<%--		<div style="float: right;">--%>
		<%--			<!--			<input value="下一页"-->--%>
		<%--			<!--				onclick="javascript:window.location.href = '${pageContext.request.contextPath}/FailureStAction!findAllFailureSSOnWeek.action?id=${id}';"-->--%>
		<%--			<!--				style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;"-->--%>
		<%--			<!--				type="button">-->--%>
		<%--			<input value="下一页"--%>
		<%--				onclick="javascript:window.location.href = '${pageContext.request.contextPath}/FailureStAction!findAllFailureSSOnWeek.action?id=${id}';"--%>
		<%--				style="width: 80px; height: 40px; color: #ffffff; background-color: blue; font-weight: bolder;"--%>
		<%--				type="button">--%>
		<%----%>
		<%----%>
		<%--		</div>--%>
		<script type="text/javascript">
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
$('#order').empty();//清空表格

//添加表头
var head = "";
var name = "${Users.name}";
if (name != null && name.length > 0) {
} else {
}
var tr1 = head
		+ "<div class='title' style='width: 4%;padding-top: 20px;height: 85px;'>序<br/><br/>号</div>"
		+ "<div class='title' style='width: 14%'>件号</div>"
		+ "<div class='title' style='width: 12%'>名称</div>"
		+ "<div class='title' style='width: 16%;'>可领人员</div>"
		+ "<div class='title' style='width: 6%'>批次</div>"
		+ "<div class='title' style='width: 9%;padding-top: 20px;height: 85px;'>生产<br/><br/>开始时间</div>"
		+ "<div class='title' style='width: 9%;padding-top: 20px;height: 85px;'>最迟<br/><br/>入库时间</div>"
		+ "<div class='title' style='width: 7%'>数量</div>"
		+ "<div class='title' style='width: 7%'>状态</div>"
		+ "<div class='title' style='width: 14%'>进度</div><div style='clear: both;'></div>"
$('#order').append(tr1);

var pagePage = "${param.pagePage}";
$(function() {
	if (pagePage == "") {
		pagePage = 1;
	} else {
		pagePage = parseInt(pagePage);
	}
	
	//加载页面
	function load() {
		getOnline();//在线检测
		findDimensions();

		//计算每页显示数量
		var pageRows = parseInt((winHeight - 270) / 107);
		var linRows = (winHeight - 270) / 107;
		if (linRows - pageRows >= 0.7) {
			pageRows++;
		}

		$
				.ajax( {
					type : "POST",
					url : "${pageContext.request.contextPath}/ProcardAction!showScreen2.action",
					data : "id=${id}" + "&page=" + pagePage + "&rows ="
							+ pageRows,
					dataType : "json",
					async : false,
					success : function(object) {
						var data = object[0];//数据列表
					var count = object[1];//数据列表
					
					//填充内容 
					for ( var i = 0; i < data.length; i++) {
						var peopleList = data[i].processPeopleList;//领取人员列表
						var peopleteable = "";
						if (peopleList != null) {
							for ( var j = 0; j < peopleList.length; j++) {
								if ((j % 6) == 0) {
									peopleteable += "<tr>"
								}
								peopleteable += "<td>" + peopleList[j].name
										+ "</td>";
								if ((j % 6 + 1) == 0) {
									peopleteable += "</tr>"
								}
								if (j == 23)
									break;
							}
							if (peopleteable != "") {
								peopleteable = "<table class='table'>"
										+ peopleteable + "</table>";

							}
						}
						var index = i + 1;
						if (data[i].jihuoDate != null) {
							var jihuoDatesub = data[i].jihuoDate.substring(0,
									10);
							var jihuoDatesub2 = data[i].jihuoDate.substring(10,
									20);
						}
						if (data[i].needFinalDate != null) {
							var needFinalDatesub = data[i].needFinalDate
									.substring(0, 10);
							var needFinalDatesub2 = data[i].needFinalDate
									.substring(10, 20);
						}
						var name = data[i].proName;
						if (data[i].proName == null) {
							name="";
						}
						if (data[i].proName != null
								&& data[i].proName.length < 8) {
							name = "<div class='detail' style='width: 12%;font-size: 30px;text-align:left;word-wrap:break-word;line-height:28px;padding-top:30px;height:75px;line-height:32px;text-align:left;'>"
									+ name + "</div>"

						} else {
							name = "<div class='detail' style='width: 12%;font-size: 30px;text-align:left;word-wrap:break-word;line-height:28px;padding-top:5px;height:100px;line-height:32px;text-align:left;'>"
									+ name + "</div>"

						}
						var tr = "<div class='detail' style='width: 4%'>"
								+ index
								+ "</div>"
								+ "<div class='detail' style='width: 14%;font-size: 30px;text-align:left;word-wrap:break-word;line-height:32px;text-align:left;'>"
								+ data[i].markId
								+ "</div>"
								+ name
								+ "<div class='detail' style='width: 16%;padding-top:0px;height:105px;'>"
								+ peopleteable
								+ "</div>"
								+ "<div class='detail' style='width: 6%;padding-top:30px;height:75px;font-size:30px;text-align:right;word-wrap:break-word;line-height:32px;'>"
								+ data[i].selfCard
								+ "</div>"
								+ "<div class='detail' style='width: 9%;padding-top: 20px;height: 85px;text-align:right;'>"
								+ "<span style='font-size: 30px;'>"
								+	jihuoDatesub
								+ "</span><br/><br/>"
								+ "<span style='color:Yellow;font-size: 30px;'>"
								+ 	jihuoDatesub2
								+ "</span>"
								+ "</div>"
								+ "<div class='detail' style='width: 9%;padding-top: 20px;height: 85px;text-align:right;'>"
								+ "<span style='font-size: 30px;'>"
								+ needFinalDatesub
								+ "</span><br/><br/>"
								+ "<span style='color:Yellow;font-size: 30px;'>"
								+ needFinalDatesub2
								+ "</span>"
								+ "</div>"
								+ "<div class='detail' style='width: 7%;font-size: 30px;'>"
								+ data[i].filnalCount
								+ "</div>"
								+ "<div class='detail' style='width: 7%;font-size: 30px;'>"
								+ data[i].status
								+ "</div>"
								+ "<div class='detail rkjindu' data='"
								+ data[i].lingliaoren
								+ "' style='padding-top:40px;font-size: 30px;width: 14%;background-color: #000000;'>"
								+ data[i].lingliaoren
								+ "</div></div><div style='clear: both;'></div>"

						$('#order').append(tr);
						/*//						$(".rkjindu").each(function(i) {
						//							var bar= parseInt(data[i].lingliaoren);
						//							$(this).barIndicator({
						var bar = parseInt(data[i].lingliaoren);
						$(".rkjindu" + i).barIndicator( {
							milestones : false,
							data : bar,
							horBarHeight : 105,
							backColor : '#000000',
							animTime : 1000,
							horLabelPos : 'right',
							colorRange : true,
							colorRangeLimits : {
								newRangeOne : '0-40-#f80043',
								newRangeTwo : '41-60-#ffff00',
								newRangeThree : '61-100-#7fc41c'
							}
						});*/
					}
					var allPage = (Math.ceil(count / pageRows)) == 0 ? 1
							: (Math.ceil(count / pageRows));

					var tr3 = "<tr><td colspan='8' align='center'>页码:"
							+ pagePage + "/" + allPage + "</td></tr>";
					$('#order').append(tr3);
					//					计算下一页 
					var nexPage = (pagePage + 1) * pageRows - count;
					var allPage = count / pageRows;
					if (nexPage >= pageRows) {
						setInterval(nextPage, 1000 * 60*5);
					} else {
						pagePage++;

					}
				}
				});
		testStyle();
	}

	function testStyle() {
		$(".rkjindu").each(function(i) {
			var hk_val = $(this).attr('data');
			var bar = parseInt(hk_val);
			$(this).barIndicator( {
				milestones : false,
				data : bar,
				horBarHeight : 110,
				backColor : '#000',
				horLabelPos : 'right',
				//labelVisibility:'hidden',
				colorRange : true,
				colorRangeLimits : {
					newRangeOne : '0-40-#f80043',
					newRangeTwo : '41-60-#FF800A',
					newRangeThree : '61-100-#7fc41c'
				}
			});
		});
	}

	//重新加载页面
	//	function reLoad() {
	//		//window.location.reload();
	//	}

	load();//第一次加载页面

	//页面尺寸变化时重新加载
	//window.onresize = load;

	//自动切换下一页 (20秒/次)
	setInterval(nextPage2, 1000 * 60*5);

	//下一个页面（饼图）
	function nextPage() {
		window.parent.clearsetInterval();
		window.parent.next();
<%--		getOnline();//在线检测--%>
<%--		//window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure.jsp?id=${id}";--%>
<%--		window.location.href = "${pageContext.request.contextPath}/FailureStAction!findAllFailureSSOnWeek.action?id=${id}";--%>
	}

	//
	function nextPage2() {
		window.parent.clearsetInterval();
		window.parent.auto2nextpage(60);
		//window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure.jsp?id=${id}";
		window.location.href = "${pageContext.request.contextPath}/ProcardAction!viewProcardList.action?id=${id}&pagePage="
				+ pagePage;
	}

});
</script>
	</body>
</html>
