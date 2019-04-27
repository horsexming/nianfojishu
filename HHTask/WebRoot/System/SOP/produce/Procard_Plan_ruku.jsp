<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="generator" content="editplus" />
		<meta name="author" content="" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/css.css" />
		<style type="text/css">
* {
	font-size: 14px
}

/*日期最外层*/
.daysList {
	width: 100%;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
}

/*日期内部所有单元格*/
.daysList td {
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 0px;
	margin: 0px;
	height: 22px;
	line-height: 22px;
}

#daysList {
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc
}

/*日期列表所有单元格*/
#daysList td {
	height: 22px;
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

/*当天样式*/
.nowday {
	color: #fff;
	font-weight: bolder;
	background-color: green;
}

.test {
	color: #f00;
	text-decoration: none
}
</style>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
		</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/javascript.js">
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none');">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative;background: url(<%=basePath%>/images/bq_bg2.gif); width:100%;">
				<table style="width: 100%; background-color: #ffffff">
					<tr>
						<td>
							您正在调整排程:
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
			</div>
			<div id="showblDiv" style="display: none;">
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showblIf" src="" marginwidth="0" marginheight="0"
						frameborder="0" scrolling="yes"
						style="width: 100%; margin: 0px; padding: 0px; height: 500px;"></iframe>
				</div>
			</div>
		</div>
		<form action="" method="post" id="proForm">
			<input name="pageStatus" value="${param.pageStatus}" type="hidden" />
			<table class="daysList" style="border-bottom: solid #ccc 1px;">
				<tr>
					<td align="right">
						订单号:
					</td>
					<td align="left">
						<input name="procard.orderNumber" />
					</td>
					<td align="right">
						件号:
					</td>
					<td align="left">
						<input name="procard.markId" />
					</td>
				</tr>
				<tr>
					<td align="right">
						产品名称:
					</td>
					<td align="left">
						<input name="procard.proName" />
					</td>
					<td align="right">
						产品类别:
					</td>
					<td align="left">
						<input name="procard.carStyle" />
					</td>
				</tr>
				<tr>
					<td align="right">
						工序名称:
					</td>
					<td align="left">
						<input name="procard.remark" />
					</td>
					<td align="right">
						排产数据:
					</td>
					<td align="left">
						<input type="radio" checked="checked" name="procard.zhikaren"
							value="one" />
						个人
						<input type="radio" name="procard.zhikaren" value="all" />
						所有
					</td>
				</tr>
				<tr>
					<td align="right">
						生产类型:
					</td>
					<td align="left">
						<select name="procard.productStyle" style="width: 155px;">
							<option></option>
							<option>
								批产
							</option>
							<option>
								试制
							</option>
						</select>
					</td>
					<td align="right">
						剩余数量:
					</td>
					<td align="left">
						<input type="radio" checked="checked" name="procard.hasPlan"
							value="1" />
						待排产
						<input type="radio" name="procard.hasPlan" value="0" />
						所有
					</td>
				</tr>
				<tr>
					<td colspan="1" align="right">
						查询日期:
					</td>
					<td colspan="3">
						<input class="Wdate" type="text" name="firstTime" id="firstTime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						-
						<input class="Wdate" type="text" name="endTime" id="endTime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
					</td>
				</tr>
				<tr>
					<th colspan="4">
						<input onclick="chageDate()" type="button" value="查询"
							style="width: 70px; height: 40px;" />
					</th>
				</tr>
			</table>
		</form>
		<br />
		<br />
		<div align="left">
			今日:
			<input type="text" name="week1" class="bd" id="year1" readonly
				size="2" />
			年
			<input type="text" name="week1" class="bd" id="month1" readonly
				size="2" />
			月
			<input type="text" name="day" size="2" class="bd" id="day" readonly />
			日 第
			<input type="text" name="week1" class="bd" id="week" readonly
				size="2" />
			周 星期
			<input type="text" name="weekday1" class="bd" id="weekday" readonly
				size="2" />
		</div>
		<table cellpadding="3" cellspacing="0" class="daysList" id="daysList">
			<tr style="text-align: center;" id="showWeek">
			</tr>
			<tr id="showdetail" style="text-align: center;">
			</tr>
		</table>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
