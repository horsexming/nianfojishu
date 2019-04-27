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
		<style type="text/css">
a {
	cursor: pointer;
}

.table {
	border: 0px solid #999;
	width: 756px;
	border-collapse: collapse;
}

.table th,.table td {
	border-width: 1px;
	padding: 0px;
}

.subTable {
	text-align: center;
	border-collapse: collapse;
	width: 100%;
	border-width: 1px;
	border-style: hidden;
}
</style>
	</head>
	<body style="text-align: center;">
		<!-- 弹出层开始 -->
		<div id="bodyDiv" align="center" class="transDiv" style="left: 0px;"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 999; width: 1024px; display: none;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%; height: 400px;">
					<iframe id="uploadIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 100%; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<!-- 弹出层结束 -->
		<div align="center">
			<!-- A4页面开始 -->
			<div id="printDiv" style="width: 794px; border: 1px solid #000000;">
				<!-- 页边距内开始 -->
				<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
				<div
					style="width: 756px; border: 1px solid #000000; position: relative; top: 0px; bottom: 100px;">
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}" />
					<input type="hidden" id="processTableId" />
					<table id="processDataListTable" width="width:750px;" class="table"
						cellpadding="0" cellspacing="0">
						<!-- 1 -->
						<tr align="center">
							<td align="center" colspan="9">
								<table class="subTable">
									<tr>
										<td rowspan="2" style="width: 13%;">
											<img style="width: 80px;"
												src="<%=basePath%>${companyInfo.shhhjpg}" />
										</td>
										<td style="width: 20%; font-size: 25px;" rowspan="2">
											工艺程序图表
										</td>
										<td style="width: 13%">
											型别
										</td>
										<td style="width: 20%">
											件号
										</td>
										<td style="width: 20%">
											件名
										</td>
										<td style="width: 4%">
											第
										</td>
										<td style="width: 4%">
											<%--<input type="text" id="diPage" name="processTable.diPage" style="width:100%;"/>--%>
											1
										</td>
										<td style="width: 4%">
											页
										</td>
									</tr>
									<tr>
										<td>
											${gongyiGuicheng.xingbie}
										</td>
										<td>
											${gongyiGuicheng.jianNumb}
										</td>
										<td>
											${gongyiGuicheng.jianName}
										</td>
										<td>
											共
										</td>
										<td>
											1
										</td>
										<td>
											页
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="9">
								<input id="addProcessDataButton" type="button" value="添加">
								<input id="deleteProcessDataButton" type="button" value="删除">
							</td>
						</tr>
						<!-- 2 -->
						<tr align="center">
							<td style="width: 5%">
								工序号
							</td>
							<td style="width: 15%">
								工序名称
							</td>
							<td style="width: 13%">
								设备
							</td>
							<td style="width: 15%">
								工装号
							</td>
							<td style="width: 10%">
								量具
							</td>
							<td style="width: 3%">
								批次
							</td>
							<td style="width: 18%">
								备注
							</td>
							<td style="width: 10%;">
								状态
							</td>
							<td style="width: 10%;">
								操作
							</td>
						</tr>
						<!-- 34 -->
						<%--<s:iterator id="p" value="gongyiGuicheng.processList" status="st">
							<tr align="center">
								<td>${p.processNO}</td><td>${p.processName}</td><td>${p.shebeiName}</td><td>${p.gongwei}</td><td>4</td><td>备注内容</td>
							</tr>
							<tr align="center">
								<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td>
							</tr>
						</s:iterator>
						--%>
						<!-- 35 -->
						<tr align="center">
							<td align="center" colspan="9">
								<table class="subTable">
									<tr align="center">
										<td style="width: 15%;">
											索引号
										</td>
										<td style="width: 16%;">
											<%--<input type="text" id="suoyinNumb" name="processTable.suoyinNumb"/>--%>
										</td>
										<td style="width: 15%;"></td>
										<td style="width: 15%;">
											编制
										</td>
										<td style="width: 15%;">
											校对
										</td>
										<td style="width: 15%;">
											审核
										</td>
										<td style="width: 15%">
											批准
										</td>
									</tr>
									<tr>
										<td>
											更改单号
										</td>
										<td>
											<%--<input type="text" id="danNumb" name="processTable.danNumb"/>--%>
										</td>
										<td></td>
										<td>
											${gongyiGuicheng.bianzhiName}
										</td>
										<td>
											${gongyiGuicheng.jiaoduiName}
										</td>
										<td>
											${gongyiGuicheng.shenheName}
										</td>
										<td>
											${gongyiGuicheng.pizhunName}
										</td>
									</tr>
									<tr>
										<td>
											签名日期
										</td>
										<td>
											<%--<input type="text" id="qianming" name="processTable.qianming"/>--%>
										</td>
										<td>
											<%--<input id="qianmingDate" class="Wdate" type="text" name="processTable.qianmingDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>--%>
										</td>
										<td>
											<s:date name="gongyiGuicheng.bianzhiDate" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:date name="gongyiGuicheng.jiaoduiDate" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:date name="gongyiGuicheng.shenheDate" format="yyyy-MM-dd" />
										</td>
										<td>
											<s:date name="gongyiGuicheng.pizhunDate" format="yyyy-MM-dd" />
										</td>
									</tr>
									<tr>
										<td colspan="7" style="text-align: right;">
											${gongyiGuicheng.id}
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</div>
				<!-- 页边距内结束-->
			</div>
			<!-- A4页面结束  -->
		</div>
		<div style="width: 794px; text-align: center; margin: 0 auto;">

			<table style="width: 100%; text-align: center; border: 0px;">
				<tr>
					<td>
						<input type="button" id="saveButton" value="保存" class="input" />
						<input type="button" id="markingButton" value="打分" class="input" />
						<%--<input type="button" id="printButton" value="打印" class="input" />
					--%>
					</td>
				</tr>
			</table>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">

