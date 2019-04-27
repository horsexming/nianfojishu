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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">添加总成信息</span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">

				</div>
				<div id="addProductPrice"
					style="background-color: #ffffff; width: 100%;">
					<form
						action="productPriceAction!saveProductPrice.action?messagePower=manager"
						method="post">
						<table>
							<tr>
								<td align="right">
									总成件号：
								</td>
								<td>
									<input type="text" name="productPrice.markId" />
								</td>
								<td align="right">
									产品名称：
								</td>
								<td>
									<input type="text" name="productPrice.goodsName" />
								</td>
							</tr>
							<tr>
								<td align="right">
									直接人工：
								</td>
								<td>
									<input type="text" name="productPrice.laborcost" />
								</td>
								<td align="right">
									可调系数：
								</td>
								<td>
									<input type="text" name="productPrice.fenpeiRate" />
								</td>

							</tr>
							<tr>
								<td align="right">
									入库量：
								</td>
								<td>
									<input type="text" name="productPrice.Dailyoutput" />
								</td>
								<td align="right">
									产品型别：
								</td>
								<td>
									<input type="text" name="productPrice.style" />
								</td>

							</tr>
							<tr>
								<td align="right">
									车型：
								</td>
								<td>
									<input type="text" name="productPrice.carStyle" />
								</td>
								<td align="right">
									备注：
								</td>
								<td>
									<input type="text" name="productPrice.more" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="添加" />
									&nbsp;
									<input type="reset" value="取消">

								</td>
							</tr>
						</table>
					</form>

				</div>
			</div>
		</div>
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
								<input type="button" onclick="chageDiv('block');" value="添加总成信息" />
							</td>
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
					action="productPriceAction!jisuanDanjianBonus.action?messagePower=${messagePower}&cpage=${cpage}"
					method="post">
					<table width="95%" class="table">

						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<s:if test="%{messagePower=='manager'}">
								<td>
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</s:if>
							<td>
								序号
							</td>
							<td>
								总成件号
							</td>
							<td>
								产品名称
							</td>
							<s:if test="%{messagePower=='manager'}">
								<td>
									直接人工费
								</td>
								<td>
									可调系数
								</td>
							</s:if>

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
							<s:if test="%{messagePower=='manager'}">
								<td>
									<input type="checkbox"
										id="productPriceId<s:property value="#se.index"/>"
										name="productPriceId" value="<s:property value='id'/>"
										onclick="chageNum(this)">
								</td>
							</s:if>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								<s:property value="markId" />
							</td>
							<td>
								<s:property value="goodsName" />
							</td>
							<s:if test="%{messagePower=='manager'}">
								<td>
									<s:property value="laborcost" />
								</td>
								<td>
									<s:property value="fenpeiRate" />
								</td>
							</s:if>
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
								<a
									href="System/renshi/tiPrice/interiorMentioningAwardPrice.jsp?id=<s:property value='id' />"
									target="tt">试算</a>
								<s:if test="%{messagePower=='manager'}">
									<a
										href="productPriceAction!getOneProductPrice.action?id=<s:property value='id' />&tag=update&messagePower=manager">修改</a>
									<a onClick="return confirm('确定要删除该条记录吗？')"
										href="productPriceAction!getOneProductPrice.action?id=<s:property value='id' />&tag=delete&cpage=<s:property value='cpage' />&messagePower=manager">删除</a>
								</s:if>
							</td>


							</tr>
						</s:iterator>

						<s:if test="%{messagePower=='manager'}">
							<tr>
								<td colspan="11" align="right">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 条总成产品</font>
									<input type="submit" value="计算提奖单价和工序分配额"
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
						</s:if>
						<s:else>
							<tr>
								<td colspan="8" align="right">
									共
									<s:property value="total" />
									页 第
									<s:property value="cpage" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

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
