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
		<SCRIPT type="text/javascript">
		$(function() {
			//加载所有件号
			$.ajax( {
				type : "post",
				url : "ProcardAction!mmMarkId.action",
				dataType : "json",
				success : function(data) {
					//填充部门信息
					$(data).each(
						function() {
							$(
								"<option value='" + this + "'>" + this + "</option>").appendTo("#markId_1");
						});
					$("#markId_1").tinyselect();
				}
			});
		});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
					<h3>测量件号</h3>
				</div>
			</div>
			<div align="center" style="width: 100%;">
				<div >
					<table class="table">
						<tbody>
							<tr align="center">
								<th>请选择件号</th>
								<td align="center">
									<SELECT id="markId_1" style="width: 157px;" name="markId" onchange="picihuoqu()">
										<option >
											请选择件号
										</option>
									</SELECT>
								</td>
								<th>请选择批次</th>
								<td id="piciNum_2" align="center" >
									<select id="piciNum_1" name="piciNum" style="width: 157px; height: 35px;" onclick="markIdNotNull()">
										<option value="">请选择批次</option>
									</select>
								</td>
							</tr>
							<%--<tr>
								<th>请输入检测数量</th>
								<td align="center" style="height: 39px;"><input type="text" id="num"/> 件</td>
								<td align="center">标准值：<input type="text" id="biaozhun" readonly="readonly"/></td>
								<td align="center"><input style="width: 65px; height: 30px;" type="button" value="开始检测" onclick="startDetect1()"/>
								</td>
							</tr>
						--%></tbody>
					</table>
				</div>
				<div style="margin-top: 0px;">
					<form action="ProcardAction!addProcardSpeci.action" method="post" onsubmit="return yanAdd()">
						<table style="border-top-width: 0px;" align="center" class="table"
						id="tableb">
						<tr style="display: none;"></tr>
							<tbody>
								<%--<tr align="center">
									<th style="border-top-width: 0px;" align="center">1<input type="hidden" value="1" name=""/>
									</th>
									<td style="border-top-width: 0px;" align="center">
										<b>件号：</b>
										<input type="text" name="list[i]" value="" readonly="readonly"/>
									</td>
									<td style="border-top-width: 0px;" align="center">
										<b>批次：</b>
										<input type="text" name="list[i]" value="" readonly="readonly"/>
									</td>
									<td style="border-top-width: 0px;" align="center">
										<b>测量实际值：</b>
										<input type="text" name="list[i]" value="p913"/>
									</td>
								</tr>
								--%>
								<tr >
									<th align='center' style='border-top-width: 0px;'>1
									</th>
									<td align='center' style='border-top-width: 0px;'>
										<b>件号：</b>
										<input type='text' readonly='readonly' name='procardSpecification.markId' value='${procardSpecification.markId}' id='markId0' />
										<input type='hidden' name='procardSpecification.proName' id='proName0' value="${procardSpecification.proName}"/>
									</td>
									<td align='center' style='border-top-width: 0px;'>
										<b>批次：</b>
										<input type='text' readonly='readonly' name='procardSpecification.selfCard' value='${procardSpecification.selfCard}' id='selfCard0' />
									</td>
									<td align='center' style='border-top-width: 0px;'>
										<b>实际测量值：</b>
										<input type='text' name='procardSpecification.markIdActualSize' id='markIdActualSize0' onkeydown="onleshi('markIdActualSize0')"/>
										<input type='hidden' name='procardSpecification.markIdSize' id='markIdSize0' value="${procardSpecification.markIdSize}"/>
									</td>
								</tr>
								<tr style='display:none' align='center'>
									<td colspan='4'>
										<input style='width: 70px; height: 45px;' type='submit' value='提交'/>
									</td>
								</tr>
							</tbody>
						</table>
						<div style="margin-top:5px" align="center" ><input align="top" type="text" style="color: red;font-size: 26px; border: 0px;" readonly="readonly" id="eroren" value="${errorMessage}"/></div>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">

