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
				<h5>
					询比议价评审单
				</h5>
				<form id="testForm" action="bargainAction_updateBargain.action?test=<s:property value="#parameters.test"/>" method="post">
					<table style="width: 100px;" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th colspan="7">
									询价物品修改
								</th>
							</tr>
							<tr>
								<th>
									品名
								</th>
								<th>
									规格
								</th>
								<th>
									采购数量
								</th>
								<th>
									单位
								</th>
								<th>
									采购交期
								</th>
								<th>
									质量要求
								</th>
								<th>
									备注
								</th>
							</tr>
								<s:iterator value="listgoods" id="pageList" status="pageStatus">
							<tr align="left">
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].goods_name" value="${pageList.goods_name}">
									<input type="hidden" name="listgoods[${pageStatus.index}].id" value="${pageList.id}">
								</th>
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].goods_format" value="${pageList.goods_format}">
								</th>
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].goods_amount" value="${pageList.goods_amount}">
								</th>
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].goods_unit" value="${pageList.goods_unit}">
								</th>
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].purchase_delivery"  value="${pageList.purchase_delivery}">
								</th>
								<th>
									<input type="text" name="listgoods[${pageStatus.index}].quality_requirements" value="${pageList.quality_requirements}">
								</th>
								<th>
									<textarea cols="20" name="listgoods[${pageStatus.index}].remark">${pageList.remark}</textarea>
								</th>
							</tr>
							</s:iterator>
							<tr id="uploadtr">
								<th align="left">
									<input type="button" id="inforButton_1"
										onclick="saveHKInfor(this,1)" value="添加物品" />
								</th>
								<th width="29%" align="left" colspan="6">
									<input id="deleteItem" style="display: none;"  type="button"
										id="inforButton_2" onclick="delInfor()" value="删除物品" />
								</th>
							</tr>
							
							<!-- 添加询比议价厂商 -->
							<tr>
								<th colspan="7">
									询比议价厂商添加
									<input id="deleteItem1" type="button" id="inforButton_3"
										onclick="saveHKInfor2()" value="添加公司" />
								</th>
							</tr>
							<s:iterator value="listCompanyVO" id="pageList1" status="pageStatus">
							<tr id="${pageStatus.index+1}_aaa_1">
								<th>
									询比议价厂商
								</th>
								<th colspan="2">
									公司名称
									<br />
									<input type="text" name="listcompany[${pageStatus.index}].company_name" value="${pageList1.company_name}"
										size="40px;">
										<br/>
										<s:if test='#pageList1.selected_status=="是"'>
										是否选中
										<input type="radio"  name="selected_status"  value="${pageStatus.index+1}"  checked="checked"/>
											是
										</s:if>
										<s:else>
										是否选中
										<input type="radio"  name="selected_status"  value="${pageStatus.index+1}" />
											是
										</s:else>
										
											
									<input type="hidden" name="listcompany[${pageStatus.index}].id" value="${pageList1.id}"
										size="40px;">
								</th>
								<th>
									联系人
									<br />
									<input type="text" name="listcompany[${pageStatus.index}].contacts" value="${pageList1.contacts}">
								</th>
								<th>
									电话/邮箱
									<br />
									<input type="text" name="listcompany[${pageStatus.index}].telephone" value="${pageList1.telephone}">
								</th>
								<th>
									结算方式
									<br />
									<select name="listcompany[${pageStatus.index}].clearing_way" 
										style="width: 100px;">
										<option value="${pageList1.clearing_way}" selected="selected">${pageList1.clearing_way}</option>
										<option>
											银行
										</option>
										<option>
											汇票
										</option>
										<option>
											汇兑
										</option>
										<option>
											支票
										</option>
										<option>
											贷记
										</option>
										<option>
											现金
										</option>
										<option>
											其他
										</option>
									</select>
								</th>
								<th>
									结算日期
									<br />
									<input type="text" class="Wdate"
										name="listcompany[${pageStatus.index}].clearing_date"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  value="${pageList1.clearing_date}"/>
								</th>
							</tr>
							<s:iterator value="#pageList1.bargainingDetails" id="pageList2" status="index">
							<s:if test="#index.index>2">
							<tr id="${pageStatus.index+1}_aaa_${index.index+3}">
							</s:if>
							<s:else>
								<tr id="${pageStatus.index+1}_aaa_${index.index+2}">
							</s:else>
								<th>
									<span>第${index.index+1}次议价</span>
									<input type="hidden" name="listcompany[${pageStatus.index}].bargList[${index.index}].numbers"
										value="${pageList2.numbers}">
									<input type="hidden" name="listcompany[${pageStatus.index}].bargList[${index.index}].id"
										value="${pageList2.id}">
								</th>
								<th>
									数量
								</th>
								<th>
									<input type="text" name="listcompany[${pageStatus.index}].bargList[${index.index}].amount" value="${pageList2.amount}">
								</th>
								<th>
									单价
								</th>
								<th colspan="3" align="left">
									<input type="text" name="listcompany[${pageStatus.index}].bargList[${index.index}].unitprice"  value="${pageList2.unitprice}">
								</th>
							</tr>
					</s:iterator>
							<tr id="${pageStatus.index+1}_aaa_5">
								<th align="left">
									<input type="button" id="inforButton_1" 
										onclick="saveHKInfor1(this,1)" value="添加议价" />
								</th>
								<th align="left">
									<s:if test="#pageList1.bargainingDetails.size()>3">
										<input id="deleteyijia1" type="button"
										onclick="delInfor1(this)" value="删除议价" />
									</s:if>
									<s:else>
										<input id="deleteyijia1" type="button" style="display: none;"
										onclick="delInfor1(this)" value="删除议价" />
									</s:else>
								</th>
								<th align="left" colspan="5">
									<input id="${pageStatus.index+1}_aaa" type="hidden" value="<s:property value="#pageList1.bargainingDetails.size()-3+5"/>">
									<input id="deleteItem2" type="button"  
										onclick="delInfor2(this)" value="删除公司" />
								</th>
							</tr>
				</s:iterator>
							<tr id="button1">
								<td colspan="8" align="center">
								<input type="hidden" name="bargain.id"  value="${bargain.id}">
									<input type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
							<tr>
								<td colspan="8" style="font-size: 12px;">
									附件：
									<br>
									1、该表适用于单一材料、单项设备、单项工程；
									<br />
									2、评审部门由申请人所在部门业务需要填写；
									<br />
									3、该表一式两联。申请人所在部门第一联，财务第二联；
									<br />
									4、审批流程为申请人依次请各部门审批后，由总经理批示确定供应商。
									<br />
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
	//alert(Math.max.apply(null, listCompanyVO.bargainingDetails));
	if(begAddLineNum>1){
		document.getElementById("deleteItem").style.display = "block";
	}
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})
var lineCount = "<s:property value='listgoods.size()'/>";
var begAddLineNum = "<s:property value='listgoods.size()'/>";//物品
var begAddLineNum1 = 8;//议价
var begAddLineNum2 = 10;//公司
//添加物品
function saveHKInfor(obj, few) {
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	var x = _tr.insertCell(0);
	x.innerHTML = "<input type=\"text\"  name=\"listgoods[" + lineCount
			+ "].goods_name\">";
	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<input type=\"text\" name=\"listgoods[" + lineCount
			+ "].goods_format\">";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<input type=\"text\" name=\"listgoods[" + lineCount
			+ "].goods_amount\">";
	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input type=\"text\" name=\"listgoods[" + lineCount
			+ "].goods_unit\">";
	var x4 = _tr.insertCell(4);
	x4.innerHTML = "<input type=\"text\" name=\"listgoods[" + lineCount
			+ "].purchase_delivery\">";
	var x5 = _tr.insertCell(5);
	x5.innerHTML = "<input type=\"text\" name=\"listgoods[" + lineCount
			+ "].quality_requirements\">";
	var x6 = _tr.insertCell(6);
	x6.innerHTML = "<textarea cols=\"20\" name=\"listgoods[" + lineCount
			+ "].remark\" ></textarea>";
	lineCount++;
	begAddLineNum++;
	document.getElementById("deleteItem").style.display = "block";
}
//删除物品
function delInfor() {
	complexselectedlist.deleteRow(parseInt(begAddLineNum)+1);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 2) {
		document.getElementById("deleteItem").style.display = "none";
	}
}
//添加议价
function saveHKInfor1(obj, few) {
	var selectedTr = obj.parentNode.parentNode;
	var trId = selectedTr.id;//获得当前隐藏域的id
	trId = trId.substring(0, 1);//获取隐藏id的第一个下标
	var a = $("#" + trId + "_aaa").val();
	var a1 = parseInt(a) + 1;
	var a2 = parseInt(a) - 1;
	$("#" + trId + "_aaa").attr("value", a1);//添加议价时更新其隐藏域的值

	//找到当前tr，获得tr里面删除议价按钮，改变其id
	$("#" + trId + "_aaa_5 input[type='button']").each(function(i) {
		if (this.value == '删除议价') {
			this.id = "deleteyijia" + trId;
		}
	});
	var company_num = parseInt(trId) - 1;
	var bar_num = parseInt(a2) - 1;
	//添加议价时给当前name重新赋值属性
	$("#" + trId + "_aaa_5")
			.before(
					"<tr id='"+ trId+ "_aaa_"+ a1+ "'>"
					+ "<th><span>第"+ a2+ "次议价</span><input type='hidden' name='listcompany["+ company_num+ "].bargList["+ bar_num+ "].numbers' " +
					"value='"+ a2+ "'></th>"
					+ "<th>数量</th><th><input type='text' name='listcompany["+company_num+"].bargList["+bar_num+"].amount'></th>"
				+ "<th>单价</th><th align='left' colspan='3'><input type='text' name='listcompany["+company_num+"].bargList["+bar_num+"].unitprice'></th></tr>");
	//var s = $("#" + trId + "_aaa_" + a2 + " th span");
	///if (s.html() == null) {
	//	s = $("#" + trId + "_aaa_" + a1 + " th span");
	//	}
	//alert(s.html());

	document.getElementById("deleteyijia" + trId).style.display = "block";
	begAddLineNum1++;
}
//删除议价
function delInfor1(obj) {
	var selectedTr = obj.parentNode.parentNode;
	var trId = selectedTr.id;//获得当前隐藏域的id
	trId = trId.substring(0, 1);//获取隐藏id的第一个下	
	var a = $("#" + trId + "_aaa").val();
	var a1 = parseInt(a) - 1;
	$("#" + trId + "_aaa_" + a).remove();
	$("#" + trId + "_aaa").attr("value", a1);//添加议价时更新其隐藏域的值
	if (a1 == 5) {
		document.getElementById("deleteyijia" + trId).style.display = "none";
	} 
}

