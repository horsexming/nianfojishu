<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	//response.setHeader("Cache-Control","no-store");	
	//response.setHeader("Pragrma","no-cache");
	//response.setDateHeader("Expires",0);
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
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
				
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
			.subTable tr,.subTable td{
				height: 20px;
				border-width: 1px;
			}
		</style>
		<script type="text/javascript">
		var processImg='';
		function uploadProcessDataAffixResend(data){
			var processData=JSON.parse(data);
			var processImg=processData.processImg==null? '': processData.processImg;
			var processVideo=processData.processVideo==null? '':processData.processVideo;
			document.getElementById("processImg").src=processImg;
		}
		</script>
	</head>
	<body style="text-align: center;">
			<div align="center" style="width: 100%;">
				<!-- A4页面开始 -->
				<div id="printDiv" style="width:1587px;height:1122px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：1549×1084   5mm/18.89  19-->
					<div style="width:1549px;height:1084px;border:1px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<div style=" font-size: 24px;">检验图表</div>
					<table border="0" class="table" cellspacing="0" cellpadding="0" style="width: 100%">
						<!-- 1 -->
						<tr align="center">
							<td>
								<p><img style="width: 500px;height: 600px;" id="processImg" src="${processData.processImg}" alt="请上传图片"/></p>
								<p>
								    <form action="gongyiGuichengAction!uploadProcessDataAffix.action?affixType=processImg" enctype="multipart/form-data" method="post" target="hidden_iframe">  
								      <div style="position:relative;display:none;">  
								        <input type="hidden" name="processData.id" value="${processData.id}" />  
								        <iframe name="hidden_iframe" id="hidden_iframe"></iframe>  
								      </div>  
							    	  <input type="file" name="processDataImgFile" />  
									  <input type="button" id="upload" value=" 上 传 " onclick="this.form.submit();"  />
								    </form>  
								</p>
							</td>
							<td>
								<table class="subTable" id="operationOrderTable" >
									<tr>
										<td style="width: 80px;">操作序号</td>
										<td style="width: 80px;">操作顺序<br/><input id="addOperationOrderButton" type="button" value="添加"><input id="deleteOperationOrderButton" type="button" value="删除"></td>
										<td style="width: 80px;">检查项目</td>
										<td style="width: 80px;">测量器具</td>
										<td style="width: 80px;">判断标准</td>
										<td style="width: 80px;">检验频次</td>
										<td style="width: 80px;">异常处理检查要领</td>
									</tr>
									<tr>
										<td><input type="hidden" name="operationOrder.id" /><input type="text" name="operationOrder.numb" style="width: 100%;"/></td>
										<td><input type="text" name="operationOrder.content" style="width: 100%;"/></td>
										<td colspan="4">
											<table class="subTable" name="operationOrderItemTable">
												<tr><td colspan="4"><input name="addOperationOrderItemButton" type="button" value="添加"><input name="deleteOperationOrderItemButton" type="button" value="删除"></td></tr>
												<tr>
													<td><input type="hidden" name="operationOrderItem.id"/><input type="text" name="operationOrderItem.xiangmu" style="width: 100%;"/></td>
													<td><input type="text" name="operationOrderItem.qiju" style="width: 100%;"/></td>
													<td><input type="text" name="operationOrderItem.pandingBiaozhun" style="width: 100%;"/></td>
													<td><input type="text" name="operationOrderItem.jianchaPinci" style="width: 100%;"/></td>
												</tr>
											</table>
										</td>
										<td>异常检查内容</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 2 -->
						<tr>
							<td colspan="2">
								<table class="subTable">
									<tr>
										<td>件名</td>
										<td>${gongyiGuicheng.jianName}</td>
										<td>工序名称</td>
										<td width="100px;">${processData.gongxuName}</td>
										<td>型别</td>
										<td>${gongyiGuicheng.xingbie}</td>
										<td>编制</td>
										<td>审核</td>
										<td>批准</td>
										<td rowspan="2">会签</td>
										<td>加工</td>
										<td>品质</td>
									</tr>
									<tr>
										<td>件号</td>
										<td>${gongyiGuicheng.jianNumb}</td>
										<td>工序号</td>
										<td>${processData.gongxuNo}</td>
										<td>工装编号</td>
										<td>${processData.gongzhuangNo}</td>
										<td>${gongyiGuicheng.bianzhiName}<s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></td>
										<td>${gongyiGuicheng.shenheName}<s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></td>
										<td>${gongyiGuicheng.pizhunName}<s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></td>
										<td>${gongyiGuicheng.jiagongName}<s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></td>
										<td>${gongyiGuicheng.pinzhiName}<s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</div>
				</div>
			</div>
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post"
					style="">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="jyxmlq"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				<table id="jyxmlq" style="display: block;">
					<tr>
						<td><span>序号栏：</span></td>
						<td>
							<label for="xingbieScore1">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.xuhaoScoreJyxmlq" value="2" checked="checked"/>
							<label for="xingbieScore2">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.xuhaoScoreJyxmlq" value="0"/>
							<label for="xingbieScore3">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.xuhaoScoreJyxmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>检验项目栏：</span></td>
						<td>
							<label for="xingbieScore1">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.xiangmuScoreJyxmlq" value="2" checked="checked"/>
							<label for="xingbieScore2">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.xiangmuScoreJyxmlq" value="0"/>
							<label for="xingbieScore3">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.xiangmuScoreJyxmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>测量器具：</span></td>
						<td>
							<label for="xingbieScore1">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.qijuScoreJyxmlq" value="2" checked="checked"/>
							<label for="xingbieScore2">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.qijuScoreJyxmlq" value="0"/>
							<label for="xingbieScore3">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.qijuScoreJyxmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>测定频率：</span></td>
						<td>
							<label for="xingbieScore1">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.cedingPinlvScoreJyxmlq" value="2" checked="checked"/>
							<label for="xingbieScore2">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.cedingPinlvScoreJyxmlq" value="0"/>
							<label for="xingbieScore3">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.cedingPinlvScoreJyxmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>辅助栏：</span></td>
						<td>
							<label for="xingbieScore1">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.fuzhuScoreJyxmlq" value="2" checked="checked"/>
							<label for="xingbieScore2">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.fuzhuScoreJyxmlq" value="0"/>
							<label for="xingbieScore3">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.fuzhuScoreJyxmlq" value="-2"/>
						</td>
					</tr>
				</table>
				</form>
				<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<td><input type="button" id="saveButton" value="保存" class="input" /><input type="button" id="markingButton" value="打分" class="input" /><input type="button" id="printButton" value="打印" class="input" /></td>
					</tr>
				</table>
				
			</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	//全局工艺规程状态
	var dengluId='${sessionScope.Users.id}';
	var status='';
	var bianzhiId='';
	var jiaoduiId='';
	var shenheId='';
	var pizhunId='';
	var gygcId=$("#gygcId").val();
	var processDataId=$("#processDataId").val();
	$("#printButton").bind("click",function() {
		window.location="gongyiGuichengAction!getGongyiGuiChengJyxmlqPage.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
	});
	//****************************************初始化参数****************************************************
	//初始化操作顺序
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getOperationOrderListByprocessDataId.action",
        data:{
			'operationOrder.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var operationOrderArr=data.data;
				//var $tr=$("#operationOrderTable>tbody>tr:last-child").clone(true);
				//$operationOrderTable.append($tr);
				var $operationOrderTable=$("#operationOrderTable");
				if(operationOrderArr.length>0){
					$("#operationOrderTable>tbody>tr:last-child").remove();
				}
				var html='';
				$(operationOrderArr).each(function(i,n){
					var operationOrder=n;
					var operationOrderItemList=operationOrder.operationOrderItemList;
					 	html +=	'<tr>';
						html +=		'<td><input type="hidden" name="operationOrder.id" value="'+operationOrder.id+'"/><input type="text" name="operationOrder.numb" value="'+operationOrder.numb+'" style="width: 100%;"/></td>';
						html +=		'<td><input type="text" name="operationOrder.content" value="'+operationOrder.content+'" style="width: 100%;"/></td>';
						html +=		'<td colspan="4">';
						html +=			'<table class="subTable" name="operationOrderItemTable">';
						html +=				'<tr><td colspan="4"><input name="addOperationOrderItemButton" type="button" value="添加"><input name="deleteOperationOrderItemButton" type="button" value="删除"></td></tr>';
						$(operationOrderItemList).each(function(i,m){
						var operationOrderItem=m
						html +=				'<tr>';
						html +=					'<td><input type="hidden" name="operationOrderItem.id" value="'+operationOrderItem.id+'"/><input type="text" name="operationOrderItem.xiangmu" value="'+operationOrderItem.xiangmu+'" style="width: 100%;"/></td>';
						html +=					'<td><input type="text" name="operationOrderItem.qiju" value="'+operationOrderItem.qiju+'" style="width: 100%;"/></td>';
						html +=					'<td><input type="text" name="operationOrderItem.pandingBiaozhun" value="'+operationOrderItem.pandingBiaozhun+'" style="width: 100%;"/></td>';
						html +=					'<td><input type="text" name="operationOrderItem.jianchaPinci" value="'+operationOrderItem.jianchaPinci+'" style="width: 100%;"/></td>';
						html +=				'</tr>';
						});
						html +=			'</table>';
						html +=		'</td>';
						html +=		'<td>异常检查内容</td>';
						html +=	'</tr>';
				});
				$operationOrderTable.append(html);
				
			}
		}
	});
	
	//*************************************获取操作顺序参数************************************************
	function getOperationOrderParams(){
		var $idArr=$("input[name='operationOrder.id']");
		var $numbArr=$("input[name='operationOrder.numb']");
		var $contentArr=$("input[name='operationOrder.content']");
		
		var $operationOrderItemTableArr=$("table[name='operationOrderItemTable']");
		
		var operationOrderArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			operationOrderArr[i]={
				'id': $($idArr[i]).val(),
				'numb': $($numbArr[i]).val(),
				'content': $($contentArr[i]).val(),
				'processDataId': $("#processDataId").val()
			}
			var $operationOrderItemTable=$($operationOrderItemTableArr[i]);
			var $operationOrderItemIdArr=$operationOrderItemTable.find("input[name='operationOrderItem.id']");
			var $xiangmuArr=$operationOrderItemTable.find("input[name='operationOrderItem.xiangmu']");
			var $qijuArr=$operationOrderItemTable.find("input[name='operationOrderItem.qiju']");
			var $pandingBiaozhunArr=$operationOrderItemTable.find("input[name='operationOrderItem.pandingBiaozhun']");
			var $jianchaPinciArr=$operationOrderItemTable.find("input[name='operationOrderItem.jianchaPinci']");
			var operationOrderItemArr=new Array();
			for(var j=0;j<$operationOrderItemIdArr.length;j++){
				operationOrderItemArr[j]={
					'id': $($operationOrderItemIdArr[j]).val(),
					'xiangmu': $($xiangmuArr[j]).val(),
					'qiju': $($qijuArr[j]).val(),
					'pandingBiaozhun': $($pandingBiaozhunArr[j]).val(),
					'jianchaPinci': $($jianchaPinciArr[j]).val()
				}
			}
			operationOrderArr[i].operationOrderItemList=operationOrderItemArr;
			
		}
		return JSON.stringify(operationOrderArr);
	}
	
	//添加删除 操作顺序
	$("#addOperationOrderButton").bind("click",function(){
		var $operationOrderTable=$("#operationOrderTable");
		var $tr=$("#operationOrderTable>tbody>tr:last-child").clone(true);
		$operationOrderTable.append($tr);
		$tr.find("input[name='operationOrder.id']").val('');
		$tr.find("input[name='operationOrderItem.id']").each(function(){
			$(this).val('');
		});
	});
	$("#deleteOperationOrderButton").bind("click",function(){
		var $operationOrderTable=$("#operationOrderTable");
		var $subTr=$("#operationOrderTable>tbody>tr");
		if($subTr.length<3){
			alert('不能再删了!');
			return;
		}
		var operationOrderId=$("#operationOrderTable>tbody>tr:last-child").find("input[name='operationOrder.id']").val();
		$("#operationOrderTable>tbody>tr:last-child").remove();
		if(operationOrderId){
			$.ajax({
				type: "post",
				dataType: "json",
		        url: "gongyiGuichengAction!deleteOperationOrderById.action",
		        data:{
					'operationOrder.id': operationOrderId
				},
				async: true,
				success: function(data){
					//不做任何处理
				}
			});
		}
	});
	//添加删除 操作顺序下 检查项目
	$("input[name='addOperationOrderItemButton']").bind("click",function(){
		var $operationOrderItemTable=$(this).parents("table").first();
		var $tr=$operationOrderItemTable.find("tbody>tr:last-child").clone(false);
		$operationOrderItemTable.append($tr);
		$tr.find("input[name='operationOrderItem.id']").val('');
	});
	$("input[name='deleteOperationOrderItemButton']").bind("click",function(){
		var $operationOrderItemTable=$(this).parents("table").first();
		var $subTr=$operationOrderItemTable.find("tbody>tr");
		if($subTr.length<3){
			alert('不能再删了!');
			return;
		}
		var operationOrderItemId=$operationOrderItemTable.find("tbody>tr:last-child").find("input[name='operationOrderItem.id']").val();
		$operationOrderItemTable.find("tbody>tr:last-child").remove();
		if(operationOrderItemId){
			$.ajax({
				type: "post",
				dataType: "json",
		        url: "gongyiGuichengAction!deleteOperationOrderItemById.action",
		        data:{
					'operationOrderItem.id': operationOrderItemId,
				},
				async: true,
				success: function(data){
					//不做任何处理
				}
			});
		}
	});
	//************************************************保存页面数据**********************************************************
	$("#saveButton").bind("click",function(){
		var operationOrderParams=getOperationOrderParams();
		
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!updateGongyiGuiChengJyxmlqPage.action",
	        data:{
				'operationOrder.params': operationOrderParams
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("保存成功");
					window.location.reload();
				}
			}
		});
	});
	//*******************************************************打分区***********************************************************
	//$("form table:visible").css('display','none');
	$.ajax({
		type: "get",
		dataType: "json",
        url: "gongyiGuichengScoreAction!getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId.action",
        data:{
			'gongyiGuichengScore.gongyiGuichengId': $("#gygcId").val(),
			'gongyiGuichengScore.model': "jyxmlq",
			'gongyiGuichengScore.processDataId': $('#processDataId').val()
		},
		async: true,
		success: function(data){
			var success=data.success;
			if(success){
				var gongyiGuichengScore=data.data;
				$("input[name='gongyiGuichengScore.xuhaoScoreJyxmlq'][value="+gongyiGuichengScore.xuhaoScoreJyxmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.xiangmuScoreJyxmlq'][value="+gongyiGuichengScore.xiangmuScoreJyxmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.qijuScoreJyxmlq'][value="+gongyiGuichengScore.qijuScoreJyxmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.cedingPinlvScoreJyxmlq'][value="+gongyiGuichengScore.cedingPinlvScoreJyxmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.fuzhuScoreJyxmlq'][value="+gongyiGuichengScore.fuzhuScoreJyxmlq+"]").attr("checked",true);
			}
		}
	});
	$('#markingButton').bind('click',function(){
		var param=$('#markingForm').serialize();
		$.ajax({
			type: "get",
			dataType: "json",
	        url: "gongyiGuichengScoreAction!updateGongyiGuichengScore.action",
	        data:$('#markingForm').serialize(),
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					var gongyiGuichengScore=data.data;
					alert('打分成功!');
				}else{
					alert('打分失败!');
				}
			}
		});
	});
	
	//****************************************添加权限******************************************************
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
						$("#markingButton").attr('disabled',true);
						$("#backButton").attr('disabled',true);
						
						break;
					case '已编制':
						if(jiaoduiId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已校对':
						if(shenheId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已审核':
						if(pizhunId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						break;
					case '已批准':
						$(":enabled").attr('disabled',true);
						
						$("#printButton").removeAttr("disabled");
						break;
					default :
						
						break;
				}
			}
		}
	});
});
</script>
