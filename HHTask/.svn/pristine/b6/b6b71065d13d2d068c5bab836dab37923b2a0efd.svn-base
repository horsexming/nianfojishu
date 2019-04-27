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
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.table th,.table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
	height: 24px;
}

#gongyiNav {
	width: 100%;
	background: #666666;
	font-family: 黑体;
	text-align: center;
	font-size: 13px;
	font-weight: bold;
}

#gongyiNav a,span {
	border-left: #CCCCCC 1px solid;
	display: inline-block;
	height: 40px;
	line-height: 40px;
	padding: 0px 10px;
	margin-left: -8px;
	color: white;
	text-decoration: none;
}
</style>

		<style type="text/css">
/* focus  210mm×297mm 800*1130 280 380   842×595*/ /*
*{margin:0;padding:0;}
body{font-size:12px;color:#222;font-family:Verdana,Arial,Helvetica,sans-serif;background:#f0f0f0;}
.clearfix:after{content: ".";display: block;height: 0;clear: both;visibility: hidden;}
.clearfix{zoom:1;}
ul,li{list-style:none;}
img{border:0;}
.wrapper{width:800px;margin:0 auto;padding-bottom:50px;}
#focus{width:800px;height:1130px;overflow:hidden;position:relative;}
#focus ul{height:1130px;position:absolute;}
#focus ul li{float:left;width:800px;height:1130px;overflow:hidden;position:relative;background:#000;}
#focus ul li div{position:absolute;overflow:hidden;}
#focus ul li img{width: 800px;height: 1130px;}
#focus .btnBg{position:absolute;width:800px;height:20px;left:0;bottom:0;background:#000;}
#focus .btn{position:absolute;width:780px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus .btn span.on{background:#fff;}
#focus .preNext{width:45px;height:100px;position:absolute;top:500px;background:url(images/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus .pre{left:0;}
#focus .next{right:0;background-position:right top;}
*/
* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 12px;
	color: #222;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	background: #f0f0f0;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	zoom: 1;
}

ul,li {
	list-style: none;
}

img {
	border: 0;
}

.wrapper {
	width: 595px;
	margin: 0 auto;
	padding-bottom: 0px;
}

#focus {
	width: 595px;
	height: 842px;
	overflow: hidden;
	position: relative;
}

#focus ul {
	height: 842px;
	position: absolute;
}

#focus ul li {
	float: left;
	width: 595px;
	height: 842px;
	overflow: hidden;
	position: relative;
	background: #000;
}

#focus ul li div {
	position: absolute;
	overflow: hidden;
}

#focus ul li img {
	width: 595px;
	height: 842px;
}

#focus .btnBg {
	position: absolute;
	width: 800px;
	height: 20px;
	left: 0;
	bottom: 0;
	background: #000;
}

#focus .btn {
	position: absolute;
	width: 780px;
	height: 10px;
	padding: 5px 10px;
	right: 0;
	bottom: 0;
	text-align: right;
}

#focus .btn span {
	display: inline-block;
	_display: inline;
	_zoom: 1;
	width: 25px;
	height: 10px;
	_font-size: 0;
	margin-left: 5px;
	cursor: pointer;
	background: #fff;
}

#focus .btn span.on {
	background: #fff;
}

#focus .preNext {
	width: 45px;
	height: 100px;
	position: absolute;
	top: 300px;
	background: url(images/sprite.png) no-repeat 0 0;
	cursor: pointer;
}

#focus .pre {
	left: 0;
}

