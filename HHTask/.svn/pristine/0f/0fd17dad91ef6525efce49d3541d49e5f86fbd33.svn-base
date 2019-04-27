<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<div align="center" ><h3>项目投资设置</h3> </div>
				<s:if test="quotedPrice.fbSatuts==null||quotedPrice.fbSatuts=='未放标'">
				<div  align="right">
				<input type="button" value="放标投资" style="width: 100px;height: 30px;" onclick="updatefbStatus('放标')">&nbsp;&nbsp;&nbsp;
				</div>
				<form action="QuotedPrice_setProTz.action" method="post">
				<input type="hidden" value="${quotedPrice.id}" name="quotedPrice.id">
				<input type="hidden" value="${pageStatus}" name="pageStatus">
					<table class="table" id="tztable" >
					<tr>
					<td colspan="4" align="center"> 零件号:<font color="red">${quotedPrice.markId}</font>&nbsp;&nbsp;阶段:<font color="red">${quotedPrice.status}</font>&nbsp;&nbsp;放标状态:<font color="red">${quotedPrice.fbSatuts}</font> </td>
					</tr>
					<tr>
					<th>工装费</th><td>${projectTime.money}</td>
					<th>工装记入成本</th><td>
					<s:if test="quotedPrice.gzcb==null||quotedPrice.gzcb=='是'.toString()">
					<input id="gzcbyes" type="radio" name="quotedPrice.gzcb" value="是" checked="checked" onchange="sumAllfy()">是&nbsp;&nbsp;<input id="gzcbno" type="radio" name="quotedPrice.gzcb" value="否" onchange="sumAllfy()">否 
					</s:if>
					<s:else>
					<input id="gzcbyes" type="radio" name="quotedPrice.gzcb" value="是" onchange="sumAllfy()">是&nbsp;&nbsp;<input id="gzcbno" type="radio" name="quotedPrice.gzcb" value="否" checked="checked" onchange="sumAllfy()">否 
					</s:else>
					 </td>
					</tr>
					<tr>
					<th>首件成本</th><td>${moneysj}</td>
					<th>小批量单件成</th><td>${moneysz}</td>
					</tr>
					<tr>
					<th>大批量单件成本</th><td>${money}</td>
					<th>小批量数量</th><td> <input id="szCount" value="${quotedPrice.szCount}" name="quotedPrice.szCount" onkeyup="sumAllfy()">(不含首件)</td>
					</tr>
					<tr id="qttr">
					 <th>其他申报总额</th><td>${moneyqt} <label id="open" onclick="qtFeiYong('展开')"><font color="red">(展开)</font></label> <label style="display: none" id="close" onclick="qtFeiYong('关闭')"><font color="red">(关闭)</font></label><input id="flg" type="hidden" value="0"> </td>
					<td colspan="2" id="qtmxtd"></td>
					</tr>
					<tr>
					<th>批产前预计总费用:</th>
					<td><font color="red"><label id="allFeiyong">${quotedPrice.allFeiyong}</label></font></td>
					<th>投资比例:</th>
					<td><input name="quotedPrice.fbBili" value="${quotedPrice.fbBili}" id="fbBili" onkeyup="setfs(1)"/></td>
					</tr>
					<tr>
					<th>单份金额:</th>
					<td><input name="quotedPrice.dfMoney" value="${quotedPrice.dfMoney}" id="dfMoney" onkeyup="setfs(2)"></td>
					<th>份数:</th>
					<td>
					<label id="fs">0</label>
					 </td>
					</tr>
					<tr>
					<td colspan="4" align="center"><input type="submit" value="提交" class="input"> </td>
					</tr>
					</table>
				</form>
				</s:if>
				<s:elseif test="quotedPrice.fbSatuts=='放标'">
				<div  align="right">
				<input type="button" value="关闭放标" style="width: 100px;height: 30px;" onclick="updatefbStatus('关闭')">&nbsp;&nbsp;&nbsp;
				</div>
					<table class="table" id="tztable" >
					<tr>
					<td colspan="4" align="center"> 零件号:<font color="red">${quotedPrice.markId}</font>&nbsp;&nbsp;阶段:<font color="red">${quotedPrice.status}</font>&nbsp;&nbsp;放标状态:<font color="red">${quotedPrice.fbSatuts}</font> </td>
					</tr>
					<tr>
					<th>工装费</th><td>${projectTime.money}</td>
					<th>工装记入成本</th><td>
					<s:if test="quotedPrice.gzcb==null||quotedPrice.gzcb=='是'.toString()">
					<input id="gzcbyes" type="radio" name="quotedPrice.gzcb" value="是" checked="checked" onchange="sumAllfy()">是&nbsp;&nbsp;<input id="gzcbno" type="radio" name="quotedPrice.gzcb" value="否" onchange="sumAllfy()">否 
					</s:if>
					<s:else>
					<input id="gzcbyes" type="radio" name="quotedPrice.gzcb" value="是" onchange="sumAllfy()">是&nbsp;&nbsp;<input id="gzcbno" type="radio" name="quotedPrice.gzcb" value="否" checked="checked" onchange="sumAllfy()">否 
					</s:else>
					 </td>
					</tr>
					<tr>
					<th>首件成本</th><td>${moneysj}</td>
					<th>小批量单件成</th><td>${moneysz}</td>
					</tr>
					<tr>
					<th>大批量单件成本</th><td>${money}</td>
					<th>小批量数量</th><td> ${quotedPrice.szCount}(不含首件)</td>
					</tr>
					<tr id="qttr">
					 <th>其他申报总额</th><td>${moneyqt} <label id="open" onclick="qtFeiYong('展开')"><font color="red">(展开)</font></label> <label style="display: none" id="close" onclick="qtFeiYong('关闭')"><font color="red">(关闭)</font></label><input id="flg" type="hidden" value="0"> </td>
					<td colspan="2" id="qtmxtd"></td>
					</tr>
					<tr>
					<th>批产前预计总费用:</th>
					<td><font color="red"><label id="allFeiyong">${quotedPrice.allFeiyong}</label></font></td>
					<th>投放金额:</th>
					<td>${quotedPrice.tfzonge}</td>
					</tr>
					<tr>
					<th>单份金额:</th>
					<td>${quotedPrice.dfMoney}</td>
					<th>份数:</th>
					<td>
					<label id="fs">0</label>
					 </td>
					</tr>
					</table>
				</s:elseif>
				<s:else>
				<table class="table" id="tztable" >
					<tr>
					<td colspan="4" align="center"> 零件号:<font color="red">${quotedPrice.markId}</font>&nbsp;&nbsp;阶段:<font color="red">${quotedPrice.status}</font>&nbsp;&nbsp;放标状态:<font color="red">${quotedPrice.fbSatuts}</font> </td>
					</tr>
					<tr>
					<th>总费用:</th>
					<td><font color="red">${quotedPrice.allFeiyong}</font></td>
					<th>投放金额:</th>
					<td><input name="quotedPrice.tfzonge" value="${quotedPrice.tfzonge}" id="tfzonge" onkeyup="setfs(1)" readonly="readonly"/></td>
					</tr>
					<tr>
					<th>单份金额:</th>
					<td><input name="quotedPrice.dfMoney" value="${quotedPrice.dfMoney}" id="dfMoney" onkeyup="setfs(2)" readonly="readonly"></td>
					<th>份数:</th>
					<td>
					<label id="fs">0</label>
					 </td>
					</tr>
					</table>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function setfs(index){
			var fbBili = $("#fbBili").val();
			var dfMoney = $("#dfMoney").val();
			var allFeiyong = $("#allFeiyong").html();
			if(index==1){
				if(isNaN(fbBili)){
				alert("请输入数字");
				$("#fbBili").val(1);
				fbBili=1;
				if(dfMoney==null){
					return false;
				}
				}
			}else if(index==2){
				if(isNaN(dfMoney)){
				alert("请输入数字");
				$("#dfMoney").val(1);
				dfMoney=1;
				if(fbBili==null){
					return false;
					}
				}
			}else{
				if(isNaN(dfMoney)||isNaN(fbBili)){
					$("#fs").html(0);
				}
			}
			if(fbBili!=null&&dfMoney!=null&&dfMoney!=0){
			$("#fs").html(Math.floor(allFeiyong*fbBili/dfMoney));
			}
		}
		$(document).ready(function(){
			var fbSatuts="${quotedPrice.fbSatuts}";
			if(fbSatuts=="未放标"){
			 setfs(0);
			}else{
				var tfzonge="${quotedPrice.tfzonge}";
				var dfMoney="${quotedPrice.dfMoney}";
				$("#fs").html(Math.floor(tfzonge/dfMoney));
			}
		});
		function updatefbStatus(op){
			if(window.confirm("您将对此项目"+op+",是否确定?")){
				$.ajax( {
		type : "post",
		url : "QuotedPrice_updatefbStatus.action",
		data :{
			id : ${quotedPrice.id}
		},
		dataType : "json",
		success : function(data) {
			if(data=="true"){
				alert(op+"成功!");
				window.location.href="QuotedPrice_tosetProTz.action?id="+${quotedPrice.id}+"&pageStatus="+${pageStatus};
			}else{
				alert(data);
			}
		}
	});
			}
		}
	function sumAllfy(){
		mustBeNumber("szCount")
		var gzcb=0;
		if($("input:radio[name='quotedPrice.gzcb']:checked").val()=="是"){
			gzcb=${projectTime.money-0};
		}
			var szCount = $("#szCount").val();
			var moneysz = "${moneysz}";
			var moneysj = "${moneysj}";
			var moneyqt = "${moneyqt}";
			var allFeiyong = szCount*moneysz+(moneysj-0)+(moneyqt-0)+gzcb;
			$("#allFeiyong").html(allFeiyong);
		setfs(0);
	}
	function qtFeiYong(op){
		if(op=="展开"){
			var flg= $("#flg").val();
			if(flg==0){
				$.ajax( {
		type : "post",
		url : "QuotedPrice_findYsFeiYong.action",
		data :{
			id : ${quotedPrice.id}
		},
		dataType : "json",
		success : function(data) {
			var html ="";
			var index = 0;
			$(data).each(
					function() {
						if(index==0){
							html+="<table style='width: 90%' class='tbale qtmx'>";
							html+="<tr><th>序号</th><th>类型</th><th>申请人</th><th>费用</th></tr>";
						}
						html+="<tr><td align='center'>"+(index+1)+"</td><td align='center'>"+this.costType+"</td>" +
						"<td align='center'>"+this.userName+"</td><td align='center'>"+this.money+"</td></tr>";
						
					});
			if(html!=""){
				html+="</table>";
				$("#qtmxtd").append(html);
			}
				$("#open").hide();
				$("#close").show();
				$("#flg").val(1);
		}
	});
			}else{
				$(".qtmx").show();
				$("#open").hide();
				$("#close").show();
			}
		}else{
			$(".qtmx").hide();
			$("#open").show();
			$("#close").hide();
		}
	}
		</SCRIPT>
	</body>
</html>