//2007-12-21 工作日志日期组件  
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

	/*
	 * 获取后一天的时间
	 */
	var getNextDay = function(d) {
		d = new Date(d);
		d = +d + 1000 * 60 * 60 * 24;
		d = new Date(d);
		return d;
	}
	/*
	 * 获取前一天的时间
	 */
	var getLastDay = function(d) {
		d = new Date(d);
		d = +d - 1000 * 60 * 60 * 24;
		d = new Date(d);
		return d;
	}

	var DateDiff = function(sDate1, sDate2) { //sDate1和sDate2是2002-12-18格式 
		var aDate, oDate1, oDate2, iDays
		aDate = sDate1.split("-")
		oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]) //转换为12-18-2002格式 
		aDate = sDate2.split("-")
		oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
		iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24) //把相差的毫秒数转换为天数 
		return iDays
	}

	//显示日期列表,传入年、月(按日常月份传入。如二月传入2)、及显示位置 
	var displayDayList = function(dateTime, endDateTime, pos, chage) {
		var daysList = [];
		var showWeekhtml = "<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>";
		var showDatehtml = "<td style='font-size: 8px;'>序号</td><td style='font-size: 8px;'>产品<br>类别</td><td style='font-size: 8px;'>生产<br>类型</td><td style='font-size: 8px;'>订单号</td>"
				+ "<td style='font-size: 8px;'>件号</td><td style='font-size: 8px;'>名称</td>"
				+ "<td style='font-size: 8px;'>排产<br>数量</td><td style='font-size: 8px;'>剩余<br>数量</td>";
		var daysArr = [ "日", "一", "二", "三", "四", "五", "六" ];
		//下面的month-1转换为js月份表示 
		var indexof = 10;
		var nowdate = new Date();
		var nyear = nowdate.getYear() + 1900;
		var nmonth = nowdate.getMonth() + 1;
		var nday = nowdate.getDate();
		if (nmonth < 10)
			nmonth = "0" + nmonth;
		if (nday < 10)
			nday = "0" + nday;
		var nowdatef = nyear + "-" + nmonth + "-" + nday;//yyyy-MM-dd
		if (dateTime == null || dateTime == "") {
			dateTime = nowdatef;
		}
		var now = new Date(dateTime.replace('/-/g', '/') + " " + '00' + ":"
				+ '00' + ":" + '00');
		var dateLenth = 45;
		if (endDateTime != null && endDateTime != "") {
			dateLenth = DateDiff(dateTime, endDateTime) + 1;
		}
		for ( var i = indexof, l = (dateLenth + indexof); i < l; i++) {
			if (chage != "chage") {
				//向前推3天
				if (i == indexof) {
					now = getLastDay(now);
					now = getLastDay(now);
					now = getLastDay(now);
				}
			}
			var year = now.getYear() + 1900;
			var month = now.getMonth() + 1;
			var day = now.getDate();
			var wd = now.getDay();//周几
			if (month < 10)
				month = "0" + month;
			if (day < 10)
				day = "0" + day;
			var date = year + "-" + month + "-" + day;//yyyy-MM-dd

			showWeekhtml += "<td ";
			if (wd == 0 || wd == 6) {
				showWeekhtml += "class='weekEnd'> ";
			} else {
				showWeekhtml += "> ";
			}
			showWeekhtml += daysArr[wd] + "</td> ";

			showDatehtml += "<td ";
			//将当天日期高亮显示
			if (nowdatef == date) {
				showDatehtml += "class='nowday'> ";
			} else {
				showDatehtml += "class='unSelectDay'> ";

			}
			showDatehtml += "<span title='" + date + "'>" + now.getDate()
					+ "</span><input id='date_" + (i - indexof)
					+ "' type='hidden' value='" + date + "'/></td>";
			now = getNextDay(now);
		}
		$("showWeek").innerHTML = showWeekhtml;
		$("showdetail").innerHTML = showDatehtml;
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
		var month = curDate.getMonth() + 1;
		var day = curDate.getDate();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		$("year1").value = curYear; //当前年份 
		$("month1").value = month; //当前月份 
		$("day").value = day; //当前日期 
		$("week").value = calWeek(); //当前第几周 
		$("weekday").value = [ "日", "一", "二", "三", "四", "五", "六" ][curDate
				.getDay()]; //当前星期几 
		var nowdate = curDate.getFullYear() + "-" + month + "-" + day;
		$("firstTime").value = nowdate; //当前第几周 
		//显示当月日期列表,并高亮当天的日期 
		displayDayList(nowdate, "", "daysList");

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
			displayDayList($("firstTime").value, $("endTime").value,
					"daysList", "chage");
			return;
		}
	}
	//对外接口 
	return {
		setDateStyle : setDateStyle
	}
})();
$(function() {
	getPlan();//展示计划信息
})

