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
<%
		Users user = (Users) session.getAttribute("Users");
	%>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h3>
					${companyInfo.name}费用应付登记单
				</h3>

				<form
					action="FundApplyAction_updatefundApply.action"
					method="post" onsubmit="return checkForm()">
					<table  class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									单位(个人)
								</th>
								<td>
									<input type="text" id="unitname" name="fundApply.unitname" value="${fundApply.unitname}"  readonly="readonly"/>
								</td>

								<th>
									收款单位
								</th>
								<td>
									<input type="text" id="relationclient"
										name="fundApply.relationclient" value="${fundApply.relationclient}"  readonly="readonly"/>
								</td>

								<th>
									部门
								</th>
								<td>
									<input type="text" id="approvaldept" readonly="readonly"
										name="fundApply.approvaldept"
										value="${fundApply.approvaldept}" />
								</td>
							</tr>
							<tr >
								<th>
									付款方式
								</th>
								<td>
									<select name="fundApply.payStyle" id="payStyle">
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
									报销日期
								</th>
								<td>
									<input class="Wdate" type="text" name="fundApply.baoxiaoDate"
										size="15" id="baoxiaoDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									预算月份
								</th>
								<td >
									<input type="text" id="planMonth" name="fundApply.planMonth"
										style="background-color: Gray" value="" readonly="readonly" />
								</td>
							</tr>
							<tr class="zhifu_tr">
								<th>
									发票类型
								</th>
								<td>
									<select name="fundApply.isTax">
										<option value="增值税发票">
											增值税发票
										</option>
										<option value="普通发票">
											普通发票
										</option>
									</select>
										<input type="hidden" value="self" name="fundApply.isSelfDept" id="isSelfDept"/>
								</td>
								<th>
									附件张数
								</th>
								<td >
									<select name="fundApply.attachmentsCount">
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
									承担部门
								</th>
								<td >
									<input type="radio" id="budgetDept"
										onclick="chagebaoxiaoClass()" name="fundApply.isSelfDept"
										value="self" checked="checked" />
									本部门
									<input type="radio" id="budgetDept2"
										"
										name="fundApply.isSelfDept" value="others" />
									多部门
								</td>
							</tr>
						<tr class="zhifu_tr" >
								<th>
									发票号码
								</th>
								<td colspan="7">
									<input size="100px" type="text" id="invoiceNum"
										name="fundApply.invoiceNum" />
									(注：多张发票逗号分开)
								</td>
							</tr>
							<tr class="zhifu_tr">
								<th>
									说明
								</th>
								<td colspan="7">
									<textarea cols="88" name="fundApply.explain" id="explain"></textarea>
								</td>
							</tr>
							<tr>
								<th colspan="8">
									资金使用明细
								</th>
							</tr>
							<tr>
								<th>
									部门
								</th>
								<th>
									预算科目
								</th>
								<th>
									资金使用科目
								</th>
								<th>
									资金使用明细
								</th>
								<th>
									付款金额
								</th>
								<th colspan="3">
									用途
								</th>
							</tr>
						<s:iterator value="fundDetailedList" id="fadset">
							<tr >
								<th>
									${fadset.budgetDept}
								</th>
								<th >
									${fadset.deptMonthBudgetName}
								</th>
								<th>
									${fadset.zjStyle}
								</th>
								<th>
									${fadset.zjStyleMx}
								</th>
								<th>
									${fadset.voucherMoney}
								</th>
								<th colspan="3" align="center">
									<textarea cols="40" rows="2"
										name="">
											${fadset.pay_use}
									</textarea>
								</th>
							</tr>
						</s:iterator>
							
							<tr id="uploadtr">
								<th>
									合计金额(${fundApply.currency}):${fundApply.totalMoney}
									</th>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="hidden" value="${fundApply.id}" name="fundApply.id"/>
									<input type="hidden" value="${cpage}" name="cpage"/>
									<input type="submit" value="申请支付" id="loanId" 
										align="top" class="input">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										class="input"align="top">
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
$(function() {
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
	showHt();
})

