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

		<title>供应商综合分析</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">



		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/echarts/echarts.min.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
		<!-- echarts主题 -->
		<script
			src="${pageContext.request.contextPath}/javascript/echarts/dark2.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/echarts/dark.js">
</script>
		<style>
.center-block {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.container-fluid {
	background: #333;
	color: white;
}
</style>

	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div align="center"
					style="background: #1b1430; color: white; height: 70px; font-size: 50px; font-weight: bold;">
					${ZhUser.cmp}
				</div>
			</div>

			<div class="row" style="border: 1px solid grey;">
				<div class="col-md-12">
					<div class="row" style="border: 1px solid grey;">
						<div id="Supply-information-of-the-year"
							style="width: 100%; height: 450px;">
						</div>
					</div>
					<div class="row"
						style="border: 1px solid grey; background: #333333;">
						<div id="Defective-material"
							style="width: 33%; height: 260px; float: left;">
						</div>
						<div id="chart5" style="width: 33%; height: 260px; float: left;">
						</div>
						<div id="chart6" style="width: 33%; height: 270px; float: left;">
						</div>
					</div>

				</div>
			</div>
		</div>

		<script type="text/javascript">
var myChartMonth = {};
var myChartDeliverydata = {};//交货量
var myChartQualifieddata = {};//合格率
var myChartTimelinessdata = {};//及时率
var myChart3SupplyCategorydata = {};$.ajax( {
	url : 'zhaobiaoAction!jsonDelivery.action',
	dataType : 'json',
	data : {
		id:"${ZhUser.id}"
	},
	async : false,
	cache : false,//防止数据缓存
	success : function(jsondata) {
		myChartQualifieddata=jsondata.hege;
		myChartTimelinessdata=jsondata.jishi;
		myChartDeliverydata=jsondata.number;
		myChartMonth=jsondata.yuefen;
		 $.each(myChartQualifieddata, function (n, value) {
			 if(myChartQualifieddata[n]!=null){
				 myChartQualifieddata[n]=value*100;
			 }
			 
	          });
		 $.each(myChartTimelinessdata, function (n, value) {
			 if(myChartTimelinessdata[n]!=null){
				myChartTimelinessdata[n]=value*100;	 
			 }
			 
           });
	}
});



$.ajax( {
	url : 'markIdAction!SupplyCategoryPercent.action',
	type:'post',     
	dataType : 'json',
	data : {
		id:"${ZhUser.id}"
		
	},
	async : false,
	cache : false,//防止数据缓存
	success : function(obj) {
		myChart3SupplyCategorydata=obj;
	}
});





// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document
		.getElementById('Supply-information-of-the-year'), 'dark');
var myChart4 = echarts.init(document.getElementById('Defective-material'),
		'dark');
var myChart5 = echarts.init(document.getElementById('chart5'), 'dark');
var myChart6 = echarts.init(document.getElementById('chart6'), 'dark');

// 指定图表的配置项和数据
var myChartoption = {
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross',
			crossStyle : {
				color : '#999'
			}
		}
	},
	toolbox : {
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : [ 'line', 'bar' ]
			},
			restore : {
				show : true
			},
			saveAsImage : {
				show : true
			}
		}
	},
	legend : {
		data : [ '供货合格率', '供货及时率', '总交货量' ]
	},
	xAxis : [ {
		type : 'category',
		data : myChartMonth,
		axisPointer : {
			type : 'shadow'
		}
	} ],
	yAxis : [ {
		type : 'value',
		name : '合格率',
		min : 0,
		max : 100,
		interval : 50,
		axisLabel : {
			formatter : '{value}%'
		}
	}, {
		type : 'value',
		name : '',
		//min : 0,
		//max : 1000,
		//interval : 100,
		axisLabel : {
			formatter : '{value}'
		}
	} ],
	series : [
			{
				name : '供货合格率',
				type : 'line',
				//[ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 13.6, 16.2, 32.6,20.0, 6.4, 3.3 ]
				data :myChartQualifieddata 
			},
			{
				name : '供货及时率',
				type : 'line',
				//[ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 15.6, 18.2, 48.7,18.8, 6.0, 2.3 ]
				data : myChartTimelinessdata
			},
			{
				name : '总交货量',
				type : 'bar',
				yAxisIndex : 1,
				data : myChartDeliverydata,
			} ]
};


var myChart4option = {
	title : {
		text : '来料不良占比分析',
	},
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : '55%',
		data : [ {
			value : 235,
			name : '折反折斜'
		}, {
			value : 274,
			name : '尺寸不良'
		}, {
			value : 310,
			name : '角度不良'
		}, {
			value : 335,
			name : '变形'
		}, {
			value : 400,
			name : '压印'
		} ]
	} ]
};
var myChart5option = {
	title : {
		text : '不良率分布',
	},
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : '25%',
		data : [ {
			value : 235,
			name : '来料不良'
		}, {
			value : 274,
			name : '现场不良'
		}, {
			value : 310,
			name : '在库不良'
		} ]
	} ]
};
var myChart6option = {
	title : {
		text : '不合格类别分布',
	},
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : '55%',
		data : [ {
			value : 235,
			name : '护罩'
		}, {
			value : 274,
			name : '隔热材料'
		}, {
			value : 310,
			name : '螺纹管'
		}, {
			value : 335,
			name : '支架'
		}, {
			value : 400,
			name : '盖板'
		} ]
	} ]
};

// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(myChartoption);
	myChart4.setOption(myChart4option);
	myChart5.setOption(myChart5option);
	myChart6.setOption(myChart6option);

</script>
	</body>
</html>
