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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form
					action="productPriceAction!queryProductPrice.action?messagePower=${messagePower}"
					method="post">
					<table>
						<tr>
							<td>
								总成件号：
								<input type="text" name="productPrice.markId" />
							</td>
							<td>
								产品名称：
								<input type="text" name="productPrice.goodsName" />
							</td>
							<td rowspan="2">
								<input type="submit" value="查找" />
						</tr>
						<tr>
							<td>
								产品型别：
								<input type="text" name="productPrice.style" />
							</td>
							<td>
								产品车型：
								<input type="text" name="productPrice.carStyle" />
							</td>
						</tr>
					</table>
				</form>
				<br>
				<form
					action="productPriceAction!shizhi.action?messagePower=${messagePower}&cpage=${cpage}"
					method="post">
					<table width="95%" class="table">
						<tr style="height: 20px; font-weight: bold;">
							<td colspan="2" align="center">
								提奖试算条件输入
							</td>
						</tr>
						<tr align="center" style="height: 20px; font-weight: bold;">
							<td>
								起始时间：
								<input class="Wdate" type="text" name="startDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<td>
								截止时间：
								<input class="Wdate" type="text" name="endDate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>

					<table width="95%" class="table">

						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</td>

							<td>
								序号
							</td>
							<td>
								总成件号
							</td>
							<td>
								产品名称
							</td>
							<td>
								直接人工费
							</td>
							<td>
								可调系数
							</td>

							<td>
								入库量
							</td>
							<td>
								型别
							</td>
							<td>
								车型
							</td>
							<td>
								提奖单价
							</td>
							<td>
								操作
							</td>

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
								<input type="checkbox"
									id="productPriceId<s:property value="#se.index"/>"
									name="productPriceId" value="<s:property value='id'/>"
									onclick="chageNum(this)">
							</td>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								<s:property value="markId" />
							</td>
							<td>
								<s:property value="goodsName" />
							</td>

							<td>
								<s:property value="laborcost" />
							</td>
							<td>
								<s:property value="fenpeiRate" />
							</td>

							<td>
								<s:property value="dailyoutput" />
							</td>
							<td>
								<s:property value="style" />
							</td>
							<td>
								<s:property value="carStyle" />
							</td>
							<td>
								<s:property value="onePrice" />
							</td>
							<td>
								<a
									href="productPriceAction!getOneProductPrice.action?id=<s:property value='id' />&tag=querySpareParts">查看组件信息</a>

							</td>


							</tr>
						</s:iterator>


						<tr>
							<td colspan="11" align="right">
								<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条总成产品</font>
								<input type="submit" value="提奖"
									style="width: 150px; height: 40px;" align="top">

								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>

					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll") {
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
	//var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			//checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true) {
			checkAll.checked = false;
			//checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	//document.getElementById("peopleLabel2").innerHTML = num;
}
</script>
	</body>
</html>