function showHt() {
	var val_payPlatform = $('input[name="paymentVoucher.voucherbasis"]:checked')
			.val();
	if (val_payPlatform == "合同") {
		$("#showHeTong").show();
		$.ajax( {
			type : "POST",
			url : "bargainAction_getLoginHeTong.action",
			dataType : "json",
			success : function(data) {
				$("#showHeTong").empty();//清空值
				$.each(data, function(i, n) {
					$("#showHeTong").append(
							"<option value='" + n.id + "' >" + n.contract_num
									+ "</option>");
				})
				//加载数据成功
				showHeTo();
			}
		});
	} else {
		$("#showHeTong").empty();
		$("#showHeTong").hide();
		document.getElementById("loanId").disabled = false
	}
}

function showHeTo() {
	if ($("#showHeTong").val() != null && $("#showHeTong").val() != "") {
		document.getElementById("loanId").disabled = false

	} else {
		document.getElementById("loanId").disabled = true
	}
}

function deptSelect(obj) {
	var selected = obj.id;//获取到当前列的id
	$.ajax( {
		type : "POST",
		url : "paymentVoucherAction_findSubjectName.action",
		data : {},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$("#" + selected + "").append(
						"<option value='" + n + "' >" + n + "</option>");
			})
		}
	});

}
var lineCount = 1;
var begAddLineNum = 10;

