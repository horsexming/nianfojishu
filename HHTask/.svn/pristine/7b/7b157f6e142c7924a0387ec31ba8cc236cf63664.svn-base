<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>new document</title>
		<meta name="generator" content="editplus" />
		<meta name="author" content="" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<style type="text/css">
* {
	font-size: 14px
}

/*日期最外层*/
#logCalendar {
	width: 100%;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc
}

/*日期内部所有单元格*/
#logCalendar td {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding-left: 5px;
	text-align: center;
	height: 22px;
	line-height: 22px;
}

#daysList {
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc
}

/*日期列表所有单元格*/
#daysList td {
	text-align: center;
	height: 22px
}

/*周次及星期几的输入框*/
.bd {
	border: 0;
	text-align: center;
	color: #00f
}

/*日期默认样式*/
.unSelectDay {
	color: #00f;
	font-weight: normal;
	padding: 3px;
	cursor: pointer;
	text-decoration: underline
}

/*当前点击的日期样式*/
.selectDay {
	color: #fff;
	font-weight: bold;
	background: #FEB0B0;
	padding: 1px;
	text-decoration: none;
	cursor: normal
}

/*周末样式*/
.weekEnd {
	color: #f00
}

.test {
	color: #f00;
	text-decoration: none
}
</style>
		
	</head>

	<body>
		<table cellpadding="3" cellspacing="0" id="logCalendar">
			<tr>
				<td colspan="1">
					<select name="year" id="year"></select>
					年
				</td>
				<td colspan="1">
					<select name="month" id="month">
						<option value="1">
							1
						</option>
						<option value="2">
							2
						</option>
						<option value="3">
							3
						</option>
						<option value="4">
							4
						</option>
						<option value="5">
							5
						</option>
						<option value="6">
							6
						</option>
						<option value="7">
							7
						</option>
						<option value="8">
							8
						</option>
						<option value="9">
							9
						</option>
						<option value="10">
							10
						</option>
						<option value="11">
							11
						</option>
						<option value="12">
							12
						</option>
					</select>
					月
					<input type="text" name="day" size="2" class="bd" id="day" id="day" />
					号
				</td>
				<td colspan="1">
					第
					<input type="text" name="week" class="bd" id="week" readonly
						size="2" />
					周
				</td>
				<td colspan="1">
					星期
					<input type="text" name="weekday" class="bd" id="weekday" readonly
						size="2" />
				</td>
				<td rowspan="3" style="padding: 0">
					<table width="100%" cellpadding="3" height="100%" cellspacing="0"
						id="daysList">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>
								2
							</td>
							<td>
								50000
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="procardTitile">
				<td>
					产品类型
				</td>
				<td>
					订单号
				</td>
				<td>
					产品编码
				</td>
				<td>
					排产数量
				</td>
			</tr>
			<tr>
				<td>
					热产品+芯子
				</td>
				<td>
					YT20170711019
				</td>
				<td>
					21161797
				</td>
				<td>
					500
				</td>
			</tr>
		</table>
		<script type="text/javascript">
