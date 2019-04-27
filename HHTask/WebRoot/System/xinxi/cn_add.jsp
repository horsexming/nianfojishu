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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<STYLE type="text/css">
.input_a {
	width: 80px;
}
.button {
	display: inline-block;
	zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ *
	display: inline;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/ 100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="zi_font" color="red" size="5">${errorMessage}</font>
				<form action="IntelligentDiagnosisAction_addMore.action"
					method="post" onsubmit="return check()">
					<table class="table" id="mytable">
						<tr>
							<th colspan="5">
								&nbsp;&nbsp;企业名称:
								<input type="text" name="cn.campanyname" id="campanyname"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" />
								<font color="red">*</font>
							</th>
						</tr>
						<tr>
							<th colspan="5">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业规模:
								<input type="text" name="cn.guimo" id="guimo"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')"
									style="width: 160px;" />
								(人数)
								<font color="red">*</font>
							</th>
						</tr>
						<tr>
							<th colspan="5">
								企业性质:
								<input type="text" name="cn.type" id="type"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" />
							</th>
						</tr>
						<tr>
							<th colspan="5">

								联   系   人 :
								<input type="text" name="cn.lianxiren" id="lianxiren"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" /><font color="red">*</font>
							</th>
						</tr>
						<tr>
							<th colspan="5">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系人手机号:
								<input type="text" name="cn.phone" id="phone" maxlength="11"
									onchange="mobilephoneyanzhen(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')"
									onkeyup="mobilephoneyanzhen(this)" /><font color="red">*</font>
							</th>
						</tr>
						<tr align="center">
							<th>
								选择
							</th>
							<th>
								功能
							</th>
							<th>
								人工完成
							</th>
							<!--<th >部门</th>
							<th >岗位</th>
							-->
							<th>
								人均年成本(万元)
							</th>
							<th>
								岗位人数
							</th>
							<!--<th>年龄</th>
							<th>专职/兼职</th>
							<th>任职时期</th>
							-->
							
						</tr><!--  -->
						<s:iterator value="pnList" id="pageList" status="pageStatus">
							<tr id="tr_${pageStatus.index}" align="center" style="display: none;">
								<td>
									<input type="checkbox"
										name="cn.islist[${pageStatus.index}].isxuyao"
										checked="checked" value="yes"
										onclick="xccheckbox(this,'${pageStatus.index}')">
								</td>
								<td>
									${pageList.name}
									<input type="hidden"
										name="cn.islist[${pageStatus.index}].processName"
										id="processName_${pageStatus.index}" value="${pageList.name}"
										readonly="readonly">
								</td>
								<td>
									<input type="radio" name="cn.islist[${pageStatus.index}].isman"
										checked="checked" id="isman_${pageStatus.index}" value="yes">
									是
									<input type="radio" name="cn.islist[${pageStatus.index}].isman"
										id="noman_${pageStatus.index}" value="no">
									否
								</td>
								<!--<td>
									<input type="text" name="cn.islist[${pageStatus.index}].dept"
										id="dept_${pageStatus.index}" class="input_a" />
								</td>
								<td>
									<input type="text" name="cn.islist[${pageStatus.index}].post"
										id="post_${pageStatus.index}" class="input_a" />
								</td>
								-->
								<td>
									<input type="hidden"
										name="cn.islist[${pageStatus.index}].iseconomize"
										value="${pageList.iseconomize}" />
									<input type="text" name="cn.islist[${pageStatus.index}].pcai"
										id="pcai_${pageStatus.index}" class="input_a"
										onchange="checkpcai('${pageStatus.index}')" value="0" />
									<%--<input type="button"
										onclick="delLine('tr_${pageStatus.index}')" value="删除">
								--%>
								</td>
								<td>
									<input type="text"
										name="cn.islist[${pageStatus.index}].postnum"
										id="postnum_${pageStatus.index}" class="input_a"
										onblur="numyanzhen(this)" onkeyup="numyanzhen(this)" value="1" />
								</td>
								<!--<td>
									<input type="text" name="cn.islist[${pageStatus.index}].age"
										id="age_${pageStatus.index}"
										onchange="formatage('${pageStatus.index}')" class="input_a" />
								</td>
								<td>
									<input type="radio" name="cn.islist[${pageStatus.index}].zorj"
										id="z_${pageStatus.index}" checked="checked" value="专职" />
									专职
									<input type="radio" name="cn.islist[${pageStatus.index}].zorj"
										id="j_${pageStatus.index}" value="兼职" />
									兼职
								</td>
								<td>
									<input type="text"
										name="cn.islist[${pageStatus.index}].servingtime"
										id="servingtime_${pageStatus.index}"
										onchange="formatervingtime('${pageStatus.index}')"
										class="input_a">
									(年)
								</td>
								-->
							</tr>
						</s:iterator>
						<tr id="tr_fenye">
							<td id="td_fenye" colspan="6" align="right">
							
							</td>
						</tr>
<%--						 <tr>--%>
<%--				<td colspan="6" align="right">--%>
<%--								第--%>
<%--					<font color="red"><s:property value="cpage" /> </font> /--%>
<%--						<s:property value="total" />--%>
<%--							页--%>
<%--						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"--%>
<%--									styleClass="page" theme="number" />--%>
<%--									--%>
<%--				</td>--%>
<%--			</tr>--%>
					</table>
					<input type="hidden" value="total" name="status" />
					<input type="hidden" value="${id}" name="userId">
					<input type="hidden" value="${No}" name="No"/>
					<input type="hidden" value="${ceshiNo}" name="cn.ceshiNo"/>
					<input type="submit" class="button" value="开始评估"  id="sub" />
					<font id="zi_font1" color="red" size="5">${errorMessage}</font>
				</form>
			</div>
					<div id="he_div" align="right">
				<input type="button" class="button" value="成为经销商" onclick="showQRCodeKu('${No}')" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
 var index = ${size};
function delLine(obj) {
	if(index<=1){
		alert('再删就没有了哦。')
	}else{
	$("#"+obj).remove();
	index--;
	var n = $('#mytable tr').length;
	for(var i=0;i<index;i++){
		var id=	$('#mytable tr')[n-(i+1)].id 
		var num =id.split('_');
		if(num.length == 2){
			$("#processName_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].processName');
			$("#isman_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].isman');
			$("#isman_"+num[1]).attr('checked','checked');
			$("#noman_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].isman');
			$("#dept_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].dept');
			$("#post_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].post');
			$("#postnum_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].postnum');
			$("#age_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].age');
			$("#age_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].age');
			$("#zorj_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].zorj');
			$("#servingtime_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].servingtime');
			$("#pcai_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].pcai');
		}
		
	}
	}
}


function numyanzhen(obj,tag) {
	var num = obj.value;
	var ty = /^[0-9]*[0-9][0-9]*$/;
	var bChk=ty.test(obj.value);;
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
		$("#zi_font").html("只能输入整数");
		$("#zi_font1").html("只能输入整数");
	}
}
function formatage(num){
	var postnum = document.getElementById('postnum_'+num);
	var age = document.getElementById('age_'+num);
	var n=	parseInt(postnum.value)
	var szReg=/^[0-9]*[0-9][0-9]*$/;	
	var str = "";
	if(n<=1){
		str ="请输入正整数";
	}else{
		szReg=/^(\d{1,2},{1}\d{0,2}){1,}$/;
		str = "请安此种41,50,16这种格式输入";
	}
	var bChk=szReg.test(age.value);
	if(!bChk){
		age.value = "";
		age.focus();
		age.select();
		$("#zi_font").html(str);
		$("#zi_font1").html(str);
	}
	
}
function formatervingtime(num){
	var postnum = document.getElementById('postnum_'+num);
	var servingtime = document.getElementById('servingtime_'+num);
	var n = parseInt(postnum.value);
	var szReg=/^\d+(\.\d+)?$/;	
	var str = "";
	if(n<=1){
		str ="请输入正数";
	}else{
		szReg=/^[\d+(\.\d+)?,{1}\d{1,}+(\.\d+)?]{1,}$/;
		str = "请安此种4.4,5,16这种格式输入";
	}
	var bChk=szReg.test(servingtime.value);
	if(!bChk){
		servingtime.value = "";
		servingtime.focus();
		servingtime.select();
		$("#zi_font").html(str);
		$("#zi_font1").html(str);
	}
}
function checkpcai(num){
	var pcai =  document.getElementById('pcai_'+num);
	if(pcai!=null && pcai.value!=""){
		var szReg=/^\d+(\.\d+)?$/;
		var bChk=szReg.test(pcai.value);
		if(!bChk){
			pcai.value = "";
			pcai.focus();
			pcai.select();
			$("#zi_font").html('请输入正数');
			$("#zi_font1").html('请输入正数');
		}
	}
}
function check(){
	var vipNo = document.getElementById('vipNo');
	var campanyname = document.getElementById("campanyname");
	var lianxiren = document.getElementById("lianxiren");
	var phone = document.getElementById("phone");
	if(vipNo!=null && vipNo.value==""){
		$("#zi_font").html('请先选择企业');
		$("#zi_font1").html('请先选择企业');
		return false;
	}else if(campanyname!=null && campanyname.value == ""){
		$("#zi_font").html('请填写公司名称');
		$("#zi_font1").html('请填写公司名称');
		return false;
	}else if(lianxiren !=null && lianxiren.value == ""){
		$("#zi_font").html('请填写联系人名称');
		$("#zi_font1").html('请填写联系人名称');
		return false;
	}else if(phone!=null && phone.value == ""){
		$("#zi_font").html('请填写联系人手机号');
		$("#zi_font1").html('请填写联系人手机号');
		return false;
	}
	for(var i=0;i<index; i++){
		var processName = document.getElementById('processName_'+i);
		var postnum = document.getElementById('postnum_'+i);
		var pcai = document.getElementById('pcai_'+i);
		if(processName!=null && processName.value == ''){
			$("#zi_font").html('请填写流程内容');
			$("#zi_font1").html('请填写流程内容');
			processName.focus();
			return false;
		}else if(postnum!=null && postnum.value == ''){
			$("#zi_font").html('请填写员工人数');
			$("#zi_font1").html('请填写员工人数');
			postnum.focus();
			return false;
		}else if(pcai!=null && pcai.value == ''){
			$("#zi_font").html('请填写人均年收入');
			$("#zi_font1").html('请填写人均年收入');
			pcai.focus();
			return false;
		}
	}
	document.getElementById('sub').disabled="disabled";
	return true;
	
}
function quxiao(obj){
	if(obj.checked == true){
		var id = 	obj.id;
		var array = id.split('_');
		if(array.length == 2){
			id = "tr_"+array[1];
			delLine(id);
		}
		//delLine(obj);
	}
}

function xccheckbox(obj,num){
	if(obj!=null && obj.checked == true){
		obj.value = 'yes';
		$("#postnum_"+num).removeAttr('readonly');
		$("#postnum_"+num).val('1');
		$("#pcai_"+num).val('0');
		$("#pcai_"+num).removeAttr('readonly');
	}else if(obj!=null && obj.checked == false){
		obj.value = 'no';
		$("#postnum_"+num).attr('readonly','readonly');
		$("#postnum_"+num).val(0);
		$("#pcai_"+num).val(0);
		$("#pcai_"+num).attr('readonly','readonly');
	}
	
}

function showkehu_div(){
	$("#kehu_div").show();
		$("#he_div").hide();
}
function showQRCodeKu(No){
	window.location.href = "IntelligentDiagnosisAction_initadduser.action?No="+No;
}
function mobilephoneyanzhen(obj){
	 if(!(/^1[3|4|5|8|7|9][0-9]\d{8}$/.test(obj.value))){ 
        $("#zi_font").html("请输入正确的手机号");
        $("#phoneNumber_font").css({
    		color:"red"
    	});
      	 obj.focus(); 
    } else{
    	$("#zi_font").html("");
    }
}

function fenye(cpage,pageSize){
	var count = Math.ceil(index/10);
	
		if(cpage==1){
			$("#td_fenye").html('第<font color="red">'+cpage+'</font>/'+count+'页<strong>[<a href="javascript:;" onclick=fenye('+(cpage+1)+',10)>下一页</a>]</strong>');
		}else if(cpage == count){
			$("#td_fenye").html('第<font color="red">'+cpage+'</font>/'+count+'页<strong>[<a href="javascript:;" onclick=fenye('+(cpage-1)+',10)>上一页</a>]</strong>');
		}else{
			$("#td_fenye").html('第<font color="red">'+cpage+'</font>/'+count+'页<strong>[<a href="javascript:;" onclick=fenye('+(cpage-1)+',10)>上一页</a> /<a href="javascript:;" onclick=fenye('+(cpage+1)+',10)>下一页</a>]</strong>');
		}
	var size = (cpage-1)*pageSize;
	for(var i=0;i<size;i++){
		$("#tr_"+i).hide();
	}
	for(var i=size;i<cpage*pageSize;i++){
		$("#tr_"+i).show();
	}
	for(var i=cpage*pageSize; i<index; i++){
		$("#tr_"+i).hide();
	}
}
$(function(){
	fenye(1,10);
})
	

</SCRIPT>
	</body>
</html>
