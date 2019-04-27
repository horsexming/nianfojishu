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
		<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
		src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
<script type="text/javascript">
	// 现在window.$和window.jQuery是3.2.1版本:
	console.log($().jquery); // => '3.2.1'
	var $jq = jQuery.noConflict(true);
	// 现在window.$和window.jQuery被恢复成1.5版本:
	console.log($().jquery); // => '1.5.0'

</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			
			
			<div align="center" style="margin-top:20px;">
				<p>
					<span><font style="font-size: 20px; font-weight: bold;">请刷卡：</font>
					</span>
					<input id="cardNum" type="text"/>
					<input type="button" onclick="clickCard()" value="确定">
				</p>
				<input id="cardNum2" type="hidden" />
				<hr>
				
				<h3>
					 库存领用详单
				</h3>

				<form action="" method="post"
					 id="submit">
					<table border="1" style="border-collapse: collapse;">
						<tr>
							<td align="left">
								<input type="hidden" name="good.goodsId" value="${good.goodsId}" />
								<input type="hidden" name="nect.goodsId" value="${good.goodsId}" />
								件号：
								<input type="text" name="nect.goodsMarkId" value="${good.goodsMarkId}"
									readonly="readonly" />
								
							</td>
							<td align="right">
								批次：
								<input type="text" name="nect.goodsLotId" value="${good.goodsLotId}"
									readonly="readonly" />
							</td>
							<td align="left">
								名称：
								<input type="text" name="nect.goodsFullName" value="${good.goodsFullName}"
									readonly="readonly" />
							</td>
							
						</tr>
						<tr>
							<td align="left">
								类别：
								<input type="text" name="nect.wgType" value="${good.wgType}"
									readonly="readonly" />
							</td>
							<td align="left">
								库别：
								<input type="text" name="nect.storehouse"
									value="${good.goodsClass}" readonly="readonly" />
							</td>
							<td align="left">
								仓区：
								<input type="text" name="nect.goodHouse"
									value="${good.goodHouseName}" readonly="readonly" />
							</td>
						</tr>
						
						<tr>
							<td align="left">
								数量：
								<input type="text" name="nect.num" id="num" /><br/>
								<span style="color:red;">最多可领${good.goodsCurQuantity}</span>
								<input type="hidden" name="maxNum" value="${good.goodsCurQuantity}" id="maxNum"  readonly="readonly"> 
							</td>
							<td align="left">
								单位：
								<input type="text" name="nect.unit" value="${good.goodsUnit}"
									readonly="readonly" />
							</td>
							<td align="left">
								规格：
								<input type="text" name="nect.format" value="${good.goodsFormat}"
									readonly="readonly" />
							</td>
	
							
						</tr>

						<tr>
							<td align="left" colspan="3">
								库位：
								<input type="text" name="nect.wareHouse"
									value="${good.goodsPosition}" readonly="readonly"  style="width:300px;"/>
							</td>
							
						</tr>
						<tr>
							<td align="left" colspan="3">
								备注：
								<input type="text" id="more" name="nect.remark" style="width:300px;" />
							</td>
							
						</tr>
						
						<tr style="display: none;">
							<td align="left">
								卡号：
								<input type="text" id="cardId" name="nect.cardNum"
									readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="right">
								姓名：
								<input type="text" id="peopleName" name="nect.peopleName"
									readonly="readonly" />
							</td>
						</tr>
						<tr style="display: none;">
							<td align="left">
								部门：
								<input type="text" id="dept" name="nect.dept" readonly="readonly" />
							</td>
							<td style="width: 30px;"></td>
							<td align="left">
								操作人：
								<input type="text" id="admin" readonly="readonly" name="nect.admin"/>
								<input type="hidden"  id="adminId" name="nect.adminId"/>
								<input type="hidden" name="subTag" value="nect"> 
								
								
							</td> 
						</tr>
					
						<tr style="display: none;">
							<td align="center" colspan="3">
								<input type="button" id="agree" value="领用" onclick="tijiao()"/>
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
		<div id="ajaxContent"></div>
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



