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
			<form action="CompanyVIPAction_updateComanyVIP.action" method="post"  enctype="multipart/form-data">
				<table  id="table1" class="table" style="display: block;">
					<tr>
						<td colspan="8" align="center">
							<h3>企业会员企业基本资料</h3>
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称:
						</th>
						<td >
							${companvip.name}
						</td>
					</tr>
					<tr>
						<th align="right">
							会&nbsp;&nbsp;&nbsp;&nbsp;员&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;&nbsp;&nbsp;&nbsp;号:
						</th>
						<td>
							${companvip.vipNo}
						</td>
					</tr>
					<tr>
							<th align="right" style="">
								负&nbsp;&nbsp;责&nbsp;&nbsp;人&nbsp;&nbsp;姓&nbsp;&nbsp;名:
							</th>
							<td >
								${companvip.companyboss.name}
							</td>
						</tr>
						<tr>
							<th align="right">
								负&nbsp;&nbsp;责&nbsp;&nbsp;人&nbsp;&nbsp;手&nbsp;&nbsp;机:
							</th>
							<td >
								${companvip.companyboss.mobilephone}
							</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;质:
						</th>
						<td>
							${companvip.typexz}
						</td>
					</tr>
					<tr>
						<th align="right">
							所&nbsp;&nbsp;&nbsp;&nbsp;属&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;业:
						</th>
						<td>
							${companvip.industry}
						</td>
					</tr>
					<tr>
						<th align="right">
							成&nbsp;&nbsp;&nbsp;&nbsp;立&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;&nbsp;&nbsp;间:
						</th>
						<td>
							${companvip.foundingTime}
						</td>
					</tr>
					<tr>
						<th align="right">
							营业执照注册号:
						</th>
						<td >
							${companvip.number}
						</td>
					</tr>
					
					
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;址:
						</th>
						<td>
							${companvip.website}
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;址:
						</th>
						<td>
							${companvip.address}
						</td>
					</tr>
					<tr>
						<th align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</th>
						<td>
							${companvip.email}
						</td>
					</tr>
					<tr>
						<th align="right">
							邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编:
						</th>
						<td>
							${companvip.zipcode}
						</td>
					</tr>
					<tr>
						<th align="right">
							员&nbsp;&nbsp;&nbsp;&nbsp;工&nbsp;&nbsp;&nbsp;&nbsp;人&nbsp;&nbsp;&nbsp;&nbsp;数:
						</th>
						<td>
							${companvip.enumber}
						</td>
					</tr>
					<tr>
						<th align="right">
							注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;&nbsp;&nbsp;资&nbsp;&nbsp;&nbsp;&nbsp;金:
						</th>
						<td>
							${companvip.rcapital}
						</td>
					</tr>
					<tr>
						<th align="right">
							去&nbsp;年&nbsp;营&nbsp;业&nbsp;总&nbsp;额:
						</th>
						<td>
							${companvip.lastsales}
						</td>
					</tr>
					<tr>
						<th align="right">
							资&nbsp;&nbsp;&nbsp;&nbsp;产&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;&nbsp;&nbsp;额:
						</th>
						<td>
							${companvip.sales}
						</td>
					</tr>
					<tr>
						<th align="right">
							缴&nbsp;&nbsp;&nbsp;&nbsp;税&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;&nbsp;&nbsp;额:
						</th>
						<td>
							${companvip.totaltax}
						</td>
					</tr>
					<tr>
						<th align="right">
							经&nbsp;&nbsp;&nbsp;&nbsp;营&nbsp;&nbsp;&nbsp;&nbsp;范&nbsp;&nbsp;&nbsp;&nbsp;围
						</th>
						<td >
							<textarea rows="10" cols="60" name="companvip.range">
							${companvip.range}
							
							</textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;介:
						</th>
						<td colspan="3">
							<textarea rows="10" cols="60" name="companvip.synopsis">
								${companvip.synopsis}
							</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" >
							企&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;&nbsp;&nbsp;&nbsp;荣&nbsp;&nbsp;&nbsp;&nbsp;誉:
						</th>
						<td  colspan="3">
							<textarea rows="10" cols="60" name="companvip.honor">
								${companvip.honor}
							</textarea>
						</td>
					</tr>
				</table>
				<table  id="table2" class="tale" style="display: none;">
					<tr>
						<th colspan="2" align="center">
							<h3>负责人基本资料表</h3>
						</th>
					</tr>
					<tr>
						<th align="right">
							姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
						</th>
						<td>
							${companvip.companyboss.name}
						</td>
					</tr>
					<tr>
						<th align="right">
							手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:
						</th>
						<td>
							${companvip.companyboss.mobilephone}
						</td>
					</tr>
					<tr>
						<th align="right">
							性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别 :
						</th>
						<td>
							${companvip.companyboss.sex}
						</td>
					</tr>
					<tr>
						<th align="right">
							联系地址:
						</th>
						<td>
							${companvip.companyboss.address}
						</td>
					</tr>
					<tr>
						<th align="right">
							职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务:
						</th>
						<td>
							${companvip.companyboss.post}
						</td>
					</tr>
					<tr>
						<th align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:
						</th>
						<td>
							${companvip.companyboss.age}
						</td>
					</tr>
					<tr>
						<th align="right">
							民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族:
						</th>
						<td>
							${companvip.companyboss.nation}
						</td>
					</tr>
					<tr>
						<th align="right">
							政治面貌
						</th>
						<td>
							${companvip.companyboss.politicalstatus}:
						</td>
					</tr>
					<tr>
						<th align="right">
							文化程度:
						</th>
						<td>
							${companvip.companyboss.education}
						</td>
					</tr>
					<tr>
						<th align="right">
							座&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:
						</th>
						<td>
							${companvip.companyboss.phoneNum}
						</td>
					</tr>
					<tr>
						<th align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;:
						</th>
						<td>
							${companvip.companyboss.email}
						</td>
					</tr>
					<tr>
						<th align="right">
							个人简历:
						</th>
						<td colspan="5">
							<textarea rows="10" cols="60" name="companvip.companyboss.resume">
								${companvip.companyboss.resume}
							</textarea>
						</td>
					</tr>
				</table>
				<input type="button" value="负责人信息"  id="fuzhebut" onclick="changtable()" style="width: 75px;height: 35px; "/>
				<input type="button" value="下载附件" id="xiazaibut" onclick="xiazai()" style="width: 75px;height: 35px; "/>
				<div id="xiazaidiv" style="display: none;">
				
				</div>
				<input type="button" value="修改"  onclick="update(${companvip.id})" style="width: 75px;height: 35px; "/>
				<input type="button" value="删除"  onclick="del(${companvip.id})" style="width: 75px;height: 35px; "/>
					<br/>
					
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changtable(){
	var table1 = document.getElementById("table1");
	var table2 = document.getElementById("table2");
	if(table1.style.display != "none"){
		table1.style.display = "none";
		table2.style.display="block";
		$("#fuzhebut").val("企业信息");
	}else{
		table2.style.display="none";
		table1.style.display="block";
		$("#fuzhebut").val("负责人信息");
	}
	
	
}

