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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font id="ziti_font" color="red">${errorMessage}</font>
			<s:if test="gift.num>0">
				<form action="IntegralGiftAction_addintegralgift.action" method="post" onsubmit="">
					<table>
						<tr>
							<th >
								部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:
							</th>
							<td>
								<select name="integralgift.dept" id="dept"
									style="width: 155px;" onchange="showzpname()">
									<option value=""></option>
								</select>
							</td>
						</tr>
						<tr>
							<th >
								姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:
							</th>
							<td>
								<select name="" id="users" style="width: 155px;" onclick="sel()">
									<option value=""></option>
								</select>
								<input type="hidden" name="integralgift.userName" id="userName"
									value="" />
								<input type="hidden" id="userId" value="" name="integralgift.userId">
								<input type="hidden" id="code" name="integralgift.userCode" />	
							</td>
						</tr>
						<tr>
							<th>礼品名称:</th>
							<td>
								<input type="text" value="${gift.name}" name="integralgift.giftNmae" id="giftNmae" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>领取数量:</th>
							<td>
								<input type="text" value="" name="integralgift.lqnum" id="lqnum" onkeyup="numyanzheng(this,'zhengshu');jisuan()"/>
							</td>
						</tr>
						<tr>
							<th>单件积分:</th>
							<td>
								<input type="text" value="${gift.xy_Integral}" name="integralgift.djIntegral" id="djIntegral" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>共需积分:</th>
							<td>
								<input type="text"  name="integralgift.xaIntegral" 
								id="xaIntegral" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="${gift.id}" name="integralgift.giftId"/>
								<input type="hidden" value="兑换" name="integralgift.type"/>
								<input type="submit" value="领取" class="input">
							</td>
						</tr>
					</table>
				</form>
			</s:if>
			<s:else>
					<h2 style="color: red; font-size: 25;">该礼品已兑换完</h2>
			</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
var select;
var num = ${gift.num};
function showzpname() {
	deptname = document.getElementById("dept").value;
	select = document.getElementById("users");
	if (deptname != "") {
		$.ajax( {
			type : "POST",
			url : "UsersAction!findUsersByDept.action",
			cache : false,//防止数据缓存
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				deptName : deptname
			},
			dataType : "json",
			success : function(useradsfa) {
				$("#users").empty();//清空
			$("<option></option>").appendTo("#users");
			$(useradsfa).each(
					function() {
						$(
								"<option value='" + this.code + "|" + this.name
										+ "|" + this.id + "|" + this.cardId
										+ "'>" + this.name + "</option>")
								.appendTo("#users")
					});
			$("#users").bind("change", function() {
				var user = $("#users").val();
				var userCodeName = user.split("|");
				if (userCodeName != "") {
					$("#code").val(userCodeName[0]);
					$("#userName").val(userCodeName[1]);
					$("#userId").val(userCodeName[2]);
					$("#cardId").val(userCodeName[3]);
				} else {
					$("#code").val("");
					$("#userName").val("");
				}
			})

		},
		error : function() {
			alert("服务器异常!");
		}
		});
	} else {
		$("#users").empty();//清空
	}
}
function sel(){
	var dept=document.getElementById("dept").value;
	if(dept==""){
		alert("请先选择部门")
	}
}
function jisuan(){
	var djIntegral = $("#djIntegral").val();
	var lqnum = $("#lqnum").val();
		djIntegral =parseFloat(djIntegral);
		lqnum = parseFloat(lqnum);
	var xaIntegral = 0;
	num = parseFloat(num);
	if(lqnum>0 && djIntegral>0){
		if(lqnum<=num){
			xaIntegral = lqnum*djIntegral;
			$("#xaIntegral").val(xaIntegral);
			var obj=document.getElementById("xaIntegral");
			bijiao(obj);
		}else{
			$("#ziti_font").html($("#giftNmae").val()+'库存剩余量不足'+lqnum+'件。请重新输入数量');
			$("#lqnum").val('');
			$("#xaIntegral").val('');
		}
	}
	
	
}
function bijiao(obj){
	var userId = $("#userId").val()
	var xaIntegral = $("#xaIntegral").val();
	var userName = $("#userName").val();
	var lqnum = $("#lqnum").val();
	var	giftNmae = $("#giftNmae").val();
	if(obj!=null && obj.value!='' && userId != '' && xaIntegral!= null 
			&& xaIntegral != ''){
		$.ajax( {
		type : "POST",
		url : "IntegralGiftAction_isenough.action",
		data : {
			id:userId,
			xaIntegral:xaIntegral
		},
		dataType : "json", 
		success : function(data) {
			if(data == 'error'){
				alert("啊哦,出错了呢")
			}else if(!data){
				$("#ziti_font").html(userName+"所剩余积分不足兑换"+lqnum+"件"+giftNmae+"。请重新输入数量");
				$("#lqnum").val('');
				$("#xaIntegral").val('');
			}else{
				$("#ziti_font").html("");
			}
		}
	})
	}
}

</script>
	</body>
</html>
