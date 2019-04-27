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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="left">
				<div id="printDiv">
					<br />
					<div
						style="font-weight: bolder; font-size: 30px; margin-bottom: 10px;"
						align="center">
						未关门库位
					</div>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
								</th>
								<th align="center">
									库位编号<input type="hidden" name="warehouseNumber.id" value="${warehouseNumber.id}"/>
								</th>
								<th align="center">
									层数
								</th>
								<th align="center">
									开门时间
								</th>
								<th align="center">
									操作
								</th>
							</tr>
							<s:iterator value="warehouseNumbers" id="pageWgww2" status="pageStatus2">
								<s:if test="#pageStatus2.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus2.index+1" />
								</td>
								<td>
									${pageWgww2.number}
								</td>
								<td>
									${pageWgww2.floorNumbe}
								</td>
								<td >
									${pageWgww2.sczTime}
								</td>
								<td>
									<a href="WaigouwaiweiPlanAction!findRuGuiQueren.action?id=${pageWgww2.id}">确认数量</a>
								</td>
							</s:iterator>
							<s:if test="warehouseNumbers.size()<=0">
								<tr>
									<th colspan="5">没有未关闭的库位</th>
								</tr>
							</s:if>
						</table>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getcheckList2() {
	var invalue = document.getElementsByName("wgjId");
	var num= invalue.length;
	var mxId= "";
	for (i=0;i<num;i++){
		if(invalue[i].checked == true) {
		   	mxId+=invalue[i].value+",";
		}
	}
	if(mxId == ""){
		alert("请选择待存物品");return false;
	}
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	var invalue = document.getElementsByName("wgjId");
	var num= invalue.length;
	var mxId= "";
	for (i=0;i<num;i++){
		if(invalue[i].checked == true) {
		   	mxId+=invalue[i].value+",";
		}
	}
	window.location.href = "WaigouwaiweiPlanAction!findDeliveryRuGuiBacode.action?bacode=" + tm +"&mxId="+mxId;
}
</script>
	</body>
</html>
