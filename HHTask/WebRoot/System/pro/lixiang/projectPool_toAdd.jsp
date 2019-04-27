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
				<h3>
					添加项目池
				</h3>
				<form action="projectPoolAction_addPool.action" method="post"
					onsubmit="return valedata();">
					<table class="table">
						<tr>
							<th width="50%" colspan="2" align="right">
								标题
							</th>
							<td width="50%" colspan="3">
								<input name="projectPool.poolName" id="poolName"
									style="width: 150px;">
							</td>
						</tr>
						<tr>
							<th colspan="2" align="right">
								开始时间
							</th>
							<td colspan="3">
								<input name="projectPool.startTime" class="Wdate" id="startTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th width="50%" colspan="2" align="right">
								预结束时间
							</th>
							<td width="50%" colspan="3">
								<input name="projectPool.reEndTime" class="Wdate" id="endTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th width="50%" colspan="2" align="right">
								金额
							</th>
							<td width="50%" colspan="3">
								<input name="projectPool.totalMoney" id="totalMoney"
									onkeyup="mustBeNumber('totalMoney')">
								/元
							</td>
						</tr>
						<tr>
							<th colspan="5">
								各项目
								<input type="button" value="增加" onclick="addnr()">
							</th>
						</tr>
						<tr id="nrtr0">
							<td align="center">
								名称:
								<input type="hidden" id="proName0" name="projectPool.pmyfList[0].proName"/> 
								<select name="projectPool.pmyfList[0].projectManageId" id="projectManageId0" onchange="changeProName(0)"></select>
								<span onclick="manualInput('0')" id="font0" style="font-size:12px;cursor: pointer;">手动输入</font>
							</td>
							<td align="center">
								描述：
								<textarea name="projectPool.pmyfList[0].remark" rows="3" cols="40"></textarea>
							</td>
							<td align="center">
								开始时间：
								<input name="projectPool.pmyfList[0].startTime" class="Wdate" id="datestart0"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td align="center">
								预完成时间：
								<input name="projectPool.pmyfList[0].reTime" class="Wdate" id="date0"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<td>
								<input type="button" value="删除" onclick="deletenr(0)">
							</td>
						</tr>
						<tr id="submitTr">
							<td align="center" colspan="5">
								<input id="subtn" type="submit" value="提交"
									style="width: 100px; height: 40px" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var projects;
$(document).ready(function(){
 	var d = new Date();
 	var day = d.getDate();        //获取当前日(1-31)
    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
    var s = d.getFullYear().toString() + '-'+addzero(d.getMonth() + 1)+'-'+day;
    //document.getElementById('startTime').value=s;
    $("#startTime").val(s);
    
    $.ajax({
    	type:"post",
    	url:"${pageContext.request.contextPath}/QuotedPrice_getQuotedPriceByCon.action",
    	data:{},
    	dataType:"json",
    	success:function(data){
    		projects=data;
		    setProName(nrindex);
    	}
    });
    
});

function setProName(index){
	$("<option value='0'>请选择项目名称</option>").appendTo("#projectManageId"+index);
	$(projects).each(function(){
		$( "<option value='" + this.id + "'>" + this.proName + "</option>") .appendTo("#projectManageId"+index);
	});
	var tinyselect = $(".tinyselect");
	/*if (tinyselect[1] != null) {
		document.getElementById("depttd"+index).removeChild(
				tinyselect[1]);
	}*/
	$("#projectManageId"+index).tinyselect();
}

function changeProName(index){
	var projectManageId = $("#projectManageId"+index).find("option:selected").text();
	$("#proName"+index).val(projectManageId);
}

