<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>${moduleFunction.functionName},生产力生态平衡系统</title>
		<link rel="shortcut icon" href="favicon.ico" />
		<script type="text/javascript"
			src="<%=basePath%>javascript/DatePicker/WdatePicker.js">
</script>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/css/mainstructure.css">
		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/css/maincontent.css">
		<!-- Jquery and Jquery UI -->

		<script type="text/javascript"
			src="<%=basePath%>javascript/jquery-1.7.2.min.js">
</script>

		<script type="text/javascript"
			src="<%=basePath%>javascript/calendar/js/jquery-ui-1.8.6.custom.min.js">
</script>

		<script type="text/javascript"
			src="<%=basePath%>javascript/calendar/js/jquery-ui-timepicker-addon.js">
</script>

		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">

		<!-- 表单验证CSS -->
		<script
			src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine.js"
			type="text/javascript">
</script>
		<script
			src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine-en.js"
			type="text/javascript">
</script>
		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/js/formValidator/css/validationEngine.jquery.css"
			type="text/css" media="screen" charset="utf-8" />

		<!-- FullCalender -->

		<link rel='stylesheet' type='text/css'
			href='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.css' />
		<link
			href='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.print.css'
			rel='stylesheet' media='print' />
		<script type='text/javascript'
			src='<%=basePath%>javascript/calendar/fullcalendar/fullcalendar.min.js'>
</script>


		<!-- 
		<link rel="stylesheet" href="jquery/jquery-ui-1.10.2.custom.min.css"
			type="text/css" />
		<link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
		<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet'
			media='print' />
		<script src='jquery/jquery-1.9.1.min.js'>
</script>
		<script src='jquery/jquery-ui-1.10.2.custom.min.js'>
</script>
		<script src='fullcalendar/fullcalendar.min.js'>
</script> -->
		<script>

