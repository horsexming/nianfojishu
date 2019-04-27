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

	<body onload="hejiJine()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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
				<h3>
					${companyInfo.name}费用报销单
				</h3>
				<!-- BaoXiaoDanAction!updateBaoXiaoDan.action -->
				<form action="BaoXiaoDanAction!updateBaoXiaoDan.action"
					onsubmit="return checkForm()" method="post">
					<input type="hidden" name="baoxiaodan.id" value="${baoxiaodan.id}">
					<input type="hidden" name="baoxiaodan.dept" value="${baoxiaodan.dept}">
					<input type="hidden" name="baoxiaodan.code" value="${baoxiaodan.code}">
					<input type="hidden" name="baoxiaodan.baoxiaoren" value="${baoxiaodan.baoxiaoren}">
					<input type="hidden" name="baoxiaodan.saveTime" value="${baoxiaodan.saveTime}">
					<input type="hidden" name="baoxiaodan.baoxiaoBarcode" value="${baoxiaodan.baoxiaoBarcode}">
					<input type="hidden" name="baoxiaodan.status" value="${baoxiaodan.status}">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th width="100px">
									收款单位(个人)
								</th>
								<td>
									<input type="text" name="baoxiaodan.shoukuanRen"
										id="shoukuanRen" value="${baoxiaodan.shoukuanRen}" />
								</td>
								<th>
									预算月份
								</th>
								<td>
									<input type="text" id="planMonth" name="baoxiaodan.planMonth"
										style="background-color: Gray" value="${baoxiaodan.planMonth}"
										readonly="readonly" />

								</td>

							</tr>
							<tr>
								<th width="100px">
									付款方式
								</th>
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
									报销日期
								</th>
								<td>
									<input class="Wdate" type="text" name="baoxiaodan.baoxiaoDate"
										id="baoxiaoDate" size="15" value="${baoxiaodan.baoxiaoDate}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<th width="100px">
									附件张数
								</th>
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
									承担部门
								</th>
								<td>
									<s:if test="%{'self'==baoxiaodan.isSelfDept}">
										<input type="radio" id="budgetDept"
											onclick="chagebaoxiaoClass()" name="baoxiaodan.isSelfDept"
											value="self" checked="checked" />
									本部门
									<input type="radio" id="budgetDept2"
											"
										name="baoxiaodan.isSelfDept" value="others" />
									多部门
								</s:if>
									<s:else>
										<input type="radio" id="budgetDept"
											onclick="chagebaoxiaoClass()" name="baoxiaodan.isSelfDept"
											value="self" />
									本部门
									<input type="radio" id="budgetDept2"
											name="baoxiaodan.isSelfDept" value="others" checked="checked" />
									多部门
								</s:else>

								</td>
							</tr>
							<tr>
							<th>发票类型</th>
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
								<th width="100px">
									说明
								</th>
								<td colspan="3">
									<textarea cols="88" name="baoxiaodan.explain" id="explain">${baoxiaodan.explain}</textarea>
								</td>
							</tr>
							<tr>
								<th colspan="4">
									报销明细
								</th>
							</tr>

							<tr>
								<th>
									部门
								</th>
								<th width="180px">
									报销科目及月份
								</th>
								<th>
									报销内容摘要
								</th>

								<th>
									金额
								</th>
							</tr>
							<s:iterator value="baoxiaodan.getBaoxiaoDetail()" status="se" id="bxd">
								<tr align="left">
									<th>
										<select name="listDetail[${se.index}].budgetDept" style="width: 80px;"
											onClick="selectDept(${se.index})"
											onChange="selectSubjects(${se.index})" id="course${se.index}">
											<option value="${bxd.budgetDept}">
												${bxd.budgetDept}
											</option>
									</th>
									<th>
										<input type="hidden" name="listDetail[${se.index}].id"
											value="${bxd.id }" />
										<select style="width: 180px;"
											name="listDetail[${se.index}].baoxiaoStyle"
											onchange="selectYuefen(this)"  id="subject${se.index}">
											<option value="${bxd.deptMonthBudgetID}">
												${bxd.baoxiaoStyle}(${bxd.deptMonthBudgetID}/${bxd.planMonth})
											</option>

										</select>
									</th>
									<th>
										<input type="text"
											name="listDetail[${se.index}].baoxiaoContent"
											value="${bxd.baoxiaoContent}" />
									</th>
									<th align="center">
										<input type="text" onkeyup="hejiJine();compareCount(${se.index})" id="h${se.index}"
											name="listDetail[${se.index}].money"
											value="${bxd.money}" />
									</th>
								</tr>
								<input type="hidden" id="budgetid${se.index}" value="${bxd.deptMonthBudgetID }" />
								<input type="hidden" id="hid${se.index}" value="${bxd.money }" />
							</s:iterator>
							<tr id="uploadtr">
								<th align="left">
									<input type="button" id="inforButton_1"
										onclick="saveHKInfor(this,1)" value="添加明细" />
								</th>
								<th align="left">
									<input id="deleteItem" style="display: block" type="button"
										id="inforButton_2" onclick="delInfor()" value="删除明细" />
								</th>
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
							</tr>
							<tr>
								<td colspan="4" align="center">
								<input id="money1" type="hidden" name="money1" value="">
									<input type="hidden" id="oldmoney"  name="oldmoney"  value="" />
									<input type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
							<tr>
								<td colspan="4" style="font-size: 12px;">
									备注：
									<br>
									1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
									2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
									4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
									5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等） 6、领款日为每周二、四（对公转账及急事、特事除外）。
								</td>
							</tr>
						</tbody>
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
	var payStyle = $("#payStyle").val();
	if (payStyle == "归还借款") {
		$("#payvoucher").attr("style","display:block");
		paychange();
	}
})
	
	function selectYuefen() {
	var allText = "";
	//根据页面科目及月份的下拉列表
	for ( var i = 0; i < lineCount; i++) {
		var obj = document.getElementById("subject" + i);
		var a = obj.options[obj.selectedIndex].text;
		if (a != "") {
			var a1 = a.lastIndexOf("/");
			a = a.substring(a1 + 1, a.length-1);
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
	
	//选择部门
	function selectDept(few) {
		var budgetDept = $("#budgetDept");
		var budgetDetpR = document.getElementsByName("baoxiaodan.isSelfDept");
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
		var planMonth = "";
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
											+ "(" +this.realMoney+"/"+  this.accountMoney+"元"+"/"
										+ this.budgetMonth + ")</option>").appendTo(
									"#subject" + few);
						});
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
	$("#oldmoney").attr("value", total);
}

function saveHKInfor(obj, few) {
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);

	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.innerHTML = "<select style=\"width:80px;\" onclick=\"selectDept("
			+ lineCount + ")\" name=\"listDetail[" + lineCount
			+ "].budgetDept\" id=\"course" + lineCount + "\">" +"<option value=\"''\">选择部门</option></select>";
	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<select style=\"width:180px;\"  onchange='selectYuefen(this)' onblue=\"selectSubjects("
			+ lineCount + ")\" name=\"listDetail[" + lineCount
			+ "].baoxiaoStyle\" id=\"subject" + lineCount + "\">" + "</select>";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<input type=\"text\" name=\"listDetail[" + lineCount
			+ "].baoxiaoContent\" >";
	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine();compareCount("+lineCount+")\" id=\"h"
			+ lineCount + "\" name=\"listDetail[" + lineCount + "].money\" > ";
	
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
	var detailSize= "<s:property value='baoxiaodan.getBaoxiaoDetail().size()'/>";//历史中记录条数
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
