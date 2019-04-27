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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					成品库入外购件库调仓申请
					<s:if test="successMessage!=null">
					<br/><font color="red"><s:property value="successMessage"/> </font>
					</s:if>
				</h2>
				<form action="goodsAction!saveCPChangeWG.action"
					onsubmit="return checkFormm()" method="post">
<%--					<input type="hidden" name="role" value="${role}">--%>
					<input type="hidden" name="cpChangeWg.goodsId" value="${goods.goodsId}">
					<table class="table" style="width: 95%;">
						<tr>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="cpChangeWg.goodsLotId"
									value="${goods.goodsLotId }" readonly="readonly" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input type="text" name="cpChangeWg.goodsMarkId" id ="sellMarkId"
									value="${goods.goodsMarkId}" readonly="readonly" /><s:if test="goods.processNo!=null"><font color="red">(工序:${goods.processNo})</font></s:if>
							</td>
							<th>
								品名:
							</th>
							<td>
								<input type="text" name="cpChangeWg.goodsFullName" 
									value="${goods.goodsFullName }" readonly="readonly" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="cpChangeWg.goodsFormat" id="sellFormat"
									value="${goods.goodsFormat }" readonly="readonly" />
							</td>

							</th>
						</tr>
						<tr>
							<th>
								最大数量：
							</th>
							<td>
								<input type="text"  id="maxCount"
									value="${goods.goodsCurQuantity }" disabled="disabled"/>
									<span id="span"></span>
							</td>
							<th>
								计量单位：
							</th>
							<td>
								<input type="text" name="cpChangeWg.goodsUnit"
									value="${goods.goodsUnit }" readonly="readonly" />
							</td>
							<th>
								库别：
							</th>
							<td>${goods.goodsClass}->外购件库
								<input type="hidden" name="cpChangeWg.cpGoodsClass" value="成品库">
								<input type="hidden" name="cpChangeWg.wgGoodsClass" value="外购件库">
							</td>
							<th>
								仓区：
							</th>
							<td id="cangqu_td">${goods.goodHouseName}->
									<input type="hidden" name="cpChangeWg.cpGoodHouseName" value="${goods.goodHouseName}">
									<SELECT name="cpChangeWg.wgGoodHouseName" id="goodHouseName" onchange="changvalue1(this)">
<%--										<option></option>--%>
									</SELECT>
							</td>
							

						</tr>
						<tr>
							<th>
								调仓数量:
							</th>
							<td>
								<input type="text" name="cpChangeWg.changeCount" id="changeCount"
									value="${cpChangeWg.changeCount}" onchange=" numyanzhen(this)"/>
									<span style="color: red">*</span>
							</td>
							
							
							<th>
							  操作人
							</th>
							<td>
								<input id="sellCompanyPeople" type="text" name="cpChangeWg.changePeoleName" value="${loginUser.name}"/>
								<input type="hidden" name="cpChangeWg.changePeopleId" value="${loginUser.id}">
							</td>

							<th>
								库位:
							</th>
							<td>${goods.goodsPosition}->
									<input type="hidden" name="cpChangeWg.cpGoodsPosition" value="${goods.goodHouseName}">
								<select name="cpChangeWg.wgGoodsPosition" id="goodsPosition">
<%--										<option></option>--%>
								</select>
							</td>	
							<th>
								出库类型
							</th>
							<td>
							<input type="text" name="sell.style" id="sellStyle" value="调仓出库" readonly="readonly"/>
							</td>
							
						</tr>
						<tr>
                            <th>供料属性</th>
							<td>
								<select name="sell.kgliao">
								<option></option>
									<option value="<s:property value="goodsStore.kgliao" />">
										<s:if test="goodsStore.kgliao=='TK'">
										自购(TK)
										</s:if>
										<s:elseif test="goodsStore.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
										<s:elseif test="goodsStore.kgliao=='CS'">
										客供(CS)
										</s:elseif>
										<s:elseif test="goodsStore.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
									</option>
									<option value="TK">
									自购(TK)
								</option>
								<option value="TK AVL">
									指定供应商(TK AVL)
								</option>
								<option value="CS">
									客供(CS)
								</option>
								<option value="TK Price">
									完全指定(TK Price)
								</option>
								</select>
							</td>
							<th>
								申请日期
							</th>
							<td>
								<input class="Wdate" type="text" id="goodsChangeTime"
									name="cpChangeWg.changeDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<span style="color: red">*</span>
							</td>
							<td colspan="4"></td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" value="调仓" class="input" />
								
								&nbsp;
								<input type="reset" value="取消" class="input" />
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	
	<SCRIPT type="text/javascript">
$(function(){
	//仓区
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:'外购件库'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
			}
		}
	});
})

//库位
function changvalue1(obj){
	
	if(obj!=null && obj.value != ""){
		$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:'外购件库',
			cangqu:obj.value
		},
		dataType : "json",
		success : function(data) {
			
			if (data != null) {
				$("#goodsPosition").empty();
				$(data).each(function(){
						$("#goodsPosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
		}
	});
	}
}
//调仓数量
function numyanzhen(obj) {
	if(obj.value==null || obj.value=="" || obj.value==0){
		alert("数量输入有误，请重输");
		obj.value = "";
		obj.focus();
	}
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		alert("数量输入有误，请重输");
		obj.value = "";
		obj.focus();
	} else{
		var maxCount=$("#maxCount").val();
		if(obj.value-maxCount>0){
			alert("库存不足，请检查");
			obj.value =maxCount;
			obj.focus();
			
		}
	}
}
//提交验证
function checkFormm() {
	var warehouse = document.getElementById("warehouse");
	var sellStyle = document.getElementById("sellStyle");
	var changeCount = document.getElementById("changeCount");
	if ($("#goodsChangeTime").val() == "") {
		alert("请填写出库时间!");
		$("#goodsChangeTime").focus();
		return false;
	}
	if($("#sellSendnum").val()!=""&&$("#sellCompanyName").val()==""){
		alert("请填写客户!");
		return false;
	}
	if($("#sellSendnum").val()!=""&&$("#sellCompanyPeople").val()==""){
		alert("请填写对方负责人!");
		return false;
	}
	if (changeCount.value == "") {
			alert("转换数量不能为空!");
			changeCount.focus();
			return false;
	}

	return true;
}
	</SCRIPT>
</html>
