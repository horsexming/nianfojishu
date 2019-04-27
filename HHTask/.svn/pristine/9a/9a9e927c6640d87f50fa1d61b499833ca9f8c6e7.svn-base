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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<p align="left">
				<font color="red">
					制作方式为自制的，必须填写工序号、工序名，如果有多个，请用;分开<br/>
					关联件号有多个请用;分开，如:DKBA8.042.3156;DKBA4.215.3042<br/>
				</font>
			</p>
				<form action="MouldApplyOrderAction_addmao.action" method="post" enctype="multipart/form-data"  onsubmit="return check()" >
					<table class="table" id="mytable">
						<tr>
							<th align="right">
								客户名称
							</th>
							<td>
								<input type="text" value="" name="mao.kehu" id="kehu"/>
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="" name="mao.ywMarkId" id="ywMarkId"/>
								<input type="hidden" style="width: 130px;" id="gztypename">
								<input type="hidden" style="width: 130px;" id="categoryId" >
							</td>
							<th align="right">
								申请日期
							</th>
							<td colspan="1">
								<input type="text" value="" name="mao.adddate" id="adddate" readonly="readonly"/>
							</td>
							<th align="right">
								申请单号
							</th>
							<td colspan="5">
								<input type="text" value="" name="mao.planNumber" id="planNumber" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								订单数量
							</th>
							<td>
								<input type="text" value="" name="mao.orderNum" id="orderNum" onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')"/>
							</td>
							<th align="right">
								产品交期
							</th>
							<td>
								<input type="text" value="" name="mao.projiaoqiTime" id="projiaoqiTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"   class="Wdate"/>
							</td>
							<th align="right">
								需求日期
							</th>
							<td colspan="1">
								<input type="text" value="" name="mao.xqTime" id="xqTime" 	onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"   class="Wdate"/>
							</td>
							<th align="right">
								样&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板
							</th>
							<td colspan="5">
								<input type="radio" value="有" name="mao.ishaveyangban" checked="checked"/>有
								<input type="radio" value="无" name="mao.ishaveyangban" />无
							</td>
						</tr>
						<tr>
							<th align="right">
								产&nbsp;&nbsp;品&nbsp;图
							</th>
							<td>
								<input type="radio" value="有" name="mao.protuzi" checked="checked"/>有
								<input type="radio" value="无" name="mao.protuzi" />无
							</td>
							<th align="right">
								展&nbsp;&nbsp;开&nbsp;图
							</th>
							<td>
								<input type="radio" value="有" name="mao.zaikaitu" checked="checked"/>有
								<input type="radio" value="无" name="mao.zaikaitu" />无
							</td>
							<th align="right">
								3D图
							</th>
							<td colspan="1">
								<input type="radio" value="有" name="mao.tu3d" checked="checked"/>有
								<input type="radio" value="无" name="mao.tu3d" />无
							</td>
							<th align="right">
								申请类型
							</th>
							<td colspan="2">
								<input type="radio" value="开新模" name="mao.applytype" checked="checked"/>开新模
								<input type="radio" value="改旧模" name="mao.applytype" />改旧模
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th align="right">--%>
<%--								申请类型--%>
<%--							</th>--%>
<%--							<td colspan="3">--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								<input type="radio" value="开新模" name="mao.applytype" checked="checked"/>开新模--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								<input type="radio" value="改旧模" name="mao.applytype" />改旧模--%>
<%--							</td>--%>
<%--							<th align="right">--%>
<%--								样&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板--%>
<%--							</th>--%>
<%--							<td colspan="2">--%>
<%--								<input type="radio" value="有" name="mao.ishaveyangban" checked="checked"/>有--%>
<%--								<input type="radio" value="无" name="mao.ishaveyangban" />无--%>
<%--							</td>--%>
<%--							<td></td>--%>
<%--						</tr>--%>
						<tr>
							<th>件号</th>
							<th>零件名称</th>
							<th>模具号</th>
							<th>单台用量</th>
							<th colspan="2">开模原因/要求说明</th>
							<th>未投产数量</th>
							<th>工序名</th>
							<th>工序号</th>
						</tr>
						<tr>
							<td>
								<input type="text" value="" id="markId_0" name="mao.mdList[0].markId" style="width: 100px;"
								onchange="getWtcNumber(this,'0')"/><font color="red">*</font>
							</td>
							<td>
								<input type="text" value="" id="proName_0" name="mao.mdList[0].proName"/><font color="red">*</font>
							</td>
							<td>
								<input type="text" value="" id="mojuNo_0" name="mao.mdList[0].mojuNo" style="width: 100px;"/>
							</td>
							<td>
								<input type="text" value="" name="mao.mdList[0].yongliang" id="yongliang_0" onkeyup="numyanzheng(this,'zhengshu')" onblur="numyanzheng(this,'zhengshu')" /><font color="red">*</font>
							</td>
							<td colspan="2">
								<textarea rows="1" cols="30" name="mao.mdList[0].yuanyin"  id="yuanyin_0"></textarea>
							</td>
							<td>
								<input type="text" value="" name="mao.mdList[0].wtcNumber" style="width: 100px;" id="wtcNumber_0"
								readonly="readonly" />
							</td>
							<td>
								<input type="text" value="" name="mao.mdList[0].processName"  id="processName_0"/>
							</td>
							<td>
								<input type="text" value="" name="mao.mdList[0].processNo"  id="processNo_0"/>
							</td>
