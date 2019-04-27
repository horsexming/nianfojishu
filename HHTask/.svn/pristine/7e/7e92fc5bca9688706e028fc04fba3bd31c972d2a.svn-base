<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">个人车辆使用补贴汇总</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<input type="button"  style="width:120px;height:40px" value="申请登记" onclick="appDengji()" />
					&nbsp;&nbsp;&nbsp;
					<input type="button"  style="width:120px;height:40px" value="申请记录" onclick="appHistory()" />
					&nbsp;&nbsp;&nbsp;
					<input type="button"  style="width:120px;height:40px" value="补贴申领" onclick="butieApp()" />
					
					&nbsp;&nbsp;&nbsp;
					<input type="button"  style="width:120px;height:40px" value="申领记录" onclick="butieAppHistory()" />
					
					&nbsp;&nbsp;&nbsp;
					<input type="button" style="width:120px;height:40px"  value="个人充值记录" onclick="appchognzhiHistory()" />
					&nbsp;&nbsp;&nbsp;
				</div>
			</div>

			<div style="font-size: 25px;">个人车补汇总信息表</div>
			<br/>
			<table class="table">
			<tr style="height: 35px;"><th colspan="4">基本信息</th></tr>
			<tr>
			<th>姓名</th><th>${carAllowSum.name}</th><th>工号</th><th>${carAllowSum.code}</th></tr>
			<tr>
			<th>车牌</th><th>${carAllowSum.platenumber}</th><th>充值额度</th><th>${carAllowSum.chognzhiedu}/月</th>
			</tr>
			<tr>
			<th>最后申请时间</th><th>${carAllowSum.lastChongzhiMonth}</th><th>单次充值上限</th><th>${carAllowSum.chongzhiMax}/次</th>
			</tr>
			
			<tr style="height: 35px;"><th colspan="4">申请补助汇总</th></tr>
			<tr>
			<th>过路费</th><th>${carAllowSum.roadcost}</th><th>保险费</th><th>${carAllowSum.insurancecost}</th>
			</tr>
			<tr>
			<th>停车费</th><th>${carAllowSum.parkcost}</th><th>其他费用</th><th>${carAllowSum.repaircost}</th>
			</tr>		
			
			<tr style="height: 35px;"><th colspan="4">结余</th></tr>
			<tr>
			<th>累计总费用</th><th>${carAllowSum.sumcost}</th><th>充值总费用</th><th>${carAllowSum.sumchognzhi}</th>
			</tr>
			<tr>
			<th>申请余额(待用)</th><th>${carAllowSum.sumremainbaoxiao}</th>
			<th>当前可申领费用</th><th>${carAllowSum.sumbaoxiao}</th>
			</tr>
			
			</table>
			
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
	</body>
<script type="text/javascript">
//申请登记
function appDengji() {
	var id1=${carAllowSum.id};
	//alert(id1);
	var url = "carAllowAction!appDengji.action?id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//个人申请历史记录
function appHistory() {
	var id1=${carAllowSum.id};
	//alert(id1);
	var url = "carAllowAction!appHistory.action?id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//个人充值历史记录
function appchognzhiHistory() {
	var id1=${carAllowSum.id};
	//alert(id1);
	var url = "carAllowAction!appchognzhiHistory.action?tag=cz&id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//补贴申领申请
function butieApp(){
	var id1=${carAllowSum.id};
	//alert(id1);
	var url = "carAllowAction!preshenling.action?tag=bx&id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//补贴申领历史记录
function butieAppHistory(){
	var id1=${carAllowSum.id};
	//alert(id1);
	var url = "carAllowAction!appchognzhiHistory.action?tag=bx&id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>		

</html>