function picihuoqu(){
	var markId = $("#markId_1").val();
if (markId != "") {
	$.ajax({
			type : "post",
			url : "ProcardAction!mmPiCi.action",
			data : {
				markId : markId
			},
			dataType : "json",
			success : function(data) {
				$("#piciNum_1").empty();//清空
				$(data).each(
					function() {
						$(
						"<option value='" +this.selfCard + "_" + this.proName + "'>"
								+ this.selfCard + "</option>")
						.appendTo("#piciNum_1");
					});
				markIdNotNull();
				//var tinyselect = $(".tinyselect");
				//if (tinyselect[1] != null) {
				//	document.getElementById("piciNum_2").removeChild(
				//			tinyselect[1]);
				//}
				//$("#piciNum_1").tinyselect();
				$("#markId0").val(markId);
				$("#selfCard0").val();
				$("#proName0").val();
			}
		});
	} else {
		$("#piciNum_1").empty();//清空
		$("#").val("");
	}
}

function onleshi(obj){
	var s = $("#"+obj).val();
	$("#"+obj).val(s.replace(/\s/ig,''));
	//var markId = $("#markId_1").val();//件号
	//var markIdActualSize = s.replace(/\s/ig,'');
	
	/*if(s != ""){
		$.ajax({
			type : "post",
			url : "ProcardAction!addProcardSpeci.action",
			data : {
				"procardSpecification.markId" : markId
			},
			dataType : "json",
			success : function(data) {
				$("#eroren").val(data.message);
			}
		});
	} else {
	}
	*/
}


function markIdNotNull() {
	if ($("#markId_1").val() == "" || $("#markId_1").val() == "请选择件号") {
		alert("件号不能为空！");
		return false;
	}else{
		var sqlt = $("#piciNum_1").val().split("_")
		$("#selfCard0").val(sqlt[0]);
		$("#proName0").val(sqlt[1]);
	}
	
}

function startDetect(){
	if (!validateText("markId_1", "件号")) {
		return false;
	}
	if (!validateText("piciNum_1", "批次")) {
		return false;
	}
	var mark = $("#markId_1").val();
	var pici = $("#piciNum_1").val();
	var html="";
	var e = 1;
	for(var i=0;i<e;i++){
		html+="<tr >" +
				"<th align='center' style='border-top-width: 0px;'>" +(i+1)+
				"</th>" +
				"<td align='center' style='border-top-width: 0px;'>" +
					"<b>件号：</b>" +
					"<input type='text' readonly='readonly' name='procardSpecificationList.markId"+ i +"' value='"+mark+"' id='markId"+ i + "' />" +
					"<input type='hidden' name='procardSpecificationList.proName"+ i +"' id='proName"+ i + "' />" +
				"</td>" +
				"<td align='center' style='border-top-width: 0px;'>" +
					"<b>批次：</b>" +
					"<input type='text' readonly='readonly' name='procardSpecificationList["
							+ i + "].selfCard"+ i +"' value='"+pici+"' id='selfCard"+ i + "' />" +
				"</td>" +
				"<td align='center' style='border-top-width: 0px;'><b>实际测量值：</b>" +
					"<input type='text' name='procardSpecificationList["
							+ i + "].markIdActualSize' id='markIdActualSize" + i + "' onkeydown=\"onleshi('markIdActualSize"+ i + "')\"/>" +
					"<input type='hidden' name='procardSpecificationList["
							+i+ "].markIdSize' id='markIdSize"+ i + "' />" +
				"</td>" +
			  "</tr>";
	}
	if(e>0)
		html+="<tr style='display:none' align='center'><td colspan='4'><input style='width: 70px; height: 45px;' type='submit' value='提交'/></td></tr>";
	//$(html).appendTo("#tableb");
	$("#tableb").html(html);
}
function deleteKongjian(){
	$("#tableb").html("");
}
function yanAdd(){
	for ( var i = 0; i < 1; i++) {
		n = i + 1;
		var markId = "markId" + i;
		var selfCard = "selfCard" + i;
		var markIdActualSize = "markIdActualSize" + i;
		if (!validateText1(markId, "件号" + n)) {
			return false;
		}
		if (!validateText1(selfCard, "批次" + n)) {
			return false;
		}
		if (!validateText1(markIdActualSize, "实际测量值" + n)) {
			return false;
		}
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "" || textValue == "请选择件号") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function validateText1(id, textname) {
	var textValue = $.trim($("#" + id).val());
	//$("#" + id).val(textValue);.replace(/\s/ig,'')
	if (textValue == null || textValue == "") {
		return false;
	}
	return true;
}
function onleshijian(obj){
	$("#"+obj).val("");
}
$("#num").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
	if(tmptxt>30){
		alert("一次检测数量不得大于30件");$(this).val(30);
	}
})
		</SCRIPT>
	</body>
</html>
