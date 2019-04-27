<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>

	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				
			</div>
			
			<div align="center">
				<script type="text/javascript">
function check() {
	var shebeixinghao = document.getElementById("shebeixinghao");
	var zhizaochangjia = document.getElementById("zhizaochangjia");
	var chuchangbianhao = document.getElementById("chuchangbianhao");
	var chuchangriq = document.getElementById("chuchangriq");
	var anzhaungdidian = document.getElementById("anzhaungdidian");
	var anzhaungshij = document.getElementById("anzhaungshij");
	var baofeiyuanyin = document.getElementById("baofeiyuanyin");
	if (shebeixinghao.value == "") {
		alert("设备型号不能为空");
		shebeixinghao.focus();
		return false;
	} else if (zhizaochangjia.value == "") {
		alert("制造厂家不能为空");
		zhizaochangjia.focus();
		return false;
	} else if (chuchangbianhao.value == "") {
		alert("出厂编号不能为空");
		chuchangbianhao.focus();
		return false;
	} else if (chuchangriq.value == "") {
		alert("出厂日期不能为空");
		chuchangriq.focus();
		return false;
	} else if (anzhaungdidian.value == "") {
		alert("安装地点不能为空");
		anzhaungdidian.focus();
		return false;
	} else if (anzhaungshij.value == "") {
		alert("安装时间不能为空");
		anzhaungshij.focus();
		return false;
	} else if (baofeiyuanyin.value == "") {
		alert("报废原因不能为空");
		baofeiyuanyin.focus();
		return false;
	} else {
		return true;
	}
}
</script>
				<form action="AssetAction!updateAssetscrappedSingle.action"
					method="post" onsubmit="return check()">
					<input type="hidden" value="${aesset.id}" name="aesset.id">
					<!-- ID -->
					<table>
						<tr>
							<th colspan="6">
								<font size="5">填写需要报废的信息</font>
							</th>
						</tr>
						<tr>
							<th>
								设备名称
							</th>
							<td>
								${aesset.taassetsname}
								<input type="hidden" name="aesset.taassetsname"
									value="${aesset.taassetsname}" />
							</td>
							<th>
								设备型号
							</th>
							<td>
								<input type="text" name="aesset.taequipmentModel"
									value="${aesset.taequipmentModel}" id="shebeixinghao" />
							</td>
							<th>
								规格
							</th>
							<td>
								${aesset.taspecificationsmodel}
								<input type="hidden" name="aesset.taspecificationsmodel"
									value="${aesset.taspecificationsmodel }" />
							</td>
						</tr>

						<tr>
							<th>
								制造厂家
							</th>
							<td>
								<input type="text" name="aesset.tamanufacturer"
									value="${aesset.tamanufacturer}" id="zhizaochangjia" />
							</td>
							<th>
								出厂编号
							</th>
							<td>
								<input type="text" name="aesset.tafactorynumber"
									value="${aesset.tafactorynumber}" id="chuchangbianhao" />
							</td>
							<th>
								出厂日期
							</th>
							<td>
								<input type="text" name="aesset.tafactorydate"
									onfocus="HS_setDate(this)" value="${aesset.tafactorydate}"
									id="chuchangriq" />

							</td>
						</tr>

						<tr>
							<th>
								安装地点
							</th>
							<td>
								<input type="text" name="aesset.tainstallationsite"
									value="${aesset.tainstallationsite}" id="anzhaungdidian" />
							</td>
							<th>
								安装时间
							</th>
							<td>
								<input type="text" name="aesset.tainstallationdate"
									value="${aesset.tainstallationdate}" id="anzhaungshij"
									onfocus="HS_setDate(this)" />
							</td>
							<th>
								统一编号
							</th>
							<td>
								${aesset.taassetsnumber}
								<input type="hidden" name="aesset.taassetsnumber"
									value="${aesset.taassetsnumber}" />
							</td>
						</tr>

						<tr>
							<th>
								设备出厂原价
							</th>
							<td colspan="5">
								${aesset.taassetsNetworth}
								<input type="hidden" name="aesset.taassetsNetworth"
									value="${aesset.taassetsNetworth}" />
							</td>
						</tr>
						<tr>
							<th>
								报废原因及根据
							</th>
							<td colspan="5">
								<input type="text" name="aesset.tascrappedwilling"
									value="${aesset.tascrappedwilling}" id="baofeiyuanyin"
									style="width: 300px; height: 80px">
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;
								<input type="reset" value="取  消" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<SCRIPT type="text/javascript">
		function HS_DateAdd(interval,number,date){
	number = parseInt(number);
	if (typeof(date)=="string"){var date = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2])}
	if (typeof(date)=="object"){var date = date}
	switch(interval){
	case "y":return new Date(date.getFullYear()+number,date.getMonth(),date.getDate()); break;
	case "m":return new Date(date.getFullYear(),date.getMonth()+number,checkDate(date.getFullYear(),date.getMonth()+number,date.getDate())); break;
	case "d":return new Date(date.getFullYear(),date.getMonth(),date.getDate()+number); break;
	case "w":return new Date(date.getFullYear(),date.getMonth(),7*number+date.getDate()); break;
	}
	}
	function checkDate(year,month,date){
		var enddate = ["31","28","31","30","31","30","31","31","30","31","30","31"];
		var returnDate = "";
		if (year%4==0){enddate[1]="29"}
		if (date>enddate[month]){returnDate = enddate[month]}else{returnDate = date}
		return returnDate;
	}
	
	function WeekDay(date){
		var theDate;
		if (typeof(date)=="string"){theDate = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2]);}
		if (typeof(date)=="object"){theDate = date}
		return theDate.getDay();
	}
	function HS_calender(){
		var lis = "";
		var style = "";
		/* http://www.codefans.net */
		style +="<style type='text/css'>";
		style +=".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
		style +=".calender ul {list-style-type:none; margin:0; padding:0;}";
		style +=".calender .day { background-color:#EDF5FF; height:20px;}";
		style +=".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
		style +=".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
		style +=".calender li a:hover { color:#f30; text-decoration:underline}";
		style +=".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
		style +=".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
		style +=".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
		style +=".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
		style +=".calender .LastMonth { float:left;}";
		style +=".calender .NextMonth { float:right;}";
		style +=".calenderBody {clear:both}";
		style +=".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
		style +=".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
		style +=".today a { color:#f30; }";
		style +=".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
		style +=".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
		style +=".calenderBottom a.closeCalender{float:right}";
		style +=".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
		style +="</style>";
	
		var now;
		if (typeof(arguments[0])=="string"){
			selectDate = arguments[0].split("-");
			var year = selectDate[0];
			var month = parseInt(selectDate[1])-1+"";
			var date = selectDate[2];
			now = new Date(year,month,date);
		}else if (typeof(arguments[0])=="object"){
			now = arguments[0];
		}
		var lastMonthEndDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+now.getMonth()+"-01").getDate();
		var lastMonthDate = WeekDay(now.getFullYear()+"-"+now.getMonth()+"-01");
		var thisMonthLastDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-01");
		var thisMonthEndDate = thisMonthLastDate.getDate();
		var thisMonthEndDay = thisMonthLastDate.getDay();
		var todayObj = new Date();
		today = todayObj.getFullYear()+"-"+todayObj.getMonth()+"-"+todayObj.getDate();
		
		for (i=0; i<lastMonthDate; i++){  // Last Month's Date
			lis = "<li class='lastMonthDate'>"+lastMonthEndDate+"</li>" + lis;
			lastMonthEndDate--;
		}
		for (i=1; i<=thisMonthEndDate; i++){ // Current Month's Date
	
			if(today == now.getFullYear()+"-"+now.getMonth()+"-"+i){
				var todayString = now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-"+i;
				lis += "<li><a href=javascript:void(0) class='today' onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
			}else{
				lis += "<li><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
			}
			
		}
		var j=1;
		for (i=thisMonthEndDay; i<6; i++){  // Next Month's Date
			lis += "<li class='nextMonthDate'>"+j+"</li>";
			j++;
		}
		lis += style;
	
		var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=HS_calender(HS_DateAdd('m',1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Next Month'>&raquo;</a>";
		CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=HS_calender(HS_DateAdd('m',-1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Previous Month'>&laquo;</a>";
		CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='CalenderselectYear(this)' title='Click here to select other year' >"+now.getFullYear()+"</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='CalenderselectMonth(this)' title='Click here to select other month'>"+(parseInt(now.getMonth())+1).toString()+"</a></span>月"; 
	
		if (arguments.length>1){
			arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
			arguments[1].parentNode.innerHTML = CalenderTitle;
	
		}else{
			var CalenderBox = style+"<div class='calender'><div class='calenderTitle'>"+CalenderTitle+"</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>"+lis+"</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='closeCalender(this)'>×</a><span><span><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+todayString+"'>Today</a></span></span></div></div>";
			return CalenderBox;
		}
	}
	function _selectThisDay(d){
		var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
			var str=d.title;
			var year1=str.split("-")[0];
			var month1=str.split("-")[1];
			var day1=str.split("-")[2];
			if(parseInt(month1)<10){month1="0"+month1;}
			if(parseInt(day1)<10){day1="0"+day1;}
			str=year1+"-"+month1+"-"+day1;
			boxObj.targetObj.value =str;
			boxObj.parentNode.removeChild(boxObj);
	}
	function closeCalender(d){
		var boxObj = d.parentNode.parentNode.parentNode;
			boxObj.parentNode.removeChild(boxObj);
	}
	
	function CalenderselectYear(obj){
			var opt = "";
			var thisYear = obj.innerHTML;
			for (i=1970; i<=2020; i++){
				if (i==thisYear){
					opt += "<option value="+i+" selected>"+i+"</option>";
				}else{
					opt += "<option value="+i+">"+i+"</option>";
				}
			}
			opt = "<select onblur='selectThisYear(this)' onchange='selectThisYear(this)' style='font-size:11px'>"+opt+"</select>";
			obj.parentNode.innerHTML = opt;
	}
	
	function selectThisYear(obj){
		HS_calender(obj.value+"-"+obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML+"-1",obj.parentNode);
	}
	
	function CalenderselectMonth(obj){
			var opt = "";
			var thisMonth = obj.innerHTML;
			for (i=1; i<=12; i++){
				if (i==thisMonth){
					opt += "<option value="+i+" selected>"+i+"</option>";
				}else{
					opt += "<option value="+i+">"+i+"</option>";
				}
			}
			opt = "<select onblur='selectThisMonth(this)' onchange='selectThisMonth(this)' style='font-size:11px'>"+opt+"</select>";
			obj.parentNode.innerHTML = opt;
	}
	function selectThisMonth(obj){
		HS_calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML+"-"+obj.value+"-1",obj.parentNode);
	}
	function HS_setDate(inputObj){
		var calenderObj = document.createElement("span");
		calenderObj.innerHTML = HS_calender(new Date());
		calenderObj.style.position = "absolute";
		calenderObj.targetObj = inputObj;
		inputObj.parentNode.insertBefore(calenderObj,inputObj.nextSibling);
	}
		</SCRIPT>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