<%--							<td>--%>
<%--								<input type="button" value="添加" onclick="addLine()"/>--%>
<%--								<input type="button" value="删除" onclick="delLin()"/>--%>
<%--							</td>--%>
						</tr>
						<tr id="lastTr">
							<th colspan="20" align="left">选定开模评估人:</th>
						</tr>
						<tr>
							<td colspan="20">
								PMC部(市场需求评估):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="市场需求评估" name="mao.mpgList[0].type" />
								评估人:<SELECT name="mao.mpgList[0].usersId" onmouseover="getuser('PMC部',this)">
									</SELECT>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								生产部(产能需求评估):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="产能需求评估" name="mao.mpgList[1].type" />
								评估人:<SELECT name="mao.mpgList[1].usersId" onmouseover="getuser('生产部',this)">
										<option></option>
									</SELECT>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								工程部(工艺实现评估):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" value="工艺实现评估" name="mao.mpgList[2].type" />
								评估人:<SELECT name="mao.mpgList[2].usersId" onmouseover="getuser('工程技术部',this)">
										<option></option>
									</SELECT>
							</td>
						</tr>
						<tr>
							<th>
								制作方式
							</th>
							<td colspan="20">
								<input type="radio" value="外发" name="mao.maketype" id="maketype1" checked="checked"/> 外发&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" value="自制" name="mao.maketype" id="maketype2"/> 自制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th >
								上传图纸
							</th>
							<td colspan="20">
								<input type="button" id="fileButton" onclick="uploadFile(this)" value="上传附件">
								<div id="fileDiv" style="display: none;">
								</div>			
							</td>
						</tr>
						<tr>
							<th>
								备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
							</th>
							<td colspan="20">
								<textarea rows="1" cols="120"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="20">
								<b>申&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请:</b>${Users.name}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;核:</b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;准:</b>
							</td>
						</tr>
					</table>
					<input type="submit" value="提交" class="input" id="sub" />
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	var nowTime =	getNowFormatDate("-","");
	$("#adddate").val(nowTime);
	getMaxNo();
		
})
function getNowFormatDate(seperator1,seperator2) {
	var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
     var currentdate = "";
    if(seperator1!=null && seperator1!=""){
    	currentdate+=date.getFullYear() + seperator1 + month + seperator1 + strDate;
    }
    if(seperator2!=null && seperator2!=""){
    	if(currentdate.length>0){
    		currentdate+=" ";
    	}
    	currentdate+=date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    }
    return currentdate;
}
function getMaxNo(){
	$.ajax( {
		type : "POST",
		url : "MouldApplyOrderAction_findMaxNo.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#planNumber").val(data);
			}
		}
	})
}
var index =1;
function addLine(){
	var newLine = '<tr><td><input type="text" value="" name="mao.mdList['+index+'].markId" style="width: 100px;"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].proName"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].mojuNo" style="width: 100px;"/></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].yongliang"  /></td>' +
	'<td colspan="2"><textarea rows="1" cols="30" name="mao.mdList['+index+'].yuanyin"  id="yuanyin_0"></textarea></td>' +
	'<td><input type="text" value="" name="mao.mdList['+index+'].more" style="width: 100px;"/></td>' +
	'<td></td> </tr>';
	$("#lastTr").before(newLine);
	index++;
}
function delLin(){
	if(index<1){
		alert("只剩最后一项了,不让删了。");
		return;
	}
	var n = $('#mytable tr').length;
	$($('#mytable tr')[n - 6]).remove();
	index--;
}
function getuser(dept,obj){
	$.ajax( {
		type : "POST",
		url : "DeptNumberAction!findUsersBydept.action",
		data : {
			moveType:dept
		},
		dataType : "json",
		success : function(data) {
				$(obj).empty();
				$(obj).append("<option value = ''></option>");
				$(obj).append("<option value = '10138'>王晓飞</option>");
			if(data!=null){
				$(data).each(function(){
					$(obj).append('<option value="'+this.id+'">'+this.name+'</option>');
				})
			}
		}
	})
}

