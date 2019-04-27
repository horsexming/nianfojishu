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
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
				width: 756px;
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
			<div align="center">
				<!-- A4页面开始 -->
				<div id="printDiv" style="width:794px;height:1123px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
					<div style="width:756px;height:1086px;border:1px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table border="0" width="100%" class="table" cellspacing="0" cellpadding="0">
						<!-- 1 -->
						<tr align="center">
							<td>
								<table class="subTable">
									<tr><td rowspan="2" style="width: 13%;"><img style="width: 80px;height: 80px;" src="<%=basePath%>${companyInfo.shhhjpg}"/></td><td style="width: 20%;font-size: 25px;" rowspan="2">毛料图表<br/><select name="processData.maoliaoType" ><option value="管料">管料</option><option value="板料">板料</option></select></td><td style="width: 13%">型别</td><td style="width: 15%">件号</td><td style="width: 15%">件名</td><td style="width: 10%;">工序号</td><td style="width: 4%">第</td><td style="width: 4%">1</td><td style="width: 4%">页</td></tr>
									<tr><td>${gongyiGuicheng.xingbie}</td><td>${gongyiGuicheng.jianNumb}</td><td>${gongyiGuicheng.jianName}</td><td>${processData.gongxuNo}</td><td>共</td><td>1</td><td>页</td></tr>
								</table>
							</td>
						</tr>
						<!-- 2 -->
						<tr align="center">
							<td>
								<table class="subTable">
									<tr>
										<td rowspan="2">材料</td><td>牌号</td><td><input type="text" name="processData.paihao" style="width: 100%;"/></td><td>分类</td><td><input type="text" name="processData.fenlei" style="width: 100%;"/></td><td>切割设备</td>
									</tr>
									<tr>
										<td>厚度</td><td><input type="text" name="processData.houdu" style="width: 100%;"/></td><td>技术条件</td><td><input type="text" name="processData.jishuTiaojian" style="width: 100%;"/></td><td><input type="text" name="processData.qieliaoShebei" style="width: 100%;"/></td>
									</tr>
								</table>
							</td>
						</tr>
						
						<!-- 3 -->
						<tr align="center">
							<td>
								<table id="partTable" class="subTable" style="width: 100%;margin-bottom: 20px;border: 1px;">
									<tr>
										<td>
											<img id="processImg" style="width: 500px;height: 400px;" src="${processData.processImg}"/>
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
									</tr>
								</table>
							</td>
						</tr>
						<!-- 4-->
						<tr align="center">
							<td>
								<table id="maoliaoParam" class="subTable">
									<tr>
										<td style="width: 5%;">项目</td><td style="width: 35%;">内容</td><td style="width: 20%;">单位</td><td style="width: 20%;">数值</td><td style="width: 20%;">备注</td>
									</tr>
									<!-- 1 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 2 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 3 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 4 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 5 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 6 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 7 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 8 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 9 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 10 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 11 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
									<!-- 12 -->
									<tr>
										<td><input type="hidden" name="maoliaoParam.id"/><input type="text" name="maoliaoParam.numb" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.content" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.danwei" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.shuzhi" style="width: 100%;"/></td><td><input type="text" name="maoliaoParam.remark" style="width: 100%;"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="4">
								<table id="maoliaoJishuTiaojian" class="subTable">
									<tr>
										<td rowspan="3" style="width: 5%;">技<br/>术<br/>条<br/>件</td>
										<td style="width: 45%;"><input type="hidden" name="maoliaoJishuTiaojian.id"/><input type="text" name="maoliaoJishuTiaojian.content" style="width: 100%;"/></td>
										<td rowspan="3" style="width: 5%;">更改</td>
										<td style="width: 15%;">索引号</td>
										<td style="width: 30%;"><%--<input type="text" name="processData.suoyinNumb" style="width: 100%;"/>--%></td>
										
									</tr>
									<tr>
										<td><input type="hidden" name="maoliaoJishuTiaojian.id"/><input type="text" name="maoliaoJishuTiaojian.content" style="width: 100%;"/></td>
										<td>更改单号</td>
										<td><%--<input type="text" name="processData.danNumb" style="width: 100%;"/>--%></td>
										
									</tr>
									<tr>
										<td><input type="hidden" name="maoliaoJishuTiaojian.id"/><input type="text" name="maoliaoJishuTiaojian.content" style="width: 100%;"/></td>
										<td>签名、日期</td>
										<td><%--<input type="text" name="processData.qianming" style="width: 40%;"/>--%>
										<%--<input class="Wdate" type="text" name="processData.qianmingDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" style="width: 40%;"/>--%></td>
										
									</tr>
								</table>
							</td>
						</tr>
						<!-- 35 -->
						<tr align="center">
							<td align="center" colspan="4" >
								<table class="subTable">
									<tr>
										<td style="width: 8%;">编制</td>
										<td style="width: 8%;">${gongyiGuicheng.bianzhiName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">校对</td>
										<td style="width: 8%;">${gongyiGuicheng.jiaoduiName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">审核</td>
										<td style="width: 8%;">${gongyiGuicheng.shenheName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">批准</td>
										<td style="width: 8%;">${gongyiGuicheng.pizhunName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></td>
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
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="gxtblmfqmx"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				<!-- 工序图表栏目分区明细 -->
				<table id="gxtblmfqmx" style="display: block;">
					<tr>
						<td><span>检查内容：</span></td>
						<td>
							<label for="">5</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="5" checked="checked"/>
							<label for="">10</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="10"/>
							<label for="">15</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="15"/>
							<label for="">20</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="20"/>
							<label for="">25</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="25"/>
							<label for="">30</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="30"/>
							<label for="">35</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="35"/>
							<label for="">40</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="40"/>
							<label for="">45</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.contentScoreGxtblmfqmx" value="45"/>
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
		window.location="gongyiGuichengAction!getGongyiGuiChengGxsmlqMaoliaoPage.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
	});
	//初始化工序数据
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessDataId.action",
        data:{
			'processData.id': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var processData=data.data;
				var $maoliaoType=$("select[name='processData.maoliaoType']");
				var $paihao=$("input[name='processData.paihao']");
				var $houdu=$("input[name='processData.houdu']");
				var $fenlei=$("input[name='processData.fenlei']");
				var $jishuTiaojian=$("input[name='processData.jishuTiaojian']");
				var $qieliaoShebei=$("input[name='processData.qieliaoShebei']");
				var maoliaoType=processData.maoliaoType;
				var paihao=processData.paihao;
				var houdu=processData.houdu;
				var fenlei=processData.fenlei;
				var jishuTiaojian=processData.jishuTiaojian;
				var qieliaoShebei=processData.qieliaoShebei;
				$maoliaoType.find("option[value='"+maoliaoType+"']").attr("selected",true);
				$paihao.val(paihao);
				$houdu.val(houdu);
				$fenlei.val(fenlei);
				$jishuTiaojian.val(jishuTiaojian);
				$qieliaoShebei.val(qieliaoShebei);
			}
		}
	});
	//初始化毛料参数
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getMaoliaoParamListByProcessDataId.action",
        data:{
			'maoliaoParam.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				maoliaoParamList=data.data;
				var $idArr=$("#maoliaoParam input[name='maoliaoParam.id']");
				var $numbArr=$("#maoliaoParam input[name='maoliaoParam.numb']");
				var $contentArr=$("#maoliaoParam input[name='maoliaoParam.content']");
				var $danweiArr=$("#maoliaoParam input[name='maoliaoParam.danwei']");
				var $shuzhiArr=$("#maoliaoParam input[name='maoliaoParam.shuzhi']");
				var $remarkArr=$("#maoliaoParam input[name='maoliaoParam.remark']");
				$(maoliaoParamList).each(function(i,m){
					$idArr.eq(i).val(this.id);
					$numbArr.eq(i).val(this.numb);
					$contentArr.eq(i).val(this.content);
					$danweiArr.eq(i).val(this.danwei);
					$shuzhiArr.eq(i).val(this.shuzhi);
					$remarkArr.eq(i).val(this.remark);
				});
			}
		}
	});
	//初始化毛料技术条件
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getMaoliaoJishuTiaojianListByProcessDataId.action",
        data:{
			'maoliaoJishuTiaojian.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				maoliaoJishuTiaojianList=data.data;
				var $idArr=$("#maoliaoJishuTiaojian input[name='maoliaoJishuTiaojian.id']");
				var $contentArr=$("#maoliaoJishuTiaojian input[name='maoliaoJishuTiaojian.content']");
				$(maoliaoJishuTiaojianList).each(function(i,m){
					$idArr.eq(i).val(this.id);
					$contentArr.eq(i).val(this.content);
				});
			}
		}
	});
	//添加工序数据Data jQuery数组取第一个值
	function getProcessDataParams(){
		var $maoliaoType=$("select[name='processData.maoliaoType']");
		var $paihao=$("input[name='processData.paihao']");
		var $houdu=$("input[name='processData.houdu']");
		var $fenlei=$("input[name='processData.fenlei']");
		var $jishuTiaojian=$("input[name='processData.jishuTiaojian']");
		var $qieliaoShebei=$("input[name='processData.qieliaoShebei']");
		var processData={
			'id': processDataId,
			'maoliaoType': $maoliaoType.val(),
			'paihao': $paihao.val(),
			'houdu': $houdu.val(),
			'fenlei': $fenlei.val(),
			'jishuTiaojian': $jishuTiaojian.val(),
			'qieliaoShebei': $qieliaoShebei.val()
		};
		return JSON.stringify(processData);
	}
	//添加毛料参数
	function getMaoliaoParamParams(){
		var $idArr=$("#maoliaoParam input[name='maoliaoParam.id']");
		var $numbArr=$("#maoliaoParam input[name='maoliaoParam.numb']");
		var $contentArr=$("#maoliaoParam input[name='maoliaoParam.content']");
		var $danweiArr=$("#maoliaoParam input[name='maoliaoParam.danwei']");
		var $shuzhiArr=$("#maoliaoParam input[name='maoliaoParam.shuzhi']");
		var $remarkArr=$("#maoliaoParam input[name='maoliaoParam.remark']");
		var maoliaoParamArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			maoliaoParamArr[i]={	
							'id': $idArr.eq(i).val(),
							'numb':$numbArr.eq(i).val(),
							'content':$contentArr.eq(i).val(),
							'danwei':$danweiArr.eq(i).val(),
							'shuzhi':$shuzhiArr.eq(i).val(),
							'remark':$remarkArr.eq(i).val(),
							'processDataId':processDataId
						  };
		}
		return JSON.stringify(maoliaoParamArr);
	}
	//添加毛料技术条件
	function getMaoliaoJishuTiaojianParams(){
		var $idArr=$("#maoliaoJishuTiaojian input[name='maoliaoJishuTiaojian.id']");
		var $contentArr=$("#maoliaoJishuTiaojian input[name='maoliaoJishuTiaojian.content']");
		var maoliaoJishuTiaojianArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			maoliaoJishuTiaojianArr[i]={	
							'id': $idArr.eq(i).val(),
							'content':$contentArr.eq(i).val(),
							'processDataId':processDataId
						  };
		}
		return JSON.stringify(maoliaoJishuTiaojianArr);
	}
	//*************************************以上获取参数******************************************************
	//*************************************下边初始化表格******************************************************
	
	
	//********************************保存页面数据******************************************************
	$("#saveButton").bind("click",function(){
		var processDataParams=getProcessDataParams();
		var maoliaoParamParams=getMaoliaoParamParams();
		var maoliaoJishuTiaojianParams=getMaoliaoJishuTiaojianParams();
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!updateGongyiGuiChengGxsmlqMaoliaoPage.action",
	        data:{
				'processData.params': processDataParams,
				'maoliaoParam.params': maoliaoParamParams,
				'maoliaoJishuTiaojian.Params': maoliaoJishuTiaojianParams
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
	//********************************保存页面数据******************************************************
	//*************************************打分******************************************************
	//$("form table:visible").css('display','none');
	$.ajax({
		type: "get",
		dataType: "json",
        url: "gongyiGuichengScoreAction!getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId.action",
        data:{
			'gongyiGuichengScore.gongyiGuichengId': $("#gygcId").val(),
			'gongyiGuichengScore.model': "gxtblmfqmx",
			'gongyiGuichengScore.processDataId': $('#processDataId').val()
		},
		async: true,
		success: function(data){
			var success=data.success;
			if(success){
				var gongyiGuichengScore=data.data;
				$("input[name='gongyiGuichengScore.contentScoreGxtblmfqmx'][value="+gongyiGuichengScore.contentScoreGxtblmfqmx+"]").attr("checked",true);
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
//*************************末尾***************************
});
</script>
