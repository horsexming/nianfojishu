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
				<div class="col-md-2" style="border: 1px solid grey;">
					<div class="row" align="left">
						<%--						<br />--%>
						<%--						<div align="center">--%>
						<%--							<img src="${pageContext.request.contextPath}/images/man.jpg"--%>
						<%--								alt="负责人" class="img-circle" height="100" width="100">--%>
						<%--						</div>--%>
						<%--						<br />--%>
						<%--						<b>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称 :</b>--%>
						<%--						:${ZhUser.cmp}--%>
						<%--						<br />--%>
						<%--						<br />--%>
						<b>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</b>
						${ZhUser.companydz}
						<br />
						<br />
						<b>联&nbsp;&nbsp;系&nbsp;&nbsp;人 :</b> ${ZhUser.cperson}
						<br />
						<br />
						<b>联系方式 :</b> ${ZhUser.ctel}
						<br />
						<br />
						<b>注册时间：</b>${ZhUser.time}
						<br />
						<br />
						<b>供应产品：</b> ${ZhUser.note}
					</div>
					<hr />
					<div id="Supply-category" 综合评价
						style="width: 100%; height: 240px; float: left;">
					</div>
					<div align="center" style="padding-left: 0px; width: 100%">
						<div id="overall-merit"
							style="width: 100%; height: 240px; float: left;">
						</div>
					</div>
				</div>
				<div class="col-md-7">
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
				<div class="col-md-3">
					<div id="wtf" style="width: 100%; height: 250px; float: left;">
					</div>
					<table class="table"
						style="color: white; margin-left: -15px; margin-right-15px; width: 100%">
						<tr>
							<th style="background: #333333;" colspan="3" align="left">
								供应产品信息
							</th>
						</tr>
						<tr>
							<td>
								件号
							</td>
							<td>
								版本
							</td>
							<td>
								配额
							</td>
						</tr>

						<s:iterator value="gysMarkIdjiepais" status="st" id="gyss">
							<s:if test="#st.index<9">
								<tr>
									<td>
										${gyss.markId}
									</td>
									<td>
										${gyss.banBenNumber}
									</td>
									<td>
										${gyss.cgbl}
									</td>
								</tr>
							</s:if>
							<s:if test="#st.index==9">
								<tr>
									<td>
									<a href="markIdAction!listGysMarkIdjiepaichakan.action?gysMarkIdjiepai.zhuserId=${zhUser.id}">查看更多</a>
										
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>
							</s:if>


						</s:iterator>
					</table>

				</div>
			</div>
		</div>

		<script type="text/javascript">
var myChartMonth = {};
var myChartDeliverydata = {};//交货量
var myChartQualifieddata = {};//合格率
var myChartTimelinessdata = {};//及时率
var myChart2TotalProductdata_name = [];
var myChart2TotalProductdata_value = [];
var myChart3SupplyCategorydata = {};$.ajax( {
	url : 'zhaobiaoAction!jsonDelivery.action',
	dataType : 'json',
	data : {
		id:${ZhUser.id},
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
	url : 'WaigouwaiweiPlanAction!TotalProductAnalysis.action',
	type:'post',     
	dataType : 'json',
	data : {
	 id2:${ZhUser.id}
	},
	async : false,
	cache : false,//防止数据缓存
	success : function(obj) {
		
		$(obj).each(function(){
			myChart2TotalProductdata_name.push($(this).attr('name'));
			myChart2TotalProductdata_value.push($(this).attr('value'));
				
		})
	}
});


$.ajax( {
	url : 'markIdAction!SupplyCategoryPercent.action',
	type:'post',     
	dataType : 'json',
	data : {
<%--		gys:"${ZhUser.cmp}",--%>
		id:${ZhUser.id}
		
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
var myChart2 = echarts.init(document.getElementById('wtf'), 'dark');
var myChart3 = echarts.init(document.getElementById('Supply-category'), 'dark');
var myChart4 = echarts.init(document.getElementById('Defective-material'),
		'dark');
var myChart5 = echarts.init(document.getElementById('chart5'), 'dark');
var myChart6 = echarts.init(document.getElementById('chart6'), 'dark');
var myChart7 = echarts.init(document.getElementById('overall-merit'), 'dark');//综合评价

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

var myChart2option= {
	title : {
		text : '产品总量分析',
	},
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'shadow'
		}
	},
	grid : {
		left : '3%',
		right : '4%',
		bottom : '3%',
		containLabel : true
	},
	xAxis : {
		type : 'value',
		boundaryGap : [ 0, 0.01 ]
	},
	yAxis : {
		type : 'category',
		//[ '5Z0253097A', '5AX1400143B', '1K0253144AE', '1K0253461J', '5Z0253097A', '1K0253461J' ]
		data :myChart2TotalProductdata_name
			
	},
	series : [ {
		name : '到货数量',
		type : 'bar',
		//[ 18, 23, 29, 104, 131, 630]
		data :myChart2TotalProductdata_value
			
	} ]
};

var myChart3option = {
	title : {
		text : '供应类别占比',
	},
	tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
	series : [ {
		name : '供应类别占比',
		type : 'pie',
		radius : '55%',
		data :
			myChart3SupplyCategorydata, 
<%--		  [ {value : 235,name : '护罩'}, --%>
<%--			{value : 274,name : '隔热材料'}, --%>
<%--			{value : 310,name : '螺纹管'},--%>
<%--			{value : 335,name : '支架'},--%>
<%--			{value : 400,name : '盖板'} ]--%>
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
var myChart7option = {
	  title: {
        text: '综合评价'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        x: 'center',
        data:['综合评价']
    },
    radar: [
        {
            indicator: [
                {text: '合格率', max: 1},
                {text: '及时率', max: 1},
                {text: '开票率', max: 1},
                //{text: 'XX率', max: 100}
            ],
            center: ['50%','50%'],
            radius: 60
        }
    ],
    series: [
        {
            type: 'radar',
             tooltip: {
                trigger: 'item'
            },
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data: [
                {
                    //value: [0.60,0.73,1],
                    value: [${ZhUser.gonghuolv},${ZhUser.zhiliang},1],
                    name: '供应商'
                    
                }
            ]
        }
    ]
	
};

// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(myChartoption);
	myChart2.setOption(myChart2option);
	myChart3.setOption(myChart3option);
	myChart4.setOption(myChart4option);
	myChart5.setOption(myChart5option);
	myChart6.setOption(myChart6option);
	myChart7.setOption(myChart7option);

</script>
	</body>
</html>
