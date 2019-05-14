////饼形图/////////////
function getPieOpt() {
	var pieOpt = {
		tooltip : {
			trigger : 'item',
			textStyle : {
				fontSize : 16
			},
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 18,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		legend : {
			show : true,
			orient : 'vertical',
			itemWidth : 10,
			itemHeight : 7,
			inactiveColor : '#787878',
			textStyle : {
				color : '#fff',
				fontSize : 15
			},
			x : '1%',
			y : '80%',
			data : []
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : true
				},
				restore : {
					show : true
				}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			right : '1%',
			top : '5%'
		},
		calculable : false,
		series : [ {
			name : '性别统计',
			type : 'pie',
			radius : [ 0, '60%' ],
			center : [ '50%', '60%' ],
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : '{b}: {c}',
						textStyle : {
							color : '#fff',
							fontSize : 15
						}
					},
					labelLine : {
						show : true,
						length : 2
					}
				},
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			},
			data : []
		} ]
	};
	// 处理数据
	pieOpt.legend.data = [ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ];
	pieOpt.series[0].data = [ {
		name : 'A',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'B',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'C',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'D',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'E',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'F',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'G',
		value : Math.round(Math.random() * 100)
	} ];
	return pieOpt;
}
// /////折线图//////////////
function getLineOpt() {
	lineOpt = {
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 18,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			},
			textStyle : {
				fontSize : 16
			}
		},
		grid : {
			right : '8%',
			top : 68,
			bottom : 5,
			containLabel : true
		},
		toolbox : {
			show : true,
			feature : {
				// mark : {show: true},
				dataView : {
					show : true,
					readOnly : true
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				}
			// saveAsImage : {show: true}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			top : '5%',
			right : '1%'
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [],
			axisLabel : {
				interval : 0,
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				// 刻度大小
				}
			},
			axisTick : {
				alignWithLabel : true
			}
		} ],
		yAxis : [ {
			type : 'value',
			minInterval : 1,
			nameGap : 12,
			nameTextStyle : {
				color : "#fff", // 刻度颜色
			},
			axisLabel : {
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				// 刻度大小
				}
			}
		} ],
		series : []
	};
	// 数据处理
	lineOpt.xAxis[0].data = [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ];
	lineOpt.series = [ {
		name : '吃饭',
		type : 'line',
		data : [ 120, 132, 101, 134, 90, 230, 210 ]
	}, {
		name : '喝水',
		type : 'line',
		data : [ 220, 182, 191, 234, 290, 330, 310 ]
	}, {
		name : '看博客',
		type : 'line',
		data : [ 150, 232, 201, 154, 190, 330, 410 ]
	}, {
		name : '学习',
		type : 'line',
		data : [ 320, 332, 301, 334, 390, 330, 320 ]
	}, {
		name : '运动',
		type : 'line',
		data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
	} ];
	return lineOpt;
}
// /////柱形图//////////
function getBarOpt() {
	var barOpt = {
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 18,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		tooltip : {
			trigger : 'axis',
			formatter : '{a}<br/>{b} : {c}',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			},
			textStyle : {
				fontSize : 16
			}
		},
		grid : {
			right : '8%',
			top : 68,
			bottom : 5,
			containLabel : true
		},
		toolbox : {
			show : true,
			feature : {
				// mark : {show: true},
				dataView : {
					show : true,
					readOnly : true
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				}
			// saveAsImage : {show: true}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			top : '5%',
			right : '1%'
		},
		xAxis : [ {
			type : 'category',
			data : [],
			axisLabel : {
				interval : 0,
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				// 刻度大小
				}
			},
			axisTick : {
				alignWithLabel : true
			}
		} ],
		yAxis : [ {
			type : 'value',
			minInterval : 1,
			nameGap : 12,
			nameTextStyle : {
				color : "#fff", // 刻度颜色
			},
			axisLabel : {
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				// 刻度大小
				}
			}
		} ],
		series : [ {
			name : '',
			type : 'bar',
			data : []
		} ]
	};
	// 处理数据
	barOpt.xAxis[0].data = [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ];
	barOpt.xAxis[0].name = '日期';
	barOpt.yAxis[0].name = '数量';
	barOpt.series[0].data = [ {
		name : '周日',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周一',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周二',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周三',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周四',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周五',
		value : Math.round(Math.random() * 100)
	}, {
		name : '周六',
		value : Math.round(Math.random() * 100)
	} ];
	return barOpt;
}
// ///散点图////////////
function getScatterOpt() {
	var scatterOpt = {
		backgroundColor : new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [ {
			offset : 0,
			color : '#f7f8fa'
		}, {
			offset : 1,
			color : '#cdd0d5'
		} ]),
		title : {
			subtext : '1990 与 2015 年各国家人均寿命与 GDP'
		},
		legend : {
			right : 10,
			data : [ '1990', '2015' ]
		},
		xAxis : {
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			}
		},
		yAxis : {
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			},
			scale : true
		},
		series : [ {
			name : '1990',
			data : [],
			type : 'scatter',
			symbolSize : function(data) {
				return Math.sqrt(data[2]) / 5e2;
			},
			label : {
				emphasis : {
					show : true,
					formatter : function(param) {
						return param.data[3];
					},
					position : 'top'
				}
			},
			itemStyle : {
				normal : {
					shadowBlur : 10,
					shadowColor : 'rgba(120, 36, 50, 0.5)',
					shadowOffsetY : 5,
					color : new echarts.graphic.RadialGradient(0.4, 0.3, 1, [ {
						offset : 0,
						color : 'rgb(251, 118, 123)'
					}, {
						offset : 1,
						color : 'rgb(204, 46, 72)'
					} ])
				}
			}
		}, {
			name : '2015',
			data : [],
			type : 'scatter',
			symbolSize : function(data) {
				return Math.sqrt(data[2]) / 5e2;
			},
			label : {
				emphasis : {
					show : true,
					formatter : function(param) {
						return param.data[3];
					},
					position : 'top'
				}
			},
			itemStyle : {
				normal : {
					shadowBlur : 10,
					shadowColor : 'rgba(25, 100, 150, 0.5)',
					shadowOffsetY : 5,
					color : new echarts.graphic.RadialGradient(0.4, 0.3, 1, [ {
						offset : 0,
						color : 'rgb(129, 227, 238)'
					}, {
						offset : 1,
						color : 'rgb(25, 183, 207)'
					} ])
				}
			}
		} ]
	};
	// 数据处理
	var data = [
			[ [ 28604, 77, 17096869, 'Australia', 1990 ],
					[ 31163, 77.4, 27662440, 'Canada', 1990 ],
					[ 1516, 68, 1154605773, 'China', 1990 ],
					[ 13670, 74.7, 10582082, 'Cuba', 1990 ],
					[ 28599, 75, 4986705, 'Finland', 1990 ],
					[ 29476, 77.1, 56943299, 'France', 1990 ],
					[ 31476, 75.4, 78958237, 'Germany', 1990 ],
					[ 28666, 78.1, 254830, 'Iceland', 1990 ],
					[ 1777, 57.7, 870601776, 'India', 1990 ],
					[ 29550, 79.1, 122249285, 'Japan', 1990 ],
					[ 2076, 67.9, 20194354, 'North Korea', 1990 ],
					[ 12087, 72, 42972254, 'South Korea', 1990 ],
					[ 24021, 75.4, 3397534, 'New Zealand', 1990 ],
					[ 43296, 76.8, 4240375, 'Norway', 1990 ],
					[ 10088, 70.8, 38195258, 'Poland', 1990 ],
					[ 19349, 69.6, 147568552, 'Russia', 1990 ],
					[ 10670, 67.3, 53994605, 'Turkey', 1990 ],
					[ 26424, 75.7, 57110117, 'United Kingdom', 1990 ],
					[ 37062, 75.4, 252847810, 'United States', 1990 ] ],
			[ [ 44056, 81.8, 23968973, 'Australia', 2015 ],
					[ 43294, 81.7, 35939927, 'Canada', 2015 ],
					[ 13334, 76.9, 1376048943, 'China', 2015 ],
					[ 21291, 78.5, 11389562, 'Cuba', 2015 ],
					[ 38923, 80.8, 5503457, 'Finland', 2015 ],
					[ 37599, 81.9, 64395345, 'France', 2015 ],
					[ 44053, 81.1, 80688545, 'Germany', 2015 ],
					[ 42182, 82.8, 329425, 'Iceland', 2015 ],
					[ 5903, 66.8, 1311050527, 'India', 2015 ],
					[ 36162, 83.5, 126573481, 'Japan', 2015 ],
					[ 1390, 71.4, 25155317, 'North Korea', 2015 ],
					[ 34644, 80.7, 50293439, 'South Korea', 2015 ],
					[ 34186, 80.6, 4528526, 'New Zealand', 2015 ],
					[ 64304, 81.6, 5210967, 'Norway', 2015 ],
					[ 24787, 77.3, 38611794, 'Poland', 2015 ],
					[ 23038, 73.13, 143456918, 'Russia', 2015 ],
					[ 19360, 76.5, 78665830, 'Turkey', 2015 ],
					[ 38225, 81.4, 64715810, 'United Kingdom', 2015 ],
					[ 53354, 79.1, 321773631, 'United States', 2015 ] ] ];
	scatterOpt.series[0].data = data[0];
	scatterOpt.series[1].data = data[1];
	return scatterOpt;
}
// ///////贵州地图///////////////
function getGzMapOpt() {
	var gzMapOpt = {
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 20,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		tooltip : {
			trigger : 'item',

			textStyle : {
				fontSize : 18
			}
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : true
				},
				restore : {
					show : true
				}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			right : '5%',
			top : '5%'
		},
		dataRange : {
			min : 0,
			max : 1000,
			color : [ 'red', 'yellow' ],
			text : [ '高', '低' ], // 文本，默认为数值文本
			calculable : true,
			right : 2,
			top : '42%',
			textStyle : {
				color : '#ffffff',
				fontSize : 15
			}
		},
		series : [ {
			name : '',
			type : 'map',
			mapType : '贵州',
			selectedMode : 'single',
			layoutCenter : [ '45%', '50%' ],
			layoutSize : '90%',
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontSize : 15
						},
						formatter : function(v) {
							var vn = v.name;
							if (vn.match(/黔南/)) {
								return '黔南';
							} else if (vn.match(/黔东南/)) {
								return '黔东南';
							} else if (vn.match(/黔西南/)) {
								return '黔西南';
							}
						}
					}
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : []
		} ]
	};
	// 处理数据
	gzMapOpt.series[0].data = [ {
		name : '遵义市',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '黔东南苗族侗族自治州',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '毕节市',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '黔南布依族苗族自治州',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '铜仁市',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '黔西南布依族苗族自治州',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '六盘水市',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '安顺市',
		value : Math.round(Math.random() * 1000)
	}, {
		name : '贵阳市',
		value : Math.round(Math.random() * 1000)
	} ];
	var dats = gzMapOpt.series[0].data;
	var mdat = 100;
	for ( var i = 0, len = dats.length; i < len; i++) {
		if (mdat < dats[i].value) {
			mdat = dats[i].value;
		}
	}
	gzMapOpt.dataRange.max = mdat;
	return gzMapOpt;
}
// /////雷达图//////////
function getRadarOpt() {
	var radarOpt = {
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 18,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		tooltip : {
			trigger : 'item',
			textStyle : {
				fontSize : 16
			}
		},
		legend : {
			show : false,
			orient : 'vertical',
			itemWidth : 10,
			itemHeight : 7,
			inactiveColor : '#787878',
			textStyle : {
				color : '#fff',
				fontSize : 15
			},
			left : '1%',
			top : '60%',
			data : []
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : true
				},
				restore : {
					show : true
				}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			right : '1%',
			top : '5%'
		},
		radar : [ {
			indicator : [],
			center : [ '50%', '60%' ],
			radius : '60%',
			axisLabel : {
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				// 刻度大小
				}
			},
		} ],
		calculable : false,
		textStyle : {
			color : "#fff", // 刻度颜色
			fontSize : 15
		},
		series : [ {
			name : '特别管控年龄统计',
			type : 'radar',
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					},
				},
				textStyle : {
					color : "#fff", // 刻度颜色
					fontSize : 15
				}
			},
			data : []
		} ]
	};
	// 数据处理
	radarOpt.series[0].data = [ {
		name : '属性',
		value : [ Math.round(Math.random() * 1000),
				Math.round(Math.random() * 1000),
				Math.round(Math.random() * 1000),
				Math.round(Math.random() * 1000),
				Math.round(Math.random() * 1000) ]
	} ];
	var dats = radarOpt.series[0].data[0].value;
	var mVal = 100;
	for ( var i = 0, len = dats.length; i < len; i++) {
		if (mVal < dats[i]) {
			mVal = dats[i];
		}
	}
	radarOpt.radar[0].indicator = [ {
		name : '攻击',
		max : mVal
	}, {
		name : '防御',
		max : mVal
	}, {
		name : '体力',
		max : mVal
	}, {
		name : '天赋',
		max : mVal
	}, {
		name : '生存',
		max : mVal
	}, ];
	return radarOpt;
}
// ////仪表盘/////////////
function getGaugeOpt() {
	var gaugeOpt = {
		tooltip : {
			formatter : "{a} <br/>{c} {b}",
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontSize : 16
			}
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : true
				},
				restore : {
					show : true
				}
			},
			iconStyle : {
				normal : {
					borderColor : '#fff',
				}
			},
			right : '1%',
			top : '5%'
		},
		title : {
			show : true,
			text : '',
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bold',
				fontSize : 18,
				color : '#fff',
				shadowColor : '#fff', // 默认透明
				shadowBlur : 10
			},
			left : '1%',
			top : '5%'
		},
		series : [
				{
					name : '',
					type : 'gauge',
					radius : '70%',
					min : 0,
					max : 100,
					splitNumber : 8,
					axisLine : { // 坐标轴线
						lineStyle : { // 属性lineStyle控制线条样式
							color : [ [ 0.09, 'lime' ], [ 0.82, '#1e90ff' ],
									[ 1, '#ff4500' ] ],
							width : 3,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					axisLabel : { // 坐标轴小标记
						textStyle : { // 属性lineStyle控制线条样式
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 15,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						},
						formatter : function(val) {
							return val.toFixed(0);
						}
					},
					axisTick : { // 坐标轴小标记
						length : 15, // 属性length控制线长
						lineStyle : { // 属性lineStyle控制线条样式
							color : 'auto',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					splitLine : { // 分隔线
						length : 25, // 属性length控制线长
						lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
							width : 3,
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					pointer : { // 分隔线
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5
					},
					title : {
						show : true,
						offsetCenter : [ 0, '-20%' ],
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							fontSize : 12,
							fontStyle : 'italic',
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						},
						bottom : 20
					},
					detail : {
						backgroundColor : 'rgba(30,144,255,0.8)',
						borderWidth : 1,
						borderColor : '#fff',
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5,
						width : 76,
						height : 35,
						offsetCenter : [ 0, '75%' ], // x, y，单位px
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 16
						}
					},
					data : []
				},
				{
					name : '',
					type : 'gauge',
					center : [ '20%', '55%' ], // 默认全局居中
					radius : '50%',
					min : 0,
					max : 100,
					endAngle : 60,
					startAngle : 240,
					splitNumber : 4,
					axisLine : { // 坐标轴线
						lineStyle : { // 属性lineStyle控制线条样式
							color : [ [ 0.29, 'lime' ], [ 0.86, '#1e90ff' ],
									[ 1, '#ff4500' ] ],
							width : 2,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					axisLabel : { // 坐标轴小标记
						textStyle : { // 属性lineStyle控制线条样式
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 12,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						},
						formatter : function(val) {
							return val.toFixed(0);
						}
					},
					axisTick : { // 坐标轴小标记
						length : 12, // 属性length控制线长
						lineStyle : { // 属性lineStyle控制线条样式
							color : 'auto',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					splitLine : { // 分隔线
						length : 20, // 属性length控制线长
						lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
							width : 3,
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					pointer : {
						width : 5,
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5
					},
					title : {
						show : true,
						offsetCenter : [ '-10%', '-10%' ], // x, y，单位px
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							fontSize : 12,
							fontStyle : 'italic',
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					detail : {
						// backgroundColor: 'rgba(30,144,255,0.8)',
						// borderWidth: 1,
						borderColor : '#fff',
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5,
						width : 70,
						height : 32,
						offsetCenter : [ '10%', '90%' ], // x, y，单位px
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 14
						}
					},
					data : []
				},
				{
					name : '',
					type : 'gauge',
					center : [ '80%', '55%' ], // 默认全局居中
					radius : '50%',
					min : 0,
					max : 10,
					startAngle : 120,
					endAngle : -60,
					splitNumber : 4,
					axisLine : { // 坐标轴线
						lineStyle : { // 属性lineStyle控制线条样式
							color : [ [ 0.29, 'lime' ], [ 0.86, '#1e90ff' ],
									[ 1, '#ff4500' ] ],
							width : 2,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					axisLabel : { // 坐标轴小标记
						textStyle : { // 属性lineStyle控制线条样式
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 12,
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						},
						formatter : function(val) {
							return val.toFixed(0);
						}
					},
					axisTick : { // 坐标轴小标记
						length : 12, // 属性length控制线长
						lineStyle : { // 属性lineStyle控制线条样式
							color : 'auto',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					splitLine : { // 分隔线
						length : 20, // 属性length控制线长
						lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
							width : 3,
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					pointer : {
						width : 5,
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5
					},
					title : {
						show : true,
						offsetCenter : [ '10%', '-10%' ], // x, y，单位px
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							fontStyle : 'italic',
							fontSize : 12,
							color : '#fff',
							shadowColor : '#fff', // 默认透明
							shadowBlur : 10
						}
					},
					detail : {
						// backgroundColor: 'rgba(30,144,255,0.8)',
						// borderWidth: 1,
						borderColor : '#fff',
						shadowColor : '#fff', // 默认透明
						shadowBlur : 5,
						width : 70,
						height : 32,
						offsetCenter : [ '-10%', '90%' ], // x, y，单位px
						textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
							fontWeight : 'bolder',
							color : '#fff',
							fontSize : 14
						}
					},
					data : []
				} ]
	};
	// 数据处理
	var datas = [ {
		name : 'km/h',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'x1000r/min',
		value : Math.round(Math.random() * 100)
	}, {
		name : 'gas',
		value : Math.round(Math.random() * 100)
	} ];

	gaugeOpt.series[0].data = datas[0];
	gaugeOpt.series[0].name = '速度';
	gaugeOpt.series[0].max = (m = datas[0].value) < 10 ? 60 : m < 50 ? 100 : m
			* (((c = m % 9) == 0 ? 1 : c) + m % 0.9).toFixed(1);

	gaugeOpt.series[1].data = datas[1];
	gaugeOpt.series[1].name = '转速';
	gaugeOpt.series[1].max = (m = datas[1].value) < 10 ? 60 : m < 50 ? 100 : m
			* (((c = m % 5) == 0 ? 1 : c) + m % 0.9).toFixed(1);

	gaugeOpt.series[2].data = datas[2];
	gaugeOpt.series[2].name = '油表';
	gaugeOpt.series[2].max = (m = datas[2].value) < 10 ? 60 : m < 50 ? 100 : m
			* (((c = m % 5) == 0 ? 1 : c) + m % 0.9).toFixed(1);

	return gaugeOpt;
}
// K线图/////////////
function getCandlestickOpt() {
	var candlestickOpt = {
		backgroundColor : '#21202D',
		legend : {
			data : [ '日K', 'MA5', 'MA10', 'MA20', 'MA30' ],
			inactiveColor : '#777',
			textStyle : {
				color : '#fff'
			}
		},
		title : {
			subtext : '2015 年上证指数'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				animation : false,
				lineStyle : {
					color : '#376df4',
					width : 2,
					opacity : 1
				}
			}
		},
		xAxis : {
			type : 'category',
			data : [],
			axisLine : {
				lineStyle : {
					color : '#8392A5'
				}
			}
		},
		yAxis : {
			scale : true,
			axisLine : {
				lineStyle : {
					color : '#8392A5'
				}
			},
			splitLine : {
				show : false
			}
		},
		dataZoom : [
				{
					textStyle : {
						color : '#8392A5'
					},
					handleIcon : 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
					handleSize : '80%',
					dataBackground : {
						areaStyle : {
							color : '#8392A5'
						},
						lineStyle : {
							opacity : 0.8,
							color : '#8392A5'
						}
					},
					handleStyle : {
						color : '#fff',
						shadowBlur : 3,
						shadowColor : 'rgba(0, 0, 0, 0.6)',
						shadowOffsetX : 2,
						shadowOffsetY : 2
					}
				}, {
					type : 'inside'
				} ],
		animation : false,
		series : [ {
			type : 'candlestick',
			name : '日K',
			data : [],
			itemStyle : {
				normal : {
					color : '#FD1050',
					color0 : '#0CF49B',
					borderColor : '#FD1050',
					borderColor0 : '#0CF49B'
				}
			}
		}, {
			name : 'MA5',
			type : 'line',
			data : [],
			smooth : true,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			}
		}, {
			name : 'MA10',
			type : 'line',
			data : [],
			smooth : true,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			}
		}, {
			name : 'MA20',
			type : 'line',
			data : [],
			smooth : true,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			}
		}, {
			name : 'MA30',
			type : 'line',
			data : [],
			smooth : true,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			}
		} ]
	};
	// 数据处理
	var rawData = [
			[ '2015/12/31', '3570.47', '3539.18', '-33.69', '-0.94%',
					'3538.35', '3580.6', '176963664', '25403106', '-' ],
			[ '2015/12/30', '3566.73', '3572.88', '9.14', '0.26%', '3538.11',
					'3573.68', '187889600', '26778766', '-' ],
			[ '2015/12/29', '3528.4', '3563.74', '29.96', '0.85%', '3515.52',
					'3564.17', '182551920', '25093890', '-' ],
			[ '2015/12/28', '3635.77', '3533.78', '-94.13', '-2.59%',
					'3533.78', '3641.59', '269983264', '36904280', '-' ],
			[ '2015/12/25', '3614.05', '3627.91', '15.43', '0.43%', '3601.74',
					'3635.26', '198451120', '27466004', '-' ],
			[ '2015/12/24', '3631.31', '3612.49', '-23.6', '-0.65%', '3572.28',
					'3640.22', '227785216', '31542126', '-' ],
			[ '2015/12/23', '3653.28', '3636.09', '-15.68', '-0.43%',
					'3633.03', '3684.57', '298201792', '41990292', '-' ],
			[ '2015/12/22', '3645.99', '3651.77', '9.3', '0.26%', '3616.87',
					'3652.63', '261178752', '36084604', '-' ],
			[ '2015/12/21', '3568.58', '3642.47', '63.51', '1.77%', '3565.75',
					'3651.06', '299849280', '39831696', '-' ],
			[ '2015/12/18', '3574.94', '3578.96', '-1.03', '-0.03%', '3568.16',
					'3614.7', '273707904', '36538580', '-' ],
			[ '2015/12/17', '3533.63', '3580', '63.81', '1.81%', '3533.63',
					'3583.41', '283856480', '38143960', '-' ],
			[ '2015/12/16', '3522.09', '3516.19', '5.83', '0.17%', '3506.29',
					'3538.69', '193482304', '26528864', '-' ],
			[ '2015/12/15', '3518.13', '3510.35', '-10.31', '-0.29%',
					'3496.85', '3529.96', '200471344', '27627494', '-' ],
			[ '2015/12/14', '3403.51', '3520.67', '86.09', '2.51%', '3399.28',
					'3521.78', '215374624', '27921354', '-' ],
			[ '2015/12/11', '3441.6', '3434.58', '-20.91', '-0.61%', '3410.92',
					'3455.55', '182908880', '24507642', '-' ],
			[ '2015/12/10', '3469.81', '3455.5', '-16.94', '-0.49%', '3446.27',
					'3503.65', '200427520', '27949970', '-' ],
			[ '2015/12/9', '3462.58', '3472.44', '2.37', '0.07%', '3454.88',
					'3495.7', '195698848', '26785488', '-' ],
			[ '2015/12/8', '3518.65', '3470.07', '-66.86', '-1.89%', '3466.79',
					'3518.65', '224367312', '29782174', '-' ],
			[ '2015/12/7', '3529.81', '3536.93', '11.94', '0.34%', '3506.62',
					'3543.95', '208302576', '28056158', '-' ],
			[ '2015/12/4', '3558.15', '3524.99', '-59.83', '-1.67%', '3510.41',
					'3568.97', '251736416', '31976682', '-' ],
			[ '2015/12/3', '3525.73', '3584.82', '47.92', '1.35%', '3517.23',
					'3591.73', '281111232', '33885908', '-' ],
			[ '2015/12/2', '3450.28', '3536.91', '80.6', '2.33%', '3427.66',
					'3538.85', '301491488', '36918304', '-' ],
			[ '2015/12/1', '3442.44', '3456.31', '10.9', '0.32%', '3417.54',
					'3483.41', '252390752', '33025674', '-' ],
			[ '2015/11/30', '3433.85', '3445.4', '9.1', '0.26%', '3327.81',
					'3470.37', '304197888', '38750364', '-' ],
			[ '2015/11/27', '3616.54', '3436.3', '-199.25', '-5.48%',
					'3412.43', '3621.9', '354287520', '46431124', '-' ],
			[ '2015/11/26', '3659.57', '3635.55', '-12.38', '-0.34%',
					'3629.86', '3668.38', '306761600', '42624744', '-' ],
			[ '2015/11/25', '3614.07', '3647.93', '31.82', '0.88%', '3607.52',
					'3648.37', '273024864', '38080292', '-' ],
			[ '2015/11/24', '3602.89', '3616.11', '5.79', '0.16%', '3563.1',
					'3616.48', '248810512', '32775852', '-' ],
			[ '2015/11/23', '3630.87', '3610.31', '-20.18', '-0.56%',
					'3598.87', '3654.75', '315997472', '41414824', '-' ],
			[ '2015/11/20', '3620.79', '3630.5', '13.44', '0.37%', '3607.92',
					'3640.53', '310801984', '41391088', '-' ],
			[ '2015/11/19', '3573.78', '3617.06', '48.59', '1.36%', '3561.04',
					'3618.21', '247915584', '32844260', '-' ],
			[ '2015/11/18', '3605.06', '3568.47', '-36.33', '-1.01%', '3558.7',
					'3617.07', '297580736', '39233876', '-' ],
			[ '2015/11/17', '3629.98', '3604.8', '-2.16', '-0.06%', '3598.07',
					'3678.27', '383575456', '52152036', '-' ],
			[ '2015/11/16', '3522.46', '3606.96', '26.12', '0.73%', '3519.42',
					'3607.61', '276187040', '36942184', '-' ],
			[ '2015/11/13', '3600.76', '3580.84', '-52.06', '-1.43%',
					'3564.81', '3632.56', '345870944', '46866864', '-' ],
			[ '2015/11/12', '3656.82', '3632.9', '-17.35', '-0.48%', '3603.23',
					'3659.31', '361717600', '48283260', '-' ],
			[ '2015/11/11', '3635', '3650.25', '9.76', '0.27%', '3605.62',
					'3654.88', '360972672', '46782220', '-' ],
			[ '2015/11/10', '3617.4', '3640.49', '-6.4', '-0.18%', '3607.89',
					'3669.53', '429746592', '56005512', '-' ],
			[ '2015/11/9', '3588.5', '3646.88', '56.85', '1.58%', '3588.5',
					'3673.76', '503016704', '63618404', '-' ],
			[ '2015/11/6', '3514.44', '3590.03', '67.21', '1.91%', '3508.83',
					'3596.38', '429167040', '54328220', '-' ],
			[ '2015/11/5', '3459.22', '3522.82', '63.18', '1.83%', '3455.53',
					'3585.66', '553254976', '67867464', '-' ],
			[ '2015/11/4', '3325.62', '3459.64', '142.94', '4.31%', '3325.62',
					'3459.65', '339078752', '42610440', '-' ],
			[ '2015/11/3', '3330.32', '3316.7', '-8.39', '-0.25%', '3302.18',
					'3346.27', '192897440', '24436056', '-' ],
			[ '2015/11/2', '3337.58', '3325.08', '-57.48', '-1.70%', '3322.31',
					'3391.06', '230951136', '28601932', '-' ],
			[ '2015/10/30', '3380.28', '3382.56', '-4.75', '-0.14%', '3346.59',
					'3417.2', '243595120', '30726678', '-' ],
			[ '2015/10/29', '3387.77', '3387.32', '12.12', '0.36%', '3362.51',
					'3411.71', '235676016', '29450842', '-' ],
			[ '2015/10/28', '3417.01', '3375.2', '-59.14', '-1.72%', '3367.23',
					'3439.76', '293523296', '36165620', '-' ],
			[ '2015/10/27', '3409.14', '3434.34', '4.76', '0.14%', '3332.62',
					'3441.57', '328172768', '40888724', '-' ],
			[ '2015/10/26', '3448.65', '3429.58', '17.15', '0.50%', '3402',
					'3457.52', '365560864', '45394252', '-' ],
			[ '2015/10/23', '3377.55', '3412.43', '43.7', '1.30%', '3360.22',
					'3422.02', '347372864', '42526308', '-' ],
			[ '2015/10/22', '3292.29', '3368.74', '48.06', '1.45%', '3282.99',
					'3373.78', '323739328', '37545200', '-' ],
			[ '2015/10/21', '3428.56', '3320.68', '-104.65', '-3.06%',
					'3265.44', '3447.26', '458455424', '51850924', '-' ],
			[ '2015/10/20', '3377.55', '3425.33', '38.63', '1.14%', '3357.86',
					'3425.52', '318973760', '38358252', '-' ],
			[ '2015/10/19', '3401.63', '3386.7', '-4.65', '-0.14%', '3355.57',
					'3423.4', '378112160', '45330364', '-' ],
			[ '2015/10/16', '3358.3', '3391.35', '53.28', '1.60%', '3334.85',
					'3393.02', '395460576', '45944784', '-' ],
			[ '2015/10/15', '3255.03', '3338.07', '75.63', '2.32%', '3254.39',
					'3338.3', '316283840', '36256556', '-' ],
			[ '2015/10/14', '3280.02', '3262.44', '-30.79', '-0.93%',
					'3256.25', '3307.32', '295077760', '33027752', '-' ],
			[ '2015/10/13', '3262.16', '3293.23', '5.57', '0.17%', '3253.25',
					'3298.63', '297153120', '33480608', '-' ],
			[ '2015/10/12', '3193.54', '3287.66', '104.51', '3.28%', '3188.41',
					'3318.71', '386294688', '43554100', '-' ],
			[ '2015/10/9', '3146.64', '3183.15', '39.79', '1.27%', '3137.79',
					'3192.72', '234851456', '25637910', '-' ],
			[ '2015/10/8', '3156.07', '3143.36', '90.58', '2.97%', '3133.13',
					'3172.28', '234276048', '25883034', '-' ],
			[ '2015/9/30', '3052.84', '3052.78', '14.64', '0.48%', '3039.74',
					'3073.3', '146642448', '15656919', '-' ],
			[ '2015/9/29', '3055.22', '3038.14', '-62.62', '-2.02%', '3021.16',
					'3068.3', '163222672', '16968660', '-' ],
			[ '2015/9/28', '3085.57', '3100.76', '8.41', '0.27%', '3042.31',
					'3103.07', '156727536', '16642240', '-' ],
			[ '2015/9/25', '3130.85', '3092.35', '-50.34', '-1.60%', '3063',
					'3149.95', '236263872', '24897112', '-' ],
			[ '2015/9/24', '3126.49', '3142.69', '26.8', '0.86%', '3109.69',
					'3151.16', '212887712', '23136904', '-' ],
			[ '2015/9/23', '3137.72', '3115.89', '-69.73', '-2.19%', '3104.74',
					'3164.04', '236322672', '25756004', '-' ],
			[ '2015/9/22', '3161.32', '3185.62', '29.08', '0.92%', '3152.48',
					'3213.48', '274786176', '30507132', '-' ],
			[ '2015/9/21', '3072.09', '3156.54', '58.62', '1.89%', '3060.86',
					'3159.88', '239897360', '25979668', '-' ],
			[ '2015/9/18', '3100.28', '3097.92', '11.86', '0.38%', '3070.34',
					'3122.05', '209175392', '21844244', '-' ],
			[ '2015/9/17', '3131.98', '3086.06', '-66.2', '-2.10%', '3085.31',
					'3204.7', '317602912', '33739328', '-' ],
			[ '2015/9/16', '2998.04', '3152.26', '147.09', '4.89%', '2983.54',
					'3182.93', '277524512', '28199226', '-' ],
			[ '2015/9/15', '3043.8', '3005.17', '-109.63', '-3.52%', '2983.92',
					'3081.7', '249194448', '24390460', '-' ],
			[ '2015/9/14', '3221.17', '3114.8', '-85.44', '-2.67%', '3049.23',
					'3229.48', '346631168', '37357680', '-' ],
			[ '2015/9/11', '3189.48', '3200.23', '2.34', '0.07%', '3163.45',
					'3223.76', '224557808', '25276946', '-' ],
			[ '2015/9/10', '3190.55', '3197.89', '-45.2', '-1.39%', '3178.9',
					'3243.28', '273261760', '29958108', '-' ],
			[ '2015/9/9', '3182.55', '3243.09', '72.64', '2.29%', '3165.7',
					'3256.74', '375328000', '41299144', '-' ],
			[ '2015/9/8', '3054.44', '3170.45', '90.03', '2.92%', '3011.12',
					'3174.71', '255415456', '26391038', '-' ],
			[ '2015/9/7', '3149.38', '3080.42', '-79.75', '-2.52%', '3066.3',
					'3217.58', '296468096', '30268972', '-' ],
			[ '2015/9/2', '3027.68', '3160.17', '-6.46', '-0.20%', '3019.09',
					'3194.48', '438170176', '42326236', '-' ],
			[ '2015/9/1', '3157.83', '3166.62', '-39.36', '-1.23%', '3053.74',
					'3180.33', '432432448', '42041164', '-' ],
			[ '2015/8/31', '3203.56', '3205.99', '-26.36', '-0.82%', '3109.16',
					'3207.86', '397431392', '43106860', '-' ],
			[ '2015/8/28', '3125.26', '3232.35', '148.76', '4.82%', '3102.95',
					'3235.84', '443136928', '47463100', '-' ],
			[ '2015/8/27', '2978.03', '3083.59', '156.3', '5.34%', '2906.49',
					'3085.42', '400308384', '40428928', '-' ],
			[ '2015/8/26', '2980.79', '2927.29', '-37.68', '-1.27%', '2850.71',
					'3092.04', '466699680', '46178896', '-' ],
			[ '2015/8/25', '3004.13', '2964.97', '-244.94', '-7.63%',
					'2947.94', '3123.03', '352325088', '35873576', '-' ],
			[ '2015/8/24', '3373.48', '3209.91', '-297.84', '-8.49%',
					'3191.88', '3388.36', '334671776', '35881888', '-' ],
			[ '2015/8/21', '3609.96', '3507.74', '-156.55', '-4.27%',
					'3490.54', '3652.84', '369920480', '45061648', '-' ],
			[ '2015/8/20', '3754.57', '3664.29', '-129.82', '-3.42%',
					'3663.61', '3788.01', '390063072', '50119500', '-' ],
			[ '2015/8/19', '3646.8', '3794.11', '45.95', '1.23%', '3558.38',
					'3811.43', '475396224', '59951332', '-' ],
			[ '2015/8/18', '3999.13', '3748.16', '-245.5', '-6.15%', '3743.39',
					'4006.34', '543770816', '72246720', '-' ],
			[ '2015/8/17', '3947.84', '3993.67', '28.33', '0.71%', '3907.4',
					'3994.54', '460432064', '62632768', '-' ],
			[ '2015/8/14', '3976.41', '3965.33', '10.78', '0.27%', '3939.83',
					'4000.68', '467988224', '64746644', '-' ],
			[ '2015/8/13', '3869.91', '3954.56', '68.24', '1.76%', '3838.16',
					'3955.79', '430073280', '57868552', '-' ],
			[ '2015/8/12', '3881.23', '3886.32', '-41.59', '-1.06%', '3871.14',
					'3937.77', '442688256', '59705028', '-' ],
			[ '2015/8/11', '3928.81', '3927.91', '-0.51', '-0.01%', '3891.18',
					'3970.34', '538923456', '71228992', '-' ],
			[ '2015/8/10', '3786.03', '3928.42', '184.21', '4.92%', '3775.85',
					'3943.62', '497304320', '65262204', '-' ],
			[ '2015/8/7', '3692.61', '3744.2', '82.67', '2.26%', '3686.3',
					'3756.74', '340757184', '44548504', '-' ],
			[ '2015/8/6', '3625.5', '3661.54', '-33.03', '-0.89%', '3614.74',
					'3710.57', '274074656', '35751512', '-' ],
			[ '2015/8/5', '3745.65', '3694.57', '-61.97', '-1.65%', '3676.39',
					'3782.35', '366423008', '48385028', '-' ],
			[ '2015/8/4', '3621.85', '3756.54', '133.64', '3.69%', '3601.29',
					'3757.03', '362901664', '46403624', '-' ],
			[ '2015/8/3', '3614.99', '3622.91', '-40.82', '-1.11%', '3549.5',
					'3648.94', '363968704', '44599160', '-' ],
			[ '2015/7/31', '3655.67', '3663.73', '-42.04', '-1.13%', '3620.17',
					'3729.51', '350955712', '46047224', '-' ],
			[ '2015/7/30', '3773.79', '3705.77', '-83.4', '-2.20%', '3685.96',
					'3844.37', '457943232', '61597792', '-' ],
			[ '2015/7/29', '3689.82', '3789.17', '126.17', '3.44%', '3612.06',
					'3792.07', '434352096', '55749196', '-' ],
			[ '2015/7/28', '3573.14', '3663', '-62.56', '-1.68%', '3537.36',
					'3762.53', '563330048', '68505752', '-' ],
			[ '2015/7/27', '3985.57', '3725.56', '-345.35', '-8.48%',
					'3720.44', '4051.16', '556003264', '72129808', '-' ],
			[ '2015/7/24', '4124.75', '4070.91', '-53.02', '-1.29%', '4044.83',
					'4184.45', '627424896', '84302208', '-' ],
			[ '2015/7/23', '4022.27', '4123.92', '97.88', '2.43%', '4019.04',
					'4132.61', '563585984', '74353184', '-' ],
			[ '2015/7/22', '3996.43', '4026.04', '8.37', '0.21%', '3960.86',
					'4042.34', '520732224', '67883192', '-' ],
			[ '2015/7/21', '3939.9', '4017.67', '25.57', '0.64%', '3912.8',
					'4041.82', '504288000', '64641684', '-' ],
			[ '2015/7/20', '3948.42', '3992.11', '34.76', '0.88%', '3927.12',
					'4021.33', '539106688', '68825560', '-' ],
			[ '2015/7/17', '3831.42', '3957.35', '134.18', '3.51%', '3814.15',
					'3994.48', '481726272', '59306700', '-' ],
			[ '2015/7/16', '3758.5', '3823.18', '17.47', '0.46%', '3688.44',
					'3877.51', '492256224', '56985892', '-' ],
			[ '2015/7/15', '3874.97', '3805.7', '-118.78', '-3.03%', '3741.25',
					'3914.27', '601301312', '70053656', '-' ],
			[ '2015/7/14', '3958.37', '3924.49', '-45.9', '-1.16%', '3855.56',
					'4035.43', '670558784', '83007464', '-' ],
			[ '2015/7/13', '3918.99', '3970.39', '92.58', '2.39%', '3858.64',
					'4030.19', '643489024', '78243056', '-' ],
			[ '2015/7/10', '3707.46', '3877.8', '168.47', '4.54%', '3677.43',
					'3959.22', '586364288', '68043416', '-' ],
			[ '2015/7/9', '3432.45', '3709.33', '202.14', '5.76%', '3373.54',
					'3748.48', '656914624', '67331096', '-' ],
			[ '2015/7/8', '3467.4', '3507.19', '-219.93', '-5.90%', '3421.53',
					'3599.25', '680356928', '70024832', '-' ],
			[ '2015/7/7', '3654.78', '3727.12', '-48.79', '-1.29%', '3585.4',
					'3750.57', '698818752', '77607256', '-' ],
			[ '2015/7/6', '3975.21', '3775.91', '89', '2.41%', '3653.04',
					'3975.21', '831139264', '94342040', '-' ],
			[ '2015/7/3', '3793.71', '3686.92', '-225.85', '-5.77%', '3629.56',
					'3927.13', '548163072', '64805412', '-' ],
			[ '2015/7/2', '4058.62', '3912.77', '-140.93', '-3.48%', '3795.25',
					'4080.39', '586015616', '73600680', '-' ],
			[ '2015/7/1', '4214.15', '4053.7', '-223.52', '-5.23%', '4043.37',
					'4317.05', '598769408', '83807088', '-' ],
			[ '2015/6/30', '4006.75', '4277.22', '224.19', '5.53%', '3847.88',
					'4279.97', '709176576', '94152464', '-' ],
			[ '2015/6/29', '4289.77', '4053.03', '-139.84', '-3.34%',
					'3875.05', '4297.47', '673786368', '90427136', '-' ],
			[ '2015/6/26', '4399.93', '4192.87', '-334.91', '-7.40%',
					'4139.53', '4456.9', '565217920', '78783568', '-' ],
			[ '2015/6/25', '4711.76', '4527.78', '-162.37', '-3.46%',
					'4483.55', '4720.7', '572797568', '86537976', '-' ],
			[ '2015/6/24', '4604.58', '4690.15', '113.66', '2.48%', '4552.13',
					'4691.77', '543003712', '81506272', '-' ],
			[ '2015/6/23', '4471.61', '4576.49', '98.13', '2.19%', '4264.77',
					'4577.94', '473526112', '69361720', '-' ],
			[ '2015/6/19', '4689.93', '4478.36', '-306.99', '-6.42%', '4476.5',
					'4744.08', '452689632', '68545816', '-' ],
			[ '2015/6/18', '4942.52', '4785.36', '-182.54', '-3.67%',
					'4780.87', '4966.77', '507440896', '78584504', '-' ],
			[ '2015/6/17', '4890.55', '4967.9', '80.47', '1.65%', '4767.22',
					'4983.66', '537101120', '83026672', '-' ],
			[ '2015/6/16', '5004.41', '4887.43', '-175.56', '-3.47%', '4842.1',
					'5029.68', '550801408', '89542056', '-' ],
			[ '2015/6/15', '5174.42', '5062.99', '-103.36', '-2.00%',
					'5048.74', '5176.79', '637803968', '106499200', '-' ],
			[ '2015/6/12', '5143.34', '5166.35', '44.76', '0.87%', '5103.4',
					'5178.19', '625627904', '106016744', '-' ],
			[ '2015/6/11', '5101.44', '5121.59', '15.56', '0.30%', '5050.76',
					'5122.46', '563990528', '97467056', '-' ],
			[ '2015/6/10', '5049.2', '5106.04', '-7.5', '-0.15%', '5001.49',
					'5164.16', '596969024', '100642824', '-' ],
			[ '2015/6/9', '5145.98', '5113.53', '-18.35', '-0.36%', '5042.96',
					'5147.45', '729893824', '115080864', '-' ],
			[ '2015/6/8', '5045.69', '5131.88', '108.78', '2.17%', '4997.48',
					'5146.95', '855035072', '130992456', '-' ],
			[ '2015/6/5', '5016.09', '5023.1', '75.99', '1.54%', '4898.07',
					'5051.63', '772240832', '123230064', '-' ],
			[ '2015/6/4', '4912.95', '4947.1', '37.12', '0.76%', '4647.41',
					'4947.96', '674952384', '105227024', '-' ],
			[ '2015/6/3', '4924.38', '4909.98', '-0.55', '-0.01%', '4822.44',
					'4942.06', '611453824', '101018000', '-' ],
			[ '2015/6/2', '4844.7', '4910.53', '81.79', '1.69%', '4797.55',
					'4911.57', '623748096', '99874576', '-' ],
			[ '2015/6/1', '4633.1', '4828.74', '216.99', '4.71%', '4615.23',
					'4829.5', '593389056', '93445544', '-' ],
			[ '2015/5/29', '4603.47', '4611.74', '-8.52', '-0.18%', '4431.56',
					'4698.19', '611262400', '95536560', '-' ],
			[ '2015/5/28', '4943.74', '4620.27', '-321.45', '-6.50%',
					'4614.24', '4986.5', '782964544', '124792600', '-' ],
			[ '2015/5/27', '4932.85', '4941.71', '30.82', '0.63%', '4857.06',
					'4958.16', '681165376', '111626184', '-' ],
			[ '2015/5/26', '4854.85', '4910.9', '97.1', '2.02%', '4779.08',
					'4911.69', '704892864', '113850936', '-' ],
			[ '2015/5/25', '4660.08', '4813.8', '156.2', '3.35%', '4656.83',
					'4814.67', '682461376', '107929560', '-' ],
			[ '2015/5/22', '4584.98', '4657.6', '128.17', '2.83%', '4562.99',
					'4658.27', '655591296', '100717320', '-' ],
			[ '2015/5/21', '4456.44', '4529.42', '83.13', '1.87%', '4438.26',
					'4530.48', '464996512', '72908056', '-' ],
			[ '2015/5/20', '4434.98', '4446.29', '28.74', '0.65%', '4432.28',
					'4520.54', '514106208', '80608056', '-' ],
			[ '2015/5/19', '4285.78', '4417.55', '134.06', '3.13%', '4285.78',
					'4418.4', '436735232', '69381256', '-' ],
			[ '2015/5/18', '4277.9', '4283.49', '-25.2', '-0.58%', '4260.51',
					'4324.83', '380057440', '59455952', '-' ],
			[ '2015/5/15', '4366.82', '4308.69', '-69.62', '-1.59%', '4278.55',
					'4366.82', '439706208', '66596564', '-' ],
			[ '2015/5/14', '4372.82', '4378.31', '2.55', '0.06%', '4329.04',
					'4397.75', '449077952', '66988224', '-' ],
			[ '2015/5/13', '4402.38', '4375.76', '-25.46', '-0.58%', '4342.48',
					'4415.63', '510490464', '78075496', '-' ],
			[ '2015/5/12', '4342.37', '4401.22', '67.64', '1.56%', '4317.98',
					'4402.31', '521866400', '79346376', '-' ],
			[ '2015/5/11', '4231.27', '4333.58', '127.67', '3.04%', '4187.82',
					'4334.88', '488750528', '71524664', '-' ],
			[ '2015/5/8', '4152.98', '4205.92', '93.7', '2.28%', '4099.04',
					'4206.86', '397428096', '55964868', '-' ],
			[ '2015/5/7', '4197.9', '4112.21', '-117.05', '-2.77%', '4108.01',
					'4213.76', '394566656', '54020632', '-' ],
			[ '2015/5/6', '4311.64', '4229.27', '-69.44', '-1.62%', '4187.37',
					'4376.35', '481732992', '71653624', '-' ],
			[ '2015/5/5', '4479.85', '4298.71', '-181.76', '-4.06%', '4282.24',
					'4488.87', '572858624', '80556608', '-' ],
			[ '2015/5/4', '4441.34', '4480.46', '38.81', '0.87%', '4387.43',
					'4487.57', '494173376', '71754080', '-' ],
			[ '2015/4/30', '4483.01', '4441.65', '-34.96', '-0.78%', '4441.05',
					'4507.34', '526728000', '77434920', '-' ],
			[ '2015/4/29', '4446.12', '4476.62', '0.4', '0.01%', '4398.64',
					'4499.94', '519834208', '75240176', '-' ],
			[ '2015/4/28', '4527.63', '4476.21', '-51.18', '-1.13%', '4432.9',
					'4572.39', '767676416', '106117216', '-' ],
			[ '2015/4/27', '4441.93', '4527.4', '133.71', '3.04%', '4441.93',
					'4529.73', '671088512', '97524208', '-' ],
			[ '2015/4/24', '4355.95', '4393.69', '-20.82', '-0.47%', '4318.12',
					'4416.38', '628555008', '91687296', '-' ],
			[ '2015/4/23', '4414.48', '4414.51', '16.01', '0.36%', '4358.84',
					'4444.41', '667344640', '96302488', '-' ],
			[ '2015/4/22', '4304.6', '4398.49', '104.87', '2.44%', '4297.95',
					'4400.19', '680305088', '97687696', '-' ],
			[ '2015/4/21', '4212.19', '4293.62', '76.55', '1.82%', '4188.57',
					'4294.38', '634470656', '86244776', '-' ],
			[ '2015/4/20', '4301.35', '4217.08', '-70.22', '-1.64%', '4190.68',
					'4356', '857132800', '100000000', '-' ],
			[ '2015/4/17', '4254.72', '4287.3', '92.47', '2.20%', '4238.91',
					'4317.22', '701706240', '91563304', '-' ],
			[ '2015/4/16', '4055.92', '4194.82', '110.66', '2.71%', '4031.24',
					'4195.31', '551242944', '71208248', '-' ],
			[ '2015/4/15', '4135.65', '4084.16', '-51.4', '-1.24%', '4069.01',
					'4175.49', '613005824', '77312592', '-' ],
			[ '2015/4/14', '4125.78', '4135.56', '13.85', '0.34%', '4091.26',
					'4168.35', '610683520', '81464520', '-' ],
			[ '2015/4/13', '4072.72', '4121.71', '87.4', '2.17%', '4057.29',
					'4128.07', '589814208', '78166736', '-' ],
			[ '2015/4/10', '3947.49', '4034.31', '76.78', '1.94%', '3929.32',
					'4040.35', '484283648', '66850416', '-' ],
			[ '2015/4/9', '4006.13', '3957.53', '-37.28', '-0.93%', '3900.03',
					'4016.4', '585176832', '81671088', '-' ],
			[ '2015/4/8', '3976.53', '3994.81', '33.43', '0.84%', '3903.65',
					'4000.22', '618085440', '83915928', '-' ],
			[ '2015/4/7', '3899.42', '3961.38', '97.45', '2.52%', '3891.73',
					'3961.67', '570447552', '74642400', '-' ],
			[ '2015/4/3', '3803.38', '3863.93', '38.14', '1.00%', '3792.21',
					'3864.41', '473033312', '63565136', '-' ],
			[ '2015/4/2', '3827.69', '3825.78', '15.49', '0.41%', '3775.89',
					'3835.45', '479299680', '63202892', '-' ],
			[ '2015/4/1', '3748.34', '3810.29', '62.4', '1.66%', '3742.21',
					'3817.08', '447458304', '59241832', '-' ],
			[ '2015/3/31', '3822.99', '3747.9', '-38.67', '-1.02%', '3737.04',
					'3835.57', '561676032', '72129488', '-' ],
			[ '2015/3/30', '3710.61', '3786.57', '95.47', '2.59%', '3710.61',
					'3795.94', '564702400', '69212536', '-' ],
			[ '2015/3/27', '3686.13', '3691.1', '9', '0.24%', '3656.83',
					'3710.48', '408945184', '50929848', '-' ],
			[ '2015/3/26', '3641.94', '3682.09', '21.37', '0.58%', '3615.01',
					'3707.32', '488647200', '61951560', '-' ],
			[ '2015/3/25', '3680.95', '3660.73', '-30.68', '-0.83%', '3634.56',
					'3693.15', '521886336', '64549892', '-' ],
			[ '2015/3/24', '3692.57', '3691.41', '3.68', '0.10%', '3600.7',
					'3715.87', '639554688', '75488472', '-' ],
			[ '2015/3/23', '3640.1', '3687.73', '70.41', '1.95%', '3635.49',
					'3688.25', '536062816', '66157464', '-' ],
			[ '2015/3/20', '3587.08', '3617.32', '35.05', '0.98%', '3569.38',
					'3632.34', '516661664', '65177196', '-' ],
			[ '2015/3/19', '3576.02', '3582.27', '4.97', '0.14%', '3546.84',
					'3600.68', '537346624', '61224968', '-' ],
			[ '2015/3/18', '3510.5', '3577.3', '74.45', '2.13%', '3503.85',
					'3577.66', '545217152', '61736700', '-' ],
			[ '2015/3/17', '3469.6', '3502.85', '53.54', '1.55%', '3459.69',
					'3504.12', '520939520', '60150068', '-' ],
			[ '2015/3/16', '3391.16', '3449.3', '76.39', '2.26%', '3377.09',
					'3449.3', '399132416', '47935532', '-' ],
			[ '2015/3/13', '3359.49', '3372.91', '23.59', '0.70%', '3352.15',
					'3391.25', '328410144', '37404140', '-' ],
			[ '2015/3/12', '3314.81', '3349.32', '58.42', '1.78%', '3300.49',
					'3360.05', '357295104', '40719240', '-' ],
			[ '2015/3/11', '3289.59', '3290.9', '4.83', '0.15%', '3278.47',
					'3325.05', '282985536', '32757308', '-' ],
			[ '2015/3/10', '3289.08', '3286.07', '-16.34', '-0.49%', '3277.09',
					'3309.92', '285817568', '32995596', '-' ],
			[ '2015/3/9', '3224.31', '3302.41', '61.22', '1.89%', '3198.37',
					'3307.7', '321495456', '35992752', '-' ],
			[ '2015/3/6', '3248.04', '3241.19', '-7.29', '-0.22%', '3234.53',
					'3266.93', '282915808', '32834414', '-' ],
			[ '2015/3/5', '3264.08', '3248.48', '-31.06', '-0.95%', '3221.67',
					'3266.64', '320663584', '37358000', '-' ],
			[ '2015/3/4', '3264.18', '3279.53', '16.48', '0.51%', '3250.48',
					'3286.59', '293639520', '34678976', '-' ],
			[ '2015/3/3', '3317.7', '3263.05', '-73.23', '-2.20%', '3260.43',
					'3317.7', '382044608', '44159348', '-' ],
			[ '2015/3/2', '3332.72', '3336.28', '25.98', '0.78%', '3298.67',
					'3336.76', '346445664', '41025956', '-' ],
			[ '2015/2/27', '3296.83', '3310.3', '11.94', '0.36%', '3291.01',
					'3324.55', '299163712', '33501958', '-' ],
			[ '2015/2/26', '3222.15', '3298.36', '69.52', '2.15%', '3202.19',
					'3300.62', '301263872', '33434746', '-' ],
			[ '2015/2/25', '3256.48', '3228.84', '-18.06', '-0.56%', '3215.55',
					'3257.22', '233348096', '26514336', '-' ],
			[ '2015/2/17', '3230.88', '3246.91', '24.54', '0.76%', '3230.77',
					'3255.73', '228332624', '26334004', '-' ],
			[ '2015/2/16', '3206.14', '3222.36', '18.54', '0.58%', '3195.88',
					'3228.85', '223797440', '26595070', '-' ],
			[ '2015/2/13', '3186.81', '3203.83', '30.41', '0.96%', '3182.79',
					'3237.16', '261290432', '29301768', '-' ],
			[ '2015/2/12', '3157.96', '3173.42', '15.71', '0.50%', '3134.24',
					'3181.77', '194592320', '22969158', '-' ],
			[ '2015/2/11', '3145.76', '3157.7', '16.11', '0.51%', '3139.05',
					'3166.42', '172840096', '21086256', '-' ],
			[ '2015/2/10', '3090.49', '3141.59', '46.47', '1.50%', '3084.25',
					'3142.1', '193817136', '22508492', '-' ],
			[ '2015/2/9', '3063.51', '3095.12', '19.22', '0.62%', '3049.11',
					'3119.03', '206108384', '24071968', '-' ],
			[ '2015/2/6', '3120.09', '3075.91', '-60.62', '-1.93%', '3052.94',
					'3129.54', '246749680', '26650278', '-' ],
			[ '2015/2/5', '3251.21', '3136.53', '-37.6', '-1.18%', '3135.82',
					'3251.21', '306139296', '34826696', '-' ],
			[ '2015/2/4', '3212.82', '3174.13', '-30.78', '-0.96%', '3171.14',
					'3238.98', '249098080', '29015516', '-' ],
			[ '2015/2/3', '3156.09', '3204.91', '76.61', '2.45%', '3129.73',
					'3207.94', '248192160', '28335594', '-' ],
			[ '2015/2/2', '3148.14', '3128.3', '-82.06', '-2.56%', '3122.57',
					'3175.13', '250861632', '26684996', '-' ],
			[ '2015/1/30', '3273.75', '3210.36', '-51.94', '-1.59%', '3210.31',
					'3288.5', '258312544', '28426564', '-' ],
			[ '2015/1/29', '3259', '3262.3', '-43.43', '-1.31%', '3234.24',
					'3286.79', '274658624', '29642450', '-' ],
			[ '2015/1/28', '3325.72', '3305.74', '-47.22', '-1.41%', '3294.66',
					'3354.8', '301927104', '34156428', '-' ],
			[ '2015/1/27', '3389.85', '3352.96', '-30.22', '-0.89%', '3290.22',
					'3390.22', '374517568', '41829884', '-' ],
			[ '2015/1/26', '3347.26', '3383.18', '31.42', '0.94%', '3321.31',
					'3384.8', '317540992', '35842744', '-' ],
			[ '2015/1/23', '3357.1', '3351.76', '8.42', '0.25%', '3328.29',
					'3406.79', '366249248', '42097952', '-' ],
			[ '2015/1/22', '3327.32', '3343.34', '19.73', '0.59%', '3293.98',
					'3352.38', '353382976', '40787408', '-' ],
			[ '2015/1/21', '3189.08', '3323.61', '150.56', '4.74%', '3178.34',
					'3337', '410956032', '47375868', '-' ],
			[ '2015/1/20', '3114.56', '3173.05', '56.7', '1.82%', '3100.48',
					'3190.25', '357080800', '41629524', '-' ],
			[ '2015/1/19', '3189.73', '3116.35', '-260.14', '-7.70%',
					'3095.07', '3262.21', '401098784', '40988600', '-' ],
			[ '2015/1/16', '3343.6', '3376.5', '40.04', '1.20%', '3340.49',
					'3400.32', '339876768', '39225388', '-' ],
			[ '2015/1/15', '3224.07', '3336.46', '114.02', '3.54%', '3207.54',
					'3337.08', '282546240', '33061054', '-' ],
			[ '2015/1/14', '3242.34', '3222.44', '-12.86', '-0.40%', '3193.98',
					'3268.48', '240190752', '26720452', '-' ],
			[ '2015/1/13', '3223.54', '3235.3', '5.98', '0.19%', '3214.41',
					'3259.39', '230725760', '27358878', '-' ],
			[ '2015/1/12', '3258.21', '3229.32', '-56.1', '-1.71%', '3191.58',
					'3275.19', '322064672', '36627308', '-' ],
			[ '2015/1/9', '3276.97', '3285.41', '-8.04', '-0.24%', '3267.51',
					'3404.83', '410240864', '45864804', '-' ],
			[ '2015/1/8', '3371.96', '3293.46', '-80.5', '-2.39%', '3285.09',
					'3381.57', '371131200', '39923032', '-' ],
			[ '2015/1/7', '3326.65', '3373.95', '22.51', '0.67%', '3312.21',
					'3374.9', '391918880', '43641672', '-' ],
			[ '2015/1/6', '3330.8', '3351.45', '0.93', '0.03%', '3303.18',
					'3394.22', '501661696', '53239848', '-' ],
			[ '2015/1/5', '3258.63', '3350.52', '115.84', '3.58%', '3253.88',
					'3369.28', '531352384', '54976008', '-' ],
			[ '', '', '', '', '', '', '', '', '', '' ],
			[ '', '', '', '', '', '', '', '', '', '' ] ].reverse();

	function calculateMA(dayCount, data) {
		var result = [];
		for ( var i = 0, len = data.length; i < len; i++) {
			if (i < dayCount) {
				result.push('-');
				continue;
			}
			var sum = 0;
			for ( var j = 0; j < dayCount; j++) {
				sum += data[i - j][1];
			}
			result.push(sum / dayCount);
		}
		return result;
	}

	var dates = rawData.map(function(item) {
		return item[0];
	});

	var data = rawData.map(function(item) {
		return [ +item[1], +item[2], +item[5], +item[6] ];
	});
	candlestickOpt.xAxis.data = dates;
	candlestickOpt.series[0].data = data;
	candlestickOpt.series[1].data = calculateMA(5, data);
	candlestickOpt.series[2].data = calculateMA(10, data);
	candlestickOpt.series[3].data = calculateMA(20, data);
	candlestickOpt.series[4].data = calculateMA(30, data);
	return candlestickOpt;
}
// ////矢量图////////////
function getSankeyOpt(datas) {
	var sankeyOpt = {
		title : {
			subtext : 'Sankey Diagram'
		},
		tooltip : {
			trigger : 'item',
			triggerOn : 'mousemove'

		},
		series : [ {
			type : 'sankey',
			layout : 'none',
			top : 68,
			data : datas.nodes,
			links : datas.links,
			itemStyle : {
				normal : {
					borderWidth : 1,
					borderColor : '#aaa'
				}
			},
			lineStyle : {
				normal : {
					curveness : 0.5
				}
			}
		} ]
	};
	return sankeyOpt;
}
// //漏斗图//////////////
function getFunnelOpt() {
	var funnelOpt = {
		title : {
			text : '漏斗图',
			subtext : '纯属虚构'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			feature : {
				dataView : {
					readOnly : false
				},
				restore : {},
				saveAsImage : {}
			}
		},
		legend : {
			data : []
		},
		series : [ {
			name : '预期',
			type : 'funnel',
			left : '10%',
			width : '80%',
			label : {
				normal : {
					formatter : '{b}预期'
				},
				emphasis : {
					position : 'inside',
					formatter : '{b}预期: {c}%'
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			itemStyle : {
				normal : {
					opacity : 0.7
				}
			},
			data : []
		}, {
			name : '实际',
			type : 'funnel',
			left : '10%',
			width : '80%',
			maxSize : '80%',
			label : {
				normal : {
					position : 'inside',
					formatter : '{c}%',
					textStyle : {
						color : '#fff'
					}
				},
				emphasis : {
					position : 'inside',
					formatter : '{b}实际: {c}%'
				}
			},
			itemStyle : {
				normal : {
					opacity : 0.5,
					borderColor : '#fff',
					borderWidth : 2
				}
			},
			data : []
		} ]
	};
	// 处理数据
	funnelOpt.legend.data = [ '展现', '点击', '访问', '咨询', '订单' ];
	var datas = [ [ {
		value : 60,
		name : '访问'
	}, {
		value : 40,
		name : '咨询'
	}, {
		value : 20,
		name : '订单'
	}, {
		value : 80,
		name : '点击'
	}, {
		value : 100,
		name : '展现'
	} ], [ {
		value : 30,
		name : '访问'
	}, {
		value : 10,
		name : '咨询'
	}, {
		value : 5,
		name : '订单'
	}, {
		value : 50,
		name : '点击'
	}, {
		value : 80,
		name : '展现'
	} ] ];
	funnelOpt.series[0].data = datas[0];
	funnelOpt.series[1].data = datas[1];
	return funnelOpt;
}
// ////箱线图/////////////
function getBoxplotOpt(prepareBoxplotData) {
	var boxplotOpt = {
		title : {
			subtext : 'Multiple Categories',
			left : 'center',
		},
		legend : {
			y : '10%',
			data : [ 'category0', 'category1', 'category2', 'category3' ]
		},
		tooltip : {
			trigger : 'item',
			axisPointer : {
				type : 'shadow'
			}
		},
		grid : {
			left : '10%',
			top : '20%',
			right : '10%',
			bottom : '15%'
		},
		xAxis : {
			type : 'category',
			data : [],
			boundaryGap : true,
			nameGap : 30,
			splitArea : {
				show : true
			},
			axisLabel : {
				formatter : 'expr {value}'
			},
			splitLine : {
				show : false
			}
		},
		yAxis : {
			type : 'value',
			name : 'Value',
			min : -400,
			max : 600,
			splitArea : {
				show : false
			}
		},
		dataZoom : [ {
			type : 'inside',
			start : 0,
			end : 20
		}, {
			show : true,
			height : 20,
			type : 'slider',
			top : '90%',
			xAxisIndex : [ 0 ],
			start : 0,
			end : 20
		} ],
		series : [ {
			name : 'category0',
			type : 'boxplot',
			data : [],
			tooltip : {}
		}, {
			name : 'category1',
			type : 'boxplot',
			data : [],
			tooltip : {}
		}, {
			name : 'category2',
			type : 'boxplot',
			data : [],
			tooltip : {}
		} ]
	};
	// 格式化
	function formatter(param) {
		return [ 'Experiment ' + param.name + ': ', 'upper: ' + param.data[0],
				'Q1: ' + param.data[1], 'median: ' + param.data[2],
				'Q3: ' + param.data[3], 'lower: ' + param.data[4] ]
				.join('<br/>');
	}
	boxplotOpt.series[0].tooltip.formatter = formatter;
	boxplotOpt.series[1].tooltip.formatter = formatter;
	boxplotOpt.series[2].tooltip.formatter = formatter;
	// 数据处理
	// Generate data.
	data = [];
	for ( var seriesIndex = 0; seriesIndex < 5; seriesIndex++) {
		var seriesData = [];
		for ( var i = 0; i < 18; i++) {
			var cate = [];
			for ( var j = 0; j < 100; j++) {
				cate.push(Math.random() * 200);
			}
			seriesData.push(cate);
		}
		data.push(prepareBoxplotData(seriesData));
	}
	boxplotOpt.xAxis.data = data[0].axisData;
	boxplotOpt.series[0].data = data[0].boxData;
	boxplotOpt.series[1].data = data[1].boxData;
	boxplotOpt.series[2].data = data[2].boxData;
	return boxplotOpt;
}
// ///热力图////////////////
function getHeatmapOpt() {
	var geoCoordMap = {
		"海门" : [ 121.15, 31.89 ],
		"鄂尔多斯" : [ 109.781327, 39.608266 ],
		"招远" : [ 120.38, 37.35 ],
		"舟山" : [ 122.207216, 29.985295 ],
		"齐齐哈尔" : [ 123.97, 47.33 ],
		"盐城" : [ 120.13, 33.38 ],
		"赤峰" : [ 118.87, 42.28 ],
		"青岛" : [ 120.33, 36.07 ],
		"乳山" : [ 121.52, 36.89 ],
		"金昌" : [ 102.188043, 38.520089 ],
		"泉州" : [ 118.58, 24.93 ],
		"莱西" : [ 120.53, 36.86 ],
		"日照" : [ 119.46, 35.42 ],
		"胶南" : [ 119.97, 35.88 ],
		"南通" : [ 121.05, 32.08 ],
		"拉萨" : [ 91.11, 29.97 ],
		"云浮" : [ 112.02, 22.93 ],
		"梅州" : [ 116.1, 24.55 ],
		"文登" : [ 122.05, 37.2 ],
		"上海" : [ 121.48, 31.22 ],
		"攀枝花" : [ 101.718637, 26.582347 ],
		"威海" : [ 122.1, 37.5 ],
		"承德" : [ 117.93, 40.97 ],
		"厦门" : [ 118.1, 24.46 ],
		"汕尾" : [ 115.375279, 22.786211 ],
		"潮州" : [ 116.63, 23.68 ],
		"丹东" : [ 124.37, 40.13 ],
		"太仓" : [ 121.1, 31.45 ],
		"曲靖" : [ 103.79, 25.51 ],
		"烟台" : [ 121.39, 37.52 ],
		"福州" : [ 119.3, 26.08 ],
		"瓦房店" : [ 121.979603, 39.627114 ],
		"即墨" : [ 120.45, 36.38 ],
		"抚顺" : [ 123.97, 41.97 ],
		"玉溪" : [ 102.52, 24.35 ],
		"张家口" : [ 114.87, 40.82 ],
		"阳泉" : [ 113.57, 37.85 ],
		"莱州" : [ 119.942327, 37.177017 ],
		"湖州" : [ 120.1, 30.86 ],
		"汕头" : [ 116.69, 23.39 ],
		"昆山" : [ 120.95, 31.39 ],
		"宁波" : [ 121.56, 29.86 ],
		"湛江" : [ 110.359377, 21.270708 ],
		"揭阳" : [ 116.35, 23.55 ],
		"荣成" : [ 122.41, 37.16 ],
		"连云港" : [ 119.16, 34.59 ],
		"葫芦岛" : [ 120.836932, 40.711052 ],
		"常熟" : [ 120.74, 31.64 ],
		"东莞" : [ 113.75, 23.04 ],
		"河源" : [ 114.68, 23.73 ],
		"淮安" : [ 119.15, 33.5 ],
		"泰州" : [ 119.9, 32.49 ],
		"南宁" : [ 108.33, 22.84 ],
		"营口" : [ 122.18, 40.65 ],
		"惠州" : [ 114.4, 23.09 ],
		"江阴" : [ 120.26, 31.91 ],
		"蓬莱" : [ 120.75, 37.8 ],
		"韶关" : [ 113.62, 24.84 ],
		"嘉峪关" : [ 98.289152, 39.77313 ],
		"广州" : [ 113.23, 23.16 ],
		"延安" : [ 109.47, 36.6 ],
		"太原" : [ 112.53, 37.87 ],
		"清远" : [ 113.01, 23.7 ],
		"中山" : [ 113.38, 22.52 ],
		"昆明" : [ 102.73, 25.04 ],
		"寿光" : [ 118.73, 36.86 ],
		"盘锦" : [ 122.070714, 41.119997 ],
		"长治" : [ 113.08, 36.18 ],
		"深圳" : [ 114.07, 22.62 ],
		"珠海" : [ 113.52, 22.3 ],
		"宿迁" : [ 118.3, 33.96 ],
		"咸阳" : [ 108.72, 34.36 ],
		"铜川" : [ 109.11, 35.09 ],
		"平度" : [ 119.97, 36.77 ],
		"佛山" : [ 113.11, 23.05 ],
		"海口" : [ 110.35, 20.02 ],
		"江门" : [ 113.06, 22.61 ],
		"章丘" : [ 117.53, 36.72 ],
		"肇庆" : [ 112.44, 23.05 ],
		"大连" : [ 121.62, 38.92 ],
		"临汾" : [ 111.5, 36.08 ],
		"吴江" : [ 120.63, 31.16 ],
		"石嘴山" : [ 106.39, 39.04 ],
		"沈阳" : [ 123.38, 41.8 ],
		"苏州" : [ 120.62, 31.32 ],
		"茂名" : [ 110.88, 21.68 ],
		"嘉兴" : [ 120.76, 30.77 ],
		"长春" : [ 125.35, 43.88 ],
		"胶州" : [ 120.03336, 36.264622 ],
		"银川" : [ 106.27, 38.47 ],
		"张家港" : [ 120.555821, 31.875428 ],
		"三门峡" : [ 111.19, 34.76 ],
		"锦州" : [ 121.15, 41.13 ],
		"南昌" : [ 115.89, 28.68 ],
		"柳州" : [ 109.4, 24.33 ],
		"三亚" : [ 109.511909, 18.252847 ],
		"自贡" : [ 104.778442, 29.33903 ],
		"吉林" : [ 126.57, 43.87 ],
		"阳江" : [ 111.95, 21.85 ],
		"泸州" : [ 105.39, 28.91 ],
		"西宁" : [ 101.74, 36.56 ],
		"宜宾" : [ 104.56, 29.77 ],
		"呼和浩特" : [ 111.65, 40.82 ],
		"成都" : [ 104.06, 30.67 ],
		"大同" : [ 113.3, 40.12 ],
		"镇江" : [ 119.44, 32.2 ],
		"桂林" : [ 110.28, 25.29 ],
		"张家界" : [ 110.479191, 29.117096 ],
		"宜兴" : [ 119.82, 31.36 ],
		"北海" : [ 109.12, 21.49 ],
		"西安" : [ 108.95, 34.27 ],
		"金坛" : [ 119.56, 31.74 ],
		"东营" : [ 118.49, 37.46 ],
		"牡丹江" : [ 129.58, 44.6 ],
		"遵义" : [ 106.9, 27.7 ],
		"绍兴" : [ 120.58, 30.01 ],
		"扬州" : [ 119.42, 32.39 ],
		"常州" : [ 119.95, 31.79 ],
		"潍坊" : [ 119.1, 36.62 ],
		"重庆" : [ 106.54, 29.59 ],
		"台州" : [ 121.420757, 28.656386 ],
		"南京" : [ 118.78, 32.04 ],
		"滨州" : [ 118.03, 37.36 ],
		"贵阳" : [ 106.71, 26.57 ],
		"无锡" : [ 120.29, 31.59 ],
		"本溪" : [ 123.73, 41.3 ],
		"克拉玛依" : [ 84.77, 45.59 ],
		"渭南" : [ 109.5, 34.52 ],
		"马鞍山" : [ 118.48, 31.56 ],
		"宝鸡" : [ 107.15, 34.38 ],
		"焦作" : [ 113.21, 35.24 ],
		"句容" : [ 119.16, 31.95 ],
		"北京" : [ 116.46, 39.92 ],
		"徐州" : [ 117.2, 34.26 ],
		"衡水" : [ 115.72, 37.72 ],
		"包头" : [ 110, 40.58 ],
		"绵阳" : [ 104.73, 31.48 ],
		"乌鲁木齐" : [ 87.68, 43.77 ],
		"枣庄" : [ 117.57, 34.86 ],
		"杭州" : [ 120.19, 30.26 ],
		"淄博" : [ 118.05, 36.78 ],
		"鞍山" : [ 122.85, 41.12 ],
		"溧阳" : [ 119.48, 31.43 ],
		"库尔勒" : [ 86.06, 41.68 ],
		"安阳" : [ 114.35, 36.1 ],
		"开封" : [ 114.35, 34.79 ],
		"济南" : [ 117, 36.65 ],
		"德阳" : [ 104.37, 31.13 ],
		"温州" : [ 120.65, 28.01 ],
		"九江" : [ 115.97, 29.71 ],
		"邯郸" : [ 114.47, 36.6 ],
		"临安" : [ 119.72, 30.23 ],
		"兰州" : [ 103.73, 36.03 ],
		"沧州" : [ 116.83, 38.33 ],
		"临沂" : [ 118.35, 35.05 ],
		"南充" : [ 106.110698, 30.837793 ],
		"天津" : [ 117.2, 39.13 ],
		"富阳" : [ 119.95, 30.07 ],
		"泰安" : [ 117.13, 36.18 ],
		"诸暨" : [ 120.23, 29.71 ],
		"郑州" : [ 113.65, 34.76 ],
		"哈尔滨" : [ 126.63, 45.75 ],
		"聊城" : [ 115.97, 36.45 ],
		"芜湖" : [ 118.38, 31.33 ],
		"唐山" : [ 118.02, 39.63 ],
		"平顶山" : [ 113.29, 33.75 ],
		"邢台" : [ 114.48, 37.05 ],
		"德州" : [ 116.29, 37.45 ],
		"济宁" : [ 116.59, 35.38 ],
		"荆州" : [ 112.239741, 30.335165 ],
		"宜昌" : [ 111.3, 30.7 ],
		"义乌" : [ 120.06, 29.32 ],
		"丽水" : [ 119.92, 28.45 ],
		"洛阳" : [ 112.44, 34.7 ],
		"秦皇岛" : [ 119.57, 39.95 ],
		"株洲" : [ 113.16, 27.83 ],
		"石家庄" : [ 114.48, 38.03 ],
		"莱芜" : [ 117.67, 36.19 ],
		"常德" : [ 111.69, 29.05 ],
		"保定" : [ 115.48, 38.85 ],
		"湘潭" : [ 112.91, 27.87 ],
		"金华" : [ 119.64, 29.12 ],
		"岳阳" : [ 113.09, 29.37 ],
		"长沙" : [ 113, 28.21 ],
		"衢州" : [ 118.88, 28.97 ],
		"廊坊" : [ 116.7, 39.53 ],
		"菏泽" : [ 115.480656, 35.23375 ],
		"合肥" : [ 117.27, 31.86 ],
		"武汉" : [ 114.31, 30.52 ],
		"大庆" : [ 125.03, 46.58 ]
	};

	var convertData = function(data) {
		var res = [];
		for ( var i = 0; i < data.length; i++) {
			var geoCoord = geoCoordMap[data[i].name];
			if (geoCoord) {
				res.push(geoCoord.concat(data[i].value));
			}
		}
		return res;
	};

	heatmapOpt = {
		title : {
			subtext : '全国主要城市空气质量',
			subtext : 'data from PM25.in',
			sublink : 'http://www.pm25.in',
			left : 'center',
			textStyle : {
				color : '#fff'
			}
		},
		backgroundColor : '#404a59',
		visualMap : {
			min : 0,
			max : 500,
			splitNumber : 5,
			inRange : {
				color : [ '#d94e5d', '#eac736', '#50a3ba' ].reverse()
			},
			textStyle : {
				color : '#fff'
			}
		},
		geo : {
			map : 'china',
			label : {
				emphasis : {
					show : false
				}
			},
			roam : true,
			itemStyle : {
				normal : {
					areaColor : '#323c48',
					borderColor : '#111'
				},
				emphasis : {
					areaColor : '#2a333d'
				}
			}
		},
		series : [ {
			name : 'AQI',
			type : 'heatmap',
			coordinateSystem : 'geo',
			data : convertData([ {
				name : "海门",
				value : 9
			}, {
				name : "鄂尔多斯",
				value : 12
			}, {
				name : "招远",
				value : 12
			}, {
				name : "舟山",
				value : 12
			}, {
				name : "齐齐哈尔",
				value : 14
			}, {
				name : "盐城",
				value : 15
			}, {
				name : "赤峰",
				value : 16
			}, {
				name : "青岛",
				value : 18
			}, {
				name : "乳山",
				value : 18
			}, {
				name : "金昌",
				value : 19
			}, {
				name : "泉州",
				value : 21
			}, {
				name : "莱西",
				value : 21
			}, {
				name : "日照",
				value : 21
			}, {
				name : "胶南",
				value : 22
			}, {
				name : "南通",
				value : 23
			}, {
				name : "拉萨",
				value : 24
			}, {
				name : "云浮",
				value : 24
			}, {
				name : "梅州",
				value : 25
			}, {
				name : "文登",
				value : 25
			}, {
				name : "上海",
				value : 25
			}, {
				name : "攀枝花",
				value : 25
			}, {
				name : "威海",
				value : 25
			}, {
				name : "承德",
				value : 25
			}, {
				name : "厦门",
				value : 26
			}, {
				name : "汕尾",
				value : 26
			}, {
				name : "潮州",
				value : 26
			}, {
				name : "丹东",
				value : 27
			}, {
				name : "太仓",
				value : 27
			}, {
				name : "曲靖",
				value : 27
			}, {
				name : "烟台",
				value : 28
			}, {
				name : "福州",
				value : 29
			}, {
				name : "瓦房店",
				value : 30
			}, {
				name : "即墨",
				value : 30
			}, {
				name : "抚顺",
				value : 31
			}, {
				name : "玉溪",
				value : 31
			}, {
				name : "张家口",
				value : 31
			}, {
				name : "阳泉",
				value : 31
			}, {
				name : "莱州",
				value : 32
			}, {
				name : "湖州",
				value : 32
			}, {
				name : "汕头",
				value : 32
			}, {
				name : "昆山",
				value : 33
			}, {
				name : "宁波",
				value : 33
			}, {
				name : "湛江",
				value : 33
			}, {
				name : "揭阳",
				value : 34
			}, {
				name : "荣成",
				value : 34
			}, {
				name : "连云港",
				value : 35
			}, {
				name : "葫芦岛",
				value : 35
			}, {
				name : "常熟",
				value : 36
			}, {
				name : "东莞",
				value : 36
			}, {
				name : "河源",
				value : 36
			}, {
				name : "淮安",
				value : 36
			}, {
				name : "泰州",
				value : 36
			}, {
				name : "南宁",
				value : 37
			}, {
				name : "营口",
				value : 37
			}, {
				name : "惠州",
				value : 37
			}, {
				name : "江阴",
				value : 37
			}, {
				name : "蓬莱",
				value : 37
			}, {
				name : "韶关",
				value : 38
			}, {
				name : "嘉峪关",
				value : 38
			}, {
				name : "广州",
				value : 38
			}, {
				name : "延安",
				value : 38
			}, {
				name : "太原",
				value : 39
			}, {
				name : "清远",
				value : 39
			}, {
				name : "中山",
				value : 39
			}, {
				name : "昆明",
				value : 39
			}, {
				name : "寿光",
				value : 40
			}, {
				name : "盘锦",
				value : 40
			}, {
				name : "长治",
				value : 41
			}, {
				name : "深圳",
				value : 41
			}, {
				name : "珠海",
				value : 42
			}, {
				name : "宿迁",
				value : 43
			}, {
				name : "咸阳",
				value : 43
			}, {
				name : "铜川",
				value : 44
			}, {
				name : "平度",
				value : 44
			}, {
				name : "佛山",
				value : 44
			}, {
				name : "海口",
				value : 44
			}, {
				name : "江门",
				value : 45
			}, {
				name : "章丘",
				value : 45
			}, {
				name : "肇庆",
				value : 46
			}, {
				name : "大连",
				value : 47
			}, {
				name : "临汾",
				value : 47
			}, {
				name : "吴江",
				value : 47
			}, {
				name : "石嘴山",
				value : 49
			}, {
				name : "沈阳",
				value : 50
			}, {
				name : "苏州",
				value : 50
			}, {
				name : "茂名",
				value : 50
			}, {
				name : "嘉兴",
				value : 51
			}, {
				name : "长春",
				value : 51
			}, {
				name : "胶州",
				value : 52
			}, {
				name : "银川",
				value : 52
			}, {
				name : "张家港",
				value : 52
			}, {
				name : "三门峡",
				value : 53
			}, {
				name : "锦州",
				value : 54
			}, {
				name : "南昌",
				value : 54
			}, {
				name : "柳州",
				value : 54
			}, {
				name : "三亚",
				value : 54
			}, {
				name : "自贡",
				value : 56
			}, {
				name : "吉林",
				value : 56
			}, {
				name : "阳江",
				value : 57
			}, {
				name : "泸州",
				value : 57
			}, {
				name : "西宁",
				value : 57
			}, {
				name : "宜宾",
				value : 58
			}, {
				name : "呼和浩特",
				value : 58
			}, {
				name : "成都",
				value : 58
			}, {
				name : "大同",
				value : 58
			}, {
				name : "镇江",
				value : 59
			}, {
				name : "桂林",
				value : 59
			}, {
				name : "张家界",
				value : 59
			}, {
				name : "宜兴",
				value : 59
			}, {
				name : "北海",
				value : 60
			}, {
				name : "西安",
				value : 61
			}, {
				name : "金坛",
				value : 62
			}, {
				name : "东营",
				value : 62
			}, {
				name : "牡丹江",
				value : 63
			}, {
				name : "遵义",
				value : 63
			}, {
				name : "绍兴",
				value : 63
			}, {
				name : "扬州",
				value : 64
			}, {
				name : "常州",
				value : 64
			}, {
				name : "潍坊",
				value : 65
			}, {
				name : "重庆",
				value : 66
			}, {
				name : "台州",
				value : 67
			}, {
				name : "南京",
				value : 67
			}, {
				name : "滨州",
				value : 70
			}, {
				name : "贵阳",
				value : 71
			}, {
				name : "无锡",
				value : 71
			}, {
				name : "本溪",
				value : 71
			}, {
				name : "克拉玛依",
				value : 72
			}, {
				name : "渭南",
				value : 72
			}, {
				name : "马鞍山",
				value : 72
			}, {
				name : "宝鸡",
				value : 72
			}, {
				name : "焦作",
				value : 75
			}, {
				name : "句容",
				value : 75
			}, {
				name : "北京",
				value : 79
			}, {
				name : "徐州",
				value : 79
			}, {
				name : "衡水",
				value : 80
			}, {
				name : "包头",
				value : 80
			}, {
				name : "绵阳",
				value : 80
			}, {
				name : "乌鲁木齐",
				value : 84
			}, {
				name : "枣庄",
				value : 84
			}, {
				name : "杭州",
				value : 84
			}, {
				name : "淄博",
				value : 85
			}, {
				name : "鞍山",
				value : 86
			}, {
				name : "溧阳",
				value : 86
			}, {
				name : "库尔勒",
				value : 86
			}, {
				name : "安阳",
				value : 90
			}, {
				name : "开封",
				value : 90
			}, {
				name : "济南",
				value : 92
			}, {
				name : "德阳",
				value : 93
			}, {
				name : "温州",
				value : 95
			}, {
				name : "九江",
				value : 96
			}, {
				name : "邯郸",
				value : 98
			}, {
				name : "临安",
				value : 99
			}, {
				name : "兰州",
				value : 99
			}, {
				name : "沧州",
				value : 100
			}, {
				name : "临沂",
				value : 103
			}, {
				name : "南充",
				value : 104
			}, {
				name : "天津",
				value : 105
			}, {
				name : "富阳",
				value : 106
			}, {
				name : "泰安",
				value : 112
			}, {
				name : "诸暨",
				value : 112
			}, {
				name : "郑州",
				value : 113
			}, {
				name : "哈尔滨",
				value : 114
			}, {
				name : "聊城",
				value : 116
			}, {
				name : "芜湖",
				value : 117
			}, {
				name : "唐山",
				value : 119
			}, {
				name : "平顶山",
				value : 119
			}, {
				name : "邢台",
				value : 119
			}, {
				name : "德州",
				value : 120
			}, {
				name : "济宁",
				value : 120
			}, {
				name : "荆州",
				value : 127
			}, {
				name : "宜昌",
				value : 130
			}, {
				name : "义乌",
				value : 132
			}, {
				name : "丽水",
				value : 133
			}, {
				name : "洛阳",
				value : 134
			}, {
				name : "秦皇岛",
				value : 136
			}, {
				name : "株洲",
				value : 143
			}, {
				name : "石家庄",
				value : 147
			}, {
				name : "莱芜",
				value : 148
			}, {
				name : "常德",
				value : 152
			}, {
				name : "保定",
				value : 153
			}, {
				name : "湘潭",
				value : 154
			}, {
				name : "金华",
				value : 157
			}, {
				name : "岳阳",
				value : 169
			}, {
				name : "长沙",
				value : 175
			}, {
				name : "衢州",
				value : 177
			}, {
				name : "廊坊",
				value : 193
			}, {
				name : "菏泽",
				value : 194
			}, {
				name : "合肥",
				value : 229
			}, {
				name : "武汉",
				value : 273
			}, {
				name : "大庆",
				value : 279
			} ])
		} ]
	};
	return heatmapOpt;
}
// ///关系图//////////////
function getGraphOpt(webkitDep) {
	var graphOpt = {
		title : {
			text : ''
		},
		legend : {
			data : []
		},
		series : [ {
			type : 'graph',
			layout : 'force',
			animation : false,
			label : {
				normal : {
					position : 'right',
					formatter : '{b}'
				}
			},
			draggable : true,
			data : [],
			categories : [],
			force : {
				// initLayout: 'circular'
				// gravity: 0
				// repulsion: 20,
				edgeLength : 5,
				repulsion : 20
			},
			edges : []
		} ]
	};
	// 数据处理
	graphOpt.legend.data = [ 'HTMLElement', 'WebGL', 'SVG', 'CSS', 'Other' ];
	graphOpt.series[0].data = webkitDep.nodes.map(function(node, idx) {
		node.id = idx;
		return node;
	});
	graphOpt.series[0].categories = webkitDep.categories;
	graphOpt.series[0].edges = webkitDep.links;
	return graphOpt;
}
//////矩形树图////////////////
function getTreemapOpt(rawData) {
	var treemapOpt = {
		title : {
			subtext : 'ECharts 配置项查询分布\n2016/04',
			left : 'leafDepth'
		},
		tooltip : {},
		series : [ {
			name : 'option',
			type : 'treemap',
			visibleMin : 300,
			data : [],
			leafDepth : 2,
			levels : [ {
				itemStyle : {
					normal : {
						borderColor : '#555',
						borderWidth : 4,
						gapWidth : 4
					}
				}
			}, {
				colorSaturation : [ 0.3, 0.6 ],
				itemStyle : {
					normal : {
						borderColorSaturation : 0.7,
						gapWidth : 2,
						borderWidth : 2
					}
				}
			}, {
				colorSaturation : [ 0.3, 0.5 ],
				itemStyle : {
					normal : {
						borderColorSaturation : 0.6,
						gapWidth : 1
					}
				}
			}, {
				colorSaturation : [ 0.3, 0.5 ]
			} ]
		} ]
	};
	//数据处理
	function convert(source, target, basePath) {
		for ( var key in source) {
			var path = basePath ? (basePath + '.' + key) : key;
			if (key.match(/^\$/)) {

			} else {
				target.children = target.children || [];
				var child = {
					name : path
				};
				target.children.push(child);
				convert(source[key], child, path);
			}
		}

		if (!target.children) {
			target.value = source.$count || 1;
		} else {
			target.children.push({
				name : basePath,
				value : source.$count
			});
		}
	}
	var data = [];
	convert(rawData, data, '');
	treemapOpt.series[0].data = data.children;
	return treemapOpt;
}
/////平行坐标图//////////////
function getParallelOpt() {
	var parallelOpt = {
		backgroundColor : '#333',
		legend : {
			bottom : 30,
			data : [ '北京', '上海', '广州' ],
			itemGap : 20,
			textStyle : {
				color : '#fff',
				fontSize : 14
			}
		},
		title : {
			text : ''
		},
		tooltip : {
			padding : 10,
			backgroundColor : '#222',
			borderColor : '#777',
			borderWidth : 1
		},
		// dataZoom: {
		//     show: true,
		//     orient: 'vertical',
		//     parallelAxisIndex: [0]
		// },
		parallelAxis : [],
		visualMap : {
			show : true,
			min : 0,
			max : 150,
			dimension : 2,
			inRange : {
				color : [ '#d94e5d', '#eac736', '#50a3ba' ].reverse(),
			// colorAlpha: [0, 1]
			}
		},
		parallel : {
			left : '5%',
			right : '18%',
			bottom : 100,
			parallelAxisDefault : {
				type : 'value',
				name : 'AQI指数',
				nameLocation : 'end',
				nameGap : 20,
				nameTextStyle : {
					color : '#fff',
					fontSize : 12
				},
				axisLine : {
					lineStyle : {
						color : '#aaa'
					}
				},
				axisTick : {
					lineStyle : {
						color : '#777'
					}
				},
				splitLine : {
					show : false
				},
				axisLabel : {
					textStyle : {
						color : '#fff'
					}
				}
			}
		},
		series : [ {
			name : '北京',
			type : 'parallel',
			lineStyle : {},
			data : []
		}, {
			name : '上海',
			type : 'parallel',
			lineStyle : {},
			data : []
		}, {
			name : '广州',
			type : 'parallel',
			lineStyle : {},
			data : []
		} ]
	};
	//数据处理
	// Schema:
	// date,AQIindex,PM2.5,PM10,CO,NO2,SO2
	var dataBJ = [ [ 1, 55, 9, 56, 0.46, 18, 6, "良" ],
			[ 2, 25, 11, 21, 0.65, 34, 9, "优" ],
			[ 3, 56, 7, 63, 0.3, 14, 5, "良" ],
			[ 4, 33, 7, 29, 0.33, 16, 6, "优" ],
			[ 5, 42, 24, 44, 0.76, 40, 16, "优" ],
			[ 6, 82, 58, 90, 1.77, 68, 33, "良" ],
			[ 7, 74, 49, 77, 1.46, 48, 27, "良" ],
			[ 8, 78, 55, 80, 1.29, 59, 29, "良" ],
			[ 9, 267, 216, 280, 4.8, 108, 64, "重度污染" ],
			[ 10, 185, 127, 216, 2.52, 61, 27, "中度污染" ],
			[ 11, 39, 19, 38, 0.57, 31, 15, "优" ],
			[ 12, 41, 11, 40, 0.43, 21, 7, "优" ],
			[ 13, 64, 38, 74, 1.04, 46, 22, "良" ],
			[ 14, 108, 79, 120, 1.7, 75, 41, "轻度污染" ],
			[ 15, 108, 63, 116, 1.48, 44, 26, "轻度污染" ],
			[ 16, 33, 6, 29, 0.34, 13, 5, "优" ],
			[ 17, 94, 66, 110, 1.54, 62, 31, "良" ],
			[ 18, 186, 142, 192, 3.88, 93, 79, "中度污染" ],
			[ 19, 57, 31, 54, 0.96, 32, 14, "良" ],
			[ 20, 22, 8, 17, 0.48, 23, 10, "优" ],
			[ 21, 39, 15, 36, 0.61, 29, 13, "优" ],
			[ 22, 94, 69, 114, 2.08, 73, 39, "良" ],
			[ 23, 99, 73, 110, 2.43, 76, 48, "良" ],
			[ 24, 31, 12, 30, 0.5, 32, 16, "优" ],
			[ 25, 42, 27, 43, 1, 53, 22, "优" ],
			[ 26, 154, 117, 157, 3.05, 92, 58, "中度污染" ],
			[ 27, 234, 185, 230, 4.09, 123, 69, "重度污染" ],
			[ 28, 160, 120, 186, 2.77, 91, 50, "中度污染" ],
			[ 29, 134, 96, 165, 2.76, 83, 41, "轻度污染" ],
			[ 30, 52, 24, 60, 1.03, 50, 21, "良" ],
			[ 31, 46, 5, 49, 0.28, 10, 6, "优" ] ];

	var dataGZ = [ [ 1, 26, 37, 27, 1.163, 27, 13, "优" ],
			[ 2, 85, 62, 71, 1.195, 60, 8, "良" ],
			[ 3, 78, 38, 74, 1.363, 37, 7, "良" ],
			[ 4, 21, 21, 36, 0.634, 40, 9, "优" ],
			[ 5, 41, 42, 46, 0.915, 81, 13, "优" ],
			[ 6, 56, 52, 69, 1.067, 92, 16, "良" ],
			[ 7, 64, 30, 28, 0.924, 51, 2, "良" ],
			[ 8, 55, 48, 74, 1.236, 75, 26, "良" ],
			[ 9, 76, 85, 113, 1.237, 114, 27, "良" ],
			[ 10, 91, 81, 104, 1.041, 56, 40, "良" ],
			[ 11, 84, 39, 60, 0.964, 25, 11, "良" ],
			[ 12, 64, 51, 101, 0.862, 58, 23, "良" ],
			[ 13, 70, 69, 120, 1.198, 65, 36, "良" ],
			[ 14, 77, 105, 178, 2.549, 64, 16, "良" ],
			[ 15, 109, 68, 87, 0.996, 74, 29, "轻度污染" ],
			[ 16, 73, 68, 97, 0.905, 51, 34, "良" ],
			[ 17, 54, 27, 47, 0.592, 53, 12, "良" ],
			[ 18, 51, 61, 97, 0.811, 65, 19, "良" ],
			[ 19, 91, 71, 121, 1.374, 43, 18, "良" ],
			[ 20, 73, 102, 182, 2.787, 44, 19, "良" ],
			[ 21, 73, 50, 76, 0.717, 31, 20, "良" ],
			[ 22, 84, 94, 140, 2.238, 68, 18, "良" ],
			[ 23, 93, 77, 104, 1.165, 53, 7, "良" ],
			[ 24, 99, 130, 227, 3.97, 55, 15, "良" ],
			[ 25, 146, 84, 139, 1.094, 40, 17, "轻度污染" ],
			[ 26, 113, 108, 137, 1.481, 48, 15, "轻度污染" ],
			[ 27, 81, 48, 62, 1.619, 26, 3, "良" ],
			[ 28, 56, 48, 68, 1.336, 37, 9, "良" ],
			[ 29, 82, 92, 174, 3.29, 0, 13, "良" ],
			[ 30, 106, 116, 188, 3.628, 101, 16, "轻度污染" ],
			[ 31, 118, 50, 0, 1.383, 76, 11, "轻度污染" ] ];

	var dataSH = [ [ 1, 91, 45, 125, 0.82, 34, 23, "良" ],
			[ 2, 65, 27, 78, 0.86, 45, 29, "良" ],
			[ 3, 83, 60, 84, 1.09, 73, 27, "良" ],
			[ 4, 109, 81, 121, 1.28, 68, 51, "轻度污染" ],
			[ 5, 106, 77, 114, 1.07, 55, 51, "轻度污染" ],
			[ 6, 109, 81, 121, 1.28, 68, 51, "轻度污染" ],
			[ 7, 106, 77, 114, 1.07, 55, 51, "轻度污染" ],
			[ 8, 89, 65, 78, 0.86, 51, 26, "良" ],
			[ 9, 53, 33, 47, 0.64, 50, 17, "良" ],
			[ 10, 80, 55, 80, 1.01, 75, 24, "良" ],
			[ 11, 117, 81, 124, 1.03, 45, 24, "轻度污染" ],
			[ 12, 99, 71, 142, 1.1, 62, 42, "良" ],
			[ 13, 95, 69, 130, 1.28, 74, 50, "良" ],
			[ 14, 116, 87, 131, 1.47, 84, 40, "轻度污染" ],
			[ 15, 108, 80, 121, 1.3, 85, 37, "轻度污染" ],
			[ 16, 134, 83, 167, 1.16, 57, 43, "轻度污染" ],
			[ 17, 79, 43, 107, 1.05, 59, 37, "良" ],
			[ 18, 71, 46, 89, 0.86, 64, 25, "良" ],
			[ 19, 97, 71, 113, 1.17, 88, 31, "良" ],
			[ 20, 84, 57, 91, 0.85, 55, 31, "良" ],
			[ 21, 87, 63, 101, 0.9, 56, 41, "良" ],
			[ 22, 104, 77, 119, 1.09, 73, 48, "轻度污染" ],
			[ 23, 87, 62, 100, 1, 72, 28, "良" ],
			[ 24, 168, 128, 172, 1.49, 97, 56, "中度污染" ],
			[ 25, 65, 45, 51, 0.74, 39, 17, "良" ],
			[ 26, 39, 24, 38, 0.61, 47, 17, "优" ],
			[ 27, 39, 24, 39, 0.59, 50, 19, "优" ],
			[ 28, 93, 68, 96, 1.05, 79, 29, "良" ],
			[ 29, 188, 143, 197, 1.66, 99, 51, "中度污染" ],
			[ 30, 174, 131, 174, 1.55, 108, 50, "中度污染" ],
			[ 31, 187, 143, 201, 1.39, 89, 53, "中度污染" ] ];

	var schema = [ {
		name : 'date',
		index : 0,
		text : '日期'
	}, {
		name : 'AQIindex',
		index : 1,
		text : 'AQI'
	}, {
		name : 'PM25',
		index : 2,
		text : 'PM2.5'
	}, {
		name : 'PM10',
		index : 3,
		text : 'PM10'
	}, {
		name : 'CO',
		index : 4,
		text : ' CO'
	}, {
		name : 'NO2',
		index : 5,
		text : 'NO2'
	}, {
		name : 'SO2',
		index : 6,
		text : 'SO2'
	}, {
		name : '等级',
		index : 7,
		text : '等级'
	} ];

	var lineStyle = {
		normal : {
			width : 1,
			opacity : 0.5
		}
	};
	parallelOpt.tooltip.formatter = function(obj) {
		var value = obj[0].value;
		return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
				+ obj[0].seriesName
				+ ' '
				+ value[0]
				+ '日期：'
				+ value[7]
				+ '</div>'
				+ schema[1].text
				+ '：'
				+ value[1]
				+ '<br>'
				+ schema[2].text
				+ '：'
				+ value[2]
				+ '<br>'
				+ schema[3].text
				+ '：'
				+ value[3]
				+ '<br>'
				+ schema[4].text
				+ '：'
				+ value[4]
				+ '<br>'
				+ schema[5].text
				+ '：'
				+ value[5]
				+ '<br>'
				+ schema[6].text + '：' + value[6] + '<br>';
	};
	parallelOpt.parallelAxis = [ {
		dim : 0,
		name : schema[0].text,
		inverse : true,
		max : 31,
		nameLocation : 'start'
	}, {
		dim : 1,
		name : schema[1].text
	}, {
		dim : 2,
		name : schema[2].text
	}, {
		dim : 3,
		name : schema[3].text
	}, {
		dim : 4,
		name : schema[4].text
	}, {
		dim : 5,
		name : schema[5].text
	}, {
		dim : 6,
		name : schema[6].text
	}, {
		dim : 7,
		name : schema[7].text,
		type : 'category',
		data : [ '优', '良', '轻度污染', '中度污染', '重度污染', '严重污染' ]
	} ];
	parallelOpt.series[0].lineStyle = lineStyle;
	parallelOpt.series[1].lineStyle = lineStyle;
	parallelOpt.series[2].lineStyle = lineStyle;
	parallelOpt.series[0].data = dataBJ;
	parallelOpt.series[1].data = dataSH;
	parallelOpt.series[2].data = dataGZ;

	return parallelOpt;
}
