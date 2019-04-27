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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行手动下单修改操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action=""
					method="POST">
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
					<input type="button" value="查询" class="input" onclick="chaxun()"/>
				</form>
				<h2><font id="years_font"></font>年部门长周例会考评表</h2>
				<table class="table" id="mytable">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th rowspan="3">
							序号
						</th>
						<th rowspan="3">
							金额从
						</th>
						<th rowspan="3">
							部门
						</th>
						<th rowspan="3">
							姓名
						</th>
						<th colspan="2">
							全年
						</th>
						<th colspan="8">
							一季度
						</th>
						<th colspan="8">
							二季度
						</th>
						<th colspan="8">
							三季度
						</th>
						<th colspan="8">
							四季度
						</th>
					</tr>
					<tr align="center" bgcolor="#c0dcf2">
						<th colspan="2">
							累计
						</th>
						<th colspan="2">
							1
						</th>
						<th colspan="2">
							2
						</th>
						<th colspan="2">
							3
						</th>
						<th colspan="2">
							小计
						</th>
						<th colspan="2">
							4
						</th>
						<th colspan="2">
							5
						</th>
						<th colspan="2">
							6
						</th>
						<th colspan="2">
							小计
						</th>
						<th colspan="2">
							7
						</th>
						<th colspan="2">
							8
						</th>
						<th colspan="2">
							9
						</th>
						<th colspan="2">
							小计
						</th>
						<th colspan="2">
							10
						</th>
						<th colspan="2">
							11
						</th>
						<th colspan="2">
							12
						</th>
						<th colspan="2">
							小计
						</th>
					</tr>
					<tr align="center" bgcolor="#c0dcf2">
						<th>
							加
							<br />
							分
						</th>
						<th>
							减
							<br />
							分
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
						<th>
							加
						</th>
						<th>
							减
						</th>
					</tr>
					<tbody id="tbody0">
					
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
	findDudList();
	if('${param.pageStatus}'=='update'){
		adInput();
	}
})
function tanchu(num){
	if(num>0){
		document.getElementById("xiugaiIframe").src = "JiaoXiaoKaoHeAction_getsacById.action?sac.id="+num;
	}else{
		document.getElementById("xiugaiIframe").src = "./System/jxkh/sac_add.jsp";
	}
	chageDiv('block');
}
function findDudList(years){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isbmzZlh':1},
		dataType : "json",
		async : false,
		success : function(data) {
			var count =1;
			$(data).each(function(){
				var newTr = '<tr id="tr_'+this.id+'"><td>'+ (count++) +'</td><td>'+this.deptName+'</td>' +
				'<td>'+this.leader+'</td><td>'+this.rank+'</td>';
				findbmzzlhList(this.leader,newTr,this.id,years);
			})
			
		}
	})
}
function findbmzzlhList(name,newTr,id,years){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findbmzzlhList.action",
		data:{
			name:name,
			years:years,
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null && data.length==3){
				var bmzzlhList =data[0];
				var monthsNo = data[1];
				var monthsNo0=data[2];
				var scoreTd = '';
				var yearScoreTd = '';
				for(var i=1;i<monthsNo;i++){
					scoreTd+='<td class="td_input" id="add_'+id+'_'+i+'"></td><td class="td_input" id="red_'+id+'_'+i+'"></td>';
					if(i%3==0){
						var jidu =	Math.ceil(i/3);
						scoreTd+='<td id="jidu0_'+id+'_'+jidu+'">0</td><td id="jidu1_'+id+'_'+jidu+'">0</td>';
					}
				}
				var jidu0 =0;
				var jidu1 =0;
				var year0 =0;
				var year1 =0;
				$(bmzzlhList).each(function(){
					var addscore ='';
					var reducescore='';
					if(this.addscore!=null
							|| this.addscore=='null'){
						addscore = this.addscore;
						jidu0+=this.addscore;
						year0+=this.addscore;
					}
					if(this.reducescore!=null
							|| this.reducescore!='null'){
						reducescore=this.reducescore;
						jidu1+=this.reducescore;
						year1+=this.reducescore;
					}
					scoreTd+='<td class="td_input" id="add_'+id+'_'+this.monthsNo+'">'+addscore+'</td><td class="td_input" id="red_'+id+'_'+this.monthsNo+'">'+reducescore+'</td>';
					if(this.monthsNo%3==0){
						var jidu =	Math.ceil(monthsNo/3);
						scoreTd+='<td id="jidu0_'+id+'_'+jidu+'">'+jidu0+'</td><td id="jidu1_'+id+'_'+jidu+'">'+jidu1+'</td>';
						jidu0=0;
						jidu1=0;
					}
				})
				for(var i=monthsNo0+1;i<13;i++){
					scoreTd+='<td class="td_input" id="add_'+id+'_'+i+'"></td><td class="td_input" id="red_'+id+'_'+i+'"></td>';
					if(i%3==0){
						var jidu =	Math.ceil(i/3);
						scoreTd+='<td id="jidu0_'+id+'_'+jidu+'">'+jidu0+'</td><td id="jidu1_'+id+'_'+jidu+'">'+jidu1+'</td>';
						jidu0=0;
						jidu1=0;
					}
				}
				yearScoreTd+='<td id="year0_'+id+'">'+year0+'</td><td id="year1_'+id+'">'+year1+'</td>';
				newTr+=yearScoreTd+scoreTd+'</tr>';
				$("#tbody0").append(newTr);
			}
		}
	})
}
function adInput(){
	var td_inputs = $(".td_input");
	if(td_inputs!=null && td_inputs.length>0){
		for(var i=0;i<td_inputs.length;i++){
			var td_input =td_inputs[i];
			var value =$(td_input).text();
			if(value == null || value=='null' || value ==undefined
					|| value =='undefined'){
				value='';
			}
			$(td_input).text('')
			$(td_input).append('<input type="text" value="'+value+'" onchange="numyanzheng(this,&apos;zhengshu&apos;);addbmzlh(this)"' +
			' ondblclick="addbmzlh(this)" style="width: 30px;">');
		}
	}
}
function addbmzlh(obj){
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	if(obj.value!=''){
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
		var add=null;
		var red=null;
		if(str=='add'){
			add=obj.value;
		}else{
			red=obj.value;
		}
		
			$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_addBmzZlh0.action",
		data:{
				'bmzzlh.months':months,
				'bmzzlh.addscore':add,
				'bmzzlh.reducescore':red,
				id:id,
			},
		dataType : "json",
		success : function(data) {
				var jidu = Math.ceil(monthsNo/3);
			if(data =="true"){
				if(str=='add'){
					var years0 =0;
					var  jidu0 =0;
					for(var i=0;i<12;i++){
						var input =	$("#add_"+id+"_"+i).find("input")[0];
						var value = $(input).val();
						if(value=='' || value==null
								||  value=='null'){
							value =0;
						}else{
							value = value*1;
						}
						years0+=value;
						if(Math.ceil(i/3)==jidu){
							jidu0+=value;
						}
					}
					$("#year0_"+id).text(years0);
					$("#jidu0_"+id+"_"+jidu).text(jidu0);
				}else{
					var years1 =0;
					var  jidu1 =0;
					for(var i=0;i<12;i++){
						var input =	$("#red_"+id+"_"+i).find("input")[0];
						var value = $(input).val();
						if(value=='' || value==null
								||  value=='null'){
							value =0;
						}else{
							value = value*1;
						}
						years1+=value;
						if(Math.ceil(i/3)==jidu){
							jidu1+=value ;
						}
					}
					$("#year1_"+id).text(years1);
					$("#jidu1_"+id+"_"+jidu).text(jidu1);
				}
				alert('修改成功!')
			}
			
		}
	})
		}
	}
}
function chaxun(){
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	$("#years_font").html(years);
	$("#tbody0").empty();
	findDudList();
	adInput();
}
</SCRIPT>
	</body>
</html>
