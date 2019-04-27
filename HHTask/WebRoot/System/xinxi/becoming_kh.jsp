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
				<strong><font size="5">新员工或转岗人员试用期考核表</font></strong>
			<form action="AssScoreAction!addAssScore.action" method="post">
				<table id="mytable" class="table">
					<tr align="center">
						<th>姓名</th>
						<td>
							${user.name}
						</td>
						<th>部门</th>
						<td>
							${user.dept}
						</td>
						<th>岗位</th>
						<td colspan="2">
							${user.onWork}
						</td>
						<th>履职日期</th>
						<td>
							<fmt:formatDate value="${user.joined}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<th>试岗结束日期</th>
						<td>
							<fmt:formatDate value="${user.tryDays}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					</table>
					<br/>
					<table class="table">
					<s:iterator value="tdlist" id="list" status="pagestatus">
					<tr align="center" id="tr_${pagestatus.index}" >
						<th id="td_${pagestatus.index}" >
							${list.project}
						<input type="hidden" value="${list.weight}" id="weight_${pagestatus.index}" />
						<input type="hidden" value="${list.customScore}" id="customScore_${pagestatus.index}"/>
						<input type="hidden" value="${list.id}" id="rootid_${pagestatus.index}" />
						
						</th>
					</tr>
					</s:iterator>
					<tr>
						<th>总分</th>
						<td align="center">
							<input type="text" value="" name="assScore.accScore" id="accScore" readonly="readonly"/>
						</td>
					</tr>
				</table>
				<input type="hidden" value="${template.id}" id="id" name="id"/>
				<input type="hidden" value="${user.name}"  name="assScore.userName"/>
				<input type="hidden" value="${user.dept}" name="assScore.dept"/>
				<input type="hidden" value="${user.cardId}" name="assScore.cardId"/>
				<input type="hidden" value="${user.code}" name="assScore.code"/>
				<input type="hidden" value="${template.assObject}" name="assScore.assType" />
				<input type="hidden" name="assScore.userId" value="${user.id}" />
				<input type="hidden" name="assScore.addUserId" value="${loguser.id}" />		
				<input type="hidden" name="assScore.assPeople" value="${loguser.name}" />	
				<input type="hidden" name="assScore.remarks" value="${template.remarks}" />
				<input type="submit" value="打分" class="input"/>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var size = ${size}
var index = 0;
$(function(){
	for(var i=0;i<size; i++){
		demo(i);
	}
})
function demo(i){
		var id = $("#rootid_"+i).val();
		$.ajax( {
		type : "POST",
		url : "BecomingAction_gettdlist.action",
		data : {id:id},
		async : false,
		dataType : "json",
		success : function(data) {
			$("#tr_"+i).append('<td colspan="3" align="left"><ul id=ul_'+i+'></ul>' +
			'<input type=hidden id=hid_'+i+' name="hidvalue" value="0">		</td>')
			if(data!=null && data.length>0){
				for(var j=0;j<data.length;j++){
					var array = data[j].split('&');
					if(j==data.length-1){
						$("#ul_"+i).append('<li id=li_'+array[0]+'>'+array[1]+' <input type="hidden" name="templateId" value='+array[0]+'> </li>');
					}else{
						$("#ul_"+i).append('<li id=li_'+array[0]+'>'+array[1]+' <input type="hidden" name="templateId" value='+array[0]+'> </li><hr>');
					}
					demo1(array[0],i)
				}
			}
		}
	})
	
}

function demo1(id,i){
	$.ajax( {
		type : "POST",
		url : "BecomingAction_gettdlist.action",
		data : {id:id},
		dataType : "json",
		success : function(data) {
			$("#li_"+id).append("<select id=select_"+id+" onchange='changvalue("+i+")' name='score'>" +
			" <option value='0'></option></select>");
			if(data!=null && data.length>0){
				for(var k=0;k<data.length;k++){
					var array = data[k].split('&');
					$("#select_"+id).append('<option value='+array[2]+'>'+array[1]+'</option>');
					
				}
			}
		}
	})
}
function changvalue(i){
	var a =	$("#ul_"+i).find("select");
	var value=0;
	if(a!=null && a.length>0){
		for(var m=0; m<a.length ; m++){
			var score = parseFloat(a[m].value);
			if(score!=null ){
				value+=score;
			}
		}
	}
	var customScore = $("#customScore_"+i).val();
	var weight = $("#weight_"+i).val();
		value = ((value/customScore)*(100*weight)).toFixed(3);
	$("#hid_"+i).val(value);
	 getScore();
	
}
function getScore(){
	var hidvalues =	document.getElementsByName('hidvalue');
	var score =0;
	 if(hidvalues!=null && hidvalues.length){
		 for(var i=0;i<hidvalues.length; i++){
			 var num =  parseFloat(hidvalues[i].value);
			 score+=num;
		 }
		 $("#accScore").val(score);
	 }
}
</script>
	</body>
</html>
