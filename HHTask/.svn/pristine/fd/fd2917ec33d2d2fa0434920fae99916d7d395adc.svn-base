<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h3>
					${companyInfo.name}费用报销单
				</h3>
				<!-- BaoXiaoDanAction!saveBaoXiaoDan.action -->
				<form action=" BaoXiaoDanAction!updateBaoXiaoDan1.action?tag=<s:property value="tag"/>" method="post"
					onsubmit="return checkForm()">
					<input type="hidden" name="baoxiaodan.id" value="${baoxiaodan.id}">
					<input type="hidden" name="baoxiaodan.dept" value="${baoxiaodan.dept}">
					<input type="hidden" name="baoxiaodan.code" value="${baoxiaodan.code}">
					<input type="hidden" name="baoxiaodan.saveTime" value="${baoxiaodan.saveTime}">
					<input type="hidden" name="baoxiaodan.baoxiaoBarcode" value="${baoxiaodan.baoxiaoBarcode}">
					<input type="hidden" name="baoxiaodan.status" value="${baoxiaodan.status}">
					<input type="hidden" name="baoxiaodan.producestatus" value="${baoxiaodan.producestatus}">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th colspan="2">
									收款单位(个人)
								</th>
							</s:if>
							<s:else>
							<th>
									收款单位(个人)
								</th>
							</s:else>
								
								<td>
									<input type="text" id="shoukuanRen"
										name="baoxiaodan.shoukuanRen"  value="${baoxiaodan.shoukuanRen}"/>
								</td>
								<th>
									合同号
								</th>
								<td>
									<input type="text"  name="baoxiaodan.contract_Number"  value="${baoxiaodan.contract_Number}"/>
								</td>
							</tr>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th colspan="2">
									付款方式
								</th>
							</s:if>
							<s:else>
							<th>
									付款方式
								</th>
							</s:else>
								
								<td>
									<select name="baoxiaodan.payStyle" id="payStyle" onchange="paychange()">
										<option value="${baoxiaodan.payStyle}">
											${baoxiaodan.payStyle}
										</option>
										<option value="现金">
											现金
										</option>
										<option value="银行对公转账">
											银行对公转账
										</option>
										<option value="归还借款">
											归还借款
										</option>
										<option value="冲账">
											冲账
										</option>
										<option value="其他">
											其他
										</option>
									</select>
									<select id="payvoucher" style="display: none;" name="money2">
										<option value=""></option>
									</select>
								</td>
								<th>
									订单号
								</th>
								<td>
									<input type="text"  name="baoxiaodan.order_number"  value="${baoxiaodan.order_number}"/>
								</td>
							</tr>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th colspan="2">
									附件张数
								</th>
							</s:if>
							<s:else>
							<th>
									附件张数
								</th>
							</s:else>
								
								<td>
									<select name="baoxiaodan.attachmentsCount">
										<option value="${baoxiaodan.attachmentsCount}">
											${baoxiaodan.attachmentsCount}
										</option>
										<%
											for (int i = 0; i < 301; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>

									</select>
								</td>

								<th>
									报销日期
								</th>
								<td>
									<input class="Wdate" type="text" name="baoxiaodan.baoxiaoDate"
										size="15" id="baoxiaoDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  value="${baoxiaodan.baoxiaoDate}"/>
								</td>
							</tr>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th colspan="2">
									发票类型
								</th>
							</s:if>
							<s:else>
							<th>
									发票类型
								</th>
							</s:else>
								
								<td>
									<select name="baoxiaodan.isTax">
							<s:if test="%{'增值税发票'==baoxiaodan.isTax}">
							<option value="增值税发票">增值税发票</option>
							<option value="普通发票">普通发票</option>
							</s:if>
							<s:else>
							<option value="普通发票" >普通发票</option>
							<option value="增值税发票">增值税发票</option>
							
							</s:else>
							
							</select>
								</td>
								<th>发票号码</th><td><input size="100px" type="text" id="invoiceNum"
										name="baoxiaodan.invoiceNum" value="${baoxiaodan.invoiceNum}"/>(注：多张发票逗号分开)</td>
							</tr>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th width="100px"  colspan="2">
									说明
								</th>
							</s:if>
							<s:else>
							<th width="100px">
									说明
								</th>
							</s:else>
								<td colspan="3">
									<textarea cols="88" name="baoxiaodan.explain" id="explain">${baoxiaodan.explain}</textarea>
								</td>
							</tr>
							<tr>
							<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
							<th colspan="5">
									报销明细
								</th>
							</s:if>
							<s:else>
							<th colspan="4">
									报销明细
								</th>
							</s:else>
								
							</tr>
							<s:if test="baoxiaodan.producestatus=='其他'">
							<tr>
								<th align="center">
									件号
								</th>
								<th>
									发票号
								</th>
								<th>
									内容
								</th>
								<th>
									报销金额
								</th>
								</tr>
							</s:if>
							<s:elseif test="baoxiaodan.producestatus=='外购'">
							<tr>
								<th align="center">
									件号
								</th>
								<th>
									零件名称
								</th>
								<th>
									批次
								</th>
								<th>
									数量
								</th>
								<th>
									金额
								</th>
								</tr>
							</s:elseif>
							<s:elseif test="baoxiaodan.producestatus=='外委'">
							<tr>
								<th align="center">
									件号
								</th>
								<th>
									工序号
								</th>
								<th>
									批次
								</th>
								<th>
									 数量
								</th>
								<th>
									 金额
								</th>
								</tr>
							</s:elseif>
							<s:else>
							<tr>
								<th>
									件号
								</th>
								<th>
									批次
								</th>
								<th>
									数量
								</th>
								<th>
									报销金额
								</th>
							</tr>
							</s:else>
								<s:iterator value="baoxiaodan.getBaoxiaoDetail()" status="se" id="bxd">
								<s:if test="baoxiaodan.producestatus=='其他'">
								<tr align="center" height="35px">
								<th align="center">
									<input id="course${se.index}" name="listDetail[${se.index}].goodsStoreMarkId" type="text" value="${bxd.goodsStoreMarkId}">
								</th>
								<th align="center">
									<input type="text" name="listDetail[${se.index}].invoiceNo" value="${bxd.invoiceNo}">
									<input type="hidden" id="count_id${se.index}" name="listDetail[${se.index}].goodsStoreId" />
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[${se.index}].content"  value="${bxd.content}"/>
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine()" id="h${se.index}"
										name="listDetail[${se.index}].money"  value="${bxd.money}"/>
								</th>
							</tr>
								</s:if>
								<s:elseif test="baoxiaodan.producestatus=='外购'">
								<tr align="center" height="35px">
								<th align="center">
									<input id="course${se.index}" name="listDetail[${se.index}].goodsStoreMarkId" type="text" value="${bxd.goodsStoreMarkId}">
								</th>
								<th align="center">
									<input type="text" name="listDetail[${se.index}].partName" value="${bxd.partName}">
									<input type="hidden" id="count_id${se.index}" name="listDetail[${se.index}].goodsStoreId" />
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[${se.index}].goodsStoreLot"  value="${bxd.goodsStoreLot}"/>
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[${se.index}].goodsStoreCount"  value="${bxd.goodsStoreCount}"/>
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine()" id="h${se.index}"
										name="listDetail[${se.index}].money"  value="${bxd.money}"/>
								</th>
							</tr>
								</s:elseif>
								<s:elseif test="baoxiaodan.producestatus=='外委'">
								<tr align="center" height="35px">
								<th align="center">
									<input id="course${se.index}" name="listDetail[${se.index}].goodsStoreMarkId" type="text" value="${bxd.goodsStoreMarkId}">
								</th>
								<th align="center">
									<input id="course${se.index}" name="listDetail[${se.index}].processesNo" type="text" value="${bxd.processesNo}">
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[${se.index}].goodsStoreLot"  value="${bxd.goodsStoreLot}"/>
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[${se.index}].goodsStoreCount"  value="${bxd.goodsStoreCount}"/>
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine()" id="h${se.index}"
										name="listDetail[${se.index}].money"  value="${bxd.money}"/>
								</th>
							</tr>
								</s:elseif>
								<s:else>
								<tr align="left">
								<th>
									<select name="listDetail[${se.index}].goodsStoreMarkId" style="width: 80px;"
										onMouseOver="selectDept(${se.index})" onChange="selectSubjects(${se.index})"
										id="course${se.index}">
										<option value="${bxd.goodsStoreMarkId}">
											${bxd.goodsStoreMarkId}
										</option>
									</select>
								</th>
								<th align="center">
								<input type="hidden" name="listDetail[${se.index}].id"
											value="${bxd.id }" />
									<!-- onMouseOver="selectSubjects(0)"  -->
									<select name="listDetail[${se.index}].goodsStoreLot" style="width: 180px;" onchange="selectSubjects1(${se.index})"
										id="subject${se.index}" >
										<option value="${bxd.goodsStoreLot}">
											${bxd.goodsStoreLot}
										</option>
									</select>
								</th>
								<th>
									<input type="text" id="count${se.index}" name="listDetail[${se.index}].goodsStoreCount"  value="${bxd.goodsStoreCount}" readonly="readonly" />
									<input type="hidden" id="count_id${se.index}" name="listDetail[${se.index}].goodsStoreId" />
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine();" id="h${se.index}"
										name="listDetail[${se.index}].money"  value="${bxd.money}"/>
								</th>
							</tr>
								</s:else>
<%--							<input type="hidden" id="hid${se.index}" value="${bxd.money}" />--%>
							</s:iterator>
							<tr id="uploadtr">
								<tr>
						<s:if test="baoxiaodan.producestatus!='外委'&&baoxiaodan.producestatus!='外购'">
						<th align="left">
										<input type="button" id="inforButton_1"
											onclick="saveHKInfor(this,1)" value="添加明细" />
									</th>
									<th width="29%" align="left">
										<input id="deleteItem" style="display: none;" type="button"
											id="inforButton_2" onclick="delInfor()" value="删除明细" />
									</th>
						</s:if>
									<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
									<th colspan="5">
										合计金额
										<span id="hejiMoney"><font color="red"><label
													id="allMoney"></label> </font> </span>	
										<select name="baoxiaodan.currency">
										<option value="${baoxiaodan.currency}">
											${baoxiaodan.currency}
										</option>
											<option value="RMB">
												人民币
											</option>
											<option value="USD">
												美元
											</option>
											<option value="EUR">
												欧元
											</option>
											<option value="HKD">
												港币
											</option>
											<option value="GBR">
												英镑
											</option>
											<option value="JPY">
												日元
											</option>
											<option value="CHF">
												瑞士法郎
											</option>
											<option value="AUD">
												澳元
											</option>
											<option value="CAD">
												加元
											</option>
											<option value="SGD">
												新加坡元
											</option>
											<option value="SEK">
												瑞典克朗
											</option>
											<option value="DKK">
												丹麦克朗
											</option>
											<option value="NOK">
												挪威克朗
											</option>
											<option value="THB">
												泰国铢
											</option>
											<option value="NZD">
												新西兰元
											</option>
											<option value="KRW">
												韩国元
											</option>
										</select>
									</th>
									</s:if>
									<s:else>
									<th colspan="2">
										合计金额
										<span id="hejiMoney"><font color="red"><label
													id="allMoney"></label> </font> </span>
										<select name="baoxiaodan.currency">
										<option value="${baoxiaodan.currency}">
											${baoxiaodan.currency}
										</option>
											<option value="RMB">
												人民币
											</option>
											<option value="USD">
												美元
											</option>
											<option value="EUR">
												欧元
											</option>
											<option value="HKD">
												港币
											</option>
											<option value="GBR">
												英镑
											</option>
											<option value="JPY">
												日元
											</option>
											<option value="CHF">
												瑞士法郎
											</option>
											<option value="AUD">
												澳元
											</option>
											<option value="CAD">
												加元
											</option>
											<option value="SGD">
												新加坡元
											</option>
											<option value="SEK">
												瑞典克朗
											</option>
											<option value="DKK">
												丹麦克朗
											</option>
											<option value="NOK">
												挪威克朗
											</option>
											<option value="THB">
												泰国铢
											</option>
											<option value="NZD">
												新西兰元
											</option>
											<option value="KRW">
												韩国元
											</option>
										</select>
									</th>
									</s:else>
									
								</tr>
								<tr>
								<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
								<td colspan="5" align="center">
										<input id="money1" type="hidden" name="money1" value="">
										<input type="submit" value="提交"
											style="width: 60px; height: 40px;" align="top">
										&nbsp;&nbsp;
										<input type="reset" value="取消"
											style="width: 60px; height: 40px;" align="top">
									</td>
								</s:if>
								<s:else>
								<td colspan="4" align="center">
										<input id="money1" type="hidden" name="money1" value="">
										<input type="submit" value="提交"
											style="width: 60px; height: 40px;" align="top">
										&nbsp;&nbsp;
										<input type="reset" value="取消"
											style="width: 60px; height: 40px;" align="top">
									</td>
								</s:else>
								</tr>
								<tr>
								<s:if test="baoxiaodan.producestatus=='外委'||baoxiaodan.producestatus=='外购'">
								<td colspan="5" style="font-size: 12px;">
										备注：
										<br>
										1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
										2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
										4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
										5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等）
										6、领款日为每周二、四（对公转账及急事、特事除外）。
									</td>
								</s:if>
								<s:else>
								<td colspan="4" style="font-size: 12px;">
										备注：
										<br>
										1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
										2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
										4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
										5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等）
										6、领款日为每周二、四（对公转账及急事、特事除外）。
									</td>
								</s:else>
									
								</tr>
								<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
		<script type="text/javascript">
var inforDivHTML = "";
	var lineCount = "<s:property value='baoxiaodan.getBaoxiaoDetail().size()'/>";
	var begAddLineNum = 6+parseInt(lineCount);
	var planMonth = $("#planMonth").val();
	
	$(function(){
		hejiJine();
	var payStyle = $("#payStyle").val();
	if (payStyle == "归还借款") {
		$("#payvoucher").attr("style","display:block");
		paychange();
	}
})
	
	 
	
	//选择部门
	function selectDept(few) {
		var id = "course" + few;
	createDept(id, "BaoXiaoDanAction!findjianhao.action");
	selectSubjects(few);
	}
	//选择科目
	function selectSubjects(few) {
		var budgetept = $("#course" + few).val();
	$.ajax( {
		type : "POST",
		url : "BaoXiaoDanAction!findpici.action",
		data : {
			tag : budgetept
		},
		dataType : 'json',
		success : function(data) {
			$("#subject" + few).empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.goodsStoreLot + "'> "+ this.goodsStoreLot +"</option>")
								.appendTo("#subject" + few);
					});
			selectSubjects1(few);
		}
	});
	}
	//选择显示数量
