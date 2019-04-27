<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>

		<title>${companyInfo.shortName}电子看板</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/highcharts/highcharts.js">
</script>
		<script
			src="${pageContext.request.contextPath}/javascript/highcharts/exporting.js">
</script>

		<script type="text/javascript">

$(function() {
	var winWidth = 0;
	var winHeight = 0;
	//获取窗口宽度 
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;

	//获取窗口高度 
	if (window.innerHeight) {
		winHeight = window.innerHeight;
	} else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;

	//通过深入Document内部对body进行检测，获取窗口大小 
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	$("#container").width(winWidth);
	$("#container").height(winHeight);
	$
			.ajax( {
				type : "POST",
				url : "orderManager_findDjfProduct.action",
				dataType : "json",
				async : false,
				success : function(object) {
					Highcharts
							.createElement(
									'link',
									{
										href : '${pageContext.request.contextPath}/javascript/highcharts/css?family=Unica+One',
										rel : 'stylesheet',
										type : 'text/css'
									}, null, document
											.getElementsByTagName('head')[0]);

					Highcharts.theme = {
						colors : [ "#2b908f", "#90ee7e", "#f45b5b", "#7798BF",
								"#aaeeee", "#ff0066", "#eeaaee", "#55BF3B",
								"#DF5353", "#7798BF", "#aaeeee" ],
						chart : {
							backgroundColor : {
								linearGradient : {
									x1 : 0,
									y1 : 0,
									x2 : 1,
									y2 : 1
								},
								stops : [ [ 0, '#2a2a2b' ], [ 1, '#3e3e40' ] ]
							},
							style : {
								fontFamily : "'Unica One', sans-serif"
							},
							plotBorderColor : '#606063'
						},
						title : {
							style : {
								color : '#E0E0E3',
								textTransform : 'uppercase',
								fontSize : '20px'
							}
						},
						subtitle : {
							style : {
								color : '#E0E0E3',
								textTransform : 'uppercase'
							}
						},
						xAxis : {
							gridLineColor : '#707073',
							labels : {
								style : {
									color : '#E0E0E3'
								}
							},
							lineColor : '#707073',
							minorGridLineColor : '#505053',
							tickColor : '#707073',
							title : {
								style : {
									color : '#A0A0A3'

								}
							}
						},
						yAxis : {
							gridLineColor : '#707073',
							labels : {
								style : {
									color : '#E0E0E3'
								}
							},
							lineColor : '#707073',
							minorGridLineColor : '#505053',
							tickColor : '#707073',
							tickWidth : 1,
							title : {
								style : {
									color : '#A0A0A3'
								}
							}
						},
						tooltip : {
							backgroundColor : 'rgba(0, 0, 0, 0.85)',
							style : {
								color : '#F0F0F0'
							}
						},
						plotOptions : {
							series : {
								dataLabels : {
									color : '#FFFFFF'
								},
								marker : {
									lineColor : '#333'
								}
							},
							boxplot : {
								fillColor : '#505053'
							},
							candlestick : {
								lineColor : 'white'
							},
							errorbar : {
								color : 'white'
							}
						},
						legend : {
							itemStyle : {
								color : '#E0E0E3'
							},
							itemHoverStyle : {
								color : '#FFF'
							},
							itemHiddenStyle : {
								color : '#606063'
							}
						},
						credits : {
							style : {
								color : '#666'
							}
						},
						labels : {
							style : {
								color : '#707073'
							}
						},

						drilldown : {
							activeAxisLabelStyle : {
								color : '#F0F0F3'
							},
							activeDataLabelStyle : {
								color : '#F0F0F3'
							}
						},

						navigation : {
							buttonOptions : {
								symbolStroke : '#DDDDDD',
								theme : {
									fill : '#505053'
								}
							}
						},

						// scroll charts
						rangeSelector : {
							buttonTheme : {
								fill : '#505053',
								stroke : '#000000',
								style : {
									color : '#CCC'
								},
								states : {
									hover : {
										fill : '#707073',
										stroke : '#000000',
										style : {
											color : 'white'
										}
									},
									select : {
										fill : '#000003',
										stroke : '#000000',
										style : {
											color : 'white'
										}
									}
								}
							},
							inputBoxBorderColor : '#505053',
							inputStyle : {
								backgroundColor : '#333',
								color : 'silver'
							},
							labelStyle : {
								color : 'silver'
							}
						},

						navigator : {
							handles : {
								backgroundColor : '#666',
								borderColor : '#AAA'
							},
							outlineColor : '#CCC',
							maskFill : 'rgba(255,255,255,0.1)',
							series : {
								color : '#7798BF',
								lineColor : '#A6C7ED'
							},
							xAxis : {
								gridLineColor : '#505053'
							}
						},

						scrollbar : {
							barBackgroundColor : '#808083',
							barBorderColor : '#808083',
							buttonArrowColor : '#CCC',
							buttonBackgroundColor : '#606063',
							buttonBorderColor : '#606063',
							rifleColor : '#FFF',
							trackBackgroundColor : '#404043',
							trackBorderColor : '#404043'
						},

						// special colors for some of the
						legendBackgroundColor : 'rgba(0, 0, 0, 0.5)',
						background2 : '#505053',
						dataLabelsColor : '#B0B0B3',
						textColor : '#C0C0C0',
						contrastTextColor : '#F0F0F3',
						maskColor : 'rgba(255,255,255,0.3)'
					};

					// Apply the theme
					Highcharts.setOptions(Highcharts.theme);

					$('#container')
							.highcharts(
									{
										chart : {
											type : 'bar'
										},
										title : {
											text : '订单待交付数量'
										},
										subtitle : {
											text : ''
										},
										xAxis : {
											categories : object[0],
											title : {
												text : null
											}
										},
										yAxis : {
											min : 0,
											title : {
												text : 'Population (millions)',
												align : 'high'
											},
											labels : {
												overflow : 'justify'
											}
										},
										tooltip : {
											valueSuffix : ' millions'
										},
										plotOptions : {
											bar : {
												dataLabels : {
													enabled : true
												}
											}
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'top',
											x : -40,
											y : 80,
											floating : true,
											borderWidth : 1,
											backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
											shadow : true
										},
										credits : {
											enabled : false
										},
										series : [ {
											name : '2016',
											data : object[1]
										} ]
									});
				}
			});

	//页面定时刷新(3分钟/次)
	setTimeout(nextPage, 1000 * 20);

	//下一个页面
	function nextPage() {
		getOnline();//在线检测
		window.location.href = "screen_printScreen3.action?screen.id=${param.id}";
	}
});
</script>

	</head>
	<body id="aaa" style="overflow: hidden; font-family: 黑体">
		<div id="container" style="min-width: 310px; margin: 0 auto"></div>
	</body>
</html>
