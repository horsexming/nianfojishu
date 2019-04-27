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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font color="red" id="fontzi">${errorMessage}</font>
				<br />
				<strong><font size="5">企业会员申请</font>
				</strong>
				<form action="CompanyVIPAction_add.action" method="post"
					onsubmit="return check()" enctype="multipart/form-data">
					<table id="table1" 
						>
						<tr>
							<th align="right" style="">
								会&nbsp;员&nbsp;&nbsp;编&nbsp;号
							</th>
							<td style="width: 257px;">
								<input type="text" name="companvip.vipNo" id="vipNo"
									value="${companvip.vipNo}" readonly="readonly"
									style="width: 257px; height: 34px;" />
							</td>
						</tr>
						<tr>
							<th align="right" style="">
								企&nbsp;业&nbsp;&nbsp;名&nbsp;称
							</th>
							<td >
								<input type="text" name="companvip.name" id="name"
									style="width: 257px; height: 34px;" value="${campanyname}"
									onfocus="nameyanzhe('name','请输入企业名称')" onkeyup="nameyanzhe('name','请输入企业名称')"
									onblur="nameyanzhe('name','请输入企业名称')" />

							</td>
							<td >

								<font color="red" id="namefont">*</font>
							</td>
						</tr>
						<tr>
							<th align="right" style="">
								负责人姓名
							</th>
							<td >
								<input type="text" name="companvip.companyboss.name" id="cname"
									style="width: 257px; height: 34px;"
									onfocus="nameyanzhe('cname','请输入负责人姓名')" onkeyup="nameyanzhe('cname','请输入负责人姓名')"
									onblur="nameyanzhe('cname','请输入负责人姓名')" />
							</td>
							<td>
								<font color="red" id="cnamefont">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								负责人手机
							</th>
							<td >
								<input type="text" name="companvip.companyboss.mobilephone"
									maxlength="11" id="mobilephone"
									style="width: 257px; height: 34px;"
									onfocus="mobilephoneyanzhen(this)"
									onkeyup="mobilephoneyanzhen(this)"
									onblur="mobilephoneyanzhen(this)" />
							</td>
							<td>
								<font color="red" id="mobilephonefont">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								所&nbsp;属&nbsp;&nbsp;行&nbsp;业
							</th>
							<td >
								<select class="cxselect" name="companvip.industry" id="industry"
									style="width: 257px; height: 34px;"
									onchange="nameyanzhe('industry','请选择所属行业')"
									>
									<option value="">
										--请选择--
									</option>
									<option value=" 农、林、牧、渔业">
										农、林、牧、渔业
									</option>
									<option value="采矿业">
										采矿业
									</option>
									<option value="制造业">
										制造业
									</option>
									<option value="电力、热力、燃气及水的生产和供应业">
										电力、热力、燃气及水的生产和供应业
									</option>
									<option value="环境和公共设施管理业">
										环境和公共设施管理业
									</option>
									<option value="建筑业">
										建筑业
									</option>
									<option value="交通运输、仓储业和邮政业">
										交通运输、仓储业和邮政业
									</option>
									<option value="信息传输、计算机服务和软件业">
										信息传输、计算机服务和软件业
									</option>
									<option value="批发和零售业">
										批发和零售业
									</option>
									<option value="住宿、餐饮业">
										住宿、餐饮业
									</option>
									<option value="金融、保险业">
										金融、保险业
									</option>
									<option value="房地产业">
										房地产业
									</option>
									<option value="租赁和商务服务业">
										租赁和商务服务业
									</option>
									<option value="科学研究、技术服务和地质勘查业">
										科学研究、技术服务和地质勘查业
									</option>
									<option value="水利、环境和公共设施管理业">
										水利、环境和公共设施管理业
									</option>
									<option value="居民服务和其他服务业">
										居民服务和其他服务业
									</option>
									<option value="教育">
										教育
									</option>
									<option value="卫生、社会保障和社会服务业">
										卫生、社会保障和社会服务业
									</option>
									<option value="文化、体育、娱乐业">
										文化、体育、娱乐业
									</option>
									<option value="综合（含投资类、主业不明显）">
										综合（含投资类、主业不明显）
									</option>
									<option value="其它">
										其它
									</option>

								</select>

							</td>
							<td>
								<font color="red" id="industryfont">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								企&nbsp;业&nbsp;&nbsp;性&nbsp;质
							</th>
							<td >
								<SELECT id="typexz" name="companvip.typexz" class="cxselect"
									style="width: 257px; height: 34px;"
									onchange="nameyanzhe('typexz','请选择企业性质')"
									>
									<option value="">
										--请选择--
									</option>
									<option value="国有企业">
										国有企业
									</option>
									<option value="集体企业">
										集体企业
									</option>
									<option value="联营企业">
										联营企业
									</option>
									<option value="股份合作制企业">
										股份合作制企业
									</option>
									<option value="私营企业">
										私营企业
									</option>
									<option value="个体户">
										个体户
									</option>
									<option value="合伙企业">
										合伙企业
									</option>
									<option value="有限责任公司">
										有限责任公司
									</option>
									<option value="股份有限公司">
										股份有限公司
									</option>
									<option value="其它">
										其它
									</option>
								</SELECT>

							</td>
							<td>
								<font color="red" id="typexzfont">*</font>
							</td>
						</tr>


						<tr>
							<td></td>
							<td align="center">

							</td>
						</tr>
					</table>
				<div style="display: none;" id="zidiv"><font id="zifont" color="red"></font><br></div>	
					<input type="submit" value="申请" style="width: 75px; height: 35px;"
						id="sub" />
					<br />

				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">

