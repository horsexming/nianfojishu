<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>

		<script type="text/javascript">

var winWidth = 0;
var winHeight = 0;
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
var load;
var weekds = "";
var pagePage = "${param.pagePage}";
$(function() {
	var time,week,checkDate = new Date(new Date());
                                        checkDate.setDate(checkDate.getDate() + 4 - (checkDate.getDay() || 7));
                                        time = checkDate.getTime();
                                        checkDate.setMonth(0);
                                        checkDate.setDate(1);
                                        week=Math.floor(Math.round((time - checkDate) / 86400000) / 7) + 1;
	var myDate = new Date();//获取系统当前时间
	var year=myDate.getFullYear()+"年"; //获取完整的年份(4位,1970-????)
	week =week+"周"
	$("#year").val(year) ;
	$("#weeks option").each(function(){
		var text = $(this).val();
		if(text==week){
			$(this).attr("selected","selected")
		}
	})
	var pagePage = 1;
	if (pagePage == "") {
		pagePage = 1;
	} else {
		pagePage = parseInt(pagePage);
	}
	//加载页面
 	load=function () {
		getOnline();//在线检测
		findDimensions();
		//计算每页显示数量
		var pageRows = parseInt((winHeight - 100) / 85);
		var linRows = (winHeight - 100) / 85;
		if (linRows - pageRows >= 0.7) {
			pageRows++;
		}
		
				$.ajax( {
					type : "POST",
					url : "FailureStAction!findAllFailure.action",
					data : {
						weekds : weekds,
						page : pagePage,
						rows : pageRows,
					},
					dataType : "json",
					success : function(object) {
						var obj =object[0];
						var count = object[1];
						var bhgList = object[2];
						$('#machineTable').empty();//清空表格
					//表头
					var tr1 = "<tr style=' width: 100%'>"
							+ "<th style='width: auto ' class='title'   align='left' >日期</th>"
							+ "<th style='width: auto '  class='title' align='left'>零件号</th>"
							+ "<th style='width: auto ' class='title'  align='left'>提交数量</th>"
							+ "<th style='width: auto' class='title'  align='left'>不合格数量</th>"
							;
					var tr1_1 ="";	
					$(bhgList).each(function(){
						tr1_1+="<th class='title'  style='width: auto' align='left'>"+this+"</th>";
					})
					$('#machineTable').append(tr1+tr1_1+ "</tr>");

					//填充内容
					var js = 0;
					$.each(obj, function(i, obj) {

						var tr2 = "<tr style='width: 100%'>"
								+ "<th class='detail' style='width: auto' align='left'>"
								+ obj.dateTime
								+ "</th>"
								+ "<th class='detail' style='width: auto' align='left'>"
								+ obj.markId
								+ "</th>"
								+ "<th class='detail' style='width: auto' align='right'>"
								+ obj.submitCount
								+ "</th>"
								+ "<th class='detail' style='width: auto' align='right'>"
								+ obj.failureCount
								+ "</th>";
						var tr2_1="";
						var fsdList = obj.fsdList;
					var idStr = "";
					$(bhgList).each(function(){
						var bool = true;
						for(var j=0;j<fsdList.length;j++){
							var fsd = fsdList[j];
							if(idStr.indexOf(fsd.id)<0){
								if(fsd.type == this){
									idStr+=";"+fsd.id;
									bool = false;
									tr2_1+="<th class='detail' style='width: auto' align='right'>"+fsd.badNumber+"</th>";
								}
							}else{
								continue;
							}
						}
						if(bool){
							tr2_1+="<th class='detail' style='width: auto' align='right'></th>"
						}
					})
						$('#machineTable').append(tr2+tr2_1+"</tr>");
					});

					var allPage = (Math.ceil(count / pageRows)) == 0 ? 1
							: (Math.ceil(count / pageRows));

					var tr3 = "<tr><td colspan='"+(4+bhgList.length)+"' align='center'>页码:"
							+ pagePage + "/" + allPage + "</td></tr>";
					$('#order').append(tr3);
					//					计算下一页 
					var nexPage = (pagePage + 1) * pageRows - count;
					var allPage = count / pageRows;
					if (nexPage >= pageRows) {
<%--						setInterval(nextPage, 1000 * 10);--%>
					} else {
						pagePage++;

					}

					$("#weekdsDiv").html(weekds);
				}

				});
	}

	load();//第一次加载页面

	//页面尺寸变化时重新加载
<%--	window.onresize = load;--%>

	//自动切换下一页 (20秒/次)
<%--	setInterval(nextPage2, 1000 * 20);--%>

	//下一个页面（饼图）
<%--	function nextPage() {--%>
<%--		if ("${param.pageStatus}" == "oneView") {--%>
<%--			getOnline();//在线检测--%>
<%--			window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure.jsp?pageStatus=oneView";--%>
<%--		} else {--%>
<%--			getOnline();//在线检测--%>
<%--			window.location.href = "${pageContext.request.contextPath}/FailureStAction!findAllFailureSSOnWeek.action?id=${param.id}";--%>
<%--		}--%>
<%--	}--%>
	
	function nextPage2() {
		//window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure.jsp?id=${id}";
		window.location.href = "${pageContext.request.contextPath}/System/bbs/showFailure.jsp?id=${id}&pagePage="
				+ pagePage;
	}

});

//查询
	function getNewWeekds() {
		weekds = $("#year").val() + $("#weeks").val();
		load();
	}
$(function() {
	if ("${param.pageStatus}" == "oneView") {
		$("#oneView").show();
	}
})





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
	background-color: black
}

body {
	color: white
}

.title {
	border: 1px solid black;
	width: 11%;
	float: left;
	background-color: #407eb8;
	height: 100px;
	font-weight: bolder;
	font-size: 40px;
	padding-top: 55px;
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
	<body id="aaa" style="overflow: hidden">
		<div style="clear: both;"></div>
		<div style="font-size: 24px; clear: both; margin-top: 10px;"
			align="right">
			<input value="下一页"
				onclick="javascript:window.location.href = '${pageContext.request.contextPath}/FailureStAction!findAllFailureSSOnWeek.action?id=${param.id}';"
				style="width: 80px; height: 20px; color: #ffffff; background-color: blue; font-weight: bolder;"
				type="button">
		</div>
		<div id="oneView" align="center" style="display: none">
			<input id="year" class="Wdate" type="text"
				onClick="WdatePicker({dateFmt:'yyyy年',skin:'whyGreen'})"
				value="2014年" />
			<select id="weeks">
				<s:iterator
					value="{'1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53'}"
					id="week">
					<option value="${week}周" >
						${week}周
					</option>
				</s:iterator>
			</select>
			<input type="button" value="查询" class="input" onclick="getNewWeekds()" />
		</div>
		<div id="weekdsDiv" align="center">
		</div>
		<table id="machineTable" style="width: 100%; line-height: 50px;">
		</table>
	</body>
</html>
