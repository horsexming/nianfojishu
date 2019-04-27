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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								年份:
							</th>
							<th align="left">
								<input type="text" value="" class="Wdate" id="years"
									onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})" />
							</th>
						</tr>
					</table>
					<input type="button" value="查询" class="input" onclick="chaxun()" />
				</form>
				<h2><font id="years_font"></font>年各车间年度效率累计增长情况表</h2>
				<table class="table" id="wsxlzz_table">
					<tr id="thead_tr" align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							月份
						</th>
					</tr>
					<tr id="tr_1">
						<th>
							1
						</th>
					</tr>
					<tr id="tr_2">
						<th>
							2
						</th>
					</tr>
					<tr id="tr_3">
						<th>
							3
						</th>
					</tr>
					<tr id="tr_4">
						<th>
							4
						</th>
					</tr>
					<tr id="tr_5">
						<th>
							5
						</th>
					</tr>
					<tr id="tr_6">
						<th>
							6
						</th>
					</tr>
					<tr id="tr_7">
						<th>
							7
						</th>
					</tr>
					<tr id="tr_8">
						<th>
							8
						</th>
					</tr>
					<tr id="tr_9" >
						<th>
							9
						</th>
					</tr>
					<tr id="tr_10">
						<th>
							10
						</th>
					</tr>
					<tr id="tr_11">
						<th>
							11
						</th>
					</tr>
					<tr id="tr_12">
						<th>
							12
						</th>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	$("#years_font").html(years);
	findDudList(years);
	if('${param.pageStatus}'=='update'){
		adInput();
	}
})
function findDudList(years){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isZzXl':1},
		dataType : "json",
		async : false,
		success : function(data) {
			var count =1;
			$(data).each(function(){
				$("#thead_tr").append('<th id="th_'+this.id+'" class="th_append">'+this.deptName+'</th>');
				findWsxlzzBy(years,this.deptName,this.id);
			})
		}
	})
}
function findWsxlzzBy(years,deptName,id){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findWsxlzBy.action",
		data:{
			'wsxlzz.years':years,
			'wsxlzz.dept':deptName,
		},
		                dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null && data.length==3){
				var wsxlzzList =data[0];
				var monthsNo = data[1];
				var monthsNo0=data[2];
				for(var i=1;i<monthsNo;i++){
					$("#tr_"+i).append('<td id="td_'+i+'_'+id+'" class="td_append"></td>');
				}
				$(wsxlzzList).each(function(){
					var xiaolv =  (this.xiaolv*100)+'%';
					$("#tr_"+this.month0).append('<td id="td_'+this.month0+'_'+id+'" class="td_append" align="right">'+xiaolv+'</td>');	
				})
				for(var i=monthsNo0+1;i<13;i++){
					$("#tr_"+i).append('<td id="td_'+i+'_'+id+'" class="td_append"></td>');
				}
			}
		}
	})
}
function adInput(){
	var td_inputs = $(".td_append");
	if(td_inputs!=null && td_inputs.length>0){
		for(var i=0;i<td_inputs.length;i++){
			var td_input =td_inputs[i];
			var value =$(td_input).text();
			if(value == null || value=='null' || value ==undefined
					|| value =='undefined'){
				value='';
			}
			$(td_input).text('')
			$(td_input).append('<input type="text" value="'+value+'" onchange="numyanzheng(this);addWsxlz(this)"' +
			' ondblclick="addWsxlz(this)" >');
		}
	}
}
function addWsxlz(obj){
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	if(obj.value!=''){
		var num = obj.value*1;
		if(num>1){
			alert('请填写0~1之间的数字。')
		}
		var td =	$(obj).parents("td");
		var td_id =	$(td).attr("id");
		var strs =	td_id.split("_");
		if(strs.length==3){
			var str = strs[0];
			var monthsNo =strs[1];
			var id=strs[2];
			var months = "";
		if(monthsNo<10){
			months=years+"-"+"0"+monthsNo;
		}else{
			months=years+"-"+monthsNo;
		}
	}
		$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_addWsxlz.action",
		data:{
				'wsxlzz.years':years,
				'wsxlzz.month0':monthsNo,
				'wsxlzz.months':months,
				'wsxlzz.xiaolv':obj.value,
				'wsxlzz.deptId':id,
			},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data=='true'){
				alert('修改成功!~')
			}
		}
	})
	}
}
function chaxun(){
	$(".th_append").remove();
	$(".td_append").remove();
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	$("#years_font").html(years);
	findDudList(years);
	if('${param.pageStatus}'=='update'){
		adInput();
	}
}
</SCRIPT>
	</body>
</html>
