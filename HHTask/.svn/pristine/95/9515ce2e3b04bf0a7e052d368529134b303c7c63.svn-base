<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.7.2.min.js"></script>
		<%@include file="/util/sonHead.jsp"%>
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
		<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"> </script>
		<script type="text/javascript">
			console.log("$jq=  "+$().jquery);//3.2.1
			var $jq = jQuery.noConflict(true); 
			console.log("$jquery=  "+$().jquery); //1.7.2
			var $jquery = jQuery.noConflict(true);
			console.log("$=  "+$().jquery);//1.8.3
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/calendar/js/jquery-ui-1.8.6.custom.min.js"></script>
		<style type="text/css">
			#showShiChang{
				color: red;
			}
			.inputInfo{
				width: 100%;
			}
			#overtimeNeirong,.tinyselect{
				width: 100%;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div class="container">
			<s:if test="successMessage!=null">
				<div align="center">
					<h5>
						<font color="red">${successMessage}</font>
					</h5>
				</div>
			</s:if>
			<s:if test="errorMessage!=null">
				<div align="center">
					<h5>
						<font color="red">${errorMessage}</font>
					</h5>
				</div>
			</s:if>
			<form action="${pageContext.request.contextPath }/overtimeAction!addOvertime.action" method="post"  onsubmit="return checkType();" role="form">
				<div class="row">
					<div class="col-xs-12 text-center"><h2>加班申请</h2></div>
				</div>
				
				<s:if test="tag!=null&&tag=='overList'">
					<!-- 代理加班 -->
					<input id="applyId" type="hidden" name="overtime.applyId"
						value="${sessionScope.Users.id}" />
					<input type="hidden" name="tag" value="${tag }">
					<div class="row">
						<div class="col-xs-6">
							<div class="form-group">
								<label for="name">加班人部门</label>
								<select id="overtimeDept" name="overtimeDept" class="selectpicker show-tick form-control"
											 multiple  onchange="changeDept(this)" onclick="showShaiXuan(this)">
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<div class="form-group">
								<label for="name">加班人</label>
								<div id="usertable0">
									
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<!-- 个人申请加班 -->
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<div class="form-group input-group">
								<span class="input-group-addon">加班人部门</span>
								<input type="text" class="form-control" id="overtimeDept" type="text"
											name="overtime.overtimeDept" value="${sessionScope.Users.dept}"
											readonly="readonly">
								<input type="hidden" name="overtime.applyId" value="${sessionScope.Users.id }">
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="form-group input-group">
								<span class="input-group-addon">加班人姓名</span>
								<input class="form-control" id="overtimeName" type="text"
										name="overtime.overtimeName" value="${sessionScope.Users.name}"
										readonly="readonly">
							</div>
						</div>
					</div>
					
				</s:else>
				<div class="row">
					<label for="name" class="col-xs-12">加班信息 <a href="#" onclick="addOverTimeDetail()">添加</a></label><br>
					<div class="table-responsive col-xs-12" >
						<table class="table">
							<thead>
								<tr>
									<th class="col-xs-2">加班类型</th>
									<th class="col-xs-2">开始时间</th>
									<th class="col-xs-2">结束时间</th>
									<th class="col-xs-2">中途休息时间(分钟)</th>
									<th class="col-xs-2">加班小时-分钟</th>
									<th class="col-xs-2">操作</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4" class="text-center"></td>
									<td><span id="showShiChang"></span></td>
									<td></td>
								</tr>
							</tfoot>
						</table>
					</div>