$(document).ready(function() {

			//更改表单的验证方式(键盘按键起来时验证)(默认提交表单时验证)
				//$("#reserveformID").validationEngine( {
				//validationEventTriggers : "submit",
				//openDebug : true
				//});

				var date = new Date();
				var fstart;
				var fend;
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();

				$("#reserveformID").validationEngine( {
					validationEventTriggers : "keyup blur",
					openDebug : true
				});

				$("#start2").timepicker( {
					dateFormat : 'yyyy-MM-dd',
					timeFormat : 'hh:mm:ss',
					hourMin : 5,
					hourMax : 24,
					hourGrid : 3,
					minuteGrid : 15
				});
				$("#end2").timepicker( {
					dateFormat : 'yyyy-MM-dd',
					timeFormat : 'hh:mm:ss',
					hourMin : 5,
					hourMax : 24,
					hourGrid : 3,
					minuteGrid : 15
				});

				var calendar = $('#calendar')
						.fullCalendar(
								{
									//头部显示的信息，分left , center, right三个部位
									//合法的属性值：title,prev,next,prevYear,nextYear,today, avaibleViewName
									header : {
										left : 'prevYear,nextYear, prev,next, today',
										center : 'title',
										right : 'month,agendaWeek,agendaDay'
									},

									//当为true时，可以配合JQUERY-UI，配置日历的皮肤
									theme : false,

									//每周开始的日期：0为周日
									firstDay : 0,

									//是否从右至左组织日历(没有效果)
									isRLT : false,

									//是否显示周六、周日
									weekends : true,

									//周的显示模式(fixed、liquid、variable)
									weekMode : 'liquid',

									//侧边栏显示第几周
									weekNumbers : true,

									//整个日历的高度（包括header和content）
									//height : 650,

									//内容高度
									//contentHeight : 600,

									//宽和高的比例(数值越小比例越大)
									//aspectRatio : 0,

									//日历初始化时的视图(month、basicWeek、basicDay、agendaWeek、agendaDay)，默认为month(注意单引号'month')
									defaultView : 'month',

									//////////////////议程相关——控制日程相关的视图的显示信息
									//是否显示全天日程
									allDaySlot : true,

									//显示的文字
									allDayText : '所有安排',

									//日期显示的格式
									axisFormat : 'H:mm',

									//间隔时间
									slotMinutes : 30,

									//拖动时显示的时间间隔
									snapMinutes : 1,

									//没有效果
									//defaultEventMinutes : 100,
									//在日程view里可见的起始时间，可通过滚动条滚动到在此时间之前的时间
									firstHour : 7,

									//每个视图列名显示的格式
									columnFormat : {
										month : 'dddd', // Mon
										week : 'dddd M/d', // Mon 9/7
										day : 'dddd M/d' // Monday 9/7
									},
									//每个视图里title显示的格式
									titleFormat : {
										month : 'yyyy年MMMM', // 2013年四月
										week : "yyyy年MMMMd号[yyyy]{ '&#8212;'[MMMM] d号 }", // 2013年四月7号 — 13号
										day : 'yyyy年,MMMMd号,dddd' // 2013年,四月8号,星期一
									},
									//视图里每个button显示的文字
									buttonText : {
										prev : '&lt;',
										next : '&gt;',
										prevYear : '去年',
										nextYear : '明年',
										today : '今天',
										month : '月',
										agendaWeek : '周',
										agendaDay : '日'
									},
									//月的全称
									monthNames : [ '一月', '二月', '三月', '四月',
											'五月', '六月', '七月', '八月', '九月', '十月',
											'十一月', '十二月' ],
									//星期的全称
									dayNames : [ '星期日', '星期一', '星期二', '星期三',
											'星期四', '星期五', '星期六' ],
									//第几周显示的文字
									weekNumberTitle : '周',

									//================================描绘日历数据==========
									//填充日历数据(json)
									events : function(start, end, callback) {
										$
												.ajax( {
													url : 'internalOrder_findAllIodForJson.action',
													dataType : 'json',
													data : {
														start : $.fullCalendar
																.formatDate(
																		start,
																		"yyyy-MM-dd HH:mm:ss"),
														end : $.fullCalendar
																.formatDate(
																		end,
																		"yyyy-MM-dd HH:mm:ss")
													},
													cache : false,//防止数据缓存
													success : function(doc) {
														var events = [];
														$(doc)
																.each(
																		function() {
																			events
																					.push( {
																						fkId : $(
																								this)
																								.attr(
																										'id'),
																						id : $(
																								this)
																								.attr(
																										'id'),
																						title : $(
																								this)
																								.attr(
																										'pieceNumber')+" 交货量:"+$(
																								this)
																								.attr(
																										'num'),
																						start : $(
																								this)
																								.attr(
																										'touchanDate'),
																						end : $(
																								this)
																								.attr(
																										'paymentDate'),
																						thingContent : "件号:"+$(
																								this)
																								.attr(
																										'pieceNumber')+"<br/>计划数量:"+ $(
																								this)
																								.attr(
																										'num')+"<br/>交付日期:"+ $(
																								this)
																								.attr(
																										'touchanDate')+"--"+ $(
																								this)
																								.attr(
																										'paymentDate'),
																					});
																		});
														callback(events);
													},
													error : function() {
														alert('there was an error while fetching events!');
													},
													color : 'red', // a non-ajax option
													textColor : 'black' // a non-ajax option
												});
									},

									//==================================相关点击事件===================================================
									//(callback)函数回调，每次view显示时均会调用
									viewDisplay : function(event, element) {
										//格式化时间
										fstart = $.fullCalendar.formatDate(
												event.start,
												"yyyy-MM-dd HH:mm:ss");
										fend = $.fullCalendar.formatDate(
												event.end,
												"yyyy-MM-dd HH:mm:ss");
										//alert(fstart);
										//alert(fend);
										//console.log(view.visStart);
										//alert(view.start);
										//alert(view.title + view.d);
										//alert(event);
										///$('#calendar').fullCalendar('updateEvent', event);
										//alert(123);
									},
									//当日历描绘(填充每个数据的时候)时触发
									eventRender : function(event, element) {
										//alert(1);
										//element.html('<a href=#>'+ fstart+ "-"+ fend+ '<div style=color:#E5E5E5>'+ event.title+ "</div></a>");
									},
									//当日历描绘(填充每个数据的时候)eventRender处理后触发
									eventAfterRender : function(event, element) {
										//alert(2);
									},
									//当某天被点击时触发
									dayClick : function(date, allDay, jsEvent,
											view) {
										//判断显示明细的对话框是否在打开
										if ($("#reserveinfo").dialog("isOpen")) {
											$("#reserveinfo").dialog("destroy");//销毁明细对话框
										}
										;
										//判断添加的对话框是否在打开
										if ($("#reservebox").dialog("isOpen")) {
											var titleVal = $("#title").val();
											if (titleVal != "") {
												if (window
														.confirm("您的活动尚未保存,确定要舍弃吗?") == false) {
													return false;
												}
											}
											//重置表单
											$("#reserveformID")[0].reset();
											$("#thingTypeDiv").hide();
											$("#repeatFrequencyDiv").hide();
											$("#reservebox").dialog("destroy");//销毁添加的对话框
										}
										;
										//选中的时间
										var selectdate = $.fullCalendar
												.formatDate(date, "yyyy-MM-dd");
										var x = event.clientX;//x坐标
										var y = event.clientY;//y坐标
										$("#start").val(selectdate);//为开始时间赋值
										$("#end").val(selectdate);//为开始时间赋值

										//jquery弹出对话框插件(dialog)
										$("#reservebox")
												.dialog(
														{
															title : selectdate,
															modal : false,//模态对话框，若为是，则不可操作背景层。
															autoOpen : false,//这个属性为true的时候dialog被调用的时候自动打开dialog窗口。当属性为false的时候，一开始隐藏窗口，知道.dialog("open")的时候才弹出dialog窗口。默认为：true。
															closeOnEscape : true, //为true的时候，点击键盘ESC键关闭dialog，默认为true;(ie不支持)
															hide : 'slide',//关闭效果
															show : 'show',//打开效果
															draggable : true,//是否可拖动
															resizable : true,//是否可改变大小
															//height : 270,//高度
															//minHeight : 270,//最小高度
															width : 355,//宽度
															minWidth : 355,//最小宽度
															position : [
																	x - 200,
																	y - 500 ],//使窗口跟随鼠标移动

															//关闭之前的方法
															beforeClose : function(
																	event, ui) {
																var titleVal = $(
																		"#title")
																		.val();
																if (titleVal != "") {
																	if (window
																			.confirm("您的活动尚未保存,确定要舍弃吗?") == false) {
																		return false;
																	}
																}
																;
																//重置表单
																$("#reserveformID")[0]
																		.reset();
																//清空验证提示信息
																$.validationEngine
																		.closePrompt("#title");
																$.validationEngine
																		.closePrompt("#start");
																$.validationEngine
																		.closePrompt("#end");
																//隐藏date2
																$("#start2")
																		.hide();
																$("#end2")
																		.hide();

															},
															buttons : {
																"关闭" : function() {
																	$(this)
																			.dialog(
																					"close");
																},
																"添加" : function() {
																	if ($(
																			"#reserveformID")
																			.validationEngine(
																					{
																						returnIsValid : true
																					})) {
																		$(
																				"#reserveformID")
																				.submit();
																	}
																}
															}
														});

										//是否重复处理
										$("#isRepeat")
												.bind(
														"click",
														function() {
															if ($("#isRepeat")
																	.attr(
																			'checked')) {
																$(
																		"#repeatFrequency")
																		.empty();//清空下拉框
																//添加option(生成30个重复周期)
																for ( var i = 1; i <= 30; i++) {
																	$(
																			"<option value='"
																					+ i
																					+ "'>"
																					+ i
																					+ "</option>")
																			.appendTo(
																					"#repeatFrequency");
																}
																$(
																		"#thingTypeDiv")
																		.show(
																				'slow');
																$(
																		"#repeatFrequencyDiv")
																		.show(
																				'slow');

															} else {
																$(
																		"#thingTypeDiv")
																		.hide(
																				'slow');
																$(
																		"#repeatFrequencyDiv")
																		.hide(
																				'slow');
															}
														});

										//是全天处理
										$("#allDay").bind("click", function() {
											if ($("#allDay").attr("checked")) {
												$("#start2").hide("slow");
												$("#end2").hide("slow");
												$("#start2").val();
												$("#end2").val();
											}
										});
										//不是全天处理
										$("#allDay2").bind("click", function() {
											if ($("#allDay2").attr("checked")) {
												$("#start2").show('slow');
												$("#end2").show('slow');
												$("#start2").val("07:30:00");
												$("#end2").val("16:15:00");
											}
										});

										//查询所有的部门
										$
												.ajax( {
													url : 'DeptNumberAction!findAllDept.action',
													dataType : 'json',
													cache : false,//防止数据缓存
													success : function(allDdept) {
														$("#dept").empty();
														$("<option></option>")
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
										//级联查询出部门所对应的所有人员
										$("#dept")
												.bind(
														"change",
														function() {
															if ($("#dept")
																	.val() != "") {
																$
																		.ajax( {
																			url : "UsersAction!findUsersByDept.action",
																			type : 'post',
																			dataType : 'json',
																			contentType : "application/x-www-form-urlencoded; charset=utf-8",
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
																				//$("<option></option>").appendTo("#users");
																				$(
																						useradsfa)
																						.each(
																								function() {
																									$(
																											"<option value='"
																													+ this.id
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

										//打开添加对话框
										$("#reservebox").dialog("open");
										return false;
									},
									//当一个事件给点击时触发
									eventClick : function(event) {
										//判断显示明细的对话框是否在打开
										if ($("#reserveinfo").dialog("isOpen")) {
											$("#reserveinfo").dialog("destroy");//销毁明细对话框
										}
										;
										//判断添加的对话框是否在打开
										if ($("#reservebox").dialog("isOpen")) {
											var titleVal = $("#title").val();
											if (titleVal != "") {
												if (window
														.confirm("您的活动尚未保存,确定要舍弃吗?") == false) {
													return false;
												}
											}
											;
											//重置表单
											$("#reserveformID")[0].reset();
											$("#thingTypeDiv").hide();
											$("#repeatFrequencyDiv").hide();
											$("#reservebox").dialog("destroy");//销毁添加的对话框
										}
										;

										var fstart = $.fullCalendar
												.formatDate(event.start,
														"yyyy/MM/dd HH:mm");
										var fend = $.fullCalendar.formatDate(
												event.end, "yyyy/MM/dd HH:mm");
										var fkId = event.fkId;
										var x = event.clientX; //x坐标
										var y = event.clientY; //y坐标
										//删除事件请求方法
										function delfunction(isRepeat) {
											$
													.ajax( {
														url : "CalendarAction!delCalendar.action",
														dataType : 'json',
														data : {
															id : fkId,
															isRepeat : isRepeat
														},
														cache : false,//防止数据缓存
														success : function(
																message) {
															if (message == "true") {
																if (event.isRepeat == "yes") {
																	window.location
																			.reload();
																} else {
																	$(
																			"#calendar")
																			.fullCalendar(
																					'removeEvents',
																					[ event.id ]);
																	$(
																			"#reserveinfo")
																			.dialog(
																					"destroy");//销毁明细对话框
																	$(
																			"#repeatThings")
																			.dialog(
																					"destroy");//销毁选择重复对话框
																}
															} else {
																alert(message);
															}
														}
													});
										}
										;
										//事件明细对话框
										$("#reserveinfo")
												.dialog(
														{
															modal : false,//模态对话框，若为是，则不可操作背景层。
															autoOpen : false,//这个属性为true的时候dialog被调用的时候自动打开dialog窗口。当属性为false的时候，一开始隐藏窗口，知道.dialog("open")的时候才弹出dialog窗口。默认为：true。
															closeOnEscape : true, //为true的时候，点击键盘ESC键关闭dialog，默认为true;(ie不支持)
															hide : 'slide',//关闭效果
															show : 'show',//打开效果
															draggable : true,//是否可拖动
															resizable : true,//是否可改变大小
															width : 360,//宽度
															minWidth : 360,//最小宽度
															position : [
																	x - 360,
																	y - 300 ],//使窗口跟随鼠标移动
															buttons : {
																"关闭" : function() {
																	$(this)
																			.dialog(
																					"close");
																},
																"修改" : function() {
																	alert("无法修改");
																},
																"删除" : function() {
																	var isRepeat;
																	if (event.isRepeat == "yes") {
																		$(
																				"#repeatThings")
																				.dialog(
																						{
																							title : "删除周期性活动",
																							autoOpen : true,
																							draggable : true,
																							modal : true,
																							hide : 'slide',//关闭效果
																							show : 'show',//打开效果
																							width : 360,
																							minWidth : 560,
																							buttons : {
																								"关闭" : function() {
																									$(
																											this)
																											.dialog(
																													"close");
																								}
																							}
																						});
																		$(
																				"#delOne")
																				.bind(
																						"click",
																						function() {
																							delfunction("delOne");
																						});
																		$(
																				"#delAfter")
																				.bind(
																						"click",
																						function() {
																							delfunction("delAfter");
																						});
																		$(
																				"#delAll")
																				.bind(
																						"click",
																						function() {
																							delfunction("delAll");
																						});
																		$(
																				"#repeatThings")
																				.dialog(
																						"open");

																	} else {
																		if (window
																				.confirm("确定要删除该事件吗?")) {
																			isRepeat = "delOne";
																			delfunction(isRepeat);
																		}
																	}
																}
															}
														});

										var showtopic = '';
										if (event.title.length > 15) {
											showtopic = event.title.substring(
													0, 15) + '...';
										} else {
											showtopic = event.title;
										}
										var thingContent = event.thingContent == null ? ""
												: event.thingContent;

										$("#revdesc")
												.html(
														'<div style="font-weight:bold;color:#5383c2;border-bottom: 1px dotted #5383c2; padding: 3px 0px 3px;">'
																+ showtopic
																+ "</div>"
																+ "<div style='margin-top:10px;' align='left'><b>事件类型:</b> "
																+ event.thingType
																+ "</div>"
																+ "<div style='margin-top:10px;' align='left'><b>内容:</b><br/>"
																+ thingContent
																+ "</div>");
										$("#reserveinfo").dialog(
												{
													title : fstart + "-" + fend
															+ " " + showtopic
												});
										$("#reserveinfo").dialog("open");
										return false;
									},
									//鼠标悬浮事件上时显示
									eventMouseover : function(calEvent,
											jsEvent, view) {
										var fstart = $.fullCalendar.formatDate(
												calEvent.start,
												"yyyy/MM/dd HH:mm");
										var fend = $.fullCalendar.formatDate(
												calEvent.end,
												"yyyy/MM/dd HH:mm");
										$(this)
												.attr(
														'title',
														fstart
																+ " - "
																+ fend
																+ " "
																+ calEvent.title
																+ " : "
																+ calEvent.thingContent);
										$(this).css('font-weight', 'normal');
									},
									//鼠标离开事件上时处理
									eventMouseout : function(calEvent, jsEvent,
											view) {
										$(this).css('font-weight', 'normal');
									},

									//是否选中对应元素
									selectable : true,

									//在日程表相关的view里，当选中对应时刻时，是否显示相关信息
									selectHelper : true,

									//被选中时的函数回调
									select : function(startDate, endDate,
											allDay, jsEvent, view) {
									},
									//当点击页面日历以外的位置时，是否自动取消当前的选中(默认true)
									unselectAuto : true,

									//选中被取消时的回调
									unselect : function(view, jsEvent) {
										$(this).css('border-color', 'blue');
									},

									//是否允许拖动事件
									editable : true,
									//页面加载开始和加载结束
									loading : function(bool) {
										if (bool) {
											$('#loading').show();
										} else {
											$('#loading').hide();
										}
									}

								});

			});
</script>
		<style>
body {
	margin-top: 0px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#calendar {
	width: 980px;
	margin: 0 auto;
}

#loading {
	position: absolute;
	top: 5px;
	right: 5px;
	color: #ffffff;
	background-color: #c44;
	font-size: 10px;
}

.tooltip {
	PADDING-BOTTOM: 25px;
	PADDING-LEFT: 25px;
	WIDTH: 160px;
	PADDING-RIGHT: 25px;
	DISPLAY: none;
	BACKGROUND: url(images/black_arrow.png);
	HEIGHT: 70px;
	COLOR: #fff;
	FONT-SIZE: 12px;
	PADDING-TOP: 25px;
	z-order: 100
}

.table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #000;
	border-width: 1px 0 0 1px;
	width: 99%;
}

.table th,.table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body>
		<center>
			<div align="left">
				<table class="table" >
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							产品名称
						</td>
						<td align="center">
							计划数量
						</td>
						<td align="center">
							计划投产日期
						</td>
						<td align="center">
							计划交付日期
						</td>
						<td align="center">
							入库数量
						</td>
					</tr>
					<s:iterator value="iodList" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="left">
							${pageList.pieceNumber }
						</td>
						<td>
							${pageList.ywMarkId}
						</td>
						<td align="left">
							${pageList.name}
						</td>
						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.touchanDate}
						</td>
						<td>
							${pageList.paymentDate}
						</td>
						<td>
							${pageList.quantityCompletion}
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage != null">
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:if>
					</tr>
				</table>
			</div>
			<div id="all" style="width: 100%">
				<%--<div id="logo" align="left"
					style="width: 980px; height: 150px; border: solid 1px #0170b8; background: url('<%=basePath%>images/tasklogoOK.jpg'); margin-top: 38px;">
				</div>
				--%>
				<div id="gongneng"
					style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
					<div id='loading' style='display: none'>
						正在加载...
					</div>
					<!-- 显示整个日历 -->
					<div id='calendar'></div>
					<!-- 点击某个事件时显示的对话框 -->
					<DIV id=reserveinfo>
						<DIV id=revtitle></DIV>
						<DIV id=revdesc></DIV>
					</DIV>
					<!-- 点击某个日期格子时显示的对话框 -->
					<DIV style="DISPLAY: none;" id="reservebox">
						<FORM action="CalendarAction!addCalendar.action"
							id="reserveformID" style="padding: 0px; margin: 0px;"
							method="post">
							<DIV class="rowElem" align="left">
								<LABEL for="title">
									标题:
								</LABEL>
								<INPUT id="title" name="calendar.title"
									class="validate[required]" />
								<font color="red">*</font>
							</DIV>
							<DIV class="rowElem" align="left" style="height: 70px;">
								<LABEL for="content">
									内容:
								</LABEL>
								<textarea rows="4" cols="45" id="content"
									style="margin: 0px; padding: 0px;" name="calendar.thingContent"></textarea>
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="thingType">
									所属人:
								</LABEL>
								<select id="dept" style="width: 100px; float: left;">
									<option></option>
								</select>
								<select id="users" style="width: 100px; float: left;"
									name="calendar.userId">
								</select>
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="thingType">
									事件类型:
								</LABEL>
								<select id="thingType" style="width: 100px;"
									name="calendar.thingType">
									<option></option>
									<option value="工作计划">
										工作计划
									</option>
									<option value="法定假">
										法定假
									</option>
									<option value="年休假">
										年休假
									</option>
									<option value="换休">
										换休
									</option>
									<option value="公假">
										公假
									</option>
									<option value="病假">
										病假
									</option>
									<option value="婚假">
										婚假
									</option>
									<option value="丧假">
										丧假
									</option>
									<option value="事假">
										事假
									</option>
									<option value="双休日">
										双休日
									</option>
								</select>
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="start">
									开始时间:
								</LABEL>
								<INPUT id="start"
									class="validate[required,funcCall[validate2time]]"
									name="calendar.start"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									style="width: 80px;" />
								<font color="red">*</font>
								<INPUT id="start2" name="calendar.start"
									style="width: 80px; display: none;" />

							</DIV>
							<DIV class="rowElem" align="left" />
								<LABEL for="end">
									结束时间:
								</LABEL>
								<INPUT id="end" name="calendar.endDate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									style="width: 80px;" />
								<INPUT id="end2" name="calendar.endDate"
									style="width: 80px; display: none;" />
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="allDay">
									是否全天:
								</LABEL>
								<input id="allDay" type="radio" value="true"
									name="calendar.allDay" checked="checked"
									style="float: left; width: 20px;" />
								<span style="float: left; width: 40px;">是</span>
								<input id="allDay2" type="radio" value="false"
									name="calendar.allDay" style="float: left; width: 40px;" />
								<span style="float: left; width: 40px;">否</span>
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="thingStatus">
									事件性质:
								</LABEL>
								<input id="thingStatus" type="radio" value="private"
									name="calendar.thingStatus" checked="checked"
									style="float: left; width: 20px;" />
								<span style="float: left; width: 40px;">私人</span>
								<input type="radio" value="public" name="calendar.thingStatus"
									style="float: left; width: 40px;" />
								<span style="float: left; width: 40px;">公开</span>
							</DIV>
							<DIV class="rowElem" align="left">
								<LABEL for="isRepeat">
									是否重复:
								</LABEL>
								<input id="isRepeat" name="calendar.isRepeat" type="checkbox"
									value="yes" style="width: 20px" />
							</DIV>
							<DIV id="thingTypeDiv" class="rowElem" align="left"
								style="display: none;">
								<LABEL for="repeatCycle">
									重复周期:
								</LABEL>
								<select id="thingType" style="width: 60px;"
									name="calendar.repeatCycle">
									<option value="day">
										每天
									</option>
									<option value="week">
										每周
									</option>
									<option value="month">
										每月
									</option>
									<option value="year">
										每年
									</option>
								</select>
							</DIV>
							<DIV id="repeatFrequencyDiv" class="rowElem" align="left"
								style="display: none;">
								<LABEL for="repeatFrequency">
									重复频率:
								</LABEL>
								<select id="repeatFrequency" name="calendar.repeatFrequency"
									style="width: 50px;">
									<option value="1">
										1
									</option>
								</select>
							</DIV>
						</FORM>
					</DIV>
					<div id="repeatThings" style="display: none;" align="left">
						您想仅删除这一系列中的此活动、所有活动，还是系列中的这一活动及将来所有活动？
						<br />
						<br />
						<input id="delOne" type="button" value="仅此次活动"
							style="width: 150px; height: 30px;" />
						此系列中的其他所有活动均会保留。
						<br />
						<br />
						<input id="delAfter" type="button" value="所有后续活动"
							style="width: 150px; height: 30px;" />
						此活动和所有后续活动均会被删除。
						<br />
						<br />
						<input id="delAll" type="button" value="此系列中的所有所动"
							style="width: 150px; height: 30px;" />
						此系列中的所有活动均会被删除。
						<br />
						<br />
					</div>
				</div>
				<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