//删除明细
function delInfor() {
	//alert(begAddLineNum);
	complexselectedlist.deleteRow(begAddLineNum);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 11) {
		document.getElementById("deleteItem").style.display = "none";
	}
	hejiJine();
}
function hejiJine() {
	var total = 0;
	for ( var t = 0; t < lineCount; t++) {
		var id = "h" + t;
		var cur = document.getElementById(id).value;
		total = total + parseFloat(cur);
	}
	document.getElementById("allMoney").innerHTML = total;
	document.getElementById("voucherMoney").value = total;//把总金额赋给借款总金额
}
//添加明细
function saveHKInfor(obj, few) {
	
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.innerHTML = '<select name="fundApply.fadList['+lineCount+'].budgetDept" style= "width: 80px;" onMouseOver="selectDept('+lineCount+')" onChange="selectSubjects('+lineCount+')" id="course'+lineCount+'"> <option value="">选择部门</option></select>';
	var x1 = _tr.insertCell(1);
	x1.innerHTML = '<select name="fundApply.fadList['+lineCount+'].style" style="width: 180px;" id="subject'+lineCount+'" onchange="selectYuefen(this)"></select> ';
	var x2 = _tr.insertCell(2);
	x2.innerHTML = '<select name="fundApply.fadList['+lineCount+'].zjStyle" onchange="changvalue(this,'+lineCount+')" id="zjstyle'+lineCount+'">' +
	'<option></option>' +
	'<option value="设备维修费">设备维修费</option>' +
	'<option value="差旅、招待费">差旅、招待费</option>' +
	'<option value="规费">规费</option>' +
	' <option value="零件采购费">零件采购费</option>' +
	' <option value="辅料采购费">辅料采购费</option>' +
	'<option value="奖金">奖金</option>' +
	'<option value="奖金">奖金</option>' +
	'<option value="工资">工资</option></select>';
	var x3 = _tr.insertCell(3);
	x3.innerHTML = '<div id="select_div'+lineCount+'"> <select name="fundApply.fadList['+lineCount+'].zjStyleMx" id="zjsymx'+lineCount+'" style="width: 155px;">' +
	'<option></option></select></div>' +
	'<div id="input_div'+lineCount+'" style="display: none;"><input type="text" value="" name="fundApply.fadList['+lineCount+'].zjStyleMx"/></div>';

	var x4 = _tr.insertCell(4);
	x4.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine();compareCount("
			+ lineCount + ")\"  id=\"h" + lineCount
			+ "\" name=\"fundApply.fadList["+lineCount+"].voucherMoney\">";

	var x5 = _tr.insertCell(5);
	x5.setAttribute('colspan', '3');
	x5.innerHTML = "<textarea cols='40' rows='2' name='fundApply.fadList["+lineCount+"].pay_use'></textarea>";
	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
}

//提交验证
function checkForm() {
	var unitname = document.getElementById("unitname");
	var relationclient = document.getElementById("relationclient");
	var accreditationnum = document.getElementById("accreditationnum");
	var contractnum = document.getElementById("contractnum");
	var voucherMoney = document.getElementById("voucherMoney");
	if (unitname.value == "") {
		alert("借款单位（人）不能为空!");
		unitname.focus();
		return false;
	}
	/*else if (relationclient.value == "") {
		alert("关联客户不能为空!");
		relationclient.focus();
		return false;
	} else if (accreditationnum.value == "") {
		alert("评审编号不能为空!");
		accreditationnum.focus();
		return false;
	}else if (contractnum.value == "") {
		alert("合同号不能为空!");
		contractnum.focus();
		return false;
	}*/
	else if (voucherMoney.value == "") {
		alert("借款金额不能为空!");
		voucherMoney.focus();
		return false;
	}
	
	document.getElementById("loanId").disabled = false;
}


//选择部门
//清空下拉部门
function chagebaoxiaoClass() {
	for ( var i = 0; i < lineCount; i++) {
		document.getElementById("course" + i).options.length = 0;
		selectDept(i);
	}
}
var planMonth = $("#planMonth").val();
function selectDept(few) {
	var budgetDept = $("#budgetDept");
	var budgetDetpR = document.getElementsByName("fundApply.isSelfDept");
		for ( var i = 0; i < budgetDetpR.length; i++) {
		if (budgetDetpR[i].checked)
			var budgetDept = budgetDetpR[i].value;
	}
	var id = "course" + few;
	createDept(id, "BaoXiaoDanAction!findchildDept.action?tag=" + budgetDept
			+ "&planMonth=" + planMonth);
	selectSubjects(few);
}

//选择科目
function selectSubjects(few) {
	//var planMonth=$("#planMonth").val();
	var budgetept = $("#course" + few).val();
	$.ajax( {
		type : "POST",
		url : "BaoXiaoDanAction!findchildSubjects.action",
		data : {
			tag : budgetept,
			planMonth : planMonth
		},
		dataType : 'json',
		success : function(data) {
			$("#subject" + few).empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.realMoney + "/"
										+ this.accountMoney + "元" + "/"
										+ this.budgetMonth + ")</option>")
								.appendTo("#subject" + few);
					});
		}
	});
}
//点击科目及月份是触发的事件
function selectYuefen() {
	var allText = "";
	//根据页面科目及月份的下拉列表
	for ( var i = 0; i < lineCount; i++) {
		var obj = document.getElementById("subject" + i);
		var a = obj.options[obj.selectedIndex].text;
		if (a != "") {
			var a1 = a.lastIndexOf("/");
			a = a.substring(a1 + 1, a.length - 1);
			if (allText == "") {
				allText = a;
			} else {
				if (allText.indexOf(a) < 0) {
					allText = allText + "," + a;
				}
			}
		}
	}
	$("#planMonth").val(allText);
}
function changvalue(obj,num){
	if(obj!=null && obj.value!=""){
		if(obj.value == '设备维修费' || obj.value == '差旅、招待费' || obj.value == '零件采购费' ){
			$.ajax( {
		type : "POST",
		url : "FundApplyAction_getzjStyleMx.action",
		data : {
			style:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#zjsymx"+num).empty();
				if(obj.value == '设备维修费'){
					$(data).each(function(){
					$("#zjsymx"+num).append("<option value="+this.barcode+">"+this.barcode+"</option>");
				})
				}else if(obj.value == '差旅、招待费'){
					$(data).each(function(){
						$("#zjsymx"+num).append("<option value="+this+">"+this+"</option>");
					})
				}else{
					$(data).each(function(){
						$("#zjsymx"+num).append("<option value="+this.planNumber+">"+this.planNumber+"</option>");
					})
				}
				
			}
		}
	})
			$("#select_div"+num).show();
			$("#input_div"+num).hide();
		}else{
			$("#select_div"+num).hide();
			$("#input_div"+num).show();
		}
	}
}
function showzhifu_tr(){
	$(".zhifu_tr").show();
}
function hidzhifu_tr(){
	$(".zhifu_tr").hide();
}
function changspan(obj){
	if(obj!=null && obj.value!=""){
		var arrays = ['人民币','美元','欧元','港币','英镑','日元','瑞士法郎','澳元','加元','新加坡元','瑞典克朗','丹麦克朗','挪威克朗','泰国铢','新西兰元','韩国元'];
		var rmbs =['RMB','USD','EUR','HKD','GBR','JPY','CHF','AUD','CAD','SGD','SEK','DKK','NOK','THB','NZD','KRW'];
		for(var i=0;i<rmbs.length;i++){
			if(rmbs[i] == obj.value){
				$("#currency_span").html(arrays[i]);
			}
		}
	}
}
</script>

</html>
