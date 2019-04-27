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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/echarts/echarts.js">
			</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="main" style="width: 98%; height: 400px;"></div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var UCLXBar,xAxis_data,yAxis_data0,min0,max0;
!function fuzhi(){
// 基于准备好的dom，初始化echarts实例
var name = '${user.name}'+'的';
var tag = '${pageStatus}'
if(tag == 'jiangjin'){
	name+='奖金'
}else if(tag == 'kaohe'){
		name+='考核'
}

var myChart0 = echarts.init(document.getElementById('main'));
	 myChart0.setOption({
	 backgroundColor: '#c0dcf2',
    title: {
        text: name+'折线图',
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['UCLXBar']
    },
    toolbox: {
        show: true,
        feature: {
            dataZoom: {
                 show: true
            },
            dataView: {readOnly: false},
            magicType: {type: ['line','bar']},
            restore: {},
            saveAsImage: {show: true}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: {
       type: 'value',
    },
    series: [
        {
            name:name,
            type:'line',
            data:[],
             itemStyle:{
        		normal:{
        			color:'#FF0000',
        			lineStyle:{
        				color:'#FF0000',
        			}
        		}
            }
        },
    ]
});
	
 myChart0.showLoading();    //数据加载完之前先显示一段简单的loading动画
	UCLXBar = new Array();
 xAxis_data =  new Array();
	var jiange = 1;
	var jiange1 = 1;
	$.ajax( {
		type : "POST",
		url : "WageAction!getWageByUsers.action",
		data : {
			id : '${id}',
			mouth:'${mouth}'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$.each(data,function(index,obj){
					var value = 0;
					if(tag == 'jiangjin'){
						value = obj.jiangjin
					}else if(tag == 'kaohe'){
						value = obj.jixiaokaohegongzi
					}
					UCLXBar[index]=Math.round(value*1000)/1000;
					xAxis_data[index]=obj.mouth;
				})
				
				
	  myChart0.hideLoading();    //隐藏加载动画
	myChart0.setOption({
        xAxis: {
            data: xAxis_data
        },
       yAxis: {
       boundaryGap:yAxis_data0,
       min:min0,
       max:max0
    },
        series: [{
            // 根据名字对应到相应的系列
            name: name,
            data: UCLXBar,
        	}
        ]
    });
	
	
			}
		}
	})
	
	
myChart0.on('click', function (params) {
    if (params.componentType === 'series') {
        if (params.seriesType === 'line') {
            if (params.dataType === 'edge') {
            }else {
            	if(tag == 'jiangjin'){
						window.open("ProcardAction!findUMMoneyByCondition.action?umm.userId=${user.id}&umm.month=${mouth}");
				}else if(tag == 'kaohe'){
						window.open("AssScoreAction!findAssScoreByCondition.action?assScore.code=${user.code}&assScore.asstMouth=${mouth}");
				}
            	
            }
        }
    }

});
	
}();




</script>
	</body>
</html>
