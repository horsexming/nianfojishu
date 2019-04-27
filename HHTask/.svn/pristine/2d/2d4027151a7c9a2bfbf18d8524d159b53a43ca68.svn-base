<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript"
	src="<%=basePath%>/javascript/echartsIE8/jquery.js"></script>

<script type="text/javascript"
	src="<%=basePath%>/javascript/echartsIE8/echarts.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/javascript/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/css.css" />
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
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%;">
		<div align="center">
			<form action="faceAction!toSearchLineChart.action" id="myForm" method="post">
				<table class="table">
					<tr>
						<th>工号</th>
						<td><input type="hidden" value="${userCode}" name="userCode">${userCode }</td>
						<th>开始时间</th>
						<td><input name="startTime" class="Wdate" id="startTime"
							value="${startTime }"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
						</td>
						<th>结束时间</th>
						<td><input name="endTime" class="Wdate" id="endTime"
							value="${endTime }"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
						</td>
						<s:if test="pageStatus==null && pageStatus!='e-work'">
							<td>
								<input type="submit" value="查询" >
							</td>
						</s:if>
					</tr>
				</table>
			</form>
			<br />
			<div id="Supply-information-of-the-year"
				style="width: 100%; height: 450px;"></div>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function showchart() {
			var myChartMonth = {};
			var myChartDeliverydata = {};
			var zwsdata = {};
			var zbTimeData = {};
			var zbbzTimeData = {};
			
			var dateTimeList = {};
			var workTimeList = {};
			var hoursList = {};
			var startTimeList = {};
			var endTimeList = {};
			var timeList ={};// new Array('00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00',
					//'12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00');
			$.ajax({
				url : 'faceAction!searchLineChart.action',
				dataType : 'json',
				data : $("#myForm").serialize(),
				async : false,
				cache : false,//防止数据缓存
				success : function(jsondata) {
					if (jsondata != null) {
						dateTimeList = jsondata.dateTimeList;
						workTimeList = jsondata.workTimeList;
						hoursList = jsondata.hoursList;
						timeList = jsondata.timeList;
						startTimeList = jsondata.startTimeList;
						endTimeList = jsondata.endTimeList;
					}
				}
			});

			require.config({
						paths : {
							echarts : '${pageContext.request.contextPath}/javascript/echartsIE8'
						}
					});
			require(
					[ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ],
					function(ec) {
						var myChart = ec
								.init(document
										.getElementById('Supply-information-of-the-year'));

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
										type : [ 'line', 'bar', 'stack' ]
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
								data : [ '工作时长(h)']
							},
							xAxis : [ {
								type : 'category',
								data : dateTimeList,
								name : '日期',
								axisPointer : {
									type : 'shadow'
								}
							} ],
							yAxis : [ {
								type : 'value',
								name : '小时(hour)',
						        splitLine: {
						            show: false
						        }
							} ],
							series : [ {
								name : '工作时间',
								type : 'bar',
								stack : '时长',
								data : hoursList,
								itemStyle : {
									normal : {
										color : '#3DF43D',
										shadowBlur : 200,
										shadowColor : 'rgba(0, 0, 0, 0.5)'
									}
								}
// 							},{
// 								name:"工作结束",
// 								type:'bar',
// 								stack: '时长',
// 								itemStyle : { normal: {label : {show: true, position: 'inside'}}},
// 								data:endTimeList
							} ]
						};
						myChart.setOption(myChartoption);
					});
		}
		showchart();
	</script>
</body>
</html>
