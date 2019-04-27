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
    <base href="<%=basePath%>">
    
    <title>E-DESK</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<meta charset="utf-8">
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
	<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8;">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="System/edesk/css/desk.css">
	<script src="System/edesk/js/jquery.js"></script> 
	<script src="System/edesk/js/calendar.js"></script>
	
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<%--        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script> --%>
<%--        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script> --%>
<%--        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script> --%>
<%--        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script> --%>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
     
<link rel="stylesheet" type="text/css" href="System/edesk/css/calendar.css">
<style type="text/css">
.skillbar {
	position:relative;
	display:block;
	margin-bottom:15px;
	background:#eee;
	height:15px;
	-webkit-transition:0.4s linear;
	-moz-transition:0.4s linear;
	-ms-transition:0.4s linear;
	-o-transition:0.4s linear;
	transition:0.4s linear;
	-webkit-transition-property:width, background-color;
	-moz-transition-property:width, background-color;
	-ms-transition-property:width, background-color;
	-o-transition-property:width, background-color;
	transition-property:width, background-color;
}



.skillbar-bar {
	height:15px;
	width:0px;
}

.skill-bar-percent {
	position:absolute;
	right:10px;
	top:0;
	font-size:11px;
	height:15px;
	line-height:35px;
	color:#ffffff;
	color:rgba(0, 0, 0, 0.4);
	margin-top: -13px;
}

</style>
  </head>
   
  <body style="height: 100%;">
  <div id="box">
  
  <!-- 上 -->
  <div style="width: 40%;">
  <!-- 上左 -->
  <div style="width: 20%;float: left;margin-left: 30px;margin-top: 20px;">
  <div><img src="System/edesk/img/weixxing.png" ></div>
  <div>崴 蕊</div>
  <div><u>项目总监</u></div>
  <div style="background-color: #C7D2DD;">工作累计<br/>2098小时</div>
  <div style="background-color:#C7D2DD;margin-top: 4px; ">积分<p style="color: red;">256</p></div>
  </div>
  <!-- 上中 -->
  <div style="float: left;margin-left: 300px;margin-top: -300px;">
  
  <div style="background-color: #F2F2F2;width: 400px;">
  <h3 style="color: #FF0000;">关键目标</h3>1.3月5日完成易-K系统简化设计<br/>
  	                                       1.3月5日完成易-K系统简化设计</div>
  <div style="width: 110px;height:110px;background-color: #D9D9D9;margin-top: 30px;color: red;font-size: 70px;"align="center" class="date"></div>
  <div class="month" align="left" style="font-size: 20px;float:left;"></div>
  <div style="margin-left: 100px;margin-top:-100px; float:left;"><div style="color:#FFFF34;font-size:20px;. ">今日待办:</div><u>1.税务局付款  指派人：系统</u><br/><u>2.检查卫生      指派人：贾辉辉</u></div>
  </div>
  <!-- 上右 -->
  <div id="demo" style="float:rights;margin-top: -10px;margin-left: 859px; ">
  <div><h3 align="center">月度待办一览表</h3></div>
 
  <div id="ca" align="center"></div>

  </div>
  
  </div>
  
  <!-- 中 -->
  <div>
  <!-- 中左 -->
  <div>
  
  <div style="margin-left: 30px;margin-top: -170px;" class="zhaodai">
    <div id="box1"></div>
 	<div id="box2" align="center" style="color:#FFFFFF;font-size:14px;">招待</div>
 	<div id="box3"></div> 
 </div>
 <div style="margin-left: 30px;margin-top: 4px;">
    <div id="aox1"></div>
 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:13px;">借款、还款</div>
 	<div id="aox3"></div> 
 </div>
 	<div style="margin-left: 10px;margin-top: -5px;">
 	 <div id="wox1"></div>
 	<div id="wox2" align="center" style="color:#FFFFFF;font-size:13px;">请假</div>
 	<div id="wox3"></div> 
 	</div>
 	
 	 <div style="margin-left:30px;margin-top: -7px;">
 	 <div id="qox1"></div>
 	<div id="qox2" align="center" style="color:#FFFFFF;font-size:13px;">购买</div>
 	<div id="qox3"></div> 
 	
 	<div style="margin-left: -23px;margin-top: -8px;">
 	 <div id="eox1"></div>
 	<div id="eox2" align="center" style="color:#FFFFFF;font-size:13px;">出差</div>
 	<div id="eox3"></div> 
 	</div>
 	
 	<div style="margin-top: -8px;">
 	 <div id="rox1"></div>
 	<div id="rox2" align="center" style="color:#FFFFFF;font-size:13px;">报告</div>
 	<div id="rox3"></div> 
 	</div>
 	
 	 	<div style="margin-left: 25px;margin-top: -8px;">
 	 <div id="fox1"></div>
 	<div id="fox2" align="center" style="color:#FFFFFF;font-size:13px;">其他</div>
 	<div id="fox3"></div> 
 	</div>

  </div>
  <!-- 二中 -->
  <div  style="float: left;">
	<div id="container" style="height: 820px;;width: 800px;"></div>
	
  </div>
  
  
  
 <!--中右 -->
  <div>
  
  </div>
  
  
		
  </div>
  
 
  
  
  
  </div>
  
    <script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;

option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['节点总超时长', '进行时长','标准时长']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['报销','项目1','招聘','领用']
    },
    series: [
        {
            name: '节点总超时长',
            type: 'bar',
            data: [50, 1, 80, 30],
            itemStyle:{
                                    normal:{
                                        color:'#FDA844'
                                    }
                                },
        },
        {
            name: '进行时长',
            type: 'bar',
            data: [120, 72, 170, 120],
                        itemStyle:{
                                    normal:{
                                        color:'#003A7E'
                                    }
                                },
        },
        {
            name: '标准时长',
            type: 'bar',
            data: [90, 130, 90, 90],
                        itemStyle:{
                                    normal:{
                                        color:'#89C4EB'
                                    }
                                },
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
  <!-- 上右日历 -->
     <script type="text/javascript">       
      
     jQuery(document).ready(function(){
	jQuery('.skillbar').each(function(){
		jQuery(this).find('.skillbar-bar').animate({
			width:jQuery(this).attr('data-percent')
		},6000);
	});
});
 var date = new Date();
 //月
 var month = date.getMonth() + 1;
 //天
 var strDate = date.getDate();
 $(".date").text(strDate);
 
  if(month==1){
 $(".month").text("一月");
 } else if(month==2){
 $(".month").text("二月");
 } else if(month==3){
 $(".month").text("三月");
 } else if(month==4){
 $(".month").text("死月");
 } else if(month==5){
 $(".month").text("五月");
 } else if(month==6){
 $(".month").text("六月");
 } else if(month==7){
 $(".month").text("七月");
 } else if(month==8){
 $(".month").text("八月");
 } else if(month==9){
 $(".month").text("九月");
 } else if(month==10){
 $(".month").text("十月");
 } else if(month==11){
 $(".month").text("十一月");
 } else if(month==12){
 $(".month").text("十二月");
 }
   $('#ca').calendar({
        data: [
			{
			  date: '2015/12/24',
			  value: 'Christmas Eve'
			},
			{
			  date: '2015/12/25',
			  value: 'Merry Christmas'
			},
			{
			  date: '2016/01/01',
			  value: 'Happy New Year'
			}
		],

    });

  
  </script> 
  
  </body>
</html>