<!-- 					<div class="col-xs-12 col-md-6"> -->
<!-- 						<div class="form-group input-group"> -->
<%-- 							<span class="input-group-addon">加班开始时间</span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-xs-12 col-md-6"> -->
<!-- 						<div class="form-group input-group"> -->
<%-- 							<span class="input-group-addon">加班结束时间</span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
<!-- 				<div class="row"> -->
<!-- 					<div class="col-xs-12 col-md-8"> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="name">中途休息时长(分钟)</label><br> -->
<!-- 							<input class="form-control" style="display: inline; width: 70%;" id="xiuxi" type="text" onchange="ischaoshi()" /> -->
<!-- 							加班总时长:<font id="" style=""></font> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group tabs">
							<label for="name">加班原因</label>
							<br>
							<label class="radio-inline">
								<input id="overtimeType1"  type="radio" name="overtime.overtimeType"
										value="生产" checked="checked" onchange="switchCause(this);" />生产
							</label>
							<label class="radio-inline">
								<input id="overtimeType2" type="radio" name="overtime.overtimeType"
									 value="项目" onchange="switchCause(this);" />项目
							</label>
							<label class="radio-inline">
								<input id="overtimeType3" type="radio" name="overtime.overtimeType"
									 value="KVP" onchange="switchCause(this);" /> 持续改进(KVP)
							</label>
							<label class="radio-inline">
								<input id="overtimeType3" type="radio" name="overtime.overtimeType" 
									value="其它" onchange="switchCause(this);" />其它
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="name">加班内容</label>
							<br>
							<div class="content row col-xs-12">
								<select name="overtime.overtimeNeirong" id="overtimeNeirong">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="name">是否换休</label>
							<br>
							<label class="radio-inline">
								<input type="radio" name="overtime.huanxiu" value="是"/>是
							</label>
							<label class="radio-inline">
								<input type="radio" name="overtime.huanxiu" value="否" checked="checked"/>否
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
					
						<div class="form-group">
							<label for="">
								加班说明
							</label>
							<textarea class="form-control" rows="3" id="shuoming" name="overtime.shuoming"></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<input type="hidden" name="overtime.overTimeLong" id="overTimeLong" >
						<input type="hidden" name="overtime.overTimeMinutes" id="overTimeMinutes">
						<input  id="joined1" type="hidden" name="overtime.startDate"  />
						<input  id="joined2"  type="hidden" name="overtime.endDate"  />
						<button class="btn btn-default" id="submitBtn" type="submit">提交</button>
					</div>
				</div>
			</form>
			
			<!-- 弹出框 -->
			<div id="showDialogs" style="overflow: hidden;">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="name">加班类型</label>
							<select class="form-control" id="category">
								<option>日常加班</option>
								<option>公休日加班</option>
								<option>法定节假日加班</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label>加班开始时间</label>
							<input class="form-control Wdate" id="start"type="text" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',skin:'whyGreen'})" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label>加班结束时间</label>
							<input class="form-control Wdate" id="end"  type="text"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',skin:'whyGreen'})" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label>中途休息时间(分钟)</label>
							<input class="form-control" type="text" id="xiuxiDetail" />
						</div>
					</div>
				</div>
			</div>
			<!-- 弹出框over -->
			<!-- 拼接加班信息 -->
			<textarea id="overtimeInfo" style="display: none;">
				<tr id="tr_0">
					<td>
						<select name='overtimeDetailList[0].type' id="type_0" class="inputInfo form-control" >
							<option>日常加班</option>
							<option>公休日加班</option>
							<option>法定节假日加班</option>
						</select>
						<!-- <input type='text' name='overtimeDetailList[0].type' id="type_0" class="inputInfo form-control" readonly="readonly"> -->
					</td>
					<td><input type="text" name="overtimeDetailList[0].startTime" id="start_0" class="inputInfo form-control"
							 onblur="computeDate('_0')" onfocus="recordDate('_0')"></td>
					<td><input type="text" name="overtimeDetailList[0].endTime" id="end_0" class="inputInfo form-control" 
							onblur="computeDate('_0')" onfocus="recordDate('_0')"></td>
					<td><input type="text" name="overtimeDetailList[0].xiuxi" id="xiuxi_0" class="inputInfo form-control" 
							onblur="computeDate('_0')" onfocus="recordDate('_0')"></td>
					<td>
						<input type="text" name="overtimeDetailList[0].hour" id="hour_0" readonly="readonly" 
									class="inputInfo form-control" style="display: inline;width: 50px;">小时
						<input type="text" name="overtimeDetailList[0].minutes" id="minutes_0" readonly="readonly" 
									class="inputInfo form-control" style="display: inline;width: 50px;">分钟
					</td>
        			<td><button class='btn btn-default' onclick='delDetail(this,"_0")' id="del_0">删除</button></td>
        		</tr>
			</textarea>
			<!-- 拼接加班信息 over-->
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">

