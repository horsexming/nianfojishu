<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>desk</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/bootstrap-datetimepicker/bootstrap.min.js"></script>
		<!-- 日历 -->
		<script src="System/edesk/js/calendar.js"></script>
		<link rel="stylesheet" type="text/css" href="System/edesk/css/calendar.css">
		<!-- 柱状图 -->
		<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
		<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <!-- 饼图 -->
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
        <!-- 堆积柱状图 -->
        
<style type="text/css">
 
	
#aox1{
	width:0;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	border-bottom: 10px solid #00417F;    
	}
#aox2{
	width: 54px;
	height: 40px;
	background-color: #00417F;
	}
#aox3{
	width:0;
	border-top: 10px solid #00417F;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	}
	
#box1{
	width:0;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	border-bottom: 10px solid #CFD1DE;    
	}
#box2{
	width: 54px;
	height: 35px;
	background-color: #CFD1DE;
	}
#box3{
	width:0;
	border-top: 10px solid #CFD1DE;
	border-left: 27px solid transparent;
	border-right: 27px solid transparent;
	}	


</style>



  </head>
  
  <body>
  
<div class="container" style="width: 100%;height: 100%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-4 column" style="height: 10%;width: 33.3%;">
				<img  src="System/edesk/img/desk.png"  id="edsk">
				</div>
				<div class="col-md-4 column" style="height: 10%;width: 33.3%;">
				
				<div style="float: left;">
				<div style="float: left;">
				<font style="font-size: 60px;" color="#E4E5E9">"</font>
				</div>
				<div>
				<p style="color: #E4E5E9">关键目标:</p>
				<font>4月26日完成人工智能算法的解析</font>
				</div>
				</div>
				
				<div>
				<div style="float: left;">
				<font style="font-size: 60px;" color="#E4E5E9">"</font>
				</div>
				<div>
				<p style="color: #E4E5E9">关键目标:</p>
				<font>6月7日完成易-系统简化设计</font>
				</div> 
				</div>

				</div>
				<div class="col-md-4 column" style="height: 10%;width: 33.3%;" align="right">
				
				<!-- 头像 -->
				<div style="float: left;margin-left: 200px;border-radius:50%;overflow:hidden;">
				 <s:if test='#session.Users.sex =="男"'>
				<img  src="upload/user/${Users.password.picture}" height="60px" width="60px" onerror="this.src='images/man.jpg'">
				</s:if>
				<s:else>
				<img  src="upload/user/${Users.password.picture}" height="60px" width="60px" onerror="this.src='images/woman.jpg'">
				</s:else>
				
				</div>
				<!-- 基础工作情况 -->
				<div>
				<div><font  style="font-size:20px;">${Users.name}     </font>项目总监</div>
				<div><font style="font-size:15px;">积分:</font>206</div>
				<div><font style="font-size:15px;">工作累计(小时):</font>2098</div>
				</div>
				
				</div>
			</div>
		</div>
	</div>
	<!-- 中部内容 -->
	<div class="row clearfix">
		<div class="col-md-6 column"  style="height:40%;width:48%;margin:5px auto;border:2px solid #EAEBEE;box-shadow:5px 5px 5px #EAEBEE,5px -5px 5px #EAEBEE,-5px 5px 5px #EAEBEE,-5px -5px 5px #EAEBEE;margin-left: 15px;">
		
		<div style="margin-top: 20px;">
		  <div style="float: left;margin-left:20px;" class="zhodai">
		    <div id="aox1"></div>
		 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:14px;">招待<img src="System/edesk/img/cf34.png"  style="width: 70%;height: 70%;" /></div>
		 	<div id="aox3"></div> 		 
		 	<input type="text" style="border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px  solid #DBDBDB;outline:none;  margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;" readonly="readonly" value="999">	
		 </div>
		 <div style="float: left;margin-left:20px;" class="money">
		    <div id="aox1"></div>
		 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:14px;">借/还款<img src="System/edesk/img/mongey.png"  style="width: 70%;height: 60%;" /></div>
		 	<div id="aox3"></div>
		 	<input type="text" style="border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px solid #DBDBDB;margin-top: -10px;outline:none;margin-left: 35px;color: #00417F;text-align:center;" readonly="readonly" value="0">		 	
		 </div>
		 <div style="float: left;margin-left:20px;" class="qingjia">
		    <div id="aox1"></div>
		 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:14px;">请假<img src="System/edesk/img/loadtime.png"  style="width: 70%;height: 70%;" /></div>
		 	<div id="aox3"></div> 		 	
		 	<input type="text" style="border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px solid #DBDBDB;outline:none;margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;" readonly="readonly" value="1">	
		 </div>
		 <div style="float: left;margin-left:20px;" class="goumai">
		    <div id="aox1"></div>
		 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:14px;">购买<img src="System/edesk/img/shop.png"  style="width: 70%;height: 70%;" /></div>
		 	<div id="aox3"></div>
		 	<input type="text" style="border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px solid #DBDBDB;outline:none;margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;" readonly="readonly" value="2">	 		 	
		 </div>
		 <div style="float: left;margin-left:20px;" class="baogao">
		    <div id="aox1"></div>
		 	<div id="aox2" align="center" style="color:#FFFFFF;font-size:14px;">报告<img src="System/edesk/img/baogao.png"  style="width: 50%;height: 70%;" /></div>
		 	<div id="aox3"></div> 
		 	<input type="text" style="border-radius:18px;width: 33px;height: 20px;background-color: #FFFFFF;border:1px solid #DBDBDB;outline:none;margin-top: -10px;margin-left: 35px;color: #00417F;text-align:center;" readonly="readonly" value="3">			 	
		 </div>
		 
		<div style="margin-left:350px;" align="center" class="addliu">
		    <div id="box1"></div>
		 	<div id="box2" align="center" style="color:#FFFFFF;font-size:20px;">+</div>
		 	<div id="box3"></div> 		 	
		 </div>
		 </div>
		 <!-- 今日代办 -->
		 <div style="margin-top: 30px;margin-left: 10px;">
		 <input type="text" readonly="readonly" value="今日待办" style="color:#FFFFFF;background-color: #88C1E4; border-color: #88C1E4;width: 70px;border-radius:10px;margin-left: 1px; border:none; text-align:center; "/>
		 </div>
		 <br/>
		 <!-- 代办内容 -->
		 <div style="float: left;">
		 <div>1.<font color="#F3A551">[指派人:系统]</font>税务局付款</div>
		  <br/>
		 <div>2.<font color="#F3A551">[指派人:贾辉辉]</font>检查卫生</div>
		 </div>
		 
		 <div align="right" style="margin-top: -5px;">
		 <img src="System/edesk/img/xiaor.png" width="26%" height="50%">
		 </div>
		</div>
		<div class="col-md-6 column" style="height:40%;width:48%;margin:5px auto;border:2px solid #EAEBEE;box-shadow:5px 5px 5px #EAEBEE,5px -5px 5px #EAEBEE,-5px 5px 5px #EAEBEE,-5px -5px 5px #EAEBEE;margin-left: 26px;">
		<!-- 天气 -->
		<div style="float: left;">
		<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=55"  width="255" height="220" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
		</div>
				<!-- 日历 -->
		<div style="margin-left: 300px;">
		<div id="ca" align="center" ></div>
		</div>
		
		</div>
	</div>
	<!-- 下部内容		 -->
	<div class="row clearfix" >
		<div class="col-md-6 column" style="height:43%;width:48%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:5px 5px 5px #EAEBEE,5px -5px 5px #EAEBEE,-5px 5px 5px #EAEBEE,-5px -5px 5px #EAEBEE;margin-left:15px; ">
		<div id="container" style="height: 100%;width: 100%;margin-left: 1px;"></div>
		</div>
		<div class="col-md-6 column">
			<div class="row clearfix" style="margin-left: 10px;">
				<div class="col-md-6 column" style="height:43%;width:45%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:5px 5px 5px #EAEBEE,5px -5px 5px #EAEBEE,-5px 5px 5px #EAEBEE,-5px -5px 5px #EAEBEE;">
				 <div id="chart" style="height: 100%;"></div>
				</div>
				<div class="col-md-6 column" style="height:43%;width:45%;margin:20px auto;border:2px solid #EAEBEE;box-shadow:5px 5px 5px #EAEBEE,5px -5px 5px #EAEBEE,-5px 5px 5px #EAEBEE,-5px -5px 5px #EAEBEE;margin-left: 60px;">
				 <div id="duiji" style="height: 100%"></div>
				</div>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
	var dom = document.getElementById("duiji");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '堆叠柱状图';

