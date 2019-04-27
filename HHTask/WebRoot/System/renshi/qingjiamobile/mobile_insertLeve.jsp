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
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">

		<title>请假申请</title>

		<link rel="stylesheet"
			href="<%=basePath%>/css/jquery.mobile-1.3.2.min.css">
		<script src="<%=basePath%>/js/jquery-1.8.3.min.js">
</script>
		<script src="<%=basePath%>/js/jquery.mobile-1.3.2.min.js">
</script>
  
		<script src="<%=basePath%>/js/mobiscroll.core-2.5.2.js"
			type="text/javascript">
</script>
		<script src="<%=basePath%>/js/mobiscroll.core-2.5.2-zh.js"
			type="text/javascript">
</script>

		<link href="<%=basePath%>/css/mobiscroll.core-2.5.2.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/css/mobiscroll.animation-2.5.2.css"
			rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>/js/mobiscroll.datetime-2.5.1.js"
			type="text/javascript">
</script>
		<script src="<%=basePath%>/js/mobiscroll.datetime-2.5.1-zh.js"
			type="text/javascript">
</script>

		<!-- S 可根据自己喜好引入样式风格文件 -->
		<script src="<%=basePath%>/js/mobiscroll.android-ics-2.5.2.js"
			type="text/javascript">
</script>
		<link href="<%=basePath%>/css/mobiscroll.android-ics-2.5.2.css"
			rel="stylesheet" type="text/css" />
		<!-- E 可根据自己喜好引入样式风格文件 -->



	</head>
	<body>
		<div data-role="page" data-theme="c">
			<div data-role="content">
				<form id="saveForm" action="" method="post"
					onsubmit="return checkType();">
					<input type="hidden" name="device" value="mobile" />
					<input type="hidden" name="askForLeave.appayTag"
						value="${askForLeave.appayTag}" />
					<div data-role="fieldcontain">
						<label for="askForLeave.leaveType">
							请假类型:
						</label>
						<select id="aLeaveType" name="askForLeave.leaveType">
							<option value="个人请假">
								个人请假
							</option>
							<option value="代理请假">
								代理请假
							</option>
						</select>
						<br>
						<label for="askForLeave.leavePersonDept">
							请假人部门:
						</label>
						<select name="askForLeave.leavePersonDept" id="dept">
							<option value="${Users.dept}">
								${Users.dept}
							</option>
						</select>
						<br>
						<div id="userNameDiv">
							<label for="askForLeave.leavePerson">
								请假人：
							</label>
							<input id="userName" type="text" name="askForLeave.leavePerson"
								value="${Users.name}">
						</div>
						<div id="usersDiv" style="display: none;">
							<label for="askForLeave.leavePerson1">
								请假人：
							</label>
							<select id="users" name="askForLeave.leavePerson1"></select>
						</div>
						<br>
						<label for="askForLeave.leavePersonCode">
							工号：
						</label>
						<input type="text" id="leavePersonCode"
							name="askForLeave.leavePersonCode" value="${Users.code}" />
						<br>
						<label for="askForLeave.leaveStartDate">
							请假日期从：
						</label>
						<input type="text" data-role="datebox" id="startDate"
							name="askForLeave.leaveStartDate" />
						<br>
						<label for="askForLeave.leaveEndDate">
							到：
						</label>
						<input type="text" data-role="datebox" id="endDate"
							name="askForLeave.leaveEndDate" />
						<br>
						<label for="askForLeave.leaveTypeOf">
							假事类型：
						</label>
						<select id="aLeaveTypeOf" name="askForLeave.leaveTypeOf">
							<option value="">
								--请选择假事类型--
							</option>
							<option value="事假">
								事假
							</option>
							<option value="病假">
								病假
							</option>
							<option value="丧假">
								丧假
							</option>
							<option value="婚假">
								婚假
							</option>
							<option value="换休">
								换休
							</option>
							<option value="年休">
								年休
							</option>
							<option value="公出">
								公出
							</option>
						</select>
						<br>
						<div id="huanxiuxieyi" style="display: none;">
							<div data-role="collapsible" data-collapsed="false"
								style="background: #ddd;">
								<h4>
									换休说明
								</h4>
								<div id="huanxiuxieyiContent"></div>
								<select id="slider" data-role="slider">
									<option value="false">
										不同意
									</option>
									<option value="true">
										同意
									</option>
								</select>
							</div>
						</div>
						<div id="freeDeptDiv" style="display: none;">
							<hr />
							<font color="red">费用承担部门与审批人:</font>
							<input type="button" value="增加" onclick="addFreeDept()"
								 >
							<ul id="freeDeptUl0">
								<li id="freeDeptli0">
									<SELECT id="zrdept0" name="approvalId"
										onchange="changefreeDept(0)"></SELECT>
									<SELECT id="zrpeople0" name="ids"></SELECT>
									<input type="button" value="删除" onclick="deleteFreeDept(0)"
										 >
								</li>
							</ul>
						</div>
						<br>
						<div id="gongchuStyle" style="background: #ddd; display: none;">
							<label for="askForLeave.gongchuPlace">
								选择公出目的地：
							</label>
							<select name="askForLeave.gongchuPlace">
								<option value="省内">
									省内
								</option>
								<option value="省外">
									省外
								</option>
							</select>
							<br>
							<label for="askForLeave.needCar">
								用车情况：
							</label>
							<select id="needCar" name="askForLeave.needCar">
								<option value="否">
									否
								</option>
								<option value="是">
									是
								</option>
							</select>
							<br>
							<label for="askForLeave.leaveObjectType">
								请选择项目类型：
							</label>
							<select id="aLeaveObjectType" name="askForLeave.leaveObjectType">
								<option value="生产">
									生产
								</option>
								<option value="项目">
									项目
								</option>
								<option value="KVP">
									KVP
								</option>
								<option value="其他">
									其他
								</option>
							</select>
							<br>
							<div id="d1">
								<label for="askForLeave.leaveObjectType1">
									加工件号:
								</label>
								<select name="askForLeave.leaveObjectNeirong1" id="markId"></select>
								<br>
							</div>
							<div id="d2" style="display: none;">
								<label for="askForLeave.leaveObjectNeirong2">
									项目编号:
								</label>
								<select name="askForLeave.leaveObjectNeirong2" id="xiangmu">
								</select>
								<br>
							</div>
							<div id="d3" style="display: none;">
								<label for="askForLeave.leaveObjectNeirong3">
									KVP编号:
								</label>
								<select name="askForLeave.leaveObjectNeirong3" id="kvp">
								</select>
								<br>
							</div>
							<div id="d4" style="display: none;" data-role="fieldcontain">
								<label for="askForLeave.leaveObjectNeirong">
									其他原因:
								</label>
								<textarea name="askForLeave.leaveObjectNeirong" id="qiTa"></textarea>
								<br>
							</div>
						</div>
						<div data-role="fieldcontain">
							<label for="askForLeave.leaveReason">
								请假原因:
							</label>
							<textarea name="askForLeave.leaveReason" id="reason"></textarea>
						</div>
						<br>
						<div id="carTab" style="display: none;">
							<div data-role="collapsible" data-collapsed="false"
								style="background: #ddd;">
								<h4>
									申请用车单
								</h4>
								<label for="askForLeave.singleCar.car_date">
									用车时间:
								</label>
								<input type="text" id="useCarDate"
									name="askForLeave.singleCar.car_date" />
								<br>
								<label for="askForLeave.singleCar.car_amount">
									人数及吨位:
								</label>
								<input type="text" name="askForLeave.singleCar.car_amount"
									value="" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" />
								<br>
								<label for="askForLeave.singleCar.car_place">
									到达地点:
								</label>
								<input type="text" id="car_place"
									name="askForLeave.singleCar.car_place" />
								<br>
								<label for="askForLeave.singleCar.charges">
									收费标准:
								</label>
								<input type="text" id="car_place"
									name="askForLeave.singleCar.charges"
									onkeyup="if(isNaN(value))execCommand('undo')" />
								<br>
								<label for="askForLeave.singleCar.comeoroutdate">
									进出库时间:
								</label>
								<input type="text" id="comeoroutlibdate"
									name="askForLeave.singleCar.comeoroutdate" />
								<br>
								<label for="askForLeave.singleCar.beforeodometer">
									出车前里程表:
								</label>
								<input type="text" name="askForLeave.singleCar.beforeodometer"
									onkeyup="if(isNaN(value))execCommand('undo')" />
								<br>
								<label for="askForLeave.singleCar.endodometer">
									回车后里程表:
								</label>
								<input type="text" name="askForLeave.singleCar.endodometer"
									onkeyup="if(isNaN(value))execCommand('undo')" />
								<br>
								<label for="askForLeave.singleCar.kilometers">
									公里数:
								</label>
								<input type="text" name="askForLeave.singleCar.kilometers"
									onkeyup="if(isNaN(value))execCommand('undo')" />
								<br>
								<label for="askForLeave.singleCar.pilotname">
									驾驶员姓名:
								</label>
								<input type="text" name="askForLeave.singleCar.pilotname" />
								<br>
								<label for="askForLeave.singleCar.car_number">
									车号:
								</label>
								<input type="text" name="askForLeave.singleCar.car_number" />
								<br>
								<label for="askForLeave.singleCar.unit_leading">
									用车单位领导:
								</label>
								<input type="text" name="askForLeave.singleCar.unit_leading" />
								<br>
								<label for="askForLeave.singleCar.car_useType">
									用车类型:
								</label>
								<select name="askForLeave.singleCar.car_useType">
									<option>
										处理质量事故
									</option>
									<option>
										市场开发
									</option>
									<option>
										产品试装
									</option>
									<option>
										财务相关
									</option>
									<option>
										PEBS系统维护
									</option>
								</select>
								<br>
								<label for="askForLeave.singleCar.remark">
									备注:
								</label>
								<input type="text" name="askForLeave.singleCar.remark" />
							</div>
						</div>
						<br>
						<input type="button" value="请假" id="tijiao" onclick="nianxiu()" />
						<br>
						<input type="reset" value="重置" />
					</div>
				</form>
			</div>

		</div>

		<script type="text/javascript">
