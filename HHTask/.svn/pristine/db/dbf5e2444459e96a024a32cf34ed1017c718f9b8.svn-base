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
	</head>
	<body onload="createDept('interviewDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="JLMApplicationAction_add.action" method="post"
					onsubmit="return adddata()">
					<table class="table" align="center" style="width: 92%;"
						id="tablebod">
							<tr>
								<td colspan="2" align="center">
									<h4>
										卷帘门申请表
									</h4>
									<h3>
										<font color="red">${successMessage}</font>
									</h3>
									<h3>
										<font color="red">${errorMessage}</font>
									</h3>
								</td>
							</tr>

							<tr>
								<td align="center">
									申请原因
								</td>
								<td colspan="1" align="left">
									<input type="radio" name="jlmApplication.reason" id="reason1"
										value="取/送货" onclick="leixing()">
									取/送货&nbsp;
									<input type="radio" name="jlmApplication.reason" id="reason2"
										value="设备修理" onclick="leixing()">
									设备修理&nbsp;
									<input type="radio" name="jlmApplication.reason" id="reason3"
										value="紧急" onclick="leixing()">
									紧急&nbsp;
								</td>
							</tr>
							<tr id="songhuo" style="display: none;">
								<td align="center">
									送货单号
								</td>
								<td align="left" colspan="1">
									<select id="danhao" name="jlmApplication.planNumber">
										<option value="">请选择单号</option>
									</select>
								</td>
							</tr>
							<s:iterator value="accessEquipmentList" id="accwsse"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 25px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									<input id="dor<s:property value="#pageStatus.index+1" />" onclick="chageNum()"
										type="checkbox" name="operationList[<s:property value="#pageStatus.index+1" />].doorId" value='${accwsse.id}'/>
									${accwsse.equipmentName}
								</td>
								<td colspan="1" align="left">
									开门时间
									<input class="Wdate" type="text"
										name="operationList[<s:property value="#pageStatus.index+1" />].startTime"
										id="startTime<s:property value="#pageStatus.index+1" />"
										value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})">
									关门时间
									<input class="Wdate" type="text"
										name="operationList[<s:property value="#pageStatus.index+1" />].endTime"
										id="endTime<s:property value="#pageStatus.index+1" />"
										value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
										onchange="diffTime(<s:property value="#pageStatus.index+1" />)">
									时长
									<input type="text"
										style="width: 100px; height: 20px; border: none;"
										name="operationList[<s:property value="#pageStatus.index+1" />].timeLength"
										id="timeLength<s:property value="#pageStatus.index+1" />"
										value="" readonly="readonly">
									<input type="hidden" id="times<s:property value="#pageStatus.index+1" />" value="0" readonly="readonly"/>
								</td>
							</s:iterator>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="确认申请" id="queren"
										style="width: 80px; height: 35px;" disabled="disabled">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 80px; height: 35px;">
								</td>
							</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function(){
	$.ajax( {
			url : "WaigouwaiweiPlanAction!findAllDshJson.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(useradsfa) {
				//$("<option></option>").appendTo("#danhao");
				$(useradsfa).each(
					function() {
						$(
							"<option value='" + this.planNumber
									+ "'>" + this.planNumber +"|"
									+ this.rootMarkId 
									+ "|" +this.gysName +"|"
									+ this.type
									+ "</option>")
							.appendTo("#danhao")
					});
			},
			error : function() {
				alert("服务器异常!");
			}
		});
});
		
function diffTime(index) {
	var diff1 = $("#startTime" + index).val();//时间差的毫秒数  
	var diff2 = $("#endTime" + index).val();//时间差的毫秒数  
	var diff = Date.parse(diff2) - Date.parse(diff1);
	var mintime = diff/60000;
	$("#times" + index).val(mintime);
	//计算出相差天数  
	var days = Math.floor(diff / (24 * 3600 * 1000));
	//计算出小时数  
	var leave1 = diff % (24 * 3600 * 1000); //计算天数后剩余的毫秒数  
	var hours = Math.floor(leave1 / (3600 * 1000));
	//计算相差分钟数  
	var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数  
	var minutes = Math.floor(leave2 / (60 * 1000));
	//计算相差秒数  
	var leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数  
	var seconds = Math.round(leave3 / 1000);
	var returnStr = seconds + "秒";
	if (minutes > 0) {
		returnStr = minutes + "分" + returnStr;
	}
	if (hours > 0) {
		returnStr = hours + "小时" + returnStr;
	}
	if (days > 0) {
		returnStr = days + "天" + returnStr;
	}
	$("#timeLength" + index).val(returnStr);
}

var leixin = '';
function leixing() {
	var reasons = document.getElementsByName("jlmApplication.reason");
	for(var i=0; i<reasons.length; i ++){
        if(reasons[i].checked){
            leixin = reasons[i].value;
        }
    }
	if(leixin == '取/送货'){
		$("#songhuo").show();
		$("#danhao").removeAttr("disabled");
	}else{
		$("#songhuo").hide();
		$("#danhao").attr("disabled", "disabled");
	}
}
function adddata() {
	var size = "<s:property value='accessEquipmentList.size()'/>";
	for ( var i = 1; i <= size; i++) {
		var doorid = "dor"+i;
		var door = document.getElementById(doorid);
		if(door.checked == true){
			if (!validateText("startTime"+i, "门"+i+"开门时间")) {
				return false;
			}
			if (!validateText("endTime"+i, "门"+i+"关门时间")) {
				return false;
			}
			var shus = $("#times"+i).val();
			if(shus <= 0){
				alert("请正确填写门"+i+"申请时间");
				return false;
			}else if(shus < 30){
				alert("门"+i+"申请时长不能小于30分钟");
				return false;
			}else if(shus > 1440){
				alert("门"+i+"申请时长不能超过一天");
				return false;
			}
		}
	}
	if (leixin == '') {
		alert("申请类型不能为空");
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

function chageNum(){
	var status = false;
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked == true) {
				status = true;
				break;
			}
		}
	}
	if(status){
		$("#queren").removeAttr("disabled");
	}else{
		$("#queren").attr("disabled", "disabled");
	}
}

</script>
	</body>
</html>
