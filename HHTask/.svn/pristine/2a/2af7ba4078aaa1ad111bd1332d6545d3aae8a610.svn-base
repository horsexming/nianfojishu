<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<script type="text/javascript"
			src="<%=basePath%>/javascript/echarts/echarts.js">
</script>
		<STYLE type="text/css">
#show_div input {
	border: 0px;
	text-align: right;
}

#show_div .th_1 {
	background-color: #73264e;
	color: #fff;
}

.td_1 {
	background-color: #FFFF99;
}

.td_2 {
	background-color: #407eb8;
}
</STYLE>
	</head>
	<body style="background-color: #407eb8">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<%--							current :${spcControl.id}/--%>
			<s:iterator value="spcControlList" id="pagesList" status="statusList">
				<%--							${pagesList.id}--%>
				<input type="hidden" name="showURL" value=" ${pagesList.id}">
			</s:iterator>

			<div align="center">
				<s:if test="pageStatus!='show'">


					<br />
					<div id="">
						<br />
						<h2 style="font-size: x-large;">
							SPC 管制图-均值极差控制图数据填写
						</h2>
						<br />
						<form action="SpcControlAction_daoru.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							<b>选择导入文件:</b>
							<input type="file" name="spc_file">
							<a href="<%=basePath%>/upload/file/download/spc_daoru.xls">导入模版下载</a>
							<a
								href="FileViewAction.action?FilePath=/upload/file/download/spc_daoru.xls&Refresh=true">/预览</a>
							<input type="hidden" value="${spcControl.id}" name="id">
							<input type="submit" value="批量导入" id="sub">
						</form>

						<form action="SpcControlAction_addspcControl.action" method="post">
							<b>工位号:</b>
							<input type="text" value="${spcControl.gongwei}"
								readonly="readonly" name="spcControl.gongwei" />
							<b>设备号:</b>
							<input type="text" value="${spcControl.shebeiNo}"
								readonly="readonly" name="spcControl.shebeiNo" />
							<table class="table" id="mytable">
								<tr>
									<th colspan="3">
										项目名称
									</th>
									<td colspan="3">
										<input type="text" name="spcControl.proName"
											value="${spcControl.proName}" readonly="readonly"
											style="width: 135px;" />
										<input type="hidden" name="spcControl.id"
											value="${spcControl.id}" />
									</td>
									<th colspan="2">
										测量项目
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.clcontent"
											value="${spcControl.clcontent}" style="width: 90px;" />
									</td>
									<th colspan="2">
										名义值
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.nominalValue"
											value="${spcControl.nominalValue}" readonly="readonly"
											style="width: 90px;" />
									</td>
									<th colspan="1">
										上限
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.ceilValue"
											value="${spcControl.ceilValue}" readonly="readonly"
											style="width: 90px;" />
									</td>
									<th colspan="2">
										测量仪器
									</th>
									<td colspan="3">
										<input type="text" name="spcControl.clinstrument"
											value="${spcControl.clinstrument}" style="width: 135px;" />
									</td>
									<th colspan="3">
										测量单位
									</th>
									<td colspan="1">
										<input type="text" name="spcControl.clunit"
											value="${spcControl.clunit}" style="width: 45px;" />
									</td>
									<th colspan="1">
										版本
									</th>
								</tr>
								<tr>
									<th colspan="3">
										物料名称
									</th>
									<td colspan="3">
										<input type="text" name="spcControl.wlName"
											value="${spcControl.wlName}" readonly="readonly"
											style="width: 135px;" />
									</td>
									<th colspan="2">
										物料编码
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.markId"
											value="${spcControl.markId}" readonly="readonly"
											style="width: 90px;" />
									</td>
									<th colspan="2">
										公差(±)
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.tolerance"
											value="${spcControl.tolerance}" readonly="readonly"
											style="width: 90px;" />
									</td>
									<th colspan="1">
										下限
									</th>
									<td colspan="2">
										<input type="text" name="spcControl.floorValue"
											value="${spcControl.floorValue}" readonly="readonly"
											style="width: 90px;" />
									</td>
									<th colspan="2">
										模穴号
									</th>
									<td colspan="3">
										<input type="text" name="spcControl.mxNo"
											value="${spcControl.mxNo}" readonly="readonly"
											style="width: 135px;" />
									</td>
									<th colspan="3">
										群组数大小
									</th>
									<td colspan="1">
										<input type="text" name="spcControl.groupsize"
											value="${spcControl.groupsize}" id="groupsize"
											readonly="readonly" style="width: 45px;" />
									</td>
									<th colspan="1">
										v1.0
									</th>
								</tr>
								<tr>
									<th>
										Date
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${statussdf1.index}].cldate"
												value="${pagescg.cldate}" readonly="readonly"
												style="width: 45px;" />
											<input type="hidden"
												name="spcControl.spcControlGroupList[${statussdf1.index}].id"
												value="${pagescg.id}" />
										</td>
									</s:iterator>
									<c:forEach var="index" begin="${fn:length(scgList)}" end="24"
										step="1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${index}].cldate"
												style="width: 45px;" />
										</td>
									</c:forEach>
									<th>
										Date
									</th>
								</tr>
								<tr>
									<th>
										n
									</th>
									<c:forEach var="index" begin="0" end="24" step="1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${index}].groupsNO"
												value="${index+1}" readonly="readonly" style="width: 45px;" />
										</td>
									</c:forEach>
									<th>
										n
									</th>
								</tr>
								<tr id="tr_1">
									<th>
										x1
										<input type="hidden" value="" name="size" />
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
												value="${pagescg.detailList[0].clValue}"
												style="width: 45px;" onchange="numyanzheng(this)" />
											<input type="hidden"
												name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[0].id"
												value="${pagescg.detailList[0].id}" />
										</td>
									</s:iterator>
									<c:forEach var="index" begin="${fn:length(scgList)}" end="24"
										step="1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${index}].clvalues"
												style="width: 45px;" onchange="numyanzheng(this)" />
										</td>
									</c:forEach>
									<th>
										x1
									</th>
								</tr>
								<tr id="tr_2">
									<th>
										x2
										<input type="hidden" value="" name="size" />
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
												value="${pagescg.detailList[1].clValue}"
												style="width: 45px;" onchange="numyanzheng(this)" />
											<input type="hidden"
												name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[1].id"
												value="${pagescg.detailList[1].id}"
												onchange="numyanzheng(this)" />
										</td>
									</s:iterator>
									<c:forEach var="index" begin="${fn:length(scgList)}" end="24"
										step="1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${index}].clvalues"
												style="width: 45px;" onchange="numyanzheng(this)" />
										</td>
									</c:forEach>
									<th>
										x2
									</th>
								</tr>
								<tr id="tr_3">
									<th>
										x3
										<input type="hidden" value="" name="size" />
									</th>

									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text"
												name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
												value="<s:property value='#pagescg.detailList[2].clValue'/>"
												style="width: 45px;" onchange="numyanzheng(this)" />
											<input type="hidden"
												name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[2].id"
												value="${pagescg.detailList[2].id}"
												onchange="numyanzheng(this)" />
										</td>
									</s:iterator>
									<c:forEach var="index" begin="${fn:length(scgList)}" end="24"
										step="1">
										<td>
											<input type="text" onchange="numyanzheng(this)"
												name="spcControl.spcControlGroupList[${index}].clvalues"
												style="width: 45px;" />
										</td>
									</c:forEach>
									<th>
										x3
									</th>
								</tr>
							</table>
							<input type="submit" value="修改" class="input" />
						</form>
					</div>
				</s:if>
				<s:else>
					<div id="show_div">
						<br />
						<h2 style="font-size: x-large;">
							SPC 管制图-均值极差控制图
						</h2>
						<br />
						<b>工位号:</b>
						<b>${spcControl.gongwei}</b>
						<b>设备号:</b>
						<b>${spcControl.shebeiNo}</b>
						<table class="table" id="mytable" >
							<tr>
								<th class="th_1" colspan="3">
									项目名称
								</th>
								<td colspan="3" align="center" class="td_1">
									${spcControl.proName}
									<input type="hidden" name="spcControl.id"
										value="${spcControl.id}" />
								</td>
								<th class="th_1" colspan="2">
									测量项目
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.clcontent}
								</td>
								<th class="th_1" colspan="2">
									名义值
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.nominalValue}
								</td>
								<th class="th_1" colspan="1">
									上限
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.ceilValue}
								</td>
								<th class="th_1" colspan="2">
									测量仪器
								</th>
								<td colspan="3" align="center" class="td_1">
									${spcControl.clinstrument}
								</td>
								<th class="th_1" colspan="3">
									测量单位
								</th>
								<td colspan="1" align="center" class="td_1">
									${spcControl.clunit}
								</td>
								<th class="th_1" colspan="1">
									版本
								</th>
							</tr>
							<tr>
								<th class="th_1" colspan="3">
									物料名称
								</th>
								<td colspan="3" align="center" class="td_1">
									${spcControl.wlName}
								</td>
								<th class="th_1" colspan="2">
									物料编码
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.markId}
								</td>
								<th class="th_1" colspan="2">
									公差(±)
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.tolerance}
								</td>
								<th class="th_1" colspan="1">
									下限
								</th>
								<td colspan="2" align="center" class="td_1">
									${spcControl.floorValue}
								</td>
								<th class="th_1" colspan="2">
									模穴号
								</th>
								<td colspan="3" align="center" class="td_1">
									${spcControl.mxNo}
								</td>
								<th class="th_1" colspan="3">
									群组数大小
								</th>
								<td colspan="1" align="center" class="td_1">
									${spcControl.groupsize}
									<input type="hidden" value="${spcControl.groupsize}" name=""
										id="groupsize" />
								</td>
								<th class="th_1" colspan="1">
									v1.0
								</th>
							</tr>
							<tr>
								<th class="th_1">
									Date
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text" class="td_1"
											name="spcControl.spcControlGroupList[${statussdf1.index}].cldate"
											value="${pagescg.cldate}" readonly="readonly"
											style="width: 45px;" />
										<input type="hidden"
											name="spcControl.spcControlGroupList[${statussdf1.index}].id"
											value="${pagescg.id}" />
									</td>
								</s:iterator>
								<th class="th_1">
									Date
								</th>
							</tr>
							<tr>
								<th class="th_1">
									n
								</th>
								<c:forEach var="index" begin="0" end="24" step="1">
									<th class="th_1" align="center">
										${index+1}
									</th>
								</c:forEach>
								<th class="th_1">
									n
								</th>
							</tr>
							<tr id="tr_1">
								<th class="th_1">
									x1
									<input type="hidden" value="" name="size" />
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text"
											name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
											value="${pagescg.detailList[0].clValue}" readonly="readonly"
											style="width: 45px;" />
										<input type="hidden"
											name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[0].id"
											value="${pagescg.detailList[0].id}" />
									</td>
								</s:iterator>
								<th class="th_1">
									x1
								</th>
							</tr>
							<tr id="tr_2">
								<th class="th_1">
									x2
									<input type="hidden" value="" name="size" />
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text"
											name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
											value="${pagescg.detailList[1].clValue}" readonly="readonly"
											style="width: 45px;" />
										<input type="hidden"
											name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[1].id"
											value="${pagescg.detailList[1].id}" />
									</td>
								</s:iterator>
								<th class="th_1">
									x2
								</th>
							</tr>
							<tr id="tr_3">
								<th class="th_1">
									x3
									<input type="hidden" value="" name="size" />
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text"
											name="spcControl.spcControlGroupList[${statussdf1.index}].clvalues"
											value="${pagescg.detailList[2].clValue}" readonly="readonly"
											style="width: 45px;" />
										<input type="hidden"
											name="spcControl.spcControlGroupList[${statussdf1.index}].detailList[2].id"
											value="${pagescg.detailList[2].id}" />
									</td>
								</s:iterator>
								<th class="th_1">
									x3
								</th>
							</tr>
							<tr>
								<th class="th_1">
									XBar
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text" value="${pagescg.xbar}" readonly="readonly"
											style="width: 45px;" />
									</td>
								</s:iterator>
								<th class="th_1">
									XBar
								</th>
							</tr>
							<tr>
								<th class="th_1">
									Range
								</th>
								<s:iterator value="scgList" id="pagescg" status="statussdf1">
									<td>
										<input type="text" value="${pagescg.range}"
											readonly="readonly" style="width: 45px;" />
									</td>
								</s:iterator>
								<th class="th_1">
									Range
								</th>
							</tr>
						</table>
						<div style="background-color: #407eb8; width: 100%; height: 82%">
							<br />
							<div id="main" style="width: 100%; height: 350px;"></div>
							<br />
							<div id="main1" style="width: 100%; height: 350px;"></div>
							<br />
						</div>
						<div>
							<table class="table" style="display: none;">
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										UCLXBar
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.uclxbar}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 50px;">
										UCLXBar
									</th>
								</tr>
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										XDBar
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.xdbar}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										XDBar
									</th>
								</tr>
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										LCLXBar
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.lclxbar}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										LCLXBar
									</th>
								</tr>
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										UCLR
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.uclr}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										UCLR
									</th>
								</tr>
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										RBar
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.rbar}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										RBar
									</th>
								</tr>
								<tr>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										LCLR
									</th>
									<s:iterator value="scgList" id="pagescg" status="statussdf1">
										<td>
											<input type="text" value="${pagescg.lclr}"
												readonly="readonly" style="width: 45px;" />
										</td>
									</s:iterator>
									<th class="th_1" style="font-size: 0.5mm; width: 48px;">
										LCLR
									</th>
								</tr>
							</table>
						</div>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<div style="">
							<div style="width: 5%; float: left;">
								&nbsp;
							</div>
							<table class="table" style="width: 13%; float: left;">
								<tr>
									<th class="th_1">
										n
									</th>
									<th class="th_1">
										A2
									</th>
									<th class="th_1">
										D4
									</th>
									<th class="th_1">
										D3
									</th>
								</tr>
								<s:iterator value="spcgroupsList" id="pagesg">
									<tr>
										<th class="th_1">
											${pagesg.gropsSize}
										</th>
										<td class="td_2">
											${pagesg.a2}
										</td>
										<td class="td_2">
											${pagesg.d4}
										</td>
										<td class="td_2">
											0
										</td>
									</tr>
								</s:iterator>
							</table>
							<div style="width: 5%; float: left;">
								&nbsp;
							</div>
							<table style="width: 8%; float: left;" class="table">
								<tr>
									<th class="th_1">
										标准差
									</th>
									<td class="td_2">
										${spcControl.stdev}
									</td>
								</tr>
								<tr>
									<th class="th_1">
										Cp
									</th>
									<td class="td_2">
										${spcControl.cp}
									</td>
								</tr>
								<tr>
									<th class="th_1">
										Ca
									</th>
									<td class="td_2">
										${spcControl.ca}
									</td>
								</tr>
								<tr>
									<th class="th_1">
										Cpk
									</th>
									<td class="td_2">
										${spcControl.cpk}
									</td>
								</tr>
							</table>
							<div style="width: 5%; float: left;">
								&nbsp;
							</div>
							<table style="width: 10%; float: left;" class="table">
								<tr>
									<td class="th_1">
										XBar
									</td>
									<td class="td_2">
										群组均值
									</td>
								</tr>
								<tr>
									<td class="th_1">
										XDBar
									</td>
									<td class="td_2">
										总均值
									</td>
								</tr>
								<tr>
									<td class="th_1">
										Range
									</td>
									<td class="td_2">
										群组极差
									</td>
								</tr>
								<tr>
									<td class="th_1">
										RBar
									</td>
									<td class="td_2">
										极差均值
									</td>
								</tr>
							</table>
							<div style="width: 5%; float: left;">
								&nbsp;
							</div>
							<table style="width: 20%; float: left;" class="table">
								<tr>
									<td class="td_2" align="right">
										Xbar 最大值
									</td>
									<td class="td_2">
										${max_Xbar}
									</td>
									<td class="td_2">
										${max_Xbar_ceil}
									</td>
									<td class="td_2">
										坐标上限
									</td>
								</tr>
								<tr>
									<td class="td_2" align="right">
										Xbar 最小值
									</td>
									<td class="td_2">
										${min_Xbar}
									</td>
									<td class="td_2">
										${min_Xbar_floor}
									</td>
									<td class="td_2">
										坐标下限
									</td>
								</tr>
								<tr>
									<td class="td_2" align="right">
										极差R 最大值
									</td>
									<td class="td_2">
										${max_Range}
									</td>
									<td class="td_2">
										${max_Range_ceil}
									</td>
									<td class="td_2">
										坐标上限
									</td>
								</tr>
								<tr>
									<td class="td_2" align="right">
										极差R 最小值
									</td>
									<td class="td_2">
										0
									</td>
									<td class="td_2">
										0
									</td>
									<td class="td_2">
										坐标下限
									</td>
								</tr>
							</table>
						</div>
					</div>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	var groupsize = document.getElementById("groupsize");
	changtr(groupsize);
})
function changtr(obj){
	var num = parseInt(obj.value);
	var size = document.getElementsByName("size").length;
	var num0 = num-size;
	if(num0>0){
			$.ajax( {
				type : "POST",
				url : "SpcControlAction_findSpcControlById0.action",
				dataType : "json",
				data : {
					id:${spcControl.id}
				},
				success : function(data) {
					if(data!=null){
						var table = document.getElementById("mytable");
						for(var i=0;i<num0;i++){
							var newTr = table.insertRow(7+i);
							newTr.id = 'tr_' + (size+i+1);
							var newtd0 = '<th class ="th_1">x'+(size+i+1)+'</th>'
							$(newtd0).appendTo(newTr);
							$.each(data,function(index,obj){
								var xiabiao = size+i;
								var detailList = obj.detailList;
								var clValue = '';
								var id;
								if(detailList[size+i]!=undefined){
									clValue = detailList[size+i].clValue;
									id = detailList[size+i].id;
								}
								var newtd = '<td><input type="text"  style="width: 45px;" value="'+clValue+'" name="spcControl.spcControlGroupList['+index+'].clvalues" >' +
								'<input type="hidden" name="spcControl.spcControlGroupList['+index+'].detailList['+xiabiao+'].id" value="'+id+'"></td>';
								$(newtd).appendTo(newTr);
							})
							var newtd1 = '<th class ="th_1">x'+(size+i+1)+'</th>'
							$(newtd1).appendTo(newTr);
						}
					}
				
				}
			});
	}else{
		for(var i=0;i<Math.abs(num0);i++){
			$('#tr_'+(size-i)).remove();
		}
	}
}




      
var xAxis_data0,yAxis_data0,min0,max0, 
	xAxis_data1,yAxis_data1,min1,max1, 
	UCLXBar,XDBar,LCLXBar,LCLXBar,
	XBar,Range,UCLR,RBar,LCLR;