<script type="text/javascript">
function getUser() {
	var cardNum = $('#cardNum').val();
	$.ajax( {
		url : "store!getUser.action",
		type : "post",
		dataType : 'json',
		data : {
			cardNum : cardNum
		},
		success : function(data) {
			if (data.success) {
				var map = data.data;
				var o = map.user;
				var src = "<%=basePath%>upload/user/" + o.picture;
				$('#cardId').val(o.cardId);
				$('#peopleName').val(o.name);
				$('#dept').val(o.dept);
				$('#portrait').attr('src', src);
				$('#power').val(o.power);
				if (o.power == "允许") {
					$('#agree').show();
				}
				$('tr:hidden').show();
			} else {
				$('tr:hidden').hide();
			}
		}
	});
}
var exp = /^\d+$/;
function validate() {
	var cardNumStr = document.getElementById("cardNum").value.trim();
	var num = document.getElementById("num").value;
	var numObj=document.getElementById("num");
	var numFlag=false;
	//var dateStr = document.getElementById("dateStr").value.trim();
	var maxNum=document.getElementById("maxNum").value;
	
	if (cardNumStr == "") {
		alert("请刷卡!");
		$("#cardNum").focus();
		$("#cardNum").val("");
		return false;
	}
	if (num == "") {
		alert("请输入数量!");
		$("#num").focus();
		$("#num").val("");
		return false;
	}
	numFlag=numyanzheng(numObj,null);
	if(!numFlag){
		return false;
	}
<%--	if (!exp.test(num)) {--%>
<%--		alert("请输入整数!");--%>
<%--		$("#num").focus();--%>
<%--		$("#num").val("");--%>
<%--		return false;--%>
<%--	}--%>

	if(num-maxNum>0){
		alert("领用数量不能大于库存量!");
		$("#num").focus();
		$("#num").val("");
		return false;
	}
<%--	if (dateStr == "") {--%>
<%--		alert("请选择时间");--%>
<%--		$("#dateStr").focus();--%>
<%--		$("#dateStr").val("");--%>
<%--		return false;--%>
<%--	}--%>
	return true;
}


function tijiao(){
	var bool=validate();
	var nectId=0;
	if(bool){
		$.ajax({
			type : "POST",
			url : "LendNectAction!lend.action",
			dataType : "json",
			data : $("#submit").serialize(),
			async : false,
			success : function(data) {
				if(data!=null){
					var str=data.split(",");
					if(str[0]){
						alert("领用成功，生成领用清单中!");
						nectId=str[1];
						window.location.href="LendNectAction!printNect.action?nectId="+nectId;
					}
				}
			},
<%--			error: function(XMLHttpRequest, textStatus, errorThrown) {--%>
<%--                alert("系统异常\n状态："+XMLHttpRequest.status+",状态值："+XMLHttpRequest.readyState+",异常信息:"+textStatus);--%>
<%--			}--%>
		});
	}
}

</script>


<script>
//卡号验证
function clickCard(){
	var cardNum=$("#cardNum").val().trim();
	if (cardNum=="") {
		alert("请刷卡!");
		return ;
	}
	$.ajax({
			url : "LendNectAction!ajaxGetUser.action",
			type : "post",
			dataType : 'json',
			data : {
				cardNum : cardNum,
				goodsId:${good.goodsId}
			},
			success : function(data) {
				if(data!=null){
					var user=data.data1;
					var loginUser=data.data2;
					var power=data.success;
					if(power){
						$('tr:hidden').show();
						$('#cardId').val(user.cardId);
						$('#peopleName').val(user.name);
						$('#dept').val(user.dept);
						$('#power').val(user.power);
						$('#admin').val(loginUser.name);
						$('#adminId').val(loginUser.id);
					}else{
						alert(data.msg);
						$("#cardNum").focus();
						$("#cardNum").val("");
						$('tr:hidden').hide();
					}
					
				}else{
					alert("卡号错误,请重输");
					$("#cardNum").focus();
					$("#cardNum").val("");
					$('tr:hidden').hide();
				}
			}
	});	
}

</script>

	</body>
</html>
