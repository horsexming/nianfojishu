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
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<h3>
					修改订单
					<s:if test="successMessage!=null">
						<br />
						<font color="red"><s:property value="successMessage" /> </font>
					</s:if>
				</h3>
				<form action="orderManager_update.action" method="post"
					onsubmit="return validate()" enctype="multipart/form-data">
					<table class="table" style="width: 50%;">
						<s:if test="status!='xieshang'&& status!='dingdan'">
							<tr>
								<td>
									客户名字：
									<select name="id" id="custome">
										<s:iterator id="cu" value="list">
											<s:if test="#cu.id == om.custome.id">
												<option value="${cu.id}" selected="selected">
													${cu.clientcompanyname}
												</option>
											</s:if>
											<s:else>
												<option value="${cu.id}">
													${cu.clientcompanyname}
												</option>
											</s:else>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									订单编号（内部）:
									<input type="text" style="margin-left: 12px;"
										name="om.orderNum" value="${om.orderNum}" />
								</td>
							</tr>
							<tr>
								<td>
									订单编号（外部）:
									<input type="text" style="margin-left: 12px;"
										name="om.outOrderNumber" value="${om.outOrderNumber}" />
								</td>
							</tr>
							<tr>
								<td>
									跟单人：
									<input type="text" style="margin-left: 18px;"
										name="om.documentaryPeople" id="documentaryPeople"
										value="${om.documentaryPeople}" />
								</td>
							</tr>
							<tr>
								<td>
									跟单人部门：
									<input type="text" style="margin-left: 18px;" name="om.dept"
										id="dept" value="${om.dept}" />
								</td>
							</tr>
							<tr>
								<td>
									开单人：
									<input type="text" style="margin-left: 20px;"
										name="om.billingPeople" id="billingPeople"
										value="${om.billingPeople}" />
								</td>
							</tr>
							<tr>
								<td>
									订单文件：
									<input type="file" name="orderFile" id="orderFile2"
										value="${om.orderFil}" />
								</td>
							</tr>
							<tr>
								<td>
									合同文件：
									<input type="file" name="orderFile" id="orderFile1"
										disabled="disabled" />

								</td>
							</tr>
							<tr>
								<td align="left">
									订单类型：
									<select name="om.orderType" style="width: 155px;">
										<option value="${om.orderType}">
											${om.orderType}
										</option>
										<option value="正式">
											正式
										</option>
										<option value="预测">
											预测
										</option>
										<option value="备货">
											备货
										</option>
									</select>
								</td>
							</tr>
					</table>
					<input type="button" value="添加产品" class="input" onclick="addshow(${om.id})">
					<input type="button" value="添加配件" class="input" onclick="showAddParts('')"/>
					<table width="100%" border="0" style="border-collapse: collapse;"
						id="table" class="table">
						<tr bgcolor="#c0dcf2" height="50px" id="btn" align="center">
							<td>
								序号
							</td>
							<td id="partNumber">
								件号
							</td>
							<td id="name">
								产品名称
							</td>
							<td id="type">
								型别
							</td>
							<td>
								数量
							</td>
							<td id="hsPrice">
								不含税价
							</td>
							<td id="hsPrice">
								含税价
							</td>
							<td id="hsPrice">
								税率
							</td>
							<td id="hsPrice">
								总价(含税)
							</td>

							<td>
								价格明细
							</td>
							<td>
								单位
							</td>
							<td>
								交付日期
							</td>
							<td>
								相关备货
							</td>
							<td>
								备注
							</td>
							<td>
								操作
							</td>
						</tr>
						<tr id="un">
							<s:if test="errorMessage==null">
								<td colspan="15" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
									${errorMessage}
							</s:else>
							</td>
						</tr>
					</table>
					</s:if>
					<s:elseif test="status=='dingdan'">
						<tr>
							<td>
								客户名字： ${om.custome.clientcompanyname}
								<input type="hidden" name="id" value="${om.custome.id}">
							</td>
						</tr>
						<tr>
							<td>
								订单编号（内部）:
								<input type="hidden" value="${status}" name="status" />
								<input type="text" name="om.orderNum" value="${om.orderNum}" />
							</td>
						</tr>
						<tr>
							<td>
								订单编号（外部）:
								<input type="text" name="om.outOrderNumber"
									value="${om.outOrderNumber}" />
							</td>
						</tr>
						<tr>
							<td>
								跟单人： ${om.documentaryPeople}
							</td>
						</tr>
						<tr>
							<td>
								开单人： ${om.billingPeople}
							</td>
						</tr>
						<tr>
							<td>
								交付日期： ${om.paymentDate}
							</td>
						</tr>
						<tr>
							<td>
								协商交付日期： ${om.paymentDate2}
							</td>
						</tr>
					</s:elseif>
					<s:else>
						<tr>
							<td>
								客户名字： ${om.custome.clientcompanyname}
								<input type="hidden" name="om.outOrderNumber"
									value="${om.custome.id}" />
							</td>
						</tr>
						<tr>
							<td>
								订单编号（内部）: ${om.orderNum}
							</td>
						</tr>
						<tr>
							<td>
								订单编号（外部）: ${om.outOrderNumber}
							</td>
						</tr>
						<tr>
							<td>
								跟单人： ${om.documentaryPeople}
							</td>
						</tr>
						<tr>
							<td>
								开单人： ${om.billingPeople}
							</td>
						</tr>
						<tr>
							<td>
								交付日期： ${om.paymentDate}
							</td>
						</tr>
						<tr>
							<td>
								协商交付日期：
								<input class="Wdate" type="text" name=om.paymentDate2
									id="paymentDate2" value="${om.paymentDate2}"
									onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});"
									onblur="checkNeedFile()" />
								<input type="hidden" value="${status}" name="status" id="status">
							</td>
						</tr>
						<tr>
							<td>
								合同文件：
								<input type="file" name="orderFile" id="orderFile1" />
							</td>
						</tr>
					</s:else>
					<tr align="center">
						<td>
							<input type="hidden" name="om.id" value="${om.id}" />
							<input type="hidden" name="om.conversionStatus" value="NO" />
							<input type="submit" value="提交 "
								style="width: 80px; height: 50px;" />
						</td>
					</tr>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}' />
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var oldpayDate2 = "${om.paymentDate2}";
	var newpayDate2 = $("#paymentDate2").val();
	var Wdates = $(".Wdate");
	if (oldpayDate2 == null) {
		oldpayDate2 = "";
	}
	if (newpayDate2 == null) {
		newpayDate2 = "";
	}
	if (oldpayDate2 != newpayDate2) {
		if ($("#orderFile1").val() == null || $("#orderFile1").val() == '') {
			alert("修改协商交付日期需要重新上传合同!");
			return false;
		}
	}
	var documentaryPeople = document.getElementById("documentaryPeople").value;
	var billingPeople = document.getElementById("billingPeople").value;
	if (documentaryPeople == "") {
		alert("请选择跟单人!");
		return false;
	}
	if (billingPeople == "") {
		alert("请输入开单人!");
		return false;
	}
	if (Wdates != null && Wdates.length > 0 && '${status}' != 'yc') {
		for ( var i = 0; i < Wdates.length; i++) {
			/*var num = $("#sp_"+i).val();
			if(num==null||num==""){
				alert("请填写第" + (i + 1) + "行的数量")
				$("#sp_"+i).focus();
				return false;
			}else if(num<=0){
				alert("请填写第" + (i + 1) + "行的数量不能小于0")
				$("#sp_"+i).focus();
				return false;
			}*/
			if (Wdates[i].value == "") {
				alert("请填写第" + (i + 1) + "行的交付日期")
				$(Wdates[i]).focus();
				return false;
			}
		}
	}
}
function checkNeedFile() {
	var oldpayDate2 = "${om.paymentDate2}";
	var newpayDate2 = $("#paymentDate2").val();
	if (oldpayDate2 == null) {
		oldpayDate2 = "";
	}
	if (newpayDate2 == null) {
		newpayDate2 = "";
	}
	if (oldpayDate2 != newpayDate2) {
		$("#htTr").show();
		$("#orderFile1").removeAttr("disabled");
	} else {
		$("#htTr").show();
		$("#orderFile1").attr("disabled", "disabled");
	}
}
function del(obj) {
	var table = document.getElementById("table");
	obj = 'tr_' + obj;
	table.deleteRow($("#" + obj).index());
	var index = table.rows.length;
	for ( var i = 1; i < index - 1; i++) {
		table.rows[i].cells[0].innerHTML = i;
		var list9 = table.rows[i].cells[5].getElementsByTagName("input");
		list9[0].name = "pmList[" + (i - 1) + "].taxprice"
		var list8 = table.rows[i].cells[5].getElementsByTagName("input");
		list8[0].name = "pmList[" + (i - 1) + "].bhsPrice"
		var list7 = table.rows[i].cells[5].getElementsByTagName("input");
		list7[0].name = "pmList[" + (i - 1) + "].priceId"
		var list6 = table.rows[i].cells[5].getElementsByTagName("input");
		list6[0].name = "pmList[" + (i - 1) + "].unit"
		var list5 = table.rows[i].cells[5].getElementsByTagName("input");
		list5[0].name = "pmList[" + (i - 1) + "].num"
		var list4 = table.rows[i].cells[4].getElementsByTagName("input");
		list4[0].name = "pmList[" + (i - 1) + "].unitPrice"
		var list3 = table.rows[i].cells[3].getElementsByTagName("input");
		list3[0].name = "pmList[" + (i - 1) + "].type"
		var list2 = table.rows[i].cells[2].getElementsByTagName("input");
		list2[0].name = "pmList[" + (i - 1) + "].name"
		var list1 = table.rows[i].cells[1].getElementsByTagName("input");
		list1[0].name = "pmList[" + (i - 1) + "].pieceNumber"
		var list6 = table.rows[i].cells[6].getElementsByTagName("input");
		list1[0].name = "pmList[" + (i - 1) + "].priceId"
	}
}