$(function () {
			var currYear = (new Date()).getFullYear();	
			var opt={};
			opt.date = {preset : 'date'};
			//opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				lang:'zh',
		        startYear:currYear - 10, //开始年份
		        endYear:currYear + 10 //结束年份
			};

			//$("#startDate").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
			
		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
		  	var optTime = $.extend(opt['time'], opt['default']);
		  	$("#startDate").mobiscroll(optDateTime).datetime(optDateTime);
		  	$("#endDate").mobiscroll(optDateTime).datetime(optDateTime);
		  	$("#useCarDate").mobiscroll(optDateTime).datetime(optDateTime);
		  	$("#comeoroutlibdate").mobiscroll(optDateTime).datetime(optDateTime);

			//下面注释部分是上面的参数可以替换改变它的样式
			//希望一起研究插件的朋友加我个人QQ也可以，本人也建个群 291464597 欢迎进群交流。哈哈。这个不能算广告。
			// 直接写参数方法
			//$("#scroller").mobiscroll(opt).date(); 
			// Shorthand for: $("#scroller").mobiscroll({ preset: 'date' });
			//具体参数定义如下
		    //{
		    //preset: 'date', //日期类型--datatime --time,
		    //theme: 'ios', //皮肤其他参数【android-ics light】【android-ics】【ios】【jqm】【sense-ui】【sense-ui】【sense-ui】
										//【wp light】【wp】
		    //mode: "scroller",//操作方式【scroller】【clickpick】【mixed】
		    //display: 'bubble', //显示方【modal】【inline】【bubble】【top】【bottom】
		    //dateFormat: 'yyyy-mm-dd', // 日期格式
		    //setText: '确定', //确认按钮名称
		    //cancelText: '清空',//取消按钮名籍我
		    //dateOrder: 'yymmdd', //面板中日期排列格
		    //dayText: '日', 
		    //monthText: '月',
		    //yearText: '年', //面板中年月日文字
		    //startYear: (new Date()).getFullYear(), //开始年份
		    //endYear: (new Date()).getFullYear() + 9, //结束年份
		    //showNow: true,
		    //nowText: "明天",  //
		    //showOnFocus: false,
		    //height: 45,
		    //width: 90,
		    //rows: 3}

        });
    </script>
		<script type="text/javascript">
