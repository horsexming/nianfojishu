<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/javascript/bootstrap-3.3.7-dist/css/bootstrap.css" />
		<script type="text/javascript"
			src="<%=basePath%>/javascript/echarts/echarts.min.js"></script>
		<script src="<%=basePath%>/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="<%=basePath%>/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript"
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			caption{
				text-align: center;
			}
			body{
				margin:5px;
/* 				padding: 5px; */
			}
			.row{
				margin:0;
			}
			#showTop{
				max-height: 60%;
			}
			* {margin: 0; padding: 0;}
			.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th,
			 .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
			 	padding: 4px;
			 }
		</style>
	</head>
	<body id="body" style="background-color: #F5FFFA;">
		<div class="row" id="showTop">
			<div class="cell col-md-12">
				<form action="productEBAction!findPebUsersByCon.action" method="post" autocomplete=off>
					<table class="table table-responsive"  style="border: solid 2px #000000;">
						<caption><h2 style="margin: 0;padding: 0">${banzu}生产效率简报</h2></caption>
						<tr>
							<th style="padding: 0;">项目</th>
							<s:iterator value="showList" id="show" >
								<th style="padding: none;">${show[0] }</th>
							</s:iterator>
						</tr>
						<tr>
							<th style="padding: 0;">出勤<br>天数</th>
							<s:iterator value="showList" id="show" >
								<th>${show[1] }</th>
							</s:iterator>
						</tr>
						<tr>
							<th style="padding: 0;">人数</th>
							<s:iterator value="showList" id="show" >
								<th>${show[2] }</th>
							</s:iterator>
						</tr>
						<tr>
							<th style="padding: 0;">实际产<br>出(仓)</th>
							<s:iterator value="showList" id="show" >
								<th>${show[3] }</th>
							</s:iterator>
						</tr>
						<tr>
							<th style="padding: 0;">实际日<br>人均仓</th>
							<s:iterator value="showList" id="show" >
								<th>${show[4] }</th>
							</s:iterator>
						</tr>
						<tr>
							<th style="padding: 0;">目标日<br>人均仓</th>
							<s:iterator value="showList" id="show" >
								<th>${show[5] }</th>
							</s:iterator>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div id="myChart" ></div>
			</div>
		</div>
		<br>
		<div class="row" style="line-height: 13px;">
			<p>每日点评：</p>
			<s:iterator value="dianPingList" id="dianping" >
				<p>${dianping }</p>
			</s:iterator>
		</div>
	</body>	
	<script type="text/javascript">
	
	var bodyHeight = $jq("#body").height();
	var height=$jq("#showTop").height();
	$jq("#myChart").height(bodyHeight-height-110);
	// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('myChart'));
	// 指定图表的配置项和数据
    var option = {
   	    title: {
   	        text: '${banzu}计划/实际产出仓数：单位-台',
   	    },
   	 	backgroundColor:'#ffffff',
   	    tooltip: {
   	        trigger: 'axis'
   	    },
   	    legend: {
   	        data:['实际仓','日人均仓','日人均产出目标']
   	    },
	    calculable : true,
   	    grid: {
   	        left: '0%',
   	        right: '0%',
   	        bottom: '2%',
   	        containLabel: true
   	    },
   	    xAxis: [{
   	        type: 'category',
   	        boundaryGap: true,
   	        data: getValues(".table tr:first"),
   	     	axisLabel: {//x轴文字显示不全并将文字倾斜
				interval:0//,
				//rotate:40
			}

   	    }],
   	    yAxis: [
   	    	{
	            type : 'value',
	            name : '实际产出仓',
	            splitLine : {//去掉网格线
					show : false
				}
	        },
	        {
	            type : 'value',
	            name : '日人均仓',
	            splitLine : {//去掉网格线
					show : false
				}
	        }
   	    ],
   	    series: [
   	        {
   	            name:'实际仓',
   	         	yAxisIndex:0,
   	            type:'bar',
   	         	symbolSize: 8,   //折线点的大小
   	         	itemStyle : { 
   	         		normal: {
   	         			label : {
   	         				show: true,
   	         				textStyle:{
//    	         					fontSize:15
   	         				}
   	        			},
   	        			color:'rgb(255,102,255)'
   	        		}
   	        	},
   	            data:getValues(".table tr:eq(3)")//,
//         		barWidth : 25,//柱图宽度
//  	            barMaxWidth:50//最大宽度
   	        },
   	        {
   	            name:'日人均仓',
   	            type:'line',
   	         	yAxisIndex:1,
   	 			connectNulls: true,
   	 			symbolSize: 8,   //折线点的大小
   	         	itemStyle : {
   	         		normal: {
       					label : {
       						show: true,
   	         				textStyle:{
   	         					fontSize:16
   	         				}
   	        			},
       					lineStyle:{width:4},
   	        			color:'rgb(0,0,255)'
   	        		}
   	         		
   	         	},
   	            data:getValues(".table tr:eq(4)")
   	        },
   	        {
   	            name:'日人均产出目标',
   	            type:'line',
   	         	yAxisIndex:1,
   	         	symbolSize: 8,   //折线点的大小
   	 			connectNulls: true,
   	         	itemStyle : { 
   	         		normal: {
	   	         		lineStyle:{
	                        width:4,
	                        type:'dashed'  //'dotted'虚线 'solid'实线
	                    },
   	         			label:{
   	         				show: false
   	         			},
   	        			color:'rgb(0,0,255)'
       					//textStyle:{fontSize:20px}
   	         		}
   	        	},
   	            data:getValues(".table tr:eq(5)")
   	        }
   	    ]
   	};
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
    
    function getValues(object){
    	 var contrTemp=[];
    	 var tr = $(object);
    	 
         for(var i=1;i<tr[0].cells.length;i++){
        	 var value = tr[0].cells[i].innerText;
//         	 if(value==0){
//         		 contrTemp.push(0);
//         	 }else{
	             contrTemp.push(value);
//         	 }
         }
         return contrTemp;
    }
	</script>
</html>
