<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
					编辑项目池
				</h3>
				<input type="hidden" value="${fn:length(projectPool.pmyfList)}" id="listSize"/>
				<form action="projectPoolAction_editPool.action" method="post"
					onsubmit="return valedata();">
					<input type="hidden" value="${projectPool.id}" name="projectPool.id"/>
					<table class="table">
						<tr>
							<th width="50%" colspan="2" align="right">
								标题
							</th>
							<td width="50%" colspan="3">
								<input name="projectPool.poolName" value="${projectPool.poolName}" id="poolName" style="width: 150px;">
							</td>
						</tr>
						<tr>
							<th colspan="2" align="right">
								开始时间
							</th>
							<td colspan="3">
								<input name="projectPool.startTime" class="Wdate" id="startTime" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${projectPool.startTime}"/>
							</td>
						</tr>
						<tr>
							<th colspan="2" align="right">
								预结束时间
							</th>
							<td colspan="3">
								<input name="projectPool.reEndTime" class="Wdate" value="${projectPool.reEndTime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" id="endTime">
							</td>
						</tr>
						<tr>
							<th width="50%" colspan="2" align="right">
								金额
							</th>
							<td width="50%" colspan="3">
								<input name="projectPool.totalMoney" id="totalMoney" value="${projectPool.totalMoney}"
									onkeyup="mustBeNumber('totalMoney')">
								/元
							</td>
						</tr>
						<%--						<tr>--%>
						<%--							<th width="50%" align="right">是否保密</th>--%>
						<%--							<td width="50%"><input type="radio" name="projectPool.isbaomi" value="是">
						保密&nbsp;&nbsp;<input type="radio" name="projectPool.isbaomi" value="否" checked="checked">普通 </td>--%>
						<%--						</tr>--%>
						<tr>
							<th colspan="5">
								各项目
								<input type="button" value="增加" onclick="addnr()">
							</th>
						</tr>
						<s:if test="null!=projectPool.pmyfList&&projectPool.pmyfList.size()>0">
							<s:iterator id="yfproject" value="projectPool.pmyfList" status="yfStatus">
								<tr id="nrtr${yfStatus.index}">
									<td align="center">
										名称:
										<input type="hidden" name="projectPool.pmyfList[${yfStatus.index}].proName" value="${yfproject.proName}"  id="proName${yfStatus.index}"/>
										<select name="projectPool.pmyfList[${yfStatus.index}].projectManageId" id="projectManageId${yfStatus.index}" onchange="changeProName(${yfStatus.index})">
											<option value="${yfproject.id}">${yfproject.proName}</option>
										</select>
										<span onclick="manualInput('${yfStatus.index}')" id="font${yfStatus.index}" style="font-size:12px;cursor: pointer;">手动输入</font>
									</td>
									<td align="center">
										描述：
										<textarea name="projectPool.pmyfList[${yfStatus.index}].remark" rows="3"
											cols="40">${yfproject.remark}</textarea>
									</td>
									<td align="center">
										开始时间：
										<input name="projectPool.pmyfList[${yfStatus.index}].startTime" class="Wdate" id="datestart${yfStatus.index}"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${yfproject.startTime}">
									</td>
									<td align="center">
										预完成时间：
										<input name="projectPool.pmyfList[${yfStatus.index}].reTime" class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" id="date${yfStatus.index}"
											value="${yfproject.reTime}">
									</td>
									<td>
										<input type="hidden" name="projectPool.pmyfList[${yfStatus.index}].id" id="poolId${yfStatus.index}" value="${yfproject.id}"/>
										<input type="button" value="删除" onclick="deletenr(${yfStatus.index})">
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr id="nrtr0">
								<td align="center">
									名称:
									<td align="center">
										名称:
										<input type="hidden" id="proName0" name="projectPool.pmyfList[0].proName"/> 
										<select name="projectPool.pmyfList[0].projectManageId" id="projectManageId0" onchange="changeProName(0)"></select>
										<span onclick="manualInput('0')" id="font0" style="font-size:12px;cursor: pointer;">手动输入</font>
									</td>
								</td>
								<td align="center">
									描述：
									<textarea name="projectPool.pmyfList[0].remark" rows="3"
										cols="40"></textarea>
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
						</s:else>
						<tr id="submitTr">
							<td align="center" colspan="5">
								<input id="subtn" type="submit" value="提交" style="width: 100px;height:40px">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var nrsize=1;
var nrindex=0;
var projects;
$(function(){
	var listSize = $("#listSize").val();
	if(listSize==null){
		listSIze=0;
	}
	nrsize = listSize;
	nrindex = listSize-1;
	
	 $.ajax({
    	type:"post",
    	url:"${pageContext.request.contextPath}/QuotedPrice_getQuotedPriceByCon.action",
    	data:{},
    	dataType:"json",
    	success:function(data){
    		projects=data;
    		for(var i = 0; i<=nrindex;i++){
				//var proName = $("#proName"+i).val();
			    setProName(i);
				
				
			} 
    	}
    });
	
	
	
});
function changeProName(index){
	var projectManageId = $("#projectManageId"+index).find("option:selected").text();
	$("#proName"+index).val(projectManageId);
}

function setProName(index){
	$("<option value='0'>请选择项目名称</option>").appendTo("#projectManageId"+index);
	$(projects).each(function(){
		var proName = $("#proName"+index).val();
		if(proName == this.proName){
			$( "<option value='" + this.id + "' selected='selected' >" + this.proName + "</option>") .appendTo("#projectManageId"+index);
		}else{
			$( "<option value='" + this.id + "'>" + this.proName + "</option>") .appendTo("#projectManageId"+index);
		}
	});
	var tinyselect = $(".tinyselect");
<%--	if (tinyselect[1] != null) {--%>
<%--		document.getElementById("depttd"+index).removeChild(--%>
<%--				tinyselect[1]);--%>
<%--	}--%>
	$("#projectManageId"+index).tinyselect();
}

function addnr(){
	nrsize++;
	nrindex++;
	var html = "<tr id='nrtr"+nrindex+"'>" +
	"<td align='center' >名称:<input type='hidden' id='proName"+nrindex+"' name='projectPool.pmyfList["+nrindex+"].proName'/> "+
		"<select name='projectPool.pmyfList["+nrindex+"].projectManageId' id='projectManageId"+nrindex+"' onchange='changeProName("+nrindex+")'></select>"+
		"<span onclick='manualInput(\""+nrindex+"\")' id='font"+nrindex+"' style='font-size:12px;cursor: pointer;'>手动输入</font></td>"+
	"<td align='center'>描述：<textarea name='projectPool.pmyfList["+nrindex+"].remark' rows='3' cols='40'></textarea></td>"+
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
	if(!confirm("确定要删除吗？")){
		return;
	}
	var poolId = $("#poolId"+index).val();
	if(null==poolId){
		$("#nrtr"+index).remove();
		return;
	}
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/projectPoolAction_delProject.action",
		data:{
			id2:poolId,
			pageStatus:"manage"
		},
		dataType:"json",
		success:function(data){
			if("删除成功"==data){
				nrsize--;
				nrindex--;
				$("#nrtr"+index).remove();
			}else{
				alert(data);
			}
		},
		error:function(){
			alert("删除错误！请联系管理员。");
			
		}
	});
	
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
		if(null== proName || ""==proName){
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
			alert("项目时间预完成时间不正确");
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
	}
}
</SCRIPT>

	</body>
</html>
