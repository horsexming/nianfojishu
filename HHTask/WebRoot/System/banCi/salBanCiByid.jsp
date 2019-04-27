<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	</head>
	<body>
		<form action="banCiAction_updateBanCi.action" method="post"
			onsubmit="return checkOK()">
			<table class="table" id="tablebod">
				<tbody>
					<tr>
						<th colspan="4">
							<font size="5">修改班次</font>
						</th>
					</tr>
					<tr>
						<th align="right">
							班次名称:
							<input name="banCi.id" value="${banCi.id}" type="hidden"/>
						</th>
						<td>
							<input id="name" name="banCi.name" value="${banCi.name}" />
							<font color="red">*</font>
						</td>
						<th align="right">
							下班时间是否覆盖
						</th>
						<td>
						<s:if test='banCi.xiaFuis=="是"'>
							<input type="radio" name="banCi.xiaFuis"
								id="xiaFuis1" value="是" checked="checked">
							<label for="xiaFuis1">
								是&nbsp;
							</label>
							<input type="radio" name="banCi.xiaFuis"
								id="xiaFuis2" value="否">
							<label for="xiaFuis2">
								否&nbsp;
							</label>
						</s:if>
						<s:else>
							<input type="radio" name="banCi.xiaFuis"
								id="xiaFuis1" value="是">
							<label for="xiaFuis1">
								是&nbsp;
							</label>
							<input type="radio" name="banCi.xiaFuis"
								id="xiaFuis2" value="否" checked="checked">
							<label for="xiaFuis2">
								否&nbsp;
							</label>
						</s:else>
						</td>
					</tr>
					<s:iterator value="banCi.banCiTime" id="banciTimeList" status="pageStatus">
						<tr>
							<th align="right">
								第${banciTimeList.duan}段上班时间:<input type="hidden" name="banciTimeList[${pageStatus.index}].id" value="${banciTimeList.id}"/>
								<input type="hidden" name="banciTimeList[${pageStatus.index}].duan" value="${banciTimeList.duan}"/>
							</th>
							<td>
								<select id="dayType${pageStatus.index}" name="banciTimeList[${pageStatus.index}].dayType"  >
									<option value="${banciTimeList.dayType}">${banciTimeList.dayType}</option>
									<option value="当日">当日</option>
									<option value="次日">次日</option>
								</select>
								<input type="text" id="startTime${pageStatus.index}" class="Wdate"
									name="banciTimeList[${pageStatus.index}].startTime" value="${banciTimeList.startTime}"
									onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
							<th align="right" style="width: 180px;">
								<s:if test="#banciTimeList.startIsDaka">
									<b> 必须打卡:</b><input type="radio" name="banciTimeList[${pageStatus.index}].startIsFor" id="startIsFor${pageStatus.index}"
										value="是" checked="checked">
									<label for="startIsFor${pageStatus.index}">
										是&nbsp;
									</label>
									<input type="radio" name="banciTimeList[${pageStatus.index}].startIsFor" id="startIsFor${pageStatus.index}1"
										value="否">
									<label for="startIsFor${pageStatus.index}1">
										否&nbsp;
									</label>
								</s:if>
								<s:else>
									<b> 必须打卡:</b><input type="radio" name="banciTimeList[${pageStatus.index}].startIsFor" id="startIsFor${pageStatus.index}"
										value="是">
									<label for="startIsFor${pageStatus.index}">
										是&nbsp;
									</label>
									<input type="radio" name="banciTimeList[${pageStatus.index}].startIsFor" id="startIsFor${pageStatus.index}1"
										value="否" checked="checked">
									<label for="startIsFor${pageStatus.index}1">
										否&nbsp;
									</label>
								</s:else>
							</th>
							<td>
								<b>允许提前：</b><input type="text" id="startBeforeMin${pageStatus.index}" style="width: 60px;"
									name="banciTimeList[${pageStatus.index}].startBeforeMin" value="${banciTimeList.startBeforeMin}" onblur="mustBeNumber('startBeforeMin${pageStatus.index}')"
									>分
								<b>允许推迟：</b><input type="text" id="startLaterMin${pageStatus.index}" style="width: 60px;"
									name="banciTimeList[${pageStatus.index}].startLaterMin" value="${banciTimeList.startLaterMin}" onblur="mustBeNumber('startLaterMin${pageStatus.index}')"
									>分
							</td>
						</tr>
						<tr>
							<th align="right">
								第${banciTimeList.duan}段下班时间:
							</th>
							<td>
								<select id="edayType${pageStatus.index}" name="banciTimeList[${pageStatus.index}].edayType"  >
									<option value="${banciTimeList.edayType}">${banciTimeList.edayType}</option>
									<option value="当日">当日</option>
									<option value="次日">次日</option>
								</select>
								<input type="text" id="endTime${pageStatus.index}" class="Wdate"
									name="banciTimeList[${pageStatus.index}].endTime" value="${banciTimeList.endTime}"
									onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
							<th align="right" style="width: 180px;">
								<s:if test="#banciTimeList.endIsDaka">
									<b> 必须打卡:</b><input type="radio" name="banciTimeList[${pageStatus.index}].endIsFor" id="endIsFor${pageStatus.index}"
										value="是" checked="checked">
									<label for="endIsFor${pageStatus.index}">
										是&nbsp;
									</label>
									<input type="radio" name="banciTimeList[${pageStatus.index}].endIsFor" id="endIsFor${pageStatus.index}1"
										value="否">
									<label for="endIsFor${pageStatus.index}1">
										否&nbsp;
									</label>
								</s:if>
								<s:else>
									<b> 必须打卡:</b><input type="radio" name="banciTimeList[${pageStatus.index}].endIsFor" id="endIsFor${pageStatus.index}"
										value="是">
									<label for="endIsFor${pageStatus.index}">
										是&nbsp;
									</label>
									<input type="radio" name="banciTimeList[${pageStatus.index}].endIsFor" id="endIsFor${pageStatus.index}1"
										value="否" checked="checked">
									<label for="endIsFor${pageStatus.index}1">
										否&nbsp;
									</label>
								</s:else>
							</th>
							<td>
								<b>允许提前：</b><input type="text" id="endBeforeMin${pageStatus.index}" style="width: 60px;"
									name="banciTimeList[${pageStatus.index}].endBeforeMin" value="${banciTimeList.endBeforeMin}" onblur="mustBeNumber('endBeforeMin${pageStatus.index}')"
									>分
								<b>允许推迟：</b><input type="text" id="endLaterMin${pageStatus.index}" style="width: 60px;"
									name="banciTimeList[${pageStatus.index}].endLaterMin" value="${banciTimeList.endLaterMin}" onblur="mustBeNumber('endLaterMin${pageStatus.index}')"
									>分
							</td>
						</tr>
					</s:iterator>
					
					<tr>
						<th align="right">
							添加休息时段:
						</th>
						<td align="right" style="border-right-width: 0px;">
							<input type="button" onclick="tianjia()" value="添加"
								style="width: 110px; height: 25px;" />
						</td>
						<td align="left" colspan="2" style="border-left-width: 0px;">
							<input type="button" onclick="delTime()" id="shanchu"
								style="display: none; width: 110px; height: 25px;" value="删除" />
						</td>
					</tr>
					<tr>
						<th align="right">
							上班星期:<input type="hidden" id="sbdate" value="${banCi.sbdate}"/>
						</th>
						<td colspan="3">
							<input type="checkbox" id="date1" name="banCi.sbdate" value="星期一"/>星期一
							<input type="checkbox" id="date2" name="banCi.sbdate" value="星期二"/>星期二
							<input type="checkbox" id="date3" name="banCi.sbdate" value="星期三"/>星期三
							<input type="checkbox" id="date4" name="banCi.sbdate" value="星期四"/>星期四
							<input type="checkbox" id="date5" name="banCi.sbdate" value="星期五"/>星期五
							<input type="checkbox" id="date6" name="banCi.sbdate" value="星期六"/>星期六
							<input type="checkbox" id="date7" name="banCi.sbdate" value="星期日"/>星期日
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" value="修改" class="input" id="sub" />
							<input type="reset" value=" 重置" class="input" />
						</td>
					</tr>
					<tr>