function selectSubjects1(few) {
	var budgetept = $("#course" + few).val();//件号
	var budgetsubject = $("#subject" + few).val();//批次
	$.ajax( {
		type : "POST",
		url : "BaoXiaoDanAction!findshuliang.action",
		data : {
			tag : budgetept,
			goodsStoreLots : budgetsubject
		},
		dataType : 'json',
		success : function(data) {
			$("#count" + few).empty();
			$("#count" + few).attr("value",data.goodsStoreCount);
			$("#count_id" + few).attr("value",data.goodsStoreId);
		}
	});
}
	function hejiJine() {
	var total = 0;
	for ( var t = 0; t < lineCount; t++) {
		var id = "h" + t;
		var cur = document.getElementById(id).value;
		total = total + parseFloat(cur);
	}
	document.getElementById("allMoney").innerHTML = total;
	$("#money1").attr("value", total);
}

function saveHKInfor(obj, few) {
	var status = "${baoxiaodan.producestatus}";
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	//if(local.complexselectedlist.value.length != 0) {	
		if(status=='其他'){
			var x = _tr.insertCell(0);
		x.innerHTML = "<input type='text' id='course0'  name=\"listDetail["+lineCount+"].goodsStoreMarkId\"> ";
		var x1 = _tr.insertCell(1);
		x1.innerHTML = "<input type='text'  name=\"listDetail["+lineCount+"].invoiceNo\">" +
		"<input type='hidden' id=\"count_id"+lineCount+"\" name=\"listDetail["+lineCount+"].goodsStoreId\" /> ";
		var x2 = _tr.insertCell(2);
		x2.innerHTML = "<input type=\"text\" style='width: 280px'   name=\"listDetail[" + lineCount
			+ "].content\" >";
		var x3 = _tr.insertCell(3);
		x3.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine()\"  id=\"h" + lineCount + "\" name=\"listDetail["
				+ lineCount + "].money\" >";
		}else{
			var x = _tr.insertCell(0);
	x.innerHTML = "<select style=\"width:80px;\" onclick=\"selectDept("
			+ lineCount + ")\" name=\"listDetail[" + lineCount
			+ "].goodsStoreMarkId\" id=\"course" + lineCount + "\">"
			+ "<option value=\"''\">选择件号</option></select>";
	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<select style=\"width:180px;\" onblue=\"selectSubjects("
			+ lineCount
			+ ")\" onchange=\"selectSubjects1("+lineCount+")\" name=\"listDetail["
			+ lineCount + "].goodsStoreLot\" id=\"subject" + lineCount + "\">"
			+ "</select>";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<input type=\"text\" id=\"count" + lineCount + "\"  readonly='readonly'  name=\"listDetail[" + lineCount
			+ "].goodsStoreCount\" ><input type='hidden' id=\"count_id"+lineCount+"\" name=\"listDetail["+lineCount+"].goodsStoreId\" />";
	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine();compareCount("
			+ lineCount + ")\"  id=\"h" + lineCount + "\" name=\"listDetail["
			+ lineCount + "].money\" >";
		}
	
	//执行下拉
	selectDept(lineCount);
	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
}
function delInfor() {
	//alert(begAddLineNum);
	complexselectedlist.deleteRow(begAddLineNum);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 8) {
		document.getElementById("deleteItem").style.display = "none";
	}
	hejiJine();
}