function check(){
	var zidiv = document.getElementById("zidiv");
	var name = document.getElementById("name");
	var cname = document.getElementById("cname");
	var mobilephone = document.getElementById("mobilephone");
	var industry = document.getElementById("industry");
	var typexz = document.getElementById("typexz");
	if(name!=null && name.value == ""){
		$("#namefont").html("✘");
		name.focus();
		return false;
	}else if(cname!=null && cname.value == ""){
		$("#cnamefont").html("✘");
		cname.focus();
		return false;
	}else if(mobilephone!=null && mobilephone.value == ""){
		$("#mobilephonefont").html("✘");
		mobilephone.focus();
		return false;
	}else if(industry!=null && industry.value == ""){
		$("#industryfont").html("✘");
		 zidiv.style.display = "block";
        $("#zifont").html("请选择所属行业");
		industry.focus();
		return false;
	}else if(typexz!=null && typexz.value == ""){
		$("#typexzfont").html("✘");
		typexz.focus();
		 zidiv.style.display = "block";
        $("#zifont").html("请选择企业性质");
		return false;
	}
	
}

function mobilephoneyanzhen(obj){
	var zidiv = document.getElementById("zidiv");
	 if(!(/^1[3|4|5|8|7|9][0-9]\d{8}$/.test(obj.value))){ 
        $("#mobilephonefont").html("✘");
        $("#mobilephonefont").css({
    		color:"red"
    	});
        zidiv.style.display = "block";
        $("#zifont").html("请输入正确的手机号");
      	 obj.focus(); 
        return false; 
    } else{
    	$("#mobilephonefont").css({
    		color:"blue"
    	});
    		$("#mobilephonefont").html("✔");	
    		 zidiv.style.display = "none";
        $("#zifont").html("");
    }
}
function nameyanzhe(obj,obj2){
	var zidiv = document.getElementById("zidiv");
	var str = document.getElementById(obj);
	if(str!=null && str.value.toString().trim() ==""){
		$("#"+obj+"font").css({
    		color:"red"
    	});
		$("#"+obj+"font").html("✘");
		zidiv.style.display = "block";
		$("#zifont").html(obj2);
		str.focus()	;
	}else{
		$("#"+obj+"font").css({
    		color:"blue"
    	});
    		$("#"+obj+"font").html("✔");
    		 zidiv.style.display = "none";
        $("#zifont").html("");
	}	
}

</SCRIPT>
	</body>
</html>
