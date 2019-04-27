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
		<title>选择开票申请的出库清单</title>
		<%@include file="/util/sonHead.jsp"%>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
body {
	text-align: center;
}
</style>
	</head>
	<body style="text-align: center; background-color: #ffffff">
		<form action="huikuanAction!querySta.action" method="post">
			<table width="90%" class="table">
				<tr>
					<th>
						客户：
						<s:textfield name="tahkSellSta.hkSellCumpanyName" size="20" />
					</th>
					<th>
						零件号：
						<s:textfield name="tahkSellSta.hkSellMarkId" size="20" />
					</th>

				</tr>
				<tr>
					<th>
						品名：
						<s:textfield name="tahkSellSta.hkSellGoods" size="20" />
					</th>
					<th>
						送货单号：
						<input type="text" name="tahkSellSta.hkSellSendId" size="20" />
					</th>
				</tr>
				<tr>
					<th colspan="2">
						<s:submit value="查找" cssStyle="width:120px;" />
						&nbsp;
						<s:reset value="放弃" cssStyle="width:80px;" />
					</th>
				</tr>
			</table>
		</form>
		<form action="huikuanAction!selectSta.action" method="post">
			<table width="98%" class="table">
				<s:if test="%{tag=='view'}"></s:if>
				<s:else>
					<tr>
						<td align="right" colspan="8">
							<a href="huikuanAction!manageShopping.action">任务栏处理</a>
							<font color="red">共选择 <label id="peopleLabel">
									${count}
								</label> <input type="hidden" id="propleText" name="peopleNum"
									style="width: 20px;" readonly="readonly"> 条送货记录</font>
							<input type="submit" value="开票申请"
								style="width: 60px; height: 40px;" align="top">
							<br>
							<br>
						</td>
					</tr>
				</s:else>
				<tr align="center" bgcolor="#c0dcf2"
					style="height: 40px; font-weight: bold;">
					<td>
						序号
					</td>
					<td>
						客户
					</td>
					<td>
						件号
					</td>
					<td>
						品名
					</td>
					<td>
						送货单号
					</td>
					<td>
						数量
					</td>
					<s:if test="%{tag=='view'}"></s:if>
					<s:else>
						<td>
							<input type="checkbox" id="checkAll"
								onclick="chageAllCheck(this)">
							全选
						</td>
					</s:else>
					<td>
						操作
					</td>
				</tr>
				<s:iterator value="listHkSellSta" status="se" id="sta">
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
						<s:property value="hkSellCumpanyName" />
					</td>
					<td>
						<s:property value="hkSellMarkId" />
					</td>
					<td>
						<s:property value="hkSellGoods" />
					</td>
					<td>
						<s:property value="hkSellSendId" />
					</td>
					<td>
						<s:property value="hkSellCount" />
					</td>
					<s:if test="%{tag=='view'}"></s:if>
					<s:else>
						<td>
							<input type="checkbox" id="sell<s:property value="#se.index"/>"
								name="sellId" value="<s:property value='id'/>"
								onclick="chageNum(this)">
						</td>
						<td>
							<img src="<%=basePath%>images/saveShopp.jpg"
								onclick="saveShopping('huikuanAction!saveShopping.action?id='+<s:property value='id'/>)"
								width="120px" height="40px">
							&nbsp;
							<a onClick="return confirm('确定要删除该条记录吗？')"
								href="huikuanAction!deleteSta.action?cpage=<s:property value='cpage'/>&id=<s:property value='id'/>">删除</a>
						</td>
					</s:else>
					<!-- huikuanAction!saveShopping.action?id=<s:property value='id'/>
    	'huikuanAction!saveShopping.action?id='+<s:property value='id'/>
    	 -->

					</tr>
				</s:iterator>
				<s:if test="%{tag=='view'}"></s:if>
				<s:else>
					<tr>
						<td colspan="9" align="right"
							style="font-weight: bold; padding-right: 40px">
							<input type="checkbox" id="checkAll2"
								onclick="chageAllCheck(this)">
							全选
						</td>
					</tr>
				</s:else>
				<s:if test="%{tag=='view'}">
					<tr>
						<td colspan="7" align="right">
				</s:if>
				<s:else>
					<tr>
						<td colspan="9" align="right">
				</s:else>
				共
				<s:property value="total" />
				页 第
				<s:property value="cpage" />
				页
				<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
					styleClass="page" theme="number" />

				</td>
				</tr>
				<s:if test="%{tag=='view'}"></s:if>
				<s:else>
					<tr>
						<td align="right" colspan="9">
							<font color="red">共选择 <label id="peopleLabel2">
									${count}
								</label> <input type="hidden" id="propleText" name="peopleNum"
									style="width: 20px;" readonly="readonly"> 条送货记录</font>
							<input type="submit" value="开票申请"
								style="width: 60px; height: 40px;" align="top">
							<br>
							<br>
						</td>
					</tr>
				</s:else>
			</table>
		</form>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">

var oldObj;
var oldObj2;
function chageModule(obj, obj2) {
	if (obj.id != "module1") {
		document.getElementById("module1").className = "tag_1";
		document.getElementById("module1_1").style.display = "none";
	}
	if (oldObj != null) {
		oldObj.className = "tag_1";
		document.getElementById("module1_" + oldObj2).style.display = "none";
	}

	obj.className = "tag_2";
	document.getElementById("module1_" + obj2).style.display = "block";

	oldObj = obj;
	oldObj2 = obj2;
}
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox);
				}
			}
		}
	}
}
var num = "${count}";
if (num == "") {
	num = 0;
}
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
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
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
}
//添加到任务栏
//function saveShopping2(obj){
//	var id=obj;
//	alert(id);

//}

//为select赋值(返回信息为包含|的字符串)
function saveShoppingStatus() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			alert(message);
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
//处理回款任务栏功能
function saveShopping(actionUrl) { //
	//alert("=======");
	sendRequest(actionUrl, saveShoppingStatus);
}

var XMLHttpReq;
//创建XMLHttpRequest对象 
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {//Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
}
//发送请求函数 (url=请求地址 ,obj=指定响应函数)
function sendRequest(url, obj) {
	createXMLHttpRequest();
	url = encodeURI(encodeURI(url));
	XMLHttpReq.open("post", url, true);
	XMLHttpReq.onreadystatechange = obj;//指定响应函数
	XMLHttpReq.send(null);// 发送请求
}
</script>
</html>