$(function() {
	chageDiv("none");

	//全局工艺规程状态
	var dengluId = '${sessionScope.Users.id}';
	var status = '';
	var bianzhiId = '';
	var jiaoduiId = '';
	var shenheId = '';
	var pizhunId = '';
	var xingbie = '${gongyiGuicheng.xingbie}';
	var gongyiGuichengScoreLeibieIdForGycxtb = '';
	/*
	$("#printButton")
			.bind(
					"click",
					function() {
						window.location = "gongyiGuichengAction!getGongyiGuiChengGycxtbPage.action?gongyiGuicheng.id=${gongyiGuicheng.id}&print=print";
					});
	 */

	//出事化件号-----------------------
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getProcessTableBygongyiGuichengId.action",
		data : {
			'processTable.gongyiGuichengId' : $('#gygcId').val()
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var data = data.data;
				var id = data.id;
				//var diPage=data.diPage;
		//var suoyinNumb=data.suoyinNumb;
		//var danNumb=data.danNumb;
		//var qianming=data.qianming;
		//var qianmingDate=data.qianmingDate;
		//if(qianmingDate){
		//	qianmingDate=new Date(qianmingDate).format('yyyy-MM-dd');
		//}else{
		//	qianmingDate=new Date().format('yyyy-MM-dd');
		//}
		//$("#processTableId").val(id);
		//$("#diPage").val(diPage);
		//$("#suoyinNumb").val(suoyinNumb);
		//$("#danNumb").val(danNumb);
		//$("#qianming").val(qianming);
		//$("#qianmingDate").val(qianmingDate);
	}
}
	});

	//出事化工序数据列表--------------------------
	/*
	$
			.ajax( {
				type : "post",
				dataType : "json",
				url : "gongyiGuichengAction!getProcessDataListBygongyiGuichengId.action",
				data : {
					'gongyiGuicheng.id' : $('#gygcId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					var html = '';
					if (success) {
						var processDataArr = data.data;
						if (processDataArr.length > 0) {
							$(processDataArr)
									.each(
											function(i, n) {
												var processData = n;
												var id = processData.id == null ? ''
														: processData.id;
												var gongxuNo = processData.gongxuNo == null ? ''
														: processData.gongxuNo;
												var gongxuName = processData.gongxuName == null ? ''
														: processData.gongxuName;
												var shebeiId = processData.shebeiId == null ? ''
														: processData.shebeiId;
												var shebeiNo = processData.shebeiNo == null ? ''
														: processData.shebeiNo;
												var shebeiName = processData.shebeiName == null ? ''
														: processData.shebeiName;
												var gongzhuangId = processData.gongzhuangId == null ? ''
														: processData.gongzhuangId;
												var gongzhuangNo = processData.gongzhuangNo == null ? ''
														: processData.gongzhuangNo;
												var gongzhuangName = processData.gongzhuangName == null ? ''
														: processData.gongzhuangName;
												var liangjuId = processData.liangjuId == null ? ''
														: processData.liangjuId;
												var liangjuNo = processData.liangjuNo == null ? ''
														: processData.liangjuNo;
												var liangjuName = processData.liangjuName == null ? ''
														: processData.liangjuName;
												
												var pici = processData.pici == null ? ''
														: processData.pici;
												var remark = processData.remark == null ? ''
														: processData.remark;
												var editStatus = processData.editStatus == null ? ''
														: processData.editStatus;
												html += '<tr align="center">';
												//html+=	'<td><input type="hidden" name="processData.id" value="'+id+'"/><input type="text" name="processData.gongxuNo" value="'+gongxuNo+'" style="width:100%"/></td><td><input type="text" name="processData.gongxuName" value="'+gongxuName+'" style="width:100%"/></td><td><input type="hidden" name="processData.shebeiName" value="'+shebeiName+'"/><input type="hidden" name="processData.shebeiNo" value="'+shebeiNo+'"/><select name="processData.shebeiId" style="width: 100%;"><option value="'+shebeiId+'"></option></select></td><td><input type="hidden" name="processData.gongzhuangName" value="'+gongzhuangName+'"/><input type="hidden" name="processData.gongzhuangNo" value="'+gongzhuangNo+'"/><select name="processData.gongzhuangId" style="width: 100%;"><option value="'+gongzhuangId+'"></option></select></td><td><input type="text" name="processData.pici" value="'+pici+'" style="width:100%"/></td><td><input type="text" name="processData.remark" value="'+remark+'" style="width:100%"/></td><td>'+editStatus+'</td><td><a name="processData.a" data-type="a3">A3图纸</a><a name="processData.a" data-type="a4">A4图纸</a><a name="processData.a" data-type="hanjie">焊接图表</a></td>';
												html += '<td><input type="hidden" name="processData.id" value="'
														+ id
														+ '"/><input type="text" name="processData.gongxuNo" value="'
														+ gongxuNo
														+ '" style="width:100%"/></td><td><input type="text" name="processData.gongxuName" value="'
														+ gongxuName
														+ '" style="width:100%"/></td><td><input type="hidden" name="processData.shebeiName" value="'
														+ shebeiName
														+ '"/><input type="hidden" name="processData.shebeiNo" value="'
														+ shebeiNo
														+ '"/><select name="processData.shebeiId" style="width: 100%;"><option value="'
														+ shebeiId
														+ '"></option></select></td><td><input type="hidden" name="processData.gongzhuangName" value="'
														+ gongzhuangName
														+ '"/><input type="hidden" name="processData.gongzhuangNo" value="'
														+ gongzhuangNo
														+ '"/><select name="processData.gongzhuangId" style="width: 100%;"><option value="'
														+ gongzhuangId
														+ '"></option></select></td> <td><input type="hidden" name="processData.liangjuName" value="'
														+ liangjuName
														+ '"/><input type="hidden" name="processData.liangjuNo" value="'
														+ liangjuNo
														+ '"/><select name="processData.liangjuId" style="width: 100%;"><option value="'
														+ liangjuId
														+ '"></option></select></td> <td><input type="text" name="processData.pici" value="'
														+ pici
														+ '" style="width:100%"/></td><td><input type="text" name="processData.remark" value="'
														+ remark
														+ '" style="width:100%"/></td><td>'
														+ editStatus
														+ '</td><td><a name="processData.a" data-type="open">文件管理</a><a name="processData.a" data-type="marking" style="display:none">打分</a><select name="processData.gongyiGuichengScoreLeibie"><option>请选择打分类别</option></select></td>';
												html += '</tr>';
												html += '<tr align="center">';
												//html+=	'<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td><a name="processData.a" data-type="maoliao">毛料图表</a></td>';
												html += '<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td>&nbsp;</td>';
												html += '</tr>';
											});
							$("#processDataListTable>tbody>tr").eq(2).after(
									html);
						} else {
							html += '<tr align="center">';
							html += '<td><input type="hidden" name="processData.id"/><input type="text" name="processData.gongxuNo" style="width:100%"/></td><td><input type="text" name="processData.gongxuName" style="width:100%"/></td><td><input type="hidden" name="processData.shebeiName" /><input type="hidden" name="processData.shebeiNo"/><select name="processData.shebeiId" style="width:100%;"></select></td><td><input type="hidden" name="processData.gongzhuangName" /><input type="hidden" name="processData.gongzhuangNo"/><select name="processData.gongzhuangId" style="width:100%;"></select></td> <td><input type="hidden" name="processData.liangjuName" /><input type="hidden" name="processData.liangjuNo"/><select name="processData.liangjuId" style="width:100%;"></select></td> <td><input type="text" name="processData.pici" style="width:100%"/></td><td><input type="text" name="processData.remark" style="width:100%"/><td>未完成</td><td><a name="processData.a" data-type="open">文件管理</a><a name="processData.a" data-type="marking" style="display:none">打分</a><select name="processData.gongyiGuichengScoreLeibie"><option>请选择打分类别</option></select></td>';
							html += '</tr>';
							html += '<tr align="center">';
							html += '<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>';
							html += '</tr>';
							$("#processDataListTable>tbody>tr").eq(2).after(
									html);
						}

					}
				}
			});
	 */

	$
			.ajax( {
				type : "post",
				dataType : "json",
				url : "gongyiGuichengAction!getProcessDataListBygongyiGuichengId.action",
				data : {
					'gongyiGuicheng.id' : $('#gygcId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					var html = '';
					if (success) {
						var processDataArr = data.data;
						if (processDataArr.length > 0) {
							$(processDataArr)
									.each(
											function(i, n) {
												var processData = n;
												var id = processData.id == null ? ''
														: processData.id;
												var gongxuId = processData.gongxuId == null ? ''
														: processData.gongxuId;
												var gongxuNo = processData.gongxuNo == null ? ''
														: processData.gongxuNo;
												var gongxuName = processData.gongxuName == null ? ''
														: processData.gongxuName;
												var shebeiId = processData.shebeiId == null ? ''
														: processData.shebeiId;
												var shebeiNo = processData.shebeiNo == null ? ''
														: processData.shebeiNo;
												var shebeiName = processData.shebeiName == null ? ''
														: processData.shebeiName;
												var gongzhuangId = processData.gongzhuangId == null ? ''
														: processData.gongzhuangId;
												var gongzhuangNo = processData.gongzhuangNo == null ? ''
														: processData.gongzhuangNo;
												var gongzhuangName = processData.gongzhuangName == null ? ''
														: processData.gongzhuangName;
												var liangjuId = processData.liangjuId == null ? ''
														: processData.liangjuId;
												var liangjuNo = processData.liangjuNo == null ? ''
														: processData.liangjuNo;
												var liangjuName = processData.liangjuName == null ? ''
														: processData.liangjuName;
												var liangjuNoForCompany = processData.liangjuNoForCompany == null ? ''
														: processData.liangjuNoForCompany;

												var pici = processData.pici == null ? ''
														: processData.pici;
												var remark = processData.remark == null ? ''
														: processData.remark;
												var editStatus = processData.editStatus == null ? ''
														: processData.editStatus;
												html += '<tr align="center">';
												//html+=	'<td><input type="hidden" name="processData.id" value="'+id+'"/><input type="text" name="processData.gongxuNo" value="'+gongxuNo+'" style="width:100%"/></td><td><input type="text" name="processData.gongxuName" value="'+gongxuName+'" style="width:100%"/></td><td><input type="hidden" name="processData.shebeiName" value="'+shebeiName+'"/><input type="hidden" name="processData.shebeiNo" value="'+shebeiNo+'"/><select name="processData.shebeiId" style="width: 100%;"><option value="'+shebeiId+'"></option></select></td><td><input type="hidden" name="processData.gongzhuangName" value="'+gongzhuangName+'"/><input type="hidden" name="processData.gongzhuangNo" value="'+gongzhuangNo+'"/><select name="processData.gongzhuangId" style="width: 100%;"><option value="'+gongzhuangId+'"></option></select></td><td><input type="text" name="processData.pici" value="'+pici+'" style="width:100%"/></td><td><input type="text" name="processData.remark" value="'+remark+'" style="width:100%"/></td><td>'+editStatus+'</td><td><a name="processData.a" data-type="a3">A3图纸</a><a name="processData.a" data-type="a4">A4图纸</a><a name="processData.a" data-type="hanjie">焊接图表</a></td>';
												html += '<td><input type="hidden" name="processData.id" value="'
														+ id
														+ '"/><input type="text" name="processData.gongxuNo" value="'
														+ gongxuNo
														+ '" style="width:100%;"/></td><td><select name="processData.gongxuId" style="width: 100%;"><option value="'
														+ gongxuId
														+ '" selected="selected">'
														+ gongxuName
														+ '/'
														+ gongxuNo
														+ '</option></select><input type="text" name="processData.gongxuName" readonly="readonly" value="'
														+ gongxuName
														+ '" style="width:100%"/></td><td><input type="hidden" name="processData.shebeiName" value="'
														+ shebeiName
														+ '"/><input type="hidden" name="processData.shebeiNo" value="'
														+ shebeiNo
														+ '"/><select name="processData.shebeiId" style="width: 100%;"><option value="'
														+ shebeiId
														+ '">'
														+ shebeiName
														+ '/'
														+ shebeiNo
														+ '</option></select></td><td><input type="hidden" name="processData.gongzhuangName" value="'
														+ gongzhuangName
														+ '"/><input type="hidden" name="processData.gongzhuangNo" value="'
														+ gongzhuangNo
														+ '"/><select name="processData.gongzhuangId" style="width: 100%;"><option value="'
														+ gongzhuangId
														+ '">'
														+ gongzhuangName
														+ '/'
														+ gongzhuangNo
														+ '</option></select></td> <td><input type="hidden" name="processData.liangjuName" value="'
														+ liangjuName
														+ '"/><input type="hidden" name="processData.liangjuNo" value="'
														+ liangjuNo
														+ '"/><input type="hidden" name="processData.liangjuNoForCompany" value="'
														+ liangjuNoForCompany
														+ '"/><select name="processData.liangjuId" style="width: 100%;"><option value="'
														+ liangjuId
														+ '">'
														+ liangjuName
														+ '/'
														+ liangjuNo
														+ '/'
														+ liangjuNoForCompany
														+ '</option></select></td> <td><input type="text" name="processData.pici" value="'
														+ pici
														+ '" style="width:100%"/></td><td><input type="text" name="processData.remark" value="'
														+ remark
														+ '" style="width:100%"/></td><td>'
														+ editStatus
														+ '</td><td><a name="processData.a" data-type="open">文件管理</a><a name="processData.a" data-type="marking" style="display:none">打分</a><select name="processData.gongyiGuichengScoreLeibie"><option>请选择打分类别</option></select></td>';
												html += '</tr>';
												html += '<tr align="center">';
												//html+=	'<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td><a name="processData.a" data-type="maoliao">毛料图表</a></td>';
												html += '<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td>&nbsp;</td>';
												html += '</tr>';
											});
							$("#processDataListTable>tbody>tr").eq(2).after(
									html);
						} else {
							html += '<tr align="center">';
							html += '<td><input type="hidden" name="processData.id"/><input type="text" name="processData.gongxuNo" style="width:100%"/></td><td><select name="processData.gongxuId" style="width: 100%;"></select><input type="text" name="processData.gongxuName" style="width:100%" readonly="readonly"/></td><td><input type="hidden" name="processData.shebeiName"/><input type="hidden" name="processData.shebeiNo"/><select name="processData.shebeiId" style="width:100%;"></select></td><td><input type="hidden" name="processData.gongzhuangName" /><input type="hidden" name="processData.gongzhuangNo"/><select name="processData.gongzhuangId" style="width:100%;"></select></td> <td><input type="hidden" name="processData.liangjuName" /><input type="hidden" name="processData.liangjuNo"/><input type="hidden" name="processData.liangjuNoForCompany"/><select name="processData.liangjuId" style="width:100%;"></select></td> <td><input type="text" name="processData.pici" style="width:100%"/></td><td><input type="text" name="processData.remark" style="width:100%"/><td>未完成</td><td><a name="processData.a" data-type="open">文件管理</a><a name="processData.a" data-type="marking" style="display:none">打分</a><select name="processData.gongyiGuichengScoreLeibie"><option>请选择打分类别</option></select></td>';
							html += '</tr>';
							html += '<tr align="center">';
							html += '<td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>';
							html += '</tr>';
							$("#processDataListTable>tbody>tr").eq(2).after(
									html);
						}

					}
				}
			});
	//初始化工序
	/*
	$.ajax( {
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		type : 'post',
		dataType : 'json',
		data : {},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var gongxuArr = data.data;
				var $gongxuIdArr = $("select[name='processData.gongxuId']");
				for ( var i = 0; i < $gongxuIdArr.length; i++) {
					var $gongxuId = $gongxuIdArr.eq(i);
					var gongxuId = $gongxuId.val();
					$gongxuId.empty();
					$(
							"<option value='' data-no='' data-name=''>请选择工序</option>")
							.appendTo($gongxuId);
					$(gongxuArr).each(
							function(i, n) {
								var gongxu = n;
								$(
										"<option value='" + gongxu.id
												+ "' data-no='"
												+ "' data-name='"
												+ gongxu.processName + "'>"
												+ gongxu.processName
												+ "</option>")
										.appendTo($gongxuId);
							});
					$gongxuId.find("option[value='" + gongxuId + "']")
							.attr("selected", true);
					
					$.ajax( {
						url : "GzstoreAction_getMachineListByGongxuId.action",
						type : 'post',
						dataType : 'json',
						data : {'id':gongxuId},
						async : false,
						success : function(data) {
							var success = data.success;
							if (success) {
								var shebeiArr = data.data;
								var $shebeiIdArr = $("select[name='processData.shebeiId']");
									var $shebeiId = $shebeiIdArr.eq(i);
									var shebeiId = $shebeiId.val();
									$shebeiId.empty();
									$(
											"<option value='' data-no='' data-name=''>请选择设备</option>")
											.appendTo($shebeiId);
									$(shebeiArr).each(
											function(k, n) {
												var shebei = n;
												$(
														"<option value='" + shebei.id
																+ "' data-no='"
																+ shebei.no
																+ "' data-name='"
																+ shebei.name + "'>"
																+ shebei.name+"/"+shebei.no
																+ "</option>")
														.appendTo($shebeiId);
											});
									$shebeiId.find("option[value='" + shebeiId + "']")
											.attr("selected", true); 
								
							}
						}
					});
					
					
				}
			}
		}
	});*/

	$("select[name='processData.gongxuId']")
			.bind(
					"change",
					function() {
						var $gongxuId = $(this);
						var $selectedOption = $gongxuId.find("option:selected");
						var name = $selectedOption.attr("data-name");
						var gongxuId = $selectedOption.val();
						//$gongxuId.parent().find("input[name='processData.gongxuName']").val(name);
						var $selectName = $gongxuId.parent().find(
								"input[name='processData.gongxuName']");
                         if(name==""||name=="-"){
                        	 $selectName.val('');
                         }else{
                        	 var selectName = $selectName.val();
                        	 if(selectName!=null&&selectName!=""){
							    selectName += ','+ name ;
                        	 }else{
                        		  selectName =  name ;
                        	 }
							$selectName.val(selectName);
                         }
                         /*
						if (name == '冲孔' || name == '翻边' || name == '切边'
								|| name == '落料' || name == '拉伸') {
							var selectName = $selectName.val();
							var selectNameArr = selectName.split(',');
							for ( var i = 0; i < selectNameArr.length; i++) {
								var _name = selectNameArr[i];
								if ('' == _name) {
									continue;
								}
								var result = _name == '冲孔' || _name == '翻边'
										|| _name == '切边' || _name == '落料'
										|| _name == '拉伸';
								if (!result) {
									$selectName.val('');
									break;
								}
							}
							selectName = $selectName.val();
							selectName += name + ',';
							$selectName.val(selectName);
						} else {
							$selectName.val(name);
						}
						*/

						$
								.ajax( {
									url : "GzstoreAction_getMachineListByGongxuId.action",
									type : 'post',
									dataType : 'json',
									data : {
										'id' : gongxuId
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											var shebeiArr = data.data;
											var $shebeiId = $gongxuId
													.parent()
													.parent()
													.find(
															"select[name='processData.shebeiId']");
											var shebeiId = $shebeiId.val();
											$shebeiId.empty();
											$(
													"<option value='-1' data-no='-' data-name='-'>请选择设备</option>")
													.appendTo($shebeiId);
											$(shebeiArr)
													.each(
															function(k, n) {
																var shebei = n;
																$(
																		"<option value='"
																				+ shebei.id
																				+ "' data-no='"
																				+ shebei.no
																				+ "' data-name='"
																				+ shebei.name
																				+ "'>"
																				+ shebei.name
																				+ "/"
																				+ shebei.no
																				+ "</option>")
																		.appendTo(
																				$shebeiId);
															});
											$shebeiId.find(
													"option[value='" + shebeiId
															+ "']").attr(
													"selected", true);

										}
									}
								});

					});
	//初始化设备
	/*
	$
			.ajax( {
				url : "gongyiGuichengAction!getMachineList.action",
				type : 'post',
				dataType : 'json',
				data : {},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var shebeiArr = data.data;
						var $shebeiIdArr = $("select[name='processData.shebeiId']");
						for ( var i = 0; i < $shebeiIdArr.length; i++) {
							var $shebeiId = $shebeiIdArr.eq(i);
							var shebeiId = $shebeiId.val();
							$shebeiId.empty();
							$(
									"<option value='' data-no='' data-name=''>请选择设备</option>")
									.appendTo($shebeiId);
							$(shebeiArr).each(
									function(i, n) {
										var shebei = n;
										$(
												"<option value='" + shebei.id
														+ "' data-no='"
														+ shebei.no
														+ "' data-name='"
														+ shebei.name + "'>"
														+ shebei.name+"/"+shebei.no
														+ "</option>")
												.appendTo($shebeiId);
									});
							$shebeiId.find("option[value='" + shebeiId + "']")
									.attr("selected", true);
						}
					}
				}
			});
	 */
	/*
	$
		.ajax( {
			url : "GzstoreAction_getMachineListByGongxuId.action",
			type : 'post',
			dataType : 'json',
			data : {id:gongxuId},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					var shebeiArr = data.data;
					var $shebeiIdArr = $("select[name='processData.shebeiId']");
					for ( var i = 0; i < $shebeiIdArr.length; i++) {
						var $shebeiId = $shebeiIdArr.eq(i);
						var shebeiId = $shebeiId.val();
						$shebeiId.empty();
						$(
								"<option value='' data-no='' data-name=''>请选择设备</option>")
								.appendTo($shebeiId);
						$(shebeiArr).each(
								function(i, n) {
									var shebei = n;
									$(
											"<option value='" + shebei.id
													+ "' data-no='"
													+ shebei.no
													+ "' data-name='"
													+ shebei.name + "'>"
													+ shebei.name+"/"+shebei.no
													+ "</option>")
											.appendTo($shebeiId);
								});
						$shebeiId.find("option[value='" + shebeiId + "']")
								.attr("selected", true);
					}
				}
			}
		});*/
	$("select[name='processData.shebeiId']").bind(
			"change",
			function() {
				var $shebeiId = $(this);
				var $selectedOption = $shebeiId.find("option:selected");
				var no = $selectedOption.attr("data-no");
				var name = $selectedOption.attr("data-name");
				$shebeiId.parent().find("input[name='processData.shebeiNo']")
						.val(no);
				$shebeiId.parent().find("input[name='processData.shebeiName']")
						.val(name);
			});

	//初始化工装
	/*
	$
			.ajax( {
				url : "gongyiGuichengAction!getGzstoreList.action",
				type : 'post',
				dataType : 'json',
				data : {},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var gongzhaungArr = data.data;
						var $gongzhuangIdArr = $("select[name='processData.gongzhuangId']");
						for ( var i = 0; i < $gongzhuangIdArr.length; i++) {
							var $gongzhuangId = $gongzhuangIdArr.eq(i);
							var gongzhuangId = $gongzhuangId.val();
							$gongzhuangId.empty();
							$(
									"<option value='' data-no='' data-name=''>请选择工装</option>")
									.appendTo($gongzhuangId);
							$(gongzhaungArr).each(
									function(i, n) {
										var gongzhuang = n;
										$(
												"<option value='"
														+ gongzhuang.id
														+ "' data-no='"
														+ gongzhuang.no
														+ "' data-name='"
														+ gongzhuang.name
														+ "'>"
														+ gongzhuang.name+"/"+gongzhuang.no
														+ "</option>")
												.appendTo($gongzhuangId);
									});
							$gongzhuangId.find(
									"option[value='" + gongzhuangId + "']")
									.attr("selected", true);
						}
					}
				}
			});*/
	$("select[name='processData.gongzhuangId']").bind(
			"change",
			function() {
				var $gongzhuangId = $(this);
				var $selectedOption = $gongzhuangId.find("option:selected");
				var no = $selectedOption.attr("data-no");
				var name = $selectedOption.attr("data-name");
				$gongzhuangId.parent().find(
						"input[name='processData.gongzhuangNo']").val(no);
				$gongzhuangId.parent().find(
						"input[name='processData.gongzhuangName']").val(name);
			});
	//初始量具
	/*
	$.ajax( {
		url : "MeasuringAction_getMeasuringList.action",
		type : 'post',
		dataType : 'json',
		data : {},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var liangjuList = data.data;
				var $liangjuIdArr = $("select[name='processData.liangjuId']");
				for ( var i = 0; i < $liangjuIdArr.length; i++) {
					var $liangjuId = $liangjuIdArr.eq(i);
					var liangjuId = $liangjuId.val();
					$liangjuId.empty();
					$("<option value='' data-no='' data-name=''>请选择量具</option>").appendTo($liangjuId);
					$(liangjuList).each(function(i, n) {
						var liangju = n;
						$("<option value='"+ liangju.id+ "' data-no='"+ liangju.number+ "' data-name='"+ liangju.matetag+ "'>"+ liangju.matetag+"/"+liangju.number+ "</option>").appendTo($liangjuId);
					});
					$liangjuId.find("option[value='" + liangjuId + "']").attr("selected", true);
				}
			}
		}
	});*/

	$("select[name='processData.liangjuId']").bind(
			"change",
			function() {
				var $liangjuId = $(this);
				var $selectedOption = $liangjuId.find("option:selected");
				var no = $selectedOption.attr("data-no");
				var name = $selectedOption.attr("data-name");
				var noForCompany = $selectedOption.attr("data-noForCompany");
				$liangjuId.parent().find("input[name='processData.liangjuNo']")
						.val(no);
				$liangjuId.parent().find(
						"input[name='processData.liangjuName']").val(name);
				$liangjuId.parent().find(
						"input[name='processData.liangjuNoForCompany']").val(noForCompany);
			});

	//初始化打分类别

	$("select")
			.bind(
					"click",
					function() {
						var $select = $(this);
						var selectName = $select.attr("name");
						if ($select.find("option").size() > 1) {
							return;
						}
						if (selectName == 'processData.gongxuId') {

							$
									.ajax( {
										url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
										type : 'post',
										dataType : 'json',
										data : {},
										async : false,
										success : function(data) {
											var success = data.success;
											if (success) {
												var gongxuArr = data.data;
												var $gongxuIdArr = $("select[name='processData.gongxuId']");
												for ( var i = 0; i < $gongxuIdArr.length; i++) {
													var $gongxuId = $gongxuIdArr
															.eq(i);
													var gongxuId = $gongxuId
															.val();
													//	$gongxuId.empty();
													//var $gongxuId=$select;
													var gongxuId = $gongxuId
															.val();
													$(
															"<option value='-1' data-no='-' data-name='-'>请选择工序</option>")
															.appendTo($gongxuId);
													$(gongxuArr)
															.each(
																	function(i,
																			n) {
																		var gongxu = n;
																		//if (gongxu.id != gongxuId) {
																			$(
																					"<option value='"
																							+ gongxu.id
																							+ "' data-no='"
																							+ "' data-name='"
																							+ gongxu.processName
																							+ "'>"
																							+ gongxu.processName
																							+ "</option>")
																					.appendTo(
																							$gongxuId);
																		//}
																	});
													$gongxuId.find(
															"option[value='"
																	+ gongxuId
																	+ "']")
															.attr("selected",
																	true);
												}
											}
										}
									});
						} else if (selectName == 'processData.shebeiId') {
							var gongxuId = $select.parent().parent().find(
									"select[name='processData.gongxuId']")
									.val();
							$
									.ajax( {
										url : "GzstoreAction_getMachineListByGongxuId.action",
										type : 'post',
										dataType : 'json',
										data : {
											'id' : gongxuId
										},
										async : false,
										success : function(data) {
											var success = data.success;
											if (success) {
												var shebeiArr = data.data;
												//var $shebeiIdArr = $("select[name='processData.shebeiId']");
										//var $shebeiId = $shebeiIdArr.eq(i);
										$shebeiId = $select;
										var shebeiId = $shebeiId.val();
										//$shebeiId.empty();
										$(
												"<option value='-1' data-no='-' data-name='-'>请选择设备</option>")
												.appendTo($shebeiId);
										$(shebeiArr)
												.each(
														function(k, n) {
															var shebei = n;
															if (shebeiId != shebei.id) {
																$(
																		"<option value='"
																				+ shebei.id
																				+ "' data-no='"
																				+ shebei.no
																				+ "' data-name='"
																				+ shebei.name
																				+ "'>"
																				+ shebei.name
																				+ "/"
																				+ shebei.no
																				+ "</option>")
																		.appendTo(
																				$shebeiId);
															}

														});
										$shebeiId.find(
												"option[value='" + shebeiId
														+ "']").attr(
												"selected", true);

									}
								}
									});

						} else if (selectName == 'processData.gongzhuangId') {

							$
									.ajax( {
										url : "GzstoreAction_getGzstoreListByXingbie.action",
										type : 'post',
										dataType : 'json',
										data : {
											'gzstore.xingbie' : xingbie
										},
										async : false,
										success : function(data) {
											var success = data.success;
											if (success) {
												var gongzhaungArr = data.data;
												var $gongzhuangIdArr = $("select[name='processData.gongzhuangId']");
												for ( var i = 0; i < $gongzhuangIdArr.length; i++) {
													var $gongzhuangId = $gongzhuangIdArr
															.eq(i);
													var gongzhuangId = $gongzhuangId
															.val();
													$gongzhuangId.empty();
													$(
															"<option value='-1' data-no='-' data-name='-'>请选择工装</option>")
															.appendTo(
																	$gongzhuangId);
													$(gongzhaungArr)
															.each(
																	function(i,
																			n) {
																		var gongzhuang = n;
																		$(
																				"<option value='"
																						+ gongzhuang.id
																						+ "' data-no='"
																						+ gongzhuang.no
																						+ "' data-name='"
																						+ gongzhuang.name
																						+ "'>"
																						+ gongzhuang.name
																						+ "/"
																						+ gongzhuang.no
																						+ "</option>")
																				.appendTo(
																						$gongzhuangId);
																	});
													$gongzhuangId
															.find(
																	"option[value='"
																			+ gongzhuangId
																			+ "']")
															.attr("selected",
																	true);
												}
											}
										}
									});

						} else if (selectName == 'processData.liangjuId') {
							$
									.ajax( {
										url : "MeasuringAction_getMeasuringList.action",
										type : 'post',
										dataType : 'json',
										data : {},
										async : false,
										success : function(data) {
											var success = data.success;
											if (success) {
												var liangjuList = data.data;
												var $liangjuIdArr = $("select[name='processData.liangjuId']");
												for ( var i = 0; i < $liangjuIdArr.length; i++) {
													var $liangjuId = $liangjuIdArr
															.eq(i);
													var liangjuId = $liangjuId
															.val();
													//$liangjuId.empty();
													$(
															"<option value='-1' data-no='-' data-name='-' data-noForCompany='-'>请选择量具</option>")
															.appendTo(
																	$liangjuId);
													$(liangjuList)
															.each(
																	function(i,
																			n) {
																		var liangju = n;
																		if (liangju.id != liangjuId) {
																			$(
																					"<option value='"
																							+ liangju.id
																							+ "' data-no='"
																							+ liangju.number
																							+ "' data-name='"
																							+ liangju.matetag
																							+ "' data-noForCompany='"
																							+ liangju.measuring_no
																							+ "'>"
																							+ liangju.matetag
																							+ "/"
																							+ liangju.number
																							+"/"
																							+gongxu.liangjuNoForCompany
																							+ "</option>")
																					.appendTo(
																							$liangjuId);
																		}
																	});
													$liangjuId.find(
															"option[value='"
																	+ liangjuId
																	+ "']")
															.attr("selected",
																	true);
												}
											}
										}
									});
						}
					});

	$
			.ajax( {
				url : "gongyiGuichengScoreAction!getGongyiGuichengScoreLeibieListAllForSelect.action",
				type : 'post',
				dataType : 'json',
				data : {},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var gongyiGuichengScoreLeibieList = data.data;
						var $gongyiGuichengScoreLeibieArr = $("select[name='processData.gongyiGuichengScoreLeibie']");
						for ( var i = 0; i < $gongyiGuichengScoreLeibieArr.length; i++) {
							var $gongyiGuichengScoreLeibie = $gongyiGuichengScoreLeibieArr
									.eq(i);
							$gongyiGuichengScoreLeibie.empty();
							$("<option value=''  >请选择打分类别</option>").appendTo(
									$gongyiGuichengScoreLeibie);
							$(gongyiGuichengScoreLeibieList)
									.each(
											function(i, n) {
												var gongyiGuichengScoreLeibie = n;
												var gongyiGuichengScoreLeibieBiaoshi = gongyiGuichengScoreLeibie.biaoshi;
												if (gongyiGuichengScoreLeibieBiaoshi == 'gygcsy') {
													return;
												}
												if (gongyiGuichengScoreLeibieBiaoshi == 'gycxtb') {
													gongyiGuichengScoreLeibieIdForGycxtb = gongyiGuichengScoreLeibie.id;
													return;
												}
												$(
														"<option value='"
																+ gongyiGuichengScoreLeibie.id
																+ "'>"
																+ gongyiGuichengScoreLeibie.name
																+ "</option>")
														.appendTo(
																$gongyiGuichengScoreLeibie);
											});
							//$liangjuId.find("option[value='" + liangjuId + "']").attr("selected", true);
						}
					}
				}
			});
	$("select[name='processData.gongyiGuichengScoreLeibie']")
			.bind(
					"change",
					function() {
						var $tr = $(this).parents("tr").first();
						var processDataId = $tr.find(
								"input[name='processData.id']").val();
						var gygcId = $('#gygcId').val();
						var gongyiGuichengScoreLeibieId = $(this).val();
						//var gongyiGuichengScoreLeibieBiaoshi=$(this).find("option:selected").attr("data-biaoshi");
						chageDiv('block');
						$("#contentDiv").css("top", "150px");
						var _src = 'gongyiGuichengScoreAction!getGongyiGuiChengMarkingPage.action?gongyiGuichengScoreItem.gongyiGuichengId='
								+ gygcId
								+ '&gongyiGuichengScoreItem.processDataId='
								+ processDataId
								+ '&gongyiGuichengScoreJilu.parentId='
								+ gongyiGuichengScoreLeibieId;
						$("#uploadIframe").attr("src", _src);
					});

	$('#markingButton')
			.bind(
					'click',
					function() {
						chageDiv('block');
						$("#contentDiv").css("top", "150px");
						//var _src='gongyiGuichengScoreAction!getGongyiGuichengScorePage.action?gongyiGuichengScore.gongyiGuichengId='+ $("#gygcId").val();
						var _src = 'gongyiGuichengScoreAction!getGongyiGuiChengMarkingPage.action?gongyiGuichengScoreItem.gongyiGuichengId='
								+ $("#gygcId").val()
								+ '&gongyiGuichengScoreJilu.parentId='
								+ gongyiGuichengScoreLeibieIdForGycxtb;
						$("#uploadIframe").attr("src", _src);
					});
	//查看A3A4图纸绑定事件
	$("a[name='processData.a']")
			.bind(
					"click",
					function() {
						var $a = $(this);
						var aType = $a.attr("data-type");
						var $tr = $(this).parents("tr").first();
						var processDataId = $tr.find(
								"input[name='processData.id']").val();
						var gygcId = $('#gygcId').val();
						if (aType == 'a3') {
							window.location = 'gongyiGuichengAction!getGongyiGuiChengGxsmlqPage.action?gongyiGuicheng.id='
									+ gygcId
									+ '&processData.gongyiGuichengId='
									+ gygcId
									+ '&processData.id='
									+ processDataId;
						} else if (aType == 'a4') {
							window.location = 'gongyiGuichengAction!getGongyiGuiChengGxsmlqa4Page.action?gongyiGuicheng.id='
									+ gygcId
									+ '&processData.gongyiGuichengId='
									+ gygcId
									+ '&processData.id='
									+ processDataId;
						} else if (aType == 'maoliao') {
							processDataId = $tr.prev().find(
									"input[name='processData.id']").val();
							window.location = 'gongyiGuichengAction!getGongyiGuiChengGxsmlqMaoliaoPage.action?gongyiGuicheng.id='
									+ gygcId
									+ '&processData.gongyiGuichengId='
									+ gygcId
									+ '&processData.id='
									+ processDataId;
						} else if (aType == 'hanjie') {
							window.location = 'gongyiGuichengAction!getGongyiGuiChengGxsmlqHanjiePage.action?gongyiGuicheng.id='
									+ gygcId
									+ '&processData.gongyiGuichengId='
									+ gygcId
									+ '&processData.id='
									+ processDataId;
						} else if (aType == 'open') {
							chageDiv('block');
							$("#contentDiv").css("top", "150px");
							var _src = 'gongyiGuichengAction!getGongyiGuiChengUploadPage.action?'
									+ '&gongyiGuichengAffix.gongyiGuichengId='
									+ gygcId
									+ '&gongyiGuichengAffix.processDataId='
									+ processDataId
									+ '&gongyiGuichengAffix.weizhi=gxsmlq';
							$("#uploadIframe").attr("src", _src);
						} else if (aType == 'marking') {
							chageDiv('block');
							$("#contentDiv").css("top", "150px");
							var _src = 'gongyiGuichengScoreAction!getGongyiGuichengScorePage.action?gongyiGuichengScore.gongyiGuichengId='
									+ gygcId
									+ '&gongyiGuichengScore.processDataId='
									+ processDataId;
							$("#uploadIframe").attr("src", _src);
						}

					});

	//添加删除 操作顺序
	$("#addProcessDataButton")
			.bind(
					"click",
					function() {
						var $trLast = $("#processDataListTable>tbody>tr:last-child");
						var $trLastPrev = $(
								"#processDataListTable>tbody>tr:last-child")
								.prev();
						var $trLastPrevPrev = $trLastPrev.prev();
						var $trTemp = $trLastPrev.clone(true);
						var $trPrevTemp = $trLastPrevPrev.clone(true);
						$trPrevTemp.find("input[name='processData.id']")
								.val('');
						$trLast.before($trPrevTemp);
						$trLast.before($trTemp);
					});
	$("#deleteProcessDataButton")
			.bind(
					"click",
					function() {
						var $id = $("input[name='processData.id']");
						if ($id.length < 2) {
							alert("不能再删了");
							return;
						}
						var $trLast = $("#processDataListTable>tbody>tr:last-child");
						var $trLastPrev = $(
								"#processDataListTable>tbody>tr:last-child")
								.prev();
						var $trLastPrevPrev = $trLastPrev.prev();
						var processDataId = $trLastPrevPrev.find(
								"input[name='processData.id']").val();
						$trLastPrev.remove();
						$trLastPrevPrev.remove();
						if (processDataId) {
							$
									.ajax( {
										type : "post",
										dataType : "json",
										url : "gongyiGuichengAction!deleteProcessDataById.action",
										data : {
											'processData.id' : processDataId
										},
										async : true,
										success : function(data) {
											//不做任何处理
									}
									});
						}
					});
	//***********************************获取参数***********************************************
	function getProcessTableParams() {
		var $id = $("#processTableId");
		//var $diPage=$("#diPage");
		//var $suoyinNumb=$("#suoyinNumb");
		//var $danNumb=$("#danNumb");
		//var $qianming=$("#qianming");
		//var $qianmingDate=$("#qianmingDate");
		/*
		processTable={
			'id': $id.val(),
			//'diPage': $diPage.val(),
			'suoyinNumb': $suoyinNumb.val(),
			'danNumb': $danNumb.val(),
			'qianming': $qianming.val(),
			'qianmingDate': $qianmingDate.val()==''? qianmingDate=new Date().format('yyyy-MM-dd'): $qianmingDate.val(),
			'gongyiGuichengId': $("#gygcId").val()
		}
		return JSON.stringify(processTable);
		 */
		return JSON.stringify( {});
	}
	function getProcessDataParams() {
		var $idArr = $("input[name='processData.id']");
		var $gongxuIdArr = $("select[name='processData.gongxuId']");
		var $gongxuNoArr = $("input[name='processData.gongxuNo']");
		var $gongxuNameArr = $("input[name='processData.gongxuName']");
		var $shebeiIdArr = $("select[name='processData.shebeiId']");
		var $shebeiNoArr = $("input[name='processData.shebeiNo']");
		var $shebeiNameArr = $("input[name='processData.shebeiName']");

		var $gongzhuangIdArr = $("select[name='processData.gongzhuangId']");
		var $gongzhuangNoArr = $("input[name='processData.gongzhuangNo']");
		var $gongzhuangNameArr = $("input[name='processData.gongzhuangName']");

		var $liangjuIdArr = $("select[name='processData.liangjuId']");
		var $liangjuNoArr = $("input[name='processData.liangjuNo']");
		var $liangjuNameArr = $("input[name='processData.liangjuName']");
		var $liangjuNoForCompanyArr = $("input[name='processData.liangjuNoForCompany']");

		var $piciArr = $("input[name='processData.pici']");
		var $remarkArr = $("input[name='processData.remark']");
		var processDataArr = new Array();
		for ( var i = 0; i < $idArr.length; i++) {
			processDataArr[i] = {
				'id' : $idArr.eq(i).val(),
				'gongxuId' : $gongxuIdArr.eq(i).val(),
				'gongxuNo' : $gongxuNoArr.eq(i).val(),
				'gongxuName' : $gongxuNameArr.eq(i).val(),
				'shebeiId' : $shebeiIdArr.eq(i).val(),
				'shebeiNo' : $shebeiNoArr.eq(i).val(),
				'shebeiName' : $shebeiNameArr.eq(i).val(),
				'gongzhuangId' : $gongzhuangIdArr.eq(i).val(),
				'gongzhuangNo' : $gongzhuangNoArr.eq(i).val(),
				'gongzhuangName' : $gongzhuangNameArr.eq(i).val(),
				'liangjuId' : $liangjuIdArr.eq(i).val(),
				'liangjuNo' : $liangjuNoArr.eq(i).val(),
				'liangjuName' : $liangjuNameArr.eq(i).val(),
				'liangjuNoForCompany' : $liangjuNoForCompanyArr.eq(i).val(),
				'pici' : $piciArr.eq(i).val(),
				'remark' : $remarkArr.eq(i).val(),
				'gongyiGuichengId' : $("#gygcId").val()
			}
		}
		return JSON.stringify(processDataArr);
	}

	//提交工艺规程图表----------------------

	$("#saveButton").bind("click", function() {
		var processTableParams = getProcessTableParams();
		var processDataParams = getProcessDataParams();
		$.ajax( {
			type : "post",
			dataType : "json",
			url : "gongyiGuichengAction!updateGongyiGuiChengGycxtbPage.action",
			data : {
				'processTable.params' : processTableParams,
				'processData.params' : processDataParams
			},
			async : false,
			success : function(data) {
				var success = data.success;
				if (success) {
					alert("保存成功");
					window.parent.location.reload();
				}
			}
		});
	});
	//提交工艺规程图表----------------------
	//*************************************打分******************************************************
	//$("form table:visible").css('display','none');

	//****************************************添加权限******************************************************
	/*
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengById.action",
		data : {
			'gongyiGuicheng.id' : $('#gygcId').val()
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var data = data.data;

				bianzhiId = data.bianzhiId;
				jiaoduiId = data.jiaoduiId;
				shenheId = data.shenheId;
				pizhunId = data.pizhunId;

				status = data.status;
				//$('#button').attr('disabled',"true");添加disabled属性
				//$('#button').removeAttr("disabled"); 移除disabled属性 
		
				//markingForm
				//markingButton
				//saveButton
				//presentButton
				//printButton
				switch (status) {
					case '打回':
					case '待编制':
						$("#markingForm input").attr('disabled', true);
						//$("#markingButton").attr('disabled', true);
						$("#backButton").attr('disabled', true);
			
						break;
					case '已编制':
						if (jiaoduiId == dengluId) {
							$(":enabled").attr('disabled', true);
			
							$("#markingForm input").removeAttr("disabled");
							//$("#markingButton").removeAttr('disabled');
							//$("#printButton").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']:option").removeAttr("disabled");
						} else {
							$(":enabled").attr('disabled', true);
						}
						
						break;
					case '已校对':
						if (shenheId == dengluId) {
							$(":enabled").attr('disabled', true);
			
							//$("#markingForm input").removeAttr("disabled");
							//$("#markingButton").removeAttr('disabled');
							//$("#printButton").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']:option").removeAttr("disabled");
						} else {
							$(":enabled").attr('disabled', true);
						}
			
						break;
					case '已审核':
						if (pizhunId == dengluId) {
							$(":enabled").attr('disabled', true);
			
							//$("#markingForm input").removeAttr("disabled");
							//$("#markingButton").removeAttr('disabled');
							//$("#printButton").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']").removeAttr("disabled");
							$("select[name='processData.gongyiGuichengScoreLeibie']:option").removeAttr("disabled");
						} else {
							$(":enabled").attr('disabled', true);
						}
						break;
					case '已批准':
						$(":enabled").attr('disabled', true);
			
						//$("#printButton").removeAttr("disabled");
						break;
					default:
			
						break;
					}
			}
		}
	});
	 */
});
</script>