#focus .next {
	right: 0;
	background-position: right top;
}
/*
*{margin:0;padding:0;}
body{font-size:12px;color:#222;font-family:Verdana,Arial,Helvetica,sans-serif;background:#f0f0f0;}
.clearfix:after{content: ".";display: block;height: 0;clear: both;visibility: hidden;}
.clearfix{zoom:1;}
ul,li{list-style:none;}
img{border:0;}
.wrapper{width:100%;margin:0 auto;padding-bottom:50px;}
#focus{width:100%;height:100%;;overflow:hidden;position:relative;}
#focus ul{height:100%;position:absolute;}
#focus ul li{float:left;width:100%;;height:100%;overflow:hidden;position:relative;background:#000;}
#focus ul li div{position:absolute;overflow:hidden;}
#focus ul li img{}
#focus .btnBg{position:absolute;width:800px;height:20px;left:0;bottom:0;background:#000;}
#focus .btn{position:absolute;width:780px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus .btn span.on{background:#fff;}
#focus .preNext{width:45px;height:100px;position:absolute;top:500px;background:url(images/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus .pre{left:0;}
#focus .next{right:0;background-position:right top;}
*/
</style>
	</head>
	<body style="text-align: center;">
		<div align="center" style="width: 100%; border: 0 auto;">
			<div id="gongyiNav">
				<%--
				<a>工艺规程编号：<span>${gongyiGuicheng.numb}</span></a><a>型别：<span>${gongyiGuicheng.xingbie}</span></a>
				<a>工序号：<span id="gongxuNo"></span></a>
				<a>工序名：<span id="gongxuName"></span></a>
				--%>
				<a>加工件号</a>
				<select id="jianNumb">
				</select>
				<select id="gongyiGuichengId">
				</select>
				<select id="processDataId" style="display: none">
				</select>
			</div>
		</div>

		<div class="wrapper">

			<div id="focus">
				<ul id="gongyiGuichengAffixImg">
				</ul>
			</div>
			<!--focus end-->

		</div>
		<!-- wrapper end -->
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	//var gongyiGuichengId = '${gongyiGuicheng.id}';
	var screenId = '${screenId}';
	var processDataId = '';
	var currJianNumb = '';
	var currGygcId = '';
	//查询出所有在干的件号
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getJianNumbForZaigan.action",
		data : {
			'screenId' : screenId
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var jianNumbList = data.data;
				$(jianNumbList).each(
						function(i, n) {
							var jianNumb = this.jianNumb;
							if (i == 0) {
								currJianNumb = jianNumb;
							}
							$(
									"<option value='" + jianNumb + "'>"
											+ jianNumb + "</option>").appendTo(
									"#jianNumb");
						});
			}
		}
	});

	//件号绑定 change事件
	$("#jianNumb")
			.bind(
					"change",
					function(i, n) {
						currJianhao = $(this).val();
						$("#gongyiGuichengId").empty();
						$
								.ajax( {
									type : "get",
									dataType : "json",
									url : "gongyiGuichengAction!getGongyiGuichengListByJianNumb.action",
									data : {
										'gongyiGuicheng.jianNumb' : currJianhao
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											var gongyiGuichengList = data.data;
											$(gongyiGuichengList)
													.each(
															function(l, n) {
																var gongyiGuichengId = this.id;
																var numb = this.numb;
																if (l == 0) {
																	currGygcId = gongyiGuichengId;
																}
																$(
																		"<option value='"
																				+ gongyiGuichengId
																				+ "'>"
																				+ numb
																				+ "</option>")
																		.appendTo(
																				"#gongyiGuichengId");
															});
										}
									}
								});
					});
	$("#jianNumb").trigger("change");
	$("#gongyiGuichengId")
			.bind(
					"change",
					function(i, n) {
						currGygcId = $(this).val();
						$
								.ajax( {
									type : "get",
									dataType : "json",
									url : "gongyiGuichengAction!getProcessBygGygcId.action",
									data : {
										'gongyiGuicheng.id' : currGygcId
									},
									async : false,
									success : function(data) {
										var success = data.success;
										if (success) {
											var processDataList = data.data;
											//删除图片
									$("#gongyiGuichengAffixImg").empty();
									$("#gongyiGuichengAffixImg")
											.siblings("div").remove();
									$(processDataList)
											.each(
													function(i, n) {
														var processDataId = this.id;
														$
																.ajax( {
																	type : "get",
																	dataType : "json",
																	url : "gongyiGuichengAction!getGongyiGuichengAffixListForTupianForSelectByGongyiGuichengIdAndProcessDataId.action",
																	data : {
																		'gongyiGuichengAffix.gongyiGuichengId' : currGygcId,
																		'gongyiGuichengAffix.processDataId' : processDataId
																	},
																	async : false,
																	success : function(
																			data) {
																		var success = data.success;
																		if (success) {
																			var gongyiGuichengAffixList = data.data;
																			$(
																					gongyiGuichengAffixList)
																					.each(
																							function(
																									l,
																									n) {
																								var url = this.url;
																								if (l == 0) {
																									$(
																											"<li><a href='#' target='_blank'><img src='"
																													+ url
																													+ "' alt='工艺程序图' /></a></li>")
																											.appendTo(
																													"#gongyiGuichengAffixImg");
																								}
																							});
																		}
																	}
																});
													});
									//控件绑定事件
									initGongyiGuichengAffixImg();
								}

							}
								});
					});
	$("#gongyiGuichengId").trigger("change");
	function initGongyiGuichengAffixImg() {
		var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
		var len = $("#focus ul li").length; //获取焦点图个数
		var index = 0;
		var picTimer;

		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn = "<div class='btnBg'></div><div class='btn'>";
		for ( var i = 0; i < len; i++) {
			btn += "<span></span>";
		}
		btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$("#focus").append(btn);
		$("#focus .btnBg").css("opacity", 0.5);

		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$("#focus .btn span").css("opacity", 0.4).mouseover(function() {
			index = $("#focus .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseover");

		//上一页、下一页按钮透明度处理
		$("#focus .preNext").css("opacity", 0.2).hover(function() {
			$(this).stop(true, false).animate( {
				"opacity" : "0.5"
			}, 300);
		}, function() {
			$(this).stop(true, false).animate( {
				"opacity" : "0.2"
			}, 300);
		});

		//上一页按钮
		$("#focus .pre").click(function() {
			index -= 1;
			if (index == -1) {
				index = len - 1;
			}
			showPics(index);
		});

		//下一页按钮
		$("#focus .next").click(function() {
			index += 1;
			if (index == len) {
				index = 0;
			}
			showPics(index);
		});

		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$("#focus ul").css("width", sWidth * (len));

		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$("#focus").hover(function() {
			clearInterval(picTimer);
		}, function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if (index == len) {
					index = 0;
				}
			}, 4000); //此4000代表自动播放的间隔，单位：毫秒
			}).trigger("mouseleave");

		//显示图片函数，根据接收的index值显示相应的内容
		function showPics(index) { //普通切换
			var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
			$("#focus ul").stop(true, false).animate( {
				"left" : nowLeft
			}, 300); //通过animate()调整ul元素滚动到计算出的position
			//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
			$("#focus .btn span").stop(true, false).animate( {
				"opacity" : "0.4"
			}, 300).eq(index).stop(true, false).animate( {
				"opacity" : "1"
			}, 300); //为当前的按钮切换到选中的效果
		}
	}
	//initGongyiGuichengAffixImg();

	setInterval('myrefresh()', 300000);
});
function myrefresh() {
	window.location.reload();
}
</script>

