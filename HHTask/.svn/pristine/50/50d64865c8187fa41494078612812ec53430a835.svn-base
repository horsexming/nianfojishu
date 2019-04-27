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
	getshijan();
	//重新加载页面
	function reLoad() {
		window.location.reload();
	}

	load();//第一次加载页面

	//页面尺寸变化时重新加载
	//window.onresize = load;

	//自动切换下一页 (20秒/次)
	setInterval(load, 1000 * 20 * 10);

	//页面定时刷新(3分钟/次)
<%--	if(${total}>1){--%>
<%--		setTimeout(nextPage, 1000 * 30);--%>
<%--	}else{--%>
<%--		setTimeout(nextPage, 1000 * 30 *20);--%>
<%--	}--%>
<%----%>
<%--	//下一个页面--%>
<%--	function nextPage() {--%>
<%--		getOnline();//在线检测--%>
<%--		var cpage = ${cpage};--%>
<%--		if(${total}>cpage){--%>
<%--			cpage += 1;--%>
<%--		}else{--%>
<%--			cpage = 1;--%>
<%--		}--%>
<%--		window.location.href = "ProcardAction!showProcardMaterialHead.action?total=${total}&cpage="+cpage;--%>
<%--	}--%>
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
<%--table {--%>
<%--	border-collapse: collapse;--%>
<%--}--%>
<%----%>
<%--.table1 td,.table1 th{--%>
<%--	height:30px;--%>
<%--	font-size: 18px;--%>
<%--	padding: 0px;--%>
<%--	margin: 0px;--%>
<%--	border-collapse: collapse;--%>
<%--	border: solid #26264b;--%>
<%--	border-width: 2px;--%>
<%--	background-color: #3f4097;--%>
<%--}--%>
<%----%>
<%--.table th,.table td {--%>
<%--	border: solid #26264b;--%>
<%--	border-width: 1px;--%>
<%--	padding: 0px;--%>
<%--	font-size: 16px;--%>
<%--	background-color: #3f4097;--%>
<%--}--%>
<%--.tr1 th{--%>
<%--	border-width: 2px;--%>
<%--	padding: 0px;--%>
<%--	font-size: 16px;--%>
<%--	background-color: #188dce;--%>
<%--}--%>
<%--.tr2 th{--%>
<%--	border-width: 1px;--%>
<%--	padding: 0px;--%>
<%--	font-size: 16px;--%>
<%--	background-color: #188dce;--%>
<%--}--%>

.bbtt th,.bbtt td {
	border: solid #999;
	border-width: 0px;
	padding: 0px;
}

body {
	background-color: #26264b;
}

body {
	color: white
}


</style>
	</head>
	<body>
		<div style="width: 100%; height: 140px; color: #fff;">
			<p style="font-size: 45px; float: left; width: 39%; height: 40px;">
				<b style="margin-left: 20px;">正在检验的外购件信息:</b>
			</p>
			<p style="float: left; width: 30%;">
				<span style="font-size: 24px;">当前时间: </span><span id="shijan"
					style="font-size: 24px;"></span>
			</p>
			<p style="float: left; width: 30%; height: 40px;" align="right">
				<img alt="" src="<%=basePath%>/img/pebs_2.png">
			</p>
		</div>
		<div style="width: 100%;"  >
		<s:iterator value="list" id="djywaigoudd" status="pagestatus">
			<div style="width: 100%;">
				<div style="float: left;font-size:100px;;width: 49%;" >
					<b>
						姓名:${djywaigoudd.jyuserName}
					</b>
				</div>
				<div style="float: right;font-size: 100px;width: 49%;">
					库位:${djywaigoudd.qrWeizhi}
				</div>
			</div>
			<div style="width: 100%;height: 5%;">
				&nbsp;&nbsp;&nbsp;
			</div>
			<div style="width: 100%;">
				<div style="float: left;font-size: 100px; width: 49%;">
					件号:${djywaigoudd.markId}
				</div>
				<div style="float: right;width: 49%;">
					<img alt="" src="<%=basePath%>img/jin_1.png" width="100px;" height="200px;">
				</div>
			</div>
		</s:iterator>
<%--			<table width="100%;">--%>
<%--				<tr	align="center"  style="border-width: 2px;padding: 0px;font-size: 30px;background-color: #188dce;">--%>
<%--					<th>序号</th>--%>
<%--					<th>件号</th>--%>
<%--					<th>零件名称</th>--%>
<%--					<th>检验批次</th>--%>
<%--					<th>数量</th>--%>
<%--					<th>物料位置</th>--%>
<%--					<th>检验人</th>--%>
<%--				</tr>--%>
<%--				<s:iterator value="list" id="djywaigoudd" status="pagestatus">--%>
<%--					<tr align="center" style="border-width: 1px;padding: 0px; font-size: 20px;background-color: #3f4097;">--%>
<%--						<th>--%>
<%--							${pagestatus.index+1}--%>
<%--						</th>	--%>
<%--						<th>--%>
<%--							${djywaigoudd.markId}--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							${djywaigoudd.proName}--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							${djywaigoudd.examineLot}--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							${djywaigoudd.qrNumber}--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							${djywaigoudd.qrWeizhi}--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							${djywaigoudd.jyuserName}--%>
<%--						</th>--%>
<%--					</tr>--%>
<%--				</s:iterator>--%>
<%--			</table>--%>
			<s:if test="errorMessage!=null">
				<div style="height: auto;" align="center">
					<h3>
						<font color="#208dce" size="16px">${errorMessage}</font>
					</h3>
				</div>
			</s:if>
		</div>
	</body>
</html>
