<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
function login() {
	$("#tcIframe").attr("src", "<%=basePath%>login.jsp");
	chageDiv("block");
}
function tozuye() {
	window.location.href = "ModuleFunctionAction!findMfByUser.action?pageStatus=qx";
}

$(function() {
	//重新加载页面
	//function reLoad() {
	//	window.location.reload();
	//}

	//load();//第一次加载页面

	//页面尺寸变化时重新加载
	//window.onresize = load;

	//自动切换下一页 (20秒/次)
	//setInterval(load, 1000 * 20 * 10);

	//页面定时刷新(3分钟/次)
	if(${total}>1){
		setTimeout(nextPage, 1000 * 30);
	}else{
		setTimeout(nextPage, 1000 * 30 *20);
	}

	//下一个页面
	function nextPage() {
		getOnline();//在线检测
		var cpage = ${cpage};
		if(${total}>cpage){
			cpage += 1;
		}else{
			cpage = 1;
		}
		window.location.href = "ProcardAction!showProcardMaterialHead.action?total=${total}&cpage="+cpage;
	}
});

function getshijan(){
	var  date = new Date();
	var month = date.getMonth();//月份
	var day = date.getDate();// 日
	var hours = date.getHours();// 小时
	var minutes = date.getMinutes();//分
	var seconds = date.getSeconds();//秒
	if(seconds<10){
		seconds="0"+seconds;
	}
	if(minutes<10){
		minutes = "0"+minutes;
	}
	if(hours<10){
		hours = "0"+hours;
	}
	if(day<10){
		day = "0"+day;
	}
	month+=1;
	if(month<10){
		month = "0"+month;
	}
	var shijian = date.getFullYear()+"."+month+"."+day+" "+hours+":"+minutes+":"+seconds;
	$("#shijan").html(shijian);
}
setInterval("getshijan()", 1000 );
</script>
		<style type="text/css">
table {
	border-collapse: collapse;
}

.table1 td,.table1 th {
	height: 30px;
	font-size: 18px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #26264b;
	border-width: 2px;
	background-color: #3f4097;
}

.table th,.table td {
	border: solid #26264b;
	border-width: 1px;
	padding: 0px;
	font-size: 16px;
	background-color: #3f4097;
}

.tr1 th {
	border-width: 2px;
	padding: 0px;
	font-size: 16px;
	background-color: #188dce;
}

.tr2 th {
	border-width: 1px;
	padding: 0px;
	font-size: 16px;
	background-color: #188dce;
}

.bbtt th,.bbtt td {
	border: solid #999;
	border-width: 0px;
	padding: 0px;
}

body {
	background-color: #1b1430;
}

body {
	color: white
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
	width: 13%;
	float: left;
	background-color: #407eb8;
	height: 40px;
	font-weight: bolder;
	font-size: 26px;
	padding-top: 15px;
	color: #000000;
	text-align: center;
}

.detail {
	border: 1px solid black;
	width: 13%;
	float: left;
	background-color: #32228c;
	height: 40px;
	font-weight: bolder;
	font-size: 26px;
	padding-top: 15px;
	color: #ffffff;
	text-align: center;
}
</style>
	</head>
	<body>
		<p style="width: 30%;">
			<!--<span style="font-size: 24px;">当前时间: </span>-->
			<span id="shijan" style="font-size: 24px;"></span>
		</p>
		<div class="content"
			style="background-image: url(images/screen_title_pebs2.png);">
			物料催配查看
		</div>
		<!-- 
		<div style="width: 100%; height: 140px; color: #fff;">
			
			
			<p style="font-size: 45px; float: left; width: 39%; height: 40px;">
				<b style="margin-left: 20px;">物料催配查看</b>
			</p>
			<p style="float: left; width: 30%; height: 40px;" align="right">
				<img alt="" src="<%=basePath%>/img/pebs_2.png">
			</p>
			
		</div>
		-->
		<div style="width: 100%;">
			<s:iterator value="listPMaterHe" id="listNum" status="pageIndex">
				<div style="width: 25%">
					<div style="width: 30%; float: left;">
						<div class="title" style="width: 100%; background-color: #71c41b;">
							叫料人
						</div>
						<div class="title" style="width: 100%">
							总成
						</div>
						<div class="title" style="width: 100%">
							批次
						</div>
						<div class="title" style="width: 100%">
							数量
						</div>
						<div class="title" style="width: 100%">
							状态
						</div>
					</div>
					<div style="width: 65%; float: left;">
						<div class="title" style="width: 100%; background-color: #71c41b;">
							${pMH.userName}
						</div>
						<div class="detail" style="width: 100%">
							${pMH.markId}
						</div>
						<div class="detail" style="width: 100%">
							${pMH.selfCard}
						</div>
						<div class="detail" style="width: 100%">
							${pMH.thisCount}
						</div>
						<div class="detail" style="width: 100%">
							${pMH.lingliaoStatus}
						</div>
					</div>

				</div>

				<div style="float: right; width: 74%">
					<div class="title" style="width: 8%">
						序号
					</div>
					<div class="title" style="width: 15%">
						件号
					</div>
					<div class="title" style="width: 15%">
						规格
					</div>
					<div class="title" style="width: 18%">
						数量
					</div>
					<div class="title" style="width: 15%">
						单位
					</div>
					<div class="title" style="width: 10%">
						类型
					</div>
					<div class="title" style="width: 10%">
						库位
					</div>
					<s:iterator value="#listNum.pM" id="pageList" status="pageStatus">
						<div class="detail" style="width: 8%">
							${pageList.markId}
						</div>
						<div class="detail" style="width: 15%">
							20160800014
						</div>
						<div class="detail" style="width: 15%">
							${pageList.specification}
						</div>
						<div class="detail" style="width: 18%">
							${pageList.thecount}
						</div>
						<div class="detail" style="width: 15%">
							${pageList.unit}
						</div>
						<div class="detail" style="width: 10%">
							${pageList.type}
						</div>
						<div class="detail" style="width: 10%">
							W5-C
						</div>
					</s:iterator>
				</div>
			</s:iterator>
		</div>


	</body>
</html>
