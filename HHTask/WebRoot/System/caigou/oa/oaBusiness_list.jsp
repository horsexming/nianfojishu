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
							<span id="title">您正在查看同一物品的历史价格</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
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
					<a href="oaReimBursementAction!findBusinessList.action"
						style="color: #ffffff">报账明细</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<form
					action="oaReimBursementAction!findBusinessList.action?powerTag=${powerTag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								物品编号
							</th>
							<th>
								<select name="business.oaproductnumber" style="width: 130px;"
									id="oaproductnumber"
									onMouseOver="createDept('oaproductnumber','oaReimBursementAction!selectBusinessItem.action?tag=oaproductnumber&powerTag=${powerTag}')">
									<option value="">
										选择物品编号
									</option>
									<option value="${business.oaproductnumber}">
										${business.oaproductnumber}
									</option>
							</th>
							<th>
								物品名称
							</th>
							<th>
								<select name="business.oaproductName" style="width: 130px;"
									id="oaproductName"
									onMouseOver="createDept('oaproductName','oaReimBursementAction!selectBusinessItem.action?tag=oaproductName&powerTag=${powerTag}')">
									<option value="">
										选择物品名称
									</option>
									<option value="${business.oaproductName}">
										${business.oaproductName}
									</option>
								</select>
							</th>
							<th>
								状态
							</th>
							<th>
								<select name="business.oastatus" style="width: 130px;"
									id="oastatus"
									onMouseOver="createDept('oastatus','oaReimBursementAction!selectBusinessItem.action?tag=oastatus&powerTag=${powerTag}')">
									<option value="">
										选择状态
									</option>
									<option value="${business.oastatus}">
										${business.oastatus}
									</option>
								</select>
							</th>
							<th rowspan="3">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询" />

							</th>
						</tr>
						<tr>
							<th>
								发票号
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oainvoicenumber
									value="
									${business.oainvoicenumber }" size="80px" />
							</th>
							<th>
								规格
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oaspecification"
									value="${business.oaspecification}" size="80px" />
							</th>
							<th>
								报账人
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="business.oausername" value="${business.oausername}"
									size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								厂家
							</th>
							<th>
								<select name="business.oafactory" style="width: 130px;"
									id="oafactory"
									onMouseOver="createDept('oafactory','oaReimBursementAction!selectBusinessItem.action?tag=oafactory&powerTag=${powerTag}')">
									<option value="">
										选择厂家
									</option>
									<option value="${business.oafactory}">
										${business.oafactory}
									</option>
								</select>
							</th>
							<th>
								日期从
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
						</form>
						<form action="oaReimBursementAction!examLotBusiness.action"
					method="post"  style="margin: 0px">
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th>
								选择
								
								</th>
								<th align="center">
									序号
								</th>
								<th align="center">
									物品编号
								</th>
								<th align="center">
									物品名称
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									单价
								</th>
								<th align="center">
									总金额
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									负责人
								</th>
								<th align="center">
									厂家
								</th>
								<th align="center">
									发票号
								</th>
								<th align="center">
									报账日期
								</th>
								<th align="center">
									合同编号
								</th>
								<th align="center" style="width: 60px;">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="busi">
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
									<s:if test='"审核中".equals(#busi.oastatus)'>
									<input type="checkbox" id="${busi.oatotalMon}"
									name="ids" value="${busi.id}" 
									onclick="chageNum(this,'${busi.oatotalMon}')">
									</s:if>
									</td>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${busi.oaproductnumber}
									</td>
									<td>
										${busi.oaproductName}
									</td>
									<td>
										${busi.oaspecification}
									</td>
									<td>
										${busi.oaunit}
									</td>
									<td>
										${busi.oaquantity}
									</td>
									<td>
										${busi.oaunitprice}
									</td>
									<td>
										${busi.oatotalMon}
									</td>
									<td>
										${busi.oastatus}
									</td>
									<td>
										${busi.oausername}
									</td>
									<td>
										${busi.oafactory}
									</td>
									<td>
										${busi.oainvoicenumber}
									</td>
									<td>
										${busi.oadate}
									</td>
									<td>
										${busi.oahetongnumber}
									</td>
									<td>
										<s:if test="%{'manager'==powerTag}">
											<s:if test='"审核中".equals(#busi.oastatus)'>
												<a
													href="oaReimBursementAction!examBusiness.action?id=${id}&tag=kfk&powerTag=${powerTag}&cpage=${cpage}">可付款</a>
												<br />
												<a
													href="oaReimBursementAction!examBusiness.action?id=${id}&tag=kfkNG&powerTag=${powerTag}&cpage=${cpage}">打回</a>
												<br />
												<input type="button" id="" value="比价" onclick="bijia(${id})" />

											</s:if>
										</s:if>
										<s:elseif test="%{'shFD'==powerTag}">
											<s:if test='"可付款".equals(#busi.oastatus)'>
												<a
													href="oaReimBursementAction!examBusiness.action?id=${id}&tag=yfk&powerTag=${powerTag}&cpage=${cpage}">付款</a>
												<br />
												<a
													href="oaReimBursementAction!examBusiness.action?id=${id}&tag=yfkNG&powerTag=${powerTag}&cpage=${cpage}">打回</a>
												<br />
												<input type="button" id="" value="比价" onclick="bijia(${id})" />
											</s:if>
										</s:elseif>
										<s:elseif test="%{'shSD'==powerTag}">
											<s:if test='"打回".equals(#busi.oastatus)'>
											<a href="oaReimBursementAction!examBusiness.action?id=${id}&tag=resubmit&powerTag=${powerTag}">重新提交</a>
											</s:if>
										</s:elseif>

									</td>
									</tr>
								</s:iterator>
								<tr>
								<td align="right"
								style="font-weight: bold;width:20px;">
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</td>
									<td colspan="15" align="right">
									<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
										<font color="red">合计<label id="allMoney"></label>元</font>
										<s:if test="%{'manager'==powerTag}">
										<input id="ok"  align="top" type="submit" value="批量可付款" onclick="chageType(this,this.form)"/>
    									<input id="ng"  align="top" type="submit" value="批量驳回" onclick="chageType(this,this.form)" />
    									</s:if>
    									
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
									<td colspan="15" style="font-size: 15px; color: red;">
										对不起，没有查到相关的申报信息
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
	</body>
	<script type="text/javascript">
function bijia(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"oaReimBursementAction!findSameProductPrice.action?id=" + id);
	chageDiv('block');
}

function chageType(obj,form){
			var cpage=${cpage};
    		if(obj.id=="ok"){
    			form.action="oaReimBursementAction!examLotBusiness.action?tag=kfk&powerTag=manager&cpage="+cpage;
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="oaReimBursementAction!examLotBusiness.action?tag=kfkNG&powerTag=manager&cpge="+cpage;
    			form.submit();
    		}
    	}
var num = "${count}";
if (num == "") {
	num = 0;
}
 var money = 0;
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll") {
					chageNum(checkBox,checkBox.id);
				}
			}
		}
	}
}

function chageNum(obj,obj2) {
	
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		money+=parseFloat(obj2);
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
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		money=0;
		num = 0;
	} else {
		if (checkAll.checked == true) {
			checkAll.checked = false;
		}
		money=money-obj2;
		num--;
	}
	if(num == 0){
		money=0;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("allMoney").innerHTML=money;
}
</script>
</html>
