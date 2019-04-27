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
<script type="text/javascript">
var currentDate='<%=new Date().getTime()%>';
var lastLingyongDate='';
function getUser() {
	var cardNum = $('param.cardNum');
	$.ajax( {
		url : "store!getUser.action",
		type : "post",
		dataType : 'json',
		data : {
			cardNum : cardNum
		},
		success : function(o) {
			if (o) {
				var src = "<%=basePath%>/upload/user/" + o.picture ;
				$('#cardId').val(o.cardId);
				$('#peopleName').val(o.name);
				$('#dept').val(o.dept);
				$('#portrait').attr('src', src);
				if (o.power == "允许") {
					$('#agree').show();
				}
				$('#power').val(o.power);
				$('tr:hidden').show();
			} else {
				$('tr:hidden').hide();
			}
		}
	});
	
	//获得用户最后一次领取改记录信息
	var storeId='${store.id}'
	$.ajax({
		type: "post",
		dataType: "json",
        url: "consuming_getConsumingByStoreId.action",
        data:{
			'bo.store.id': storeId,
			'bo.cardNum': cardNum
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var con=data.data;
				if(con){
					lastLingyongDate=con.date;
				}
			}
		}
	});
}
var exp = /^\d+$/;
function validate() {
	var result=false;
	if(lastLingyongDate){
		//领取过
		if(lingyongzZhouqi='1年'){
			result=currentDate-lastLingyongDate>365*24*60*60*1000
			if(!result){
				alert("领用周期1年,你已经领取过!");
				return;
			}
		}else if(lingyongzZhouqi='2年'){
			result=currentDate-lastLingyongDate>365*24*60*60*1000*2;
			if(!result){
				alert("领用周期2年,你已经领取过!");
				return;
			}
		}
	}
	
	var cardNumStr = document.getElementById("cardNum").value;
	var num = document.getElementById("num").value;
	var dateStr = document.getElementById("dateStr").value;
	if (cardNumStr == "") {
		alert("请刷卡!");
		return false;
	}
	if (num == "") {
		alert("请输入数量!");
		return false;
	}
	if (dateStr == "") {
		alert("请选择时间");
		return false;
	}
	return true;
}
</script>
	</head>
	<body onkeydown="enter()">
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<p>
					<span><font style="font-size: 20px; font-weight: bold">请刷卡：</font>
					</span>
					<input id="cardNum" />
					<input type="button" onclick="getUser()" value="确定">
				</p>
				<input id="cardNum2" type="hidden" />
				<hr>
				<h3>
					领用详单
				</h3>
				<form action="store_lend.action" method="post"
					onsubmit="return validate();">
					<table border="1" style="border-collapse: collapse;">
						<tr>
							<td align="left">
								编号：
								<input type="text" name="vos.number" value="${store.number}"
									readonly="readonly" />
								<input type="hidden" name="vos.id" value="${store.id}" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								名称：
								<input type="text" name="vos.matetag" value="${store.matetag}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="left">
								数量：
								<input type="text" name="vos.num" id="num" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								单位：
								<input type="text" name="vos.unit" value="${store.unit}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="left">
								规格：
								<input type="text" name="vos.format" value="${store.format}"
									readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								类别：
								<input type="text" name="vos.category" value="${store.parClass}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="left">
								仓库：
								<input type="text" name="vos.storehouse"
									value="${store.storehouse}" readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								位置：
								<input type="text" name="vos.place" value="${store.place}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="left">
								日期：
								<input style="width: 155px" class="Wdate" type="text"
									name="vos.date" id="dateStr"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td style="width: 30px;"></td>
						</tr>
						<tr style="display: none;">
							<td align="left">
								卡号：
								<input type="text" id="cardId" name="vos.cardNum"
									readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								姓名：
								<input type="text" id="peopleName" name="vos.peopleName"
									readonly="readonly" />
							</td>
						</tr>
						<tr style="display: none;">
							<td align="left">
								部门：
								<input type="text" id="dept" name="vos.dept" readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="center">
								头像：
								<img src="" id="portrait" height='110px' width='80' />
							</td>
						</tr>
						<tr style="display: none;">
							<td align="left">
								权限：
								<input type="text" id="power" readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td></td>
						</tr>
						<tr style="display: none;">
							<td align="center" colspan="3">
								<input type="hidden" name="vos.formUrl" value="consuming" />
								<input type="submit" id="agree" value="领用"
									style="display: none;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var cardNumber = "";
function enter() {
	var keyCode = window.event.keyCode;
	if (keyCode < 48 || keyCode > 57) {
		if (keyCode == 13 && cardNumber.length == 10) {
			document.getElementById("cardNum").value = cardNumber;
			document.getElementById("cardNum2").value = cardNumber;
			cardNumber = "";
			getUser();
			return;
		} else {
			cardNumber = "";
			event.returnvalue = false;
			return;
		}
	}
	if (keyCode == 48) {
		keyCode = 0;
	} else if (keyCode == 49) {
		keyCode = 1;
	} else if (keyCode == 50) {
		keyCode = 2;
	} else if (keyCode == 51) {
		keyCode = 3;
	} else if (keyCode == 52) {
		keyCode = 4;
	} else if (keyCode == 53) {
		keyCode = 5;
	} else if (keyCode == 54) {
		keyCode = 6;
	} else if (keyCode == 55) {
		keyCode = 7;
	} else if (keyCode == 56) {
		keyCode = 8;
	} else if (keyCode == 57) {
		keyCode = 9;
	}
	cardNumber = cardNumber + keyCode;
}
</script>
	</body>
</html>
