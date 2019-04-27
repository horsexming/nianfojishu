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
				<h2>
					<font id="ww_yeras_font"></font>年委外加工统计表(万元)-入库（委外比目标：
					<font id="wwmb_font"></font>）
					<input type="hidden" value="" id="wwmb_hid" />
				</h2>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							委外类型
						</th>
						<th>
							合计
						</th>
						<th>
							月均
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
						<th>
							12月
						</th>
					</tr>
					<tbody id="ww_tbody">

					</tbody>
				</table>
				<h2>
					<font id="jg_yeras_font"></font> 年结构比（目标：
					<font id="jgmb_font"></font>）
					<input type="hidden" value="" id="jgmb_hid" />
				</h2>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>

						</th>
						<th>
							合计
						</th>
						<th>
							月均
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
						<th>
							12月
						</th>
					</tr>
					<tbody id="jg_tbody">

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
	$("#ww_yeras_font").text(years);
	$("#jg_yeras_font").text(years);
	findWwJgMbBy(years);
	addTr(years);
	if('${param.status}'=='update'){
		adInput();
	}
	
})
function findWwJgMbBy(years){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findWwJgMbBy0.action",
		data:{'wwjgmb.years':years},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null){
				$("#wwmb_font").text(data.waiweiMuBiao*100+'%');
				$("#jgmb_font").text(data.jieGouMuBiao*100+'%');
				$("#wwmb_hid").val(data.waiweiMuBiao);
				$("#jgmb_hid").val(data.jieGouMuBiao);
			}
		}
	})
}
function addTr(years){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findWwjgListBy.action",
		data:{'wwjg.years':years},
		dataType : "json",
		async : false,
		success : function(data) {
			if(data!=null && data.length ==3){
				var wwjgList = data[0];
				var monthsNo0 = data[1];
				var monthsNo1 = data[2];
				var cz_tr ='<tr><th style="width: 100px;">产值</th>';
				var bgbl_tr = '<tr><th style="width: 100px;">包工包料</th>';
				var gxww_tr = '<tr><th style="width: 100px;">工序外委</th>';
				var gj_tr = '<tr><th style="width: 100px;">共计</th>';
				var wwb_tr = '<tr><th style="width: 100px;">委外比</th>';
				var wwxs_tr ='<tr><th style="width: 100px;">委外系数</th>';
				var rkcz_tr = '<tr><th style="width: 100px;">入库产值</th>';
				var scBOM_tr ='<tr><th style="width: 100px;">生产BOM</th>';
				var jgb_tr = '<tr><th style="width: 100px;">结构比</th>';
				var jgxs_tr = '<tr><th style="width: 100px;">结构系数</th>';
				
				var cz_td ='';
				var bgbl_td = '';
				var gxww_td = '';
				var gj_td = '';
				var wwb_td = '';
				var wwxs_td ='';
				var rkcz_td = '';
				var scBOM_td ='';
				var jgb_td = '';
				var jgxs_td = '';
				var count = 0;
				var cz_sum='',cz_avg='',bgbl_sum='',bgbl_avg='',gxww_sum='',gxww_avg='',
					gj_sum='',gj_avg='',wwb_sum='',wwb_avg='',rucz_sum='',rucz_avg='',
					scBOM_sum='',scBOM_avg='',jgb_sum='',jgb_avg='';
				for(var i=1;i<monthsNo0;i++){
					cz_td+='<td class="td_input" id="changzhi_'+i+'" style="width: 55px;"></td>'
					bgbl_td+='<td class="td_input" id="bgbl_'+i+'" style="width: 55px;"></td>'
					gxww_td+='<td class="td_input" id="gxww_'+i+'" style="width: 55px;"></td>'
					gj_td+='<td id="wwgj_'+i+'" style="width: 55px;"></td>'
					wwb_td+='<td id="wwb_'+i+'" style="width: 55px;"></td>'
					wwxs_td+='<td class="td_input" id="wwxs_'+i+'" style="width: 55px;"></td>'
					rkcz_td+='<td class="td_input" id="rkcz_'+i+'" style="width: 55px;"></td>'
					scBOM_td+='<td class="td_input" id="scBOM_'+i+'" style="width: 55px;"></td>'
					jgb_td+='<td id="jgb_'+i+'" style="width: 55px;"></td>'
					jgxs_td+='<td class="td_input" id="jgxs_'+i+'" style="width: 55px;"></td>'
				}
				$(wwjgList).each(function(){
					count++;
					var changzhi ='';
					if(this.changzhi!=null
							|| this.changzhi!='null'){
						changzhi=this.changzhi*1;
						cz_sum+=changzhi;
						cz_sum = cz_sum*1;
					}
					cz_td+='<td class="td_input" id="changzhi_'+this.month0+'" style="width: 55px;">'+changzhi+'</td>'
					var bgbl ='';
					if(this.bgbl!=null
							|| this.bgbl!='null'){
						bgbl=this.bgbl*1;
						bgbl_sum+=bgbl;
						bgbl_sum = bgbl_sum*1;
					}
					bgbl_td+='<td class="td_input" id="bgbl_'+this.month0+'" style="width: 55px;">'+bgbl+'</td>'
					var gxww ='';
					if(this.gxww!=null
							|| this.gxww!='null'){
						gxww=this.gxww*1;
						gxww_sum+=gxww;
						gxww_sum =gxww_sum*1;
					}
					gxww_td+='<td class="td_input" id="gxww_'+this.month0+'" style="width: 55px;">'+gxww+'</td>'
					var wwgj ='';
					if(this.wwgj!=null
							|| this.wwgj!='null'){
						wwgj=this.wwgj*1;
						gj_sum+=wwgj;
						gj_sum =gj_sum*1;
					}
					gj_td+='<td id="wwgj_'+this.month0+'" style="width: 55px;">'+wwgj+'</td>'
					var wwb ='';
					if(this.wwb!=null
							|| this.wwb!='null'){
						wwb=this.wwb*1;
						wwb = (wwb*100).toFixed(2)+'%';
					}
					wwb_td+='<td id="wwb_'+this.month0+'" style="width: 55px;">'+wwb+'</td>'
					var wwxs ='';
					if(this.wwxs!=null
							|| this.wwxs!='null'){
						wwxs=this.wwxs*1;
					}
					wwxs_td+='<td class="td_input" id="wwxs_'+this.month0+'" style="width:55px;">'+wwxs+'</td>'
					var rkcz ='';
					if(this.rkcz!=null
							|| this.rkcz!='null'){
						rkcz=this.rkcz*1;
						rucz_sum+=rkcz;
						rucz_sum  =rucz_sum*1;
					}
					rkcz_td+='<td class="td_input" id="rkcz_'+this.month0+'" style="width: 75px;">'+rkcz+'</td>'
					var scBOM ='';
					if(this.scBOM!=null
							|| this.scBOM!='null'){
						scBOM=this.scBOM*1;
						scBOM_sum+=scBOM;
						scBOM_sum = scBOM_sum*1;
					}
					scBOM_td+='<td class="td_input" id="scBOM_'+this.month0+'" style="width: 55px;">'+scBOM+'</td>'
					var jgb ='';
					if(this.jgb!=null
							|| this.jgb!='null'){
						jgb=this.jgb*1;
						jgb =(jgb*100).toFixed(2)+'%';
					}
					jgb_td+='<td id="jgb_'+this.month0+'" style="width: 55px;">'+jgb+'</td>'
					var jgxs ='';
					if(this.jgxs!=null
							|| this.jgxs!='null'){
						jgxs=this.jgxs*1;
					}
					jgxs_td+='<td class="td_input" id="jgxs_'+this.month0+'" style="width: 55px;">'+jgxs+'</td>'
				})
				for(var i=monthsNo1+1;i<13;i++){
					cz_td+='<td class="td_input" id="changzhi_'+i+'" style="width: 55px;"></td>'
					bgbl_td+='<td class="td_input" id="bgbl_'+i+'" style="width: 55px;"></td>'
					gxww_td+='<td class="td_input" id="gxww_'+i+'" style="width: 55px;"></td>'
					gj_td+='<td id="wwgj_'+i+'" style="width: 55px;"></td>'
					wwb_td+='<td id="wwb_'+i+'" style="width: 55px;"></td>'
					wwxs_td+='<td class="td_input" id="wwxs_'+i+'" style="width: 55px;"></td>'
					rkcz_td+='<td class="td_input" id="rkcz_'+i+'" style="width:55px;"></td>'
					scBOM_td+='<td class="td_input" id="scBOM_'+i+'" style="width: 55px;"></td>'
					jgb_td+='<td id="jgb_'+i+'" style="width: 55px;"></td>'
					jgxs_td+='<td class="td_input" id="jgxs_'+i+'" style="width: 55px;"></td>'
				}
				cz_avg = cz_sum==''?'':(cz_sum/count).toFixed(2);
				bgbl_avg = bgbl_sum==''?'':(bgbl_sum/count).toFixed(2);
				gxww_avg = gxww_sum==''?'':(gxww_sum/count).toFixed(2);
				gj_avg = gj_sum==''?'':(gj_sum/count).toFixed(2);
				if(gj_sum!='' && cz_sum!='' 
						&& gj_sum!=0 && cz_sum!=0 ){
					wwb_sum = ((gj_sum/cz_sum)*100).toFixed(2)+'%';
				}
				if(gj_avg!='' && cz_avg!=''
						&& gj_avg!=0 && cz_avg!=0 ){
					wwb_avg =((gj_avg/cz_avg)*100).toFixed(2)+'%';
				}
				 
				rucz_avg = rucz_sum==''?'':(rucz_sum/count).toFixed(2);
				scBOM_avg = scBOM_sum==''?'':(scBOM_sum/count).toFixed(2);
				if(scBOM_sum!='' && rucz_sum!=''
						&& scBOM_sum!=0 && rucz_sum!=0){
					jgb_sum =((scBOM_sum/rucz_sum)*100).toFixed(2)+'%';
				}
				if(scBOM_avg!='' && rucz_avg!=''
						&&  scBOM_sum!=0 && rucz_sum!=0){
					jgb_avg=((scBOM_avg/rucz_avg)*100).toFixed(2)+'%';
				}
				cz_tr+='<td id="changzhi_sum" style="width: 55px;">'+cz_sum+'</td><td id="changzhi_avg" style="width: 55px;">'+cz_avg+'</td>'+cz_td+'</th>';
				bgbl_tr+='<td id="bgbl_sum" style="width: 55px;">'+bgbl_sum+'</td><td id="bgbl_avg" style="width: 55px;">'+bgbl_avg+'</td>'+bgbl_td+'</th>';
				gxww_tr+='<td id="gxww_sum" style="width: 55px;">'+gxww_sum+'</td><td id="gxww_avg" style="width: 55px;">'+gxww_avg+'</td>'+gxww_td+'</th>';
				gj_tr+='<td id="wwgj_sum" style="width: 55px;">'+gj_sum+'</td><td id="wwgj_avg" style="width: 55px;">'+gj_avg+'</td>'+gj_td+'</th>';
				wwb_tr+='<td id="wwb_sum" style="width: 55px;">'+wwb_sum+'</td><td id="wwb_avg" style="width: 55px;">'+wwb_avg+'</td>'+wwb_td+'</th>';
				wwxs_tr+='<td style="width: 55px;"></td><td style="width: 55px;"></td>'+wwxs_td+'</th>';
				rkcz_tr+='<td id="rkcz_sum" style="width: 55px;">'+rucz_sum+'</td><td id="rkcz_avg" style="width: 55px;">'+rucz_avg+'</td>'+rkcz_td+'</th>';
				scBOM_tr+='<td id="scBOM_sum" style="width: 55px;">'+scBOM_sum+'</td><td id="scBOM_avg" style="width: 55px;">'+scBOM_avg+'</td>'+scBOM_td+'</th>';
				jgb_tr+='<td id="jgb_sum" style="width: 55px;">'+jgb_sum+'</td><td id="jgb_avg" style="width: 55px;">'+jgb_avg+'</td>'+jgb_td+'</th>';
				jgxs_tr+='<td style="width: 55px;"></td><td style="width: 55px;"></td>'+jgxs_td+'</th>';
				$("#ww_tbody").append(cz_tr+bgbl_tr+gxww_tr+gj_tr+wwb_tr+wwxs_tr);
				$("#jg_tbody").append(rkcz_tr+scBOM_tr+jgb_tr+jgxs_tr);
				
				
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
			$(td_input).append('<input type="text" value="'+value+'" onchange="numyanzheng(this);addbmzlh(this)"' +
			' ondblclick="addbmzlh(this)" style="width: 70px;">');
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
		if(strs.length==2){
			var str = strs[0];
			var monthsNo =strs[1];
			var months = "";
		if(monthsNo<10){
			months=years+"-"+"0"+monthsNo;
		}else{
			months=years+"-"+monthsNo;
		}
		var add=null;
		var red=null;
			$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_addwwjg.action",
		data:{
				'wwjg.months':months,
				'wwjg.str':obj.value,
				'wwjg.years':years,
				'wwjg.month0':monthsNo,
				'wwjg.jgbmb':$("#jgmb_hid").val(),
				'wwjg.wwbmb':$("#wwmb_hid").val(),
				pageStatus:str
			},
		dataType : "json",
		success : function(data) {
				var jidu = Math.ceil(monthsNo/3);
			if(data =="true"){
				var sum =0;
				var count =0;
				for(var i=1;i<13;i++){
					var input =	$("#"+str+"_"+i).find("input")[0];
					var value = $(input).val();
					if(value!='' && value!=null
							&& value!='null'){
						count++;
						value =value*1;	
						sum+=value;
					}
					}
					$("#"+str+"_sum").text(sum);
					$("#"+str+"_avg").text(sum/count);
					if("changzhi"==str){
						var wwb = 0;
						var input = $("#wwgj_"+monthsNo).find("input")[0];
						var wwgj =input.value;
						if(wwgj!=null && wwgj!='null'
								&& wwgj!=''){
							wwgj =wwgj*1;
							wwb = (((wwgj/(obj.value*1)).toFixed(4))*100)+"%";
							$("#wwb_"+monthsNo).text(wwb);
						}
						var wwb_sum = 0;
						var wwgj_sum = $("#wwgj_sum").text();
						if(wwgj_sum!=null && wwgj_sum!='null'
								&& wwgj_sum!=''){
							wwgj_sum =wwgj_sum*1;
							wwb_sum = (((wwgj_sum/sum).toFixed(4))*100)+"%";
							$("#wwb_sum").text(wwb_sum);
						}
							var wwb_avg =0;
							var wwgj_avg = $("#wwgj_avg").text();
							if(wwgj_avg!=null && wwgj_avg!='null'
								&& wwgj_avg!=''){
								wwb_avg =wwgj_avg*1;
								wwb_avg = (((wwb_avg/(sum/count)).toFixed(4))*100)+"%";
								$("#wwb_avg").text(wwb_avg);
							}
					}else if("bgbl"==str){
						var wwgj =0;
						var input = $("#gxww_"+monthsNo).find("input")[0];
						var gxww =input.value;
						if(gxww!=null && gxww!='null'
								&& gxww!=''){
							gxww = gxww*1;
							wwgj =gxww+(obj.value*1);
							$("#wwgj_"+monthsNo).text(wwgj);
							
							var wwb =0;
							var changzhi = $("#changzhi_"+monthsNo).val();
							if(changzhi!=null && changzhi!='null'
								&& changzhi!=''){
								changzhi =changzhi*1;
								wwb = (((wwgj/changzhi).toFixed(2))*100)+"%";
								$("#wwb_"+monthsNo).text(wwb);
							}
						}
						
						var wwgj_sum =0;
						var gxww_sum = $("#gxww_sum").text();
						if(gxww_sum!=null && gxww!='null'
								&& gxww_sum!=''){
							gxww_sum =gxww_sum*1;
							wwgj_sum = sum+gxww_sum;
							$("#wwgj_sum").text(wwgj_sum);
							var wwgj_avg =(wwgj_sum/count).toFixed(2); 
							$("#wwgj_avg").text(wwgj_avg);
							var wwb_sum =0;
							var changzhi_sum = $("#changzhi_sum").text();
							if(changzhi_sum!=null && changzhi_sum!='null'
								&& changzhi_sum!=''){
								changzhi_sum =changzhi_sum*1;
								wwb_sum = (((wwgj_sum/wwgj_sum).toFixed(2))*100)+"%";
								$("#wwb_sum").text(wwb_sum);
							}
							var wwb_avg =0;
							var changzhi_avg = $("#changzhi_avg").text();
							if(changzhi_avg!=null && changzhi_avg!='null'
								&& changzhi_avg!=''){
								changzhi_avg =changzhi_avg*1;
								wwb_sum = (((wwgj_avg/changzhi_avg).toFixed(2))*100)+"%";
								$("#wwb_avg").text(wwb_avg);
							}
						}
					}else if('gxww' ==str){
						var wwgj =0;
						var input = $("#bgbl_"+monthsNo).find("input")[0];
						var bgbl =input.value;
						if(bgbl!=null && bgbl!='null'
								&& bgbl!=''){
							bgbl = bgbl*1;
							wwgj =bgbl+(obj.value*1);
							$("#wwgj_"+monthsNo).text(wwgj);
							
							var wwb =0;
							var changzhi = $("#changzhi_"+monthsNo).val();
							if(changzhi!=null && changzhi!='null'
								&& changzhi!=''){
								changzhi =changzhi*1;
								wwb = (((wwgj/changzhi).toFixed(2))*100)+"%";
								$("#wwb_"+monthsNo).text(wwb);
							}
						}
						
						var wwgj_sum =0;
						var bgbl_sum = $("#bgbl_sum").text();
						if(bgbl_sum!=null && bgbl_sum!='null'
								&& bgbl_sum!=''){
							bgbl_sum =bgbl_sum*1;
							wwgj_sum = sum+bgbl_sum;
							$("#wwgj_sum").text(wwgj_sum);
							var wwgj_avg =(wwgj_sum/count).toFixed(2); 
							$("#wwgj_avg").text(wwgj_avg);
							var wwb_sum =0;
							var changzhi_sum = $("#changzhi_sum").text();
							if(changzhi_sum!=null && changzhi_sum!='null'
								&& changzhi_sum!=''){
								changzhi_sum =changzhi_sum*1;
								wwb_sum = (((wwgj_sum/wwgj_sum).toFixed(2))*100)+"%";
								$("#wwb_sum").text(wwb_sum);
							}
							var wwb_avg =0;
							var changzhi_avg = $("#changzhi_avg").text();
							if(changzhi_avg!=null && changzhi_avg!='null'
								&& changzhi_avg!=''){
								changzhi_avg =changzhi_avg*1;
								wwb_sum = (((wwgj_avg/changzhi_avg).toFixed(2))*100)+"%";
								$("#wwb_avg").text(wwb_avg);
							}
						}
					}else if('rkcz'==str){
						var jgb =0;
						var input = $("#scBOM_"+monthsNo).find("input")[0];
						var scBOM =input.value;
						if(scBOM!=null && scBOM!='null'
								&& scBOM!=''){
							scBOM =scBOM*1;
							jgb = (((scBOM/(obj.value*1)).toFixed(2))*100)+"%";
							$("#jgb_"+monthsNo).text(jgb);
						}
						var jgb_sum =0;
						var scBOM_sum = $("#scBOM_sum").text();
						if(scBOM_sum!=null && scBOM_sum!='null'
								&& scBOM_sum!=''){
							scBOM_sum =scBOM_sum*1;
							jgb_sum = (((scBOM_sum/sum).toFixed(2))*100)+"%";
							$("#jgb_sum").text(jgb_sum);
						}
						var jgb_avg =0;
						var scBOM_avg = $("#scBOM_avg").text();
						if(scBOM_avg!=null && scBOM_avg!='null'
								&& scBOM_avg!=''){
							scBOM_avg =scBOM_avg*1;
							jgb_avg = (((scBOM_avg/(sum/count)).toFixed(2))*100)+"%";
							$("#jgb_avg").text(jgb_avg);
						}
							
					}else if('scBOM'==str){
						var jgb =0;
						var input = $("#rkcz_"+monthsNo).find("input")[0];
						var rkcz =input.value;
						if(rkcz!=null && rkcz!='null'
								&& rkcz!=''){
							rkcz =rkcz*1;
							jgb = ((((obj.value*1)/rkcz).toFixed(4))*100)+"%";
							alert(jgb);
						}
						var jgb_sum =0;
						var rkcz_sum = $("#rkcz_sum").text();
						if(rkcz_sum!=null && rkcz_sum!='null'
								&& rkcz_sum!=''){
							rkcz_sum =rkcz_sum*1;
							jgb_sum = (((sum/rkcz_sum).toFixed(2))*100)+"%";
							$("#jgb_sum").text(jgb_sum);
						}
					}
			}
			
		}
	})
		}
	}
}
function chaxun(){
	$("#ww_tbody").empty();
	$("#jg_tbody").empty();
	var years =	$("#years").val();
	if(years == ''){
		var date = new Date();
		var years = date.getFullYear();
	}
	$("#ww_yeras_font").text(years);
	$("#jg_yeras_font").text(years);
	findWwJgMbBy(years);
	addTr(years);
	if('${param.status}'=='update'){
		adInput();
	}
}
</SCRIPT>
	</body>
</html>
