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
	<body onload="send1()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			
			<div align="center">

				<form action="paymentVoucherAction_findExamList.action?tag=<s:property value='tag' />"
					method="post" >
					<table class="table">
<%--						<tr>--%>
<%--							<td align="right" colspan="12">--%>
<%--								<font color="red">共选择 <label id="peopleLabel">--%>
<%--										${count}--%>
<%--									</label> <input type="hidden" id="propleText" name="peopleNum"--%>
<%--										style="width: 20px;" readonly="readonly"> 条记录</font>--%>
<%--								<br>--%>
<%--								<br>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<td align="center">
								序号
							</td>
							<td align="center">
								收款单位
							</td>
							<td align="center">
								合同日期
							</td>
							<td align="center">
								付款金额
							</td>
							<td align="center">
								关联客户
							</td>
							<td align="center">
								审批状态
							</td>
							<td align="center">
								合同编号
							</td>
							<th align="center" style="width: 40px;">
<%--								<input type="checkbox" id="checkAll"--%>
<%--									onclick="chageAllCheck(this)">--%>
<%--								全选--%>
						操作
							</th>
						</tr>

						<s:iterator value="list" status="se" id="payment">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${payment.unitname}
							</td>
							<td>
								${payment.contractdate}
							</td>
							<td>
								${payment.voucherMoney}
							</td>
							<td>
								${payment.relationclient}
							</td>
							<td>
								${payment.approvalStatus}
							</td>
							<td>
								${payment.number}
							</td>
							<td>
<%--								<input type="checkbox" id="${payment.voucherMoney}"--%>
<%--									name="detailSelect" value="${payment.id}" --%>
<%--									onclick="chageNum(this,'${payment.voucherMoney}')">--%>
									<a href="CircuitRunAction_findAduitPage.action?id=${payment.epId}">审批动态</a>
							</td>
							</tr>
						</s:iterator>
<%--						<tr>--%>
<%--							<td colspan="12" align="right"--%>
<%--								style="font-weight: bold; padding-right: 40px">--%>
<%--						<input id="number1" type="text" style="width: 80px;" onkeyup="getNumber()" align="middle" maxlength="6">--%>
<%--						 <input id="send_id" type="button" onclick="onsend()" value="获取验证码" align="middle" >--%>
<%--						<span id="isok"> </span>--%>
<%--								<input type="checkbox" id="checkAll2"--%>
<%--									onclick="chageAllCheck(this)">--%>
<%--								全选--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr id="tr1" >--%>
<%--							<td align="right" colspan="12">--%>
<%--								<font color="red">共选择 <label id="peopleLabel2">--%>
<%--										${count}--%>
<%--									</label> <input type="hidden" id="propleText" name="peopleNum"--%>
<%--										style="width: 20px;" readonly="readonly"> 条记录</font>--%>
<%--										<font color="red">合计<label id="allMoney"></label>元</font>--%>
<%--										<input id="ok" class="input"  style="width:120px;" align="top" type="submit" disabled="disabled" value="批量审批通过" onclick="chageType(this,this.form)"/>--%>
<%--    									<input id="ng" class="input" align="top" type="submit" value="批量驳回" disabled="disabled" onclick="chageType(this,this.form)" />--%>
<%--    									 共 <s:property value="total"/> 页 第 <s:property value="cpage"/> 页--%>
<%--        								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number"/>--%>
<%--								<br>--%>
<%--								<br>--%>
<%--							</td>--%>
<%--						</tr>--%>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	function setTime(){
		var a = setTimeout(function(){
				$("#send_id").removeAttr("disabled");
		},1000*10);
	}
	function onsend(){
		$("#send_id").attr("disabled","disabled");
		$.ajax( {
				type : "POST",
				url : "<%=request.getContextPath()%>/paymentVoucherAction_send.action",
				data : {},
				dataType : "json",
				success : function(data) {
					setTime();
				}
			});
	}
	function getNumber(){
			var number1 = $("#number1").val();
			var a = number1.length;
			var number2 = "";
			if(a==6){
					var strCookie = document.cookie;//获得所有cookie
					var arrCookie = strCookie.split(";");
					var code = ${sessionScope.Users.code};//获得当前登录人工号
						for ( var i = 0; i < arrCookie.length; i++) {
						var arr = arrCookie[i].split("=");
						var arr0 = arr[0].replace(/\ /g, "");
						if (""+code+"_yzm" == arr0) {
							number2 = arr[1];
							if(number1==number2){
								$("#isok").html( "<img	src='${pageContext.request.contextPath}/images/success.jpg'>");
								$("#ok").removeAttr("disabled");
								$("#ng").removeAttr("disabled");
							}else{
									$("#isok").html( "<img	src='${pageContext.request.contextPath}/images/error.jpg'>");
							}
						} 
					}
			}
	}
    	function chageType(obj,form){
    		if(obj.id=="ok"){
    			var id = "${payment.id}";
    			form.action="paymentVoucherAction_updateExamDetail.action?tag=ok&id="+id;
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="paymentVoucherAction_updateExamDetail.action?tag=ng";
    			form.submit();
    		}
    	}
   
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox,checkBox.id);
				}
			}
		}
	}
}
var num = "${count}";
if (num == "") {
	num = 0;
}
 var money = 0;
function chageNum(obj,obj2) {
	
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		money+=parseFloat(obj2);
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		money=0;
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		money=money-obj2;
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
}
</script>
</html>
