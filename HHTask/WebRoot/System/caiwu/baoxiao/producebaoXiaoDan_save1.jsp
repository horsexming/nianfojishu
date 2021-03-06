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
				<!--BaoXiaoDanAction!saveBaoXiaoDan1.action?tag=QT -->
				<form action="BaoXiaoDanAction!saveBaoXiaoDan1.action?tag=QT" method="post"
					onsubmit="return checkForm()">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									收款单位(个人)
								</th>
								<td>
									<input type="text" id="shoukuanRen"
										name="baoxiaodan.shoukuanRen" />
								</td>
								<th>
									合同号
								</th>
								<td colspan="2">
									<input type="text"  name="baoxiaodan.contract_Number" />
								</td>
							</tr>
							<tr>
								<th>
									付款方式
								</th>
								<td>
									<select name="baoxiaodan.payStyle"  id="payStyle">
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
										<option></option>
									</select>
								</td>
								<th>
									订单号
								</th>
								<td colspan="2">
									<input type="text"  name="baoxiaodan.order_number" />
								</td>
							</tr>
							<tr>
								<th>
									附件张数
								</th>
								<td>
									<select name="baoxiaodan.attachmentsCount">
										<%
											for (int i = 0; i < 301; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>

									</select>
								</td>

								<input type="hidden" name="baoxiaodan.baoxiaoren"
									value="<%=user.getName()%>" />

								<th>
									报销日期
								</th>
								<td colspan="2">
									<input class="Wdate" type="text" name="baoxiaodan.baoxiaoDate"
										size="15" id="baoxiaoDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<th>
									发票类型
								</th>
								<td>
									<select name="baoxiaodan.isTax">
										<option value="增值税发票">
											增值税发票
										</option>
										<option value="普通发票">
											普通发票
										</option>
									</select>
								</td>
								<th>
									发票号码
								</th>
								<td colspan="2">
									<input size="100px" type="text" id="invoiceNum"
										name="baoxiaodan.invoiceNum" />
									(注：多张发票逗号分开)
								</td>
							</tr>
							<tr>
								<th>
									说明
								</th>
								<td colspan="5">
									<textarea cols="88" name="baoxiaodan.explain" id="explain"></textarea>
								</td>
							</tr>
							<tr>
								<th colspan="4">
									报销明细
								</th>
							</tr>
							<tr>
								<th>
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
							<tr>
								<th align="center">
									<input id="course0" name="listDetail[0].goodsStoreMarkId" type="text" value="">
								</th>
								<th align="center">
									<input type="text" name="listDetail[0].invoiceNo" value="">
									<input type="hidden" id="count_id0" name="listDetail[0].goodsStoreId" />
								</th>
								<th align="center">
									<input type="text" style="width: 280px;"   name="listDetail[0].content" />
								</th>
								<th align="center">
									<input type="text" onKeyUp="hejiJine()" id="h0"
										name="listDetail[0].money" />
								</th>
							</tr>
							<tr id="uploadtr">
								<tr>
									<th align="left">
										<input type="button" id="inforButton_1"
											onclick="saveHKInfor(this,1)" value="添加明细" />
									</th>
									<th width="29%" align="left">
										<input id="deleteItem" style="display: none;" type="button"
											id="inforButton_2" onclick="delInfor()" value="删除明细" />
									</th>
									<th colspan="5">
										合计金额
										<span id="hejiMoney"><font color="red"><label
													id="allMoney"></label> </font> </span>
										<select name="baoxiaodan.currency">
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
								</tr>
								<tr>
									<td colspan="7" align="center">
										<input id="money1" type="hidden" name="money1" value="">
										<input type="submit" value="提交"
											style="width: 60px; height: 40px;" align="top">
										&nbsp;&nbsp;
										<input type="reset" value="取消"
											style="width: 60px; height: 40px;" align="top">
									</td>
								</tr>
								<tr>
									<td colspan="7" style="font-size: 12px;">
										备注：
										<br>
										1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
										2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
										4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
										5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等）
										6、领款日为每周二、四（对公转账及急事、特事除外）。
									</td>
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

$("#payStyle").change(function() {
	var payStyle = $("#payStyle").val();
	var username = "${sessionScope.Users.name}";
	$("#payvoucher").empty();//清空
		if (payStyle == "归还借款") {
			$.ajax( {
				type : "POST",
				url : "paymentVoucherAction_findPayVoucherByname.action",
				data : {
					username : username
				},
				dataType : "json",
				success : function(data) {
					$("#payvoucher").attr("style","display:block");
					$("#payvoucher").empty();//清空
				$.each(data.data1, function(i, n) {
					var countmoney = n.voucherMoney;
					var money1 = n.prepaidMoney;
					var money2 = countmoney - money1;
					$("#payvoucher").append(
							"<option value='" + n.voucherMoney + "," + n.id
									+ "' >" + n.number + "(" + money2 + ")"
									+ "</option>");
						})
				}
			})
		}else{
			$("#payvoucher").empty();//清空
			$("#payvoucher").attr("style","display:none");
		}
	});

var inforDivHTML = "";
var lineCount = 1;
var begAddLineNum = 7;
//选择件号
function selectDept(few) {
	var id = "course" + few;
	createDept(id, "BaoXiaoDanAction!findjianhao.action");
	selectSubjects(few);
}
//选择批次
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
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	//if(local.complexselectedlist.value.length != 0) {			
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
	//执行下拉
	selectDept(lineCount);
	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
	//} 

	//lineCount++;   

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
//提交验证
function checkForm() {
	var money = document.getElementById("payvoucher");
	var payStyle = document.getElementById("payStyle");
	var money1 = money.value;
	var array = money1.split(",");
	var money2 = array[0];//取出借款金额
	var pay_id = array[1];//取出借款id
	//alert("pay_id:"+pay_id+"==="+money2);
	if (payStyle.value == '归还借款') {
		var total = 0;
		for ( var t = 0; t < lineCount; t++) {
			var id = "h" + t;
			var cur1 = document.getElementById(id);
			var cur = cur1.value;
			total = total + parseFloat(cur);
			if (total > money2) {
				alert("报销金额不能超过借款金额");
				cur1.focus();
				return false;
			}
		}
	}
	var shoukuanRen = document.getElementById("shoukuanRen");
	var payStyle = document.getElementById("payStyle");
	var baoxiaoDate = document.getElementById("baoxiaoDate");
	var explain = document.getElementById("explain");
	var invoiceNum = document.getElementById("invoiceNum");

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
	} else if (invoiceNum.value == "") {
		alert("发票号不能为空!");
		invoiceNum.focus();
		return false;
	}
}
//清空下拉部门
function chagebaoxiaoClass() {
	for ( var i = 0; i < lineCount; i++) {
		document.getElementById("course" + i).options.length = 0;
		selectDept(i);
	}
}
function compareCount(few) {
	var budgetMoneyId = $("#subject" + few).val();//部门申报ID
	if (null != budgetMoneyId && "" != budgetMoneyId) {
		var baoxiaoMoney = $("#h" + few).val();//报销金额
		if (lineCount >= 1) {
			for ( var i = lineCount - 1; i >= 0; i--) {
				var budgetMoneyIdLast = $("#subject" + i).val();//部门申报ID
				if (budgetMoneyIdLast == budgetMoneyId) {
					if (few != i) {
						var bm = $("#h" + i).val();
						baoxiaoMoney = parseFloat(baoxiaoMoney)
								+ parseFloat(bm);
					}

				}
			}
		}
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
				if ("NO" == msg) {
					alert("报销金额超出预算金额，请核实！");
					$("#h" + few).val(0);
					hejiJine();
				}
			}

		});
	} else {
		alert("请选择件号");
		$("#h" + few).val(0);
	}
}
</script>

</html>
