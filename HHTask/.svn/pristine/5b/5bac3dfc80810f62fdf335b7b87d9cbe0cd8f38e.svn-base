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
						待存柜批次
					</div>
					<table class="table">
						<tr>
							<th colspan="12">
								<input value="扫描库位码" style="width: 80px; height: 60px;"
									type="button" onclick="getcheckList2()">
								<input type="button" style="width: 70px; height: 60px;"
									onclick="javascript:window.location.href = 'WaigouwaiweiPlanAction!findDqrDeliRuGui.action';"
									value="刷新" />
<%--								<input type="button" style="width: 70px; height: 60px;"--%>
<%--									value="ceshi" />--%>
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								选择
							</th>
							<th align="center">
								序号
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								类型
							</th>
							<th align="center">
								供料属性
							</th>
							<th align="center">
								图号
							</th>
							<th align="center">
								待存数量
							</th>
							<th align="center">
								箱数
							</th>
							<th align="center">
								物料位置
							</th>
							<th align="center">
								检验批次
							</th>
						</tr>
						<s:iterator value="list" id="pageWgww2" status="pageStatus2">
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
								<input type="checkbox"
									name="wgjId" value="${pageWgww2.id}" >
							</td>
							<td>
								<s:property value="#pageStatus2.index+1" />
							</td>
							<td>
								${pageWgww2.markId}
							</td>
							<td>
								${pageWgww2.proName}
							</td>
							<td>
								<s:if test="#pageWgww2.banben!=null&&#pageWgww2.banben!='null'">
								${pageWgww2.banben}
								</s:if>
							</td>
							<td>
								${pageWgww2.type}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<td>
								${pageWgww2.tuhao}
							</td>
							<td >
								<s:if test="#pageWgww2.ycNumber>0">
									${pageWgww2.qrNumber-pageWgww2.ycNumber} 
								</s:if>
								<s:else>
									${pageWgww2.qrNumber}
								</s:else>
								${pageWgww2.unit}
							</td>
							<td>
								${pageWgww2.ctn}
							</td>
							<td>
								${pageWgww2.qrWeizhi}
							</td>
							<td>
								${pageWgww2.examineLot}
							</td>
						</s:iterator>
						<s:if test="list.size()<=0">
							<tr>
								<th colspan="12">没有待入柜任务,可以休息一会儿了~</th>
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
