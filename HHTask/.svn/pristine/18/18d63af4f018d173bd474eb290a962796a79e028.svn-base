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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div> 总成件号：${procard.markId}&nbsp;&nbsp;&nbsp;业务件号：${procard.ywMarkId}&nbsp;&nbsp;&nbsp;订单号：${procard.orderNumber}&nbsp;&nbsp;&nbsp;批产数量${procard.filnalCount}
				</div>
				<div>
					<form action="ProcardAction!procardOutww.action" method="post" onsubmit="return lq();">
					<input type="hidden" value="${procard.id}" name="id">
					<table class="table">
					 <tr>
					 	<td align="center"> 序号</td>
					 	<td align="center">件号</td>
					 	<td align="center">版本</td>
					 	<td align="center">批次</td>
					 	<td align="center">名称</td>
					 	<td align="center">外委工序号</td>
					 	<td align="center">外委工序名</td>
					 	<td align="center">下工序</td>
					 	<td align="center">可领数量</td>
					 	<td align="center">已领数量</td>
					 	<td align="center">批次数量</td>
					 	<td align="center">
					 		<table style="margin: 0px;padding: 0px;">
					 		<tr style="margin: 0px;padding: 0px;">
					 		<td align="center" width="130px;" style="border:0px; margin:0px;padding:0px;">外委单号</td>
					 		<td align="center" width="20px;" style="border-bottom: 0px;border-top: 0px; margin: 0px;padding: 0px;">外委状态</td>
					 		<td align="center" width="30px;" style="border:0px; margin: 0px;padding: 0px;">外委数量</td>
					 		</tr></table>
						</td>
					 	<td align="center">
					 		<table style="margin: 0px;padding: 0px;">
					 		<tr>
					 		<td align="center" width="100px;" style="border:0px;">库存批次</td>
					 		<td align="center" width="30px;" style="border-bottom:0px;border-top: 0;px;border-right: 0px; margin: 0px;padding: 0px;">对应数量</td>
					 		</tr>
					 		</table>
						</td>
					 	
					 	
					 	
					 </tr>
					 <s:iterator value="list" id="pvo" status="pvoindex">
					 	<s:if test="#pvo.wwylCount==#pvo.filnalCount">
						<tr align="center" bgcolor="green" >
					 	</s:if>
					 	<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					 	</s:else>
					<td>
						<s:if test="#pvoindex.index%2==1">
							<font>
						</s:if>
						<s:else>
							<font color="#c0dcf2">
						</s:else>
						<b><s:property value="#pvoindex.index+1" />
						</b>
						</font>
					</td>
					<td align="right"><s:property value="#pvo.markId"/></td>
					<td align="right"><s:property value="#pvo.banBenNumber"/></td>
					<td align="right"><s:property value="#pvo.selfCard"/></td>
					<td align="right"><s:property value="#pvo.proName"/></td>
					<td align="right"><s:property value="#pvo.processnos"/></td>
					<td align="right"><s:property value="#pvo.processnames"/></td>
					<td align="right"><s:property value="#pvo.nextProcessName"/></td>
					<td align="left"><s:property value="#pvo.wwklCount"/></td>
					<td align="left"><s:property value="#pvo.wwylCount"/></td>
					<td align="left"><s:property value="#pvo.filnalCount"/></td>
					<td align="right">
						<table >
							<s:iterator value="#pvo.wwshowList" id="wwshow">
							<tr>
								<td align="right" width="130px;" style="border: 0px;"><s:property value="#wwshow.wwnumber"/></td>
								<td align="right" width="20px;" style="border-bottom: 0px;border-top: 0px;"><s:property value="#wwshow.wwstatus"/></td>
								<td align="left" width="30px;" style="border: 0px;"><FONT size="1"><s:property value="#wwshow.wwcount"/></FONT></td>
							</tr>
							</s:iterator>
						</table>
					</td>
					<td align="right">
						<table border="0">
							<s:iterator value="#pvo.goodsList" id="wwgoods">
							<tr>
								<td align="right" width="100px;" style="border: 0px;"><FONT size="1"><s:property value="#wwgoods.goodsLotId"/></FONT>
									<s:if test="#wwgoods.dtcFlag!=null&&#wwgoods.dtcFlag=='外协调委外'">
									<br/>
									<font color="red">外协调委外</font>
									</s:if>
								</td>
								<td align="left" width="30px;" style="border-bottom: 0px;border-top: 0px;border-right: 0px; margin: 0px;padding: 0px;">
								<FONT size="1"><s:property value="#wwgoods.goodsCurQuantitySting"/></FONT></td>
							</tr>
							</s:iterator>
						</table>
					</td>
					</tr>
					 </s:iterator>
					</table>
				</form>
				</div>
				<input type="button" value="导出" onclick="location.href='ProcardAction!findProcardwwList.action?id=${id}&tag=export'" class="input"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("selected");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
			$("#lqCounts"+i).show();
			$("#lqCounts"+i).removeAttr("disabled");
			$("#processno"+i).removeAttr("disabled");
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
			$("#lqCounts"+i).hide();
			$("#lqCounts"+i).attr("disabled","disabled");
			$("#processno"+i).attr("disabled","disabled");
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("selected");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			$("#lqCounts"+i).attr("disabled","disabled");
			$("#processno"+i).attr("disabled","disabled");
			$("#lqCounts"+i).hide();
			
		} else {
			$("#lqCounts"+i).show();
			$("#lqCounts"+i).removeAttr("disabled");
			$("#processno"+i).removeAttr("disabled");
			count++;
		}
	}
	if(count>0){
	}else{
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function peitao(){
	if(mustBeNumber("ptcount")){
		var ptcount = $("#ptcount").val();
		var totalCount = $("#totalCount").val();
		var checkboxs = document.getElementsByName("selected");
		for ( var i = 0; i < checkboxs.length; i++) {
				var klcount = $("#lqCountsx"+i).val();
				var finalCount = $("#lqCountsy"+i).val();
				var ptcount2 = ptcount*finalCount/totalCount;
				if((ptcount2-klcount)>0){
					$("#lqCounts"+i).val(klcount);
				}else{
					$("#lqCounts"+i).val(ptcount2);
				}
			
		}
	}
	
}

function lq(){
	$("#lqBtn").attr("disabled","disabled");
}
</SCRIPT>
	</body>
</html>
