<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="generator" content="editplus" />
		<meta name="author" content="" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<style type="text/css">
* {
	font-size: 14px
}

/*日期最外层*/
.daysList {
	width: 100%;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc
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
		<form action="" method="post" id="proForm">
			<input name="pageStatus" value="zhuanpeiPlan" type="hidden" />
			<input name="firstTime" value="" id="firstTime" type="hidden" />
			<input name="endTime" value="" id="endTime" type="hidden" />
			<table class="daysList">
				<tr>
					<td align="right">
						订单号:
					</td>
					<td align="left">
						<input name="procard.orderNumber" />
					</td>
					<td align="right">
						产品编码:
					</td>
					<td align="left">
						<input name="procard.markId" />
					</td>
				</tr>
				<tr>
					<td colspan="1" align="right">
						查询日期:
					</td>
					<td colspan="2">
						<select name="year" id="year" class="bd"></select>
						年
						<select name="month" id="month" class="bd">
							<option value="01">
								01
							</option>
							<option value="02">
								02
							</option>
							<option value="03">
								03
							</option>
							<option value="04">
								04
							</option>
							<option value="05">
								05
							</option>
							<option value="06">
								06
							</option>
							<option value="07">
								07
							</option>
							<option value="08">
								08
							</option>
							<option value="09">
								09
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
						<select name="month" id="day_start" class="bd">
							<option value="01">
								01
							</option>
							<option value="02">
								02
							</option>
							<option value="03">
								03
							</option>
							<option value="04">
								04
							</option>
							<option value="05">
								05
							</option>
							<option value="06">
								06
							</option>
							<option value="07">
								07
							</option>
							<option value="08">
								08
							</option>
							<option value="09">
								09
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
							<option value="13">
								13
							</option>
							<option value="14">
								14
							</option>
							<option value="15">
								15
							</option>
							<option value="16">
								16
							</option>
							<option value="17">
								17
							</option>
							<option value="18">
								18
							</option>
							<option value="19">
								19
							</option>
							<option value="20">
								20
							</option>
							<option value="21">
								21
							</option>
							<option value="22">
								22
							</option>
							<option value="23">
								23
							</option>
							<option value="24">
								24
							</option>
							<option value="25">
								25
							</option>
							<option value="26">
								26
							</option>
							<option value="27">
								27
							</option>
							<option value="28">
								28
							</option>
							<option value="29">
								29
							</option>
							<option value="30">
								30
							</option>
							<option value="31">
								31
							</option>
						</select>
						号 --
						<select name="month" id="day_end" class="bd">
							<option value="31">
								31
							</option>
							<option value="01">
								01
							</option>
							<option value="02">
								02
							</option>
							<option value="03">
								03
							</option>
							<option value="04">
								04
							</option>
							<option value="05">
								05
							</option>
							<option value="06">
								06
							</option>
							<option value="07">
								07
							</option>
							<option value="08">
								08
							</option>
							<option value="09">
								09
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
							<option value="13">
								13
							</option>
							<option value="14">
								14
							</option>
							<option value="15">
								15
							</option>
							<option value="16">
								16
							</option>
							<option value="17">
								17
							</option>
							<option value="18">
								18
							</option>
							<option value="19">
								19
							</option>
							<option value="20">
								20
							</option>
							<option value="21">
								21
							</option>
							<option value="22">
								22
							</option>
							<option value="23">
								23
							</option>
							<option value="24">
								24
							</option>
							<option value="25">
								25
							</option>
							<option value="26">
								26
							</option>
							<option value="27">
								27
							</option>
							<option value="28">
								28
							</option>
							<option value="29">
								29
							</option>
							<option value="30">
								30
							</option>
							<option value="31">
								31
							</option>
						</select>
						号
					</td>
					<td>
						<input onclick="chageDate()" type="button" value="查询"
							style="width: 70px; height: 40px;" />
					</td>
				</tr>
			</table>
		</form>
		<br />
		<table width="100%" cellpadding="3" height="100%" cellspacing="0"
			class="daysList" id="daysList">
			<tr>
				<td colspan="1">
					今日:
				</td>
				<td>
					<input type="text" name="week1" class="bd" id="year1" readonly
						size="2" />
					年
				</td>
				<td>
					<input type="text" name="week1" class="bd" id="month1" readonly
						size="2" />
					月
					<input type="text" name="day" size="2" class="bd" id="day" readonly />

				</td>
				<td>
					第
					<input type="text" name="week1" class="bd" id="week" readonly
						size="2" />
					周
				</td>
				<td>
					星期
					<input type="text" name="weekday1" class="bd" id="weekday" readonly
						size="2" />
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
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="showdetail">
				<td>
					序号
				</td>
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
				<td></td>
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
	//显示日期列表,传入年、月(按日常月份传入。如二月传入2)、及显示位置 
	var displayDayList = function(year, month, pos) {
		var daysList = [];
		var cells1 = $(pos).rows[0].cells;
		var cells2 = $(pos).rows[1].cells;
		var daysArr = [ "日", "一", "二", "三", "四", "五", "六" ];
		//下面的month-1转换为js月份表示 
		var indexof = 6;
		for ( var i = indexof, l = getDaysLen(year, --month) + 7; i < l; i++) {
			var wd = new Date(year, month, i - (indexof - 1)).getDay();
			cells1[i - 1].className = "";
			if (wd == 0 || wd == 6) {
				cells1[i - 1].className = "weekEnd";
			} //为周末添加特殊样式 
			//_oldCls保存当前日期的默认样式 
			cells1[i - 1].innerHTML = daysArr[wd];
			cells2[i - 1].className = "unSelectDay";
			cells2[i - 1].setAttribute("_oldCls", "unSelectDay");
			cells2[i - 1].innerHTML = (i - (indexof - 1)) > 9 ? (i - (indexof - 1))
					: "0" + (i - (indexof - 1));
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
		startime = curYear;
		$("year").selectedIndex = 10; //默认选中当前年份 
		$("year1").value = curYear; //当前年份 
		$("month").selectedIndex = curDate.getMonth(); //当前月份 
		$("month1").value = curDate.getMonth(); //当前月份 
		$("day").value = curDate.getDate(); //当前日期 
		$("weekday").value = [ "日", "一", "二", "三", "四", "五", "六" ][curDate
				.getDay()]; //当前星期几 
		$("week").value = calWeek(); //当前第几周 
		//改变日期或年份更新日期列表 
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
			displayDayList($("year").value, $("month").value, "daysList");
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

	$("#year").bind("chage", function() {
		chageDate()
	});
	$("#month").bind("chage", function() {
		chageDate()
	});
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
					for ( var p in doc) {
						var procardInfor = p.split(";");
						var proDateAndCount = doc[p].split(";");
						var paicancount = 0;
						var countArray = new Array(31);
						$(proDateAndCount)
								.each(
										function(j, cd) {
											if (cd != "") {
												var oneCAndDate = cd.split("|");
												var jihuoDates = oneCAndDate[0];
												var count = parseInt(jihuoDates
														.substring(8, 10));
												for ( var k = 1; k <= 31; k++) {
													if (k == count) {
														var proCountsum = parseFloat(oneCAndDate[1]);
														if (countArray[k] != null
																&& parseFloat(countArray[k]) > 0) {
															proCountsum += parseFloat(countArray[k]);
														}
														countArray[k] = proCountsum;
														paicancount += proCountsum;
													} else {
														if (countArray[k] == null) {
															countArray[k] = "";
														}
													}
												}
											}
										});
						procardhtml += "<tr><td>"
								+ i
								+ "</td><td></td><td  align='left'>"
								+ procardInfor[0]
								+ "</td><td>"
								+ procardInfor[1]
								+ (procardInfor[2] == null ? "" : "("
										+ procardInfor[2] + ")") + "</td><td>"
								+ paicancount + "</td>";
						for ( var a = 1; a <= 31; a++) {
							procardhtml += "<td>" + countArray[a] + "</td>";
						}

						procardhtml += "</tr>";
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

function chageDate() {
	var pageFirstTime = $("#year").val() + "-" + $("#month").val() + "-"
			+ $("#day_start").val();
	var pageEndTime = $("#year").val() + "-" + $("#month").val() + "-"
			+ $("#day_end").val();
	$("#firstTime").val(pageFirstTime);
	$("#endTime").val(pageEndTime);
	getPlan(pageFirstTime, pageEndTime);
	//自动调整日历表
	logDateControl.setDateStyle( [
			[ $("#year").val(), $("#month").val(), $("#day_start").val() ],
			[ $("#year").val(), $("#month").val(), $("#day_end").val() ] ], "");
}
</script>
	</body>
</html>