<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<%-- 		<%@include file="/util/sonHead.jsp"%> --%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
<%-- 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/jquery.js"></script> --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/jquery.ui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/gantt/DateSlider.js"></script>
		<script type="text/javascript">
			//现在window.$和window.jQuery是3.2.1版本:
// 			console.log($().jquery); // => '3.2.1'
// 			var $jq = jQuery.noConflict(true);
			//现在window.$和window.jQuery被恢复成1.5版本:
// 			console.log($().jquery); // => '1.5.0'
		</script>
		<style type="text/css">
			.ui-ruler-tick {
	border-left: 1px solid #C1C8DF;
	margin-left: 1px;
	height: 12px;
}
/*月份文字*/
.ui-ruler-tick-inner {
	position: absolute;
	top: 12px;
	margin-right: 1px;
}
/*时间轴*/
.ui-rangeSlider .ui-rangeSlider-innerBar {
	height: 5px;
	margin: 0px;
	padding: 0px;
	background: #8D98BC;
}

.ui-rangeSlider .ui-rangeSlider-bar {
	height: 5px;
	margin: 0px;
	padding: 0px;
	background: transparent url(../images/dateSilderMove.png) repeat-x;
	cursor:pointer;
}

/*拖拽按钮*/
.ui-rangeSlider .ui-rangeSlider-handle {
	width: 18px;
	height: 31px;
	background: #DDDDDD url(../images/dateSilderArr.png) no-repeat;
	margin-top: -15px;
	cursor: pointer;
}

/*文字*/
.ui-rangeSlider-label {
	bottom: 15px;
	margin: 0px 0px 2px;
	background: #F3F9EF;
	height: 12px;
	cursor: pointer;
}
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="productEBAction!settingTarget.action" method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						设置目标日人均仓
					</h2>
					<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">班组</span>
	                        <input class="form-control" type="text" name="pebUsers.banZu" value="${banzu}">
	                    </div>
	                </div>
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标日人均仓</span>
	                        <input class="form-control" type="text" name="pebUsers.mbTarget"
	                        	 value="${pebUsers.mbTarget }" id="dangAnNum">
	                    </div>
	                </div>
	            </div>
	            <div class="row clearfix">
		            <div class="form-group col-lg-12">
<!-- 		                <div class="input-group"> -->
							<label for="">设置日期</label>
							<div class="dateSlider" id="dateSlider" class="col-lg-10">&nbsp;</div>
<!-- 			            </div> -->
			        </div>
				</div>
				<br>
				<br>
				<div class="form-group">
					<label for="">
						备注
					</label>
					<textarea class="form-control" rows="3"
						name="pebUsers.remarks" >${pebUsers.remarks}</textarea>
				</div>
				
				</br>
				<input type="hidden" name="goYear">
				<input type="hidden" name="goMonth">
				<input type="hidden" name="goDay">
				<input type="hidden" name="toYear">
				<input type="hidden" name="toMonth">
				<input type="hidden" name="toDay">
				<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
					提交
				</button>
			</div>
		</form>
	</div>
	</body>	
	<script type="text/javascript">
	var Months = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
   	$(function(){
		createDemos();		
	});
	function createDemos(){		
		var	date = $("<div id='date' />").appendTo($("#dateSlider"));//渲染日期组件
		
		var dates=new Date;
		 var year=dates.getFullYear(); 
		 var month=dates.getMonth()+1;
		 var day = dates.getDate();
		 //month =(month<10 ? "0"+month:month); 
		 
		var dateSilderObj=date.dateRangeSlider({
			arrows:false,//是否显示左右箭头
			bounds: {min:new Date(year,month-3,day),max:new Date(year+1,month+9,30)
// 				min:function(){
// 					return new Date(2018,2,1);
// 				},
// 				max:function(){
// 					return new Date(2019, 1, 31, 12, 59, 59);
// 				}
			},//最大 最少日期
			defaultValues: {
// 				min: function(){
// 					return new Date(2018, 2, 1);
// 				}, 
// 				max:function(){
// 					return new Date(2019, 1, 30);
// 				}
			},//默认选中区域
			scales:[{
					first: function(value){return value; },
					end: function(value) {return value; },
					next: function(val){
						var next = new Date(val);
						return new Date(next.setMonth(next.getMonth() + 1));
					 },
					label: function(val){
						return Months[val.getMonth()];
					},
					format: function(tickContainer, tickStart, tickEnd){
						tickContainer.addClass("myCustomClass");
					}
			}]
		});//日期控件
		//重新赋值（整个时间轴）
		dateSilderObj.dateRangeSlider("bounds", new Date(2017,12,1), new Date(2019, 1, 31, 12, 59, 59));
		//重新赋值（选中区域）
		dateSilderObj.dateRangeSlider("values", new Date(2017, 12, 1), new Date(2019, 1, 30));
		//拖动完毕后的事件
		dateSilderObj.bind("valuesChanged", function(e, data){
			var val=data.values;
			var stime=val.min.getFullYear()+"-"+(val.min.getMonth()+1)+"-"+val.min.getDate();
			var etime=val.max.getFullYear()+"-"+(val.max.getMonth()+1)+"-"+val.max.getDate();
		  	console.log("起止时间："+stime+" 至 "+etime);
			
			$("input[name='goYear']").val(val.min.getFullYear());
			$("input[name='goMonth']").val(val.min.getMonth()+1);
			$("input[name='goDay']").val(val.min.getDate());
			$("input[name='toYear']").val(val.max.getFullYear());
			$("input[name='toMonth']").val(val.max.getMonth()+1);
			$("input[name='toDay']").val(val.max.getDate());
		});
	}
	</script>
</html>