!function fuzhi(){
// 基于准备好的dom，初始化echarts实例
var myChart0 = echarts.init(document.getElementById('main'));
var myChart1 = echarts.init(document.getElementById('main1'));
	 myChart0.setOption({
	 backgroundColor: '#c0c0c0',
    title: {
        text: '均值控制图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['UCLXBar','XDBar','LCLXBar','XBar']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                 show: true
            },
            dataView: {readOnly: false},
            magicType: {type: ['line']},
            restore: {},
            saveAsImage: {show: true}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: {
       type: 'value',
    },
    series: [
        {
            name:'UCLXBar',
            type:'line',
            data:[],
             itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
        {
            name:'XDBar',
            type:'line',
            data:[],
            itemStyle:{
        		normal:{
        			color:'#FFFF99',
        			lineStyle:{
        				color:'#FFFF99',
        			}
        		}
            }
        },
        {
            name:'LCLXBar',
            type:'line',
            data:[],
            itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
        {
            name:'XBar',
            type:'line',
            data:[],
            symbol:'circle',
            symbolSize: 10,
            itemStyle: {
            normal: {
                 color: "#C0C0C0",  // 会设置点和线的颜色，所以需要下面定制 line
                borderColor: "#FF0000"  // 点边线的颜色
            }
        },
        lineStyle: {
            normal: {
                color: "#FFF"   // 线条颜色
            }
        },
        }
    ]
});
	 myChart1.setOption({
	 backgroundColor: '#c0c0c0',
    title: {
        text: '极差控制图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {//XBar,Range,UCLR,RBar;
        data:['LCLR','Range','UCLR','RBar']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: {
       type: 'value',
    },
    series: [
        {
            name:'LCLR',
            type:'line',
            data:[],
            itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
        {
            name:'Range',
            type:'line',
            data:[],
             symbol:'circle',
            symbolSize: 10,
            itemStyle: {
            normal: {
                 color: "#C0C0C0",  // 会设置点和线的颜色，所以需要下面定制 line
                borderColor: "#FF0000"  // 点边线的颜色
            }
        },
        lineStyle: {
            normal: {
                color: "#FFF"   // 线条颜色
            }
        }
        },
        {
            name:'UCLR',
            type:'line',
            data:[],
            itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
        {
            name:'RBar',
            type:'line',
            data:[],
             itemStyle:{
        		normal:{
        			color:'#FFFF99',
        			lineStyle:{
        				color:'#FFFF99',
        			}
        		}
            }
        }
    ]
});
 myChart0.showLoading();    //数据加载完之前先显示一段简单的loading动画
 myChart1.showLoading();    //数据加载完之前先显示一段简单的loading动画
	xAxis_data =  new Array();
 	yAxis_data0 = new Array();
	UCLXBar = new Array();
	XDBar = new Array();
	LCLXBar = new Array();
	XBar = new Array();
	Range = new Array();
	UCLR = new Array();
	RBar = new Array();
	LCLR = new Array();
	var jiange = 1;
	var jiange1 = 1;
	$.ajax( {
		type : "POST",
		url : "SpcControlAction_findSpcControlById0.action",
		data : {
			id:${spcControl.id}
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$.each(data,function(index,obj){
					xAxis_data[index]=obj.cldate;
					UCLXBar[index]=obj.uclxbar;
					XDBar[index]=obj.xdbar;
					LCLXBar[index]=obj.lclxbar;
					XBar[index]=obj.xbar;
					Range[index]=obj.range;
					UCLR[index]=obj.uclr;
					RBar[index]=obj.rbar;
					LCLR[index]=0;
				})
				var a = UCLXBar[0]-LCLXBar[0];
				jiange =a/8;
					min0 = LCLXBar[0]-jiange;
					max0 = UCLXBar[0]+jiange;
					min0=Math.round(min0*100)/100;
					max0=Math.round(max0*100)/100;
				var a1 = UCLR[0]-LCLR[0];
				jiange1 = a1/8;
				min1 = LCLR[0]-jiange1;
				max1 = UCLR[0]+jiange1;
				min1=Math.round(min1*100)/100;
				max1=Math.round(max1*100)/100;
				if(min1<0){
					min1 = 0;
				}
				for(var i=0;i<8;i++){
					yAxis_data0=min0+((i+1)*jiange);
					yAxis_data1=min1+((i+1)*jiange1);
				}
	  myChart0.hideLoading();    //隐藏加载动画
	myChart0.setOption({
        xAxis: {
            data: xAxis_data
        },
       yAxis: {
       boundaryGap:yAxis_data0,
       min:min0,
       max:max0
    },
        series: [{
            // 根据名字对应到相应的系列
            name: 'UCLXBar',
            data: UCLXBar
        	},
        	{
            name: 'XDBar',
            data: XDBar
        	},
        	{
            name: 'LCLXBar',
            data: LCLXBar
        	},
        	{
            name: 'XBar',
            data: XBar
        	}
        ]
    });
	 /**********************************************************************/
	  myChart1.hideLoading();    //隐藏加载动画
	myChart1.setOption({
        xAxis: {
            data: xAxis_data
        },
       yAxis: {
       boundaryGap:yAxis_data1,
       min:min1,
       max:max1
    },
        series: [{//'XBar','Range','UCLR','RBar'
            // 根据名字对应到相应的系列
            name: 'LCLR',
            data: LCLR
        	},
        	{
            name: 'Range',
            data: Range
        	},
        	{
            name: 'UCLR',
            data: UCLR
        	},
        	{
            name: 'RBar',
            data: RBar
        	}
        ]
    });
	
			}
		}
	})


	

}();
   
$(function() {

	//页面定时刷新()
	setTimeout(nextspc, 1000 * 50);
	
	function nextspc() {
		var src= new Array() ;
		$("input[name='showURL']").each(function(i,element){
			src.push($(this).val());
		})
		if(${spcControl.id}!=src[src.length-1]){
			window.parent.clearsetInterval();
				window.location.href='SpcControlAction_findSpcControlById.action?pageStatus=show&id=${spcControl.id}&screen=${screen}';
			}
		else{
			setTimeout(nextPage, 1000 * 55);
		}
		
	}

	//下一个页面
	function nextPage() {
		window.parent.clearsetInterval();
		window.parent.next();
	}

});
</SCRIPT>
	</body>
</html>