function paychange(){
	var payStyle = $("#payStyle").val();
	var username = "${sessionScope.Users.name}";
	var baoxiao_id = "${baoxiaodan.paymentVouchers.id}";
	$("#payvoucher").empty();//清空
		if (payStyle == "归还借款") {
			$.ajax( {
				type : "POST",
				url : "paymentVoucherAction_findPayVoucherByname.action",
				data : {
					username : username,
					baoxiao_id:baoxiao_id
				},
				dataType : "json",
				success : function(data) {
					$("#payvoucher").attr("style","display:block");
					$("#payvoucher").empty();//清空
				$.each(data.data1, function(i, n) {
					var countmoney = n.voucherMoney;
					var money1 = n.prepaidMoney;
					var money2 = countmoney - money1;
					if(n.number==data.data2.number){
						$("#payvoucher").append(
							"<option selected=\"selected\" value='" + data.data2.voucherMoney + "," + data.data2.id
									+ "' >" + data.data2.number + "(" + money2 + ")"
									+ "</option>");
					}else{
							$("#payvoucher").append(
							"<option value='" + n.voucherMoney + "," + n.id
									+ "' >" + n.number + "(" + money2 + ")"
									+ "</option>");
					}
				
					
				})
			}
			});
		}else{
			$("#payvoucher").empty();//清空
			$("#payvoucher").attr("style","display:none");
		}
}
//$("#payStyle").change(function() {
	
	//});