var size = 0;//信息条数
var index = 1;//信息下标
var xia = 1;

$jq(function() {
	$("#showDialogs").hide();
	getProduction();//加载生产件号和批次
	//加载部门
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "GzstoreAction_getdept.action",
		async : false, 
		success : function(data) {
			var value= [];
			$(data).each(function() {
				var html  = "<option value='" + this.id + "'>"
							+ this.dept + "</option>";
				value.push(this.id);
				$(html).appendTo("#overtimeDept");
			});
			$jq('#overtimeDept').selectpicker({noneSelectedText:'请选择部门'});
		}
	});

	var clickEvents = false;
	$("button[data-id='overtimeDept']").toggle(function(){
		if(!clickEvents){
			$(".dropdown-menu").show();
			clickEvents=true;
		}else{
			$(".dropdown-menu").hide();
			clickEvents = false;
		}
	},function(){
		if(!clickEvents){
			$(".dropdown-menu").show();
			clickEvents=true;
		}else{
			$(".dropdown-menu").hide();
			clickEvents = false;
		}
	});
	$(".dropdown-menu").mouseleave(function(){
		$(".dropdown-menu").hide();
		clickEvents = false;
	});
});


function changeDept(obj){
	var deptIds = $(obj).val();
	if(deptIds==null){
		return ;
	}
	var brId = 0;
	$("#usertable0").empty();//清空
	$("#usertable0").append("<p><input type='checkbox' onchange='isQuanXuan(this)' checked='checked' >全选</p>");
	for(var i=0; i<deptIds.length;i++){
		var id = deptIds[i];
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getusers.action",
			dataType : "json",
			data : {
				"id" : id
			},
			async : false, 
			success : function(data) {
				//填充部门信息
				$(data).each(function() {
					brId++;
					if(brId%10==0){
						$("<br/>").appendTo("#usertable0");
					}
					$("<label class='checkbox-inline'><input type='checkbox' checked='checked' name='usersId' "
						+ "value='" + this.id + "' /> " + this.name+"</label>").appendTo(
						"#usertable0");
				});
			}
		});
	}
}

function isQuanXuan(obj){
	$("label input[type='checkbox']").each(function(){
		if(obj.checked){
			$(this).attr("checked","checked");
		}else{
			$(this).removeAttr("checked");
		}
		
	});
}

