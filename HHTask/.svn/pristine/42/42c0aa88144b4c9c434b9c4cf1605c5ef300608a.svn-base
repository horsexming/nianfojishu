<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function send(obj) {
	var value = encodeURI(obj.value);//对strValue进行编码
	alert(value);
	sendRequest(
			"OrderManagementAction!findlianxiren.action?lianxiren=" + value,
			messageResponse);
}
// 联系人查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var lianxidianh = document.getElementById("lianxidianh");//联系人电话
			var kehuname = document.getElementById("kehuname");//客户名称
			var lianxiren = document.getElementById("lianxiren");//联系人
			if (message == "") {
				lianxidianh.value = "";
				kehuname.value = "";
				lianxiren.focus();
				lianxiren.select();
				lianxiren.title = "该联系人不存在!";
				lianxiren.style.border = " solid 1px red";
				return;
			} else {
				var value = message.split("|");
				lianxiren.title = "该联系人存在!";
				lianxidianh.value = value[0];
				kehuname.value = value[1];
				lianxiren.style.border = " solid 1px #0f0f1f";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
//表单验证
function check() {
	var lianxiren = document.getElementById("lianxiren");
	var gaiyao = document.getElementById("gaiyao");
	var count = document.getElementById("count");
	var danjia = document.getElementById("danjia");
	var yufumoney = document.getElementById("yufumoney");
	var fzr = document.getElementById("fzr");
	if (lianxiren.value == "") {
		alert("联系人不能为空");
		lianxiren.focus();
		return false;
	} else if (gaiyao.value == "") {
		alert("概要不能为空");
		gaiyao.focus();
		return false;
	} else if (count.value == "") {
		alert("数量不能为空");
		count.focus();
		return false;
	} else if (danjia.value == "") {
		alert("单价不能为空");
		danjia.focus();
		return false;
	} else if (yufumoney.value == "") {
		alert("预付金额不能为空");
		yufumoney.focus();
		return false;
	} else if (fzr.value == "") {
		alert("第一阶段负责人不能为空");
		fzr.focus();
		return false;
	} else {
		return true;
	}
}
//只能输入数字和小数点
function clearNoNum(obj) {
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g, "");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g, "");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}
//获得当前时间
function fangfa() {
	var week;
	var now = new Date();
	var x = now.getDay();
	var n = now.getFullYear();
	var y = now.getMonth() + 1;
	var r = now.getDate();
	var s = now.getHours();
	var f = now.getMinutes();
	var m = now.getSeconds();
	document.getElementById("time").value = n + "-" + y + "-" + r + " " + s
			+ ":" + f + ":" + m;
	setTimeout("fangfa()", 1000);
}
</script>
	</head>
	<body bgcolor="#ffffff" onLoad="fangfa()">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
						<a href="FunctionAction!findFunctionById.action?id=${function.id}"
							style="color: #ffffff">添加功能</a>
					</div>
				</div>
				
				<div align="center">
					<form action="OrderManagementAction.action" method="post"
						onsubmit="return check()">
						<table>
							<tr>
								<th colspan="6">
									<font size="5">添加实制订单</font>
								</th>
							</tr>
							<s:if test="str==123">
								<tr>
									<td align="center" colspan="6">
										<font color="red">编号： ${number} 添加成功</font>
									</td>
								</tr>
							</s:if>
							<tr>
								<th>
									联系人
								</th>
								<td>
									<input type="text" name="orderManagement.ordername"
										onblur="send(this)" id="lianxiren" />
								</td>
								<th>
									联系人电话
								</th>
								<td>
									<input type="text" name="orderManagement.ordermobilenumber"
										id="lianxidianh" title="只读" />
								</td>
								<th>
									客户名称
								</th>
								<td>
									<input type="text" name="orderManagement.ordercompanyname"
										id="kehuname" title="只读" />
								</td>
							</tr>
							<tr>
								<th>
									编号
								</th>
								<td>
									<input type="text" readonly="readonly"
										name="orderManagement.ordernumber" value="${dingdannumber}"
										title="只读" />
								</td>
								<th>
									概要(需要什么)
								</th>
								<td colspan="3">
									<input style="width: 300px;" id="gaiyao" type="text"
										name="orderManagement.ordersummary" />
								</td>
							</tr>
							<tr>
								<th>
									数量
								</th>
								<td>
									<input type="text" name="orderManagement.orderquantity"
										onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false"
										onkeyup="onbul()" id="count" />
								</td>
								<th>
									单价(元)
								</th>
								<td>
									<input type="text" name="orderManagement.orderunitprice"
										onkeyup="onbul(),clearNoNum(this)" id="danjia" />
								</td>
								<th>
									实际金额(元)
								</th>
								<td>
									<input type="text" name="orderManagement.orderactualmoney"
										id="money" />
									<!-- 算出实际金额 -->
									<script type="text/javascript">
function onbul() {
	var count = document.getElementById("count").value; //数量
	var danjia = document.getElementById("danjia").value;//单价
	if (count != "" && danjia != "") {
		var sumMoney1 = parseFloat(count) * parseFloat(danjia);
		document.getElementById("money").value = sumMoney1;
	} else {
		document.getElementById("money").value = "";
	}
}
</script>
								</td>
							</tr>
							<tr>
								<th>
									预付金额
								</th>
								<td>
									<input type="text" id="yufumoney"
										name="orderManagement.orderPrepaidmoney" />
								</td>
								<th>
									创建人
								</th>
								<td>
									<input type="text" name="orderManagement.ordercreatePeople"
										readonly="readonly" value="${username}" title="只读" />
								</td>
								<th>
									创建时间
								</th>
								<td>
									<input readonly="readonly" type="text"
										name="orderManagement.ordercreatedatatime" id="time"
										title="只读" />
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="5">
									<textarea rows="" name="orderManagement.orderremarks"
										style="width: 300px; height: 80px;" cols=""></textarea>
								</td>
							</tr>
							<tr>
								<th>
									第一阶段负责人
								</th>
								<td colspan="5">
									<select name="orderManagement.orderpersoncharge" id="fzr">
										<option value=""></option>
										<s:iterator id="gongyi" value="gongyilist">
											<option value="${gongyi}">
												${gongyi}
											</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="6">
									<input type="submit" value="确   定" />
									&nbsp;&nbsp;
									<input type="reset" value="取   消" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