//提交验证
function checkForm() {
	var shoukuanRen = document.getElementById("shoukuanRen");
	var payStyle = document.getElementById("payStyle");
	var baoxiaoDate = document.getElementById("baoxiaoDate");
	var explain = document.getElementById("explain");
	var invoiceNum=document.getElementById("invoiceNum");
	if (shoukuanRen.value == "") {
		alert("收款单位（人）不能为空!");
		shoukuanRen.focus();
		return false;
	} else if (payStyle.value == "") {
		alert("付款方式不能为空!");
		payStyle.focus();
		return false;
	} else if (baoxiaoDate.value == "") {
		alert("报销日期不能为空!");
		baoxiaoDate.focus();
		return false;
	} else if (explain.value == "") {
		alert("说明不能为空!");
		explain.focus();
		return false;
	}else if (invoiceNum.value == "") {
		alert("发票号不能为空!");
		explain.focus();
		return false;
	}
}
function chagebaoxiaoClass() {
	for ( var i = 0; i < lineCount; i++) {
		document.getElementById("course" + i).options.length = 0;
		selectCourse(i);
	}
}
//清空下拉部门
function chagebaoxiaoClass() {
	for ( var i = 0; i < lineCount; i++) {
		document.getElementById("course" + i).options.length = 0;
		selectDept(i);
	}
}
function compareCount(few){
	//判断有无选择部门
	var budgetMoneyId=$("#subject"+few).val();//部门申报ID(新)
	if(null!=budgetMoneyId && ""!=budgetMoneyId){
		var baoxiaoMoney=0//$("#h"+few).val();//报销金额（新）
	
	var oldBudgetId=$("#budgetid"+few).val();//老申报部门ID
	var oldbaoxiaoMoney=$("#hid"+i).val();//数据库报销费用
	var detailSize= "<s:property value='listDetail.size()'/>";//历史中记录条数
	var newMoney=0;//新报销费用
	var oldMoney=0;//老报销费用
	if(lineCount>1){
		for(var i=lineCount-1;i>=0;i--){			
			var budgetMoneyIdLast=$("#subject"+i).val();//部门申报ID
			if(budgetMoneyIdLast==budgetMoneyId){//与新科目一致
				var bm=$("#h"+i).val();
				//判断增量
				//判断是否新添行记录
				if(detailSize>i){//没有新添行
					//判断行内增量
					var oldBudgetId=$("#budgetid"+i).val();//老申报部门ID
					if(oldBudgetId==budgetMoneyId){//部门一直增量
						var hidM=$("#hid"+i).val();
						newMoney=parseFloat(newMoney)+parseFloat(bm)-parseFloat(hidM);
					}else{//新选科目
						newMoney=parseFloat(newMoney)+parseFloat(bm)
					}
				}else{//新添行
					newMoney=parseFloat(newMoney)+parseFloat(bm);//新添费用
				}
			}
		}
	}else{
		var bm=$("#h"+0).val();//新金额
		var hidM=$("#hid"+0).val();//老金额
		newMoney=parseFloat(bm)-parseFloat(hidM);
	}
	
	baoxiaoMoney=parseFloat(newMoney);
	//判断报销金额与预算金额大小
	$.ajax( {
		type : "POST",
		url : 'BaoXiaoDanAction!compareBudgetCount.action',
		data : {
			id : budgetMoneyId,
			money : baoxiaoMoney
		},
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(msg) {
			if("NO"==msg){
				alert("报销金额超出预算金额，请核实！");
				$("#h"+few).val(0);
				hejiJine();
			}		
		}
		
	});
	}else{
		alert("请选择部门");
		$("#h"+few).val(0);
	}
	
	
}
</script>

</html>