function gongchu1(val) {
	document.getElementById("xiangmu").value = "";
	document.getElementById("kvp").value = "";
	document.getElementById("markId").value = "";
	//项目
	if (val == "项目") {
		$("#t1").css("display", "none");
		$("#t3").css("display", "none");
		$("#t2").show();
		$("#markId").attr("disabled", "disabled");
		$("#kvp").attr("disabled", "disabled");
		$("#xiangmu").removeAttr("disabled");
		//tr_modifing.style.display = "table−row";
		//显示项目信息
		//-----------------------------------------------------------
		//-------------------------------------KVP------------------------------------
	} else if (val == "KVP") {
		$("#t1").css("display", "none");
		$("#t2").css("display", "none");
		$("#t3").show();
		$("#xiangmu").attr("disabled", "disabled");
		$("#markId").attr("disabled", "disabled");
		$("#kvp").removeAttr("disabled");

	} else {
		$("#t3").css("display", "none");
		$("#t2").css("display", "none");
		$("#t1").show();
		$("#xiangmu").attr("disabled", "disabled");
		$("#kvp").attr("disabled", "disabled");
		$("#markId").removeAttr("disabled");
		//------------------------------------
		//---------------------------------------

	}
}
function checkType() {
	var joined1 = null;//总的开始时间
	var joined2 = null;//总的结束时间
	
	//校验加班开始时间和结束时间
	var flag = false;
	for(var i=1;i<=totalIndex;i++){
		var startTime = $("#start_"+i).val();
		var endTime = $("#end_"+i).val();
		if(startTime!=null && endTime!=null){
			if(joined1==null){
				joined1=startTime;
			}
			joined2 = endTime;
			flag = true;
		}
	}
	$("#joined1").val(joined1);
	$("#joined2").val(joined2);
	if(!flag){
		alert("请填写加班记录");
		return false;
	}
	
	var shuoming = $("#shuoming").val();
	if (shuoming == null || shuoming == "") {
		alert("加班说明不能为空");
		return false;
	}
	/*var xiangmu = document.getElementById('xiangmu').value;
	var kvp = document.getElementById('kvp').value;
	var markId = document.getElementById('markId').value;
	var value = "";
	var obj2 = document.getElementsByName('overtime.overtimeType');
	for ( var i = 0; i < obj2.length; i++) {
		if (obj2[i].checked == true) {
			value = obj2[i].value;
			break;
		}
	}

	if (value == "项目") {
		if (xiangmu == "") {
			alert("项目不能为空");
			return false;
		}
		var amount = document.getElementById('amount').value;
		if (amount == "") {
			alert("加工件号不能为空！");
			return false;
		}
	}

	if (value == "kvp") {
		if (kvp == "") {
			alert("kVP不能为空");
			return false;
		}
	}
	if (value == "生产") {
		if (markId == "") {
			alert("加工件号不能为空");
			return false;
		}
	}*/
	$("#submitBtn").attr("disabled","disabled");
	return true;
}

//判断超时
function ischaoshi() {
	
	//计算总时长
	var joined1 = $("#joined1").val();
	var joined2 = $("#joined2").val();
	var xiuxi = $("#xiuxi").val();
	
	var overtimeId = $("#overtimeId").val();  //加班人ID
	if (joined1 != "" && joined2 != "" && overtimeId !="") {
		$.ajax( {
			type : "POST",
			url : "overtimeAction!isbandcisc.action",
			data : { 
				id :overtimeId,
				startDate:joined1,
				endDate:joined2,
				xiuxi:xiuxi
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					if(data != "true"){
						if(data.indexOf("加班开始时间")!=-1){
							 $("#joined1").val("");
						}else if(data.indexOf("加班结束时间")!=-1){
							 $("#joined2").val("");
						}else{
							 $("#joined2").val("");
						}
							alert(data);
					}
				}
			}
		})
	}

}