//2007-12-21 工作日志日期组件 yanwei 
//使用闭包隐藏所有变量和函数，防止与外界冲突
var logDateControl = (function() {
	var curSelEl; //当前选中的日期 
	var styleData = [], dataStyle = {};
	//获取指定id的元素 
	var $ = function(id) {
		return document.getElementById(id)
	}
	//计算指定日期是第几周（默认为当前日期），该计算方法比较严密准确 
	var isIE = isIE || (function() {
		var browser = function(str) {
			return navigator.userAgent.indexOf(str) > -1
		}
		return browser("MSIE") && browser("compatible") && !browser("Opera");
	})();
	//对于非IE浏览器的处理
	if (!isIE) {
		//innerText
		HTMLElement.prototype.__defineSetter__("innerText", function(sText) {
			this.textContent = sText
		});
		HTMLElement.prototype.__defineGetter__("innerText", function() {
			return this.textContent
		})
	}
	//触发click事件
	var doClickEvent = function(obj) {
		obj = obj || window;
		if (isIE) {
			return obj.click()
		}
		var e = document.createEvent("MouseEvents");
		e.initEvent("click", true, true);
		obj.dispatchEvent(e);
	}
	//绑定事件
	var attachEvent = function(evt, handler, obj) {
		obj = obj || window;
		if (obj.addEventListener) {
			obj.addEventListener(evt, handler, false);
		} else {
			obj.attachEvent("on" + evt, handler);
		}
	}

	var calWeek = function(dt) {
		var calDay = dt || new Date(); //当前要计算的时间 
		var firstDay = new Date(calDay.getFullYear(), 0, 1); //本年第一天 
		//计算当前是本年的第几天,00：00为当天开始 
		var daysAll = Math.floor((calDay - firstDay) / 1000 / 60 / 60 / 24) + 1;
		//本年第一天星期几 
		var firstDayWeekday = firstDay.getDay();
		//该结果加到第一周的周一，便于后面计算 
		var diffDay = firstDayWeekday == 0 ? 6 : firstDayWeekday - 1;
		daysAll = daysAll + diffDay;
		return Math.ceil(daysAll / 7); //返回计算结果 
	}
	//计算一个月多少天,年份4位数字，月份1-2位数字（应该是js日期格式如1月传入0）,数据非法返回-1 
	var getDaysLen = function(year, month) {
		if (!(/^\d{4}$/.test(year) && /^\d{1,2}$/.test(month))) {
			return -1
		}
		var monthDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
		//存在2月29日 
		if (month == 1 && new Date(year, 1, 29).getMonth() == 1) {
			monthDays[1] = 29
		}
		return monthDays[month]
	}
	//显示日期列表,传入年、月(按日常月份传入。如二月传入2)、及显示位置 
	var displayDayList = function(year, month, pos) {
		var daysList = [];
		var cells1 = $(pos).rows[0].cells;
		var cells2 = $(pos).rows[1].cells;
		var daysArr = [ "日", "一", "二", "三", "四", "五", "六" ];
		//下面的month-1转换为js月份表示 
		for ( var i = 1, l = getDaysLen(year, --month) + 1; i < l; i++) {
			var wd = new Date(year, month, i).getDay();
			cells1[i - 1].className = "";
			if (wd == 0 || wd == 6) {
				cells1[i - 1].className = "weekEnd";
			} //为周末添加特殊样式 
			//_oldCls保存当前日期的默认样式 
			cells1[i - 1].innerHTML = daysArr[wd];
			cells2[i - 1].className = "unSelectDay";
			cells2[i - 1].setAttribute("_oldCls", "unSelectDay");
			cells2[i - 1].innerHTML = i > 9 ? i : "0" + i;
			//匹配用户自定义样式 
			var dtStr = year + "|" + (month + 1) + "|" + i;
			if (("," + styleData.join() + ",").indexOf("," + dtStr + ",") > -1) {
				cells2[i - 1].className = "unSelectDay " + dataStyle[dtStr];
				cells2[i - 1].setAttribute("_oldCls", "unSelectDay "
						+ dataStyle[dtStr]);
			}
		}
		//如果是当前月则选中当日 
		if (new Date().getMonth() == month) {
			curSelEl = cells2[new Date().getDate() - 1];
			curSelEl.className = "selectDay";
		}
		for ( var j = i - 1; j < 31; j++) {
			cells1[j].className = cells2[j].className = "";
			cells1[j].innerHTML = cells2[j].innerHTML = "  ";
		}
		//$(pos).onclick = function (e) { changeInfo(e) }
	}
	//根据选择的值进行处理周次和周几的调整,可以直接传入保存日期内容的dom元素，或者函数根据点击位置判断 
	var changeInfo = function(e) {
		e = e || event;
		var el = e.target || e.srcElement || e; //最后一个e：可能是传入的对象 
		var day = el.innerText;
		//alert(day);
		if (!/^\d{1,2}$/.test(day))
			return; //如果不是日期什么都不做 
		//恢复之前选中日期的样式 
		if (curSelEl) {
			curSelEl.className = curSelEl.getAttribute("_oldCls")
		}
		curSelEl = el; //保存当前处理的元素 
		//更新选中日期的样式 
		el.className = "selectDay";
		var dt = new Date($("year").value, $("month").value - 1, day);
		//更新信息 
		$("day").value = day; //日期 

		$("weekday").value = [ "日", "一", "二", "三", "四", "五", "六" ][dt.getDay()]; //星期几 
		$("week").value = calWeek(dt); //第几周 

	}
	//键盘事件监听
	var listenKey = function(e) {
		var keyCode = (e || event).keyCode;
		var p = curSelEl.parentNode.cells;
		var cellIndex;
		if (keyCode == "37") { //left
			cellIndex = curSelEl.cellIndex != 0 ? curSelEl.cellIndex : p.length;
			//doClickEvent(p[cellIndex - 1])
		}
		if (keyCode == "39") { //right
			cellIndex = curSelEl.cellIndex != (p.length - 1) ? curSelEl.cellIndex
					: -1;
			//doClickEvent(p[cellIndex + 1])
		}
	}
	//初始化 
	if (window.attachEvent) {
		window.attachEvent("onload", docInit);
	} else {
		window.addEventListener("load", docInit, false);
	}
	function docInit() {
		var curDate = new Date(), curYear = curDate.getFullYear();
		//显示上下十年 
		for ( var i = -10; i < 10; i++) {
			$("year").add(new Option(curYear + i, curYear + i))
		}
		$("year").selectedIndex = 10; //默认选中当前年份 
		$("month").selectedIndex = curDate.getMonth(); //当前月份 
		$("day").value = curDate.getDate(); //当前日期 

		$("weekday").value = [ "日", "一", "二", "三", "四", "五", "六" ][curDate
				.getDay()]; //当前星期几 
		$("week").value = calWeek(); //当前第几周 
		//改变日期或年份更新日期列表 
		$("year").onchange = $("month").onchange = function() {
			displayDayList($("year").value, $("month").value, "daysList")
		};
		//显示当月日期列表,并高亮当天的日期 
		displayDayList(curDate.getFullYear(), curDate.getMonth() + 1,
				"daysList");
	}

	//对外设定样式的接口。 
	//格式：([2007,10,12],"color:#f00") ([[2007,10,20],[2007,11,25]],"color:#00f") 
	//如果月份小于10不要带0 
	var setDateStyle = function(dateArr, style) {
		if (typeof dateArr != "object")
			return;
		if (dateArr instanceof Array) {
			if (dateArr[0] instanceof Array) {
				for ( var i = 0; i < dateArr.length; i++)
					setDateStyle(dateArr[i], style);
			}
			var dataStr = dateArr.join("|");
			styleData.push(dataStr);
			dataStyle[dataStr] = style;
			return;
		}
	}
	//对外接口 
	return {
		setDateStyle : setDateStyle
	}
})();
//测试样式设定 
//logDateControl.setDateStyle( [ [ 2017, 12, 15 ], [ 2017, 11, 12 ] ], "test");

