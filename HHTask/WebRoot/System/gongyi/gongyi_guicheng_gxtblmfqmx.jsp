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
.table {
	border: 0px solid #999;
	border-width: 1px;
	border-collapse: collapse;
	width: 756px;
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
		<div align="center">
			<!-- A4页面开始 -->
			<div id="printDiv"
				style="width: 794px; height: 1123px; border: 1px solid #000000;">
				<!-- 页边距内开始 -->
				<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
				<div
					style="width: 756px; height: 1086px; border: 1px solid #000000; position: relative; top: 19px;">
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}" />
					<input type="hidden" id="processDataId" value="${processData.id}" />
					<table border="0" width="100%" class="table" cellspacing="0"
						cellpadding="0">
						<!-- 1 -->
						<tr align="center">
							<td align="center" colspan="4">
								<table class="subTable">
									<tr>
										<td rowspan="2" style="width: 13%;">
											<img style="width: 80px;" src="<%=basePath%>${companyInfo.shhhjpg}" />
										</td>
										<td style="width: 20%; font-size: 25px;" rowspan="2">
											工艺程序图表
										</td>
										<td style="width: 13%">
											型别
										</td>
										<td style="width: 15%">
											件号
										</td>
										<td style="width: 15%">
											件名
										</td>
										<td style="width: 10%;">
											工序号
										</td>
										<td style="width: 4%">
											第
										</td>
										<td style="width: 4%">
											<%--<input type="text" id="onePage" name="processData.onePage" style="width: 100%;"/>--%>
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
											${processData.gongxuNo}
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
						<!-- 2 -->
						<tr align="center">
							<td style="width: 15%;">
								材料
							</td>
							<td style="width: 25%;">
								<input type="text" id="" name="processData.cailiao" />
							</td>
							<td style="width: 15%;">
								工序名称
							</td>
							<td style="width: 25%;">
								<input type="text" name="processData.gongxuName"
									disabled="disabled" />
							</td>
						</tr>
						<tr align="center">
							<td style="width: 15%;">
								设备
							</td>
							<td style="width: 25%;">
								<input type="text" name="processData.shebeiName"
									disabled="disabled" />
							</td>
							<td style="width: 15%;" rowspan="2">
								夹/模具
							</td>
							<td style="width: 25%;">
								<input type="text" name="processData.jiaOrMoju"
									disabled="disabled" />
							</td>
						</tr>
						<tr align="center">
							<td style="width: 15%;">
								基准
							</td>
							<td style="width: 25%;">
								<input type="text" name="processData.jizhun" />
							</td>
							<td style="width: 25%;"></td>
						</tr>
						<!-- 3 -->
						<tr align="center">
							<td colspan="4">
								<p style="margin-top: 20px; font-size: 20px;">
									配齐以下零件
									<input id="addPartButton" type="button" value="添加">
									<input id="deletePartButton" type="button" value="删除">
									<p>
										<table id="partTable" class="subTable"
											style="width: 100%; margin-bottom: 100px; border: 1px;">
											<tr>
												<td>
													序号
												</td>
												<td>
													件号
												</td>
												<td>
													名称
												</td>
												<td>
													单台数量
												</td>
												<td>
													材料牌号、标准、规格
												</td>
												<td>
													备注
												</td>
											</tr>
											<!-- 
									<tr>
										<td><input type="hidden" id="" name="id" /><input type="hidden" id="" name="processDataId" value="${processData.id}"/><input type="text" id="" name="numb" style="width: 50px;"/></td><td><input type="text" id="" name="jianNumb" style="width: 50px;"/></td><td><input type="text" id="" name="jianName" style="width: 50px;"/></td><td><input type="text" id="" name="danNumb" style="width: 50px;"/></td><td><input type="text" id="" name="cailiao" style="width: 100px;"/></td><td><input type="text" id="" name="remark" style="width: 50px;"/></td>
									</tr>
									 -->
										</table>
							</td>
						</tr>
						<!-- 4-->
						<tr align="center">
							<td colspan="4">
								<table id="operationStandardTable" class="subTable">
									<tr>
										<td style="width: 5%;">
											项目
										</td>
										<td style="width: 89%;">
											作业规范
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="content" style="width: 100%;" />
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="content" style="width: 100%;" />
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="content" style="width: 100%;" />
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="content" style="width: 100%;" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 5 -->
						<tr align="center">
							<td colspan="4">
								<table id="detectionItemTable" class="subTable">
									<tr>
										<td style="width: 5%;">
											序号
										</td>
										<td style="width: 12%;">
											检测项目
										</td>
										<td style="width: 12%;">
											测量器具
										</td>
										<td style="width: 12%;">
											操作者测定频次
										</td>
										<td style="width: 12%;">
											巡检磁动频次
										</td>
										<td rowspan="5" style="width: 3%;">
											过程参数
										</td>
										<td style="">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="xiangmu" />
										</td>
										<td>
											<input type="text" name="qiju" />
										</td>
										<td>
											<input type="text" name="caozuoPinci" />
										</td>
										<td>
											<input type="text" name="xunjianPinci" />
										</td>
										<td></td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="xiangmu" />
										</td>
										<td>
											<input type="text" name="qiju" />
										</td>
										<td>
											<input type="text" name="caozuoPinci" />
										</td>
										<td>
											<input type="text" name="xunjianPinci" />
										</td>
										<td></td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="xiangmu" />
										</td>
										<td>
											<input type="text" name="qiju" />
										</td>
										<td>
											<input type="text" name="caozuoPinci" />
										</td>
										<td>
											<input type="text" name="xunjianPinci" />
										</td>
										<td></td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="id" />
											<input type="hidden" name="processDataId" />
											<input type="text" name="numb" style="width: 20px;" />
										</td>
										<td>
											<input type="text" name="xiangmu" />
										</td>
										<td>
											<input type="text" name="qiju" />
										</td>
										<td>
											<input type="text" name="caozuoPinci" />
										</td>
										<td>
											<input type="text" name="xunjianPinci" />
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="4">
								<table class="subTable">
									<tr>
										<td rowspan="3" style="width: 8%;">
											更改
										</td>
										<td style="width: 15%;">
											索引号
										</td>
										<td style="width: 15%;">
											<%--<input type="text" name="processData.suoyinNumb"/>--%>
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td style="width: 15%;">
											更改单号
										</td>
										<td style="width: 15%;">
											<%--<input type="text" name="processData.danNumb"/>--%>
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
										<td style="width: 15%;">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											签名、日期
										</td>
										<td>
											<%--<input type="text" name="processData.qianming"/>--%>
										</td>
										<td>
											<%--<input class="Wdate" type="text" name="processData.qianmingDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%>
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 35 -->
						<tr align="center">
							<td align="center" colspan="4">
								<table class="subTable">
									<tr>
										<td style="width: 8%;">
											编制
										</td>
										<td style="width: 8%;">
											${gongyiGuicheng.bianzhiName}
										</td>
										<td style="width: 8%;">
											<s:date name="gongyiGuicheng.bianzhiDate" format="yyyy-MM-dd" />
										</td>
										<td style="width: 8%;">
											校对
										</td>
										<td style="width: 8%;">
											${gongyiGuicheng.jiaoduiName}
										</td>
										<td style="width: 8%;">
											<s:date name="gongyiGuicheng.jiaoduiDate" format="yyyy-MM-dd" />
										</td>
										<td style="width: 8%;">
											审核
										</td>
										<td style="width: 8%;">
											${gongyiGuicheng.shenheName}
										</td>
										<td style="width: 8%;">
											<s:date name="gongyiGuicheng.shenheDate" format="yyyy-MM-dd" />
										</td>
										<td style="width: 8%;">
											批准
										</td>
										<td style="width: 8%;">
											${gongyiGuicheng.pizhunName}
										</td>
										<td style="width: 8%;">
											<s:date name="gongyiGuicheng.pizhunDate" format="yyyy-MM-dd" />
										</td>
									</tr>
									<tr>
										<td>
											会签
										</td>
										<td colspan="2">
											生产加工
										</td>
										<td colspan="2">
											<select id="jiagongDept"
												style="color: #000000; width: 100px;">
											</select>
											<select id="jiagongId" name="gongyiGuicheng.jiagongId">
											</select>
										</td>
										<input type="hidden" id="jiagongName"
											name="gongyiGuicheng.jiagongName" />
										<td>
											<input class="Wdate" type="text" id="jiagongDate"
												name="gongyiGuicheng.jiagongDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										</td>
										<td colspan="3">
											技术质保部品质
										</td>
										<td colspan="2">
											<select id="pinzhiDept" style="color: #000000; width: 100px;">
											</select>
											<select id="pinzhiId" name="gongyiGuicheng.pinzhiId">
											</select>
										</td>
										<input type="hidden" id="pinzhiName"
											name="gongyiGuicheng.pinzhiName" />
										<td>
											<input class="Wdate" type="text" id="pinzhiDate"
												name="gongyiGuicheng.pinzhiDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</div>
				<!-- 页边距内结束 -->
			</div>
			<!-- A4页面打印结束 -->
		</div>
		<div style="width: 794px; text-align: center; margin: 0 auto;">

			<table style="width: 100%; text-align: center; border: 0px;">
				<tr>
					<td>
						<input type="button" id="saveButton" value="保存" class="input" />
						<%--<input type="button" id="markingButton" value="打分" class="input" />
						<input type="button" id="printButton" value="打印" class="input" />
						
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

	//全局工艺规程状态
	var dengluId = '${sessionScope.Users.id}';
	var status = '';
	var bianzhiId = '';
	var jiaoduiId = '';
	var shenheId = '';
	var pizhunId = '';
	var gygcId = $("#gygcId").val();
	var processDataId = $("#processDataId").val();
	//$("#printButton").bind("click",function() {
	//window.location="gongyiGuichengAction!getGongyiGuiChengGxtblmfqmxPage.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
	//});
	//添加零件
	function getProcessPartParams() {
		var $idArr = $("#partTable input[name='id']");
		var $numbArr = $("#partTable input[name='numb']");
		var $jianNumbArr = $("#partTable input[name='jianNumb']");
		var $jianNameArr = $("#partTable input[name='jianName']");
		var $danNumbArr = $("#partTable input[name='danNumb']");
		var $cailiaoArr = $("#partTable input[name='cailiao']");
		var $remarkArr = $("#partTable input[name='remark']");
		var $processDataIdArr = $("#partTable input[name='processDataId']");
		//var ids,numbs,jianNumbs,jianNames,danNumbs,cailiaos,remarks,processDataIds;
		//return {'ids':ids,'numbs':numbs,'jianNumbs':jianNumbs,'jianNames':jianNames,'danNumbs':danNumbs,'cailiaos':cailiaos,'remarks':remarks,'processDataIds':processDataIds};
		//return ids+';'+numbs+';'+jianNumbs+';'+jianNames+';'+danNumbs+';'+cailiaos+';'+remarks+';'+processDataIds;
		var processPartArr = new Array();
		for ( var i = 0; i < $idArr.length; i++) {
			processPartArr[i] = {
				'id' : $($idArr[i]).val(),
				'numb' : $($numbArr[i]).val(),
				'jianNumb' : $($jianNumbArr[i]).val(),
				'jianName' : $($jianNameArr[i]).val(),
				'danNumb' : $($danNumbArr[i]).val(),
				'cailiao' : $($cailiaoArr[i]).val(),
				'remark' : $($remarkArr[i]).val(),
				'processDataId' : $("#processDataId").val()
			};
		}
		return JSON.stringify(processPartArr);
	}
	//添加作业规范
	function getOperationStandardParams() {
		var $idArr = $("#operationStandardTable input[name='id']");
		var $numbArr = $("#operationStandardTable input[name='numb']");
		var $contentArr = $("#operationStandardTable input[name='content']");
		var $processDataIdArr = $("#partTable input[name='processDataId']");
		var operationStandardArr = new Array();
		for ( var i = 0; i < $idArr.length; i++) {
			operationStandardArr[i] = {
				'id' : $($idArr[i]).val(),
				'numb' : $($numbArr[i]).val(),
				'content' : $($contentArr[i]).val(),
				'processDataId' : $("#processDataId").val()
			};
		}
		return JSON.stringify(operationStandardArr);
	}
	//添加检查项目
	function getDetectionItemParams() {
		var $idArr = $("#detectionItemTable input[name='id']");
		var $numbArr = $("#detectionItemTable input[name='numb']");
		var $xiangmuArr = $("#detectionItemTable input[name='xiangmu']");
		var $qijuArr = $("#detectionItemTable input[name='qiju']");
		var $caozuoPinciArr = $("#detectionItemTable input[name='caozuoPinci']");
		var $xunjianPinciArr = $("#detectionItemTable input[name='xunjianPinci']");
		var $processDataIdArr = $("#detectionItemTable input[name='processDataId']");
		var detectionItemArr = new Array();
		for ( var i = 0; i < $idArr.length; i++) {
			detectionItemArr[i] = {
				'id' : $($idArr[i]).val(),
				'numb' : $($numbArr[i]).val(),
				'xiangmu' : $($xiangmuArr[i]).val(),
				'qiju' : $($qijuArr[i]).val(),
				'caozuoPinci' : $($caozuoPinciArr[i]).val(),
				'xunjianPinci' : $($xunjianPinciArr[i]).val(),
				'processDataId' : $("#processDataId").val()
			};
		}
		return JSON.stringify(detectionItemArr);
	}

	//添加工序数据Data jQuery数组取第一个值
	function getProcessDataParams() {
		var $cailiao = $("input[name='processData.cailiao']");
		//var $shebeiName=$("input[name='processData.shebeiName']");
		var $jizhun = $("input[name='processData.jizhun']");
		//var $gongxuName=$("input[name='processData.gongxuName']");
		//var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
		//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
		//var $danNumb=$("input[name='processData.danNumb']");
		//var $qianming=$("input[name='processData.qianming']");
		//var $qianmingDate=$("input[name='processData.qianmingDate']");
		//var $onePage=$("input[name='processData.onePage']");
		var processData = {
			'id' : $("#processDataId").val(),
			'cailiao' : $cailiao.val(),
			//'shebeiName':$shebeiName.val(),
			'jizhun' : $jizhun.val()
		//'gongxuName':$gongxuName.val(),
		//'jiaOrMoju':$jiaOrMoju.val(),
		//'suoyinNumb':$suoyinNumb.val(),
		//'danNumb':$danNumb.val(),
		//'qianming':$qianming.val(),
		//'qianmingDate':$qianmingDate.val(),
		//'onePage':$onePage.val()
		};
		return JSON.stringify(processData);
	}
	//工艺规程
	function getgongyiGuichengParams() {
		//var $jiagongId=$("input[name='gongyiGuicheng.jiagongId']");取不到值
		var $jiagongId = $("#jiagongId");
		var $jiagongName = $("input[name='gongyiGuicheng.jiagongName']");
		var $jiagongDate = $("input[name='gongyiGuicheng.jiagongDate']");

		var $pinzhiId = $("#pinzhiId");
		var $pinzhiName = $("input[name='gongyiGuicheng.pinzhiName']");
		var $pinzhiDate = $("input[name='gongyiGuicheng.pinzhiDate']");
		var gongyiGuicheng = {
			'id' : $("#gygcId").val(),
			'jiagongId' : $jiagongId.val(),
			'jiagongName' : $jiagongName.val(),
			'jiagongDate' : $jiagongDate.val(),
			'pinzhiId' : $pinzhiId.val(),
			'pinzhiName' : $pinzhiName.val(),
			'pinzhiDate' : $pinzhiDate.val()
		};
		return JSON.stringify(gongyiGuicheng);
	}

	//*************************************以上获取参数******************************************************
	//*************************************下边初始化表格******************************************************
	//初始化零件
	//$("#partTable tr:last").remove();
	$
			.ajax( {
				type : "post",
				dataType : "json",
				url : "gongyiGuichengAction!getProcessPartListByprocessDataId.action",
				data : {
					'processPart.processDataId' : $('#processDataId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						var processPartArr = data.data;
						$(processPartArr)
								.each(
										function(i, n) {
											$(
													'<tr><td><input type="hidden" id="" name="id" value="'
															+ this.id
															+ '"/><input type="hidden" id="" name="processDataId" value="'
															+ this.processDataId
															+ '"/><input type="text" id="" name="numb" style="width: 100%;" value="'
															+ this.numb
															+ '"/></td><td><input type="text" id="" name="jianNumb" style="width: 100%;" value="'
															+ this.jianNumb
															+ '"/></td><td><input type="text" id="" name="jianName" style="width: 100%;" value="'
															+ this.jianName
															+ '"/></td><td><input type="text" id="" name="danNumb" style="width: 100%;" value="'
															+ this.danNumb
															+ '"/></td><td><input type="text" id="" name="cailiao" style="width: 100%;" value="'
															+ this.cailiao
															+ '"/></td><td><input type="text" id="" name="remark" style="width: 100%;" value="'
															+ this.remark
															+ '"/></td></tr>')
													.appendTo('#partTable');
										});
					} else {
						$(
								'<tr><td><input type="hidden" id="" name="id" value=""/><input type="hidden" id="" name="processDataId" value=""/><input type="text" id="" name="numb" style="width: 100%;" value=""/></td><td><input type="text" id="" name="jianNumb" style="width: 100%;" value=""/></td><td><input type="text" id="" name="jianName" style="width: 100%;" value=""/></td><td><input type="text" id="" name="danNumb" style="width: 100%;" value=""/></td><td><input type="text" id="" name="cailiao" style="width: 100%;" value=""/></td><td><input type="text" id="" name="remark" style="width: 100%;" value=""/></td></tr>')
								.appendTo('#partTable');
					}
				}
			});

	//初始化操作规范

	$
			.ajax( {
				type : "post",
				dataType : "json",
				url : "gongyiGuichengAction!getOperationStandardListByprocessDataId.action",
				data : {
					'operationStandard.processDataId' : $('#processDataId')
							.val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						//$("#operationStandardTable tr:gt(0)").remove();
						var operationStandardArr = data.data;
						$(operationStandardArr)
								.each(
										function(i, n) {
											$(
													'<tr><td><input type="hidden" name="id" value="'
															+ this.id
															+ '"/><input type="hidden" name="processDataId" value="'
															+ this.processDataId
															+ '"/><input type="text" name="numb"  style="width: 20px;" value="'
															+ this.numb
															+ '"/></td><td><input type="text" name="content" style="width: 100%;" value="'
															+ this.content
															+ '"/></td></tr>')
													.replaceAll(
															'#operationStandardTable tr:eq(' + (i + 1) + ')');
										});
					}
				}
			});
	//初始化检查项目
	$
			.ajax( {
				type : "post",
				dataType : "json",
				url : "gongyiGuichengAction!getDetectionItemListByprocessDataId.action",
				data : {
					'detectionItem.processDataId' : $('#processDataId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						//$("#operationStandardTable tr:gt(0)").remove();
				var detectionItemArr = data.data;
				$(detectionItemArr)
						.each(
								function(i, n) {
									$(
											'<tr><td><input type="hidden" name="id" value="'
													+ this.id
													+ '"/><input type="hidden" name="processDataId" value="'
													+ this.processDataId
													+ '"/><input type="text" name="numb" style="width: 20px;" value="'
													+ this.numb
													+ '"/></td><td><input type="text" name="xiangmu" value="'
													+ this.xiangmu
													+ '"/></td><td><input type="text" name="qiju" value="'
													+ this.qiju
													+ '"/></td><td><input type="text" name="caozuoPinci" value="'
													+ this.caozuoPinci
													+ '"/></td><td><input type="text" name="xunjianPinci" value="'
													+ this.xunjianPinci
													+ '"/></td><td></td></tr>')
											.replaceAll(
													'#detectionItemTable tr:eq(' + (i + 1) + ')');
								});
			}
		}
			});

	//初始化工序数据
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getProcessDataId.action",
		data : {
			'processData.id' : $('#processDataId').val()
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var processData = data.data;
				var cailiao = processData.cailiao;
				var shebeiName = processData.shebeiName;
				var jizhun = processData.jizhun;
				var gongxuName = processData.gongxuName;
				var jiaOrMoju = processData.jiaOrMoju;
				var gongzhuangName = processData.gongzhuangName;
				//var suoyinNumb=processData.suoyinNumb;
		//var danNumb=processData.danNumb;
		//var qianming=processData.qianming;
		//var qianmingDate=processData.qianmingDate==null? new Date(): processData.qianmingDate;
		//qianmingDate=new Date(qianmingDate).format('yyyy-MM-dd');
		//var onePage=processData.onePage;

		var $cailiao = $("input[name='processData.cailiao']");
		var $shebeiName = $("input[name='processData.shebeiName']");
		var $jizhun = $("input[name='processData.jizhun']");
		var $gongxuName = $("input[name='processData.gongxuName']");
		var $jiaOrMoju = $("input[name='processData.jiaOrMoju']");
		//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
		//var $danNumb=$("input[name='processData.danNumb']");
		//var $qianming=$("input[name='processData.qianming']");
		//var $qianmingDate=$("input[name='processData.qianmingDate']");
		//var $onePage=$("input[name='processData.onePage']");
		$cailiao.val(cailiao);
		$shebeiName.val(shebeiName);
		$jizhun.val(jizhun);
		$gongxuName.val(gongxuName);
		$jiaOrMoju.val(gongzhuangName);
		//$suoyinNumb.val(suoyinNumb);
		//$danNumb.val(danNumb);
		//$qianming.val(qianming);
		//$qianmingDate.val(qianmingDate);
		//$onePage.val(onePage);
	}
}
	});
	//添加 删除零
	$("#addPartButton").bind("click", function() {
		var $addPartTable = $("#partTable");
		var $tr = $("#partTable tr:last").clone(false);
		$addPartTable.append($tr);
		$tr.find("input[name='id']").val('');
	});
	$("#deletePartButton")
			.bind(
					"click",
					function() {
						var addPartTable = $("#partTable");
						var subTr = $("#partTable tr");
						if (subTr.length == 2) {
							alert('不能再删了!');
							return;
						}
						var processPartId = $("#partTable tr:last").find(
								"input[name='id']").val();
						$("#partTable tr:last").remove();
						if (processPartId) {
							$
									.ajax( {
										type : "post",
										dataType : "json",
										url : "gongyiGuichengAction!deleteProcessPartById.action",
										data : {
											'processPart.id' : processPartId
										},
										async : true,
										success : function(data) {
											//不做任何处理
									}
									});
						}
					});
	//********************************保存页面数据******************************************************
	$("#saveButton")
			.bind(
					"click",
					function() {
						var processPartParams = getProcessPartParams();
						var operationStandardParams = getOperationStandardParams();
						var detectionItemParams = getDetectionItemParams();
						var processDataParams = getProcessDataParams();
						var gongyiGuichengParams = getgongyiGuichengParams();
						$
								.ajax( {
									type : "post",
									dataType : "json",
									url : "gongyiGuichengAction!updateGongyiGuiChengGxtblmfqmxPage.action",
									data : {
										'processPart.params' : processPartParams,
										'operationStandard.params' : operationStandardParams,
										'detectionItem.params' : detectionItemParams,
										'processData.params' : processDataParams,
										'gongyiGuicheng.params' : gongyiGuichengParams
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											alert("保存成功");
											window.location.reload();
										}
									}
								});
					});
	//********************************保存页面数据******************************************************
	//*************************************打分******************************************************
	//$("form table:visible").css('display','none');

	//********************************部门人员管理***************************************************
	//加工
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#jiagongDept");
			$("<option value='' >请选择人员</option>").appendTo("#jiagongId");
			$(data).each(
					function(i, n) {
						$(
								"<option value='" + this.id + "'>"
										+ this.groupName + "</option>")
								.appendTo("#jiagongDept");
					});
		}
	});

	//级联查询出部门所对应的所有人员

	$("#jiagongDept")
			.bind(
					"change",
					function() {
						if ($("#jiagongDept").val() != "") {
							$
									.ajax( {
										url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
										type : 'post',
										dataType : 'json',
										cache : false,//防止数据缓存
										data : {
											id : $("#jiagongDept").val()
										},
										success : function(useradsfa) {
											$("#jiagongId").empty();//清空
										$(
												"<option value='' data-name=''>请选择人员</option>")
												.appendTo("#jiagongId");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.userId
																			+ "' data-name='"
																			+ this.userName
																			+ "'>"
																			+ this.userName
																			+ "</option>")
																	.appendTo(
																			"#jiagongId");
														});
									},
									error : function() {
										alert("服务器异常!");
									}
									});
						}
					});
	//品质
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#pinzhiDept");
			$("<option value='' >请选择人员</option>").appendTo("#pinzhiId");
			$(data).each(
					function(i, n) {
						$(
								"<option value='" + this.id + "'>"
										+ this.groupName + "</option>")
								.appendTo("#pinzhiDept");
					});
		}
	});

	//级联查询出部门所对应的所有人员

	$("#pinzhiDept")
			.bind(
					"change",
					function() {
						if ($("#jiagongDept").val() != "") {
							$
									.ajax( {
										url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
										type : 'post',
										dataType : 'json',
										cache : false,//防止数据缓存
										data : {
											id : $("#pinzhiDept").val()
										},
										success : function(useradsfa) {
											$("#pinzhiId").empty();//清空
										$(
												"<option value='' data-name=''>请选择人员</option>")
												.appendTo("#pinzhiId");
										$(useradsfa)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.userId
																			+ "' data-name='"
																			+ this.userName
																			+ "'>"
																			+ this.userName
																			+ "</option>")
																	.appendTo(
																			"#pinzhiId");
														});
									},
									error : function() {
										alert("服务器异常!");
									}
									});
						}
					});
	$("#jiagongId").bind("change", function() {
		var selectedIndex = $(this).get(0).selectedIndex;
		//$(this).get(0).options[selectedIndex].selected = true;
			var selectedOption = $($(this).get(0).options[selectedIndex]);
			var jiagongName = selectedOption.attr('data-name');
			$('#jiagongName').val(jiagongName);
		});
	$("#pinzhiId").bind("change", function() {
		var selectedIndex = $(this).get(0).selectedIndex;
		//$(this).get(0).options[selectedIndex].selected = true;
			var selectedOption = $($(this).get(0).options[selectedIndex]);
			var pinzhiName = selectedOption.attr('data-name');
			$('#pinzhiName').val(pinzhiName);
		});
	//初始化工艺规程
	$
			.ajax( {
				type : "get",
				dataType : "json",
				url : "gongyiGuichengAction!getGongyiGuichengById.action",
				data : {
					'gongyiGuicheng.id' : $('#gygcId').val()
				},
				async : false,
				success : function(data) {
					var success = data.success;
					if (success) {
						data = data.data;
						var jiagongId = data.jiagongId;
						var pinzhiId = data.pinzhiId;
						var jiagongName = data.jiagongName == null ? ''
								: data.jiagongName;
						var pinzhiName = data.pinzhiName == null ? ''
								: data.pinzhiName;
						var jiagongDate = data.jiagongDate == null ? new Date()
								: data.jiagongDate;
						var pinzhiDate = data.pinzhiDate == null ? new Date()
								: data.pinzhiDate;
						jiagongDate = new Date(jiagongDate)
								.format('yyyy-MM-dd');
						pinzhiDate = new Date(pinzhiDate).format('yyyy-MM-dd');
						//$('#jiagongId').find("option[value='"+jiagongId+"']").attr("selected",true);
						//$('#pinzhiId').find("option[value='"+pinzhiId+"']").attr("selected",true);
						$('#jiagongId').append(
								"<option value='" + jiagongId + "' data-name='"
										+ jiagongName
										+ "' selected='selected'>"
										+ jiagongName + "</option>");
						$('#pinzhiId').append(
								"<option value='" + pinzhiId + "' data-name='"
										+ pinzhiName + "' selected='selected'>"
										+ pinzhiName + "</option>");
						$('#jiagongDate').val(jiagongDate);
						$('#pinzhiDate').val(pinzhiDate);
					}
				}
			});

	//****************************************添加权限******************************************************

	/*
	 $.ajax({
	 type: "post",
	 dataType: "json",
	 url: "gongyiGuichengAction!getGongyiGuichengById.action",
	 data:{
	 'gongyiGuicheng.id': $('#gygcId').val()
	 },
	 async: false,
	 success: function(data){
	 var success=data.success;
	 if(success){
	 var data=data.data;
	
	 bianzhiId=data.bianzhiId;
	 jiaoduiId=data.jiaoduiId;
	 shenheId=data.shenheId;
	 pizhunId=data.pizhunId;
	
	 status=data.status;
	 //$('#button').attr('disabled',"true");添加disabled属性
	 //$('#button').removeAttr("disabled"); 移除disabled属性 
	
	 //markingForm
	 //markingButton
	 //saveButton
	 //presentButton
	 //printButton
	 switch(status){
	 case '打回':
	 case '待编制':
	 $("#markingForm input").attr('disabled',true);
	 //$("#markingButton").attr('disabled',true);
	 $("#backButton").attr('disabled',true);
	
	 break;
	 case '已编制':
	 if(jiaoduiId==dengluId){
	 $(":enabled").attr('disabled',true);
	
	 $("#markingForm input").removeAttr("disabled");
	 //$("#markingButton").removeAttr('disabled');
	 //$("#printButton").removeAttr("disabled");
	 }else{
	 $(":enabled").attr('disabled',true);
	 }
	
	 break;
	 case '已校对':
	 if(shenheId==dengluId){
	 $(":enabled").attr('disabled',true);
	
	 //$("#markingForm input").removeAttr("disabled");
	 //$("#markingButton").removeAttr('disabled');
	 //$("#printButton").removeAttr("disabled");
	 }else{
	 $(":enabled").attr('disabled',true);
	 }
	
	 break;
	 case '已审核':
	 if(pizhunId==dengluId){
	 $(":enabled").attr('disabled',true);
	
	 //$("#markingForm input").removeAttr("disabled");
	 //$("#markingButton").removeAttr('disabled');
	 //$("#printButton").removeAttr("disabled");
	 }else{
	 $(":enabled").attr('disabled',true);
	 }
	 break;
	 case '已批准':
	 $(":enabled").attr('disabled',true);
	
	 //$("#printButton").removeAttr("disabled");
	 break;
	 default :
	
	 break;
	 }
	 }
	 }
	 });*/
	//*************************末尾***************************
});
</script>