var totalFenzhong = 0;
var totalIndex =0;
//点击添加加班明细
function addOverTimeDetail(){
	$("#showDialogs").dialog({
        buttons:{
        	"确定":function(){
        		
        		var overtimeInfo = $("#overtimeInfo").val();
        		totalIndex++;
        		while(overtimeInfo.indexOf("_0")>0 || overtimeInfo.indexOf("[0]")>0){
        			overtimeInfo = overtimeInfo.replace("_0","_"+totalIndex);
        			overtimeInfo = overtimeInfo.replace("[0]","["+totalIndex+"]");
        		}
        		var start = $("#start").val();
        		var end = $("#end").val();
        		var category = $("#category").val();
        		var xiuxi = $("#xiuxiDetail").val();
        		if(start==""){
        			alert("请选择开始时间");
        			return false;
        		}
        		if(end==""){
        			alert("请选择结束时间");
        			return false;
        		}
        		if(xiuxi==null || xiuxi==""){
        			xiuxi=0;
        		}
        		if(xiuxi<0){
        			alert("休息时间不能小于0");
        			return false;
        		}
        		
        		var startDate = new Date(start);
        		var endDate = new Date(end);
        		var stime = Date.parse(startDate);
        		var etime = Date.parse(endDate);
        		if(stime>=etime){
        			alert("开始时间不能大于结束时间");
        			return false;
        		}
        		
        		//检查时候有冲突记录
        		for(var i=0;i<totalIndex;i++){
        			var dstime = $("#start_"+i).val();
        			var detime = $("#end_"+i).val();
        			if(dstime!=null && detime!=null){
        				var s = Date.parse(new Date(dstime));
                		var e = Date.parse(new Date(detime));
                		if((stime>=s && stime<=e) || (etime<=e && etime>=s)){
                			alert("开始时间和结束时间和列表中的时间冲突");
                			return false;
                		}
        			}
        		}
        		
        		//天数拆分
        		while(!judgeDays(startDate,endDate)){
        			
        			//获取开始时间（当天的最后一秒）
        			var currentEndDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),'23','59','00');
        			var currentEndTime = Date.parse(currentEndDate);
        			var currentStartTime = Date.parse(startDate);
        			var fenzhong = Math.floor((currentEndTime-currentStartTime)/(60*1000))+1; //相差的分钟数-休息时间（分钟）
            		var hour = Math.floor(fenzhong/60);//小时
            		var minute = Math.floor(fenzhong%60);//分钟
            		if(hour>=8 && minute>0){
            			alert("单条申请不能超过8小时");
            			return false;
            		}
            		$("tbody").append(overtimeInfo);
            		
            		$("#type_"+totalIndex).val(category);
            		$("#start_"+totalIndex).val(formatTime(startDate));
            		var yeartemp = startDate.getFullYear();
            		var monthtemp = startDate.getMonth()+1, month = month < 10 ? '0' + month : month;
            		var daytemp = startDate.getDate(), day =day < 10 ? '0' + day : day;
            		var datetemp = yeartemp + '-' + monthtemp + '-' + daytemp+" 23:59:00";
            		
            		$("#end_"+totalIndex).val(datetemp);
            		$("#xiuxi_"+totalIndex).val(0);
            		$("#hour_"+totalIndex).val(hour);
            		$("#minutes_"+totalIndex).val(minute);
            		
            		totalFenzhong+=fenzhong;
            		var totalHour = Math.floor(totalFenzhong/60);//小时
            		var totalMinute = Math.floor(totalFenzhong%60);//分钟
            		$("#overTimeLong").val(totalHour);
            		$("#overTimeMinutes").val(totalMinute);
            		$("#showShiChang").text("总时长："+totalHour+"小时，"+totalMinute+"分钟");
        			startDate= new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate()+1,'00','00','00');

        			totalIndex++;
        			overtimeInfo = $("#overtimeInfo").val();
        			while(overtimeInfo.indexOf("_0")>0 || overtimeInfo.indexOf("[0]")>0){
            			overtimeInfo = overtimeInfo.replace("_0","_"+totalIndex);
            			overtimeInfo = overtimeInfo.replace("[0]","["+totalIndex+"]");
            		}
        		}
        		stime = Date.parse(startDate);
        		var fenzhong = Math.floor((etime-stime)/(60*1000))-xiuxi; //相差的分钟数-休息时间（分。钟）
        		var hour = Math.floor(fenzhong/60);//小时
        		var minute = Math.floor(fenzhong%60);//分钟
        		if(hour>8 || (hour>=8 && minute>0)){
        			alert("单条申请不能超过8小时");
        			return false;
        		}
        		if(fenzhong<0){
        			alert("休息时间大于开始时间-结束时间");
        			regainDate(_index);
        			return false;
        		}
        		totalFenzhong+=fenzhong;
        		var totalHour = Math.floor(totalFenzhong/60);//小时
        		var totalMinute = Math.floor(totalFenzhong%60);//分钟
        		
        		
        		$("#overTimeLong").val(totalHour);
        		$("#overTimeMinutes").val(totalMinute);
        		$("#showShiChang").text("总时长："+totalHour+"小时，"+totalMinute+"分钟");
        		
        		$("tbody").append(overtimeInfo);
        		$("#type_"+totalIndex).val(category);
        		$("#start_"+totalIndex).val(formatTime(startDate));
        		$("#end_"+totalIndex).val(end);
        		$("#xiuxi_"+totalIndex).val(xiuxi);
        		$("#hour_"+totalIndex).val(hour);
        		$("#minutes_"+totalIndex).val(minute);
        		
        		
        		
        		$(this).dialog('close');
        	},
        	"取消":function(){$(this).dialog('close');}
        },
        title:"加班明细",
        modal: true,// 创建模式对话框
        height: "auto",
        position: { my: "center top", at: "center top", of: window }
    });
}