<%--						<td colspan="4"><font color="red">注：上下班时间必须要打卡的时间需要设置有效打卡时段。</font></td>--%>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		parent.location.reload(true);//刷新父页面
	}
	week();
});
var yiyou = 0;
var zhi = <s:property value="banCi.banCiTime.size()" />;
if(zhi!=null){
	yiyou = zhi;
}
var size = 0;//存档信息条数
var index = yiyou-1;//存档信息下标
var tiao = yiyou+1;//班次条数
var xia = yiyou;
var gai = index*2;
function tianjia() {
	if (size >= 9 - yiyou*2) {
		alert("上班时段条数达到上限");
		return false;
	}
	var trindex = 3 + gai;
	//alert("trindex"+trindex +" size"+size+" yiyou"+yiyou+" tiao"+tiao+" index"+index+" xia"+xia);
	var dayType = "dayType" + xia;//
	var edayType = "edayType" + xia;//
	var start = "startTime" + xia;//
	var end = "endTime" + xia;//
	var startIsFor = "startIsFor" + xia;//
	var startIsFor1 = "startIsFor" + xia + "1";//
	var endIsFor = "endIsFor" + xia;//
	var endIsFor1 = "endIsFor" + xia + "1";//
	var startBeforeMin = "startBeforeMin" + xia;//
	var startLaterMin = "startLaterMin" + xia;//
	var endBeforeMin = "endBeforeMin" + xia;//
	var endLaterMin = "endLaterMin" + xia;//
	$("#tablebod>tbody>tr")
			.eq(trindex)
			.after(
					"<tr id='addtr"
							+ xia
							+ "'>"
							+ "<th colspan='1' align='right'>第"+tiao+"段上班时间: " +
							" <input type='hidden' name='banciTimeList["
							+ xia
							+ "].duan' value='"+tiao+"'></th>"
							+ "<td align='left'><select id="+dayType
							+ " name='banciTimeList["+xia+"].dayType' ><option value='当日' selected='selected'>当日</option><option value='次日'>次日</option></select>"
							+ " <input type='text' name='banciTimeList["
							+ xia
							+ "].startTime' onclick=\"WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})\" "
							+ "class='Wdate' id="
							+ start
							+ "></td><th align='right' style='width: 180px;'><b> 必须打卡:</b>"
							+ "<input type='radio' checked='checked' value='是' name='banciTimeList["
							+ xia
							+ "].startIsFor' id="
							+ startIsFor
							+ "> <label for="
							+ startIsFor
							+ ">是&nbsp;</label>"
							+ " <input type='radio' value='否' name='banciTimeList["
							+ xia + "].startIsFor' id=" + startIsFor1 + "> <label for="
							+ startIsFor1 + ">否&nbsp;</label></th>"
							+ "<td><b>允许提前：</b><input type='text' id="+startBeforeMin+" style='width: 60px;'name='banciTimeList["
							+ xia
							+ "].startBeforeMin' value='' onblur=\"mustBeNumber('"+startBeforeMin+"')\">分 <b>允许推迟：</b><input type='text' id="
							+ startLaterMin+" style='width: 60px;' name='banciTimeList["
							+ xia
							+ "].startLaterMin' value='' onblur=\"mustBeNumber('"+startLaterMin+"')\">分</td>" 
						+ "</tr>" 
						+ "<tr>"
							+ "<th align='right'>第"+tiao+"段下班时间:"
							+ "</th>"
							+ "<td><select id="+edayType
							+ " name='banciTimeList["+xia+"].edayType' ><option value='当日' selected='selected'>当日</option><option value='次日'>次日</option></select>"
							+ " <input type='text' name='banciTimeList["
							+ xia 
							+ "].endTime' onclick=\"WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})\" class='Wdate' id="
							+ end
							+ "></td><th align='right' style='width: 180px;'><b> 必须打卡:</b>"
							+ "<input type='radio' checked='checked' value='是' name='banciTimeList["
							+ xia
							+ "].endIsFor' id="
							+ endIsFor
							+ "> <label for="
							+ endIsFor
							+ ">是&nbsp;</label>"
							+ " <input type='radio' value='否' name='banciTimeList["
							+ xia + "].endIsFor' id=" + endIsFor1 + "> <label for="
							+ endIsFor1 + ">否&nbsp;</label></th><td><b>允许提前：</b><input type='text' id="+endBeforeMin+" style='width: 60px;'name='banciTimeList["
							+ xia
							+ "].endBeforeMin' value='' onblur=\"mustBeNumber('"+endBeforeMin+"')\">分 <b>允许推迟：</b><input type='text' id="
							+ endLaterMin+" style='width: 60px;' name='banciTimeList["
							+ xia
							+ "].endLaterMin' value='' onblur=\"mustBeNumber('"+endLaterMin+"')\">分</td>" 
						+ "</tr>");
	size++;
	size++;
	index++;
	index++;
	gai++;
	gai++;
	xia++;
	tiao++;
	document.getElementById("shanchu").style.display = "block";

}
//删除存档信息
function delTime() {
	tablebod.deleteRow(index + 2 + yiyou);
	tablebod.deleteRow(index + 1 + yiyou);
	size--;
	size--;
	index--;
	index--;
	gai--;
	gai--;
	xia--;
	tiao--;
	if (size < 1) {
		document.getElementById("shanchu").style.display = "none";
	}
}