duiji = {   


    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['自有资产','负债','累计资产']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'自有资产',
            type:'bar',
            stack: '广告',
            data:[120, 132, 101, 134]
        },
        {
            name:'负债',
            type:'bar',
            stack: '广告',
            data:[220, 182, 191, 234]
        },
        {
            name:'累计资产',
            type:'bar',
            stack: '广告',
            data:[150, 232, 201, 154]
        }
        
    ]
};
;
if (duiji && typeof duiji === "object") {
    myChart.setOption(duiji, true);
}
	
	
	var dom = document.getElementById("chart");
var myChart = echarts.init(dom);
var app = {};
fenxi = null;
fenxi = {
    title : {
        text: '功效分析',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['有效时长','离岗时长','休息时长']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'有效时长'},
                {value:360, name:'离岗时长'},
                {value:1345, name:'休息时长'},
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (fenxi && typeof fenxi === "object") {
    myChart.setOption(fenxi, true);
}




	var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;

option = {
    title: {
        text: '正在进行工作流分析',
        textStyle: {
        fontSize: 13
        }
    },
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
	
	$(function(){
	    //鼠标悬停事件
    $(".zhodai").hover(function(){
    	$(".zhodai").css({"cursor":"pointer"});      
    },function(){
          });
          	    //鼠标悬停事件
    $(".money").hover(function(){
    	$(".money").css({"cursor":"pointer"});      
    },function(){
          });
          	    //鼠标悬停事件
    $(".qingjia").hover(function(){
    	$(".qingjia").css({"cursor":"pointer"});      
    },function(){
          });
          	    //鼠标悬停事件
    $(".goumai").hover(function(){
    	$(".goumai").css({"cursor":"pointer"});      
    },function(){
          });
          	    //鼠标悬停事件
    $(".baogao").hover(function(){
    	$(".baogao").css({"cursor":"pointer"});      
    },function(){
          });
                    	    //鼠标悬停事件
    $(".addliu").hover(function(){
    	$(".addliu").css({"cursor":"pointer"});      
    },function(){
          });


   $('#ca').calendar({
        data: [
			{
			  date: '2019/3/18',
			  value: 'Christmas Eve'
			},
			{
			  date: '2019/3/19',
			  value: '任务进行中....'
			},
			{
			  date: '2019/3/20',
			  value: '所有任务完成'
			},
			{
			  date: '2019/3/1',
			  value: '已完成任务领料'
			}
		],

    });    
    
       $("#edsk").hover(function(){
    	$("#edsk").css({"cursor":"pointer"});      
    },function(){
          });
   
   //主页面点击事件
   $("#edsk").click(function(){
   	window.location.href="ModuleFunctionAction!findMfByUser.action";
   
   });
   
    
/*     $(".old").click(function(){
    alert("PEBS")
    });
     */
     
	});
	</script>
  </body>
</html>