//判断两个日期是否是同一天
function judgeDays(startDate,endDate){
	var syear = startDate.getFullYear();
	var eyear = endDate.getFullYear();
	
	var smonth = startDate.getMonth();
	var emonth = endDate.getMonth();
	
	var sdate = startDate.getDate();
	var edate = endDate.getDate();
	
	if(syear==eyear && smonth==emonth && sdate==edate){
		return true;
	}else{
		return false;//不是同一天
	}
}

function delDetail(obj,_index){
	var _hour = $("#hour"+_index).val();
	var _minutes = $("#minutes"+_index).val();
	var totalMinutes = parseInt(_minutes)+(60*_hour);
	$(obj).parent().parent().remove();
	totalFenzhong-=totalMinutes;
	
	var hour = Math.floor(totalFenzhong/60);//小时
	var minute = Math.floor(totalFenzhong%60);//分钟
	var xiuxiStr = hour+"小时，"+minute+"分钟";
	$("#overTimeLong").val(hour);
	$("#overTimeMinutes").val(minute);
	$("#showShiChang").text("总时长："+hour+"小时，"+minute+"分钟");
}

function formatTime(date) {
  var year = date.getFullYear();
  var month = date.getMonth()+1, month = month < 10 ? '0' + month : month;
  var day = date.getDate(), day =day < 10 ? '0' + day : day;
  var hour = date.getHours(), hour = hour<10 ? '0'+ hour: hour;
  var minutes = date.getMinutes(),minutes=minutes<10 ? '0'+minutes:minutes;
  return year + '-' + month + '-' + day+" "+hour+":"+minutes+":00";
}

//记录开始时间、结束时间和休息时间（用户修改校验失败后回滚[恢复]操作）
var recordStart;
var recordEnd;
var recordXiuxi;
function recordDate(_index){
	recordStart = $("#start"+_index).val();
	recordEnd = $("#end"+_index).val();
	recordXiuxi = $("#xiuxi"+_index).val();
}
//恢复数据
function regainDate(_index){
	$("#start"+_index).val(recordStart);
	$("#end"+_index).val(recordEnd);
	$("#xiuxi"+_index).val(recordXiuxi);
}