//显示添加配件窗口
function showAddParts(markId){
	if(markId==''){
		$("#xiugaiIframe").attr("src","${pageContext.request.contextPath}/ProcardTemplateAction!findAllProcardTemp.action?pageStatus=lp&type=lp");
		chageDiv('block');
	}else{
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/ProcardTemplateAction!getProcardByMarkId.action",
			dataType:"JSON",
			data:{
				markId:markId
			},
			success:function(data){
				$("#xiugaiIframe").attr("src","${pageContext.request.contextPath}/System/SOP/produce/Template_addParts.jsp?pageStatus=lp&id="+data.id+"&markId="+markId);
				chageDiv('block');
			}
		});
	}
}

//计算总价格
function computeSumPrice(index){
	var count = $("#sp_"+index).val();
	var price= $("#hsprice_"+index).val();
	var sumPrice = (parseFloat(count) * parseFloat(price)).toFixed(2);
	$("#unitPriceText_"+index).text(sumPrice);
	$("#unitPrice_"+index).val(sumPrice);
	releaseBtn();
}
function releaseBtn(){
	var addCount = $(".addCount");
	for(var i = 0;i<addCount.length;i++){
		if(addCount[i].value=='' || addCount[i].value==null ){
			$("#sub").attr("disabled","disabled");
		}else{
			$("#sub").removeAttr("disabled");
		}
	}
	
	if($(".nonePrice").length>0){
		$("#sub").attr("disabled","disabled");
	}
	
}
function addshow(id) {
	var custome = $("#custome").val();
	$("#xiugaiIframe").attr(
			"src",
			"pieceNum_queryPieceNumByCondition.action?pagestatus=Addorder&id="
					+ custome + "&tag=KH");
	chageDiv('block');
}
$(document).ready(function() {
	var rebeack = $("#rebeack").val();
	if (rebeack == "修改成功!" || rebeack == "修改失败!") {
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
</script>
	</body>
</html>
