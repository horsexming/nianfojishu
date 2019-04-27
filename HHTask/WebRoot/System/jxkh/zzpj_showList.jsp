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
				<h2>制造评价<font id="years_font"></font>年度</h2>
				<table class="table" id="wsxlzz_table">
					<tr id="thead_tr" align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							姓名
						</th>
						<th>
							1月
						</th>
						<th>
							2月
						</th>
						<th>
							3月
						</th>
						<th>
							4月
						</th>
						<th>
							5月
						</th>
						<th>
							6月
						</th>
						<th>
							7月
						</th>
						<th>
							8月
						</th>
						<th>
							9月
						</th>
						<th>
							10月
						</th>
						<th>
							11月
						</th>
						<th >
							12月
						</th>
					</tr>
					<tbody id="tbody_dept">
						
					</tbody>
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
		data:{'dud.isZzPj':1},
		dataType : "json",
		async : false,
		success : function(data) {
			var count =1;
			var tbody_dept ='';
			var count = 0;
			$(data).each(function(){
				count++;
				var newtr = '<tr id="tr_'+this.id+'" align="center"><td>'+count+'</td>' +
				'<td class="noinput">'+this.deptName+'</td><td class="noinput">'+this.leader+'</td>'
				 newtr =	findZzpjBy(years,this.deptName,this.leader, this.id,newtr);
				$("#tbody_dept").append(newtr);
			})
		}
	})
}
function findZzpjBy(years,deptName,usersName, id,newtr){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findZzpjBy.action",
		data:{
			'zzpj.years':years,
			'zzpj.dept':deptName,
			'zzpj.name':usersName
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null && data.length==3){
				var zzpjList =data[0];
				var monthsNo = data[1];
				var monthsNo0=data[2];
				for(var i=1;i<monthsNo;i++){
					newtr+='<td id="td_'+id+'_'+i+'"  class="td_append"></td>';
				}
				$(zzpjList).each(function(){
					var xiShu =  this.xiShu*1;
					newtr+='<td id="td_'+id+'_'+this.month0+'" class="td_append" align="right">'+xiShu+'</td>';
				})
				for(var i=monthsNo0+1;i<13;i++){
					newtr+='<td id="td_'+id+'_'+i+'"  class="td_append"></td>';
				}
			}
		}
	})
	return newtr;
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
			$(td_input).append('<input type="text"  style="width: 75px;" value="'+value+'" onchange="numyanzheng(this);addZzpj(this)"' +
			' ondblclick="addZzpj(this)" >');
		}
	}
}
function addZzpj(obj){
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
			var id =strs[1];
			var monthsNo=strs[2];
			var months = "";
		if(monthsNo<10){
			months=years+"-"+"0"+monthsNo;
		}else{
			months=years+"-"+monthsNo;
		}
	}
		$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_addZzpj.action",
		data:{
				'zzpj.years':years,
				'zzpj.month0':monthsNo,
				'zzpj.months':months,
				'zzpj.xiShu':obj.value,
				'zzpj.dudId':id,
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
	$("#tbody_dept").remove();
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