//计算天数
function computeDate(_index){
	
	
	var start = $("#start"+_index).val();
	var end = $("#end"+_index).val();
	var xiuxi = $("#xiuxi"+_index).val();
	if(start==""){
		alert("请选择开始时间");
		regainDate(_index);
		return false;
	}
	if(end==""){
		alert("请选择结束时间");
		regainDate(_index);
		return false;
	}
	if(recordStart==start && recordEnd==end && recordXiuxi==xiuxi){
		return false ;//如果没有发生改变，不用执行下面的计算方法
	}
	if(xiuxi==null || xiuxi==""){
		xiuxi=0;
	}
	if(xiuxi<0){
		alert("休息时间不能小于0");
		return false;
	}
	var startDate = new Date(start);
	var endDate = new Date(end);
	var stime = Date.parse(startDate);
	var etime = Date.parse(endDate);
	if(stime>=etime){
		alert("开始时间不能大于结束时间");
		regainDate(_index);
		return false;
	}
	
	//检查时候有冲突记录
	for(var i=0;i<totalIndex;i++){
		if("_"+i==_index){
			continue;
		}
		var dstime = $("#start_"+i).val();
		var detime = $("#end_"+i).val();
		if(dstime!=null && detime!=null){
			var s = Date.parse(new Date(dstime));
    		var e = Date.parse(new Date(detime));
    		if((stime>=s && stime<=e) || (etime<=e && etime>=s)){
    			alert("开始时间和结束时间和列表中的时间冲突");
    			regainDate(_index);
    			return false;
    		}
		}
	}
	var overtimeInfo = $("#overtimeInfo").val();
	totalIndex++;
	while(overtimeInfo.indexOf("_0")>0 || overtimeInfo.indexOf("[0]")>0){
		overtimeInfo = overtimeInfo.replace("_0","_"+totalIndex);
		overtimeInfo = overtimeInfo.replace("[0]","["+totalIndex+"]");
	}
	
	//天数拆分
	while(!judgeDays(startDate,endDate)){
		
		//获取开始时间（当天的最后一秒）
		var currentEndDate = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate(),'23','59','00');
		var currentEndTime = Date.parse(currentEndDate);
		var currentStartTime = Date.parse(startDate);
		var fenzhong = Math.floor((currentEndTime-currentStartTime)/(60*1000))+1; //相差的分钟数-休息时间（分钟）
		var hour = Math.floor(fenzhong/60);//小时
		var minute = Math.floor(fenzhong%60);//分钟
		if(hour>=8 && minute>0){
			alert("单条申请不能超过8小时");
			regainDate(_index);
			return false;
		}
		$("tbody").append(overtimeInfo);
		//delDetail(null,_index);
		//$("#tr"+_index).remove();
		$("#type_"+totalIndex).val(category);
		$("#start_"+totalIndex).val(formatTime(startDate));
		var yeartemp = startDate.getFullYear();
		var monthtemp = startDate.getMonth()+1, month = month < 10 ? '0' + month : month;
		var daytemp = startDate.getDate(), day =day < 10 ? '0' + day : day;
		var datetemp = yeartemp + '-' + monthtemp + '-' + daytemp+" 23:59:00";
		
		$("#end_"+totalIndex).val(datetemp);
		$("#xiuxi_"+totalIndex).val(0);
		$("#hour_"+totalIndex).val(hour);
		$("#minutes_"+totalIndex).val(minute);
		
		totalFenzhong+=fenzhong;
		var totalHour = Math.floor(totalFenzhong/60);//小时
		var totalMinute = Math.floor(totalFenzhong%60);//分钟
		$("#overTimeLong").val(totalHour);
		$("#overTimeMinutes").val(totalMinute);
		$("#showShiChang").text("总时长："+totalHour+"小时，"+totalMinute+"分钟");
		startDate= new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate()+1,'00','00','00');

		totalIndex++;
		overtimeInfo = $("#overtimeInfo").val();
		while(overtimeInfo.indexOf("_0")>0 || overtimeInfo.indexOf("[0]")>0){
			overtimeInfo = overtimeInfo.replace("_0","_"+totalIndex);
			overtimeInfo = overtimeInfo.replace("[0]","["+totalIndex+"]");
		}
	}
	stime = Date.parse(startDate);
	var fenzhong = Math.floor((etime-stime)/(60*1000))-xiuxi; //相差的分钟数-休息时间（分。钟）
	var hour = Math.floor(fenzhong/60);//小时
	var minute = Math.floor(fenzhong%60);//分钟
	if(hour>8 || (hour>=8 && minute>0)){
		alert("单条申请不能超过8小时");
		regainDate(_index);
		return false;
	}
	if(fenzhong<0){
		alert("休息时间大于开始时间-结束时间");
		regainDate(_index);
		return false;
	}
	totalFenzhong+=fenzhong;
	var totalHour = Math.floor(totalFenzhong/60);//小时
	var totalMinute = Math.floor(totalFenzhong%60);//分钟
	