function check(){
	var bool = $("#maketype2").attr("checked");
	
	for(var i=0;i<index;i++){
		var markId = $("#markId_"+i).val();
		var proName = $("#proName_"+i).val();
		var mojuNo = $("#mojuNo_"+i).val();
		var yongliang = $("#yongliang_"+i).val();
		if(markId == ''){
			alert('第'+(i+1)+'行开模明细未填写开模件号。')
			$("#sub").removeAttr('disabled')
			$("#markId_"+i).focus();
			return false;
		}else if(proName == ''){
			alert('第'+(i+1)+'行开模明细未填写零件名称。')
			$("#sub").removeAttr('disabled')
			$("#proName_"+i).focus();
			return false;
		}else if(yongliang == ''){
			alert('第'+(i+1)+'行开模明细未填写单台用量。')
			$("#sub").removeAttr('disabled')
			$("#yongliang_"+i).focus();
			return false;
		}else if(bool){
			var processName = $("#processName_"+i).val();
			var processNo = $("#processNo_"+i).val();
			if(processName == ''){
				alert('第'+(i+1)+'行开模明细未填写开工序名。')
				$("#sub").removeAttr('disabled')
				$("#processName_"+i).focus();
				return false;
			}else if(processNo == ''){
				alert('第'+(i+1)+'行开模明细未填写开工序号。')
				$("#sub").removeAttr('disabled')
				$("#processNo_"+i).focus();
				return false;
			}
		}
	}
	var attachments = document.getElementsByName('attachment');
	if(attachments!=null && attachments.length>0){
		for(var i=0;i<attachments.length;i++){
			if(attachments[i].value ==''){
				alert('第'+(i+1)+'行开模明细未上传附件,请上传附件')
				$(attachments[i]).focus();
				$("#sub").removeAttr('disabled')
				return false;
			}
		}
	}
	$("#sub").attr('disabled','disabled');
}


var fileDivHTML = "";
var count = 0;
function uploadFile(obj) {
	var fileDiv = document.getElementById("fileDiv");
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><b>名称:</b><input type='text' name='otherNames'><a href='javascript:delFile("
			+ count + ")'> 删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton").value = "上传附件";
		document.getElementById("fileDiv").style.display = "none";
	}
}

function getfirstNo(){
	var categoryId  = $("#categoryId").val();
	var gztypename = $("#gztypename").val();
	var sqNum = 1;
	if(categoryId!='' && gztypename!='' && sqNum!= ''){
			$.ajax( {
		url : 'ChartNOSQAction_getfirstNo.action',
		type : 'POST',
		data : {
			'cq.categoryId':categoryId,
			'cq.gztype':gztypename,
			'cq.sqNum':sqNum
		},
		dataType : 'json',
		success : function(data) {
			if(data!=null){
				var firstNo = data.firstNo;
				$("#wlcode").val(firstNo);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
}

$(function(){
	$.ajax({
		url : 'ChartNOSQAction_getgzTypeList.action',
		type : 'POST',
		data : {
			status:'mj'
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			if(doc!=null){
				$(doc).each(function(){
					$("#gztypename0").val(this.type);
				});
			}
		},
	})
	
})
$(function(){
	$.ajax({
		url : 'ChartNOSQAction_getgzTypeList.action',
		type : 'POST',
		data : {
			secondChartNO:'模具'
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			if(doc!=null){
				 $("#categoryId").val(doc.id);
			}
		},
	})
	
})

function getWtcNumber(obj,num){
	var markIdS = obj.value;
	if(markIdS!=null && markIdS!=''){
		$.ajax({
		url : 'MouldApplyOrderAction_getWtcNumber.action',
		type : 'POST',
		data : {
			markIdS:markIdS
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			if(doc!=null){
				$("#wtcNumber_"+num).val(doc);
			}
		}
	})
	}
}
</SCRIPT>
	</body>
</html>