function getRootPath() {
	//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}
var rootPath = getRootPath() + "/mobile/";
//项目编号
function objectType(val) {
	document.getElementById("xiangmu").value = "";
	document.getElementById("kvp").value = "";
	document.getElementById("markId").value = "";
	document.getElementById("qiTa").value = "";
	//项目
	if (val == "项目") {
		$("#d1").css("display", "none");
		$("#d3").css("display", "none");
		$("#d4").css("display", "none");
		$("#markId").attr("disabled", "disabled");
		$("#qiTa").attr("disabled", "disabled");
		$("#kvp").attr("disabled", "disabled");
		$("#xiangmu").removeAttr("disabled");
		$("#d2").show();
		//tr_modifing.style.display = "table−row";
		//显示项目信息
		//-----------------------------------------------------------
		//-------------------------------------KVP------------------------------------
	} else if (val == "KVP") {
		$("#d1").css("display", "none");
		$("#markId").attr("disabled", "disabled");
		$("#qiTa").attr("disabled", "disabled");
		$("#xiangmu").attr("disabled", "disabled");
		$("#kvp").removeAttr("disabled");
		$("#d2").css("display", "none");
		$("#d4").css("display", "none");
		$("#d3").show();

	} else if (val == "其他") {
		$("#d1").css("display", "none");
		$("#d2").css("display", "none");
		$("#d3").css("display", "none");
		$("#markId").attr("disabled", "disabled");
		$("#xiangmu").attr("disabled", "disabled");
		$("#kvp").attr("disabled", "disabled");
		$("#qiTa").removeAttr("disabled");
		$("#d4").show();

	} else {
		$("#d3").css("display", "none");
		$("#d2").css("display", "none");
		$("#d4").css("display", "none");
		$("#xiangmu").attr("disabled", "disabled");
		$("#qiTa").attr("disabled", "disabled");
		$("#kvp").attr("disabled", "disabled");
		$("#markId").removeAttr("disabled");
		$("#d1").show();
		//------------------------------------
		//---------------------------------------
	}
}
$(document)
		.ready(
				function() {
					$('#aLeaveType')
							.change(
									function() {
										var type = $(this).children(
												'option:selected').val();//这就是selected的值 
										if (type == "个人请假") {
											$("#userName").val("${Users.name}");
											$("#userNameDiv").css("display",
													"block");
											$("#usersDiv").css("display",
													"none");
											$("#userName").attr("readonly",
													"readonly");
											$("#dept").empty();
											$(
													"<option value='${Users.dept}'>${Users.dept}</option>")
													.appendTo("#dept");
										} else if (type == "代理请假") {
											$("#userNameDiv").css("display",
													"none");
											$("#usersDiv").css("display",
													"block");
											$("#leavePersonCode").val("");
											//显示所有部门信息
											$
													.ajax( {
														url : rootPath + 'DeptNumberAction!findAllDept.action',
														dataType : 'json',
														cache : false,//防止数据缓存
														success : function(
																allDdept) {
															$("#dept").empty();
															$(
																	"<option value=''>--请选择部门--</option>")
																	.appendTo(
																			"#dept");
															$(allDdept)
																	.each(
																			function() {
																				$(
																						"<option value='"
																								+ this.dept
																								+ "'>"
																								+ this.dept
																								+ "</option>")
																						.appendTo(
																								"#dept");
																			});
														}

													});
											//显示部门对应的员工信息
											$("#dept")
													.bind(
															"change",
															function() {
																if ($("#dept")
																		.val() != "") {
																	$
																			.ajax( {
																				url : rootPath
																						+ "UsersAction!findUsersByDept.action",
																				type : 'post',
																				dataType : 'json',
																				cache : false,//防止数据缓存
																				data : {
																					deptName : $(
																							"#dept")
																							.val()
																				},
																				success : function(
																						useradsfa) {
																					$(
																							"#users")
																							.empty();//清空
																					$(
																							"<option></option>")
																							.appendTo(
																									"#users");
																					$(
																							useradsfa)
																							.each(
																									function() {
																										$(
																												"<option value='"
																														+ this.code
																														+ "|"
																														+ this.name
																														+ "'>"
																														+ this.name
																														+ "</option>")
																												.appendTo(
																														"#users")
																									});
																				},
																				error : function() {
																					alert("服务器异常!");
																				}
																			});
																}

															});

										}
										$("#users").bind("change", function() {
											var users = $("#users").val();
											var usersData = users.split("|");
											var code = usersData[0];
											$("#userName").val(usersData[1]);
											$("#leavePersonCode").val(code);
										});
									});

					//处理公出 换休
					$('#aLeaveTypeOf')
							.change(
									function() {
										$("#carTab").hide();
										var qjStyle = $(this).children(
												'option:selected').val();//这就是selected的值 
										if ("公出" == qjStyle) {
											var leaveObjectType = $(
													"#tpdiv input[name='askForLeave.leaveObjectType']:checked ")
													.val();
											objectType(leaveObjectType);
											$("#gongchuStyle").show();
											$("#huanxiuxieyi").hide();
											//$("#tijiao").attr("disabled","false");
											$("#tijiao").removeAttr("disabled");

											$("#feigongchu").hide();
											$("#carhao").attr("disabled",
													"disabled");
											$("#freeDeptDiv").show();
											if ($("#zrdept0").val() == null) {
												setDept(0);
											}

										} else if ("换休" == qjStyle) {
											//-----------------------------------------------------------------
											$("#huanxiuxieyi").show();
											//disabled="true"
											//$("#tijiao").disabled="true";tijiao
											$("#tijiao").attr("disabled",
													"true");
											$("#gongchuStyle").hide();
											//------------------------------------------------------------------
										} else {
											$("#huanxiuxieyi").hide();
											$("#gongchuStyle").hide();
											$("#tijiao").removeAttr("disabled");
										}
									});
					//项目类型切换
					$('#aLeaveObjectType').change(function() {
						var val = $(this).children('option:selected').val();//这就是selected的值 
							//项目
							if (val == "项目") {
								$("#d1").css("display", "none");
								$("#d3").css("display", "none");
								$("#d4").css("display", "none");
								$("#markId").attr("disabled", "disabled");
								$("#qiTa").attr("disabled", "disabled");
								$("#kvp").attr("disabled", "disabled");
								$("#xiangmu").removeAttr("disabled");
								$("#d2").show();
								//tr_modifing.style.display = "table−row";
								//显示项目信息
								//-----------------------------------------------------------
								//-------------------------------------KVP------------------------------------
							} else if (val == "KVP") {
								$("#d1").css("display", "none");
								$("#markId").attr("disabled", "disabled");
								$("#qiTa").attr("disabled", "disabled");
								$("#xiangmu").attr("disabled", "disabled");
								$("#kvp").removeAttr("disabled");
								$("#d2").css("display", "none");
								$("#d4").css("display", "none");
								$("#d3").show();

							} else if (val == "其他") {
								$("#d1").css("display", "none");
								$("#d2").css("display", "none");
								$("#d3").css("display", "none");
								$("#markId").attr("disabled", "disabled");
								$("#xiangmu").attr("disabled", "disabled");
								$("#kvp").attr("disabled", "disabled");
								$("#qiTa").removeAttr("disabled");
								$("#d4").show();

							} else {
								$("#d3").css("display", "none");
								$("#d2").css("display", "none");
								$("#d4").css("display", "none");
								$("#xiangmu").attr("disabled", "disabled");
								$("#qiTa").attr("disabled", "disabled");
								$("#kvp").attr("disabled", "disabled");
								$("#markId").removeAttr("disabled");
								$("#d1").show();
								//------------------------------------
								//---------------------------------------
							}
						});

					$('#needCar').change(function() {
						var obj = $(this).children('option:selected').val();//这就是selected的值 
							if (obj == '是') {
								$("#carTab").show();
							} else {
								$("#carTab").hide();
							}
						});
				});

//获取数据
function getf1() {
	//显示项目信息
	$.ajax( {
		type : "POST",
		url : rootPath + "proAction!listPro.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj.code + " / " + obj.name + "'>"
						+ obj.code + "   " + obj.name + "</option>";
			});
			$(bj).appendTo("#xiangmu")
		}
	});
	//显示KVp信息
	//-----------------------------------------------------------
	var userName = document.getElementById('userName').value;
	$.ajax( {
		type : "POST",
		url : rootPath + "proAction!listKVP.action",
		data : {
			overName : userName
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj[0] + " / " + obj[1] + "'>"
						+ obj[0] + "   " + obj[1] + "</option>";
			});
			$(bj).appendTo("#kvp");
		},
		error : function() {
			alert("KVP加载失败！");
		}

	});
	//------------------------
}