var nrsize=1;
var nrindex=0;
function addnr(){
	nrsize++;
	nrindex++;
	var html = "<tr id='nrtr"+nrindex+"'>" +
	"<td align='center' >名称:<input type='hidden' id='proName"+nrindex+"' name='projectPool.pmyfList["+nrindex+"].proName'/> "+
		"<select name='projectPool.pmyfList["+nrindex+"].projectManageId' id='projectManageId"+nrindex+"' onchange='changeProName("+nrindex+")'></select>"+
		"<span onclick='manualInput(\""+nrindex+"\")' id='font"+nrindex+"' style='font-size:12px;cursor: pointer;'>手动输入</font></td>"+
	"<td align='center'>描述：<textarea name='projectPool.pmyfList["+nrindex+"].remark' rows='3' cols='40'></textarea></td>" +
	"<td align='center'>开始时间：<input name='projectPool.pmyfList[0].startTime' class='Wdate' id='datestart0' "+
	"onClick='WdatePicker({dateFmt:\"yyyy-MM-dd\",skin:\"whyGreen\"})' id='date"+nrindex+"'></td>"+
	"<td align='center' >预完成时间：<input name='projectPool.pmyfList["+nrindex+"].reTime' class='Wdate' " +
		"onClick='WdatePicker({dateFmt:\"yyyy-MM-dd\",skin:\"whyGreen\"})' id='date"+nrindex+"'></td>"+
	"<td><input type='button' value='删除' onclick='deletenr("+nrindex+")'></td>"+
	"</tr>";
	$("#submitTr").before(html);
	setProName(nrindex);
}
function deletenr(index){
	if(nrsize==1){
		alert("最少留一行!");
		return ;
	}
	nrsize--;
	nrindex--;
	$("#nrtr"+index).remove();
}
function valedata(){
	var poolName = $("#poolName").val();
	if(null==poolName || ""==poolName){
		alert("请输入项目池名称");
		$("#poolName").focus();
		return false;
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var d1 ;
	var d2;
	if(null!=endTime && ""!=endTime){
	 d1 = new Date(startTime.replace(/\-/g, "\/"));  
	 d2 = new Date(endTime.replace(/\-/g, "\/"));  
		if(d1 >d2)  {
			alert("开始时间不能大于结束时间！");
			$("#endTime").focus();
			return false; 
		}
	}
	for(var i = 0; i<=nrindex;i++){
		var proName = $("#proName"+i).val();
		if(null== proName || ""==proName || "0"==proName){
			alert("项目名称不正确");
			$("#proName"+i).focus();
			return false;
		}
		var date = $("#date"+i).val();
		if(null==date){
			continue;
		}
		var d3 = new Date(date.replace(/\-/g, "\/"));
		if(d3<d1 || d3>d2){
			alert("项目预完成时间不正确");
			$("#date"+i).focus();
			return false;
		}
		var stDate = $("#datestart"+i).val();
		var d4 = new Date(stDate.replace(/\-/g, "\/"));
		if(d4>d3){
			alert("项目时间不正确");
			return false;
		}
	}
	$("#subtn").attr("disabled","disabled");
	return true;
}

function manualInput(num){
	var font  = $("#font"+num).text();
	var browserName=navigator.appName;    //浏览器名称
	if("手动输入"==font.trim()){
   		if (browserName=="Netscape"){  
	         document.getElementById('proName'+num).type ="text";  
        } else if(browserName=="Microsoft Internet Explorer") {  
           //$('projectManageId'+num).replace('<input name="loginPassword" type="password" class="input" id="loginPassword" autocomplete="off"/>');  
           alert("浏览器不识别，请换浏览器重试");
        }
   		$("#projectManageId"+num).next(".tinyselect").hide();
		$("#projectManageId"+num).hide();
		
		$("#font"+num).text("选择列表");
	}else if("选择列表"==font.trim()){
   		if (browserName=="Netscape"){  
	         document.getElementById('proName'+num).type ="hidden";  
        } else if(browserName=="Microsoft Internet Explorer") {  
           //$('projectManageId'+num).replace('<input name="loginPassword" type="password" class="input" id="loginPassword" autocomplete="off"/>');  
           alert("浏览器不识别，请换浏览器重试");
        }
   		$("#projectManageId"+num).next(".tinyselect").show();
   		$("#font"+num).text("手动输入");
<%--		$("#proName"+num).attr("type","hidden");--%>
<%--		$("#projectManageId"+num).show();--%>
	}
}
</SCRIPT>

	</body>
</html>
