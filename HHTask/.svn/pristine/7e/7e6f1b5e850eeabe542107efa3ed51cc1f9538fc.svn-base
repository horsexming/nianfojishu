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

		<title>工资薪酬分析</title>

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
		<script type="text/javascript"
			src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
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

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css" />
	</head>
	<body>
		<div align="center">
			<div id="Supply-information-of-the-year"
				style="width: 100%; height: 450px;">
			</div>
		</div>
		<script type="text/javascript">
function showchart() {
	var myChartMonth = {};
	var myChartDeliverydata = {};
	var zwsdata = {};
	var namedata = "";
	$
			.ajax( {
				url : 'WageAction!findWageMonth.action?id=${param.id}&pageStatus=${param.pageStatus}',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				success : function(jsondata) {
					myChartDeliverydata = jsondata.timelong;
					myChartMonth = jsondata.yuefen;
					zwsdata = jsondata.zws;
					namedata = jsondata.name;
				}
			});

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document
			.getElementById('Supply-information-of-the-year'), 'dark');

	// 指定图表的配置项和数据
	var myChartoption = {
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'cross',
				crossStyle : {
					color : '#000'
				}
			}
		},
		itemStyle : {
			normal : {
				color : '#3e76b1',
				shadowBlur : 200,
				shadowColor : 'rgba(0, 0, 0, 0.5)'
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
			data : [ namedata ]
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
			name : '',
			//min : 0,
			//max : 1000,
			//interval : 100,
			axisLabel : {
				formatter : '{value}'
			}
		} ],
		series : [ {
			name : namedata,
			type : 'line',
			//[ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 13.6, 16.2, 32.6,20.0, 6.4, 3.3 ]
			data : myChartDeliverydata,
			itemStyle : {
				normal : {
					color : '#3e76b1',
					shadowBlur : 200,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		}, {
			name : '',
			type : 'line',
			//[ 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 13.6, 16.2, 32.6,20.0, 6.4, 3.3 ]
			data : zwsdata,
			itemStyle : {
				normal : {
					color : 'red',
					shadowBlur : 200,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(myChartoption);
}
showchart();
</script>
	</body>
</html>
