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
				<div>
					<form action="AccessEquipmentAction_findPriceByCondition.action"
						method="post" style="margin: 0px">
						<br>
						<table class="table">
							<tr>
							<td align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;号:
								<br />
								(Item Number)
								<input type="hidden" name="accessWebcam.id" value="${accessWebcam.id}">
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.partNumber">
							</td>
							<td align="right">
								名&nbsp;&nbsp;称:
								<br />
								(Name)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.name">
							</td>
							<td align="right">
								产品类别:
								<br />
								(Product Type)
							</td>
							<td>
								<select style="width: 150px;" name="price.productCategory">
									<option></option>
									<option>
										总成
									</option>
									<option>
										组件
									</option>
									<option>
										零件
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								价格(含税):
								<br>
								(Price (including tax))
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.hsPrice">
							</td>
							<td align="right">
								价格(不含税):
								<br>
								(Price (excluding tax))
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.bhsPrice">
							</td>
							<td align="right">
								生产类型:
								<br>
								(Production Type)
							</td>
							<td>
								<select style="width: 150px;" name="price.produceType">
									<option></option>
									<option value="销售">
										销售
									</option>
									<option value="外委">
										外委
									</option>
									<option value="外购">
										外购
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="price.contractNumber">
							</td>
							<td align="right">
								签订方
								<br>
								(The Signing Party)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.type">
							</td>
							<td align="right">
								档案号
								<br>
								(file Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.fileNumber">
							</td>
						</tr>
						<tr>
							<td align="right">
								价格有效期截止时间
								<br>
								(Price valid end time)
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="price.pricePeriodEnd"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
							<td colspan="4" rowspan="2" align="center">
								<input type="submit" value="查询select"
									style="width: 100px; height: 32px">
								<input type="reset" value="重置 submit"
									style="width: 100px; height: 32px">
							</td>
						</tr>
						</table>
					</form>
					<form action="AccessEquipmentAction_binDingPrice.action"
						method="post" style="margin: 0px">
						<input type="hidden" name="accessWebcam.id"
							value="${accessWebcam.id}">
						<table class="table" align="center">
							<tr>
								<td align="right" colspan="10">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 档案</font>
									<input type="submit" value="绑定" id="bangding1"
										style="width: 60px; height: 40px;" align="top">
									<br>
									<br>
								</td>
							</tr>
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<td>
									序号
								</td>
								<td>
									件号
								<br>
								(Item Number)
								</td>
								<td>
									名称
								<br>
								(Name)
								</td>
								<td>
									签订方
								<br>
								(The Signing Party)
								</td>
								<td>
									价格(含税)
								<br>
								(Price (including tax))
								</td>
								<td>
									价 格 (不含税)
								<br>
								(Price (excluding tax))
								</td>
								<td>
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								档案编号
								<br>
								(file Number)
							</td>
							<td>
								价格有效期
								<br>
								(Price valid)
							</td>
								<td>
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>

							<s:iterator id="pageprice" value="bangpriceList"
								status="ststusfunction">
								<s:if test="#ststusfunction.first">
									<tr bgcolor="green">
										<td colspan="10" style="font-family: 微软雅黑; font-weight: bold;"
											align="center">
											<font color="#ffffff"> 已绑定档案</font>
										</td>
									</tr>
								</s:if>
								<s:if test="#ststusfunction.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#ststusfunction.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#ststusfunction.index+1" />
									</font>
								</td>
								<td>
									${pageprice.partNumber}
								</td>
								<td>
									${pageprice.name}
								</td>
								<td>
									${pageprice.type}
								</td>
								<td>
									${pageprice.hsPrice}
								</td>
								<td>
									${pageprice.bhsPrice}
								</td>
								<td>
									${pageprice.contractNumber}
								</td>
								<td>
									${pageprice.fileNumber}
								</td>
								<td>
									${pageprice.pricePeriodEnd}
								</td>
								<td>
									<input type="checkbox"
										id="price<s:property value="#ststusfunction.index"/>"
										name="priceId" value="${pageprice.id}" onclick="chageNum(this)"
										checked="checked">
								</td>
								<s:if test="#ststusfunction.last">
									<tr bgcolor="green">
										<td colspan="10" style="font-family: 微软雅黑; font-weight: bold;"
											align="center">
											<font color="#ffffff"> 未绑定档案</font>
										</td>
									</tr>
								</s:if>
							</s:iterator>

							<s:iterator id="pageprice" value="priceList" status="ststusfunction">
								<s:if test="#ststusfunction.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#ststusfunction.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#ststusfunction.index+1" />
									</font>
								</td>
								<td>
									${pageprice.partNumber}
								</td>
								<td>
									${pageprice.name}
								</td>
								<td>
									${pageprice.type}
								</td>
								<td>
									${pageprice.hsPrice}
								</td>
								<td>
									${pageprice.bhsPrice}
								</td>
								<td>
									${pageprice.contractNumber}
								</td>
								<td>
									${pageprice.fileNumber}
								</td>
								<td>
									${pageprice.pricePeriodEnd}
								</td>
								<td>
									<input type="checkbox"
										id="price<s:property value="#ststusfunction.index"/>"
										name="priceId" value="${pageprice.id}" onclick="chageNum(this)">
								</td>
							</s:iterator>
							<tr>
								<td colspan="10" align="right"
									style="font-weight: bold; padding-right: 40px">
									<input type="checkbox" id="checkAll2"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="10" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="10" align="center" style="color: red">
										${errorMessage}
									</td>
								</s:else>
							</tr>
							<tr>
								<td align="right" colspan="10">
									<br>
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label>档案</font>
									<input type="submit" value="绑定" id="bangding2"
										style="width: 60px; height: 40px;" align="top">
									<br>
									<br>
									<br>
									<br>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
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
					chageNum(checkBox, checkBox.id);
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
	//var maxNum = ;
	if(num > '${accessWebcam.maxNum}'){
		$("#bangding1").attr("disabled", "disabled");
		$("#bangding2").attr("disabled", "disabled");
	}else{
		$("#bangding1").removeAttr("disabled");
		$("#bangding2").removeAttr("disabled");
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
	document.getElementById("propleText").value = num;
}
</script>
	</body>
</html>