function f1() {
	$.ajax( {
		type : "POST",
		url : rootPath + "overtimeAction!finAllMarkIdForSetlectAll.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object,
					function(i, obj) {
						bj += "<option value='" + "件号 " + obj[0] + "  /批次 "
								+ obj[1] + "'>" + "件号" + obj[0] + "   批次"
								+ obj[1] + "</option>";
					});
			$(bj).appendTo("#markId")
		}
	});
}
function getxieyi() {
	$.ajax( {
		type : "POST",
		url : rootPath + "AskForLeaveAction!listhuanxiuxieyi.action",
		dataType : "json",
		success : function(object) {
			var reshtml = "<table class='table' id='t2'>";
			var th = "";

			$.each(object, function(i, obj) {
				th += "<tr ><td align='left'>" + (i + 1) + "." + obj.content
						+ "</td></tr>"
			});
			reshtml = reshtml + th + "</table>";
			$(reshtml).appendTo("#huanxiuxieyiContent");

		}

	});

}
function disable() {
	document.getElementById("tijiao").disabled = true
}
function enable() {
	document.getElementById("tijiao").disabled = false
}
function nianxiu() {
	var s = checkType();
	if (s) {
		var temp = document.getElementsByName("askForLeave.leaveTypeOf");
		var nianxiuvalue;
		for (i = 0; i < temp.length; i++) {
			if (temp[i].checked) {
				nianxiuvalue = temp[i].value;
				break;
			}
		}
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		var leavePersonCode = document.getElementById("leavePersonCode").value;
		//var tijiao=document.getElementById("tijiao");
		//------------------------------------------------------------------------
		if ("年休" == nianxiuvalue) {
			$
					.ajax( {
						type : "POST",
						url : rootPath
								+ "AskForLeaveAction!panduannianxiu.action?askForLeave.leaveStartDate="
								+ startDate + "&askForLeave.leaveEndDate="
								+ endDate + "&askForLeave.leavePersonCode="
								+ leavePersonCode,
						dataType : "json",
						success : function(object) {
							alert(object);
							if (object == "您可以申请年休!") {
								document.getElementById("saveForm").submit();
								return;
							}
						}
					});
		} else {
			$.ajax( {
				type : "POST",
				url : "AskForLeaveAction!saveOrUpdate.action",
				dataType : "json",
				data : $("#saveForm").serialize(),
				success : function(datas) {
					if (datas == "saveOk") {
						alert("请假成功");
						//window.location.href = rootPath
				//		+ "System/renshi/qingjiamobile/private.html";
				return;
			} else {
				alert(datas);
			}
		}
			});

			return;
		}
	}
}
function getPostParams() {
	var params = "";
	var leaveTypeOfValue = $("#aLeaveTypeOf").children('option:selected').val();
	params = params + "device=" + $("input[name='device']").val() + "&";
	params = params
			+ "askForLeave.leaveType="
			+ $("select[name='askForLeave.leaveType']").children(
					'option:selected').val() + "&";
	params = params
			+ "askForLeave.leavePersonDept="
			+ $("select[name='askForLeave.leavePersonDept']").children(
					'option:selected').val() + "&";
	if ($("#userName").val() != null) {
		params = params + "askForLeave.leavePerson=" + $("#userName").val()
				+ "&";
	} else {
		params = params + "askForLeave.leavePerson="
				+ $("#users").children('option:selected').val() + "&";
	}
	params = params + "askForLeave.leavePersonCode="
			+ $("input[name='askForLeave.leavePersonCode']").val() + "&";
	params = params + "askForLeave.leaveStartDate="
			+ $("input[name='askForLeave.leaveStartDate']").val() + "&";
	params = params + "askForLeave.leaveEndDate="
			+ $("input[name='askForLeave.leaveEndDate']").val() + "&";
	params = params + "askForLeave.leaveTypeOf=" + leaveTypeOfValue + "&";
	params = params + "askForLeave.leaveReason="
			+ $("textarea[name='askForLeave.leaveReason']").val() + "&";
	if ("公出" == leaveTypeOfValue) {
		var leaveObjectType = $("select[name='askForLeave.leaveObjectType']")
				.children('option:selected').val();
		var needCar = $("select[name='askForLeave.needCar']").children(
				'option:selected').val();
		params = params
				+ "askForLeave.gongchuPlace="
				+ $("select[name='askForLeave.gongchuPlace']").children(
						'option:selected').val() + "&";
		params = params + "askForLeave.needCar=" + needCar + "&";
		params = params + "askForLeave.leaveObjectType=" + leaveObjectType
				+ "&";
		if ("生产" == leaveObjectType) {
			params = params
					+ "askForLeave.leaveObjectNeirong1="
					+ $("select[name='askForLeave.leaveObjectNeirong1']")
							.children('option:selected').val() + "&";
		} else if ("项目" == leaveObjectType) {
			params = params
					+ "askForLeave.leaveObjectNeirong2="
					+ $("select[name='askForLeave.leaveObjectNeirong2']")
							.children('option:selected').val() + "&";
		} else if ("KVP" == leaveObjectType) {
			params = params
					+ "askForLeave.leaveObjectNeirong3="
					+ $("select[name='askForLeave.leaveObjectNeirong3']")
							.children('option:selected').val() + "&";
		} else if ("其他" == leaveObjectType) {
			params = params
					+ "askForLeave.leaveObjectNeirong="
					+ $("textarea[name='askForLeave.leaveObjectNeirong']")
							.val() + "&";
		}
		if ("是" == needCar) {
			params = params + "askForLeave.singleCar.car_date="
					+ $("input[name='askForLeave.singleCar.car_date']").val()
					+ "&";
			params = params + "askForLeave.singleCar.car_amount="
					+ $("input[name='askForLeave.singleCar.car_amount']").val()
					+ "&";
			params = params + "askForLeave.singleCar.car_place="
					+ $("input[name='askForLeave.singleCar.car_place']").val()
					+ "&";
			params = params + "askForLeave.singleCar.charges="
					+ $("input[name='askForLeave.singleCar.charges']").val()
					+ "&";
			params = params
					+ "askForLeave.singleCar.comeoroutdate="
					+ $("input[name='askForLeave.singleCar.comeoroutdate']")
							.val() + "&";
			params = params
					+ "askForLeave.singleCar.beforeodometer="
					+ $("input[name='askForLeave.singleCar.beforeodometer']")
							.val() + "&";
			params = params
					+ "askForLeave.singleCar.endodometer="
					+ $("input[name='askForLeave.singleCar.endodometer']")
							.val() + "&";
			params = params + "askForLeave.singleCar.kilometers="
					+ $("input[name='askForLeave.singleCar.kilometers']").val()
					+ "&";
			params = params + "askForLeave.singleCar.pilotname="
					+ $("input[name='askForLeave.singleCar.pilotname']").val()
					+ "&";
			params = params + "askForLeave.singleCar.car_number="
					+ $("input[name='askForLeave.singleCar.car_number']").val()
					+ "&";
			params = params
					+ "askForLeave.singleCar.unit_leading="
					+ $("input[name='askForLeave.singleCar.unit_leading']")
							.val() + "&";
			params = params + "askForLeave.singleCar.car_number="
					+ $("input[name='askForLeave.singleCar.car_number']").val()
					+ "&";
			params = params
					+ "askForLeave.singleCar.car_useType="
					+ $("select[name='askForLeave.singleCar.car_useType']")
							.children('option:selected').val() + "&";
			params = params + "askForLeave.singleCar.remark="
					+ $("input[name='askForLeave.singleCar.remark']").val()
					+ "&";
		}
	}
	params = params.substr(0, params.length - 1);
	return params;
}
function checkType() {

	var val = document.getElementById('startDate').value;
	var val2 = document.getElementById('endDate').value;
	var val3 = document.getElementById('reason').value;
	var val4 = document.getElementById('useCarDate').value;
	var val5 = document.getElementById('comeoroutlibdate').value;

	var userName = document.getElementById('userName').value;
	var leavePersonCode = document.getElementById('leavePersonCode').value;

	if (!val || val == "" || !val2 || val2 == "") {
		alert("时间不能为空，请选择请假开始和结束时间！！！");
		return false;
	} else if (!val3 || val3 == "") {
		alert("请假原因不能为空，请准确填写请假原因！！！");
		return false;
	}
	if (val > val2) {
		alert("请假开始时间不能大于结束时间！！！");
		return false;
	}
	if (userName = "") {
		alert("请假人不能为空！！！");
		return false;
	}
	if (leavePersonCode == "") {
		alert("请假工号不能为空！！！");
		return false;
	}

	if (typeof (val4) != "undefined" && val4 != ""
			&& "00" != val4.substr(val4.length - 2, val4.length)) {
		val4 = val4 + ":00";
		document.getElementById('useCarDate').value = val4;
	}
	if (typeof (val5) != "undefined" && val5 != ""
			&& "00" != val5.substr(val5.length - 2, val5.length)) {
		val5 = val5 + ":00";
		document.getElementById('comeoroutlibdate').value = val5;
	}
	if ("00" != val.substr(val.length - 2, val.length)) {
		val = val + ":00";
	}
	if ("00" != val2.substr(val2.length - 2, val2.length)) {
		val2 = val2 + ":00";
	}
	document.getElementById('startDate').value = val;
	document.getElementById('endDate').value = val2;

	var leaveTypeOfValue = $("#aLeaveTypeOf").children('option:selected').val();
	if ("换休" == leaveTypeOfValue
			&& "true" !== $("#slider").children('option:selected').val()) {
		alert("请同意换休协议！");
		return false;
	}
	/*
	alert(radioType);
	if (radioType ="换休") {
		//tongyi
		alert("test");
		var tongyi = document.getElementById('tongyi').value;
		if(tongyi==false){
			alert("请同意换休协议！！！");
		}
		return false;
	}*/
	var obj2 = $("#aLeaveTypeOf").children('option:selected').val();//这就是selected的值 
	var error = '';
	/* 非空验证 ******************************** */
	if (typeof (obj2) == "undefined") {
		error = "请选择假事类型！\n";
		alert(error);
	} else {
		return true;
	}
	return false;
}
var deptIndex = 0;
function setDept(i) {
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						var html = "";
						if (this.dept == "${Users.dept}") {
							html = "<option selected='selected' value='"
									+ this.id + "'>" + this.dept + "</option>";
						} else {
							html = "<option value='" + this.id + "'>"
									+ this.dept + "</option>";
						}
						$(html).appendTo("#zrdept" + i);
					});
			changefreeDept(i);
			//$("#zrdept" + i).tinyselect();
		}
	});
}
function changefreeDept(i) {
	var deptId = $("#zrdept" + i).val();
	if (deptId > 0) {
		$.ajax( {
			type : "post",
			url : "AskForLeaveAction!getDeptUsers.action",
			dataType : "json",
			data : {
				id : deptId
			},
			success : function(data) {
				//填充部门信息
			var selectbox = $("#freeDeptUl" + i + " .tinyselect");
			if (selectbox.length > 1) {
				var len = selectbox.length - 1;
				for ( var n = len; n >= 1; n--) {
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople" + i).empty();
			$(data).each(
					function() {
						var html = "<option value='" + this.userId + "'>"
								+ this.userName + "</option>";
						$(html).appendTo("#zrpeople" + i);
					});
			//$("#zrpeople" + i).tinyselect();

		}
		});
	}
}
getxieyi();
f1();
getf1();
function addFreeDept() {
	deptIndex++;
	var html = "<ul id='freeDeptUl" + deptIndex + "'>" + "<li id='freeDeptli"
			+ deptIndex + "'>" + "<SELECT id='zrdept" + deptIndex
			+ "' name='approvalId' onchange='changefreeDept(" + deptIndex
			+ ")'></SELECT>" + "<SELECT id='zrpeople" + deptIndex
			+ "' name='ids'></SELECT>"
			+ "<input type='button' value='删除' onclick='deleteFreeDept("
			+ deptIndex + ")' style='width: 60px;height: 30px'>" + "</li></ul>"
	$(html).appendTo("#freeDeptDiv");
	setDept(deptIndex);
}
function deleteFreeDept(index) {
	$("#freeDeptUl" + index).remove();
}
</script>
	</body>
</html>

