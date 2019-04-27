<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

		<link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/calendar/css/mainstructure.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/calendar/css/maincontent.css">
		<!-- Jquery and Jquery UI -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/calendar/js/jquery-ui-1.8.6.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/calendar/js/jquery-ui-timepicker-addon.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">
		<!-- 表单验证CSS -->
		<script src="${pageContext.request.contextPath}/javascript/calendar/js/formValidator/js/jquery.validationEngine.js"
			type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/javascript/calendar/js/formValidator/js/jquery.validationEngine-en.js"
			type="text/javascript"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/calendar/js/formValidator/css/validationEngine.jquery.css"
			type="text/css" media="screen" charset="utf-8" />
		<!-- FullCalender -->

		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/javascript/calendar/fullcalendar/fullcalendar.css' />
		<link href='${pageContext.request.contextPath}/javascript/calendar/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
		<script type='text/javascript'src='${pageContext.request.contextPath}/javascript/calendar/fullcalendar/fullcalendar.js'></script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
  			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script>  
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
<script>
$(document).ready(function() {
	$("#reserveinfo").dialog("close");//显示的模态框关闭
	$("#reserveinfo").hide();
				var date = new Date();
				var fstart;
				var fend;
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				var calendar = $('#calendar').fullCalendar({
					//头部显示的信息，分left , center, right三个部位
					//合法的属性值：title,prev,next,prevYear,nextYear,today, avaibleViewName
					header : {
						left : ' prev,next, today',
						center : 'title'
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
					weekMode : 'variable',
					//侧边栏显示第几周
					weekNumbers : true,
					//整个日历的高度（包括header和content）
					height : 450,
					//内容高度
					contentHeight : 450,
					//宽和高的比例(数值越小比例越大)
					//aspectRatio : 0,
					//日历初始化时的视图(month、basicWeek、basicDay、agendaWeek、agendaDay)，默认为month(注意单引号'month')
					defaultView : 'month',
					//////////////////议程相关——控制日程相关的视图的显示信息
					//是否显示全天日程
					allDaySlot : false,
					//显示的文字
					//allDayText : '所有安排',
					//日期显示的格式
					axisFormat : 'H:mm',
					//间隔时间
					//slotMinutes : 30,
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
						prev : '上个月',
						next : '下个月',
						today : '今天',
						year : '年',
						month : '月'
					},
					//月的全称
					monthNames : [ '一月', '二月', '三月', '四月',
							'五月', '六月', '七月', '八月', '九月', '十月',
							'十一月', '十二月' ],
					//星期的全称
					dayNames : [ '星期日', '星期一', '星期二', '星期三',
							'星期四', '星期五', '星期六' ],
					//第几周显示的文字
					//weekNumberTitle : '周',

					//================================描绘日历数据==========
					//填充日历数据(json)
					events : function(start, end, callback) {
						$.ajax( {
							url : 'banCiAction_findSchedulIngByDate.action',
							dataType : 'json',
							data : {
								startTime : $.fullCalendar.formatDate(start,"yyyy-MM-dd"),
								endTime : $.fullCalendar.formatDate(end,"yyyy-MM-dd"),
								"banCi.id":"${param.id}"
							},
							cache : false,//防止数据缓存
							success : function(doc) {
								var events = [];
								$(doc).each(function() {
									var dataType = $(this).attr("dataType");
									var remarks = $(this).attr("remarks");
									var color = "#999999";
									var title = "工作日";
									if(dataType==1){
										color = "#CC9966";
										title="公休日";
									}else if(dataType==2){
										color = "#666666";
										title="节假日";
									}
									if(remarks!=null && remarks!="" && remarks!="null"){
										title+="\n"+remarks;
									}
									
									events.push( {
										fkId : $(this).attr('id'),
										id : $(this).attr('id'),
										title : title,
										start : $(this).attr('dateTime'),
										end : $(this).attr('dateTime'),
										color : color,
										backgroundColor : color,
										remarks:remarks
									});
								});
								callback(events);
							},error:function(XMLHttpRequest, textStatus, errorThrown){
								alert("系统异常");
								//alert("错误信息：" + ); //获取的信息即是异常中的Message 
								console.log(XMLHttpRequest.responseText);
							},
							color : 'red', // a non-ajax option
							textColor : 'black' // a non-ajax option
						});
					},

					//==================================相关点击事件===================================================
					//(callback)函数回调，每次view显示时均会调用
					viewDisplay : function(event, element) {
						//格式化时间
						fstart = $.fullCalendar.formatDate(event.start,"yyyy-MM-dd");
						fend = $.fullCalendar.formatDate(event.end,"yyyy-MM-dd");
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
					dayClick : function(date, allDay, jsEvent,view) {
						//判断显示明细的对话框是否在打开
						if ($("#reserveinfo").dialog("isOpen")) {
							$("#reserveinfo").dialog("destroy");//销毁明细对话框
							$("#reserveinfo").dialog("close");
						}
						return false;
					},
					//当一个事件给点击时触发
					eventClick : function(event) {
						//判断显示明细的对话框是否在打开
						if ($("#reserveinfo").dialog("isOpen")) {
							$("#reserveinfo").dialog("destroy");//销毁明细对话框
							$("#reserveinfo").dialog("close");
						}

						var fstart = $.fullCalendar.formatDate(event.start,"yyyy/MM/dd");
						var fend = $.fullCalendar.formatDate(event.end, "yyyy/MM/dd");
						var fkId = event.fkId;
						var x = event.clientX; //x坐标
						var y = event.clientY; //y坐标
						//事件明细对话框
						$("#reserveinfo").dialog({
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
									$(this).dialog("close");
								},
								"修改" : function() {
									var form = new FormData(document.getElementById("updateSchedule"));
									$.ajax({
						                url:"${pageContext.request.contextPath}/banCiAction_updateSchedulTable.action",
						                type:"post",
						                data:form,
						                processData:false,
						                contentType:false,
						                async : false,
						                dataType:"json",
						                success:function(data){
						                	if(data==null || data.indexOf("true")<0){
						                		alert("修改失败："+data);
						                	}else{
												//$(this).parent().find("#banci_${id}").click();
												//$("#reserveinfo").dialog("destroy");
												//parent.$(dom1,parent.doucment).trigger('');
// 												window.location.reload();
												$("#reserveinfo").remove();
												parent.loadCalendar('${param.id}');
												return false;
						                	}
						                },error:function(){
						                }
									});
								}
							}
						});

						var showtopic =  event.title;
						var thingContent = event.thingContent == null ? "": event.thingContent;
						$("#showtopic").text(showtopic);
						$("#scheduleId").val(event.id);
						$("#showTitle").val(event.title);
						$("#remarks").val(event.remarks);
						$("#reserveinfo").dialog({title : fstart + "-" + fend+ " " + showtopic});
						$("#reserveinfo").dialog("open");
						return false;
					},
					//鼠标悬浮事件上时显示
					eventMouseover : function(calEvent,jsEvent, view) {
						var fstart = $.fullCalendar.formatDate(calEvent.start,"yyyy/MM/dd");
						var fend = $.fullCalendar.formatDate(calEvent.end,"yyyy/MM/dd");
						$(this).attr('title',fstart+ " - "+ fend+ " "+ calEvent.title);
						$(this).css('font-weight', 'normal');
					},
					//鼠标离开事件上时处理
					eventMouseout : function(calEvent, jsEvent,view) {
						$(this).css('font-weight', 'normal');
					},
					//是否选中对应元素
					selectable : true,
					//在日程表相关的view里，当选中对应时刻时，是否显示相关信息
					selectHelper : false,
					//被选中时的函数回调
					select : function(startDate, endDate, allDay, jsEvent, view) {
					},
					//当点击页面日历以外的位置时，是否自动取消当前的选中(默认true)
					unselectAuto : true,
					//选中被取消时的回调
					unselect : function(view, jsEvent) {
						$(this).css('border-color', 'blue');
					},
					//是否允许拖动事件
					editable : false,
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
	margin-top: 40px;
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
.fc-event-inner{
	height: 60px;
}
</style>
	</head>
	<body>
		<center>
			<div id="all" >
				<div id="gongneng"
					style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
					<div id='loading' style='display: none'>
						正在加载...
					</div>
					<!-- 显示整个日历 -->
					<div id='calendar'></div>
					<!-- 点击某个事件时显示的对话框 -->
					<DIV id="reserveinfo">
						<form action="" method="post" id="updateSchedule">
							<div style="font-weight:bold;color:#5383c2;border-bottom: 1px dotted #5383c2; padding: 3px 0px 3px;"
								 id="showtopic"></div>
							<div style='margin-top:10px;' align='left'>
								<b>类型:</b>
								<select name="schedulingTable.dataType" id="showTitle" class="form-control">
									<option value="0">工作日</option>
									<option value="1">公休日</option>
									<option value="2">节假日</option>
								</select>
								<input type="hidden" class="" id="scheduleId" name="schedulingTable.id"> 
							</div>
							<div style='margin-top:10px;' align='left'>
								<b>内容:</b><br/>
								<textarea class="form-control" rows="3" name="schedulingTable.remarks" id="remarks"></textarea>
							</div>
						</form>
					</DIV>
					<!-- 点击某个日期格子时显示的对话框 -->
<!-- 					<DIV style="DISPLAY: none;" id="reservebox"> -->
						
<!-- 					</DIV> -->
<!-- 					<div id="repeatThings" style="display: none;" align="left"> -->
<!-- 						您想仅删除这一系列中的此活动、所有活动，还是系列中的这一活动及将来所有活动？ -->
<!-- 						<br /> -->
<!-- 						<br /> -->
<!-- 						<input id="delOne" type="button" value="仅此次活动" -->
<!-- 							style="width: 150px; height: 30px;" /> -->
<!-- 						此系列中的其他所有活动均会保留。 -->
<!-- 						<br /> -->
<!-- 						<br /> -->
<!-- 						<input id="delAfter" type="button" value="所有后续活动" -->
<!-- 							style="width: 150px; height: 30px;" /> -->
<!-- 						此活动和所有后续活动均会被删除。 -->
<!-- 						<br /> -->
<!-- 						<br /> -->
<!-- 						<input id="delAll" type="button" value="此系列中的所有所动" -->
<!-- 							style="width: 150px; height: 30px;" /> -->
<!-- 						此系列中的所有活动均会被删除。 -->
<!-- 						<br /> -->
<!-- 						<br /> -->
<!-- 					</div> -->
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