function getPlan(pageFirstTime, pageEndTime) {
	$
			.ajax( {
				url : 'ProcardAction!findAllPlan.action',
				type : 'post',
				dataType : 'json',
				data : $("#proForm").serialize(),
				cache : true,
				success : function(doc) {
					var i = 1;
					var procardhtml = "";
					var dateLength = 45;
					if (pageFirstTime != null && pageFirstTime != ""
							&& pageEndTime != null && pageEndTime != "") {
						dateLength = DateDiff(pageFirstTime, pageEndTime) + 1;
					}
					for ( var p in doc) {
						var procardInfor = p.split(";");
						var proDateAndCount = doc[p].split(";");
						var paicancount = 0;
						var counthtml = "";
						//先把重复的日期对应的数量合并了
						var data = new Object();
						$(proDateAndCount)
								.each(
										function(j, cd) {
											if (cd != "") {
												var oneCAndDate = cd.split("|");
												var jihuoDates = oneCAndDate[0]
														.substring(0, 10);
												var proCountsum = parseFloat(oneCAndDate[1]);
												if (data[jihuoDates] == null) {
													data[jihuoDates] = proCountsum;
												} else {
													data[jihuoDates] = parseFloat(data[jihuoDates])
															+ proCountsum;
												}
											}
										});

						for ( var k = 0; k < dateLength; k++) {
							var gettitledate = $("#date_" + k).val();
							if (data[gettitledate] == null) {
								counthtml += "<td align='right' style='width:66px;'></td>";
							} else {
								counthtml += "<td align='right' style='width:66px;'>"
										+ data[gettitledate] + "</td>";
								paicancount += parseFloat(data[gettitledate]);
							}
						}
						procardhtml += "<tr><td>"
								+ i
								+ "</td><td>"
								+ procardInfor[4]
								+ "</td><td>"
								+ procardInfor[5]
								+ "</td><td  align='left'>"
								+ procardInfor[0]
								+ "</td><td   align='left'><a target='showProView' href='ProcardAction!findProcardView.action?pageStatus=history&viewStatus=&id="
								+ procardInfor[8]
								+ "'>"
								+ procardInfor[1]
								+ (procardInfor[2] == null ? "" : "<br>["
										+ procardInfor[2] + "]</a>")
								+ "</td><td  align='left' style='font-size:6px;width:auto;'>"
								+ procardInfor[3]
								+ "</td><td  align='right'><a href='javascript:;' onclick='showbl("
								+ procardInfor[8]
								+ ")'>"
								+ procardInfor[9]
								+ "</a></td><td  align='right'><a href='javascript:;' onclick='showbl("
								+ procardInfor[8] + ")'>" + procardInfor[10]
								+ "</a></td>";

						procardhtml += counthtml + "</tr>";
						i++;
					}
					$("#daysList tr").eq(1).nextAll().remove();
					$("#showdetail").after(procardhtml);
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}

function showbl(procardId) {
	$("#showblIf").attr("src",
			"procardBlAction_findProcardBlByPId.action?rootId=" + procardId);
	$("#showblDiv").show();
	chageDiv("block");
}

function chageDate() {
	//自动调整日历表
	logDateControl.setDateStyle( [
			[ $("#year").val(), $("#month").val(), $("#day_start").val() ],
			[ $("#year").val(), $("#month").val(), $("#day_end").val() ] ], "");
	getPlan($("#firstTime").val(), $("#endTime").val());
}

function DateDiff(sDate1, sDate2) { //sDate1和sDate2是2002-12-18格式 
	var aDate, oDate1, oDate2, iDays
	aDate = sDate1.split("-")
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]) //转换为12-18-2002格式 
	aDate = sDate2.split("-")
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24) //把相差的毫秒数转换为天数 
	return iDays
}

function Map() {
	/** 存放键的数组(遍历用到) */
	this.keys = new Array();
	/** 存放数据 */
	this.data = new Object();

	/**   
	 * 放入一个键值对   
	 * @param {String} key   
	 * @param {Object} value   
	 */
	this.put = function(key, value) {
		if (this.data[key] == null) {
			this.keys.push(key);
		}
		this.data[key] = value;
	};

	/**   
	 * 获取某键对应的值   
	 * @param {String} key   
	 * @return {Object} value   
	 */
	this.get = function(key) {
		return this.data[key];
	};

	/**   
	 * 删除一个键值对   
	 * @param {String} key   
	 */
	this.remove = function(key) {
		this.keys.remove(key);
		this.data[key] = null;
	};

	/**   
	 * 遍历Map,执行处理函数   
	 *    
	 * @param {Function} 回调函数 function(key,value,index){..}   
	 */
	this.each = function(fn) {
		if (typeof fn != 'function') {
			return;
		}
		var len = this.keys.length;
		for ( var i = 0; i < len; i++) {
			var k = this.keys[i];
			fn(k, this.data[k], i);
		}
	};

	/**   
	 * 获取键值数组(类似Java的entrySet())   
	 * @return 键值对象{key,value}的数组   
	 */
	this.entrys = function() {
		var len = this.keys.length;
		var entrys = new Array(len);
		for ( var i = 0; i < len; i++) {
			entrys[i] = {
				key : this.keys[i],
				value : this.data[i]
			};
		}
		return entrys;
	};

	/**   
	 * 判断Map是否为空   
	 */
	this.isEmpty = function() {
		return this.keys.length == 0;
	};

	/**   
	 * 获取键值对数量   
	 */
	this.size = function() {
		return this.keys.length;
	};

	/**   
	 * 重写toString    
	 */
	this.toString = function() {
		var s = "{";
		for ( var i = 0; i < this.keys.length; i++, s += ',') {
			var k = this.keys[i];
			s += k + "=" + this.data[k];
		}
		s += "}";
		return s;
	};
}

function testMap() {
	var m = new Map();
	m.put('key1', 'Comtop');
	m.put('key2', '南方电网');
	m.put('key3', '景新花园');
	alert("init:" + m);

	m.put('key1', '康拓普');
	alert("set key1:" + m);

	m.remove("key2");
	alert("remove key2: " + m);

	var s = "";
	m.each(function(key, value, index) {
		s += index + ":" + key + "=" + value + "/n";
	});
	alert(s);
}
</script>
	</body>
</html>
