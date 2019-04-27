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
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js">
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="left">
				<input type="button" value="导出" id="exportbtn"
				style="height: 60px; width:80px; "
				onclick="exportAll();todisabledone(this)" data="downData">
				</div>
				<div> 总成件号：${procard.markId}&nbsp;&nbsp;&nbsp;业务件号：${procard.ywMarkId}&nbsp;&nbsp;&nbsp;订单号：${procard.orderNumber}
				</div>
				<div>
					<form action="ProcardAction!procardOutbcp.action" method="post" onsubmit="return lq();">
					<input type="hidden" value="${procard.id}" name="id">
					<table class="table">
					 <tr>
					 	<td align="left"><font class="dc">全选</font>
					 	<input class="dc" type="text"  id="ptcount" value="${procard.filnalCount}" style="width: 40px;" onblur="peitao()" >
					 	<input type="text"  id="totalCount" value="${procard.filnalCount}" style="display: none;" >
					 	<input class="dc" type="checkbox" id="checkAll" onchange="chageAllCheck()">
					 	</td>
					 	<td>序号</td>
					 	<td align="center">件号</td>
					 	<td align="center">版本</td>
					 	<td align="center">批次</td>
					 	<td align="center">名称</td>
					 	<td align="center">工序号</td>
					 	<td align="center">工序名</td>
					 	<td align="center">下工序</td>
					 	<td align="center">可领数量</td>
					 	<td align="center">批次数量</td>
					 	<td align="center">单台用量</td>
					 	<td align="center">申请中数量</td>
					 	<td align="center">已入库数量</td>
					 	<td align="center">库存数量</td>
					 	<td align="center">出库数量</td>
					 	<td align="center">缺料数量</td>
					 </tr>
					 <s:iterator value="list" id="pvo" status="pvoindex">
					 	<s:if test="#pvoindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" height="27px"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"height="27px"
								onmouseout="outBgcolor(this,'')">
						</s:else>
					<td align="left">
					<input class="dc" type="checkbox" name="selected" value="${pvo.id}"
								onchange="chageNum()" />
					<input type="text" name="lqCounts" id="lqCounts${pvoindex.index}" value="${pvo.zaizhikzkCount}" style="display: none;width: 40px;" disabled="disabled">
					<input type="text"  id="lqCountsx${pvoindex.index}" value="${pvo.zaizhikzkCount}" style="display: none;width: 40px;" >
					<input type="text"  id="lqCountsy${pvoindex.index}" value="${pvo.filnalCount}" style="display: none;width: 40px;" >
				 	<input type="hidden" name="processIds" id="processno${pvoindex.index}" value="${pvo.processNo}" disabled="disabled">
				 		
					</td>
					<td><s:property value="#pvoindex.index+1" /></td>
					<td align="right"><s:property value="#pvo.markId"/></td>
					<td align="right"><s:property value="#pvo.banBenNumber"/></td>
					<td align="right"><s:property value="#pvo.selfCard"/></td>
					<td align="right"><s:property value="#pvo.proName"/></td>
					<td align="right"><s:property value="#pvo.processnos"/></td>
					<td align="right"><s:property value="#pvo.processnames"/></td>
					<td align="right"><s:property value="#pvo.nextProcessName"/></td>
					<td align="left"><s:property value="#pvo.zaizhikzkCount"/></td>
					<td align="left"><s:property value="#pvo.filnalCount"/></td>
					<td align="left"><s:property value="#pvo.filnalCount/procard.filnalCount"/></td>
					<td align="left"><s:property value="#pvo.bcprksqCount"/></td>
					<td align="left"><s:property value="#pvo.bcprkCount"/></td>
					<td align="left"><s:property value="#pvo.bcpkcCount"/></td>
					<td align="left"><s:property value="#pvo.bcpckCount"/></td>
					<s:if test="#pvo.bcpqlCount>0">
					 	<td align="center" bgcolor="red" >
					 	</s:if>
					 	<s:elseif test="#pvo.bcpqlCount==0&&#pvo.bcpkcCount==0">
						<td align="center" bgcolor="green" >
					 	</s:elseif>
					 	<s:else>
						<td align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					 	</s:else><s:property value="#pvo.bcpqlCount"/></td>
					</tr>
					 </s:iterator>
					 <tr class="dc">
					 	<td align="center" colspan="20">
					 	刷卡:<input type="text" name="code1"> 密码：<input type="password" name="pwsswords">
					 	</td>
					 </tr>
					 <tr class="dc">
					 	<td align="center" colspan="20">
					 	<input type="submit" value="领取" style="width: 80px;height: 60px;" id="lqBtn">
					 	</td>
					 </tr>
					</table>
				</form>
				</div>
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

function exportAll(){
	$(".table").table2excel({
    exclude: ".dc",//BUG // hidden input移出TD // 格式css selector 
    name: "BOM半成品转存清单",
    filename: "${procard.orderNumber}_半成品转存清单" //do not include extension
	});
}
</SCRIPT>
	</body>
</html>
