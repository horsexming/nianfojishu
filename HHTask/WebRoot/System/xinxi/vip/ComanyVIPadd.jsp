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
				<font color="red"  size="5">${errorMessage}</font><br/><!-- onsubmit="return check()" -->
			<form action="CompanyVIPAction_updateComanyVIP.action" 
			 onsubmit="return check()"	method="post"  enctype="multipart/form-data">
				<table  id="table1" style="display: block;">
					<tr>
						<td colspan="8" align="center">
							<h3>企业会员企业基本资料</h3>
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称
						</th>
						<td >
							<input type="text" name="companvip.name" id="name" value="${companvip.name}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							会&nbsp;&nbsp;&nbsp;&nbsp;员&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;&nbsp;&nbsp;&nbsp;号
						</th>
						<td>
							<input type="text" name="companvip.vipNo" id="vipNo" value="${companvip.vipNo}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
							<th align="right" style="">
								负&nbsp;&nbsp;责&nbsp;&nbsp;人&nbsp;&nbsp;姓&nbsp;&nbsp;名
							</th>
							<td >
								<input type="text" name="companvip.companyboss.name" id="cname" readonly="readonly"
									 value="${companvip.companyboss.name}"
									 />
							</td>
						</tr>
						<tr>
							<th align="right">
								负&nbsp;&nbsp;责&nbsp;&nbsp;人&nbsp;&nbsp;手&nbsp;&nbsp;机
							</th>
							<td >
								<input type="text" name="companvip.companyboss.mobilephone" readonly="readonly"
									maxlength="11" id="mobilephone" value="${companvip.companyboss.mobilephone}"
									/>
							</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;质
						</th>
						<td>
							<input type="text" value="${companvip.typexz}" name="companvip.typexz" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							所&nbsp;&nbsp;&nbsp;&nbsp;属&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;业
						</th>
						<td>
							<input type="text" name="companvip.industry" id="industry" value="${companvip.industry}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							成&nbsp;&nbsp;&nbsp;&nbsp;立&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间
						</th>
						<td>
							<input type="text" name="companvip.foundingTime" 
							id="foundingTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" 
							class="Wdate" onfocus="nameyanzhe('foundingTime','请输入成立时间')" onkeyup="nameyanzhe('foundingTime','请输入成立时间')"
									onblur="nameyanzhe('foundingTime','请输入成立时间')"/>
							<font color="red" id="foundingTimefont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							营业执照注册号
						</th>
						<td >
							<input type="text" name="companvip.number" 
							id="number" onfocus="nameyanzhe('number','请输入营业执照注册号')" onkeyup="nameyanzhe('number','请输入营业执照注册号')"
									onblur="nameyanzhe('number','请输入营业执照注册号')"/>
							<font color="red" id="numberfont">*</font>
						</td>
					</tr>
					
					
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;址
						</th>
						<td>
							<input type="text" name="companvip.website" 
							id="website" onfocus="nameyanzhe('website','请输入企业网址')" onkeyup="nameyanzhe('website','请输入企业网址')"
									onblur="nameyanzhe('website','请输入企业网址')"/>
							<font color="red" id="websitefont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;址
						</th>
						<td>
							<input type="text" name="companvip.address" 
							id="address" onfocus="nameyanzhe('address','请输入企业地址')" onkeyup="nameyanzhe('address','请输入企业地址')"
									onblur="nameyanzhe('address','请输入企业地址')"/>
							<font color="red" id="addressfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</th>
						<td>
							<input type="text"  id="email" name="companvip.email"  onkeyup="Emailyanzhen('email','请输入正确的邮箱格式')"
							 onblur="Emailyanzhen('email','请输入正确的邮箱格式')" onfocus="Emailyanzhen('email','请输入正确的邮箱格式')"/>
							 <font color="red" id="emailfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编
						</th>
						<td>
							<input type="text" name="companvip.zipcode"  onfocus="nameyanzhe('zipcode','请输入企业邮编')" onkeyup="nameyanzhe('zipcode','请输入企业邮编')"
									onblur="nameyanzhe('zipcode','请输入企业邮编')"
							id="zipcode" /><font color="red" id="zipcodefont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							员&nbsp;&nbsp;&nbsp;&nbsp;工&nbsp;&nbsp;&nbsp;&nbsp;人&nbsp;&nbsp;&nbsp;&nbsp;数
						</th>
						<td>
							<input type="text" name="companvip.enumber" onfocus="numyanzhen('enumber','请输入员工人数')" onkeyup="numyanzhen('enumber','请输入员工人数')"
									onblur="numyanzhen('enumber','请输入员工人数')" 
							id="enumber" onchange="numyanzhen(this)"/>
							<font color="red" id="enumberfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;&nbsp;&nbsp;资&nbsp;&nbsp;&nbsp;&nbsp;金
						</th>
						<td>
							<input type="text" name="companvip.rcapital" onfocus="numyanzhen('rcapital','请输入注册资金')" onkeyup="nameyanzhe('rcapital','请输入注册资金')"
									onblur="numyanzhen('rcapital','请输入注册资金')"
							id="rcapital" onchange="numyanzhen(this)"/>(万元)
							<font color="red" id="rcapitalfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							去&nbsp;年&nbsp;营&nbsp;业&nbsp;总&nbsp;额
						</th>
						<td>
							<input type="text" name="companvip.lastsales"onfocus="numyanzhen('lastsales','请输入去年营业总额')" onkeyup="numyanzhen('lastsales','请输入去年营业总额')"
									onblur="numyanzhen('lastsales','请输入去年营业总额')" onclick="numyanzhen(this)" 
							 id="lastsales" />(万元)
							 <font color="red" id="lastsalesfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							资&nbsp;&nbsp;&nbsp;&nbsp;产&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;&nbsp;&nbsp;额
						</th>
						<td>
							<input type="text" name="companvip.sales" onfocus="numyanzhen('sales','请输入资产总额')" onkeyup="numyanzhen('sales','请输入资产总额')"
									onblur="numyanzhen('sales','请输入资产总额')" 
							id="sales" />(万元)
							<font color="red" id="salesfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							缴&nbsp;&nbsp;&nbsp;&nbsp;税&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;&nbsp;&nbsp;额
						</th>
						<td>
							<input type="text" name="companvip.totaltax" onfocus="numyanzhen('totaltax','请输入缴税总额')" onkeyup="numyanzhen('totaltax','请输入缴税总额')"
									onblur="numyanzhen('totaltax','请输入缴税总额')"  
							 id="totaltax" />(万元)
							 <font color="red" id="totaltaxfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							上&nbsp;&nbsp;&nbsp;&nbsp;传&nbsp;&nbsp;&nbsp;&nbsp;附&nbsp;&nbsp;&nbsp;&nbsp;件	
						</th>
						<td>
							<input type="button" id="fileButton"
											onclick="uploadFile(this)" value="上传附件"><font color="red">*</font>

										<div id="fileDiv" style="display: none;">

										</div>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							经&nbsp;&nbsp;&nbsp;&nbsp;营&nbsp;&nbsp;&nbsp;&nbsp;范&nbsp;&nbsp;&nbsp;&nbsp;围
						</th>
						<td >
							<textarea rows="10" cols="60" name="companvip.range"></textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;介
						</th>
						<td colspan="3">
							<textarea rows="10" cols="60" name="companvip.synopsis"></textarea>
						</td>
					</tr>
					<tr>
						<th align="right" >
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;荣&nbsp;&nbsp;&nbsp;&nbsp;誉
						</th>
						<td  colspan="3">
							<textarea rows="10" cols="60" name="companvip.honor"></textarea>
						</td>
					</tr>
					
					
					<tr>
						<td colspan="8" align="center">
							<input type="button" onclick="GoOn()" value="下一步" style="width: 75px;height: 35px;"/>
						</td>
					</tr>
				</table>
				<table  id="table2" style="display: none;">
					<tr>
						<th colspan="2" align="center">
							<h3>负责人基本资料表</h3>
						</th>
					</tr>
					<tr>
						<th align="right">
							姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名
						</th>
						<td>
							<input type="text"  id="cname" value="${companvip.companyboss.name}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机
						</th>
						<td>
							<input type="text"  id="mobilephone" value="${companvip.companyboss.mobilephone}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别 
						</th>
						<td>
							<input type="radio" name="companvip.companyboss.sex"  value="男" checked="checked"/>男
							<input type="radio" name="companvip.companyboss.sex"  value ="女"/>女
						</td>
					</tr>
					<tr>
						<th align="right">
							联系地址
						</th>
						<td>
							<input type="text" name="companvip.companyboss.address"
							 id="caddress" onfocus="nameyanzhe('caddress','请输入联系地址')" onkeyup="nameyanzhe('caddress','请输入联系地址')"
									onblur="nameyanzhe('caddress','请输入联系地址')"/>
							<font color="red" id="caddressfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务
						</th>
						<td>
							<input type="text" name="companvip.companyboss.post" onfocus="nameyanzhe('post','请输入职务')" onkeyup="nameyanzhe('post','请输入职务')"
									onblur="nameyanzhe('post','请输入职务')"
							 id="post"/><font color="red" id="postfont">*</font>
						</td>
					</tr>
					<tr>
						<th align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄
						</th>
						<td>
							<input type="text" name="companvip.companyboss.age" onfocus="numyanzhen1('age','请输入年龄')" onkeyup="numyanzhen1('age','请输入年龄')"
									onblur="numyanzhen1('age','请输入年龄')"
							 id="age" maxlength="3" />
							 <font color="red" id="agefont"></font>
						</td>
					</tr>
					<tr>
						<th align="right">
							民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族
						</th>
						<td>
							<input type="text" name="companvip.companyboss.nation" id="nation"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							政治面貌
						</th>
						<td>
							<input type="text" name="companvip.companyboss.politicalstatus" id="politicalstatus"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							文化程度
						</th>
						<td>
							<input type="text" name="companvip.companyboss.education" id="education"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							座&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机
						</th>
						<td>
							<input type="text" name="companvip.companyboss.phoneNum" id="phoneNum"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;
						</th>
						<td>
							<input type="text" name="companvip.companyboss.email" id="cemail" onkeyup="Emailyanzhen('cemail','请输入正确的邮箱格式')"
							 onblur="Emailyanzhen('cemail','请输入正确的邮箱格式')" onfocus="Emailyanzhen('cemail','请输入正确的邮箱格式')"/>
							 <font color="red" id="cemailfont"></font>
						</td>
					</tr>
					<tr>
						<th align="right">
							个人简历
						</th>
						<td colspan="5">
							<textarea rows="10" cols="60" name="companvip.companyboss.resume"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="hidden" value="${companvip.id}" name="companvip.id"/>
							<input type="hidden" value="${companvip.companyboss.id}" name="companvip.companyboss.id"/>
							<input type="button" value="上一步" style="width: 75px;height: 35px;" onclick="Back()"/>
							<input type="submit" value="提交" style="width: 75px;height: 35px;" id="sub" />
						</td>
					</tr>
				</table>
						<font color="red" id="fontzi"></font><br/>
					<br/>
					
			</form>
			<div align="left">
			<span>
					<STRONG>
							注 1、请认真填写以下资料，方便我们审核和联系您。<br/>
							2、请附企业工商营业执照副本、税务登记有关证件和个人身份证等扫描件。<br/>
						</STRONG>
					</span>
			</div>
			<br/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
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
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + ")'>删除</a></div>";
	
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton").value = "上传附件";
		document.getElementById("fileDiv").style.display = "none";
	}
}
function GoOn(){
	var rcapital =document.getElementById("rcapital");
	var fileButton = document.getElementById("fileButton");
	var foundingTime = document.getElementById("foundingTime");
	//var rcapital =document.getElementById("rcapital");
	var vipNo = document.getElementById("vipNo");
	var email = document.getElementById("email");
	var number = document.getElementById("number");
	var typexz = document.getElementById("typexz");
	var industry = document.getElementById("industry");
	var website = document.getElementById("website");
	var address = document.getElementById("address");
	var zipcode = document.getElementById("zipcode");
	var lastsales = document.getElementById("lastsales");
	var sales = document.getElementById("sales");
	var totaltax = document.getElementById("totaltax");
	var enumber = document.getElementById("enumber");
	var attachment = document.getElementsByName("attachment");
	var bool = true;
	if(attachment!=null && attachment.length>0){
		for(var i=0;i<attachment.length ;i++){
			if(attachment[i].value != ""){
				bool = false;
			}
		}
	}
	if(foundingTime!=null && foundingTime.value == ""){
		$("#fontzi").html("请填写公司成立时间");
		foundingTime.focus();
		return false;
	}else if(number!=null && number.value == ""){
		$("#fontzi").html("请填写公司营业执照注册号");
		number.focus();
		return false;
	}else if(website!=null && website.value == ""){
		$("#fontzi").html("请填写企业网址");
		website.focus();
		return false;
	}else if(address!=null && address.value == ""){
		$("#fontzi").html("请填写企业地址");
		address.focus();
		return false;
	}else if(email!=null && email.value == ""){
		$("#fontzi").html("请填写公司email");
		email.focus();
		return false;
	}else if(zipcode!=null && zipcode.value == ""){
		$("#fontzi").html("请填写公司邮编");
		zipcode.focus();
		return false;
	}else if(enumber!=null && enumber.value == ""){
		$("#fontzi").html("请填写公司员工人数");
		enumber.focus();
		return false;
	}else if(rcapital!=null && rcapital.value == ""){
		$("#fontzi").html("请填写注册资金");
		rcapital.focus();
		return false;
	}
	else if(lastsales!=null && lastsales.value == ""){
		$("#fontzi").html("请填写公司去年营业总额");
		lastsales.focus();
		return false;
	}else if(sales!=null && sales.value == ""){
		$("#fontzi").html("请填写公司资产总额");
		sales.focus();
		return false;
	}else if(totaltax!=null && totaltax.value == ""){
		$("#fontzi").html("请填写公司缴税总额");
		totaltax.focus();
		return false;
	}else if(bool){
		$("#fontzi").html("请上传附件");
		fileButton.focus();
		return false;
	}
	$("#fontzi").html("");
	var table1 = document.getElementById("table1");
	var table2 = document.getElementById("table2");
	table1.style.display="none";
	table2.style.display="block";
}

