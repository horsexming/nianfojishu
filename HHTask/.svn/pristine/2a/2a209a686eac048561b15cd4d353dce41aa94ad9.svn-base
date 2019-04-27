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
				<form action="huikuanAction!querySell.action" method="post">
					<table width="900px">
						<tr>
							<td>
								件号：
								<s:textfield name="sell.sellMarkId" size="20" />
							</td>
							<td>
								品名：
								<s:textfield name="sell.sellGoods" size="20" />
							</td>
							<td>
								批次：
								<s:textfield name="sell.sellLot" size="20" />
							</td>
						</tr>
						<tr>
							<td>
								客户：
								<s:textfield name="sell.sellCompanyName" size="20" />
							</td>
							<td>
								开始：
								<input class="Wdate" type="text" name="startDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								截止：
								<input class="Wdate" type="text" name="endDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								汇款单号：
								<s:textfield name="sell.sellHkId" size="20" />
							</td>
							<td colspan="2">
								<s:submit value="查找" />
								&nbsp;
								<s:reset value="放弃" />
							</td>
						</tr>
					</table>
				</form>
				<form action="huikuanAction!getSelectSellResult.action"
					method="post"  style="margin: 0px">
					<table width="900px" class="table">

						<s:if test="%{tag=='view'}"></s:if>
						<s:else>
							<tr>
								<td align="right" colspan="8">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 条出库记录</font>
									<input type="submit" value="送货申请"
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
								件号
							</td>
							<td>
								批次
							</td>
							<td>
								品名
							</td>
							<td>
								客户
							</td>
							<td>
								数量
							</td>
							<td>
								出库日期
							</td>
							<s:if test="%{tag=='view'}"></s:if>
							<s:else>
								<td>
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</s:else>
						</tr>
						<s:iterator value="list" status="se" id="sell">
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
								<s:property value="sellMarkId" />
							</td>
							<td>
								<s:property value="sellLot" />
							</td>
							<td>
								<s:property value="sellGoods" />
							</td>
							<td>
								<s:property value="sellCompanyName" />
							</td>
							<td>
								<s:property value="sellCount" />
							</td>
							<td>
								<s:property value="sellDate" />
							</td>
							<s:if test="%{tag=='view'}"></s:if>
							<s:else>
								<td>
									<input type="checkbox" id="sell<s:property value="#se.index"/>"
										name="sellId" value="<s:property value='sellId'/>"
										onclick="chageNum(this)">
								</td>
							</s:else>
							</tr>
						</s:iterator>
						<s:if test="%{tag=='view'}"></s:if>
						<s:else>
							<tr>
								<td colspan="8" align="right"
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
								<td colspan="8" align="right">
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
								<td align="right" colspan="8">
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 条出库记录</font>
									<input type="submit" value="送货申请"
										style="width: 60px; height: 40px;" align="top">
									<br>
									<br>
								</td>
							</tr>
						</s:else>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
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
</script>
	</body>
</html>