function week(){
	var sbdate = $("#sbdate").val();
	var sbdateList = sbdate.split(", ");
	for(i=0;i<sbdateList.length;i++){
		var date = sbdateList[i];
		if(date=='星期一'){
			var date1 = document.getElementById("date1");
			date1.checked = true;
		}else if(date=='星期二'){
			var date2 = document.getElementById("date2");
			date2.checked = true;
		}else if(date=='星期三'){
			var date3 = document.getElementById("date3");
			date3.checked = true;
		}else if(date=='星期四'){
			var date4 = document.getElementById("date4");
			date4.checked = true;
		}else if(date=='星期五'){
			var date5 = document.getElementById("date5");
			date5.checked = true;
		}else if(date=='星期六'){
			var date6 = document.getElementById("date6");
			date6.checked = true;
		}else if(date=='星期日'){
			var date7 = document.getElementById("date7");
			date7.checked = true;
		}
	}
}

function checkOK() {
	if (!validateText("name", "班次名称")) {
		return false;
	}
	for ( var i = 0; i < xia; i++) {
		n = i + 1;
		var start = "startTime" + i;
		var end = "endTime" + i;
		var day = "dayType" + i;
		var eday = "edayType" + i;
		if (!validateText_1(start, end, day, eday, "第"+n+"段工作时间")) {
			return false;
		}
	}
	
	//检验星期不能为空
	var sbdates = document.getElementsByName("banCi.sbdate");
	var sbdate = "";
	for ( var i = 0; i < sbdates.length; i++) {
		if (sbdates[i].checked == true) {
			sbdate += sbdates[i].value + ",";
		}
	}
	if (sbdates != null && sbdate == "") {
		alert("请选择上班星期!");
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	return true;
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function validateText_1(id, id2 ,e1,e2,textname) {
	var textValue = $.trim($("#" + id).val());
	var textValue1 = $.trim($("#" + id2).val());
	if ((textValue == null || textValue == "")||(textValue1 == null || textValue1 == "")) {
		alert(textname + "不能为空");
		return false;
	}
<%--	if (((textValue == null || textValue == "")&&(textValue1 != null || textValue1 != ""))||((textValue != null || textValue != "")&&(textValue1 == null || textValue1 == ""))) {--%>
<%--		alert(textname + "不能只填一个");--%>
<%--		return false;--%>
<%--	}--%>
	var day = $.trim($("#"+ e1).val());
	var eday = $.trim($("#"+ e2).val());
	if(day==eday){
		if ((textValue != null || textValue != "")&&(textValue1 != null || textValue1 != "")) {
			var xtime = Date.parse("2017-11-23 "+textValue1) - Date.parse("2017-11-23 "+textValue);
			if(xtime<0){
				alert(textname + "开始时间不能大于结束时间");
				return false;
			}
			/*var xtime1 = Date.parse("2017-10-13 "+textValue) - Date.parse("2017-10-13 "+$("#firsttime").val());
			if(xtime1<0){
				alert(textname + "开始时间不能早于上班时间");
				return false;
			}
			var xtime2 = Date.parse("2017-10-13 "+textValue1) - Date.parse("2017-10-13 "+$("#finishtime").val());
			if(xtime2>0){
				alert(textname + "结束时间不能晚于下班时间");
				return false;
			}
			return true;*/
		}
	}else{
		if(day == '次日' && eday == '当日'){
			alert("第"+n+"段工作时间,开始时间为次日，结束时间不能为当日！")
			return false;
		}
	}
	return true;
}
</script>
</html>