function Back(){
	$("#fontzi").html("");
	var table1 = document.getElementById("table1");
	var table2 = document.getElementById("table2");
	table1.style.display="block";
	table2.style.display="none";
}
function check(){
	var address = document.getElementById("address");
	var post = document.getElementById("post");
	 if(address!=null && address.value == ""){
		$("#fontzi").html("请填写企业负责人联系地址");
		address.focus();
		return false;
	}else if(post!=null && post.value == ""){
		$("#fontzi").html("请填写企业负责人职务");
		post.focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
	
}

function numyanzhen(obj,obj2) {
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var str = document.getElementById(obj);
	var re = new RegExp(ty1);
	if (str != null && str.value.match(re) == null) {
		$("#"+obj+"font").css({
    		color:"red"
    	});
		$("#"+obj+"font").html("✘");
		$("#fontzi").html(obj2);
		str.focus()	;
	}else{
		$("#"+obj+"font").css({
    		color:"blue"
    	});
    		$("#"+obj+"font").html("✔");
        $("#fontzi").html("");
	}	
}
function Emailyanzhen(obj,obj2) {
	var str = document.getElementById(obj);
	var szReg=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/; 
	var bChk=szReg.test(str.value);
	if(!bChk){
		$("#"+obj+"font").css({
    		color:"red"
    	});
		$("#"+obj+"font").html("✘");
		$("#fontzi").html(obj2);
		str.focus()	;
	}else{
		$("#"+obj+"font").css({
    		color:"blue"
    	});
    		$("#"+obj+"font").html("✔");
        $("#fontzi").html("");
	}	
}
	
function nameyanzhe(obj,obj2){
	var str = document.getElementById(obj);
	if(str!=null && str.value.toString().trim() ==""){
		$("#"+obj+"font").css({
    		color:"red"
    	});
		$("#"+obj+"font").html("✘");
		$("#fontzi").html(obj2);
		str.focus()	;
	}else{
		$("#"+obj+"font").css({
    		color:"blue"
    	});
    		$("#"+obj+"font").html("✔");
        $("#fontzi").html("");
	}	
}
function numyanzhen1(obj,obj2) {
	var str = document.getElementById(obj);
	var ty = '^[0-9]*[1-9][0-9]*$';
	var re = new RegExp(ty);
	if (str != null && str.value.match(re) == null) {
		$("#"+obj+"font").css({
    		color:"red"
    	});
		$("#"+obj+"font").html("✘");
		$("#fontzi").html(obj2);
		str.focus()	;
	}else{
		$("#"+obj+"font").css({
    		color:"blue"
    	});
    		$("#"+obj+"font").html("✔");
        $("#fontzi").html("");
	}	
}

</SCRIPT>
	</body>
</html>
