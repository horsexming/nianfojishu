<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<SCRIPT type="text/javascript">
	var gantt_pageNum = "${param.urlPageNum}";


	var gantt_addFlag ="${param.errorMessage}";
	var index=gantt_addFlag.indexOf("add");
    var gantt_add;
	if(index>=0){
		gantt_add=true;
	}
	
	</SCRIPT>
<%@include file="/util/sonHead.jsp"%>
<link rel="stylesheet" href="css/style1.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/prettify.css" />
<style type="text/css">
body {
	font-family: Helvetica, Arial, sans-serif;
	font-size: 13px;
	padding: 0 0 50px 0;
}

.contain {
	width: 98%;
	margin: 0 auto;
}

h1 {
	margin: 40px 0 20px 0;
}

h2 {
	font-size: 1.5em;
	padding-bottom: 3px;
	border-bottom: 1px solid #DDD;
	margin-top: 50px;
	margin-bottom: 25px;
}

table th:first-child {
	width: 150px;
}

#dialog-overlay {
	width: 100%;
	height: 100%;
	filter: alpha(opacity =   50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	opacity: 0.5;
	position: absolute;
	background: #000;
	top: 0;
	left: 0;
	z-index: 3000;
	display: none;
}

#dialog-box {
	-webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	-moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	background: #eee;
	width: 600px;
	position: absolute;
	z-index: 5000;
	display: none;
    left:50%;
	top: 100px;
	margin-left:-300px;;
}

#dialog-box .dialog-content {
	text-align: left;
	padding: 10px;
	margin: 13px;
	color: #666;
	font-family: arial;
	font-size: 11px;
}

a.button {
	margin: 10px auto 0 auto;
	text-align: center;
	background-color: #e33100;
	display: inline-block;
	width: 50px;
	padding: 5px 10px 6px;
	color: #fff;
	text-decoration: none;
	font-weight: bold;
	line-height: 1;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
	border-bottom: 1px solid rgba(0, 0, 0, 0.25);
	position: relative;
	cursor: pointer;
}

#submitBtn,#updateBtn,#deleteBtn,#submitUpdBtn,#addInputText,.resetBtn {
	margin: 10px auto 0 auto;
	left: 100px;
	text-align: center;
	background-color: #e33100;
	display: inline-block;
	width: 50px;
	padding: 5px 10px 6px;
	color: #fff;
	text-decoration: none;
	font-weight: bold;
	line-height: 1;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25); <%--
	border-bottom: 1px solid rgba(0, 0, 0, 0.25); --%>
	position: relative;
	cursor: pointer;
}

#deleteBtn {
	margin: 10px auto 0 auto;
	left: 200px;
	text-align: center;
	background-color: #e33100;
	display: inline-block;
	width: 100px;
	padding: 5px 10px 6px;
	color: #fff;
	text-decoration: none;
	font-weight: bold;
	line-height: 1;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25); <%--
	border-bottom: 1px solid rgba(0, 0, 0, 0.25); --%>
	position: relative;
	cursor: pointer;
}

#dialog-box .dialog-content p {
	font-weight: 700;
	margin: 0;
}

#dialog-box .dialog-content ul {
	margin: 10px 0 10px 20px;
	padding: 0;
	height: 50px;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}