function xiazai(){
	var xiazaibut =	$("#xiazaibut").val();
	var xiazaidiv = document.getElementById("xiazaidiv")
	if(xiazaibut == "下载附件"){
			//对中文进行加密
			var fileName ="${companvip.attachmentName}";
			var aa = fileName.split("|");
			if(aa!=null && aa.length>1){
				xiazaidiv.style.display="block";
				$("#xiazaibut").val("取消下载");
				for(var i=0;i<aa.length;i++){
					$("#xiazaidiv").append('<a href=javascript:; onclick=xiazai1("'+aa[i]+'")>下载附件'+(i+1)+'</a><br/>');
				}
				//document.getElementById("xiazaibut").disabled="disabled";
			}else{
				var fileName1 = encodeURI(encodeURI("${companvip.attachmentName}"));
				location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
			}
		}else if(xiazaibut == "取消下载"){
				xiazaidiv.style.display="none";
				xiazaidiv.innerHTML="";
				$("#xiazaibut").val("下载附件");
		}		
	}
function xiazai1(obj){
	var fileName1 = encodeURI(encodeURI(obj));
	location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
}

function del(id){
	if(confirm("确定要删除吗?")){
		location.href="CompanyVIPAction_delComanyVIP.action?companvip.id="+id;
	}
}
function update(id){
		location.href="CompanyVIPAction_findcompanvipById.action?id="+id+"&statue=update";

}


</SCRIPT>
	</body>
</html>