// 	if(totalHour>8 || (totalHour>=8 && totalMinute>0)){
// 		alert("总时长不能超过8小时");
// 		regainDate(_index);
// 		return false;
// 	}
	
	$("#overTimeLong").val(totalHour);
	$("#overTimeMinutes").val(totalMinute);
	$("#showShiChang").text("总时长："+totalHour+"小时，"+totalMinute+"分钟");
	
	$("tbody").append(overtimeInfo);
	//$("#tr"+_index).remove();
	delDetail($("#del"+_index),_index);
	$("#type_"+totalIndex).val(category);
	$("#start_"+totalIndex).val(formatTime(startDate));
	$("#end_"+totalIndex).val(end);
	$("#xiuxi_"+totalIndex).val(xiuxi);
	$("#hour_"+totalIndex).val(hour);
	$("#minutes_"+totalIndex).val(minute);
	
	
}


//获得生产的件号和批次
function getProduction() {
	$jq.ajax( {
		type : "POST",
		url : "overtimeAction!finAllMarkIdForSetlectAll.action",
		dataType : "json",
		data:{
			"tag":"fenye",
			"pageSize":30
		},
		success : function(object) {
			$("#overtimeNeirong").append("<option><option>");
			$jq.each(object, function(i, obj) {
				$jq("#overtimeNeirong").append("<option value='" + obj[0] + "/" + obj[1] + "'>" + "件号"
						+ obj[0] + "   批次" + obj[1] + "</option>");
			});
			$jq("#overtimeNeirong").append("<option>没有找到正确的？点击这里输入。</option>");
			$jquery("#overtimeNeirong").tinyselect();
		}
	});
}

//获得项目信息
function getProject(){
	$.ajax( {
		type : "POST",
		url : "proAction!listPro.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			$("#overtimeNeirong").append("<option><option>");
			$.each(object, function(i, obj) {
				$("#overtimeNeirong").append("<option>" + obj.code + "   "
						+ obj.name + "</option>");
			});
			$jq("#overtimeNeirong").append("<option>没有找到正确的？点击这里输入。</option>");
			$jquery("#overtimeNeirong").tinyselect();
		}
	});
}

//获取KVP
function getKVP(){
	var overtimeName = document.getElementById('overtimeName').value;
	$.ajax( {
		type : "POST",
		url : "proAction!listKVP.action",
		data : {
			overName : overtimeName
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			$("#overtimeNeirong").append("<option><option>");
			$.each(object, function(i, obj) {
				$("#overtimeNeirong").append("<option value='" + obj[0] + "'>" + obj[0] + "   "
						+ obj[1] + "</option>");
			});
			$jq("#overtimeNeirong").append("<option>没有找到正确的？点击这里输入。</option>");
			$jquery("#overtimeNeirong").tinyselect();
		},
		error : function() {
			alert("KVP加载失败！");
		}

	});
}


//让下拉框有滚动条
$(document).on('click','.tinyselect .selectbox',function(){
	$(".dropdown").removeAttr("style");//为解决这个要气死了🤬
});
//设置找不到使用自己输入的
$(document).on('click','.item,.selected',function(){
	if($(this).text().indexOf("点击这里输入。")>0){
		switchCause($("#overtimeType3"));
	}
});


function switchCause(obj){
	//清除下拉框内容
	$(".content").empty();
	$(".content").append('<select name="overtime.overtimeNeirong" id="overtimeNeirong"></select>')
	var cases = obj.value;
	
	if(cases=="生产"){
		getProduction();//获得生产的件号和批次
	}else if(cases=="项目"){
		getProject();//获得项目信息
	}else if(cases=="KVP"){
		getKVP();//获得KVP
	}else{//其他
		$(".content").empty();
		$(".content").append('<textarea class="form-control" rows="3" id="overtimeNeirong" name="overtime.overtimeNeirong"></textarea><br>');
	}
}

</script>
