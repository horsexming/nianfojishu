<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>品质异常图</title>

  	<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
  </head>
  
  <body>
  		<div style="height: 5%;margin-top: 25px">
  		<center>
  		<form action="QualityAbnormalresumeAction_findqaforPic.action" method="post" id="pic_form">
  		<select name="count_tag">
  		<s:if test="count_tag!=null">
  			<option value="${count_tag}">${count_tag}</option>
  		</s:if>
  			<option value="5">5</option>
  			<option value="10">10</option>
  			<option value="15">15</option>
  		</select>
      	<input type="radio" name="tag" value="leibie" onclick="checkForm();">问题类型
		<input type="radio" name="tag" value="bianma" onclick="checkForm();">编码
		<input type="radio" name="tag" value="chejian" checked ="checked" onclick="checkForm();">车间
      	</form>
      	</center>
  		</div>
      <div id="container" style="height: 95%;margin-top: 0px">
      
      </div>
    <script type="text/javascript">
  var dom = document.getElementById("container");
 //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
var resizeWorldMapContainer = function () {
    dom.style.width = window.innerWidth+'px';
   //dom.style.height = window.innerHeight+'px';
    dom.style.height = 540+'px';
};
resizeWorldMapContainer();
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '折柱混合';

option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow',
            crossStyle: {
                color: '#999'
            }
        },
         formatter: function(params) {
                        return params[0].name + '<br/>' +
                            params[0].seriesName + ' : ' + params[0].value + '<br/>' +
                            params[1].seriesName + ' : ' + (params[1].value + params[0].value);
                    }
    },
  
    legend: {
        data:['不良数','累计不良']
    },
    toolbox: {
        show: true,
        feature: {
            dataView: {readOnly: false},
            saveAsImage: {}
        }
    },
    xAxis: [
        {
            type: 'category',
            data: [<c:forEach var="s" items="${dateList }" varStatus="i">'${s[1]}',</c:forEach>],
            axisPointer: {
                type: 'shadow'
            },
            axisLabel: {
                   interval:0,
                   rotate:40
            },
            
   },
   {
                    type: 'category',
                    show:false,
                    boundaryGap : false,
                    data: ['0',<c:forEach var="s" items="${dateList }" varStatus="i">'${s[1]}',</c:forEach>],
                    splitLine:false
                }
            
   
   ],
    yAxis: [ 
        {
            type: 'value',
            name: '不良数',
            min: 0,
            max: ${count},
            interval: 5,
            axisLabel: {
                formatter: '{value} PCS'
            }
        },
        
        {
            type: 'value',
            name: '累计不良',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'不良数',
            type:'bar',
            barCategoryGap: '0%',
            data:[<c:forEach var="s" items="${dateList }" varStatus="i">${s[0]},</c:forEach>]
        },
        {
            name:'累计不良',
            type:'line',
            xAxisIndex: 1,
            yAxisIndex: 1,
            data:['0',<c:forEach var="s" items="${dateList }" varStatus="i">${s[2]},</c:forEach>]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
//用于使chart自适应高度和宽度
window.onresize = function () {
    //重置容器高宽
    resizeWorldMapContainer();
    myChart.resize();
};
 function checkForm(){
   		 var form = document.getElementById('pic_form');
		form.submit();
	}
    </script>
  </body>
</html>