.duoxuaselect_div {
	width: 200px; inline-block;
	top: 30px;
}
.etabs { margin: 0; padding: 0; }
.tab { display: inline-block; zoom:1; *display:inline; background: #eee; border: solid 1px #999; border-bottom: none; -moz-border-radius: 4px 4px 0 0; -webkit-border-radius: 4px 4px 0 0; }
.tab a { font-size: 14px; line-height: 2em; display: block; padding: 0 10px; outline: none; }
.tab a:hover { text-decoration: underline; }
.tab.active { background: #fff; padding-top: 6px; position: relative; top: 1px; border-color: #666; }
.tab a.active { font-weight: bold; }
.tab-container .panel-container { background: #fff; border: solid #666 1px; padding: 10px; -moz-border-radius: 0 4px 4px 4px; -webkit-border-radius: 0 4px 4px 4px; }
.panel-container { margin-bottom: 10px; }
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;"align:center;>
			<div align="center">
				<div class="contain">
					<h3>
						项目进度甘特图
					</h3>
					<span style="color: red;" id="error"> <s:if
							test="#session.errorMessage=='addtrue'">
					添加成功
				</s:if> <s:elseif test="#session.errorMessage=='addfalse'">
					添加失败
				</s:elseif> <s:elseif test="#session.errorMessage=='deltrue'">
					删除成功
				</s:elseif> <s:elseif test="#session.errorMessage=='delfalse'">
					删除失败
				</s:elseif> <s:elseif test="#session.errorMessage=='updtrue'">
					修改成功
				</s:elseif> <s:elseif test="#session.errorMessage=='updfalse'">
					修改失败
				</s:elseif> </span>
					<!--显示图-->
					<div class="gantt" id="ganttChart"></div>
					<div>
<%--						<input type="button" onclick="add()" value="添加"--%>
<%--							class="input searchBtn" style="width: 80px; height: 50px; font-weight: bold;">--%>
					</div>
					<div id="dialog-overlay"></div>
					<div id="dialog-box">
						<div class="dialog-content">
							<div id="dialog-message">
								<a href="javascript:;" class="button" onclick="closeDia()"
									style="left: 505px; top: -32px;" >关闭</a>
								<input type="hidden" id="proId"/>
							</div>
							<div id="tab-container" class='tab-container'>
								<ul class='etabs'>
								  <li class='tab'><a href="#project-info" class="active">项目信息</a></li>
								  <li class='tab'><a href="#project-add">添加子项目</a></li>
								  <li class='tab'><a href="#project-update" onclick="showUpdatePage()">修改项目</a></li>
<%--								  <li class='tab'><a href="#project-delete">删除项目</a></li>--%>
<%--								  <li class='tab'><a href="#project-delete">审批项目成员</a></li>--%>
<%--								  <li class='tab'><a href="#project-delete">指派成员</a></li>--%>
<%--								  <li class='tab'><a href="#project-delete">项目成员</a></li>--%>
								</ul>
								<div class='panel-container'>
									<div id="project-info">
										<table class="table table-striped">
											<tr>
												<th align="center" colspan="2">
													项目信息
												</th>
											</tr>
											<tr>
												<th width="50%">
													<code id="ganttData-item-id">
														项目编号：
													</code>
													<br/>
													<input type="text" id="proNum" readonly="readonly"/>
												</th>
												<th width="50%">
													<code id="ganttData-item-id">
														项目名称:
													</code>
													<br/>
													<input type="text" id="proName" readonly="readonly"/>
												</th>
											</tr>
											<tr>
												<th>
													<code id="ganttData-item-id">
															负责人:
													</code>
													<br/>
													<input type="text" id="principals" readonly="readonly"/>
												</th>
												<th>
													<code id="ganttData-item-id">
															状态:
													</code>
													<br/>
													<input type="text" id="status" readonly="readonly"/>
												</th>
											</tr>
											
											<tr>
												<th>
													<code id="ganttData-item-id">
															开始时间:
													</code>
													<br/>
													<input type="text" id="startTime" readonly="readonly"/>
												</th>
												<th>
													<code id="ganttData-item-id">
															预完成时间:
													</code>
													<br/>
													<input type="text" id="reTime" readonly="readonly"/>
												</th>
											</tr>
											
											<tr>
												<th>
													<code id="ganttData-item-id">
															项目总分数:
													</code>
													<br/>
													<input type="text" id="sumStore" readonly="readonly"/>
												</th>
												<th>
													<code id="ganttData-item-id">
															本项目分数:
													</code>
													<br/>
													<input type="text" id="selfStore" readonly="readonly"/>
												</th>
											</tr>
											<tr>
												<th colspan="2">
													<code id="ganttData-item-id">
															描述:
													</code>
													<br/>
<%--													<input type="text" id="remark" readonly="readonly"/>--%>
													<textarea rows="3" cols="60" style="width:515px;" id="remark" readonly="readonly"></textarea>
												</th>
											</tr>
											<tr>
												<td colspan="2">
													<input type="button" value="删除项目" onclick="delProject()" id="deleteBtn"/>
												</td>
											</tr>
										</table>
									</div>
									<div id="project-add">
										<form id="addForm" action="projectPoolAction_saveAndUpdateYf.action?pageStatus=${pageStatus}" 
											   method="post" style="margin: 0px; padding: 0px;">
											<input type="hidden" id="yfrootId" name="projectManageyf.rootId" />
											<input type="hidden" id="yffatherId" name="projectManageyf.fatherId" />
											<input type="hidden" id="yfbelongLayer" name="projectManageyf.belongLayer" />
											<input type="hidden" id="yfpoolId" name="projectManageyf.poolId" />
											<table class="table table-striped">
												<tr>
													<th align="center" colspan="2">
														添加子项目
													</th>
												</tr>
												<tr>
													<th width="50%">
														<code id="ganttData-item-id">
															项目名称:
														</code>
														<br/>
														<input type="text" name="projectManageyf.proName" id="addProName"/>
													</th>
													<th width="50%">
														<code id="ganttData-item-id">
															项目分数:
														</code>
														<br/>
														<input type="text" name="projectManageyf.selfStore"/>
													</th>
												</tr>
												<tr>
													<th>
														<code id="ganttData-item-id">
															开始时间:
														</code>
														<br/>
														<input type="text" id="principals" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" name="projectManageyf.startTime" />
													</th>
													<th>
														<code id="ganttData-item-id">
															预完成时间:
														</code>
														<br/>
														<input type="text" name="projectManageyf.reTime" id="addReTime"
														  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
													</th>
												</tr>
												
												<tr>
													<th colspan="2">
														<code id="ganttData-item-id">
															描述
														</code>
														<br/>
														<textarea rows="3" cols="60" style="width:515px;" name="projectManageyf.remark"></textarea>
													</th>
												</tr>
												<tr>
													<td colspan="2">
														<input type="button" id="submitBtn"
															style="width: 100px;" value="添加" onclick="submitForm('addForm')">
														<input type="reset" class="resetBtn" style="width: 100px;margin-left:80px;" value="重置" >
													</td>
												</tr>
											</table>
										</form>
									</div>
									
									<div id="project-update">
										<form id="updateForm"  method="post" action="projectPoolAction_saveAndUpdateYf.action?pageStatus=${pageStatus}" >
											<input type="hidden" id="updateId" name="projectManageyf.id"/>
											<table class="table" style="width: 100%;">
												<tr>
													<th align="center" colspan="2">
														修改项目
													</th>
												</tr>
												<tr>
													<th width="50%">
														<code id="ganttData-item-id">
															项目名称:
														</code>
														<br/>
														<input type="text" name="projectManageyf.proName" id="updateProName"/>
													</th>
													<th width="50%">
														<code id="ganttData-item-id">
															项目分数:
														</code>
														<br/>
														<input type="text" name="projectManageyf.selfStore" id="updateSelfStore"/>
													</th>
												</tr>
												<tr>
													<th>
														<code id="ganttData-item-id">
															开始时间:
														</code>
														<br/>
														<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
														name="projectManageyf.startTime" id="updateStartTime"/>
													</th>
													<th>
														<code id="ganttData-item-id">
															预完成时间:
														</code>
														<br/>
														<input type="text" name="projectManageyf.reTime" id="updateReTime" 
														  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
													</th>
												</tr>
												
												<tr>
													<th colspan="2">
														<code id="ganttData-item-id">
															描述
														</code>
														<br/>
														<textarea rows="3" cols="60" style="width:515px;" id="updateRemark" name="projectManageyf.remark"></textarea>
													</th>
												</tr>
												<tr>
													<td colspan="2">
														<input type="button" id="updateBtn"
															style="width: 100px;" value="修改" onclick="submitForm('updateForm')">
														<input type="reset" class="resetBtn" style="width: 100px;margin-left:80px;" value="重置" >
													</td>
												</tr>
											</table>
										</form>
									</div>
									<div id="project-delete">
										
									</div>
								</div>
							</div>
							<ul id="treeDemo" class="ztree"></ul>
						<%@include file="/util/foot.jsp"%>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fn.gantt.js" charset="UTF-8" /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-tooltip.js" /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-popover.js" /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/prettify.js" /></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/GanttJS.js" /></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easytabs.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.hashchange.min.js"></script>
	<script type="text/javascript">
$(function(){
	show();
	var message = $("#error").val();
	if(null!=message || ""== message){
		$("#error").text("");
	}
});

var source =[];

var list;
//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		hover:false
	},
	view:{
		fontCss:getFont,
		nameIsHTML:true
	}
};

function getFont(treeId,node){
	return node.font?node.font:{};
}

function show(){
	$.ajax({
		url : 'projectPoolAction_findProjectManageyfByRootId.action',
		type : 'post',
		data : {
			rootIdStr : '${id}'
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			list=data;
			var zNodes = [];
			$.each(data, function (i, item) {
				var barColor=['ganttRed','ganttBlue','ganttGreen','ganttOrange'];
				var num=Math.floor(Math.random()*4);
				var date = eval('new Date(' + item.startTime.replace(/\d+(?=-[^-]+$)/, function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
				var currentdate = new Date(date).getTime();
				var date1 = eval('new Date(' + item.reTime.replace(/\d+(?=-[^-]+$)/, function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
   				var reCurrentdate = new Date(date1).getTime();
   				var nbsps = "";
   				for(var y =1; y<item.belongLayer;y++){
   					nbsps+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
   				}
   				var status = item.status;
   				if("完成"==status || "确认关闭"==status ){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/green.png' width='13px' height='10px'>已完成";
					num=2;
				}else if("执行中"==status){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/yellow.png' width='13px' height='10px'>执行中";
					num=3;
				}else if("待填报"==status){
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/red.png' width='13px' height='10px'>待填报";
					num=0;
				}else{
					imgPath = "<img src='${pageContext.request.contextPath}/images/mk/red.png' width='13px' height='10px'>"+status;
					num=1;
				}
   				//var colseStatus = "<div id='status"+i+"' style='border:1px solid #000;'> - </div>";colseStatus+
   				var proName =nbsps+"<span >"+item.proName+" "+imgPath+"</span>";//onclick='showWindows(\""+item.id+"\");'
   				var label =item.proNum+"&nbsp;&nbsp;&nbsp;"+item.proName+"&nbsp;&nbsp;&nbsp;"+item.remark;
   				
				source.push({
						name:proName,
						desc:item.principals,
						values: [{
							from: "/Date("+currentdate+")/",
							to: "/Date("+reCurrentdate+")/",
							desc: item.proName,
							label: label+"<input type='hidden' value='"+i+"' />", 
							customClass: barColor[num],
						}],
				});
				
<%--				zNodes.push({--%>
<%--					id : item.id,--%>
<%--					pId : item.fatherId,--%>
<%--					rootId : item.rootId,--%>
<%--					belongLayer : item.belongLayer,--%>
<%--					name : proName,--%>
<%--					open : true,--%>
<%--					click : false--%>
<%--				});--%>
				
				
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
		
	$(".gantt").gantt({
		source:source,
		navigate: "scroll",//button/scroll
		scale: "weeks",
		maxScale: "months",
		minScale: "days",
		itemsPerPage:6,
		scrollToToday:true,
		waitText:"请稍后，正在加载中...",
		onItemClick: function(dataobj ) {
			var eve = window.event;
			var obj = eve.path[0];
			var ind=obj;
		    var input_obj=$(obj).find("input")[0];
			var lineNum=input_obj.value;
			//显示详细
			$("#proId").val(list[lineNum].id);
			$("#reTime").val(list[lineNum].reTime);
			//showWindows(list[lineNum].id);
			//$("#pageNum").val(MypageNum);
		},
		onAddClick: function(dt, rowId) {
			//addTask();
		},
		onRender: function(pageNum) {
			return pageNum;
		}
	});
	
	
	
	
	
	
}
function closeDia(){
	$('#dialog-overlay, #dialog-box').hide();  
}


function testTime() {
	var startStr = document.getElementById("startTime").value;
	var endStr = document.getElementById("endTime").value;
	if(startStr=="" ||startStr.length<=0){
		alert("请选择开始时间");
		$("#startTime").focus();
		return false;
	}
	if(endStr=="" ||endStr.length<=0){
		alert("请选择结束时间");
		$("#endTime").focus();
		return false;
	
	}
	if (startStr != "" && endStr != "") {
		var start = startStr.split("-");
		var end = endStr.split("-");
		var startTime = new Date(start[0], start[1], start[2]);
		
		var endTime = new Date(end[0], end[1], end[2]);
		var myDate = new Date(); //获得当前时间
		myDate.setMonth(myDate.getMonth() + 1);//为当前date的月份+1后重新赋值
		if (startTime <= endTime == false) {
			alert("开始时间不能大于结束时间!请重新选择!谢谢!");
			$("#endTime").val("");
			$("#endTime").focus();
			return false;
		} 
	}
	return true;
}

function showWindows(proId){
	$('#dialog-overlay').show();
	$('#dialog-box').show();
	if(null== proId || ""==proId){
		proId = $("#proId").val();
	}else{
		$("#proId").val(proId);
	}
	$('#tab-container').easytabs('select', '#project-info');
	$.ajax({
		type:'POST',
		url:'projectPoolAction_findProjectManageyfById.action',
		dataType:'json',
		data: {id:proId} ,
		cache:true,
		success:function(data){
			if(data!=null){
				$("#proNum").val(data.proNum);
				$("#proName").val(data.proName);
				$("#principals").val(data.principals);
				$("#status").val(data.status);
				$("#startTime").val(data.startTime);
				$("#reTime").val(data.reTime);
				$("#sumStore").val(data.sumStore);
				$("#selfStore").val(data.selfStore);
				$("#remark").val(data.remark);
				
			}
		}
	});
}

function submitForm(from){
	
	if("addForm"==from){
		var proId = $("#proId").val();
		$.ajax({
			type:'POST',
			url:'projectPoolAction_findProjectManageyfById.action',
			dataType:'json',
			data: {id:proId} ,
			cache:false,
			async: false,
			success:function(data){
				if(data!=null){
					$("#yfrootId").val(data.rootId);
					$("#yffatherId").val(data.fatherId);
					$("#yfbelongLayer").val(data.belongLayer+1);
					$("#yfpoolId").val(data.poolId);
				}
			}
		});
		var addProName = $("#addProName").val();
		if(null==addProName || ""==addProName){
			alert("请输入项目名称");
			 $("#addProName").facus();
			return ;
		}
		var addReTime = $("#addReTime").val();
		var showReTime = $("#reTime").val();
		var d1 = new Date(addReTime.replace(/\-/g, "\/"));  
 		var d2 = new Date(showReTime.replace(/\-/g, "\/"));
		if(addReTime!= "" && showReTime!="" && d1>d2){
			alert("子项目预完成时间不能大于父项目完成时间");
			$("#addReTime").focus();
			return;
		}
 		$("#addForm").submit();
	}else if("updateForm"==from){
		var proName = $("#updateProName").val();
		if(null==proName || ""==proName){
			alert("请输入项目名称");
			 $("#updateProName").facus();
			return ;
		}
		var addReTime = $("#updateReTime").val();
		var showReTime = $("#reTime").val();
		var d1 = new Date(addReTime.replace(/\-/g, "\/"));  
 		var d2 = new Date(showReTime.replace(/\-/g, "\/"));
		if(addReTime!= "" && showReTime!="" && d1>d2){
			alert("子项目预完成时间不能大于父项目完成时间");
			$("#addReTime").focus();
			return;
		}
		$("#updateId").val($("#proId").val());
		$("#updateForm").submit();
	}else{
		//"没有找到这个按钮");
	}
	
}

function showUpdatePage(proId){
	if(null==proId){
		proId = $("#proId").val();
		$("#updateId").val(proId);
	}
	if(null!= proId && ""!=proId){
		$.ajax({
			type:'POST',
			url:'projectPoolAction_findProjectManageyfById.action',
			dataType:'json',
			data: {id:proId} ,
			cache:true,
			success:function(data){
				if(data!=null){
					$("#updateProName").val(data.proName);
					$("#updateSelfStore").val(data.selfStore);
					$("#updateStartTime").val(data.startTime);
					$("#updateReTime").val(data.reTime);
					$("#updateRemark").val(data.remark);
				}
			}
		});
	}
}

function delProject(){
	var id = $("#proId").val();
	if(null!=id){
		if(confirm("确定要删除这个项目吗？")){
			location.href="${pageContext.request.contextPath}/projectPoolAction_delProject.action?pageStatus=${pageStatus}&id2="+id;
		}
	}
}
    </script>
</html>