//添加公司
var count_num = "<s:property value='listCompanyVO.size()'/>";
var count_num1 = 1;
function saveHKInfor2(obj, few) {
	count_num++;
	count_num1++;
	//复制部门和议价明细追加在按钮之前
	var $tr1 = $("#1_aaa_1").clone();
	var $tr2 = $("#1_aaa_2").clone();
	var $tr3 = $("#1_aaa_3").clone();
	var $tr4 = $("#1_aaa_4").clone();
	var $tr5 = $("#1_aaa_5").clone();
	//赋给变量
	var gongs = count_num + '_aaa_1';
	var gongs1 = count_num + '_aaa_2'
	var gongs2 = count_num + '_aaa_3'
	var gongs3 = count_num + '_aaa_4'
	var gongs4 = count_num + '_aaa_5'
	//重新赋值
	$tr1.attr('id', gongs);
	$tr2.attr('id', gongs1);
	$tr3.attr('id', gongs2);
	$tr4.attr('id', gongs3);
	$tr5.attr('id', gongs4);
	//copy到button1之前显示
	$("#button1").before($tr1);
	$("#button1").before($tr2);
	$("#button1").before($tr3);
	$("#button1").before($tr4);
	$("#button1").before($tr5);
	//当添加公司信息时，添加一个隐藏域
	$("#" + gongs).append(
			"<input id='" + count_num + "_aaa' type='hidden' value='5'>");
	//添加公司时给复制下来的元素重新定义name属性
	for ( var i = 0; i < 5; i++) {
		$("#" + count_num + "_aaa_" + i + " input[name],#" + count_num + "_aaa_" + i + " select[name]").each(
				function(i) {
					var inputName = this.name;
					if(this.type!="radio"){//排除复制是否选中的name
					//alert(inputName.substring(0,inputName.indexOf("[")+1));
					//alert(inputName.substring(inputName.indexOf("]"),inputName.length));
					inputName=inputName.substring(0,inputName.indexOf("[")+1)+(count_num-1)+inputName.substring(inputName.indexOf("]"),inputName.length);
					this.name = inputName;
					//判断次数(议价次数)value不清空
					if(inputName.indexOf("numbers")<0){
						this.value="";
					}
					}else{
						this.value=count_num;
					}
				});
	}
}

//删除公司
function delInfor2(obj) {
	var selectedTr = obj.parentNode.parentNode;
	var trId = selectedTr.id;
	trId = trId.substring(0, 1);
	var hidden_id = $("#" + trId + "_aaa").val();
	if (trId == 1) {
		alert("公司至少有一家!");
		return false;
	} else {
		for ( var i = hidden_id; i > 0; i--) {
			$("#" + trId + "_aaa_" + i).remove();
		}
		count_num--;
	}
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
	} else if (relationclient.value == "") {
		alert("关联客户不能为空!");
		relationclient.focus();
		return false;
	} else if (accreditationnum.value == "") {
		alert("评审编号不能为空!");
		accreditationnum.focus();
		return false;
	} else if (contractnum.value == "") {
		alert("合同号不能为空!");
		contractnum.focus();
		return false;
	} else if (voucherMoney.value == "") {
		alert("借款金额不能为空!");
		voucherMoney.focus();
		return false;
	}
}

 
</script>

</html>