$(function(){
	
	$
			.ajax( {
				url : 'ProcardAction!findProcardByRootId.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${param.id}',
					pageStatus : 'plan'
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
								//var b = true;
									if ($(this).attr('procardStyle') == "总成") {
										totalMaxCount = $(this)
												.attr('maxCount');
									}
									//供料属性
									var glsx = '';
									if ($(this).attr('procardStyle') == "外购"
											&& $(this).attr('kgliao') != null
											&& $(this).attr('kgliao') != ""
											&& $(this).attr('kgliao') != "TK") {
										glsx = " <span style='color:green;margin-right:0px;'>"
												+ $(this).attr('kgliao')
												+ "</span>";
									}
									var procardStyle = $(this).attr(
											'procardStyle');
									if (procardStyle == "待定") {
										procardStyle = "<span style='color:red;margin-right:0px;'>待定</span>";
									}
									//单交件状态
									var danjiaojian = $(this).attr(
											'danjiaojian');
									if (danjiaojian == null) {
										danjiaojian = "";
									}
									//半成品状态
									var needProcess = $(this).attr(
											'needProcess');
									if (needProcess == "yes") {
										needProcess = " (半成品)";
									} else {
										needProcess = "";
									}
									var bzStatus = $(this).attr('bzStatus');
									if (bzStatus == null || bzStatus == "") {
										bzStatus = "初始";
									}
									if (bzStatus != "已批准") {
										bzStatus = "<span style='color:red;margin-right:0px;'>"
												+ bzStatus + "</span>";
									} else {
										bzStatus = "";
									}

									var hgstyle = "<span style='font-weight: bolder;font-size: 18px;'>--</span>";
									zNodes
											.push( {
												id : $(this).attr('id'),
												sbStatus : $(this).attr(
														'sbStatus'),
												bzStatus : $(this).attr(
														'bzStatus'),
												banBenNumber : $(this).attr(
														'banBenNumber'),
												banci : $(this).attr('banci'),
												bomApplyStatus : $(this).attr(
														'bomApplyStatus'),
												epId2 : $(this).attr('epId2'),
												pId : $(this).attr('fatherId'),
												proStruts : $(this).attr(
														'procardStyle'),
												rootId : $(this).attr('rootId'),
												markId : $(this).attr('markId'),
												tuhao : $(this).attr('tuhao'),
												danjiaojian : danjiaojian,
												productStyle : $(this).attr(
														'productStyle'),
												belongLayer : $(this).attr(
														'belongLayer'),
												name : procardStyle
														+ needProcess + glsx,
												proName : $(this).attr(
														'proName'),
												quanzi1 : $(this).attr(
														'quanzi1'),
												quanzi2 : $(this).attr(
														'quanzi2'),
												corrCount : $(this).attr(
														'corrCount'),
												bianzhiName : $(this).attr(
														'bianzhiName'),
												jiaoduiName : $(this).attr(
														'jiaoduiName'),
												shenheName : $(this).attr(
														'shenheName'),
												pizhunName : $(this).attr(
														'pizhunName'),
												jihuoDate : $(this).attr(
														'jihuoDate'),
												needFinalDate : $(this).attr(
														'needFinalDate'),
												nowFinalDate : $(this).attr(
														'nowFinalDate'),
												status : $(this).attr('status'),
												title : $(this).attr(
														'procardStyle')
														+ '--'
														+ $(this)
																.attr('markId')
														+ '--'
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ needProcess,
												click : false,
												drop : true,
												open : true
											});

								});
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					//添加表头
					var li_head = ' <li class="head"><a><div class="diy" >产品类型</div><div class="diy" align="center">件号/图号</div><div class="diy">产品名称</div><div class="diy" style="width:50px;">状态</div><div class="diy" style="width:155px;">计划开始日期</div><div class="diy"  style="width:155px;">计划完成日期</div><div class="diy"  style="width:155px;">实际完成日期</div><div class="diy" style="width:120px;">调整操作</div></a></li>';
					var rows = $("#treeDemo").find('li');
					if (rows.length > 0) {
						rows.eq(0).before(li_head)
					} else {
						$("#treeDemo").append(li_head);
						$("#treeDemo")
								.append(
										'<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
})


</script>
	</body>
</html>
